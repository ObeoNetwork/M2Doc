package org.eclipse.gendoc2.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Cell;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Query;
import org.eclipse.gendoc2.template.Repetition;
import org.eclipse.gendoc2.template.Row;
import org.eclipse.gendoc2.template.StaticFragment;
import org.eclipse.gendoc2.template.Table;
import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.VarRef;
import org.eclipse.gendoc2.template.util.TemplateSwitch;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

public class TemplateProcessor extends TemplateSwitch<AbstractConstruct> {

	/**
	 * The {@link IQueryEnvironment} instance used for evaluating all the AQL
	 * queries found in the template.
	 */
	private IQueryEnvironment queryEnvironment;

	/**
	 * variable definition used during generation.
	 */
	private GenerationEnvironment definitions;
	/**
	 * The generated document.
	 */
	private IBody generatedDocument;
	/**
	 * The currently read template paragraph used to detect paragraph changes.
	 */
	private XWPFParagraph currentTemplateParagraph;
	/**
	 * The currently generated paragraph where runs are actually inserted.
	 */
	private XWPFParagraph currentGeneratedParagraph;
	/**
	 * The index of the currentGeneratedParagraph in the generatedDocument.
	 */
	private int generatedParagraphRank;
	/**
	 * Used to force a new paragraph in gf:for body when there's a carriage
	 * return before the {gd:endfor} tag.
	 */
	private boolean forceNewParagraph;

	/**
	 * Create a new {@link TemplateProcessor} instance given some definitions
	 * and a query environment.
	 * 
	 * @param initialDefs
	 *            the definitions used in queries and variable tags
	 * @param queryEnvironment
	 *            the query environment used to evaluate queries in the
	 *            template.
	 */
	public TemplateProcessor(Map<String, Object> initialDefs, IQueryEnvironment queryEnvironment,
			IBody destinationDocument) {
		this.definitions = new GenerationEnvironment(initialDefs);
		this.queryEnvironment = queryEnvironment;
		this.generatedDocument = destinationDocument;
		generatedParagraphRank = -1;// will be incremented to 0 when the first
									// paragraph is inserted.
	}

	/**
	 * Create a new {@link TemplateProcessor} instance given some definitions
	 * and a query environment.
	 * 
	 * @param initialDefs
	 *            the definitions used in queries and variable tags
	 * @param queryEnvironment
	 *            the query environment used to evaluate queries in the
	 *            template.
	 */
	public TemplateProcessor(GenerationEnvironment defs, IQueryEnvironment queryEnvironment,
			IBody destinationDocument) {
		this.definitions = defs;
		this.queryEnvironment = queryEnvironment;
		this.generatedDocument = destinationDocument;
		generatedParagraphRank = -1;// will be incremented to 0 when the first
									// paragraph is inserted.
	}

	int getDiagnostic(Diagnostic diagnostic, StringBuilder builder) {
		if (diagnostic.getCode() == Diagnostic.ERROR) {
			builder.append('\n').append(diagnostic.getMessage());
			return Diagnostic.ERROR;
		} else {
			String message = diagnostic.getMessage();
			if (message != null && !"".equals(message)) {
				builder.append('\n').append(message);
			}
			int childrenCode = diagnostic.getCode();
			for (Diagnostic child : diagnostic.getChildren()) {
				int code = getDiagnostic(child, builder);
				if (code > childrenCode) {
					childrenCode = code;
				}
			}
			return childrenCode;
		}
	}

	@Override
	public AbstractConstruct caseTemplate(Template object) {
		List<AbstractConstruct> subConstructs = object.getSubConstructs();
		for (int i = 0; i < subConstructs.size(); i++) {
			doSwitch(subConstructs.get(i));
		}
		return object;
	}

	@Override
	public AbstractConstruct caseStaticFragment(StaticFragment object) {
		for (XWPFRun run : object.getRuns()) {
			insertRun(run);
		}
		return object;
	}

	@Override
	public AbstractConstruct caseVarRef(VarRef object) {
		// retrieve the variable in the definitions
		Object value = definitions.getValue(object.getVarName());
		if (value != null) {
			insertFieldRunReplacement(object.getStyleRun(), value.toString());
		} else {
			XWPFRun run = insertFieldRunReplacement(object.getStyleRun(), "unknown variable : " + object.getVarName());
			run.setBold(true);
			run.setColor("FF0000");
		}
		return object;
	}

