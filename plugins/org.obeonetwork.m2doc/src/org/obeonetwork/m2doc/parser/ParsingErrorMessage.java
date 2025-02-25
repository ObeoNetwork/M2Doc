/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
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
    @Deprecated
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
    @Deprecated
    NOVARDEFINED("no variable defined."),
    /**
     * Error message issued when an image tag has an invalid form.
     */
    @Deprecated
    INVALID_IMAGE_TAG("Invalid image directive : no file name provided."),
    /**
     * Error message issued when an image tag has an invalid option.
     */
    @Deprecated
    INVALID_IMAGE_OPTION("Invalid image option ({0}): {1}."),
    /**
     * Error message issued when a diagram tag has an invalid form.
     */
    @Deprecated
    INVALID_DIAGRAM_TAG("Invalid diagram directive : no title provided."),
    /**
     * Error message issued when a diagram tag has invalid option.
     */
    @Deprecated
    INVALID_DIAGRAM_OPTION("Invalid diagram option ({0}): {1}."),
    /**
     * Error message issued when a userdoc tag contents some thing else than element of STATIC type.
     */
    @Deprecated
    INVALID_USERDOC_CONTENT("Invalid userdoc content, elements in userdoc must only be STATIC type."),
    /**
     * Error message issued when a userdoc tag contents some thing else than element of STATIC type.
     */
    @Deprecated
    INVALID_USERDOC_NOT_STATIC("Invalid userdoc content, the type {0} can not be contrain by userdoc tag."),
    /**
     * Error message issued when a userdoc tag have not id parameter.
     */
    @Deprecated
    INVALID_USERDOC_ID_MUST_EXIST("userdoc tag must have an id parameter."),
    /**
     * Error message issued when a userdoc tag have an unique id value.
     */
    INVALID_USERDOC_ID_NOT_UNIQUE("userdoc tag must have unique id value. ''{0}'' id already exists in document"),
    /**
     * Error message issued when a usercontent tag must have a not empty value.
     */
    INVALID_USERCONTENT_VALUE("usercontent tag must have an no empty id value."),
    /**
     * Error message issued when a block is closed while parsing an other block.
     */
    DIDYOUFORGETENDBLOCK("Did you forget the {0}?"),
    /**
     * Error message issued when a template is found in a block where it's not allowed.
     */
    TEMPLATE_NOT_ALLOWED_IN_THIS_BLOCK("Template construct not allowed in this block."),
    /**
     * Error message issued when a let is missing a variable.
     */
    MISSINGIDENTIFIER("Missing identifier"),
    /**
     * Error message issued when a let is missing a equals signe.
     */
    MISSINGEQUALS("Missing ="),
    /**
     * Error message issued when a tag has an extra space.
     */
    YOUMIGHTWANTTOREPLACE("You might want to replace {0} by {1}"),
    /**
     * Error message issued when an AQL expression can't be parsed.
     */
    UNABLETOPARSEAQLEXPRESSION("Unable to parse AQL Expression check the syntax."),
    /**
     * Error message issued when an endif is missing after an else.
     */
    MISSINGENDIFAFTREELSE("{0} ... " + TokenType.ELSE.getValue()),
    /**
     * Error message issued when a malformed for has a missing pipe.
     */
    MALFORMEDFORMISSINGPIPE("Malformed tag m:for, no ''|'' found."),
    /**
     * Error message issued when a malformed for has a missing variable.
     */
    MALFORMEDFORMISSINGVARIABLE("Malformed tag m:for : no iteration variable specified."),
    /**
     * Error message issued when a malformed for has a missing expression.
     */
    MALFORMEDFORMISSINGEXPRESSION("Malformed tag m:for : no query expression specified.{0}"),
    /**
     * Error message issued when a malformed template has a missing opening parenthesis.
     */
    MALFORMEDTEMPLATEMISSINGOPENINGPARENTH("Malformed tag m:template, no ''('' found."),
    /**
     * Error message issued when a malformed template no name specified.
     */
    MALFORMEDTEMPLATENONAMESPECIFIED("Malformed tag m:template : no name specified."),
    /**
     * Error message issued when a malformed template no parameter specified.
     */
    MALFORMEDTEMPLATENOPARAMETERSPECIFIED("Malformed tag m:template : no parameter specified.{0}"),
    /**
     * Error message issued when a malformed template has a missing closing parenthesis.
     */
    MALFORMEDTEMPLATEMISSINGCLOSINGPARENTH("Malformed tag m:template, no '')'' found."),
    /**
     * Error message issued when a malformed template has no parameter.
     */
    ATLEASTONEPARAMETERISNEEDED("At least one parameter is needed."),
    /**
     * Error message issued when a malformed parameter has non colon.
     */
    MALFORMEDPARAMETERNOCOLON("Malformed prameter, no '':'' found."),
    /**
     * Error message issued when a malformed parameter has non name specified.
     */
    MALFORMEDPARAMETERNONAMESPECIFIED("Malformed parameter no name specified."),
    /**
     * Error message issued when a null or empty string expression is parsed.
     */
    NULLOREMPTYSTRING("null or empty string."),
    /**
     * Error message issued when a type literal is missing.
     */
    MISSINGTYPELITERAL("missing type literal"),
    /**
     * Error message issued when a let is missing a variable.
     */
    MISSINGENDTAG("Missing end tag '}'");

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
