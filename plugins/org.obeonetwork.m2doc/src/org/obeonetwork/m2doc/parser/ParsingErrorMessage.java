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

/**
 * Enum used for handling parsing error messages.
 * 
 * @author Romain Guider
 */
public enum ParsingErrorMessage {
    /**
     * Error message issued when a conditional end tag is expected.
     */
    CONDTAGEXPEXTED("m:elseif, m:else or m:endif expected here while parsing {0}"),
    /**
     * Error message issued when unexpected tag is encountered.
     */
    UNEXPECTEDTAG("Unexpected tag {0} at this location"),
    /**
     * Error message issued when unexpected tag is encountered with header.
     */
    UNEXPECTEDTAGWITHHEADER("Unexpected tag {0} at this location while parsing: {1}"),
    /**
     * Error message issued when a tag is malformed.
     */
    MALFORMEDTAG("Tag {0} is malformed"),
    /**
     * Error message issued when unexpected tag is encountered.
     */
    UNEXPECTEDTAGMISSING("Unexpected tag {0} missing {1}"),
    /**
     * Error message issued when unexpected tag is encountered with header.
     */
    UNEXPECTEDTAGMISSINGWITHHEADER("Unexpected tag {0} missing {1} while parsing {2}"),
    /**
     * Error message issued when a invalid AQL expression is encountered.
     */
    INVALIDEXPR("Expression \"{0}\" is invalid: {1}"),
    /**
     * Error message issued when a var tag has no variable value.
     */
    NOVARDEFINED("no variable defined."),
    /**
     * Error message issued when an image tag has an invalid form.
     */
    INVALID_IMAGE_TAG("Invalid image directive : no file name provided."),
    /**
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
    INVALID_DIAGRAM_OPTION("Invalid diagram option ({0}): {1}."),
    /**
     * Error message issued when a userdoc tag contents some thing else than element of STATIC type.
     */
    INVALID_USERDOC_CONTENT("Invalid userdoc content, elements in userdoc must only be STATIC type."),
    /**
     * Error message issued when a userdoc tag contents some thing else than element of STATIC type.
     */
    INVALID_USERDOC_NOT_STATIC("Invalid userdoc content, the type {0} can not be contrain by userdoc tag."),
    /**
     * Error message issued when a userdoc tag have not id parameter.
     */
    INVALID_USERDOC_ID_MUST_EXIST("userdoc tag must have an id parameter."),
    /**
     * Error message issued when a userdoc tag have an unique id value.
     */
    INVALID_USERDOC_ID_NOT_UNIQUE("userdoc tag must have unique id value. ''{0}'' id already exists in document"),
    /**
     * Error message issued when a usercontent tag must have a not empty value.
     */
    INVALID_USERCONTENT_VALUE("usercontent tag must have an no empty value."),
    /**
     * Error message issued when a block is closed while parsing an other block.
     */
    DIDYOUFORGETENDBLOCK("Did you forget the {0}?"),
    /**
     * Error message issued when a template is found in a block where it's not allowed.
     */
    TEMPLATE_NOT_ALLOWED_IN_THIS_BLOCK("Template construct not allowed in this block.");
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
