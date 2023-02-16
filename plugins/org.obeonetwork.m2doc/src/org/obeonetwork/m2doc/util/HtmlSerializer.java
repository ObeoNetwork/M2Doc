/*******************************************************************************
 *  Copyright (c) 2020, 2023 Obeo. 
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
import java.math.BigInteger;
import java.util.Base64;
import java.util.Collection;

import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.Units;
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
import org.obeonetwork.m2doc.element.MElementContainer.HAlignment;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MCell.VAlignment;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;

/**
 * HTML serializer.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class HtmlSerializer {

    /**
     * Baseline.
     */
    private static final String BASELINE = "baseline";

    /**
     * Top.
     */
    private static final String TOP = "top";

    /**
     * Bottom.
     */
    private static final String BOTTOM = "bottom";

    /**
     * Left.
     */
    private static final String LEFT = "left";

    /**
     * Right.
     */
    private static final String RIGHT = "right";

    /**
     * Center.
     */
    private static final String CENTER = "center";

    /**
     * Justify.
     */
    private static final String JUSTIFY = "justify";

    /**
     * The background color CSS format.
     */
    private static final String BACKGROUND_COLOR_FORMAT = "background-color:#%s;";

    /**
     * The color CSS format.
     */
    private static final String COLOR_FORMAT = "color:#%s;";

    /**
     * The color CSS format.
     */
    private static final String COLOR_RGB_FOMRAT = "color:#%02X%02X%02X;";

    /**
     * The vertical align CSS format.
     */
    private static final String VERTICAL_ALIGN_FORMAT = "vertical-align:%s;";

    /**
     * The text align CSS format.
     */
    private static final String TEXT_ALIGN_FORMAT = "text-align:%s;";

    /**
     * The table border collapse CSS.
     */
    private static final String BORDER_COLLAPSE_COLLAPSE = "border-collapse: collapse;";

    /**
     * The font size CSS format.
     */
    private static final String FONT_SIZE_FORMAT = "font-size:%spx;";

    /**
     * The background color CSS format.
     */
    private static final String BACKGROUND_COLOR_RGB_FORMAT = "background-color:#%02X%02X%02X;";

    /**
     * The table of content marker.
     */
    private static final String TABLE_OF_CONTENT = "[TABLE OF CONTENT]";

    /**
     * The table border CSS.
     */
    private static final String BORDER_1PX_SOLID_BLACK = "border: 1px solid black;";

    /**
     * HTML br.
     */
    private static final String BR = "<br>";

    /**
     * HTML link format string.
     */
    private static final String LINK_FORMAT = "<a href=\"%s\">%s</a>";

    /**
     * HTML paragraph format string.
     */
    private static final String PARAGRAPH_FORMAT = "<p>%s</p>";

    /**
     * HTML paragraph format string.
     */
    private static final String PARAGRAPH_STYLED_FORMAT = "<p style=\"%s\">%s</p>";

    /**
     * HTML image format string.
     */
    private static final String IMAGE_FORMAT = "<img width=%s height=%s src=\"data:%s;base64, %s\">";

    /**
     * HTML strong format string.
     */
    private static final String STRONG_FORMAT = "<strong>%s</strong>";

    /**
     * HTML italic format string.
     */
    private static final String ITALIC_FORMAT = "<i>%s</i>";

    /**
     * HTML strikethrough format string.
     */
    private static final String STRIKETHROUGH_FORMAT = "<del>%s</del>";

    /**
     * HTML underline format string.
     */
    private static final String UNDERLINE_FORMAT = "<u>%s</u>";

    /**
     * HTML table format string.
     */
    private static final String TABLE_STYLED_FORMAT = "<table style=\"%s\">%s</table>";

    /**
     * HTML td format string.
     */
    private static final String TD_STYLED_FORMAT = "<td style=\"%s\">%s</td>";

    /**
     * HTML tr format string.
     */
    private static final String TR_STYLED_FORMAT = "<tr style=\"%s\">%s</tr>";

    /**
     * HTML spawn format string.
     */
    private static final String SPAWN_STYLED_FORMAT = "<spawn style=\"%s\">%s</spawn>";

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
            res = serialize(object);
        } else if (object instanceof Collection) {
            final StringBuilder builder = new StringBuilder();
            for (Object child : (Collection<?>) object) {
                builder.append(serialize(child));
            }
            res = builder.toString();
        } else if (object != null) {
            res = String.format(PARAGRAPH_FORMAT, object.toString());
        } else {
            res = String.format(PARAGRAPH_FORMAT, "null");
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
            res = serialize((MParagraph) element);
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
     * Serialize the given {@link MParagraph} into HTML.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @return the given {@link MParagraph} into HTML
     */
    private String serialize(MParagraph paragraph) {
        final String res;
        final String content = serialize(paragraph.getContents());
        if (paragraph.getHAlignment() != null) {
            final String style = String.format(TEXT_ALIGN_FORMAT, getHTMLAlignment(paragraph.getHAlignment()));
            res = String.format(PARAGRAPH_STYLED_FORMAT, style, content);
        } else {
            res = String.format(PARAGRAPH_FORMAT, content);
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
                res = TABLE_OF_CONTENT;
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
            builder.append(String.format(IMAGE_FORMAT, image.getWidth(), image.getHeight(),
                    "image/" + image.getType().name().toLowerCase(),
                    new String(Base64.getEncoder().encode(os.toByteArray()))));
        } catch (IOException e) {
            builder.append(String.format(PARAGRAPH_FORMAT, e.getMessage()));
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
        final StringBuilder res = new StringBuilder();
        for (MRow row : table.getRows()) {
            final StringBuilder rowBuilder = new StringBuilder();
            for (MCell cell : row.getCells()) {
                // TODO merge cell
                String style = BORDER_1PX_SOLID_BLACK;
                if (cell.getBackgroundColor() != null) {
                    final Color color = cell.getBackgroundColor();
                    style += String.format(BACKGROUND_COLOR_RGB_FORMAT, color.getRed(), color.getGreen(),
                            color.getBlue());
                }
                if (cell.getHAlignment() != null) {
                    style += String.format(TEXT_ALIGN_FORMAT, getHTMLAlignment(cell.getHAlignment()));
                }
                if (cell.getVAlignment() != null) {
                    style += String.format(VERTICAL_ALIGN_FORMAT, getHTMLAlignment(cell.getVAlignment()));
                }
                rowBuilder.append(String.format(TD_STYLED_FORMAT, style, serialize(cell.getContents())));
            }
            res.append(String.format(TR_STYLED_FORMAT, BORDER_1PX_SOLID_BLACK, rowBuilder.toString()));
        }

        return String.format(TABLE_STYLED_FORMAT, BORDER_1PX_SOLID_BLACK + BORDER_COLLAPSE_COLLAPSE, res.toString());
    }

    /**
     * Gets the HTML alignment for the given {@link HAlignment}.
     * 
     * @param alignment
     *            the {@link HAlignment}
     * @return the HTML alignment for the given {@link HAlignment}
     */
    private String getHTMLAlignment(HAlignment alignment) {
        final String res;

        switch (alignment) {
            case CENTER:
                res = CENTER;
                break;

            case BOTH:
            case DISTRIBUTE:
                res = JUSTIFY;
                break;

            case RIGHT:
                res = RIGHT;
                break;

            case LEFT:
            default:
                res = LEFT;
                break;
        }

        return res;
    }

    /**
     * Gets the HTML alignment for the given {@link VAlignment}.
     * 
     * @param alignment
     *            the {@link VAlignment}
     * @return the HTML alignment for the given {@link VAlignment}
     */
    private String getHTMLAlignment(VAlignment alignment) {
        final String res;

        switch (alignment) {
            case BOTTOM:
                res = BOTTOM;
                break;

            case TOP:
                res = TOP;
                break;

            case CENTER:
            case BOTH:
            default:
                res = BASELINE;
                break;
        }

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
        final String formatedText = String.format(getStyleFormat(text.getStyle()), escapeHTML(text.getText()));
        if (text instanceof MHyperLink) {
            res = String.format(LINK_FORMAT, ((MHyperLink) text).getUrl(), formatedText);
        } else {
            res = formatedText;
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
                    res = String.format(STRONG_FORMAT, res);
                }
                if ((style.getFontModifiers() & MStyle.FONT_ITALIC) != 0) {
                    res = String.format(ITALIC_FORMAT, res);
                }
                if ((style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH) != 0) {
                    res = String.format(STRIKETHROUGH_FORMAT, res);
                }
                if ((style.getFontModifiers() & MStyle.FONT_UNDERLINE) != 0) {
                    res = String.format(UNDERLINE_FORMAT, res);
                }
            }

            String spawnStyle = "";
            if (style.getFontSize() != -1) {
                spawnStyle += String.format(FONT_SIZE_FORMAT, style.getFontSize());
            }
            if (style.getBackgroundColor() != null) {
                final Color color = style.getBackgroundColor();
                spawnStyle += String.format(BACKGROUND_COLOR_RGB_FORMAT, color.getRed(), color.getGreen(),
                        color.getBlue());
            }
            if (style.getForegroundColor() != null) {
                final Color color = style.getForegroundColor();
                spawnStyle += String.format(COLOR_RGB_FOMRAT, color.getRed(), color.getGreen(), color.getBlue());
            }
            if (!spawnStyle.isEmpty()) {
                res = String.format(SPAWN_STYLED_FORMAT, spawnStyle, res);
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
        final String res;

        // TODO numbering
        String paragraphStyle = "";
        final XWPFParagraph localParagraph = new XWPFParagraph(paragraph.getCTP(), paragraph.getBody());
        if (localParagraph.getStyleID() != null) {
            final XWPFStyle style = localParagraph.getBody().getXWPFDocument().getStyles()
                    .getStyle(localParagraph.getStyleID());
            final CTStyle ctStyle = style.getCTStyle();
            if (ctStyle.isSetRPr()) {
                final CTRPr rPr = ctStyle.getRPr();
                for (CTHpsMeasure mesure : rPr.getSzCsList()) {
                    double size = ((BigInteger) mesure.getVal()).doubleValue();
                    paragraphStyle += String.format(FONT_SIZE_FORMAT, size);
                }
                for (CTColor ctColor : rPr.getColorList()) {
                    final String color = ctColor.xgetVal().getStringValue();
                    paragraphStyle += String.format(COLOR_FORMAT, color);
                }
            }
        }
        if (localParagraph.getAlignment() != null) {
            paragraphStyle += String.format(TEXT_ALIGN_FORMAT, getHTMLAlignment(localParagraph.getAlignment()));
        }
        final StringBuilder contentBuilder = new StringBuilder();
        for (XWPFRun run : localParagraph.getRuns()) {
            final String escapedText = escapeHTML(run.getText(0));
            final String formatedText = String.format(getStyleFormat(run), escapedText);
            for (XWPFPicture picture : run.getEmbeddedPictures()) {
                final int width = Units
                        .pointsToPixel(Units.toPoints(picture.getCTPicture().getSpPr().getXfrm().getExt().getCx()));
                final int height = Units
                        .pointsToPixel(Units.toPoints(picture.getCTPicture().getSpPr().getXfrm().getExt().getCy()));
                contentBuilder.append(String.format(IMAGE_FORMAT, width, height,
                        picture.getPictureData().getPackagePart().getContentType(),
                        new String(Base64.getEncoder().encode(picture.getPictureData().getData()))));
            }
            if (run instanceof XWPFHyperlinkRun) {
                final String id = ((XWPFHyperlinkRun) run).getCTHyperlink().getId();
                final PackageRelationship relation = localParagraph.getBody().getPart().getPackagePart()
                        .getRelationship(id);
                final String url = relation.getSourceURI().toString();
                contentBuilder.append(String.format(LINK_FORMAT, escapeHTML(url), formatedText));
            } else {
                contentBuilder.append(formatedText);
            }
        }
        if (!paragraphStyle.isEmpty()) {
            res = String.format(PARAGRAPH_STYLED_FORMAT, paragraphStyle, contentBuilder.toString());
        } else {
            res = String.format(PARAGRAPH_FORMAT, contentBuilder.toString());
        }

        return res;
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
            res = String.format(STRONG_FORMAT, res);
        }
        if (run.isItalic()) {
            res = String.format(ITALIC_FORMAT, res);
        }
        if (run.isStrikeThrough()) {
            res = String.format(STRIKETHROUGH_FORMAT, res);
        }
        if (run.getUnderline() != null && run.getUnderline() != UnderlinePatterns.NONE) {
            res = String.format(UNDERLINE_FORMAT, res);
        }

        String spawnStyle = "";
        if (run.getFontSize() != -1) {
            spawnStyle += String.format(FONT_SIZE_FORMAT, run.getFontSize());
        }
        if (run.getCTR().getRPr() != null) {
            // TODO double check this.
            for (CTShd ctShd : run.getCTR().getRPr().getShdList()) {
                if (ctShd.getFill() != null) {
                    spawnStyle += String.format(BACKGROUND_COLOR_FORMAT, ctShd.getFill());
                }
            }
        }
        if (run.getColor() != null) {
            // TODO double check this.
            spawnStyle += String.format(COLOR_FORMAT, run.getColor());
        }
        if (!spawnStyle.isEmpty()) {
            res = String.format(SPAWN_STYLED_FORMAT, spawnStyle, res);
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

        if (str != null) {
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
            case CENTER:
                res = CENTER;
                break;

            case BOTH:
            case DISTRIBUTE:
                res = JUSTIFY;
                break;

            case RIGHT:
                res = RIGHT;
                break;

            case LEFT:
            default:
                res = LEFT;
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

        final XWPFTable localTable = new XWPFTable(table.getCTTbl(), table.getBody());
        for (XWPFTableRow row : localTable.getRows()) {
            final StringBuilder rowBuilder = new StringBuilder();
            for (XWPFTableCell cell : row.getTableCells()) {
                // TODO merge cell
                String style = BORDER_1PX_SOLID_BLACK;
                if (cell.getColor() != null) {
                    style += String.format(BACKGROUND_COLOR_FORMAT, cell.getColor());
                }
                if (cell.getVerticalAlignment() != null) {
                    style += String.format(VERTICAL_ALIGN_FORMAT, getHTMLAlignment(cell.getVerticalAlignment()));
                }
                rowBuilder.append(String.format(TD_STYLED_FORMAT, style, serialize(cell)));
            }
            res.append(String.format(TR_STYLED_FORMAT, BORDER_1PX_SOLID_BLACK, rowBuilder.toString()));
        }

        return String.format(TABLE_STYLED_FORMAT, BORDER_1PX_SOLID_BLACK + BORDER_COLLAPSE_COLLAPSE, res.toString());
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
                res = BOTTOM;
                break;

            case TOP:
                res = TOP;
                break;

            case CENTER:
            case BOTH:
            default:
                res = BASELINE;
                break;
        }

        return res;
    }

}
