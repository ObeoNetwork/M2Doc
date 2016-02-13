package org.eclipse.gendoc2.generator.test;

public class TemplateProcessorTest {
	//
	// private IQueryEnvironment env =
	// org.eclipse.acceleo.query.runtime.Query.newEnvironmentWithDefaultServices(null);
	//
	// private XWPFDocument createDestinationDocument(String
	// inputDocumentFileName)
	// throws IOException, InvalidFormatException {
	// FileInputStream is = new FileInputStream(inputDocumentFileName);
	// OPCPackage oPackage = OPCPackage.open(is);
	// XWPFDocument document = new XWPFDocument(oPackage);
	// int size = document.getBodyElements().size();
	// for (int i = 0; i < size; i++) {
	// document.removeBodyElement(0);
	// }
	// return document;
	// }
	//
	// @Test
	// public void testVarRefProcessing() throws InvalidFormatException,
	// IOException, DocumentParserException {
	// FileInputStream is = new FileInputStream("templates/testVar.docx");
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
	// }
	//
	// @Test
	// public void testVarRefStyledProcessing() throws InvalidFormatException,
	// IOException, DocumentParserException {
	// FileInputStream is = new FileInputStream("templates/testVarStyle.docx");
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
