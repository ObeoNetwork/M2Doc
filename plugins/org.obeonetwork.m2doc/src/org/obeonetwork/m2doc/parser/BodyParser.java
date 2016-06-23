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

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import org.obeonetwork.m2doc.provider.DiagramProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractImage;
import org.obeonetwork.m2doc.template.AbstractProvider;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Compound;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.Image;
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
 * that there's no ambiguïty with the rest of the text.
 * 
 * @author Romain Guider
 */
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
    private static final String DIAGRAM_PROVIDER_KEY = "diagramProvider";
    /**
     * Diagram title option name.
     */
    private static final String DIAGRAM_TITLE_KEY = "title";
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

    {IMAGE_FILE_NAME_KEY, IMAGE_HEIGHT_KEY, IMAGE_LEGEND_KEY, IMAGE_LEGEND_POSITION, IMAGE_WIDTH_KEY};
    /**
     * Array of representation's options name constants.
     */
    private static final String[] DIAGRAM_OPTION_SET =

    {DIAGRAM_PROVIDER_KEY, IMAGE_FILE_NAME_KEY, IMAGE_HEIGHT_KEY, IMAGE_LEGEND_KEY, IMAGE_LEGEND_POSITION,
        IMAGE_WIDTH_KEY};

    /**
     * Regex that allows to parse image options.
     */
    private static final Pattern IMAGE_TAG_PATTERN = Pattern
            .compile("\\s*gd:image\\s*(([a-zA-Z]+)\\s*:\\s*'([^']*)')+\\s*");
    /**
     * Regex that allows to parse image options.
     */
    private static final Pattern OPTION_PATTERN = Pattern.compile("([a-zA-Z]+)\\s*:\\s*'([^']*)'*");

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
     * Creates a new {@link BodyParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryEnvironment
     *            the query environment to used during parsing.
     */
    @SuppressWarnings("restriction")
    public BodyParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = new QueryBuilderEngine(queryEnvironment);
    }

    /**
     * Creates a new {@link BodyParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryParser
     *            the query parser to use during parsing
     */
    BodyParser(IBody inputDocument, IQueryBuilderEngine queryParser) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = queryParser;
    }

    /**
     * Creates an error message.
     * 
     * @param error
     *            the error to create a message from
     * @param objects
     *            the list of the message arguments
     * @return the formated error message
     */
    private String message(ParsingErrorMessage error, Object... objects) {
        return MessageFormat.format(error.getMessage(), objects);
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
        template.setDocument(this.document);
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
                    // report the error and ignore the problem so that parsing
                    // continues in other parts of the document.
                    XWPFRun run = runIterator.lookAhead(1).getRun();
                    if (run == null) {
                        throw new IllegalStateException(
                                "Token of type " + type + " detected. Run shouldn't be null at this place.");
                    }
                    compound.getParsingErrors().add(
                            new DocumentParsingError(message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
                    readTag(compound, compound.getRuns());
                    break;
                case EOF:
                    compound.getParsingErrors()
                            .add(new DocumentParsingError(message(ParsingErrorMessage.UNEXPECTEDTAG, type), null));
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
                case WTABLE:
                    compound.getSubConstructs().add(parseTable(runIterator.next().getTable()));
                    break;
                case DIAGRAM:
                    compound.getSubConstructs().add(parseRepresentation());// XXX : change representation into diagram in the template
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
        AstResult result = queryParser.build(queryText.trim());
        if (result.getErrors().size() == 0) {
            query.setQuery(result);
        } else {
            query.getParsingErrors().add(
                    new DocumentParsingError(message(ParsingErrorMessage.INVALIDEXPR, queryText), query.getStyleRun()));
        }
        return query;
    }

    /**
     * Parses an option regarding the given parametering context.
     * 
     * @param tag
     *            the tag to parse
     * @param tokenType
     *            the token type triggering the option retrieval.
     * @param optionGroupRank
     *            the rank of the option in the tag.
     * @param optionValGroupRank
     *            the rank of the option's value in the tag.
     * @param optionPattern
     *            the authorized pattern for the option.
     * @return the option map created.
     */
    Map<String, String> parseOptions(String tag, TokenType tokenType, Pattern optionPattern, int optionGroupRank,
            int optionValGroupRank) {
        Map<String, String> result = new HashMap<String, String>();
        // first match the tag:
        String trimedTag = tag.trim();
        if (tag.startsWith(tokenType.getValue())) {
            int length = tokenType.getValue().length();
            int tagLength = trimedTag.length();
            trimedTag = trimedTag.substring(length, tagLength);
            Matcher matcher = optionPattern.matcher(trimedTag);
            while (matcher.find()) {
                String option = matcher.group(optionGroupRank);
                String value = matcher.group(optionValGroupRank);
                result.put(option, value);
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
     * @param imageOptionSet
     *            the options set to take in consideration.
     * @param providerOptions
     *            the options handled by the provider.
     */
    private void checkImagesOptions(Map<String, String> options, AbstractImage image, String[] imageOptionSet,
            Set<String> providerOptions) {
        Set<String> optionSet = Sets.newHashSet(imageOptionSet);
        for (String key : options.keySet()) {
            if (!optionSet.contains(key) && !providerOptions.contains(key)) {
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

    /**
     * Parses an image tag.
     * 
     * @return the image object created
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Image parseImage() throws DocumentParserException {
        Image image = (Image) EcoreUtil.create(TemplatePackage.Literals.IMAGE);
        Map<String, String> options = parseOptions(readTag(image, image.getRuns()), TokenType.IMAGE, OPTION_PATTERN,
                OPTION_GROUP_RANK, OPTION_VAL_GROUP_RANK);
        checkImagesOptions(options, image, IMAGE_OPTION_SET, new HashSet<String>(0));
        if (!options.containsKey(IMAGE_FILE_NAME_KEY)) {
            image.getParsingErrors().add(
                    new DocumentParsingError(message(ParsingErrorMessage.INVALID_IMAGE_TAG), image.getRuns().get(1)));
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
     * @param optionToIgnore
     *            a set of options that must not be put in the generic option map.
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
                    image.getParsingErrors()
                            .add(new DocumentParsingError(
                                    message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_HEIGHT_KEY, heightStr),
                                    image.getRuns().get(1)));
                }
            } else if (IMAGE_WIDTH_KEY.equals(entry.getKey())) {
                String heightStr = options.get(IMAGE_WIDTH_KEY);
                try {
                    image.setWidth(Integer.parseInt(options.get(IMAGE_WIDTH_KEY)));
                } catch (NumberFormatException e) {
                    image.getParsingErrors()
                            .add(new DocumentParsingError(
                                    message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_WIDTH_KEY, heightStr),
                                    image.getRuns().get(1)));
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
     * @param construct
     *            the construct that will receive generic options.
     * @param options
     *            the options to set.
     * @param optionToIgnore
     *            all specific options that should not be in the generic map.
     * @param specificOptionsToIgnore
     *            a set of options that must not be put in the generic option map because they are not generic.
     * @param provider
     *            a provider used to get information for the given construct.
     */
    private void setGenericOptions(AbstractProvider providerTemplate, Map<String, String> options,
            Set<String> optionToIgnore, IProvider provider) {
        Map<String, OptionType> optionTypes = provider.getOptionTypes();
        Set<Entry<String, String>> parsedOptions = options.entrySet();
        for (Entry<String, String> parsedOption : parsedOptions) {
            if (!optionToIgnore.contains(parsedOption.getKey())) {
                OptionType optionType = optionTypes == null ? null : optionTypes.get(parsedOption.getKey());
                if (optionType != null) {
                    if (OptionType.AQL_EXPRESSION == optionType) {
                        String query = parsedOption.getValue();
                        AstResult result = queryParser.build(query);
                        if (result.getErrors().size() == 0) {
                            providerTemplate.getOptionValueMap().put(parsedOption.getKey(), result);
                        } else {
                            providerTemplate.getParsingErrors()
                                    .add(new DocumentParsingError(message(ParsingErrorMessage.INVALIDEXPR, query),
                                            providerTemplate.getRuns().get(1)));
                            providerTemplate.getOptionValueMap().put(parsedOption.getKey(), null);
                        }
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
        Map<String, String> options = parseOptions(readTag(representation, representation.getRuns()), TokenType.DIAGRAM,
                OPTION_PATTERN, OPTION_GROUP_RANK, OPTION_VAL_GROUP_RANK);
        IProvider provider = null;
        String providerQualifiedName = options.get(DIAGRAM_PROVIDER_KEY);
        if (providerQualifiedName != null) {
            provider = ProviderRegistry.INSTANCE.getProvider(providerQualifiedName);
        }

        if (provider == null) {
            representation.getParsingErrors().add(new DocumentParsingError(
                    "The image tag is referencing an unknown diagram provider : '" + providerQualifiedName + "'",
                    representation.getRuns().get(1)));
        } else if (!(provider instanceof DiagramProvider)) {
            representation.getParsingErrors()
                    .add(new DocumentParsingError(
                            "The image tag is referencing a provider that is not an instance of DiagramProvider : '"
                                + providerQualifiedName + "'",
                            representation.getRuns().get(1)));
        } else {
            representation.setProvider(provider);
            Set<String> providerOptions = provider.getOptionTypes() == null ? new HashSet<String>(0)
                    : provider.getOptionTypes().keySet();
            checkImagesOptions(options, representation, DIAGRAM_OPTION_SET, providerOptions);
            setImageOptions(representation, options);
            Set<String> optionToIgnore = new HashSet<String>();
            optionToIgnore.add(IMAGE_LEGEND_KEY);
            optionToIgnore.add(IMAGE_LEGEND_POSITION);
            optionToIgnore.add(IMAGE_HEIGHT_KEY);
            optionToIgnore.add(IMAGE_WIDTH_KEY);
            optionToIgnore.add(DIAGRAM_PROVIDER_KEY);
            setGenericOptions(representation, options, optionToIgnore, provider);
        }
        return representation;
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
            conditionnal.getParsingErrors().add(new DocumentParsingError(
                    message(ParsingErrorMessage.INVALIDEXPR, query), conditionnal.getRuns().get(1)));
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
                conditionnal.getParsingErrors().add(new DocumentParsingError(
                        message(ParsingErrorMessage.CONDTAGEXPEXTED), conditionnal.getRuns().get(1)));
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
                BodyParser parser = new BodyParser(tableCell, this.queryParser);
                cell.setTemplate(parser.parseTemplate());
            }
        }
        return table;
    }
}
