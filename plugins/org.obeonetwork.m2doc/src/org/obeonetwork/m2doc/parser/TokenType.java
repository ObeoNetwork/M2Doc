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
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Token types are used to characterize tokens during parsing and guide the parsing algorithm.
 * 
 * @author Romain Guider
 */
public enum TokenType {

    /**
     * A {@link Template} tag.
     */
    TEMPLATE("m:template"),

    /**
     * A {@link Template} end tag.
     */
    ENDTEMPLATE("m:endtemplate"),

    /**
     * A {@link Comment} tag.
     */
    COMMENT("m:comment"),

    /**
     * A {@link Query} tag.
     */
    QUERY(M2DocUtils.M),

    /**
     * A {@link Repetition} tag.
     */
    FOR("m:for"),

    /**
     * A {@link Repetition} end tag.
     */
    ENDFOR("m:endfor"),

    /**
     * A {@link Conditional} tag.
     */
    IF("m:if"),

    /**
     * A {@link Conditional} else if tag.
     */
    ELSEIF("m:elseif"),

    /**
     * A {@link Conditional} else tag.
     */
    ELSE("m:else"),

    /**
     * A {@link Conditional} end tag.
     */
    ENDIF("m:endif"),

    /**
     * A {@link Let} tag.
     */
    LET("m:let"),

    /**
     * A {@link Let} end tag.
     */
    ENDLET("m:endlet"),

    /**
     * A {@link Bookmark} tag.
     */
    BOOKMARK("m:bookmark"),

    /**
     * A {@link Bookmark} end tag.
     */
    ENDBOOKMARK("m:endbookmark"),

    /**
     * A {@link Link} tag.
     */
    LINK("m:link"),

    /**
     * A {@link UserDoc} tag.
     */
    USERDOC("m:userdoc"),

    /**
     * A {@link UserDoc} end tag.
     */
    ENDUSERDOC("m:enduserdoc"),

    /**
     * A {@link UserContent} tag.
     */
    USERCONTENT("m:usercontent"),

    /**
     * A {@link UserContent} end tag.
     */
    ENDUSERCONTENT("m:endusercontent"),

    /**
     * A static {@link XWPFRun}.
     */
    STATIC("static"),

    /**
     * The end of the file.
     */
    EOF("end of file."),

    /**
     * A {@link XWPFTable}.
     */
    WTABLE("table"),

    /**
     * A {@link XWPFSDT}.
     */
    CONTENTCONTROL("contentcontrol"),

    ;

    /**
     * Token type value.
     */
    private String value;

    /**
     * Tells if this Token type needs extra spaces check.
     */
    private boolean needExtraSpacesCheck;

    /**
     * Constructor.
     * 
     * @param theValue
     *            String
     */
    TokenType(String theValue) {
        this.value = theValue;
        this.needExtraSpacesCheck = value.startsWith(M2DocUtils.M) && !M2DocUtils.M.equals(value);
    }

    /**
     * return the token value.
     * 
     * @return the token value
     */
    public String getValue() {
        return value;
    }

    /**
     * Tells if this token type need extra spaces check.
     * 
     * @return <code>true</code> if this token type need extra spaces check, <code>false</code> otherwise
     */
    public boolean needExtraSpacesCheck() {
        return needExtraSpacesCheck;
    }

}