	@SuppressWarnings("deprecation")
	private XWPFRun insertRun(XWPFRun srcRun) {
		if (srcRun.getParagraph() != currentTemplateParagraph || forceNewParagraph) {
			createNewParagraph(srcRun.getParagraph());
			forceNewParagraph = false;
		}
		XWPFRun generatedRun = currentGeneratedParagraph.createRun();
		generatedRun.getCTR().set(srcRun.getCTR());
		return generatedRun;
	}

	private XWPFRun insertFieldRunReplacement(XWPFRun srcRun, String replacement) {
		if (srcRun.getParagraph() != currentTemplateParagraph || forceNewParagraph) {
			createNewParagraph(srcRun.getParagraph());
			forceNewParagraph = false;
		}
		// creates as many paragraphs as there are '\n's in the string.
		String[] fragments = replacement.split("\n");
		XWPFRun result = null;
		int size = fragments.length;
		for (int i = 0; i < size - 1; i++) {
			String fragment = fragments[i];
			XWPFRun generatedRun = insertFragment(fragments[i], srcRun);
			if (result == null) {
				result = generatedRun;
			}
			createNewParagraph(srcRun.getParagraph());
		}
		if (size > 0) {
			XWPFRun generatedRun = insertFragment(fragments[size - 1], srcRun);
			if (result == null) {
				result = generatedRun;
			}
		}
		return result;
	}

	private XWPFRun insertFragment(String fragment, XWPFRun srcRun) {
		XWPFRun generatedRun = currentGeneratedParagraph.createRun();
		generatedRun.getCTR().set(srcRun.getCTR().copy());
		generatedRun.getCTR().getInstrTextList().clear();
		generatedRun.setText(fragment);
		return generatedRun;
	}

	private void createNewParagraph(XWPFParagraph srcParagraph) {
		// create a new paragraph.
		XWPFParagraph newParagraph;
		if (generatedDocument instanceof XWPFTableCell) {
			XWPFTableCell cell = (XWPFTableCell) generatedDocument;
			newParagraph = cell.addParagraph();
		} else if (generatedDocument instanceof XWPFDocument) {
			newParagraph = ((XWPFDocument) generatedDocument).createParagraph();
		} else if (generatedDocument instanceof XWPFHeaderFooter) {
			newParagraph = ((XWPFHeaderFooter) generatedDocument).createParagraph();
		} else {
			throw new UnsupportedOperationException("unkown IBody type :" + generatedDocument.getClass());
		}
		CTP ctp = (CTP) srcParagraph.getCTP().copy();
		ctp.getRList().clear();
		ctp.getFldSimpleList().clear();
		ctp.getHyperlinkList().clear();
		newParagraph.getCTP().set(ctp);
		int runNb = newParagraph.getRuns().size();
		for (int i = 0; i < runNb; i++) {
			newParagraph.removeRun(i);
		}
		currentTemplateParagraph = srcParagraph;
		currentGeneratedParagraph = newParagraph;
	}

