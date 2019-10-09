/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.generator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackagePartName;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.obeonetwork.m2doc.parser.AbstractBodyParser;
import org.obeonetwork.m2doc.template.UserContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.w3c.dom.NodeList;

/**
 * Raw Copier manipulatin xml structure.
 * 
 * @author ohaegi
 */
public class RawCopier {

    /**
     * Mask.
     */
    private static final int MASK_16 = 16;

    /**
     * Mask.
     */
    private static final int MASK_0X100 = 0x100;

    /**
     * Mask.
     */
    private static final int MASK_0XFF = 0xff;

    /**
     * The buffer size.
     */
    private static final int BUFFER_SIZE = 8192;

    /**
     * The replacement relation id {@link Pattern}.
     */
    private static final Pattern RELATION_ID_PATTERN = Pattern
            .compile("<([^>]*) r:(id|embed)=\\\"([^\\\"]+)\\\"( [^>]* ?)?/?>");

    /**
     * The r:id value index in {@link #RELATION_ID_PATTERN}.
     */
    private static final int RELATION_ID_INDEX = 3;

    /**
     * Integer {@link Pattern}.
     */
    private static final Pattern INTEGER_PATTERN = Pattern.compile("[0-9]+");

    /**
     * Need new paragraph after copy.
     * Last Content Run And EndUserContent are not In Same Paragraph.
     */
    private boolean needNewParagraph = true;

    /**
     * The per {@link XWPFDocument} mapping form {@link PackagePart}'s MD5 to its {@link PackagePart#getPartName() name}.
     */
    private final Map<XWPFDocument, Map<String, URI>> partMD5ToName = new HashMap<>();

    /**
     * Gets the mapping form {@link PackagePart}'s MD5 to its {@link PackagePart#getPartName() name} for the given {@link XWPFDocument}.
     * 
     * @param document
     *            the {@link XWPFDocument}
     * @return the mapping form {@link PackagePart}'s MD5 to its {@link PackagePart#getPartName() name} for the given {@link XWPFDocument}
     */
    public Map<String, URI> getPartMD5ToName(XWPFDocument document) {
        Map<String, URI> res = partMD5ToName.get(document);

        if (res == null) {
            res = new HashMap<>();
            try {
                for (RelationPart part : document.getRelationParts()) {
                    final PackagePart packagePart = part.getDocumentPart().getPackagePart();
                    try (InputStream is = packagePart.getInputStream()) {
                        res.put(getMD5(is), packagePart.getPartName().getURI());
                    }
                }
            } catch (NoSuchAlgorithmException e) {
                // nothing to do here: worst case senario the generated document is not optimized in size
            } catch (IOException e) {
                // nothing to do here: worst case senario the generated document is not optimized in size
            }
            partMD5ToName.put(document, res);
        }

        return res;
    }

    /**
     * Gets the MD5 hash of the given {@link InputStream}.
     * 
     * @param is
     *            the {@link InputStream}
     * @return the MD5 hash of the given {@link InputStream}
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     * @throws IOException
     *             if the given {@link InputStream} cant be read
     */
    private String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
        final StringBuilder res = new StringBuilder();

        final MessageDigest md5 = MessageDigest.getInstance("MD5");

        final byte[] buffer = new byte[BUFFER_SIZE];
        int nbBytes;
        do {
            nbBytes = is.read(buffer);
            if (nbBytes > 0) {
                md5.update(buffer, 0, nbBytes);
            }
        } while (nbBytes != -1);

        final byte[] digest = md5.digest();
        for (int i = 0; i < digest.length; i++) {
            res.append(Integer.toString((digest[i] & MASK_0XFF) + MASK_0X100, MASK_16).substring(1));
        }

