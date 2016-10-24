/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.providers.tables;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.obeonetwork.m2doc.provider.AbstractTableProvider.MStyle;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Implementation of MStyle based on a DStyle.
 * 
 * @author ldelaigue
 */
public class DMStyle implements MStyle {
    /** The wrapped sirius style. */
    private final DTableElementStyle style;

    /**
     * Constructor.
     * 
     * @param style
     *            The style to wrap, cannot be <code>null</code>
     */
    public DMStyle(DTableElementStyle style) {
        this.style = checkNotNull(style);
    }

    @Override
    public int getFontSize() {
        return style.getLabelSize();
    }

    @Override
    public int getFontModifiers() {
        int result = 0;
        EList<FontFormat> formats = style.getLabelFormat();
        for (FontFormat format : formats) {
            switch (format) {
                case BOLD_LITERAL:
                    result |= MStyle.FONT_BOLD;
                    break;
                case ITALIC_LITERAL:
                    result |= MStyle.FONT_ITALIC;
                    break;
                case STRIKE_THROUGH_LITERAL:
                    result |= MStyle.FONT_STRIKE_THROUGH;
                    break;
                case UNDERLINE_LITERAL:
                    result |= MStyle.FONT_UNDERLINE;
                    break;
                default:
                    // Nothing to do, if Sirius adds a format we won't deal with it
            }
        }
        return result;
    }

    @Override
    public int getForegroundColor() {
        RGBValues rgb = style.getForegroundColor();
        return rgbToInt(rgb);
    }

    @Override
    public int getBackgroundColor() {
        RGBValues rgb = style.getBackgroundColor();
        return rgbToInt(rgb);
    }

    /**
     * Convert the given RGB values to an int in the format expected by M2Doc.
     * 
     * @param rgb
     *            The rgb values to convert
     * @return A int that represents the color in the format expected by M2Doc.
     */
    private int rgbToInt(RGBValues rgb) {
        return rgb.getRed() << Byte.SIZE * 2 | rgb.getGreen() << Byte.SIZE | rgb.getBlue();
    }
}
