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
import org.obeonetwork.m2doc.element.impl.MParagraphImpl;
import org.obeonetwork.m2doc.element.impl.MTextImpl;

/**
 * Pagination services.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
//@formatter:off
@ServiceProvider(
value = "Services available for pagination"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class PaginationServices {

    /**
     * The mapping from the level to the style identifier.
     */
    private final Map<String, String> styleIDs = new HashMap<>();

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
                styleIDs.put(style.getStyleId(), style.getName());
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("can't load template to retreive style names.", e);
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
            @Param(name = "style", value = "The style name"),
        },
        result = "Insert the given text as a title.",
        examples = {
            @Example(expression = "'Section 1'.asStyle('Title1')", result = "insert 'Section 1' as style 'Titre1' in a new paragraph if the style exists in the template."),
        }
    )
    // @formatter:on
    public MParagraph asStyle(String text, String styleName) {
        final String styleID = styleIDs.get(styleName);
        if (styleID != null) {
            return new MParagraphImpl(new MTextImpl(text, null), styleName);
        } else {
            throw new IllegalArgumentException("no style " + styleName);
        }
    }

}
