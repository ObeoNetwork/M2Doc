/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/**
 * Manage bookmarks.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class BookmarkManager {

    /**
     * The buffer size.
     */
    private static final int BUFFER_SIZE = 16;

    /**
     * The reference text.
     */
    private static final String REF_TAG = " REF %s \\h ";

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
                    pendingRef.setStringValue(String.format(REF_TAG, bookmark.getName()));
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
     * Inserts a pending reference to the given name in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param name
     *            the bookmark name
     * @param text
     *            the text
     */
    public void insertReference(XWPFParagraph paragraph, String name, String text) {
        final CTBookmark bookmark = bookmarks.get(name);
        if (bookmark != null) {
            insertReference(paragraph, bookmark, text);
        } else {
            final XWPFRun messageRun = paragraph.createRun();
            final CTText ref = insertPendingReference(paragraph, name, text);
            ref.setStringValue(String.format(REF_TAG, name));
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
     */
    private void insertReference(XWPFParagraph paragraph, CTBookmark bookmark, String text) {
        final String name = bookmark.getName();
        final CTText pendingCTText = insertPendingReference(paragraph, name, text);
        pendingCTText.setStringValue(String.format(REF_TAG, name));
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
                    final XWPFRun refRun = messagePositions.remove(ref);
                    result.addMessage(M2DocUtils.insertMessageAfter(refRun, ValidationMessageLevel.ERROR,
                            "dangling reference for bookmark " + entry.getKey()));
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

}
