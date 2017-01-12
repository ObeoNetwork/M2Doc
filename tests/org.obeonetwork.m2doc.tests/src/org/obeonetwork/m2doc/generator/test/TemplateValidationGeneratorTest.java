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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.test.M2DocTestUtils;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link TemplateGenerator} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateValidationGeneratorTest {

    /**
     * Ensure that the validation generation produces a document with an info.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testInfoGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        final File tempFile = File.createTempFile("testParsingErrorSimpleTag", ".docx");

        try (DocumentTemplate template = M2DocUtils
                .parse(URI.createFileURI("resources/document/notEmpty/notEmpty-template.docx"), queryEnvironment)) {
            final XWPFRun location = ((XWPFParagraph) template.getDocument().getBodyElements().get(0)).getRuns().get(0);
            template.getBody().getValidationMessages().add(
                    new TemplateValidationMessage(ValidationMessageLevel.INFO, "XXXXXXXXXXXXXXXXXXXXXXXX", location));
            M2DocUtils.serializeValidatedDocumentTemplate(template, URI.createFileURI(tempFile.getAbsolutePath()));
        }
        assertTrue(new File(tempFile.getAbsolutePath()).exists());

        try (FileInputStream resIs = new FileInputStream(tempFile.getAbsolutePath());
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            final XWPFRun messageRun = M2DocTestUtils.getRunContaining(resDocument, "XXXXXXXXXXXXXXXXXXXXXXXX");

            assertNotNull(messageRun);
            assertEquals("XXXXXXXXXXXXXXXXXXXXXXXX", messageRun.text());
            assertEquals("0000FF", messageRun.getColor());
        }

        tempFile.delete();
    }

    /**
     * Ensure that the validation generation produces a document with an warning.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testWarningGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        final File tempFile = File.createTempFile("testParsingErrorSimpleTag", ".docx");

        try (DocumentTemplate template = M2DocUtils
                .parse(URI.createFileURI("resources/document/notEmpty/notEmpty-template.docx"), queryEnvironment)) {
            final XWPFRun location = ((XWPFParagraph) template.getDocument().getBodyElements().get(0)).getRuns().get(0);
            template.getBody().getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.WARNING,
                    "XXXXXXXXXXXXXXXXXXXXXXXX", location));
            M2DocUtils.serializeValidatedDocumentTemplate(template, URI.createFileURI(tempFile.getAbsolutePath()));
        }
        assertTrue(new File(tempFile.getAbsolutePath()).exists());

        try (FileInputStream resIs = new FileInputStream(tempFile.getAbsolutePath());
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            final XWPFRun messageRun = M2DocTestUtils.getRunContaining(resDocument, "XXXXXXXXXXXXXXXXXXXXXXXX");

            assertNotNull(messageRun);
            assertEquals("XXXXXXXXXXXXXXXXXXXXXXXX", messageRun.text());
            assertEquals("FFA500", messageRun.getColor());
        }

        tempFile.delete();
    }

    /**
     * Ensure that the validation generation produces a document with an error.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testErrorGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        final File tempFile = File.createTempFile("testParsingErrorSimpleTag", ".docx");

        try (DocumentTemplate template = M2DocUtils
                .parse(URI.createFileURI("resources/document/notEmpty/notEmpty-template.docx"), queryEnvironment)) {
            final XWPFRun location = ((XWPFParagraph) template.getDocument().getBodyElements().get(0)).getRuns().get(0);
            template.getBody().getValidationMessages().add(
                    new TemplateValidationMessage(ValidationMessageLevel.ERROR, "XXXXXXXXXXXXXXXXXXXXXXXX", location));
            M2DocUtils.serializeValidatedDocumentTemplate(template, URI.createFileURI(tempFile.getAbsolutePath()));
        }
        assertTrue(new File(tempFile.getAbsolutePath()).exists());

        try (FileInputStream resIs = new FileInputStream(tempFile.getAbsolutePath());
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            final XWPFRun messageRun = M2DocTestUtils.getRunContaining(resDocument, "XXXXXXXXXXXXXXXXXXXXXXXX");

            assertNotNull(messageRun);
            assertEquals("XXXXXXXXXXXXXXXXXXXXXXXX", messageRun.text());
            assertEquals("FF0000", messageRun.getColor());
        }

        tempFile.delete();
    }

    /**
     * Ensure that the validation generation produces a document with errors in the good order.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testErrorGenerationOrder()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        final File tempFile = File.createTempFile("testParsingErrorSimpleTag", ".docx");

        try (DocumentTemplate template = M2DocUtils
                .parse(URI.createFileURI("resources/document/notEmpty/notEmpty-template.docx"), queryEnvironment)) {
            final XWPFRun location = ((XWPFParagraph) template.getDocument().getBodyElements().get(0)).getRuns().get(0);
            template.getBody().getValidationMessages()
                    .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, "AAAA", location));
            template.getBody().getValidationMessages()
                    .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, "BBBB", location));
            template.getBody().getValidationMessages()
                    .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, "CCCC", location));
            template.getBody().getValidationMessages()
                    .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, "DDDD", location));
            M2DocUtils.serializeValidatedDocumentTemplate(template, URI.createFileURI(tempFile.getAbsolutePath()));
        }
        assertTrue(new File(tempFile.getAbsolutePath()).exists());

        try (FileInputStream resIs = new FileInputStream(tempFile.getAbsolutePath());
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            final XWPFRun messageARun = M2DocTestUtils.getRunContaining(resDocument, "AAAA");
            final XWPFRun messageBRun = M2DocTestUtils.getRunContaining(resDocument, "BBBB");
            final XWPFRun messageCRun = M2DocTestUtils.getRunContaining(resDocument, "CCCC");
            final XWPFRun messageDRun = M2DocTestUtils.getRunContaining(resDocument, "DDDD");

            assertNotNull(messageARun);
            assertEquals("AAAA", messageARun.text());
            assertEquals("FF0000", messageARun.getColor());

            assertNotNull(messageBRun);
            assertEquals("BBBB", messageBRun.text());
            assertEquals("FF0000", messageBRun.getColor());

            assertNotNull(messageCRun);
            assertEquals("CCCC", messageCRun.text());
            assertEquals("FF0000", messageCRun.getColor());

            assertNotNull(messageDRun);
            assertEquals("DDDD", messageDRun.text());
            assertEquals("FF0000", messageDRun.getColor());

            final int indexA = ((XWPFParagraph) messageARun.getParent()).getRuns().indexOf(messageARun);
            final int indexB = ((XWPFParagraph) messageBRun.getParent()).getRuns().indexOf(messageBRun);
            final int indexC = ((XWPFParagraph) messageCRun.getParent()).getRuns().indexOf(messageCRun);
            final int indexD = ((XWPFParagraph) messageDRun.getParent()).getRuns().indexOf(messageDRun);

            assertTrue(indexA < indexB);
            assertTrue(indexB < indexC);
            assertTrue(indexC < indexD);
        }

        tempFile.delete();
    }

}
