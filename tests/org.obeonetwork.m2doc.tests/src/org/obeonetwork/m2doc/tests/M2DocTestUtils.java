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
package org.obeonetwork.m2doc.tests;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
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
     * The hash function for binary content.
     */
    private static final HashFunction HF = Hashing.md5();
    /**
     * Image comparison threshold.
     */
    private static final double IMAGE_THRESHOLD = 0.4;

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
     * Links the given {@link IConstruct} with new {@link XWPFRun} created in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param construct
     *            the {@link IConstruct}
     */
    private static void linkRuns(XWPFParagraph paragraph, IConstruct construct) {
        construct.setStyleRun(paragraph.createRun());

        construct.getRuns().add(paragraph.createRun());
        construct.getRuns().add(paragraph.createRun());
        construct.getRuns().add(paragraph.createRun());

        for (EObject child : construct.eContents()) {
            if (child instanceof IConstruct) {
                linkRuns(paragraph, (IConstruct) child);
            }
        }

        construct.getClosingRuns().add(paragraph.createRun());
        construct.getClosingRuns().add(paragraph.createRun());
        construct.getClosingRuns().add(paragraph.createRun());
    }

    /**
     * Asserts that the given expected .docx and given actual .docx are the same.
     * 
     * @param expectedURI
     *            the expected .docx {@link URI}
     * @param actualURI
     *            the actual .docx path {@link URI}
     * @throws FileNotFoundException
     *             if .docx files can't be found
     * @throws IOException
     *             if .docx files can't be read
     */
    public static void assertDocx(URI expectedURI, URI actualURI) throws FileNotFoundException, IOException {
        final String expectedTextContent = getPortableString(getTextContent(expectedURI));
        final String actualTextContent = getPortableString(getTextContent(actualURI));
        assertEquals(expectedTextContent, actualTextContent);

        assertArchiveContent(expectedURI, actualURI);
    }

    /**
     * Gets the portable version of the given {@link String}.
     * 
     * @param textContent
     *            the text content
     * @return the portable version of the given {@link String}
     */
    private static String getPortableString(String textContent) {
        String res;

        res = textContent.replaceAll("file:/.*/M2Doc", "file:/.../M2Doc");
        res = res.replaceAll("Aucun fichier ou dossier de ce type", "No such file or directory");
        res = res.replaceAll("20[^ ]* [^ ]* - Lost", "20...date and time... - Lost");
        res = res.replaceAll("@[a-f0-9]{6,8} ", "@00000000 "); // object address in toString()

        return res;
    }

    /**
     * Gets the textual element of the .docx at the given {@link URI}.
     * 
     * @param uri
     *            the .docx {@link URI}
     * @return the textual element of the .docx at the given {@link URI}
     */
    public static String getTextContent(URI uri) {
        String result = "";

        try (InputStream is = URIConverter.INSTANCE.createInputStream(uri);
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
     * Asserts the archive content of expected and actual .docx.
     * 
     * @param expectedURI
     *            the expected {@link URI}
     * @param actualURI
     *            the actual {@link URI}
     * @throws IOException
     *             if .docx can't be read
     * @throws FileNotFoundException
     *             if .docx can't be found
     */
    public static void assertArchiveContent(URI expectedURI, URI actualURI) throws IOException, FileNotFoundException {

        try (InputStream expectedIs = URIConverter.INSTANCE.createInputStream(expectedURI);
                ZipInputStream expectedZin = new ZipInputStream(new BufferedInputStream(expectedIs));
                InputStream actualIs = URIConverter.INSTANCE.createInputStream(actualURI);
                ZipInputStream actualZin = new ZipInputStream(new BufferedInputStream(actualIs))) {

            List<ZipEntry> expectedEntries = new ArrayList<>();
            ZipEntry anExpectedEntry;
            while ((anExpectedEntry = expectedZin.getNextEntry()) != null) {
                expectedEntries.add(anExpectedEntry);
            }

            List<ZipEntry> actualEntries = new ArrayList<>();
            ZipEntry anActualEntry;
            while ((anActualEntry = actualZin.getNextEntry()) != null) {
                actualEntries.add(anActualEntry);
            }

            assertEquals(expectedEntries.size(), actualEntries.size());

            // Build a map in order to make sure we iterate on equivalent files (i.e. with the same name), because the order of iteration
            // from ZipInputStream depends on the order of creation.
            Map<ZipEntry, ZipEntry> entryMap = new HashMap<>();
            for (ZipEntry expectedEntry : expectedEntries) {
                for (ZipEntry actualEntry : actualEntries) {
                    if (expectedEntry.getName().equals(actualEntry.getName())) {
                        entryMap.put(expectedEntry, actualEntry);
                    }
                }
            }

            ZipEntry expectedEntry;
            ZipEntry actualEntry;
            while ((expectedEntry = expectedZin.getNextEntry()) != null) {
                actualEntry = actualZin.getNextEntry();
                if (expectedEntry.getName().endsWith(".xml") || expectedEntry.getName().endsWith(".rels")) {
                    final String expectedXMLContent = getXMLContent(expectedZin, expectedEntry);
                    final String actualXMLContent = getXMLContent(actualZin, actualEntry);
                    assertEquals(expectedXMLContent, actualXMLContent);
                } else if (expectedEntry.getName().endsWith(".jpeg") || expectedEntry.getName().endsWith(".jpg")) {
                    final File imageDiff = getDiffImageFile(expectedURI.toFileString(), expectedEntry);
                    ImageTestUtils.assertJPG(imageDiff, expectedZin, actualZin, IMAGE_THRESHOLD);
                } else if (expectedEntry.getName().endsWith(".gif")) {
                    final File imageDiff = getDiffImageFile(expectedURI.toFileString(), expectedEntry);
                    ImageTestUtils.assertGIF(imageDiff, expectedZin, actualZin, IMAGE_THRESHOLD);
                } else if (expectedEntry.getName().endsWith(".png")) {
                    final File imageDiff = getDiffImageFile(expectedURI.toFileString(), expectedEntry);
                    ImageTestUtils.assertPNG(imageDiff, expectedZin, actualZin, IMAGE_THRESHOLD);
                } else {
                    final String expectedHash = getZipEntryHash(expectedZin, expectedEntry);
                    final String actualHash = getZipEntryHash(actualZin, actualEntry);
                    assertEquals(expectedHash, actualHash);
                }
            }
        }
    }

    /**
     * Gets the image difference output file.
     * 
     * @param expectedDocxPath
     *            the expected .docx file path
     * @param entry
     *            the {@link ZipEntry}
     * @return the image difference output file
     * @throws IOException
     *             if the file can't be created
     */
    private static File getDiffImageFile(String expectedDocxPath, ZipEntry entry) throws IOException {
        final File result = new File(expectedDocxPath + "-diff-" + entry.getName().replaceAll("/", "-"));
        return result;
    }

    /**
     * Gets the XML contents of the given {@link ZipEntry}.
     * 
     * @param zin
     *            the {@link ZipInputStream}
     * @param entry
     *            the {@link ZipEntry}
     * @throws IOException
     *             if the {@link ZipInputStream} can't be read
     * @return the XML contents of the given {@link ZipEntry}
     */
    private static String getXMLContent(ZipInputStream zin, ZipEntry entry) throws IOException {
        final StringBuilder result = new StringBuilder();

        result.append(String.format("\n%s | ", entry.getName()));
        String fileContent = CharStreams.toString(new InputStreamReader(zin, Charsets.UTF_8));
        fileContent = indentXML(fileContent);
        // normalizing on \n to have a cross-platform comparison.
        fileContent = fileContent.replace("\r\n", "\n");
        // removing XML attributes which might change from run to run.
        fileContent = fileContent.replaceAll("rsidR=\"([^\"]+)", "");
        fileContent = fileContent.replaceAll("id=\"([^\"]+)", "");
        fileContent = fileContent.replaceAll("descr=\"([^\"]+)", "");
        fileContent = getPortableString(fileContent);
        result.append(String.format("\n%s\n", fileContent));

        return result.toString();
    }

    /**
     * Gets the name and hash of the given {@link ZipEntry}.
     * 
     * @param zin
     *            the {@link ZipInputStream}
     * @param entry
     *            the {@link ZipEntry}
     * @throws IOException
     *             if the {@link ZipInputStream} can't be read
     * @return the name and hash of the given {@link ZipEntry}
     */
    private static String getZipEntryHash(ZipInputStream zin, ZipEntry entry) throws IOException {
        final StringBuilder result = new StringBuilder();

        final HashCode code = HF.hashBytes(ByteStreams.toByteArray(zin));
        result.append(String.format("\n%s | ", entry.getName()));
        result.append(String.format("\n md5:%s", code.toString()));

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
