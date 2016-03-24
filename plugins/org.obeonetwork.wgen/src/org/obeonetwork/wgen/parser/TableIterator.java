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
package org.obeonetwork.wgen.parser;

import java.util.Iterator;

import org.apache.poi.xwpf.usermodel.XWPFTable;

import com.google.common.collect.Lists;

/**
 * Table iterator instances are only iterating over a single instance but they
 * are useful to simplify the code of the {@link TokenIterator} class.
 * 
 * @author Romain Guider
 *
 */
public class TableIterator implements Iterator<ParsingToken> {
	/**
	 * The underlying iterator.
	 */
	private Iterator<XWPFTable> iterator;

	/**
	 * Create a new iterator for a single table.
	 * 
	 * @param table
	 */
	public TableIterator(XWPFTable table) {
		this.iterator = Lists.newArrayList(table).iterator();
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
