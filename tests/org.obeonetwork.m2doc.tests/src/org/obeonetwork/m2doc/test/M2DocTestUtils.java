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
package org.obeonetwork.m2doc.test;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentTemplateParser;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;

import static org.junit.Assert.assertEquals;

/**
 * Utilities for M2Doc tests.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class M2DocTestUtils {

    /**
     * Constructor.
     */
    private M2DocTestUtils() {
        // nothing to do here
    }

    /**
     * Asserts the given {@link TemplateValidationMessage}.
     * 
     * @param actualMessage
     *            the actual {@link TemplateValidationMessage}
     * @param expectedLevel
     *            the expected {@link TemplateValidationMessage#getLevel() level}
     * @param exprectedMessage
     *            the expected {@link TemplateValidationMessage#getMessage() message}
     * @param expectetLocation
     *            the expected {@link TemplateValidationMessage#getLocation() location}
     */
    public static void assertTemplateValidationMessage(TemplateValidationMessage actualMessage,
            ValidationMessageLevel expectedLevel, String exprectedMessage, XWPFRun expectetLocation) {
        assertEquals(expectedLevel, actualMessage.getLevel());
        assertEquals(exprectedMessage, actualMessage.getMessage());
        assertEquals(expectetLocation, actualMessage.getLocation());
    }

    /**
     * Gets the {@link XWPFRun} containing the given text in the given {@link XWPFDocument}.
     * 
     * @param document
     *            the {@link XWPFDocument}
     * @param text
     *            the {@link XWPFRun}
     * @return the {@link XWPFRun} containing the given text in the given {@link XWPFDocument} if any, <code>null</code> otherwise
     */
    public static XWPFRun getRunContaining(XWPFDocument document, String text) {
        XWPFRun res = null;

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.text().contains(text)) {
                    res = run;
                    break;
                }
            }
        }

        return res;
    }

    /**
     * Creates a new {@link DocumentTemplate} with the given {@link DocumentTemplate#getBody() body}. The body is linked to {@link XWPFRun}.
     * 
     * @param body
     *            the {@link Template}
     * @return a new {@link DocumentTemplate}
     */
    @SuppressWarnings("resource")
    public static DocumentTemplate createDocumentTemplate(Template body) {
        final DocumentTemplate res = TemplatePackage.eINSTANCE.getTemplateFactory().createDocumentTemplate();

        final XWPFDocument document = new XWPFDocument();
        res.setDocument(document);
        res.setBody(body);

        final XWPFParagraph paragraph = document.createParagraph();

        linkRuns(paragraph, body);

        return res;
    }

    /**
     * Links the given {@link AbstractConstruct} with new {@link XWPFRun} created in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param construct
     *            the {@link AbstractConstruct}
     */
    private static void linkRuns(XWPFParagraph paragraph, AbstractConstruct construct) {
        construct.setStyleRun(paragraph.createRun());

        construct.getRuns().add(paragraph.createRun());
        construct.getRuns().add(paragraph.createRun());
        construct.getRuns().add(paragraph.createRun());

        for (EObject child : construct.eContents()) {
            if (child instanceof AbstractConstruct) {
                linkRuns(paragraph, (AbstractConstruct) child);
            }
        }

        construct.getClosingRuns().add(paragraph.createRun());
        construct.getClosingRuns().add(paragraph.createRun());
        construct.getClosingRuns().add(paragraph.createRun());
    }

    /**
     * Generates the given template and check the result against the given result.
     * 
     * @param templatePath
     *            the template .docx path
     * @param resultPath
     *            the expected result .docx path
     * @param definitions
     *            the variables
     * @param generation
     *            the {@link Generation}
     * @throws Exception
     *             if something go wring
     */
    public static void doGenerateDocAndCheckText(String templatePath, String resultPath,
            Map<String, Object> definitions, Generation generation) throws Exception {
        doGenerateDocAndCheckText(templatePath, resultPath, definitions, generation, true);
    }

    /**
     * Generates the given template and check the result against the given result.
     * 
     * @param templatePath
     *            the template .docx path
     * @param resultPath
     *            the expected result .docx path
     * @param definitions
     *            the variables
     * @param generation
     *            the {@link Generation}
     * @param checkThroughPOI
     *            should we check the text extracted using PIO
     * @throws Exception
     *             if something go wring
     */
    public static void doGenerateDocAndCheckText(String templatePath, String resultPath,
            Map<String, Object> definitions, Generation generation, boolean checkThroughPOI) throws Exception {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        File out = null;
        try (FileInputStream is = new FileInputStream(templatePath)) {
            try (OPCPackage oPackage = OPCPackage.open(is)) {
                try (XWPFDocument document = new XWPFDocument(oPackage)) {
                    out = File.createTempFile(resultPath, "generated-test");
                    String outputPath = out.getAbsolutePath();
                    generate(templatePath, outputPath, queryEnvironment, definitions, generation);
                    assertDocx(resultPath, outputPath, checkThroughPOI);
                }
            }
        } finally {
            if (out != null) {
                out.delete();
            }
        }
    }

    /**
     * Generates the given template to the given output path.
     * 
     * @param templatePath
     *            the template path .docx
     * @param outputPath
     *            the output path .docx
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param variables
     *            the variables
     * @param generation
     *            the {@link Generation}
     * @throws Exception
     *             if something went wrong
     */
    public static void generate(String templatePath, String outputPath, IQueryEnvironment queryEnvironment,
            Map<String, Object> variables, Generation generation) throws Exception {
        try (FileInputStream is = new FileInputStream(templatePath)) {
            try (OPCPackage oPackage = OPCPackage.open(is)) {
                try (XWPFDocument document = new XWPFDocument(oPackage)) {
                    DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
                    DocumentTemplate template = parser.parseDocument(URI.createFileURI(templatePath));
                    DocumentGenerator generator = new DocumentGenerator(URI.createFileURI(templatePath),
                            URI.createFileURI(outputPath), template, variables, queryEnvironment, generation);
                    generator.generate();
                }
            }
        }
    }

    /**
     * Asserts that the given expected .docx and given actual .docx are the same.
     * 
     * @param expectedPath
     *            the expected .docx path
     * @param actualPath
     *            the actual .docx path
     * @param checkThroughPOI
     *            should we check the text extracted using PIO
     * @throws FileNotFoundException
     *             if .docx files can't be found
     * @throws IOException
     *             if .docx files can't be read
     */
    public static void assertDocx(String expectedPath, String actualPath, boolean checkThroughPOI)
            throws FileNotFoundException, IOException {
        if (checkThroughPOI) {
            final String expectedTextContent = getTextContent(expectedPath);
            final String actualTextContent = getTextContent(actualPath);
            assertEquals(expectedTextContent, actualTextContent);
        }
        final String expectedArchiveContent = getArchiveContent(expectedPath);
        final String actualArchiveContent = getArchiveContent(actualPath);
        assertEquals(expectedArchiveContent, actualArchiveContent);
    }

    /**
     * Gets the textual element of the .docx at the given path.
     * 
     * @param path
     *            the .docx path
     * @return the textual element of the .docx at the given path
     */
    public static String getTextContent(String path) {
        String result = "";

        try (FileInputStream is = new FileInputStream(path);
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFWordExtractor ex = new XWPFWordExtractor(document);) {

            result += "===== Document Text ====\n";
            result += ex.getText();
            // CHECKSTYLE:OFF
        } catch (Throwable e) {
            // CHECKSTYLE:ON
            /*
             * if for some reason we can't use POI to get the text content then move along, we'll still get the XML and hashs
             */
        }
        return result;
    }

    /**
     * Gets the textual representation of non textual element of the .docx at the given path.
     * 
     * @param path
     *            the path
     * @return the textual representation of non textual element of the .docx at the given path
     * @throws IOException
     *             if .docx can't be read
     * @throws FileNotFoundException
     *             if .docx can't be found
     */
    public static String getArchiveContent(String path) throws IOException, FileNotFoundException {
        StringBuilder result = new StringBuilder();
        HashFunction hf = Hashing.md5();
        try (FileInputStream is = new FileInputStream(path);
                ZipInputStream zin = new ZipInputStream(new BufferedInputStream(is))) {

            result.append("\n===== Archive Content ====");
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null) {
                result.append(String.format("\n%s | ", entry.getName()));
                if (entry.getName().endsWith(".xml") || entry.getName().endsWith(".rels")) {
                    String fileContent = CharStreams.toString(new InputStreamReader(zin, Charsets.UTF_8));
                    fileContent = indentXML(fileContent);
                    // normalizing on \n to have a cross-platform comparison.
                    fileContent = fileContent.replace("\r\n", "\n");
                    // removing XML attributes which might change from run to run.
                    fileContent = fileContent.replaceAll("rsidR=\"([^\"]+)", "");
                    fileContent = fileContent.replaceAll("id=\"([^\"]+)", "");
                    fileContent = fileContent.replaceAll("descr=\"([^\"]+)", "");
                    result.append(String.format("\n%s\n", fileContent));
                } else {
                    HashCode code = hf.hashBytes(ByteStreams.toByteArray(zin));
                    result.append(String.format("\n md5:%s", code.toString()));
                }
            }
        }

        return result.toString();
    }

    /**
     * Indents the given XML file content.
     * 
     * @param fileContent
     *            the XML file content.
     * @return the indented XML file content
     */
    private static String indentXML(String fileContent) {
        final String res;

        StreamResult xmlOutput = null;
        try {
            // Configure transformer
            Transformer transformer;
            Source xmlInput = new StreamSource(new StringReader(fileContent));
            xmlOutput = new StreamResult(new StringWriter());
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            try {
                transformer.transform(xmlInput, xmlOutput);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        }

        if (xmlOutput != null) {
            res = xmlOutput.getWriter().toString();
        } else {
            res = fileContent;
        }
        return res;
    }

}
