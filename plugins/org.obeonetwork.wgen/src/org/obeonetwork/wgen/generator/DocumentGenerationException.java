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
package org.obeonetwork.wgen.generator;

public class DocumentGenerationException extends Exception {

	/**
	 * generated serial version UID.
	 */
	private static final long serialVersionUID = -1381727145002218485L;

	public DocumentGenerationException() {
	}

	public DocumentGenerationException(String arg0) {
		super(arg0);
	}

	public DocumentGenerationException(Throwable arg0) {
		super(arg0);
	}

	public DocumentGenerationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
