/*******************************************************************************
 *  Copyright (c) 2021, 2024 Obeo. 
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

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.obeonetwork.m2doc.element.MStyle;

/**
 * Abstract parser class for utility methods.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class Parser {

    /**
     * The {@link Map} of known {@link Color}.
     */
    protected static final Map<String, Color> COLORS = initializeColors();

    /**
     * The default font size.
     */
    protected static final int DEFAULT_FONT_SIZE = 11;

    /**
     * The class attribute.
     */
    protected static final String CLASS_ATTR = "class";

    /**
     * The hexadecimal color string length.
     */
    private static final int HEXA_COLOR_LENGTH = 7;

    /**
     * The rgb(x,x,x) {@link Pattern}.
     */
    private static final Pattern RGB_PATTERN = Pattern
            .compile("\\s*rgb\\s*\\(\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*,\\s*([0-9]+)\\s*\\)\\s*");;

    /**
     * The index of red in {@link #RGB_PATTERN}.
     */
    private static final int R_GROUP_INDEX = 1;

    /**
     * The index of green in {@link #RGB_PATTERN}.
     */
    private static final int G_GROUP_INDEX = 2;

    /**
     * The index of blue in {@link #RGB_PATTERN}.
     */
    private static final int B_GROUP_INDEX = 3;

    /**
     * The fixed size regex.
     */
    private static final Pattern FIXED_SIZE_PATTERN = Pattern.compile("([0-9]+(\\.[0-9]+)?)(cm|mm|px|in|pt|pc|em|%)");

    /**
     * The value group for {@link #FIXED_SIZE_PATTERN}.
     */
    private static final int FIXED_SIZE_PATTERN_VALUE_GROUP = 1;

    /**
     * The unit group for {@link #FIXED_SIZE_PATTERN}.
     */
    private static final int FIXED_SIZE_PATTERN_UNIT_GROUP = 3;

    /**
     * The font size regex.
     */
    private static final Pattern FONT_SIZE_PATTERN = Pattern.compile("([0-9]+(\\.[0-9]+)?)(pt)?");

    /**
     * The value group for {@link #FONT_SIZE_PATTERN}.
     */
    private static final int FONT_SIZE_PATTERN_VALUE_GROUP = 1;

    /**
     * The unit group for {@link #FONT_SIZE_PATTERN}.
     */
    private static final int FONT_SIZE_PATTERN_UNIT_GROUP = 3;

    /**
     * Initializes the {@link Color} mapping.
     * 
     * @return the HTML name to {@link Color} mapping
     */
    // CHECKSTYLE:OFF
    private static Map<String, Color> initializeColors() {
        final Map<String, Color> res = new HashMap<String, Color>();

        res.put("AliceBlue".toLowerCase(), Color.decode("0xF0F8FF"));
        res.put("AntiqueWhite".toLowerCase(), Color.decode("0xFAEBD7"));
        res.put("Aqua".toLowerCase(), Color.decode("0x00FFFF"));
        res.put("Aquamarine".toLowerCase(), Color.decode("0x7FFFD4"));
        res.put("Azure".toLowerCase(), Color.decode("0xF0FFFF"));
        res.put("Beige".toLowerCase(), Color.decode("0xF5F5DC"));
        res.put("Bisque".toLowerCase(), Color.decode("0xFFE4C4"));
        res.put("Black".toLowerCase(), Color.decode("0x000000"));
        res.put("BlanchedAlmond".toLowerCase(), Color.decode("0xFFEBCD"));
        res.put("Blue".toLowerCase(), Color.decode("0x0000FF"));
        res.put("BlueViolet".toLowerCase(), Color.decode("0x8A2BE2"));
        res.put("Brown".toLowerCase(), Color.decode("0xA52A2A"));
        res.put("BurlyWood".toLowerCase(), Color.decode("0xDEB887"));
        res.put("CadetBlue".toLowerCase(), Color.decode("0x5F9EA0"));
        res.put("Chartreuse".toLowerCase(), Color.decode("0x7FFF00"));
        res.put("Chocolate".toLowerCase(), Color.decode("0xD2691E"));
        res.put("Coral".toLowerCase(), Color.decode("0xFF7F50"));
        res.put("CornflowerBlue".toLowerCase(), Color.decode("0x6495ED"));
        res.put("Cornsilk".toLowerCase(), Color.decode("0xFFF8DC"));
        res.put("Crimson".toLowerCase(), Color.decode("0xDC143C"));
        res.put("Cyan".toLowerCase(), Color.decode("0x00FFFF"));
        res.put("DarkBlue".toLowerCase(), Color.decode("0x00008B"));
        res.put("DarkCyan".toLowerCase(), Color.decode("0x008B8B"));
        res.put("DarkGoldenRod".toLowerCase(), Color.decode("0xB8860B"));
        res.put("DarkGray".toLowerCase(), Color.decode("0xA9A9A9"));
        res.put("DarkGrey".toLowerCase(), Color.decode("0xA9A9A9"));
        res.put("DarkGreen".toLowerCase(), Color.decode("0x006400"));
        res.put("DarkKhaki".toLowerCase(), Color.decode("0xBDB76B"));
        res.put("DarkMagenta".toLowerCase(), Color.decode("0x8B008B"));
        res.put("DarkOliveGreen".toLowerCase(), Color.decode("0x556B2F"));
        res.put("DarkOrange".toLowerCase(), Color.decode("0xFF8C00"));
        res.put("DarkOrchid".toLowerCase(), Color.decode("0x9932CC"));
        res.put("DarkRed".toLowerCase(), Color.decode("0x8B0000"));
        res.put("DarkSalmon".toLowerCase(), Color.decode("0xE9967A"));
        res.put("DarkSeaGreen".toLowerCase(), Color.decode("0x8FBC8F"));
        res.put("DarkSlateBlue".toLowerCase(), Color.decode("0x483D8B"));
        res.put("DarkSlateGray".toLowerCase(), Color.decode("0x2F4F4F"));
        res.put("DarkSlateGrey".toLowerCase(), Color.decode("0x2F4F4F"));
        res.put("DarkTurquoise".toLowerCase(), Color.decode("0x00CED1"));
        res.put("DarkViolet".toLowerCase(), Color.decode("0x9400D3"));
        res.put("DeepPink".toLowerCase(), Color.decode("0xFF1493"));
        res.put("DeepSkyBlue".toLowerCase(), Color.decode("0x00BFFF"));
        res.put("DimGray".toLowerCase(), Color.decode("0x696969"));
        res.put("DimGrey".toLowerCase(), Color.decode("0x696969"));
        res.put("DodgerBlue".toLowerCase(), Color.decode("0x1E90FF"));
        res.put("FireBrick".toLowerCase(), Color.decode("0xB22222"));
        res.put("FloralWhite".toLowerCase(), Color.decode("0xFFFAF0"));
        res.put("ForestGreen".toLowerCase(), Color.decode("0x228B22"));
        res.put("Fuchsia".toLowerCase(), Color.decode("0xFF00FF"));
        res.put("Gainsboro".toLowerCase(), Color.decode("0xDCDCDC"));
        res.put("GhostWhite".toLowerCase(), Color.decode("0xF8F8FF"));
        res.put("Gold".toLowerCase(), Color.decode("0xFFD700"));
        res.put("GoldenRod".toLowerCase(), Color.decode("0xDAA520"));
        res.put("Gray".toLowerCase(), Color.decode("0x808080"));
        res.put("Grey".toLowerCase(), Color.decode("0x808080"));
        res.put("Green".toLowerCase(), Color.decode("0x008000"));
        res.put("GreenYellow".toLowerCase(), Color.decode("0xADFF2F"));
        res.put("HoneyDew".toLowerCase(), Color.decode("0xF0FFF0"));
        res.put("HotPink".toLowerCase(), Color.decode("0xFF69B4"));
        res.put("IndianRed".toLowerCase(), Color.decode("0xCD5C5C"));
        res.put("Indigo".toLowerCase(), Color.decode("0x4B0082"));
        res.put("Ivory".toLowerCase(), Color.decode("0xFFFFF0"));
        res.put("Khaki".toLowerCase(), Color.decode("0xF0E68C"));
        res.put("Lavender".toLowerCase(), Color.decode("0xE6E6FA"));
        res.put("LavenderBlush".toLowerCase(), Color.decode("0xFFF0F5"));
        res.put("LawnGreen".toLowerCase(), Color.decode("0x7CFC00"));
        res.put("LemonChiffon".toLowerCase(), Color.decode("0xFFFACD"));
        res.put("LightBlue".toLowerCase(), Color.decode("0xADD8E6"));
        res.put("LightCoral".toLowerCase(), Color.decode("0xF08080"));
        res.put("LightCyan".toLowerCase(), Color.decode("0xE0FFFF"));
        res.put("LightGoldenRodYellow".toLowerCase(), Color.decode("0xFAFAD2"));
        res.put("LightGray".toLowerCase(), Color.decode("0xD3D3D3"));
        res.put("LightGrey".toLowerCase(), Color.decode("0xD3D3D3"));
        res.put("LightGreen".toLowerCase(), Color.decode("0x90EE90"));
        res.put("LightPink".toLowerCase(), Color.decode("0xFFB6C1"));
        res.put("LightSalmon".toLowerCase(), Color.decode("0xFFA07A"));
        res.put("LightSeaGreen".toLowerCase(), Color.decode("0x20B2AA"));
        res.put("LightSkyBlue".toLowerCase(), Color.decode("0x87CEFA"));
        res.put("LightSlateGray".toLowerCase(), Color.decode("0x778899"));
        res.put("LightSlateGrey".toLowerCase(), Color.decode("0x778899"));
        res.put("LightSteelBlue".toLowerCase(), Color.decode("0xB0C4DE"));
        res.put("LightYellow".toLowerCase(), Color.decode("0xFFFFE0"));
        res.put("Lime".toLowerCase(), Color.decode("0x00FF00"));
        res.put("LimeGreen".toLowerCase(), Color.decode("0x32CD32"));
        res.put("Linen".toLowerCase(), Color.decode("0xFAF0E6"));
        res.put("Magenta".toLowerCase(), Color.decode("0xFF00FF"));
        res.put("Maroon".toLowerCase(), Color.decode("0x800000"));
        res.put("MediumAquaMarine".toLowerCase(), Color.decode("0x66CDAA"));
        res.put("MediumBlue".toLowerCase(), Color.decode("0x0000CD"));
        res.put("MediumOrchid".toLowerCase(), Color.decode("0xBA55D3"));
        res.put("MediumPurple".toLowerCase(), Color.decode("0x9370DB"));
        res.put("MediumSeaGreen".toLowerCase(), Color.decode("0x3CB371"));
        res.put("MediumSlateBlue".toLowerCase(), Color.decode("0x7B68EE"));
        res.put("MediumSpringGreen".toLowerCase(), Color.decode("0x00FA9A"));
        res.put("MediumTurquoise".toLowerCase(), Color.decode("0x48D1CC"));
        res.put("MediumVioletRed".toLowerCase(), Color.decode("0xC71585"));
        res.put("MidnightBlue".toLowerCase(), Color.decode("0x191970"));
        res.put("MintCream".toLowerCase(), Color.decode("0xF5FFFA"));
        res.put("MistyRose".toLowerCase(), Color.decode("0xFFE4E1"));
        res.put("Moccasin".toLowerCase(), Color.decode("0xFFE4B5"));
        res.put("NavajoWhite".toLowerCase(), Color.decode("0xFFDEAD"));
        res.put("Navy".toLowerCase(), Color.decode("0x000080"));
        res.put("OldLace".toLowerCase(), Color.decode("0xFDF5E6"));
        res.put("Olive".toLowerCase(), Color.decode("0x808000"));
        res.put("OliveDrab".toLowerCase(), Color.decode("0x6B8E23"));
        res.put("Orange".toLowerCase(), Color.decode("0xFFA500"));
        res.put("OrangeRed".toLowerCase(), Color.decode("0xFF4500"));
        res.put("Orchid".toLowerCase(), Color.decode("0xDA70D6"));
        res.put("PaleGoldenRod".toLowerCase(), Color.decode("0xEEE8AA"));
        res.put("PaleGreen".toLowerCase(), Color.decode("0x98FB98"));
        res.put("PaleTurquoise".toLowerCase(), Color.decode("0xAFEEEE"));
        res.put("PaleVioletRed".toLowerCase(), Color.decode("0xDB7093"));
        res.put("PapayaWhip".toLowerCase(), Color.decode("0xFFEFD5"));
        res.put("PeachPuff".toLowerCase(), Color.decode("0xFFDAB9"));
        res.put("Peru".toLowerCase(), Color.decode("0xCD853F"));
        res.put("Pink".toLowerCase(), Color.decode("0xFFC0CB"));
        res.put("Plum".toLowerCase(), Color.decode("0xDDA0DD"));
        res.put("PowderBlue".toLowerCase(), Color.decode("0xB0E0E6"));
        res.put("Purple".toLowerCase(), Color.decode("0x800080"));
        res.put("RebeccaPurple".toLowerCase(), Color.decode("0x663399"));
        res.put("Red".toLowerCase(), Color.decode("0xFF0000"));
        res.put("RosyBrown".toLowerCase(), Color.decode("0xBC8F8F"));
        res.put("RoyalBlue".toLowerCase(), Color.decode("0x4169E1"));
        res.put("SaddleBrown".toLowerCase(), Color.decode("0x8B4513"));
        res.put("Salmon".toLowerCase(), Color.decode("0xFA8072"));
        res.put("SandyBrown".toLowerCase(), Color.decode("0xF4A460"));
        res.put("SeaGreen".toLowerCase(), Color.decode("0x2E8B57"));
        res.put("SeaShell".toLowerCase(), Color.decode("0xFFF5EE"));
        res.put("Sienna".toLowerCase(), Color.decode("0xA0522D"));
        res.put("Silver".toLowerCase(), Color.decode("0xC0C0C0"));
        res.put("SkyBlue".toLowerCase(), Color.decode("0x87CEEB"));
        res.put("SlateBlue".toLowerCase(), Color.decode("0x6A5ACD"));
        res.put("SlateGray".toLowerCase(), Color.decode("0x708090"));
        res.put("SlateGrey".toLowerCase(), Color.decode("0x708090"));
        res.put("Snow".toLowerCase(), Color.decode("0xFFFAFA"));
        res.put("SpringGreen".toLowerCase(), Color.decode("0x00FF7F"));
        res.put("SteelBlue".toLowerCase(), Color.decode("0x4682B4"));
        res.put("Tan".toLowerCase(), Color.decode("0xD2B48C"));
        res.put("Teal".toLowerCase(), Color.decode("0x008080"));
        res.put("Thistle".toLowerCase(), Color.decode("0xD8BFD8"));
        res.put("Tomato".toLowerCase(), Color.decode("0xFF6347"));
        res.put("Turquoise".toLowerCase(), Color.decode("0x40E0D0"));
        res.put("Violet".toLowerCase(), Color.decode("0xEE82EE"));
        res.put("Wheat".toLowerCase(), Color.decode("0xF5DEB3"));
        res.put("White".toLowerCase(), Color.decode("0xFFFFFF"));
        res.put("WhiteSmoke".toLowerCase(), Color.decode("0xF5F5F5"));
        res.put("Yellow".toLowerCase(), Color.decode("0xFFFF00"));
        res.put("YellowGreen".toLowerCase(), Color.decode("0x9ACD32"));

        return res;
    }
    // CHECKSTYLE:ON

    /**
     * Sets the given modifier for the given {@link Context}.
     * 
     * @param mStyle
     *            the {@link MStyle}
     * @param modifier
     *            the modifier
     */
    protected void setModifiers(MStyle mStyle, int modifier) {
        if (mStyle.getFontModifiers() == -1) {
            mStyle.setModifiers(modifier);
        } else {
            mStyle.setModifiers(mStyle.getFontModifiers() | modifier);
        }
    }

    /**
     * Gets the {@link Color} from the given HTML color {@link String}.
     * 
     * @param htmlColor
     *            the HTML color {@link String}
     * @return the {@link Color} from the given HTML color {@link String}
     */
    protected Color htmlToColor(String htmlColor) {
        Color res;

        try {
            final Color knownColor = COLORS.get(htmlColor);
            if (knownColor != null) {
                res = knownColor;
            } else {
                final Matcher matcher = RGB_PATTERN.matcher(htmlColor);
                if (matcher.matches()) {
                    final int r = Integer.valueOf(matcher.group(R_GROUP_INDEX));
                    final int g = Integer.valueOf(matcher.group(G_GROUP_INDEX));
                    final int b = Integer.valueOf(matcher.group(B_GROUP_INDEX));
                    res = new Color(r, g, b);
                } else if (htmlColor.length() == HEXA_COLOR_LENGTH) {
                    res = Color.decode(htmlColor.replace("#", "0x"));
                } else if (htmlColor.length() == 4) {
                    res = Color.decode("0x" + htmlColor.charAt(1) + htmlColor.charAt(1) + htmlColor.charAt(2)
                        + htmlColor.charAt(2) + htmlColor.charAt(3) + htmlColor.charAt(3));
                } else {
                    res = null;
                }
                COLORS.put(htmlColor, res);
            }
            // CHECKSTYLE:OFF ignore color
        } catch (Exception e) {
            // CHECKSTYLE:ON
            // something went wrong ignore the color
            res = null;
        }

        return res;
    }

    /**
     * Gets the number of point for the given font size.
     * 
     * @param fontSize
     *            the font size
     * @return the number of point for the given font size
     */
    protected int fontSizeToPoint(String fontSize) {
        final int res;

        final Matcher matcher;
        final String localPropertyValue = fontSize.trim().toLowerCase();
        if (localPropertyValue.startsWith(".")) {
            matcher = FONT_SIZE_PATTERN.matcher("0" + localPropertyValue);
        } else {
            matcher = FONT_SIZE_PATTERN.matcher(localPropertyValue);
        }

        if (matcher.find()) {
            final double value = Double.valueOf(matcher.group(FONT_SIZE_PATTERN_VALUE_GROUP));
            final String unit = matcher.group(FONT_SIZE_PATTERN_UNIT_GROUP);

            if (unit == null || "pt".equals(unit)) {
                // CHECKSTYLE:OFF
                switch ((int) value) {
                    case 1:
                        res = 7;
                        break;

                    case 2:
                        res = 10;
                        break;

                    case 3:
                        res = 12;
                        break;

                    case 4:
                        res = 14;
                        break;

                    case 5:
                        res = 18;
                        break;

                    case 6:
                        res = 24;
                        break;

                    default:
                        res = 36;
                        break;
                }
            } else {
                res = -1;
            }
            // CHECKSTYLE:ON
        } else {
            res = -1;
        }
        // CHECKSTYLE:ON

        return res;
    }

    /**
     * Gets the size in pixels for the given CSS property value.
     * 
     * @param propertyValue
     *            the property value
     * @return the size in pixels if any, <code>-1</code> otherwise
     */
    protected int getPixels(String propertyValue) {
        final int res;

        final Matcher matcher;
        final String localPropertyValue = propertyValue.trim().toLowerCase();
        if (localPropertyValue.startsWith(".")) {
            matcher = FIXED_SIZE_PATTERN.matcher("0" + localPropertyValue);
        } else {
            matcher = FIXED_SIZE_PATTERN.matcher(localPropertyValue);
        }

        if (matcher.find()) {
            final double value = Double.valueOf(matcher.group(FIXED_SIZE_PATTERN_VALUE_GROUP));
            final String unit = matcher.group(FIXED_SIZE_PATTERN_UNIT_GROUP);

            // CHECKSTYLE:OFF unit conversion
            switch (unit) {
                case "cm":
                    res = (int) ((value * 96d) / 2.54d);
                    break;
                case "mm":
                    res = (int) (((value / 100d) * 96d) / 2.54d);
                    break;
                case "px":
                    res = (int) value;
                    break;
                case "in":
                    res = (int) (value * 96d);
                    break;
                case "pt":
                    res = (int) ((value / 72d) * 96d);
                    break;
                case "pc":
                    res = (int) (((value * 12d) / 72d) * 96d);
                    break;
                case "em":
                    res = (int) (value * Double.valueOf(DEFAULT_FONT_SIZE));
                    break;
                case "%":
                    res = (int) ((value / 100d) * Double.valueOf(DEFAULT_FONT_SIZE));
                    break;

                default:
                    res = -1;
                    break;
                // CHECKSTYLE:ON
            }
        } else {
            res = -1;
        }

        return res;
    }

}
