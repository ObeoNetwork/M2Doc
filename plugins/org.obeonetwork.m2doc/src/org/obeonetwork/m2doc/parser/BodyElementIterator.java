/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
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

import org.apache.poi.xwpf.usermodel.IBodyElement;

/**
 * {@link IBodyElement} iterator instances are only iterating over a single instance but they
 * are useful to simplify the code of the {@link TokenIterator} class.
 * 
 * @author Romain Guider
 */
public class BodyElementIterator implements Iterator<ParsingToken> {
    /**
     * The underlying iterator.
     */
    private Iterator<IBodyElement> iterator;

    /**
     * Create a new iterator for a single {@link IBodyElement}.
     * 
     * @param bodyElement
     *            the {@link IBodyElement}
     */
    public BodyElementIterator(IBodyElement bodyElement) {
        final List<IBodyElement> list = new ArrayList<IBodyElement>();

        list.add(bodyElement);

        this.iterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public ParsingToken next() {
        return new ParsingToken(iterator.next());
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
