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
package org.obeonetwork.m2doc.html.maven.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.impl.namespace.ClassLoaderQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.ILoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.acceleo.query.services.configurator.AQLServiceConfigurator;
import org.eclipse.acceleo.query.services.configurator.ServicesConfiguratorDescriptor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.html.services.HTMLServicesConfigurator;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.services.namespace.M2DocDocumentTemplateLoader;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Maven html test class.
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
		final URI templateURI = URI.createFileURI(new File(
				"src/main/java/org/obeonetwork/m2doc/html/maven/tests/main.docx").getAbsolutePath());
		final URI outputURI = URI.createURI(MemoryURIHandler.PROTOCOL + "://test");

		final Map<String, String> options = new HashMap<String, String>();
		options.put(M2DocUtils.TEMPLATE_URI_OPTION, templateURI.toString());
		options.put(M2DocUtils.RESULT_URI_OPTION, outputURI.toString());

		AQLUtils.registerServicesConfigurator(new ServicesConfiguratorDescriptor(M2DocUtils.M2DOC_LANGUAGE,
				new HTMLServicesConfigurator()));
		AQLUtils.registerServicesConfigurator(new ServicesConfiguratorDescriptor(AQLUtils.AQL_LANGUAGE,
				new AQLServiceConfigurator()));

		List<Exception> exceptions = new ArrayList<>();

		final ResourceSet resourceSetForModels = AQLUtils.createResourceSetForModels(exceptions, this,
				new ResourceSetImpl(), options);
		resourceSetForModels.getURIConverter().getURIHandlers().add(0, uriHandler);
		resourceSetForModels.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
				new XMIResourceFactoryImpl());

		final Registry ePackageRegistry = EPackage.Registry.INSTANCE;
		final IQualifiedNameResolver resolver = new ClassLoaderQualifiedNameResolver(this.getClass()
				.getClassLoader(), ePackageRegistry, M2DocUtils.QUALIFIER_SEPARATOR);
		final IQualifiedNameQueryEnvironment queryEnvironment = M2DocUtils.getQueryEnvironment(resolver,
				resourceSetForModels, templateURI, options, false);
		final M2DocEvaluationEnvironment m2docEnv = new M2DocEvaluationEnvironment(resolver,
				resourceSetForModels, templateURI, outputURI);

		resolver.addLoader(new M2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(),
				M2DocUtils.QUALIFIER_SEPARATOR));
		final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
		resolver.addLoader(javaLoader);

		final Monitor monitor = new BasicMonitor();
		try (DocumentTemplate documentTemplate = (DocumentTemplate)resolver.resolve(
				"org.obeonetwork.m2doc.html.maven.tests.main")) {
			M2DocUtils.prepareEnvironment(queryEnvironment, ePackageRegistry, documentTemplate);

			final ValidationMessageLevel validationLevel = M2DocUtils.validate(documentTemplate,
					queryEnvironment, monitor);
			assertEquals(ValidationMessageLevel.OK, validationLevel);

			final Map<String, Object> variables = new HashMap<String, Object>();

			final GenerationResult generationResult = M2DocUtils.generate(m2docEnv, documentTemplate,
					variables, false, monitor);

			assertEquals(ValidationMessageLevel.OK, generationResult.getLevel());
			assertTrue(uriHandler.exists(outputURI, null));
		} finally {
			AQLUtils.cleanResourceSetForModels(this, resourceSetForModels);
			AQLUtils.cleanServices(M2DocUtils.M2DOC_LANGUAGE, queryEnvironment, resourceSetForModels);
			uriHandler.clear();
		}
	}

}
