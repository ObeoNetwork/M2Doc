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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.jsoup.nodes.Node;
import org.obeonetwork.m2doc.element.MBorder;
import org.obeonetwork.m2doc.element.MBorder.Type;
import org.obeonetwork.m2doc.element.MElementContainer;
import org.obeonetwork.m2doc.element.MElementContainer.HAlignment;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.impl.MBorderImpl;
import org.obeonetwork.m2doc.html.services.M2DocHTMLParser.Context;

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
     * The background property.
     */
    protected static final String CSS_BACKGROUND = "background";

    /**
     * The list-style-type property.
     */
    protected static final String CSS_LIST_STYLE_TYPE = "list-style-type";

    /**
     * The text-align property.
     */
    protected static final String CSS_TEXT_ALIGN = "text-align";

    /**
     * The text-transform property.
     */
    protected static final String CSS_TEXT_TRANSFORM = "text-transform";

    /**
     * The vertical-align property.
     */
    protected static final String CSS_VERTICAL_ALIGN = "vertical-align";

    /**
     * The text-decoration property.
     */
    protected static final String CSS_TEXT_DECORATION = "text-decoration";

    /**
     * The font-style property.
     */
    protected static final String CSS_FONT_STYLE = "font-style";

    /**
     * The font-weight property.
     */
    protected static final String CSS_FONT_WEIGHT = "font-weight";

    /**
     * The font-family property.
     */
    protected static final String CSS_FONT_FAMILY = "font-family";

    /**
     * The font-family property.
     */
    protected static final String CSS_FONT_SIZE = "font-size";

    /**
     * The font-variant property.
     */
    protected static final String CSS_FONT_VARIANT = "font-variant";

    /**
     * The margin property.
     */
    protected static final String CSS_MARGIN = "margin";

    /**
     * The margin-left property.
     */
    protected static final String CSS_MARGIN_LEFT = "margin-left";

    /**
     * The margin-right property.
     */
    protected static final String CSS_MARGIN_RIGHT = "margin-right";

    /**
     * The margin-top property.
     */
    protected static final String CSS_MARGIN_TOP = "margin-top";

    /**
     * The margin-bottom property.
     */
    protected static final String CSS_MARGIN_BOTTOM = "margin-bottoms";

    /**
     * The padding property.
     */
    protected static final String CSS_PADDING = "padding";

    /**
     * The padding-left property.
     */
    protected static final String CSS_PADDING_LEFT = "padding-left";

    /**
     * The padding-right property.
     */
    protected static final String CSS_PADDING_RIGHT = "padding-right";

    /**
     * The padding-top property.
     */
    protected static final String CSS_PADDING_TOP = "padding-top";

    /**
     * The padding-bottom property.
     */
    protected static final String CSS_PADDING_BOTTOM = "padding-bottom";

    /**
     * The border-style property.
     */
    protected static final String CSS_BORDER_STYLE = "border-style";

    /**
     * The border property.
     */
    protected static final String CSS_BORDER = "border";

    /**
     * The display property.
     */
    protected static final String CSS_DISPLAY = "display";

    /**
     * The float property.
     */
    protected static final String CSS_FLOAT = "float";

    /**
     * The width property.
     */
    private static final String CSS_WIDTH = WIDTH;

    /**
     * The width property.
     */
    private static final String CSS_HEIGHT = HEIGHT;

    /**
     * The CSS dot class separator.
     */
    private static final String CSS_CLASS_DOT = ".";

    /**
     * The line-through value.
     */
    private static final String LINE_THROUGH = "line-through";

    /**
     * The underline value.
     */
    private static final String UNDERLINE = "underline";

    /**
     * The bold value.
     */
    private static final String BOLD = "bold";

    /**
     * The oblique value.
     */
    private static final String OBLIQUE = "oblique";

    /**
     * The italic value.
     */
    private static final String ITALIC = "italic";

    /**
     * The normal value.
     */
    private static final String NORMAL = "normal";

    /**
     * The field separator.
     */
    private static final String FIELD_SEPARATOR = ";";

    /**
     * The value separator.
     */
    private static final String VALUE_SEPARATOR = ":";

    /**
     * The CSS comment.
     */
    private static final String CSS_COMMENT = "!";

    /**
     * The mask that activate all font modifiers.
     */
    private static final int ALL_FONT_MODIFIERS_MASK = 0xFFFFFFFF;

    /**
     * The {@link Pattern} that parse a CSS comment.
     */
    private static final Pattern CSS_COMMENT_PATTERN = Pattern.compile("/\\*.*?\\*/");

    /**
     * The {@link Pattern} that parse a CSS class.
     */
    private static final Pattern CSS_CLASS_PATTERN = Pattern
            .compile("(([_a-zA-Z0-9.:\\->,\\[\\]()\"=@^* #+~$\n\r]+\\s)+)\\{([^\\}]*)\\}");

    /**
     * The class name group from {@link #CSS_CLASS_PATTERN}.
     */
    private static final int CSS_CLASS_PATTERN_NAME_GROUP = 1;

    /**
     * The CSS styles group from {@link #CSS_CLASS_PATTERN}.
     */
    private static final int CSS_CLASS_PATTERN_CSS_STYLES_GROUP = 3;

    /**
     * The bold threshold for font weight.
     */
    private static final int CSS_FONT_WEIGHT_BOLD_THRESHOLD = 700;

    /**
     * Regular expression spaces match.
     */
    private static final String REG_EXP_SPACES = "\\s+";

    /**
     * The expression separating CSS class definition.
     */
    private static final String CLASS_DEFINITION_SEPARATOR = ",";

    /**
     * The style attribute.
     */
    private static final String STYLE_ATTR = "style";

    /**
     * Parses the given {@link String} representing a list of CSS classes.
     * 
     * @param cssClasses
     *            the {@link String} representing a list of CSS classes
     * @return a mapping from the CSS class name to its {@link #parseStyles(String) CSS styles}.
     */
    public Map<String, Map<String, List<String>>> parseClasses(String cssClasses) {
        Map<String, Map<String, List<String>>> res = new LinkedHashMap<>();

        final String cssNoComments = CSS_COMMENT_PATTERN.matcher(cssClasses).replaceAll("");
        final String cssNoAtRule = removeAtRules(cssNoComments);

        final Matcher matcher = CSS_CLASS_PATTERN.matcher(cssNoAtRule);
        while (matcher.find()) {
            final String classNames = matcher.group(CSS_CLASS_PATTERN_NAME_GROUP);
            final Map<String, List<String>> styles = parseStyles(matcher.group(CSS_CLASS_PATTERN_CSS_STYLES_GROUP));
            for (String name : classNames.split(CLASS_DEFINITION_SEPARATOR)) {
                final String className = name.trim();
                if (className != null && !className.isEmpty()) {
                    res.computeIfAbsent(className.trim(), n -> new LinkedHashMap<String, List<String>>())
                            .putAll(styles);
                }
            }
        }

        return res;
    }

    /**
     * Removes @ rules (@media, @scope, ...) form the given CSS.
     * 
     * @param cssNoComments
     *            the CSS without comment
     * @return the CSS without @ rules
     */
    private String removeAtRules(String cssNoComments) {
        final StringBuilder res = new StringBuilder();

        boolean inAtRule = false;
        int curlyBraceDepth = 0;
        for (int i = 0; i < cssNoComments.length(); i++) {
            final char current = cssNoComments.charAt(i);
            if (current == '@') {
                inAtRule = true;
            } else if (inAtRule) {
                if (current == '{') {
                    curlyBraceDepth++;
                } else if (current == '}') {
                    curlyBraceDepth--;
                    inAtRule = curlyBraceDepth > 0;
                }
            } else {
                res.append(current);
            }
        }

        return res.toString();
    }

    /**
     * Merges the source CSS styles to the target CSS styles.
     * 
     * @param source
     *            the source CSS styles
     * @param target
     *            the target CSS styles
     */
    public void mergeCSSStyles(final Map<String, Map<String, List<String>>> source,
            Map<String, Map<String, List<String>>> target) {
        for (Entry<String, Map<String, List<String>>> entry : source.entrySet()) {
            target.computeIfAbsent(entry.getKey(), n -> new LinkedHashMap<>()).putAll(entry.getValue());
        }
    }

    /**
     * Gets the {@link List} of CSS class names for the given {@link Node}.
     * 
     * @param node
     *            the {@link Node}
     * @return the {@link List} of CSS class names for the given {@link Node}
     */
    public List<String> getCSSClassNames(Node node) {
        final List<String> res = new ArrayList<>();

        res.add(node.nodeName());
        if (node.hasAttr(CLASS_ATTR)) {
            for (String className : node.attr(CLASS_ATTR).split(REG_EXP_SPACES)) {
                res.add(CSS_CLASS_DOT + className);
                res.add(VALUE_SEPARATOR + className);
                res.add(node.nodeName() + CSS_CLASS_DOT + className);
                res.add(node.nodeName() + VALUE_SEPARATOR + className);
            }
        }

        return res;
    }

    /**
     * Gets the CSS properties for the given {@link Node} and {@link #parseClasses(String) CSS classes}.
     * 
     * @param node
     *            the node
     * @param cssClasses
     *            the {@link #parseClasses(String) CSS classes}
     * @return the mapping of CSS key to values
     */
    public Map<String, List<String>> getCSSProperties(Node node,
            final Map<String, Map<String, List<String>>> cssClasses) {
        final Map<String, List<String>> cssProperties = new HashMap<>();
        final List<String> cssClasseNames = getCSSClassNames(node);

        for (String cssClasseName : cssClasseNames) {
            final Map<String, List<String>> cssClassesProperties = cssClasses.getOrDefault(cssClasseName,
                    Collections.emptyMap());
            cssProperties.putAll(cssClassesProperties);
        }

        if (node.hasAttr(STYLE_ATTR)) {
            final Map<String, List<String>> cssStyleProperties = parseStyles(node.attr(STYLE_ATTR));
            cssProperties.putAll(cssStyleProperties);
        }
        return cssProperties;
    }

    /**
     * Parses the given CSS "style" attribute.
     * 
     * @param cssStyle
     *            the CSS style attribute
     * @return the mapping of CSS key to values
     * @deprecated see {@link #parseStyles(String)}
     */
    public Map<String, List<String>> parse(String cssStyle) {
        return parseStyles(cssStyle);
    }

    /**
     * Parses the given CSS "style" attribute.
     * 
     * @param cssStyle
     *            the CSS style attribute
     * @return the mapping of CSS key to values
     */
    public Map<String, List<String>> parseStyles(String cssStyle) {
        final Map<String, List<String>> res = new LinkedHashMap<String, List<String>>();

        for (String field : cssStyle.split(FIELD_SEPARATOR)) {
            final String[] keyValue = field.split(VALUE_SEPARATOR);
            if (keyValue.length >= 1 && !keyValue[0].trim().isEmpty()) {
                final List<String> values = res.computeIfAbsent(keyValue[0].trim().toLowerCase(),
                        key -> new ArrayList<String>());
                if (keyValue.length == 2) {
                    final int commentIndex = keyValue[1].indexOf(CSS_COMMENT);
                    if (commentIndex >= 0) {
                        values.add(keyValue[1].substring(0, commentIndex).trim().toLowerCase());
                    } else {
                        values.add(keyValue[1].trim().toLowerCase());
                    }
                }
            }
        }

        return res;
    }

    /**
     * Sets the CSS styles to the given {@link MImage}.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param mImage
     *            the {@link MImage}
     */
    public void setStyle(Map<String, List<String>> cssProperties, MImage mImage) {
        final List<String> cssWidths = cssProperties.get(CSS_WIDTH);
        String width = null;
        if (cssWidths != null) {
            for (String cssWidth : cssWidths) {
                width = cssWidth;
            }
        }
        final List<String> cssHeights = cssProperties.get(CSS_HEIGHT);
        String height = null;
        if (cssHeights != null) {
            for (String cssHeight : cssHeights) {
                height = cssHeight;
            }
        }
        setImageSize(mImage, width, height);
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
        final List<String> cssBackgrounds = cssProperties.get(CSS_BACKGROUND);
        if (cssBackgrounds != null) {
            for (String cssBackground : cssBackgrounds) {
                mStyle.setBackgroundColor(htmlToColor(cssBackground));
            }
        }

        int modifiers = mStyle.getFontModifiers();
        if (modifiers == -1) {
            modifiers = 0;
        }
        final List<String> cssTextDecorations = cssProperties.get(CSS_TEXT_DECORATION);
        if (cssTextDecorations != null) {
            for (String cssTextDecoration : cssTextDecorations) {
                if (LINE_THROUGH.equals(cssTextDecoration)) {
                    modifiers = modifiers | MStyle.FONT_STRIKE_THROUGH;
                } else if (UNDERLINE.equals(cssTextDecoration)) {
                    modifiers = modifiers | MStyle.FONT_UNDERLINE;
                }
            }
        }

        final List<String> cssFontStyles = cssProperties.get(CSS_FONT_STYLE);
        if (cssFontStyles != null) {
            for (String cssFontStyle : cssFontStyles) {
                if (ITALIC.equals(cssFontStyle) || OBLIQUE.equals(cssFontStyle)) {
                    modifiers = modifiers | MStyle.FONT_ITALIC;
                } else if (NORMAL.equals(cssFontStyle)) {
                    modifiers = modifiers & (ALL_FONT_MODIFIERS_MASK ^ MStyle.FONT_ITALIC);
                }
            }
        }

        final List<String> cssFontWeights = cssProperties.get(CSS_FONT_WEIGHT);
        if (cssFontWeights != null) {
            for (String cssFontWeight : cssFontWeights) {
                if (BOLD.equals(cssFontWeight)) {
                    modifiers = modifiers | MStyle.FONT_BOLD;
                } else if (NORMAL.equals(cssFontWeight) || "lighter".equals(cssFontWeight)) {
                    modifiers = modifiers & (ALL_FONT_MODIFIERS_MASK ^ MStyle.FONT_BOLD);
                } else {
                    try {
                        final int value = Integer.valueOf(cssFontWeight);
                        if (value >= CSS_FONT_WEIGHT_BOLD_THRESHOLD) {
                            modifiers = modifiers | MStyle.FONT_BOLD;
                        } else {
                            modifiers = modifiers & (ALL_FONT_MODIFIERS_MASK ^ MStyle.FONT_BOLD);
                        }
                        // CHECKSTYLE:OFF
                    } catch (Exception e) {
                        // CHECKSTYLE:ON
                        // nothing to do here
                    }
                }
            }
        }

        final List<String> cssFontFamilies = cssProperties.get(CSS_FONT_FAMILY);
        if (cssFontFamilies != null) {
            for (String cssFontFamily : cssFontFamilies) {
                final String[] family = cssFontFamily.split("\\s*,\\s*");
                if (family.length >= 1) {
                    mStyle.setFontName(family[0].trim());
                }
            }
        }

        final List<String> cssFontSizes = cssProperties.get(CSS_FONT_SIZE);
        if (cssFontSizes != null) {
            for (String cssFontSize : cssFontSizes) {
                final int fontSize = fontSizeToPoint(cssFontSize);
                if (fontSize != -1) {
                    mStyle.setFontSize(fontSize);
                }
            }
        }

        final List<String> cssFontVariants = cssProperties.get(CSS_FONT_VARIANT);
        if (cssFontVariants != null) {
            for (String cssFontVariant : cssFontVariants) {
                if ("small-caps".equals(cssFontVariant.trim())) {
                    modifiers = modifiers | MStyle.FONT_SMALL_CAPS;
                } else if (NORMAL.equals(cssFontVariant.trim())) {
                    modifiers = modifiers & (ALL_FONT_MODIFIERS_MASK ^ MStyle.FONT_SMALL_CAPS);
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
     * Sets the CSS styles to the given {@link MCell}.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param mCell
     *            the {@link MCell}
     */
    public void setStyle(Map<String, List<String>> cssProperties, MCell mCell) {
        setContainerBackgroundColor(cssProperties, mCell);
        final List<String> cssWidths = cssProperties.get(CSS_WIDTH);
        if (cssWidths != null) {
            for (String cssWidth : cssWidths) {
                setCellWidth(mCell, cssWidth);
            }
        }
        setContainerStyle(cssProperties, mCell);
    }

    /**
     * Sets the given {@link MElementContainer} {@link MElementContainer#getBackgroundColor() background color}.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param mContainer
     *            the {@link MElementContainer}
     */
    public void setContainerBackgroundColor(Map<String, List<String>> cssProperties, MElementContainer mContainer) {
        final List<String> cssBackgroundColors = cssProperties.get(CSS_BACKGROUND_COLOR);
        if (cssBackgroundColors != null) {
            for (String cssBackgroundColor : cssBackgroundColors) {
                mContainer.setBackgroundColor(htmlToColor(cssBackgroundColor));
            }
        }
        final List<String> cssBackgrounds = cssProperties.get(CSS_BACKGROUND);
        if (cssBackgrounds != null) {
            for (String cssBackground : cssBackgrounds) {
                mContainer.setBackgroundColor(htmlToColor(cssBackground));
            }
        }
    }

    /**
     * Sets the CSS styles to the given {@link MRow}.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param mRow
     *            the {@link MRow}
     */
    public void setStyle(Map<String, List<String>> cssProperties, MRow mRow) {
        final List<String> cssHeights = cssProperties.get(CSS_HEIGHT);
        if (cssHeights != null) {
            for (String cssHeight : cssHeights) {
                setRowHeight(mRow, cssHeight);
            }
        }
    }

    /**
     * Sets the CSS styles to the given {@link MElementContainer}.
     * 
     * @param cssProperties
     *            the CSS properties
     * @param mContainer
     *            the {@link MElementContainer}
     */
    private void setContainerStyle(Map<String, List<String>> cssProperties, MElementContainer mContainer) {
        final List<String> cssTextAligns = cssProperties.get(CSS_TEXT_ALIGN);
        if (cssTextAligns != null) {
            for (String cssTextAlign : cssTextAligns) {
                if ("center".equalsIgnoreCase(cssTextAlign)) {
                    mContainer.setHAlignment(HAlignment.CENTER);
                } else if ("left".equalsIgnoreCase(cssTextAlign)) {
                    mContainer.setHAlignment(HAlignment.LEFT);
                } else if ("right".equalsIgnoreCase(cssTextAlign)) {
                    mContainer.setHAlignment(HAlignment.RIGHT);
                } else if ("justify".equalsIgnoreCase(cssTextAlign)) {
                    mContainer.setHAlignment(HAlignment.BOTH);
                }
            }
        }
        final List<String> cssFloats = cssProperties.get(CSS_FLOAT);
        if (cssFloats != null) {
            for (String cssFloat : cssFloats) {
                if ("left".equalsIgnoreCase(cssFloat)) {
                    mContainer.setHAlignment(HAlignment.LEFT);
                } else if ("right".equalsIgnoreCase(cssFloat)) {
                    mContainer.setHAlignment(HAlignment.RIGHT);
                }
            }
        }
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

    /**
     * Sets the CSS styles for the given {@link MParagraph}.
     * 
     * @param context
     *            the current {@link Context}
     * @param paragraph
     *            the {@link MParagraph}
     */
    public void setStyle(Context context, MParagraph paragraph) {
        setMarginAll(context, paragraph);
        setPaddingAll(context, paragraph);
        setMarginLeft(context, paragraph);
        setMarginRight(context, paragraph);
        setMarginTop(context, paragraph);
        setMarginBottom(context, paragraph);
        setBorderStyles(context, paragraph);
        setBorders(context, paragraph);
        setContainerStyle(context.getCssProperties(), paragraph);
    }

    /**
     * Sets the {@link MBorder} of the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link MParagraph}
     */
    private void setBorderStyles(Context context, MParagraph paragraph) {
        final List<String> cssBorderStyles = context.getCssProperties().get(CSS_BORDER_STYLE);
        if (cssBorderStyles != null) {
            for (String cssBorderStyle : cssBorderStyles) {
                final String[] borderStyles = cssBorderStyle.split(REG_EXP_SPACES);
                if (borderStyles.length == 1) {
                    final Type type = getBorderType(borderStyles[0]);
                    final MBorder leftBorder = new MBorderImpl();
                    leftBorder.setType(type);
                    paragraph.setLeftBorder(leftBorder);
                    final MBorder rightBorder = new MBorderImpl();
                    rightBorder.setType(type);
                    paragraph.setRightBorder(rightBorder);
                    final MBorder topBorder = new MBorderImpl();
                    topBorder.setType(type);
                    paragraph.setTopBorder(topBorder);
                    final MBorder bottomBorder = new MBorderImpl();
                    bottomBorder.setType(type);
                    paragraph.setBottomBorder(bottomBorder);
                } else if (borderStyles.length == 4) {
                    final Type topType = getBorderType(borderStyles[0]);
                    final Type rightType = getBorderType(borderStyles[1]);
                    final Type bottomType = getBorderType(borderStyles[2]);
                    final Type leftType = getBorderType(borderStyles[3]);
                    final MBorder leftBorder = new MBorderImpl();
                    leftBorder.setType(leftType);
                    paragraph.setLeftBorder(leftBorder);
                    final MBorder rightBorder = new MBorderImpl();
                    rightBorder.setType(rightType);
                    paragraph.setRightBorder(rightBorder);
                    final MBorder topBorder = new MBorderImpl();
                    topBorder.setType(topType);
                    paragraph.setTopBorder(topBorder);
                    final MBorder bottomBorder = new MBorderImpl();
                    bottomBorder.setType(bottomType);
                    paragraph.setBottomBorder(bottomBorder);
                }
            }
        }
    }

    /**
     * Sets the {@link MBorder} of the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link MParagraph}
     */
    private void setBorders(Context context, MParagraph paragraph) {
        final List<String> cssBorders = context.getCssProperties().get(CSS_BORDER);
        if (cssBorders != null) {
            for (String cssBorder : cssBorders) {
                final String[] borders = cssBorder.split(REG_EXP_SPACES);
                if (borders.length == 1) {
                    final Type type = getBorderType(borders[0]);
                    final MBorder leftBorder = new MBorderImpl();
                    leftBorder.setType(type);
                    paragraph.setLeftBorder(leftBorder);
                    final MBorder rightBorder = new MBorderImpl();
                    rightBorder.setType(type);
                    paragraph.setRightBorder(rightBorder);
                    final MBorder topBorder = new MBorderImpl();
                    topBorder.setType(type);
                    paragraph.setTopBorder(topBorder);
                    final MBorder bottomBorder = new MBorderImpl();
                    bottomBorder.setType(type);
                    paragraph.setBottomBorder(bottomBorder);
                } else if (borders.length == 3) {
                    final int size = getPixels(borders[0]);
                    final Type type = getBorderType(borders[1]);
                    final Color color = htmlToColor(borders[2]);
                    final MBorder leftBorder = new MBorderImpl();
                    leftBorder.setSize(size);
                    leftBorder.setType(type);
                    leftBorder.setColor(color);
                    paragraph.setLeftBorder(leftBorder);
                    final MBorder rightBorder = new MBorderImpl();
                    rightBorder.setSize(size);
                    rightBorder.setType(type);
                    rightBorder.setColor(color);
                    paragraph.setRightBorder(rightBorder);
                    final MBorder topBorder = new MBorderImpl();
                    topBorder.setSize(size);
                    topBorder.setType(type);
                    topBorder.setColor(color);
                    paragraph.setTopBorder(topBorder);
                    final MBorder bottomBorder = new MBorderImpl();
                    bottomBorder.setSize(size);
                    bottomBorder.setType(type);
                    bottomBorder.setColor(color);
                    paragraph.setBottomBorder(bottomBorder);
                }
            }
        }
    }

    /**
     * Gets the border {@link Type} for the given CSS name.
     * 
     * @param cssName
     *            the CSS name
     * @return the border {@link Type} for the given CSS name
     */
    private Type getBorderType(String cssName) {
        final Type res;

        switch (cssName) {
            case "dotted":
                res = Type.DOTTED;
                break;

            case "dashed":
                res = Type.DASHED;
                break;

            case "solid":
                res = Type.SINGLE;
                break;

            case "double":
                res = Type.DOUBLE;
                break;

            case "groove":
                res = Type.THICK_THIN_SMALL_GAP;
                break;

            case "ridge":
                res = Type.THIN_THICK_SMALL_GAP;
                break;

            case "inset":
                res = Type.INSET;
                break;

            case "outset":
                res = Type.OUTSET;
                break;

            case "none":
            case "hidden":
            default:
                res = Type.NONE;
                break;
        }

        return res;
    }

    /**
     * Sets all margins for the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link ParagraphAlignment}
     */
    private void setMarginAll(Context context, MParagraph paragraph) {
        final List<String> cssMargins = context.getCssProperties().get(CSS_MARGIN);
        if (cssMargins != null) {
            for (String cssMargin : cssMargins) {
                final String[] margins = cssMargin.split(REG_EXP_SPACES);
                final int marginLeft;
                final int marginRight;
                final int marginTop;
                final int marginBottom;
                if (margins.length == 1) {
                    if ("auto".equals(margins[0])) {
                        paragraph.setHAlignment(HAlignment.CENTER);
                        marginLeft = -1;
                        marginRight = -1;
                        marginTop = -1;
                        marginBottom = -1;
                    } else {
                        final int value = getPixels(margins[0]);
                        marginLeft = value;
                        marginRight = value;
                        marginTop = value;
                        marginBottom = value;
                    }
                } else if (margins.length == 2) {
                    final int topBottom = getPixels(margins[0]);
                    final int leftRight = getPixels(margins[1]);
                    marginLeft = leftRight;
                    marginRight = leftRight;
                    marginTop = topBottom;
                    marginBottom = topBottom;
                } else if (margins.length == 3) {
                    final int top = getPixels(margins[0]);
                    final int leftRight = getPixels(margins[1]);
                    final int bottom = getPixels(margins[2]);
                    marginLeft = leftRight;
                    marginRight = leftRight;
                    marginTop = top;
                    marginBottom = bottom;
                } else if (margins.length == 4) {
                    final int top = getPixels(margins[0]);
                    final int right = getPixels(margins[1]);
                    final int bottom = getPixels(margins[2]);
                    final int left = getPixels(margins[3]);
                    marginLeft = left;
                    marginRight = right;
                    marginTop = top;
                    marginBottom = bottom;
                } else {
                    marginLeft = -1;
                    marginRight = -1;
                    marginTop = -1;
                    marginBottom = -1;
                }
                if (marginLeft != -1) {
                    context.replaceLastDefaultMarginLeft(marginLeft);
                }
                if (marginRight != -1) {
                    context.replaceLastDefaultMarginRight(marginRight);
                }
                if (marginTop != -1) {
                    context.replaceLastDefaultMarginTop(marginTop);
                }
                if (marginBottom != -1) {
                    context.replaceLastDefaultMarginBottom(marginBottom);
                }
            }
            final Integer marginLeft = context.getMarginLeft();
            if (marginLeft != null) {
                paragraph.setMarginLeft(marginLeft);
            }
            final Integer marginRight = context.getMarginRight();
            if (marginRight != null) {
                paragraph.setMarginRight(marginRight);
            }
            final Integer marginTop = context.getMarginTop();
            if (marginTop != null) {
                paragraph.setMarginTop(marginTop);
            }
            final Integer marginBottom = context.getMarginBottom();
            if (marginBottom != null) {
                paragraph.setMarginBottom(marginBottom);
            }
        }
    }

    /**
     * Sets all paddings for the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link ParagraphAlignment}
     */
    private void setPaddingAll(Context context, MParagraph paragraph) {

        final List<String> cssPaddings = context.getCssProperties().get(CSS_PADDING);
        if (cssPaddings != null) {
            for (String cssPadding : cssPaddings) {
                final String[] paddings = cssPadding.split(REG_EXP_SPACES);
                final int paddingLeft;
                final int paddingRight;
                final int paddingTop;
                final int paddingBottom;
                if (paddings.length == 1) {
                    final int value = getPixels(paddings[0]);
                    paddingLeft = value;
                    paddingRight = value;
                    paddingTop = value;
                    paddingBottom = value;
                } else if (paddings.length == 2) {
                    final int topBottom = getPixels(paddings[0]);
                    final int leftRight = getPixels(paddings[1]);
                    paddingLeft = leftRight;
                    paddingRight = leftRight;
                    paddingTop = topBottom;
                    paddingBottom = topBottom;
                } else if (paddings.length == 3) {
                    final int top = getPixels(paddings[0]);
                    final int leftRight = getPixels(paddings[1]);
                    final int bottom = getPixels(paddings[2]);
                    paddingLeft = leftRight;
                    paddingRight = leftRight;
                    paddingTop = top;
                    paddingBottom = bottom;
                } else if (paddings.length == 4) {
                    final int top = getPixels(paddings[0]);
                    final int right = getPixels(paddings[1]);
                    final int bottom = getPixels(paddings[2]);
                    final int left = getPixels(paddings[3]);
                    paddingLeft = left;
                    paddingRight = right;
                    paddingTop = top;
                    paddingBottom = bottom;
                } else {
                    paddingLeft = -1;
                    paddingRight = -1;
                    paddingTop = -1;
                    paddingBottom = -1;
                }
                if (paddingLeft != -1) {
                    context.replaceLastDefaultMarginLeft(paddingLeft);
                }
                if (paddingRight != -1) {
                    context.replaceLastDefaultMarginRight(paddingRight);
                }
                if (paddingTop != -1) {
                    context.replaceLastDefaultMarginTop(paddingTop);
                }
                if (paddingBottom != -1) {
                    context.replaceLastDefaultMarginBottom(paddingBottom);
                }
            }
            final Integer marginLeft = context.getMarginLeft();
            if (marginLeft != null) {
                paragraph.setMarginLeft(marginLeft);
            }
            final Integer marginRight = context.getMarginRight();
            if (marginRight != null) {
                paragraph.setMarginRight(marginRight);
            }
            final Integer marginTop = context.getMarginTop();
            if (marginTop != null) {
                paragraph.setMarginTop(marginTop);
            }
            final Integer marginBottom = context.getMarginBottom();
            if (marginBottom != null) {
                paragraph.setMarginBottom(marginBottom);
            }
        }
    }

    /**
     * Sets the left margin for the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link ParagraphAlignment}
     */
    private void setMarginLeft(Context context, MParagraph paragraph) {
        final List<String> cssMarginLefts = context.getCssProperties().get(CSS_MARGIN_LEFT);
        if (cssMarginLefts != null) {
            int value = -1;
            for (String cssMarginLeft : cssMarginLefts) {
                value = getPixels(cssMarginLeft);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginLeft(value);
            }
        }
        final Integer marginLeft = context.getMarginLeft();
        if (marginLeft != null) {
            paragraph.setMarginLeft(marginLeft);
        }
        final List<String> cssPaddingLefts = context.getCssProperties().get(CSS_PADDING_LEFT);
        if (cssPaddingLefts != null) {
            int value = -1;
            for (String cssMarginLeft : cssPaddingLefts) {
                value = getPixels(cssMarginLeft);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginLeft(value);
            }
        }
        final Integer paddingLeft = context.getMarginLeft();
        if (paddingLeft != null) {
            paragraph.setMarginLeft(paddingLeft);
        }
    }

    /**
     * Sets the right margin for the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link ParagraphAlignment}
     */
    private void setMarginRight(Context context, MParagraph paragraph) {
        final List<String> cssMarginRights = context.getCssProperties().get(CSS_MARGIN_RIGHT);
        if (cssMarginRights != null) {
            int value = -1;
            for (String cssMarginRight : cssMarginRights) {
                value = getPixels(cssMarginRight);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginRight(value);
            }
        }
        final Integer marginRight = context.getMarginRight();
        if (marginRight != null) {
            paragraph.setMarginRight(marginRight);
        }
        final List<String> cssPaddingRights = context.getCssProperties().get(CSS_PADDING_RIGHT);
        if (cssPaddingRights != null) {
            int value = -1;
            for (String cssMarginRight : cssPaddingRights) {
                value = getPixels(cssMarginRight);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginRight(value);
            }
        }
        final Integer paddingRight = context.getMarginRight();
        if (paddingRight != null) {
            paragraph.setMarginRight(paddingRight);
        }
    }

    /**
     * Sets the top margin for the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link ParagraphAlignment}
     */
    private void setMarginTop(Context context, MParagraph paragraph) {
        final List<String> cssMarginTops = context.getCssProperties().get(CSS_MARGIN_TOP);
        if (cssMarginTops != null) {
            int value = -1;
            for (String cssMarginTop : cssMarginTops) {
                value = getPixels(cssMarginTop);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginTop(value);
            }
        }
        final Integer marginTop = context.getMarginTop();
        if (marginTop != null) {
            paragraph.setMarginTop(marginTop);
        }
        final List<String> cssPaddingTops = context.getCssProperties().get(CSS_PADDING_TOP);
        if (cssPaddingTops != null) {
            int value = -1;
            for (String cssMarginTop : cssPaddingTops) {
                value = getPixels(cssMarginTop);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginTop(value);
            }
        }
        final Integer paddingTop = context.getMarginTop();
        if (paddingTop != null) {
            paragraph.setMarginTop(paddingTop);
        }
    }

    /**
     * Sets the bottom margin for the given {@link MParagraph} in the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param paragraph
     *            the {@link ParagraphAlignment}
     */
    private void setMarginBottom(Context context, MParagraph paragraph) {
        final List<String> cssMarginBottoms = context.getCssProperties().get(CSS_MARGIN_BOTTOM);
        if (cssMarginBottoms != null) {
            int value = -1;
            for (String cssMarginBottom : cssMarginBottoms) {
                value = getPixels(cssMarginBottom);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginBottom(value);
            }
        }
        final Integer marginBottom = context.getMarginBottom();
        if (marginBottom != null) {
            paragraph.setMarginBottom(marginBottom);
        }
        final List<String> cssPaddingBottoms = context.getCssProperties().get(CSS_PADDING_BOTTOM);
        if (cssPaddingBottoms != null) {
            int value = -1;
            for (String cssMarginBottom : cssPaddingBottoms) {
                value = getPixels(cssMarginBottom);
            }
            if (value != -1) {
                context.replaceLastDefaultMarginBottom(value);
            }
        }
        final Integer paddingBottom = context.getMarginBottom();
        if (paddingBottom != null) {
            paragraph.setMarginBottom(paddingBottom);
        }
    }

}
