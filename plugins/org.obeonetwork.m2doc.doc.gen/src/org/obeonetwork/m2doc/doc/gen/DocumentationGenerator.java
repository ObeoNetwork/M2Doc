/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.doc.gen;

import static org.obeonetwork.m2doc.doc.gen.M2DocHelpContentUtils.md;
import static org.obeonetwork.m2doc.doc.gen.M2DocHelpContentUtils.mdBody;
import static org.obeonetwork.m2doc.doc.gen.M2DocHelpContentUtils.mdHead;
import static org.obeonetwork.m2doc.doc.gen.M2DocHelpContentUtils.mdHeader;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.m2doc.html.services.M2DocHTMLServices;
import org.obeonetwork.m2doc.ide.ui.services.M2DocEObjectServices;
import org.obeonetwork.m2doc.ide.ui.services.SWTPromptServices;
import org.obeonetwork.m2doc.services.BooleanServices;
import org.obeonetwork.m2doc.services.ExcelServices;
import org.obeonetwork.m2doc.services.ImageServices;
import org.obeonetwork.m2doc.services.LinkServices;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.obeonetwork.m2doc.services.PromptServices;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;
import org.obeonetwork.m2doc.wikitext.services.M2DocWikiTextServices;

/**
 * Utility class used to generate the M2Doc documentation.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public final class DocumentationGenerator {

	/**
	 * The name of the charset to use to write the documentation.
	 */
	private static final String UTF8 = "UTF-8"; //$NON-NLS-1$

	/**
	 * The list of the service providers to consider for the standard documentation.
	 */
	private static final Class<?>[] STANDARD_SERVICE_PROVIDERS = new Class<?>[] { BooleanServices.class,
			ImageServices.class, LinkServices.class, PaginationServices.class, M2DocSiriusServices.class,
			ExcelServices.class, M2DocHTMLServices.class, PromptServices.class, SWTPromptServices.class,
			M2DocEObjectServices.class, M2DocWikiTextServices.class};

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

		File documentationFolder = pluginFolder;

		// services-toc.xml
		StringBuffer buffer = M2DocHelpContentUtils.computeServicesToc(STANDARD_SERVICE_PROVIDERS);
		try {
			File tocFile = new File(pluginFolder, "services-toc.xml");
			System.out.println("Writing the content of toc.xml in " + tocFile.getAbsolutePath());
			try (PrintWriter writer = new PrintWriter(tocFile, UTF8);) {
				writer.print(buffer.toString());
			}
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		// Services
		StringBuffer aggregated = new StringBuffer();
		for (Class<?> serviceProviderClass : STANDARD_SERVICE_PROVIDERS) {
			if (serviceProviderClass.isAnnotationPresent(ServiceProvider.class)) {
				try {
					List<StringBuffer> sections = M2DocHelpContentUtils.mdServiceSections(serviceProviderClass);
					StringBuffer stringBuffer = md(mdHead(), mdBody(mdHeader(false), sections));

					File file = new File(documentationFolder, M2DocHelpContentUtils.M2DOC_HREF_PREFIX
							+ serviceProviderClass.getSimpleName().toLowerCase() + ".md");
					System.out.println("Writing content of " + file.getAbsolutePath());
					try (PrintWriter writer = new PrintWriter(file, UTF8);) {
						writer.print(stringBuffer.toString());
					}

					/*
					 * generating a documentation aggregating all the services at once.
					 */
					List<StringBuffer> sectionsForAggregatedServices = M2DocHelpContentUtils.htmlServiceSections(
							serviceProviderClass, 3, M2DocHelpContentUtils.METHOD_SIGNATURE_GENERATOR_2016);
					for (StringBuffer b : sectionsForAggregatedServices) {
						aggregated.append(b);
					}

				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
