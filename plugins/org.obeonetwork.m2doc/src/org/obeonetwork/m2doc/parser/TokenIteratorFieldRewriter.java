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

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Rewrites M2Doc field (see {@link M2DocUtils#M_FIELD_START} and {@link M2DocUtils#FIELD_END}) inside of {@link XWPFParagraph}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TokenIteratorFieldRewriter extends TokenIterator {

    /**
     * Constructor.
     * 
     * @param inputBody
     *            the input {@link IBody}
     */
    public TokenIteratorFieldRewriter(IBody inputBody) {
        super(inputBody);
    }

    @Override
    protected Iterator<ParsingToken> getParagraphIterator(XWPFParagraph paragraph) {
        final List<ParsingToken> result = getParagraphTokens(paragraph);
        final List<RunSplit> splits = new ArrayList<>();

        boolean inField = false;

        final CharIterator iterator = new CharIterator(paragraph, result);
        int startFieldMachingIndex = 0;
        RunSplit currentSplit = null;
        while (iterator.hasNext()) {
            if (inField) {
                // look for the field end
                final Character lastChar = consuteUntilAqlExpressionEndLimit(iterator);
                if (lastChar != null && lastChar == M2DocUtils.FIELD_END.charAt(0)) {
                    inField = false;
                    splits.add(iterator.getRunSplit(ParsingToken.END_FIELD_TOKEN));
                }
            } else {
                // look for the field start
                final char currentChar = iterator.next();
                if (currentChar == M2DocUtils.M_FIELD_START.charAt(startFieldMachingIndex)) {
                    startFieldMachingIndex++;
                    if (startFieldMachingIndex == 1) {
                        currentSplit = iterator.getRunSplit(ParsingToken.START_FIELD_TOKEN);
                    } else if (startFieldMachingIndex == M2DocUtils.M_FIELD_START.length()) {
                        splits.add(currentSplit);
                        inField = true;
                        startFieldMachingIndex = 0;
                    }
                } else {
                    startFieldMachingIndex = 0;
                    currentSplit = null;
                }
            }
        }

        // splits from the end to preserve indexes
        for (int i = splits.size() - 1; i >= 0; i--) {
            splits.get(i).split();
        }

        if (inField) {
            // close the field at the end of the paragraph
            result.add(ParsingToken.MISSING_END_FIELD_TOKEN);
        }

        return result.iterator();
    }

    /**
     * Consumes the given {@link CharIterator} until the end of the AQL Expression.
     * 
     * @param iterator
     *            the {@link CharIterator}
     * @return the last consumed {@link Character} if any, <code>null</code> otherwise
     */
    protected Character consuteUntilAqlExpressionEndLimit(CharIterator iterator) {
        int parenthesisDepth = 0;
        int curlyBracketDepth = 0;
        Character currentCharacter = null;
        while (iterator.hasNext()) {
            currentCharacter = iterator.next();
            if (currentCharacter == M2DocUtils.FIELD_END.charAt(0) && parenthesisDepth == 0 && curlyBracketDepth == 0) {
                break;
            }
            switch (currentCharacter) {
                case '\'':
                    // skip string literal
                    boolean isEscaped = false;
                    endStrinLiteral: while (iterator.hasNext()) {
                        currentCharacter = iterator.next();
                        switch (currentCharacter) {
                            case '\\':
                                isEscaped = !isEscaped;
                                break;
                            case '\'':
                                if (!isEscaped) {
                                    break endStrinLiteral;
                                }
                                isEscaped = false;
                                break;
                            default:
                                isEscaped = false;
                                break;
                        }
                    }
                    break;
                case '{':
                    curlyBracketDepth++;
                    break;
                case '}':
                    curlyBracketDepth--;
                    break;
                case '(':
                    parenthesisDepth++;
                    break;
                case ')':
                    parenthesisDepth--;
                    break;
                default:
                    break;
            }
        }

        return currentCharacter;
    }

    /**
     * Gets the {@link List} of {@link ParsingToken} from the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @return the {@link List} of {@link ParsingToken} from the given {@link XWPFParagraph}
     */
    private List<ParsingToken> getParagraphTokens(XWPFParagraph paragraph) {
        final List<ParsingToken> result = new ArrayList<>();

        // create an empty run if there's no run in the paragraph.
        // this eases the processing of documents. The processing is based on runs and a paragraph that has no run in it
        // won't
        // be seen by the generator and, as a consequence, won't be inserted as a static part in the result.
        if (paragraph.getRuns().size() == 0) {
            paragraph.createRun().setText("");
        }

        for (XWPFRun run : paragraph.getRuns()) {
            result.add(new ParsingToken(run));
        }

        return result;
    }

}
