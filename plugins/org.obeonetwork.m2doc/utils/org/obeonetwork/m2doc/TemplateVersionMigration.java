/*******************************************************************************
 *  Copyright (c) 2022, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Migrates M2Doc version of all .docx files in the given folder.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class TemplateVersionMigration {

    /**
     * Constructor.
     */
    private TemplateVersionMigration() {
        // nothing to do here
    }

    /**
     * Migrates M2Doc version of all .docx files in the given folder.
     * 
     * @param args
     *            the folder to migrate
     */
    public static void main(String[] args) {

        for (String rootName : args) {
            final File root = new File(rootName);
            walkFolder(root);
        }
    }

    /**
     * Walks the given folder to update .docx files.
     * 
     * @param root
     *            the root folder
     */
    private static void walkFolder(File root) {
        for (File child : root.listFiles()) {
            if (child.isDirectory()) {
                walkFolder(child);
            } else if (child.getName().endsWith(".docx")) {
                final URI uri = URI.createFileURI(child.getAbsolutePath());
                if (!isEmpty(uri)) {
                    try (XWPFDocument xwpfDocument = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                            uri)) {
                        TemplateCustomProperties properties = new TemplateCustomProperties(xwpfDocument);
                        // TODO update version number
                        if ("3.3.4".equals(properties.getM2DocVersion())) {
                            properties.setM2DocVersion(M2DocUtils.VERSION);
                            properties.save();
                            POIServices.getInstance().saveFile(URIConverter.INSTANCE, xwpfDocument, uri);
                            System.out.println("Migrated: " + child.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Tells if the given {@link URI} is empty.
     * 
     * @param uri
     *            the {@link URI} to check
     * @return <code>true</code> if the given {@link URI} is empty, <code>false</code> otherwise
     */
    private static boolean isEmpty(URI uri) {
        try {
            try (InputStream inputStream = URIConverter.INSTANCE.createInputStream(uri);) {
                return URIConverter.INSTANCE.createInputStream(uri).read() == -1;
            }
        } catch (IOException e) {
            return true;
        }
    }

}
