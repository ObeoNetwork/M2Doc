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

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.emf.common.util.EList;
import org.obeonetwork.m2doc.parser.DocumentParsingError;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

/**
 * The {@link TemplateParsingValidator} class adds error messages coming from parsing into a copy of the original template.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateParsingValidator extends TemplateSwitch<AbstractConstruct> {
    /**
     * The font size of error messages.
     */
    private static final int ERROR_MESSAGE_FONT_SIZE = 16;
    /**
     * constant defining the color of error messages.
     */
    private static final String ERROR_COLOR = "FF0000";
    /**
     * Separator between the text of a M2DOC template element and a corresponding parsing error and between two parsing error.
     */
    private static final String BLANK_SEPARATOR = "    ";
    /**
     * Th separator between the tag were a parsing error has been detected and the start of the error message.
     */
    private static final String LOCATION_SEPARATOR = "<---";

    /**
     * Create a new {@link TemplateParsingValidator} instance given some definitions
     * and a query environment.
     */
    public TemplateParsingValidator() {
    }

    @Override
    public AbstractConstruct caseTemplate(Template object) {
        List<AbstractConstruct> subConstructs = object.getSubConstructs();
        for (int i = 0; i < subConstructs.size(); i++) {
            doSwitch(subConstructs.get(i));
        }
        return object;
    }

    @Override
    public AbstractConstruct caseStaticFragment(StaticFragment object) {
        return object;
    }

    @Override
    public AbstractConstruct caseQuery(Query object) {
        insertErrorMessages(object);
        return object;
    }

    /**
     * Insert all parsing error messages in a run next to the one of the given construct subject to the error.
     * 
     * @param abstractConstruct
     *            the construct that may contains parsing errors to insert into the produced document.
     */
    @SuppressWarnings("deprecation")
    protected void insertErrorMessages(AbstractConstruct abstractConstruct) {
        EList<DocumentParsingError> parsingErrors = abstractConstruct.getParsingErrors();
        int lastOriginRunIndex = 0;
        if (abstractConstruct.getRuns().size() > 0) {
            lastOriginRunIndex = abstractConstruct.getRuns().get(0).getParagraph().getRuns()
                    .indexOf(abstractConstruct.getRuns().get(abstractConstruct.getRuns().size() - 1));
        }
        int runInsertionNumber = 0;
        for (DocumentParsingError documentParsingError : parsingErrors) {
            addRunError(documentParsingError.getMessage(),
                    abstractConstruct.getRuns().get(abstractConstruct.getRuns().size() - 1));
            runInsertionNumber += 3;
        }
        if (runInsertionNumber != 0 && abstractConstruct.getRuns().size() > 0) {
            // We insert a blank after the last error message
            XWPFParagraph paragraph = abstractConstruct.getRuns().get(0).getParagraph();
            XWPFRun newBlankRun = paragraph.insertNewRun(lastOriginRunIndex + runInsertionNumber + 1);
            newBlankRun.setText(BLANK_SEPARATOR);
        }
    }

    /**
     * Inserts the given error message in a run next to the one of the given construct subject to the error.
     * 
     * @param errorMessage
     *            the error message to insert in a run.
     * @param problemRun
     *            the run subject to a parsing anomaly.
     */
    @SuppressWarnings("deprecation")
    private void addRunError(String errorMessage, XWPFRun problemRun) {
        // separation run.
        XWPFParagraph paragraph = problemRun.getParagraph();
        XWPFRun newBlankRun = paragraph.insertNewRun(problemRun.getParagraph().getRuns().indexOf(problemRun) + 1);
        newBlankRun.setText(BLANK_SEPARATOR);

        XWPFRun separationRun = problemRun.getParagraph()
                .insertNewRun(problemRun.getParagraph().getRuns().indexOf(problemRun) + 2);
        separationRun.setText(LOCATION_SEPARATOR);
        separationRun.setColor(ERROR_COLOR);
        separationRun.setFontSize(ERROR_MESSAGE_FONT_SIZE);
        CTHighlight newHighlight = separationRun.getCTR().getRPr().addNewHighlight();
        newHighlight.setVal(STHighlightColor.LIGHT_GRAY);

        XWPFRun newRun = problemRun.getParagraph()
                .insertNewRun(problemRun.getParagraph().getRuns().indexOf(problemRun) + 3);
        newRun.setText(errorMessage);
        newRun.setColor(ERROR_COLOR);
        newRun.setFontSize(ERROR_MESSAGE_FONT_SIZE);
        newHighlight = newRun.getCTR().getRPr().addNewHighlight();
        newHighlight.setVal(STHighlightColor.LIGHT_GRAY);

    }

    @Override
    public AbstractConstruct caseRepetition(Repetition object) {
        insertErrorMessages(object);
        return object;

    }

    @Override
    public AbstractConstruct caseConditionnal(Conditionnal object) {
        insertErrorMessages(object);
        return object;
    }

    @Override
    public AbstractConstruct caseTable(Table object) {
        insertErrorMessages(object);
        return super.caseTable(object);
    }

    @Override
    public AbstractConstruct caseImage(Image object) {
        insertErrorMessages(object);
        return super.caseImage(object);
    }

    @Override
    public AbstractConstruct caseRepresentation(Representation object) {
        insertErrorMessages(object);
        return super.caseRepresentation(object);
    }

}
