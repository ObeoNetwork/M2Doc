package org.obeonetwork.wgen.generator.test;

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
import org.junit.Test;
import org.obeonetwork.wgen.generator.DocumentGenerationException;
import org.obeonetwork.wgen.generator.DocumentGenerator;
import org.obeonetwork.wgen.parser.DocumentParser;
import org.obeonetwork.wgen.parser.DocumentParserException;
import org.obeonetwork.wgen.template.DocumentTemplate;
import org.obeonetwork.wgen.template.TemplatePackage;

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