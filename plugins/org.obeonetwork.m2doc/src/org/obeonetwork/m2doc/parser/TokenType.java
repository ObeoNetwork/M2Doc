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
 * Token types are used to characterize tokens during parsing and guide the parsing algorithm.
 * 
 * @author Romain Guider
 */
public enum TokenType {

    /**
     * Token type constants.
     */
    AQL("m:"), FOR("m:for"), ENDFOR("m:endfor"), IF("m:if"), ELSEIF("m:elseif"), ELSE("m:else"), ENDIF("m:endif"), LET("m:let"), ENDLET("m:endlet"), ELT("elt:"), STATIC("static"), IMAGE("m:image"), EOF("end of file."), WTABLE("table"), DIAGRAM("m:diagram"), BOOKMARK("m:bookmark"), ENDBOOKMARK("m:endbookmark"), LINK("m:link"), TABLE("m:wtable"), USERDOC("m:userdoc"), ENDUSERDOC("m:enduserdoc"), USERCONTENT("m:usercontent"), ENDUSERCONTENT("m:endusercontent");

    /**
     * Token type value.
     */
    private String value;

    /**
     * Constructor.
     * 
     * @param theValue
     *            String
     */
    TokenType(String theValue) {
        this.value = theValue;
    }

    /**
     * return the token value.
     * 
     * @return the token value
     */
    public String getValue() {
        return value;
    }

}
