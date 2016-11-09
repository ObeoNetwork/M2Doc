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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.generator.BookmarkManager;
import org.obeonetwork.m2doc.generator.TemplateProcessor;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.Template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@link TemplateProcessor} class.
 * 
 * @author ohaegi
 */
public class TemplateProcessorUserDocRemplaceTest {
    /**
     * Root object of the genconf example model.
     */
    private EObject rootObject;

    /**
     * Query environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    /**
     * Create Destination Document.
     * 
     * @param inputDocumentFileName
     *            inputDocumentFileName
     * @return XWPFDocument
     * @throws IOException
     *             IOException
     * @throws InvalidFormatException
     *             InvalidFormatException
     */
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
     * Setup.
     */
    @Before
    public void setup() {
        ResourceSet rs = new ResourceSetImpl();
        rs.getPackageRegistry().put(GenconfPackage.eNS_URI, GenconfPackage.eINSTANCE);

        Registry r = rs.getResourceFactoryRegistry();
        Map<String, Object> m = r.getExtensionToFactoryMap();
        m.put("genconf", new XMIResourceFactoryImpl());

        URI uri = URI.createFileURI(new File("resources/semantic.genconf").getAbsolutePath());
        Resource resource = rs.getResource(uri, true);
        rootObject = resource.getContents().get(0);
    }

