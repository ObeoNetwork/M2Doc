/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.view.aql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;

/**
 * The AQL {@link ITokenScanner}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AQLScanner implements ITokenScanner {

    /**
     * Keyword regular expression.
     */
    private static final String KEYWORD_REGEX = "if|then|else|endif|let|in|true|false|null|not";

    /**
     * String literal regular expression.
     */
    private static final String STRING_LITERAL_REGEX = "'([^'\\\\]|\\\\.)*'";

    /**
     * Keyword regular expression.
     */
    private static final String ECLASSIFIER_TYPE_REGEX = "[a-zA-Z_][a-zA-Z_0-9]*::[a-zA-Z_][a-zA-Z_0-9]*";

    /**
     * Keyword regular expression.
     */
    private static final String ECLASSIFIER_SET_TYPE_REGEX = "\\{\\s*" + ECLASSIFIER_TYPE_REGEX + "(\\s*\\|\\s*"
        + ECLASSIFIER_TYPE_REGEX + ")*\\s*\\}";

    /**
     * Scanner {@link Pattern}.
     */
    private static final Pattern SCANNER = Pattern
            .compile("(\\s+)|((?<![a-zA-Z])(" + KEYWORD_REGEX + ")(?![a-zA-Z]))|(" + STRING_LITERAL_REGEX
                + ")|((?<![a-zA-Z])((String|Integer|Real|Boolean)(?![a-zA-Z]))|Sequence(?=\\()|OrderedSet(?=\\()|"
                + ECLASSIFIER_SET_TYPE_REGEX + "|" + ECLASSIFIER_TYPE_REGEX + ")");

    /**
     * White spaces group index.
     */
    private static final int WS_GROUP = 1;

    /**
     * Keyword group index.
     */
    private static final int KEYWORD_GROUP = 2;

    /**
     * Keyword group index.
     */
    private static final int STRING_GROUP = 4;

    /**
     * Keyword group index.
     */
    private static final int TYPE_LITERAL_GROUP = 6;

    /**
     * Keyword.
     */
    private final IToken keywordToken;

    /**
     * {@link org.eclipse.acceleo.query.ast.StringLiteral StringLiteral}.
     */
    private final IToken stringToken;

    /**
     * {@link org.eclipse.acceleo.query.ast.TypeLiteral TypeLiteral}.
     */
    private final IToken typeLiteralToken;

    /**
     * The current {@link Matcher}.
     */
    private Matcher matcher;

    /**
     * The offset in the document.
     */
    private int documentOffset;

    /**
     * The expression length.
     */
    private int expressionLength;

    /**
     * The current token start position.
     */
    private int currentOffset;

    /**
     * The current token length.
     */
    private int currentLength;

    /**
     * Tells if the matcher is ready or if we should call find().
     */
    private boolean matcherReady;

    /**
     * Constructor.
     * 
     * @param colorManager
     *            the {@link ColorManager}
     */
    public AQLScanner(ColorManager colorManager) {
        keywordToken = new Token(new TextAttribute(colorManager.getColor(IAQLColorConstants.KEYWORD)));
        stringToken = new Token(new TextAttribute(colorManager.getColor(IAQLColorConstants.STRING)));
        typeLiteralToken = new Token(new TextAttribute(colorManager.getColor(IAQLColorConstants.TYPE_LITERAL)));
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.rules.ITokenScanner#setRange(org.eclipse.jface.text.IDocument, int, int)
     */
    @Override
    public void setRange(IDocument document, int offset, int length) {
        try {
            matcher = SCANNER.matcher(document.get(offset, length));
            expressionLength = length;
            documentOffset = offset;
            currentOffset = 0;
            currentLength = 0;
        } catch (BadLocationException e) {
            // nothing to do here
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.rules.ITokenScanner#nextToken()
     */
    @Override
    public IToken nextToken() {
        final IToken res;

        if (matcherReady || matcher.find()) {
            if (matcher.start() == currentOffset + currentLength) {
                matcherReady = false;
                currentOffset = matcher.start();
                currentLength = matcher.end() - matcher.start();
                if (matcher.group(WS_GROUP) != null) {
                    res = Token.WHITESPACE;
                } else if (matcher.group(KEYWORD_GROUP) != null) {
                    res = keywordToken;
                } else if (matcher.group(STRING_GROUP) != null) {
                    res = stringToken;
                } else if (matcher.group(TYPE_LITERAL_GROUP) != null) {
                    res = typeLiteralToken;
                } else {
                    throw new IllegalStateException("should not happen.");
                }

            } else {
                matcherReady = true;
                res = Token.UNDEFINED;
                currentOffset = currentOffset + currentLength;
                currentLength = matcher.start() - currentOffset;
            }
        } else if (currentOffset + currentLength < expressionLength) {
            matcherReady = false;
            res = Token.UNDEFINED;
            currentOffset = currentOffset + currentLength;
            currentLength = expressionLength - currentOffset;
        } else {
            matcherReady = false;
            res = Token.EOF;
            currentOffset = expressionLength;
            currentLength = 0;
        }

        return res;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.rules.ITokenScanner#getTokenOffset()
     */
    @Override
    public int getTokenOffset() {
        return documentOffset + currentOffset;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.rules.ITokenScanner#getTokenLength()
     */
    @Override
    public int getTokenLength() {
        return currentLength;
    }

}
