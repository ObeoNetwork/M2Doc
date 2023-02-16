/*******************************************************************************
 *  Copyright (c) 2019, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.wikitext.services;

import java.awt.Color;
import java.math.BigInteger;
import java.util.List;
import java.util.Stack;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.mylyn.wikitext.parser.Attributes;
import org.eclipse.mylyn.wikitext.parser.DocumentBuilder;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.MHyperLinkImpl;
import org.obeonetwork.m2doc.element.impl.MImageImpl;
import org.obeonetwork.m2doc.element.impl.MListImpl;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MStyleImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
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

/**
 * The {@link DocumentBuilder} that produce the {@link List} of {@link MElement}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocMElementBuilder extends DocumentBuilder {

    /**
     * The indentation left.
     */
    private static final int INDENT_LEFT = 720;

    /**
     * The indentation hanging.
     */
    private static final int INDENT_HANGING = 180;

    /**
     * The disc symbol for bullet list.
     */
    private static final String DISC_SYMBOL = "\uF0B7";

    /**
     * The square symbol for bullet list.
     */
    private static final String SQUARE_SYMBOL = "\uF0A7";

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
     * The {@link Context}.
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
         * The current numbering level.
         */
        private long numberingLevel;

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
         * The parent {@link MList}.
         */
        private MList parent;

        /**
         * Constructor.
         * 
         * @param parent
         *            the parent {@link MList}
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
        private Context(MList parent, URI baseURI, URI linkTargetURI, MStyle style, CTAbstractNum numbering,
                BigInteger numberingID, long numberingLevel) {
            this.parent = parent;
            this.baseURI = baseURI;
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
            return new Context(parent, baseURI, linkTargetURI, mStyle, numbering, numberingID, numberingLevel);
        }

    }

    /**
     * The {@link MHyperLink} {@link Color}.
     */
    private static final Color LINK_COLOR = Color.BLUE;

    /**
     * The restult {@link List} of {@link MElement}.
     */
    private MList result;

    /**
     * The base {@link URI}.
     */
    private URI baseURI;

    /**
     * The {@link URIConverter}.
     */
    private URIConverter uriConverter;

    /**
     * The destination {@link XWPFDocument}.
     */
    private XWPFDocument destinationDocument;

    /**
     * The {@link Context} {@link Stack}.
     */
    private final Stack<Context> contexts = new Stack<Context>();

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter}
     * @param destinationDocument
     *            the destination {@link XWPFDocument}
     */
    public M2DocMElementBuilder(URIConverter uriConverter, XWPFDocument destinationDocument) {
        this.uriConverter = uriConverter;
        this.destinationDocument = destinationDocument;
    }

    /**
     * Gets the result {@link List} of {@link MElement}.
     * 
     * @return the result {@link List} of {@link MElement}
     */
    public List<MElement> getResult() {
        return result;
    }

    public void setBaseURI(URI baseURI) {
        this.baseURI = baseURI;
    }

    @Override
    public void beginDocument() {
        result = new MListImpl();
        final MStyle defaultStyle = new MStyleImpl(null, -1, null, null, -1);
        contexts.push(new Context(result, baseURI, null, defaultStyle, null, null, 0));
    }

    @Override
    public void endDocument() {
        contexts.pop();
    }

    @Override
    public void beginBlock(BlockType type, Attributes attributes) {
        final Context context = contexts.peek().copy();
        contexts.push(context);

        boolean isNumbering = false;
        switch (type) {
            case PARAGRAPH:
                context.parent = createMParagraph(context.parent, attributes, null, null);
                break;

            case LIST_ITEM:
                context.parent = createMParagraph(context.parent, attributes, context.numberingID.longValue(),
                        context.numberingLevel - 1);
                isNumbering = true;
                break;

            case NUMERIC_LIST:
                setOrderedListNumbering(context, attributes);
                isNumbering = true;
                break;

            case BULLETED_LIST:
                setUnorderedListNumbering(context, attributes);
                isNumbering = true;
                break;

            default:
                break;
        }

        if (!isNumbering) {
            context.numbering = null;
            context.numberingLevel = 0;
        }
    }

    @Override
    public void endBlock() {
        contexts.pop();
    }

    @Override
    public void beginSpan(SpanType type, Attributes attributes) {
        final Context context = contexts.peek().copy();
        contexts.push(context);

        switch (type) {
            case STRONG:
            case BOLD:
                setModifiers(context, MStyle.FONT_BOLD);
                break;

            case EMPHASIS:
            case ITALIC:
                setModifiers(context, MStyle.FONT_ITALIC);
                break;

            case DELETED:
                setModifiers(context, MStyle.FONT_STRIKE_THROUGH);
                break;

            case UNDERLINED:
                setModifiers(context, MStyle.FONT_UNDERLINE);
                break;

            default:
                // nothing to do here
                break;
        }
    }

    @Override
    public void endSpan() {
        contexts.pop();
    }

    @Override
    public void beginHeading(int level, Attributes attributes) {
        final Context context = contexts.peek().copy();
        contexts.push(context);

        context.parent = createMParagraph(context.parent, attributes, null, null);

        switch (level) {
            case 1:
                context.style.setFontSize(H1_FONT_SIZE);
                break;
            case 2:
                context.style.setFontSize(H2_FONT_SIZE);
                break;
            case 3:
                context.style.setFontSize(H3_FONT_SIZE);
                break;
            case 4:
                context.style.setFontSize(H4_FONT_SIZE);
                break;
            case 5:
                context.style.setFontSize(H5_FONT_SIZE);
                break;
            case 6:
                context.style.setFontSize(H6_FONT_SIZE);
                break;

            default:
                // nothing to do here
                break;
        }
        setModifiers(context, MStyle.FONT_BOLD);

    }

    @Override
    public void endHeading() {
        contexts.pop();
    }

    @Override
    public void characters(String text) {
        final MText mText = new MTextImpl(text, contexts.peek().style);
        contexts.peek().parent.add(mText);
    }

    @Override
    public void entityReference(String entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void image(Attributes attributes, String url) {
        final URI imageURI = URI.createURI(url).resolve(contexts.peek().baseURI);
        final MImage mImage = new MImageImpl(uriConverter, imageURI);
        contexts.peek().parent.add(mImage);
    }

    @Override
    public void link(Attributes attributes, String hrefOrHashName, String text) {
        final Context context = contexts.peek().copy();
        context.style.setForegroundColor(LINK_COLOR);
        final URI uri;
        if (hrefOrHashName != null) {
            if (hrefOrHashName.startsWith("#")) {
                uri = URI.createURI(hrefOrHashName);
            } else {
                uri = URI.createURI(hrefOrHashName).resolve(context.baseURI);
            }
        } else {
            uri = URI.createURI("", false);
        }
        final MHyperLink mLink = new MHyperLinkImpl(text, context.style, uri.toString());
        contexts.peek().parent.add(mLink);
    }

    @Override
    public void imageLink(Attributes linkAttributes, Attributes imageAttributes, String href, String imageUrl) {
        final URI imageURI = URI.createURI(imageUrl).resolve(contexts.peek().baseURI);
        final MImage mImage = new MImageImpl(uriConverter, imageURI);
        contexts.peek().parent.add(mImage);
    }

    @Override
    public void acronym(String text, String definition) {
        // TODO double check this
        final MText mText = new MTextImpl(text, contexts.peek().style);
        contexts.peek().parent.add(mText);
    }

    @Override
    public void lineBreak() {
        contexts.peek().parent.add(MPagination.ligneBreak);
    }

    @Override
    public void charactersUnescaped(String literal) {
        // TODO double check this
        final MText mText = new MTextImpl(literal, contexts.peek().style);
        contexts.peek().parent.add(mText);
    }

    /**
     * Sets the unordered list numbering.
     * 
     * @param context
     *            the {@link Context}
     * @param attributes
     *            the ol {@link Attributes}
     */
    private void setUnorderedListNumbering(Context context, Attributes attributes) {
        final String symbol = DISC_SYMBOL;

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
     * @param attributes
     *            the ol {@link Attributes}
     */
    private void setOrderedListNumbering(Context context, Attributes attributes) {
        final STNumberFormat.Enum type = STNumberFormat.DECIMAL;
        final long start = 1;
        if (context.numbering == null) {
            createNumbering(context);
        }
        context.numberingLevel = incrementNumberingLevel(context.numbering, context.numberingLevel, type, start, "",
                false);
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
                    final STOnOff onOff = STOnOff.Factory.newInstance();
                    onOff.setStringValue("1");
                    level.xsetTentative(onOff);
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
     * @param attributes
     *            the {@link Attributes}
     * @param numberingID
     *            the numbering ID
     * @param numberingLevel
     *            the numbering level
     * @return the created {@link MParagraph}
     */
    private MList createMParagraph(MList parent, Attributes attributes, Long numberingID, Long numberingLevel) {
        final MList res = new MListImpl();

        final MParagraph paragraph = new MParagraphImpl(res, null);
        parent.add(paragraph);
        paragraph.setNumberingID(numberingID);
        paragraph.setNumberingLevel(numberingLevel);

        return res;
    }

}
