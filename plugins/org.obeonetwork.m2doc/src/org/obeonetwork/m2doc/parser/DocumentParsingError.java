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

import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * Parsing error stored in the template during parsing.
 * 
 * @author Romain Guider
 */
public class DocumentParsingError {
    /**
     * The error message.
     */
    private String message;
    /**
     * The run where the error has been located.
     */
    private XWPFRun location;

    /**
     * Creates a new {@link DocumentParsingError} instance provided a message
     * and a location.
     * 
     * @param theMessage
     *            the message explaining the error
     * @param theLocation
     *            the location where the error has been detected. When the error
     *            occurs at the end of the document (unexpected EOF
     *            encountered), the <code>null</code> value is used.
     */
    public DocumentParsingError(String theMessage, XWPFRun theLocation) {
        this.location = theLocation;
        this.message = theMessage;
    }

    /**
     * Returns the location of the error.
     * 
     * @return the location of the error.
     */
    public XWPFRun getLocation() {
        return location;
    }

    /**
     * The error message.
     * 
     * @return the error message.
     */
    public String getMessage() {
        return message;
    }

}
