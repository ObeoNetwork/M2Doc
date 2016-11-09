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
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.ast.Call;
import org.eclipse.acceleo.query.ast.Conditional;
import org.eclipse.acceleo.query.ast.StringLiteral;
import org.eclipse.acceleo.query.ast.impl.CallImpl;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.EMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.BodyTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.provider.test.StubDiagramProvider;
import org.obeonetwork.m2doc.sirius.providers.SiriusDiagramByDiagramDescriptionNameProvider;
import org.obeonetwork.m2doc.sirius.providers.SiriusDiagramByTitleProvider;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.POSITION;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserDoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.obeonetwork.m2doc.test.M2DocTestUtils.assertTemplateValidationMessage;

/**
 * Tests the {@link BodyTemplateParser} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
@SuppressWarnings("restriction")
public class DocumentParserTest {
    /**
     * BOOKMARK1 text.
     */
    private static final String BOOKMARK1 = "bookmark1";
    /**
     * Number constant.
     */
    private static final int SEVEN = 7;
    /**
     * Number constant.
     */
    private static final int THIRTY_FOUR = 34;
    /**
     * Number constant.
     */
    private static final int TWENTY_SEVEN = 27;
    /**
     * Number constant.
     */
    private static final int TWENTY_FIVE = 25;
    /**
     * Number constant.
     */
    private static final int TWENTY_FOUR = 24;
    /**
     * Number constant.
     */
    private static final int TWENTY_TWO = 22;
    /**
     * Number constant.
     */
    private static final int TWENTY = 20;
    /**
     * Number constant.
     */
    private static final int TWO_HUNDRED = 200;
    /**
     * AQL query environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);
    /**
     * {@link ProviderRegistry} instance used during testing.
     */
    private ProviderRegistry registry = ProviderRegistry.INSTANCE;

    /**
     * Initialize provider registry.
     */
    @Before
    public void setUp() {
        registry.clear();
        registry.registerProvider(new StubDiagramProvider());
        registry.registerProvider(new NoDiagramProvider());
    }

    /**
     * Cleaning.
     */
    @After
    public void after() {
        registry.clear();
    }

    /**
     * Test Template Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testTemplateParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testTemplate.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertEquals(2, ((StaticFragment) template.getSubConstructs().get(0)).getRuns().size());
    }

    /**
     * Test var Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testVarParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Query);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Query varRef = (Query) template.getSubConstructs().get(1);
        assertNotNull(varRef.getQuery());
    }

    /**
     * Test query Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testQueryParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Query);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Query query = (Query) template.getSubConstructs().get(1);
        assertNotNull(query.getQuery());
    }

    /**
     * Test Repetition Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepetitionParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testGDFOR.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Repetition);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Repetition repetition = (Repetition) template.getSubConstructs().get(1);
        assertNotNull(repetition.getQuery());
        assertEquals("v", repetition.getIterationVar());
        assertTrue(repetition.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(repetition.getSubConstructs().get(1) instanceof Query);
        assertTrue(repetition.getSubConstructs().get(0) instanceof StaticFragment);
    }

    /**
     * Test simple conditional Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testSimpleConditionnalParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Conditionnal);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(1);
        assertNotNull(conditionnal.getQuery());
        assertTrue(conditionnal.getSubConstructs().get(0) instanceof StaticFragment);
        assertNull(conditionnal.getElse());
        assertNull(conditionnal.getAlternative());
    }

    /**
     * Test simple conditional with else Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testConditionnalWithElseParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Conditionnal);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(1);
        assertNotNull(conditionnal.getQuery());
        assertTrue(conditionnal.getSubConstructs().get(0) instanceof StaticFragment);
        assertNull(conditionnal.getAlternative());
        assertEquals(1, conditionnal.getElse().getSubConstructs().size());
        assertTrue(conditionnal.getElse().getSubConstructs().get(0) instanceof StaticFragment);
    }

    /**
     * Test simple conditional with else if Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testConditionnalWithElseIfParsing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Conditionnal);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(1);
        assertNotNull(conditionnal.getQuery());
        assertTrue(conditionnal.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(conditionnal.getAlternative() instanceof Conditionnal);
        assertNull(conditionnal.getElse());
        assertTrue(conditionnal.getAlternative().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(conditionnal.getAlternative().getQuery());
        assertNull(conditionnal.getAlternative().getAlternative());
        assertNull(conditionnal.getAlternative().getElse());
    }

    /**
     * Test simple conditional with 2 else if Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testConditionnalWith2ElseIfParsing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal4.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Conditionnal);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(1);
        assertNotNull(conditionnal.getQuery());
        assertTrue(conditionnal.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(conditionnal.getAlternative() instanceof Conditionnal);
        assertNull(conditionnal.getElse());
        assertTrue(conditionnal.getAlternative().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(conditionnal.getAlternative().getQuery());
        assertNotNull(conditionnal.getAlternative().getAlternative());
        assertNull(conditionnal.getAlternative().getElse());
        assertTrue(conditionnal.getAlternative().getAlternative().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(conditionnal.getAlternative().getAlternative().getQuery());
        assertNull(conditionnal.getAlternative().getAlternative().getAlternative());
        assertNull(conditionnal.getAlternative().getAlternative().getElse());
    }

    /**
     * Test simple conditional with 2 else if and an else Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testConditionnalWith2ElseIfAndElseParsing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Conditionnal);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Conditionnal conditionnal = (Conditionnal) template.getSubConstructs().get(1);
        assertNotNull(conditionnal.getQuery());
        assertTrue(conditionnal.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(conditionnal.getAlternative() instanceof Conditionnal);
        assertNull(conditionnal.getElse());
        assertTrue(conditionnal.getAlternative().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(conditionnal.getAlternative().getQuery());
        assertNotNull(conditionnal.getAlternative().getAlternative());
        assertNull(conditionnal.getAlternative().getElse());
        assertTrue(conditionnal.getAlternative().getAlternative().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(conditionnal.getAlternative().getAlternative().getQuery());
        assertNull(conditionnal.getAlternative().getAlternative().getAlternative());
        assertNotNull(conditionnal.getAlternative().getAlternative().getElse());
        assertTrue(conditionnal.getAlternative().getAlternative().getElse() instanceof Default);
        assertTrue(conditionnal.getAlternative().getAlternative().getElse().getSubConstructs()
                .get(0) instanceof StaticFragment);
    }

    /**
     * Test table Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void tableParsingTest() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testTable.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(2, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof Table);
        Table table = (Table) template.getSubConstructs().get(0);
        assertEquals(2, table.getRows().size());
        // First row testing.
        Row row = table.getRows().get(0);
        assertEquals(2, row.getCells().size());
        assertTrue(row.getCells().get(0).getTemplate().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(row.getCells().get(0).getTableCell());
        assertTrue(row.getCells().get(1).getTemplate().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(row.getCells().get(1).getTableCell());
        // Second row testing.
        row = table.getRows().get(1);
        assertEquals(2, row.getCells().size());
        assertTrue(row.getCells().get(0).getTemplate().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(row.getCells().get(0).getTableCell());
        assertTrue(row.getCells().get(1).getTemplate().getSubConstructs().get(0) instanceof Query);
        assertNotNull(row.getCells().get(1).getTableCell());
        assertNotNull(((Query) row.getCells().get(1).getTemplate().getSubConstructs().get(0)).getQuery());

    }

    /**
     * Test for with table Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void forWithtableParsingTest() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testGDFORWithTable.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(6, template.getSubConstructs().size());
        Repetition rep = (Repetition) template.getSubConstructs().get(4);
        Table table = (Table) rep.getSubConstructs().get(0);
        assertEquals(1, table.getRows().size());
        // First row testing.
        Row row = table.getRows().get(0);
        assertEquals(2, row.getCells().size());
        assertTrue(row.getCells().get(0).getTemplate().getSubConstructs().get(0) instanceof StaticFragment);
        assertNotNull(row.getCells().get(0).getTableCell());
        assertTrue(row.getCells().get(1).getTemplate().getSubConstructs().get(0) instanceof Query);
        assertNotNull(((Query) row.getCells().get(1).getTemplate().getSubConstructs().get(0)).getQuery());
    }

    /**
     * Test image Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void imageParsingTest() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testImageTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        // CHECKSTYLE:OFF
        assertEquals("images/dh1.gif", im.getFileName());
        // CHECKSTYLE:ON
        assertEquals(100, im.getHeight());
        assertEquals(100, im.getWidth());
        // CHECKSTYLE:OFF
        assertEquals("plan de forme du dingy herbulot", im.getLegend());
        // CHECKSTYLE:ON
        assertEquals(POSITION.BELOW, im.getLegendPOS());
    }

    /**
     * Tests that the escaping character {@link BodyTemplateParser#M2DOC_ESCAPE_CHARACTER} does escape the value delimiter character
     * {@link BodyTemplateParser#VALUE_DELIMITER_CHARACTER}.
     * The legend option is legend:"\"plan de forme\" du dingy herbulot\"" .
     * The result value option should be <"plan de forme" du dingy herbulot">
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionEscaping() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionEscaping.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("images/dh1.gif", im.getFileName());
        assertEquals(100, im.getHeight());
        assertEquals(100, im.getWidth());
        assertEquals("\"plan de forme\" du dingy herbulot\"", im.getLegend());
        assertEquals(POSITION.BELOW, im.getLegendPOS());
    }

    /**
     * Tests that the escaping character {@link BodyTemplateParser#M2DOC_ESCAPE_CHARACTER} is not kept when applied to a character that does
     * not
     * need to be escaped.
     * The legend option is legend:"plan de for\me du dingy herbulot" .
     * The result value option should be <"plan de forme du dingy herbulot">
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionEscaping2() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionEscaping2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("images/dh1.gif", im.getFileName());
        assertEquals(100, im.getHeight());
        assertEquals(100, im.getWidth());
        assertEquals("plan de forme du dingy herbulot", im.getLegend());
        assertEquals(POSITION.BELOW, im.getLegendPOS());
    }

    /**
     * Tests that the escaping character {@link BodyTemplateParser#M2DOC_ESCAPE_CHARACTER} when escaped is producing the escape character.
     * The option is <legend:"\\plan de for\\me du di\\\"ngy herbulot\\" legendPos:"below">
     * The result should be <\plan de for\me du di\"ngy herbulot\>
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionEscaping3() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionEscaping3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("images/dh1.gif", im.getFileName());
        assertEquals(100, im.getHeight());
        assertEquals(100, im.getWidth());
        assertEquals("\\plan de for\\me du di\\\"ngy herbulot\\", im.getLegend());
        assertEquals(POSITION.BELOW, im.getLegendPOS());
    }

    /**
     * Tests that a parsing error is provided when the delimiter character is not escaped in an option's value.
     * The tag is <m:image file:"images/dh1.gif" height:"100" width:"100" legend:"plan de forme" du dingy herbulot" legendPos:"below" >.
     * The result must have an error message indicated that an invalid character is present at index afet the u of "du".
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionInvalid() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionNoEscaping.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertTemplateValidationMessage(im.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "A forbidden space character is present at the index 2 of the key definition 'du dingy herbulot\" legendPos'.",
                im.getRuns().get(TWENTY_FIVE));
    }

    /**
     * Tests that a parsing error is present when the delimiter character is not escaped in an option's value.
     * The tag is <m:image file:"images/dh1.gif" height:"100" width:"100" legen d:"plan de forme du dingy herbulot" legendPos:"below" >.
     * The result must have an error message indicated that an unknow character is present at the key description "leg end".
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionInvalid2() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionInvalidKey.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertTemplateValidationMessage(im.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "A forbidden space character is present at the index 5 of the key definition 'legen d'.",
                im.getRuns().get(TWENTY_SEVEN));
    }

    /**
     * Tests that a parsing error is provided when the delimiter character is not escaped in an option's value.
     * The tag is {m:image file:"images/dh1.gif" height:"100" width:"100" legend:"plan de forme du dingy herbulot" legendPos}.
     * The result must have an error message indicated that an unknown character is present at the key description "leg end".
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionInvalid3() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionInvalidNotComplete.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertTemplateValidationMessage(im.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The start of an option's key has been read but the end of it and the value were missing : ' legendPos'.",
                im.getRuns().get(TWENTY_TWO));
    }

    /**
     * Tests that insignificant spaces between key/value separator {@link BodyTemplateParser#KEY_VALUE_SEPARATOR} and value/key are handled
     * correctly.
     * The tag is {m:image file:"images/dh1.gif" height:"100" width:"100" legend : "plan de forme du dingy herbulot" legendPos:"below" } .
     * All options should be handled correctly.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testStringOptionAuthorizedEmptySpace()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testStringOptionEmptySpaces.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("images/dh1.gif", im.getFileName());
        assertEquals(100, im.getHeight());
        assertEquals(100, im.getWidth());
        assertEquals("plan de forme du dingy herbulot", im.getLegend());
        assertEquals(POSITION.BELOW, im.getLegendPOS());
    }

    /**
     * Test image with out file directive Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void imageParsingTestWithoutFiledirective()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testImageTag2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertTemplateValidationMessage(im.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Invalid image directive : no file name provided.", im.getRuns().get(TWENTY));
    }

    /**
     * Test image with bad option Parsing.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void imageParsingTestWithBadOptionName()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testImageTag3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertTemplateValidationMessage(im.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Invalid image option (leg): unknown option name.", im.getRuns().get(TWENTY_FOUR));
    }

    /**
     * Tests that parsing a valid diagram tag with a {@link StubDiagramProvider} provided the right template element.
     * The tested tag is {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider" width:"200" height:"200"
     * resultKind:"oneImage" legend:"plan de forme du dingy herbulot" legendPos:"below"}.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithProviderProducingOneImageOk()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidOneImage.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof StubDiagramProvider);
        // CHECKSTYLE:OFF
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        // CHECKSTYLE:ON
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, representation.getOptionValueMap().size());
        assertEquals("oneImage", optionValueMap.get("resultKind"));
    }

    /**
     * Tests that parsing a valid diagram tag with a {@link StubDiagramProvider} provided the right template element when some
     * spaces are around the provider.
     * The tested tag is {m:diagram diagramProvider:' org.obeonetwork.m2doc.provider.test.StubDiagramProvider ' width:'200' height:'200'
     * title:'RF Schema' legend:'plan de forme du dingy herbulot' legendPos:'below'}.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithProviderSurroundedBySpaces()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidSpacesAroundProvider.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof StubDiagramProvider);
        // CHECKSTYLE:OFF
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        // CHECKSTYLE:ON
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, representation.getOptionValueMap().size());
        assertEquals("oneImage", optionValueMap.get("resultKind"));
    }

    /**
     * Tests that parsing a valid diagram tag with an AQL option containing the escaped character " will be handled correctly.
     * The tested root object option is <aqlExpression:"if ('test\"'.size()=5)) then db endif">
     * If handled correctly, the if condition will contains the double quote character.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testAQLParsingOptionOk() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidEscaping.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof StubDiagramProvider);
        // CHECKSTYLE:OFF
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        // CHECKSTYLE:ON
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, representation.getOptionValueMap().size());
        // CHECKSTYLE:OFF
        assertNotNull(optionValueMap.get("aqlExpression"));
        // CHECKSTYLE:ON
        AstResult result = (AstResult) optionValueMap.get("aqlExpression");
        org.eclipse.acceleo.query.ast.Conditional conditional = (Conditional) result.getAst();

        Call call = (CallImpl) conditional.getPredicate();
        Call subCall = (Call) call.getArguments().get(0);
        StringLiteral stringLiteral = (StringLiteral) subCall.getArguments().get(0);
        assertEquals("test\"", stringLiteral.getValue());
    }

    /**
     * Tests parsing of a valid diagram tag with an AQL option containing a string that contains a simple quote that must be escaped for
     * AQL. With the M2Doc escape, the simple quote must be precede by two backslashes.
     * The tested root object option is <aqlExpression:"if ('test\\''.size()=5)) then db endif" >
     * If handled correctly, the if condition will contains one backslash character followed by the simple quote.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testAQLParsingOptionOk2() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidEscaping2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof StubDiagramProvider);
        // CHECKSTYLE:OFF
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        // CHECKSTYLE:ON
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, representation.getOptionValueMap().size());
        assertNotNull(optionValueMap.get("aqlExpression"));
        AstResult result = (AstResult) optionValueMap.get("aqlExpression");
        org.eclipse.acceleo.query.ast.Conditional conditional = (Conditional) result.getAst();

        Call call = (CallImpl) conditional.getPredicate();
        Call subCall = (Call) call.getArguments().get(0);
        StringLiteral stringLiteral = (StringLiteral) subCall.getArguments().get(0);
        assertEquals("test\'", stringLiteral.getValue());
    }

    /**
     * Tests that parsing a diagram tag with no provider provided the right template element.
     * The tested tag is {m:diagram diagramProvider:'wrong' width:'200' height:'200'
     * title:'RF Schema'}.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithNoProviderKo()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidNoProvider.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertNull(representation.getProvider());
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        assertNull(representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(0, optionValueMap.size());
        assertEquals(1, representation.getValidationMessages().size());
        assertTemplateValidationMessage(representation.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "The image tag is referencing an unknown diagram provider : 'wrong'", representation.getRuns().get(1));
    }

    /**
     * General provider for testing.
     * 
     * @author pguilet<pierre.guilet@obeo.fr>
     */
    private class NoDiagramProvider implements IProvider {

        /*
         * (non-Javadoc)
         * 
         * @see org.obeonetwork.m2doc.provider.IProvider#getOptionTypes()
         */
        @Override
        public Map<String, OptionType> getOptionTypes() {
            return null;
        }

        @Override
        public List<ProviderValidationMessage> validate(Map<String, Object> options) {
            return Collections.emptyList();
        }

    }

    /**
     * Tests that parsing a diagram tag with a provider that does not handle diagram tag produces an error.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.parser.test.DocumentParserTest.NoDiagramProvider' width:'200'
     * height:'200'
     * title:'RF Schema'}.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithNoDiagramProvider()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidWrongProvider.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertEquals(NoDiagramProvider.class, representation.getProvider().getClass());
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        assertNull(representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, optionValueMap.size());
        assertEquals(0, representation.getValidationMessages().size());
    }

    /**
     * Tests parsing of a diagram tag with the {@link SiriusDiagramByTitleProvider}. "title" option is missing.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider' width:'200' height:'200'
     * title:'RF Schema'}.
     * Provider options are handled by template processor so no error should be added.
     * 
     * @throws IOException
     *             IOException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithValidProviderButNoOptions()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramNoProviderOptions.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof StubDiagramProvider);
        // CHECKSTYLE:OFF
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        // CHECKSTYLE:ON
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(0, optionValueMap.size());
        assertEquals(0, representation.getValidationMessages().size());
    }

    /**
     * Tests that parsing a diagram tag with a {@link SiriusDiagramByDiagramDescriptionNameProvider} and an invalid AQL expression
     * provides the right template element.
     * The tested tag is {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider " width:"200" height:"200"
     * aqlExpression:"wrong.->" legend:"plan de forme du dingy herbulot" legendPos:"below"}.
     * 
     * @throws IOException
     *             IOException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testRepresentationParsingInvalidAqlExpressionOk()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidAqlExpression.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof StubDiagramProvider);
        // CHECKSTYLE:OFF
        assertEquals(TWO_HUNDRED, representation.getHeight());
        assertEquals(TWO_HUNDRED, representation.getWidth());
        // CHECKSTYLE:ON
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        assertEquals(1, representation.getOptionValueMap().size());
        assertEquals(2, representation.getValidationMessages().size());
        assertTemplateValidationMessage(representation.getValidationMessages().get(0), ValidationMessageLevel.ERROR,
                "Expression \"wrong.->\" is invalid: missing feature access or service call",
                representation.getRuns().get(THIRTY_FOUR));
        assertTemplateValidationMessage(representation.getValidationMessages().get(1), ValidationMessageLevel.ERROR,
                "Expression \"wrong.->\" is invalid: missing collection service call",
                representation.getRuns().get(THIRTY_FOUR));
    }

    /**
     * Bookmark And Link tag.
     * 
     * @throws IOException
     *             IOException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void bookmarkAndLink() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testBookmarkNominal.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(SEVEN, template.getSubConstructs().size());

        assertTrue(template.getSubConstructs().get(1) instanceof Link);
        final Link linkBefore = (Link) template.getSubConstructs().get(1);
        assertTrue(linkBefore.getName().getAst() instanceof StringLiteral);
        assertEquals(BOOKMARK1, ((StringLiteral) linkBefore.getName().getAst()).getValue());
        assertTrue(linkBefore.getText().getAst() instanceof StringLiteral);
        assertEquals("a reference to bookmark1", ((StringLiteral) linkBefore.getText().getAst()).getValue());

        assertTrue(template.getSubConstructs().get(3) instanceof Bookmark);
        final Bookmark bookmark = (Bookmark) template.getSubConstructs().get(3);
        assertTrue(bookmark.getName().getAst() instanceof StringLiteral);
        assertEquals(BOOKMARK1, ((StringLiteral) bookmark.getName().getAst()).getValue());
        assertEquals(1, bookmark.getSubConstructs().size());
        assertTrue(bookmark.getSubConstructs().get(0) instanceof StaticFragment);
        final StaticFragment fragment = (StaticFragment) bookmark.getSubConstructs().get(0);
        assertEquals(1, fragment.getRuns().size());
        assertEquals("bookmarked content", fragment.getRuns().get(0).text());

        assertTrue(template.getSubConstructs().get(5) instanceof Link);
        final Link linkAfter = (Link) template.getSubConstructs().get(5);
        assertTrue(linkAfter.getName().getAst() instanceof StringLiteral);
        assertEquals(BOOKMARK1, ((StringLiteral) linkAfter.getName().getAst()).getValue());
        assertTrue(linkAfter.getText().getAst() instanceof StringLiteral);
        assertEquals("a reference to bookmark1", ((StringLiteral) linkAfter.getText().getAst()).getValue());
    }

    /**
     * Test parsing userDoc tag with default simple string.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocSimpleText() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testUserDoc1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof UserDoc);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getSubConstructs().get(1);
        assertNotNull(userDoc.getId());
        assertTrue(userDoc.getId() instanceof AstResult);
        assertEquals(1, userDoc.getSubConstructs().size());
        assertTrue(userDoc.getSubConstructs().get(0) instanceof StaticFragment);
        // AQL parsing test
        assertTrue(userDoc.getId().getAst() instanceof StringLiteral);
        assertEquals("value1", ((StringLiteral) (userDoc.getId().getAst())).getValue());

    }

    /**
     * Test parsing userDoc tag with default one line string.
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
        FileInputStream is = new FileInputStream("templates/testUserDoc2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof UserDoc);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getSubConstructs().get(1);
        assertNotNull(userDoc.getId());
        assertTrue(userDoc.getId() instanceof AstResult);
        assertEquals(1, userDoc.getSubConstructs().size());
        assertTrue(userDoc.getSubConstructs().get(0) instanceof StaticFragment);
        // AQL parsing test
        assertTrue(userDoc.getId().getAst() instanceof StringLiteral);
        assertEquals("value1", ((StringLiteral) (userDoc.getId().getAst())).getValue());
    }

    /**
     * Test parsing userDoc tag with condition tag in userDoc content.
     * 
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     */
    @Test
    public void testUserDocWithConditionInContent()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testUserDoc3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyTemplateParser parser = new BodyTemplateParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getBody());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof UserDoc);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        UserDoc userDoc = (UserDoc) template.getSubConstructs().get(1);
        assertNotNull(userDoc.getId());
        assertTrue(userDoc.getId() instanceof AstResult);
        assertEquals(3, userDoc.getSubConstructs().size());
        assertTrue(userDoc.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(userDoc.getSubConstructs().get(1) instanceof Conditionnal);
        assertTrue(userDoc.getSubConstructs().get(2) instanceof StaticFragment);
        // Check ValidationMessage
        assertEquals(0, userDoc.getValidationMessages().size());
    }
}
