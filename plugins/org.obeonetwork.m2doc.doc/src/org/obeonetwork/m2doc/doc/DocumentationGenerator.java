/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.doc;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.m2doc.services.BooleanServices;
import org.obeonetwork.m2doc.services.ImageServices;
import org.obeonetwork.m2doc.services.LinkServices;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;

import static org.obeonetwork.m2doc.doc.M2DocHelpContentUtils.body;
import static org.obeonetwork.m2doc.doc.M2DocHelpContentUtils.head;
import static org.obeonetwork.m2doc.doc.M2DocHelpContentUtils.header;
import static org.obeonetwork.m2doc.doc.M2DocHelpContentUtils.html;

/**
 * Utility class used to generate the M2Doc documentation.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public final class DocumentationGenerator {

    /**
     * 
     */
    private static final String TRIGGER_TO_APPEND_STD_DOC = "<p>These sections are listing all the services of the standard library of M2Doc.</p>";

    /**
     * The name of the charset to use to write the documentation.
     */
    private static final String UTF8 = "UTF-8"; //$NON-NLS-1$

    /**
     * The list of the service providers to consider for the standard
     * documentation.
     */
    // CHECKSTYLE:OFF
    private static final Class<?>[] STANDARD_SERVICE_PROVIDERS = new Class<?>[] {BooleanServices.class,
        ImageServices.class, LinkServices.class, PaginationServices.class, M2DocSiriusServices.class,};
    // CHECKSTYLE:ON

    /**
     * The constructor.
     */
    private DocumentationGenerator() {
        // Prevent instantiation
    }

    /**
     * Launches the generation of the documentation.
     * 
     * @param args
     *            The arguments of the generation
     */
    public static void main(String[] args) {
        File pluginFolder = new File(args[0]);

        System.out.println("Prepare the generation of the documentation for " + pluginFolder.getAbsolutePath());

        File inputFolder = new File(pluginFolder, "input"); //$NON-NLS-1$
        File documentationFolder = new File(pluginFolder, "pages"); //$NON-NLS-1$
        File indexHtmlFile = new File(documentationFolder, "index.html"); //$NON-NLS-1$

        // toc.xml
        StringBuffer buffer = M2DocHelpContentUtils.computeToc(STANDARD_SERVICE_PROVIDERS);
        try {
            File tocFile = new File(pluginFolder, "toc.xml");
            System.out.println("Writing the content of toc.xml in " + tocFile.getAbsolutePath());
            Files.write(buffer, tocFile, Charset.forName(UTF8));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        // Services
        StringBuffer aggregated = new StringBuffer();
        for (Class<?> serviceProviderClass : STANDARD_SERVICE_PROVIDERS) {
            if (serviceProviderClass.isAnnotationPresent(ServiceProvider.class)) {
                try {
                    List<StringBuffer> sections = M2DocHelpContentUtils.computeServiceSections(serviceProviderClass);
                    StringBuffer stringBuffer = html(head(), body(header(false), sections));

                    File file = new File(documentationFolder, M2DocHelpContentUtils.M2DOC_HREF_PREFIX
                        + serviceProviderClass.getSimpleName().toLowerCase() + ".html");
                    System.out.println("Writing content of " + file.getAbsolutePath());
                    Files.write(stringBuffer, file, Charset.forName(UTF8));

                    /*
                     * generating a documentation aggregating all the services
                     * at once.
                     */
                    List<StringBuffer> sectionsForAggregatedServices = M2DocHelpContentUtils.computeServiceSections(
                            serviceProviderClass, 3, M2DocHelpContentUtils.METHOD_SIGNATURE_GENERATOR_2016);
                    for (StringBuffer b : sectionsForAggregatedServices) {
                        aggregated.append(b);
                    }

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }

        // index.html
        try {
            List<StringBuffer> sections = M2DocHelpContentUtils.computeM2DocOverviewSections();

            String inputHtmlContent = Files.toString(new File(inputFolder, "index.html"), Charset.forName(UTF8));
            int indexOfBodyStart = inputHtmlContent.indexOf("<body>");
            if (indexOfBodyStart != -1 && indexOfBodyStart + 6 < inputHtmlContent.length()) {
                inputHtmlContent = inputHtmlContent.substring(indexOfBodyStart + 6);
            }
            int indexOfBodyEnd = inputHtmlContent.indexOf("</body>");
            if (indexOfBodyEnd != -1) {
                inputHtmlContent = inputHtmlContent.substring(0, indexOfBodyEnd);
            }
            sections.add(new StringBuffer(inputHtmlContent));

            StringBuffer stringBuffer = html(head(), body(header(true), sections));
            String out = stringBuffer.toString().replace(TRIGGER_TO_APPEND_STD_DOC,
                    TRIGGER_TO_APPEND_STD_DOC + "\n" + aggregated);

            System.out.println("Writing content of " + indexHtmlFile.getAbsolutePath());
            Files.write(out, indexHtmlFile, Charset.forName(UTF8));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