        return res.toString();
    }

    /**
     * Is last Content Run And EndUserContent are In Same Paragraph.
     * 
     * @return the needNewParagraph
     */
    public boolean needNewParagraph() {
        return needNewParagraph;
    }

    /**
     * Copies the given {@link UserContent} to the given output {@link XWPFParagraph}.
     * 
     * @param userContent
     *            UserContent EObject
     * @param outputParagraph
     *            Output Paragraph Before User Doc Dest content (User Code dest is writen by {@link M2DocEvaluator} )
     * @return last paragraph created by copy
     * @throws Exception
     *             if something goes wrong
     */
    @SuppressWarnings("resource")
    public XWPFParagraph copyUserContent(UserContent userContent, XWPFParagraph outputParagraph) throws Exception {
        XWPFParagraph res = null;

        final XWPFRun startUserContentRun = userContent.getRuns().get(userContent.getRuns().size() - 1);
        final XWPFParagraph startUserContentParagraph = (XWPFParagraph) startUserContentRun.getParent();
        final XmlObject startXmlObject = startUserContentRun.getCTR();
        final NodeList startSiblings = startXmlObject.getDomNode().getParentNode().getChildNodes();
        final boolean inlineStartUserContent = startSiblings.item(startSiblings.getLength() - 1) != startXmlObject
                .getDomNode();

        final XWPFRun endUserContentRun;
        final XWPFParagraph endUserContentParagraph;
        final XmlObject endXmlObject;
        final NodeList endSiblings;
        final boolean inlineEndUserContent;
        if (!userContent.getClosingRuns().isEmpty()) {
            endUserContentRun = userContent.getClosingRuns().get(0);
            endUserContentParagraph = (XWPFParagraph) endUserContentRun.getParent();
            endXmlObject = endUserContentRun.getCTR();
            endSiblings = endXmlObject.getDomNode().getParentNode().getChildNodes();
            inlineEndUserContent = endSiblings.item(0) != endXmlObject.getDomNode();
        } else {
            endUserContentRun = null;
            endUserContentParagraph = null;
            endXmlObject = null;
            endSiblings = null;
            inlineEndUserContent = false;
        }

        final XWPFDocument containerInputDocument = startUserContentRun.getDocument();
        final IBody inputBody = startUserContentParagraph.getBody();
        final XWPFDocument containerOutputDocument = outputParagraph.getDocument();
        final IBody outputBody = outputParagraph.getBody();

        final boolean inline;
        if (!(outputBody instanceof XWPFTableCell) && outputParagraph.getRuns().size() == 1
            && outputParagraph.getRuns().get(0).getText(0).length() == 0) {
            removeParagraph(outputBody, outputParagraph);
            inline = false;
        } else {
            inline = true;
        }

        final Iterator<IBodyElement> it = inputBody.getBodyElements().iterator();
        // skip
        while (it.hasNext()) {
            if (it.next().equals(startUserContentParagraph)) {
                break;
            }
        }

        final List<XWPFParagraph> listOutputParagraphs = new ArrayList<>();
        final List<XWPFRun> listOutputRuns = new ArrayList<>();
        final List<XWPFTable> listOutputTables = new ArrayList<>();
        final Map<String, String> inputRelationIdToOutputMap = new HashMap<>();
        final Map<URI, URI> inputPartURIToOutputPartURI = new HashMap<>();
        if (inlineStartUserContent) {
            if (inline) {
                if (endUserContentParagraph == startUserContentParagraph) {
                    copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, outputParagraph,
                            startUserContentParagraph, startXmlObject, endXmlObject);
                } else {
                    copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, outputParagraph,
                            startUserContentParagraph, startXmlObject, null);
                }
                res = outputParagraph;
            } else {
                if (endUserContentParagraph == startUserContentParagraph) {
                    res = createNewParagraph(outputBody);
                    copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, res,
                            endUserContentParagraph, null, endXmlObject);
                }
            }
        } else {
            // nothing to do here we will copy the next body element.
        }

        if (endUserContentParagraph != startUserContentParagraph) {
            while (it.hasNext()) {
                final IBodyElement element = it.next();
                if (endUserContentParagraph == element) {
                    break;
                }
                if (element instanceof XWPFParagraph) {
                    final XWPFParagraph inputParagraph = (XWPFParagraph) element;
                    res = copyParagraph(containerInputDocument, containerOutputDocument, outputBody,
                            inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputParagraph);
                    listOutputParagraphs.add(res);
                } else if (element instanceof XWPFTable) {
                    final XWPFTable outputTable = copyTable(containerInputDocument, containerOutputDocument, outputBody,
                            inputRelationIdToOutputMap, inputPartURIToOutputPartURI, (XWPFTable) element);
                    listOutputTables.add(outputTable);
                    res = null;
                } else if (element instanceof XWPFSDT) {
                    copyCTSdtBlock(outputBody, AbstractBodyParser.getCTSdtBlock(inputBody, (XWPFSDT) element));
                    res = null;
                } else {
                    throw new IllegalStateException("unknown body element.");
                }
            }
            if (inlineEndUserContent) {
                if (res != null) {
                    copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, res,
                            endUserContentParagraph, null, endXmlObject);
                } else {
                    copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, outputParagraph,
                            endUserContentParagraph, null, endXmlObject);
                }
            } else {
                // nothing to do here the last body element has already been copied
            }
        }

        // Change relations Id by xml replacement
        changeRelationsId(inputRelationIdToOutputMap, listOutputRuns, listOutputParagraphs, listOutputTables);

        needNewParagraph = !inlineEndUserContent;

        return res;
    }

    /**
     * Copies the given {@link XWPFParagraph} to the given {@link IBody}.
     * 
     * @param containerInputDocument
     *            the input {@link XWPFDocument}
     * @param containerOutputDocument
     *            the output {@link XWPFDocument}
     * @param outputBody
     *            the output {@link IBody}
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param inputParagraph
     *            the input {@link XWPFParagraph}
     * @return the output {@link XWPFParagraph}
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     */
    private XWPFParagraph copyParagraph(final XWPFDocument containerInputDocument,
            final XWPFDocument containerOutputDocument, final IBody outputBody,
            final Map<String, String> inputRelationIdToOutputMap, Map<URI, URI> inputPartURIToOutputPartURI,
            XWPFParagraph inputParagraph) throws InvalidFormatException, NoSuchAlgorithmException, IOException {
        final XWPFParagraph res = createNewParagraph(outputBody);

        // Copy new paragraph
        res.getCTP().set(inputParagraph.getCTP());
        // Create relation embedded in run and keep relation id in map (input to output)
        createRelations(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputParagraph.getCTP().xmlText(),
                containerInputDocument, containerOutputDocument);

        return res;
    }

    /**
     * Copies the given {@link XWPFTable} into the given output {@link IBody}.
     * 
     * @param containerInputDocument
     *            the input {@link XWPFDocument}
     * @param containerOutputDocument
     *            the output {@link XWPFDocument}
     * @param outputBody
     *            the output {@link IBody}
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param inputTable
     *            the input {@link XWPFTable}
     * @return the output {@link XWPFTable}
     * @throws IOException
     *             if the copy fails
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     */
    private XWPFTable copyTable(final XWPFDocument containerInputDocument, final XWPFDocument containerOutputDocument,
            final IBody outputBody, final Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, final XWPFTable inputTable)
            throws IOException, InvalidFormatException, NoSuchAlgorithmException {
        final XWPFTable res = createNewTable(outputBody, inputTable);

        res.getCTTbl().set(inputTable.getCTTbl());
        copyTableStyle(inputTable, containerOutputDocument);
        // Create relation embedded in run and keep relation id in map (input to output)
        createRelations(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputTable.getCTTbl().xmlText(),
                containerInputDocument, containerOutputDocument);

        return res;
    }

    /**
     * Copies the fragment of the input {@link XWPFParagraph} into the output {@link XWPFParagraph} starting at the given start
     * {@link XmlObject}.
     * 
     * @param inputRelationIdToOutputmap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param outputParagraph
     *            the output {@link XWPFParagraph}
     * @param inputParagraph
     *            the input {@link XWPFParagraph}
     * @param startXmlObject
     *            the {@link XmlObject} to start at
     * @param endXmlObject
     *            the {@link XmlObject} to end at
     * @throws XmlException
     *             if xml manipulation fails
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     */
    private void copyParagraphFragment(Map<String, String> inputRelationIdToOutputmap,
            Map<URI, URI> inputPartURIToOutputPartURI, XWPFParagraph outputParagraph, XWPFParagraph inputParagraph,
            XmlObject startXmlObject, XmlObject endXmlObject)
            throws XmlException, InvalidFormatException, NoSuchAlgorithmException, IOException {
        if (inputParagraph.getCTP().isSetPPr()) {
            outputParagraph.getCTP().setPPr((CTPPr) inputParagraph.getCTP().getPPr().copy());
        }

        XmlCursor inputCursor = inputParagraph.getCTP().newCursor();
        try {
            final XmlCursor savedCursor;
            if (inputCursor.toFirstChild() && startXmlObject != null) {
                savedCursor = copyFromRun(startXmlObject, inputCursor);
            } else {
                savedCursor = null;
            }

            if (startXmlObject == null || inputCursor.getObject().equals(startXmlObject)) {
                if (savedCursor != null) {
                    inputCursor.dispose();
                    inputCursor = savedCursor;
                }
                final XmlToken xmlWithOuputRelationId = createTemporaryParagraphFragment(inputRelationIdToOutputmap,
                        inputPartURIToOutputPartURI, outputParagraph, inputParagraph, inputCursor, endXmlObject);
                final XmlCursor withNewIDCursor = xmlWithOuputRelationId.newCursor();
                try {
                    withNewIDCursor.toFirstChild();
                    withNewIDCursor.toFirstChild();

                    final XmlCursor outputCursor = outputParagraph.getCTP().newCursor();
                    try {
                        outputCursor.toLastChild();
                        outputCursor.toEndToken();
                        outputCursor.toNextToken();
                        withNewIDCursor.copyXml(outputCursor);
                        while (withNewIDCursor.toNextSibling()) {
                            withNewIDCursor.copyXml(outputCursor);
                        }
                    } finally {
                        outputCursor.dispose();
                    }
                } finally {
                    withNewIDCursor.dispose();
                }
            }
        } finally {
            inputCursor.dispose();
        }
    }

    /**
     * Creates a temporary paragraph fragment with translated relation IDs.
     * 
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param outputParagraph
     *            the output {@link XWPFParagraph}
     * @param inputParagraph
     *            the input {@link XWPFParagraph}
     * @param inputCursor
     *            the cursor of the fragmant to copy
     * @param endXmlObject
     *            the {@link XmlObject} to end at
     * @return the {@link XmlToken} to the temporary paragraph fragment
     * @throws XmlException
     *             if xml manipulation fails
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     */
    private XmlToken createTemporaryParagraphFragment(Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, XWPFParagraph outputParagraph, XWPFParagraph inputParagraph,
            XmlCursor inputCursor, XmlObject endXmlObject)
            throws XmlException, InvalidFormatException, NoSuchAlgorithmException, IOException {
        final XmlObject tmpXmlObject = XmlObject.Factory.parse(
                "<root xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" mc:Ignorable=\"w14 w15 wp14\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:w14=\"http://schemas.microsoft.com/office/word/2010/wordml\" xmlns:w15=\"http://schemas.microsoft.com/office/word/2012/wordml\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:wp14=\"http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing\" xmlns:wpc=\"http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas\" xmlns:wpg=\"http://schemas.microsoft.com/office/word/2010/wordprocessingGroup\" xmlns:wpi=\"http://schemas.microsoft.com/office/word/2010/wordprocessingInk\" xmlns:wps=\"http://schemas.microsoft.com/office/word/2010/wordprocessingShape\"></root>");
        final XmlCursor tmpCursor = tmpXmlObject.newCursor();
        try {
            tmpCursor.toEndToken();
            tmpCursor.toPrevToken();
            do {
                if (endXmlObject != null && inputCursor.getObject().equals(endXmlObject)) {
                    break;
                }
                inputCursor.copyXml(tmpCursor);
            } while (inputCursor.toNextSibling());
        } finally {
            tmpCursor.dispose();
        }

        // Create relations embedded in run and keep relation id in map (input to output)
        final String tmpXmlText = tmpXmlObject.xmlText();
        createRelations(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, tmpXmlText,
                inputParagraph.getDocument(), outputParagraph.getDocument());
        // replace relations IDs
        final XmlToken xmlWithOuputId = getXmlWithOuputId(inputRelationIdToOutputMap, tmpXmlText);

        return xmlWithOuputId;
    }

    /**
     * Creates the frargment from the input paragraph and the {@link XWPFRun} to start the fragment from.
     * 
     * @param startXmlObject
     *            the {@link XmlObject} marking the start of the fragment
     * @param inputCursor
     *            the {@link XmlCursor} that will be moved to the start of the fragment
     * @return a {@link XmlCursor} if the fragment must start before the given {@link XmlObject}, <code>null</code> otherwise
     */
    private XmlCursor copyFromRun(XmlObject startXmlObject, XmlCursor inputCursor) {
        XmlCursor savedCursor = null; // used to keep bookmarks before the referenced run
        do {
            if (inputCursor.isStart()) {
                if ("bookmarkStart".equals(inputCursor.getName().getLocalPart())
                    || "fldSimple".equals(inputCursor.getName().getLocalPart())) {
                    if (savedCursor != null) {
                        savedCursor.dispose();
                    }
                    savedCursor = inputCursor.getObject().newCursor();
                }
                if ("r".equals(inputCursor.getName().getLocalPart())
                    || "bookmarkEnd".equals(inputCursor.getName().getLocalPart())) {
                    savedCursor = null;
                }
            }
            if (inputCursor.getObject().equals(startXmlObject) || !inputCursor.toNextSibling()) {
                break;
            }
        } while (!inputCursor.getObject().equals(startXmlObject));

        return savedCursor;
    }

    /**
     * Removes the given {@link XWPFParagraph} from the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody}
     * @param outputParagraph
     *            the {@link XWPFParagraph}
     */
    private void removeParagraph(IBody body, XWPFParagraph outputParagraph) {
        if (body instanceof XWPFDocument) {
            final XWPFDocument document = (XWPFDocument) body;
            final int index = document.getBodyElements().indexOf(outputParagraph);
            if (index != -1) {
                document.removeBodyElement(index);
            }
        } else if (body instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) body;
            headerFooter.removeParagraph(outputParagraph);
        } else if (body instanceof XWPFTableCell) {
            final XWPFTableCell cell = (XWPFTableCell) body;
            final int index = cell.getBodyElements().indexOf(outputParagraph);
            if (index != -1) {
                cell.removeParagraph(index);
            }
        } else {
            throw new IllegalStateException("can't delete paragraph from " + body.getClass().getCanonicalName());
        }
    }

    /**
     * Copies the given {@link CTSdtBlock} in the given output {@link IBody}.
     * 
     * @param outputBody
     *            the output {@link IBody}
     * @param block
     *            the {@link CTSdtBlock} to insert
     */
    private void copyCTSdtBlock(IBody outputBody, CTSdtBlock block) {
        final CTSdtBlock stdBlock;
        if (outputBody instanceof XWPFDocument) {
            stdBlock = ((XWPFDocument) outputBody).getDocument().getBody().addNewSdt();
        } else if (outputBody instanceof XWPFHeaderFooter) {
            stdBlock = ((XWPFHeaderFooter) outputBody)._getHdrFtr().addNewSdt();
        } else if (outputBody instanceof XWPFFootnote) {
            stdBlock = ((XWPFFootnote) outputBody).getCTFtnEdn().addNewSdt();
        } else if (outputBody instanceof XWPFTableCell) {
            stdBlock = ((XWPFTableCell) outputBody).getCTTc().addNewSdt();
        } else {
            throw new IllegalStateException("can't insert control in " + outputBody.getClass().getCanonicalName());
        }
        stdBlock.set(block.copy());
        new XWPFSDT(stdBlock, outputBody); // this do the insertion
    }

    /**
     * Create new Table.
     * 
     * @param document
     *            document
     * @param inputTable
     *            input Table
     * @return get Table
     */
    private XWPFTable createNewTable(IBody document, XWPFTable inputTable) {
        final XWPFTable res;

        final CTTbl copy = (CTTbl) inputTable.getCTTbl().copy();
        if (document instanceof XWPFDocument) {
            final CTTbl cttbl = ((XWPFDocument) document).getDocument().getBody().addNewTbl();
            res = new XWPFTable(cttbl, document);
            if (res.getRows().size() > 0) {
                res.removeRow(0);
            }
            document.insertTable(0, res);
        } else if (document instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) document;
            final int index = headerFooter._getHdrFtr().getTblList().size();
            final CTTbl cttbl = headerFooter._getHdrFtr().insertNewTbl(index);
            final XWPFTable newTable = new XWPFTable(cttbl, headerFooter);
            if (newTable.getRows().size() > 0) {
                newTable.removeRow(0);
            }
            headerFooter.insertTable(index, newTable);
            res = headerFooter.getTables().get(index);
            res.getCTTbl().set(copy);
        } else if (document instanceof XWPFTableCell) {
            final XWPFTableCell tCell = (XWPFTableCell) document;
            final int tableRank = tCell.getTables().size();
            final CTTbl tbl = tCell.getCTTc().addNewTbl();
            final XWPFTable newTable = new XWPFTable(tbl, tCell);
            if (newTable.getRows().size() > 0) {
                newTable.removeRow(0);
            }
            tbl.set(copy);
            tCell.insertTable(tableRank, newTable);
            res = tCell.getTables().get(tableRank);
        } else {
            throw new UnsupportedOperationException("unknown type of IBody : " + document.getClass());
        }

        return res;
    }

    /**
     * Create New Paragraph.
     * 
     * @param document
     *            document
     * @return new paragraph
     */
    private XWPFParagraph createNewParagraph(IBody document) {
        final XWPFParagraph res;

        if (document instanceof XWPFTableCell) {
            XWPFTableCell cell = (XWPFTableCell) document;
            res = cell.addParagraph();
        } else if (document instanceof XWPFDocument) {
            res = ((XWPFDocument) document).createParagraph();
        } else if (document instanceof XWPFHeaderFooter) {
            res = ((XWPFHeaderFooter) document).createParagraph();
        } else {
            throw new UnsupportedOperationException("unkown IBody type :" + document.getClass());
        }

        return res;
    }

    /**
     * Copy Table Style.
     * 
     * @param inputTable
     *            input Table
     * @param outputDoc
     *            outputDoc where copy style
     * @throws IOException
     *             if the copy fails
     */
    private static void copyTableStyle(XWPFTable inputTable, XWPFDocument outputDoc) throws IOException {
        try (XWPFDocument inputDoc = inputTable.getBody().getXWPFDocument();) {
            XWPFStyle style = inputDoc.getStyles().getStyle(inputTable.getStyleID());
            if (outputDoc == null || style == null) {
                return;
            }

            if (outputDoc.getStyles() == null) {
                outputDoc.createStyles();
            }

            List<XWPFStyle> usedStyleList = inputDoc.getStyles().getUsedStyleList(style);
            for (XWPFStyle xwpfStyle : usedStyleList) {
                outputDoc.getStyles().addStyle(xwpfStyle);
            }
        }
    }

    /**
     * Change relations Id.
     * 
     * @param inputRelationIdToOutputmap
     *            the relation ID mapping
     * @param listOutputRuns
     *            the {@link List} of output {@link XWPFRun}
     * @param listOutputParagraphs
     *            the {@link List} of output {@link XWPFParagraph}
     * @param listOutputTables
     *            the {@link List} of output {@link XWPFTable}
     * @throws XmlException
     *             XmlException
     */
    private void changeRelationsId(Map<String, String> inputRelationIdToOutputmap, List<XWPFRun> listOutputRuns,
            List<XWPFParagraph> listOutputParagraphs, List<XWPFTable> listOutputTables) throws XmlException {

        for (XWPFRun run : listOutputRuns) {
            XmlToken outputXmlObject = getXmlWithOuputId(inputRelationIdToOutputmap, run.getCTR().xmlText());
            if (outputXmlObject != null) {
                run.getCTR().set(outputXmlObject);
            }
        }

        for (XWPFParagraph paragraph : listOutputParagraphs) {
            XmlToken outputXmlObject = getXmlWithOuputId(inputRelationIdToOutputmap, paragraph.getCTP().xmlText());
            if (outputXmlObject != null) {
                paragraph.getCTP().set(outputXmlObject);
            }
        }

        for (XWPFTable table : listOutputTables) {
            XmlToken outputXmlObject = getXmlWithOuputId(inputRelationIdToOutputmap, table.getCTTbl().xmlText());
            if (outputXmlObject != null) {
                table.getCTTbl().set(outputXmlObject);
            }
        }

    }

    /**
     * Get Xml With Ouput relation Id.
     * 
     * @param inputRelationIdToOutputmap
     *            the relation ID mapping
     * @param xmlText
     *            xmlText
     * @return Xml With Ouput relation Id
     * @throws XmlException
     *             XmlException
     */
    private XmlToken getXmlWithOuputId(Map<String, String> inputRelationIdToOutputmap, String xmlText)
            throws XmlException {
        final StringBuilder builder = new StringBuilder(xmlText.length());

        final Matcher matcher = RELATION_ID_PATTERN.matcher(xmlText);

        int lastIndex = 0;
        while (matcher.find()) {
            final int pictureStart = matcher.start(RELATION_ID_INDEX);
            if (pictureStart != -1) {
                lastIndex = getXmlWithOutputIdCopyFragment(inputRelationIdToOutputmap, xmlText, builder, matcher,
                        lastIndex, RELATION_ID_INDEX, pictureStart);
            } else {
                final int hyperLinkStart = matcher.start(RELATION_ID_INDEX);
                lastIndex = getXmlWithOutputIdCopyFragment(inputRelationIdToOutputmap, xmlText, builder, matcher,
                        lastIndex, RELATION_ID_INDEX, hyperLinkStart);
            }
        }
        builder.append(xmlText.substring(lastIndex, xmlText.length()));

        return XmlToken.Factory.parse(builder.toString());
    }

    /**
     * Copy a fragment of the xml with replaced relation IDs.
     * 
     * @param inputRelationIdToOutputmap
     *            the relation ID mapping
     * @param xmlText
     *            xmlText
     * @param builder
     *            the output {@link StringBuilder}
     * @param matcher
     *            the {@link Matcher} that matched the relation
     * @param lastIndex
     *            the end of the last matiched id
     * @param groupIndex
     *            the group index in the matcher
     * @param start
     *            the start of the matched id
     * @return the new last index
     */
    private int getXmlWithOutputIdCopyFragment(Map<String, String> inputRelationIdToOutputmap, String xmlText,
            final StringBuilder builder, final Matcher matcher, int lastIndex, final int groupIndex, final int start) {
        builder.append(xmlText.subSequence(lastIndex, start));
        final String oldID = matcher.group(groupIndex);
        final String newID = inputRelationIdToOutputmap.get(oldID);
        if (newID != null) {
            builder.append(newID);
        } else {
            builder.append(oldID);
        }

        return matcher.end(groupIndex);
    }

    /**
     * Creates relations in output {@link XWPFDocument}.
     * 
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param xmlText
     *            the xml text to search for relations
     * @param inputDoc
     *            input Document
     * @param outputDoc
     *            output Document
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be read
     */
    private void createRelations(Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, String xmlText, XWPFDocument inputDoc, XWPFDocument outputDoc)
            throws InvalidFormatException, NoSuchAlgorithmException, IOException {
        final Matcher matcher = RELATION_ID_PATTERN.matcher(xmlText);
        while (matcher.find()) {
            final String inputRelationID = matcher.group(RELATION_ID_INDEX);
            final PackageRelationship inputRelationship = inputDoc.getPackagePart().getRelationship(inputRelationID);
            final PackagePart outputPart;
            if (inputRelationship.getTargetMode() == TargetMode.INTERNAL) {
                final PackagePart source = inputDoc.getPackage()
                        .getPart(PackagingURIHelper.createPartName(inputRelationship.getTargetURI()));
                outputPart = getOrCopyPart(inputPartURIToOutputPartURI, source, outputDoc);
            } else {
                outputPart = null;
                inputPartURIToOutputPartURI.put(inputRelationship.getTargetURI(), inputRelationship.getTargetURI());
            }

            final PackageRelationship outputRelationship = getOrCreateRelationship(inputPartURIToOutputPartURI,
                    outputDoc, outputPart, inputRelationship);
            inputRelationIdToOutputMap.put(inputRelationship.getId(), outputRelationship.getId());
        }
    }

    /**
     * Gets or creates the output {@link PackageRelationship} for the given input {@link PackageRelationship}.
     * 
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param outputDoc
     *            the ouput {@link XWPFDocument}
     * @param outputPart
     *            the output {@link PackagePart}
     * @param inputRelationship
     *            the input {@link PackageRelationship}
     * @return the ouptut {@link PackageRelationship}
     * @throws InvalidFormatException
     *             if image copy fails
     */
    private PackageRelationship getOrCreateRelationship(Map<URI, URI> inputPartURIToOutputPartURI,
            XWPFDocument outputDoc, PackagePart outputPart, PackageRelationship inputRelationship)
            throws InvalidFormatException {
        final PackageRelationship res;

        PackageRelationship existingRelationship = null;
        for (PackageRelationship relationship : outputDoc.getPackagePart()
                .getRelationshipsByType(inputRelationship.getRelationshipType())) {
            if (relationship.getTargetMode() == inputRelationship.getTargetMode() && inputPartURIToOutputPartURI
                    .get(inputRelationship.getTargetURI()).equals(relationship.getTargetURI())) {
                existingRelationship = relationship;
                break;
            }
        }

        if (existingRelationship != null) {
            res = existingRelationship;
        } else {
            if (outputPart != null) {
                res = outputDoc.getPackagePart().addRelationship(outputPart.getPartName(),
                        inputRelationship.getTargetMode(), inputRelationship.getRelationshipType());
            } else {
                res = outputDoc.getPackagePart().addExternalRelationship(inputRelationship.getTargetURI().toString(),
                        inputRelationship.getRelationshipType());
            }
        }

        return res;
    }

    /**
     * Gets or copy a {@link PackagePart} corresponding to the given source {@link PackagePart}.
     * 
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param source
     *            the source {@link PackagePart}
     * @param outputDoc
     *            the output {@link XWPFDocument}
     * @return the corresponding {@link PackagePart} in output {@link XWPFDocument}
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     * @throws InvalidFormatException
     *             if {@link PackagePart} can't be accessed
     */
    private PackagePart getOrCopyPart(Map<URI, URI> inputPartURIToOutputPartURI, PackagePart source,
            XWPFDocument outputDoc) throws InvalidFormatException, NoSuchAlgorithmException, IOException {
        final PackagePart res;

        final URI existingPartURI = inputPartURIToOutputPartURI.get(source.getPartName().getURI());
        if (existingPartURI != null) {
            res = outputDoc.getPackage().getPart(PackagingURIHelper.createPartName(existingPartURI));
        } else {
            res = copyPart(source, outputDoc);
        }
        inputPartURIToOutputPartURI.put(source.getPartName().getURI(), res.getPartName().getURI());

        return res;
    }

    /**
     * Creates a copy of the source {@link PackagePart} in the given ouput {@link XWPFDocument}.
     * 
     * @param source
     *            the source {@link PackagePart}
     * @param outputDoc
     *            the oupput {@link XWPFDocument}
     * @return the copied {@link PackagePart}
     * @throws InvalidFormatException
     *             if the {@link PackagePart} can't be accessed
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     */
    private PackagePart copyPart(PackagePart source, XWPFDocument outputDoc)
            throws InvalidFormatException, NoSuchAlgorithmException, IOException {
        final PackagePart res;

        final PackagePartName outputPartName = getOutputPartName(source, outputDoc);
        final PackagePart copiedPart = outputDoc.getPackage().createPart(outputPartName, source.getContentType());

        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        try (InputStream is = source.getInputStream();
                DigestInputStream dis = new DigestInputStream(is, md5);
                OutputStream os = copiedPart.getOutputStream()) {
            IOUtil.copyCompletely(dis, os);
        }
        final byte[] digest = md5.digest();
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            builder.append(Integer.toString((digest[i] & MASK_0XFF) + MASK_0X100, MASK_16).substring(1));
        }

        final URI existingPartURI = getPartMD5ToName(outputDoc).get(builder.toString());
        if (existingPartURI != null) {
            res = outputDoc.getPackage().getPart(PackagingURIHelper.createPartName(existingPartURI));
            outputDoc.getPackage().removePart(copiedPart);
        } else {
            res = copiedPart;
            getPartMD5ToName(outputDoc).put(builder.toString(), res.getPartName().getURI());
        }

        return res;
    }

    /**
     * Gets the output {@link PackagePart} name for the given source {@link PackagePart} in the given output {@link XWPFDocument}.
     * 
     * @param source
     *            the source {@link PackagePart}
     * @param outputDoc
     *            the output {@link XWPFDocument}
     * @return the output {@link PackagePart} name for the given source {@link PackagePart} in the given output {@link XWPFDocument}
     * @throws InvalidFormatException
     *             if a {@link PackagePart} can't be accessed
     */
    private PackagePartName getOutputPartName(PackagePart source, XWPFDocument outputDoc)
            throws InvalidFormatException {
        PackagePartName possiblePartName = source.getPartName();
        PackagePart existingPart = outputDoc.getPackage().getPart(possiblePartName);
        int index = 1;
        final Matcher matcher = INTEGER_PATTERN.matcher(possiblePartName.getName());
        final boolean indexFound = matcher.find();
        while (existingPart != null) {
            existingPart = outputDoc.getPackage().getPart(possiblePartName);
            if (existingPart != null) {
                if (indexFound) {
                    possiblePartName = PackagingURIHelper.createPartName(matcher.replaceFirst(String.valueOf(index++)));
                } else {
                    possiblePartName = PackagingURIHelper
                            .createPartName(source.getPartName().getName().replace(".", index++ + "."));
                }
            }
        }

        return possiblePartName;
    }

    /**
     * Copies the given {@link IBody} to the given {@link XWPFParagraph}.
     * 
     * @param outputParagraph
     *            the output {@link XWPFParagraph}
     * @param body
     *            the {@link IBody}
     * @return the new current {@link XWPFParagraph}
     * @throws Exception
     *             if something goes wrong
     */
    @SuppressWarnings("resource")
    public XWPFParagraph copyBody(XWPFParagraph outputParagraph, IBody body) throws Exception {
        XWPFParagraph res = null;

        if (body.getBodyElements().isEmpty()) {
            res = outputParagraph;
        } else {
            final IBody outputBody = outputParagraph.getBody();
            boolean inline;
            if (!(outputBody instanceof XWPFTableCell) && outputParagraph.getRuns().size() == 1
                && outputParagraph.getRuns().get(0).getText(0).length() == 0) {
                removeParagraph(outputBody, outputParagraph);
                inline = false;
            } else {
                inline = true;
            }

            final XWPFDocument containerInputDocument = body.getXWPFDocument();
            final XWPFDocument containerOutputDocument = outputBody.getXWPFDocument();
            final List<XWPFParagraph> listOutputParagraphs = new ArrayList<>();
            final List<XWPFRun> listOutputRuns = new ArrayList<>();
            final List<XWPFTable> listOutputTables = new ArrayList<>();
            final Map<String, String> inputRelationIdToOutputMap = new HashMap<>();
            final Map<URI, URI> inputPartURIToOutputPartURI = new HashMap<>();
            for (IBodyElement element : body.getBodyElements()) {
                if (element instanceof XWPFParagraph) {
                    final XWPFParagraph inputParagraph = (XWPFParagraph) element;
                    if (inline) {
                        // inline the all paragraph
                        copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, outputParagraph,
                                inputParagraph, null, null);
                        res = null;
                    } else {
                        res = copyParagraph(containerInputDocument, containerOutputDocument, outputBody,
                                inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputParagraph);
                        listOutputParagraphs.add(res);
                    }
                } else if (element instanceof XWPFTable) {
                    final XWPFTable outputTable = copyTable(containerInputDocument, containerOutputDocument, outputBody,
                            inputRelationIdToOutputMap, inputPartURIToOutputPartURI, (XWPFTable) element);
                    listOutputTables.add(outputTable);
                    res = null;
                } else if (element instanceof XWPFSDT) {
                    copyCTSdtBlock(outputBody, AbstractBodyParser.getCTSdtBlock(outputBody, (XWPFSDT) element));
                    res = null;
                } else {
                    throw new IllegalStateException("unknown body element.");
                }
                inline = false;
            }

            if (res != null && outputBody instanceof XWPFTableCell && res.getRuns().isEmpty()) {
                res.createRun();
            }

            // Change relations Id by xml replacement
            changeRelationsId(inputRelationIdToOutputMap, listOutputRuns, listOutputParagraphs, listOutputTables);
        }

        return res;
    }

}
