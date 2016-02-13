package org.ecliplse.gendoc2.parser;

public enum RunType {
	AQL("aql:"), GDFOR("gd:for"), GDENDFOR("gd:endfor"), GDIF("gd:if"), GDELSEIF("gd:elseif"), GDELSE(
			"gd:else"), GDENDIF("gd:endif"), GDTABLE("gd:table"), GDLET("gd:let"), GDENDLET("gd:endlet"), ELT(
					"elt:"), VAR("var:"), STATIC("static"), GDIMAGE("gd:image"), EOF("end of file.");

	private String value;

	public String getValue() {
		return value;
	}

	private RunType(String theValue) {
		this.value = theValue;
	}
}
