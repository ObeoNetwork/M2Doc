package org.eclipse.gendoc2.parser;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Compound;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Default;
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

import com.google.common.collect.Lists;

/**
 * DocumentParser reads a {@link XWPFDocument} and produces a abstract syntax
 * tree that represents the template in the document.
 * 
 * 
 * @author Romain Guider
 *
 */
public class BodyParser {

	private static final String LABEL_MODIFIER = " label";
	private static final String ICON_MODIFIER = " icon";
	private static final String TEXT_MODIFIER = " text";

	private Map<XWPFRun, ParsingErrorMessage> parsingErrors = new HashMap<XWPFRun, ParsingErrorMessage>();
	/**
	 * Parsed template document.
	 */
	private IBody document;
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
	public BodyParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
		this.document = inputDocument;
		runIterator = new RunProvider(inputDocument);
		this.queryParser = new QueryBuilderEngine(queryEnvironment);
	}

	private String message(ParsingErrorMessage error, Object... objects) {
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
		XWPFRun run = runIterator.lookAhead(1);
		RunType result;
		if (run == null) {
			return RunType.EOF;
		}
		// is run a field begin run
		if (isFieldBegin(run)) {
			String code = lookAheadTag();
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
		return result;
	}

	/**
	 * Returns the template contained in the document.
	 * 
	 * @return the parsed template.
	 * @throws DocumentParserException
	 *             if a syntax problem is detected during parsing.
	 */
	public Template parseTemplate() throws DocumentParserException {
		Template template = (Template) EcoreUtil.create(TemplatePackage.Literals.TEMPLATE);
		template.setDocument(this.document);
		parseCompound(template, RunType.EOF);
		return template;
	}

	private void parseCompound(Compound compound, RunType... endTypes) throws DocumentParserException {
		RunType type = getNextRunType();
		List<RunType> endTypeList = Lists.newArrayList(endTypes);
		while (!endTypeList.contains(type)) {
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
				// report the error and ignore the problem so that parsing
				// continues in other parts of the document.
				compound.getParsingErrors().add(new DocumentParsingError(
						message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), runIterator.lookAhead(1)));
				String tag = readTag(compound, compound.getRuns());
				break;
			case EOF:
				compound.getParsingErrors()
						.add(new DocumentParsingError(message(ParsingErrorMessage.UNEXPECTEDTAG, type), null));
				return;
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
	 * read up a tag looking ahead the runs so that a prediction can be made
	 * over the nature of a field.
	 * <p>
	 * Using such a method is mandatory because for some reasons fields like
	 * {gd:if ...} can be broken up in an unexpected number of runs thus
	 * precluding the tag nature prediction based on the first run only.
	 * </p>
	 * 
	 * @return the complete text of the current field.
	 */
	private String lookAheadTag() {
		int i = 1;
		// first run must begin a field.
		XWPFRun run = this.runIterator.lookAhead(1);
		if (isFieldBegin(run)) {
			StringBuilder builder = new StringBuilder();
			i++;
			run = this.runIterator.lookAhead(i);
			while (run != null && !isFieldEnd(run)) {
				builder.append(readUpInstrText(run));
				run = this.runIterator.lookAhead(++i);
			}
			return builder.toString().trim();
		} else {
			return "";
		}
	}

	private StringBuilder readUpInstrText(XWPFRun run) {
		List<CTText> texts = run.getCTR().getInstrTextList();
		StringBuilder runBuilder = new StringBuilder();
		for (CTText text : texts) {
			runBuilder.append(text.getStringValue());
		}
		return runBuilder;
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
		if (run != null && isFieldBegin(run)) {
			runsToFill.add(runIterator.next());// consumme begin field
			boolean finished = false;
			StringBuilder builder = new StringBuilder();
			while (runIterator.hasNext() && !finished) {
				run = runIterator.next();
				runsToFill.add(run);
				if (isFieldEnd(run)) {
					finished = true;
				} else {
					String runText = readUpInstrText(run).toString();
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
		if ("".equals(varName)) {
			result.getParsingErrors()
					.add(new DocumentParsingError(message(ParsingErrorMessage.NOVARDEFINED), result.getRuns().get(1)));
		}
		result.setVarName(varName);
		return result;
	}

	private AbstractConstruct parseELT() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemented yet");
	}

	private AbstractConstruct parseLet() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemented yet");
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
		queryText = queryText.trim();
		AstResult result = queryParser.build(queryText.trim());
		if (result.getErrors().size() == 0) {
			query.setQuery(result);
		} else {
			query.getParsingErrors().add(
					new DocumentParsingError(message(ParsingErrorMessage.INVALIDEXPR, queryText), query.getStyleRun()));
		}
		return query;
	}

	private Image parseImage() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

	private StaticFragment parseStaticFragment() throws DocumentParserException {
		StaticFragment result = (StaticFragment) EcoreUtil.create(TemplatePackage.Literals.STATIC_FRAGMENT);
		while (getNextRunType() == RunType.STATIC) {
			result.getRuns().add(runIterator.next());
		}
		return result;
	}

	private Representation parseRepresentation() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

	/**
	 * conditionnal are made of the following set of tags : {gd:if "query"} ...
	 * [{gd:elseif "query"} ....]* ({gd:else})? ... {gd:endif}
	 * 
	 * @return
	 * @throws DocumentParserException
	 */
	private Conditionnal parseConditionnal() throws DocumentParserException {
		Conditionnal conditionnal = (Conditionnal) EcoreUtil.create(TemplatePackage.Literals.CONDITIONNAL);
		String tag = readTag(conditionnal, conditionnal.getRuns()).trim();
		boolean headConditionnal = tag.startsWith(RunType.GDIF.getValue());
		int tagLength = headConditionnal ? RunType.GDIF.getValue().length() : RunType.GDELSEIF.getValue().length();
		String query = tag.substring(tagLength).trim();
		AstResult result = queryParser.build(query);
		if (result.getErrors().size() == 0) {
			conditionnal.setQuery(result);
		} else {
			conditionnal.getParsingErrors().add(new DocumentParsingError(
					message(ParsingErrorMessage.INVALIDEXPR, query), conditionnal.getRuns().get(1)));
		}
		parseCompound(conditionnal, RunType.GDELSEIF, RunType.GDELSE, RunType.GDENDIF);
		RunType nextRunType = getNextRunType();
		switch (nextRunType) {
		case GDELSEIF:
			conditionnal.setAlternative(parseConditionnal());
			break;
		case GDELSE:
			Default defaultCompound = (Default) EcoreUtil.create(TemplatePackage.Literals.DEFAULT);
			readTag(defaultCompound, defaultCompound.getRuns());
			parseCompound(defaultCompound, RunType.GDENDIF);
			conditionnal.setElse(defaultCompound);

			// read up the gd:endif tag if it exists
			if (getNextRunType() != RunType.EOF) {
				readTag(conditionnal, conditionnal.getClosingRuns());
			}
			break;
		case GDENDIF:
			readTag(conditionnal, conditionnal.getClosingRuns());
			break;// we just finish the current conditionnal.
		default:
			conditionnal.getParsingErrors().add(new DocumentParsingError(message(ParsingErrorMessage.CONDTAGEXPEXTED),
					conditionnal.getRuns().get(1)));
		}
		return conditionnal;
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
			repetition.getParsingErrors()
					.add(new DocumentParsingError("Malformed tag gd:for, no '|' found.", repetition.getRuns().get(1)));
		} else {
			String iterationVariable = tagText.substring(0, indexOfPipe).trim();
			if ("".equals(iterationVariable)) {
				repetition.getParsingErrors().add(new DocumentParsingError(
						"Malformed tag gd:for : no iteration variable specified.", repetition.getRuns().get(1)));
			}
			repetition.setIterationVar(iterationVariable);
			if (tagText.length() == indexOfPipe + 1) {
				repetition.getParsingErrors()
						.add(new DocumentParsingError("Malformed tag gd:for : no query expression specified." + tagText,
								repetition.getRuns().get(1)));
			}
			String query = tagText.substring(indexOfPipe + 1, tagText.length()).trim();
			AstResult result = queryParser.build(query);
			if (result.getErrors().size() == 0) {
				repetition.setQuery(result);
			} else {
				// TODO : build an error message that contains the query parsing
				// message.
				repetition.getParsingErrors().add(new DocumentParsingError(
						message(ParsingErrorMessage.INVALIDEXPR, query), repetition.getRuns().get(1)));
			}
			repetition.setQuery(result);
		}
		// read up the tags until the "gd:endfor" tag is encountered.
		parseCompound(repetition, RunType.GDENDFOR);
		if (getNextRunType() != RunType.EOF) {
			readTag(repetition, repetition.getClosingRuns());
		}
		return repetition;
	}

	private Table parseTable() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

}
