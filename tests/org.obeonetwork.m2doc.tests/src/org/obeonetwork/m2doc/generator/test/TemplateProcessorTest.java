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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
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
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.util.FieldUtils;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@link TemplateProcessor} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateProcessorTest {

    /**
     * {@link FieldUtils} instance used during testing.
     */
    private FieldUtils fieldUtils = new FieldUtils();

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
    @SuppressWarnings("resource")
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
     * Tests NewLine processing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testNewLineProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        try ( // CHECKSTYLE:OFF
                FileInputStream is = new FileInputStream(
                        "resources/document/carriageReturn/carriageReturn-template.docx");
                // CHECKSTYLE:ON
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            // CHECKSTYLE:OFF
            definitions.put("v", "part1\npart2\npart3\npart4");
            // CHECKSTYLE:ON
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("part1\npart2\npart3\npart4", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests NewLine processing with No Text Around.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testNewLineProcessingNoTextAround()
            throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            // CHECKSTYLE:OFF
            definitions.put("v", "\n\n\n");
            // CHECKSTYLE:ON
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("\n\n\n", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests NewLine processing with No Text Before.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testNewLineProcessingNoTextBefore()
            throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            // CHECKSTYLE:OFF
            definitions.put("v", "\n\n\ntext");
            // CHECKSTYLE:ON
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("\n\n\ntext", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests NewLine processing with No Text After.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testNewLineProcessingNoTextAfter() throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            // CHECKSTYLE:OFF
            definitions.put("v", "text\n\n\n");
            // CHECKSTYLE:ON
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("text\n\n\n", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests Carriage Return NewLine processing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testCarryageReturnNewLineProcessing()
            throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            definitions.put("v", "part1\r\npart2\r\npart3\r\npart4");
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("part1\npart2\npart3\npart4", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests Carriage Return NewLine processing with No Text Around.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testCarryageReturnNewLineProcessingNoTextAround()
            throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            definitions.put("v", "\r\n\r\n\r\n");
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("\n\n\n", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests Carriage Return NewLine processing with No Text Before.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testCarryageReturnNewLineProcessingNoTextBefore()
            throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            definitions.put("v", "\r\n\r\n\r\ntext");
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("\n\n\ntext", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests Carryage Return NewLine processing with No Text After.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testCarryageReturnNewLineProcessingNoTextAfter()
            throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("resources/document/carriageReturn/carriageReturn-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument(
                        "resources/document/carriageReturn/carriageReturn-template.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            definitions.put("v", "text\r\n\r\n\r\n");
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, null);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("text\n\n\n", destinationDoc.getParagraphs().get(0).getText());
        }
    }

    /**
     * Tests userdoc template tag without userContent tag support.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocSimple() throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc1.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager,
                    userContentManager, env, destinationDoc, rootObject);
            processor.doSwitch(template);
            // POIServices.getInstance().saveFile(destinationDoc, "results/generated/testUserDoc1Resultat.docx");
            // CHECKSTYLE:OFF
            assertEquals(8, destinationDoc.getParagraphs().size());
            assertEquals(0, destinationDoc.getParagraphs().get(0).getCTP().getFldSimpleList().size());
            assertEquals(1, destinationDoc.getParagraphs().get(1).getCTP().getFldSimpleList().size());
            assertEquals("m:usercontent value1",
                    destinationDoc.getParagraphs().get(1).getCTP().getFldSimpleList().get(0).getInstr());
            assertEquals(1, destinationDoc.getParagraphs().get(5).getCTP().getFldSimpleList().size());
            assertEquals("m:endusercontent",
                    destinationDoc.getParagraphs().get(5).getCTP().getFldSimpleList().get(0).getInstr());
            // CHECKSTYLE:ON
        }
    }

    /**
     * Tests userdoc template tag without userContent tag support.
     * userdoc tag in one line
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocOneLine() throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("templates/testUserDoc2.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc2.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager,
                    userContentManager, env, destinationDoc, rootObject);
            processor.doSwitch(template);
            // POIServices.getInstance().saveFile(destinationDoc, "results/generated/testUserDoc2Resultat.docx");
            // CHECKSTYLE:OFF
            assertEquals(4, destinationDoc.getParagraphs().size());
            assertEquals(0, destinationDoc.getParagraphs().get(0).getCTP().getFldSimpleList().size());
            assertEquals(2, destinationDoc.getParagraphs().get(1).getCTP().getFldSimpleList().size());
            assertEquals("m:usercontent value1",
                    destinationDoc.getParagraphs().get(1).getCTP().getFldSimpleList().get(0).getInstr());
            assertEquals("m:endusercontent",
                    destinationDoc.getParagraphs().get(1).getCTP().getFldSimpleList().get(1).getInstr());
            // CHECKSTYLE:ON
        }
    }

    /**
     * Tests userdoc template tag without userContent tag support with Tag In Content (parsing errors).
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocTagInContent() throws InvalidFormatException, IOException, DocumentParserException {
        try (FileInputStream is = new FileInputStream("templates/testUserDoc3.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc3.docx");) {
            BodyTemplateParser parser = new BodyTemplateParser(document, env);
            Template template = parser.parseTemplate();
            Map<String, Object> definitions = new HashMap<String, Object>();
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager,
                    userContentManager, env, destinationDoc, rootObject);
            processor.doSwitch(template);
            // POIServices.getInstance().saveFile(destinationDoc, "results/generated/testUserDoc3Resultat.docx");
            // CHECKSTYLE:OFF
            assertEquals(9, destinationDoc.getParagraphs().size());
            assertEquals(0, destinationDoc.getParagraphs().get(0).getCTP().getFldSimpleList().size());
            XWPFParagraph paragraph1 = destinationDoc.getParagraphs().get(1);
            assertEquals(1, paragraph1.getCTP().getFldSimpleList().size());
            assertEquals("m:usercontent value1", paragraph1.getCTP().getFldSimpleList().get(0).getInstr());
            // CHECKSTYLE:ON
        }
    }

    /**
     * Tests userdoc template tag without userContent tag support with no AQL parameter (parsing errors).
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocTagNoAql() throws InvalidFormatException, IOException, DocumentParserException {
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(URI.createFileURI("templates/testUserDoc4.docx"),
                env);
                FileInputStream is = new FileInputStream("templates/testUserDoc4.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);
                XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc4.docx");) {
            Map<String, Object> definitions = new HashMap<String, Object>();
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager,
                    userContentManager, env, destinationDoc, rootObject);
            processor.doSwitch(documentTemplate.getBody());
            // POIServices.getInstance().saveFile(destinationDoc, "results/generated/testUserDoc4Resultat.docx");
            // CHECKSTYLE:OFF
            assertEquals(5, destinationDoc.getParagraphs().size());
            assertEquals(0, destinationDoc.getParagraphs().get(0).getCTP().getFldSimpleList().size());
            XWPFParagraph paragraph1 = destinationDoc.getParagraphs().get(1);
            assertEquals(1, paragraph1.getRuns().get(0).getCTR().getFldCharList().size());
            assertEquals("m:userdoc", fieldUtils.lookAheadTag(paragraph1.getRuns()));
            int paragraph1RunNbr = paragraph1.getRuns().size();
            assertEquals("Syntax error in AQL expression: Expression \"\" is invalid: null or empty string.",
                    paragraph1.getRuns().get(paragraph1RunNbr - 1).getText(0));
            // CHECKSTYLE:ON
        }
    }

    /**
     * Tests userdoc With No Unique Id.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocWithNoUniqueId() throws InvalidFormatException, IOException, DocumentParserException {
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(URI.createFileURI("templates/testUserDoc6.docx"),
                env); XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc6.docx");) {
            Map<String, Object> definitions = new HashMap<String, Object>();
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, "results", bookmarkManager,
                    userContentManager, env, destinationDoc, rootObject);
            processor.doSwitch(documentTemplate.getBody());
            POIServices.getInstance().saveFile(destinationDoc,
                    URI.createFileURI("results/generated/testUserDoc6Resultat.docx"));

            // CHECKSTYLE:OFF
            assertEquals(12, destinationDoc.getParagraphs().size());
            XWPFParagraph paragraph5 = destinationDoc.getParagraphs().get(4);
            assertEquals(
                    "The id 'value1' is already used in generated document. Ids must be unique otherwise document part contained userContent could be lost at next generation.",
                    paragraph5.getRuns().get(0).getText(0));
            XWPFParagraph paragraph8 = destinationDoc.getParagraphs().get(7);
            assertEquals(
                    "The id 'value1' is already used in generated document. Ids must be unique otherwise document part contained userContent could be lost at next generation.",
                    paragraph8.getRuns().get(0).getText(0));
            // CHECKSTYLE:ON
        }
    }

    /**
     * Tests userdoc With No Unique Id.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocWithOutEndUserDoc() throws InvalidFormatException, IOException, DocumentParserException {
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(URI.createFileURI("templates/testUserDoc8.docx"),
                env); XWPFDocument destinationDoc = createDestinationDocument("templates/testUserDoc8.docx");) {
            Map<String, Object> definitions = new HashMap<String, Object>();
            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(URI.createFileURI("noResult"));
            TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager, env,
                    destinationDoc, rootObject);
            processor.doSwitch(documentTemplate.getBody());
            POIServices.getInstance().saveFile(destinationDoc,
                    URI.createFileURI("results/generated/testUserDoc8Resultat.docx"));

            assertTrue(documentTemplate.getBody().getBody().getStatements().get(1) instanceof UserDoc);
            UserDoc userDoc = (UserDoc) documentTemplate.getBody().getBody().getStatements().get(1);
            assertTrue(userDoc.getClosingRuns().isEmpty());
            assertEquals("Unexpected tag EOF at this location missing [ENDUSERDOC]",
                    userDoc.getBody().getValidationMessages().get(0).getMessage());
            assertEquals(ValidationMessageLevel.ERROR, userDoc.getBody().getValidationMessages().get(0).getLevel());
            XWPFRun lastRunOfContent = userDoc.getBody().getStatements().get(0).getRuns()
                    .get(userDoc.getBody().getStatements().get(0).getRuns().size() - 1);
            assertEquals(lastRunOfContent, userDoc.getBody().getValidationMessages().get(0).getLocation());
            assertEquals(1, userDoc.getBody().getValidationMessages().size());
        }
    }
}
