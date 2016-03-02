package org.eclipse.gendoc2.parser;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Cell;
import org.eclipse.gendoc2.template.Compound;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Default;
import org.eclipse.gendoc2.template.Image;
import org.eclipse.gendoc2.template.POSITION;
import org.eclipse.gendoc2.template.Query;
import org.eclipse.gendoc2.template.QueryBehavior;
import org.eclipse.gendoc2.template.Repetition;
import org.eclipse.gendoc2.template.Representation;
import org.eclipse.gendoc2.template.Row;
import org.eclipse.gendoc2.template.StaticFragment;
import org.eclipse.gendoc2.template.Table;
import org.eclipse.gendoc2.template.TableMerge;
import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.TemplatePackage;
import org.eclipse.gendoc2.template.VarRef;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

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
	private static final String IMAGE_FILE_NAME_KEY = "file";
	private static final String IMAGE_HEIGHT_KEY = "height";
	private static final String IMAGE_WIDTH_KEY = "width";
	private static final String IMAGE_LEGEND_KEY = "legend";
	private static final String IMAGE_LEGEND_POSITION = "legendPos";
	private static final String IMAGE_LEGEND_ABOVE = "above";
	private static final String IMAGE_LEGEND_BELOW = "below";
	private static final String[] IMAGE_OPTION_SET =

	{ IMAGE_FILE_NAME_KEY, IMAGE_HEIGHT_KEY, IMAGE_LEGEND_KEY, IMAGE_LEGEND_POSITION, IMAGE_WIDTH_KEY };

	/**
	 * Regex that allows to parse image options.
	 */
	private static final Pattern IMAGE_OPTIONS_PATTERN = Pattern.compile("(([a..zA..Z])\\s*:\\s*'([^']*)')+");

	/**
	 * Parsed template document.
	 */
	private IBody document;
	/**
	 * iterator over the document used to access {@link XWPFRun} instances in
	 * sequence.
	 */
	private TokenProvider runIterator;
	/**
	 * {@link IQueryBuilderEngine} used to parse AQL queries.
	 */
	private IQueryBuilderEngine queryParser;

	@SuppressWarnings("restriction")
	public BodyParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
		this.document = inputDocument;
		runIterator = new TokenProvider(inputDocument);
		this.queryParser = new QueryBuilderEngine(queryEnvironment);
	}

	@SuppressWarnings("restriction")
	BodyParser(IBody inputDocument, IQueryBuilderEngine queryParser) {
		this.document = inputDocument;
		runIterator = new TokenProvider(inputDocument);
		this.queryParser = queryParser;
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

	private TokenType getNextTokenType() {
		ParsingToken token = runIterator.lookAhead(1);
		if (token == null) {
			return TokenType.EOF;
		} else if (token.getKind() == ParsingTokenKind.TABLE) {
			return TokenType.WTABLE;
		} else {
			XWPFRun run = token.getRun();
			TokenType result;
			// is run a field begin run
			if (isFieldBegin(run)) {
				String code = lookAheadTag();
				if (code.startsWith(TokenType.AQL.getValue())) {
					result = TokenType.AQL;
				} else if (code.startsWith(TokenType.GDFOR.getValue())) {
					result = TokenType.GDFOR;
				} else if (code.startsWith(TokenType.GDENDFOR.getValue())) {
					result = TokenType.GDENDFOR;
				} else if (code.startsWith(TokenType.GDIF.getValue())) {
					result = TokenType.GDIF;
				} else if (code.startsWith(TokenType.GDELSEIF.getValue())) {
					result = TokenType.GDELSEIF;
				} else if (code.startsWith(TokenType.GDELSE.getValue())) {
					result = TokenType.GDELSE;
				} else if (code.startsWith(TokenType.GDENDIF.getValue())) {
					result = TokenType.GDENDIF;
				} else if (code.startsWith(TokenType.GDTABLE.getValue())) {
					result = TokenType.GDTABLE;
				} else if (code.startsWith(TokenType.ELT.getValue())) {
					result = TokenType.ELT;
				} else if (code.startsWith(TokenType.VAR.getValue())) {
					result = TokenType.VAR;
				} else if (code.startsWith(TokenType.GDLET.getValue())) {
					result = TokenType.GDLET;
				} else if (code.startsWith(TokenType.GDENDLET.getValue())) {
					result = TokenType.GDENDLET;
				} else {
					result = TokenType.STATIC;
				}
			} else {
				result = TokenType.STATIC;
			}
			return result;
		}
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
		parseCompound(template, TokenType.EOF);
		return template;
	}

	private void parseCompound(Compound compound, TokenType... endTypes) throws DocumentParserException {
		TokenType type = getNextTokenType();
		List<TokenType> endTypeList = Lists.newArrayList(endTypes);
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
				XWPFRun run = runIterator.lookAhead(1).getRun();
				if (run == null) {
					throw new IllegalStateException(
							"Token of type " + type + " detected. Run shouldn't be null at this place.");
				}
				compound.getParsingErrors().add(
						new DocumentParsingError(message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
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
				compound.getSubConstructs().add(parseTableMerge());
				break;
			case VAR:
				compound.getSubConstructs().add(parseVar());
				break;
			case WTABLE:
				compound.getSubConstructs().add(parseTable(runIterator.next().getTable()));
				break;

			}
			type = getNextTokenType();
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
		XWPFRun run = this.runIterator.lookAhead(1).getRun();
		if (run == null) {
			throw new IllegalStateException("lookAheadTag shouldn't be called on a table.");
		}
		if (isFieldBegin(run)) {
			StringBuilder builder = new StringBuilder();
			i++;
			run = this.runIterator.lookAhead(i).getRun();
			// run is null when EOF is reached or a table is encountered.
			while (run != null && !isFieldEnd(run)) {
				builder.append(readUpInstrText(run));
				run = this.runIterator.lookAhead(++i).getRun();
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
		XWPFRun run = this.runIterator.lookAhead(1).getRun();
		if (run == null) {
			throw new IllegalStateException("readTag shouldn't be called with a table in the lookahead window.");
		}
		XWPFRun styleRun = null;
		boolean columnRead = false;
		if (run != null && isFieldBegin(run)) {
			runsToFill.add(runIterator.next().getRun());// consumme begin field
			boolean finished = false;
			StringBuilder builder = new StringBuilder();
			while (runIterator.hasNext() && !finished) {
				run = runIterator.next().getRun();
				if (run == null) {
					// XXX : treat this as a proper parsing error.
					throw new IllegalArgumentException("table cannot be inserted into tags.");
				}
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
		String varName = readTag(result, result.getRuns()).trim().substring(TokenType.VAR.getValue().length());
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
		String queryText = readTag(query, query.getRuns()).trim().substring(TokenType.AQL.getValue().length());
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

	Map<String, String> parseImageOptions(String tag) {
		Map<String, String> result = new HashMap<String, String>();
		Matcher matcher = IMAGE_OPTIONS_PATTERN.matcher(tag);
		if (matcher.find()) {
			for (int i = 0; i < matcher.groupCount() / 2; i += 2) {
				result.put(matcher.group(i), matcher.group(i + 1));
			}
		}
		return result;
	}

	/**
	 * Check that all options are known options.
	 * 
	 * @param options
	 *            the option map
	 * @param image
	 *            the image
	 */
	private void checkOptions(Map<String, String> options, Image image) {
		Set optionSet = Sets.newHashSet(IMAGE_OPTION_SET);
		for (String key : options.keySet()) {
			if (!optionSet.contains(key)) {
				image.getParsingErrors()
						.add(new DocumentParsingError(
								message(ParsingErrorMessage.INVALID_IMAGE_OPTION, key, "unknown option name"),
								image.getRuns().get(1)));
			} else if (IMAGE_LEGEND_POSITION.equals(key)) {
				String value = options.get(key);
				if (!IMAGE_LEGEND_ABOVE.equals(value) && !IMAGE_LEGEND_BELOW.equals(value)) {
					image.getParsingErrors()
							.add(new DocumentParsingError(message(ParsingErrorMessage.INVALID_IMAGE_OPTION, key,
									"unknown option value (" + value + ")."), image.getRuns().get(1)));
				}
			}
		}
	}

	private Image parseImage() throws DocumentParserException {
		Image image = (Image) EcoreUtil.create(TemplatePackage.Literals.IMAGE);
		Map<String, String> options = parseImageOptions(readTag(image, image.getRuns()));
		checkOptions(options, image);
		if (!options.containsKey(IMAGE_FILE_NAME_KEY)) {
			image.getParsingErrors().add(
					new DocumentParsingError(message(ParsingErrorMessage.INVALID_IMAGE_TAG), image.getRuns().get(1)));
		} else {
			image.setFileName(options.get(IMAGE_FILE_NAME_KEY));
			if (options.containsKey(IMAGE_LEGEND_KEY)) {
				image.setLegend(options.get(IMAGE_LEGEND_KEY));
			}
			if (options.containsKey(IMAGE_HEIGHT_KEY)) {
				String heightStr = options.get(IMAGE_HEIGHT_KEY);
				try {
					image.setHeight(Integer.parseInt(options.get(IMAGE_HEIGHT_KEY)));
				} catch (NumberFormatException e) {
					image.getParsingErrors()
							.add(new DocumentParsingError(
									message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_HEIGHT_KEY, heightStr),
									image.getRuns().get(1)));
				}
			}
			if (options.containsKey(IMAGE_WIDTH_KEY)) {
				String heightStr = options.get(IMAGE_WIDTH_KEY);
				try {
					image.setHeight(Integer.parseInt(options.get(IMAGE_WIDTH_KEY)));
				} catch (NumberFormatException e) {
					image.getParsingErrors()
							.add(new DocumentParsingError(
									message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_WIDTH_KEY, heightStr),
									image.getRuns().get(1)));
				}
			}
			if (options.containsKey(IMAGE_LEGEND_POSITION)) {
				String value = options.get(IMAGE_LEGEND_POSITION);
				if (IMAGE_LEGEND_ABOVE.equals(value)) {
					image.setLegendPOS(POSITION.ABOVE);
				} else {// put the legend below by default.
					image.setLegendPOS(POSITION.BELOW);
				}
			}
		}
		return image;
	}

	private StaticFragment parseStaticFragment() throws DocumentParserException {
		StaticFragment result = (StaticFragment) EcoreUtil.create(TemplatePackage.Literals.STATIC_FRAGMENT);
		while (getNextTokenType() == TokenType.STATIC) {
			result.getRuns().add(runIterator.next().getRun());
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
		boolean headConditionnal = tag.startsWith(TokenType.GDIF.getValue());
		int tagLength = headConditionnal ? TokenType.GDIF.getValue().length() : TokenType.GDELSEIF.getValue().length();
		String query = tag.substring(tagLength).trim();
		AstResult result = queryParser.build(query);
		if (result.getErrors().size() == 0) {
			conditionnal.setQuery(result);
		} else {
			conditionnal.getParsingErrors().add(new DocumentParsingError(
					message(ParsingErrorMessage.INVALIDEXPR, query), conditionnal.getRuns().get(1)));
		}
		parseCompound(conditionnal, TokenType.GDELSEIF, TokenType.GDELSE, TokenType.GDENDIF);
		TokenType nextRunType = getNextTokenType();
		switch (nextRunType) {
		case GDELSEIF:
			conditionnal.setAlternative(parseConditionnal());
			break;
		case GDELSE:
			Default defaultCompound = (Default) EcoreUtil.create(TemplatePackage.Literals.DEFAULT);
			readTag(defaultCompound, defaultCompound.getRuns());
			parseCompound(defaultCompound, TokenType.GDENDIF);
			conditionnal.setElse(defaultCompound);

			// read up the gd:endif tag if it exists
			if (getNextTokenType() != TokenType.EOF) {
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
		tagText = tagText.substring(TokenType.GDFOR.getValue().length());
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
		parseCompound(repetition, TokenType.GDENDFOR);
		if (getNextTokenType() != TokenType.EOF) {
			readTag(repetition, repetition.getClosingRuns());
		}
		return repetition;
	}

	private TableMerge parseTableMerge() throws DocumentParserException {
		throw new UnsupportedOperationException("not implemented yet");
	}

	private Table parseTable(XWPFTable wtable) throws DocumentParserException {
		if (wtable == null) {
			throw new IllegalArgumentException("parseTable can't be called on a null argument.");
		}
		Table table = (Table) EcoreUtil.create(TemplatePackage.Literals.TABLE);
		table.setTable(wtable);
		for (XWPFTableRow tablerow : wtable.getRows()) {
			Row row = (Row) EcoreUtil.create(TemplatePackage.Literals.ROW);
			table.getRows().add(row);
			row.setTableRow(tablerow);
			for (XWPFTableCell tableCell : tablerow.getTableCells()) {
				Cell cell = (Cell) EcoreUtil.create(TemplatePackage.Literals.CELL);
				row.getCells().add(cell);
				cell.setTableCell(tableCell);
				BodyParser parser = new BodyParser(tableCell, this.queryParser);
				cell.setTemplate(parser.parseTemplate());
			}
		}
		return table;
	}
}
