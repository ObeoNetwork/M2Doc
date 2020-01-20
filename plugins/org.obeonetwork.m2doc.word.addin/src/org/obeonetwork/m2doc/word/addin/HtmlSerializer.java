/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.word.addin;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.StringJoiner;

import org.apache.poi.util.IOUtils;
import org.obeonetwork.m2doc.element.MBookmark;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MText;

/**
 * HTML serializer.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class HtmlSerializer {

    /**
     * HTML br.
     */
    private static final String BR = "<br>";

    /**
     * HTML link format string.
     */
    private static final String LINK_FORMAT = "<a href=\"%s\">%s</a>";

    /**
     * Serialize the given {@link Object} into HTML.
     * 
     * @param object
     *            the {@link Object}
     * @return the given {@link Object} into HTML
     */
    public String serialize(Object object) {
        final String res;

        if (object instanceof MElement) {
            res = serialize((MElement) object);
        } else if (object != null) {
            res = "<p>" + object.toString() + "</p>";
        } else {
            res = "<p>null</p>";
        }

        return res;
    }

    /**
     * Serialize the given {@link MElement} into HTML.
     * 
     * @param element
     *            the {@link MElement}
     * @return the given {@link MElement} into HTML
     */
    public String serialize(MElement element) {
        final String res;

        if (element instanceof MList) {
            final StringBuilder builder = new StringBuilder();
            for (MElement child : (MList) element) {
                builder.append(serialize(child));
            }
            res = builder.toString();
        } else if (element instanceof MPagination) {
            res = serialize((MPagination) element);
        } else if (element instanceof MBookmark) {
            final MBookmark bookmark = (MBookmark) element;
            res = String.format(LINK_FORMAT, bookmark.getText(), bookmark.getId());
        } else if (element instanceof MImage) {
            final MImage image = (MImage) element;
            res = serialize(image);
        } else if (element instanceof MParagraph) {
            res = "<p>" + serialize(((MParagraph) element).getContents()) + "</p>";
        } else if (element instanceof MTable) {
            final MTable table = (MTable) element;
            res = serialize(table);
        } else if (element instanceof MText) {
            final MText text = (MText) element;
            res = serialize(text);
        } else if (element != null) {
            res = element.toString();
        } else {
            res = "";
        }

        return res;
    }

    /**
     * Serializes the given {@link MPagination} into HTML.
     * 
     * @param pagination
     *            the {@link MPagination}
     * @return the given {@link MPagination} into HTML
     */
    private String serialize(MPagination pagination) {
        final String res;
        switch (pagination) {
            case ligneBreak:
                res = BR;
                break;
            case newColumn:
                res = BR;
                break;
            case newPage:
                res = BR;
                break;
            case newParagraph:
                res = BR;
                break;
            case newTableOfContent:
                res = "[TABLE OF CONTENT]";
                break;
            case newTextWrapping:
                res = BR;
                break;

            default:
                res = "";
                break;
        }
        return res;
    }

    /**
     * Serializes the given {@link MImage} into HTML.
     * 
     * @param image
     *            the {@link MImage}
     * @return the given {@link MImage} into HTML
     */
    private String serialize(final MImage image) {
        final String res;
        final StringBuilder builder = new StringBuilder();
        builder.append("<img src=\"data:image/" + image.getType().name().toLowerCase() + ";base64, ");
        try (InputStream is = image.getInputStream(); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            IOUtils.copy(is, os);
            builder.append(new String(Base64.getEncoder().encode(os.toByteArray())));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        builder.append("\"/>");
        res = builder.toString();
        return res;
    }

    /**
     * Serializes the given {@link MTable} into HTML.
     * 
     * @param table
     *            the {@link MTable}
     * @return the given {@link MTable} into HTML
     */
    private String serialize(final MTable table) {
        final String res;
        final StringBuilder builder = new StringBuilder();
        builder.append("<table>");
        for (MRow row : table.getRows()) {
            builder.append("<tr>");
            for (MCell cell : row.getCells()) {
                builder.append("<td>");
                builder.append(serialize(cell.getContents()));
                builder.append("<td>");
            }
            builder.append("</tr>");
        }
        builder.append("</table>");
        res = builder.toString();
        return res;
    }

    /**
     * Serializes the given {@link MText} into HTML.
     * 
     * @param text
     *            the {@link MText}
     * @return the given {@link MText} into HTML
     */
    private String serialize(final MText text) {
        final String res;
        if (text instanceof MHyperLink) {
            res = "<a href=\"" + ((MHyperLink) text).getUrl() + "\">"
                + String.format(getStyleFormat(text.getStyle()), text.getText()) + "</a>";
        } else {
            res = String.format(getStyleFormat(text.getStyle()), text.getText());
        }
        return res;
    }

    /**
     * Gets the string format for the given {@link MStyle}.
     * 
     * @param style
     *            the {@link MStyle}
     * @return the string format for the given {@link MStyle}
     */
    private String getStyleFormat(MStyle style) {
        String res = "%s";

        if (style != null) {
            if ((style.getFontModifiers() & MStyle.FONT_BOLD) != 0) {
                res = "<strong>" + res + "</strong>";
            }
            if ((style.getFontModifiers() & MStyle.FONT_ITALIC) != 0) {
                res = "<i>" + res + "</i>";
            }
            if ((style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH) != 0) {
                res = "<del>" + res + "</del>";
            }
            if ((style.getFontModifiers() & MStyle.FONT_UNDERLINE) != 0) {
                res = "<u>" + res + "</u>";
            }

            final StringJoiner joiner = new StringJoiner(";");
            if (style.getBackgroundColor() != null) {
                final Color color = style.getBackgroundColor();
                joiner.add("background-color:#" + Integer.toHexString(color.getRed())
                    + Integer.toHexString(color.getGreen()) + Integer.toHexString(color.getBlue()));
            }
            if (style.getForegroundColor() != null) {
                final Color color = style.getForegroundColor();
                joiner.add("color:#" + Integer.toHexString(color.getRed()) + Integer.toHexString(color.getGreen())
                    + Integer.toHexString(color.getBlue()));
            }
            final String spawnStyle = joiner.toString();
            if (!spawnStyle.isEmpty()) {
                res = "<spawn style=\"" + spawnStyle + "\">" + res + "</spawn>";
            }
        }

        return res;
    }

}
