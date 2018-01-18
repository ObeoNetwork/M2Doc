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
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.Statement;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.UserContent;
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
    private static final Pattern REPLACEMENT_PICTURE_ID_PATTERN = Pattern
            .compile("<a:blip( .*). r:embed=\\\"([^\\\"]+)\\\"( .* )?>");

    /**
     * The replacement picture id {@link Pattern}.
     */
    private static final int REPLACEMENT_PICTURE_ID_GROUP_ID = 2;

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
     * @param outputBody
     *            output body
     * @return last paragraph created by copy
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws XmlException
     *             XmlException
     * @throws IOException
     *             if the copy fails
     * @deprecated use {@link #copy(UserContent, XWPFParagraph)}
     */
    @Deprecated
    public XWPFParagraph copy(UserContent userContent, XWPFParagraph outputParagraphBeforeUserDocContent,
            IBody outputBody) throws InvalidFormatException, XmlException, IOException {
        return copy(userContent, outputParagraphBeforeUserDocContent);
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
    private XWPFParagraph copyStatements(XWPFParagraph outputParagraph, XWPFParagraph currentInputParagraph,
            XWPFParagraph previousInputParagraph, final List<Statement> statements)
            throws InvalidFormatException, IOException, XmlException {
        XWPFParagraph localCurrentInputParagraph = currentInputParagraph;
        final XWPFDocument containerOutputDocument = outputParagraph.getDocument();
        final IBody outputBody = outputParagraph.getBody();
        final List<XWPFParagraph> listOutputParagraphs = new ArrayList<>();
        final List<XWPFTable> listOutputTables = new ArrayList<>();
        final List<XWPFRun> listOutputRuns = new ArrayList<>();
        final Map<String, String> inputPicuteIdToOutputmap = new HashMap<>();
        XWPFParagraph currentOutputParagraph = outputParagraph;
        for (Statement statement : statements) {
            for (XWPFRun inputRun : statement.getRuns()) {
                final XWPFParagraph currentRunParagraph = (XWPFParagraph) inputRun.getParent();
                if (currentRunParagraph != localCurrentInputParagraph) {
                    localCurrentInputParagraph = currentRunParagraph;
                    // currentOutputParagraph = outputDocument.createParagraph();
                    currentOutputParagraph = createNewParagraph(outputBody);
                    // Copy new paragraph
                    currentOutputParagraph.getCTP().set(localCurrentInputParagraph.getCTP());
                    listOutputParagraphs.add(currentOutputParagraph);
                }
                // Test if some run exist between userContent tag and first paragraph in this tag
                if (currentRunParagraph == previousInputParagraph) {
                    // Clone run directly, paragraph is already generate by normal processing
                    final XWPFRun outputRun = currentOutputParagraph.createRun();
                    outputRun.getCTR().set(inputRun.getCTR());
                    // Keep run to change relation id later
                    listOutputRuns.add(outputRun);
                }
                // Create picture embedded in run and keep relation id in map (input to output)
                createPictures(inputPicuteIdToOutputmap, inputRun, containerOutputDocument);
            }
            // In case of table (no run in abstractConstruct)
            if (statement instanceof Table) {
                Table table = (Table) statement;
                XWPFTable inputTable = table.getTable();
                // XWPFTable outputTable = contenerOutputDocument.createTable();
                XWPFTable outputTable = createNewTable(outputBody, inputTable);
                outputTable.getCTTbl().set(inputTable.getCTTbl());
                copyTableStyle(inputTable, containerOutputDocument);
                listOutputTables.add(outputTable);
                // Inspect table to extract all picture ID in run
                collectRelationId(inputPicuteIdToOutputmap, inputTable, containerOutputDocument);
            } else if (statement instanceof ContentControl) {
                final ContentControl contentControl = (ContentControl) statement;
                final XWPFSDT control = contentControl.getControl();
                copyControl(outputBody, control);
            }
        }
        // Change Picture Id by xml replacement
        changePictureId(inputPicuteIdToOutputmap, listOutputRuns, listOutputParagraphs, listOutputTables);
        return currentOutputParagraph;
    }

    /**
     * Copies the given {@link XWPFSDT} in the given output {@link IBody}.
     * 
     * @param outputBody
     *            the output {@link IBody}
     * @param control
     *            the {@link XWPFSDT} to insert
     */
    private void copyControl(IBody outputBody, XWPFSDT control) {
        final CTSdtBlock sdt;
        if (outputBody instanceof XWPFDocument) {
            sdt = ((XWPFDocument) outputBody).getDocument().getBody().addNewSdt();
        } else if (outputBody instanceof XWPFHeaderFooter) {
            sdt = ((XWPFHeaderFooter) outputBody)._getHdrFtr().addNewSdt();
        } else if (outputBody instanceof XWPFFootnote) {
            sdt = ((XWPFFootnote) outputBody).getCTFtnEdn().addNewSdt();
        } else if (outputBody instanceof XWPFTableCell) {
            sdt = ((XWPFTableCell) outputBody).getCTTc().addNewSdt();
        } else {
            throw new IllegalStateException("can't insert control in " + outputBody.getClass().getCanonicalName());
        }
        // TODO we miss POI write API
        // XWPFSDT c = new XWPFSDT(sdt, outputBody);
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
        final XWPFTable generatedTable;
        final CTTbl copy = (CTTbl) inputTable.getCTTbl().copy();
        if (document instanceof XWPFDocument) {
            generatedTable = ((XWPFDocument) document).createTable();
        } else if (document instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) document;
            final int index = headerFooter._getHdrFtr().getTblArray().length;
            final CTTbl cttbl = headerFooter._getHdrFtr().insertNewTbl(index);
            XWPFTable newTable = new XWPFTable(cttbl, headerFooter);
            if (newTable.getRows().size() > 0) {
                newTable.removeRow(0);
            }
            headerFooter.insertTable(index, newTable);
            generatedTable = headerFooter.getTables().get(index);
            generatedTable.getCTTbl().set(copy);
        } else if (document instanceof XWPFTableCell) {
            XWPFTableCell tCell = (XWPFTableCell) document;
            int tableRank = tCell.getTables().size();
            tCell.insertTable(tableRank, new XWPFTable(copy, tCell, 0, 0));
            generatedTable = tCell.getTables().get(tableRank);
        } else {
            throw new UnsupportedOperationException("unknown type of IBody : " + document.getClass());
        }
        return generatedTable;
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
     * Collect Relation Id in table.
     * Put picture in output document and keep old and new picture id in map.
     * 
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param inputWTable
     *            inputWTable
     * @param outputDoc
     *            outputDoc
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void collectRelationId(Map<String, String> inputPicuteIdToOutputmap, XWPFTable inputWTable,
            XWPFDocument outputDoc) throws InvalidFormatException {
        for (XWPFTableRow row : inputWTable.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                    collectRelationId(inputPicuteIdToOutputmap, paragraph, outputDoc);
                }
            }
        }
    }

    /**
     * Collect Relation Id in paragraph.
     *
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param paragraph
     *            paragraph
     * @param outputDoc
     *            outputDoc
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void collectRelationId(Map<String, String> inputPicuteIdToOutputmap, XWPFParagraph paragraph,
            XWPFDocument outputDoc) throws InvalidFormatException {
        for (XWPFRun run : paragraph.getRuns()) {
            createPictures(inputPicuteIdToOutputmap, run, outputDoc);
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

        final Matcher matcher = REPLACEMENT_PICTURE_ID_PATTERN.matcher(xmlText);

        int lastIndex = 0;
        while (matcher.find()) {
            builder.append(xmlText.subSequence(lastIndex, matcher.start(REPLACEMENT_PICTURE_ID_GROUP_ID)));
            builder.append(inputPicuteIdToOutputmap.get(matcher.group(REPLACEMENT_PICTURE_ID_GROUP_ID)));
            lastIndex = matcher.end(REPLACEMENT_PICTURE_ID_GROUP_ID);
        }
        builder.append(xmlText.substring(lastIndex, xmlText.length()));

        return XmlToken.Factory.parse(builder.toString());
    }

    /**
     * createPictures in document.
     * 
     * @param inputPicuteIdToOutputmap
     *            the picture ID mapping
     * @param inputRun
     *            input Run
     * @param outputDoc
     *            output Document
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void createPictures(Map<String, String> inputPicuteIdToOutputmap, XWPFRun inputRun, XWPFDocument outputDoc)
            throws InvalidFormatException {
        // Add picture in document and keep relation id change idRelation reference
        for (XWPFPicture inputPic : inputRun.getEmbeddedPictures()) {
            byte[] img = inputPic.getPictureData().getData();
            // Put image in doc and get idRelation
            String idRelationOutput = outputDoc.addPictureData(img, inputPic.getPictureData().getPictureType());
            String idRelationInput = inputPic.getCTPicture().getBlipFill().getBlip().getEmbed();
            inputPicuteIdToOutputmap.put(idRelationInput, idRelationOutput);
        }
    }

}
