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
 * Enum used for handling parsing error messages.
 * 
 * @author Romain Guider
 */
public enum ParsingErrorMessage {
    /**
     * Error message issued when a cnoditionnal end tag is expected.
     */
    CONDTAGEXPEXTED("gd:elseif, gd:else or gd:endif expected here."), /**
                                                                       * Error message issued when unexpected tag is encountered.
                                                                       */
    UNEXPECTEDTAG("Unexpected tag {0} at this location"), MALFORMEDTAG("Tag {0} is malformed"),
    /**
     * Error message issued when a invalid AQL expression is encountered.
     */
    INVALIDEXPR("Expression {0} is invalid"), /**
                                               * Error message issued when a var tag has no variable value.
                                               */
    NOVARDEFINED("no variable defined."), /**
                                           * Error message issued when an image tag has an invalid form.
                                           */
    INVALID_IMAGE_TAG("Invalid image directive : no file name provided."), /**
                                                                            * Error message issued when an image tag has an invalid option.
                                                                            */
    INVALID_IMAGE_OPTION("Invalid image option ({0}): {1}."),
    /**
     * Error message issued when a diagram tag has an invalid form.
     */
    INVALID_DIAGRAM_TAG("Invalid diagram directive : no title provided."),
    /**
     * Error message issued when a diagram tag has invalid option.
     */
    INVALID_DIAGRAM_OPTION("Invalid diagram option ({0}): {1}.");
    /**
     * The error message.
     */
    private String errorMsg;

    /**
     * creates a new instance.
     * 
     * @param msg
     *            the message
     */
    ParsingErrorMessage(String msg) {
        this.errorMsg = msg;
    }

    public String getMessage() {
        return errorMsg;
    }
}
