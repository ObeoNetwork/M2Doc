package org.eclipse.gendoc2.generator.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.InvalidAcceleoPackageException;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.gendoc2.generator.DocumentGenerationException;
import org.eclipse.gendoc2.generator.DocumentGenerator;
import org.eclipse.gendoc2.parser.DocumentParser;
import org.eclipse.gendoc2.parser.DocumentParserException;
import org.eclipse.gendoc2.template.DocumentTemplate;
import org.eclipse.gendoc2.template.TemplatePackage;
import org.junit.Test;

public class EcoreDocumentationGeneration {
	@Test
	public void testStaticFragmentWithFieldProcessing() throws InvalidFormatException, IOException,
			DocumentParserException, DocumentGenerationException, InvalidAcceleoPackageException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
		queryEnvironment.registerServicePackage(EcoreDocumentationServices.class);
		FileInputStream is = new FileInputStream("templates/ecoreDocumentationTemplate.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		DocumentTemplate template = parser.parseDocument();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("self", TemplatePackage.eINSTANCE);
		DocumentGenerator generator = new DocumentGenerator("templates/ecoreDocumentationTemplate.docx",
				"results/ecoreDocumentationTemplateResults.docx", template, definitions, queryEnvironment);
		generator.generate();
	}

	@Test
	public void syntaxDocumentationGeneration() throws InvalidFormatException, IOException, DocumentParserException,
			DocumentGenerationException, InvalidAcceleoPackageException {
		IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
				.newEnvironmentWithDefaultServices(null);
		queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
		queryEnvironment.registerServicePackage(EcoreDocumentationServices.class);
		FileInputStream is = new FileInputStream("templates/WGenSyntax.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		DocumentParser parser = new DocumentParser(document, queryEnvironment);
		DocumentTemplate template = parser.parseDocument();
		Map<String, Object> definitions = new HashMap<String, Object>();
		definitions.put("self", TemplatePackage.eINSTANCE);
		DocumentGenerator generator = new DocumentGenerator("templates/WGenSyntax.docx", "results/WGenSyntax.docx",
				template, definitions, queryEnvironment);
		generator.generate();
	}

}