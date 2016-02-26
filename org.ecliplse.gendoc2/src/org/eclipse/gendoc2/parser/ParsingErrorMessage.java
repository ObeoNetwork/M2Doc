package org.eclipse.gendoc2.parser;

public enum ParsingErrorMessage {

	CONDTAGEXPEXTED("gd:elseif, gd:else or gd:endif expected here."), UNEXPECTEDTAG(
			"Unexpected tag {0} at this location"), MALFORMEDTAG("Tag {0} is malformed"), INVALIDEXPR(
					"Expression {0} is invalid"), NOVARDEFINED("no variable defined.");

	private String errorMsg;

	private ParsingErrorMessage(String msg) {
		this.errorMsg = msg;
	}

	public String getMessage() {
		return errorMsg;
	}
}
