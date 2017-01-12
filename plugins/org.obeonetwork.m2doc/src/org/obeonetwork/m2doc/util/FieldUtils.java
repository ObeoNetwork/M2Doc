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
package org.obeonetwork.m2doc.util;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.obeonetwork.m2doc.parser.TokenProvider;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/**
 * DocX Field tools.
 * Used to extract field value.
 * 
 * @author ohaegi
 */
public class FieldUtils {

    /**
     * read up a tag looking ahead the runs so that a prediction can be made
     * over the nature of a field.
     * <p>
     * Using such a method is mandatory because for some reasons fields like
     * {m:if ...} can be broken up in an unexpected number of runs thus
     * precluding the tag nature prediction based on the first run only.
     * </p>
     * 
     * @param runIterator
     *            run iterator
     * @return the complete text of the current field.
     */
    public String lookAheadTag(final TokenProvider runIterator) {
        int i = 1;
        // first run must begin a field.
        XWPFRun run = runIterator.lookAhead(i).getRun();
        if (run == null) {
            throw new IllegalStateException("lookAheadTag shouldn't be called on a table.");
        }
        if (isFieldBegin(run)) {
            StringBuilder builder = new StringBuilder();
            i++;
            run = runIterator.lookAhead(i).getRun();
            // run is null when EOF is reached or a table is encountered.
            while (run != null && !isFieldEnd(run)) {
                builder.append(readUpInstrText(run));
                run = runIterator.lookAhead(++i).getRun();
            }
            return builder.toString().trim();
        } else {
            return "";
        }
    }

    /**
     * Extract tag string (word field) from runs list.
     * The tag must begin at the first run, return empty sting if not.
     * 
     * @param runs
     *            list of run where find tag
     * @return tag string
     */
    public String lookAheadTag(final List<XWPFRun> runs) {
        int i = 0;
        XWPFRun run = runs.get(i);
        if (run != null) {
            if (isFieldBegin(run)) {
                StringBuilder builder = new StringBuilder();
                i++;
                run = runs.get(i);
                // run is null when EOF is reached or a table is encountered.
                while (run != null && !isFieldEnd(run)) {
                    builder.append(readUpInstrText(run));
                    run = runs.get(++i);
                }
                return builder.toString().trim();
            }
        }
        return "";
    }

    /**
     * Extract tag string (word field) from runs list.
     * The tag must begin at the first run, return empty sting if not.
     * 
     * @param runs
     *            list of run where find tag
     * @param index
     *            run index in iterator
     * @return tag string
     */
    public String lookAheadTag(List<XWPFRun> runs, int index) {
        int i = index;
        // first run must begin a field.
        XWPFRun run = runs.get(i);
        if (run != null) {
            if (isFieldBegin(run)) {
                StringBuilder builder = new StringBuilder();
                i++;
                run = runs.get(i);
                // run is null when EOF is reached or a table is encountered.
                while (run != null && !isFieldEnd(run)) {
                    builder.append(readUpInstrText(run));
                    run = runs.get(++i);
                }
                return builder.toString().trim();
            }
        }
        return "";
    }

    /**
     * Returns <code>true</code> when the specified run is a field begin run and <code>false</code> otherwise.
     * 
     * @param run
     *            the concerned run
     * @return <code>true</code> for field begin.
     */
    public boolean isFieldBegin(XWPFRun run) {
        if (run.getCTR().getFldCharList().size() > 0) {
            CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
            return STFldCharType.BEGIN.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> when the specified run is a field end run and <code>false</code> otherwise.
     * 
     * @param run
     *            the concerned run
     * @return <code>true</code> for field end.
     */

    public boolean isFieldEnd(XWPFRun run) {
        if (run.getCTR().getFldCharList().size() > 0) {
            CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
            return STFldCharType.END.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * reads up the instruction of a field's run.
     * 
     * @param run
     *            the run to read.
     * @return the aggregated instruction text of the run
     */
    public static StringBuilder readUpInstrText(XWPFRun run) {
        List<CTText> texts = run.getCTR().getInstrTextList();
        StringBuilder runBuilder = new StringBuilder();
        for (CTText text : texts) {
            runBuilder.append(text.getStringValue());
        }
        return runBuilder;
    }

    /**
     * * Add instruction word Document Field.
     * 
     * @param paragraph
     *            paragraph where write instruction
     * @param instruction
     *            M2Doc tag
     */
    public void addInstrField(XWPFParagraph paragraph, String instruction) {
        // Add r begin
        CTFldChar fldCharBegin = paragraph.getCTP().addNewR().addNewFldChar();
        fldCharBegin.setFldCharType(STFldCharType.BEGIN);

        // Add m2doc tag
        CTText instr = paragraph.getCTP().addNewR().addNewInstrText();
        instr.setStringValue(instruction);

        // Add r Separate
        CTFldChar fldCharSeparate = paragraph.getCTP().addNewR().addNewFldChar();
        fldCharSeparate.setFldCharType(STFldCharType.SEPARATE);

        // Add r Separate
        CTFldChar fldCharEnd = paragraph.getCTP().addNewR().addNewFldChar();
        fldCharEnd.setFldCharType(STFldCharType.END);
    }
}
