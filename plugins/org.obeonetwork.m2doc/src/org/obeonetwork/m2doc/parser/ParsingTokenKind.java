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

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 * Enum used to determine the kind of parsing token.
 * 
 * @author Romain Guider
 */
public enum ParsingTokenKind {
    /**
     * {@link XWPFRun} kind.
     */
    RUN,

    /**
     * {@link XWPFTable} kind.
     */
    TABLE,

    /**
     * {@link XWPFSDT} kind.
     */
    CONTENTCONTROL,;

    /**
     * Gets the
     * 
     * @param bodyElementType
     * @return
     */
    public static ParsingTokenKind getParsingTokenKind(BodyElementType bodyElementType) {
        final ParsingTokenKind res;

        switch (bodyElementType) {
            case PARAGRAPH:
                res = ParsingTokenKind.RUN;
                break;

            case TABLE:
                res = ParsingTokenKind.TABLE;
                break;

            case CONTENTCONTROL:
                res = ParsingTokenKind.CONTENTCONTROL;
                break;

            default:
                throw new UnsupportedOperationException("Unsupported type of body element : " + bodyElementType);
        }

        return res;
    }
}
