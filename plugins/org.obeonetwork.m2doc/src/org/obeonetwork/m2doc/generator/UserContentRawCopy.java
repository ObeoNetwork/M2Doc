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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlToken;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.Statement;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.UserContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

/**
 * UserContent Raw Copy.
 * 
 * @author ohaegi
 */
public class UserContentRawCopy {

    /**
     * The replacement picture id {@link Pattern}.
     */
    private static final Pattern PICTURE_ID_PATTERN = Pattern
            .compile("<a:blip(( .*).)? r:embed=\\\"([^\\\"]+)\\\"( .* ?)?/?>");

    /**
     * The replacement picture id {@link Pattern}.
     */
    private static final int PICTURE_ID_GROUP_ID = 3;

    /**
     * Need new paragraph after copy.
     * Last Content Run And EndUserContent are not In Same Paragraph.
     */
    private boolean needNewParagraph = true;

    /**
     * Is last Content Run And EndUserContent are In Same Paragraph.
     * 
     * @return the needNewParagraph
     */
    public boolean needNewParagraph() {
        return needNewParagraph;
    }

    /**
     * Copy.
     * 
     * @param userContent
     *            UserContent EObject
     * @param outputParagraphBeforeUserDocContent
     *            Output Paragraph Before User Doc Dest content (User Code dest is writen by {@link M2DocEvaluator} )
     * @return last paragraph created by copy
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws XmlException
     *             XmlException
     * @throws IOException
     *             if the copy fails
     */
    public XWPFParagraph copy(UserContent userContent, XWPFParagraph outputParagraphBeforeUserDocContent)
            throws InvalidFormatException, XmlException, IOException {

        // Test if run before userContent is in same XWPFParagraph than first run of userContent
        final XWPFParagraph previousInputParagraph = (XWPFParagraph) userContent.getRuns()
                .get(userContent.getRuns().size() - 1).getParent();
        XWPFParagraph currentInputParagraph = previousInputParagraph;
        final List<Statement> statements = userContent.getBody().getStatements();

        XWPFParagraph currentOutputParagraph = copyStatements(outputParagraphBeforeUserDocContent,
                currentInputParagraph, previousInputParagraph, statements);

        // test if the last statement last run is different from the closing run
        if (!userContent.getClosingRuns().isEmpty() && !statements.isEmpty()) {
            final Statement lastStatment = statements.get(statements.size() - 1);
            if (!lastStatment.getRuns().isEmpty()) {
                needNewParagraph = lastStatment.getRuns().get(lastStatment.getRuns().size() - 1)
                        .getParent() != userContent.getClosingRuns().get(0).getParent();
            }
        }

        return currentOutputParagraph;
    }

