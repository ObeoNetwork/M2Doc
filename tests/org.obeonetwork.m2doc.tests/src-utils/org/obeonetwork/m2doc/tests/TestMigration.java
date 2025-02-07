/*******************************************************************************
 *  Copyright (c) 2023, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Migrate templates of given folders toward the {@link M2DocUtils#VERSION current version} of M2Doc.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TestMigration {

    /**
     * Migrates M2Doc version of all .docx files in the given folder.
     * 
     * @param args
     *            version to migrate then folders to migrate: 3.3.0 /mnt/development/git/M2Doc /mnt/development/git/M2Doc-gh-pages
     */
    public static void main(String[] args) {
        if (args.length >= 2) {
            for (int i = 1; i < args.length; i++) {
                final String rootName = args[i];
                final File root = new File(rootName);
                walkFolder(root, args[0]);
            }
        } else {
            System.out.println("USAGE: 3.3.0 /mnt/development/git/M2Doc /mnt/development/git/M2Doc-gh-pages");
        }
    }

    /**
     * Walks the given folder to update .docx files.
     * 
     * @param root
     *            the root folder
     * @param oldVersion
     *            the old version to migrate
     */
    private static void walkFolder(File root, String oldVersion) {
        for (File child : root.listFiles()) {
            if (child.isDirectory()) {
                walkFolder(child, oldVersion);
            } else if (child.getName().endsWith(".docx")) {
                final URI uri = URI.createFileURI(child.getAbsolutePath());
                if (!isEmpty(uri)) {
                    try (XWPFDocument xwpfDocument = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                            uri)) {
                        TemplateCustomProperties properties = new TemplateCustomProperties(xwpfDocument);
                        if (oldVersion.equals(properties.getM2DocVersion())) {
                            properties.setM2DocVersion(M2DocUtils.VERSION);
                            properties.save();
                            POIServices.getInstance().saveFile(URIConverter.INSTANCE, xwpfDocument, uri);
                            System.out.println("Migrated: " + child.getAbsolutePath());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else if (child.getName().endsWith(".test")) {
                // RCPTT tests
                final URI uri = URI.createFileURI(child.getAbsolutePath());
                String content;
                try (InputStream input = URIConverter.INSTANCE.createInputStream(uri)) {
                    content = AbstractTemplatesTestSuite.getContent(input, StandardCharsets.UTF_8.name());
                } catch (IOException e) {
                    e.printStackTrace();
                    content = null;
                }
                if (content != null && content.contains(oldVersion)) {
                    content = content.replace(oldVersion, M2DocUtils.VERSION);
                    try (OutputStream output = URIConverter.INSTANCE.createOutputStream(uri)) {
                        AbstractTemplatesTestSuite.setContent(output, StandardCharsets.UTF_8.name(), content);
                        System.out.println("Migrated: " + child.getAbsolutePath());
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
                return inputStream.read() == -1;
            }
        } catch (IOException e) {
            return true;
        }
    }

}
