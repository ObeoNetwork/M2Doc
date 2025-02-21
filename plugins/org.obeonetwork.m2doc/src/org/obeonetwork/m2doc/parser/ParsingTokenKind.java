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

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.obeonetwork.m2doc.util.M2DocUtils;

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
    CONTENTCONTROL,

    /**
     * {@link M2DocUtils#M_FIELD_START} kind.
     */
    FIELD_START,

    /**
     * {@link M2DocUtils#M_FIELD_END} kind.
     */
    FIELD_END,

    /**
     * Missing {@link M2DocUtils#M_FIELD_END} kind.
     */
    MISSING_FIELD_END,;

    /**
     * Gets the {@link ParsingTokenKind} from the given {@link BodyElementType}.
     * 
     * @param bodyElementType
     *            the {@link BodyElementType}
     * @return the {@link ParsingTokenKind} from the given {@link BodyElementType}
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
