package org.ecliplse.gendoc2.parser;

public enum DocumentParserError {

	UNEXPECTEDTAG("Unexpected tag {0} at this location"), MALFORMEDTAG("Tag {0} is malformed");

	private String errorMsg;

	private DocumentParserError(String msg) {
		this.errorMsg = msg;
	}

	public String getMessage() {
		return errorMsg;
	}
}
