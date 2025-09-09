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
package org.obeonetwork.m2doc.maven.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.ClassProvider;
import org.obeonetwork.m2doc.util.IClassProvider;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Maven test class.
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
				"src/main/java/org/obeonetwork/m2doc/maven/tests/main.docx").getAbsolutePath());

		final Map<String, String> options = new HashMap<String, String>();
		List<Exception> exceptions = new ArrayList<>();

		final ResourceSet resourceSetForModels = AQLUtils.createResourceSetForModels(exceptions, this,
				new ResourceSetImpl(), options);
		resourceSetForModels.getURIConverter().getURIHandlers().add(0, uriHandler);
		resourceSetForModels.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
				new XMIResourceFactoryImpl());

		final IQueryEnvironment queryEnvironment = M2DocUtils.getQueryEnvironment(resourceSetForModels,
				templateURI, options, false);

		final IClassProvider classProvider = new ClassProvider(this.getClass().getClassLoader());
		final Monitor monitor = new BasicMonitor();
		try (DocumentTemplate template = M2DocUtils.parse(resourceSetForModels.getURIConverter(), templateURI,
				monitor)) {
			M2DocUtils.prepareEnvironment(queryEnvironment, classProvider, template);

			final ValidationMessageLevel validationLevel = M2DocUtils.validate(template, queryEnvironment,
					monitor);
			assertEquals(ValidationMessageLevel.OK, validationLevel);

			final URI modelURI = URI.createFileURI(new File(
					"src/main/java/org/obeonetwork/m2doc/maven/tests/anydsl.ecore").getAbsolutePath());
			final Resource resource = resourceSetForModels.getResource(modelURI, true);

			final Map<String, Object> variables = new HashMap<String, Object>();
			variables.put("self", resource.getContents().get(0));

			final URI outputURI = URI.createURI(MemoryURIHandler.PROTOCOL + "://test");
			final GenerationResult generationResult = M2DocUtils.generate(template, queryEnvironment,
					variables, resourceSetForModels, outputURI, false, monitor);

			assertEquals(ValidationMessageLevel.OK, generationResult.getLevel());
			assertTrue(uriHandler.exists(outputURI, null));
		} finally {
			AQLUtils.cleanResourceSetForModels(this, resourceSetForModels);
			uriHandler.clear();
		}
	}

}
