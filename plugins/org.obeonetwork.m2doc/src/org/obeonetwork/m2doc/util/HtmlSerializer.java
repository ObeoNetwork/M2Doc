/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.util;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Collection;

import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
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
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;

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
        } else if (object instanceof GenerationResult) {
            res = serialize((GenerationResult) object);
        } else if (object instanceof IBody) {
            res = serialize((IBody) object);
        } else if (object instanceof Collection) {
            final StringBuilder builder = new StringBuilder();
            for (Object child : (Collection<?>) object) {
                builder.append(serialize(child));
            }
            res = builder.toString();
        } else if (object != null) {
            res = paragraph(object.toString());
        } else {
            res = "<p>null</p>";
        }

        return res;
    }

    /**
     * Gets an HTML paragraph containing the given {@link String}.
     * 
     * @param text
     *            the {@link String}
     * @return an HTML paragraph containing the given {@link String}
     */
    private String paragraph(String text) {
        return "<p>" + text + "</p>";
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
            res = paragraph(serialize(((MParagraph) element).getContents()));
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
        try (InputStream is = image.getInputStream(); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            IOUtils.copy(is, os);
            builder.append("<img src=\"data:image/" + image.getType().name().toLowerCase() + ";base64, ");
            builder.append(new String(Base64.getEncoder().encode(os.toByteArray())));
            builder.append("\"/>");
        } catch (IOException e) {
            builder.append(paragraph(e.getMessage()));
        }
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
            if (style.getFontModifiers() != -1) {
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
            }

            String spawnStyle = "";
            if (style.getFontSize() != -1) {
                spawnStyle += String.format("font-size:%spx;", style.getFontSize());
            }
            if (style.getBackgroundColor() != null) {
                final Color color = style.getBackgroundColor();
                spawnStyle += String.format("background-color:#%02X%02X%02X;", color.getRed(), color.getGreen(),
                        color.getBlue());
            }
            if (style.getForegroundColor() != null) {
                final Color color = style.getForegroundColor();
                spawnStyle += String.format("color:#%02X%02X%02X;", color.getRed(), color.getGreen(), color.getBlue());
            }
            if (!spawnStyle.isEmpty()) {
                res = "<spawn style=\"" + spawnStyle + "\">" + res + "</spawn>";
            }
        }

        return res;
    }

    /**
     * Serialize the given {@link GenerationResult} into HTML.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @return the given {@link GenerationResult} into HTML
     */
    private String serialize(GenerationResult result) {
        // TODO messages ?
        return serialize(result.getBody());
    }

    /**
     * Serialize the given {@link IBody} into HTML.
     * 
     * @param body
     *            the {@link IBody}
     * @return the given {@link IBody} into HTML
     */
    public String serialize(IBody body) {
        final StringBuilder res = new StringBuilder();

        for (IBodyElement element : body.getBodyElements()) {
            res.append(serialize(element));
        }

        return res.toString();
    }

    /**
     * Serialize the given {@link IBodyElement} into HTML.
     * 
     * @param element
     *            the {@link IBodyElement}
     * @return the given {@link IBodyElement} into HTML
     */
    public String serialize(IBodyElement element) {
        final String res;

        if (element instanceof XWPFParagraph) {
            res = serialize((XWPFParagraph) element);
        } else if (element instanceof XWPFTable) {
            res = serialize((XWPFTable) element);
        } else {
            res = "";
        }

        return res;
    }

    /**
     * Serialize the given {@link XWPFParagraph} into HTML.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @return the given {@link XWPFParagraph} into HTML
     */
    private String serialize(XWPFParagraph paragraph) {
        final StringBuilder res = new StringBuilder();

        // TODO numbering
        String paragraphStyle = "";
        final XWPFParagraph localParagraph = new XWPFParagraph(paragraph.getCTP(), paragraph.getBody());
        if (localParagraph.getStyleID() != null) {
            final XWPFStyle style = localParagraph.getBody().getXWPFDocument().getStyles()
                    .getStyle(localParagraph.getStyleID());
            final CTStyle ctStyle = style.getCTStyle();
            if (ctStyle.isSetRPr()) {
                final CTRPr rPr = ctStyle.getRPr();
                if (rPr.isSetSz()) {
                    double size = rPr.getSz().getVal().doubleValue();
                    paragraphStyle += String.format("font-size:%spx;", size);
                }
                if (rPr.isSetColor()) {
                    final String color = rPr.getColor().xgetVal().getStringValue();
                    paragraphStyle += String.format("color:#%s;", color);
                }
            }
        }
        if (localParagraph.getAlignment() != null) {
            paragraphStyle += String.format("text-align:#%s;", getHTMLAlignment(localParagraph.getAlignment()));
        }
        if (!paragraphStyle.isEmpty()) {
            res.append("<p style=\"" + paragraphStyle + "\">");
        } else {
            res.append("<p>");
        }
        for (XWPFRun run : localParagraph.getRuns()) {
            final String escapedText = escapeHTML(run.getText(0));
            final String formatedText = String.format(getStyleFormat(run), escapedText);
            for (XWPFPicture picture : run.getEmbeddedPictures()) {
                res.append(
                        "<img src=\"data:" + picture.getPictureData().getPackagePart().getContentType() + ";base64, ");
                res.append(new String(Base64.getEncoder().encode(picture.getPictureData().getData())));
                res.append("\"/>");
            }
            if (run instanceof XWPFHyperlinkRun) {
                final String id = ((XWPFHyperlinkRun) run).getCTHyperlink().getId();
                final PackageRelationship relation = localParagraph.getBody().getPart().getPackagePart()
                        .getRelationship(id);
                final String url = relation.getSourceURI().toString();
                res.append("<a href=\"" + escapeHTML(url) + "\">" + formatedText + "</a>");
            } else {
                res.append(formatedText);
            }
        }
        res.append("</p>");

        return res.toString();
    }

    /**
     * Gets the string format for the given {@link XWPFRun}.
     * 
     * @param run
     *            the {@link XWPFRun}
     * @return the string format for the given {@link XWPFRun}
     */
    private String getStyleFormat(XWPFRun run) {
        String res = "%s";

        if (run.isBold()) {
            res = "<strong>" + res + "</strong>";
        }
        if (run.isItalic()) {
            res = "<i>" + res + "</i>";
        }
        if (run.isStrikeThrough()) {
            res = "<del>" + res + "</del>";
        }
        if (run.getUnderline() != null && run.getUnderline() != UnderlinePatterns.NONE) {
            res = "<u>" + res + "</u>";
        }

        String spawnStyle = "";
        if (run.getFontSize() != -1) {
            spawnStyle += String.format("font-size:%spx;", run.getFontSize());
        }
        if (run.getCTR().getRPr() != null && run.getCTR().getRPr().getShd() != null
            && run.getCTR().getRPr().getShd().getFill() != null) {
            // TODO double check this.
            spawnStyle += String.format("background-color:#%s;", run.getCTR().getRPr().getShd().getFill());
        }
        if (run.getColor() != null) {
            // TODO double check this.
            spawnStyle += String.format("color:#%s;", run.getColor());
        }
        if (!spawnStyle.isEmpty()) {
            res = "<spawn style=\"" + spawnStyle + "\">" + res + "</spawn>";
        }

        return res;
    }

    /**
     * Gets an escaped HTML version of the given {@link String}.
     * 
     * @param str
     *            the {@link String} to escape
     * @return the escaped HTML version of the given {@link String}
     */
    public static String escapeHTML(String str) {
        final StringBuilder res = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            switch (c) {
                case '"':
                case '\'':
                case '<':
                case '>':
                case '&':
                    res.append("&#");
                    res.append((int) c);
                    res.append(';');
                    break;

                default:
                    res.append(c);
                    break;
            }
        }

        return res.toString();
    }

    /**
     * Gets the HTML alignment for the given {@link ParagraphAlignment}.
     * 
     * @param alignment
     *            the {@link ParagraphAlignment}
     * @return the HTML alignment for the given {@link ParagraphAlignment}
     */
    private String getHTMLAlignment(ParagraphAlignment alignment) {
        final String res;

        switch (alignment) {
            case BOTH:
                res = "justify";
                break;

            case CENTER:
                res = "center";
                break;

            case DISTRIBUTE:
                res = "justify";
                break;

            case LEFT:
                res = "left";
                break;

            case RIGHT:
                res = "right";
                break;

            default:
                res = "left";
                break;
        }

        return res;
    }

    /**
     * Serialize the given {@link XWPFTable} into HTML.
     * 
     * @param table
     *            the {@link XWPFTable}
     * @return the given {@link XWPFTable} into HTML
     */
    private String serialize(XWPFTable table) {
        final StringBuilder res = new StringBuilder();

        res.append("<table style=\"border: 1px solid black;border-collapse: collapse;\">");
        final XWPFTable localTable = new XWPFTable(table.getCTTbl(), table.getBody());
        for (XWPFTableRow row : localTable.getRows()) {
            res.append("<trstyle=\"border: 1px solid black;\">");

            for (XWPFTableCell cell : row.getTableCells()) {
                // TODO merge cell
                if (cell.getVerticalAlignment() != null) {
                    res.append("<td style=\"border: 1px solid black;vertical-align:"
                        + getHTMLAlignment(cell.getVerticalAlignment()) + ";\">");
                } else {
                    res.append("<td style=\"border: 1px solid black;\">");
                }
                res.append(serialize(cell));
                res.append("</td>");
            }
            res.append("</tr>");

        }
        res.append("</table>");

        return res.toString();
    }

    /**
     * Gets the HTML alignement for the given {@link XWPFVertAlign}.
     * 
     * @param verticalAlignment
     *            the {@link XWPFVertAlign}
     * @return the HTML alignement for the given {@link XWPFVertAlign}
     */
    private String getHTMLAlignment(XWPFVertAlign verticalAlignment) {
        final String res;

        switch (verticalAlignment) {
            case BOTTOM:
                res = "bottom";
                break;

            case TOP:
                res = "top";
                break;

            case CENTER:
            case BOTH:
            default:
                res = "baseline";
                break;
        }

        return res;
    }

}
