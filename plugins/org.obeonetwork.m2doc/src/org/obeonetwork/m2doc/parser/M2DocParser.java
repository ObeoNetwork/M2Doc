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
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.eclipse.acceleo.query.ast.AstPackage;
import org.eclipse.acceleo.query.ast.ErrorExpression;
import org.eclipse.acceleo.query.parser.AstBuilderListener;
import org.eclipse.acceleo.query.parser.QueryLexer;
import org.eclipse.acceleo.query.parser.QueryParser;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.template.AbstractImage;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.POSITION;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.TableClient;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserDoc;

import static org.obeonetwork.m2doc.provider.ProviderConstants.HIDE_TITLE_KEY;
import static org.obeonetwork.m2doc.util.M2DocUtils.message;
import static org.obeonetwork.m2doc.util.M2DocUtils.validationError;

/**
 * DocumentParser reads a {@link XWPFDocument} and produces a abstract syntax
 * tree that represents the template in the document.
 * The parsing algorithm is an LL(k) like algorithm that uses look ahead to predict parsing decisions. Tokens are only read from fields so
 * that there's no ambiguity with the rest of the text.
 * 
 * @author Romain Guider
 */
public class M2DocParser extends BodyAbstractParser {

    /**
     * Image file name option name.
     */
    private static final String IMAGE_FILE_NAME_KEY = "file";
    /**
     * Diagram or table provider option name.
     */
    private static final String PROVIDER_KEY = "provider";
    /**
     * Diagram or table create option name.
     */
    private static final String CREATE_KEY = "create";
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

            {PROVIDER_KEY, IMAGE_HEIGHT_KEY, IMAGE_LEGEND_KEY, IMAGE_LEGEND_POSITION, IMAGE_WIDTH_KEY,
                DIAGRAM_LAYERS_KEY, CREATE_KEY, };

    /**
     * Rank of the option's value group in the matcher.
     */
    private static final int OPTION_VAL_GROUP_RANK = 2;
    /**
     * Rank of the option's name group in the matcher.
     */
    private static final int OPTION_GROUP_RANK = 1;

