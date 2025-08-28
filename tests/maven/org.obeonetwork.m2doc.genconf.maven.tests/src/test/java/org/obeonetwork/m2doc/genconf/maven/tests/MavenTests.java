/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.maven.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.util.ClassProvider;
import org.obeonetwork.m2doc.util.IClassProvider;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Maven wikitext test class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class MavenTests {

	/**
	 * The {@link MemoryURIHandler}.
	 */
	private final MemoryURIHandler uriHandler = new MemoryURIHandler();

	@Test
	public void maven() throws DocumentGenerationException, IOException, DocumentParserException {
		GenconfPackage.eINSTANCE.getName(); // register the GenconfPackage

		final URI genconfURI = URI.createFileURI(new File(
				"src/main/java/org/obeonetwork/m2doc/genconf/maven/tests/nominal.genconf").getAbsolutePath());

		final ResourceSet resourceSetForGenconf = new ResourceSetImpl();
		resourceSetForGenconf.getURIConverter().getURIHandlers().add(0, uriHandler);
		resourceSetForGenconf.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
				new XMIResourceFactoryImpl());
		final Generation generation = GenconfUtils.getGeneration(resourceSetForGenconf, genconfURI);

		final Map<String, String> options = GenconfUtils.getOptions(generation);
		final List<Exception> exceptions = new ArrayList<>();

		final ResourceSet resourceSetForModels = AQLUtils.createResourceSetForModels(exceptions, this,
				new ResourceSetImpl(), options);
		resourceSetForModels.getURIConverter().getURIHandlers().add(0, uriHandler);
		resourceSetForModels.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
				new XMIResourceFactoryImpl());

		final IClassProvider classProvider = new ClassProvider(this.getClass().getClassLoader());
		final Monitor monitor = new BasicMonitor();
		try {

			final boolean hasErrors = GenconfUtils.validate(generation, resourceSetForModels, options,
					exceptions, classProvider, monitor);
			assertEquals(false, hasErrors);

			final List<URI> generatedURIs = GenconfUtils.generate(generation, resourceSetForModels, options,
					classProvider, monitor);

			assertEquals(1, generatedURIs.size());
			assertTrue(uriHandler.exists(generatedURIs.get(0), null));
		} finally {
			AQLUtils.cleanResourceSetForModels(this, resourceSetForModels);
			uriHandler.clear();
		}
	}

}
