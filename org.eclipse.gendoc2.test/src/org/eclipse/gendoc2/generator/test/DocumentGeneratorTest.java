package org.eclipse.gendoc2.generator.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gendoc2.generator.DocumentGenerationException;
import org.eclipse.gendoc2.generator.DocumentGenerator;
import org.eclipse.gendoc2.parser.BodyParser;
import org.eclipse.gendoc2.parser.DocumentParserException;
import org.eclipse.gendoc2.template.Template;
import org.junit.Test;

public class DocumentGeneratorTest {
	@Test
	public void testStaticFragmentWithFieldProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testStaticFragmentWithfields.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragmentWithfields.docx",
				"results/testStaticFragmentWithfields.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testStaticFragmentProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testStaticFragment.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testStaticFragment.docx",
				"results/testStaticFragment.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testVarRefInHeaderProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testVarInHeader.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testVarInHeader.docx",
				"results/testVarInHeaderResult.docx", template, definitions, queryEnvironment);
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
		BodyParser parser = new BodyParser(document, queryEnvironment);
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
		FileInputStream is = new FileInputStream("templates/testVarStyle.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
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
		BodyParser parser = new BodyParser(document, queryEnvironment);
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
		BodyParser parser = new BodyParser(document, queryEnvironment);
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
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("self", EcorePackage.eINSTANCE);
		DocumentGenerator generator = new DocumentGenerator("templates/testGDFOR.docx", "results/testGDFOR.docx",
				template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnal1trueProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "value1");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal1.docx",
				"results/testConditionnal1Result.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnal1falseProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal1.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal1.docx",
				"results/testConditionnal1FalseResult.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnal2Processing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "value1");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal2.docx",
				"results/testConditionnal2Result.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnalFalseProcessing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal2.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal2.docx",
				"results/testConditionnal2FalseResult.docx", template, definitions, queryEnvironment);
		generator.generate();
	}
	//
	// @Test
	// public void testConditionnal3Processing()
	// throws InvalidFormatException, IOException, DocumentParserException,
	// DocumentGenerationException {
	// IQueryEnvironment queryEnvironment =
	// org.eclipse.acceleo.query.runtime.Query
	// .newEnvironmentWithDefaultServices(null);
	// FileInputStream is = new
	// FileInputStream("templates/testConditionnal3.docx");
	// OPCPackage oPackage = OPCPackage.open(is);
	// XWPFDocument document = new XWPFDocument(oPackage);
	// BodyParser parser = new BodyParser(document, queryEnvironment);
	// Template template = parser.parseTemplate();
	// Map<String, Object> definitions = new HashMap<String, Object>();
	// definitions.put("x", "valueofx");
	// DocumentGenerator generator = new
	// DocumentGenerator("templates/testConditionnal3.docx",
	// "results/testConditionnal3Result.docx", template, definitions,
	// queryEnvironment);
	// generator.generate();
	// }
	//
	// @Test
	// public void testConditionnal4Processing()
	// throws InvalidFormatException, IOException, DocumentParserException,
	// DocumentGenerationException {
	// IQueryEnvironment queryEnvironment =
	// org.eclipse.acceleo.query.runtime.Query
	// .newEnvironmentWithDefaultServices(null);
	// FileInputStream is = new
	// FileInputStream("templates/testConditionnal4.docx");
	// OPCPackage oPackage = OPCPackage.open(is);
	// XWPFDocument document = new XWPFDocument(oPackage);
	// BodyParser parser = new BodyParser(document, queryEnvironment);
	// Template template = parser.parseTemplate();
	// Map<String, Object> definitions = new HashMap<String, Object>();
	// definitions.put("x", "valueofx");
	// DocumentGenerator generator = new
	// DocumentGenerator("templates/testConditionnal4.docx",
	// "results/testConditionnal4Result.docx", template, definitions,
	// queryEnvironment);
	// generator.generate();
	// }

	@Test
	public void testConditionnal5Processing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "value1");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
				"results/testConditionnal5Result.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnal6Processing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "value2");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
				"results/testConditionnal6Result.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnal7Processing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "value3");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
				"results/testConditionnal7Result.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void testConditionnal8Processing()
			throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		FileInputStream is = new FileInputStream("templates/testConditionnal5.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		BodyParser parser = new BodyParser(document, queryEnvironment);
		Template template = parser.parseTemplate();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("x", "valueofx");
		DocumentGenerator generator = new DocumentGenerator("templates/testConditionnal5.docx",
				"results/testConditionnal8Result.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

}