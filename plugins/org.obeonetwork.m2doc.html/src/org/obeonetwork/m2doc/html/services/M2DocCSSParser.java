/*******************************************************************************
 *  Copyright (c) 2021 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.html.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.obeonetwork.m2doc.element.MStyle;

/**
 * A simple naive CSS parser for "style" attribute.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocCSSParser extends Parser {

    /**
     * The color property.
     */
    protected static final String CSS_COLOR = "color";

    /**
     * The background color property.
     */
    protected static final String CSS_BACKGROUND_COLOR = "background-color";

    /**
     * The list-style-type color property.
     */
    protected static final String CSS_LIST_STYLE_TYPE = "list-style-type";

    /**
     * The text-align color property.
     */
    protected static final String CSS_TEXT_ALIGN = "text-align";

    /**
     * The text-transform color property.
     */
    protected static final String CSS_TEXT_TRANSFORM = "text-transform";

    /**
     * The vertical-align color property.
     */
    protected static final String CSS_VERTICAL_ALIGN = "vertical-align";

    /**
     * The text-decoration color property.
     */
    protected static final String CSS_TEXT_DECORATION = "text-decoration";

    /**
     * The font-style color property.
     */
    protected static final String CSS_FONT_STYLE = "font-style";

    /**
     * The field separator.
     */
    private static final String FIELD_SEPARATOR = ";";

    /**
     * The value separator.
     */
    private static final String VALUE_SEPARATOR = ":";

    /**
     * The mask that activate all font modifiers.
     */
    private static final int ALL_FONT_MODIFIERS_MASK = 0xFFFFFFFF;

    /**
     * Parses the given CSS "style" attribute.
     * 
     * @param cssStyle
     *            the CSS style attribute
     * @return the mapping of CSS key to values
     */
    public Map<String, List<String>> parse(String cssStyle) {
        final Map<String, List<String>> res = new HashMap<String, List<String>>();

        for (String field : cssStyle.split(FIELD_SEPARATOR)) {
            final String[] keyValue = field.split(VALUE_SEPARATOR);
            if (keyValue.length >= 1 && !keyValue[0].trim().isEmpty()) {
                final List<String> values = res.computeIfAbsent(keyValue[0].trim().toLowerCase(),
                        key -> new ArrayList<String>());
                if (keyValue.length == 2) {
                    values.add(keyValue[1].trim().toLowerCase());
                }
            }
        }

        return res;
    }

    /**
     * Sets the CSS styles to the given {@link MStyle}.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param mStyle
     *            the {@link MStyle}
     */
    public void setStyle(Map<String, List<String>> cssProperties, MStyle mStyle) {
        final List<String> cssColors = cssProperties.get(CSS_COLOR);
        if (cssColors != null) {
            for (String cssColor : cssColors) {
                mStyle.setForegroundColor(htmlToColor(cssColor));
            }
        }

        final List<String> cssBackgroundColors = cssProperties.get(CSS_BACKGROUND_COLOR);
        if (cssBackgroundColors != null) {
            for (String cssBackgroundColor : cssBackgroundColors) {
                mStyle.setBackgroundColor(htmlToColor(cssBackgroundColor));
            }
        }

        int modifiers = mStyle.getFontModifiers();
        if (modifiers == -1) {
            modifiers = 0;
        }
        final List<String> cssTextDecorations = cssProperties.get(CSS_TEXT_DECORATION);
        if (cssTextDecorations != null) {
            for (String cssTextDecoration : cssTextDecorations) {
                if ("line-through".equals(cssTextDecoration)) {
                    modifiers = modifiers | MStyle.FONT_STRIKE_THROUGH;
                } else if ("underline".equals(cssTextDecoration)) {
                    modifiers = modifiers | MStyle.FONT_UNDERLINE;
                }
            }
        }

        final List<String> cssFontStyles = cssProperties.get(CSS_FONT_STYLE);
        if (cssFontStyles != null) {
            for (String cssFontStyle : cssFontStyles) {
                if ("italic".equals(cssFontStyle) || "oblique".equals(cssFontStyle)) {
                    modifiers = modifiers | MStyle.FONT_ITALIC;
                } else if ("normal".equals(cssFontStyle)) {
                    modifiers = modifiers & (ALL_FONT_MODIFIERS_MASK ^ MStyle.FONT_ITALIC);
                }
            }
        }

        final List<String> cssVerticalAligns = cssProperties.get(CSS_VERTICAL_ALIGN);
        if (cssVerticalAligns != null) {
            for (String cssVerticalAlign : cssVerticalAligns) {
                if ("sub".equals(cssVerticalAlign)) {
                    modifiers = modifiers | MStyle.SUBSCRIPT;
                } else if ("super".equals(cssVerticalAlign)) {
                    modifiers = modifiers | MStyle.SUPERSCRIPT;
                }
            }
        }

        mStyle.setModifiers(modifiers);
    }

    /**
     * Tells if the given CSS properties has the given property and value.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param propertyName
     *            the property name
     * @param propertyValue
     *            the property value
     * @return <code>true</code> if the given CSS properties has the given property and value, <code>false</code> otherwise
     */
    public boolean hasCSS(Map<String, List<String>> cssProperties, String propertyName, String propertyValue) {
        boolean res = false;

        final List<String> values = cssProperties.get(propertyName);
        if (values != null) {
            for (String value : values) {
                if (value.equals(propertyValue)) {
                    res = true;
                    break;
                }
            }
        }

        return res;
    }

}
