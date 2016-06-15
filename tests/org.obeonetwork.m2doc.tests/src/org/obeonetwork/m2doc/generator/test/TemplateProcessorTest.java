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
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.TemplateProcessor;
import org.obeonetwork.m2doc.parser.BodyParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.test.AbstractM2DocTest;

public class TemplateProcessorTest extends AbstractM2DocTest {

    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

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
     * Test the replacement of a variable in a doc.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testVarRefProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVar.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testVar.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        // scan the destination document
        assertEquals(2, destinationDoc.getParagraphs().size());
        System.out.println(destinationDoc.getParagraphs().get(0).getText());
        assertEquals("Template de test pour les balises de référence à une variable\u00a0: valueofx",
                destinationDoc.getParagraphs().get(0).getText());
        assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
    }

    @Test
    public void testVarRefStyledProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVarStyle.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testVarStyle.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises de référence à une variable\u00a0: valueofx",
                destinationDoc.getParagraphs().get(0).getText());
        XWPFParagraph paragraph = destinationDoc.getParagraphs().get(0);
        XWPFRun run = paragraph.getRuns().get(paragraph.getRuns().size() - 1);
        assertEquals("E36C0A", run.getColor());
        assertNotNull(run.getCTR().getRPr().getI());
        assertNotNull(run.getCTR().getRPr().getB());
    }

    @Test
    public void testQueryProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testAQL.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        XWPFDocument destinationDoc = createDestinationDocument("templates/testAQL.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        // scan the destination document
        assertEquals(3, destinationDoc.getParagraphs().size());
        System.out.println(destinationDoc.getParagraphs().get(0).getText());
        assertEquals("Template de test pour les balises de query aql\u00a0: ecore",
                destinationDoc.getParagraphs().get(0).getText());
        assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
        assertEquals("", destinationDoc.getParagraphs().get(2).getText());
    }

    @Test
    public void testVarQueryStyledProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testVarStyle.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "valueofx");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testVarStyle.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises de référence à une variable\u00a0: valueofx",
                destinationDoc.getParagraphs().get(0).getText());
        XWPFParagraph paragraph = destinationDoc.getParagraphs().get(0);
        XWPFRun run = paragraph.getRuns().get(paragraph.getRuns().size() - 1);
        assertEquals("E36C0A", run.getColor());
        assertNotNull(run.getCTR().getRPr().getI());
        assertNotNull(run.getCTR().getRPr().getB());
    }

    @Test
    public void testGDFORProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testGDFOR.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", EcorePackage.eINSTANCE);
        XWPFDocument destinationDoc = createDestinationDocument("templates/testGDFOR.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals(
                "Template de test pour les balises de répétition: name = EAttribute, name = EAnnotation, name = EClass, name = EClassifier, name = EDataType, name = EEnum, name = EEnumLiteral, name = EFactory, name = EModelElement, name = ENamedElement, name = EObject, name = EOperation, name = EPackage, name = EParameter, name = EReference, name = EStructuralFeature, name = ETypedElement, name = EStringToStringMapEntry, name = EGenericType, name = ETypeParameter, name = EBigDecimal, name = EBigInteger, name = EBoolean, name = EBooleanObject, name = EByte, name = EByteArray, name = EByteObject, name = EChar, name = ECharacterObject, name = EDate, name = EDiagnosticChain, name = EDouble, name = EDoubleObject, name = EEList, name = EEnumerator, name = EFeatureMap, name = EFeatureMapEntry, name = EFloat, name = EFloatObject, name = EInt, name = EIntegerObject, name = EJavaClass, name = EJavaObject, name = ELong, name = ELongObject, name = EMap, name = EResource, name = EResourceSet, name = EShort, name = EShortObject, name = EString, name = ETreeIterator, name = EInvocationTargetException, ",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>true</code> expression evaluation and without an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF1Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal1.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de value1",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and without
     * an else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF2Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal1.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>true</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF3Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal2.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de value1",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF4Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal2.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de default value",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF5Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal5.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de value1",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF6Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value2");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal5.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de value2",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF7Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal5.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de default value",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testGDIF8Processing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testConditionnal6.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("x", "value1");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testConditionnal6.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals("Template de test pour les balises conditionnelles\u00a0: ajout de value1",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testCarryageReturnProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testCarriageReturn.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("v", "part1\npart2\npart3\npart4");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testCarriageReturn.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals(4, destinationDoc.getParagraphs().size());
    }

    /**
     * Tests a gd:if with <code>false</code> expression evaluation and with an
     * else.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testEmptyParagraphsProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/testEmptyParagraphs.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("author", "Moi");
        XWPFDocument destinationDoc = createDestinationDocument("templates/testEmptyParagraphs.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "", env, destinationDoc, null);
        processor.doSwitch(template);
        assertEquals(5, destinationDoc.getParagraphs().size());
        assertEquals("Paragraph1 Moi", destinationDoc.getParagraphs().get(0).getText());
        assertEquals("", destinationDoc.getParagraphs().get(1).getText());
        assertEquals("Paragraph2", destinationDoc.getParagraphs().get(2).getText());
        assertEquals("", destinationDoc.getParagraphs().get(3).getText());
        assertEquals("Paragraph3 Moi", destinationDoc.getParagraphs().get(4).getText());
    }

    /**
     * Tests template tag {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider' width:'200'
     * height:'200' title:'RF Schema'} that should produced an image in the run.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepresentationProcessing() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValid.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramValid.docx");
        IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
        TemplateProcessor processor = new TemplateProcessor(definitions, iProject.getLocation().toString(), env,
                destinationDoc, getSemanticResource().getContents().get(0));
        processor.doSwitch(template);
        assertEquals(2, destinationDoc.getParagraphs().size());
        assertEquals(1, destinationDoc.getParagraphs().get(0).getRuns().get(0).getEmbeddedPictures().size());
    }

    /**
     * Tests template tag {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider' width:'200'
     * height:'200' title:'wrong'} that should produced an error in the run because the corresponding diagram does not exist.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testRepresentationProcessingFail() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalid.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramInvalid.docx");
        IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
        TemplateProcessor processor = new TemplateProcessor(definitions, iProject.getLocation().toString(), env,
                destinationDoc, getSemanticResource().getContents().get(0));
        processor.doSwitch(template);
        assertEquals(2, destinationDoc.getParagraphs().size());
        assertEquals(
                "A problem occured while creating image from an IDiagramProvider : Representation with title 'wrong' not found",
                destinationDoc.getParagraphs().get(0).getText());
    }

}
