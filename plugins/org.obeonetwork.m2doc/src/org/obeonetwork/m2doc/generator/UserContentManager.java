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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
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
    public UserContentManager(String destinationPathFileName) throws IOException, DocumentParserException {
        File lastGeneratedFile = new File(destinationPathFileName);
        if (lastGeneratedFile.exists() && lastGeneratedFile.isFile()) {
            // Copy file
            generatedFileCopy = tempCopyFile(lastGeneratedFile);
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
    private void launchParsing() throws IOException, DocumentParserException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        XWPFDocument document = POIServices.getInstance().getXWPFDocument(generatedFileCopy.getAbsolutePath());
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
                        mapIdUserContent = new HashMap<String, UserContent>();
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
    private File tempCopyFile(File source) throws IOException {
        // Create temporary file
        File dest = File.createTempFile(source.getName(), TEMP_DEST_SUFFIX, source.getParentFile());
        // Copy generated file in temp file
        copyFile(source, dest);
        return dest;
    }

    /**
     * Delete Temp Generated File.
     */
    public void deleteTempGeneratedFile() {
        if (generatedFileCopy != null && generatedFileCopy.exists() && generatedFileCopy.isFile()) {
            mapIdUserContent = null;
            generatedFileCopy.delete();
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
    private static void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            // CHECKSTYLE:OFF
            byte[] buffer = new byte[1024];
            // CHECKSTYLE:ON
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }
}
