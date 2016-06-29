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

/**
 * Token types are used to caracterise tokens during parsing and guide the parsing algorithm.
 * 
 * @author Romain Guider
 */
public enum TokenType {
    AQL("m:"), FOR("m:for"), ENDFOR("m:endfor"), IF("m:if"), ELSEIF("m:elseif"), ELSE("m:else"), ENDIF("m:endif"),
    LET("m:let"), ENDLET("m:endlet"), ELT("elt:"), STATIC("static"), IMAGE("m:image"), EOF("end of file."),
    WTABLE("table"), DIAGRAM("m:diagram");

    private String value;

    TokenType(String theValue) {
        this.value = theValue;
    }

    public String getValue() {
        return value;
    }

}
