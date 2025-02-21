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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 * A {@link Character} {@link Iterator} for {@link XWPFParagraph}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
final class CharIterator implements Iterator<Character> {

    /**
     * The {@link Character} position in the {@link XWPFParagraph}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static final class Position {

        /**
         * The {@link Character}.
         */
        private final Character character;

        /**
         * The {@link XWPFRun} index in the {@link XWPFParagraph}.
         */
        private final int runIndex;

        /**
         * The {@link CTText} index in the {@link XWPFRun}.
         */
        private final int tIndex;

        /**
         * The {@link Character} index in the {@link CTText}.
         */
        private final int charIndex;

        /**
         * Constructor.
         * 
         * @param character
         *            the {@link Character}
         * @param runIndex
         *            the {@link XWPFRun} index in the {@link XWPFParagraph}
         * @param tIndex
         *            the {@link CTText} index in the {@link XWPFRun}
         * @param charIndex
         *            the {@link Character} index in the {@link CTText}
         */
        Position(Character character, int runIndex, int tIndex, int charIndex) {
            this.character = character;
            this.runIndex = runIndex;
            this.tIndex = tIndex;
            this.charIndex = charIndex;
        }

        @Override
        public String toString() {
            // CHECKSTYLE:OFF
            return character + " " + runIndex + " " + tIndex + " " + charIndex;
            // CHECKSTYLE:ON
        }

    }

    /**
     * The current {@link XWPFParagraph}.
     */
    private final XWPFParagraph paragraph;

    /**
     * The {@link List} of {@link ParsingToken}.
     */
    private final List<ParsingToken> tokens;

    /**
     * The {@link Position} iterator.
     */
    private final Iterator<Position> positionIt;

    /**
     * The current {@link Position}.
     */
    private Position currentPosition;

    /**
     * Constructor.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to iterate
     * @param tokens
     *            the {@link List} of {@link ParsingToken} for the {@link XWPFParagraph}
     */
    CharIterator(XWPFParagraph paragraph, List<ParsingToken> tokens) {
        this.paragraph = paragraph;
        this.tokens = tokens;

        final List<Position> positions = new ArrayList<>();
        int runIndex = 0;
        for (XWPFRun run : paragraph.getRuns()) {
            int tIndex = 0;
            for (CTText text : run.getCTR().getTList()) {
                final String value = text.getStringValue();
                for (int charIndex = 0; charIndex < value.length(); charIndex++) {
                    positions.add(new Position(value.charAt(charIndex), runIndex, tIndex, charIndex));
                }
                tIndex++;
            }
            runIndex++;
        }
        positionIt = positions.iterator();
    }

    @Override
    public boolean hasNext() {
        return positionIt.hasNext();
    }

    @Override
    public Character next() {
        currentPosition = positionIt.next();
        return currentPosition.character;
    }

    /**
     * Gets the {@link RunSplit} at the current position.
     * 
     * @param tokenToInsert
     *            the {@link ParsingToken} to insert, either {@link ParsingToken#START_FIELD_TOKEN} or
     *            {@link ParsingToken#END_FIELD_TOKEN}
     * @return the {@link RunSplit} at the current position
     */
    RunSplit getRunSplit(ParsingToken tokenToInsert) {
        final RunSplit result;

        result = new RunSplit(paragraph, tokens, currentPosition.runIndex, currentPosition.tIndex,
                currentPosition.charIndex, tokenToInsert);

        return result;
    }

}
