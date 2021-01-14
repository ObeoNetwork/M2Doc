/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.tests;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
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
import org.obeonetwork.m2doc.tests.M2DocUtilsTests;
import org.obeonetwork.m2doc.tests.M2DocUtilsTests.TestServiceConfigurator;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests {@link GenconfUtils}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenconfUtilsTests {

    @BeforeClass
    public static void beforeClass() {
        M2DocUtilsTests.beforeClass();
    }

    @AfterClass
    public static void afterClass() {
        M2DocUtilsTests.afterClass();
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
        assertEquals("test.docx", options.get(M2DocUtils.TEMPLATE_URI_OPTION));
    }

    @Test
    public void getOptionsResultURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        generation.setResultFileName("test.docx");

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(1, options.size());
        assertEquals("test.docx", options.get(M2DocUtils.RESULT_URI_OPTION));
    }

    @Test
    public void getOptionsValidationURI() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        generation.setValidationFileName("test.docx");

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(1, options.size());
        assertEquals("test.docx", options.get(M2DocUtils.VALIDATION_URI_OPTION));
    }

    @Test
    public void getOptionsNoOptions() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createFileURI("/generation.xmi"));
        resource.getContents().add(generation);

        generation.setTemplateFileName("template.docx");
        generation.setResultFileName("result.docx");
        generation.setValidationFileName("validation.docx");

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        assertEquals(4, options.size());
        assertEquals("file:/generation.xmi", options.get(GenconfUtils.GENCONF_URI_OPTION));
        assertEquals("file:/template.docx", options.get(M2DocUtils.TEMPLATE_URI_OPTION));
        assertEquals("file:/result.docx", options.get(M2DocUtils.RESULT_URI_OPTION));
        assertEquals("file:/validation.docx", options.get(M2DocUtils.VALIDATION_URI_OPTION));
    }

    @Test
    public void getOptions() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final Resource resource = new ResourceImpl(URI.createFileURI("/generation.xmi"));
        resource.getContents().add(generation);

        generation.setTemplateFileName("template.docx");
        generation.setResultFileName("result.docx");
        generation.setValidationFileName("validation.docx");

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
        assertEquals(7, options.size());
        assertEquals("file:/generation.xmi", options.get(GenconfUtils.GENCONF_URI_OPTION));
        assertEquals("file:/template.docx", options.get(M2DocUtils.TEMPLATE_URI_OPTION));
        assertEquals("file:/result.docx", options.get(M2DocUtils.RESULT_URI_OPTION));
        assertEquals("file:/validation.docx", options.get(M2DocUtils.VALIDATION_URI_OPTION));
        assertEquals("value1", options.get("option1"));
        assertEquals("value2", options.get("option2"));
        assertEquals("value3", options.get("option3"));
    }

    @Test
    public void initializeOptionsNotExistingOption() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        GenconfUtils.initializeOptions(generation);

        assertEquals(2, generation.getOptions().size());
        assertEquals(M2DocUtils.UPDATE_FIELDS_OPTION, generation.getOptions().get(0).getName());
        assertEquals("false", generation.getOptions().get(0).getValue());
        assertEquals(TestServiceConfigurator.OPTION, generation.getOptions().get(1).getName());
        assertEquals(TestServiceConfigurator.VALUE, generation.getOptions().get(1).getValue());
    }

    @Test
    public void initializeOptionsExistingOption() {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();

        final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
        option.setName(TestServiceConfigurator.OPTION);
        generation.getOptions().add(option);

        GenconfUtils.initializeOptions(generation);

        assertEquals(2, generation.getOptions().size());
        assertEquals(TestServiceConfigurator.OPTION, generation.getOptions().get(0).getName());
        assertEquals(TestServiceConfigurator.VALUE, generation.getOptions().get(0).getValue());
        assertEquals(M2DocUtils.UPDATE_FIELDS_OPTION, generation.getOptions().get(1).getName());
        assertEquals("false", generation.getOptions().get(1).getValue());
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
        try (XWPFDocument document = new XWPFDocument()) {
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
        try (XWPFDocument document = new XWPFDocument()) {
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

        try (XWPFDocument document = new XWPFDocument()) {
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

        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", "ecore::EClass");

            final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, properties);

            assertEquals(1, definitions.size());
            assertTrue(definitions.get(0) instanceof ModelDefinition);
            assertEquals("variable", definitions.get(0).getKey());
        }
    }

    @Test
    public void getNewDefinitionsExistingNotDeclared() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final StringDefinition stringDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
        stringDefinition.setKey("variable");
        generation.getDefinitions().add(stringDefinition);

        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, properties);

            assertEquals(0, definitions.size());
        }
    }

    @Test
    public void getOldDefinitionsNotExistingModelDefinition() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", "ecore::EClass");

            final List<Definition> definitions = GenconfUtils.getOldDefinitions(generation, properties);

            assertEquals(0, definitions.size());
        }
    }

    @Test
    public void getOldDefinitionsNotExistingStringDefinition() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", TemplateCustomProperties.STRING_TYPE);

            final List<Definition> definitions = GenconfUtils.getOldDefinitions(generation, properties);

            assertEquals(0, definitions.size());
        }
    }

    @Test
    public void getOldDefinitionsExisting() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final StringDefinition stringDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
        stringDefinition.setKey("variable");
        generation.getDefinitions().add(stringDefinition);

        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", TemplateCustomProperties.STRING_TYPE);

            final List<Definition> definitions = GenconfUtils.getOldDefinitions(generation, properties);

            assertEquals(0, definitions.size());
        }
    }

    @Test
    public void getOldDefinitionsExistingInvalidType() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final StringDefinition stringDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
        stringDefinition.setKey("variable");
        generation.getDefinitions().add(stringDefinition);

        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            properties.getVariables().put("variable", "ecore::EClass");

            final List<Definition> definitions = GenconfUtils.getOldDefinitions(generation, properties);

            assertEquals(1, definitions.size());
            assertEquals(stringDefinition, definitions.get(0));
        }
    }

    @Test
    public void getOldDefinitionsExistingNotDeclared() throws IOException {
        final Generation generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final StringDefinition stringDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
        stringDefinition.setKey("variable");
        generation.getDefinitions().add(stringDefinition);

        try (XWPFDocument document = new XWPFDocument()) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            final List<Definition> definitions = GenconfUtils.getOldDefinitions(generation, properties);

            assertEquals(1, definitions.size());
            assertEquals(stringDefinition, definitions.get(0));
        }
    }

}
