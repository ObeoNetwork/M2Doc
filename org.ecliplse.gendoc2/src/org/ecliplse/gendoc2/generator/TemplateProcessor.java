package org.ecliplse.gendoc2.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Query;
import org.eclipse.gendoc2.template.Repetition;
import org.eclipse.gendoc2.template.StaticFragment;
import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.VarRef;
import org.eclipse.gendoc2.template.util.TemplateSwitch;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

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
	private XWPFDocument generatedDocument;
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
			XWPFDocument destinationDocument) {
		this.definitions = new GenerationEnvironment(initialDefs);
		this.queryEnvironment = queryEnvironment;
		this.generatedDocument = destinationDocument;
		generatedParagraphRank = -1;// will be incremented to 0 when the first
									// paragraph is inserted.
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
		}
		return object;
	}

	@SuppressWarnings("deprecation")
	private XWPFRun insertRun(XWPFRun srcRun) {
		if (srcRun.getParagraph() != currentTemplateParagraph) {
			createNewParagraph(srcRun.getParagraph());
		}
		XWPFRun generatedRun = currentGeneratedParagraph.createRun();
		generatedRun.getCTR().set(srcRun.getCTR());
		return generatedRun;
	}

	private XWPFRun insertFieldRunReplacement(XWPFRun srcRun, String replacement) {
		if (srcRun.getParagraph() != currentTemplateParagraph) {
			createNewParagraph(srcRun.getParagraph());
		}
		XWPFRun generatedRun = currentGeneratedParagraph.createRun();
		generatedRun.getCTR().set(srcRun.getCTR());
		generatedRun.getCTR().getInstrTextList().clear();
		generatedRun.setText(replacement);
		return generatedRun;
	}

	private void createNewParagraph(XWPFParagraph srcParagraph) {
		// create a new paragraph.
		XWPFParagraph newParagraph = generatedDocument.createParagraph();
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
		if (result.getDiagnostic().getCode() == Diagnostic.ERROR) {
			strResult = result.getDiagnostic().getMessage();
		} else {
			strResult = result.getResult() == null ? "null" : result.getResult().toString();
		}
		insertFieldRunReplacement(object.getStyleRun(), strResult);
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AbstractConstruct caseRepetition(Repetition object) {
		// first evaluate the query
		@SuppressWarnings("restriction")
		EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval(object.getQuery(),
				definitions.getCurrentDefinitions());
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
		}
		return object;
	}

	@Override
	public AbstractConstruct caseConditionnal(Conditionnal object) {
		@SuppressWarnings("restriction")
		EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval(object.getQuery(),
				definitions.getCurrentDefinitions());
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
			// TODO : the evaluation result could be the result of a runtime
			// error that cannot be detected at validation time
			// to cover such cases we should insert a piece of text in the
			// resulting document indicating the problem.
			throw new IllegalArgumentException(
					"evaliation of conditionnal expression should result in boolean value : " + result.getResult());
		}
		return object;
	}
}
