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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

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
     * Get XWPFDocument from template file.
     * 
     * @param templateFile
     *            IFile
     * @return XWPFDocument
     * @throws IOException
     *             IOException
     */
    @SuppressWarnings("resource")
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
    public TemplateCustomProperties getTemplateInformations(URI templateFile) throws IOException {
        final TemplateCustomProperties res;

        try (XWPFDocument document = getXWPFDocument(templateFile);) {
            res = new TemplateCustomProperties(document);
        }
        return res;
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
