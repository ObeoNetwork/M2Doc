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

public enum ParsingErrorMessage {

	CONDTAGEXPEXTED("gd:elseif, gd:else or gd:endif expected here."), UNEXPECTEDTAG(
			"Unexpected tag {0} at this location"), MALFORMEDTAG("Tag {0} is malformed"), INVALIDEXPR(
					"Expression {0} is invalid"), NOVARDEFINED("no variable defined."), INVALID_IMAGE_TAG(
							"Invalid image directive : no file name provided."), INVALID_IMAGE_OPTION(
									"Invalid image option ({0}): {1}.");

	private String errorMsg;

	private ParsingErrorMessage(String msg) {
		this.errorMsg = msg;
	}

	public String getMessage() {
		return errorMsg;
	}
}
