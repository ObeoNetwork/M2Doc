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
import org.eclipse.emf.common.util.Diagnostic;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assertEquals(Diagnostic.ERROR, userDoc.getId().getDiagnostic().getSeverity());
        // Check ValidationMessage
        assertEquals(1, userDoc.getValidationMessages().size());
        TemplateValidationMessage validationMessage1 = userDoc.getValidationMessages().get(0);
        XWPFRun location = userDoc.getRuns().get(userDoc.getRuns().size() - 1);
        assertEquals("Expression \"\" is invalid: null or empty string.", validationMessage1.getMessage());
        assertEquals(ValidationMessageLevel.ERROR, validationMessage1.getLevel());
        assertEquals(location, validationMessage1.getLocation());
    }

}
