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
package org.obeonetwork.m2doc.parser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

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
     *            the body.
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
                        // create an empty run if there's no run in the paragraph.
                        // this eases the processing of documents. The processing is based on runs and a paragraph that has no run in it
                        // won't
                        // be seen by the generator and, as a consequence, won't be inserted as a static part in the result.
                        XWPFParagraph paragraph = (XWPFParagraph) element;
                        if (paragraph.getRuns().size() == 0) {
                            paragraph.createRun().setText("");
                        }
                        tokenIterator = new RunIterator(((XWPFParagraph) element).getRuns());
                        break;

                    case TABLE:
                        tokenIterator = new BodyElementIterator(element);
                        break;

                    case CONTENTCONTROL:
                        tokenIterator = new BodyElementIterator(element);
                        break;

                    default:
                        throw new UnsupportedOperationException(
                                "Unsupported type of body element : " + element.getElementType());
                }
            }
        }
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
