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
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;

import static org.obeonetwork.m2doc.util.M2DocUtils.message;

/**
 * Body parser for destination document (result of M2Doc generation document).
 * This
 * 
 * @author ohaegi
 */
public class BodyGeneratedParser extends AbstractBodyParser {

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
    private BodyGeneratedParser(IBody inputDocument, IQueryBuilderEngine queryParser,
            IQueryEnvironment queryEnvironment) {
        super(inputDocument, queryParser, queryEnvironment);
    }

    @Override
    protected TokenType getNextTokenMTag(ParsingToken token) {
        final TokenType result;

        final XWPFRun run = token.getRun();
        // is run a field begin run
        if (fieldUtils.isFieldBegin(run)) {
            String type = getType(fieldUtils.lookAheadTag(runIterator));
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
    public Block parseBlock(List<Template> templates, TokenType... endTypes) throws DocumentParserException {
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
        String tagText = readTag(userContent, userContent.getRuns()).trim();
        // remove the prefix
        tagText = tagText.substring(TokenType.USERCONTENT.getValue().length()).trim();

        if (tagText == null || "".equals(tagText)) {
            final XWPFRun lastRun = userContent.getRuns().get(userContent.getRuns().size() - 1);
            TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.ERROR, ParsingErrorMessage.INVALID_USERCONTENT_VALUE.getMessage(), lastRun);
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
        final Block body = parseBlock(null, TokenType.ENDUSERCONTENT);
        userContent.setBody(body);
        if (getNextTokenType() != TokenType.EOF) {
            readTag(userContent, userContent.getClosingRuns());
        }

        return userContent;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.obeonetwork.m2doc.parser.BodyAbstractParser#getNewParser(org.apache.poi.xwpf.usermodel.IBody)
     */
    @Override
    protected AbstractBodyParser getNewParser(IBody inputDocument) {
        AbstractBodyParser parser = new BodyGeneratedParser(inputDocument, this.queryParser, this.queryEnvironment);
        return parser;
    }
}
