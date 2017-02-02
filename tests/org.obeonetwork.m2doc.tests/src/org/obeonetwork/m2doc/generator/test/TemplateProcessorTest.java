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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.BookmarkManager;
import org.obeonetwork.m2doc.generator.TemplateProcessor;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.Template;

import static org.junit.Assert.assertEquals;

/**
 * Test the {@link TemplateProcessor} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateProcessorTest {

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
                    destinationDoc);
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
                    destinationDoc);
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
                    destinationDoc);
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
                    destinationDoc);
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
                    destinationDoc);
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
                    destinationDoc);
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
                    destinationDoc);
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
                    destinationDoc);
            processor.doSwitch(template);
            assertEquals(1, destinationDoc.getParagraphs().size());
            assertEquals("text\n\n\n", destinationDoc.getParagraphs().get(0).getText());
        }
    }

}
