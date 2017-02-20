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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.XmlException;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * This class manage UserDoc Destination tag UserContent.
 * It launch destination parsing doc and keep map of UserDoc id / UserContent EObject element.
 * Be careful, parsing must be done on a copy of destination document because old destination document is remove by generation.
 * 
 * @author ohaegi
 */
public class UserContentManager {

    /**
     * The error copy message.
     */
    public static final String USERDOC_COPY_ERROR = "userdoc copy error : ";

    /**
     * Temporary Generated destination file name suffix.
     */
    public static final String TEMP_DEST_SUFFIX = "tmpDocDest";

    /**
     * Buffer size.
     */
    private static final int BUFFER_SIZE = 1024 * 8;

    /**
     * The {@link DateFormat} used to log lost {@link UserContent}.
     */
    private static final DateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Generated file.
     */
    private final File generatedFileCopy;

    /**
     * Map for id to the {@link List} of .
     */
    private final Map<String, List<UserContent>> mapIdUserContent = new HashMap<String, List<UserContent>>();

    /**
     * The input {@link DocumentTemplate}.
     */
    private final DocumentTemplate documentTemplate;

    /**
     * The destination {@link URI}.
     */
    private final URI destination;

    /**
     * Constructor.
     * 
     * @param documentTemplate
     *            the input {@link DocumentTemplate}
     * @param destination
     *            the destination {@link URI}
     * @throws IOException
     *             IOException if the destination can't be copied to a temporary file
     */
    public UserContentManager(DocumentTemplate documentTemplate, URI destination) throws IOException {
        this.documentTemplate = documentTemplate;
        this.destination = destination;
        if (URIConverter.INSTANCE.exists(destination, Collections.EMPTY_MAP)) {
            // Copy file
            generatedFileCopy = tempCopyFile(destination);
            // Launch parsing
            launchParsing();
        } else {
            generatedFileCopy = null;
        }
    }

    /**
     * Launch Parsing.
     * 
     * @throws IOException
     *             IOException
     */
    private void launchParsing() throws IOException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);

        try (DocumentTemplate userDocDocument = M2DocUtils
                .parseUserContent(URI.createFileURI(generatedFileCopy.getAbsolutePath()), queryEnvironment);) {
            final TreeIterator<EObject> iter = userDocDocument.eAllContents();
            while (iter.hasNext()) {
                EObject eObject = iter.next();
                if (eObject instanceof UserContent) {
                    UserContent userContent = (UserContent) eObject;
                    if (userContent.getId() != null) {
                        final String id = userContent.getId();
                        List<UserContent> userContents = mapIdUserContent.get(id);
                        if (userContents == null) {
                            userContents = new ArrayList<UserContent>();
                            mapIdUserContent.put(id, userContents);
                        }
                        userContents.add(userContent);
                    }
                }
            }
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            // In this case, we do nothing.
            // The old output doc is not a docx document and it will be overwrite at current generation.
            // And we have nothing to extract to a no docx document.
        }
    }

    /**
     * Consumes the next {@link UserContent} with the given {@link UserContent#getId() ID}.
     * 
     * @param id
     *            the {@link UserContent#getId() ID}
     * @return the consumed {@link UserContent} if any, <code>null</code> otherwise
     */
    public UserContent consumeUserContent(String id) {
        final UserContent res;

        List<UserContent> userContents = mapIdUserContent.get(id);
        if (userContents != null && !userContents.isEmpty()) {
            res = userContents.remove(0);
            if (userContents.isEmpty()) {
                mapIdUserContent.remove(id);
            }
        } else {
            res = null;
        }

        return res;
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
    private static void copyFile(URI source, File dest) throws IOException {
        try (InputStream is = URIConverter.INSTANCE.createInputStream(source);
                OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    /**
     * Gets the {@link List} of duplicated {@link UserContent#getId() user content ID}.
     * 
     * @return the {@link List} of duplicated {@link UserContent#getId() user content ID}
     */
    public List<String> getDuplicatedUserContentIDs() {
        List<String> res = new ArrayList<String>();

        for (Entry<String, List<UserContent>> entry : mapIdUserContent.entrySet()) {
            if (entry.getValue().size() > 1) {
                res.add(entry.getKey());
            }
        }

        return res;
    }

    /**
     * Generates lost files if needed and update given {@link GenerationResult}.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @throws IOException
     *             if the lost {@link UserContent} can't be written
     * @throws InvalidFormatException
     *             if the input {@link DocumentTemplate} can't be read
     */
    public void generateLostFiles(GenerationResult result) throws IOException, InvalidFormatException {
        for (Entry<String, List<UserContent>> entry : mapIdUserContent.entrySet()) {
            final URI lostUserContentURI = getLostUserContentURI(destination, entry.getKey());
            result.getLostUserContents().put(entry.getKey(), lostUserContentURI);
            final boolean isNewUserContentLoss;
            final URI inputURI;
            if (URIConverter.INSTANCE.exists(lostUserContentURI, Collections.EMPTY_MAP)) {
                inputURI = lostUserContentURI;
                isNewUserContentLoss = false;
            } else {
                inputURI = documentTemplate.eResource().getURI();
                isNewUserContentLoss = true;
            }

            try (InputStream is = URIConverter.INSTANCE.createInputStream(inputURI);
                    OPCPackage oPackage = OPCPackage.open(is);
                    XWPFDocument destinationDocument = new XWPFDocument(oPackage);) {
                if (isNewUserContentLoss) {
                    // clear the document
                    int size = destinationDocument.getBodyElements().size();
                    for (int i = 0; i < size; i++) {
                        destinationDocument.removeBodyElement(0);
                    }
                }
                XWPFParagraph currentGeneratedParagraph = destinationDocument.createParagraph();
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.WARNING,
                        FORMAT.format(new Date()) + " - Lost user content " + entry.getKey());
                result.updateLevel(ValidationMessageLevel.WARNING);
                currentGeneratedParagraph = destinationDocument.createParagraph();

                for (UserContent userContent : entry.getValue()) {
                    final UserContentRawCopy userContentRawCopy = new UserContentRawCopy();
                    try {
                        currentGeneratedParagraph = destinationDocument.createParagraph();
                        currentGeneratedParagraph = userContentRawCopy.copy(userContent, currentGeneratedParagraph,
                                destinationDocument);
                    } catch (InvalidFormatException e) {
                        M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                                USERDOC_COPY_ERROR + e.getMessage());
                        result.updateLevel(ValidationMessageLevel.ERROR);
                    } catch (XmlException e) {
                        M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                                USERDOC_COPY_ERROR + e.getMessage());
                        result.updateLevel(ValidationMessageLevel.ERROR);
                    } catch (IOException e) {
                        M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                                USERDOC_COPY_ERROR + e.getMessage());
                        result.updateLevel(ValidationMessageLevel.ERROR);
                    }
                }

                POIServices.getInstance().saveFile(destinationDocument, lostUserContentURI);
            }
        }
    }

    /**
     * Gets the lost {@link UserContent} {@link URI} for the given destination {@link URI} and {@link UserContent#getId() user content ID}.
     * 
     * @param dest
     *            the destination {@link URI}
     * @param id
     *            the {@link UserContent#getId() user content ID}
     * @return the lost {@link UserContent} {@link URI} for the given destination {@link URI} and {@link UserContent#getId() user content
     *         ID}
     */
    protected URI getLostUserContentURI(URI dest, String id) {
        final URI res = URI.createURI("./" + dest.lastSegment() + "-" + id + "-lost.docx");

        return res.resolve(dest);
    }

}
