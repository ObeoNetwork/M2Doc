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

public class DocumentParserException extends Exception {

	/**
	 * generated serial version UID.
	 */
	private static final long serialVersionUID = -5608638529753048869L;

	public DocumentParserException(String message) {
		super(message);
	}

	public DocumentParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
