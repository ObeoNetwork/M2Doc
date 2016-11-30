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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.TemplateValidationGenerator;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.Template;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;

import static org.junit.Assert.assertEquals;

public class TemplateParsingValidatorTest {
    /**
     * Query environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    /**
     * Creates a WORD document at the given path and returns the memory representation.
     * 
     * @param theDocument
     * @param inputDocumentFilePath
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    private void createDestinationDocument(XWPFDocument theDocument, String inputDocumentFilePath)
            throws IOException, InvalidFormatException {
        FileOutputStream os = new FileOutputStream(inputDocumentFilePath);
        theDocument.write(os);
    }

    /**
     * Tests that parsing errors from AQL template tag (conditional here) are placed next to the start tag.
     * The tested tag is <{m:wrong->.}ajout de value1{m:endif}>
     * The expected tag is : <{m:wrong->.} Expression wrong->. is invalid ajout de value1{m:endif}>
     * After the run with the end '}' char, the following runs must be present :
     * A run must contains blanks char.
     * The next one must contains the error message.
     * The next one must contains blank char and the next one the static content of the conditional.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testErrorInStartTag()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        FileInputStream is = new FileInputStream("templates/testParsingErrorStartTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        TemplateValidationGenerator validator = new TemplateValidationGenerator();
        validator.doSwitch(template);
        createDestinationDocument(document, "results/generated/testParsingErrorStartTag.docx");

        // scan the destination document
        assertEquals(2, document.getParagraphs().size());
        assertEquals(16, document.getParagraphs().get(0).getRuns().size());
        assertEquals(1, document.getParagraphs().get(1).getRuns().size());
        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(5).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(6).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(6).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(6).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(7).getCTR().getRPr().getHighlight().getVal());
        assertEquals("Expression \"wrong->.\" is invalid: missing collection service call",
                document.getParagraphs().get(0).getRuns().get(7).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(7).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(7).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(7).getCTR().getRPr().getHighlight().getVal());
        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(8).getText(0));
        assertEquals("ajout de value1", document.getParagraphs().get(0).getRuns().get(9).getText(0));
        assertEquals("Unexpected tag m:endif at this location",
                document.getParagraphs().get(0).getRuns().get(13).getText(0));
    }

    /**
     * Tests that parsing errors from AQL template tag (conditional here) are placed next to the start tag.
     * The tested tag is <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}Some text>
     * The expected tag is : <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}<---The image
     * tag is referencing an unknown diagram provider : 'noExistingProvider' Some text>
     * After the run with the end '}' char, the following runs must be present :
     * A run must contains blanks char.
     * The next one must contains the error message.
     * The next one is a blank separator.
     * The next one must contains the other error message.
     * The next one must contains blank char and the next one the static content after the tag in the original template.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testErrorInSimpleTag()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        FileInputStream is = new FileInputStream("templates/testParsingErrorSimpleTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        TemplateValidationGenerator validator = new TemplateValidationGenerator();
        validator.doSwitch(template);
        createDestinationDocument(document, "results/generated/testParsingErrorSimpleTag.docx");
        // scan the destination document
        assertEquals(2, document.getParagraphs().size());
        assertEquals(11, document.getParagraphs().get(0).getRuns().size());
        assertEquals(1, document.getParagraphs().get(1).getRuns().size());
        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(2).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(3).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(3).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(3).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(5).getCTR().getRPr().getHighlight().getVal());
        assertEquals("The image tag is referencing an unknown diagram provider : 'noExistingProvider'",
                document.getParagraphs().get(0).getRuns().get(5).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(5).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(5).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(5).getCTR().getRPr().getHighlight().getVal());
        assertEquals("Some text", document.getParagraphs().get(0).getRuns().get(10).getText(0));
    }

    /**
     * Tests that parsing errors from AQL template tag (conditional here) are placed next to the start tag.
     * The tested tag is <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}Some text>
     * The expected tag is : <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}<---The image
     * tag is referencing an unknown diagram provider : 'noExistingProvider' <---The start of an option's key has been read but the end of
     * it and the value were missing : ' title="representationTitle"'. Some text>
     * After the run with the end '}' char, the following runs must be present :
     * A run must contains blanks char.
     * The next one must contains the error message.
     * The next one is a blank separator.
     * The next one must contains the other error message.
     * The next one must contains blank char and the next one the static content after the tag in the original template.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testMultiErrorInSimpleTag()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        FileInputStream is = new FileInputStream("templates/testMultiParsingErrorSimpleTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        TemplateValidationGenerator validator = new TemplateValidationGenerator();
        validator.doSwitch(template);
        createDestinationDocument(document, "results/generated/testMultiParsingErrorSimpleTag.docx");
        // scan the destination document
        assertEquals(1, document.getParagraphs().size());
        assertEquals(14, document.getParagraphs().get(0).getRuns().size());
        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(2).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(3).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(3).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(3).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(3).getCTR().getRPr().getHighlight().getVal());
        assertEquals("The image tag is referencing an unknown diagram provider : 'noExistingProvider'",
                document.getParagraphs().get(0).getRuns().get(6).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(6).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(6).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(6).getCTR().getRPr().getHighlight().getVal());

        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(10).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(11).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(11).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(11).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(11).getCTR().getRPr().getHighlight().getVal());
        assertEquals(
                "The start of an option's key has been read but the end of it and the value were missing : ' title=\"representationTitle\"'.",
                document.getParagraphs().get(0).getRuns().get(12).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(12).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(12).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(11).getCTR().getRPr().getHighlight().getVal());

        assertEquals("Some text", document.getParagraphs().get(0).getRuns().get(13).getText(0));
    }

    /**
     * Tests that parsing errors from AQL template tag (conditional here) are placed next to the start tag.
     * The tested tag is <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}Some text>
     * The expected tag is : <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}<---The image
     * tag is referencing an unknown diagram provider : 'noExistingProvider' Some text>
     * After the run with the end '}' char, the following runs must be present :
     * A run must contains blanks char.
     * The next one must contains the error message.
     * The next one is a blank separator.
     * The next one must contains the other error message.
     * The next one must contains blank char and the next one the static content after the tag in the original template.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testErrorInEndTag()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        FileInputStream is = new FileInputStream("templates/testParsingErrorEndTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        TemplateValidationGenerator validator = new TemplateValidationGenerator();
        validator.doSwitch(template);
        createDestinationDocument(document, "results/generated/testParsingErrorEndTag.docx");
        // scan the destination document
        assertEquals(1, document.getParagraphs().size());
        assertEquals(24, document.getParagraphs().get(0).getRuns().size());
        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(9).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(10).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(10).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(10).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(10).getCTR().getRPr().getHighlight().getVal());
        assertEquals("Unexpected tag m:endlet at this location",
                document.getParagraphs().get(0).getRuns().get(11).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(11).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(11).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(11).getCTR().getRPr().getHighlight().getVal());

        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(10).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(10).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(10).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(10).getCTR().getRPr().getHighlight().getVal());
        assertEquals("Unexpected tag m:endlet at this location",
                document.getParagraphs().get(0).getRuns().get(11).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(11).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(11).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(11).getCTR().getRPr().getHighlight().getVal());

        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(15).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(16).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(16).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(16).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(16).getCTR().getRPr().getHighlight().getVal());
        assertEquals("gd:elseif, gd:else or gd:endif expected here.",
                document.getParagraphs().get(0).getRuns().get(17).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(17).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(17).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(17).getCTR().getRPr().getHighlight().getVal());

        assertEquals("Some", document.getParagraphs().get(0).getRuns().get(18).getText(0));
        assertEquals(" t", document.getParagraphs().get(0).getRuns().get(19).getText(0));
        assertEquals("ext", document.getParagraphs().get(0).getRuns().get(20).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(22).getText(0));
        assertEquals("Unexpected tag EOF at this location",
                document.getParagraphs().get(0).getRuns().get(23).getText(0));
    }

    /**
     * Tests that parsing errors from AQL template tag (conditional here) are placed next to the start tag when no following text exists.
     * The tested tag is <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}>
     * The expected tag is : <{m:diagram provider:"noExistingProvider" width:"500" height:"500" title="representationTitle"}<---The image
     * tag is referencing an unknown diagram provider : 'noExistingProvider' >
     * After the run with the end '}' char, the following runs must be present :
     * A run must contains blanks char.
     * The next one must contains the error message.
     * The next one is a blank separator.
     * The next one must contains the other error message.
     * The next one must contains blank char and the next one the static content after the tag in the original template.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testErrorInSimpleTagWithoutFollowing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        FileInputStream is = new FileInputStream("templates/testParsingErrorSimpleTagWithoutFollowingText.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        TemplateValidationGenerator validator = new TemplateValidationGenerator();
        validator.doSwitch(template);
        createDestinationDocument(document, "results/generated/testParsingErrorSimpleTagWithoutFollowingText.docx");
        // scan the destination document
        assertEquals(1, document.getParagraphs().size());
        assertEquals(11, document.getParagraphs().get(0).getRuns().size());
        assertEquals("    ", document.getParagraphs().get(0).getRuns().get(2).getText(0));
        assertEquals("<---", document.getParagraphs().get(0).getRuns().get(3).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(3).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(3).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(5).getCTR().getRPr().getHighlight().getVal());
        assertEquals("The image tag is referencing an unknown diagram provider : 'noExistingProvider'",
                document.getParagraphs().get(0).getRuns().get(5).getText(0));
        assertEquals("FF0000", document.getParagraphs().get(0).getRuns().get(5).getColor());
        assertEquals(16, document.getParagraphs().get(0).getRuns().get(5).getFontSize());
        assertEquals(STHighlightColor.LIGHT_GRAY,
                document.getParagraphs().get(0).getRuns().get(5).getCTR().getRPr().getHighlight().getVal());
    }

    @Test
    public void testErrorInUserDocAqlParsing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        String templatePath = "templates/testParsingErrorSimpleTagWithoutFollowingText.docx";
        XWPFDocument document = loadDoc(templatePath);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        TemplateValidationGenerator validator = new TemplateValidationGenerator();
        validator.doSwitch(template);
        createDestinationDocument(document, "results/generated/testParsingErrorSimpleTagWithoutFollowingText.docx");

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
