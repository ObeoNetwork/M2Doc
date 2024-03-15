/*******************************************************************************
 *  Copyright (c) 2016, 2024 Obeo. 
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
import java.util.List;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.QueryParsing;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.util.FieldUtils;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;

import static org.obeonetwork.m2doc.util.FieldUtils.readUpInstrText;
import static org.obeonetwork.m2doc.util.M2DocUtils.message;

/**
 * Abstract Body Parser.
 * Common feature to parse template and m2doc result generated file (to find userdocdes tag).
 * 
 * @author ohaegi
 */
public abstract class AbstractBodyParser {

    /**
     * Parsed template document.
     */
    protected IBody document;
    /**
     * iterator over the document used to access {@link XWPFRun} instances in
     * sequence.
     */
    protected TokenProvider runIterator;
    /**
     * {@link IQueryBuilderEngine} used to parse AQL queries.
     */
    protected IQueryBuilderEngine queryParser;

    /**
     * The {@link IQueryEnvironment}.
     */
    protected final IQueryEnvironment queryEnvironment;

    /**
     * The {@link FieldUtils}.
     */
    protected final FieldUtils fieldUtils;

    /**
     * Creates a new {@link M2DocParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryEnvironment
     *            the query environment to used during parsing.
     */
    public AbstractBodyParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = QueryParsing.newBuilder();
        this.queryEnvironment = queryEnvironment;
        this.fieldUtils = new FieldUtils();
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
    protected AbstractBodyParser(IBody inputDocument, IQueryBuilderEngine queryParser,
            IQueryEnvironment queryEnvironment) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = queryParser;
        this.queryEnvironment = queryEnvironment;
        this.fieldUtils = new FieldUtils();
    }

    /**
     * returns the next token type after index.
     * 
     * @return the next token type.
     */
    protected TokenType getNextTokenType() {
        final ParsingToken token = runIterator.lookAhead(1);
        final TokenType result;
        if (token == null) {
            result = TokenType.EOF;
        } else if (token.getKind() == ParsingTokenKind.TABLE) {
            result = TokenType.WTABLE;
        } else if (token.getKind() == ParsingTokenKind.CONTENTCONTROL) {
            result = TokenType.CONTENTCONTROL;
        } else {
            result = getNextTokenMTag(token);
        }
        return result;
    }

    /**
     * Gets the {@link TokenType} of the given {@link ParsingToken}.
     * 
     * @param token
     *            the {@link ParsingToken}
     * @return the {@link TokenType} of the given {@link ParsingToken}
     */
    protected abstract TokenType getNextTokenMTag(ParsingToken token);

    /**
     * Gets the type {@link String} for the given tag if starting with {@link M2DocUtils#M}.
     * 
     * @param tag
     *            the tag {@link String}
     * @return the type {@link String} for the given tag if starting with {@link M2DocUtils#M}, <code>null</code> otherwise
     */
    protected String getType(String tag) {
        final StringBuilder res = new StringBuilder();

        if (tag.startsWith(M2DocUtils.M)) {
            res.append(M2DocUtils.M);
            for (int i = 2; i < tag.length(); i++) {
                final char currentChar = tag.charAt(i);
                if (Character.isAlphabetic(currentChar) || Character.isDigit(currentChar)) {
                    res.append(currentChar);
                } else {
                    break;
                }
            }

            return res.toString();
        } else {
            return null;
        }
    }

    /**
     * Parses a {@link Block}.
     * 
     * @param templates
     *            an empty {@link List} if {@link Template} are allowed in this {@link Block}, <code>null</code> otherwise
     * @param endTypes
     *            the token types that mark the end of the parsed compound
     * @return the parsed {@link Block}
     * @throws DocumentParserException
     *             if a problem occurs while parsing
     */
    public Block parseBlock(List<Template> templates, TokenType... endTypes) throws DocumentParserException {
        return parseBlock(templates, null, endTypes);
    }

    /**
     * Parses a {@link Block}.
     * 
     * @param templates
     *            an empty {@link List} if {@link Template} are allowed in this {@link Block}, <code>null</code> otherwise
     * @param header
     *            the header of the current {@link Block}
     * @param endTypes
     *            the token types that mark the end of the parsed compound
     * @return the parsed {@link Block}
     * @throws DocumentParserException
     *             if a problem occurs while parsing
     */
    public abstract Block parseBlock(List<Template> templates, String header, TokenType... endTypes)
            throws DocumentParserException;

    /**
     * Reads up a tag so that it can be parsed as a simple string.
     * 
     * @param construct
     *            the construct to read tag to
     * @param runsToFill
     *            the run list to fill
     * @return the string present into the tag as typed by the template author.
     */
    protected String readTag(IConstruct construct, List<XWPFRun> runsToFill) {
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
            final String runText = readUpInstrText(run).toString();
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
    protected List<TemplateValidationMessage> getValidationMessage(Diagnostic diagnostic, String queryText,
            XWPFRun location) {
        final List<TemplateValidationMessage> res = new ArrayList<>();

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
     * Parses a {@link StaticFragment}.
     * 
     * @return the object created
     * @throws DocumentParserException
     *             if something wrong happens during parsing
     */
    protected StaticFragment parseStaticFragment() throws DocumentParserException {
        StaticFragment result = (StaticFragment) EcoreUtil.create(TemplatePackage.Literals.STATIC_FRAGMENT);
        while (getNextTokenType() == TokenType.STATIC) {
            result.getRuns().add(runIterator.next().getRun());
        }
        return result;
    }

    /**
     * Parses a {@link Table}.
     * 
     * @param wtable
     *            the table to parse
     * @return the created object
     * @throws DocumentParserException
     *             if a problem occurs while parsing.
     */
    protected Table parseTable(XWPFTable wtable) throws DocumentParserException {
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
                AbstractBodyParser parser = getNewParser(tableCell);
                cell.setBody(parser.parseBlock(null, TokenType.EOF));
            }
        }
        return table;
    }

    /**
     * Parses a {@link ContentControl}.
     * 
     * @param control
     *            the {@link XWPFSDT}
     * @return the parsed {@link ContentControl}
     */
    protected ContentControl parseContentControl(XWPFSDT control) {
        if (control == null) {
            throw new IllegalArgumentException("parseContentControl can't be called on a null argument.");
        }
        ContentControl contentControl = (ContentControl) EcoreUtil.create(TemplatePackage.Literals.CONTENT_CONTROL);
        contentControl.setBlock(getCTSdtBlock(document, control));

        return contentControl;
    }

    /**
     * Gets the {@link CTSdtBlock} corresponding to the given {@link ContentControl}.
     * 
     * @param body
     *            the {@link IBody}
     * @param sdt
     *            the {@link XWPFSDT}
     * @return the {@link CTSdtBlock} corresponding to the given {@link ContentControl}
     */
    public static CTSdtBlock getCTSdtBlock(IBody body, XWPFSDT sdt) {
        final CTSdtBlock res;

        int sdtIndex = -1;
        for (IBodyElement element : body.getBodyElements()) {
            if (element instanceof XWPFSDT) {
                sdtIndex++;
                if (element == sdt) {
                    break;
                }
            }
        }

        if (sdtIndex > -1) {
            res = getCTSdtBlock(body, sdtIndex);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link CTSdtBlock} at the given sdtIndex in the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody}
     * @param sdtIndex
     *            the index in internal sdt list
     * @return the {@link CTSdtBlock} at the given sdtIndex in the given {@link IBody}
     */
    private static CTSdtBlock getCTSdtBlock(IBody body, int sdtIndex) {
        final CTSdtBlock res;

        if (body instanceof XWPFDocument) {
            res = ((XWPFDocument) body).getDocument().getBody().getSdtArray(sdtIndex);
        } else if (body instanceof XWPFHeaderFooter) {
            res = ((XWPFHeaderFooter) body)._getHdrFtr().getSdtArray(sdtIndex);
        } else if (body instanceof XWPFFootnote) {
            res = ((XWPFFootnote) body).getCTFtnEdn().getSdtArray(sdtIndex);
        } else if (body instanceof XWPFTableCell) {
            res = ((XWPFTableCell) body).getCTTc().getSdtArray(sdtIndex);
        } else {
            throw new IllegalStateException("can't insert control in " + body.getClass().getCanonicalName());
        }

        return res;
    }

    /**
     * Get new Parser.
     * 
     * @param inputDocument
     *            Document to parse
     * @return new Template parser
     */
    protected abstract AbstractBodyParser getNewParser(IBody inputDocument);

}
