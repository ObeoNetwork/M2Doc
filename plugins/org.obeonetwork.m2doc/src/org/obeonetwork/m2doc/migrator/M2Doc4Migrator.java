/*******************************************************************************
 *  Copyright (c) 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.migrator;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.util.FieldUtils;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Migrates template to version 4.x of M2Doc.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2Doc4Migrator implements IM2DocMigrator {

    /**
     * The instance of {@link FieldUtils}.
     */
    private static final FieldUtils FIELD_UTILS = new FieldUtils();

    @Override
    public List<TemplateValidationMessage> migrate(IBody body) {
        final List<TemplateValidationMessage> result = new ArrayList<>();

        for (IBodyElement element : body.getBodyElements()) {
            if (element instanceof XWPFParagraph) {
                result.addAll(migrateParagraph((XWPFParagraph) element));
            } else if (element instanceof XWPFTable) {
                result.addAll(migrateTable((XWPFTable) element));
            }
        }

        return result;
    }

    /**
     * Migrates the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to migrate
     * @return the {@link List} of {@link TemplateValidationMessage}
     */
    protected List<TemplateValidationMessage> migrateParagraph(XWPFParagraph paragraph) {
        final List<TemplateValidationMessage> result = new ArrayList<>();

        boolean inField = false;
        boolean isM2DocInstruction = false;
        StringBuilder instructionText = null;
        List<XWPFRun> fieldRuns = null;
        for (XWPFRun run : new ArrayList<>(paragraph.getRuns())) {
            if (FIELD_UTILS.isFieldBegin(run)) {
                inField = true;
                fieldRuns = new ArrayList<>();
                instructionText = new StringBuilder();
            }

            if (inField) {
                fieldRuns.add(run);
                if (!isM2DocInstruction) {
                    final String runInstructionText = FIELD_UTILS.readUpInstrText(run);
                    if (!runInstructionText.isEmpty()) {
                        instructionText.append(runInstructionText);
                        isM2DocInstruction = instructionText.indexOf(M2DocUtils.M) > -1;
                    }
                }
                inField = !FIELD_UTILS.isFieldEnd(run);
                if (FIELD_UTILS.isFieldEnd(run)) {
                    inField = false;
                    if (isM2DocInstruction) {
                        migrateField(paragraph, fieldRuns);
                    }
                }
            }
        }

        if (inField) {
            final XWPFRun lastParagraphRun = paragraph.getRuns().get(paragraph.getRuns().size() - 1);
            result.add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, "Multi paragraph field detected.",
                    lastParagraphRun));
        }

        return result;
    }

    /**
     * Migrates the given M2Doc field instruction represented by the {@link List} of {@link XWPFRun} in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the containing {@link XWPFParagraph}
     * @param fieldRuns
     *            the M2Doc field instruction represented by the {@link List} of {@link XWPFRun}
     * @return the {@link List} of {@link TemplateValidationMessage}
     */
    private List<TemplateValidationMessage> migrateField(XWPFParagraph paragraph, List<XWPFRun> fieldRuns) {
        final List<TemplateValidationMessage> result = new ArrayList<>();

        XWPFRun lastRun = null;
        boolean isFirstRun = true;
        for (XWPFRun run : fieldRuns) {
            final String instructionText = FIELD_UTILS.readUpInstrText(run);
            if (FIELD_UTILS.isFieldBegin(run) || FIELD_UTILS.isFieldEnd(run)
                || FIELD_UTILS.isFieldSeparate(run.getCTR()) || instructionText.isBlank()) {
                final int index = paragraph.getRuns().indexOf(run);
                paragraph.removeRun(index);
            } else {
                lastRun = run;
                run.getCTR().setInstrTextArray(null);
                run.getCTR().setTArray(null);
                if (isFirstRun) {
                    int startIndex = 0;
                    while (startIndex < instructionText.length()
                        && Character.isWhitespace(instructionText.charAt(startIndex))) {
                        startIndex++;
                    }
                    run.setText("{" + instructionText.substring(startIndex));
                    isFirstRun = false;
                } else {
                    run.setText(instructionText);
                }
            }
        }
        if (lastRun != null) {
            final String text = lastRun.getText(0);
            int endIndex = text.length();
            while (endIndex >= 0 && Character.isWhitespace(text.charAt(endIndex - 1))) {
                endIndex--;
            }
            lastRun.setText(lastRun.getText(0).substring(0, endIndex) + "}", 0);
        }

        return result;
    }

    /**
     * Migrates the given {@link XWPFTable}.
     * 
     * @param table
     *            the {@link XWPFTable} to migrate
     * @return the {@link List} of {@link TemplateValidationMessage}
     */
    protected List<TemplateValidationMessage> migrateTable(XWPFTable table) {
        final List<TemplateValidationMessage> result = new ArrayList<>();

        for (XWPFTableRow row : table.getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                migrate(cell);
            }
        }

        return result;
    }

}
