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
package org.obeonetwork.m2doc.services;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MElementContainer.HAlignment;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MCellImpl;
import org.obeonetwork.m2doc.element.impl.MTableImpl.MRowImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;

/**
 * Pagination services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
//@formatter:off
@ServiceProvider(
value = "Services available for pagination. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/paginationServices)."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class PaginationServices {

    /**
     * The mapping from the text style identifier to the style name.
     */
    private final Map<String, String> textStyleIDs = new HashMap<>();

    /**
     * The mapping from the table style identifier to the style name.
     */
    private final Map<String, String> tableStyleIDs = new HashMap<>();

    /**
     * The mapping from numbering ID to its number of level.
     */
    private final Map<BigInteger, BigInteger> numberingIDs = new HashMap<>();

    /**
     * Consturtor.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     */
    public PaginationServices(URIConverter uriConverter, URI templateURI) {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(uriConverter, templateURI);) {
            final List<XWPFStyle> styles = getStyles(document.getStyles());
            for (XWPFStyle style : styles) {
                switch (style.getType().intValue()) {
                    case STStyleType.INT_PARAGRAPH:
                        textStyleIDs.put(style.getStyleId(), style.getName());
                        break;

                    case STStyleType.INT_TABLE:
                        tableStyleIDs.put(style.getStyleId(), style.getName());
                        break;

                    default:
                        break;
                }
            }
            if (document.getNumbering() != null) {
                final CTNumbering numbering = getCTNumbering(document.getNumbering());
                for (CTAbstractNum num : numbering.getAbstractNumList()) {
                    final BigInteger id = num.getAbstractNumId().add(BigInteger.valueOf(1));
                    final BigInteger maxLevel = BigInteger.valueOf(num.getLvlList().size());
                    numberingIDs.put(id, maxLevel);
                }
            }
        } catch (IOException e) {
            // nothing to do here
        }
    }

    /**
     * Gets the {@link List} of {@link XWPFStyle} from the given {@link XWPFStyles}.
     * 
     * @param styles
     *            the {@link XWPFStyles}
     * @return the {@link List} of {@link XWPFStyle} from the given {@link XWPFStyles}
     */
    @SuppressWarnings("unchecked")
    private List<XWPFStyle> getStyles(XWPFStyles styles) {
        final List<XWPFStyle> res = new ArrayList<>();

        for (Field field : styles.getClass().getDeclaredFields()) {
            if ("listStyle".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    res.addAll((List<XWPFStyle>) field.get(styles));
                    break;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("can't retreive style names.", e);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("can't retreive style names.", e);
                } finally {
                    field.setAccessible(false);
                }
            }
        }

        return res;
    }

    /**
     * Gets the {@link CTNumbering} of the given {@link XWPFNumbering}.
     * 
     * @param numbering
     *            the {@link XWPFNumbering}
     * @return the {@link CTNumbering} of the given {@link XWPFNumbering}
     */
    private CTNumbering getCTNumbering(XWPFNumbering numbering) {
        CTNumbering res = null;
        for (Field field : numbering.getClass().getDeclaredFields()) {
            if ("ctNumbering".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    res = (CTNumbering) field.get(numbering);
                    break;
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("can't retreive numbering IDs.", e);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException("can't retreive numbering IDs.", e);
                } finally {
                    field.setAccessible(false);
                }
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Converts a String to an hyperlink",
        params = {
            @Param(name = "paginationElement", value = "The name of the pagination element: 'newTableOfContent', 'newPage', 'newParagraph', 'newColumn', 'newTextWrapping'"),
        },
        result = "Insert the element.",
        examples = {
            @Example(expression = "'newPage'.asPagination()", result = "insert a page break"),
        }
    )
    // @formatter:on
    public MPagination asPagination(String paginationElement) {
        return MPagination.valueOf(paginationElement);
    }

    // @formatter:off
    @Documentation(
        value = "Converts a String with a given style if the style exists in the template, this service will insert a new paragraph. You can add styled text in comment to make sure they are present.",
        params = {
            @Param(name = "text", value = "The text"),
            @Param(name = "style", value = "The style ID. see availableTextStyles()."),
        },
        result = "Insert the given text as the given style.",
        examples = {
            @Example(expression = "'Section 1'.asStyle('Title1')", result = "insert 'Section 1' as style 'Titre1' in a new paragraph if the style exists in the template."),
        }
    )
    // @formatter:on
    public MParagraph asStyle(String text, String styleID) {
        final String styleName = textStyleIDs.get(styleID);
        if (styleName != null) {
            return new MParagraphImpl(new MTextImpl(text, null), styleID);
        } else {
            throw new IllegalArgumentException("no text style " + styleID);
        }
    }

    // @formatter:off
    @Documentation(
        value = "Lists available text styles in the template. You can add styled text in comment to make sure a style is present.",
        params = {
            @Param(name = "obj", value = "Any object"),
        },
        result = "List available text styles in the template.",
        examples = {
            @Example(expression = "''.availableTextStyles()", result = "insert 'List of available text styles:...'."),
        }
    )
    // @formatter:on
    public List<MParagraph> availableTextStyles(Object obj) {
        final List<MParagraph> res = new ArrayList<>();

        res.add(new MParagraphImpl(new MTextImpl("List of available text styles:", null), null));
        for (String styleID : textStyleIDs.keySet()) {
            res.add(asStyle(styleID, styleID));
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Converts a MTable with a given style if the style exists in the template. You can add styled text in blockcomment to make sure they are present.",
        params = {
            @Param(name = "table", value = "The MTable"),
            @Param(name = "styleID", value = "The style ID. see availableTableStyles()."),
        },
        result = "Apply the given style to the given table.",
        examples = {
            @Example(expression = "myTable.asStyle('Title1')", result = "set the given style to myTable."),
        }
    )
    // @formatter:on
    public MTable asStyle(MTable table, String styleID) {
        final String styleName = tableStyleIDs.get(styleID);
        if (styleName != null) {
            table.setStyleID(styleID);
            return table;
        } else {
            throw new IllegalArgumentException("no table style " + styleID);
        }
    }

    // @formatter:off
    @Documentation(
        value = "Lists available table styles in the template. You can add styled table in comment to make sure a style is present.",
        params = {
            @Param(name = "obj", value = "Any object"),
        },
        result = "List available table styles in the template.",
        examples = {
            @Example(expression = "''.availableTableStyles()", result = "insert 'List of available table styles:...'."),
        }
    )
    // @formatter:on
    public List<MElement> availableTableStyles(Object obj) {
        final List<MElement> res = new ArrayList<>();

        res.add(new MParagraphImpl(new MTextImpl("List of available table styles:", null), null));
        for (String styleID : tableStyleIDs.keySet()) {
            res.add(asStyle(styledTable(styleID), styleID));
        }

        return res;
    }

    /**
     * Creates a sample styled {@link MTable}.
     * 
     * @param styleID
     *            the style ID
     * @return the created sample styled {@link MTable}
     */
    private MTable styledTable(String styleID) {
        final MTable res = new MTableImpl();

        res.setStyleID(styleID);
        for (int i = 0; i < 3; i++) {
            final MRow row = new MRowImpl();
            res.getRows().add(row);
            for (int j = 0; j < 3; j++) {
                row.getCells().add(new MCellImpl(new MTextImpl(styleID, null), null));
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Lists available numbering IDs in the template. You can add bullet/numbered list in comment to make sure a numbering is present.",
        params = {
            @Param(name = "obj", value = "Any object"),
        },
        result = "List available numbering IDs in the template.",
        examples = {
            @Example(expression = "''.availableNumberingIDs()", result = "insert 'List of available numbering IDs:...'."),
        }
    )
    // @formatter:on
    public List<MParagraph> availableNumberingIDs(Object obj) {
        final List<MParagraph> res = new ArrayList<>();

        res.add(new MParagraphImpl(new MTextImpl("List of available numbering IDs:", null), null));
        for (Entry<BigInteger, BigInteger> entry : numberingIDs.entrySet()) {
            for (long level = 0; level < entry.getValue().longValue(); level++) {
                final MParagraphImpl paragraph = new MParagraphImpl(
                        new MTextImpl(String.valueOf("ID: " + entry.getKey() + " Level: " + level), null), null);
                paragraph.setNumberingID(entry.getKey().longValue());
                paragraph.setNumberingLevel(level);
                res.add(paragraph);
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Convert a String into a MParagraph.",
        params = {
            @Param(name = "text", value = "The text"),
        },
        result = "Convert a String into a MParagraph.",
        examples = {
            @Example(expression = "'some text'.asParagraph()", result = "insert 'some text' in a new paragraph."),
        }
    )
    // @formatter:on
    public MParagraph asParagraph(String text) {
        return new MParagraphImpl(new MTextImpl(text, null), null);
    }

    // @formatter:off
    @Documentation(
        value = "Sets the style of the paragraph.",
        params = {
            @Param(name = "paragraph", value = "The paragraph"),
            @Param(name = "styleID", value = "The style ID. see availableTextStyles()."),
        },
        result = "Sets the style of the paragraph.",
        examples = {
            @Example(expression = "myParagraph.setStyle('Title1')", result = "Sets the style to Title1."),
        }
    )
    // @formatter:on
    public MParagraph setStyle(MParagraph paragraph, String styleID) {
        final String styleName = textStyleIDs.get(styleID);
        if (styleName != null) {
            paragraph.setStyleName(styleID);
            return paragraph;
        } else {
            throw new IllegalArgumentException("no text style " + styleID);
        }
    }

    // @formatter:off
    @Documentation(
        value = "Sets the alignment of the paragraph.",
        params = {
            @Param(name = "paragraph", value = "The paragraph"),
            @Param(name = "alignment", value = "The alignment name: 'BOTH', 'CENTER', 'DISTRIBUTED', 'HIGH_KASHIDA', 'LEFT', 'LOW_KASHIDA', 'MEDIUM_KASHIDA', 'NUM_TAB', 'RIGHT', 'THAI_DISTRIBUTE'."),
        },
        result = "Sets the alignement of the paragraph.",
        examples = {
            @Example(expression = "myParagraph.setAlignment('CENTER')", result = "Sets the alignment to center."),
        }
    )
    // @formatter:on
    public MParagraph setAlignment(MParagraph paragraph, String alignment) {
        paragraph.setHAlignment(HAlignment.valueOf(alignment));

        return paragraph;
    }

    // @formatter:off
    @Documentation(
        value = "Sets the numbering and level of the paragraph.",
        params = {
            @Param(name = "paragraph", value = "The paragraph"),
            @Param(name = "numberingID", value = "The integer idenfying the numbering. see availableNumberingIDs()."),
            @Param(name = "level", value = "The level in the numbering. see availableNumberingIDs()."),
        },
        result = "Sets the numbering and level on the paragraph.",
        examples = {
            @Example(expression = "myParagraph.setNumbering(0, 3)", result = "Sets the numbering to the 0 numbering and level to 3 in this numbering."),
        }
    )
    // @formatter:on
    public MParagraph setNumbering(MParagraph paragraph, Integer numberingID, Integer level) {
        final BigInteger maxLevel = numberingIDs.get(BigInteger.valueOf(numberingID));
        if (maxLevel != null) {
            if (maxLevel.longValue() > level) {
                paragraph.setNumberingID(Long.valueOf(numberingID));
                paragraph.setNumberingLevel(Long.valueOf(level));
            } else {
                throw new IllegalArgumentException("maximum level for numbering with ID " + numberingID + " is "
                    + maxLevel.subtract(BigInteger.ONE) + ", " + level + " is too high.");
            }
        } else {
            throw new IllegalArgumentException("no numbering with ID " + numberingID);
        }

        return paragraph;
    }

}
