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

import com.google.common.collect.Maps;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlException;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.element.MBookmark;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;

/**
 * The {@link M2DocEvaluator} class implements a switch over template that generates the doc.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public class M2DocEvaluator extends TemplateSwitch<IConstruct> {

    /**
     * Error message when a {@link Bookmark} errors.
     */
    private static final String INVALID_BOOKMARK_STATEMENT = "Invalid bookmark statement: ";

    /**
     * Error message when a {@link Conditional} errors.
     */
    private static final String INVALID_CONDITIONAL_STATEMENT = "Invalid if statement: ";

    /**
     * Error message when a {@link Let} errors.
     */
    private static final String INVALID_LET_STATEMENT = "Invalid let statement: ";

    /**
     * Error message when a {@link Link} errors.
     */
    private static final String INVALID_LINK_STATEMENT = "Invalid link statement: ";

    /**
     * Error message when a {@link Query} errors.
     */
    private static final String INVALID_QUERY_STATEMENT = "Invalid query statement: ";

    /**
     * Error message when a {@link Repetition} errors.
     */
    private static final String INVALID_REPETITION_STATEMENT = "Invalid for statement: ";

    /**
     * Error message when a {@link UserDoc} errors.
     */
    private static final String INVALID_USERDOC_STATEMENT = "Invalid userDoc statement: ";

    /**
     * I/O reading problem message.
     */
    private static final String AN_I_O_PROBLEM_OCCURED_WHILE_READING = "An I/O Problem occured while reading %s: %s.";

    /**
     * Invalid format picture message.
     */
    private static final String PICTURE_INVALID_FORMAT = "Picture in %s has an invalid format: %s.";

    /**
     * The {@link BookmarkManager}.
     */
    private final BookmarkManager bookmarkManager;
    /**
     * variable definition used during generation.
     */
    private final Stack<Map<String, Object>> variablesStack = new Stack<>();
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
     * The {@link GenerationResult}.
     */
    private GenerationResult result;

    /**
     * The progress {@link Monitor}.
     */
    private Monitor monitor;

    /**
     * Create a new {@link M2DocEvaluator} instance given some definitions
     * and a query environment.
     * 
     * @param bookmarkManager
     *            the {@link BookmarkManager}
     * @param userContentManager
     *            the {@link UserContentManager}
     * @param queryEnvironment
     *            the query environment used to evaluate queries in the
     * @param monitor
     *            used to track the progress will generating.
     */
    public M2DocEvaluator(BookmarkManager bookmarkManager, UserContentManager userContentManager,
            IReadOnlyQueryEnvironment queryEnvironment, Monitor monitor) {
        this.bookmarkManager = bookmarkManager;
        this.userContentManager = userContentManager;
        this.evaluator = new QueryEvaluationEngine((IQueryEnvironment) queryEnvironment);
        this.monitor = monitor;
    }

    /**
     * Generates the given {@link DocumentTemplate}.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param variables
     *            the variables
     * @param destinationDocument
     *            the destination document.
     * @return the {@link GenerationResult}
     */
    public GenerationResult generate(DocumentTemplate documentTemplate, Map<String, Object> variables,
            IBody destinationDocument) {
        this.generatedDocument = destinationDocument;
        variablesStack.push(variables);
        result = new GenerationResult();
        result.getDuplicatedUserContentIDs().addAll(userContentManager.getDuplicatedUserContentIDs());

        doSwitch(documentTemplate);

        return result;
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
                    // CHECKSTYLE:OFF
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

    /**
     * Tells if the given {@link IConstruct} has {@link ValidationMessageLevel#ERROR error} in its {@link IConstruct#getValidationMessages()
     * validation messages}.
     * 
     * @param construct
     *            the {@link IConstruct}
     * @return <code>true</code> if the given {@link IConstruct} has {@link ValidationMessageLevel#ERROR error} in its
     *         {@link IConstruct#getValidationMessages() validation messages}, <code>false</code> otherwise
     */
    protected boolean hasError(IConstruct construct) {
        boolean res = false;

        for (TemplateValidationMessage message : construct.getValidationMessages()) {
            if (message.getLevel() == ValidationMessageLevel.ERROR) {
                res = true;
                break;
            }
        }

        return res;
    }

    @Override
    public IConstruct caseQuery(Query query) {
        if (hasError(query)) {
            insertQuerySyntaxMessages(query, INVALID_QUERY_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(query.getQuery(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(query, queryResult.getDiagnostic());
            } else {
                final Object value = queryResult.getResult();
                final XWPFRun styleRun = query.getStyleRun();
                insertObject(value, styleRun);
            }
        }

        return query;
    }

    /**
     * Inserts the given {@link Object} in the given {@link XWPFRun}.
     * 
     * @param object
     *            the {@link Object} to insert
     * @param run
     *            the {@link XWPFRun}
     */
    private void insertObject(Object object, XWPFRun run) {
        if (object instanceof Collection<?>) {
            for (Object child : (Collection<?>) object) {
                insertObject(child, run);
                // TODO insert Run ?
            }
        } else if (object instanceof MHyperLink) {
            final XWPFRun linkRun = insertFieldRunReplacement(run, "");
            insertMHyperLink(linkRun, (MHyperLink) object);
        } else if (object instanceof MBookmark) {
            insertMBookmark(run, (MBookmark) object);
        } else if (object instanceof MImage) {
            final XWPFRun imageRun = insertFieldRunReplacement(run, "");
            insertMImage(imageRun, (MImage) object);
        } else if (object instanceof MTable) {
            XWPFRun tableRun = run;
            tableRun.getCTR().getInstrTextList().clear();
            insertMTable(tableRun, (MTable) object);
        } else if (object instanceof MPagination) {
            insertMPagination(run, (MPagination) object);
        } else if (object == null) {
            insertFieldRunReplacement(run, "");
        } else {
            insertFieldRunReplacement(run, object.toString());
        }
    }

    @Override
    public IConstruct caseComment(Comment comment) {
        // nothing to do here
        return comment;
    }

    /**
     * Inserts a {@link MBookmark} in the given {@link XWPFRun}.
     * 
     * @param run
     *            the {@link XWPFRun}
     * @param bookmark
     *            the {@link MBookmark}
     */
    private void insertMBookmark(XWPFRun run, MBookmark bookmark) {
        insertFieldRunReplacement(run, "");
        if (bookmark.isReference()) {
            bookmarkManager.insertReference(currentGeneratedParagraph, bookmark.getId(), bookmark.getText());
        } else {
            bookmarkManager.startBookmark(result, currentGeneratedParagraph, bookmark.getId());
            insertFieldRunReplacement(run, bookmark.getText());
            bookmarkManager.endBookmark(result, currentGeneratedParagraph, bookmark.getId());
        }
    }

    /**
     * Inserts the given {@link MHyperLink}.
     * 
     * @param run
     *            the {@link XWPFRun}
     * @param hyperLink
     *            the {@link MHyperLink}
     */
    private void insertMHyperLink(XWPFRun run, MHyperLink hyperLink) {
        final String id = currentGeneratedParagraph.getDocument().getPackagePart()
                .addExternalRelationship(hyperLink.getUrl(), XWPFRelation.HYPERLINK.getRelation()).getId();
        final CTHyperlink cLink = currentGeneratedParagraph.getCTP().addNewHyperlink();
        cLink.setId(id);
        CTText ctText = CTText.Factory.newInstance();
        ctText.setStringValue(hyperLink.getText());

        CTR ctr = CTR.Factory.newInstance();
        ctr.setRPr((CTRPr) run.getCTR().getRPr().copy());
        ctr.setTArray(new CTText[] {ctText });
        cLink.setRArray(new CTR[] {ctr });
    }

    /**
     * Inserts the given {@link MImage}.
     * 
     * @param run
     *            the {@link XWPFRun} to insert to
     * @param image
     *            the {@link MImage} to insert
     */
    private void insertMImage(XWPFRun run, MImage image) {
        try {
            int heigth = Units.toEMU(image.getHeight());
            int width = Units.toEMU(image.getWidth());

            try (InputStream imageStream = image.getInputStream()) {
                run.addPicture(imageStream, image.getType().getPoiType(), image.getURI().toString(), width, heigth);
            }
        } catch (InvalidFormatException e) {
            insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                    String.format(PICTURE_INVALID_FORMAT, image.getURI().toString(), e.getMessage()));
        } catch (IOException e) {
            insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                    String.format(AN_I_O_PROBLEM_OCCURED_WHILE_READING, image.getURI().toString(), e.getMessage()));
        }
    }

    /**
     * Insert the given {@link MPagination}.
     * 
     * @param run
     *            the {@link XWPFRun} to insert to
     * @param mPagination
     *            the {@link MPagination}
     */
    private void insertMPagination(XWPFRun run, MPagination mPagination) {
        switch (mPagination) {
            case newColumn:
                run.addBreak(BreakType.COLUMN);
                break;

            case newParagraph:
                createNewParagraph((XWPFParagraph) run.getParent());
                break;

            case newPage:
                run.addBreak(BreakType.PAGE);
                break;

            case newTableOfContent:
                CTP ctP = currentGeneratedParagraph.getCTP();
                CTSimpleField toc = ctP.addNewFldSimple();
                toc.setInstr("TOC \\h");
                toc.setDirty(STOnOff.TRUE);
                break;

            case newTextWrapping:
                run.addBreak(BreakType.TEXT_WRAPPING);
                break;

            default:
                throw new IllegalStateException("Not supported MPagination.");
        }
    }

    /**
     * Inserts the given {@link MTable}.
     * 
     * @param run
     *            the {@link XWPFRun} to insert to
     * @param table
     *            the {@link MTable} to insert
     */
    private void insertMTable(XWPFRun run, MTable table) {
        final XWPFTable docTable;
        if (generatedDocument instanceof XWPFDocument) {
            if (table.getLabel() != null) {
                XWPFRun captionRun;
                captionRun = run;
                IRunBody runBody = captionRun.getParent();
                if (runBody instanceof XWPFParagraph) {
                    ((XWPFParagraph) runBody).setSpacingAfter(0);
                }
                captionRun.setText(table.getLabel());
                captionRun.setBold(true);
            }
            docTable = ((XWPFDocument) generatedDocument).createTable();
        } else if (generatedDocument instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) generatedDocument;
            final int index = headerFooter._getHdrFtr().getTblArray().length;
            final CTTbl cttbl = headerFooter._getHdrFtr().insertNewTbl(index);
            docTable = new XWPFTable(cttbl, headerFooter);
            headerFooter.insertTable(index, docTable);
        } else if (generatedDocument instanceof XWPFTableCell) {
            XWPFTableCell tcell = (XWPFTableCell) generatedDocument;
            if (table.getLabel() != null) {
                final XWPFRun captionRun = run;
                IRunBody runBody = captionRun.getParent();
                if (runBody instanceof XWPFParagraph) {
                    ((XWPFParagraph) runBody).setSpacingAfter(0);
                }
                captionRun.setText(table.getLabel());
                captionRun.setBold(true);
            }
            CTTbl ctTbl = tcell.getCTTc().addNewTbl();
            docTable = new XWPFTable(ctTbl, tcell);
            int tableRank = tcell.getTables().size();
            tcell.insertTable(tableRank, docTable);
            // A paragraph is mandatory at the end of a cell, so let's always add one.
            tcell.addParagraph();
        } else {
            docTable = null;
            insertMessage((XWPFParagraph) run.getParent(), ValidationMessageLevel.ERROR,
                    "m:table can't be inserted here.");
        }

        if (docTable != null) {
            fillTable(docTable, table);
        }
    }

    /**
     * Fill a newly created word table with the data from an MTable.
     * 
     * @param table
     *            The newly created word table
     * @param mtable
     *            The MTable that describes the data and styles to insert
     */
    private void fillTable(XWPFTable table, MTable mtable) {
        List<MRow> rows = mtable.getRows();
        // Iterate over the rows
        for (int rowIdx = 0; rowIdx < rows.size(); rowIdx++) {
            MRow mRow = rows.get(rowIdx);

            // Get or create XWPF row
            XWPFTableRow xwpfRow;
            if (table.getNumberOfRows() > rowIdx) {
                xwpfRow = table.getRow(rowIdx);
            } else {
                xwpfRow = table.createRow();
            }

            // Iterate over the columns
            for (int colIdx = 0; colIdx < mtable.getColumnsCount(); colIdx++) {
                // Get or create XWPF cell
                XWPFTableCell xwpfCell;
                if (xwpfRow.getTableCells().size() > colIdx) {
                    xwpfCell = xwpfRow.getCell(colIdx);
                } else {
                    xwpfCell = xwpfRow.createCell();
                }

                // Populate cell
                XWPFParagraph xwpfCellParagraph = xwpfCell.getParagraphs().get(0);
                xwpfCellParagraph.setSpacingBefore(0);
                xwpfCellParagraph.setSpacingAfter(0);
                MCell mCell = null;
                if (colIdx < mRow.getCells().size()) {
                    mCell = mRow.getCells().get(colIdx);
                }
                setCellContent(xwpfCell, mCell);
            }
        }
    }

    /**
     * Create a new run in the cell's paragraph and set this run's text, and apply the given style to the cell and its paragraph.
     * 
     * @param cell
     *            Cell to fill in
     * @param mCell
     *            the cell to read data from
     */
    private void setCellContent(XWPFTableCell cell, MCell mCell) {
        XWPFParagraph cellParagraph = cell.getParagraphs().get(0);
        XWPFRun cellRun = cellParagraph.createRun();
        if (mCell != null) {
            String label = mCell.getLabel();
            if (label != null) {
                cellRun.setText(label);
            }
            MStyle style = mCell.getStyle();
            if (style != null) {
                if (style.getBackgroundColor() != null) {
                    cell.setColor(hexColor(style.getBackgroundColor()));
                }
                applyMStyle(cellRun, style);
            }
        }
    }

    /**
     * Apply the given style to the given run. Background color is not taken into account here since it does not apply to runs.
     * 
     * @param run
     *            The run to style
     * @param style
     *            The style to apply, can be <code>null</code>
     */
    private void applyMStyle(XWPFRun run, MStyle style) {
        if (style.getFontSize() != -1) {
            run.setFontSize(style.getFontSize());
        }
        if (style.getFontModifiers() != -1) {
            run.setBold((style.getFontModifiers() & MStyle.FONT_BOLD) != 0);
            run.setItalic((style.getFontModifiers() & MStyle.FONT_ITALIC) != 0);
            if ((style.getFontModifiers() & MStyle.FONT_UNDERLINE) != 0) {
                run.setUnderline(UnderlinePatterns.SINGLE);
            }
            run.setStrikeThrough((style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH) != 0);
        }
        if (style.getForegroundColor() != null) {
            run.setColor(hexColor(style.getForegroundColor()));
        }
    }

    /**
     * Translate a {@link Color} to the word format.
     * 
     * @param color
     *            the {@link Color}
     * @return The color as a 6-digits string.
     */
    private static String hexColor(Color color) {
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    @Override
    public IConstruct caseRepetition(Repetition repetition) {
        if (hasError(repetition)) {
            insertQuerySyntaxMessages(repetition, INVALID_REPETITION_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(repetition.getQuery(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(repetition, queryResult.getDiagnostic());
            } else {
                final List<Object> iteration = new ArrayList<>();
                if (queryResult.getResult() instanceof Collection) {
                    iteration.addAll((Collection<?>) queryResult.getResult());
                } else if (queryResult.getResult() != null) {
                    iteration.add(queryResult.getResult());
                } else {
                    insertMessage(currentGeneratedParagraph, ValidationMessageLevel.WARNING,
                            repetition.getIterationVar() + " value is null.");
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
    public IConstruct doSwitch(EObject eObject) {
        if (!monitor.isCanceled()) {
            return super.doSwitch(eObject);
        }
        return null;

    }

    @Override
    public IConstruct caseLet(Let let) {
        if (hasError(let)) {
            insertQuerySyntaxMessages(let, INVALID_LET_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(let.getValue(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(let, queryResult.getDiagnostic());
            } else {
                if (queryResult.getResult() == null) {
                    insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                            "User doc id can't be null.");
                } else {
                    final Map<String, Object> newVariables = Maps.newHashMap(variablesStack.peek());
                    variablesStack.push(newVariables);
                    try {
                        newVariables.put(let.getName(), queryResult.getResult());
                        doSwitch(let.getBody());
                    } finally {
                        variablesStack.pop();
                    }
                }
            }
        }

        return let;
    }

    @Override
    public IConstruct caseUserDoc(UserDoc userDoc) {
        if (hasError(userDoc)) {
            insertQuerySyntaxMessages(userDoc, INVALID_USERDOC_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(userDoc.getId(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(userDoc, queryResult.getDiagnostic());
            } else {
                if (queryResult.getResult() == null) {
                    insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
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
        UserContent userContent = userContentManager.consumeUserContent(id);
        boolean needNewParagraphBeforeEndTag = true;
        if (userContent == null) {
            doSwitch(userDoc.getBody());
            needNewParagraphBeforeEndTag = needNewParagraph(userDoc);
        } else {
            final UserContentRawCopy userContentRawCopy = new UserContentRawCopy();
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
                insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        UserContentManager.USERDOC_COPY_ERROR + e.getMessage());
            } catch (XmlException e) {
                insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        UserContentManager.USERDOC_COPY_ERROR + e.getMessage());
            } catch (IOException e) {
                insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
                        UserContentManager.USERDOC_COPY_ERROR + e.getMessage());
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
            insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR, msgError);

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
        currentGeneratedParagraph.getCTP().addNewFldSimple().setInstr(TokenType.USERCONTENT.getValue() + " " + id);
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
            currentGeneratedParagraph.getCTP().addNewFldSimple().setInstr(TokenType.ENDUSERCONTENT.getValue());
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
        if (hasError(block)) {
            insertQuerySyntaxMessages(block, INVALID_CONDITIONAL_STATEMENT);
        } else {
            for (IConstruct construct : block.getStatements()) {
                doSwitch(construct);
            }
        }

        return block;
    }

    @Override
    public IConstruct caseConditional(Conditional conditional) {
        if (hasError(conditional)) {
            insertQuerySyntaxMessages(conditional, INVALID_CONDITIONAL_STATEMENT);
        } else {
            final EvaluationResult evaluationResult = evaluator.eval(conditional.getCondition(), variablesStack.peek());
            if (evaluationResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(conditional, evaluationResult.getDiagnostic());
                for (XWPFRun tagRun : conditional.getClosingRuns()) {
                    insertRun(tagRun);
                }
            } else if (evaluationResult.getResult() instanceof Boolean) {
                if ((Boolean) evaluationResult.getResult()) {
                    doSwitch(conditional.getThen());
                } else if (conditional.getElse() != null) {
                    doSwitch(conditional.getElse());
                }
            } else {
                for (XWPFRun tagRun : conditional.getRuns()) {
                    insertRun(tagRun);
                }
                insertMessage(currentGeneratedParagraph, ValidationMessageLevel.ERROR,
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
        } else if (generatedDocument instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) generatedDocument;
            final int index = headerFooter._getHdrFtr().getTblArray().length;
            final CTTbl cttbl = headerFooter._getHdrFtr().insertNewTbl(index);
            XWPFTable newTable = new XWPFTable(cttbl, headerFooter);
            if (newTable.getRows().size() > 0) {
                newTable.removeRow(0);
            }
            headerFooter.insertTable(index, newTable);
            currentGeneratedTable = headerFooter.getTables().get(index);
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

    @Override
    public IConstruct caseBookmark(Bookmark bookmark) {
        if (hasError(bookmark)) {
            insertQuerySyntaxMessages(bookmark, INVALID_BOOKMARK_STATEMENT);
        } else {
            final EvaluationResult evaluationResult = evaluator.eval(bookmark.getName(), variablesStack.peek());
            if (evaluationResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                insertQueryEvaluationMessages(bookmark, evaluationResult.getDiagnostic());
            } else {
                bookmarkManager.startBookmark(result, currentGeneratedParagraph,
                        evaluationResult.getResult().toString());
                doSwitch(bookmark.getBody());
                bookmarkManager.endBookmark(result, currentGeneratedParagraph, evaluationResult.getResult().toString());
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
        for (TemplateValidationMessage message : M2DocUtils.appendDiagnosticMessage(currentGeneratedParagraph,
                diagnostic)) {
            result.addMessage(message);
        }
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
            insertMessage(currentGeneratedParagraph, message.getLevel(), errorPrefix + message.getMessage());
        }
        for (XWPFRun tagRun : construct.getClosingRuns()) {
            insertRun(tagRun);
        }
    }

    /**
     * Inserts the given message with the given {@link ValidationMessageLevel}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message
     */
    protected void insertMessage(XWPFParagraph paragraph, ValidationMessageLevel level, String message) {
        result.addMessage(M2DocUtils.appendMessageRun(paragraph, level, message));
    }

    @Override
    public IConstruct caseLink(Link link) {
        if (hasError(link)) {
            insertQuerySyntaxMessages(link, INVALID_LINK_STATEMENT);
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

}
