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

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import java.io.BufferedInputStream;
import java.io.File;
//CHECKSTYLE:OFF
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Ignore;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentGeneratorTest {

    private void doGenerateDocAndCheckText(String templatePath, String resultPath, Map<String, Object> definitions)
            throws FileNotFoundException, IOException, InvalidFormatException, DocumentParserException,
            DocumentGenerationException {
        doGenerateDocAndCheckText(templatePath, resultPath, definitions, true);
    }

    private void doGenerateDocAndCheckText(String templatePath, String resultPath, Map<String, Object> definitions,
            boolean checkThroughPOI) throws FileNotFoundException, IOException, InvalidFormatException,
            DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        String previousTextContent = getTextContent(resultPath);
        String previousArchiveContent = getArchiveContent(resultPath);
        File out = null;
        try (FileInputStream is = new FileInputStream(templatePath)) {
            OPCPackage oPackage = OPCPackage.open(is);
            XWPFDocument document = new XWPFDocument(oPackage);
            DocumentParser parser = new DocumentParser(document, queryEnvironment);
            DocumentTemplate template = parser.parseDocument();
            out = File.createTempFile(resultPath, "generated-test");
            String outputPath = out.getAbsolutePath();
            DocumentGenerator generator = new DocumentGenerator(templatePath, outputPath, template, definitions,
                    queryEnvironment, null);
            generator.generate();
            if (checkThroughPOI) {
                assertEquals(previousTextContent, getTextContent(outputPath));
            }
            assertEquals(previousArchiveContent, getArchiveContent(outputPath));

        } finally {
            if (out != null) {
                out.delete();
            }
        }
    }

    private String loadTextRepresentation(String path)
            throws FileNotFoundException, IOException, InvalidFormatException {
        String result = getTextContent(path);

        return result += getArchiveContent(path);
    }

    /**
     * @param path
     * @return
     */
    private String getTextContent(String path) {
        String result = "";

        try (FileInputStream is = new FileInputStream(path);
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFWordExtractor ex = new XWPFWordExtractor(document);) {

            result += "===== Document Text ====\n";
            result += ex.getText();
        } catch (Throwable e) {
            /*
             * if for some reason we can't use POI to get the text content then move along, we'll still get the XML and hashs
             */
        }
        return result;
    }

    /**
     * @param path
     * @param result
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    private String getArchiveContent(String path) throws IOException, FileNotFoundException {
        String result = "";
        HashFunction hf = Hashing.md5();
        try (FileInputStream is = new FileInputStream(path);
                ZipInputStream zin = new ZipInputStream(new BufferedInputStream(is))) {

            result += "\n===== Archive Content ====";
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                result += "\n" + entry.getName() + " | ";
                if (entry.getName().endsWith(".xml") || entry.getName().endsWith(".rels")) {
                    String fileContent = CharStreams.toString(new InputStreamReader(zin, Charsets.UTF_8));
                    // normalizing on \n to have a cross-platform comparison.
                    fileContent = fileContent.replace("\r\n", "\n");
                    // removing XML attributes which might change from run to run.
                    fileContent = fileContent.replaceAll("rsidR=\"([^\"]+)", "");
                    fileContent = fileContent.replaceAll("id=\"([^\"]+)", "");
                    result += "\n" + fileContent;
                } else {
                    HashCode code = hf.hashBytes(ByteStreams.toByteArray(zin));
                    result += "\n md5:" + code.toString();
                }
            }
        }

        return result;
    }

    @Test
    public void testFormsAndTextArea()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        doGenerateDocAndCheckText("templates/testTextAreaAndForms.docx", "results/testTextAreaAndForms.docx",
                definitions);
    }

    @Test
    public void testStaticFragmentWithFieldProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testStaticFragmentWithfields.docx",
                "results/testStaticFragmentWithfields.docx", definitions);

    }

    @Test
    public void testStaticFragmentProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testStaticFragment.docx", "results/testStaticFragment.docx", definitions);
    }

    @Test
    public void testVarRefInHeaderProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testVarInHeader.docx", "results/testVarInHeaderResult.docx", definitions);
    }

    @Test
    public void testVarRefInFooterProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testVarInFooter.docx", "results/testVarInFooterResult.docx", definitions);

    }

    @Test
    public void testVarRefProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testVar.docx", "results/testVarResult.docx", definitions);
    }

    @Test
    public void testVarRefErrorProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/testVar.docx", "results/testVarResultError.docx", definitions);
    }

    @Test
    public void testVarRefStyledProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testVarStyle.docx", "results/testVarStyleResult.docx", definitions);
    }

    @Test
    public void testVarRefStyledMultipleParagraphsProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testVarStyleSpanning2Paragraphs.docx",
                "results/testVarStyleSpanning2ParagraphsResult.docx", definitions);
    }

    @Test
    public void testQueryStyledProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        doGenerateDocAndCheckText("templates/testAQL.docx", "results/testAQLResult.docx", definitions);
    }

    @Test
    public void testQueryStyledErrorProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        // TODO this can't be right! two tests modifying the same data.
        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/testAQL.docx", "results/testAQLResultError.docx", definitions);
    }

    @Test
    public void testGDFORProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        doGenerateDocAndCheckText("templates/testGDFOR.docx", "results/testGDFOR.docx", definitions);

    }

    @Test
    public void testGDFORWithTableProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        doGenerateDocAndCheckText("templates/testGDFORWithTable.docx", "results/testGDFORWithTable.docx", definitions);
    }

    @Test
    public void testConditionnal1trueProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "value1");
        doGenerateDocAndCheckText("templates/testConditionnal1.docx", "results/testConditionnal1Result.docx",
                definitions);

    }

    @Test
    public void testConditionnal1falseProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testConditionnal1.docx", "results/testConditionnal1FalseResult.docx",
                definitions);

    }

    @Test
    public void testConditionnal2Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "value1");
        doGenerateDocAndCheckText("templates/testConditionnal2.docx", "results/testConditionnal2Result.docx",
                definitions);
    }

    @Test
    public void testConditionnalFalseProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testConditionnal2.docx", "results/testConditionnal2FalseResult.docx",
                definitions);

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

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("self", EcorePackage.eINSTANCE);
        doGenerateDocAndCheckText("templates/test.docx", "results/testResult.docx", definitions);

    }

    @Test
    public void testConditionnal5Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "value1");
        doGenerateDocAndCheckText("templates/testConditionnal5.docx", "results/testConditionnal5Result.docx",
                definitions);
    }

    @Test
    public void testConditionnal6Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "value2");
        doGenerateDocAndCheckText("templates/testConditionnal5.docx", "results/testConditionnal6Result.docx",
                definitions);
    }

    @Test
    public void testConditionnal7Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "value3");
        doGenerateDocAndCheckText("templates/testConditionnal5.docx", "results/testConditionnal7Result.docx",
                definitions);
    }

    @Test
    public void testConditionnal8Processing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testConditionnal5.docx", "results/testConditionnal8Result.docx",
                definitions);
    }

    @Test
    @Ignore(value = "Seems like nobody knows yet what is the actual expected result.")
    public void testImageGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        definitions.put("x", "valueofx");
        doGenerateDocAndCheckText("templates/testImageTag.docx", "results/testImageTag.docx", definitions, false);
    }

    @Test
    @Ignore(value = "Seems like nobody knows yet what is the actual expected result.")
    public void testDiagramGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/allDiagram.docx", "results/allDiagram.docx", definitions, false);
    }

    @Test
    public void testStaticHyperlink()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/staticHyperlink.docx", "results/staticHyperlink.docx", definitions);

    }

    @Test
    public void testDynamicHyperlink()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/dynamicHyperlink.docx", "results/dynamicHyperlink.docx", definitions);

    }

    @Test
    public void testBookmarkNominal()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/testBookmarkNominal.docx", "results/testBookmarkNominal.docx",
                definitions);

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

    @Test
    public void testBookmarkNoBookmark()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {

        Map<String, Object> definitions = new HashMap<>();
        doGenerateDocAndCheckText("templates/testBookmarkNoBookmark.docx", "results/testBookmarkNoBookmark.docx",
                definitions);

        try (FileInputStream resIs = new FileInputStream("results/testBookmarkNoBookmark.docx");
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(2, resDocument.getBodyElements().size());
            assertTrue(resDocument.getBodyElements().get(0) instanceof XWPFParagraph);
            XWPFParagraph paragraph = (XWPFParagraph) resDocument.getBodyElements().get(0);
            assertEquals(10, paragraph.getRuns().size());
            assertEquals("Test link ", paragraph.getRuns().get(0).text());
            assertEquals("without", paragraph.getRuns().get(1).text());
            assertEquals(" bookmark : ", paragraph.getRuns().get(2).text());

            resOPackage.close();
            resDocument.close();
        }
    }

}
