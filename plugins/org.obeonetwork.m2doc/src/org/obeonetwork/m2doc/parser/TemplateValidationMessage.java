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

import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * Template validation message.
 * 
 * @author Romain Guider
 */
public class TemplateValidationMessage {

    /**
     * The {@link ValidationMessageLevel}.
     */
    private final ValidationMessageLevel level;

    /**
     * The error message.
     */
    private final String message;
    /**
     * The run where the error has been located.
     */
    private final XWPFRun location;

    /**
     * Creates a new {@link TemplateValidationMessage} instance provided a message
     * and a location.
     * 
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message explaining the error
     * @param location
     *            the location where the error has been detected. When the error
     *            occurs at the end of the document (unexpected EOF
     *            encountered), the <code>null</code> value is used
     */
    public TemplateValidationMessage(ValidationMessageLevel level, String message, XWPFRun location) {
        this.level = level;
        this.location = location;
        this.message = message;
    }

    /**
     * Gets the {@link ValidationMessageLevel}.
     * 
     * @return the {@link ValidationMessageLevel}
     */
    public ValidationMessageLevel getLevel() {
        return level;
    }

    /**
     * Gets the location of the error.
     * 
     * @return the location of the error
     */
    public XWPFRun getLocation() {
        return location;
    }

    /**
     * Gets the error message.
     * 
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

}
