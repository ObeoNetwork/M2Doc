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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Compound;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.util.FieldUtils;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Sets.difference;

import static org.obeonetwork.m2doc.util.FieldUtils.readUpInstrText;
import static org.obeonetwork.m2doc.util.M2DocUtils.message;

/**
 * Abstract Body Parser.
 * Common feature to parse template and m2doc result generated file (to find userdocdes tag).
 * 
 * @author ohaegi
 */
@SuppressWarnings("restriction")
public abstract class BodyAbstractParser {

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
     * Creates a new {@link BodyTemplateParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryEnvironment
     *            the query environment to used during parsing.
     */
    public BodyAbstractParser(IBody inputDocument, IQueryEnvironment queryEnvironment) {
        this.document = inputDocument;
        runIterator = new TokenProvider(inputDocument);
        this.queryParser = new QueryBuilderEngine(queryEnvironment);
        this.queryEnvironment = queryEnvironment;
        this.fieldUtils = new FieldUtils();
    }

    /**
     * Creates a new {@link BodyTemplateParser} instance.
     * 
     * @param inputDocument
     *            the input template to parser
     * @param queryParser
     *            the query parser to use during parsing
     * @param queryEnvironment
     *            The {@link IQueryEnvironment}
     */
    BodyAbstractParser(IBody inputDocument, IQueryBuilderEngine queryParser, IQueryEnvironment queryEnvironment) {
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
    protected abstract TokenType getNextTokenType();

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
    protected abstract void parseCompound(Compound compound, TokenType... endTypes) throws DocumentParserException;

    /**
     * Reads up a tag so that it can be parsed as a simple string.
     * 
     * @param construct
     *            the construct to read tag to
     * @param runsToFill
     *            the run list to fill
     * @return the string present into the tag as typed by the template author.
     */
    // CHECKSTYLE:OFF
    protected String readTag(AbstractConstruct construct, List<XWPFRun> runsToFill) {
        XWPFRun run = this.runIterator.lookAhead(1).getRun();
        if (run == null) {
            throw new IllegalStateException("readTag shouldn't be called with a table in the lookahead window.");
        }
        XWPFRun styleRun = null;
        boolean columnRead = false;
        if (run != null && fieldUtils.isFieldBegin(run)) {
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
                if (fieldUtils.isFieldEnd(run)) {
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
    // CHECKSTYLE:ON

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
     * parses a static fragment.
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
     * Parses a table.
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
                BodyAbstractParser parser = getNewParser(tableCell);
                cell.setTemplate(parser.parseTemplate());
            }
        }
        return table;
    }

    /**
     * Get new Parser.
     * 
     * @param inputDocument
     *            Document to parse
     * @return new Template parser
     */
    protected abstract BodyAbstractParser getNewParser(IBody inputDocument);

    /**
     * Class to easily validate a list of options against options declared by a provider.
     * 
     * @author ldelaigue
     */
    protected static final class OptionChecker {
        /** The provider options, considered mandatory. */
        private final Map<String, OptionType> providerOptions;
        /** The options to ignore. */
        private Set<String> optionsToIgnore;

        /**
         * Constructor.
         * 
         * @param providerOptions
         *            The map opf provider options, cannot be <code>null</code>
         */
        private OptionChecker(Map<String, OptionType> providerOptions) {
            this.providerOptions = checkNotNull(providerOptions);
            optionsToIgnore = new LinkedHashSet<String>();
        }

        /**
         * Static method to instantiate an OptionChecker more easily.
         * 
         * @param providerOptions
         *            The provider options, cannot be <code>null</code>
         * @return A new instance of OptionChecker.
         */
        public static OptionChecker check(Map<String, OptionType> providerOptions) {
            return new OptionChecker(providerOptions);
        }

        /**
         * Register the given option names as options to ignore. If these options are provided but are not present in the provider options,
         * they won't cause the candidate to be rejected.
         * 
         * @param options
         *            The options to ignore
         * @return this, for a fluent API.
         */
        public OptionChecker ignore(String... options) {
            for (String option : options) {
                if (option != null) {
                    optionsToIgnore.add(option);
                }
            }
            return this;
        }

        /**
         * Check whether the given map of options is compatible with the provider options, taking into account the options to ignore and the
         * rejectAdditionalOptions flag.
         * 
         * @param options
         *            Options to check
         * @return <code>true</code> if the given options are compatible with the provider options, taking the checker configuration into
         *         account (options to ignore, reject additinoal options).
         */
        public boolean against(Map<String, String> options) {
            for (Entry<String, OptionType> entry : providerOptions.entrySet()) {
                if (!options.containsKey(entry.getKey())) {
                    return false;
                }
            }
            return difference(difference(options.keySet(), providerOptions.keySet()), optionsToIgnore).isEmpty();
        }
    }
}
