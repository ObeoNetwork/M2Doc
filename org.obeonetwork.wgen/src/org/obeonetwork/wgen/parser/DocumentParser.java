package org.obeonetwork.wgen.parser;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.wgen.template.DocumentTemplate;
import org.obeonetwork.wgen.template.TemplatePackage;

public class DocumentParser {
	/**
	 * The environment used to parse queries.
	 */
	private IQueryEnvironment queryEnvironment;
	/**
	 * Main parsed document.
	 */
	private XWPFDocument document;

	public DocumentParser(XWPFDocument document, IQueryEnvironment queryEnvironment) {
		this.queryEnvironment = queryEnvironment;
		this.document = document;
	}

	/**
	 * Parses a document and returns the {@link DocumentTemplate} resulting from
	 * this parsing.
	 * 
	 * @return the {@link DocumentTemplate} resulting from parsing the specified
	 *         document.
	 * @throws DocumentParserException
	 *             if a problem occurs while parsing the document.
	 */
	public DocumentTemplate parseDocument() throws DocumentParserException {
		@SuppressWarnings("restriction")
		DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
		BodyParser parser = new BodyParser(document, new QueryBuilderEngine(queryEnvironment));
		result.setBody(parser.parseTemplate());
		for (XWPFFooter footer : document.getFooterList()) {
			BodyParser footerParser = new BodyParser(footer, new QueryBuilderEngine(queryEnvironment));
			result.getFooters().add(footerParser.parseTemplate());
		}
		for (XWPFHeader header : document.getHeaderList()) {
			BodyParser headerParser = new BodyParser(header, new QueryBuilderEngine(queryEnvironment));
			result.getHeaders().add(headerParser.parseTemplate());
		}
		return result;
	}

}
