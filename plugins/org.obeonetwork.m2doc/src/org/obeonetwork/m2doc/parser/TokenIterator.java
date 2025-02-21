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
package org.obeonetwork.m2doc.parser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 * {@link TokenIterator} realizes an iterator of {@link ParsingToken} over a document's body (document, table's cell, text zone, etc.).
 * 
 * @author Romain Guider
 */
public class TokenIterator implements Iterator<ParsingToken> {
    /**
     * The underlying element iterator.
     */
    private Iterator<IBodyElement> elementIterator;
    /**
     * Run or table {@link ParsingToken} iterator depending on the nature of the body.
     */
    private Iterator<ParsingToken> tokenIterator;

    /**
     * Creates a new {@link TokenIterator} instance.
     * 
     * @param inputBody
     *            the {@link IBody}
     */
    public TokenIterator(IBody inputBody) {
        if (inputBody == null) {
            throw new IllegalArgumentException("Input documnet shouldn't be null");
        }
        elementIterator = inputBody.getBodyElements().iterator();
    }

    /**
     * Put the iterator in a state where it is finished or there's a token to
     * consume in the currentIterator.
     */
    private void moveToNextToken() {
        if (tokenIterator == null || !tokenIterator.hasNext()) {
            while (elementIterator.hasNext() && (tokenIterator == null || !tokenIterator.hasNext())) {
                final IBodyElement element = elementIterator.next();
                switch (element.getElementType()) {
                    case PARAGRAPH:
                        tokenIterator = getParagraphIterator((XWPFParagraph) element);
                        break;

                    case TABLE:
                        tokenIterator = getTableIterator((XWPFTable) element);
                        break;

                    case CONTENTCONTROL:
                        tokenIterator = getContentControlIterator((XWPFSDT) element);
                        break;

                    default:
                        throw new UnsupportedOperationException(
                                "Unsupported type of body element : " + element.getElementType());
                }
            }
        }
    }

    /**
     * Gets the {@link ParsingToken} {@link Iterator} for the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @return the {@link ParsingToken} {@link Iterator} for the given {@link XWPFParagraph}
     */
    protected Iterator<ParsingToken> getParagraphIterator(XWPFParagraph paragraph) {
        // create an empty run if there's no run in the paragraph.
        // this eases the processing of documents. The processing is based on runs and a paragraph that has no run in it
        // won't
        // be seen by the generator and, as a consequence, won't be inserted as a static part in the result.
        if (paragraph.getRuns().size() == 0) {
            paragraph.createRun().setText("");
        }
        return new RunIterator(paragraph.getRuns());
    }

    /**
     * Gets the {@link ParsingToken} {@link Iterator} for the given {@link XWPFTable}.
     * 
     * @param table
     *            the {@link XWPFTable}
     * @return the {@link ParsingToken} {@link Iterator} for the given {@link XWPFTable}
     */
    protected Iterator<ParsingToken> getTableIterator(XWPFTable table) {
        return new BodyElementIterator(table);
    }

    /**
     * Gets the {@link ParsingToken} {@link Iterator} for the given {@link XWPFSDT}.
     * 
     * @param contentControl
     *            the {@link XWPFSDT}
     * @return the {@link ParsingToken} {@link Iterator} for the given {@link XWPFSDT}
     */
    protected Iterator<ParsingToken> getContentControlIterator(XWPFSDT contentControl) {
        return new BodyElementIterator(contentControl);
    }

    @Override
    public ParsingToken next() {
        moveToNextToken();
        if (tokenIterator != null && tokenIterator.hasNext()) {
            return tokenIterator.next();
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean hasNext() {
        moveToNextToken();
        return tokenIterator != null && tokenIterator.hasNext();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
