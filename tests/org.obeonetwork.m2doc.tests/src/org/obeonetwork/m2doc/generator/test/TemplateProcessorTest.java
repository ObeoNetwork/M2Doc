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
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.TemplateProcessor;
import org.obeonetwork.m2doc.parser.BodyParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.provider.test.StubDiagramProvider;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.ui.genconf.GenconfPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test the {@link TemplateProcessor} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateProcessorTest {
    /**
     * {@link ProviderRegistry} instance used during testing.
     */
    private ProviderRegistry registry = ProviderRegistry.INSTANCE;

    /**
     * Root object of the genconf example model.
     */
    private EObject rootObject;

    /**
     * Query environment.
     */
    private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

    /**
     * Initialize registry.
     */
    @Before
    public void setUp() {
        registry.clear();
        registry.registerProvider(new StubDiagramProvider());
    }

    /**
     * Cleaning.
     */
    @After
    public void after() {
        registry.clear();
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
     * Tests that pictures are integrated to produced WORD document when provider provides two pictures paths.
     * The template tag is {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider" width:"200" height:"200"
     * resultKind="twoImage" legend:"plan de forme du dingy herbulot" legendPos:"below"} that should produced an image in the run.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagTwoImageInsertionOk()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidTwoImage.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramValidTwoImage.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals("", destinationDoc.getParagraphs().get(0).getText());
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals(2, destinationDoc.getParagraphs().get(0).getRuns().get(0).getEmbeddedPictures().size());
    }

    /**
     * Tests that pictures are integrated to produced WORD document when provider provides one picture path.
     * The template tag is {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider" width:"200" height:"200"
     * resultKind="oneImage" legend:"plan de forme du dingy herbulot" legendPos:"below"} that should produced an image in the run.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagOneImageInsertionOk()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidOneImage.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramValidOneImage.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals("", destinationDoc.getParagraphs().get(0).getText());
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals(1, destinationDoc.getParagraphs().get(0).getRuns().get(0).getEmbeddedPictures().size());
    }

    /**
     * Tests that when no picture is found for a diagram tag, then an empty run is produced.
     * The template tag is {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider" width:"200" height:"200"
     * resultKind="zeroImage" legend:"plan de forme du dingy herbulot" legendPos:"below"} that should produced an image in the run.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagZeroImageInsertionOk()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidZeroImage.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramValidZeroImage.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals(0, destinationDoc.getParagraphs().get(0).getRuns().get(0).getEmbeddedPictures().size());
    }

    /**
     * Tests diagram tag when provider throw exception. A run must be produced with the message of the exception.
     * Used tag is : {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider" width:"200" height:"200"
     * resultKind="exception" legend:"plan de forme du dingy herbulot" legendPos:"below"}
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagException() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramException.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramException.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("A problem occured while creating image from an diagram provider : A problem occured.",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests template tag {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.Wrong' width:'200' height:'200'
     * rootObject:'db.schemas->first()' diagramDescriptionName:'Schema Diagram'} that should produced an error in the run because not
     * diagram title is provided.
     * The given provider does not exists. An error message should be in the run.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagNoProviderCorresponding()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidNoProvider.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramInvalidNoProvider.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("The image tag is referencing an unknown diagram provider : 'wrong'",
                destinationDoc.getParagraphs().get(0).getText());
    }

    /**
     * Tests template tag {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider'
     * width:'200' height:'200' rootObject:'wrong.->' diagramDescriptionName:'Schema Diagram' legend:'plan de forme du dingy herbulot'
     * legendPos:'below'} that should produced an error message in the run because the AQL expression in invalid.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagAqlOptionParsingError()
            throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramInvalidAqlExpression.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("db", rootObject);
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramInvalidAqlExpression.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("Syntax error in AQL expression.:Expression wrong.-> is invalid",
                destinationDoc.getParagraphs().get(0).getRuns().get(0).getText(0));
    }

    /**
     * Tests that valid AQL options are handled correctly. I.E {@link TemplateProcessor} parse the evaluated AQL value to the provider that
     * uses it correctly.
     * The template tag is {m:diagram provider:" org.obeonetwork.m2doc.provider.test.StubDiagramProvider" width:"200" height:"200"
     * aqlExpression:"'testImage'" legend:"plan de forme du dingy herbulot" legendPos:"below"}
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     */
    @Test
    public void testDiagramTagAqlValid() throws InvalidFormatException, IOException, DocumentParserException {
        FileInputStream is = new FileInputStream("templates/diagramValidAqlOption.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        BodyParser parser = new BodyParser(document, env);
        Template template = parser.parseTemplate();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("db", rootObject);
        XWPFDocument destinationDoc = createDestinationDocument("templates/diagramValidAqlOption.docx");
        TemplateProcessor processor = new TemplateProcessor(definitions, "results", env, destinationDoc, rootObject);
        processor.doSwitch(template);
        assertEquals(1, destinationDoc.getParagraphs().size());
        assertEquals("", destinationDoc.getParagraphs().get(0).getRuns().get(0).getText(0));
        assertEquals(1, destinationDoc.getParagraphs().get(0).getRuns().get(0).getEmbeddedPictures().size());
    }

}
