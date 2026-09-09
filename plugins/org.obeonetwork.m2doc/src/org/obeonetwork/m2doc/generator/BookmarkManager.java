/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.w3c.dom.Node;

/**
 * Manage bookmarks.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class BookmarkManager {

    /** WordprocessingML namespace. */
    private static final String WORDPROCESSINGML_NAMESPACE =
            "http://schemas.openxmlformats.org/wordprocessingml/2006/main";

    /**
     * The buffer size.
     */
    private static final int BUFFER_SIZE = 16;

    /**
     * The reference text.
     */
    private static final String REF_TAG = " REF %s \\h ";

    /**
     * The reference section.
     */
    private static final String SECTION_REF_TAG = " REF %s \\n \\h ";

    /**
     * The reference page.
     */
    private static final String PAGE_REF_TAG = " PAGEREF %s \\h ";

    /**
     * Known bookmarks so far.
     */
    private final Map<String, CTBookmark> bookmarks = new LinkedHashMap<>();

    /**
     * Open bookmarks.
     */
    private final Map<String, CTBookmark> startedBookmarks = new LinkedHashMap<>();

    /**
     * Pending references for a given bookmark name.
     */
    private final Map<String, Set<CTText>> pendingReferences = new HashMap<>();

    /**
     * The {@link Map} of optional references to its text.
     */
    private final Map<CTText, String> optionalReferences = new HashMap<>();

    /**
     * The mapping from {@link XmlObject} to bookmark name.
     */
    private final Map<XmlObject, String> xmlObjectToName = new HashMap<>();

    /**
     * Bookmark name to reference ID.
     */
    private final Map<String, byte[]> referenceIDs = new HashMap<>();

    /**
     * Position to insert message for a given reference or bookmark.
     */
    private final Map<XmlObject, XWPFRun> messagePositions = new HashMap<>();

    /**
     * Native Word bookmark IDs remapped while reconstructed content is generated.
     */
    private final Map<BigInteger, BigInteger> nativeBookmarkIDs = new HashMap<>();

    /**
     * Copies native Word bookmark markers located before the given source run.
     * Only markers between the beginning of the top-level paragraph child and
     * the previous content-bearing sibling are copied.
     *
     * @param sourceRun
     *            the source run
     * @param outputParagraph
     *            the generated paragraph
     */
    public void copyNativeBookmarksBefore(XWPFRun sourceRun, XWPFParagraph outputParagraph) {
        copyNativeBookmarkMarkers(sourceRun, outputParagraph, false);
    }

    /**
     * Copies native Word bookmark markers located after the given source run.
     * Only markers up to the next content-bearing sibling are copied.
     *
     * @param sourceRun
     *            the source run
     * @param outputParagraph
     *            the generated paragraph
     */
    public void copyNativeBookmarksAfter(XWPFRun sourceRun, XWPFParagraph outputParagraph) {
        copyNativeBookmarkMarkers(sourceRun, outputParagraph, true);
    }

    /**
     * Copies native bookmark markers from a paragraph that contains no runs or
     * other content-bearing elements. Such paragraphs are used by Word as the
     * starting point of bookmarks spanning a table row.
     *
     * @param sourceParagraph
     *            the source paragraph
     * @param outputParagraph
     *            the generated paragraph
     */
    public void copyNativeBookmarksFromEmptyParagraph(XWPFParagraph sourceParagraph,
            XWPFParagraph outputParagraph) {
        final CTP sourceCTP = sourceParagraph.getCTP();
        final Node paragraphNode = sourceCTP.getDomNode();

        for (Node node = paragraphNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (isContentBearingElement(node)) {
                return;
            }
        }

        for (Node node = paragraphNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (isBookmarkMarker(node)) {
                copyNativeBookmarkMarker(sourceCTP, node, outputParagraph);
            }
        }
    }

    /**
     * Copies bookmark markers occurring before the first cell of a table row.
     */
    public void copyNativeBookmarksBeforeRow(CTRow sourceRow, CTRow outputRow) {
        final Node rowNode = sourceRow.getDomNode();
        for (Node node = rowNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE && "tc".equals(node.getLocalName())) {
                break;
            }
            copyNativeRowBookmarkMarker(sourceRow, node, outputRow);
        }
    }

    /**
     * Copies bookmark markers occurring after the last cell of a table row.
     */
    public void copyNativeBookmarksAfterRow(CTRow sourceRow, CTRow outputRow) {
        final Node rowNode = sourceRow.getDomNode();
        final List<Node> markers = new ArrayList<>();
        for (Node node = rowNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
            if (node.getNodeType() == Node.ELEMENT_NODE && "tc".equals(node.getLocalName())) {
                break;
            }
            if (isBookmarkMarker(node)) {
                markers.add(0, node);
            }
        }
        for (Node marker : markers) {
            copyNativeRowBookmarkMarker(sourceRow, marker, outputRow);
        }
    }

    /**
     * Copies native bookmark markers located after the cell at the given
     * logical index and before the following cell. Word can place a
     * bookmarkEnd directly under w:tr between two w:tc elements.
     *
     * @param sourceRow
     *            the source row
     * @param outputRow
     *            the generated row
     * @param cellIndex
     *            zero-based index of the source cell just generated
     */
    public void copyNativeBookmarksAfterCell(CTRow sourceRow, CTRow outputRow, int cellIndex) {
        final Node rowNode = sourceRow.getDomNode();
        int currentCellIndex = -1;
        Node node = rowNode.getFirstChild();

        while (node != null) {
            if (isWordElement(node, "tc")) {
                currentCellIndex++;
                if (currentCellIndex == cellIndex) {
                    node = node.getNextSibling();
                    break;
                }
            }
            node = node.getNextSibling();
        }

        while (node != null && !isWordElement(node, "tc")) {
            if (isBookmarkMarker(node)) {
                copyNativeRowBookmarkMarker(sourceRow, node, outputRow);
            }
            node = node.getNextSibling();
        }
    }

    /**
     * Copies bookmark starts located in a row cell when the matching bookmark
     * end is a direct child of the table row. Word uses this structure for
     * cross-references to complete rows in reference tables.
     *
     * @param sourceRow
     *            the source row
     * @param outputRow
     *            the generated row
     */
    public void copyNativeBookmarksSpanningRow(XWPFTableRow sourceRow, XWPFTableRow outputRow) {
        final Set<BigInteger> rowEndIDs = new LinkedHashSet<>();
        final Node sourceRowNode = sourceRow.getCtRow().getDomNode();
        for (Node node = sourceRowNode.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (isWordElement(node, "bookmarkEnd")) {
                final BigInteger id = getWordBigIntegerAttribute(node, "id");
                if (id != null) {
                    rowEndIDs.add(id);
                }
            }
        }

        final List<CTTc> sourceCells = sourceRow.getCtRow().getTcList();
        final List<CTTc> outputCells = outputRow.getCtRow().getTcList();
        final int cellCount = Math.min(sourceCells.size(), outputCells.size());
        for (int cellIndex = 0; cellIndex < cellCount; cellIndex++) {
            final List<CTP> sourceParagraphs = sourceCells.get(cellIndex).getPList();
            final List<CTP> outputParagraphs = outputCells.get(cellIndex).getPList();
            final int paragraphCount = Math.min(sourceParagraphs.size(), outputParagraphs.size());
            for (int paragraphIndex = 0; paragraphIndex < paragraphCount; paragraphIndex++) {
                final Node paragraphNode = sourceParagraphs.get(paragraphIndex).getDomNode();
                for (Node node = paragraphNode.getFirstChild(); node != null; node = node.getNextSibling()) {
                    if (isWordElement(node, "bookmarkStart")) {
                        final BigInteger id = getWordBigIntegerAttribute(node, "id");
                        final String name = getWordAttribute(node, "name");
                        if (id != null && name != null && rowEndIDs.contains(id)
                                && !containsBookmark(outputRow.getCtRow(), name)) {
                            copyNativeBookmarkStart(name, id, outputParagraphs.get(paragraphIndex));
                        }
                    }
                }
            }
        }
    }

    private boolean containsBookmark(CTRow row, String name) {
        for (CTTc cell : row.getTcList()) {
            for (CTP paragraph : cell.getPList()) {
                for (CTBookmark bookmark : paragraph.getBookmarkStartList()) {
                    if (name.equals(bookmark.getName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void copyNativeRowBookmarkMarker(CTRow sourceRow, Node marker, CTRow outputRow) {
        final BigInteger sourceID = getWordBigIntegerAttribute(marker, "id");
        if (sourceID == null) {
            return;
        }
        if (isWordElement(marker, "bookmarkStart")) {
            final String name = getWordAttribute(marker, "name");
            if (name != null) {
                final CTBookmark target = outputRow.addNewBookmarkStart();
                final BigInteger newID = getRandomID();
                target.setName(name);
                target.setId(newID);
                nativeBookmarkIDs.put(sourceID, newID);
            }
        } else if (isWordElement(marker, "bookmarkEnd")) {
            final CTMarkupRange target = outputRow.addNewBookmarkEnd();
            final BigInteger newID = nativeBookmarkIDs.remove(sourceID);
            if (newID != null) {
                target.setId(newID);
            } else {
                target.setId(sourceID);
            }
        }
    }

    private void copyNativeBookmarkStart(String name, BigInteger sourceID, CTP outputParagraph) {
        final CTBookmark target = outputParagraph.addNewBookmarkStart();
        final BigInteger newID = getRandomID();
        target.setName(name);
        target.setId(newID);
        nativeBookmarkIDs.put(sourceID, newID);
    }

    private boolean isWordElement(Node node, String localName) {
        return node != null && node.getNodeType() == Node.ELEMENT_NODE
                && localName.equals(node.getLocalName());
    }

    private String getWordAttribute(Node node, String localName) {
        final Node attribute = node.getAttributes() != null
                ? node.getAttributes().getNamedItemNS(WORDPROCESSINGML_NAMESPACE, localName)
                : null;
        return attribute != null ? attribute.getNodeValue() : null;
    }

    private BigInteger getWordBigIntegerAttribute(Node node, String localName) {
        final String value = getWordAttribute(node, localName);
        try {
            return value != null ? new BigInteger(value) : null;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Copies the native bookmark markers adjacent to a source run while
     * preserving their order in the paragraph.
     *
     * @param sourceRun
     *            the source run
     * @param outputParagraph
     *            the output paragraph
     * @param after
     *            {@code true} to inspect following siblings, {@code false} to
     *            inspect preceding siblings
     */
    private void copyNativeBookmarkMarkers(XWPFRun sourceRun, XWPFParagraph outputParagraph, boolean after) {
        final XWPFParagraph sourceParagraph = (XWPFParagraph) sourceRun.getParent();
        final CTP sourceCTP = sourceParagraph.getCTP();
        final Node paragraphNode = sourceCTP.getDomNode();
        final Node anchor = getParagraphChild(sourceRun.getCTR().getDomNode(), paragraphNode);

        if (anchor == null) {
            return;
        }

        Node node = after ? anchor.getNextSibling() : anchor.getPreviousSibling();
        final List<Node> markers = new ArrayList<>();

        while (node != null && !isContentBearingElement(node)) {
            if (isBookmarkMarker(node)) {
                if (after) {
                    markers.add(node);
                } else {
                    markers.add(0, node);
                }
            }
            node = after ? node.getNextSibling() : node.getPreviousSibling();
        }

        // Markers between two content elements are owned by the element on
        // their left. This prevents copying them both after one run and before
        // the following run, while still allowing the same template block to
        // be generated more than once by a repetition.
        if (!after && node != null) {
            return;
        }

        for (Node marker : markers) {
            copyNativeBookmarkMarker(sourceCTP, marker, outputParagraph);
        }
    }

    /**
     * Gets the direct child of the paragraph containing the given node.
     */
    private Node getParagraphChild(Node node, Node paragraphNode) {
        Node current = node;
        while (current != null) {
            final Node parent = current.getParentNode();
            if (parent != null && parent.isSameNode(paragraphNode)) {
                return current;
            }
            current = parent;
        }
        return null;
    }

    /**
     * Tells whether a node is a native Word bookmark marker.
     */
    private boolean isBookmarkMarker(Node node) {
        return node.getNodeType() == Node.ELEMENT_NODE
                && ("bookmarkStart".equals(node.getLocalName()) || "bookmarkEnd".equals(node.getLocalName()));
    }

    /**
     * Tells whether a paragraph child carries generated or copied content.
     */
    private boolean isContentBearingElement(Node node) {
        if (node.getNodeType() != Node.ELEMENT_NODE) {
            return false;
        }
        final String localName = node.getLocalName();
        return "r".equals(localName) || "hyperlink".equals(localName) || "fldSimple".equals(localName)
                || "sdt".equals(localName) || "smartTag".equals(localName) || "customXml".equals(localName);
    }

    /**
     * Copies one native bookmark marker.
     */
    private void copyNativeBookmarkMarker(CTP sourceParagraph, Node marker, XWPFParagraph outputParagraph) {
        if ("bookmarkStart".equals(marker.getLocalName())) {
            final CTBookmark source = findBookmarkStart(sourceParagraph, marker);
            if (source != null) {
                copyNativeBookmarkStart(source, outputParagraph);
            }
        } else if ("bookmarkEnd".equals(marker.getLocalName())) {
            final CTMarkupRange source = findBookmarkEnd(sourceParagraph, marker);
            if (source != null) {
                copyNativeBookmarkEnd(source, outputParagraph);
            }
        }
    }

    private CTBookmark findBookmarkStart(CTP paragraph, Node marker) {
        for (CTBookmark bookmark : paragraph.getBookmarkStartList()) {
            if (bookmark.getDomNode().isSameNode(marker)) {
                return bookmark;
            }
        }
        return null;
    }

    private CTMarkupRange findBookmarkEnd(CTP paragraph, Node marker) {
        for (CTMarkupRange range : paragraph.getBookmarkEndList()) {
            if (range.getDomNode().isSameNode(marker)) {
                return range;
            }
        }
        return null;
    }

    private void copyNativeBookmarkStart(CTBookmark source, XWPFParagraph outputParagraph) {
        copyNativeBookmarkStart(source, outputParagraph.getCTP());
    }

    private void copyNativeBookmarkStart(CTBookmark source, CTP outputParagraph) {
        final CTBookmark target = outputParagraph.addNewBookmarkStart();
        target.set(source.copy());
        final BigInteger newID = getRandomID();
        target.setId(newID);
        nativeBookmarkIDs.put(source.getId(), newID);
    }

    private void copyNativeBookmarkEnd(CTMarkupRange source, XWPFParagraph outputParagraph) {
        final CTMarkupRange target = outputParagraph.getCTP().addNewBookmarkEnd();
        target.set(source.copy());
        final BigInteger newID = nativeBookmarkIDs.remove(source.getId());
        if (newID != null) {
            target.setId(newID);
        }
    }

    /**
     * Starts a bookmark in the given {@link XWPFParagraph} with the given name.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @param paragraph
     *            the current {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     */
    public void startBookmark(GenerationResult result, XWPFParagraph paragraph, String name) {
        if (bookmarks.containsKey(name)) {
            result.addMessage(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR,
                    "Can't start duplicated bookmark " + name));
        } else {
            final CTBookmark bookmark = paragraph.getCTP().addNewBookmarkStart();
            // we create a new run for future error messages.
            messagePositions.put(bookmark, paragraph.createRun());
            bookmark.setName(name);
            final BigInteger id = getRandomID();
            bookmark.setId(id);
            bookmarks.put(name, bookmark);
            xmlObjectToName.put(bookmark, name);
            startedBookmarks.put(name, bookmark);
            Set<CTText> pendingRefs = pendingReferences.remove(name);
            if (pendingRefs != null) {
                for (CTText pendingRef : pendingRefs) {
                    xmlObjectToName.remove(pendingRef);
                    // we remove the run created for error messages.
                    final XWPFRun run = messagePositions.get(pendingRef);
                    final IRunBody parent = run.getParent();
                    if (parent instanceof XWPFParagraph) {
                        ((XWPFParagraph) parent).removeRun(((XWPFParagraph) parent).getRuns().indexOf(run));
                    } else {
                        throw new IllegalStateException("this should not happend");
                    }
                }
            }
        }
    }

    /**
     * Ends the bookmark with the given name.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @param paragraph
     *            the current {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     */
    public void endBookmark(GenerationResult result, XWPFParagraph paragraph, String name) {
        final CTBookmark bookmark = startedBookmarks.remove(name);
        if (bookmark != null) {
            final CTMarkupRange range = paragraph.getCTP().addNewBookmarkEnd();
            range.setId(bookmarks.get(name).getId());
            // we remove the run created for error messages.
            final XWPFRun run = messagePositions.get(bookmark);
            final IRunBody parent = run.getParent();
            if (parent instanceof XWPFParagraph) {
                ((XWPFParagraph) parent).removeRun(((XWPFParagraph) parent).getRuns().indexOf(run));
            } else {
                throw new IllegalStateException("this should not happend");
            }
        } else if (bookmarks.containsKey(name)) {
            result.addMessage(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR,
                    "Can't end already closed bookmark " + name));
        } else {
            result.addMessage(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR,
                    "Can't end not existing bookmark " + name));
        }
    }

    /**
     * Gets a random ID.
     * 
     * @return a random ID
     */
    private BigInteger getRandomID() {
        final UUID uuid = UUID.randomUUID();

        ByteBuffer buffer = ByteBuffer.wrap(new byte[BUFFER_SIZE]);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());

        return new BigInteger(buffer.array()).abs();
    }

    /**
     * Inserts a reference with the bookmark page to the given name in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *            error should be inserted
     */
    public void insertPageReference(XWPFParagraph paragraph, String name, boolean optional) {
        insertReference(paragraph, name, null, PAGE_REF_TAG, optional);
    }

    /**
     * Inserts a reference with the bookmark section to the given name in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *            error should be inserted
     */
    public void insertSectionReference(XWPFParagraph paragraph, String name, boolean optional) {
        insertReference(paragraph, name, null, SECTION_REF_TAG, optional);
    }

    /**
     * Inserts a reference with the bookmark text to the given name in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *            error should be inserted
     */
    public void insertTextReference(XWPFParagraph paragraph, String name, boolean optional) {
        insertReference(paragraph, name, null, REF_TAG, optional);
    }

    /**
     * Inserts a reference with a custom text to the given name in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param text
     *            the text
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *            error should be inserted
     */
    public void insertCustomTextReference(XWPFParagraph paragraph, String name, String text, boolean optional) {
        insertReference(paragraph, name, text, REF_TAG, optional);
    }

    /**
     * Inserts a pending reference to the given name in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param text
     *            the text
     * @param format
     *            the instruction text format
     * @param optional
     *            <code>true</code> if this reference can be omitted when the bookmark declaration doesn't exists, <code>false</code> if an
     *            error
     */
    public void insertReference(XWPFParagraph paragraph, String name, String text, String format, boolean optional) {
        final CTBookmark bookmark = bookmarks.get(name);
        if (bookmark != null) {
            insertReference(paragraph, bookmark, text, format);
        } else {
            final XWPFRun messageRun = paragraph.createRun();
            final CTText ref = insertPendingReference(paragraph, name, text);
            if (optional) {
                optionalReferences.put(ref, text);
            }
            ref.setStringValue(String.format(format, name));
            messagePositions.put(ref, messageRun);
            Set<CTText> pendingRefs = pendingReferences.get(name);
            if (pendingRefs == null) {
                pendingRefs = new LinkedHashSet<>();
                pendingReferences.put(name, pendingRefs);
            }
            pendingRefs.add(ref);
            xmlObjectToName.put(ref, name);
        }
    }

    /**
     * Inserts a reference to the given {@link CTBookmark} with the given text in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param bookmark
     *            the {@link CTBookmark}
     * @param text
     *            the text
     * @param format
     *            the instruction text format
     */
    private void insertReference(XWPFParagraph paragraph, CTBookmark bookmark, String text, String format) {
        final String name = bookmark.getName();
        final CTText pendingCTText = insertPendingReference(paragraph, name, text);
        pendingCTText.setStringValue(String.format(format, name));
    }

    /**
     * Inserts a reference to the given {@link CTBookmark} with the given text in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param text
     *            the text
     * @return the {@link CTText} corresponding to the reference.
     */
    private CTText insertPendingReference(XWPFParagraph paragraph, String name, String text) {
        final byte[] id = getReferenceID(name);
        final XWPFRun beginRun = paragraph.createRun();
        beginRun.getCTR().setRsidR(id);
        beginRun.getCTR().addNewFldChar().setFldCharType(STFldCharType.BEGIN);

        final XWPFRun preservedRun = paragraph.createRun();
        preservedRun.getCTR().setRsidR(id);
        final CTText pgcttext = preservedRun.getCTR().addNewInstrText();
        pgcttext.setSpace(Space.PRESERVE);

        final XWPFRun separateRun = paragraph.createRun();
        separateRun.getCTR().setRsidR(id);
        separateRun.getCTR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);

        final XWPFRun textRun = paragraph.createRun();
        textRun.getCTR().setRsidR(id);
        textRun.getCTR().addNewRPr().addNewNoProof();
        textRun.setText(text);
        textRun.setBold(true);

        final XWPFRun endRun = paragraph.createRun();
        endRun.getCTR().setRsidR(id);
        endRun.getCTR().addNewFldChar().setFldCharType(STFldCharType.END);

        return pgcttext;
    }

    /**
     * Gets the reference ID for the given bookmark name.
     * 
     * @param name
     *            the bookmark name
     * @return the reference ID for the given bookmark name
     */
    private byte[] getReferenceID(String name) {
        final byte[] res;

        final byte[] cachedID = referenceIDs.get(name);
        if (cachedID == null) {
            res = getRandomID().toByteArray();
            referenceIDs.put(name, res);
        } else {
            res = cachedID;
        }

        return res;
    }

    /**
     * Marks the bookmarks that are still open.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @return <code>true</code> if any open bookmarks was found, <code>false</code> otherwise
     */
    public boolean markOpenBookmarks(GenerationResult result) {
        final boolean res = !startedBookmarks.isEmpty();

        if (res) {
            for (Entry<String, CTBookmark> entry : startedBookmarks.entrySet()) {
                final XWPFRun positionRun = messagePositions.remove(entry.getValue());
                result.addMessage(M2DocUtils.setRunMessage(positionRun, ValidationMessageLevel.ERROR,
                        "unclosed bookmark " + entry.getKey()));
            }
        }

        return res;
    }

    /**
     * Marks dangling references.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @return <code>true</code> if any dangling reference was found, <code>false</code> otherwise
     */
    public boolean markDanglingReferences(GenerationResult result) {
        final boolean res = !pendingReferences.isEmpty();

        if (res) {
            for (Entry<String, Set<CTText>> entry : pendingReferences.entrySet()) {
                for (CTText ref : entry.getValue()) {
                    if (optionalReferences.containsKey(ref)) {
                        final String text = optionalReferences.remove(ref);
                        final XWPFRun refRun = messagePositions.remove(ref);
                        if (text != null) {
                            refRun.setText(text);
                        } else {
                            refRun.getCTR().setInstrTextArray(null);
                        }
                        final XWPFParagraph paragraph = (XWPFParagraph) refRun.getParent();
                        final int refRunIndex = paragraph.getRuns().indexOf(refRun);
                        paragraph.removeRun(refRunIndex - 1);
                        paragraph.removeRun(refRunIndex);
                        paragraph.removeRun(refRunIndex);
                        paragraph.removeRun(refRunIndex);
                        paragraph.removeRun(refRunIndex);
                    } else {
                        final XWPFRun refRun = messagePositions.remove(ref);
                        result.addMessage(M2DocUtils.insertMessageAfter(refRun, ValidationMessageLevel.ERROR,
                                "dangling reference for bookmark " + entry.getKey()));
                    }
                }
            }
        }

        return res;
    }

    /**
     * Updates the old {@link XmlObject} with the new one.
     * 
     * @param newObject
     *            the new {@link XmlObject}
     * @param oldObject
     *            the old {@link XmlObject}
     * @param outputBoby
     *            the output {@link IBody}
     * @param <T>
     *            the actual type of both {@link XmlObject}
     */
    public <T extends XmlObject> void updateXmlObject(T newObject, T oldObject, IBody outputBoby) {
        final String name = xmlObjectToName.remove(oldObject);
        if (name != null) {
            xmlObjectToName.put(newObject, name);
            if (bookmarks.get(name) == oldObject) {
                bookmarks.put(name, (CTBookmark) newObject);
                if (startedBookmarks.get(name) == oldObject) {
                    startedBookmarks.put(name, (CTBookmark) newObject);
                }
                newObject.validate();
            } else {
                final Set<CTText> refs = pendingReferences.get(name);
                if (refs != null && refs.contains(oldObject)) {
                    refs.remove(oldObject);
                    refs.add((CTText) newObject);
                    newObject.validate();
                }
            }
        }
        final XWPFRun run = messagePositions.remove(oldObject);
        if (run != null) {
            // TODO the run should be deleted but it has already been copied to the new document
            // find a way to delete the copy
            final XWPFRun newRun;
            if (outputBoby.getParagraphs().size() > 0) {
                newRun = outputBoby.getParagraphs().get(0).createRun();
            } else {
                newRun = null;
            }
            messagePositions.put(newObject, newRun);
        }
    }

    /**
     * Resets the bookmark manager.
     */
    public void reset() {
        bookmarks.clear();
        startedBookmarks.clear();
        pendingReferences.clear();
        xmlObjectToName.clear();
        referenceIDs.clear();
        messagePositions.clear();
        nativeBookmarkIDs.clear();
    }

}
