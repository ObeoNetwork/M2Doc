/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.util.FieldUtils;

import static org.obeonetwork.m2doc.util.M2DocUtils.message;

/**
 * Body parser for destination document (result of M2Doc generation document).
 * This
 * 
 * @author ohaegi
 */
public class BodyGeneratedParser extends AbstractBodyParser {

    /**
     * The {@link FieldUtils}.
     */
    protected final FieldUtils fieldUtils = new FieldUtils();

    /**
     * User Conetnt Ids list.
     * Used for uniqueness test.
     */
    private List<String> userContentIds = new ArrayList<>();

    /**
     * Creates a new {@link BodyGeneratedParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     */
    public BodyGeneratedParser(IBody inputDocument) {
        super(inputDocument);
    }

    /**
     * Creates a new {@link BodyGeneratedParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryParser
     *            the query parser to use during parsing
     */
    private BodyGeneratedParser(IBody inputDocument, IQueryBuilderEngine queryParser) {
        super(inputDocument, queryParser);
    }

    @Override
    protected TokenProvider createTokenProvider(IBody inputDocument) {
        return new TokenProvider(new TokenIterator(inputDocument));
    }

    @Override
    protected TokenType getNextTokenMTag(ParsingToken token) {
        final TokenType result;

        final XWPFRun run = token.getRun();
        // is run a field begin run
        if (fieldUtils.isFieldBegin(run)) {
            String type = getType(fieldUtils.lookAheadFieldTag(runIterator));
            if (type == null) {
                result = TokenType.STATIC;
            } else if (type.equals(TokenType.ENDUSERCONTENT.getValue())) {
                result = TokenType.ENDUSERCONTENT;
            } else if (type.equals(TokenType.USERCONTENT.getValue())) {
                result = TokenType.USERCONTENT;
            } else {
                result = TokenType.STATIC;
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
                case USERCONTENT:
                    res.getStatements().add(parseUserContent());
                    break;
                case ENDUSERCONTENT:
                    // report the error and ignore the problem so that parsing
                    // continues in other parts of the document.
                    XWPFRun run = runIterator.lookAhead(1).getRun();
                    if (run == null) {
                        throw new IllegalStateException(
                                "Token of type " + type + " detected. Run shouldn't be null at this place.");
                    }
                    if (header == null) {
                        res.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                message(ParsingErrorMessage.UNEXPECTEDTAG, type.getValue()), run));
                    } else {
                        res.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                message(ParsingErrorMessage.UNEXPECTEDTAGWITHHEADER, type.getValue(), header), run));
                    }
                    readFieldTag(res, res.getRuns());
                    break;
                case EOF:
                    final XWPFParagraph lastParagraph = document.getParagraphs()
                            .get(document.getParagraphs().size() - 1);
                    final XWPFRun lastRun = lastParagraph.getRuns().get(lastParagraph.getRuns().size() - 1);
                    if (header == null) {
                        res.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                message(ParsingErrorMessage.UNEXPECTEDTAGMISSING, type, Arrays.toString(endTypes)),
                                lastRun));
                    } else {
                        res.getValidationMessages()
                                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR,
                                        message(ParsingErrorMessage.UNEXPECTEDTAGMISSINGWITHHEADER, type,
                                                Arrays.toString(endTypes), header),
                                        lastRun));
                    }
                    break endBlock;
                case STATIC:
                    res.getStatements().add(parseStaticFragment());
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
     * Parses a user Document destination part.
     * user Document destination part are made of the following set of tags : {m:userdoccontent id} ...
     * ... {m:endusercontent}.
     * userContent is generated by M2Doc and the parser extract user document part.
     * 
     * @author ohaegi
     * @return the created object
     * @throws DocumentParserException
     *             if something wrong happens during parsing.
     */
    private UserContent parseUserContent() throws DocumentParserException {
        // first read the tag that opens the user content
        final UserContent userContent = (UserContent) EcoreUtil.create(TemplatePackage.Literals.USER_CONTENT);
        final String header = readFieldTag(userContent, userContent.getRuns()).trim();
        // remove the prefix
        final String id = header.substring(TokenType.USERCONTENT.getValue().length()).trim();

        if (id == null || "".equals(id)) {
            final XWPFRun lastRun = userContent.getRuns().get(userContent.getRuns().size() - 1);
            TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.ERROR, ParsingErrorMessage.INVALID_USERCONTENT_VALUE.getMessage(), lastRun);
            userContent.getValidationMessages().add(templateValidationMessage);
        } else {
            userContent.setId(id);
            if (userContentIds.contains(id)) {
                final XWPFRun lastRun = userContent.getRuns().get(userContent.getRuns().size() - 1);
                TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                        ValidationMessageLevel.WARNING, message(ParsingErrorMessage.INVALID_USERDOC_ID_NOT_UNIQUE, id),
                        lastRun);
                userContent.getValidationMessages().add(templateValidationMessage);
            } else {
                userContentIds.add(id);
            }
        }

        // read up the tags until the "m:enduserdoc" tag is encountered.
        final Block body = parseBlock(null, header, TokenType.ENDUSERCONTENT);
        userContent.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readFieldTag(userContent, userContent.getClosingRuns());
        }

        return userContent;
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
    protected String readFieldTag(IConstruct construct, List<XWPFRun> runsToFill) {
        XWPFRun run = this.runIterator.lookAhead(1).getRun();
        if (run == null) {
            throw new IllegalStateException("readTag shouldn't be called with a table in the lookahead window.");
        } else if (!fieldUtils.isFieldBegin(run)) {
            throw new IllegalStateException("Shouldn't call readTag if the current run doesn't start a field");
        }

        final StringBuilder result = new StringBuilder();

        runsToFill.add(runIterator.next().getRun()); // Consume begin field
        XWPFRun styleRun = null;
        boolean columnRead = false;
        while (runIterator.hasNext()) {
            run = runIterator.next().getRun();
            if (run == null) {
                // XXX : treat this as a proper parsing error.
                throw new IllegalArgumentException("table cannot be inserted into tags.");
            }
            runsToFill.add(run);
            if (fieldUtils.isFieldEnd(run)) {
                break;
            }
            final String runText = fieldUtils.readUpInstrText(run);
            result.append(runText);
            // the style run hasn't been discovered yet.
            if (styleRun == null) {
                if (columnRead && !runText.isEmpty()) {
                    styleRun = run;
                    construct.setStyleRun(styleRun);
                } else {
                    final int indexOfColumn = runText.indexOf(':');
                    columnRead = indexOfColumn >= 0;
                    if (columnRead && indexOfColumn < runText.length() - 1) {
                        styleRun = run; // ':' doesn't appear at the end of the string
                        construct.setStyleRun(styleRun);
                    } // otherwise, use the next non empty run.
                }
            }
        }

        return result.toString();
    }

    @Override
    protected AbstractBodyParser getNewParser(IBody inputDocument) {
        AbstractBodyParser parser = new BodyGeneratedParser(inputDocument, this.queryParser);
        return parser;
    }
}
