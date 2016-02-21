package org.eclipse.gendoc2.generator.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.ecliplse.gendoc2.generator.DocumentGenerationException;
import org.ecliplse.gendoc2.generator.DocumentGenerator;
import org.ecliplse.gendoc2.parser.DocumentParser;
import org.ecliplse.gendoc2.parser.DocumentParserException;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gendoc2.template.Template;
import org.junit.Test;

public class DocumentGeneratorTest {
	@Test
	public void testStaticFragmentProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testStaticFragment.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragment.docx",
				"results/testStaticFragment.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testVarRefProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testVar.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testVar.docx", "results/testVarResult.docx",
				template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testVarRefStyledProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testVar.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testVarStyle.docx",
				"results/testVarStyleResult.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testVarRefStyledMultipleParagraphsProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testVarStyleSpanning2Paragraphs.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testVarStyleSpanning2Paragraphs.docx",
				"results/testVarStyleSpanning2ParagraphsResult.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testQueryStyledProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testAQL.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("self", EcorePackage.eINSTANCE);
		DocumentGenerator generator = new DocumentGenerator("templates/testAQL.docx", "results/testAQLResult.docx",
				template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testGDFORProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testGDFOR.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("self", EcorePackage.eINSTANCE);
		DocumentGenerator generator = new DocumentGenerator("templates/testGDFOR.docx", "results/testGDFOR.docx",
				template, definitions, queryEnvironment);
		generator.generate();
	}

}
