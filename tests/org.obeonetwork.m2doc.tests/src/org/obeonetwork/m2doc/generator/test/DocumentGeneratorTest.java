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

import java.io.File;
//CHECKSTYLE:OFF
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.DocumentTemplateParser;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentGeneratorTest {
    @Test
    public void testFormsAndTextArea()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testTextAreaAndForms.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testTextAreaAndForms.docx",
                "results/generated/testTextAreaAndForms.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragmentWithfields.docx",
                "results/generated/testStaticFragmentWithfields.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragment.docx",
                "results/generated/testStaticFragment.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarInHeader.docx",
                "results/generated/testVarInHeaderResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarInFooter.docx",
                "results/generated/testVarInFooterResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVar.docx",
                "results/generated/testVarResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testVar.docx",
                "results/generated/testVarResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarStyle.docx",
                "results/generated/testVarStyleResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testVarStyleSpanning2Paragraphs.docx",
                "results/generated/testVarStyleSpanning2ParagraphsResult.docx", template, definitions, queryEnvironment,
                null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testAQL.docx",
                "results/generated/testAQLResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testAQL.docx",
                "results/generated/testAQLResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testGDFOR.docx",
                "results/generated/testGDFOR.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/testGDFORWithTable.docx",
                "results/generated/testGDFORWithTable.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal1.docx",
                "results/generated/testConditionnal1Result.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal1.docx",
                "results/generated/testConditionnal1FalseResult.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal2.docx",
                "results/generated/testConditionnal2Result.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal2.docx",
                "results/generated/testConditionnal2FalseResult.docx", template, definitions, queryEnvironment, null);
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
    // "results/generated/testConditionnal3Result.docx", template, definitions,
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/test.docx", "results/generated/testResult.docx",
                template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/generated/testConditionnal5Result.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value2");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/generated/testConditionnal6Result.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value3");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/generated/testConditionnal7Result.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
                "results/generated/testConditionnal8Result.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        DocumentGenerator generator = new DocumentGenerator("templates/testImageTag.docx",
                "results/generated/testImageTag.docx", template, definitions, queryEnvironment, null);
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
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/allDiagram.docx",
                "results/generated/allDiagram.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testStaticHyperlink()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/staticHyperlink.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/staticHyperlink.docx",
                "results/generated/staticHyperlink.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testDynamicHyperlink()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/dynamicHyperlink.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/dynamicHyperlink.docx",
                "results/generated/dynamicHyperlink.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

    @Test
    public void testBookmarkNominal()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testBookmarkNominal.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testBookmarkNominal.docx",
                "results/generated/testBookmarkNominal.docx", template, definitions, queryEnvironment, null);
        generator.generate();

        FileInputStream resIs = new FileInputStream("results/generated/testBookmarkNominal.docx");
        OPCPackage resOPackage = OPCPackage.open(resIs);
        XWPFDocument resDocument = new XWPFDocument(resOPackage);

        assertEquals(4, resDocument.getBodyElements().size());
        assertTrue(resDocument.getBodyElements().get(0) instanceof XWPFParagraph);
        XWPFParagraph paragraph = (XWPFParagraph) resDocument.getBodyElements().get(0);
        assertEquals(6, paragraph.getRuns().size());
        assertEquals("Test link before bookmark : ", paragraph.getRuns().get(0).text());

        final BigInteger id = new BigInteger(paragraph.getRuns().get(1).getCTR().getRsidR());

        assertTrue(id != BigInteger.ZERO);
        assertEquals(1, paragraph.getRuns().get(1).getCTR().getFldCharList().size());
        assertEquals(STFldCharType.BEGIN, paragraph.getRuns().get(1).getCTR().getFldCharList().get(0).getFldCharType());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(2).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(2).getCTR().getInstrTextList().size());
        assertEquals(Space.PRESERVE, paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getSpace());
        assertEquals(" REF bookmark1 \\h ",
                paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getStringValue());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(3).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(3).getCTR().getFldCharList().size());
        assertEquals(STFldCharType.SEPARATE,
                paragraph.getRuns().get(3).getCTR().getFldCharList().get(0).getFldCharType());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(4).getCTR().getRsidR()));
        assertEquals("a reference to bookmark1", paragraph.getRuns().get(4).text());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(5).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(5).getCTR().getFldCharList().size());
        assertEquals(STFldCharType.END, paragraph.getRuns().get(5).getCTR().getFldCharList().get(0).getFldCharType());

        assertTrue(resDocument.getBodyElements().get(1) instanceof XWPFParagraph);
        paragraph = (XWPFParagraph) resDocument.getBodyElements().get(1);

        assertEquals(1, paragraph.getCTP().getBookmarkStartList().size());
        assertEquals("bookmark1", paragraph.getCTP().getBookmarkStartList().get(0).getName());
        assertEquals(1, paragraph.getCTP().getBookmarkEndList().size());

        assertTrue(resDocument.getBodyElements().get(2) instanceof XWPFParagraph);
        paragraph = (XWPFParagraph) resDocument.getBodyElements().get(2);
        assertEquals(7, paragraph.getRuns().size());
        assertEquals("Test link after bookmark : ", paragraph.getRuns().get(0).text());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(1).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(1).getCTR().getFldCharList().size());
        assertEquals(STFldCharType.BEGIN, paragraph.getRuns().get(1).getCTR().getFldCharList().get(0).getFldCharType());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(2).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(2).getCTR().getInstrTextList().size());
        assertEquals(Space.PRESERVE, paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getSpace());
        assertEquals(" REF bookmark1 \\h ",
                paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getStringValue());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(3).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(3).getCTR().getFldCharList().size());
        assertEquals(STFldCharType.SEPARATE,
                paragraph.getRuns().get(3).getCTR().getFldCharList().get(0).getFldCharType());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(4).getCTR().getRsidR()));
        assertEquals("a reference to bookmark1", paragraph.getRuns().get(4).text());

        assertEquals(id, new BigInteger(paragraph.getRuns().get(5).getCTR().getRsidR()));
        assertEquals(1, paragraph.getRuns().get(5).getCTR().getFldCharList().size());
        assertEquals(STFldCharType.END, paragraph.getRuns().get(5).getCTR().getFldCharList().get(0).getFldCharType());

        resIs.close();
        resOPackage.close();
        resDocument.close();
    }

    @Test
    public void testBookmarkNoBookmark()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testBookmarkNoBookmark.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testBookmarkNoBookmark.docx",
                "results/generated/testBookmarkNoBookmark.docx", template, definitions, queryEnvironment, null);
        generator.generate();

        FileInputStream resIs = new FileInputStream("results/generated/testBookmarkNoBookmark.docx");
        OPCPackage resOPackage = OPCPackage.open(resIs);
        XWPFDocument resDocument = new XWPFDocument(resOPackage);

        assertEquals(2, resDocument.getBodyElements().size());
        assertTrue(resDocument.getBodyElements().get(0) instanceof XWPFParagraph);
        XWPFParagraph paragraph = (XWPFParagraph) resDocument.getBodyElements().get(0);
        assertEquals(10, paragraph.getRuns().size());
        assertEquals("Test link ", paragraph.getRuns().get(0).text());
        assertEquals("without", paragraph.getRuns().get(1).text());
        assertEquals(" bookmark : ", paragraph.getRuns().get(2).text());

        resIs.close();
        resOPackage.close();
        resDocument.close();
    }

    /**
     * Test UserDoc with no previous result.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    @Test
    public void testUserDoc()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc1.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testUserDoc1.docx",
                "results/generated/testUserDoc1.docx", template, definitions, queryEnvironment, null);
        generator.generate();

        FileInputStream resIs = new FileInputStream("results/generated/testUserDoc1.docx");
        OPCPackage resOPackage = OPCPackage.open(resIs);
        XWPFDocument resDocument = new XWPFDocument(resOPackage);

        assertEquals(8, resDocument.getParagraphs().size());
        assertEquals("User document part Texte 1", resDocument.getParagraphs().get(2).getText());

        resIs.close();
        resOPackage.close();
        resDocument.close();
    }

    /**
     * Test UserDoc In Header and Footer with no previous result.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    @Test
    public void testUserDocInHeaderFooter()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc9.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testUserDoc9.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testUserDoc9.docx", resultPath, template,
                definitions, queryEnvironment, null);
        generator.generate();

        FileInputStream resIs = new FileInputStream("results/generated/testUserDoc9.docx");
        OPCPackage resOPackage = OPCPackage.open(resIs);
        XWPFDocument resDocument = new XWPFDocument(resOPackage);

        assertEquals(1, resDocument.getHeaderList().size());
        assertEquals(1, resDocument.getHeaderList().get(0).getParagraphs().size());
        assertEquals(" User document part Texte 1",
                resDocument.getHeaderList().get(0).getParagraphs().get(0).getText());

        assertEquals(6, resDocument.getParagraphs().size());
        assertEquals("User document part Texte 2", resDocument.getParagraphs().get(2).getText());

        assertEquals(1, resDocument.getFooterList().size());
        assertEquals(1, resDocument.getFooterList().get(0).getParagraphs().size());
        assertEquals(" User document part Texte 3",
                resDocument.getFooterList().get(0).getParagraphs().get(0).getText());

        resIs.close();
        resOPackage.close();
        resDocument.close();
    }

    /**
     * Test UserDoc with previous result.
     * There is a picture in previous result userCode.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    @Test
    public void testUserDocWithPreviousResult()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        // Remove previous generated file
        String resultPath = "results/generated/testUserContent1Custom1Result.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        // Copy result in right place
        Files.copy(new File("userContent/testUserContent1Custom1.docx").toPath(), new File(resultPath).toPath());

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testUserDoc1.docx", resultPath, template,
                definitions, queryEnvironment, null);
        generator.generate();

        FileInputStream resIs = new FileInputStream(resultPath);
        OPCPackage resOPackage = OPCPackage.open(resIs);
        XWPFDocument resDocument = new XWPFDocument(resOPackage);

        assertEquals(6, resDocument.getParagraphs().size());
        assertEquals(1, resDocument.getParagraphs().get(2).getRuns().get(1).getEmbeddedPictures().size());
        assertEquals(new Long("3355498452"), resDocument.getParagraphs().get(2).getRuns().get(1).getEmbeddedPictures()
                .get(0).getPictureData().getChecksum());

        resIs.close();
        resOPackage.close();
        resDocument.close();
    }

    /**
     * Test UserDoc In Header and Footer with no previous result.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    @Test
    public void testUserDocInHeaderFooterWithPreviousResult()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc9Custom1Resultat.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }
        // Copy result in right place
        Files.copy(new File("userContent/testUserContent9Custom1.docx").toPath(), new File(resultPath).toPath());

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testUserDoc9.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        DocumentGenerator generator = new DocumentGenerator("templates/testUserDoc9.docx", resultPath, template,
                definitions, queryEnvironment, null);
        generator.generate();

        FileInputStream resIs = new FileInputStream(resultPath);
        OPCPackage resOPackage = OPCPackage.open(resIs);
        XWPFDocument resDocument = new XWPFDocument(resOPackage);

        assertEquals(1, resDocument.getHeaderList().size());
        assertEquals(1, resDocument.getHeaderList().get(0).getParagraphs().size());
        assertEquals("Custom 1", resDocument.getHeaderList().get(0).getParagraphs().get(0).getText());

        assertEquals(6, resDocument.getParagraphs().size());
        assertEquals("Custom 2", resDocument.getParagraphs().get(2).getText());

        assertEquals(1, resDocument.getFooterList().size());
        assertEquals(1, resDocument.getFooterList().get(0).getParagraphs().size());
        assertEquals(" Custom 3", resDocument.getFooterList().get(0).getParagraphs().get(0).getText());

        resIs.close();
        resOPackage.close();
        resDocument.close();

    }
}
