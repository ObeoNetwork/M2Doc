/*******************************************************************************
 *  Copyright (c) 2023 Obeo. 
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
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AndFileFilter;
import org.apache.commons.io.filefilter.EmptyFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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

    private static final IOFileFilter NON_EMPTY_TEMPLATE = new AndFileFilter(FileFileFilter.INSTANCE,
            EmptyFileFilter.NOT_EMPTY, new WildcardFileFilter("*." + M2DocUtils.DOCX_EXTENSION_FILE));

    /**
     * Migrate templates of given folders toward the {@link M2DocUtils#VERSION current version} of M2Doc.
     * 
     * @param args
     *            version to migrate then folders to migrate: 3.3.0 /mnt/development/git/M2Doc /mnt/development/git/M2Doc-gh-pages
     */
    public static void main(String[] args) {
        if (args.length >= 2) {
            final String oldVersion = args[0];
            final URIConverter uriConverter = URIConverter.INSTANCE;

            for (int i = 1; i < args.length; i++) {
                final File directoryToMigrate = new File(args[i]);
                final Collection<File> files = FileUtils.listFiles(directoryToMigrate, NON_EMPTY_TEMPLATE,
                        TrueFileFilter.INSTANCE);
                for (File file : files) {
                    if (file.getName().toLowerCase().endsWith("." + M2DocUtils.DOCX_EXTENSION_FILE)) {
                        System.out.print("Migrating: " + file.getAbsolutePath() + "...");
                        final URI templateURI = URI.createFileURI(file.getAbsolutePath());
                        try (XWPFDocument xwpfDocument = POIServices.getInstance().getXWPFDocument(uriConverter,
                                templateURI)) {
                            TemplateCustomProperties properties = new TemplateCustomProperties(xwpfDocument);
                            if (oldVersion.equals(properties.getM2DocVersion())) {
                                properties.setM2DocVersion(M2DocUtils.VERSION);
                                properties.save();
                                POIServices.getInstance().saveFile(uriConverter, xwpfDocument, templateURI);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(" done.");
                    }
                }
            }
        } else

        {
            System.out.println("USAGE: 3.3.0 /mnt/development/git/M2Doc /mnt/development/git/M2Doc-gh-pages");
        }

    }

}