    /**
     * Creates a new {@link M2DocParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryEnvironment
     *            the query environment to used during parsing.
     */
    public M2DocParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
        super(inputDocument, queryEnvironment);
    }

    /**
     * Creates a new {@link M2DocParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryParser
     *            the query parser to use during parsing
     * @param queryEnvironment
     *            The {@link IQueryEnvironment}
     */
    private M2DocParser(IBody inputDocument, IQueryBuilderEngine queryParser, IQueryEnvironment queryEnvironment) {
        super(inputDocument, queryParser, queryEnvironment);
    }

    /**
     * returns the next token type after index.
     * 
     * @return the next token type.
     */
    @Override
    protected TokenType getNextTokenType() {
        int index = 1;
        ParsingToken token = runIterator.lookAhead(index);
        TokenType result;
        if (token == null) {
            result = TokenType.EOF;
        } else if (token.getKind() == ParsingTokenKind.TABLE) {
            result = TokenType.WTABLE;
        } else {
            XWPFRun run = token.getRun();
            // is run a field begin run
            if (fieldUtils.isFieldBegin(run)) {
                String code = fieldUtils.lookAheadTag(runIterator);
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
                } else if (code.startsWith(TokenType.USERDOC.getValue())) {
                    result = TokenType.USERDOC;
                } else if (code.startsWith(TokenType.ENDUSERDOC.getValue())) {
                    result = TokenType.ENDUSERDOC;
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
                } else if (code.startsWith(TokenType.TABLE.getValue())) {
                    result = TokenType.TABLE;
                } else if (code.startsWith(TokenType.BOOKMARK.getValue())) {
                    result = TokenType.BOOKMARK;
                } else if (code.startsWith(TokenType.ENDBOOKMARK.getValue())) {
                    result = TokenType.ENDBOOKMARK;
                } else if (code.startsWith(TokenType.LINK.getValue())) {
                    result = TokenType.LINK;
                } else if (code.startsWith(TokenType.COMMENT.getValue())) {
                    result = TokenType.COMMENT;
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

    @Override
    protected Block parseBlock(TokenType... endTypes) throws DocumentParserException {
        final Block res = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
        TokenType type = getNextTokenType();
        Set<TokenType> endTypeList = Sets.newHashSet(endTypes);
        endBlock: while (!endTypeList.contains(type)) {
            switch (type) {
                case AQL:
                    res.getStatements().add(parseQuery());
                    break;
                case FOR:
                    res.getStatements().add(parseRepetition());
                    break;
                case IF:
                    res.getStatements().add(parseConditional());
                    break;
                case USERDOC:
                    res.getStatements().add(parseUserDoc());
                    break;
                case COMMENT:
                    res.getStatements().add(parseComment());
                    break;
                case ELSEIF:
                case ELSE:
                case ENDFOR:
                case ENDIF:
                case ENDLET:
                case ENDBOOKMARK:
                case ENDUSERDOC:
                    // report the error and ignore the problem so that parsing
                    // continues in other parts of the document.
                    XWPFRun run = runIterator.lookAhead(1).getRun();
                    if (run == null) {
                        throw new IllegalStateException(
                                "Token of type " + type + " detected. Run shouldn't be null at this place.");
                    }
                    res.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                            message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
                    readTag(res, res.getRuns());
                    break;
                case EOF:
                    final XWPFParagraph lastParagraph = document.getParagraphs()
                            .get(document.getParagraphs().size() - 1);
                    final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
                    res.getValidationMessages()
                            .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                    message(ParsingErrorMessage.UNEXPECTEDTAGMISSING, type, Arrays.toString(endTypes)),
                                    lastRun));
                    break endBlock;
                case LET:
                    res.getStatements().add(parseLet());
                    break;
                case IMAGE:
                    res.getStatements().add(parseImage());
                    break;
                case STATIC:
                    res.getStatements().add(parseStaticFragment());
                    break;
                case BOOKMARK:
                    res.getStatements().add(parseBookmark());
                    break;
                case LINK:
                    res.getStatements().add(parseLink());
                    break;
                case WTABLE:
                    res.getStatements().add(parseTable(runIterator.next().getTable()));
                    break;
                case DIAGRAM:
                    res.getStatements().add(parseRepresentation()); // XXX : change representation into diagram in the template
                                                                    // model.
                    break;
                case TABLE:
                    res.getStatements().add(parseTableClient());
                    break;
                default:
                    throw new UnsupportedOperationException(
                            String.format("Developer error: TokenType %s is not supported", type));
            }
            type = getNextTokenType();
        }

        return res;
    }

    /**
     * Parses a let construct.
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    private Let parseLet() throws DocumentParserException {
        Let let = (Let) EcoreUtil.create(TemplatePackage.Literals.LET);

        String letText = readTag(let, let.getRuns()).trim().substring(TokenType.LET.getValue().length());
        letText = letText.trim();

        int currentIndex = 0;
        if (currentIndex < letText.length() && Character.isJavaIdentifierStart(letText.charAt(currentIndex))) {
            currentIndex++;
            while (currentIndex < letText.length() && Character.isJavaIdentifierPart(letText.charAt(currentIndex))) {
                currentIndex++;
            }
            let.setName(letText.substring(0, currentIndex));
        } else {
            validationError(let, "Missing identifier");
        }

        while (currentIndex < letText.length() && Character.isWhitespace(letText.charAt(currentIndex))) {
            currentIndex++;
        }

        if (currentIndex < letText.length() && letText.charAt(currentIndex) == '=') {
            currentIndex++;
        } else {
            validationError(let, "Missing =");
        }

        while (currentIndex < letText.length() && Character.isWhitespace(letText.charAt(currentIndex))) {
            currentIndex++;
        }

        final String queryString = letText.substring(currentIndex);
        final AstResult result = queryParser.build(queryString);
        let.setValue(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = let.getRuns().get(let.getRuns().size() - 1);
            let.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), queryString, lastRun));
        }

        final Block body = parseBlock(TokenType.ENDLET);
        let.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(let, let.getClosingRuns());
        }

        return let;
    }

    /**
     * Parses a query construct.
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    private Query parseQuery() throws DocumentParserException {
        final Query query = (Query) EcoreUtil.create(TemplatePackage.Literals.QUERY);
        final String queryText = readTag(query, query.getRuns()).trim().substring(TokenType.AQL.getValue().length())
                .trim();
        final AstResult result = queryParser.build(queryText);
        query.setQuery(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = query.getRuns().get(query.getRuns().size() - 1);
            query.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), queryText, lastRun));
        }
        return query;
    }

    /**
     * Parses a comment construct.
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    private Comment parseComment() throws DocumentParserException {
        final Comment comment = (Comment) EcoreUtil.create(TemplatePackage.Literals.COMMENT);
        final String commentText = readTag(comment, comment.getRuns()).trim()
                .substring(TokenType.COMMENT.getValue().length());

        comment.setText(commentText.trim());

        return comment;
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
     * @param logError
     *            Whether or not errors must be logged
     * @return if provider provide the image option.
     */
    private boolean checkImagesOptions(Map<String, String> options, AbstractImage image, String[] imageOptionSet,
            Set<String> providerOptions, boolean logError) {
        boolean check = true;
        Set<String> optionSet = Sets.newHashSet(imageOptionSet);
        for (String key : options.keySet()) {
            if (!optionSet.contains(key) && !providerOptions.contains(key)) {
                if (logError) {
                    validationError(image,
                            message(ParsingErrorMessage.INVALID_IMAGE_OPTION, key, "unknown option name"));
                }
                check = false;
            } else if (IMAGE_LEGEND_POSITION.equals(key)) {
                String value = options.get(key);
                if (logError) {
                    if (!IMAGE_LEGEND_ABOVE.equals(value) && !IMAGE_LEGEND_BELOW.equals(value)) {
                        validationError(image, message(ParsingErrorMessage.INVALID_IMAGE_OPTION, key,
                                "unknown option value (" + value + ")."));
                    }
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
        checkImagesOptions(options, image, IMAGE_OPTION_SET, new HashSet<String>(0), true);
        if (!options.containsKey(IMAGE_FILE_NAME_KEY)) {
            validationError(image, message(ParsingErrorMessage.INVALID_IMAGE_TAG));
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
                    validationError(image,
                            message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_HEIGHT_KEY, heightStr));
                }
            } else if (IMAGE_WIDTH_KEY.equals(entry.getKey())) {
                String heightStr = options.get(IMAGE_WIDTH_KEY);
                try {
                    image.setWidth(Integer.parseInt(options.get(IMAGE_WIDTH_KEY)));
                } catch (NumberFormatException e) {
                    validationError(image,
                            message(ParsingErrorMessage.INVALID_IMAGE_OPTION, IMAGE_WIDTH_KEY, heightStr));
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
            } else if (CREATE_KEY.equals(parsedOption.getKey())) {
                providerTemplate.getOptionValueMap().put(parsedOption.getKey(), parsedOption.getValue());
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
        final AstResult option = queryParser.build(query);
        providerTemplate.getOptionValueMap().put(aqlParsedOption.getKey(), option);
        if (!option.getErrors().isEmpty()) {
            final XWPFRun lastRun = providerTemplate.getRuns().get(providerTemplate.getRuns().size() - 1);
            providerTemplate.getValidationMessages()
                    .addAll(getValidationMessage(option.getDiagnostic(), query, lastRun));
        }
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
        optionToIgnore.add(PROVIDER_KEY);
        optionToIgnore.add(DIAGRAM_LAYERS_KEY);
        optionToIgnore.add(CREATE_KEY);
        if (representation.getProvider() != null) {
            setGenericOptions(representation, options, optionToIgnore, representation.getProvider());
        }
        return representation;
    }

    /**
     * Parses a m:table tag.
     * 
     * @return the created {@link TableClient} object
     * @throws DocumentParserException
     *             if something wrong happens during parsing
     */
    private TableClient parseTableClient() throws DocumentParserException {
        TableClient tableClient = (TableClient) EcoreUtil.create(TemplatePackage.Literals.TABLE_CLIENT);
        OptionParser optionParser = new OptionParser();
        String tag = readTag(tableClient, tableClient.getRuns());

        Map<String, String> options = optionParser.parseOptions(tag, TokenType.TABLE, OPTION_GROUP_RANK,
                OPTION_VAL_GROUP_RANK, tableClient);

        assignTableProvider(tableClient, options);
        return tableClient;
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
        String providerQualifiedName = options.get(PROVIDER_KEY);
        if (providerQualifiedName != null) {
            result = ProviderRegistry.INSTANCE.getProvider(providerQualifiedName);
            if (result == null) {
                representation.getValidationMessages()
                        .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                String.format("The image tag is referencing an unknown diagram provider : '%s'",
                                        providerQualifiedName),
                                representation.getRuns().get(1)));
                return null;
            }
        }

        // then search best provider from registered providers
        if (result == null) {
            List<IProvider> providers = ProviderRegistry.INSTANCE.getDiagramProviders();
            // no registered providers
            if (providers.isEmpty()) {
                validationError(representation,
                        "No image tag provider has been found. Please add one with diagramProvider extension.");
            }

            // search best provider
            result = getFirstProvider(representation, options, providers);

            // check errors
            if (result == null) {
                validationError(representation, "No diagram provider found for the image tag");
            } else if (!(result instanceof AbstractDiagramProvider)) {
                validationError(representation,
                        "The image tag is referencing a provider that has not been made to handle diagram tags.");
            }
        }

        return result;
    }

    /**
     * Get diagram provider matching options and set it to the given {@link TableClient}.
     * 
     * @param tableClient
     *            Representation
     * @param options
     *            Map
     */
    private void assignTableProvider(TableClient tableClient, Map<String, String> options) {
        // if provider option exists, set this one
        String providerQualifiedName = options.get(PROVIDER_KEY);
        if (providerQualifiedName != null) {
            IProvider provider = ProviderRegistry.INSTANCE.getProvider(providerQualifiedName);
            if (!(provider instanceof AbstractTableProvider)) {
                validationError(tableClient,
                        String.format(
                                "The table tag is referencing an unknown or invalid table provider in the 'provider' argument : '%s'",
                                providerQualifiedName));
                provider = null;
            } else if (OptionChecker.check(provider.getOptionTypes()).ignore(PROVIDER_KEY, HIDE_TITLE_KEY)
                    .against(options)) {
                validationError(tableClient,
                        String.format(
                                "The table tag is referencing a provider that doesn't support the other arguments provided in the tag : '%s'",
                                providerQualifiedName));
                provider = null;
            }
        } else {
            List<AbstractTableProvider> providers = ProviderRegistry.INSTANCE.getTableProviders();
            if (providers.isEmpty()) {
                validationError(tableClient,
                        "No table tag provider has been found. Please add one with tableProvider extension.");
            } else {
                // let's find the best provider
                for (AbstractTableProvider provider : providers) {
                    if (OptionChecker.check(provider.getOptionTypes()).ignore(PROVIDER_KEY, HIDE_TITLE_KEY, CREATE_KEY)
                            .against(options)) {
                        tableClient.setProvider(provider);
                        setGenericOptions(tableClient, options, ImmutableSet.of(PROVIDER_KEY), provider);
                        break;
                    }
                }
                if (tableClient.getProvider() == null) {
                    // No relevant provider has been found
                    validationError(tableClient,
                            "No table tag provider matching the tag arguments has been found. Please use arguments compatible with the table providers in use.");
                }
            }
        }
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
            if (checkImagesOptions(options, representation, DIAGRAM_OPTION_SET, providerOptions, false)) {
                return provider;
            }

        }
        return null;
    }

    /**
     * Parses a conditional.
     * conditional are made of the following set of tags : {m:if "query"} ...
     * [{m:elseif "query"} ....]* ({m:else})? ... {m:endif}
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Conditional parseConditional() throws DocumentParserException {
        Conditional conditional = (Conditional) EcoreUtil.create(TemplatePackage.Literals.CONDITIONAL);
        String tag = readTag(conditional, conditional.getRuns()).trim();
        boolean headConditionnal = tag.startsWith(TokenType.IF.getValue());
        int tagLength = headConditionnal ? TokenType.IF.getValue().length() : TokenType.ELSEIF.getValue().length();
        String query = tag.substring(tagLength).trim();
        final AstResult result = queryParser.build(query);
        conditional.setCondition(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = conditional.getRuns().get(conditional.getRuns().size() - 1);
            conditional.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), query, lastRun));
        }
        final Block thenCompound = parseBlock(TokenType.ELSEIF, TokenType.ELSE, TokenType.ENDIF);
        conditional.setThen(thenCompound);
        TokenType nextRunType = getNextTokenType();
        switch (nextRunType) {
            case ELSEIF:
                final Block elseIfCompound = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
                elseIfCompound.getStatements().add(parseConditional());
                conditional.setElse(elseIfCompound);
                break;
            case ELSE:
                final Block block = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
                readTag(block, block.getRuns());
                final Block elseCompound = parseBlock(TokenType.ENDIF);
                conditional.setElse(elseCompound);

                // read up the m:endif tag if it exists
                if (getNextTokenType() != TokenType.EOF) {
                    readTag(conditional, conditional.getClosingRuns());
                }
                break;
            case ENDIF:
                readTag(conditional, conditional.getClosingRuns());
                break; // we just finish the current conditional.
            default:
                validationError(conditional, message(ParsingErrorMessage.CONDTAGEXPEXTED));
        }
        return conditional;
    }

    /**
     * Parse a repetition construct. Repetition are of the form
     * <code>{m:for var | query} runs {m:endfor}</code>
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
            validationError(repetition, "Malformed tag m:for, no '|' found.");
            final AstResult result = queryParser.build(null);
            repetition.setQuery(result);
            final Block body = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
            repetition.setBody(body);
        } else {
            String iterationVariable = tagText.substring(0, indexOfPipe).trim();
            if ("".equals(iterationVariable)) {
                validationError(repetition, "Malformed tag m:for : no iteration variable specified.");
            }
            repetition.setIterationVar(iterationVariable);
            if (tagText.length() == indexOfPipe + 1) {
                validationError(repetition, "Malformed tag m:for : no query expression specified." + tagText);
            }
            String query = tagText.substring(indexOfPipe + 1, tagText.length()).trim();
            final AstResult result = queryParser.build(query);
            repetition.setQuery(result);
            if (!result.getErrors().isEmpty()) {
                final XWPFRun lastRun = repetition.getRuns().get(repetition.getRuns().size() - 1);
                repetition.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), query, lastRun));
            }
        }
        // read up the tags until the "m:endfor" tag is encountered.
        final Block body = parseBlock(TokenType.ENDFOR);
        repetition.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(repetition, repetition.getClosingRuns());
        }
        return repetition;
    }

    /**
     * Parse a bookmark construct. Bookmark are of the form
     * <code>{m:bookmark 'bookmark name'} runs {m:endbookmark}</code>
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
        final AstResult result = queryParser.build(tagText);
        bookmark.setName(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = bookmark.getRuns().get(bookmark.getRuns().size() - 1);
            bookmark.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), tagText, lastRun));
        }
        // read up the tags until the "m:endbookmark" tag is encountered.
        final Block body = parseBlock(TokenType.ENDBOOKMARK);
        bookmark.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(bookmark, bookmark.getClosingRuns());
        }

        return bookmark;
    }

    /**
     * Parse a link construct. Link are of the form
     * <code>{m:link 'bookmark name' 'link text'}</code>
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
        final AstResult nameResult = parseWhileAqlExpression(tagText);
        link.setName(nameResult);
        if (!nameResult.getErrors().isEmpty()) {
            final XWPFRun lastRun = link.getRuns().get(link.getRuns().size() - 1);
            link.getValidationMessages().addAll(getValidationMessage(nameResult.getDiagnostic(), tagText, lastRun));
        }

        tagText = tagText.substring(nameResult.getEndPosition(nameResult.getAst()));
        final AstResult textResult = parseWhileAqlExpression(tagText);
        link.setText(textResult);
        if (!textResult.getErrors().isEmpty()) {
            final XWPFRun lastRun = link.getRuns().get(link.getRuns().size() - 1);
            link.getValidationMessages().addAll(getValidationMessage(textResult.getDiagnostic(), tagText, lastRun));
        }

        return link;
    }

    /**
     * Parses a user Document template part.
     * user Document part are made of the following set of tags : {m:userdoc "query"} ...
     * ... {m:enduserdoc}.
     * userdoc tag must contain only some static element.
     * 
     * @author ohaegi
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private UserDoc parseUserDoc() throws DocumentParserException {
        // first read the tag that opens the link
        final UserDoc userDoc = (UserDoc) EcoreUtil.create(TemplatePackage.Literals.USER_DOC);
        String tagText = readTag(userDoc, userDoc.getRuns()).trim();
        // remove the prefix
        tagText = tagText.substring(TokenType.USERDOC.getValue().length()).trim();

        final AstResult id = queryParser.build(tagText);
        userDoc.setId(id);
        if (!id.getErrors().isEmpty()) {
            final XWPFRun lastRun = userDoc.getRuns().get(userDoc.getRuns().size() - 1);
            userDoc.getValidationMessages().addAll(getValidationMessage(id.getDiagnostic(), tagText, lastRun));
        }

        // read up the tags until the "m:enduserdoc" tag is encountered.
        final Block body = parseBlock(TokenType.ENDUSERDOC);
        userDoc.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(userDoc, userDoc.getClosingRuns());
        }
        return userDoc;
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

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.parser.BodyAbstractParser#getNewParser(org.apache.poi.xwpf.usermodel.IBody)
     */
    @Override
    protected BodyAbstractParser getNewParser(IBody inputDocument) {
        BodyAbstractParser parser = new M2DocParser(inputDocument, this.queryParser, this.queryEnvironment);
        return parser;
    }
}
