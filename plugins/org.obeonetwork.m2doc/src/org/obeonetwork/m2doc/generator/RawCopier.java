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
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.parser.AbstractBodyParser;
import org.obeonetwork.m2doc.template.UserContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Raw Copier manipulatin xml structure.
 * 
 * @author ohaegi
 */
public class RawCopier {

    /**
     * The relationships URI.
     */
    private static final String RELATIONSHIPS_URI = "http://schemas.openxmlformats.org/officeDocument/2006/relationships";

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

        final IBody inputBody = startUserContentParagraph.getBody();
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
            IBodyElement element = null;
            while (it.hasNext()) {
                element = it.next();
                if (endUserContentParagraph == element) {
                    break;
                }
                if (element instanceof XWPFParagraph) {
                    final XWPFParagraph inputParagraph = (XWPFParagraph) element;
                    res = copyParagraph(outputBody, inputRelationIdToOutputMap, inputPartURIToOutputPartURI,
                            inputParagraph, null);
                } else if (element instanceof XWPFTable) {
                    copyTable(outputBody, inputRelationIdToOutputMap, inputPartURIToOutputPartURI, (XWPFTable) element,
                            null);
                    res = null;
                } else if (element instanceof XWPFSDT) {
                    copyCTSdtBlock(outputBody, AbstractBodyParser.getCTSdtBlock(inputBody, (XWPFSDT) element));
                    res = null;
                } else {
                    throw new IllegalStateException("unknown body element.");
                }
            }
            if (inlineEndUserContent) {
                res = createNewParagraph(outputBody);
                final CTP ctp = (CTP) ((XWPFParagraph) element).getCTP().copy();
                ctp.getRList().clear();
                ctp.getFldSimpleList().clear();
                ctp.getHyperlinkList().clear();
                res.getCTP().set(ctp);
                copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, res,
                        endUserContentParagraph, null, endXmlObject);
            } else {
                // nothing to do here the last body element has already been copied
            }
        }

        needNewParagraph = !inlineEndUserContent;

        return res;
    }

    /**
     * Copies the given {@link XWPFParagraph} to the given {@link IBody}.
     * 
     * @param outputBody
     *            the output {@link IBody}
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param inputParagraph
     *            the input {@link XWPFParagraph}
     * @param bookmarkManager
     *            the {@link BookmarkManager} or <code>null</code>
     * @return the output {@link XWPFParagraph}
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     * @throws XmlException
     *             if relations can't be updated
     */
    private XWPFParagraph copyParagraph(final IBody outputBody, final Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, XWPFParagraph inputParagraph, BookmarkManager bookmarkManager)
            throws InvalidFormatException, NoSuchAlgorithmException, IOException, XmlException {
        final XWPFParagraph res = createNewParagraph(outputBody);

        // Copy new paragraph
        res.getCTP().set(inputParagraph.getCTP());
        // Create relation embedded in run and keep relation id in map (input to output)
        updateRelationIds(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputParagraph.getBody(), outputBody,
                res.getCTP());
        outputBody.getParagraphs().get(0).createRun();
        if (bookmarkManager != null) {
            updateBookmarks(bookmarkManager, res.getCTP(), inputParagraph.getCTP(), outputBody);
        }

        return res;
    }

    /**
     * Copies the given {@link XWPFTable} into the given output {@link IBody}.
     * 
     * @param outputBody
     *            the output {@link IBody}
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param inputTable
     *            the input {@link XWPFTable}
     * @param bookmarkManager
     *            the {@link BookmarkManager} or <code>null</code>
     * @return the output {@link XWPFTable}
     * @throws IOException
     *             if the copy fails
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be found
     * @throws XmlException
     *             if relations can't be updated
     */
    private XWPFTable copyTable(final IBody outputBody, final Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, final XWPFTable inputTable, BookmarkManager bookmarkManager)
            throws IOException, InvalidFormatException, NoSuchAlgorithmException, XmlException {
        final XWPFTable res = POIServices.getInstance().createTable(outputBody);

        res.getCTTbl().set(inputTable.getCTTbl());
        copyTableStyle(inputTable, outputBody.getXWPFDocument());
        // Create relation embedded in run and keep relation id in map (input to output)
        updateRelationIds(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputTable.getBody(), res.getBody(),
                res.getCTTbl());
        if (bookmarkManager != null) {
            updateBookmarks(bookmarkManager, res, inputTable);
        }

        return res;
    }

    /**
     * Copies the fragment of the input {@link XWPFParagraph} into the output {@link XWPFParagraph} starting at the given start
     * {@link XmlObject}.
     * 
     * @param inputRelationIdToOutputMap
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
    private void copyParagraphFragment(Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, XWPFParagraph outputParagraph, XWPFParagraph inputParagraph,
            XmlObject startXmlObject, XmlObject endXmlObject)
            throws XmlException, InvalidFormatException, NoSuchAlgorithmException, IOException {
        if (inputParagraph.getCTP().isSetPPr()) {
            outputParagraph.getCTP().setPPr((CTPPr) inputParagraph.getCTP().getPPr().copy());
        }

        final XmlCursor inputCursor;
        if (startXmlObject != null) {
            inputCursor = startXmlObject.newCursor();
        } else {
            inputCursor = inputParagraph.getCTP().newCursor();
            inputCursor.toFirstChild();
        }
        try {
            if (startXmlObject == null || inputCursor.getObject().equals(startXmlObject)) {
                final XmlCursor outputCursor = outputParagraph.getCTP().newCursor();
                try {
                    outputCursor.toLastChild();
                    outputCursor.toEndToken();
                    outputCursor.toNextToken();
                    do {
                        if (endXmlObject != null && inputCursor.getObject().equals(endXmlObject)) {
                            break;
                        }
                        inputCursor.copyXml(outputCursor);
                        final XmlCursor tmpCursor = outputCursor.newCursor();
                        tmpCursor.toPrevSibling();
                        tmpCursor.getObject();
                        updateRelationIds(inputRelationIdToOutputMap, inputPartURIToOutputPartURI,
                                inputParagraph.getBody(), outputParagraph.getBody(), tmpCursor.getObject());
                        // TODO update bookmark manager
                        tmpCursor.dispose();
                    } while (inputCursor.toNextSibling());
                } finally {
                    outputCursor.dispose();
                }
            }
        } finally {
            inputCursor.dispose();
        }
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
        @SuppressWarnings("resource")
        final XWPFDocument inputDoc = inputTable.getBody().getXWPFDocument();
        final XWPFStyle style = inputDoc.getStyles().getStyle(inputTable.getStyleID());
        if (outputDoc != null && style != null) {
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
     * Get Xml With Ouput relation Id.
     * 
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param outputBody
     *            the input {@link IBody}
     * @param inputBody
     *            the ouput {@link IBody}
     * @param xmlObject
     *            the {@link XmlObject} to walk
     * @throws XmlException
     *             XmlException
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be read
     */
    private void updateRelationIds(Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, IBody inputBody, IBody outputBody, XmlObject xmlObject)
            throws XmlException, InvalidFormatException, NoSuchAlgorithmException, IOException {
        final XmlObject idAttr = xmlObject.selectAttribute(RELATIONSHIPS_URI, "id");
        if (idAttr != null) {
            updateRelationAttribute(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputBody, outputBody,
                    idAttr);
        } else {
            final XmlObject embedAttr = xmlObject.selectAttribute(RELATIONSHIPS_URI, "embed");
            if (embedAttr != null) {
                updateRelationAttribute(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputBody, outputBody,
                        embedAttr);
            }
        }
        final XmlCursor cursor = xmlObject.newCursor();
        if (cursor.toFirstChild()) {
            updateRelationIds(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputBody, outputBody,
                    cursor.getObject());
            while (cursor.toNextSibling()) {
                updateRelationIds(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputBody, outputBody,
                        cursor.getObject());
            }
        }
        cursor.dispose();
    }

    /**
     * Updates the given relation attribute with its new ID.
     * 
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param outputBody
     *            the input {@link IBody}
     * @param inputBody
     *            the ouput {@link IBody}
     * @param attribute
     *            the attribute {@link Node}
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be read
     */
    private void updateRelationAttribute(Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, IBody inputBody, IBody outputBody, final XmlObject attribute)
            throws InvalidFormatException, NoSuchAlgorithmException, IOException {
        final String oldID = attribute.getDomNode().getNodeValue();
        if (oldID != null) {
            String newID = inputRelationIdToOutputMap.get(oldID);
            if (newID == null) {
                newID = createRelation(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, oldID, inputBody,
                        outputBody);
            }
            attribute.getDomNode().setNodeValue(newID);
        }
    }

    /**
     * Creates relations in output {@link XWPFDocument}.
     * 
     * @param inputRelationIdToOutputMap
     *            the relation ID mapping
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param inputRelationID
     *            the input realtion ID
     * @param inputBody
     *            the input {@link IBody}
     * @param outputBody
     *            the output {@link IBody}
     * @return the new relation ID
     * @throws InvalidFormatException
     *             if image copy fails
     * @throws IOException
     *             if a {@link PackagePart} can't be read
     * @throws NoSuchAlgorithmException
     *             if MD5 can't be read
     */
    private String createRelation(Map<String, String> inputRelationIdToOutputMap,
            Map<URI, URI> inputPartURIToOutputPartURI, String inputRelationID, IBody inputBody, IBody outputBody)
            throws InvalidFormatException, NoSuchAlgorithmException, IOException {
        final PackageRelationship inputRelationship = inputBody.getPart().getPackagePart()
                .getRelationship(inputRelationID);
        final PackagePart outputPart;
        if (inputRelationship.getTargetMode() == TargetMode.INTERNAL) {
            final PackagePart source = inputBody.getXWPFDocument().getPackagePart().getPackage()
                    .getPart(PackagingURIHelper.createPartName(inputRelationship.getTargetURI()));
            outputPart = getOrCopyPart(inputPartURIToOutputPartURI, source, outputBody.getXWPFDocument());
        } else {
            outputPart = null;
            inputPartURIToOutputPartURI.put(inputRelationship.getTargetURI(), inputRelationship.getTargetURI());
        }

        final PackageRelationship outputRelationship = getOrCreateRelationship(inputPartURIToOutputPartURI, outputBody,
                outputPart, inputRelationship);
        inputRelationIdToOutputMap.put(inputRelationship.getId(), outputRelationship.getId());

        return outputRelationship.getId();
    }

    /**
     * Gets or creates the output {@link PackageRelationship} for the given input {@link PackageRelationship}.
     * 
     * @param inputPartURIToOutputPartURI
     *            the mapping form input part {@link PackagePartName} to output par {@link PackagePartName}
     * @param outputBody
     *            the ouput {@link IBody}
     * @param outputPart
     *            the output {@link PackagePart}
     * @param inputRelationship
     *            the input {@link PackageRelationship}
     * @return the ouptut {@link PackageRelationship}
     * @throws InvalidFormatException
     *             if image copy fails
     */
    private PackageRelationship getOrCreateRelationship(Map<URI, URI> inputPartURIToOutputPartURI, IBody outputBody,
            PackagePart outputPart, PackageRelationship inputRelationship) throws InvalidFormatException {
        final PackageRelationship res;

        PackageRelationship existingRelationship = null;
        for (PackageRelationship relationship : outputBody.getPart().getPackagePart()
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
                res = outputBody.getPart().getPackagePart().addRelationship(outputPart.getPartName(),
                        inputRelationship.getTargetMode(), inputRelationship.getRelationshipType());
            } else {
                res = outputBody.getPart().getPackagePart().addExternalRelationship(
                        inputRelationship.getTargetURI().toString(), inputRelationship.getRelationshipType());
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
     * @param bookmarkManager
     *            the {@link BookmarkManager} to update or <code>null</code>
     * @return the new current {@link XWPFParagraph}
     * @throws Exception
     *             if something goes wrong
     */
    public XWPFParagraph copyBody(XWPFParagraph outputParagraph, IBody body, BookmarkManager bookmarkManager)
            throws Exception {
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

            final Map<String, String> inputRelationIdToOutputMap = new HashMap<>();
            final Map<URI, URI> inputPartURIToOutputPartURI = new HashMap<>();
            for (IBodyElement element : body.getBodyElements()) {
                if (element instanceof XWPFParagraph) {
                    final XWPFParagraph inputParagraph = (XWPFParagraph) element;
                    if (inline) {
                        // inline the all paragraph
                        copyParagraphFragment(inputRelationIdToOutputMap, inputPartURIToOutputPartURI, outputParagraph,
                                inputParagraph, null, null);
                        res = outputParagraph;
                    } else {
                        res = copyParagraph(outputBody, inputRelationIdToOutputMap, inputPartURIToOutputPartURI,
                                inputParagraph, bookmarkManager);
                    }
                } else if (element instanceof XWPFTable) {
                    final XWPFTable inputTable = (XWPFTable) element;
                    copyTable(outputBody, inputRelationIdToOutputMap, inputPartURIToOutputPartURI, inputTable,
                            bookmarkManager);
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
        }

        return res;
    }

    /**
     * Updates bookmarks for the given output {@link XWPFTable} according to its input {@link XWPFTable}.
     * 
     * @param bookmarkManager
     *            the {@link BookmarkManager}
     * @param outputTable
     *            the output {@link XWPFTable}
     * @param inputTable
     *            the input {@link XWPFTable}
     */
    private void updateBookmarks(BookmarkManager bookmarkManager, final XWPFTable outputTable,
            final XWPFTable inputTable) {
        final List<XWPFTableRow> inputRows = inputTable.getRows();
        final List<XWPFTableRow> outputRows = outputTable.getRows();
        for (int rowIndex = 0; rowIndex < inputRows.size(); rowIndex++) {
            final XWPFTableRow inputRow = inputRows.get(rowIndex);
            final XWPFTableRow outputRow = outputRows.get(rowIndex);
            final List<XWPFTableCell> inputCells = inputRow.getTableCells();
            final List<XWPFTableCell> outputCells = outputRow.getTableCells();
            for (int cellIndex = 0; cellIndex < inputCells.size(); cellIndex++) {
                final XWPFTableCell inputCell = inputCells.get(cellIndex);
                final XWPFTableCell outputCell = outputCells.get(cellIndex);
                final List<IBodyElement> inputBodyElements = inputCell.getBodyElements();
                final List<IBodyElement> outputBodyElements = outputCell.getBodyElements();
                for (int bodyElementIndex = 0; bodyElementIndex < inputBodyElements.size(); bodyElementIndex++) {
                    final IBodyElement inputBodyElement = inputBodyElements.get(bodyElementIndex);
                    if (inputBodyElement instanceof XWPFParagraph) {
                        final IBodyElement outputBodyElement = outputBodyElements.get(bodyElementIndex);
                        updateBookmarks(bookmarkManager, ((XWPFParagraph) outputBodyElement).getCTP(),
                                ((XWPFParagraph) inputBodyElement).getCTP(), outputTable.getBody());
                    }
                }
            }
        }
    }

    /**
     * Updates bookmarks for the given output {@link CTP} according to its input {@link CTP}.
     * 
     * @param bookmarkManager
     *            the {@link BookmarkManager}
     * @param outputParagraph
     *            the output {@link CTP}
     * @param inputParagraph
     *            the input {@link CTP}
     * @param outputBody
     *            the output {@link IBody}
     */
    private void updateBookmarks(BookmarkManager bookmarkManager, CTP outputParagraph, CTP inputParagraph,
            IBody outputBody) {
        final List<CTBookmark> oldBookmarks = inputParagraph.getBookmarkStartList();
        final List<CTBookmark> newBookmarks = outputParagraph.getBookmarkStartList();
        for (int bookmarkIndex = 0; bookmarkIndex < oldBookmarks.size(); bookmarkIndex++) {
            bookmarkManager.updateXmlObject(newBookmarks.get(bookmarkIndex), oldBookmarks.get(bookmarkIndex),
                    outputBody);
        }
        List<CTR> inputRuns = inputParagraph.getRList();
        final List<CTR> outputRuns = outputParagraph.getRList();
        for (int runIndex = 0; runIndex < inputRuns.size(); runIndex++) {
            final CTR inputRun = inputRuns.get(runIndex);
            final CTR outputRun = outputRuns.get(runIndex);
            final List<CTText> inputTexts = inputRun.getInstrTextList();
            final List<CTText> outputTexts = outputRun.getInstrTextList();
            for (int textIndex = 0; textIndex < inputTexts.size(); textIndex++) {
                bookmarkManager.updateXmlObject(outputTexts.get(textIndex), inputTexts.get(textIndex), outputBody);
            }
        }
    }

}
