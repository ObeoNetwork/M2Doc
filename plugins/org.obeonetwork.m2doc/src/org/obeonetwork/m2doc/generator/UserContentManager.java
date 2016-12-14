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
package org.obeonetwork.m2doc.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.parser.DocumentGeneratedParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.UserContent;

/**
 * This class manage UserDoc Destination tag UserContent.
 * It launch destination parsing doc and keep map of UserDoc id / UserContent EObject element.
 * Be careful, parsing must be done on a copy of destination document because old destination document is remove by generation.
 * 
 * @author ohaegi
 */
public class UserContentManager {
    /**
     * Temporary Generated destination file name suffix.
     */
    public static final String TEMP_DEST_SUFFIX = "tmpDocDest";

    /**
     * Generated file.
     */
    private File generatedFileCopy;

    /**
     * Map for id to UserContent EObject.
     */
    private Map<String, UserContent> mapIdUserContent;

    /**
     * Current document.
     */
    private XWPFDocument document;

    /**
     * Constructor.
     * 
     * @param destinationPathFileName
     *            Generated path file name
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
    public UserContentManager(URI destinationPathFileName) throws IOException, DocumentParserException {
        if (URIConverter.INSTANCE.exists(destinationPathFileName, Collections.EMPTY_MAP)) {
            // Copy file
            generatedFileCopy = tempCopyFile(destinationPathFileName);
            // Launch parsing
            launchParsing();
        }
    }

    /**
     * Launch Parsing.
     * 
     * @throws IOException
     *             IOException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    private void launchParsing() throws DocumentParserException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try {
            document = POIServices.getInstance()
                    .getXWPFDocument(URI.createFileURI(generatedFileCopy.getAbsolutePath()));
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // In this case, we do nothing.
            // The old output doc is not a docx document and it will be overwrite at current generation.
            // And we have nothing to extract to a no docx document.
            return;
        }
        // CHECKSTYLE:ON
        DocumentGeneratedParser documentGeneratedParser = new DocumentGeneratedParser(document, queryEnvironment);
        DocumentTemplate documentTemplate = documentGeneratedParser.parseDocument();
        final TreeIterator<EObject> iter = documentTemplate.eAllContents();
        while (iter.hasNext()) {
            EObject eObject = iter.next();
            if (eObject instanceof UserContent) {
                UserContent userContent = (UserContent) eObject;
                if (userContent.getId() != null) {
                    String id = userContent.getId();
                    if (mapIdUserContent == null) {
                        mapIdUserContent = new HashMap<>();
                    }
                    if (mapIdUserContent.containsKey(id)) {
                        // Mark message to generate lost part of document
                        TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                                ValidationMessageLevel.WARNING,
                                "The id " + id
                                    + " is already used in generated document. Ids must be unique otherwise document part contained userContent will be lost.",
                                userContent.getRuns().get(0));
                        userContent.getValidationMessages().add(templateValidationMessage);
                    } else {
                        mapIdUserContent.put(id, userContent);
                    }
                }
            }
        }
    }

    /**
     * Get User Doc Destination for an id.
     * 
     * @param id
     *            id
     * @return User Doc Destination
     */
    public UserContent getUserContent(String id) {
        if (mapIdUserContent != null && mapIdUserContent.containsKey(id)) {
            return mapIdUserContent.get(id);
        }
        return null;
    }

    /**
     * Copy file in temporary file in same folder than original file.
     * 
     * @param source
     *            source file
     * @return source copy File
     * @throws IOException
     *             IOException
     */
    private File tempCopyFile(URI source) throws IOException {
        // Create temporary file
        File dest = File.createTempFile(source.lastSegment(), TEMP_DEST_SUFFIX);
        // Copy generated file in temp file
        copyFile(source, dest);
        return dest;
    }

    /**
     * Dispose.
     * 
     * @throws IOException
     *             IOException
     */
    public void dispose() throws IOException {
        // Delete Temp Generated File.
        if (generatedFileCopy != null && generatedFileCopy.exists() && generatedFileCopy.isFile()) {
            mapIdUserContent = null;
            generatedFileCopy.delete();
        }
        // Close document
        if (document != null) {
            document.close();
        }
    }

    /**
     * Copy File.
     * 
     * @param source
     *            source file
     * @param dest
     *            destination file
     * @throws IOException
     *             IOException
     */
    private static void copyFile(URI source, File dest) throws IOException {
        try (InputStream is = URIConverter.INSTANCE.createInputStream(source);
                OutputStream os = new FileOutputStream(dest);) {
            // CHECKSTYLE:OFF
            byte[] buffer = new byte[1024];
            // CHECKSTYLE:ON
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        }
    }
}
