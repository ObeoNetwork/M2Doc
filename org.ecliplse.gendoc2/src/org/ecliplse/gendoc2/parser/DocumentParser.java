package org.ecliplse.gendoc2.parser;

import java.text.MessageFormat;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Compound;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Image;
import org.eclipse.gendoc2.template.Query;
import org.eclipse.gendoc2.template.QueryBehavior;
import org.eclipse.gendoc2.template.Repetition;
import org.eclipse.gendoc2.template.Representation;
import org.eclipse.gendoc2.template.StaticFragment;
import org.eclipse.gendoc2.template.Table;
import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.TemplatePackage;
import org.eclipse.gendoc2.template.VarRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/**
 * DocumentParser reads a {@link XWPFDocument} and produces a abstract syntax
 * tree that represents the template in the document.
 * 
 * 
 * @author Romain Guider
 *
 */
public class DocumentParser {

	private static final String LABEL_MODIFIER = " label";
	private static final String ICON_MODIFIER = " icon";
	private static final String TEXT_MODIFIER = " text";
	/**
	 * Parsed template document.
	 */
	private XWPFDocument document;
	/**
	 * iterator over the document used to access {@link XWPFRun} instances in
	 * sequence.
	 */
	private RunProvider runIterator;
	/**
	 * {@link IQueryBuilderEngine} used to parse AQL queries.
	 */
	private IQueryBuilderEngine queryParser;

	@SuppressWarnings("restriction")
	public DocumentParser(XWPFDocument inputDocument, IQueryEnvironment queryEnvironment) {
		this.document = inputDocument;
		runIterator = new RunProvider(inputDocument);
		this.queryParser = new QueryBuilderEngine(queryEnvironment);
	}

	private String message(DocumentParserError error, Object... objects) {
		return MessageFormat.format(error.getMessage(), objects);
	}

	private boolean isFieldBegin(XWPFRun run) {
		if (run.getCTR().getFldCharList().size() > 0) {
			CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
			return STFldCharType.BEGIN.equals(fldChar.getFldCharType());
		} else {
			return false;
		}
	}

	private boolean isFieldEnd(XWPFRun run) {
		if (run.getCTR().getFldCharList().size() > 0) {
			CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
			return STFldCharType.END.equals(fldChar.getFldCharType());
		} else {
			return false;
		}
	}

	private RunType getNextRunType() {
		XWPFRun run1 = runIterator.lookAhead(1);
		RunType result;
		if (run1 == null) {
			return RunType.EOF;
		}
		// is run a field begin run
		if (isFieldBegin(run1)) {
			XWPFRun run2 = runIterator.lookAhead(2);
			if (run2.getCTR().getInstrTextList().size() > 0) {
				String code = run2.getCTR().getInstrTextList().get(0).stringValue().trim();
				if (code.startsWith(RunType.AQL.getValue())) {
					result = RunType.AQL;
				} else if (code.startsWith(RunType.GDFOR.getValue())) {
					result = RunType.GDFOR;
				} else if (code.startsWith(RunType.GDENDFOR.getValue())) {
					result = RunType.GDENDFOR;
				} else if (code.startsWith(RunType.GDIF.getValue())) {
					result = RunType.GDIF;
				} else if (code.startsWith(RunType.GDELSEIF.getValue())) {
					result = RunType.GDELSEIF;
				} else if (code.startsWith(RunType.GDELSE.getValue())) {
					result = RunType.GDELSE;
				} else if (code.startsWith(RunType.GDENDIF.getValue())) {
					result = RunType.GDENDIF;
				} else if (code.startsWith(RunType.GDTABLE.getValue())) {
					result = RunType.GDTABLE;
				} else if (code.startsWith(RunType.ELT.getValue())) {
					result = RunType.ELT;
				} else if (code.startsWith(RunType.VAR.getValue())) {
					result = RunType.VAR;
				} else if (code.startsWith(RunType.GDLET.getValue())) {
					result = RunType.GDLET;
				} else if (code.startsWith(RunType.GDENDLET.getValue())) {
					result = RunType.GDENDLET;
				} else {
					result = RunType.STATIC;
				}
			} else {
				result = RunType.STATIC;
			}
		} else {
			result = RunType.STATIC;
		}
		return result;

	}

