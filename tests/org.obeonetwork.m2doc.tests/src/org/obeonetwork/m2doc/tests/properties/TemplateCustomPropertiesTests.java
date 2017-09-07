/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.properties;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.junit.Test;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the {@link TemplateCustomProperties}.
 * 
 * @author Romain Guider
 */
public class TemplateCustomPropertiesTests {

    @Test
    public void parseVariable() throws IOException, InvalidFormatException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final Map<String, String> variables = properties.getVariables();
            assertEquals(2, variables.size());
            assertEquals("database.Table", variables.get("variable1"));
            assertEquals("database.Column", variables.get("variable2"));
        }
    }

    @Test
    public void parseImport() throws IOException, InvalidFormatException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final List<String> serviceClasses = new ArrayList<String>(properties.getServiceClasses().keySet());
            assertEquals(2, serviceClasses.size());
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage1", serviceClasses.get(0));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage2", serviceClasses.get(1));
        }
    }

    /**
     * Check that reading invalid variables from a docx file does not fail (throw an exception for instance) but that invalid variables are
     * simply ignored.
     * 
     * @throws IOException
     *             If IO problem
     * @throws InvalidFormatException
     *             If format problem
     */
    @Test
    public void parseInvalidVariables() throws IOException, InvalidFormatException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/emptyVar.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final Map<String, String> variables = properties.getVariables();
            assertTrue(variables.isEmpty());
        }
    }

    /**
     * Check that reading a blank metamodel URI from a docx file does not fail (throw an exception for instance) but that no MM URI is
     * returned.
     * 
     * @throws IOException
     *             If IO problem
     * @throws InvalidFormatException
     *             If format problem
     */
    @Test
    public void testReadBlankMMUri() throws IOException, InvalidFormatException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/noUri.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final List<String> uris = properties.getPackagesURIs();
            assertTrue(uris.isEmpty());
        }
    }

    @Test
    public void addProperties() throws IOException {
        final File tempFile = File.createTempFile("properties", "-add.docx");
        final URI tempFileURI = URI.createURI(tempFile.toURI().toString());
        tempFile.deleteOnExit();
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/noProperties.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            assertTrue(properties.getPackagesURIs().isEmpty());
            properties.getPackagesURIs().add("http://www.eclipse.org/meta100");
            properties.getPackagesURIs().add("http://www.eclipse.org/meta200");

            assertTrue(properties.getServiceClasses().isEmpty());
            properties.getServiceClasses().put("org.obeonetwork.m2doc.services.test.ServicePackage100", "");
            properties.getServiceClasses().put("org.obeonetwork.m2doc.services.test.ServicePackage200", "");

            assertTrue(properties.getVariables().isEmpty());
            properties.getVariables().put("var100", "String");
            properties.getVariables().put("var200", "Integer");

            properties.save();
            POIServices.getInstance().saveFile(URIConverter.INSTANCE, document, tempFileURI);
        }

        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE, tempFileURI);) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            assertEquals(2, properties.getPackagesURIs().size());
            assertEquals("http://www.eclipse.org/meta100", properties.getPackagesURIs().get(0));
            assertEquals("http://www.eclipse.org/meta200", properties.getPackagesURIs().get(1));

            final List<String> serviceClasses = new ArrayList<String>(properties.getServiceClasses().keySet());
            assertEquals(2, serviceClasses.size());
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage100", serviceClasses.get(0));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage200", serviceClasses.get(1));

            assertEquals(2, properties.getVariables().size());
            assertEquals("String", properties.getVariables().get("var100"));
            assertEquals("Integer", properties.getVariables().get("var200"));
        }
    }

    @Test
    public void deleteProperties() throws IOException {
        final File tempFile = File.createTempFile("properties", "-add.docx");
        final URI tempFileURI = URI.createURI(tempFile.toURI().toString());
        tempFile.deleteOnExit();
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            assertEquals(2, properties.getPackagesURIs().size());
            properties.getPackagesURIs().clear();

            assertEquals(2, properties.getServiceClasses().size());
            properties.getServiceClasses().clear();

            assertEquals(2, properties.getVariables().size());
            properties.getVariables().clear();

            properties.save();
            POIServices.getInstance().saveFile(URIConverter.INSTANCE, document, tempFileURI);
        }

        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE, tempFileURI);) {
            final TemplateCustomProperties info = new TemplateCustomProperties(document);

            assertEquals(0, info.getPackagesURIs().size());

            assertEquals(0, info.getServiceClasses().size());

            assertEquals(0, info.getVariables().size());
        }
    }

    @Test
    public void updateProperties() throws IOException {
        final File tempFile = File.createTempFile("properties", "-add.docx");
        final URI tempFileURI = URI.createURI(tempFile.toURI().toString());
        tempFile.deleteOnExit();
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            assertEquals(2, properties.getPackagesURIs().size());
            properties.getPackagesURIs().add("http://www.eclipse.org/meta100");
            properties.getPackagesURIs().add("http://www.eclipse.org/meta200");

            assertEquals(2, properties.getServiceClasses().size());
            properties.getServiceClasses().put("org.obeonetwork.m2doc.services.test.ServicePackage100", "");
            properties.getServiceClasses().put("org.obeonetwork.m2doc.services.test.ServicePackage200", "");

            assertEquals(2, properties.getVariables().size());
            properties.getVariables().put("var100", "String");
            properties.getVariables().put("var200", "Integer");

            properties.save();
            POIServices.getInstance().saveFile(URIConverter.INSTANCE, document, tempFileURI);
        }

        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE, tempFileURI);) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);

            assertEquals(4, properties.getPackagesURIs().size());
            assertEquals("http://www.eclipse.org/meta1", properties.getPackagesURIs().get(0));
            assertEquals("http://www.eclipse.org/meta2", properties.getPackagesURIs().get(1));
            assertEquals("http://www.eclipse.org/meta100", properties.getPackagesURIs().get(2));
            assertEquals("http://www.eclipse.org/meta200", properties.getPackagesURIs().get(3));

            final List<String> serviceClasses = new ArrayList<String>(properties.getServiceClasses().keySet());
            assertEquals(4, serviceClasses.size());
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage1", serviceClasses.get(0));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage2", serviceClasses.get(1));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage100", serviceClasses.get(2));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage200", serviceClasses.get(3));

            assertEquals(4, properties.getVariables().size());
            assertEquals("database.Table", properties.getVariables().get("variable1"));
            assertEquals("database.Column", properties.getVariables().get("variable2"));
            assertEquals("String", properties.getVariables().get("var100"));
            assertEquals("Integer", properties.getVariables().get("var200"));
        }
    }

    @Test
    public void testIsValidVariableName() {
        assertTrue(TemplateCustomProperties.isValidVariableName("v"));
        assertTrue(TemplateCustomProperties.isValidVariableName("_valid"));
        assertTrue(TemplateCustomProperties.isValidVariableName("valid_"));
        assertTrue(TemplateCustomProperties.isValidVariableName("valid_123"));
        assertTrue(TemplateCustomProperties.isValidVariableName("_VALID_1354"));

        assertFalse(TemplateCustomProperties.isValidVariableName(null));
        assertFalse(TemplateCustomProperties.isValidVariableName(""));
        assertFalse(TemplateCustomProperties.isValidVariableName("not valid"));
        assertFalse(TemplateCustomProperties.isValidVariableName("é"));
        assertFalse(TemplateCustomProperties.isValidVariableName("3invalid"));
        assertFalse(TemplateCustomProperties.isValidVariableName("-inv"));
        assertFalse(TemplateCustomProperties.isValidVariableName("not-valid"));
        assertFalse(TemplateCustomProperties.isValidVariableName("dfg$fsd"));
    }

    @Test
    public void getMissingVariables() throws IOException, InvalidFormatException {
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(URIConverter.INSTANCE,
                URI.createFileURI("resources/document/properties/missingVariables.docx"));) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            final List<String> missingVariables = properties.getMissingVariables();

            assertEquals(16, missingVariables.size());
            assertEquals("linkNamelinkText", missingVariables.get(0));
            assertEquals("bookmarkName", missingVariables.get(1));
            assertEquals("queryInBookmark", missingVariables.get(2));
            assertEquals("ifCondition", missingVariables.get(3));
            assertEquals("queryInIf", missingVariables.get(4));
            assertEquals("elseIfCondition", missingVariables.get(5));
            assertEquals("queryInElseIf", missingVariables.get(6));
            assertEquals("queryInElse", missingVariables.get(7));
            assertEquals("letExpression", missingVariables.get(8));
            assertEquals("queryInLet", missingVariables.get(9));
            assertEquals("forExpression", missingVariables.get(10));
            assertEquals("queryInFor", missingVariables.get(11));
            assertEquals("queryExpression", missingVariables.get(12));
            assertEquals("aqlInSelect", missingVariables.get(13));
            assertEquals("aqlLetExpression", missingVariables.get(14));
            assertEquals("aqlLetBody", missingVariables.get(15));
        }
    }

}
