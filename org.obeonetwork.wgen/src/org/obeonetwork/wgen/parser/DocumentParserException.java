package org.obeonetwork.wgen.parser;

import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocumentParserException extends Exception {

	private XWPFRun run;

	public DocumentParserException(String message) {
		super(message);
	}

	public DocumentParserException(String message, XWPFRun run) {
		super(message);
	}

	public DocumentParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
