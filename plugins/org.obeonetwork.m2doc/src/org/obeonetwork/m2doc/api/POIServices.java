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
package org.obeonetwork.m2doc.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.DocumentTemplateParser;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * POI services.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class POIServices {

    /**
     * Instance.
     */
    private static POIServices eINSTANCE = new POIServices();

    /**
     * Constructor.
     */
    private POIServices() {
        super();
    }

    /**
     * return the instance.
     * 
     * @return the instance
     */
    public static POIServices getInstance() {
        return eINSTANCE;
    }

    /**
     * Parse template file.
     * 
     * @param templateFile
     *            IFile
     * @param queryEnvironment
     *            IQueryEnvironment
     * @return DocumentTemplate
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    public DocumentTemplate parseTemplate(URI templateFile, IQueryEnvironment queryEnvironment)
            throws IOException, DocumentParserException {
        XWPFDocument document = getXWPFDocument(templateFile);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument(templateFile);
        return template;
    }

    /**
     * Get XWPFDocument from template file.
     * 
     * @param templateFile
     *            IFile
     * @return XWPFDocument
     * @throws IOException
     *             IOException
     */
    public XWPFDocument getXWPFDocument(URI templateFile) throws IOException {
        OPCPackage oPackage = getOPCPackage(templateFile);
        XWPFDocument document = new XWPFDocument(oPackage);
        return document;
    }

    /**
     * Get OPCPackage from template file.
     * 
     * @param templateFile
     *            IFile
     * @return OPCPackage
     * @throws IOException
     *             IOException
     */
    public OPCPackage getOPCPackage(URI templateFile) throws IOException {
        OPCPackage oPackage;
        try (InputStream is = URIConverter.INSTANCE.createInputStream(templateFile)) {
            try {
                oPackage = OPCPackage.open(is);

            } catch (InvalidFormatException e) {
                throw new IllegalArgumentException("Couldn't open template file", e);
            }
        }
        return oPackage;
    }

    /**
     * Get template informations.
     * 
     * @param templateFile
     *            IFile
     * @return TemplateInfo
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     */
    public TemplateInfo getTemplateInformations(URI templateFile) throws IOException {
        XWPFDocument document = getXWPFDocument(templateFile);
        TemplateInfo templateInfo = new TemplateInfo(document);
        return templateInfo;
    }

    /**
     * Save the document into the file pointing at the given path.
     * 
     * @param document
     *            the validated document to save.
     * @param theDestinationFileName
     *            the file were to save the content of the validated document.
     * @throws IOException
     *             throws if the writing of the document on the file system fails.
     */
    public void saveFile(XWPFDocument document, URI theDestinationFileName) throws IOException {
        try (OutputStream os = URIConverter.INSTANCE.createOutputStream(theDestinationFileName)) {
            document.write(os);
        }
    }

}
