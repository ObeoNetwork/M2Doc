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
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.DocumentTemplate;

public class DocumentGeneratorTest {
    @Test
    public void testFormsAndTextArea()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testTextAreaAndForms.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testTextAreaAndForms.docx",
                "results/testTextAreaAndForms.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testStaticFragmentWithFieldProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testStaticFragmentWithfields.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragmentWithfields.docx",
                "results/testStaticFragmentWithfields.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testStaticFragmentProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testStaticFragment.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragment.docx",
                "results/testStaticFragment.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testVarRefInHeaderProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testVarInHeader.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarInHeader.docx",
                "results/testVarInHeaderResult.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testVarRefInFooterProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testVarInFooter.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarInFooter.docx",
                "results/testVarInFooterResult.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testVarRefProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVar.docx", "results/testVarResult.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testVarRefErrorProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testVar.docx", "results/testVarResult.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testVarRefStyledProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testVarStyle.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarStyle.docx",
                "results/testVarStyleResult.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testVarRefStyledMultipleParagraphsProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testVarStyleSpanning2Paragraphs.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarStyleSpanning2Paragraphs.docx",
                "results/testVarStyleSpanning2ParagraphsResult.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testQueryStyledProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testAQL.docx", "results/testAQLResult.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testQueryStyledErrorProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testAQL.docx", "results/testAQLResult.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testGDFORProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testGDFOR.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testGDFOR.docx", "results/testGDFOR.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testGDFORWithTableProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testGDFORWithTable.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testGDFORWithTable.docx",
                "results/testGDFORWithTable.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal1trueProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal1.docx",
                "results/testConditionnal1Result.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal1falseProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal1.docx",
                "results/testConditionnal1FalseResult.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal2Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal2.docx",
                "results/testConditionnal2Result.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnalFalseProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal2.docx",
                "results/testConditionnal2FalseResult.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    //
    // @Test
    // public void testConditionnal3Processing()
    // throws InvalidFormatException, IOException, DocumentParserException,
    // DocumentGenerationException {
    // IQueryEnvironment queryEnvironment =
    // org.eclipse.acceleo.query.runtime.Query
    // .newEnvironmentWithDefaultServices(null);
    // FileInputStream is = new
    // FileInputStream("templates/testConditionnal3.docx");
    // OPCPackage oPackage = OPCPackage.open(is);
    // XWPFDocument document = new XWPFDocument(oPackage);
    // BodyParser parser = new BodyParser(document, queryEnvironment);
    // Template template = parser.parseTemplate();
    // Map<String, Object> definitions = new HashMap<String, Object>();
    // definitions.put("x", "valueofx");
    // DocumentGenerator generator = new
    // DocumentGenerator("templates/testConditionnal3.docx",
    // "results/testConditionnal3Result.docx", template, definitions,
    // queryEnvironment);
    // generator.generate();
    // }
    //
    @Test
    public void testImagesAndFootersAndHeadersAndBullets()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        FileInputStream is = new FileInputStream("templates/test.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/test.docx", "results/testResult.docx", template,
                definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal5Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/testConditionnal5Result.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal6Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value2");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/testConditionnal6Result.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal7Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value3");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/testConditionnal7Result.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testConditionnal8Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/testConditionnal8Result.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testImageGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testImageTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testImageTag.docx", "results/testImageTag.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testDiagramGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/allDiagram.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/allDiagram.docx", "results/allDiagram.docx",
                template, definitions, queryEnvironment, null);
        generator.generate();
    }

}
