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

import com.google.common.io.Files;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.DocumentTemplateParser;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.provider.test.StubDiagramProvider;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.test.M2DocTestUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentGeneratorTest {

    /**
     * Initialize registry.
     */
    @Before
    public void setUp() {
        ProviderRegistry.INSTANCE.clear();
        ProviderRegistry.INSTANCE.registerProvider(new StubDiagramProvider());
    }

    /**
     * Cleaning.
     */
    @After
    public void after() {
        ProviderRegistry.INSTANCE.clear();
    }

    @Test
    public void testFormsAndTextArea() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testTextAreaAndForms.docx",
                "results/testTextAreaAndForms.docx", definitions, null);
    }

    @Test
    public void testStaticFragmentWithFieldProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testStaticFragmentWithfields.docx",
                "results/testStaticFragmentWithfields.docx", definitions, null);

    }

    @Test
    public void testStaticFragmentProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testStaticFragment.docx", "results/testStaticFragment.docx",
                definitions, null);
    }

    @Test
    public void testVarRefInHeaderProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testVarInHeader.docx", "results/testVarInHeaderResult.docx",
                definitions, null);
    }

    @Test
    public void testVarRefInFooterProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testVarInFooter.docx", "results/testVarInFooterResult.docx",
                definitions, null);

    }

    @Test
    public void testVarRefProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testVar.docx", "results/testVarResult.docx", definitions,
                null);
    }

    @Test
    public void testVarRefErrorProcessing() throws Exception {
        Map<String, Object> definitions = new HashMap<>();
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testVar.docx", "results/testVarResultError.docx",
                definitions, null);
    }

    @Test
    public void testVarRefStyledProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testVarStyle.docx", "results/testVarStyleResult.docx",
                definitions, null);
    }

    @Test
    public void testVarRefStyledMultipleParagraphsProcessing() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testVarStyleSpanning2Paragraphs.docx",
                "results/testVarStyleSpanning2ParagraphsResult.docx", definitions, null);
    }

    @Test
    public void testImagesAndFootersAndHeadersAndBullets() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        M2DocTestUtils.doGenerateDocAndCheckText("templates/test.docx", "results/testResult.docx", definitions, null);

    }

    @Test
    @Ignore(value = "Seems like nobody knows yet what is the actual expected result.")
    public void testImageGeneration() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testImageTag.docx", "results/testImageTag.docx",
                definitions, null, false);
    }

    @Test
    @Ignore(value = "Seems like nobody knows yet what is the actual expected result.")
    public void testDiagramGeneration() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        M2DocTestUtils.doGenerateDocAndCheckText("templates/allDiagram.docx", "results/allDiagram.docx", definitions,
                null, false);
    }

    @Test
    public void testStaticHyperlink() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        M2DocTestUtils.doGenerateDocAndCheckText("templates/staticHyperlink.docx", "results/staticHyperlink.docx",
                definitions, null);

    }

    @Test
    public void testDynamicHyperlink() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        M2DocTestUtils.doGenerateDocAndCheckText("templates/dynamicHyperlink.docx", "results/dynamicHyperlink.docx",
                definitions, null);

    }

    @Test
    public void testBookmarkNominal() throws Exception {

        Map<String, Object> definitions = new HashMap<>();
        M2DocTestUtils.doGenerateDocAndCheckText("templates/testBookmarkNominal.docx",
                "results/testBookmarkNominal.docx", definitions, null);

        try (FileInputStream resIs = new FileInputStream("results/testBookmarkNominal.docx");
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(4, resDocument.getBodyElements().size());
            assertTrue(resDocument.getBodyElements().get(0) instanceof XWPFParagraph);
            XWPFParagraph paragraph = (XWPFParagraph) resDocument.getBodyElements().get(0);
            assertEquals(6, paragraph.getRuns().size());
            assertEquals("Test link before bookmark : ", paragraph.getRuns().get(0).text());

            final BigInteger id = new BigInteger(paragraph.getRuns().get(1).getCTR().getRsidR());

            assertTrue(id != BigInteger.ZERO);
            assertEquals(1, paragraph.getRuns().get(1).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.BEGIN,
                    paragraph.getRuns().get(1).getCTR().getFldCharList().get(0).getFldCharType());

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
            assertEquals(STFldCharType.END,
                    paragraph.getRuns().get(5).getCTR().getFldCharList().get(0).getFldCharType());

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
            assertEquals(STFldCharType.BEGIN,
                    paragraph.getRuns().get(1).getCTR().getFldCharList().get(0).getFldCharType());

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
            assertEquals(STFldCharType.END,
                    paragraph.getRuns().get(5).getCTR().getFldCharList().get(0).getFldCharType());
            resOPackage.close();
            resDocument.close();
        }
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
    public void testUserDoc() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc1.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try (FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc1.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc1.docx"),
                    URI.createFileURI("results/generated/testUserDoc1.docx"), template, definitions, queryEnvironment,
                    null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }

        try (FileInputStream resIs = new FileInputStream(resultPath);
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(8, resDocument.getParagraphs().size());
            assertEquals("User document part Texte 1", resDocument.getParagraphs().get(2).getText());

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }
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
    public void testUserDocInHeaderFooter() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc9.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try (FileInputStream is = new FileInputStream("templates/testUserDoc9.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc9.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc9.docx"),
                    URI.createFileURI(resultPath), template, definitions, queryEnvironment, null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }

        try (FileInputStream resIs = new FileInputStream(resultPath);
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {
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

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }
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
    public void testUserDocWithPreviousResult() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserContent1Custom1Result.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        // Copy result in right place
        Files.copy(new File("userContent/testUserContent1Custom1.docx"), new File(resultPath));

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try (FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc1.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc1.docx"),
                    URI.createFileURI(resultPath), template, definitions, queryEnvironment, null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }

        try (FileInputStream resIs = new FileInputStream(resultPath);
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {
            assertEquals(6, resDocument.getParagraphs().size());
            assertEquals(1, resDocument.getParagraphs().get(2).getRuns().get(1).getEmbeddedPictures().size());
            assertEquals(new Long("3355498452"), resDocument.getParagraphs().get(2).getRuns().get(1)
                    .getEmbeddedPictures().get(0).getPictureData().getChecksum());

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }
    }

    /**
     * Test UserDoc In Header and Footer with previous result.
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
    public void testUserDocInHeaderFooterWithPreviousResult() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc9Custom1Resultat.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }
        // Copy result in right place
        Files.copy(new File("userContent/testUserContent9Custom1.docx"), new File(resultPath));

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);

        try (FileInputStream is = new FileInputStream("templates/testUserDoc9.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {

            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc9.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc9.docx"),
                    URI.createFileURI(resultPath), template, definitions, queryEnvironment, null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }

        try (FileInputStream resIs = new FileInputStream(resultPath);
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            // FileInputStream resIs = new FileInputStream(resultPath);
            // OPCPackage resOPackage = OPCPackage.open(resIs);
            // XWPFDocument resDocument = new XWPFDocument(resOPackage);

            assertEquals(1, resDocument.getHeaderList().size());
            assertEquals(1, resDocument.getHeaderList().get(0).getParagraphs().size());
            assertEquals("Custom 1", resDocument.getHeaderList().get(0).getParagraphs().get(0).getText());

            assertEquals(6, resDocument.getParagraphs().size());
            assertEquals("Custom 2", resDocument.getParagraphs().get(2).getText());

            assertEquals(1, resDocument.getFooterList().size());
            assertEquals(1, resDocument.getFooterList().get(0).getParagraphs().size());
            assertEquals(" Custom 3", resDocument.getFooterList().get(0).getParagraphs().get(0).getText());

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }
    }

    /**
     * Test UserDoc In Table with no previous result.
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
    public void testUserDocInTable() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc10.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try (FileInputStream is = new FileInputStream("templates/testUserDoc10.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {

            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc10.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc10.docx"),
                    URI.createFileURI(resultPath), template, definitions, queryEnvironment, null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }
        try (FileInputStream resIs = new FileInputStream("results/generated/testUserDoc10.docx");
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(5, resDocument.getBodyElements().size());
            assertTrue(resDocument.getBodyElements().get(1) instanceof XWPFTable);
            XWPFTable table = (XWPFTable) resDocument.getBodyElements().get(1);
            assertEquals(" OrigText1 ", table.getRow(0).getCell(0).getParagraphs().get(0).getText());
            assertEquals(" OrigText3 ", table.getRow(0).getCell(1).getParagraphs().get(0).getText());
            assertEquals(" OrigText2 ", table.getRow(1).getCell(0).getParagraphs().get(0).getText());
            assertEquals(" OrigText4 ", table.getRow(1).getCell(1).getParagraphs().get(0).getText());

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }
    }

    /**
     * Test UserDoc In Table with simple previous result.
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
    public void testUserDocInTableWithSimplePreviousResult() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc10Custom1.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }
        // Copy result in right place
        Files.copy(new File("userContent/testUserDoc10Custom1.docx"), new File(resultPath));

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try (FileInputStream is = new FileInputStream("templates/testUserDoc10.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {

            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc10.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc10.docx"),
                    URI.createFileURI(resultPath), template, definitions, queryEnvironment, null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }
        try (FileInputStream resIs = new FileInputStream(resultPath);
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(5, resDocument.getBodyElements().size());
            assertTrue(resDocument.getBodyElements().get(1) instanceof XWPFTable);
            XWPFTable table = (XWPFTable) resDocument.getBodyElements().get(1);
            assertEquals(" Custom1 ", table.getRow(0).getCell(0).getParagraphs().get(0).getText());
            assertEquals(" Custom3 ", table.getRow(0).getCell(1).getParagraphs().get(0).getText());
            assertEquals(" Custom2 ", table.getRow(1).getCell(0).getParagraphs().get(0).getText());
            assertEquals(" Custom4 ", table.getRow(1).getCell(1).getParagraphs().get(0).getText());

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }

    }

    /**
     * Test UserDoc In Table with complex previous result (picture in cell 1 and table in cell 2).
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
    public void testUserDocInTableWithPreviousResult() throws Exception {
        // Remove previous generated file
        String resultPath = "results/generated/testUserDoc10Custom2.docx";
        File oldResult = new File(resultPath);
        if (oldResult.exists()) {
            oldResult.delete();
        }
        // Copy result in right place
        Files.copy(new File("userContent/testUserDoc10Custom2.docx"), new File(resultPath));

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        try (FileInputStream is = new FileInputStream("templates/testUserDoc10.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument(URI.createFileURI("templates/testUserDoc10.docx"));
            Map<String, Object> definitions = new HashMap<>();
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI("templates/testUserDoc10.docx"),
                    URI.createFileURI(resultPath), template, definitions, queryEnvironment, null);
            generator.generate();
            document.close();
            oPackage.close();
            is.close();
        }
        try (FileInputStream resIs = new FileInputStream(resultPath);
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(5, resDocument.getBodyElements().size());
            assertTrue(resDocument.getBodyElements().get(1) instanceof XWPFTable);
            XWPFTable table = (XWPFTable) resDocument.getBodyElements().get(1);
            assertEquals(1,
                    table.getRow(0).getCell(0).getParagraphs().get(2).getRuns().get(0).getEmbeddedPictures().size());
            assertEquals(new Long("1120175150"), table.getRow(0).getCell(0).getParagraphs().get(2).getRuns().get(0)
                    .getEmbeddedPictures().get(0).getPictureData().getChecksum());
            assertEquals("", table.getRow(1).getCell(0).getParagraphs().get(0).getText());
            assertEquals(" Custom2 ", table.getRow(1).getCell(0).getParagraphs().get(1).getText());

            // TODO OHA fix bug in nested table on usercontent and add asset

            resDocument.close();
            resOPackage.close();
            resIs.close();
        }

    }

}
