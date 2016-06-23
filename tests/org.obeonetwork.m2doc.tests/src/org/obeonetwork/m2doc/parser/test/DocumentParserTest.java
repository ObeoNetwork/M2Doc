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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.EMap;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.BodyParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.POSITION;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class DocumentParserTest {
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    @Test
    public void testTemplateParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testTemplate.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
        assertEquals(1, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertEquals(2, ((StaticFragment) template.getSubConstructs().get(0)).getRuns().size());
    }

    @Test
    public void testVarParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Query);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Query varRef = (Query) template.getSubConstructs().get(1);
        assertNotNull(varRef.getQuery());
    }

    @Test
    public void testQueryParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
        assertEquals(3, template.getSubConstructs().size());
        assertTrue(template.getSubConstructs().get(0) instanceof StaticFragment);
        assertTrue(template.getSubConstructs().get(1) instanceof Query);
        assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
        Query query = (Query) template.getSubConstructs().get(1);
        assertNotNull(query.getQuery());
    }

    @Test
    public void testRepetitionParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testGDFOR.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
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

    @Test
    public void testSimpleConditionnalParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
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

    @Test
    public void testConditionnalWithElseParsing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
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

    @Test
    public void testConditionnalWithElseIfParsing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
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

    @Test
    public void testConditionnalWith2ElseIfParsing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal4.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
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

    @Test
    public void testConditionnalWith2ElseIfAndElseParsing()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(document, template.getDocument());
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

    @Test
    public void tableParsingTest() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testTable.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
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

    @Test
    public void forWithtableParsingTest() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testGDFORWithTable.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
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

    @Test
    public void imageParsingTest() throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testImageTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("images/dh1.gif", im.getFileName());
        assertEquals(100, im.getHeight());
        assertEquals(100, im.getWidth());
        assertEquals("plan de forme du dingy herbulot", im.getLegend());
        assertEquals(POSITION.BELOW, im.getLegendPOS());
    }

    @Test
    public void imageParsingTestWithoutFiledirective()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testImageTag2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("Invalid image directive : no file name provided.", im.getParsingErrors().get(0).getMessage());
    }

    @Test
    public void imageParsingTestWithBadOptionName()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testImageTag3.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Image im = (Image) template.getSubConstructs().get(0);
        assertEquals("Invalid image option (leg): unknown option name.", im.getParsingErrors().get(0).getMessage());
    }

    /**
     * Tests that parsing a valid diagram tag with a {@link SiriusDiagramByTitleProvider} provided the right template element.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider' width:'200' height:'200'
     * title:'RF Schema' legend:'plan de forme du dingy herbulot' legendPos:'below'}.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithTitleProviderOk()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValid.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof SiriusDiagramByTitleProvider);
        assertEquals(200, representation.getHeight());
        assertEquals(200, representation.getWidth());
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, representation.getOptionValueMap().size());
        assertEquals("RF Schema", optionValueMap.get("title"));
    }

    /**
     * Tests that parsing a valid diagram tag with a {@link SiriusDiagramByTitleProvider} provided the right template element when some
     * spaces are around the provider.
     * The tested tag is {m:diagram diagramProvider:' org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider ' width:'200' height:'200'
     * title:'RF Schema' legend:'plan de forme du dingy herbulot' legendPos:'below'}.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithProviderSUrroundedBySpaces()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidSpacesAroundProvider.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof SiriusDiagramByTitleProvider);
        assertEquals(200, representation.getHeight());
        assertEquals(200, representation.getWidth());
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(1, representation.getOptionValueMap().size());
        assertEquals("RF Schema", optionValueMap.get("title"));
    }

    /**
     * Tests that parsing a valid diagram tag with a {@link SiriusDiagramByRepresentationAndEObjectProvider} provided the right template
     * element.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider'
     * width:'200' height:'200' rootObject:'db.schemas->first()' diagramDescriptionName:'Schema Diagram' legend:'plan de forme du dingy
     * herbulot' legendPos:'below'}.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws DocumentParserException²
     */
    @Test
    public void testRepresentationParsingWithRootObjectProviderOk()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidRootObject.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof SiriusDiagramByRepresentationAndEObjectProvider);
        assertEquals(200, representation.getHeight());
        assertEquals(200, representation.getWidth());
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(2, representation.getOptionValueMap().size());
        assertNotNull(optionValueMap.get("rootObject"));
        assertEquals("Schema Diagram", optionValueMap.get("diagramDescriptionName"));
    }

    /**
     * Tests that parsing a diagram tag with no provider provided the right template element.
     * The tested tag is {m:diagram diagramProvider:'wrong' width:'200' height:'200'
     * title:'RF Schema'}.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithNoProviderKo()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidNoProvider.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertNull(representation.getProvider());
        assertEquals(0, representation.getHeight());
        assertEquals(0, representation.getWidth());
        assertNull(representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(0, optionValueMap.size());
        assertEquals(1, representation.getParsingErrors().size());
        assertEquals("The image tag is referencing an unknown diagram provider : 'wrong'",
                representation.getParsingErrors().get(0).getMessage());
    }

    /**
     * Tests parsing of a diagram tag with the {@link SiriusDiagramByTitleProvider}. "title" option is missing.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider' width:'200' height:'200'
     * title:'RF Schema'}.
     * Provider options are handled by template processor so no error should be added.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws DocumentParserException
     */
    @Test
    public void testRepresentationParsingWithTitleProviderAndNoTitle()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalid2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof SiriusDiagramByTitleProvider);
        assertEquals(200, representation.getHeight());
        assertEquals(200, representation.getWidth());
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        EMap<String, Object> optionValueMap = representation.getOptionValueMap();
        assertEquals(0, optionValueMap.size());
        assertEquals(0, representation.getParsingErrors().size());
    }

    /**
     * Tests that parsing a diagram tag with a {@link SiriusDiagramByRepresentationAndEObjectProvider} and an invalid AQL expression
     * provides the right template element.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider'
     * width:'200' height:'200' rootObject:'db.wrong->first()' diagramDescriptionName:'Schema Diagram' legend:'plan de forme du dingy
     * herbulot' legendPos:'below'}.
     * 
     * @throws IOException
     * @throws InvalidFormatException
     * @throws DocumentParserException²
     */
    @Test
    public void testRepresentationParsingWithRootObjectProviderAndInvalidAqlExpressionOk()
            throws IOException, InvalidFormatException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidAqlExpression.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        assertEquals(1, template.getSubConstructs().size());
        Representation representation = (Representation) template.getSubConstructs().get(0);
        assertTrue(representation.getProvider() instanceof SiriusDiagramByRepresentationAndEObjectProvider);
        assertEquals(200, representation.getHeight());
        assertEquals(200, representation.getWidth());
        assertEquals("plan de forme du dingy herbulot", representation.getLegend());
        assertEquals(POSITION.BELOW, representation.getLegendPOS());
        assertEquals(2, representation.getOptionValueMap().size());
        assertEquals(1, representation.getParsingErrors().size());
        assertEquals("Expression wrong.-> is invalid", representation.getParsingErrors().get(0).getMessage());
    }

}
