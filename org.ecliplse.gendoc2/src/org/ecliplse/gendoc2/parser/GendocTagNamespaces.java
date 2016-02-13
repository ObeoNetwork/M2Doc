package org.ecliplse.gendoc2.parser;

public enum GendocTagNamespaces {

	AQL("aql"), GENDOC("gd"), FEATURE("feature"), SERVICE("service");

	private String tagName;

	public String getNSName() {
		return tagName;
	}

	private GendocTagNamespaces(String tagName) {
		this.tagName = tagName;
	}

}
