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
package org.obeonetwork.m2doc.generator.test;

//CHECKSTYLE:OFF
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.BookmarkManager;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.generator.TemplateProcessor;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.Template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TemplateRuntimeErrorTests {

    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    private XWPFDocument createDestinationDocument(String inputDocumentFileName)
            throws IOException, InvalidFormatException {
        FileInputStream is = new FileInputStream(inputDocumentFileName);
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        int size = document.getBodyElements().size();
        for (int i = 0; i < size; i++) {
            document.removeBodyElement(0);
        }
        return document;
    }

    /**
     * Tests processing of a variable reference where the variable is unknown.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testUnknownVarRefProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testVar.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager lastDestinationUserContentManager = new UserContentManager(
                "noResult");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", bookmarkManager,
                lastDestinationUserContentManager, env, destinationDoc, null);
        processor.doSwitch(template);
        // scan the destination document
        assertEquals(2, destinationDoc.getParagraphs().size());
        System.out.println(destinationDoc.getParagraphs().get(0).getText());
        assertEquals("Template de test pour les balises de référence à une variable : Couldn't find the 'x' variable",
                destinationDoc.getParagraphs().get(0).getText());
        XWPFRun run = destinationDoc.getParagraphs().get(0).getRuns()
                .get(destinationDoc.getParagraphs().get(0).getRuns().size() - 1);
        assertEquals("FF0000", run.getColor());
        assertNotNull(run.getCTR().getRPr().getB());

        assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
    }

    /**
     * Tests processing of a query where the evaluation results in an error.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testQueryEvaluationErrorProcessing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testAQL.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager lastDestinationUserContentManager = new UserContentManager(
                "noResult");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", bookmarkManager,
                lastDestinationUserContentManager, env, destinationDoc, null);
        processor.doSwitch(template);
        // scan the destination document
        assertEquals(3, destinationDoc.getParagraphs().size());
        System.out.println(destinationDoc.getParagraphs().get(0).getText());
        assertEquals(
                "Template de test pour les balises de query aql : Couldn't find the 'self' variable\nCouldn't find the 'aqlFeatureAccess(org.eclipse.acceleo.query.runtime.impl.Nothing,java.lang.String)' service",
                destinationDoc.getParagraphs().get(0).getText());
        XWPFRun run = destinationDoc.getParagraphs().get(0).getRuns()
                .get(destinationDoc.getParagraphs().get(0).getRuns().size() - 1);
        assertEquals("FF0000", run.getColor());
        assertNotNull(run.getCTR().getRPr().getB());
        assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
        assertEquals("", destinationDoc.getParagraphs().get(2).getText());
    }

    /**
     * Tests that the proper error message is inserted in the result when an AQL Syntax error is detected in a repetition tag.
     * 
     * @throws IOException
     *             if an I/O problem occurs
     * @throws InvalidFormatException
     *             if the test file format is invalid (which shouldn't be the case)
     * @throws DocumentParserException
     *             if a problem occurs during the parsing of the test file
     */
    @Test
    public void syntaxErrorInRepetitionTest() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testRepetitionSyntaxError.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testRepetitionSyntaxError.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager lastDestinationUserContentManager = new UserContentManager(
                "noResult");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", bookmarkManager,
                lastDestinationUserContentManager, env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("Syntax error in AQL expression.", destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests that the proper error message is inserted in the result when an AQL Syntax error is detected in a {m:if} tag.
     * 
     * @throws IOException
     *             if an I/O problem occurs
     * @throws InvalidFormatException
     *             if the test file format is invalid (which shouldn't be the case)
     * @throws DocumentParserException
     *             if a problem occurs during the parsing of the test file
     */
    @Test
    public void syntaxErrorInConditional1Test() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testInvalidConditionnal1.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager lastDestinationUserContentManager = new UserContentManager(
                "noResult");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", bookmarkManager,
                lastDestinationUserContentManager, env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("Syntax error in AQL expression.", destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests that the proper error message is inserted in the result when an AQL Syntax error is detected in a {m:elseif} tag.
     * 
     * @throws IOException
     *             if an I/O problem occurs
     * @throws InvalidFormatException
     *             if the test file format is invalid (which shouldn't be the case)
     * @throws DocumentParserException
     *             if a problem occurs during the parsing of the test file
     */
    @Test
    public void syntaxErrorInConditional2Test() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testInvalidConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testInvalidConditionnal5.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager lastDestinationUserContentManager = new UserContentManager(
                "noResult");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", bookmarkManager,
                lastDestinationUserContentManager, env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("Syntax error in AQL expression.", destinationDoc.getParagraphs().get(0).getText());
    }

}
