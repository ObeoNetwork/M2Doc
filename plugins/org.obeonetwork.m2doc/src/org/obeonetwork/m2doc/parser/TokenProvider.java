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
import java.util.LinkedList;

import org.apache.poi.xwpf.usermodel.IBody;

/**
 * Token are Iterator of {@link ParsingToken} instances that provide lookahead facilities.
 * Lookahead methods allow to look ahead the stream the tokens that will be read so as to predict the right parsing decision at any point.
 * 
 * @author Romain Guider
 */
public class TokenProvider implements Iterator<ParsingToken> {
    /**
     * The underlying {@link ParsingToken} instance.
     */
    private TokenIterator runIterator;
    /**
     * The token read ahead of the cursor.
     */
    private LinkedList<ParsingToken> lookAhead;

    /**
     * Creates a new {@link TokenProvider} instance.
     * 
     * @param body
     *            the parsed body
     */
    public TokenProvider(IBody body) {
        this.runIterator = new TokenIterator(body);
        lookAhead = new LinkedList<ParsingToken>();
    }

    /**
     * Returns the ith element or <code>null</code> if there's no such element.
     * 
     * @param i
     *            the number of token to look ahead.
     * @return the ith element or <code>null</code> if there's no such element.
     */
    public ParsingToken lookAhead(int i) {
        loadNext(i);
        if (lookAhead.size() == 0 && i > 0) {
            return null;
        } else {
            return lookAhead.get(i - 1);
        }
    }

    /**
     * returns <code>true</code> if there's <code>i</code> elements left to
     * read.
     * 
     * @param i
     *            the number of element inquired
     * @return <code>true</code> if there's <code>i</code> elements left to read
     */
    public boolean hasElements(int i) {
        loadNext(i);
        return lookAhead.size() >= i;
    }

    /**
     * load the i next tokens.
     * 
     * @param i
     *            the number of tokens to load ahead of the cursor.
     */
    private void loadNext(int i) {
        int laSize = lookAhead.size();
        int leftToRead = i - laSize;
        int j = 0;
        while (runIterator.hasNext() && j < leftToRead) {
            lookAhead.addLast(runIterator.next());
            j++;
        }
    }

    @Override
    public boolean hasNext() {
        return !lookAhead.isEmpty() || runIterator.hasNext();
    }

    @Override
    public ParsingToken next() {
        if (!lookAhead.isEmpty()) {
            return lookAhead.removeFirst();
        } else {
            return runIterator.next();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
