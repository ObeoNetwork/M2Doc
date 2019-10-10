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
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

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
     * Buffer size.
     */
    private static final int BUFFER_SIZE = 1024 * 8;

    /**
     * The {@link DateFormat} used to log lost {@link UserContent}.
     */
    private final DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * The memory copy {@link URI}.
     */
    private URI memoryCopy;

    /**
     * Map for id to the {@link List} of {@link UserContent}.
     */
    private Map<String, List<UserContent>> mapIdUserContent;

    /**
     * The source {@link URI}.
     */
    private final URI sourceURI;

    /**
     * The destination {@link URI}.
     */
    private final URI destinationURI;

    /**
     * The URI converter to use.
     */
    private final URIConverter uriConverter;

    /**
     * The {@link MemoryURIHandler}.
     */
    private final MemoryURIHandler uriHandler = new MemoryURIHandler();

    /**
     * The {@link DocumentTemplate}.
     */
    private DocumentTemplate userDocDocument;

    /**
     * Constructor.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param sourceURI
     *            the source {@link URI}
     * @param destinationURI
     *            the destination {@link URI}
     */
    public UserContentManager(URIConverter uriConverter, URI sourceURI, URI destinationURI) {
        this.uriConverter = uriConverter;
        this.sourceURI = sourceURI;
        this.destinationURI = destinationURI;
        if (uriConverter != null && destinationURI != null
            && uriConverter.exists(destinationURI, Collections.EMPTY_MAP)) {
            // Copy file
            uriConverter.getURIHandlers().add(0, uriHandler);
            memoryCopy = memoryCopy(destinationURI);
        } else {
            memoryCopy = null;
        }
    }

    /**
     * Launch Parsing.
     */
    private void launchParsing() {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        mapIdUserContent = new HashMap<>();
        if (memoryCopy != null) {
            try {
                userDocDocument = M2DocUtils.parseUserContent(uriConverter, memoryCopy, queryEnvironment);
                final TreeIterator<EObject> iter = userDocDocument.eAllContents();
                while (iter.hasNext()) {
                    EObject eObject = iter.next();
                    if (eObject instanceof UserContent) {
                        UserContent userContent = (UserContent) eObject;
                        storeUserContent(mapIdUserContent, userContent);
                    }
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                // In this case, we do nothing.
                // The old output doc is not a docx document and it will be overwrite at current generation.
                // And we have nothing to extract from a no docx file.
            } finally {
                uriConverter.getURIHandlers().remove(uriHandler);
                uriHandler.clear();
            }
        }
    }

    /**
     * Stores the given {@link UserContent} in the given {@link Map}.
     * 
     * @param idToUserContent
     *            the {@link Map}
     * @param userContent
     *            the {@link UserContent}
     */
    private void storeUserContent(Map<String, List<UserContent>> idToUserContent, UserContent userContent) {
        if (userContent.getId() != null) {
            final String id = userContent.getId();
            List<UserContent> userContents = idToUserContent.get(id);
            if (userContents == null) {
                userContents = new ArrayList<>();
                idToUserContent.put(id, userContents);
            }
            userContents.add(userContent);
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

        if (mapIdUserContent == null) {
            launchParsing();
        }

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
     */
    private URI memoryCopy(URI source) {
        final URI res;

        final URI uri = URI.createURI(MemoryURIHandler.PROTOCOL + "://resources/" + source.lastSegment(), false);
        if (copy(source, uri)) {
            res = uri;
        } else {
            res = null;
        }
        return res;
    }

    /**
     * Dispose.
     * 
     * @throws IOException
     *             IOException
     */
    public void dispose() throws IOException {
        uriHandler.clear();
        if (userDocDocument != null) {
            userDocDocument.close();
        }
    }

    /**
     * Copy File.
     * 
     * @param source
     *            source {@link URI}
     * @param dest
     *            destination {@link URI}
     * @return <code>true</code> if the copy succeed, <code>false</code> otherwise
     */
    private boolean copy(URI source, URI dest) {
        boolean res = true;

        try (InputStream is = uriConverter.createInputStream(source);
                OutputStream os = uriConverter.createOutputStream(dest);) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException e) {
            // can't copy means no user contents
            res = false;
        }

        return res;
    }

    /**
     * Gets the {@link List} of duplicated {@link UserContent#getId() user content ID}.
     * 
     * @return the {@link List} of duplicated {@link UserContent#getId() user content ID}
     */
    public List<String> getDuplicatedUserContentIDs() {
        List<String> res = new ArrayList<>();

        if (mapIdUserContent != null) {
            for (Entry<String, List<UserContent>> entry : mapIdUserContent.entrySet()) {
                if (entry.getValue().size() > 1) {
                    res.add(entry.getKey());
                }
            }
        }

        return res;
    }

    /**
     * Generates lost files if needed and update given {@link GenerationResult}.
     * 
     * @param result
     *            the {@link GenerationResult}
     * @param copier
     *            the {@link RawCopier}
     * @throws IOException
     *             if the lost {@link UserContent} can't be written
     * @throws InvalidFormatException
     *             if the input {@link DocumentTemplate} can't be read
     */
    public void generateLostFiles(GenerationResult result, RawCopier copier)
            throws IOException, InvalidFormatException {

        if (mapIdUserContent == null) {
            launchParsing();
        }

        for (Entry<String, List<UserContent>> entry : mapIdUserContent.entrySet()) {
            final URI lostUserContentURI = getLostUserContentURI(destinationURI, entry.getKey());
            result.getLostUserContents().put(entry.getKey(), lostUserContentURI);
            final boolean isNewUserContentLoss;
            final URI inputURI;
            if (uriConverter.exists(lostUserContentURI, Collections.EMPTY_MAP)) {
                inputURI = lostUserContentURI;
                isNewUserContentLoss = false;
            } else {
                inputURI = sourceURI;
                isNewUserContentLoss = true;
            }

            try (InputStream is = uriConverter.createInputStream(inputURI);
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
                result.addMessage(M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.WARNING,
                        format.format(new Date()) + " - Lost user content " + entry.getKey()));
                currentGeneratedParagraph = destinationDocument.createParagraph();

                for (UserContent userContent : entry.getValue()) {
                    try {
                        currentGeneratedParagraph = destinationDocument.createParagraph();
                        currentGeneratedParagraph = copier.copyUserContent(userContent, currentGeneratedParagraph);
                        // CHECKSTYLE:OFF
                    } catch (Exception e) {
                        // CHECKSTYLE:ON
                        result.addMessage(M2DocUtils.appendMessageRun(currentGeneratedParagraph,
                                ValidationMessageLevel.ERROR, USERDOC_COPY_ERROR + e.getMessage()));
                    }
                }

                POIServices.getInstance().saveFile(uriConverter, destinationDocument, lostUserContentURI);
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
        final URI res = URI.createURI("./" + dest.lastSegment() + "-" + id + "-lost.docx", false);

        return res.resolve(dest);
    }

}