    /**
     * Test userdoc.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocTagWithImageInUserContent()
            throws InvalidFormatException, IOException, DocumentParserException {
        // CHECKSTYLE:OFF
        String templatePath = "templates/testUserDoc1.docx";
        // CHECKSTYLE:ON
        XWPFDocument document = loadDoc(templatePath);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc1.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager userContentManager = new UserContentManager(
                "userContent/testUserContent1Custom1.docx");
        // CHECKSTYLE:OFF
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager, userContentManager,
                env, destinationDoc, rootObject);
        // CHECKSTYLE:ON
        processor.doSwitch(template);

        String resultDoc = "results/generated/testUserDoc1Custom1Resultat.docx";
        POIServices.getInstance().saveFile(destinationDoc, resultDoc);
        // Reload generated document

        XWPFDocument reloadDocument = loadDoc(resultDoc);
        // CHECKSTYLE:OFF
        assertEquals(6, reloadDocument.getParagraphs().size());
        assertEquals(0, reloadDocument.getParagraphs().get(0).getCTP().getFldSimpleList().size());
        XWPFParagraph paragraph3 = reloadDocument.getParagraphs().get(2);
        assertEquals("Custom texte avec image ", paragraph3.getRuns().get(0).getText(0));
        assertEquals(1, paragraph3.getRuns().get(1).getEmbeddedPictures().size());
        userContentManager.deleteTempGeneratedFile();
        // CHECKSTYLE:ON
    }

    /**
     * Test userdoc.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocTagWithTableInUserContent()
            throws InvalidFormatException, IOException, DocumentParserException {
        String templatePath = "templates/testUserDoc1.docx";
        XWPFDocument document = loadDoc(templatePath);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc1.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager userContentManager = new UserContentManager(
                "userContent/testUserContent1Custom2.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager, userContentManager,
                env, destinationDoc, rootObject);
        processor.doSwitch(template);
        String resultDoc = "results/generated/testUserDoc1Custom2Resultat.docx";
        POIServices.getInstance().saveFile(destinationDoc, resultDoc);

        // Reload generated document
        XWPFDocument reloadDocument = loadDoc(resultDoc);

        // CHECKSTYLE:OFF
        assertEquals(8, reloadDocument.getParagraphs().size());
        assertTrue(reloadDocument.getBodyElements().get(4) instanceof XWPFTable);
        XWPFTable table1 = (XWPFTable) reloadDocument.getBodyElements().get(4);
        assertEquals("Un", table1.getRow(0).getCell(0).getParagraphs().get(0).getText());
        userContentManager.deleteTempGeneratedFile();
        // CHECKSTYLE:ON
    }

    /**
     * Test userdoc.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocTagWithTableAndPicture()
            throws InvalidFormatException, IOException, DocumentParserException {
        String templatePath = "templates/testUserDoc1.docx";
        XWPFDocument document = loadDoc(templatePath);

        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc1.docx");
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager userContentManager = new UserContentManager(
                "userContent/testUserContent1Custom3.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager, userContentManager,
                env, destinationDoc, rootObject);
        processor.doSwitch(template);
        String resultPath = "results/generated/testUserDoc1Custom3Resultat.docx";
        POIServices.getInstance().saveFile(destinationDoc, resultPath);

        XWPFDocument reloadDocument = loadDoc(resultPath);
        // CHECKSTYLE:OFF
        assertEquals(8, reloadDocument.getParagraphs().size());
        // CHECKSTYLE:ON
        assertTrue(reloadDocument.getBodyElements().get(4) instanceof XWPFTable);
        XWPFTable table1 = (XWPFTable) reloadDocument.getBodyElements().get(4);
        assertEquals("Un", table1.getRow(0).getCell(0).getParagraphs().get(0).getText());
        userContentManager.deleteTempGeneratedFile();
    }

    /**
     * Test userdoc.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void test3UserDocTagInOrder() throws InvalidFormatException, IOException, DocumentParserException {
        String templatePath = "templates/testUserDoc7.docx";
        XWPFDocument document = loadDoc(templatePath);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument(templatePath);
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager userContentManager = new UserContentManager(
                "userContent/testUserContent7Custom1.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager, userContentManager,
                env, destinationDoc, rootObject);
        processor.doSwitch(template);
        String resultPath = "results/generated/testUserDoc7Custom1Resultat.docx";
        POIServices.getInstance().saveFile(destinationDoc, resultPath);
        // Reload generated document
        XWPFDocument reloadDocument = loadDoc(resultPath);

        // CHECKSTYLE:OFF
        assertEquals(18, reloadDocument.getParagraphs().size());

        XWPFParagraph paragraph3 = reloadDocument.getParagraphs().get(2);
        assertEquals("Custom 1", paragraph3.getText());

        assertTrue(reloadDocument.getBodyElements().get(7) instanceof XWPFTable);
        XWPFTable table1 = (XWPFTable) reloadDocument.getBodyElements().get(7);
        assertEquals("Un", table1.getRow(0).getCell(0).getParagraphs().get(0).getText());

        XWPFParagraph paragraph14 = reloadDocument.getParagraphs().get(13);

        assertEquals(1, paragraph14.getRuns().get(0).getEmbeddedPictures().size());

        userContentManager.deleteTempGeneratedFile();
        // CHECKSTYLE:ON
    }

    /**
     * Test userdoc.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocOnLine() throws InvalidFormatException, IOException, DocumentParserException {
        String templatePath = "templates/testUserDoc2.docx";
        XWPFDocument document = loadDoc(templatePath);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument(templatePath);
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager userContentManager = new UserContentManager(
                "userContent/testUserContent2Custom1.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager, userContentManager,
                env, destinationDoc, rootObject);
        processor.doSwitch(template);

        String resultPath = "results/generated/testUserDoc2Custom1Resultat.docx";
        POIServices.getInstance().saveFile(destinationDoc, resultPath);

        // Reload generated document
        XWPFDocument reloadDocument = loadDoc(resultPath);

        // CHECKSTYLE:OFF
        assertEquals(4, reloadDocument.getParagraphs().size());
        XWPFParagraph paragraph2 = reloadDocument.getParagraphs().get(1);
        assertTrue(paragraph2.getText().startsWith("Début"));
        assertTrue(paragraph2.getText().contains("Custom texte"));
        assertTrue(paragraph2.getText().endsWith("fin"));
        userContentManager.deleteTempGeneratedFile();
        // CHECKSTYLE:ON
    }

    /**
     * Load doc from path.
     * 
     * @param docPath
     *            resultPath
     * @return document
     * @throws FileNotFoundException
     *             FileNotFoundException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     */
    private XWPFDocument loadDoc(String docPath) throws FileNotFoundException, InvalidFormatException, IOException {
        FileInputStream is = new FileInputStream(docPath);
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        return document;
    }
}
