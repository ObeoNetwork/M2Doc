/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.services.configurator.ServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link GenconfUtils}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenconfUtilsTests {

    /**
     * Test {@link IServicesConfigurator}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static final class TestServiceConfigurator implements IServicesConfigurator {

        /**
         * The option name.
         */
        public static final String OPTION = "option";

        /**
         * The option value.
         */
        public static final String VALUE = "value";

        @Override
        public List<String> getOptions() {
            final List<String> res = new ArrayList<String>();

            res.add(OPTION);

            return res;
        }

        @Override
        public Map<String, String> getInitializedOptions(Map<String, String> options) {
            final Map<String, String> res = new LinkedHashMap<String, String>();

            res.put(OPTION, VALUE);

            return res;
        }

        @Override
        public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
                Map<String, String> options) {
            final Map<String, List<Diagnostic>> res = new LinkedHashMap<String, List<Diagnostic>>();

            if (options.containsKey(OPTION)) {
                if (!VALUE.equals(options.get(OPTION))) {
                    final List<Diagnostic> diagnostic = new ArrayList<Diagnostic>();
                    diagnostic.add(new BasicDiagnostic());
                }
            }

            return res;
        }

        @Override
        public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
            // nothing to do here
            return Collections.emptySet();
        }

        @Override
        public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment) {
            // nothing to do here
        }
    }

    /**
     * The {@link TestServiceConfigurator}.
     */
    private static final ServicesConfiguratorDescriptor CONFIGURATOR = new ServicesConfiguratorDescriptor(
            new TestServiceConfigurator());

    @BeforeClass
    public static void beforeClass() {
        M2DocUtils.registerServicesConfigurator(CONFIGURATOR);
    }

    @AfterClass
    public static void afterClass() {
        M2DocUtils.unregisterServicesConfigurator(CONFIGURATOR);
    }

    @Test
    public void getOptionsNullResource() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(0, options.size());
    }

    @Test
    public void getOptionsNullResourceURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl();
        resource.getContents().add(generation);

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(0, options.size());
    }

    @Test
    public void getOptionsGenconfURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createURI("test"));
        resource.getContents().add(generation);

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(1, options.size());
        assertEquals("test", options.get(GenconfUtils.GENCONF_URI_OPTION));
    }

    @Test
    public void getOptionsTemplateURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        generation.setTemplateFileName("test.docx");

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(1, options.size());
        assertEquals("test.docx", options.get(GenconfUtils.TEMPLATE_URI_OPTION));
    }

    @Test
    public void getOptionsResultURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        generation.setResultFileName("test.docx");

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(1, options.size());
        assertEquals("test.docx", options.get(GenconfUtils.RESULT_URI_OPTION));
    }

    @Test
    public void getOptionsNoOptions() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createURI("generation.xmi"));
        resource.getContents().add(generation);

        generation.setTemplateFileName("template.docx");
        generation.setResultFileName("result.docx");

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(3, options.size());
        assertEquals("generation.xmi", options.get(GenconfUtils.GENCONF_URI_OPTION));
        assertEquals("template.docx", options.get(GenconfUtils.TEMPLATE_URI_OPTION));
        assertEquals("result.docx", options.get(GenconfUtils.RESULT_URI_OPTION));
    }

    @Test
    public void getOptions() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createURI("generation.xmi"));
        resource.getContents().add(generation);

        generation.setTemplateFileName("template.docx");
        generation.setResultFileName("result.docx");

        final Option option1 = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
        option1.setName("option1");
        option1.setValue("value1");
        generation.getOptions().add(option1);

        final Option option2 = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
        option2.setName("option2");
        option2.setValue("value2");
        generation.getOptions().add(option2);

        final Option option3 = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
        option3.setName("option3");
        option3.setValue("value3");
        generation.getOptions().add(option3);

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(6, options.size());
        assertEquals("generation.xmi", options.get(GenconfUtils.GENCONF_URI_OPTION));
        assertEquals("template.docx", options.get(GenconfUtils.TEMPLATE_URI_OPTION));
        assertEquals("result.docx", options.get(GenconfUtils.RESULT_URI_OPTION));
        assertEquals("value1", options.get("option1"));
        assertEquals("value2", options.get("option2"));
        assertEquals("value3", options.get("option3"));
    }

    @Test
    public void initializeOptionsNotExistingOption() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        GenconfUtils.initializeOptions(generation);

        assertEquals(1, generation.getOptions().size());
        assertEquals(TestServiceConfigurator.OPTION, generation.getOptions().get(0).getName());
        assertEquals(TestServiceConfigurator.VALUE, generation.getOptions().get(0).getValue());
    }

    @Test
    public void initializeOptionsExistingOption() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
        option.setName(TestServiceConfigurator.OPTION);
        generation.getOptions().add(option);

        GenconfUtils.initializeOptions(generation);

        assertEquals(1, generation.getOptions().size());
        assertEquals(TestServiceConfigurator.OPTION, generation.getOptions().get(0).getName());
        assertEquals(TestServiceConfigurator.VALUE, generation.getOptions().get(0).getValue());
    }

    @Test
    public void getResolvedURINullResource() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        final URI uri = GenconfUtils.getResolvedURI(generation, URI.createURI("test"));

        assertEquals("test", uri.toString());
    }

    @Test
    public void getResolvedURINullResourceURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl();
        resource.getContents().add(generation);

        final URI uri = GenconfUtils.getResolvedURI(generation, URI.createURI("test"));

        assertEquals("test", uri.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getResolvedRelativeResourceURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createURI("test/test.genconf"));
        resource.getContents().add(generation);

        final URI uri = GenconfUtils.getResolvedURI(generation, URI.createURI("test"));

        assertEquals("test", uri.toString());
    }

    @Test
    public void getResolvedAbsoluteResourceURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createFileURI("/test/test.genconf"));
        resource.getContents().add(generation);

        final URI uri = GenconfUtils.getResolvedURI(generation, URI.createURI("test"));

        assertEquals("file:/test/test", uri.toString());
    }

    @Test
    public void getNewDefinitionsNotExistingModelDefinition() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        try (final XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", "ecore::EClass");

            final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, properties);

            assertEquals(1, definitions.size());
            assertTrue(definitions.get(0) instanceof ModelDefinition);
            assertEquals("variable", definitions.get(0).getKey());
        }
    }

    @Test
    public void getNewDefinitionsNotExistingStringDefinition() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        try (final XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", TemplateCustomProperties.STRING_TYPE);

            final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, properties);

            assertEquals(1, definitions.size());
            assertTrue(definitions.get(0) instanceof StringDefinition);
            assertEquals("variable", definitions.get(0).getKey());
        }
    }

    @Test
    public void getNewDefinitionsExisting() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final StringDefinition stringDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
        stringDefinition.setKey("variable");
        generation.getDefinitions().add(stringDefinition);

        try (final XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", TemplateCustomProperties.STRING_TYPE);

            final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, properties);

            assertEquals(0, definitions.size());
        }
    }

    @Test
    public void getNewDefinitionsExistingInvalidType() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final StringDefinition stringDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
        stringDefinition.setKey("variable");
        generation.getDefinitions().add(stringDefinition);

        try (final XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", "ecore::EClass");

            final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, properties);

            assertEquals(1, definitions.size());
            assertTrue(definitions.get(0) instanceof ModelDefinition);
            assertEquals("variable", definitions.get(0).getKey());
        }
    }

}
