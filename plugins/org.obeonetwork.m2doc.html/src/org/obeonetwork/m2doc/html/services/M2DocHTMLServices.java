/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.html.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.element.MElement;

//@formatter:off
@ServiceProvider(
value = "Services available for HTML insertion."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class M2DocHTMLServices {

    /**
     * The URI converter to use.
     */
    private final URIConverter uriConverter;

    /**
     * The template URI.
     */
    private final URI templateURI;

    /**
     * The {@link M2DocHTMLParser} parser.
     */
    private M2DocHTMLParser parser;

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     */
    public M2DocHTMLServices(URIConverter uriConverter, URI templateURI) {
        this.uriConverter = uriConverter;
        this.templateURI = templateURI;
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given HTML String.",
        params = {
            @Param(name = "htmlString", value = "The HTML document."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'<html><head><title>Sample HTML for test purpose</title></head><body><h2 id=\"starting-with-m2doc\">Starting with M2Doc</h2></body></html>').fromHTMLString()",
                result = "The Sequence of MElement corresponding to the given HTML String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromHTMLString(String htmlString) {
        return parser.parse(templateURI, htmlString);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given body String.",
        params = {
            @Param(name = "bodyString", value = "The body String."),
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'<h2 id=\"starting-with-m2doc\">Starting with M2Doc</h2>'.fromHTMLBodyString()",
                result = "The Sequence of MElement corresponding to the given HTML String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromHTMLBodyString(String bodyString) {
        return parser.parse(templateURI, "<html><head/><body>" + bodyString + "</body></html>");
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given HTML String.",
        params = {
            @Param(name = "htmlString", value = "The HTML document."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given HTML String.",
        examples = {
            @Example(
                expression = "'<html><head><title>Sample HTML for test purpose</title></head><body><h2 id=\\\"starting-with-m2doc\\\">Starting with M2Doc</h2></body></html>'.fromHTMLString('http://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given HTML String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromHTMLString(String htmlString, String baseURI) {
        return parser.parse(URI.createURI(baseURI, false), htmlString);
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given body String.",
        params = {
            @Param(name = "bodyString", value = "The HTML body."),
            @Param(name = "baseURI", value = "The base URI to use for link and images resolving.")
        },
        result = "The Sequence of MElement corresponding to the given body String.",
        examples = {
            @Example(
                expression = "'<h2 id=\"starting-with-m2doc\">Starting with M2Doc</h2>'.fromHTMLBodyString('http://www.m2doc.org/')",
                result = "The Sequence of MElement corresponding to the given body String."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromHTMLBodyString(String bodyString, String baseURI) {
        return parser.parse(URI.createURI(baseURI, false), "<html><head/><body>" + bodyString + "</body></html>");
    }

    // @formatter:off
    @Documentation(
        value = "Returns a Sequence of MElement corresponding to the given web page.",
        params = {
            @Param(name = "uriStr", value = "The URI."),
        },
        result = "The Sequence of MElement corresponding to the given web page.",
        examples = {
            @Example(
                expression = "'http://www.m2doc.org/'.fromHTMLURI()",
                result = "The Sequence of MElement corresponding to the given web page."
            )
        }
    )
    // @formatter:on
    public List<MElement> fromHTMLURI(String uriStr) throws IOException {
        final URI htmlURI = URI.createURI(uriStr, false);
        final URI uri = htmlURI.resolve(templateURI);

        try (InputStream input = uriConverter.createInputStream(uri);) {
            final String htmlString = new String(IOUtils.toByteArray(input));

            return parser.parse(uri, htmlString);
        }
    }

    /**
     * Sets the destination {@link XWPFDocument}.
     * 
     * @param destinationDocument
     *            the destination {@link XWPFDocument}
     */
    public void setDestinationDocument(XWPFDocument destinationDocument) {
        this.parser = new M2DocHTMLParser(uriConverter, destinationDocument);
    }

}
