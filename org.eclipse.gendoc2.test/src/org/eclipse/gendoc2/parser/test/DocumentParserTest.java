package org.eclipse.gendoc2.parser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.ecliplse.gendoc2.parser.BodyParser;
import org.ecliplse.gendoc2.parser.DocumentParserException;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Default;
import org.eclipse.gendoc2.template.Query;
import org.eclipse.gendoc2.template.Repetition;
import org.eclipse.gendoc2.template.StaticFragment;
import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.VarRef;
import org.junit.Test;

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
		assertTrue(template.getSubConstructs().get(1) instanceof VarRef);
		assertTrue(template.getSubConstructs().get(2) instanceof StaticFragment);
		VarRef varRef = (VarRef) template.getSubConstructs().get(1);
		assertEquals("x", varRef.getVarName());
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

}
