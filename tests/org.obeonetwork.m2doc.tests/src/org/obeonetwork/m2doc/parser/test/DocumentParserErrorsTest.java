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
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.BodyParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.obeonetwork.m2doc.test.M2DocTestUtils.assertTemplateValidationMessage;

public class DocumentParserErrorsTest {

    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    /**
     * Tests the error reporting on a repetition tag with a syntactically
     * invalid expression
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepetitionError1() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidFor1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Repetition);
        Repetition repetition = (Repetition) template.getSubConstructs().get(0);
        assertEquals(1, repetition.getValidationMessages().size());
        assertEquals("Expression \"self.\" is invalid: missing feature access or service call",
                repetition.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tess the error reporting on a repetition tag without iteration variable
     * defined.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepetitionError2() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidFor2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Repetition);
        Repetition repetition = (Repetition) template.getSubConstructs().get(0);
        assertEquals(1, repetition.getValidationMessages().size());
        assertEquals("Malformed tag gd:for : no iteration variable specified.",
                repetition.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tess the error reporting on a repetition tag without iteration variable
     * defined.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepetitionError3() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidFor3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Repetition);
        Repetition repetition = (Repetition) template.getSubConstructs().get(0);
        assertEquals(1, repetition.getValidationMessages().size());
        assertEquals("Malformed tag gd:for, no '|' found.", repetition.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tess the error reporting on a repetition tag without iteration variable
     * defined.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepetitionError4() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidFor4.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Repetition);
        Repetition repetition = (Repetition) template.getSubConstructs().get(0);
        assertEquals(1, repetition.getValidationMessages().size());
        assertEquals("Unexpected tag EOF at this location", repetition.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tess the error reporting on a repetition tag without iteration variable
     * defined.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepetitionError5() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidFor5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertEquals(1, template.getValidationMessages().size());
        assertEquals("Unexpected tag m:endfor at this location", template.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tess the error reporting on a query tag with an invalid expression.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testQueryTagError() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(2, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(1) instanceof Query);
        Query query = (Query) template.getSubConstructs().get(1);
        assertEquals("Expression \"self.\" is invalid: missing feature access or service call",
                query.getValidationMessages().get(0).getMessage());
    }

    /**
     * Tests the error reporting on a conditionnal tag with a syntactically
     * invalid expression
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testConditionnalError1() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Conditionnal);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(0);
        assertEquals(1, conditionnal.getValidationMessages().size());
        assertTemplateValidationMessage(conditionnal.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Expression \"x=\" is invalid: missing expression", conditionnal.getRuns().get(6));
    }

    /**
     * Tests the error reporting on a conditionnal without an gd:endif tag
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testConditionnalError2() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Conditionnal);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(0);
        assertEquals(2, conditionnal.getValidationMessages().size());
        final XWPFParagraph lastParagraph = document.getParagraphs().get(document.getParagraphs().size() - 1);
        final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
        assertTemplateValidationMessage(conditionnal.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Unexpected tag EOF at this location", lastRun);
        assertTemplateValidationMessage(conditionnal.getValidationMessages().get(1), ValidationMessageLevel.ERROR,
                "gd:elseif, gd:else or gd:endif expected here.", conditionnal.getRuns().get(3));
    }

    /**
     * Tests the error reporting on a conditionnal tag with an else without an
     * gd:endif tag
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testConditionnalError3() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Conditionnal);
        Default elseBranch = ((Conditionnal) template.getSubConstructs().get(0)).getElse();
        assertNotNull(elseBranch);
        assertEquals(1, elseBranch.getValidationMessages().size());
        final XWPFParagraph lastParagraph = document.getParagraphs().get(document.getParagraphs().size() - 1);
        final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
        assertTemplateValidationMessage(elseBranch.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Unexpected tag EOF at this location", lastRun);
    }

    /**
     * Tests the error reporting on a conditional tag with a syntactically
     * invalid expression
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testConditionnalError4() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal4.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Conditionnal);
        Conditionnal elseIfBranch = ((Conditionnal) template.getSubConstructs().get(0)).getAlternative();
        assertNotNull(elseIfBranch);
        assertEquals(2, elseIfBranch.getValidationMessages().size());
        final XWPFParagraph lastParagraph = document.getParagraphs().get(document.getParagraphs().size() - 1);
        final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
        assertTemplateValidationMessage(elseIfBranch.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Unexpected tag EOF at this location", lastRun);
        assertTemplateValidationMessage(elseIfBranch.getValidationMessages().get(1), ValidationMessageLevel.ERROR,
                "gd:elseif, gd:else or gd:endif expected here.", elseIfBranch.getRuns().get(3));
    }

    /**
     * Tests the error reporting on a conditionnal tag with a syntactically
     * invalid expression
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testConditionnalError5() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Conditionnal);
        Conditionnal elseIfBranch = ((Conditionnal) template.getSubConstructs().get(0)).getAlternative();
        assertNotNull(elseIfBranch);
        assertEquals(1, elseIfBranch.getValidationMessages().size());
        assertTemplateValidationMessage(elseIfBranch.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Expression \"x=\" is invalid: missing expression", elseIfBranch.getRuns().get(4));
    }

    /**
     * Tests the error reporting on a conditionnal tag with a syntactically
     * invalid expression
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testVarRefError() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVarInvalid.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Query);
        Query query = (Query) template.getSubConstructs().get(0);
        assertEquals(1, query.getValidationMessages().size());
        assertTemplateValidationMessage(query.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Expression \"\" is invalid: null or empty string.", query.getRuns().get(4));
    }

}