	/**
	 * Returns the template contained in the document.
	 * 
	 * @return the parsed templte.
	 * @throws DocumentParserException
	 *             if a syntax problem is detected during parsing.
	 */
	public Template parseTemplate() throws DocumentParserException {
		Template template = (Template) EcoreUtil.create(TemplatePackage.Literals.TEMPLATE);
		template.setDocument(this.document);
		parseCompound(template, RunType.EOF);
		return template;
	}

	private void parseCompound(Compound compound, RunType endType) throws DocumentParserException {
		RunType type = getNextRunType();
		while (type != endType) {
			switch (type) {
			case AQL:
				compound.getSubConstructs().add(parseQuery());
				break;
			case GDFOR:
				compound.getSubConstructs().add(parseRepetition());
				break;
			case GDIF:
				compound.getSubConstructs().add(parseConditionnal());
				break;
			case GDELSEIF:
			case GDELSE:
			case GDENDFOR:
			case GDENDIF:
			case GDENDLET:
				// XXX: this approach of sending an exception does not allow to
				// pursue parsing and catch on errors. There, for instance, we
				// could seek an endif tag and ignore anything between here and
				// the tag.
				throw new DocumentParserException(message(DocumentParserError.UNEXPECTEDTAG, RunType.GDELSEIF),
						runIterator.lookAhead(1));
			case ELT:
				compound.getSubConstructs().add(parseELT());
				break;
			case GDLET:
				compound.getSubConstructs().add(parseLet());
				break;
			case GDIMAGE:
				compound.getSubConstructs().add(parseImage());
				break;
			case STATIC:
				compound.getSubConstructs().add(parseStaticFragment());
				break;
			case GDTABLE:
				compound.getSubConstructs().add(parseTable());
				break;
			case VAR:
				compound.getSubConstructs().add(parseVar());
				break;
			}
			type = getNextRunType();
		}

	}

	/**
	 * Reads up a tag so that it can be parsed as a simple string.
	 * 
	 * @return the string present into the tag as typed by the template author.
	 */
	String readTag(AbstractConstruct construct, List<XWPFRun> runsToFill) {
		XWPFRun run = this.runIterator.lookAhead(1);
		XWPFRun styleRun = null;
		boolean columnRead = false;
		if (isFieldBegin(run)) {
			runsToFill.add(runIterator.next());// consumme begin field
			boolean finished = false;
			StringBuilder builder = new StringBuilder();
			while (runIterator.hasNext() && !finished) {
				run = runIterator.next();
				runsToFill.add(run);
				if (isFieldEnd(run)) {
					finished = true;
				} else {
					List<CTText> texts = run.getCTR().getInstrTextList();
					StringBuilder runBuilder = new StringBuilder();
					for (CTText text : texts) {
						runBuilder.append(text.getStringValue());
					}
					String runText = runBuilder.toString();
					builder.append(runText);
					// the style run hasn't been discovered yet.
					if (styleRun == null) {
						if (columnRead && !"".equals(runText)) {
							styleRun = run;
							construct.setStyleRun(styleRun);
						} else {
							int indexOfColumn = runText.indexOf(':');
							if (indexOfColumn >= 0) {
								columnRead = true;
								if (indexOfColumn < runText.length() - 1) {
									styleRun = run;// ':' doesn't appear at the
									construct.setStyleRun(styleRun);
									// end of the string
								} // otherwise, use the next non empty run.
							}
						}
					}
				}
			}
			return builder.toString();
		} else {
			throw new IllegalStateException("Shouldn't call readTag if the current run doesn't start a field");
		}
	}

