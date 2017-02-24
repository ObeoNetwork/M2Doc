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
package org.obeonetwork.m2doc.properties.test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.properties.TemplateInfo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the {@link TemplateInfo}.
 * 
 * @author Romain Guider
 */
public class TemplateInfoTest {

    @Test
    public void parseServiceToken() throws IOException, InvalidFormatException {
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);
            final List<String> serviceTokens = info.getServiceTokens();
            assertEquals(2, serviceTokens.size());
            assertTrue(serviceTokens.contains("token1"));
            assertTrue(serviceTokens.contains("token2"));
        }
    }

    @Test
    public void parseVariable() throws IOException, InvalidFormatException {
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);
            final Map<String, String> variables = info.getVariables();
            assertEquals(2, variables.size());
            assertEquals("database.Table", variables.get("variable1"));
            assertEquals("database.Column", variables.get("variable2"));
        }
    }

    @Test
    public void parseImport() throws IOException, InvalidFormatException {
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);
            final List<String> serviceClasses = info.getServiceClasses();
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
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/emptyVar.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);
            final Map<String, String> variables = info.getVariables();
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
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/noUri.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);
            final List<String> uris = info.getPackagesURIs();
            assertTrue(uris.isEmpty());
        }
    }

    @Test
    public void addProperties() throws IOException {
        final File tempFile = File.createTempFile("properties", "-add.docx");
        final URI tempFileURI = URI.createFileURI(tempFile.getAbsolutePath());
        tempFile.deleteOnExit();
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/noProperties.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);

            assertTrue(info.getPackagesURIs().isEmpty());
            info.getPackagesURIs().add("http://www.eclipse.org/meta100");
            info.getPackagesURIs().add("http://www.eclipse.org/meta200");

            assertTrue(info.getServiceTokens().isEmpty());
            info.getServiceTokens().add("token100");
            info.getServiceTokens().add("token200");

            assertTrue(info.getServiceClasses().isEmpty());
            info.getServiceClasses().add("org.obeonetwork.m2doc.services.test.ServicePackage100");
            info.getServiceClasses().add("org.obeonetwork.m2doc.services.test.ServicePackage200");

            assertTrue(info.getVariables().isEmpty());
            info.getVariables().put("var100", "String");
            info.getVariables().put("var200", "Integer");

            info.save();
            POIServices.getInstance().saveFile(document, tempFileURI);
        }

        try (final XWPFDocument document = POIServices.getInstance().getXWPFDocument(tempFileURI);) {
            final TemplateInfo info = new TemplateInfo(document);

            assertEquals(2, info.getPackagesURIs().size());
            assertEquals("http://www.eclipse.org/meta100", info.getPackagesURIs().get(0));
            assertEquals("http://www.eclipse.org/meta200", info.getPackagesURIs().get(1));

            assertEquals(2, info.getServiceTokens().size());
            assertEquals("token100", info.getServiceTokens().get(0));
            assertEquals("token200", info.getServiceTokens().get(1));

            assertEquals(2, info.getServiceClasses().size());
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage100", info.getServiceClasses().get(0));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage200", info.getServiceClasses().get(1));

            assertEquals(2, info.getVariables().size());
            assertEquals("String", info.getVariables().get("var100"));
            assertEquals("Integer", info.getVariables().get("var200"));
        }
    }

    @Test
    public void deleteProperties() throws IOException {
        final File tempFile = File.createTempFile("properties", "-add.docx");
        final URI tempFileURI = URI.createFileURI(tempFile.getAbsolutePath());
        tempFile.deleteOnExit();
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);

            assertEquals(2, info.getPackagesURIs().size());
            info.getPackagesURIs().clear();

            assertEquals(2, info.getServiceTokens().size());
            info.getServiceTokens().clear();

            assertEquals(2, info.getServiceClasses().size());
            info.getServiceClasses().clear();

            assertEquals(2, info.getVariables().size());
            info.getVariables().clear();

            info.save();
            POIServices.getInstance().saveFile(document, tempFileURI);
        }

        try (final XWPFDocument document = POIServices.getInstance().getXWPFDocument(tempFileURI);) {
            final TemplateInfo info = new TemplateInfo(document);

            assertEquals(0, info.getPackagesURIs().size());

            assertEquals(0, info.getServiceTokens().size());

            assertEquals(0, info.getServiceClasses().size());

            assertEquals(0, info.getVariables().size());
        }
    }

    @Test
    public void updateProperties() throws IOException {
        final File tempFile = File.createTempFile("properties", "-add.docx");
        final URI tempFileURI = URI.createFileURI(tempFile.getAbsolutePath());
        tempFile.deleteOnExit();
        try (final XWPFDocument document = POIServices.getInstance()
                .getXWPFDocument(URI.createFileURI("resources/document/properties/properties-template.docx"));) {
            final TemplateInfo info = new TemplateInfo(document);

            assertEquals(2, info.getPackagesURIs().size());
            info.getPackagesURIs().add("http://www.eclipse.org/meta100");
            info.getPackagesURIs().add("http://www.eclipse.org/meta200");

            assertEquals(2, info.getServiceTokens().size());
            info.getServiceTokens().add("token100");
            info.getServiceTokens().add("token200");

            assertEquals(2, info.getServiceClasses().size());
            info.getServiceClasses().add("org.obeonetwork.m2doc.services.test.ServicePackage100");
            info.getServiceClasses().add("org.obeonetwork.m2doc.services.test.ServicePackage200");

            assertEquals(2, info.getVariables().size());
            info.getVariables().put("var100", "String");
            info.getVariables().put("var200", "Integer");

            info.save();
            POIServices.getInstance().saveFile(document, tempFileURI);
        }

        try (final XWPFDocument document = POIServices.getInstance().getXWPFDocument(tempFileURI);) {
            final TemplateInfo info = new TemplateInfo(document);

            assertEquals(4, info.getPackagesURIs().size());
            assertEquals("http://www.eclipse.org/meta1", info.getPackagesURIs().get(0));
            assertEquals("http://www.eclipse.org/meta2", info.getPackagesURIs().get(1));
            assertEquals("http://www.eclipse.org/meta100", info.getPackagesURIs().get(2));
            assertEquals("http://www.eclipse.org/meta200", info.getPackagesURIs().get(3));

            assertEquals(4, info.getServiceTokens().size());
            assertEquals("token1", info.getServiceTokens().get(0));
            assertEquals("token2", info.getServiceTokens().get(1));
            assertEquals("token100", info.getServiceTokens().get(2));
            assertEquals("token200", info.getServiceTokens().get(3));

            assertEquals(4, info.getServiceClasses().size());
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage1", info.getServiceClasses().get(0));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage2", info.getServiceClasses().get(1));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage100", info.getServiceClasses().get(2));
            assertEquals("org.obeonetwork.m2doc.services.test.ServicePackage200", info.getServiceClasses().get(3));

            assertEquals(4, info.getVariables().size());
            assertEquals("database.Table", info.getVariables().get("variable1"));
            assertEquals("database.Column", info.getVariables().get("variable2"));
            assertEquals("String", info.getVariables().get("var100"));
            assertEquals("Integer", info.getVariables().get("var200"));
        }
    }

    @Test
    public void testIsValidVariableName() {
        assertTrue(TemplateInfo.isValidVariableName("v"));
        assertTrue(TemplateInfo.isValidVariableName("_valid"));
        assertTrue(TemplateInfo.isValidVariableName("valid_"));
        assertTrue(TemplateInfo.isValidVariableName("valid_123"));
        assertTrue(TemplateInfo.isValidVariableName("_VALID_1354"));

        assertFalse(TemplateInfo.isValidVariableName(null));
        assertFalse(TemplateInfo.isValidVariableName(""));
        assertFalse(TemplateInfo.isValidVariableName("not valid"));
        assertFalse(TemplateInfo.isValidVariableName("é"));
        assertFalse(TemplateInfo.isValidVariableName("3invalid"));
        assertFalse(TemplateInfo.isValidVariableName("-inv"));
        assertFalse(TemplateInfo.isValidVariableName("not-valid"));
        assertFalse(TemplateInfo.isValidVariableName("dfg$fsd"));
    }
}
