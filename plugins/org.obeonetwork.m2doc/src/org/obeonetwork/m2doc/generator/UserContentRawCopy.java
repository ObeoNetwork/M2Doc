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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlToken;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.UserContent;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;

/**
 * UserContent Raw Copy.
 * 
 * @author ohaegi
 */
public class UserContentRawCopy {

    /**
     * Current Input Paragraph.
     */
    private XWPFParagraph currentInputParagraph;

    /**
     * Current Output Paragraph.
     */
    private XWPFParagraph currentOutputParagraph;
    /**
     * Previous Input Paragraph of UserContent tag content.
     */
    private XWPFParagraph previousInputParagraph;

    /**
     * List of Output Paragraphs.
     * Used to custom id relation picture.
     */
    private List<XWPFParagraph> listOutputParagraphs = new ArrayList<XWPFParagraph>();
    /**
     * List Output Tables.
     * Used to custom id relation picture.
     */
    private List<XWPFTable> listOutputTables = new ArrayList<XWPFTable>();
    /**
     * List Output Runs.
     * Those runs is located at begin of usercontent tag content before first paragraph
     * Used to custom id relation picture.
     */
    private List<XWPFRun> listOutputRuns = new ArrayList<XWPFRun>();

    /**
     * Input Picute Id To Output map.
     * Used to custom id relation picture.
     */
    private Map<String, String> inputPicuteIdToOutputmap = new HashMap<String, String>();

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
     *            Output Paragraph Before User Doc Dest content (User Code dest is writen by {@link TemplateProcessor} )
     * @param outputBody
     *            output body
     * @return last paragraph created by copy
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws XmlException
     *             XmlException
     */
    @SuppressWarnings("deprecation")
    public XWPFParagraph copy(UserContent userContent, XWPFParagraph outputParagraphBeforeUserDocContent,
            IBody outputBody) throws InvalidFormatException, XmlException {
        XWPFDocument containerOutputDocument = outputParagraphBeforeUserDocContent.getDocument();
        // Test if run before userContent is in same XWPFParagraph than first run of userContent
        if (!userDocContentIsFirstRunOfParagraph(userContent)) {
            previousInputParagraph = userContent.getRuns().get(userContent.getRuns().size() - 1).getParagraph();
            currentInputParagraph = previousInputParagraph;
            currentOutputParagraph = outputParagraphBeforeUserDocContent;
        }
        XWPFParagraph currentRunParagraph = null;
        for (AbstractConstruct abstractConstruct : userContent.getSubConstructs()) {
            for (XWPFRun inputRun : abstractConstruct.getRuns()) {
                currentRunParagraph = inputRun.getParagraph();
                if (currentRunParagraph != currentInputParagraph) {
                    currentInputParagraph = currentRunParagraph;
                    // currentOutputParagraph = outputDocument.createParagraph();
                    currentOutputParagraph = createNewParagraph(outputBody);
                    // Copy new paragraph
                    currentOutputParagraph.getCTP().set(currentInputParagraph.getCTP());
                    listOutputParagraphs.add(currentOutputParagraph);
                }
                // Test if some run exist between userContent tag and first paragraph in this tag
                if (currentRunParagraph == previousInputParagraph) {
                    // Clone run directly, paragraph is already generate by normal processing
                    XWPFRun outputRun = currentOutputParagraph.createRun();
                    outputRun.getCTR().set(inputRun.getCTR());
                    // Keep run to change relation id later
                    listOutputRuns.add(outputRun);
                }
                // Create picture embedded in run and keep relation id in map (input to output)
                createPictures(inputRun, containerOutputDocument);
            }
            // In case of table (no run in abstractConstruct)
            if (abstractConstruct instanceof Table) {
                Table table = (Table) abstractConstruct;
                XWPFTable inputTable = table.getTable();
                // XWPFTable outputTable = contenerOutputDocument.createTable();
                XWPFTable outputTable = createNewTable(outputBody, inputTable);
                outputTable.getCTTbl().set(inputTable.getCTTbl());
                copyTableStyle(inputTable, containerOutputDocument);
                listOutputTables.add(outputTable);
                // Inspect table to extract all picture ID in run
                collectRelationId(inputTable, containerOutputDocument);

            }
        }
        // Change Picture Id by xml replacement
        changePictureId();

        if (userContent.getClosingRuns().size() != 0
            && currentInputParagraph == userContent.getClosingRuns().get(0).getParagraph()) {
            needNewParagraph = false;
        }
        return currentOutputParagraph;
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
        XWPFTable generatedTable;
        CTTbl copy = (CTTbl) inputTable.getCTTbl().copy();
        if (document instanceof XWPFDocument) {
            generatedTable = ((XWPFDocument) document).createTable();
        } else if (document instanceof XWPFTableCell) {
            XWPFTableCell tCell = (XWPFTableCell) document;
            int tableRank = tCell.getTables().size();
            generatedTable = new XWPFTable(copy, tCell, 0, 0);
            tCell.insertTable(tableRank, generatedTable);
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
     */
    private static void copyTableStyle(XWPFTable inputTable, XWPFDocument outputDoc) {
        XWPFDocument inputDoc = inputTable.getBody().getXWPFDocument();
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

    /**
     * Collect Relation Id in table.
     * Put picture in output document and keep old and new picture id in map.
     * 
     * @param inputWTable
     *            inputWTable
     * @param outputDoc
     *            outputDoc
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void collectRelationId(XWPFTable inputWTable, XWPFDocument outputDoc) throws InvalidFormatException {
        for (XWPFTableRow row : inputWTable.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                    collectRelationId(paragraph, outputDoc);
                }
            }
        }
    }

    /**
     * Collect Relation Id in paragraph.
     * 
     * @param paragraph
     *            paragraph
     * @param outputDoc
     *            outputDoc
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void collectRelationId(XWPFParagraph paragraph, XWPFDocument outputDoc) throws InvalidFormatException {
        for (XWPFRun run : paragraph.getRuns()) {
            createPictures(run, outputDoc);
        }
    }

    /**
     * Change Picture Id.
     * 
     * @throws XmlException
     *             XmlException
     */
    private void changePictureId() throws XmlException {

        for (XWPFRun run : listOutputRuns) {
            XmlToken outputXmlObject = getXmlWithOuputId(run.getCTR().xmlText());
            if (outputXmlObject != null) {
                run.getCTR().set(outputXmlObject);
            }
        }

        for (XWPFParagraph paragraph : listOutputParagraphs) {
            XmlToken outputXmlObject = getXmlWithOuputId(paragraph.getCTP().xmlText());
            if (outputXmlObject != null) {
                paragraph.getCTP().set(outputXmlObject);
            }
        }

        for (XWPFTable table : listOutputTables) {
            XmlToken outputXmlObject = getXmlWithOuputId(table.getCTTbl().xmlText());
            if (outputXmlObject != null) {
                table.getCTTbl().set(outputXmlObject);
            }
        }

    }

    /**
     * Get Xml With Ouput picture Id.
     * 
     * @param xmlText
     *            xmlText
     * @return Xml With Ouput picture Id
     * @throws XmlException
     *             XmlException
     */
    private XmlToken getXmlWithOuputId(String xmlText) throws XmlException {
        String outputXmlStr = xmlText;
        for (Map.Entry<String, String> entry : inputPicuteIdToOutputmap.entrySet()) {
            String inputID = entry.getKey();
            String outputID = entry.getValue();
            outputXmlStr = outputXmlStr.replaceAll("<a:blip r:embed=\"" + inputID + "\"",
                    "<a:blip r:embed=NEW\"" + outputID + "\"");
        }
        // Clean build string
        outputXmlStr = outputXmlStr.replaceAll("<a:blip r:embed=NEW", "<a:blip r:embed=");

        XmlToken outputXmlObject = null;
        outputXmlObject = XmlToken.Factory.parse(outputXmlStr);
        return outputXmlObject;
    }

    /**
     * Test if first userContent content begin by a paragraph.
     * 
     * @param userContent
     *            userContent EObject
     * @return true if userContent content begin by a paragraph
     */
    private boolean userDocContentIsFirstRunOfParagraph(UserContent userContent) {
        XWPFRun userContentFirstRun = userContent.getSubConstructs().get(0).getRuns().get(0);
        @SuppressWarnings("deprecation")
        XWPFRun paragraphFirstRun = userContentFirstRun.getParagraph().getRuns().get(0);
        return userContentFirstRun == paragraphFirstRun;
    }

    /**
     * createPictures in document.
     * 
     * @param inputRun
     *            input Run
     * @param outputDoc
     *            output Document
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    private void createPictures(XWPFRun inputRun, XWPFDocument outputDoc) throws InvalidFormatException {
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