	/**
	 * Parses a tag formed like "{var:varname}" Parser is positionned on the
	 * first run of the field
	 * 
	 * @return
	 */
	private VarRef parseVar() {
		VarRef result = (VarRef) EcoreUtil.create(TemplatePackage.Literals.VAR_REF);
		String varName = readTag(result, result.getRuns()).trim().substring(RunType.VAR.getValue().length());
		result.setVarName(varName);
		return result;
	}

	private AbstractConstruct parseELT() {
		// TODO Auto-generated method stub
		return null;
	}

	private AbstractConstruct parseLet() {
		// TODO Auto-generated method stub
		return null;
	}

	private Query parseQuery() throws DocumentParserException {
		Query query = (Query) EcoreUtil.create(TemplatePackage.Literals.QUERY);
		String queryText = readTag(query, query.getRuns()).trim().substring(RunType.AQL.getValue().length());
		int tagLength = queryText.length();
		if (queryText.endsWith(LABEL_MODIFIER)) {
			queryText = queryText.substring(0, tagLength - LABEL_MODIFIER.length());
			query.setBehavior(QueryBehavior.LABEL);
		} else if (queryText.endsWith(ICON_MODIFIER)) {
			queryText = queryText.substring(0, tagLength - ICON_MODIFIER.length());
			query.setBehavior(QueryBehavior.ICON);
		} else if (queryText.endsWith(TEXT_MODIFIER)) {
			queryText = queryText.substring(0, tagLength - TEXT_MODIFIER.length());
		}
		AstResult result = queryParser.build(queryText.trim());
		if (result.getErrors().size() == 0) {
			query.setQuery(result);
		} else {
			throw new DocumentParserException("Query parsing error : ", query.getStyleRun());
		}
		return query;
	}

	private Image parseImage() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

	private StaticFragment parseStaticFragment() throws DocumentParserException {
		StaticFragment result = (StaticFragment) EcoreUtil.create(TemplatePackage.Literals.STATIC_FRAGMENT);
		XWPFParagraph currentParagraph = (XWPFParagraph) runIterator.lookAhead(1).getParent();
		// we cut static fragments at paragraph frontiers to ease the processing
		// of templates.
		while (getNextRunType() == RunType.STATIC && runIterator.lookAhead(1).getParent() == currentParagraph) {
			result.getRuns().add(runIterator.next());
		}
		return result;
	}

	private Representation parseRepresentation() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

	private Conditionnal parseConditionnal() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

	/**
	 * Parse a construct of the form
	 * <code>{gd:for var | query} runs {gd:endfor}</code>
	 * 
	 * @return
	 * @throws DocumentParserException
	 */
	private Repetition parseRepetition() throws DocumentParserException {
		// first read the tag that opens the repetition
		Repetition repetition = (Repetition) EcoreUtil.create(TemplatePackage.Literals.REPETITION);
		String tagText = readTag(repetition, repetition.getRuns()).trim();
		// remove the prefix
		tagText = tagText.substring(RunType.GDFOR.getValue().length());
		// extract the variable;
		int indexOfPipe = tagText.indexOf('|');
		if (indexOfPipe < 0) {
			throw new DocumentParserException("Malformed tag gd:for, no '|' found :" + tagText);
		}
		String iterationVariable = tagText.substring(0, indexOfPipe).trim();
		if ("".equals(iterationVariable)) {
			throw new DocumentParserException("Malformed tag gd:for : no iteration variable specified.");
		}
		repetition.setIterationVar(iterationVariable);
		if (tagText.length() == indexOfPipe + 1) {
			throw new DocumentParserException("Malformed tag gd:for : no query expression specified.");
		}
		String query = tagText.substring(indexOfPipe + 1, tagText.length()).trim();
		AstResult result = queryParser.build(query);
		if (result.getErrors().size() == 0) {
			repetition.setQuery(result);
		} else {
			throw new DocumentParserException("Query parsing error : ", repetition.getStyleRun());
		}
		repetition.setQuery(result);
		// read up the tags until the "gd:endfor" tag is encountered.
		parseCompound(repetition, RunType.GDENDFOR);
		readTag(repetition, repetition.getClosingRuns());
		return repetition;
	}

	private Table parseTable() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

}
