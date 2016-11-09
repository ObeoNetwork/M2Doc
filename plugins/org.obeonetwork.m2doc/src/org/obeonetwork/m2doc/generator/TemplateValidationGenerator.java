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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TableMerge;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

/**
 * The {@link TemplateValidationGenerator} class adds error messages coming from parsing into a copy of the original template.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateValidationGenerator extends TemplateSwitch<Void> {

    /**
     * The font size of error messages.
     */
    private static final int ERROR_MESSAGE_FONT_SIZE = 16;

    /**
     * Separator between the text of a M2DOC template element and a corresponding parsing error and between two parsing error.
     */
    private static final String BLANK_SEPARATOR = "    ";

    /**
     * The separator between the tag were a parsing error has been detected and the start of the error message.
     */
    private static final String LOCATION_SEPARATOR = "<---";

    /**
     * Tells whether the template contains parsing errors or not.
     */
    private boolean inError;

    /**
     * Create a new {@link TemplateValidationGenerator} instance given some definitions
     * and a query environment.
     */
    public TemplateValidationGenerator() {
    }

    /**
     * Returns <code>true</code> when the template contains at least one parsing error.
     * 
     * @return <code>true</code> when there are parsing errors.
     */
    public boolean isInError() {
        return inError;
    }

    @Override
    public Void caseDocumentTemplate(DocumentTemplate documentTemplate) {
        for (Template template : documentTemplate.getHeaders()) {
            doSwitch(template);
        }
        doSwitch(documentTemplate.getBody());
        for (Template template : documentTemplate.getFooters()) {
            doSwitch(template);
        }

        return null;
    }

    @Override
    public Void caseTemplate(Template template) {
        insertErrorMessages(template);
        for (AbstractConstruct construct : template.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseConditionnal(Conditionnal conditional) {
        insertErrorMessages(conditional);
        for (AbstractConstruct construct : conditional.getSubConstructs()) {
            doSwitch(construct);
        }
        if (conditional.getAlternative() != null) {
            doSwitch(conditional.getAlternative());
        }
        if (conditional.getElse() != null) {
            doSwitch(conditional.getElse());
        }

        return null;
    }

    @Override
    public Void caseDefault(Default object) {
        insertErrorMessages(object);
        for (AbstractConstruct construct : object.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseUserDoc(UserDoc object) {
        insertErrorMessages(object);
        for (AbstractConstruct construct : object.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseRepetition(Repetition repetition) {
        insertErrorMessages(repetition);
        for (AbstractConstruct construct : repetition.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseTableMerge(TableMerge tableMerge) {
        insertErrorMessages(tableMerge);
        for (AbstractConstruct construct : tableMerge.getSubConstructs()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseQuery(Query query) {
        insertErrorMessages(query);

        return null;
    }

    @Override
    public Void caseTable(Table table) {
        insertErrorMessages(table);
        for (Row row : table.getRows()) {
            doSwitch(row);
        }

        return null;
    }

    @Override
    public Void caseRow(Row row) {
        for (Cell cell : row.getCells()) {
            doSwitch(cell);
        }

        return null;
    }

    @Override
    public Void caseCell(Cell cell) {
        doSwitch(cell.getTemplate());

        return null;
    }

    @Override
    public Void caseAbstractProviderClient(AbstractProviderClient providerClient) {
        insertErrorMessages(providerClient);

        return null;
    }

    /**
     * Insert all messages in a run next to the one of the given construct subject to the error.
     * 
     * @param abstractConstruct
     *            the construct that may contains messages to insert into the produced document.
     */
    protected void insertErrorMessages(AbstractConstruct abstractConstruct) {
        final List<TemplateValidationMessage> messages = abstractConstruct.getValidationMessages();
        final Map<XWPFRun, Integer> offsets = new HashMap<XWPFRun, Integer>();
        for (TemplateValidationMessage message : messages) {
            offsets.put(message.getLocation(), 0);
        }
        for (TemplateValidationMessage message : messages) {
            inError = inError || message.getLevel() == ValidationMessageLevel.ERROR;
            final int offset = offsets.get(message.getLocation());
            final int shift = addRunError(message, offset);
            offsets.put(message.getLocation(), offset + shift);
        }
        for (Entry<XWPFRun, Integer> entry : offsets.entrySet()) {
            if (entry.getValue() != 0) {
                // We insert a blank after the last error message
                final IRunBody parent = entry.getKey().getParent();
                if (parent instanceof XWPFParagraph) {
                    XWPFRun newBlankRun = ((XWPFParagraph) parent).insertNewRun(entry.getValue() + 1);
                    newBlankRun.setText(BLANK_SEPARATOR);
                }
            }
        }
    }

    /**
     * Inserts the given {@link TemplateValidationMessage} in a run next to the one of the given construct subject to the message.
     * 
     * @param message
     *            the {@link TemplateValidationMessage} to insert
     *            the error message to insert in a run
     * @param offset
     *            the offset in number of {@link XWPFRun} to insert after {@link TemplateValidationMessage#getLocation() message location}
     * @return the number of inserted {@link XWPFRun}
     */
    private int addRunError(TemplateValidationMessage message, int offset) {
        int res = 0;

        if (message.getLocation().getParent() instanceof XWPFParagraph) {
            XWPFParagraph paragraph = (XWPFParagraph) message.getLocation().getParent();
            final int basePosition = paragraph.getRuns().indexOf(message.getLocation()) + offset;

            // separation run.
            res++;
            final String color = M2DocUtils.getColor(message.getLevel());
            XWPFRun newBlankRun = paragraph.insertNewRun(basePosition + res);
            newBlankRun.setText(BLANK_SEPARATOR);

            res++;
            final XWPFRun separationRun = paragraph.insertNewRun(basePosition + res);
            separationRun.setText(LOCATION_SEPARATOR);
            separationRun.setColor(color);
            separationRun.setFontSize(ERROR_MESSAGE_FONT_SIZE);
            final CTHighlight separationHighlight = separationRun.getCTR().getRPr().addNewHighlight();
            separationHighlight.setVal(STHighlightColor.LIGHT_GRAY);

            res++;
            final XWPFRun messageRun = paragraph.insertNewRun(basePosition + res);
            messageRun.setText(message.getMessage());
            messageRun.setColor(color);
            messageRun.setFontSize(ERROR_MESSAGE_FONT_SIZE);
            final CTHighlight messageHighlight = messageRun.getCTR().getRPr().addNewHighlight();
            messageHighlight.setVal(STHighlightColor.LIGHT_GRAY);
        }

        return res;
    }

}
