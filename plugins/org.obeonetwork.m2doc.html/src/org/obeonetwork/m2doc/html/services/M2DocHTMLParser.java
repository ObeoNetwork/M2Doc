/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
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
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
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
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.MHyperLinkImpl;
import org.obeonetwork.m2doc.element.impl.MImageImpl;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLevelText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumFmt;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMultiLevelType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STNumberFormat;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

/**
 * Parse HTML to {@link MElement}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocHTMLParser {

    /**
     * The indentation left.
     */
    private static final int INDENT_LEFT = 720;

    /**
     * The indentation hanging.
     */
    private static final int INDENT_HANGING = 180;

    /**
     * The type attribute.
     */
    private static final String TYPE_ATTR = "type";

    /**
     * The disc symbol for bullet list.
     */
    private static final String DISC_SYMBOL = "\uF0B7";

    /**
     * The square symbol for bullet list.
     */
    private static final String SQUARE_SYMBOL = "\uF0A7";

    /**
     * The circle symbol for bullet list.
     */
    private static final String CIRCLE_SYMBOL = "o";

    /**
     * The H6 tag font size.
     */
    private static final int H6_FONT_SIZE = 8;

    /**
     * The H5 tag font size.
     */
    private static final int H5_FONT_SIZE = 10;

    /**
     * The H4 tag font size.
     */
    private static final int H4_FONT_SIZE = 12;

    /**
     * The H3 tag font size.
     */
    private static final int H3_FONT_SIZE = 14;

    /**
     * The H2 tag font size.
     */
    private static final int H2_FONT_SIZE = 18;

    /**
     * The H1 tag font size.
     */
    private static final int H1_FONT_SIZE = 24;

    /**
     * The {@link Context} for {@link M2DocHTMLParser#walkNodeTree(MElement, MStyle, Node) tree walking}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static final class Context {

        /**
         * The current {@link MStyle}.
         */
        private final MStyle style;

        /**
         * The current numbering.
         */
        private CTAbstractNum numbering;

        /**
         * The base URI.
         */
        private final URI baseURI;

        /**
         * The current link target if any, <code>null</code> otherwise.
         */
        private URI linkTargetURI;

        /**
         * The numbering ID.
         */
        private BigInteger numberingID;

        /**
         * The current numbering level.
         */
        private long numberingLevel;

        /**
         * Constructor.
         * 
         * @param baseURI
         *            the base {@link URI}
         * @param linkTargetURI
         *            the link target {@link URI} if any, <code>null</code> otherwise
         * @param style
         *            the current {@link MStyle}.
         * @param numbering
         *            the numbering
         * @param numberingID
         *            the numbering ID
         * @param numberingLevel
         *            the numbering level
         */
        private Context(URI baseURI, URI linkTargetURI, MStyle style, CTAbstractNum numbering, BigInteger numberingID,
                long numberingLevel) {
            this.baseURI = baseURI;
            this.linkTargetURI = linkTargetURI;
            this.style = style;
            this.numbering = numbering;
            this.numberingID = numberingID;
            this.numberingLevel = numberingLevel;
        }

        /**
         * Copies this {@link Context}.
         * 
         * @return the copy of this {@link Context}
         */
        private Context copy() {
            final MStyleImpl mStyle = new MStyleImpl(style.getFontName(), style.getFontSize(),
                    style.getForegroundColor(), style.getBackgroundColor(), style.getFontModifiers());
            return new Context(baseURI, linkTargetURI, mStyle, numbering, numberingID, numberingLevel);
        }

    }

    /**
     * The {@link MHyperLink} {@link Color}.
     */
    private static final Color LINK_COLOR = Color.BLUE;

    /**
     * The {@link Map} of known {@link Color}.
     */
    private static final Map<String, Color> COLORS = initializeColors();

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
     * The {@link URIConverter}.
     */
    private final URIConverter uriConverter;

    /**
     * The destination {@link XWPFDocument}.
     */
    private XWPFDocument destinationDocument;

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter}
     * @param destinationDocument
     *            the destination XWPFDocument
     */
    public M2DocHTMLParser(URIConverter uriConverter, XWPFDocument destinationDocument) {
        this.uriConverter = uriConverter;
        this.destinationDocument = destinationDocument;
    }

    /**
     * Parses the given HTML {@link String} with the given base URI.
     * 
     * @param baseURI
     *            the base {@link URI}
     * @param htmlString
     *            the HTML {@link String}
     * @return the {@link List} of parsed {@link MElement}
     */
    public List<MElement> parse(URI baseURI, String htmlString) {
        final MList res = new MListImpl();

        final Document document = Jsoup.parse(htmlString, baseURI.toString());
        document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
        document.outputSettings().charset("UTF-8");

        final MStyle defaultStyle = new MStyleImpl(null, -1, null, null, -1);
        if (document.body().hasAttr("bgcolor")) {
            defaultStyle.setBackgroundColor(htmlToColor(document.body().attr("bgcolor").toLowerCase()));
        }

        final Context context = new Context(baseURI, null, defaultStyle, null, null, 0);

        walkNodeTree(res, context, document.body());

        return res;
    }

    /**
     * Walks the given {@link Node} recursibly to produce {@link MElement}.
     * 
     * @param parent
     *            the parent {@link MList}
     * @param context
     *            the current {@link Context}
     * @param node
     *            the {@link Node} to walk.
     */
    private void walkNodeTree(MList parent, Context context, Node node) {
        final Context contextCopy = context.copy();
        if (node instanceof Element) {
            if ("table".equals(node.nodeName())) {
                Node tBody = null;
                for (Node child : node.childNodes()) {
                    if ("tbody".equals(child.nodeName())) {
                        tBody = child;
                        break;
                    }
                }
                if (tBody != null) {
                    insertTable(parent, context, tBody);
                }
            } else {
                final MList element = startElement(parent, contextCopy, (Element) node);
                for (Node child : node.childNodes()) {
                    walkNodeTree(element, contextCopy, child);
                }
                endElement(parent, element);
            }
        } else if (node instanceof TextNode) {
            insertText(parent, contextCopy, (TextNode) node);
        }
    }

    /**
     * Inserts a table.
     * 
     * @param parent
     *            the parent {@link MList}
     * @param context
     *            the current {@link Context}
     * @param node
     *            the table {@link Node};
     */
    private void insertTable(MList parent, Context context, Node node) {
        final MTable table = new MTableImpl();
        parent.add(table);
        for (Node child : node.childNodes()) {
            if ("tr".equals(child.nodeName())) {
                final MRow row = new MRowImpl();
                table.getRows().add(row);
                for (Node rowChild : child.childNodes()) {
                    if ("th".equals(rowChild.nodeName()) || "td".equals(rowChild.nodeName())) {
                        final MList contents = new MListImpl();
                        final MCell cell = new MCellImpl(contents, null);
                        final Context localContext;
                        if ("th".equals(rowChild.nodeName())) {
                            cell.setHAlignment(HAlignment.CENTER);
                            localContext = context.copy();
                            setModifiers(localContext, MStyle.FONT_BOLD);
                        } else {
                            localContext = context;
                        }
                        row.getCells().add(cell);
                        for (Node cellChild : rowChild.childNodes()) {
                            walkNodeTree(contents, localContext, cellChild);
                        }
                    }
                }
            }
        }
    }

    /**
     * Ends the given {@link MElement}.
     * 
     * @param parent
     *            the parent {@link MList}
     * @param element
     *            the {@link MElement} to end
     */
    private void endElement(MList parent, MElement element) {
        if (element instanceof MList && ((MList) element).isEmpty()) {
            for (MElement child : parent) {
                if (child instanceof MParagraph && ((MParagraph) child).getContents() == element) {
                    parent.remove(child);
                    break;
                }
            }
        }
    }

    /**
     * Inserts the text of the given {@link TextNode}.
     * 
     * @param parent
     *            the parent {@link MList}
     * @param context
     *            the {@link Context}
     * @param node
     *            the {@link TextNode}
     */
    private void insertText(MList parent, final Context context, TextNode node) {
        final String text = node.text();
        if (!text.trim().isEmpty()) {
            if (context.linkTargetURI == null) {
                final MText mText = new MTextImpl(text, context.style);
                parent.add(mText);
            } else {
                context.style.setForegroundColor(LINK_COLOR);
                final MHyperLink mLink = new MHyperLinkImpl(text, context.style, context.linkTargetURI.toString());
                parent.add(mLink);
            }
        }
    }

    /**
     * Starts the given {@link Element}.
     * 
     * @param parent
     *            the parent {@link MList}
     * @param context
     *            the current {@link Context}
     * @param element
     *            the {@link Element}
     * @return the new parent {@link MList} for {@link Element#children() children}
     */
    private MList startElement(MList parent, Context context, Element element) {
        final MList res;

        final String nodeName = element.nodeName();
        boolean isNumbering = false;
        if ("p".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
        } else if ("strong".equals(nodeName) || "b".equals(nodeName)) {
            setModifiers(context, MStyle.FONT_BOLD);
            res = parent;
        } else if ("em".equals(nodeName) || "i".equals(nodeName)) {
            setModifiers(context, MStyle.FONT_ITALIC);
            res = parent;
        } else if ("s".equals(nodeName) || "strike".equals(nodeName)) {
            setModifiers(context, MStyle.FONT_STRIKE_THROUGH);
            res = parent;
        } else if ("u".equals(nodeName)) {
            setModifiers(context, MStyle.FONT_UNDERLINE);
            res = parent;
        } else if ("font".equals(nodeName)) {
            if (element.hasAttr("color")) {
                context.style.setForegroundColor(htmlToColor(element.attr("color").toLowerCase()));
            }
            if (element.hasAttr("face")) {
                // TODO double check this
                context.style.setFontName(element.attr("face"));
            }
            if (element.hasAttr("size")) {
                context.style.setFontSize(fontSizeToPoint(Integer.valueOf(element.attr("size"))));
            }
            res = parent;
        } else if ("a".equals(nodeName)) {
            context.linkTargetURI = URI.createURI(element.attr("href")).resolve(context.baseURI);
            res = parent;
        } else if ("br".equals(nodeName)) {
            parent.add(MPagination.ligneBreak);
            res = parent;
        } else if ("li".equals(nodeName)) {
            res = createMParagraph(parent, element, context.numberingID.longValue(), context.numberingLevel - 1);
        } else if ("ol".equals(nodeName)) {
            setOrderedListNumbering(context, element);
            isNumbering = true;
            res = parent;
        } else if ("ul".equals(nodeName)) {
            setUnorderedListNumbering(context, element);
            isNumbering = true;
            res = parent;
        } else if ("img".equals(nodeName)) {
            final URI imageURI = URI.createURI(element.attr("src")).resolve(context.baseURI);
            final MImage mImage = new MImageImpl(uriConverter, imageURI);
            // TODO set height and width pixel to ??? conversion ?
            parent.add(mImage);
            res = parent;
        } else if ("h1".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
            context.style.setFontSize(H1_FONT_SIZE);
            context.style.setModifiers(context.style.getFontModifiers() | MStyle.FONT_BOLD);
        } else if ("h2".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
            context.style.setFontSize(H2_FONT_SIZE);
            context.style.setModifiers(context.style.getFontModifiers() | MStyle.FONT_BOLD);
        } else if ("h3".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
            context.style.setFontSize(H3_FONT_SIZE);
            context.style.setModifiers(context.style.getFontModifiers() | MStyle.FONT_BOLD);
        } else if ("h4".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
            context.style.setFontSize(H4_FONT_SIZE);
            context.style.setModifiers(context.style.getFontModifiers() | MStyle.FONT_BOLD);
        } else if ("h5".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
            context.style.setFontSize(H5_FONT_SIZE);
            context.style.setModifiers(context.style.getFontModifiers() | MStyle.FONT_BOLD);
        } else if ("h6".equals(nodeName)) {
            res = createMParagraph(parent, element, null, null);
            context.style.setFontSize(H6_FONT_SIZE);
            context.style.setModifiers(context.style.getFontModifiers() | MStyle.FONT_BOLD);
        } else {
            res = parent;
        }

        if (!isNumbering) {
            context.numbering = null;
            context.numberingLevel = 0;
        }

        return res;
    }

    /**
     * Sets the unordered list numbering.
     * 
     * @param context
     *            the {@link Context}
     * @param element
     *            the ol {@link Element}
     */
    private void setUnorderedListNumbering(Context context, Element element) {
        final String symbol;
        if (element.hasAttr(TYPE_ATTR)) {
            final String type = element.attr(TYPE_ATTR);
            if ("disc".equals(type)) {
                symbol = DISC_SYMBOL;
            } else if ("square".equals(type)) {
                symbol = SQUARE_SYMBOL;
            } else if ("circle".equals(type)) {
                symbol = CIRCLE_SYMBOL;
            } else {
                symbol = DISC_SYMBOL;
            }
        } else {
            symbol = DISC_SYMBOL;
        }

        if (context.numbering == null) {
            createNumbering(context);
        }
        context.numberingLevel = incrementNumberingLevel(context.numbering, context.numberingLevel,
                STNumberFormat.BULLET, 1, symbol, false);
    }

    /**
     * Sets the ordered list numbering.
     * 
     * @param context
     *            the {@link Context}
     * @param element
     *            the ol {@link Element}
     */
    private void setOrderedListNumbering(Context context, Element element) {
        final STNumberFormat.Enum type;
        if (element.hasAttr(TYPE_ATTR)) {
            final String typeStr = element.attr(TYPE_ATTR);
            if ("1".equals(typeStr)) {
                type = STNumberFormat.DECIMAL;
            } else if ("A".equals(typeStr)) {
                type = STNumberFormat.UPPER_LETTER;
            } else if ("a".equals(typeStr)) {
                type = STNumberFormat.LOWER_LETTER;
            } else if ("I".equals(typeStr)) {
                type = STNumberFormat.UPPER_ROMAN;
            } else if ("i".equals(typeStr)) {
                type = STNumberFormat.LOWER_ROMAN;
            } else {
                type = STNumberFormat.DECIMAL;
            }
        } else {
            type = STNumberFormat.DECIMAL;
        }
        final long start;
        if (element.hasAttr("start")) {
            start = Long.valueOf(element.attr("start"));
        } else {
            start = 1;
        }
        final boolean reversed = element.hasAttr("reversed");
        if (context.numbering == null) {
            createNumbering(context);
        }
        context.numberingLevel = incrementNumberingLevel(context.numbering, context.numberingLevel, type, start, "",
                reversed);
    }

    /**
     * Creates a numbering for the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     */
    private void createNumbering(Context context) {
        final CTAbstractNum res;
        final XWPFNumbering numbering = destinationDocument.createNumbering();
        final CTNumbering ctNumbering = PaginationServices.getCTNumbering(numbering);
        res = ctNumbering.addNewAbstractNum();
        res.addNewMultiLevelType().setVal(STMultiLevelType.HYBRID_MULTILEVEL);
        BigInteger id = BigInteger.valueOf(ctNumbering.sizeOfAbstractNumArray() - 1);
        res.setAbstractNumId(id);
        final CTNum ctNum = ctNumbering.addNewNum();
        ctNum.setNumId(BigInteger.valueOf(ctNumbering.sizeOfNumArray()));
        ctNum.addNewAbstractNumId().setVal(id);

        context.numbering = res;
        context.numberingID = ctNum.getNumId();
    }

    /**
     * Increments the level for the given {@link CTAbstractNum}.
     * 
     * @param numbering
     *            the {@link CTAbstractNum}
     * @param currentLevel
     *            the current level
     * @param type
     *            the {@link STNumberFormat#enumValue()}
     * @param start
     *            the start
     * @param symbol
     *            the symbol
     * @param reversed
     *            tell if the numbering is reversed
     * @return the new level
     */
    private long incrementNumberingLevel(CTAbstractNum numbering, long currentLevel, STNumberFormat.Enum type,
            long start, String symbol, boolean reversed) {
        if (numbering.getLvlList().size() <= currentLevel) {
            final CTLvl level = numbering.addNewLvl();
            level.setIlvl(BigInteger.valueOf(currentLevel));
            final CTDecimalNumber strt = level.addNewStart();
            strt.setVal(BigInteger.valueOf(start));
            final CTNumFmt fmt = level.addNewNumFmt();
            fmt.setVal(type);
            CTLevelText text = level.addNewLvlText();
            if (type == STNumberFormat.BULLET) {
                text.setVal(symbol);
                CTFonts font = level.addNewRPr().addNewRFonts();
                if (symbol == DISC_SYMBOL) {
                    font.setAscii("Symbol");
                    font.setHAnsi("Symbol");
                } else if (symbol == SQUARE_SYMBOL) {
                    font.setAscii("Wingdings");
                    font.setHAnsi("Wingdings");
                } else if (symbol == DISC_SYMBOL) {
                    font.setAscii("Courier New");
                    font.setHAnsi("Courier New");

                }
                level.addNewLvlJc().setVal(STJc.LEFT);
                final CTInd indentation = level.addNewPPr().addNewInd();
                indentation.setHanging(BigInteger.valueOf(INDENT_HANGING * 2));
                indentation.setLeft(BigInteger.valueOf(INDENT_LEFT * (currentLevel + 1)));
            } else {
                final CTInd indentation = level.addNewPPr().addNewInd();
                text.setVal("%" + (currentLevel + 1) + ".");
                if (currentLevel > 0) {
                    level.setTentative(STOnOff.X_1);
                }
                if (type == STNumberFormat.UPPER_ROMAN) {
                    level.addNewLvlJc().setVal(STJc.RIGHT);
                    indentation.setHanging(BigInteger.valueOf(INDENT_HANGING * 2));
                } else if (type == STNumberFormat.LOWER_ROMAN) {
                    level.addNewLvlJc().setVal(STJc.RIGHT);
                    indentation.setHanging(BigInteger.valueOf(INDENT_HANGING));
                } else {
                    level.addNewLvlJc().setVal(STJc.LEFT);
                    indentation.setHanging(BigInteger.valueOf(INDENT_HANGING * 2));
                }
                indentation.setLeft(BigInteger.valueOf(INDENT_LEFT * (currentLevel + 1)));
                // TODO reversed
            }
        }

        return currentLevel + 1;
    }

    /**
     * Sets the given modifier for the given {@link Context}.
     * 
     * @param context
     *            the {@link Context}
     * @param modifier
     *            the modifier
     */
    private void setModifiers(Context context, int modifier) {
        if (context.style.getFontModifiers() == -1) {
            context.style.setModifiers(modifier);
        } else {
            context.style.setModifiers(context.style.getFontModifiers() | modifier);
        }
    }

    /**
     * Creates a {@link MParagraph}.
     * 
     * @param parent
     *            the parent {@link MList}
     * @param element
     *            the {@link Element}
     * @param numberingID
     *            the numbering ID
     * @param numberingLevel
     *            the numbering level
     * @return the created {@link MParagraph}
     */
    private MList createMParagraph(MList parent, Element element, Long numberingID, Long numberingLevel) {
        final MList res = new MListImpl();

        final MParagraph paragraph = new MParagraphImpl(res, null);
        parent.add(paragraph);
        paragraph.setNumberingID(numberingID);
        paragraph.setNumberingLevel(numberingLevel);
        if (element.hasAttr("align")) {
            final String align = element.attr("align");
            if ("left".equals(align)) {
                paragraph.setHAlignment(HAlignment.LEFT);
            } else if ("right".equals(align)) {
                paragraph.setHAlignment(HAlignment.RIGHT);
            } else if ("center".equals(align)) {
                paragraph.setHAlignment(HAlignment.CENTER);
            } else if ("justify".equals(align)) {
                paragraph.setHAlignment(HAlignment.DISTRIBUTE);
            }
        }

        return res;
    }

    /**
     * Gets the number of point for the given font size.
     * 
     * @param size
     *            the font size
     * @return the number of point for the given font size
     */
    private int fontSizeToPoint(int size) {
        final int res;

        // CHECKSTYLE:OFF
        switch (size) {
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
                res = 13;
                break;

            case 5:
                res = 18;
                break;

            case 6:
                res = 24;
                break;

            default:
                res = 3;
                break;
        }
        // CHECKSTYLE:ON

        return res;
    }

    /**
     * Gets the {@link Color} from the given HTML color {@link String}.
     * 
     * @param htmlColor
     *            the HTML color {@link String}
     * @return the {@link Color} from the given HTML color {@link String}
     */
    private Color htmlToColor(String htmlColor) {
        final Color res;

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
            } else {
                res = Color.decode(htmlColor.replace("#", "0x"));
            }
            COLORS.put(htmlColor, res);
        }

        return res;
    }

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

}
