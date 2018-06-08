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
package org.obeonetwork.m2doc.element.impl;

import java.awt.Color;

import org.obeonetwork.m2doc.element.MStyle;

/**
 * Describes the style of an element.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MStyleImpl implements MStyle {

    /**
     * The font size.
     */
    private int fontSize = -1;

    /**
     * The font name.
     */
    private String fontName;

    /**
     * The foreground {@link Color}.
     */
    private Color foregroundColor;

    /**
     * The background {@link Color}.
     */
    private Color backgroundColor;

    /**
     * Modifiers.
     */
    private int modifiers = -1;

    /**
     * Constructor.
     *
     * @param fontName
     *            the font name
     * @param fontSize
     *            the font size to use, <code>-1</code> for default
     * @param foregroundColor
     *            the foreground color to use, can be <code>null</code>
     * @param backgroundColor
     *            the background color to use, can be <code>null</code>
     * @param modifiers
     *            the modifiers color to use, <code>-1</code> for default
     */
    public MStyleImpl(String fontName, int fontSize, Color foregroundColor, Color backgroundColor, int modifiers) {
        this.fontSize = fontSize;
        this.fontName = fontName;
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
        this.modifiers = modifiers;
    }

    @Override
    public int getFontSize() {
        return fontSize;
    }

    @Override
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    @Override
    public String getFontName() {
        return fontName;
    }

    @Override
    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    @Override
    public Color getForegroundColor() {
        return foregroundColor;
    }

    @Override
    public void setForegroundColor(Color color) {
        this.foregroundColor = color;
    }

    @Override
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    @Override
    public void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    @Override
    public int getFontModifiers() {
        return modifiers;
    }

    @Override
    public void setModifiers(int modifiers) {
        this.modifiers = modifiers;
    }

}
