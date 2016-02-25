package org.eclipse.gendoc2.parser;

public enum DocumentParsingError {

	UNEXPECTEDTAG("Unexpected tag {0} at this location"), MALFORMEDTAG("Tag {0} is malformed"), INVALIDEXPR(
			"Expression {0} is invalid");

	private String errorMsg;

	private DocumentParsingError(String msg) {
		this.errorMsg = msg;
	}

	public String getMessage() {
		return errorMsg;
	}
}
