package org.eclipse.gendoc2.generator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.eclipse.gendoc2.generator.TemplateProcessor;
import org.eclipse.gendoc2.parser.BodyParser;
import org.eclipse.gendoc2.parser.DocumentParserException;
import org.eclipse.gendoc2.template.Template;
import org.junit.Test;

public class TemplateProcessorTest {

	private IQueryEnvironment env = org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);

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
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
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
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
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
		FileInputStream is = new FileInputStream("templates/testVar.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, env);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		XWPFDocument destinationDoc = createDestinationDocument("templates/testVar.docx");
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
		processor.doSwitch(template);
		// scan the destination document
		assertEquals(2, destinationDoc.getParagraphs().size());
		System.out.println(destinationDoc.getParagraphs().get(0).getText());
		assertEquals("Template de test pour les balises de référence à une variable\u00a0: valueofx",
				destinationDoc.getParagraphs().get(0).getText());
		assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
	}

	@Test
	public void testVarQueryStyledProcessing() throws InvalidFormatException, IOException, DocumentParserException {
		FileInputStream is = new FileInputStream("templates/testAQL.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, env);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		XWPFDocument destinationDoc = createDestinationDocument("templates/testAQL.docx");
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
		processor.doSwitch(template);
		assertEquals("Template de test pour les balises de référence à une variable\u00a0: ecore",
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
		definitions.put("x", "valueofx");
		XWPFDocument destinationDoc = createDestinationDocument("templates/testGDFOR.docx");
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
		processor.doSwitch(template);
		assertEquals("Template de test pour les balises de référence à une variable\u00a0: valueofx",
				destinationDoc.getParagraphs().get(0).getText());
		XWPFParagraph paragraph = destinationDoc.getParagraphs().get(0);
		XWPFRun run = paragraph.getRuns().get(paragraph.getRuns().size() - 1);
		assertEquals("E36C0A", run.getColor());
		assertNotNull(run.getCTR().getRPr().getI());
		assertNotNull(run.getCTR().getRPr().getB());
	}
	//
	// @Test
	// public void testVarRefStyledMultipleParagraphsProcessing()
	// throws InvalidFormatException, IOException, DocumentParserException {
	// FileInputStream is = new
	// FileInputStream("templates/testVarStyleSpanning2Paragraphs.docx");
	// OPCPackage oPackage = OPCPackage.open(is);
	// XWPFDocument document = new XWPFDocument(oPackage);
	// DocumentParser parser = new DocumentParser(document, env);
	// Template template = parser.parseTemplate();
	// Map<String, Object> definitions = new HashMap<String, Object>();
	// definitions.put("x", "valueofx");
	// TemplateProcessor processor = new TemplateProcessor(definitions, env);
	// AbstractConstruct result =
	// processor.doSwitch(template.getSubConstructs().get(1));
	// assertTrue(result instanceof StaticFragment);
	// XWPFRun run = result.getRuns().get(0);
	// assertEquals("valueofx", run.getText(run.getTextPosition()));
	// assertEquals("E36C0A", run.getColor());
	// assertNotNull(run.getCTR().getRPr().getI());
	// assertNotNull(run.getCTR().getRPr().getB());
	// }
	//
	// @Test
	// public void testQueryProcessing() throws InvalidFormatException,
	// IOException, DocumentParserException {
	// FileInputStream is = new FileInputStream("templates/testAQL.docx");
	// OPCPackage oPackage = OPCPackage.open(is);
	// XWPFDocument document = new XWPFDocument(oPackage);
	// DocumentParser parser = new DocumentParser(document, env);
	// Template template = parser.parseTemplate();
	// Map<String, Object> definitions = new HashMap<String, Object>();
	// definitions.put("self", EcorePackage.eINSTANCE);
	// TemplateProcessor processor = new TemplateProcessor(definitions, env);
	// AbstractConstruct result =
	// processor.doSwitch(template.getSubConstructs().get(1));
	// assertTrue(result instanceof StaticFragment);
	// XWPFRun run = result.getRuns().get(0);
	// assertEquals("ecore", run.getText(run.getTextPosition()));
	// }
}
