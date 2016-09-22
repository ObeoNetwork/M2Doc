/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.parser;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.CommonTokenFactory;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.UnbufferedTokenStream;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.ast.AstPackage;
import org.eclipse.acceleo.query.ast.ErrorExpression;
import org.eclipse.acceleo.query.parser.AstBuilderListener;
import org.eclipse.acceleo.query.parser.QueryLexer;
import org.eclipse.acceleo.query.parser.QueryParser;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractImage;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Compound;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.POSITION;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.QueryBehavior;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

/**
 * DocumentParser reads a {@link XWPFDocument} and produces a abstract syntax
 * tree that represents the template in the document.
 * The parsing algorithm is an LL(k) like algorithm that uses look ahead to predict parsing decisions. Tokens are only read from fields so
 * that there's no ambiguity with the rest of the text.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public class BodyParser {

    /**
     * Label modifier constant.
     */
    private static final String LABEL_MODIFIER = " label";
    /**
     * Icon modifier constant.
     */
    private static final String ICON_MODIFIER = " icon";
    /**
     * text modifier constant.
     */
    private static final String TEXT_MODIFIER = " text";
    /**
     * Image file name option name.
     */
    private static final String IMAGE_FILE_NAME_KEY = "file";
    /**
     * Diagram provider option name.
     */
    private static final String DIAGRAM_PROVIDER_KEY = "provider";
    /**
     * Diagram layers option name.
     */
    private static final String DIAGRAM_LAYERS_KEY = "layers";
    /**
     * Image height option name.
     */
    private static final String IMAGE_HEIGHT_KEY = "height";
    /**
     * Image width option name.
     */
    private static final String IMAGE_WIDTH_KEY = "width";
    /**
     * Image legend option name.
     */
    private static final String IMAGE_LEGEND_KEY = "legend";
    /**
     * Image legend position option name.
     */
    private static final String IMAGE_LEGEND_POSITION = "legendPos";
    /**
     * Image legend position above value's constant.
     */
    private static final String IMAGE_LEGEND_ABOVE = "above";
    /**
     * Image legend position above value's constant.
     */
    private static final String IMAGE_LEGEND_BELOW = "below";
    /**
     * Array of image's options name constants.
     */
    private static final String[] IMAGE_OPTION_SET =

    {IMAGE_FILE_NAME_KEY, IMAGE_HEIGHT_KEY, IMAGE_LEGEND_KEY, IMAGE_LEGEND_POSITION, IMAGE_WIDTH_KEY };
    /**
     * Array of representation's options name constants.
     */
    private static final String[] DIAGRAM_OPTION_SET =

    {DIAGRAM_PROVIDER_KEY, IMAGE_HEIGHT_KEY, IMAGE_LEGEND_KEY, IMAGE_LEGEND_POSITION, IMAGE_WIDTH_KEY,
        DIAGRAM_LAYERS_KEY, };

    /**
     * Rank of the option's value group in the matcher.
     */
    private static final int OPTION_VAL_GROUP_RANK = 2;
    /**
     * Rank of the option's name group in the matcher.
     */
    private static final int OPTION_GROUP_RANK = 1;
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

    /**
     * The {@link IQueryEnvironment}.
     */
    private final IQueryEnvironment queryEnvironment;

    /**
     * Creates a new {@link BodyParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryEnvironment
     *            the query environment to used during parsing.
     */
    public BodyParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = new QueryBuilderEngine(queryEnvironment);
        this.queryEnvironment = queryEnvironment;
    }

    /**
     * Creates a new {@link BodyParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryParser
     *            the query parser to use during parsing
     * @param queryEnvironment
     *            The {@link IQueryEnvironment}
     */
    BodyParser(IBody inputDocument, IQueryBuilderEngine queryParser, IQueryEnvironment queryEnvironment) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = queryParser;
        this.queryEnvironment = queryEnvironment;
    }

    /**
     * Creates an error message.
     * 
     * @param message
     *            the error to create a message from
     * @param objects
     *            the list of the message arguments
     * @return the formated error message
     */
    private String message(ParsingErrorMessage message, Object... objects) {
        return MessageFormat.format(message.getMessage(), objects);
    }

    /**
     * Returns <code>true</code> when the specified run is a field begin run and <code>false</code> otherwise.
     * 
     * @param run
     *            the concerned run
     * @return <code>true</code> for field begin.
     */
    private boolean isFieldBegin(XWPFRun run) {
        if (run.getCTR().getFldCharList().size() > 0) {
            CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
            return STFldCharType.BEGIN.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * Returns <code>true</code> when the specified run is a field end run and <code>false</code> otherwise.
     * 
     * @param run
     *            the concerned run
     * @return <code>true</code> for field end.
     */

    private boolean isFieldEnd(XWPFRun run) {
        if (run.getCTR().getFldCharList().size() > 0) {
            CTFldChar fldChar = run.getCTR().getFldCharList().get(0);
            return STFldCharType.END.equals(fldChar.getFldCharType());
        } else {
            return false;
        }
    }

    /**
     * returns the next token type.
     * 
     * @return the next token type.
     */
    private TokenType getNextTokenType() {
        ParsingToken token = runIterator.lookAhead(1);
        TokenType result;
        if (token == null) {
            result = TokenType.EOF;
        } else if (token.getKind() == ParsingTokenKind.TABLE) {
            result = TokenType.WTABLE;
        } else {
            XWPFRun run = token.getRun();
            // is run a field begin run
            if (isFieldBegin(run)) {
                String code = lookAheadTag();
                if (code.startsWith(TokenType.FOR.getValue())) {
                    result = TokenType.FOR;
                } else if (code.startsWith(TokenType.ENDFOR.getValue())) {
                    result = TokenType.ENDFOR;
                } else if (code.startsWith(TokenType.IF.getValue())) {
                    result = TokenType.IF;
                } else if (code.startsWith(TokenType.ELSEIF.getValue())) {
                    result = TokenType.ELSEIF;
                } else if (code.startsWith(TokenType.ELSE.getValue())) {
                    result = TokenType.ELSE;
                } else if (code.startsWith(TokenType.ENDIF.getValue())) {
                    result = TokenType.ENDIF;
                } else if (code.startsWith(TokenType.ELT.getValue())) {
                    result = TokenType.ELT;
                } else if (code.startsWith(TokenType.LET.getValue())) {
                    result = TokenType.LET;
                } else if (code.startsWith(TokenType.ENDLET.getValue())) {
                    result = TokenType.ENDLET;
                } else if (code.startsWith(TokenType.IMAGE.getValue())) {
                    result = TokenType.IMAGE;
                } else if (code.startsWith(TokenType.DIAGRAM.getValue())) {
                    result = TokenType.DIAGRAM;
                } else if (code.startsWith(TokenType.BOOKMARK.getValue())) {
                    result = TokenType.BOOKMARK;
                } else if (code.startsWith(TokenType.ENDBOOKMARK.getValue())) {
                    result = TokenType.ENDBOOKMARK;
                } else if (code.startsWith(TokenType.LINK.getValue())) {
                    result = TokenType.LINK;
                } else if (code.startsWith(TokenType.AQL.getValue())) {
                    result = TokenType.AQL;
                } else {
                    result = TokenType.STATIC;
                }
            } else {
                result = TokenType.STATIC;
            }
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
        template.setBody(this.document);
        parseCompound(template, TokenType.EOF);
        return template;
    }

    /**
     * Parses a compound object.
     * 
     * @param compound
     *            the compound to parse
     * @param endTypes
     *            the token types that mark the end of the parsed compound
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    private void parseCompound(Compound compound, TokenType... endTypes) throws DocumentParserException {
        TokenType type = getNextTokenType();
        List<TokenType> endTypeList = Lists.newArrayList(endTypes);
        while (!endTypeList.contains(type)) {
            switch (type) {
                case AQL:
                    compound.getSubConstructs().add(parseQuery());
                    break;
                case FOR:
                    compound.getSubConstructs().add(parseRepetition());
                    break;
                case IF:
                    compound.getSubConstructs().add(parseConditionnal());
                    break;
                case ELSEIF:
                case ELSE:
                case ENDFOR:
                case ENDIF:
                case ENDLET:
                case ENDBOOKMARK:
                    // report the error and ignore the problem so that parsing
                    // continues in other parts of the document.
                    XWPFRun run = runIterator.lookAhead(1).getRun();
                    if (run == null) {
                        throw new IllegalStateException(
                                "Token of type " + type + " detected. Run shouldn't be null at this place.");
                    }
                    compound.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
                    readTag(compound, compound.getRuns());
                    break;
                case EOF:
                    final XWPFParagraph lastParagraph = document.getParagraphs()
                            .get(document.getParagraphs().size() - 1);
                    final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
                    compound.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            message(ParsingErrorMessage.UNEXPECTEDTAG, type), lastRun));
                    return;
                case LET:
                    compound.getSubConstructs().add(parseLet());
                    break;
                case IMAGE:
                    compound.getSubConstructs().add(parseImage());
                    break;
                case STATIC:
                    compound.getSubConstructs().add(parseStaticFragment());
                    break;
                case BOOKMARK:
                    compound.getSubConstructs().add(parseBookmark());
                    break;
                case LINK:
                    compound.getSubConstructs().add(parseLink());
                    break;
                case WTABLE:
                    compound.getSubConstructs().add(parseTable(runIterator.next().getTable()));
                    break;
                case DIAGRAM:
                    compound.getSubConstructs().add(parseRepresentation()); // XXX : change representation into diagram in the template
                                                                            // model.
                    break;
                default:
                    throw new UnsupportedOperationException("shouldn't reach here");
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

    /**
     * reads up the instruction of a field's run.
     * 
     * @param run
     *            the run to read.
     * @return the aggregated instruction text of the run
     */
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
     * @param construct
     *            the construct to read tag to
     * @param runsToFill
     *            the run list to fill
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
            runsToFill.add(runIterator.next().getRun()); // Consume begin field
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
                    // CHECKSTYLE:OFF
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

    // CHECKSTYLE:ON

    /**
     * Parses a let construct.
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    private AbstractConstruct parseLet() throws DocumentParserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("not implemented yet");
    }

    /**
     * Parses a query construct.
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
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
        AstResult result = queryParser.build(queryText);
        if (result.getErrors().size() == 0) {
            query.setQuery(result);
        } else {
            final XWPFRun lastRun = query.getRuns().get(query.getRuns().size() - 1);
            query.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), queryText, lastRun));
        }
        return query;
    }

    /**
     * Gets the {@link List} of {@link TemplateValidationMessage} from the given {@link Diagnostic}.
     * 
     * @param diagnostic
     *            the {@link Diagnostic}
     * @param queryText
     *            the query text
     * @param location
     *            the location of the {@link TemplateValidationMessage}
     * @return the {@link List} of {@link TemplateValidationMessage} from the given {@link Diagnostic}
     */
    private List<TemplateValidationMessage> getValidationMessage(Diagnostic diagnostic, String queryText,
            XWPFRun location) {
        final List<TemplateValidationMessage> res = new ArrayList<TemplateValidationMessage>();

        for (Diagnostic child : diagnostic.getChildren()) {
            final ValidationMessageLevel level;
            switch (diagnostic.getSeverity()) {
                case Diagnostic.INFO:
                    level = ValidationMessageLevel.INFO;
                    break;

                case Diagnostic.WARNING:
                    level = ValidationMessageLevel.WARNING;
                    break;

                case Diagnostic.ERROR:
                    level = ValidationMessageLevel.ERROR;
                    break;

                default:
                    level = ValidationMessageLevel.INFO;
                    break;
            }
            res.add(new TemplateValidationMessage(level,
                    message(ParsingErrorMessage.INVALIDEXPR, queryText, child.getMessage()), location));
            res.addAll(getValidationMessage(child, queryText, location));
        }

        return res;
    }

    /**
     * Check that all options are known options.
     * 
     * @param options
     *            the option map
     * @param image
     *            the image
     * @param imageOptionSet
     *            the options set to take in consideration.
     * @param providerOptions
     *            the options handled by the provider.
     * @return if provider provide the image option.
     */
    private boolean checkImagesOptions(Map<String, String> options, AbstractImage image, String[] imageOptionSet,
            Set<String> providerOptions) {
        boolean check = true;
        Set<String> optionSet = Sets.newHashSet(imageOptionSet);
        final XWPFRun lastRun = image.getRuns().get(image.getRuns().size() - 1);
        for (String key : options.keySet()) {
            if (!optionSet.contains(key) && !providerOptions.contains(key)) {
                image.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        message(ParsingErrorMessage.INVALID_IMAGE_OPTION, key, "unknown option name"), lastRun));
                check = false;
            } else if (IMAGE_LEGEND_POSITION.equals(key)) {
                String value = options.get(key);
                if (!IMAGE_LEGEND_ABOVE.equals(value) && !IMAGE_LEGEND_BELOW.equals(value)) {
                    image.getValidationMessages()
                            .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                    message(ParsingErrorMessage.INVALID_IMAGE_OPTION, key,
                                            "unknown option value (" + value + ")."),
                                    lastRun));
                }
            }
        }
        return check;
    }

    /**
     * Parses an image tag.
     * 
     * @return the image object created
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Image parseImage() throws DocumentParserException {
        Image image = (Image) EcoreUtil.create(TemplatePackage.Literals.IMAGE);
        OptionParser optionParser = new OptionParser();
        Map<String, String> options = optionParser.parseOptions(readTag(image, image.getRuns()), TokenType.IMAGE,
                OPTION_GROUP_RANK, OPTION_VAL_GROUP_RANK, image);
        checkImagesOptions(options, image, IMAGE_OPTION_SET, new HashSet<String>(0));
        if (!options.containsKey(IMAGE_FILE_NAME_KEY)) {
            final XWPFRun lastRun = image.getRuns().get(image.getRuns().size() - 1);
            image.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                    message(ParsingErrorMessage.INVALID_IMAGE_TAG), lastRun));
        } else {
            image.setFileName(options.get(IMAGE_FILE_NAME_KEY));
            setImageOptions(image, options);
        }
        return image;
    }

    /**
     * Sets all images options like legend, width and height if those are defined.
     * 
     * @param image
     *            the image from which we want to set options.
     * @param options
     *            the options to set.
     */
    private void setImageOptions(AbstractImage image, Map<String, String> options) {
        Set<Entry<String, String>> entrySet = options.entrySet();
        for (Entry<String, String> entry : entrySet) {
            if (IMAGE_LEGEND_KEY.equals(entry.getKey())) {
                image.setLegend(options.get(IMAGE_LEGEND_KEY));
            } else if (IMAGE_HEIGHT_KEY.equals(entry.getKey())) {
                String heightStr = options.get(IMAGE_HEIGHT_KEY);
                try {
                    image.setHeight(Integer.parseInt(heightStr));
                } catch (NumberFormatException e) {
                    final XWPFRun lastRun = image.getRuns().get(image.getRuns().size() - 1);
                    image.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_HEIGHT_KEY, heightStr), lastRun));
                }
            } else if (IMAGE_WIDTH_KEY.equals(entry.getKey())) {
                String heightStr = options.get(IMAGE_WIDTH_KEY);
                try {
                    image.setWidth(Integer.parseInt(options.get(IMAGE_WIDTH_KEY)));
                } catch (NumberFormatException e) {
                    final XWPFRun lastRun = image.getRuns().get(image.getRuns().size() - 1);
                    image.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_WIDTH_KEY, heightStr), lastRun));
                }
            } else if (IMAGE_LEGEND_POSITION.equals(entry.getKey())) {
                String value = options.get(IMAGE_LEGEND_POSITION);
                if (IMAGE_LEGEND_ABOVE.equals(value)) {
                    image.setLegendPOS(POSITION.ABOVE);
                } else { // put the legend below by default.
                    image.setLegendPOS(POSITION.BELOW);
                }
            }
        }
    }

    /**
     * Sets all generic options parsed from a tag to the given construct.
     * 
     * @param providerTemplate
     *            the {@link AbstractProviderClient} template model element that will receive generic options.
     * @param options
     *            the options to set.
     * @param optionToIgnore
     *            all specific options that should not be in the generic map.
     * @param provider
     *            a provider used to get information for the given construct.
     */
    private void setGenericOptions(AbstractProviderClient providerTemplate, Map<String, String> options,
            Set<String> optionToIgnore, IProvider provider) {
        Map<String, OptionType> optionTypes = provider.getOptionTypes();
        Set<Entry<String, String>> parsedOptions = options.entrySet();
        for (Entry<String, String> parsedOption : parsedOptions) {
            if (!optionToIgnore.contains(parsedOption.getKey())) {
                OptionType optionType = optionTypes == null ? null : optionTypes.get(parsedOption.getKey());
                if (optionType != null) {
                    if (OptionType.AQL_EXPRESSION == optionType) {
                        parseAqlOptions(providerTemplate, parsedOption);
                    } else if (OptionType.STRING == optionType) {
                        providerTemplate.getOptionValueMap().put(parsedOption.getKey(), parsedOption.getValue());
                    } else {
                        throw new UnsupportedOperationException("All option types should be supported.");
                    }
                } else {
                    providerTemplate.getOptionValueMap().put(parsedOption.getKey(), parsedOption.getValue());
                }
            }
        }
    }

    /**
     * Parse an AQL options and produce an {@link AstResult} added to the options map of the given provider tempalte element.
     * 
     * @param providerTemplate
     *            the element which will have its options map filled with the parse result of the AQL expression.
     * @param aqlParsedOption
     *            an option containing an AQL expression as map value.
     */
    private void parseAqlOptions(AbstractProviderClient providerTemplate, Entry<String, String> aqlParsedOption) {
        String query = aqlParsedOption.getValue();
        AstResult result = queryParser.build(query);
        if (result.getErrors().size() == 0) {
            providerTemplate.getOptionValueMap().put(aqlParsedOption.getKey(), result);
        } else {
            final XWPFRun lastRun = providerTemplate.getRuns().get(providerTemplate.getRuns().size() - 1);
            providerTemplate.getValidationMessages()
                    .addAll(getValidationMessage(result.getDiagnostic(), query, lastRun));
            providerTemplate.getOptionValueMap().put(aqlParsedOption.getKey(), null);
        }
    }

    /**
     * parses a static fragment.
     * 
     * @return the object created
     * @throws DocumentParserException
     *             if something wrong happens during parsing
     */
    private StaticFragment parseStaticFragment() throws DocumentParserException {
        StaticFragment result = (StaticFragment) EcoreUtil.create(TemplatePackage.Literals.STATIC_FRAGMENT);
        while (getNextTokenType() == TokenType.STATIC) {
            result.getRuns().add(runIterator.next().getRun());
        }
        return result;
    }

    /**
     * parses a representation tag.
     * 
     * @return the object created
     * @throws DocumentParserException
     *             if something wrong happens during parsing
     */
    private Representation parseRepresentation() throws DocumentParserException {

        Representation representation = (Representation) EcoreUtil.create(TemplatePackage.Literals.REPRESENTATION);
        OptionParser optionParser = new OptionParser();
        String tag = readTag(representation, representation.getRuns());

        Map<String, String> options = optionParser.parseOptions(tag, TokenType.DIAGRAM, OPTION_GROUP_RANK,
                OPTION_VAL_GROUP_RANK, representation);

        representation.setProvider(getRepresentationProvider(representation, options));
        setImageOptions(representation, options);
        setLayersOption(representation, options);
        Set<String> optionToIgnore = new HashSet<String>();
        optionToIgnore.add(IMAGE_LEGEND_KEY);
        optionToIgnore.add(IMAGE_LEGEND_POSITION);
        optionToIgnore.add(IMAGE_HEIGHT_KEY);
        optionToIgnore.add(IMAGE_WIDTH_KEY);
        optionToIgnore.add(DIAGRAM_PROVIDER_KEY);
        optionToIgnore.add(DIAGRAM_LAYERS_KEY);
        if (representation.getProvider() != null) {
            setGenericOptions(representation, options, optionToIgnore, representation.getProvider());
        }
        return representation;
    }

    /**
     * Set layers option.
     * 
     * @param representation
     *            Representation
     * @param options
     *            the options to set.
     */
    protected void setLayersOption(Representation representation, Map<String, String> options) {
        String layers = options.get(DIAGRAM_LAYERS_KEY);
        if (!Strings.isNullOrEmpty(layers)) {
            Iterable<String> split = Splitter.on(',').trimResults().split(layers);
            representation.getActivatedLayers().addAll(Lists.newArrayList(split));
        }
    }

    /**
     * Get diagram provider matching options.
     * 
     * @param representation
     *            Representation
     * @param options
     *            Map
     * @return diagram provider
     */
    private IProvider getRepresentationProvider(Representation representation, Map<String, String> options) {
        IProvider result = null;

        // first if provider option exists, set this one
        String providerQualifiedName = options.get(DIAGRAM_PROVIDER_KEY);
        if (providerQualifiedName != null) {
            result = ProviderRegistry.INSTANCE.getProvider(providerQualifiedName);
            if (result == null) {
                representation.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        "The image tag is referencing an unknown diagram provider : '" + providerQualifiedName + "'",
                        representation.getRuns().get(1)));
                return null;
            }
        }

        // then search best provider from registered providers
        if (result == null) {
            List<IProvider> providers = ProviderRegistry.INSTANCE.getDiagramProviders();
            // no registered providers
            if (providers.isEmpty()) {
                final XWPFRun lastRun = representation.getRuns().get(representation.getRuns().size() - 1);
                representation.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        "No image tag provider is found. Please add one with diagramProvider extension.", lastRun));
            }

            // search best provider
            result = getFirstProvider(representation, options, providers);

            // check errors
            if (result == null) {
                final XWPFRun lastRun = representation.getRuns().get(representation.getRuns().size() - 1);
                representation.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        "No diagram provider found for the image tag", lastRun));
            } else if (!(result instanceof AbstractDiagramProvider)) {
                final XWPFRun lastRun = representation.getRuns().get(representation.getRuns().size() - 1);
                representation.getValidationMessages()
                        .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                "The image tag is referencing a provider that has not been made to handle diagram tags.",
                                lastRun));
            }
        }

        return result;
    }

    /**
     * return first provider that matches with options.
     * 
     * @param representation
     *            Representation
     * @param options
     *            Map<String, String>
     * @param providers
     *            List<IProvider>
     * @return first provider that matches with options.
     */
    private IProvider getFirstProvider(Representation representation, Map<String, String> options,
            List<IProvider> providers) {
        for (IProvider provider : providers) {
            Set<String> providerOptions = provider.getOptionTypes() == null ? new HashSet<String>(0)
                    : provider.getOptionTypes().keySet();
            if (checkImagesOptions(options, representation, DIAGRAM_OPTION_SET, providerOptions)) {
                return provider;
            }

        }
        return null;
    }

    /**
     * Parses a conditional.
     * conditionnal are made of the following set of tags : {gd:if "query"} ...
     * [{gd:elseif "query"} ....]* ({gd:else})? ... {gd:endif}
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Conditionnal parseConditionnal() throws DocumentParserException {
        Conditionnal conditionnal = (Conditionnal) EcoreUtil.create(TemplatePackage.Literals.CONDITIONNAL);
        String tag = readTag(conditionnal, conditionnal.getRuns()).trim();
        boolean headConditionnal = tag.startsWith(TokenType.IF.getValue());
        int tagLength = headConditionnal ? TokenType.IF.getValue().length() : TokenType.ELSEIF.getValue().length();
        String query = tag.substring(tagLength).trim();
        AstResult result = queryParser.build(query);
        if (result.getErrors().size() == 0) {
            conditionnal.setQuery(result);
        } else {
            final XWPFRun lastRun = conditionnal.getRuns().get(conditionnal.getRuns().size() - 1);
            conditionnal.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), query, lastRun));
        }
        parseCompound(conditionnal, TokenType.ELSEIF, TokenType.ELSE, TokenType.ENDIF);
        TokenType nextRunType = getNextTokenType();
        switch (nextRunType) {
            case ELSEIF:
                conditionnal.setAlternative(parseConditionnal());
                break;
            case ELSE:
                Default defaultCompound = (Default) EcoreUtil.create(TemplatePackage.Literals.DEFAULT);
                readTag(defaultCompound, defaultCompound.getRuns());
                parseCompound(defaultCompound, TokenType.ENDIF);
                conditionnal.setElse(defaultCompound);

                // read up the gd:endif tag if it exists
                if (getNextTokenType() != TokenType.EOF) {
                    readTag(conditionnal, conditionnal.getClosingRuns());
                }
                break;
            case ENDIF:
                readTag(conditionnal, conditionnal.getClosingRuns());
                break; // we just finish the current conditionnal.
            default:
                final XWPFRun lastRun = conditionnal.getRuns().get(conditionnal.getRuns().size() - 1);
                conditionnal.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        message(ParsingErrorMessage.CONDTAGEXPEXTED), lastRun));
        }
        return conditionnal;
    }

    /**
     * Parse a repetition construct. Repetition are of the form
     * <code>{gd:for var | query} runs {gd:endfor}</code>
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Repetition parseRepetition() throws DocumentParserException {
        // first read the tag that opens the repetition
        Repetition repetition = (Repetition) EcoreUtil.create(TemplatePackage.Literals.REPETITION);
        String tagText = readTag(repetition, repetition.getRuns()).trim();
        // remove the prefix
        tagText = tagText.substring(TokenType.FOR.getValue().length());
        // extract the variable;
        int indexOfPipe = tagText.indexOf('|');
        if (indexOfPipe < 0) {
            final XWPFRun lastRun = repetition.getRuns().get(repetition.getRuns().size() - 1);
            repetition.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                    "Malformed tag gd:for, no '|' found.", lastRun));
        } else {
            String iterationVariable = tagText.substring(0, indexOfPipe).trim();
            if ("".equals(iterationVariable)) {
                final XWPFRun lastRun = repetition.getRuns().get(repetition.getRuns().size() - 1);
                repetition.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        "Malformed tag gd:for : no iteration variable specified.", lastRun));
            }
            repetition.setIterationVar(iterationVariable);
            if (tagText.length() == indexOfPipe + 1) {
                final XWPFRun lastRun = repetition.getRuns().get(repetition.getRuns().size() - 1);
                repetition.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                        "Malformed tag gd:for : no query expression specified." + tagText, lastRun));
            }
            String query = tagText.substring(indexOfPipe + 1, tagText.length()).trim();
            AstResult result = queryParser.build(query);
            if (result.getErrors().size() == 0) {
                repetition.setQuery(result);
            } else {
                final XWPFRun lastRun = repetition.getRuns().get(repetition.getRuns().size() - 1);
                repetition.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), query, lastRun));
            }
        }
        // read up the tags until the "gd:endfor" tag is encountered.
        parseCompound(repetition, TokenType.ENDFOR);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(repetition, repetition.getClosingRuns());
        }
        return repetition;
    }

    /**
     * Parses a table.
     * 
     * @param wtable
     *            the table to parse
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
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
                BodyParser parser = new BodyParser(tableCell, this.queryParser, queryEnvironment);
                cell.setTemplate(parser.parseTemplate());
            }
        }
        return table;
    }

    /**
     * Parse a bookmark construct. Bookmark are of the form
     * <code>{gd:bookmark 'bookmark name'} runs {gd:endbookmark}</code>
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Bookmark parseBookmark() throws DocumentParserException {
        // first read the tag that opens the bookmark
        final Bookmark bookmark = (Bookmark) EcoreUtil.create(TemplatePackage.Literals.BOOKMARK);

        String tagText = readTag(bookmark, bookmark.getRuns()).trim();
        // remove the prefix
        tagText = tagText.substring(TokenType.BOOKMARK.getValue().length()).trim();
        AstResult result = queryParser.build(tagText);
        if (result.getErrors().size() == 0) {
            bookmark.setName(result);
        } else {
            final XWPFRun lastRun = bookmark.getRuns().get(bookmark.getRuns().size() - 1);
            bookmark.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), tagText, lastRun));
        }
        // read up the tags until the "gd:endbookmark" tag is encountered.
        parseCompound(bookmark, TokenType.ENDBOOKMARK);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(bookmark, bookmark.getClosingRuns());
        }

        return bookmark;
    }

    /**
     * Parse a link construct. Link are of the form
     * <code>{gd:link 'bookmark name' 'link text'}</code>
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Link parseLink() throws DocumentParserException {
        // first read the tag that opens the link
        final Link link = (Link) EcoreUtil.create(TemplatePackage.Literals.LINK);

        String tagText = readTag(link, link.getRuns()).trim();
        // remove the prefix
        tagText = tagText.substring(TokenType.LINK.getValue().length()).trim();
        AstResult nameResult = parseWhileAqlExpression(tagText);
        if (nameResult.getErrors().size() == 0) {
            link.setName(nameResult);
        } else {
            final XWPFRun lastRun = link.getRuns().get(link.getRuns().size() - 1);
            link.getValidationMessages().addAll(getValidationMessage(nameResult.getDiagnostic(), tagText, lastRun));
        }

        tagText = tagText.substring(nameResult.getEndPosition(nameResult.getAst()));
        AstResult textResult = parseWhileAqlExpression(tagText);
        if (textResult.getErrors().size() == 0) {
            link.setText(textResult);
        } else {
            final XWPFRun lastRun = link.getRuns().get(link.getRuns().size() - 1);
            link.getValidationMessages().addAll(getValidationMessage(textResult.getDiagnostic(), tagText, lastRun));
        }

        return link;
    }

    /**
     * Parses while matching an AQL expression.
     * 
     * @param expression
     *            the expression to parse
     * @return the corresponding {@link AstResult}
     */
    private AstResult parseWhileAqlExpression(String expression) {
        final IQueryBuilderEngine.AstResult result;

        if (expression != null && expression.length() > 0) {
            AstBuilderListener astBuilder = new AstBuilderListener(queryEnvironment);
            CharStream input = new UnbufferedCharStream(new StringReader(expression), expression.length());
            QueryLexer lexer = new QueryLexer(input);
            lexer.setTokenFactory(new CommonTokenFactory(true));
            lexer.removeErrorListeners();
            lexer.addErrorListener(astBuilder.getErrorListener());
            TokenStream tokens = new UnbufferedTokenStream<CommonToken>(lexer);
            QueryParser parser = new QueryParser(tokens);
            parser.addParseListener(astBuilder);
            parser.removeErrorListeners();
            parser.addErrorListener(astBuilder.getErrorListener());
            // parser.setTrace(true);
            parser.expression();
            result = astBuilder.getAstResult();
        } else {
            ErrorExpression errorExpression = (ErrorExpression) EcoreUtil
                    .create(AstPackage.eINSTANCE.getErrorExpression());
            List<org.eclipse.acceleo.query.ast.Error> errors = new ArrayList<org.eclipse.acceleo.query.ast.Error>(1);
            errors.add(errorExpression);
            final Map<Object, Integer> positions = new HashMap<Object, Integer>();
            if (expression != null) {
                positions.put(errorExpression, Integer.valueOf(0));
            }
            final BasicDiagnostic diagnostic = new BasicDiagnostic();
            diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, AstBuilderListener.PLUGIN_ID, 0,
                    "null or empty string.", new Object[] {errorExpression }));
            result = new AstResult(errorExpression, positions, positions, errors, diagnostic);
        }

        return result;
    }

}
