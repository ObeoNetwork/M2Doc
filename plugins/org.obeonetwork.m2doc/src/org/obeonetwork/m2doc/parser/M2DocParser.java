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
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
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
@SuppressWarnings("restriction")
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
        final Map<TokenType, Pattern> res = new HashMap<TokenType, Pattern>();

        for (TokenType tokenType : TokenType.values()) {
            if (tokenType.needExtraSpacesCheck()) {
                final String value = tokenType.getValue();
                final String tag = value.substring(TokenType.QUERY.getValue().length());
                res.put(tokenType, Pattern.compile("m:\\s+" + tag));
            }
        }

        return res;
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
        } else if (token.getKind() == ParsingTokenKind.CONTENTCONTROL) {
            result = TokenType.CONTENTCONTROL;
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
                } else if (code.startsWith(TokenType.LET.getValue())) {
                    result = TokenType.LET;
                } else if (code.startsWith(TokenType.ENDLET.getValue())) {
                    result = TokenType.ENDLET;
                } else if (code.startsWith(TokenType.BOOKMARK.getValue())) {
                    result = TokenType.BOOKMARK;
                } else if (code.startsWith(TokenType.ENDBOOKMARK.getValue())) {
                    result = TokenType.ENDBOOKMARK;
                } else if (code.startsWith(TokenType.LINK.getValue())) {
                    result = TokenType.LINK;
                } else if (code.startsWith(TokenType.COMMENT.getValue())) {
                    result = TokenType.COMMENT;
                } else if (code.startsWith(TokenType.QUERY.getValue())) {
                    result = TokenType.QUERY;
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
        Set<TokenType> endTypeList = new HashSet<TokenType>(Arrays.asList(endTypes));
        endBlock: while (!endTypeList.contains(type)) {
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
                            M2DocUtils.message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
                    readTag(res, res.getRuns());
                    break;
                case EOF:
                    final XWPFParagraph lastParagraph = document.getParagraphs()
                            .get(document.getParagraphs().size() - 1);
                    final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
                    res.getValidationMessages()
                            .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, M2DocUtils
                                    .message(ParsingErrorMessage.UNEXPECTEDTAGMISSING, type, Arrays.toString(endTypes)),
                                    lastRun));
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
            M2DocUtils.validationError(let, "Missing identifier");
        }

        while (currentIndex < letText.length() && Character.isWhitespace(letText.charAt(currentIndex))) {
            currentIndex++;
        }

        if (currentIndex < letText.length() && letText.charAt(currentIndex) == '=') {
            currentIndex++;
        } else {
            M2DocUtils.validationError(let, "Missing =");
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
        final String tagText = readTag(query, query.getRuns()).trim();
        final String queryText = tagText.substring(TokenType.QUERY.getValue().length()).trim();
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
                    M2DocUtils.validationInfo(query, "You might want to replace " + matcher.group() + " by " + value);
                    break;
                }
            }
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
                M2DocUtils.validationError(conditional, M2DocUtils.message(ParsingErrorMessage.CONDTAGEXPEXTED));
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
    protected AbstractBodyParser getNewParser(IBody inputDocument) {
        AbstractBodyParser parser = new M2DocParser(inputDocument, this.queryParser, this.queryEnvironment);
        return parser;
    }
}
