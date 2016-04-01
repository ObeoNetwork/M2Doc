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

import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * {@link RunIterator} are used to create {@link ParsingToken} isntances from {@link XWPFRun} instances.
 * 
 * @author Romain Guider
 */
public class RunIterator implements Iterator<ParsingToken> {

    /**
     * The internal iterator.
     */
    private Iterator<XWPFRun> internalIterator;

    /**
     * Create a new {@link RunIterator}.
     * 
     * @param runs
     *            the list of runs to iterate on.
     */
    public RunIterator(Collection<XWPFRun> runs) {
        this.internalIterator = runs.iterator();
    }

    @Override
    public boolean hasNext() {
        return internalIterator.hasNext();
    }

    @Override
    public ParsingToken next() {
        return new ParsingToken(internalIterator.next());
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
