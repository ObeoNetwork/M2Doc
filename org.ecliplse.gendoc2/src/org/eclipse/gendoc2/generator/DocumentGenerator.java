package org.eclipse.gendoc2.generator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.gendoc2.template.DocumentTemplate;
import org.eclipse.gendoc2.template.Template;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;

public class DocumentGenerator {
	/**
	 * The {@link IQueryEnvironment} instance used for evaluating all the AQL
	 * queries found in the template.
	 */
	private IQueryEnvironment queryEnvironment;

	/**
	 * variable definition used during generation.
	 */
	private Map<String, Object> definitions;
	/**
	 * The template against which generation is done.
	 */
	private DocumentTemplate template;
	/**
	 * The file where the generation is stored.
	 */
	private String destinationFileName;
	/**
	 * The generated document.
	 */
	private XWPFDocument destinationDocument;

	/**
	 * Create a new {@link DocumentGenerator} instance given a template and a
	 * variable definition map.
	 * 
	 * @param theTemplate
	 *            the template used for generation
	 * @param variables
	 *            a mapping of variables used during generation.
	 * @throws DocumentGenerationException
	 */
	public DocumentGenerator(String inputDocumentFileName, String destinationFileName, DocumentTemplate theTemplate,
			Map<String, Object> variables, IQueryEnvironment environment) throws DocumentGenerationException {
		this.definitions = new HashMap<String, Object>(variables);
		this.template = theTemplate;
		this.destinationFileName = destinationFileName;
		this.queryEnvironment = environment;
		try {
			this.destinationDocument = createDestinationDocument(inputDocumentFileName);
		} catch (InvalidFormatException e) {
			throw new DocumentGenerationException("Input document seems to have an invalid format.", e);
		} catch (IOException e) {
			throw new DocumentGenerationException("An I/O problem occured while creating the output document.", e);
		}
	}

	/**
	 * actually trigger the document generation process.
	 * 
	 * @throws IOException
	 *             if an I/O problem occurs during generation.
	 */
	public void generate() throws IOException {
		// The output document is created from the input so as to get the styles
		// and definitions in the original template.
		TemplateProcessor processor = new TemplateProcessor(definitions, queryEnvironment, destinationDocument);
		processor.doSwitch(this.template.getBody());
		Iterator<XWPFFooter> footers = destinationDocument.getFooterList().iterator();
		for (Template footerTemplate : this.template.getFooters()) {
			XWPFFooter footer = footers.next();
			cleanHeaderFooter(footer);
			TemplateProcessor footerProc = new TemplateProcessor(definitions, queryEnvironment, footer);
			footerProc.doSwitch(footerTemplate);
		}
		Iterator<XWPFHeader> headers = destinationDocument.getHeaderList().iterator();
		for (Template headerTemplate : this.template.getHeaders()) {
			XWPFHeader header = headers.next();
			cleanHeaderFooter(header);
			TemplateProcessor headerProc = new TemplateProcessor(definitions, queryEnvironment, header);
			headerProc.doSwitch(headerTemplate);
		}
		// At this point, the documnet has been generated and just needs being
		// writen on disk.
		saveFile();
	}

	/**
	 * Clear up the header or footer from the existing paragraphs and tables.
	 * 
	 * @param headerFooter
	 *            the header or footer to clean up.
	 */
	void cleanHeaderFooter(XWPFHeaderFooter headerFooter) {
		CTHdrFtr ctHdrFtr = (CTHdrFtr) headerFooter._getHdrFtr().copy();
		ctHdrFtr.getPList().clear();
		ctHdrFtr.getTblList().clear();
		// XXX : there are many other lists in the header and footer that should
		// probably be cleaned.
		headerFooter.setHeaderFooter(ctHdrFtr);
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
	 * Saves the generated document.
	 * 
	 * @throws IOException
	 */
	private void saveFile() throws IOException {
		FileOutputStream os = new FileOutputStream(destinationFileName);
		this.destinationDocument.write(os);
	}
}
