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

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.template.Compound;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;

import static org.obeonetwork.m2doc.util.M2DocUtils.message;

/**
 * Body parser for destination document (result of M2Doc generation document).
 * This
 * 
 * @author ohaegi
 */
public class BodyGeneratedParser extends BodyAbstractParser {

    /**
     * User Conetnt Ids list.
     * Used for uniqueness test.
     */
    private List<String> userContentIds = new ArrayList<String>();

    /**
     * Creates a new {@link BodyGeneratedParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryEnvironment
     *            the query environment to used during parsing.
     */
    public BodyGeneratedParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
        super(inputDocument, queryEnvironment);
    }

    /**
     * Creates a new {@link BodyGeneratedParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryParser
     *            the query parser to use during parsing
     * @param queryEnvironment
     *            The {@link IQueryEnvironment}
     */
    BodyGeneratedParser(IBody inputDocument, IQueryBuilderEngine queryParser, IQueryEnvironment queryEnvironment) {
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
                if (code.startsWith(TokenType.ENDUSERCONTENT.getValue())) {
                    result = TokenType.ENDUSERCONTENT;
                } else if (code.startsWith(TokenType.USERCONTENT.getValue())) {
                    result = TokenType.USERCONTENT;
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
     * Parses a compound object.
     * 
     * @param compound
     *            the compound to parse
     * @param endTypes
     *            the token types that mark the end of the parsed compound
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    @Override
    protected void parseCompound(Compound compound, TokenType... endTypes) throws DocumentParserException {
        TokenType type = getNextTokenType();
        List<TokenType> endTypeList = Lists.newArrayList(endTypes);
        while (!endTypeList.contains(type)) {
            switch (type) {
                case USERCONTENT:
                    compound.getSubConstructs().add(parseUserContent());
                    break;
                case ENDUSERCONTENT:
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
                case STATIC:
                    compound.getSubConstructs().add(parseStaticFragment());
                    break;
                case WTABLE:
                    compound.getSubConstructs().add(parseTable(runIterator.next().getTable()));
                    break;
                default:
                    throw new UnsupportedOperationException(
                            String.format("Developer error: TokenType %s is not supported", type));
            }
            type = getNextTokenType();
        }
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
        // first read the tag that opens the link
        final UserContent userContent = (UserContent) EcoreUtil.create(TemplatePackage.Literals.USER_CONTENT);
        String tagText = readTag(userContent, userContent.getRuns()).trim();
        // remove the prefix
        tagText = tagText.substring(TokenType.USERCONTENT.getValue().length()).trim();

        if (tagText == null || "".equals(tagText)) {
            final XWPFRun lastRun = userContent.getRuns().get(userContent.getRuns().size() - 1);
            TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.WARNING, ParsingErrorMessage.INVALID_USERCONTENT_VALUE.getMessage(),
                    lastRun);
            userContent.getValidationMessages().add(templateValidationMessage);
        } else {
            userContent.setId(tagText);
            if (userContentIds.contains(tagText)) {
                final XWPFRun lastRun = userContent.getRuns().get(userContent.getRuns().size() - 1);
                TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                        ValidationMessageLevel.WARNING,
                        message(ParsingErrorMessage.INVALID_USERDOC_ID_NOT_UNIQUE, tagText), lastRun);
                userContent.getValidationMessages().add(templateValidationMessage);
            } else {
                userContentIds.add(tagText);
            }
        }

        // read up the tags until the "m:enduserdoc" tag is encountered.
        parseCompound(userContent, TokenType.ENDUSERCONTENT);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(userContent, userContent.getClosingRuns());
        }

        return userContent;
    }
}
