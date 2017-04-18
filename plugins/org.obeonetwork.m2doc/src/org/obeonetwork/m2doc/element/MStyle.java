/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.element;

import java.awt.Color;

/**
 * Describes the style of an element.
 * 
 * @author ldelaigue
 */
public interface MStyle {
    /** Modifier flag for BOLD font, to be checked by and-ing it with the value returned by getFontModidiers(). */
    int FONT_BOLD = 1;
    /** Modifier flag for ITALIC font, to be checked by and-ing it with the value returned by getFontModidiers(). */
    int FONT_ITALIC = 1 << 1;
    /** Modifier flag for UNDERLINE font, to be checked by and-ing it with the value returned by getFontModidiers(). */
    int FONT_UNDERLINE = 1 << 2;
    /** Modifier flag for STRIKE-THROUGH font, to be checked by and-ing it with the value returned by getFontModidiers(). */
    int FONT_STRIKE_THROUGH = 1 << 3;

    /**
     * Gets the font size in points.
     * 
     * @return the font size in points
     */
    int getFontSize();

    /**
     * Sets the font size in points.
     * 
     * @param fontSize
     *            the new font size in points
     */
    void setFontSize(int fontSize);

    /**
     * Gets the foreground {@link Color}.
     * 
     * @return the foreground {@link Color}
     */
    Color getForegroundColor();

    /**
     * Sets the foreground {@link Color}.
     * 
     * @param color
     *            the new foreground {@link Color}
     */
    void setForegroundColor(Color color);

    /**
     * Gets the background {@link Color}.
     * 
     * @return the background {@link Color}
     */
    Color getBackgroundColor();

    /**
     * Sets the background {@link Color}.
     * 
     * @param color
     *            the new background {@link Color}
     */
    void setBackgroundColor(Color color);

    /**
     * Gets the font modifiers. To know whether the font is bold, use:
     * <code>getFontModifiers() & MStyle.FONT_BOLD != 0</code>
     * 
     * @return an integer that aggregates the font modifiers
     */
    int getFontModifiers();

    /**
     * Sets the font modifiers. To know whether the font is bold, use:
     * <code>getFontModifiers() & MStyle.FONT_BOLD != 0</code>
     * 
     * @param modifiers
     *            the new font modifiers
     */
    void setModifiers(int modifiers);
}