	@Override
	public AbstractConstruct caseQuery(Query object) {
		// first evaluate the query.
		@SuppressWarnings("restriction")
		IQueryEvaluationEngine evaluator = new QueryEvaluationEngine(queryEnvironment);
		EvaluationResult result = evaluator.eval((AstResult) object.getQuery(), definitions.getCurrentDefinitions());
		String strResult;
		if (result.getResult() == null) {
			StringBuilder builder = new StringBuilder();
			getDiagnostic(result.getDiagnostic(), builder);
			strResult = builder.toString();
		} else {
			strResult = result.getResult().toString();
		}
		XWPFRun run = insertFieldRunReplacement(object.getStyleRun(), strResult);
		if (result.getResult() == null) {
			run.setBold(true);
			run.setColor("FF0000");
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractConstruct caseRepetition(Repetition object) {
		// first evaluate the query
		@SuppressWarnings("restriction")
		EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval(object.getQuery(),
				definitions.getCurrentDefinitions());
		if (result.getDiagnostic().getCode() == Diagnostic.ERROR) {
			// insert the tag runs as is.
			for (XWPFRun tagRun : object.getRuns()) {
				insertRun(tagRun);
			}
			// insert the error message.
			XWPFRun run = currentGeneratedParagraph.createRun();
			run.setText(result.getDiagnostic().getMessage());
			if (result.getDiagnostic().getCode() == Diagnostic.ERROR) {
				run.setBold(true);
				run.setColor("FF0000");
			}
			for (XWPFRun tagRun : object.getClosingRuns()) {
				insertRun(tagRun);
			}
		} else {
			List<Object> iteration = new ArrayList<Object>();
			if (result.getResult() instanceof Collection) {
				iteration.addAll((Collection<? extends Object>) result.getResult());
			} else {
				iteration.add(result.getResult());
			}
			for (Object val : iteration) {
				this.definitions.setValue(object.getIterationVar(), val);
				for (AbstractConstruct construct : object.getSubConstructs()) {
					doSwitch(construct);
				}
				// if the {gd:endfor} lies on a distinct paragraph, insert a new
				// paragraph there to take this into acount.
				int bodySize = object.getSubConstructs().size();
				if (bodySize > 0) {
					AbstractConstruct lastBodyPart = object.getSubConstructs().get(bodySize - 1);
					int runNumber = lastBodyPart.getRuns().size();
					if (runNumber > 0) {
						XWPFRun lastRun = lastBodyPart.getRuns().get(runNumber - 1);
						int closingRunNumber = object.getClosingRuns().size();
						if (closingRunNumber > 0) {
							XWPFRun firstClosingRun = object.getClosingRuns().get(0);
							if (firstClosingRun.getParent() != lastRun.getParent()) {
								forceNewParagraph = true;
							}
						}
					}
				}
			}
		}
		return object;
	}

	@Override
	public AbstractConstruct caseConditionnal(Conditionnal object) {
		@SuppressWarnings("restriction")
		EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval(object.getQuery(),
				definitions.getCurrentDefinitions());
		// XXX : result can be null here : check that before using it.
		if (result.getResult() instanceof Boolean) {
			if ((Boolean) result.getResult()) {
				for (AbstractConstruct construct : object.getSubConstructs()) {
					doSwitch(construct);
				}
			} else if (object.getAlternative() != null) {
				doSwitch(object.getAlternative());
			} else if (object.getElse() != null) {
				for (AbstractConstruct construct : object.getElse().getSubConstructs()) {
					doSwitch(construct);
				}
			}
		} else {
			for (XWPFRun tagRun : object.getRuns()) {
				insertRun(tagRun);
			}
			XWPFRun run = currentGeneratedParagraph.createRun();
			run.setText(result.getDiagnostic().getMessage());
			if (result.getDiagnostic().getCode() == Diagnostic.ERROR) {
				run.setBold(true);
				run.setColor("FF0000");
			}
			for (XWPFRun tagRun : object.getClosingRuns()) {
				insertRun(tagRun);
			}
		}
		return object;
	}

	@Override
	public AbstractConstruct caseTable(Table object) {
		// Create the table structure in the destination document.
		XWPFTable generatedTable;
		CTTbl copy = (CTTbl) object.getTable().getCTTbl().copy();
		copy.getTrList().clear();
		if (generatedDocument instanceof XWPFDocument) {
			generatedTable = ((XWPFDocument) generatedDocument).createTable();
			if (generatedTable.getRows().size() > 0) {
				generatedTable.removeRow(0);
			}
			generatedTable.getCTTbl().set(copy);
		} else if (generatedDocument instanceof XWPFTableCell) {
			XWPFTableCell tCell = (XWPFTableCell) generatedDocument;
			int tableRank = tCell.getTables().size();
			XWPFTable newTable = new XWPFTable(copy, tCell, 0, 0);
			if (newTable.getRows().size() > 0) {
				newTable.removeRow(0);
			}
			tCell.insertTable(tableRank, newTable);
			generatedTable = tCell.getTables().get(tableRank);
		} else {
			throw new UnsupportedOperationException("unknown type of IBody : " + generatedDocument.getClass());
		}
		// iterate on the row
		for (Row row : object.getRows()) {
			XWPFTableRow newRow = generatedTable.createRow();
			CTRow ctRow = (CTRow) row.getTableRow().getCtRow().copy();
			ctRow.getTcList().clear();
			newRow.getCtRow().set(ctRow);
			// iterate on cells.
			for (Cell cell : row.getCells()) {
				XWPFTableCell newCell = newRow.createCell();
				CTTc ctCell = (CTTc) cell.getTableCell().getCTTc().copy();
				ctCell.getPList().clear();
				ctCell.getTblList().clear();
				newCell.getCTTc().set(ctCell);
				// process the cell :
				TemplateProcessor processor = new TemplateProcessor(definitions, queryEnvironment, newCell);
				processor.doSwitch(cell.getTemplate());
			}
		}
		return super.caseTable(object);
	}
}
