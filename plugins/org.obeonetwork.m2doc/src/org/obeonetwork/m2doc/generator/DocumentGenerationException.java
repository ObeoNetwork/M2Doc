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
package org.obeonetwork.m2doc.generator;

/**
 * Exception thrown when a generation problem occurs.
 * 
 * @author Romain Guider
 */
public class DocumentGenerationException extends Exception {

    /**
     * generated serial version UID.
     */
    private static final long serialVersionUID = -1381727145002218485L;

    /**
     * Creates a new {@link DocumentGenerationException} instance.
     */
    public DocumentGenerationException() {
    }

    /**
     * Creates a new {@link DocumentGenerationException} instance.
     * 
     * @param msg
     *            the message
     */
    public DocumentGenerationException(String msg) {
        super(msg);
    }

    /**
     * Creates a new {@link DocumentGenerationException} instance.
     * 
     * @param cause
     *            the cause
     */

    public DocumentGenerationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new {@link DocumentGenerationException} instance.
     * 
     * @param msg
     *            the message
     * @param cause
     *            the cause
     */
    public DocumentGenerationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