    /**
     * Copies the given {@link List} of {@link Statement} from the given {@link XWPFParagraph} to the given output {@link XWPFParagraph}.
     * 
     * @param outputParagraph
     *            the output {@link XWPFParagraph}
     * @param currentInputParagraph
     *            the current input {@link XWPFParagraph}
     * @param previousInputParagraph
     *            the previous input {@link XWPFParagraph} if any, <code>null</code> otherwise
     * @param statements
     *            the {@link List} of {@link Statement} to copy
     * @return last paragraph created by copy
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws XmlException
     *             XmlException
     * @throws IOException
     *             if the copy fails
     */
    @SuppressWarnings("resource")
    public XWPFParagraph copyStatements(XWPFParagraph outputParagraph, XWPFParagraph currentInputParagraph,
            XWPFParagraph previousInputParagraph, final List<Statement> statements)
            throws InvalidFormatException, IOException, XmlException {
        final XWPFDocument containerInputDocument = getInputDocument(currentInputParagraph, previousInputParagraph,
                statements);
        final XWPFDocument containerOutputDocument = outputParagraph.getDocument();
        final IBody outputBody = outputParagraph.getBody();
        XWPFParagraph localCurrentInputParagraph;
        XWPFParagraph currentOutputParagraph;
        if (!statements.isEmpty()) {
            if (statements.get(0) instanceof StaticFragment) {
                localCurrentInputParagraph = currentInputParagraph;
                currentOutputParagraph = outputParagraph;
            } else {
                localCurrentInputParagraph = null;
                currentOutputParagraph = null;
                if (outputParagraph.getRuns().size() == 1
                    && outputParagraph.getRuns().get(0).getText(0).length() == 0) {
                    removeParagraph(outputBody, outputParagraph);
                }
            }
        } else {
            localCurrentInputParagraph = currentInputParagraph;
            currentOutputParagraph = outputParagraph;
        }
        final List<XWPFParagraph> listOutputParagraphs = new ArrayList<>();
        final List<XWPFTable> listOutputTables = new ArrayList<>();
        final List<XWPFRun> listOutputRuns = new ArrayList<>();
        final Map<String, String> inputPicuteIdToOutputmap = new HashMap<>();
        boolean paragraphFragmentCopied = false;
        for (Statement statement : statements) {
            if (statement instanceof StaticFragment) {
                for (XWPFRun inputRun : statement.getRuns()) {
                    final XWPFParagraph currentRunParagraph = (XWPFParagraph) inputRun.getParent();
                    if (currentRunParagraph != localCurrentInputParagraph) {
                        localCurrentInputParagraph = currentRunParagraph;
                        currentOutputParagraph = createNewParagraph(outputBody);
                        // Copy new paragraph
                        currentOutputParagraph.getCTP().set(localCurrentInputParagraph.getCTP());
                        listOutputParagraphs.add(currentOutputParagraph);
                        // Create picture embedded in run and keep relation id in map (input to output)
                        createPictures(inputPicuteIdToOutputmap, localCurrentInputParagraph.getCTP().xmlText(),
                                containerInputDocument, containerOutputDocument);
                    } else if (!paragraphFragmentCopied && currentRunParagraph == previousInputParagraph) {
                        // Test if some run exist between userContent tag and first paragraph in this tag
                        copyParagraphFragment(inputPicuteIdToOutputmap, outputParagraph, currentInputParagraph,
                                inputRun);
                        paragraphFragmentCopied = true;
                    }
                }
                // In case of table (no run in abstractConstruct)
            } else if (statement instanceof Table) {
                final Table table = (Table) statement;
                final XWPFTable inputTable = table.getTable();
                final XWPFTable outputTable = createNewTable(outputBody, inputTable);
                outputTable.getCTTbl().set(inputTable.getCTTbl());
                copyTableStyle(inputTable, containerOutputDocument);
                listOutputTables.add(outputTable);
                // Create picture embedded in run and keep relation id in map (input to output)
                createPictures(inputPicuteIdToOutputmap, inputTable.getCTTbl().xmlText(), containerInputDocument,
                        containerOutputDocument);
            } else if (statement instanceof ContentControl) {
                final ContentControl contentControl = (ContentControl) statement;
                copyCTSdtBlock(outputBody, contentControl.getBlock());
            }
        }

        // Change Picture Id by xml replacement
        changePictureId(inputPicuteIdToOutputmap, listOutputRuns, listOutputParagraphs, listOutputTables);

        if (currentOutputParagraph == null) {
            currentOutputParagraph = outputParagraph;
        }

        return currentOutputParagraph;
    }

