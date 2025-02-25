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
package org.obeonetwork.m2doc.parser;

import java.util.Arrays;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 * Splits a {@link XWPFRun} and insert the given {@link ParsingToken} in between.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
class RunSplit {

    /**
     * The {@link XWPFParagraph}.
     */
    private final XWPFParagraph paragraph;

    /**
     * The {@link List} of {@link ParsingToken} for the given {@link XWPFParagraph}.
     */
    private List<ParsingToken> tokens;

    /**
     * The {@link XWPFRun} index in the {@link XWPFParagraph}.
     */
    private final int runIndex;

    /**
     * The {@link CTText} index in the {@link XWPFRun}.
     */
    private final int tIndex;

    /**
     * The char index in the {@link CTText}.
     */
    private final int charIndex;

    /**
     * The {@link ParsingToken} to insert.
     */
    private final ParsingToken tokenToInsert;

    /**
     * Constructor.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param tokens
     *            the {@link List} of {@link ParsingToken} for the given {@link XWPFParagraph}
     * @param runIndex
     *            the {@link XWPFRun} index in the {@link XWPFParagraph}
     * @param tIndex
     *            the {@link CTText} index in the {@link XWPFRun}
     * @param charIndex
     *            the char index in the {@link CTText}
     * @param tokenToInsert
     *            the {@link ParsingToken} to insert
     */
    RunSplit(XWPFParagraph paragraph, List<ParsingToken> tokens, int runIndex, int tIndex, int charIndex,
            ParsingToken tokenToInsert) {
        this.paragraph = paragraph;
        this.tokens = tokens;
        this.runIndex = runIndex;
        this.tIndex = tIndex;
        this.charIndex = charIndex;
        this.tokenToInsert = tokenToInsert;
    }

    /**
     * Executes the split.
     */
    void split() {
        if (tokenToInsert == ParsingToken.START_FIELD_TOKEN) {
            if (tIndex == 0 && charIndex == 0) {
                doSplitStartFieldStartRun();
            } else {
                doSplitStartField();
            }
        } else if (tokenToInsert == ParsingToken.END_FIELD_TOKEN) {
            final XWPFRun run = paragraph.getRuns().get(runIndex);
            final CTText text = run.getCTR().getTList().get(tIndex);
            if (tIndex == run.getCTR().getTList().size() - 1 && charIndex == text.getStringValue().length() - 1) {
                doSplitEndFieldEndRun(run, text);
            } else {
                doSplitEndField();
            }
        } else {
            throw new IllegalStateException("Use ParsingToken.START_FIELD_TOKEN or ParsingToken.END_FIELD_TOKEN");
        }
    }

    /**
     * Splits {@link ParsingToken#START_FIELD_TOKEN start field} at the beginning of a {@link XWPFRun}.
     */
    private void doSplitStartFieldStartRun() {
        // no split needed start field at the beginning of the run
        tokens.add(runIndex, tokenToInsert);
    }

    /**
     * Splits {@link ParsingToken#START_FIELD_TOKEN start field}.
     */
    private void doSplitStartField() {
        final XWPFRun run = paragraph.getRuns().get(runIndex);
        final XWPFRun runCopy = paragraph.insertNewRun(runIndex + 1);
        runCopy.getCTR().set(run.getCTR().copy());

        // remove everything after the cut in run
        final CTText[] newTArray;
        if (charIndex == 0) {
            // cut at the beginning of the CTText
            newTArray = Arrays.copyOfRange(run.getCTR().getTArray(), 0, tIndex);
        } else {
            final CTText[] tmpTArray = Arrays.copyOfRange(run.getCTR().getTArray(), 0, tIndex + 1);
            final String stringToCut = tmpTArray[tmpTArray.length - 1].getStringValue();
            tmpTArray[tmpTArray.length - 1].setStringValue(stringToCut.substring(0, charIndex));
            if (tmpTArray[tmpTArray.length - 1].getStringValue().isEmpty()) {
                newTArray = Arrays.copyOfRange(tmpTArray, 1, tmpTArray.length);
            } else {
                newTArray = tmpTArray;
            }
        }
        run.getCTR().setTArray(newTArray);

        // remove everything before the cut in runCopy
        final CTText[] newCopyTArray = Arrays.copyOfRange(runCopy.getCTR().getTArray(), tIndex,
                runCopy.getCTR().getTArray().length);
        final String stringCoptyToCut = newCopyTArray[0].getStringValue();
        newCopyTArray[0].setStringValue(stringCoptyToCut.substring(charIndex, stringCoptyToCut.length()));
        runCopy.getCTR().setTArray(newCopyTArray);

        // add the token to insert and the new run
        tokens.add(runIndex + 1, tokenToInsert);
        tokens.add(runIndex + 2, new ParsingToken(runCopy));
    }

    /**
     * Splits {@link ParsingToken#END_FIELD_TOKEN end field} at the end of a {@link XWPFRun}.
     * 
     * @param runtoSplit
     *            the {@link XWPFRun} to split
     * @param textToSplit
     *            the {@link CTText} to split
     */
    private void doSplitEndFieldEndRun(final XWPFRun runtoSplit, final CTText textToSplit) {
        // no split needed end field at the end of the run
        tokens.add(runIndex + 1, tokenToInsert);
    }

    /**
     * Splits {@link ParsingToken#END_FIELD_TOKEN end field}.
     */
    private void doSplitEndField() {
        final XWPFRun run = paragraph.getRuns().get(runIndex);
        final XWPFRun runCopy = paragraph.insertNewRun(runIndex + 1);
        runCopy.getCTR().set(run.getCTR().copy());

        // remove everything after the cut in run
        final CTText[] newTArray = Arrays.copyOfRange(run.getCTR().getTArray(), 0, tIndex + 1);
        final String stringToCut = newTArray[newTArray.length - 1].getStringValue();
        newTArray[newTArray.length - 1].setStringValue(stringToCut.substring(0, charIndex + 1));
        run.getCTR().setTArray(newTArray);

        // remove everything before the cut in runCopy
        final CTText[] newCopyTArray;
        if (charIndex == stringToCut.length() - 1) {
            // cut at the beginning of the CTText
            newCopyTArray = Arrays.copyOfRange(runCopy.getCTR().getTArray(), tIndex + 1,
                    runCopy.getCTR().getTArray().length);
        } else {
            final CTText[] tmpCopyTArray = Arrays.copyOfRange(runCopy.getCTR().getTArray(), tIndex,
                    runCopy.getCTR().getTArray().length);
            final String stringCoptyToCut = tmpCopyTArray[tmpCopyTArray.length - 1].getStringValue();
            tmpCopyTArray[tmpCopyTArray.length - 1]
                    .setStringValue(stringCoptyToCut.substring(charIndex + 1, stringCoptyToCut.length()));
            if (tmpCopyTArray[tmpCopyTArray.length - 1].getStringValue().isEmpty()) {
                // remove empty CTText
                newCopyTArray = Arrays.copyOfRange(tmpCopyTArray, 0, tmpCopyTArray.length - 1);
            } else {
                newCopyTArray = tmpCopyTArray;
            }
        }
        runCopy.getCTR().setTArray(newCopyTArray);

        // add the token to insert and the new run
        tokens.add(runIndex + 1, tokenToInsert);
        tokens.add(runIndex + 2, new ParsingToken(runCopy));
    }

}
