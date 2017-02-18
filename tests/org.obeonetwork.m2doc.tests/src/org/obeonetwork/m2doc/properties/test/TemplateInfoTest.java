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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.Test;
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
    public void testServiceToken() throws IOException, InvalidFormatException {
        FileInputStream is = new FileInputStream("resources/document/properties/properties-template.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        TemplateInfo info = new TemplateInfo(document);
        List<String> serviceTokens = info.getServiceTokens();
        assertTrue(serviceTokens.contains("token1"));
        assertTrue(serviceTokens.contains("token2"));
    }

    @Test
    public void testVariableMap() throws IOException, InvalidFormatException {
        FileInputStream is = new FileInputStream("resources/document/properties/properties-template.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        TemplateInfo info = new TemplateInfo(document);
        Map<String, String> variables = info.getVariables();
        assertEquals("database.Table", variables.get("variable1"));
        assertEquals("database.Column", variables.get("variable2"));
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
    public void testReadInvalidVariables() throws IOException, InvalidFormatException {
        FileInputStream is = new FileInputStream("resources/document/properties/emptyVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        TemplateInfo info = new TemplateInfo(document);
        Map<String, String> variables = info.getVariables();
        assertTrue(variables.isEmpty());
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
        FileInputStream is = new FileInputStream("resources/document/properties/noUri.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        TemplateInfo info = new TemplateInfo(document);
        List<String> uris = info.getPackagesURIs();
        assertTrue(uris.isEmpty());
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
