/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.parser;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.eclipse.acceleo.query.ast.AstPackage;
import org.eclipse.acceleo.query.ast.Error;
import org.eclipse.acceleo.query.ast.ErrorExpression;
import org.eclipse.acceleo.query.ast.ErrorTypeLiteral;
import org.eclipse.acceleo.query.parser.AstBuilderListener;
import org.eclipse.acceleo.query.parser.QueryLexer;
import org.eclipse.acceleo.query.parser.QueryParser;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Parameter;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.util.AQL56Compatibility;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * DocumentParser reads a {@link XWPFDocument} and produces a abstract syntax
 * tree that represents the template in the document.
 * The parsing algorithm is an LL(k) like algorithm that uses look ahead to predict parsing decisions. Tokens are only read from fields so
 * that there's no ambiguity with the rest of the text.
 * 
 * @author Romain Guider
 */
public class M2DocParser extends AbstractBodyParser {

    /**
     * The mapping of {@link TokenType} to extra spaces {@link Pattern}.
     */
    private static final Map<TokenType, Pattern> EXTRA_SPACES_PATTERNS = initExtraSpacesPatterns();

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
     * Initializes {@link #EXTRA_SPACES_PATTERNS}.
     *
     * @return the mapping of {@link TokenType} to extra spaces {@link Pattern}
     */
    private static Map<TokenType, Pattern> initExtraSpacesPatterns() {
        final Map<TokenType, Pattern> res = new HashMap<>();

        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.needExtraSpacesCheck()) {
                final String value = tokenType.getValue();
                final String tag = value.substring(TokenType.QUERY.getValue().length());
                res.put(tokenType, Pattern.compile("m:\\s+" + tag));
            }
        }

