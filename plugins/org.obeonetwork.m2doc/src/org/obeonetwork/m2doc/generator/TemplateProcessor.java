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
package org.obeonetwork.m2doc.generator;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TableClient;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;
import org.obeonetwork.m2doc.util.FieldUtils;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

/**
 * The {@link TemplateProcessor} class implements a switch over template that generates the doc.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public class TemplateProcessor extends TemplateSwitch<IConstruct> {

    /**
     * Error message when AQL query could not be evaluated.
     */
    private static final String QUERY_EVALERROR_MESSAGE = "Couldn't evaluate query.";
    /**
     * Error message when an AQL query contains syntax errors.
     */
    private static final String QUERY_SYNTAX_ERROR_MESSAGE = "Syntax error in AQL expression: ";

    /**
     * The {@link BookmarkManager}.
     */
    private final BookmarkManager bookmarkManager;
    /**
     * variable definition used during generation.
     */
    private final Stack<Map<String, Object>> variablesStack = new Stack<Map<String, Object>>();
    /**
     * The generated document.
     */
    private IBody generatedDocument;
    /**
     * The currently read template paragraph used to detect paragraph changes.
     */
    private XWPFParagraph currentTemplateParagraph;
    /**
     * The currently generated paragraph where runs are actually inserted.
     */
    private XWPFParagraph currentGeneratedParagraph;
    /**
     * The currently generated {@link XWPFTable}.
     */
    private XWPFTable currentGeneratedTable;
    /**
     * The currently generated {@link XWPFTableRow}.
     */
    private XWPFTableRow currentGeneratedRow;
    /**
     * Used to force a new paragraph in gf:for body when there's a carriage
     * return before the {m:endfor} tag.
     */
    private boolean forceNewParagraph;
    /**
     * An EObject from the conf model from which the generation has been called.
     */
    private EObject targetConfObject;

    /**
     * The {@link Set} of used {@link AbstractDiagramProvider}.
     */
    private Set<AbstractDiagramProvider> usedProviders = Sets.newLinkedHashSet();

    /**
     * Last Destination UserContent Manager.
     */
    private UserContentManager userContentManager;

    /**
     * User Doc Ids list.
     * Used for uniqueness test.
     */
    private List<String> userDocIds = new ArrayList<>();

    /**
     * The {@link IQueryEvaluationEngine}.
     */
    private final IQueryEvaluationEngine evaluator;

    /**
     * Create a new {@link TemplateProcessor} instance given some definitions
     * and a query environment.
     * 
     * @param initialDefs
     *            the definitions used in queries and variable tags
     * @param bookmarkManager
     *            the {@link BookmarkManager}
     * @param userContentManager
     *            the {@link UserContentManager}
     * @param queryEnvironment
     *            the query environment used to evaluate queries in the
     * @param destinationDocument
     *            the path to the destination document.
     * @param theTargetConfObject
     *            the root EObject of the gen conf model.
     */
    public TemplateProcessor(Map<String, Object> initialDefs, BookmarkManager bookmarkManager,
            UserContentManager userContentManager, IReadOnlyQueryEnvironment queryEnvironment,
            IBody destinationDocument, EObject theTargetConfObject) {
        variablesStack.push(initialDefs);
        this.bookmarkManager = bookmarkManager;
        this.userContentManager = userContentManager;
        this.evaluator = new QueryEvaluationEngine((IQueryEnvironment) queryEnvironment);
        this.generatedDocument = destinationDocument;
        this.targetConfObject = theTargetConfObject;
    }

    @Override
    public IConstruct caseDocumentTemplate(DocumentTemplate documentTemplate) {
        doSwitch(documentTemplate.getBody());

        final XWPFDocument document = (XWPFDocument) generatedDocument;
        final Iterator<XWPFFooter> footers = document.getFooterList().iterator();
        for (final Template footer : documentTemplate.getFooters()) {
            final XWPFFooter f = footers.next();
            cleanHeaderFooter(f);
            generatedDocument = f;
            doSwitch(footer);
        }
        final Iterator<XWPFHeader> headers = document.getHeaderList().iterator();
        for (final Template header : documentTemplate.getHeaders()) {
            final XWPFHeader h = headers.next();
            cleanHeaderFooter(h);
            generatedDocument = h;
            doSwitch(header);
        }

        return null;
    }

    /**
     * Clear up the header or footer from the existing paragraphs and tables.
     * 
     * @param headerFooter
     *            the header or footer to clean up.
     */
    void cleanHeaderFooter(XWPFHeaderFooter headerFooter) {
        CTHdrFtr ctHdrFtr = (CTHdrFtr) headerFooter._getHdrFtr().copy();
        ctHdrFtr.getPList().clear();
        ctHdrFtr.getTblList().clear();
        // XXX : there are many other lists in the header and footer that should
        // probably be cleaned.
        headerFooter.setHeaderFooter(ctHdrFtr);
    }

    @Override
    public IConstruct caseTemplate(Template template) {
        doSwitch(template.getBody());

        return template;
    }

    @Override
    public IConstruct caseStaticFragment(StaticFragment staticFragment) {
        for (XWPFRun run : staticFragment.getRuns()) {
            insertRun(run);
        }
        return staticFragment;
    }

    /**
     * Inserts a run in the generated document. The new run is a copy from the specified run.
     * 
     * @param srcRun
     *            the run to copy
     * @return the inserted run.
     */
    private XWPFRun insertRun(XWPFRun srcRun) {
        if (srcRun.getParent() != currentTemplateParagraph || forceNewParagraph) {
            createNewParagraph((XWPFParagraph) srcRun.getParent());
            forceNewParagraph = false;
        }
        XWPFRun newRun = null;
        if (srcRun instanceof XWPFHyperlinkRun) {
            // Hyperlinks meta information is saved in the paragraph and not in the run. So we have to update the paragrapah with a copy of
            // the hyperlink to insert.
            CTHyperlink newHyperlink = currentGeneratedParagraph.getCTP().addNewHyperlink();
            newHyperlink.set(((XWPFHyperlinkRun) srcRun).getCTHyperlink());

            newRun = new XWPFHyperlinkRun(newHyperlink, srcRun.getCTR(), srcRun.getParent());
            currentGeneratedParagraph.addRun(newRun);
        } else {
            newRun = currentGeneratedParagraph.createRun();
            newRun.getCTR().set(srcRun.getCTR());
        }
        return newRun;
    }

    /**
     * Inserts a run in the generated document and set it's text to the specified replacement. The new run is a copy from the specified run.
     * 
     * @param srcRun
     *            the run to copy
     * @param replacement
     *            the text to set
     * @return the inserted run
     */
    private XWPFRun insertFieldRunReplacement(XWPFRun srcRun, String replacement) {
        if (srcRun.getParent() != currentTemplateParagraph || forceNewParagraph) {
            createNewParagraph((XWPFParagraph) srcRun.getParent());
            forceNewParagraph = false;
        }
        return insertString(srcRun, replacement);
    }

    /**
     * Insert the given text into to given {@link XWPFRun}. This take care of new lines and tabulations.
     * 
     * @param srcRun
     *            the {@link XWPFRun} to populate
     * @param text
     *            the text
     * @return the last inserted {@link XWPFRun}.
     */
    private XWPFRun insertString(XWPFRun srcRun, String text) {
        int fragmentStart = 0;

        XWPFRun inserted;
        for (int i = 0; i < text.length(); i++) {
            final char current = text.charAt(i);
            switch (current) {
                case '\n':
                    inserted = insertFragment(srcRun, text.substring(fragmentStart, i));
                    inserted.addBreak();
                    fragmentStart = i + 1;
                    break;
                case '\t':
                    inserted = insertFragment(srcRun, text.substring(fragmentStart, i));
                    inserted.addTab();
                    fragmentStart = i + 1;
                    break;
                case '\r':
                    // CHECKSTYLE:OFF TODO update i++ to make cs happy !
                    if (i + 1 < text.length() && text.charAt(i + 1) == '\n') {
                        inserted = insertFragment(srcRun, text.substring(fragmentStart, i));
                        inserted.addBreak();
                        i++;
                        fragmentStart = i + 1;
                    }
                    // CHECKSTYLE:ON
                    break;

                default:
                    break;
            }
        }

        return insertFragment(srcRun, text.substring(fragmentStart, text.length()));
    }

    /**
     * Insert a new run containing the given text.
     * 
     * @param srcRun
     *            the run to copy the style from.
     * @param fragment
     *            the text fragment to insert
     * @return the generated run.
     */
    private XWPFRun insertFragment(XWPFRun srcRun, String fragment) {
        XWPFRun generatedRun = currentGeneratedParagraph.createRun();
        generatedRun.getCTR().set(srcRun.getCTR().copy());
        generatedRun.getCTR().getInstrTextList().clear();
        generatedRun.setText(fragment);
        return generatedRun;
    }

    /**
     * Creates a new paragraph and replaces the currentParagrap variable.
     * 
     * @param srcParagraph
     *            the origin paragraph to copy the style from.
     */
    private void createNewParagraph(XWPFParagraph srcParagraph) {
        // create a new paragraph.
        XWPFParagraph newParagraph;
        if (generatedDocument instanceof XWPFTableCell) {
            XWPFTableCell cell = (XWPFTableCell) generatedDocument;
            newParagraph = cell.addParagraph();
        } else if (generatedDocument instanceof XWPFDocument) {
            newParagraph = ((XWPFDocument) generatedDocument).createParagraph();
        } else if (generatedDocument instanceof XWPFHeaderFooter) {
            newParagraph = ((XWPFHeaderFooter) generatedDocument).createParagraph();
        } else {
            throw new UnsupportedOperationException("unkown IBody type :" + generatedDocument.getClass());
        }
        CTP ctp = (CTP) srcParagraph.getCTP().copy();
        ctp.getRList().clear();
        ctp.getFldSimpleList().clear();
        ctp.getHyperlinkList().clear();
        newParagraph.getCTP().set(ctp);
        int runNb = newParagraph.getRuns().size();
        for (int i = 0; i < runNb; i++) {
            newParagraph.removeRun(i);
        }
        currentTemplateParagraph = srcParagraph;
        currentGeneratedParagraph = newParagraph;
    }

    @Override
    public IConstruct caseQuery(Query query) {
        if (query.getQuery().getDiagnostic().getSeverity() == Diagnostic.ERROR) {
            insertQuerySyntaxMessages(query, QUERY_SYNTAX_ERROR_MESSAGE);
        } else {
            final EvaluationResult queryResult = evaluator.eval(query.getQuery(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(query, queryResult.getDiagnostic());
            } else if (queryResult.getResult() == null) {
                insertFieldRunReplacement(query.getStyleRun(), "");
            } else {
                insertFieldRunReplacement(query.getStyleRun(), queryResult.getResult().toString());
            }
        }

        return query;
    }

    @Override
    public IConstruct caseRepetition(Repetition repetition) {
        if (repetition.getQuery().getDiagnostic().getSeverity() == Diagnostic.ERROR) {
            insertQuerySyntaxMessages(repetition, QUERY_SYNTAX_ERROR_MESSAGE);
        } else {
            final EvaluationResult queryResult = evaluator.eval(repetition.getQuery(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(repetition, queryResult.getDiagnostic());
            } else {
                final List<Object> iteration = new ArrayList<>();
                if (queryResult.getResult() instanceof Collection) {
                    iteration.addAll((Collection<?>) queryResult.getResult());
                } else {
                    iteration.add(queryResult.getResult());
                }
                final Map<String, Object> newVariables = Maps.newHashMap(variablesStack.peek());
                variablesStack.push(newVariables);
                try {
                    for (Object val : iteration) {
                        newVariables.put(repetition.getIterationVar(), val);
                        doSwitch(repetition.getBody());
                        closingRepretition(repetition);
                    }
                } finally {
                    variablesStack.pop();
                }
            }
        }

        return repetition;

    }

    @Override
    public IConstruct caseUserDoc(UserDoc userDoc) {
        if (userDoc.getId().getDiagnostic().getSeverity() == Diagnostic.ERROR) {
            insertQuerySyntaxMessages(userDoc, QUERY_SYNTAX_ERROR_MESSAGE);
        } else {
            final EvaluationResult queryResult = evaluator.eval(userDoc.getId(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(userDoc, queryResult.getDiagnostic());
            } else {
                if (queryResult.getResult() == null) {
                    M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                            "User doc id can't be null.");
                } else {
                    final String id = queryResult.getResult().toString();
                    insertUserContent(userDoc, id);
                }
            }
        }

        return userDoc;
    }

    /**
     * Inserts the {@link UserContent}.
     * 
     * @param userDoc
     *            the source {@link UserDoc}
     * @param id
     *            the ID
     */
    private void insertUserContent(UserDoc userDoc, final String id) {
        // Tag UserContent with evaluated id
        addStartUserDocField(userDoc, id);
        // manage user Doc Id Uniqueness
        manageUserDocIdUniqueness(id, userDoc);
        // Copy userdoc content
        UserContent userContent = userContentManager.getUserContent(id);
        boolean needNewParagraphBeforeEndTag = true;
        if (userContent == null) {
            doSwitch(userDoc.getBody());
            needNewParagraphBeforeEndTag = needNewParagraph(userDoc);
        } else {
            UserContentRawCopy userContentRawCopy = new UserContentRawCopy();
            try {
                currentGeneratedParagraph = userContentRawCopy.copy(userContent, currentGeneratedParagraph,
                        generatedDocument);
                needNewParagraphBeforeEndTag = userContentRawCopy.needNewParagraph();
                // Affect currentTemplateParagraph after Raw copy
                if (userDoc.getClosingRuns().size() != 0) {
                    currentTemplateParagraph = (XWPFParagraph) userDoc.getClosingRuns()
                            .get(userDoc.getClosingRuns().size() - 1).getParent();
                }
            } catch (InvalidFormatException e) {
                userContentManager.lostIdOccur();
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "userdoc copy error : " + e.getMessage() + ". The previous generated document is copied here : "
                            + userContentManager.getGeneratedFileCopy().getAbsolutePath());
            } catch (XmlException e) {
                userContentManager.lostIdOccur();
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "userdoc copy error : " + e.getMessage() + ". The previous generated document is copied here : "
                            + userContentManager.getGeneratedFileCopy().getAbsolutePath());
            }
        }

        // Tag m:enduserContent
        addEndUserDocField(userDoc, needNewParagraphBeforeEndTag);
    }

    /**
     * Tells if the given {@link IConstruct} need new paragraph before end tag.
     * 
     * @param construct
     *            the {@link IConstruct}
     * @return <code>true</code> if the given {@link IConstruct} need new paragraph before end tag, <code>false</code> otherwise
     */
    private boolean needNewParagraph(IConstruct construct) {
        boolean needNewParagraph = true;
        if (construct.getClosingRuns().size() != 0) {
            if (construct.getClosingRuns().get(0).getParent() == currentTemplateParagraph) {
                needNewParagraph = false;
            }
        }
        return needNewParagraph;
    }

    /**
     * userDoc Id Uniqueness test.
     * 
     * @param id
     *            id
     * @param userdoc
     *            userdoc EObject
     */
    private void manageUserDocIdUniqueness(String id, UserDoc userdoc) {
        if (userDocIds.contains(id)) {
            // insert the error message.
            String msgError = "The id '" + id
                + "' is already used in generated document. Ids must be unique otherwise document part contained userContent could be lost at next generation.";
            M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR, msgError);

            TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.ERROR, msgError, userdoc.getRuns().get(userdoc.getRuns().size() - 1));
            userdoc.getValidationMessages().add(templateValidationMessage);
        } else {
            userDocIds.add(id);
        }

    }

    /**
     * Add Start UserContent word Document Field.
     * 
     * @param object
     *            AbstractConstruct where add field
     * @param id
     *            UserDoc Id
     */
    private void addStartUserDocField(IConstruct object, String id) {
        if (currentTemplateParagraph == null
            || object.getRuns().size() != 0 && object.getRuns().get(0).getParent() != currentTemplateParagraph) {
            createNewParagraph((XWPFParagraph) object.getRuns().get(0).getParent());
        }
        FieldUtils fieldUtils = new FieldUtils();
        fieldUtils.addInstrField(currentGeneratedParagraph, TokenType.USERCONTENT.getValue() + " " + id);
    }

    /**
     * Add End UserContent word Document Field.
     * 
     * @param object
     *            AbstractConstruct where add field
     * @param needNewParagraph
     *            need New Paragraph boolean
     */
    private void addEndUserDocField(IConstruct object, boolean needNewParagraph) {
        if (object.getClosingRuns().size() != 0) {
            if (needNewParagraph) {
                createNewParagraph((XWPFParagraph) object.getClosingRuns().get(0).getParent());
            }
            FieldUtils fieldUtils = new FieldUtils();
            fieldUtils.addInstrField(currentGeneratedParagraph, TokenType.ENDUSERCONTENT.getValue());
        }
    }

    /**
     * Closes the generation of the given {@link Repetition}.
     * if the end of {@link Repetition} lies on a distinct paragraph, insert a new
     * paragraph there to take this into account.
     * 
     * @param repetition
     *            {@link Repetition} to close
     */
    private void closingRepretition(Repetition repetition) {
        int bodySize = repetition.getBody().getStatements().size();
        if (bodySize > 0 && repetition.getBody().getStatements().get(bodySize - 1).getRuns().size() > 0) {
            IConstruct lastBodyPart = repetition.getBody().getStatements().get(bodySize - 1);
            int runNumber = lastBodyPart.getRuns().size();
            XWPFRun lastRun = lastBodyPart.getRuns().get(runNumber - 1);
            int closingRunNumber = repetition.getClosingRuns().size();
            if (closingRunNumber > 0 && repetition.getClosingRuns().get(0).getParent() != lastRun.getParent()) {
                forceNewParagraph = true;
            }
        }
    }

    @Override
    public IConstruct caseBlock(Block block) {
        for (IConstruct construct : block.getStatements()) {
            doSwitch(construct);
        }

        return block;
    }

    @Override
    public IConstruct caseConditional(Conditional conditional) {
        if (conditional.getCondition().getDiagnostic().getSeverity() == Diagnostic.ERROR) {
            insertQuerySyntaxMessages(conditional, QUERY_SYNTAX_ERROR_MESSAGE);
        } else {
            final EvaluationResult result = evaluator.eval(conditional.getCondition(), variablesStack.peek());
            if (result.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(conditional, result.getDiagnostic());
                for (XWPFRun tagRun : conditional.getClosingRuns()) {
                    insertRun(tagRun);
                }
            } else if (result.getResult() instanceof Boolean) {
                if ((Boolean) result.getResult()) {
                    doSwitch(conditional.getThen());
                } else if (conditional.getElse() != null) {
                    doSwitch(conditional.getElse());
                }
            } else {
                for (XWPFRun tagRun : conditional.getRuns()) {
                    insertRun(tagRun);
                }
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "Condition evaluation result must be a boolean.");
                for (XWPFRun tagRun : conditional.getClosingRuns()) {
                    insertRun(tagRun);
                }
            }
        }

        return conditional;
    }

    @Override
    public IConstruct caseTable(Table table) {
        // Create the table structure in the destination document.

        CTTbl copy = (CTTbl) table.getTable().getCTTbl().copy();
        copy.getTrList().clear();
        if (generatedDocument instanceof XWPFDocument) {
            currentGeneratedTable = ((XWPFDocument) generatedDocument).createTable();
            if (currentGeneratedTable.getRows().size() > 0) {
                currentGeneratedTable.removeRow(0);
            }
            currentGeneratedTable.getCTTbl().set(copy);
        } else if (generatedDocument instanceof XWPFTableCell) {
            XWPFTableCell tCell = (XWPFTableCell) generatedDocument;
            int tableRank = tCell.getTables().size();
            XWPFTable newTable = new XWPFTable(copy, tCell, 0, 0);
            if (newTable.getRows().size() > 0) {
                newTable.removeRow(0);
            }
            tCell.insertTable(tableRank, newTable);
            currentGeneratedTable = tCell.getTables().get(tableRank);
        } else {
            throw new UnsupportedOperationException("unknown type of IBody : " + generatedDocument.getClass());
        }
        // iterate on the row
        for (Row row : table.getRows()) {
            doSwitch(row);
        }

        return table;
    }

    @Override
    public IConstruct caseRow(Row row) {
        currentGeneratedRow = currentGeneratedTable.createRow();
        final CTRow ctRow = (CTRow) row.getTableRow().getCtRow().copy();
        ctRow.getTcList().clear();
        currentGeneratedRow.getCtRow().set(ctRow);
        // iterate on cells.
        for (Cell cell : row.getCells()) {
            doSwitch(cell);
        }

        return null;
    }

    @Override
    public IConstruct caseCell(Cell cell) {
        final XWPFTableCell newCell = currentGeneratedRow.createCell();
        final CTTc ctCell = (CTTc) cell.getTableCell().getCTTc().copy();
        ctCell.getPList().clear();
        ctCell.getTblList().clear();
        newCell.getCTTc().set(ctCell);

        final IBody savedGeneratedDocument = generatedDocument;
        generatedDocument = newCell;
        try {
            doSwitch(cell.getTemplate());
        } finally {
            generatedDocument = savedGeneratedDocument;
        }

        return null;
    }

    /**
     * Returns the picture type based on the filename's extension.
     * 
     * @param fileName
     *            the picture file
     * @return the picture's file extension
     */
    private int getPictureType(URI fileName) {
        int result;
        if (fileName.fileExtension() != null) {
            String extension = fileName.fileExtension();
            if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_JPEG;
            } else if ("gif".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_GIF;
            } else if ("png".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_PNG;
            } else if ("emf".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_EMF;
            } else if ("wmf".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_WMF;
            } else if ("pict".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_PICT;
            } else if ("dib".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_DIB;
            } else if ("tiff".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_TIFF;
            } else if ("eps".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_EPS;
            } else if ("bmp".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_BMP;
            } else if ("wpg".equalsIgnoreCase(extension)) {
                result = Document.PICTURE_TYPE_WPG;
            } else {
                result = 0;
            }
        } else {
            result = 0;
        }
        return result;
    }

    @Override
    public IConstruct caseImage(Image image) {
        XWPFRun imageRun = insertRun(image.getStyleRun());
        imageRun.setText("");
        imageRun.getCTR().getInstrTextList().clear();
        if (image.getFileName() == null) {
            insertQuerySyntaxMessages(image, "");
        } else {
            URI filePath = URI.createFileURI(image.getFileName());
            if (!filePath.hasAbsolutePath() && image.eResource() != null && image.eResource().getURI() != null) {
                /*
                 * it is expected that we have an EResource and URI for the current template to resolve relative URIs from it.
                 */
                filePath = image.eResource().getURI().trimSegments(1);
                for (String s : Splitter.on(CharMatcher.anyOf("/\\")).split(image.getFileName())) {
                    filePath = filePath.appendSegment(s);
                }
            }
            try {
                int heigth = Units.toEMU(image.getHeight());
                int width = Units.toEMU(image.getWidth());

                try (InputStream imageStream = URIConverter.INSTANCE.createInputStream(filePath)) {
                    imageRun.addPicture(imageStream, getPictureType(filePath), image.getFileName(), width, heigth);
                }
            } catch (InvalidFormatException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "Picture in " + image.getFileName() + " has an invalid format.");
            } catch (FileNotFoundException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "File " + filePath + " cannot be found.");
            } catch (IOException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "An I/O Problem occured while reading file.");
            }
        }

        return image;
    }

    @Override
    public IConstruct caseRepresentation(Representation representation) {
        XWPFRun imageRun = insertRun(representation.getStyleRun());
        IProvider provider = representation.getProvider();
        if (provider == null) {
            M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                    representation.getValidationMessages().get(0).getMessage());
        } else {
            Map<String, Object> parameters;
            try {
                parameters = setupParametersMap(representation, provider);
                List<String> imagePaths = ((AbstractDiagramProvider) provider).getRepresentationImagePath(parameters);
                usedProviders.add((AbstractDiagramProvider) provider);
                for (String imagePathStr : imagePaths) {
                    URI imagePath = URI.createFileURI(imagePathStr);
                    if (!imagePath.hasAbsolutePath() && representation.eResource() != null
                        && representation.eResource().getURI() != null) {
                        /*
                         * it is expected that we have an EResource and URI for the current template to resolve relative URIs from it.
                         */
                        imagePath = representation.eResource().getURI().trimSegments(1);
                        for (String s : Splitter.on(CharMatcher.anyOf("/\\")).split(imagePathStr)) {
                            imagePath = imagePath.appendSegment(s);
                        }
                    }

                    try {
                        imageRun.setText("");
                        imageRun.getCTR().getInstrTextList().clear();

                        // get default image size if needed
                        if (representation.getHeight() == 0) {
                            representation.setHeight(((AbstractDiagramProvider) provider).getHeight());
                        }
                        if (representation.getWidth() == 0) {
                            representation.setWidth(((AbstractDiagramProvider) provider).getWidth());
                        }
                        int height = Units.toEMU(representation.getHeight());
                        int width = Units.toEMU(representation.getWidth());

                        try (InputStream fileInputStream = URIConverter.INSTANCE.createInputStream(imagePath)) {
                            imageRun.addPicture(fileInputStream, getPictureType(imagePath), imagePathStr, width,
                                    height);
                        }
                    } catch (InvalidFormatException e) {
                        M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                                "Picture in " + imagePath + " has an invalid format.");
                    } catch (FileNotFoundException e) {
                        M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                                "File " + imagePath + " cannot be found.");
                    } catch (IOException e) {
                        M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                                "An I/O Problem occured while reading file: " + e.getMessage());
                    }
                }
            } catch (IllegalArgumentException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR, e.getMessage());
            } catch (ProviderException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "A problem occured while creating image from an diagram provider: " + e.getMessage());
            }

        }

        return representation;

    }

    @Override
    public IConstruct caseTableClient(TableClient tableClient) {
        XWPFRun tableRun = insertRun(tableClient.getStyleRun());
        tableRun.getCTR().getInstrTextList().clear();
        AbstractTableProvider provider = (AbstractTableProvider) tableClient.getProvider();
        if (provider == null) {
            M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                    tableClient.getValidationMessages().get(0).getMessage());
        } else {
            Map<String, Object> parameters;
            try {
                parameters = setupParametersMap(tableClient, provider);
                TableClientProcessor tableProcessor = new TableClientProcessor(generatedDocument, provider, parameters);
                tableProcessor.generate(tableRun);
            } catch (IllegalArgumentException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR, e.getMessage());
            } catch (ProviderException e) {
                M2DocUtils.appendMessageRun(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        "A problem occured while creating table from a table provider: " + e.getMessage());
            }
        }
        return tableClient;
    }

    /**
     * Returns a map containing all parameters coming from the table tag
     * and global variables available.
     * 
     * @param object
     *            the {@link TableClient} object from which we extracts needed parameters.
     * @param provider
     *            the provider providing information regarding tag options.
     * @return a map containing all parameters coming from the representation tag
     *         and global variables available.
     * @throws IllegalArgumentException
     *             if the evaluation fails because error were present during parse time or evaluation time.
     */
    private Map<String, Object> setupParametersMap(TableClient object, IProvider provider)
            throws IllegalArgumentException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, targetConfObject);
        if (targetConfObject instanceof Generation) {
            parameters.put(ProviderConstants.REFRESH_REPRESENTATIONS_KEY,
                    ((Generation) targetConfObject).isRefreshRepresentations());
        } else {
            parameters.put(ProviderConstants.REFRESH_REPRESENTATIONS_KEY, false);
        }
        setGenericParameters(object, provider.getOptionTypes(), parameters);
        return parameters;
    }

    @Override
    public IConstruct caseBookmark(Bookmark bookmark) {
        if (bookmark.getName().getDiagnostic().getSeverity() == Diagnostic.ERROR) {
            insertQuerySyntaxMessages(bookmark, QUERY_SYNTAX_ERROR_MESSAGE);
        } else {
            final EvaluationResult result = evaluator.eval(bookmark.getName(), variablesStack.peek());
            if (result.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(bookmark, result.getDiagnostic());
            } else {
                bookmarkManager.startBookmark(currentGeneratedParagraph, result.getResult().toString());
                doSwitch(bookmark.getBody());
                bookmarkManager.endBookmark(currentGeneratedParagraph, result.getResult().toString());
            }
        }

        return super.caseBookmark(bookmark);
    }

    /**
     * Inserts evaluation messages for the given {@link IConstruct}.
     * 
     * @param construct
     *            the {@link IConstruct}
     * @param diagnostic
     *            the {@link Diagnostic}
     */
    protected void insertQueryEvaluationMessages(IConstruct construct, Diagnostic diagnostic) {
        for (XWPFRun tagRun : construct.getRuns()) {
            insertRun(tagRun);
        }
        M2DocUtils.appendDiagnosticMessage(currentGeneratedParagraph, diagnostic);
        for (XWPFRun tagRun : construct.getClosingRuns()) {
            insertRun(tagRun);
        }
    }

    /**
     * Inserts AQL query syntax messages for the given {@link IConstruct}.
     * 
     * @param construct
     *            the {@link IConstruct}
     * @param errorPrefix
     *            error message prefix
     */
    protected void insertQuerySyntaxMessages(IConstruct construct, String errorPrefix) {
        for (XWPFRun tagRun : construct.getRuns()) {
            insertRun(tagRun);
        }
        for (TemplateValidationMessage message : construct.getValidationMessages()) {
            M2DocUtils.appendMessageRun(currentGeneratedParagraph, message.getLevel(),
                    errorPrefix + message.getMessage());
        }
        for (XWPFRun tagRun : construct.getClosingRuns()) {
            insertRun(tagRun);
        }
    }

    @Override
    public IConstruct caseLink(Link link) {
        if (link.getName().getDiagnostic().getSeverity() == Diagnostic.ERROR
            || link.getText().getDiagnostic().getSeverity() == Diagnostic.ERROR) {
            insertQuerySyntaxMessages(link, QUERY_SYNTAX_ERROR_MESSAGE);
        } else {
            final EvaluationResult nameResult = evaluator.eval(link.getName(), variablesStack.peek());
            if (nameResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(link, nameResult.getDiagnostic());
            } else {
                final EvaluationResult textResult = evaluator.eval(link.getText(), variablesStack.peek());
                if (nameResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                    insertQueryEvaluationMessages(link, textResult.getDiagnostic());
                } else {
                    bookmarkManager.insertReference(currentGeneratedParagraph, nameResult.getResult().toString(),
                            textResult.getResult().toString());
                }
            }
        }

        return link;
    }

    /**
     * Returns a map containing all parameters coming from the representation tag
     * and global variables available.
     * 
     * @param object
     *            the {@link Representation} object from which we extracts needed parameters.
     * @param provider
     *            the provider providing information regarding tag options.
     * @return a map containing all parameters coming from the representation tag
     *         and global variables available.
     * @throws IllegalArgumentException
     *             if the evaluation fails because error were present during parse time or evaluation time.
     */
    private Map<String, Object> setupParametersMap(Representation object, IProvider provider)
            throws IllegalArgumentException {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, targetConfObject);
        parameters.put(ProviderConstants.IMAGE_HEIGHT_KEY, object.getHeight());
        parameters.put(ProviderConstants.IMAGE_WIDTH_KEY, object.getWidth());
        parameters.put(ProviderConstants.DIAGRAM_ACTIVATED_LAYERS_KEY, object.getActivatedLayers());
        if (targetConfObject instanceof Generation) {
            parameters.put(ProviderConstants.REFRESH_REPRESENTATIONS_KEY,
                    ((Generation) targetConfObject).isRefreshRepresentations());
        } else {
            parameters.put(ProviderConstants.REFRESH_REPRESENTATIONS_KEY, false);
        }
        setGenericParameters(object, provider.getOptionTypes(), parameters);
        return parameters;
    }

    /**
     * Adds all parameters with the value evaluated if needed.
     * 
     * @param templateProvider
     *            the template element from which we set generic parameters.
     * @param optionTypes
     *            the option types provided by the provider used by the template provider model element.
     * @param parameters
     *            the map containing the parameters to pass to the provider.
     * @throws IllegalArgumentException
     *             if the evaluation fails because error were present during parse time or evaluation time.
     */
    private void setGenericParameters(AbstractProviderClient templateProvider, Map<String, OptionType> optionTypes,
            Map<String, Object> parameters) throws IllegalArgumentException {
        EMap<String, Object> optionsMap = templateProvider.getOptionValueMap();
        Set<Entry<String, Object>> optionsMapEntries = optionsMap.entrySet();
        for (Entry<String, Object> optionsMapEntry : optionsMapEntries) {
            if (optionTypes == null || optionTypes.get(optionsMapEntry.getKey()) == null) {
                parameters.put(optionsMapEntry.getKey(), optionsMapEntry.getValue());
            } else if (optionTypes != null) {
                OptionType optionType = optionTypes.get(optionsMapEntry.getKey());
                if (OptionType.AQL_EXPRESSION == optionType) {
                    evaluateAqlExpression(templateProvider, parameters, optionsMapEntry);
                } else if (OptionType.STRING == optionType) {
                    parameters.put(optionsMapEntry.getKey(), optionsMapEntry.getValue());
                } else {
                    throw new UnsupportedOperationException("All options types should be supported.");
                }
            }

        }
    }

    /**
     * Evaluate the given AQL AST tree in the given entry and put it in the options map.
     * 
     * @param templateProvider
     *            the template model element were to put AQL evaluation errors.
     * @param options
     *            the map of generic options were to put evaluated content.
     * @param aqlEntry
     *            an AQL options to evaluate and put the result in the given options map.
     */
    private void evaluateAqlExpression(AbstractProviderClient templateProvider, Map<String, Object> options,
            Entry<String, Object> aqlEntry) {
        if (aqlEntry.getValue() == null) {
            throw new IllegalArgumentException(
                    QUERY_SYNTAX_ERROR_MESSAGE + templateProvider.getValidationMessages().get(0).getMessage());
        } else {
            EvaluationResult result = evaluator.eval((AstResult) aqlEntry.getValue(), variablesStack.peek());
            if (result == null) {
                throw new IllegalArgumentException(QUERY_EVALERROR_MESSAGE);
            } else if (result.getResult() == null) {
                StringBuilder builder = new StringBuilder();
                getDiagnostic(result.getDiagnostic(), builder);
                throw new IllegalArgumentException(builder.toString());
            } else {
                options.put(aqlEntry.getKey(), result.getResult());
            }
        }
    }

    /**
     * returns the diagnostic associated to the {@link Diagnostic} instance and its children.
     * 
     * @param diagnostic
     *            the {@link Diagnostic} in which searching
     * @param builder
     *            a string builder that aggregate the messages
     * @return the diagnostic status of the specified diagnostic tree.
     */
    private int getDiagnostic(Diagnostic diagnostic, StringBuilder builder) {
        String message;
        int code;
        if (diagnostic.getCode() == Diagnostic.ERROR) {
            message = diagnostic.getMessage();
            code = Diagnostic.ERROR;
        } else {
            message = diagnostic.getMessage();
            code = diagnostic.getCode();
            for (Diagnostic child : diagnostic.getChildren()) {
                int childrenCode = getDiagnostic(child, builder);
                if (childrenCode > code) {
                    code = childrenCode;
                }
            }
        }
        if (message != null) {
            if (builder.length() > 0) {
                builder.append('\n');
            }
            builder.append(message);
        }
        return code;
    }

    /**
     * Should be called when the {@link TemplateProcessor} is no longer needed so that it can cleanup temporary files used during the
     * generation.
     */
    public void clear() {
        for (AbstractDiagramProvider diagprovider : usedProviders) {
            diagprovider.clear();
        }

    }

}
