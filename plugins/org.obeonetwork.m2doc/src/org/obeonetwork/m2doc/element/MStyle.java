/*******************************************************************************
 *  Copyright (c) 2017, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
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
    /** Modifier flag for BOLD font, see {@link org.apache.poi.xwpf.usermodel.XWPFRun#setBold(boolean) setBold()}. */
    int FONT_BOLD = 1;
    /** Modifier flag for ITALIC font, see {@link org.apache.poi.xwpf.usermodel.XWPFRun#setItalic(boolean) setItalic()}. */
    int FONT_ITALIC = 1 << 1;
    /**
     * Modifier flag for UNDERLINE font, see
     * {@link org.apache.poi.xwpf.usermodel.XWPFRun#setUnderline(org.apache.poi.xwpf.usermodel.UnderlinePatterns) setUnderline()}.
     */
    int FONT_UNDERLINE = 1 << 2;
    /**
     * Modifier flag for STRIKE-THROUGH font, see {@link org.apache.poi.xwpf.usermodel.XWPFRun#setStrikeThrough(boolean)
     * setStrikeThrough()}.
     */
    int FONT_STRIKE_THROUGH = 1 << 3;
    /**
     * Modifier flag for SUBSCRIPT, see
     * {@link org.apache.poi.xwpf.usermodel.XWPFRun#setSubscript(org.apache.poi.xwpf.usermodel.VerticalAlign) setSubscript()}.
     */
    int SUBSCRIPT = 1 << 4;
    /**
     * Modifier flag for SUPERSCRIPT, see
     * {@link org.apache.poi.xwpf.usermodel.XWPFRun#setSubscript(org.apache.poi.xwpf.usermodel.VerticalAlign) setSubscript()}.
     */
    int SUPERSCRIPT = 1 << 5;
    /** Modifier flag for FONT_SMALL_CAPS, see {@link org.apache.poi.xwpf.usermodel.XWPFRun#setSmallCaps(boolean) setSmallCaps()}. */
    int FONT_SMALL_CAPS = 1 << 6;

    /**
     * Gets the font size in points.
     * 
     * @return the font size in points if any, <code>-1</code> otherwise
     */
    int getFontSize();

    /**
     * Sets the font size in points.
     * 
     * @param fontSize
     *            the new font size in points, <code>-1</code> for default
     */
    void setFontSize(int fontSize);

    /**
     * Gets the font name.
     * 
     * @return the font name if any, <code>null</code> otherwise
     */
    String getFontName();

    /**
     * Sets the font name.
     * 
     * @param fontName
     *            the new font name, <code>null</code> for default
     */
    void setFontName(String fontName);

    /**
     * Gets the foreground {@link Color}.
     * 
     * @return the foreground {@link Color} if any, <code>null</code> otherwise
     */
    Color getForegroundColor();

    /**
     * Sets the foreground {@link Color}.
     * 
     * @param color
     *            the new foreground {@link Color}, <code>null</code> for default
     */
    void setForegroundColor(Color color);

    /**
     * Gets the background {@link Color}.
     * 
     * @return the background {@link Color} if any, <code>null</code> otherwise
     */
    Color getBackgroundColor();

    /**
     * Sets the background {@link Color}.
     * 
     * @param color
     *            the new background {@link Color}, <code>null</code> for default
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
     *            the new font modifiers, <code>-1</code> for default
     */
    void setModifiers(int modifiers);
}
