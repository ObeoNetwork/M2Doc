/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.parser;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * A document parser organizes the parsing.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public class DocumentGeneratedParser {
    /**
     * The environment used to parse queries.
     */
    private IQueryEnvironment queryEnvironment;
    /**
     * Main parsed document.
     */
    private XWPFDocument document;

    /**
     * Creates a new {@link DocumentGeneratedParser} instance.
     * 
     * @param document
     *            the document to parse.
     * @param queryEnvironment
     *            the queryEnvironment to used during parsing.
     */
    public DocumentGeneratedParser(XWPFDocument document, IQueryEnvironment queryEnvironment) {
        this.queryEnvironment = queryEnvironment;
        this.document = document;
    }

    /**
     * Parses a document and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document.
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    public DocumentTemplate parseDocument() throws DocumentParserException {
        DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        BodyGeneratedParser parser = new BodyGeneratedParser(document, new QueryBuilderEngine(queryEnvironment),
                queryEnvironment);
        result.setBody(parser.parseTemplate());
        result.setDocument(document);
        for (XWPFFooter footer : document.getFooterList()) {
            BodyGeneratedParser footerParser = new BodyGeneratedParser(footer, new QueryBuilderEngine(queryEnvironment),
                    queryEnvironment);
            result.getFooters().add(footerParser.parseTemplate());
        }
        for (XWPFHeader header : document.getHeaderList()) {
            BodyGeneratedParser headerParser = new BodyGeneratedParser(header, new QueryBuilderEngine(queryEnvironment),
                    queryEnvironment);
            result.getHeaders().add(headerParser.parseTemplate());
        }
        return result;
    }

}
