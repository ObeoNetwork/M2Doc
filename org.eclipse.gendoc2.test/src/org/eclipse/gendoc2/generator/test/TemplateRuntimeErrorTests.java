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
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.gendoc2.generator.TemplateProcessor;
import org.eclipse.gendoc2.parser.BodyParser;
import org.eclipse.gendoc2.parser.DocumentParserException;
import org.eclipse.gendoc2.template.Template;
import org.junit.Test;

public class TemplateRuntimeErrorTests {

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

	/**
	 * Tests processing of a variable reference where the variable is unknown.
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws DocumentParserException
	 */
	@Test
	public void testUnknownVarRefProcessing() throws InvalidFormatException, IOException, DocumentParserException {
		FileInputStream is = new FileInputStream("templates/testVar.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, env);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		XWPFDocument destinationDoc = createDestinationDocument("templates/testVar.docx");
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
		processor.doSwitch(template);
		// scan the destination document
		assertEquals(2, destinationDoc.getParagraphs().size());
		System.out.println(destinationDoc.getParagraphs().get(0).getText());
		assertEquals("Template de test pour les balises de référence à une variable\u00a0: unknown variable : x",
				destinationDoc.getParagraphs().get(0).getText());
		XWPFRun run = destinationDoc.getParagraphs().get(0).getRuns()
				.get(destinationDoc.getParagraphs().get(0).getRuns().size() - 1);
		assertEquals("FF0000", run.getColor());
		assertNotNull(run.getCTR().getRPr().getB());

		assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
	}

	/**
	 * Tests processing of a query where the evaluation results in an error.
	 * 
	 * @throws InvalidFormatException
	 * @throws IOException
	 * @throws DocumentParserException
	 */
	@Test
	public void testQueryEvaluationErrorProcessing()
			throws InvalidFormatException, IOException, DocumentParserException {
		FileInputStream is = new FileInputStream("templates/testAQL.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, env);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		XWPFDocument destinationDoc = createDestinationDocument("templates/testAQL.docx");
		TemplateProcessor processor = new TemplateProcessor(definitions, env, destinationDoc);
		processor.doSwitch(template);
		// scan the destination document
		assertEquals(2, destinationDoc.getParagraphs().size());
		System.out.println(destinationDoc.getParagraphs().get(0).getText());
		assertEquals(
				"Template de test pour les balises de query aql\u00a0: \nCouldn't find the self variable\nAttempt to access feature (name) on a non ModelObject value (org.eclipse.acceleo.query.runtime.impl.Nothing).",
				destinationDoc.getParagraphs().get(0).getText());
		XWPFRun run = destinationDoc.getParagraphs().get(0).getRuns()
				.get(destinationDoc.getParagraphs().get(0).getRuns().size() - 1);
		assertEquals("FF0000", run.getColor());
		assertNotNull(run.getCTR().getRPr().getB());
		assertEquals("Fin du gabarit", destinationDoc.getParagraphs().get(1).getText());
	}

}
