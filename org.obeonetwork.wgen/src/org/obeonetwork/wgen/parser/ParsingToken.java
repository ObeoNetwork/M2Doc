package org.obeonetwork.wgen.parser;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 * Token used to direct parsing of gendoc templates.
 * 
 * @author Romain Guider
 *
 */
public class ParsingToken {
	/**
	 * The underlying body element.
	 */
	private XWPFTable table;
	private XWPFRun run;

	/**
	 * Creates a new parsing token that corresponds to a run.
	 * 
	 * @param run
	 *            the run
	 */
	public ParsingToken(XWPFRun run) {
		this.run = run;
	}

	/**
	 * Creates a new parsing token that corresponds to a table.
	 * 
	 * @param table
	 *            the table
	 */
	public ParsingToken(XWPFTable table) {
		this.table = table;
	}

	/**
	 * returns the underlying run if any.
	 * 
	 * @return the underlying run if any.
	 */
	public XWPFRun getRun() {
		return run;
	}

	/**
	 * returns the underlying table if any.
	 * 
	 * @return the underlying table if any.
	 */
	public XWPFTable getTable() {
		return table;
	}

	ParsingTokenKind getKind() {
		if (table != null) {
			return ParsingTokenKind.TABLE;
		} else {
			return ParsingTokenKind.RUN;
		}
	}
}