        return res;
    }

    @Override
    protected TokenType getNextTokenMTag(final ParsingToken token) {
        final TokenType result;

        final XWPFRun run = token.getRun();
        // is run a field begin run
        if (fieldUtils.isFieldBegin(run)) {
            final String type = getType(fieldUtils.lookAheadTag(runIterator));
            if (type == null) {
                result = TokenType.STATIC;
            } else if (type.equals(TokenType.FOR.getValue())) {
                result = TokenType.FOR;
            } else if (type.equals(TokenType.ENDFOR.getValue())) {
                result = TokenType.ENDFOR;
            } else if (type.equals(TokenType.IF.getValue())) {
                result = TokenType.IF;
            } else if (type.equals(TokenType.ELSEIF.getValue())) {
                result = TokenType.ELSEIF;
            } else if (type.equals(TokenType.ELSE.getValue())) {
                result = TokenType.ELSE;
            } else if (type.equals(TokenType.ENDIF.getValue())) {
                result = TokenType.ENDIF;
            } else if (type.equals(TokenType.USERDOC.getValue())) {
                result = TokenType.USERDOC;
            } else if (type.equals(TokenType.ENDUSERDOC.getValue())) {
                result = TokenType.ENDUSERDOC;
            } else if (type.equals(TokenType.LET.getValue())) {
                result = TokenType.LET;
            } else if (type.equals(TokenType.ENDLET.getValue())) {
                result = TokenType.ENDLET;
            } else if (type.equals(TokenType.BOOKMARK.getValue())) {
                result = TokenType.BOOKMARK;
            } else if (type.equals(TokenType.ENDBOOKMARK.getValue())) {
                result = TokenType.ENDBOOKMARK;
            } else if (type.equals(TokenType.LINK.getValue())) {
                result = TokenType.LINK;
            } else if (type.equals(TokenType.COMMENT.getValue())) {
                result = TokenType.COMMENT;
            } else if (type.equals(TokenType.COMMENTBLOCK.getValue())) {
                result = TokenType.COMMENTBLOCK;
            } else if (type.equals(TokenType.ENDCOMMENTBLOCK.getValue())) {
                result = TokenType.ENDCOMMENTBLOCK;
            } else if (type.equals(TokenType.TEMPLATE.getValue())) {
                result = TokenType.TEMPLATE;
            } else if (type.equals(TokenType.ENDTEMPLATE.getValue())) {
                result = TokenType.ENDTEMPLATE;
            } else {
                result = TokenType.QUERY;
            }
        } else {
            result = TokenType.STATIC;
        }

        return result;
    }

    @Override
    public Block parseBlock(List<Template> templates, String header, TokenType... endTypes)
            throws DocumentParserException {
        final Block res = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
        TokenType type = getNextTokenType();
        Set<TokenType> endTypeSet = new HashSet<>(Arrays.asList(endTypes));
        endBlock: while (!endTypeSet.contains(type)) {
            switch (type) {
                case QUERY:
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
                case COMMENTBLOCK:
                    res.getStatements().add(parseCommentBlock());
                    break;
                case ELSEIF:
                case ELSE:
                case ENDFOR:
                case ENDIF:
                case ENDLET:
                case ENDBOOKMARK:
                case ENDUSERDOC:
                case ENDTEMPLATE:
                case ENDCOMMENTBLOCK:
                    // report the error and ignore the problem so that parsing
                    // continues in other parts of the document.
                    XWPFRun run = runIterator.lookAhead(1).getRun();
                    if (run == null) {
                        throw new IllegalStateException(
                                "Token of type " + type + " detected. Run shouldn't be null at this place.");
                    }
                    if (header == null) {
                        res.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                M2DocUtils.message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
                    } else {
                        res.getValidationMessages()
                                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, M2DocUtils.message(
                                        ParsingErrorMessage.UNEXPECTEDTAGWITHHEADER, type.getValue(), header), run));
                    }
                    if (!endTypeSet.contains(TokenType.EOF)) {
                        res.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.INFO,
                                M2DocUtils.message(ParsingErrorMessage.DIDYOUFORGETENDBLOCK, Arrays.toString(endTypes)),
                                run));
                    }
                    readTag(res, res.getRuns());
                    break;
                case EOF:
                    final XWPFParagraph lastParagraph = document.getParagraphs()
                            .get(document.getParagraphs().size() - 1);
                    final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
                    if (header == null) {
                        res.getValidationMessages()
                                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                        M2DocUtils.message(ParsingErrorMessage.UNEXPECTEDTAGMISSING, type,
                                                Arrays.toString(endTypes)),
                                        lastRun));
                    } else {
                        res.getValidationMessages()
                                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                        M2DocUtils.message(ParsingErrorMessage.UNEXPECTEDTAGMISSINGWITHHEADER, type,
                                                Arrays.toString(endTypes), header),
                                        lastRun));
                    }
                    break endBlock;
                case LET:
                    res.getStatements().add(parseLet());
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
                    res.getStatements().add(parseTable((XWPFTable) runIterator.next().getBodyElement()));
                    break;
                case CONTENTCONTROL:
                    res.getStatements().add(parseContentControl((XWPFSDT) runIterator.next().getBodyElement()));
                    break;
                case TEMPLATE:
                    if (templates != null) {
                        templates.add(parseTemplate());
                    } else {
                        // report the error and ignore the problem so that parsing
                        // continues in other parts of the document.
                        XWPFRun templateRun = runIterator.lookAhead(1).getRun();
                        if (templateRun == null) {
                            throw new IllegalStateException(
                                    "Token of type " + type + " detected. Run shouldn't be null at this place.");
                        }
                        res.getValidationMessages()
                                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                        M2DocUtils.message(ParsingErrorMessage.TEMPLATE_NOT_ALLOWED_IN_THIS_BLOCK),
                                        templateRun));
                        break endBlock;
                    }
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

        final String header = readTag(let, let.getRuns()).trim();
        final String tagText = header.substring(TokenType.LET.getValue().length()).trim();

        int currentIndex = 0;
        if (currentIndex < tagText.length() && Character.isJavaIdentifierStart(tagText.charAt(currentIndex))) {
            currentIndex++;
            while (currentIndex < tagText.length() && Character.isJavaIdentifierPart(tagText.charAt(currentIndex))) {
                currentIndex++;
            }
            let.setName(tagText.substring(0, currentIndex));
        } else {
            M2DocUtils.validationError(let, "Missing identifier");
        }

        while (currentIndex < tagText.length() && Character.isWhitespace(tagText.charAt(currentIndex))) {
            currentIndex++;
        }

        if (currentIndex < tagText.length() && tagText.charAt(currentIndex) == '=') {
            currentIndex++;
        } else {
            M2DocUtils.validationError(let, "Missing =");
        }

        while (currentIndex < tagText.length() && Character.isWhitespace(tagText.charAt(currentIndex))) {
            currentIndex++;
        }

        final String queryString = tagText.substring(currentIndex);
        final AstResult result = queryParser.build(queryString);
        let.setValue(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = let.getRuns().get(let.getRuns().size() - 1);
            let.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), queryString, lastRun));
        }

        final Block body = parseBlock(null, header, TokenType.ENDLET);
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
        final String tagText = readTag(query, query.getRuns()).trim();
        final String queryText = tagText.substring(TokenType.QUERY.getValue().length()).trim();
        try {
            final AstResult result = queryParser.build(queryText);
            query.setQuery(result);
            if (!result.getErrors().isEmpty()) {
                final XWPFRun lastRun = query.getRuns().get(query.getRuns().size() - 1);
                query.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), queryText, lastRun));
            }

            final boolean checkExtraSpace = !result.getErrors().isEmpty()
                || result.getEndPosition(result.getAst()) != queryText.length();
            for (TokenType tokenType : TokenType.values()) {
                final String value = tokenType.getValue();
                if (tokenType.needExtraSpacesCheck()) {
                    final Matcher matcher = EXTRA_SPACES_PATTERNS.get(tokenType).matcher(tagText);
                    if (matcher.find(0) && (matcher.end() == tagText.length() || checkExtraSpace)) {
                        M2DocUtils.validationInfo(query,
                                "You might want to replace " + matcher.group() + " by " + value);
                        break;
                    }
                }
            }
            // CHECKSTYLE:OFF
            // isolate AQL parsing exceptions.
        } catch (Exception e) {
            // CHECKSTYLE:ON
            final BasicDiagnostic diagnostic = new BasicDiagnostic();
            final String message = "Unable to parse AQL Expression check the syntax.";
            diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, M2DocUtils.PLUGIN_ID, 0, message, new Object[] {}));
            final XWPFRun lastRun = query.getRuns().get(query.getRuns().size() - 1);
            query.getValidationMessages().addAll(getValidationMessage(diagnostic, queryText, lastRun));

            query.setQuery(new AstResult(null, new HashMap<Object, Integer>(), new HashMap<Object, Integer>(),
                    new ArrayList<Error>(), diagnostic));
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
     * Parse a comment block construct. Comment block are of the form
     * <code>{m:commentblock some comment} runs {m:endcommentblock}</code>
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Comment parseCommentBlock() throws DocumentParserException {
        final Comment comment = (Comment) EcoreUtil.create(TemplatePackage.Literals.COMMENT);
        final String header = readTag(comment, comment.getRuns()).trim();
        final String commentText = header.substring(TokenType.COMMENTBLOCK.getValue().length());

        comment.setText(commentText.trim());

        final XWPFRun lastRun = comment.getRuns().get(comment.getRuns().size() - 1);
        // read up the tags until the "m:endcommentblock" tag is encountered.
        final Block body = parseBlock(null, header, TokenType.ENDCOMMENTBLOCK);
        // we discard the body, only keep messages from the block.
        for (TemplateValidationMessage message : body.getValidationMessages()) {
            comment.getValidationMessages()
                    .add(new TemplateValidationMessage(message.getLevel(), message.getMessage(), lastRun));
        }
        if (getNextTokenType() != TokenType.EOF) {
            readTag(comment, comment.getClosingRuns());
        }

        return comment;
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
        final Conditional conditional = (Conditional) EcoreUtil.create(TemplatePackage.Literals.CONDITIONAL);
        final String header = readTag(conditional, conditional.getRuns()).trim();
        final boolean headConditionnal = header.startsWith(TokenType.IF.getValue());
        final int tagLength = headConditionnal ? TokenType.IF.getValue().length()
                : TokenType.ELSEIF.getValue().length();
        final String query = header.substring(tagLength).trim();
        final AstResult result = queryParser.build(query);
        conditional.setCondition(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = conditional.getRuns().get(conditional.getRuns().size() - 1);
            conditional.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), query, lastRun));
        }
        final Block thenCompound = parseBlock(null, header, TokenType.ELSEIF, TokenType.ELSE, TokenType.ENDIF);
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
                final Block elseCompound = parseBlock(null, header + " ... " + TokenType.ELSE.getValue(),
                        TokenType.ENDIF);
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
                M2DocUtils.validationError(conditional,
                        M2DocUtils.message(ParsingErrorMessage.CONDTAGEXPEXTED, header));
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
        final String header = readTag(repetition, repetition.getRuns()).trim();
        // remove the prefix
        final String tagText = header.substring(TokenType.FOR.getValue().length());
        // extract the variable;
        int indexOfPipe = tagText.indexOf('|');
        if (indexOfPipe < 0) {
            M2DocUtils.validationError(repetition, "Malformed tag m:for, no '|' found.");
            final AstResult result = queryParser.build(null);
            repetition.setQuery(result);
            final Block body = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
            repetition.setBody(body);
        } else {
            String iterationVariable = tagText.substring(0, indexOfPipe).trim();
            if ("".equals(iterationVariable)) {
                M2DocUtils.validationError(repetition, "Malformed tag m:for : no iteration variable specified.");
            }
            repetition.setIterationVar(iterationVariable);
            if (tagText.length() == indexOfPipe + 1) {
                M2DocUtils.validationError(repetition,
                        "Malformed tag m:for : no query expression specified." + tagText);
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
        final Block body = parseBlock(null, header, TokenType.ENDFOR);
        repetition.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(repetition, repetition.getClosingRuns());
        }
        return repetition;
    }

    /**
     * Parse a template construct. Template are of the form
     * <code>{m:template myTemplate (param1 : Integer, param2 : Integer)} runs {m:endtemplate}</code>
     * 
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private Template parseTemplate() throws DocumentParserException {
        // first read the tag that opens the template
        final Template template = (Template) EcoreUtil.create(TemplatePackage.Literals.TEMPLATE);
        final String header = readTag(template, template.getRuns()).trim();
        // remove the prefix
        final String tagText = header.substring(TokenType.TEMPLATE.getValue().length());
        // extract the name
        final int indexOfOpenParenthesis = tagText.indexOf('(');
        if (indexOfOpenParenthesis < 0) {
            M2DocUtils.validationError(template, "Malformed tag m:template, no '(' found.");
            template.setName(tagText);
            final Block body = (Block) EcoreUtil.create(TemplatePackage.Literals.BLOCK);
            template.setBody(body);
        } else {
            final String name = tagText.substring(0, indexOfOpenParenthesis).trim();
            if ("".equals(name)) {
                M2DocUtils.validationError(template, "Malformed tag m:template : no name specified.");
            }
            template.setName(name);
            final int parametersStart = indexOfOpenParenthesis + 1;
            if (tagText.length() == parametersStart) {
                M2DocUtils.validationError(template, "Malformed tag m:template : parameted specified." + tagText);
            }
            final int indexOfCloseParenthesis = tagText.indexOf(')');
            final List<Parameter> parameters;
            if (indexOfCloseParenthesis < 0) {
                parameters = parseParameters(template, tagText.substring(indexOfOpenParenthesis));
                M2DocUtils.validationError(template, "Malformed tag m:template, no ')' found.");
            } else {
                parameters = parseParameters(template,
                        tagText.substring(indexOfOpenParenthesis + 1, indexOfCloseParenthesis));
            }
            if (parameters.isEmpty()) {
                M2DocUtils.validationError(template, "At least one parameter is needed.");
            } else {
                template.getParameters().addAll(parameters);
            }
        }
        // read up the tags until the "m:endtemplate" tag is encountered.
        final Block body = parseBlock(null, header, TokenType.ENDTEMPLATE);
        template.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(template, template.getClosingRuns());
        }
        return template;
    }

    /**
     * Parse a {@link List} of {@link Parameter} for the given {@link Template} from the given {@link String}.
     * 
     * @param template
     *            the {@link Template}
     * @param paramStr
     *            the {@link String} to parse
     * @return the created objects
     */
    private List<Parameter> parseParameters(Template template, String paramStr) {
        final List<Parameter> parameters = new ArrayList<>();

        final String[] prmStrs = paramStr.trim().split(",");
        if (!(prmStrs.length == 1 && prmStrs[0].trim().length() == 0)) {
            for (String prm : prmStrs) {
                parameters.add(parseParameter(template, prm.trim()));
            }
        }
        if (paramStr.endsWith(",")) {
            M2DocUtils.validationError(template, "Malformed prameter, no ':' found.");
        }

        return parameters;
    }

    /**
     * Parse a {@link Parameter} for the given {@link Template} from the given {@link String}.
     * 
     * @param template
     *            the {@link Template}
     * @param paramStr
     *            the {@link String} to parse
     * @return the created object
     */
    private Parameter parseParameter(Template template, String paramStr) {
        final Parameter parameter = (Parameter) EcoreUtil.create(TemplatePackage.Literals.PARAMETER);

        // extract the name
        int indexOfColon = paramStr.indexOf(':');
        if (indexOfColon < 0) {
            M2DocUtils.validationError(template, "Malformed prameter, no ':' found.");
            parameter.setName(paramStr);
            final AstResult type = parseWhileAqlTypeLiteral("");
            parameter.setType(type);
            if (!type.getErrors().isEmpty()) {
                final XWPFRun lastRun = template.getRuns().get(template.getRuns().size() - 1);
                template.getValidationMessages().addAll(getValidationMessage(type.getDiagnostic(), "", lastRun));
            }
        } else {
            final String name = paramStr.substring(0, indexOfColon).trim();
            if ("".equals(name)) {
                M2DocUtils.validationError(template, "Malformed parameter no name specified.");
            }
            parameter.setName(name);
            final AstResult type = parseWhileAqlTypeLiteral(paramStr.substring(indexOfColon + 1));
            parameter.setType(type);
            if (!type.getErrors().isEmpty()) {
                final XWPFRun lastRun = template.getRuns().get(template.getRuns().size() - 1);
                template.getValidationMessages().addAll(getValidationMessage(type.getDiagnostic(), name, lastRun));
            }
        }

        return parameter;
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

        final String header = readTag(bookmark, bookmark.getRuns()).trim();
        // remove the prefix
        final String tagText = header.substring(TokenType.BOOKMARK.getValue().length()).trim();
        final AstResult result = queryParser.build(tagText);
        bookmark.setName(result);
        if (!result.getErrors().isEmpty()) {
            final XWPFRun lastRun = bookmark.getRuns().get(bookmark.getRuns().size() - 1);
            bookmark.getValidationMessages().addAll(getValidationMessage(result.getDiagnostic(), tagText, lastRun));
        }
        // read up the tags until the "m:endbookmark" tag is encountered.
        final Block body = parseBlock(null, header, TokenType.ENDBOOKMARK);
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
        final String header = readTag(userDoc, userDoc.getRuns()).trim();
        // remove the prefix
        final String tagText = header.substring(TokenType.USERDOC.getValue().length()).trim();

        final AstResult id = queryParser.build(tagText);
        userDoc.setId(id);
        if (!id.getErrors().isEmpty()) {
            final XWPFRun lastRun = userDoc.getRuns().get(userDoc.getRuns().size() - 1);
            userDoc.getValidationMessages().addAll(getValidationMessage(id.getDiagnostic(), tagText, lastRun));
        }

        // read up the tags until the "m:enduserdoc" tag is encountered.
        final Block body = parseBlock(null, header, TokenType.ENDUSERDOC);
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
            AstBuilderListener astBuilder = AQL56Compatibility.createAstBuilderListener(queryEnvironment);
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
            List<org.eclipse.acceleo.query.ast.Error> errors = new ArrayList<>(1);
            errors.add(errorExpression);
            final Map<Object, Integer> positions = new HashMap<>();
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

    /**
     * Parses while matching an AQL type literal.
     * 
     * @param expression
     *            the expression to parse
     * @return the corresponding {@link AstResult}
     */
    protected AstResult parseWhileAqlTypeLiteral(String expression) {
        final IQueryBuilderEngine.AstResult result;

        if (expression != null && expression.length() > 0) {
            AstBuilderListener astBuilder = AQL56Compatibility.createAstBuilderListener(queryEnvironment);
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
            parser.typeLiteral();
            result = astBuilder.getAstResult();
        } else {
            ErrorTypeLiteral errorTypeLiteral = (ErrorTypeLiteral) EcoreUtil
                    .create(AstPackage.eINSTANCE.getErrorTypeLiteral());
            List<org.eclipse.acceleo.query.ast.Error> errs = new ArrayList<>(1);
            errs.add(errorTypeLiteral);
            final Map<Object, Integer> positions = new HashMap<>();
            if (expression != null) {
                positions.put(errorTypeLiteral, Integer.valueOf(0));
            }
            final BasicDiagnostic diagnostic = new BasicDiagnostic();
            diagnostic.add(new BasicDiagnostic(Diagnostic.ERROR, AstBuilderListener.PLUGIN_ID, 0,
                    "missing type literal", new Object[] {errorTypeLiteral }));
            result = new AstResult(errorTypeLiteral, positions, positions, errs, diagnostic);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.parser.BodyAbstractParser#getNewParser(org.apache.poi.xwpf.usermodel.IBody)
     */
    @Override
    protected AbstractBodyParser getNewParser(IBody inputDocument) {
        AbstractBodyParser parser = new M2DocParser(inputDocument, this.queryParser, this.queryEnvironment);
        return parser;
    }
}
