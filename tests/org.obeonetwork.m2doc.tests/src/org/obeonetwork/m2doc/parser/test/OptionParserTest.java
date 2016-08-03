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
package org.obeonetwork.m2doc.parser.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.OptionParser;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.TemplateFactory;

import static org.junit.Assert.assertEquals;
import static org.obeonetwork.m2doc.test.M2DocTestUtils.assertTemplateValidationMessage;

/**
 * Tests the {@link OptionParser} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class OptionParserTest {

    /**
     * Tested components.
     */
    private OptionParser optionParser;
    /**
     * The construct used to log parsing error.
     */
    private Representation construct;
    /**
     * A document containing paragraph.
     */
    private XWPFDocument document;

    /**
     * Initialize needed elements for testing.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Before
    public void setUp() throws InvalidFormatException, IOException {
        optionParser = new OptionParser();
        FileInputStream is = new FileInputStream("templates/allDiagram.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        document = new XWPFDocument(oPackage);
        XWPFParagraph paragraph = document.getParagraphs().get(0);
        construct = TemplateFactory.eINSTANCE.createRepresentation();
        construct.getRuns().add(paragraph.getRuns().get(0));
        construct.setStyleRun(paragraph.getRuns().get(0));

    }

    /**
     * Close document.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     */
    @Before
    public void after() throws InvalidFormatException, IOException {
        document.close();

    }

    /**
     * Test that options are parsed when tag is valid and no extra spaces are present.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserNoSpacesOk() throws InvalidFormatException, IOException, DocumentParserException {
        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:\"value1\" option2:\"value2\"", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(0, construct.getValidationMessages().size());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1", parsedOptions.get("option1"));
        assertEquals("value2", parsedOptions.get("option2"));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that options with an escape '"' character provide the right value without the escaped character.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserEscapedCharacter() throws InvalidFormatException, IOException, DocumentParserException {
        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:\"value1Escaped\\\"\" option2:\"value2\"",
                TokenType.DIAGRAM, 1, 2, construct);
        assertEquals(0, construct.getValidationMessages().size());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1Escaped\"", parsedOptions.get("option1"));
        assertEquals("value2", parsedOptions.get("option2"));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that options are parsed when tag is valid and extra spaces are present.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserSpacesOk() throws InvalidFormatException, IOException, DocumentParserException {
        Map<String, String> parsedOptions = optionParser.parseOptions(
                "    m:diagram provider   :    \"my provider\"     option1:\"value1\" option2  :  \"  value2  \"     ",
                TokenType.DIAGRAM, 1, 2, construct);
        assertEquals(0, construct.getValidationMessages().size());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1", parsedOptions.get("option1"));
        assertEquals("  value2  ", parsedOptions.get("option2"));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that error message is present in the template element when the parsed tag contains a key separated by spaces.
     * The option with illegal space must be present at the end.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserSpacesKo() throws InvalidFormatException, IOException, DocumentParserException {
        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" opt ion1:\"value1\" option2:\"value2\"", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(1, construct.getValidationMessages().size());
        assertTemplateValidationMessage(construct.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "A forbidden space character is present at the index 3 of the key definition 'opt ion1'.",
                construct.getStyleRun());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1", parsedOptions.get("opt ion1"));
        assertEquals("value2", parsedOptions.get("option2"));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that error message is present in the template element when the parsed tag contains for the last option a value without the end
     * delimiter.
     * This message must inform that an option can be incomplete.
     * The tag is {m:diagram provider:"my provider" option1:"value1" option2:"value2}.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserNoEndValueDelimiterKo()
            throws InvalidFormatException, IOException, DocumentParserException {

        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:\"value1\" option2:\"value2", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(1, construct.getValidationMessages().size());
        assertTemplateValidationMessage(construct.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The end delimiter for the option's value has not been reached. The option 'option2' may be invalid.",
                construct.getStyleRun());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1", parsedOptions.get("option1"));
        assertEquals("value2", parsedOptions.get("option2"));
        assertEquals("my provider", parsedOptions.get("provider"));

    }

    /**
     * Test that error message is present in the template element when the parsed tag contains an option in the middle with a value without
     * the end delimiter.
     * In this case, the value will be empty.
     * A parsing error must be present telling that a parsed key is incomplete.
     * The tag is {m:diagram provider:"my provider" option1:"value1 option2:"value2"}.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserNoEndValueDelimiterKo2()
            throws InvalidFormatException, IOException, DocumentParserException {

        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:\"value1 option2:\"value2\"", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(1, construct.getValidationMessages().size());
        assertTemplateValidationMessage(construct.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The start of an option's key has been read but the end of it and the value were missing : 'value2\"'.",
                construct.getStyleRun());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1 option2:", parsedOptions.get("option1"));
        assertEquals("", parsedOptions.get("value2\""));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that error message is present in the template element when the parsed tag contains an option missing the end key delimiter ":"
     * A parsing error must be present telling that a parsed key is incomplete.
     * The tag is {m:diagram provider:"my provider" option1:"value1" option2"value2"}.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserNoEndKeyParsingDelimiter()
            throws InvalidFormatException, IOException, DocumentParserException {

        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:\"value1\" option2\"value\"", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(1, construct.getValidationMessages().size());
        assertTemplateValidationMessage(construct.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The start of an option's key has been read but the end of it and the value were missing : ' option2\"value\"'.",
                construct.getStyleRun());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1", parsedOptions.get("option1"));
        assertEquals("", parsedOptions.get("option2\"value\""));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that error message is present in the template element when the parsed tag contains for the last option a value without the start
     * delimiter "'".
     * This message must inform that forbidden character are present after the key/value separator.
     * The tag is {m:diagram provider:"my provider" option1:"value1" option2:value2"}.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserNoStartValueDelimiterKo()
            throws InvalidFormatException, IOException, DocumentParserException {

        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:\"value1\" option2:value2\"", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(1, construct.getValidationMessages().size());
        assertTemplateValidationMessage(construct.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Forbidden characters are present after the key value separator (\"value2\") of the key : 'option2'. Expected character is \"'\"",
                construct.getStyleRun());
        assertEquals(3, parsedOptions.size());
        assertEquals("value1", parsedOptions.get("option1"));
        assertEquals("", parsedOptions.get("option2"));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

    /**
     * Test that error message is present in the template element when the parsed tag contains for the middle option a value without the
     * start
     * delimiter "'".
     * This message must inform that forbidden character are present after the key/value separator.
     * The tag is {m:diagram provider:"my provider" option1:value1" option2:"value2"}.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testOptionParserNoStartValueDelimiterKo2()
            throws InvalidFormatException, IOException, DocumentParserException {

        Map<String, String> parsedOptions = optionParser.parseOptions(
                "m:diagram provider:\"my provider\" option1:value1\" option2:\"value2\"", TokenType.DIAGRAM, 1, 2,
                construct);
        assertEquals(2, construct.getValidationMessages().size());
        assertTemplateValidationMessage(construct.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Forbidden characters are present after the key value separator (\"value1\") of the key : 'option1'. Expected character is \"'\"",
                construct.getStyleRun());
        assertTemplateValidationMessage(construct.getValidationMessages().get(1), ValidationMessageLevel.ERROR,
                "The start of an option's key has been read but the end of it and the value were missing : 'value2\"'.",
                construct.getStyleRun());
        assertEquals(3, parsedOptions.size());
        assertEquals(" option2:", parsedOptions.get("option1"));
        assertEquals("", parsedOptions.get("value2\""));
        assertEquals("my provider", parsedOptions.get("provider"));
    }

}
