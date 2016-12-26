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
package org.obeonetwork.m2doc.parser.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.obeonetwork.m2doc.test.M2DocTestUtils.assertTemplateValidationMessage;

/**
 * Document Parser Errors Test.
 * 
 * @author ohaegi
 */
public class DocumentParserErrorsTest {

    /**
     * Environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    /**
     * Test the error reporting on a query tag with an invalid expression.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testQueryTagError() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getXWPFBody());
        assertEquals(2, template.getBody().getStatements().size());
        assertTrue(template.getBody().getStatements().get(1) instanceof Query);
        Query query = (Query) template.getBody().getStatements().get(1);
        assertEquals("Expression \"self.\" is invalid: missing feature access or service call",
                query.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tests the error reporting on a conditional tag with a syntactically
     * invalid expression.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test

    public void testVarRefError() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVarInvalid.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getXWPFBody());
        assertEquals(1, template.getBody().getStatements().size());
        assertTrue(template.getBody().getStatements().get(0) instanceof Query);
        Query query = (Query) template.getBody().getStatements().get(0);
        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Expression \"\" is invalid: null or empty string.", query.getRuns().get(4));
    }

    /**
     * Test parsing userDoc tag with No ID Parameter.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocWithNoIsParameter() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testUserDoc4.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getXWPFBody());
        assertEquals(3, template.getBody().getStatements().size());
        assertTrue(template.getBody().getStatements().get(0) instanceof StaticFragment);
        assertTrue(template.getBody().getStatements().get(1) instanceof UserDoc);
        assertTrue(template.getBody().getStatements().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getBody().getStatements().get(1);
        assertNull(userDoc.getId());
        // Check ValidationMessage
        assertEquals(1, userDoc.getValidationMessages().size());
        TemplateValidationMessage validationMessage1 = userDoc.getValidationMessages().get(0);
        XWPFRun location = userDoc.getRuns().get(userDoc.getRuns().size() - 1);
        assertEquals("Expression \"\" is invalid: null or empty string.", validationMessage1.getMessage());
        assertEquals(ValidationMessageLevel.ERROR, validationMessage1.getLevel());
        assertEquals(location, validationMessage1.getLocation());
    }

}
