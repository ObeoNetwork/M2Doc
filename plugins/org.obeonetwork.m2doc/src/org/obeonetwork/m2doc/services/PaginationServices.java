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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;
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
            @Param(name = "style", value = "The style ID"),
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
        value = "Converts a MTable with a given style if the style exists in the template. You can add styled text in blockcomment to make sure they are present.",
        params = {
            @Param(name = "table", value = "The MTable"),
            @Param(name = "styleID", value = "The style ID"),
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

}
