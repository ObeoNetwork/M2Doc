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

import com.google.common.collect.Lists;

import java.util.Iterator;

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
        this.iterator = Lists.newArrayList(bodyElement).iterator();
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
