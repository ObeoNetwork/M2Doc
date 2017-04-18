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

import java.awt.Color;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.table.metamodel.table.DTableElementStyle;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.obeonetwork.m2doc.element.MStyle;

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
    public Color getForegroundColor() {
        final RGBValues rgb = style.getForegroundColor();
        return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
    }

    @Override
    public Color getBackgroundColor() {
        final RGBValues rgb = style.getBackgroundColor();
        return new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
    }

    @Override
    public void setFontSize(int fontSize) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setForegroundColor(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setBackgroundColor(Color color) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setModifiers(int modifiers) {
        throw new UnsupportedOperationException();
    }
}
