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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Table;
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

    @Override
    public Void caseDocumentTemplate(DocumentTemplate documentTemplate) {
        for (Block template : documentTemplate.getHeaders()) {
            doSwitch(template);
        }
        doSwitch(documentTemplate.getBody());
        for (Block template : documentTemplate.getFooters()) {
            doSwitch(template);
        }

        for (Template template : documentTemplate.getTemplates()) {
            doSwitch(template);
        }

        return null;
    }

    @Override
    public Void caseTemplate(Template template) {
        insertErrorMessages(template);
        doSwitch(template.getBody());

        return null;
    }

    @Override
    public Void caseBlock(Block block) {
        insertErrorMessages(block);
        for (IConstruct construct : block.getStatements()) {
            doSwitch(construct);
        }

        return null;
    }

    @Override
    public Void caseConditional(Conditional conditional) {
        insertErrorMessages(conditional);
        doSwitch(conditional.getThen());
        if (conditional.getElse() != null) {
            doSwitch(conditional.getElse());
        }

        return null;
    }

    @Override
    public Void caseUserDoc(UserDoc object) {
        insertErrorMessages(object);
        doSwitch(object.getBody());

        return null;
    }

    @Override
    public Void caseRepetition(Repetition repetition) {
        insertErrorMessages(repetition);
        doSwitch(repetition.getBody());

        return null;
    }

    @Override
    public Void caseLet(Let let) {
        insertErrorMessages(let);
        doSwitch(let.getBody());

        return null;
    }

    @Override
    public Void caseQuery(Query query) {
        insertErrorMessages(query);

        return null;
    }

    @Override
    public Void caseComment(Comment comment) {
        insertErrorMessages(comment);

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
        doSwitch(cell.getBody());

        return null;
    }

    @Override
    public Void caseBookmark(Bookmark bookmark) {
        insertErrorMessages(bookmark);
        doSwitch(bookmark.getBody());

        return null;
    }

    @Override
    public Void caseLink(Link link) {
        insertErrorMessages(link);
        return null;
    }

    /**
     * Insert all messages in a run next to the one of the given construct subject to the error.
     * 
     * @param abstractConstruct
     *            the construct that may contains messages to insert into the produced document.
     */
    protected void insertErrorMessages(IConstruct abstractConstruct) {
        final List<TemplateValidationMessage> messages = abstractConstruct.getValidationMessages();
        final Map<XWPFRun, Integer> offsets = new HashMap<>();
        for (TemplateValidationMessage message : messages) {
            offsets.put(message.getLocation(), 0);
        }
        for (TemplateValidationMessage message : messages) {
            final int offset = offsets.get(message.getLocation());
            final int shift = addRunError(message, offset);
            offsets.put(message.getLocation(), offset + shift);
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
            newBlankRun.setText(M2DocUtils.BLANK_SEPARATOR);

            res++;
            final XWPFRun separationRun = paragraph.insertNewRun(basePosition + res);
            separationRun.setText(M2DocUtils.LOCATION_SEPARATOR);
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