    /**
     * Copies the fragment of the input {@link XWPFParagraph} into the output {@link XWPFParagraph} starting at the given input {@link XWPFRun}.
     * 
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param outputParagraph
     *            the output {@link XWPFParagraph}
     * @param inputParagraph
     *            the input {@link XWPFParagraph}
     * @param inputRun
     *            the {@link XWPFRun} to start at
     * @throws XmlException
     *             if xml manipulation fails
     * @throws InvalidFormatException
     *             if image copy fails
     */
    private void copyParagraphFragment(Map<String, String> inputPicuteIdToOutputmap, XWPFParagraph outputParagraph,
            XWPFParagraph inputParagraph, XWPFRun inputRun) throws XmlException, InvalidFormatException {
        if (inputParagraph.getCTP().isSetPPr()) {
            outputParagraph.getCTP().setPPr((CTPPr) inputParagraph.getCTP().getPPr().copy());
        }

        XmlCursor inputCursor = inputParagraph.getCTP().newCursor();
        try {
            final XmlCursor savedCursor;
            if (inputCursor.toFirstChild()) {
                savedCursor = copyFromRun(inputRun, inputCursor);
            } else {
                savedCursor = null;
            }

            if (inputCursor.getObject().equals(inputRun.getCTR())) {
                if (savedCursor != null) {
                    inputCursor.dispose();
                    inputCursor = savedCursor;
                }
                final XmlToken xmlWithOuputPictureId = createTemporaryParagraphFragment(inputPicuteIdToOutputmap,
                        outputParagraph, inputParagraph, inputCursor);
                final XmlCursor withNewIDCursor = xmlWithOuputPictureId.newCursor();
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
     * Creates a temporary paragraph fragment with traslated picture IDs.
     * 
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param outputParagraph
     *            the output {@link XWPFParagraph}
     * @param inputParagraph
     *            the input {@link XWPFParagraph}
     * @param inputCursor
     *            the cursor of the fragmant to copy
     * @return the {@link XmlToken} to the temporary paragraph fragment
     * @throws XmlException
     *             if xml manipulation fails
     * @throws InvalidFormatException
     *             if image copy fails
     */
    private XmlToken createTemporaryParagraphFragment(Map<String, String> inputPicuteIdToOutputmap,
            XWPFParagraph outputParagraph, XWPFParagraph inputParagraph, XmlCursor inputCursor)
            throws XmlException, InvalidFormatException {
        final XmlObject tmpXmlObject = XmlObject.Factory.parse(
                "<root xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" mc:Ignorable=\"w14 w15 wp14\" xmlns:m=\"http://schemas.openxmlformats.org/officeDocument/2006/math\" xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w10=\"urn:schemas-microsoft-com:office:word\" xmlns:w14=\"http://schemas.microsoft.com/office/word/2010/wordml\" xmlns:w15=\"http://schemas.microsoft.com/office/word/2012/wordml\" xmlns:wne=\"http://schemas.microsoft.com/office/word/2006/wordml\" xmlns:wp=\"http://schemas.openxmlformats.org/drawingml/2006/wordprocessingDrawing\" xmlns:wp14=\"http://schemas.microsoft.com/office/word/2010/wordprocessingDrawing\" xmlns:wpc=\"http://schemas.microsoft.com/office/word/2010/wordprocessingCanvas\" xmlns:wpg=\"http://schemas.microsoft.com/office/word/2010/wordprocessingGroup\" xmlns:wpi=\"http://schemas.microsoft.com/office/word/2010/wordprocessingInk\" xmlns:wps=\"http://schemas.microsoft.com/office/word/2010/wordprocessingShape\"></root>");
        final XmlCursor tmpCursor = tmpXmlObject.newCursor();
        try {
            tmpCursor.toEndToken();
            tmpCursor.toPrevToken();
            inputCursor.copyXml(tmpCursor);
            while (inputCursor.toNextSibling()) {
                inputCursor.copyXml(tmpCursor);
            }
        } finally {
            tmpCursor.dispose();
        }

        // Create picture embedded in run and keep relation id in map (input to output)
        final String tmpXmlText = tmpXmlObject.xmlText();
        createPictures(inputPicuteIdToOutputmap, tmpXmlText, inputParagraph.getDocument(),
                outputParagraph.getDocument());
        // replace picture IDs
        final XmlToken xmlWithOuputId = getXmlWithOuputId(inputPicuteIdToOutputmap, tmpXmlText);
        return xmlWithOuputId;
    }

    /**
     * Creates the frargment from the input paragraph and the {@link XWPFRun} to start the fragment from.
     * 
     * @param inputRun
     *            the {@link XWPFRun} marking the start of the fragment
     * @param inputCursor
     *            the {@link XmlCursor} that will be moved to the start of the fragment
     * @return a {@link XmlCursor} if the fragment must start before the given {@link XWPFRun}, <code>null</code> otherwise
     */
    private XmlCursor copyFromRun(XWPFRun inputRun, XmlCursor inputCursor) {
        XmlCursor savedCursor = null; // used to keep bookmarks before the referenced run
        do {
            if (inputCursor.isStart()) {
                if ("bookmarkStart".equals(inputCursor.getName().getLocalPart())) {
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
            if (inputCursor.getObject().equals(inputRun.getCTR()) || !inputCursor.toNextSibling()) {
                break;
            }
        } while (!inputCursor.getObject().equals(inputRun.getCTR()));

        return savedCursor;
    }

    /**
     * Gets the input {@link XWPFDocument} from either current input {@link XWPFParagraph}, previous input {@link XWPFParagraph}, or the
     * {@link List} of {@link Statement}.
     * 
     * @param currentInputParagraph
     *            the current input {@link XWPFParagraph}
     * @param previousInputParagraph
     *            the previous input {@link XWPFParagraph}
     * @param statements
     *            the {@link List} of {@link Statement}
     * @return the input {@link XWPFDocument} from either current input {@link XWPFParagraph}, previous input {@link XWPFParagraph}, or the
     *         {@link List} of {@link Statement} if any, <code>null</code> otherwise
     */
    @SuppressWarnings("resource")
    private XWPFDocument getInputDocument(XWPFParagraph currentInputParagraph, XWPFParagraph previousInputParagraph,
            List<Statement> statements) {
        final XWPFDocument res;

        if (currentInputParagraph != null) {
            res = currentInputParagraph.getDocument();
        } else if (previousInputParagraph != null) {
            res = previousInputParagraph.getDocument();
        } else if (!statements.isEmpty()) {
            final Statement statement = statements.get(0);
            if (statement.getStyleRun() != null) {
                res = statement.getStyleRun().getDocument();
            } else if (!statement.getRuns().isEmpty()) {
                res = statement.getRuns().get(0).getDocument();
            } else if (!statement.getClosingRuns().isEmpty()) {
                res = statement.getClosingRuns().get(0).getDocument();
            } else {
                res = null;
            }
        } else {
            res = null;
        }

        return res;
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
     * TODO OHA fix bug in nested table on usercontent (else case in code)
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
            final int index = headerFooter._getHdrFtr().getTblArray().length;
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
        XWPFParagraph newParagraph;
        if (document instanceof XWPFTableCell) {
            XWPFTableCell cell = (XWPFTableCell) document;
            newParagraph = cell.addParagraph();
        } else if (document instanceof XWPFDocument) {
            newParagraph = ((XWPFDocument) document).createParagraph();
        } else if (document instanceof XWPFHeaderFooter) {
            newParagraph = ((XWPFHeaderFooter) document).createParagraph();
        } else {
            throw new UnsupportedOperationException("unkown IBody type :" + document.getClass());
        }
        return newParagraph;
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
     * Change Picture Id.
     * 
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param listOutputRuns
     *            the {@link List} of output {@link XWPFRun}
     * @param listOutputParagraphs
     *            the {@link List} of output {@link XWPFParagraph}
     * @param listOutputTables
     *            the {@link List} of output {@link XWPFTable}
     * @throws XmlException
     *             XmlException
     */
    private void changePictureId(Map<String, String> inputPicuteIdToOutputmap, List<XWPFRun> listOutputRuns,
            List<XWPFParagraph> listOutputParagraphs, List<XWPFTable> listOutputTables) throws XmlException {

        for (XWPFRun run : listOutputRuns) {
            XmlToken outputXmlObject = getXmlWithOuputId(inputPicuteIdToOutputmap, run.getCTR().xmlText());
            if (outputXmlObject != null) {
                run.getCTR().set(outputXmlObject);
            }
        }

        for (XWPFParagraph paragraph : listOutputParagraphs) {
            XmlToken outputXmlObject = getXmlWithOuputId(inputPicuteIdToOutputmap, paragraph.getCTP().xmlText());
            if (outputXmlObject != null) {
                paragraph.getCTP().set(outputXmlObject);
            }
        }

        for (XWPFTable table : listOutputTables) {
            XmlToken outputXmlObject = getXmlWithOuputId(inputPicuteIdToOutputmap, table.getCTTbl().xmlText());
            if (outputXmlObject != null) {
                table.getCTTbl().set(outputXmlObject);
            }
        }

    }

    /**
     * Get Xml With Ouput picture Id.
     * 
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param xmlText
     *            xmlText
     * @return Xml With Ouput picture Id
     * @throws XmlException
     *             XmlException
     */
    private XmlToken getXmlWithOuputId(Map<String, String> inputPicuteIdToOutputmap, String xmlText)
            throws XmlException {
        final StringBuilder builder = new StringBuilder(xmlText.length());

        final Matcher matcher = PICTURE_ID_PATTERN.matcher(xmlText);

        int lastIndex = 0;
        while (matcher.find()) {
            builder.append(xmlText.subSequence(lastIndex, matcher.start(PICTURE_ID_GROUP_ID)));
            final String oldID = matcher.group(PICTURE_ID_GROUP_ID);
            final String newID = inputPicuteIdToOutputmap.get(oldID);
            if (newID != null) {
                builder.append(newID);
            } else {
                builder.append(oldID);
            }
            lastIndex = matcher.end(PICTURE_ID_GROUP_ID);
        }
        builder.append(xmlText.substring(lastIndex, xmlText.length()));

        return XmlToken.Factory.parse(builder.toString());
    }

    /**
     * createPictures in document.
     * 
     * @param inputPicureIdToOutputmap
     *            the picture ID mapping
     * @param xmlText
     *            the xml text to search for pictures
     * @param inputDoc
     *            input Document
     * @param outputDoc
     *            output Document
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void createPictures(Map<String, String> inputPicureIdToOutputmap, String xmlText, XWPFDocument inputDoc,
            XWPFDocument outputDoc) throws InvalidFormatException {

        final Matcher matcher = PICTURE_ID_PATTERN.matcher(xmlText);
        while (matcher.find()) {
            final String idRelationInput = matcher.group(PICTURE_ID_GROUP_ID);
            if (!inputPicureIdToOutputmap.containsKey(idRelationInput)) {
                final XWPFPictureData inputPic = inputDoc.getPictureDataByID(idRelationInput);
                final String idRelationOutput = outputDoc.addPictureData(inputPic.getData(), inputPic.getPictureType());
                inputPicureIdToOutputmap.put(idRelationInput, idRelationOutput);
            }
        }
    }

}
