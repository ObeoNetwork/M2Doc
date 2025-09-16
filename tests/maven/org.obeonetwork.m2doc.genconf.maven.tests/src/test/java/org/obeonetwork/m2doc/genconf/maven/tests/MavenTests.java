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
import org.eclipse.acceleo.query.runtime.impl.namespace.ClassLoaderQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.ILoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
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
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.namespace.M2DocDocumentTemplateLoader;
import org.obeonetwork.m2doc.util.M2DocUtils;
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

		final Monitor monitor = new BasicMonitor();
		try {

			IQualifiedNameResolver resolver = new ClassLoaderQualifiedNameResolver(this.getClass()
					.getClassLoader(), resourceSetForModels.getPackageRegistry(),
					M2DocUtils.QUALIFIER_SEPARATOR);
			M2DocEvaluationEnvironment m2docEnv = GenconfUtils.createM2DocEvaluationEnvironment(generation,
					resolver, resourceSetForModels);

			resolver.addLoader(new M2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(),
					M2DocUtils.QUALIFIER_SEPARATOR));
			final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
			resolver.addLoader(javaLoader);

			final boolean hasErrors = GenconfUtils.validate(generation, m2docEnv, options, exceptions,
					monitor);
			assertEquals(false, hasErrors);

			final List<URI> generatedURIs = GenconfUtils.generate(generation, m2docEnv, options, monitor);

			assertEquals(1, generatedURIs.size());
			assertTrue(uriHandler.exists(generatedURIs.get(0), null));
		} finally {
			AQLUtils.cleanResourceSetForModels(this, resourceSetForModels);
			uriHandler.clear();
		}
	}

}
