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
package org.obeonetwork.m2doc.generator;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CancellationException;

import org.apache.poi.ooxml.POIXMLDocumentPart.RelationPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.TableRowHeightRule;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFFootnote;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFSDT;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableCell.XWPFVertAlign;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.element.MBookmark;
import org.obeonetwork.m2doc.element.MBorder;
import org.obeonetwork.m2doc.element.MElement;
import org.obeonetwork.m2doc.element.MElementContainer.HAlignment;
import org.obeonetwork.m2doc.element.MHyperLink;
import org.obeonetwork.m2doc.element.MImage;
import org.obeonetwork.m2doc.element.MList;
import org.obeonetwork.m2doc.element.MPagination;
import org.obeonetwork.m2doc.element.MParagraph;
import org.obeonetwork.m2doc.element.MParagraph.Dir;
import org.obeonetwork.m2doc.element.MStyle;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.element.MTable.MCell;
import org.obeonetwork.m2doc.element.MTable.MCell.VAlignment;
import org.obeonetwork.m2doc.element.MTable.MRow;
import org.obeonetwork.m2doc.element.MTable.MTableAlign;
import org.obeonetwork.m2doc.element.MText;
import org.obeonetwork.m2doc.element.impl.AbosluteResizedImage;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.IGenerateable;
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
import org.obeonetwork.m2doc.util.FieldUtils;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.util.SequenceField;
import org.openxmlformats.schemas.officeDocument.x2006.sharedTypes.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTLvl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSimpleField;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;

/**
 * The {@link M2DocEvaluator} class implements a switch over template that generates the doc.
 * 
 * @author Romain Guider
 */
@SuppressWarnings("restriction")
public class M2DocEvaluator extends TemplateSwitch<XWPFParagraph> {

    /**
     * The generation monitor work.
     */
    public static final int MONITOR_WORK = 30;

    /**
     * Ratio from some unit to pixels.
     */
    private static final double UNIT_CONVERSION = 20.0d;

    /**
     * Constant to get from pixel size to border size.
     */
    private static final int BORDER_SIZE_CONSTANT = 8;

    /**
     * Error message when a {@link Bookmark} errors.
     */
    private static final String INVALID_BOOKMARK_STATEMENT = "Invalid bookmark statement: ";

    /**
     * Error message when a {@link Conditional} errors.
     */
    private static final String INVALID_CONDITIONAL_STATEMENT = "Invalid if statement: ";

    /**
     * Error message when a {@link Block} errors.
     */
    private static final String INVALID_BLOCK_STATEMENT = "Invalid block: ";

    /**
     * Error message when a {@link Let} errors.
     */
    private static final String INVALID_LET_STATEMENT = "Invalid let statement: ";

    /**
     * Error message when a {@link Link} errors.
     */
    private static final String INVALID_LINK_STATEMENT = "Invalid link statement: ";

    /**
     * Error message when a {@link Link} errors.
     */
    private static final String INVALID_COMMENT_STATEMENT = "Invalid comment statement: ";

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
     * The {@link FieldUtils} instance.
     */
    private static final FieldUtils FIELD_UTILS = new FieldUtils();

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
     * The {@link RawCopier}.
     */
    private final RawCopier copier;

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
    private Set<String> encountereduserDocIds = new HashSet<>();

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
     * @param m2docEnv
     *            the {@link M2DocEvaluationEnvironment}.
     * @param monitor
     *            used to track the progress will generating.
     */
    public M2DocEvaluator(M2DocEvaluationEnvironment m2docEnv, Monitor monitor) {
        this.bookmarkManager = m2docEnv.getBookmarkManager();
        this.userContentManager = m2docEnv.getUserContentManager();
        this.copier = m2docEnv.getCopier();
        this.evaluator = new QueryEvaluationEngine((IQueryEnvironment) m2docEnv.getQueryEnvironment());
        this.monitor = monitor;
    }

    /**
     * Generates the given {@link IGenerateable}.
     * 
     * @param generateable
     *            the {@link IGenerateable}
     * @param variables
     *            the variables
     * @param destinationDocument
     *            the destination document.
     * @return the {@link GenerationResult}
     */
    public GenerationResult generate(IGenerateable generateable, Map<String, Object> variables,
            IBody destinationDocument) {
        generatedDocument = destinationDocument;

        variablesStack.push(variables);
        try {
            result = new GenerationResult(destinationDocument);
            result.getDuplicatedUserContentIDs().addAll(userContentManager.getDuplicatedUserContentIDs());

            doSwitch(generateable);
        } finally {
            variablesStack.pop();
        }

        return result;
    }

    @SuppressWarnings("resource")
    @Override
    public XWPFParagraph caseDocumentTemplate(DocumentTemplate documentTemplate) {
        cleanBody(generatedDocument);

        final int unitOfWork = MONITOR_WORK
            / (1 + documentTemplate.getFooters().size() + documentTemplate.getHeaders().size());

        doSwitch(documentTemplate.getBody());
        updateSequences(generatedDocument.getXWPFDocument().getDocument(), new HashMap<>());
        worked(monitor, unitOfWork);

        final XWPFDocument document = (XWPFDocument) generatedDocument;
        final Iterator<XWPFFooter> footers = document.getFooterList().iterator();
        for (final Block footer : documentTemplate.getFooters()) {
            final XWPFFooter f = footers.next();
            cleanBody(f);
            generatedDocument = f;
            doSwitch(footer);
            worked(monitor, unitOfWork);
        }
        final Iterator<XWPFHeader> headers = document.getHeaderList().iterator();
        for (final Block header : documentTemplate.getHeaders()) {
            final XWPFHeader h = headers.next();
            cleanBody(h);
            generatedDocument = h;
            doSwitch(header);
            worked(monitor, unitOfWork);
        }

        return currentGeneratedParagraph;
    }

    /**
     * Updates sequences (<code>SEQ<code>) numbers in the given {@link XWPFDocument}.
     * 
     * @param xmlObject
     *            the {@link XmlObject}
     * @param sequenceToIndex
     *            the mapping from {@link SequenceField#getName() sequence name} to its current index
     */
    private void updateSequences(XmlObject xmlObject, HashMap<String, Integer> sequenceToIndex) {
        if (xmlObject instanceof CTR) {
            final SequenceField sequence = FIELD_UTILS.getSequenceField((CTR) xmlObject);
            if (sequence != null) {
                final int index = sequenceToIndex.getOrDefault(sequence.getName(), 1);
                sequence.setIndex(index);
                sequenceToIndex.put(sequence.getName(), index + 1);
            }
        } else {
            try (XmlCursor cursor = xmlObject.newCursor()) {
                if (cursor.toFirstChild()) {
                    updateSequences(cursor.getObject(), sequenceToIndex);
                    while (cursor.toNextSibling()) {
                        updateSequences(cursor.getObject(), sequenceToIndex);
                    }
                }
            }
        }
    }

    /**
     * Progresses the given amount of work on the given {@link Monitor}.
     * 
     * @param progressMonitor
     *            the {@link Monitor}
     * @param work
     *            the amount of work
     */
    private void worked(Monitor progressMonitor, int work) {
        if (progressMonitor.isCanceled()) {
            throw new CancellationException("Canceled by user");
        }
        progressMonitor.worked(work);
    }

    @Override
    public XWPFParagraph caseTemplate(Template template) {
        cleanBody(generatedDocument);

        return doSwitch(template.getBody());
    }

    /**
     * Cleans the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody} to clean up
     */
    public void cleanBody(IBody body) {
        if (body instanceof XWPFDocument) {
            final XWPFDocument document = (XWPFDocument) body;
            final int size = body.getBodyElements().size();
            for (int i = 0; i < size; i++) {
                document.removeBodyElement(0);
            }
            // TODO remove this is here because in XWPFDocument.removeBodyElement(int)
            // Sdt are not processed it should be patched in POI
            document.getDocument().getBody().getSdtList().clear();
        } else if (body instanceof XWPFHeaderFooter) {
            CTHdrFtr ctHdrFtr = (CTHdrFtr) ((XWPFHeaderFooter) body)._getHdrFtr().copy();
            ctHdrFtr.getPList().clear();
            ctHdrFtr.getTblList().clear();
            ctHdrFtr.getSdtList().clear();
            // XXX : there are many other lists in the header and footer that should
            // probably be cleaned.
            ((XWPFHeaderFooter) body).setHeaderFooter(ctHdrFtr);
        } else {
            throw new UnsupportedOperationException("unkown IBody type :" + body.getClass());
        }
    }

    @Override
    public XWPFParagraph caseStaticFragment(StaticFragment staticFragment) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        for (XWPFRun run : staticFragment.getRuns()) {
            currentParagraph = (XWPFParagraph) insertRun(currentParagraph, run).getParent();
        }

        return currentParagraph;
    }

    /**
     * Inserts a run in the generated document. The new run is a copy from the specified run.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param srcRun
     *            the run to copy
     * @return the inserted {@link XWPFRun}
     */
    private XWPFRun insertRun(XWPFParagraph paragraph, XWPFRun srcRun) {

        final XWPFParagraph newParagraph;
        if (srcRun.getParent() != currentTemplateParagraph || forceNewParagraph || paragraph == null) {
            newParagraph = createNewParagraph(generatedDocument, (XWPFParagraph) srcRun.getParent());
        } else {
            newParagraph = paragraph;
        }

        XWPFRun newRun = null;
        if (srcRun instanceof XWPFHyperlinkRun) {
            // Hyperlinks meta information is saved in the paragraph and not in the run. So we have to update the paragrapah with a copy of
            // the hyperlink to insert.
            CTHyperlink newHyperlink = newParagraph.getCTP().addNewHyperlink();
            newHyperlink.set(((XWPFHyperlinkRun) srcRun).getCTHyperlink());

            newRun = new XWPFHyperlinkRun(newHyperlink, srcRun.getCTR(), srcRun.getParent());
            newParagraph.addRun(newRun);
        } else {
            newRun = newParagraph.createRun();
            newRun.getCTR().set(srcRun.getCTR());
        }
        return newRun;
    }

    /**
     * Inserts a run in the generated document and set it's text to the specified replacement. The new run is a copy from the specified run.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param srcRun
     *            the run to copy
     * @param replacement
     *            the text to set
     * @return the inserted run
     */
    private XWPFRun insertFieldRunReplacement(XWPFParagraph paragraph, XWPFRun srcRun, String replacement) {
        final XWPFParagraph newParagraph;
        if (srcRun.getParent() != currentTemplateParagraph || forceNewParagraph) {
            newParagraph = createNewParagraph(generatedDocument, (XWPFParagraph) srcRun.getParent());
        } else {
            newParagraph = paragraph;
        }

        return insertString(newParagraph, srcRun, replacement);
    }

    /**
     * Insert the given text into to given {@link XWPFRun}. This take care of new lines and tabulations.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param srcRun
     *            the {@link XWPFRun} to populate
     * @param text
     *            the text to insert
     * @return the last inserted {@link XWPFRun}.
     */
    private XWPFRun insertString(XWPFParagraph paragraph, XWPFRun srcRun, String text) {
        int fragmentStart = 0;

        XWPFRun inserted;
        for (int i = 0; i < text.length(); i++) {
            final char current = text.charAt(i);
            switch (current) {
                case '\n':
                    inserted = insertFragment(paragraph, srcRun, text.substring(fragmentStart, i));
                    inserted.addBreak();
                    fragmentStart = i + 1;
                    break;
                case '\t':
                    inserted = insertFragment(paragraph, srcRun, text.substring(fragmentStart, i));
                    inserted.addTab();
                    fragmentStart = i + 1;
                    break;
                case '\r':
                    // CHECKSTYLE:OFF
                    if (i + 1 < text.length() && text.charAt(i + 1) == '\n') {
                        inserted = insertFragment(paragraph, srcRun, text.substring(fragmentStart, i));
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

        return insertFragment(paragraph, srcRun, text.substring(fragmentStart, text.length()));
    }

    /**
     * Inserts a {@link XWPFRun} run containing the given text to the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param srcRun
     *            the {@link XWPFRun} to copy the style from
     * @param fragment
     *            the text fragment to insert
     * @return the inserted {@link XWPFRun}
     */
    private XWPFRun insertFragment(XWPFParagraph paragraph, XWPFRun srcRun, String fragment) {
        XWPFRun generatedRun = paragraph.createRun();
        generatedRun.getCTR().set(srcRun.getCTR().copy());
        generatedRun.getCTR().getTList().clear();
        generatedRun.getCTR().getInstrTextList().clear();
        generatedRun.setText(fragment);
        return generatedRun;
    }

    /**
     * Creates a new paragraph and replaces the currentParagrap variable.
     * 
     * @param body
     *            the {@link IBody} to modify
     * @param srcParagraph
     *            the origin paragraph to copy the style from
     * @return the created {@link XWPFParagraph}
     */
    private XWPFParagraph createNewParagraph(IBody body, XWPFParagraph srcParagraph) {
        final XWPFParagraph res;

        // create a new paragraph.
        res = createParagraph(body);
        CTP ctp = (CTP) srcParagraph.getCTP().copy();
        ctp.getRList().clear();
        ctp.getFldSimpleList().clear();
        ctp.getHyperlinkList().clear();
        res.getCTP().set(ctp);
        int runNb = res.getRuns().size();
        for (int i = 0; i < runNb; i++) {
            res.removeRun(i);
        }
        currentTemplateParagraph = srcParagraph;
        currentGeneratedParagraph = res;
        forceNewParagraph = false;

        return res;
    }

    /**
     * Creates a {@link XWPFParagraph} in the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody}
     * @return the created {@link XWPFParagraph}
     */
    private XWPFParagraph createParagraph(IBody body) {
        final XWPFParagraph res;

        if (body instanceof XWPFTableCell) {
            XWPFTableCell cell = (XWPFTableCell) body;
            res = cell.addParagraph();
        } else if (body instanceof XWPFDocument) {
            res = ((XWPFDocument) body).createParagraph();
        } else if (body instanceof XWPFHeaderFooter) {
            res = ((XWPFHeaderFooter) body).createParagraph();
        } else {
            throw new UnsupportedOperationException("unkown IBody type :" + body.getClass());
        }

        return res;
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
    public XWPFParagraph caseQuery(Query query) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(query)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, query, INVALID_QUERY_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(query.getQuery(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, query, queryResult.getDiagnostic());
            } else {
                final Object value = queryResult.getResult();
                final XWPFRun styleRun = query.getStyleRun();
                currentParagraph = insertObject(currentParagraph, value, styleRun);
            }
        }

        return currentParagraph;
    }

    /**
     * Inserts the given {@link Object} in the given {@link XWPFRun}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param object
     *            the {@link Object} to insert
     * @param run
     *            the {@link XWPFRun}
     * @return the last inserted or modified {@link XWPFRun}
     */
    private XWPFParagraph insertObject(XWPFParagraph paragraph, Object object, XWPFRun run) {
        final XWPFParagraph res;

        if (object instanceof Collection<?>) {
            XWPFParagraph currentParagraph = paragraph;
            for (Object child : (Collection<?>) object) {
                currentParagraph = insertObject(currentParagraph, child, run);
            }
            res = currentParagraph;
        } else if (object instanceof MHyperLink) {
            res = insertMHyperLink(paragraph, run, (MHyperLink) object);
        } else if (object instanceof MBookmark) {
            res = insertMBookmark(paragraph, run, (MBookmark) object);
        } else if (object instanceof MImage) {
            if (object != MImage.EMPTY) {
                final XWPFRun imageRun = insertFieldRunReplacement(paragraph, run, "");
                final XWPFParagraph imageParagraph = (XWPFParagraph) imageRun.getParent();
                insertMImage(imageParagraph, imageRun, (MImage) object);
                res = imageParagraph;
            } else {
                // nothing to do here
                res = paragraph;
            }
        } else if (object instanceof MText) {
            if (((MText) object).getText() != null && !((MText) object).getText().isEmpty()) {
                res = (XWPFParagraph) insertMText(paragraph, run, (MText) object).getParent();
            } else {
                // nothing to do here
                res = paragraph;
            }
        } else if (object instanceof MTable) {
            if (!((MTable) object).getRows().isEmpty() && ((MTable) object).getColumnsCount() != 0) {
                XWPFRun tableRun = run;
                tableRun.getCTR().getInstrTextList().clear();
                insertMTable(tableRun, (MTable) object);
                res = (XWPFParagraph) tableRun.getParent();
            } else {
                // nothing to do here
                res = paragraph;
            }
        } else if (object instanceof MPagination) {
            res = insertMPagination(paragraph, run, (MPagination) object);
        } else if (object instanceof MParagraph) {
            res = insertMParagraph(generatedDocument, (MParagraph) object, run);
        } else if (object instanceof IBody) {
            if (!((IBody) object).getBodyElements().isEmpty()) {
                final XWPFRun bodyRun = insertFieldRunReplacement(paragraph, run, "");
                res = insertBody((XWPFParagraph) bodyRun.getParent(), (IBody) object);
            } else {
                // nothing to do here
                res = paragraph;
            }
        } else if (object instanceof GenerationResult) {
            if (!((GenerationResult) object).getBody().getBodyElements().isEmpty()) {
                final XWPFRun generationRun = insertFieldRunReplacement(paragraph, run, "");
                res = insertGenerationResult((XWPFParagraph) generationRun.getParent(), (GenerationResult) object);
            } else {
                // nothing to do here
                res = paragraph;
            }
        } else if (object == null) {
            // nothing to do here
            res = paragraph;
        } else {
            final String stringValue = object.toString();
            if (stringValue != null && !stringValue.isEmpty()) {
                res = (XWPFParagraph) insertFieldRunReplacement(paragraph, run, stringValue).getParent();
            } else {
                // nothing to do here
                res = paragraph;
            }
        }

        return res;
    }

    /**
     * Inserts the given {@link GenerationResult}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param generationResult
     *            the {@link GenerationResult}
     * @return the last inserted {@link XWPFParagraph}
     */
    private XWPFParagraph insertGenerationResult(XWPFParagraph paragraph, GenerationResult generationResult) {
        for (TemplateValidationMessage message : generationResult.getMessages()) {
            result.addMessage(message);
        }

        final XWPFParagraph res = insertBody(paragraph, generationResult.getBody());
        currentGeneratedParagraph = res;
        return res;
    }

    /**
     * Inserts the given {@link IBody}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param body
     *            the {@link IBody}
     * @return the last inserted {@link XWPFParagraph}
     */
    private XWPFParagraph insertBody(XWPFParagraph paragraph, IBody body) {
        XWPFParagraph res;

        try {
            res = copier.copyBody(paragraph, body, bookmarkManager);
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            result.addMessage(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR, e.getMessage()));
            res = paragraph;
        }

        return res;
    }

    /**
     * Inserts the given {@link MText}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param run
     *            the {@link XWPFRun}
     * @param text
     *            the {@link MText}
     * @return the last inserted XWPFRun
     */
    private XWPFRun insertMText(XWPFParagraph paragraph, XWPFRun run, MText text) {
        final XWPFRun res;

        if (text.getText() != null) {
            final XWPFRun textRun = insertFieldRunReplacement(paragraph, run, text.getText());
            if (text.getStyle() != null) {
                applyMStyle(textRun, text.getStyle());
            }
            res = textRun;
        } else {
            res = run;
        }

        return res;
    }

    @Override
    public XWPFParagraph caseComment(Comment comment) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(comment)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, comment, INVALID_COMMENT_STATEMENT);
        }
        return currentParagraph;
    }

    /**
     * Inserts a {@link MBookmark} in the given {@link XWPFRun}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param run
     *            the {@link XWPFRun}
     * @param bookmark
     *            the {@link MBookmark}
     * @return the {@link XWPFParagraph} where the bookmark was inserted
     */
    private XWPFParagraph insertMBookmark(XWPFParagraph paragraph, XWPFRun run, MBookmark bookmark) {
        final XWPFParagraph res = (XWPFParagraph) insertFieldRunReplacement(paragraph, run, "").getParent();

        if (bookmark.isReference()) {
            bookmarkManager.insertReference(res, bookmark.getId(), bookmark.getText(), bookmark.isOptional());
        } else {
            bookmarkManager.startBookmark(result, res, bookmark.getId());
            insertFieldRunReplacement(res, run, bookmark.getText());
            bookmarkManager.endBookmark(result, res, bookmark.getId());
        }

        return res;
    }

    /**
     * Inserts the given {@link MHyperLink}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param run
     *            the {@link XWPFRun}
     * @param hyperLink
     *            the {@link MHyperLink}
     * @return the {@link XWPFParagraph} where the hyperling was inserted
     */
    private XWPFParagraph insertMHyperLink(XWPFParagraph paragraph, XWPFRun run, MHyperLink hyperLink) {
        final XWPFRun linkRun = insertMText(paragraph, run, hyperLink);
        final XWPFParagraph res = (XWPFParagraph) linkRun.getParent();

        final String id = res.getBody().getPart().getPackagePart()
                .addExternalRelationship(hyperLink.getUrl(), XWPFRelation.HYPERLINK.getRelation()).getId();
        final CTHyperlink cLink = res.getCTP().addNewHyperlink();
        cLink.setId(id);
        final String toolTip = hyperLink.getToolTip();
        if (toolTip != null) {
            cLink.setTooltip(toolTip);
        }

        if (hyperLink.getStyle() != null) {
            applyMStyle(linkRun, hyperLink.getStyle());
        }
        cLink.setRArray(new CTR[] {linkRun.getCTR() });
        res.removeRun(res.getRuns().indexOf(linkRun));

        return res;
    }

    /**
     * Inserts the given {@link MImage}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param run
     *            the {@link XWPFRun} to insert to
     * @param image
     *            the {@link MImage} to insert
     */
    private void insertMImage(XWPFParagraph paragraph, XWPFRun run, MImage image) {
        try {
            final MImage asoluteImage = getAbsoluteSizedImage(paragraph, image);
            final int heigth = Units.toEMU(asoluteImage.getHeight());
            final int width = Units.toEMU(asoluteImage.getWidth());
            try (InputStream imageStream = asoluteImage.getInputStream()) {
                run.addPicture(imageStream, asoluteImage.getType().getPoiType(), asoluteImage.getURI().toString(),
                        width, heigth);
            }
        } catch (InvalidFormatException e) {
            insertMessage(paragraph, ValidationMessageLevel.ERROR,
                    String.format(PICTURE_INVALID_FORMAT, image.getURI().toString(), e.getMessage()));
        } catch (IOException e) {
            insertMessage(paragraph, ValidationMessageLevel.ERROR,
                    String.format(AN_I_O_PROBLEM_OCCURED_WHILE_READING, image.getURI().toString(), e.getMessage()));
        }
    }

    /**
     * Gets the absolute sized image from the given {@link MImage}.
     * 
     * @param paragraph
     *            the current paragraph
     * @param image
     *            the {@link MImage}
     * @return the absolute sized image from the given {@link MImage}
     */
    private MImage getAbsoluteSizedImage(XWPFParagraph paragraph, MImage image) {
        final MImage res;

        if (image.getRelativeWidth() >= 0) {
            res = new AbosluteResizedImage(image);
            final int width = getAbsoluteImageWidth(paragraph, image);
            if (image.getRelativeHeight() >= 0) {
                final int height = getAbsoluteImageHeight(paragraph, image);
                res.setConserveRatio(false);
                res.setWidth(width);
                res.setHeight(height);
            } else {
                res.setWidth(width);
            }
        } else if (image.getRelativeHeight() >= 0) {
            res = new AbosluteResizedImage(image);
            final int height = getAbsoluteImageHeight(paragraph, image);
            res.setHeight(height);
        } else {
            res = image;
        }

        return res;
    }

    /**
     * Gets the image width.
     * 
     * @param paragraph
     *            the current paragraph.
     * @param image
     *            the {@link MImage}
     * @return the image width
     */
    private int getAbsoluteImageWidth(XWPFParagraph paragraph, MImage image) {
        final int res;

        if (paragraph.getCTP() != null && paragraph.getCTP().getPPr() != null
            && paragraph.getCTP().getPPr().getSectPr() != null) {
            final CTSectPr section = paragraph.getCTP().getPPr().getSectPr();
            res = getAbsoluteWidthFromSection(image, section);
        } else {
            // no section attached to the paragraph try the containing boby
            final IBody body = paragraph.getBody();
            if (body instanceof XWPFDocument) {
                final CTSectPr section = ((XWPFDocument) body).getDocument().getBody().getSectPr();
                res = getAbsoluteWidthFromSection(image, section);
            } else if (body instanceof XWPFHeaderFooter) {
                final CTSectPr section = ((XWPFHeaderFooter) body).getXWPFDocument().getDocument().getBody()
                        .getSectPr();
                res = getAbsoluteWidthFromSection(image, section);
            } else if (body instanceof XWPFTableCell) {
                final int cellWidth = ((XWPFTableCell) body).getWidth();
                res = (int) (cellWidth * image.getRelativeWidth() / 100.0d / UNIT_CONVERSION);
            } else {
                throw new UnsupportedOperationException("unknown type of IBody : " + body.getClass());
            }
        }

        return res;
    }

    /**
     * Gets the absolute width of the given {@link MImage} for the given section.
     * 
     * @param image
     *            the {@link MImage}
     * @param section
     *            the {@link CTSectPr}
     * @return the absolute width of the given {@link MImage} for the given section.
     */
    private int getAbsoluteWidthFromSection(MImage image, final CTSectPr section) {
        final int res;
        if (section.getPgSz() != null) {
            final int containerWidth;
            final int pageWidth = bigIntegerToInt((BigInteger) section.getPgSz().getW());
            if (section.getPgMar() != null) {
                final int leftMargin = bigIntegerToInt((BigInteger) section.getPgMar().getLeft());
                final int rightMargin = bigIntegerToInt((BigInteger) section.getPgMar().getRight());
                containerWidth = pageWidth - leftMargin - rightMargin;
            } else {
                containerWidth = pageWidth;
            }
            res = (int) (containerWidth * image.getRelativeWidth() / 100.0d / UNIT_CONVERSION);
        } else {
            res = image.getWidth();
        }
        return res;
    }

    /**
     * Gets the image height.
     * 
     * @param paragraph
     *            the current paragraph.
     * @param image
     *            the {@link MImage}
     * @return the image height
     */
    private int getAbsoluteImageHeight(XWPFParagraph paragraph, MImage image) {
        final int res;

        if (paragraph.getCTP() != null && paragraph.getCTP().getPPr() != null
            && paragraph.getCTP().getPPr().getSectPr() != null) {
            final CTSectPr section = paragraph.getCTP().getPPr().getSectPr();
            res = getAbsoluteHeightFromSection(image, section);
        } else {
            // no section attached to the paragraph try the containing boby
            final IBody body = paragraph.getBody();
            if (body instanceof XWPFDocument) {
                final CTSectPr section = ((XWPFDocument) body).getDocument().getBody().getSectPr();
                res = getAbsoluteHeightFromSection(image, section);
            } else if (body instanceof XWPFHeaderFooter) {
                final CTSectPr section = ((XWPFHeaderFooter) body).getXWPFDocument().getDocument().getBody()
                        .getSectPr();
                res = getAbsoluteHeightFromSection(image, section);
            } else if (body instanceof XWPFTableCell) {
                // TODO technically the cell height depend on its content
                res = image.getHeight();
            } else {
                throw new UnsupportedOperationException("unknown type of IBody : " + body.getClass());
            }
        }

        return res;
    }

    /**
     * Gets the absolute height of the given {@link MImage} for the given section.
     * 
     * @param image
     *            the {@link MImage}
     * @param section
     *            the {@link CTSectPr}
     * @return the absolute height of the given {@link MImage} for the given section.
     */
    private int getAbsoluteHeightFromSection(MImage image, final CTSectPr section) {
        final int res;
        if (section.getPgSz() != null) {
            final int containerHeight;
            final int pageHeight = bigIntegerToInt((BigInteger) section.getPgSz().getH());
            if (section.getPgMar() != null) {
                final int topMargin = bigIntegerToInt((BigInteger) section.getPgMar().getTop());
                final int bottomMargin = bigIntegerToInt((BigInteger) section.getPgMar().getBottom());
                containerHeight = pageHeight - topMargin - bottomMargin;
            } else {
                containerHeight = pageHeight;
            }
            res = (int) (containerHeight * image.getRelativeHeight() / 100.0d / UNIT_CONVERSION);
        } else {
            res = image.getHeight();
        }
        return res;
    }

    /**
     * Gets the value of the given {@link BigInteger}.
     * 
     * @param bigInteger
     *            the {@link BigInteger}
     * @return the value of the given {@link BigInteger}
     */
    private int bigIntegerToInt(BigInteger bigInteger) {
        final int res;

        if (bigInteger != null) {
            res = bigInteger.intValue();
        } else {
            res = 0;
        }

        return res;
    }

    /**
     * Insert the given {@link MPagination}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param run
     *            the {@link XWPFRun} to insert to
     * @param mPagination
     *            the {@link MPagination}
     * @return the last inserted {@link XWPFParagraph}
     */
    private XWPFParagraph insertMPagination(XWPFParagraph paragraph, XWPFRun run, MPagination mPagination) {
        final XWPFParagraph res;
        switch (mPagination) {
            case newColumn:
                final XWPFRun newColumnRun = insertFieldRunReplacement(paragraph, run, "");
                newColumnRun.addBreak(BreakType.COLUMN);
                res = (XWPFParagraph) newColumnRun.getParent();
                break;

            case newParagraph:
                res = createNewParagraph(generatedDocument, (XWPFParagraph) run.getParent());
                break;

            case newPage:
                final XWPFRun newPageRun = insertFieldRunReplacement(paragraph, run, "");
                newPageRun.addBreak(BreakType.PAGE);
                res = (XWPFParagraph) newPageRun.getParent();
                break;

            case newTableOfContent:
                CTP ctP = paragraph.getCTP();
                CTSimpleField toc = ctP.addNewFldSimple();
                toc.setInstr("TOC \\h");
                final STOnOff onOff = STOnOff.Factory.newInstance();
                onOff.setStringValue("true");
                toc.xsetDirty(onOff);
                res = paragraph;
                break;

            case newTextWrapping:
                final XWPFRun newTextWrappingRun = insertFieldRunReplacement(paragraph, run, "");
                newTextWrappingRun.addBreak(BreakType.TEXT_WRAPPING);
                res = (XWPFParagraph) newTextWrappingRun.getParent();
                break;

            case ligneBreak:
                final XWPFRun ligneBreakRun = insertFieldRunReplacement(paragraph, run, "");
                ligneBreakRun.addBreak();
                res = (XWPFParagraph) ligneBreakRun.getParent();
                break;

            default:
                throw new IllegalStateException("Not supported MPagination.");
        }

        return res;
    }

    /**
     * Inserts the given {@link MParagraph}.
     *
     * @param body
     *            the {@link IBody} to insert the paragraph into
     * @param paragraph
     *            the {@link MTable} to insert
     * @param run
     *            the source {@link XWPFRun}
     * @return the last inserted {@link XWPFParagraph}
     */
    private XWPFParagraph insertMParagraph(IBody body, MParagraph paragraph, XWPFRun run) {
        final XWPFParagraph newParagraph = createNewParagraph(body, (XWPFParagraph) run.getParent());

        setParagraphStyleName(paragraph, newParagraph);
        setParagraphHAlignment(paragraph, newParagraph);
        setParagraphNumbering(paragraph, newParagraph);
        setParagraphMargins(paragraph, newParagraph);
        setParagraphDirection(paragraph, newParagraph);
        setParagraphBorders(paragraph, newParagraph);
        setParagraphBackgroundColor(paragraph, newParagraph);

        return insertObject(newParagraph, paragraph.getContents(), run);
    }

    /**
     * Sets the style name of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphStyleName(MParagraph paragraph, final XWPFParagraph newParagraph) {
        if (paragraph.getStyleName() != null) {
            newParagraph.setStyle(paragraph.getStyleName());
        }
    }

    /**
     * Sets the horizontal alignment of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphHAlignment(MParagraph paragraph, final XWPFParagraph newParagraph) {
        if (paragraph.getHAlignment() != null) {
            newParagraph.setAlignment(getHAllignment(paragraph.getHAlignment()));
        }
    }

    /**
     * Sets the numbering of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphNumbering(MParagraph paragraph, final XWPFParagraph newParagraph) {
        if (paragraph.getNumberingID() != null) {
            newParagraph.setNumID(BigInteger.valueOf(paragraph.getNumberingID()));
        }
    }

    /**
     * Sets the margins of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphMargins(MParagraph paragraph, final XWPFParagraph newParagraph) {
        if (paragraph.getNumberingLevel() != null) {
            newParagraph.getCTP().getPPr().getNumPr().addNewIlvl()
                    .setVal(BigInteger.valueOf(paragraph.getNumberingLevel()));
            if (paragraph.getMarginLeft() != -1) {
                // CHECKSTYLE:OFF empiric constant
                newParagraph.setIndentationLeft(paragraph.getMarginLeft() * 14
                    + getNumberingIndentLeft(paragraph.getNumberingID(), paragraph.getNumberingLevel()));
                // CHECKSTYLE:ON
            }
        } else if (paragraph.getMarginLeft() != -1) {
            // CHECKSTYLE:OFF empiric constant
            newParagraph.setIndentationLeft(paragraph.getMarginLeft() * 14);
            // CHECKSTYLE:ON
        }
        if (paragraph.getMarginRight() != -1) {
            // CHECKSTYLE:OFF empiric constant
            newParagraph.setIndentationRight(paragraph.getMarginLeft() * 14);
            // CHECKSTYLE:ON
        }
        if (paragraph.getMarginTop() != -1) {
            // CHECKSTYLE:OFF empiric constant
            newParagraph.setSpacingBefore(paragraph.getMarginLeft() * 14);
            // CHECKSTYLE:ON
        }
        if (paragraph.getMarginBottom() != -1) {
            // CHECKSTYLE:OFF empiric constant
            newParagraph.setSpacingAfter(paragraph.getMarginLeft() * 14);
            // CHECKSTYLE:ON
        }
    }

    /**
     * Sets the background color of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphBackgroundColor(MParagraph paragraph, final XWPFParagraph newParagraph) {
        if (paragraph.getBackgroundColor() != null) {
            final CTPPr ctppr;
            if (newParagraph.getCTP().getPPr() != null) {
                ctppr = newParagraph.getCTP().getPPr();
            } else {
                ctppr = newParagraph.getCTP().addNewPPr();
            }
            final CTShd ctshd;
            if (ctppr.getShd() != null) {
                ctshd = ctppr.getShd();
            } else {
                ctppr.addNewShd();
                ctshd = ctppr.getShd();
            }
            ctshd.setVal(STShd.CLEAR);
            ctshd.setColor("auto");
            ctshd.setFill(hexColor(paragraph.getBackgroundColor()));
        }
    }

    /**
     * Sets the borders of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphBorders(MParagraph paragraph, final XWPFParagraph newParagraph) {
        final MBorder leftBorder = paragraph.getLeftBorder();
        if (leftBorder != null) {
            newParagraph.setBorderLeft(leftBorder.getType().toPOI());
            final Color color = leftBorder.getColor();
            if (color != null) {
                newParagraph.getCTP().getPPr().getPBdr().getLeft().setColor(hexColor(color));
            }
            final int size = leftBorder.getSize();
            if (size >= 0) {
                newParagraph.getCTP().getPPr().getPBdr().getLeft()
                        .setSz(BigInteger.valueOf(size * BORDER_SIZE_CONSTANT));
            }
        }
        final MBorder rightBorder = paragraph.getRightBorder();
        if (rightBorder != null) {
            newParagraph.setBorderRight(rightBorder.getType().toPOI());
            final Color color = rightBorder.getColor();
            if (color != null) {
                newParagraph.getCTP().getPPr().getPBdr().getRight().setColor(hexColor(color));
            }
            final int size = rightBorder.getSize();
            if (size >= 0) {
                newParagraph.getCTP().getPPr().getPBdr().getRight()
                        .setSz(BigInteger.valueOf(size * BORDER_SIZE_CONSTANT));
            }
        }
        final MBorder topBorder = paragraph.getTopBorder();
        if (topBorder != null) {
            newParagraph.setBorderTop(topBorder.getType().toPOI());
            final Color color = topBorder.getColor();
            if (color != null) {
                newParagraph.getCTP().getPPr().getPBdr().getTop().setColor(hexColor(color));
            }
            final int size = topBorder.getSize();
            if (size >= 0) {
                newParagraph.getCTP().getPPr().getPBdr().getTop()
                        .setSz(BigInteger.valueOf(size * BORDER_SIZE_CONSTANT));
            }
        }
        final MBorder bottomBorder = paragraph.getBottomBorder();
        if (bottomBorder != null) {
            newParagraph.setBorderBottom(bottomBorder.getType().toPOI());
            final Color color = bottomBorder.getColor();
            if (color != null) {
                newParagraph.getCTP().getPPr().getPBdr().getBottom().setColor(hexColor(color));
            }
            final int size = bottomBorder.getSize();
            if (size >= 0) {
                newParagraph.getCTP().getPPr().getPBdr().getBottom()
                        .setSz(BigInteger.valueOf(size * BORDER_SIZE_CONSTANT));
            }
        }
    }

    /**
     * Sets the direction of the given {@link XWPFParagraph} according to the given {@link MParagraph}.
     * 
     * @param paragraph
     *            the {@link MParagraph}
     * @param newParagraph
     *            the {@link XWPFParagraph}
     */
    private void setParagraphDirection(MParagraph paragraph, final XWPFParagraph newParagraph) {
        if (paragraph.getTextDirection() == Dir.LTR) {
            if (newParagraph.getCTP().getPPr() == null) {
                newParagraph.getCTP().addNewPPr();
            }
            if (newParagraph.getCTP().getPPr().getBidi() == null) {
                newParagraph.getCTP().getPPr().addNewBidi();
            }
            final CTOnOff value = CTOnOff.Factory.newInstance();
            final STOnOff onOff = STOnOff.Factory.newInstance();
            onOff.setStringValue("off");
            value.xsetVal(onOff);
            newParagraph.getCTP().getPPr().setBidi(value);
        } else if (paragraph.getTextDirection() == Dir.RTL) {
            if (newParagraph.getCTP().getPPr() == null) {
                newParagraph.getCTP().addNewPPr();
            }
            if (newParagraph.getCTP().getPPr().getBidi() == null) {
                newParagraph.getCTP().getPPr().addNewBidi();
            }
            final CTOnOff value = CTOnOff.Factory.newInstance();
            final STOnOff onOff = STOnOff.Factory.newInstance();
            onOff.setStringValue("on");
            value.xsetVal(onOff);
            newParagraph.getCTP().getPPr().setBidi(value);
        }
    }

    /**
     * Gets the left indentation for the given numbering ID and level.
     * 
     * @param numberingID
     *            the numbering ID
     * @param numberingLevel
     *            the numbering level
     * @return the left indentation for the given numbering ID and level
     */
    private int getNumberingIndentLeft(Long numberingID, Long numberingLevel) {
        int res = 0;

        if (numberingID != null) {
            CTAbstractNum absNum = null;
            for (RelationPart relationPart : generatedDocument.getXWPFDocument().getRelationParts()) {
                if (XWPFRelation.NUMBERING.getRelation().equals(relationPart.getRelationship().getRelationshipType())) {
                    final XWPFNumbering numbering = relationPart.getDocumentPart();
                    final CTNumbering ctNumbering = PaginationServices.getCTNumbering(numbering);
                    absNum = getAbstractNumbering(ctNumbering, numberingID);
                }
                if (absNum != null) {
                    for (CTLvl lvl : absNum.getLvlList()) {
                        if (lvl.getIlvl().longValue() == numberingLevel.longValue()) {
                            res = ((BigInteger) lvl.getPPr().getInd().getLeft()).intValue();
                        }
                    }
                }
            }
        } else {
            res = 0;
        }

        return res;
    }

    /**
     * Gets the {@link CTAbstractNum} with the given numbering ID from the given {@link CTNumbering}.
     * 
     * @param ctNumbering
     *            the {@link CTNumbering}
     * @param numberingID
     *            the numbering ID
     * @return the {@link CTAbstractNum} with the given numbering ID from the given {@link CTNumbering} if nay, <code>null</code> otherwise
     */
    private CTAbstractNum getAbstractNumbering(final CTNumbering ctNumbering, Long numberingID) {
        CTAbstractNum res = null;

        for (CTNum ctNum : ctNumbering.getNumList()) {
            if (ctNum.getNumId().longValue() == numberingID.longValue()) {
                for (CTAbstractNum ctAbsNumering : ctNumbering.getAbstractNumList()) {
                    if (ctAbsNumering.getAbstractNumId().equals(ctNum.getAbstractNumId().getVal())) {
                        res = ctAbsNumering;
                        break;
                    }
                }
            }
        }

        return res;
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
        final XWPFTable xwpfTable;
        if (generatedDocument instanceof XWPFDocument) {
            if (table.getLabel() != null) {
                final XWPFRun captionRun = run;
                final IRunBody runBody = captionRun.getParent();
                if (runBody instanceof XWPFParagraph) {
                    ((XWPFParagraph) runBody).setSpacingAfter(0);
                }
                captionRun.setText(table.getLabel());
                captionRun.setBold(true);
            }
            xwpfTable = ((XWPFDocument) generatedDocument).createTable();
            if (xwpfTable.getRows().size() > 0) {
                xwpfTable.removeRow(0);
            }
        } else if (generatedDocument instanceof XWPFHeaderFooter) {
            final XWPFHeaderFooter headerFooter = (XWPFHeaderFooter) generatedDocument;
            final int index = headerFooter._getHdrFtr().getTblList().size();
            final CTTbl ctTbl = headerFooter._getHdrFtr().insertNewTbl(index);
            xwpfTable = new XWPFTable(ctTbl, headerFooter);
            if (xwpfTable.getRows().size() > 0) {
                xwpfTable.removeRow(0);
            }
            headerFooter.insertTable(index, xwpfTable);
        } else if (generatedDocument instanceof XWPFTableCell) {
            XWPFTableCell tcell = (XWPFTableCell) generatedDocument;
            if (table.getLabel() != null) {
                final XWPFRun captionRun = run;
                final IRunBody runBody = captionRun.getParent();
                if (runBody instanceof XWPFParagraph) {
                    ((XWPFParagraph) runBody).setSpacingAfter(0);
                }
                captionRun.setText(table.getLabel());
                captionRun.setBold(true);
            }
            final CTTbl ctTbl = tcell.getCTTc().addNewTbl();

            xwpfTable = new XWPFTable(ctTbl, tcell);
            if (xwpfTable.getRows().size() > 0) {
                xwpfTable.removeRow(0);
            }
            final int tableRank = tcell.getTables().size();
            tcell.insertTable(tableRank, xwpfTable);
            // A paragraph is mandatory at the end of a cell, so let's always add one.
            tcell.addParagraph();
        } else {
            xwpfTable = null;
            insertMessage((XWPFParagraph) run.getParent(), ValidationMessageLevel.ERROR,
                    "m:table can't be inserted here.");
        }

        if (xwpfTable != null) {
            fillTable(xwpfTable, table);
            setTableAlign(xwpfTable, table);
        }
    }

    /**
     * Sets the given {@link XWPFTable} align according to the given {@link MTable}.
     * 
     * @param xwpfTable
     *            the {@link XWPFTable}
     * @param table
     *            the {@link MTable}
     */
    private void setTableAlign(XWPFTable xwpfTable, MTable table) {
        final MTableAlign tableAlign = table.getTableAlign();
        if (tableAlign != null) {
            switch (tableAlign) {
                case LEFT:
                    xwpfTable.setTableAlignment(TableRowAlign.LEFT);
                    break;

                case CENTER:
                    xwpfTable.setTableAlignment(TableRowAlign.CENTER);
                    break;

                case RIGHT:
                    xwpfTable.setTableAlignment(TableRowAlign.RIGHT);
                    break;

                default:
                    // nothing to do here
                    break;
            }
        }
    }

    /**
     * Fill a newly created word table with the data from an {@link MTable}.
     * 
     * @param xwpfTable
     *            The newly created word table
     * @param mTable
     *            The {@link MTable} that describes the data and styles to insert
     */
    private void fillTable(XWPFTable xwpfTable, MTable mTable) {

        final boolean removeBorders;
        if (mTable.getStyleID() != null) {
            xwpfTable.setStyleID(mTable.getStyleID());
            removeBorders = true;
            if (xwpfTable.getCTTbl().getTblPr() != null && xwpfTable.getCTTbl().getTblPr().isSetTblBorders()) {
                xwpfTable.getCTTbl().getTblPr().unsetTblBorders();
            }
        } else {
            removeBorders = false;
        }

        // Iterate over the rows
        for (MRow mRow : mTable.getRows()) {
            final XWPFTableRow xwpfRow = xwpfTable.createRow();
            while (!xwpfRow.getTableCells().isEmpty()) {
                xwpfRow.removeCell(0);
            }
            xwpfRow.getCtRow().getTcList().clear();
            setRowHeight(xwpfRow, mRow);

            // Iterate over the columns
            for (int column = 0; column < mRow.getCells().size(); column++) {
                final MCell mCell = mRow.getCells().get(column);
                final XWPFTableCell xwpfCell = xwpfRow.createCell();
                // Populate cell
                XWPFParagraph xwpfCellParagraph = xwpfCell.getParagraphs().get(0);
                xwpfCellParagraph.setSpacingBefore(0);
                xwpfCellParagraph.setSpacingAfter(0);
                if (mCell != null && mCell.getHAlignment() != null) {
                    xwpfCellParagraph.setAlignment(getHAllignment(mCell.getHAlignment()));
                }
                setCellContent(xwpfCell, mCell);
                setVMerge(xwpfCell, mCell);
                setHMerge(xwpfCell, mCell);
                setCellWidth(xwpfCell, mCell);
                if (removeBorders && xwpfCell.getCTTc().getTcPr() != null
                    && xwpfCell.getCTTc().getTcPr().isSetTcBorders()) {
                    xwpfCell.getCTTc().getTcPr().unsetTcBorders();
                }
            }
        }
    }

    /**
     * Sets the for the given row.
     * 
     * @param xwpfRow
     *            the {@link XWPFTableRow}
     * @param mRow
     *            the {@link MRow}
     */
    private void setRowHeight(XWPFTableRow xwpfRow, MRow mRow) {
        if (mRow != null) {
            if (mRow.getHeightRule() != null) {
                final TableRowHeightRule rule;
                switch (mRow.getHeightRule()) {
                    case AUTO:
                        rule = TableRowHeightRule.AUTO;
                        break;

                    case EXACT:
                        rule = TableRowHeightRule.AT_LEAST;
                        break;

                    case AT_LEAST:
                        rule = TableRowHeightRule.AT_LEAST;
                        break;

                    default:
                        throw new IllegalStateException();
                }
                xwpfRow.setHeightRule(rule);
            }
            if (mRow.getHeight() != -1) {
                xwpfRow.setHeight(mRow.getHeight());
            }
        }
    }

    /**
     * Sets the width for the given cell.
     * 
     * @param xwpfCell
     *            the {@link XWPFTableCell}
     * @param mCell
     *            the {@link MCell}
     */
    private void setCellWidth(XWPFTableCell xwpfCell, MCell mCell) {
        if (mCell != null) {
            if (mCell.getWidthType() != null) {
                final TableWidthType type;
                switch (mCell.getWidthType()) {
                    case AUTO:
                        type = TableWidthType.AUTO;
                        break;

                    case DXA:
                        type = TableWidthType.DXA;
                        break;

                    case NIL:
                        type = TableWidthType.NIL;
                        break;

                    case PCT:
                        type = TableWidthType.PCT;
                        break;

                    default:
                        throw new IllegalStateException();
                }
                xwpfCell.setWidthType(type);
            }
            if (mCell.getWitdh() != -1) {
                xwpfCell.setWidth(String.valueOf(mCell.getWitdh()));
            }
        }
    }

    /**
     * Sets the vertical merge for the given cell.
     * 
     * @param xwpfCell
     *            the {@link XWPFTableCell}
     * @param mCell
     *            the {@link MCell}
     */
    private void setVMerge(XWPFTableCell xwpfCell, MCell mCell) {
        if (mCell != null && mCell.getVMerge() != null) {
            final CTVMerge hmerge = CTVMerge.Factory.newInstance();
            switch (mCell.getVMerge()) {
                case RESTART:
                    hmerge.setVal(STMerge.RESTART);
                    break;

                case CONTINUE:
                    hmerge.setVal(STMerge.CONTINUE);
                    break;

                default:
                    throw new IllegalStateException();
            }
            getTcPr(xwpfCell).setVMerge(hmerge);
        }
    }

    /**
     * Gets the {@link CTTcPr} of the given {@link XWPFTableCell}.
     * 
     * @param xwpfCell
     *            the {@link XWPFTableCell}
     * @return the {@link CTTcPr} of the given {@link XWPFTableCell}
     */
    private CTTcPr getTcPr(XWPFTableCell xwpfCell) {
        if (!xwpfCell.getCTTc().isSetTcPr()) {
            xwpfCell.getCTTc().addNewTcPr();
        }

        return xwpfCell.getCTTc().getTcPr();
    }

    /**
     * Sets the horizontal merge for the given cell.
     * 
     * @param xwpfCell
     *            the {@link XWPFTableCell}
     * @param mCell
     *            the {@link MCell}
     */
    private void setHMerge(XWPFTableCell xwpfCell, MCell mCell) {
        if (mCell != null && mCell.getHMerge() != null) {
            final CTHMerge hmerge = CTHMerge.Factory.newInstance();
            switch (mCell.getHMerge()) {
                case RESTART:
                    hmerge.setVal(STMerge.RESTART);
                    break;

                case CONTINUE:
                    hmerge.setVal(STMerge.CONTINUE);
                    break;

                default:
                    throw new IllegalStateException();
            }
            getTcPr(xwpfCell).setHMerge(hmerge);
        }
    }

    /**
     * Converts the horizontal alignment.
     * 
     * @param hAlignment
     *            the horizontal alignment
     * @return the horizontal alignment
     */
    private ParagraphAlignment getHAllignment(HAlignment hAlignment) {
        final ParagraphAlignment res;

        switch (hAlignment) {
            case BOTH:
                res = ParagraphAlignment.BOTH;
                break;

            case CENTER:
                res = ParagraphAlignment.CENTER;
                break;

            case DISTRIBUTE:
                res = ParagraphAlignment.DISTRIBUTE;
                break;

            case HIGH_KASHIDA:
                res = ParagraphAlignment.HIGH_KASHIDA;
                break;

            case LEFT:
                res = ParagraphAlignment.LEFT;
                break;

            case LOW_KASHIDA:
                res = ParagraphAlignment.LOW_KASHIDA;
                break;

            case MEDIUM_KASHIDA:
                res = ParagraphAlignment.MEDIUM_KASHIDA;
                break;

            case NUM_TAB:
                res = ParagraphAlignment.NUM_TAB;
                break;

            case RIGHT:
                res = ParagraphAlignment.RIGHT;
                break;

            case THAI_DISTRIBUTE:
                res = ParagraphAlignment.THAI_DISTRIBUTE;
                break;

            default:
                throw new IllegalStateException("can't convert " + hAlignment);
        }

        return res;
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
            final MElement contents = mCell.getContents();
            if (mCell.getVAlignment() != null) {
                cell.setVerticalAlignment(getVAglignment(mCell.getVAlignment()));
            }
            if (contents != null) {
                final IBody savedGeneratedDocument = generatedDocument;
                final XWPFParagraph savedGeneratedParagraph = currentGeneratedParagraph;
                final XWPFParagraph savedTemplateParagraph = currentTemplateParagraph;
                final boolean savedforceNewParagraph = forceNewParagraph;
                generatedDocument = cell;
                currentGeneratedParagraph = cellParagraph;
                currentTemplateParagraph = cellParagraph;
                forceNewParagraph = false;
                try {
                    insertObject(cellParagraph, contents, cellRun);
                } finally {
                    generatedDocument = savedGeneratedDocument;
                    currentGeneratedParagraph = savedGeneratedParagraph;
                    currentTemplateParagraph = savedTemplateParagraph;
                    forceNewParagraph = savedforceNewParagraph;
                }
                cellParagraph.removeRun(cellParagraph.getRuns().indexOf(cellRun));
                if (startWithMParagraph(contents)) {
                    cell.removeParagraph(0);
                }
            }
            final Color backGroundColor = mCell.getBackgroundColor();
            if (backGroundColor != null) {
                cell.setColor(hexColor(backGroundColor));
            }
        }
    }

    /**
     * Tells if the given {@link MElement} starts with a {@link MParagraph}.
     * 
     * @param contents
     *            the {@link MElement}
     * @return <code>true</code> if the given {@link MElement} starts with a {@link MParagraph},
     */
    private boolean startWithMParagraph(MElement contents) {
        final boolean res;

        if (contents instanceof MParagraph) {
            res = true;
        } else if (contents instanceof MList) {
            final MList mList = (MList) contents;
            res = !mList.isEmpty() && startWithMParagraph(mList.get(0));
        } else {
            res = false;
        }

        return res;
    }

    /**
     * Converts the vertical alignment.
     * 
     * @param vAlignment
     *            the vertical alignment
     * @return the vertical alignment
     */
    private XWPFVertAlign getVAglignment(VAlignment vAlignment) {
        final XWPFVertAlign res;

        switch (vAlignment) {
            case BOTH:
                res = XWPFVertAlign.BOTH;
                break;

            case BOTTOM:
                res = XWPFVertAlign.BOTTOM;
                break;

            case CENTER:
                res = XWPFVertAlign.CENTER;
                break;

            case TOP:
                res = XWPFVertAlign.TOP;
                break;

            default:
                throw new IllegalStateException("can't convert " + vAlignment);
        }

        return res;
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
        if (style.getFontName() != null) {
            run.setFontFamily(style.getFontName());
        }
        if (style.getFontModifiers() != -1) {
            run.setBold((style.getFontModifiers() & MStyle.FONT_BOLD) != 0);
            run.setItalic((style.getFontModifiers() & MStyle.FONT_ITALIC) != 0);
            if ((style.getFontModifiers() & MStyle.FONT_UNDERLINE) != 0) {
                run.setUnderline(UnderlinePatterns.SINGLE);
            }
            run.setStrikeThrough((style.getFontModifiers() & MStyle.FONT_STRIKE_THROUGH) != 0);
            if ((style.getFontModifiers() & MStyle.SUBSCRIPT) != 0) {
                run.setSubscript(VerticalAlign.SUBSCRIPT);
            }
            if ((style.getFontModifiers() & MStyle.SUPERSCRIPT) != 0) {
                run.setSubscript(VerticalAlign.SUPERSCRIPT);
            }
            if ((style.getFontModifiers() & MStyle.FONT_SMALL_CAPS) != 0) {
                run.setSmallCaps(true);
            }
        }
        if (style.getForegroundColor() != null) {
            run.setColor(hexColor(style.getForegroundColor()));
        }
        if (style.getBackgroundColor() != null) {
            final CTRPr ctrpr;
            if (run.getCTR().getRPr() != null) {
                ctrpr = run.getCTR().getRPr();
            } else {
                ctrpr = run.getCTR().addNewRPr();
            }
            final CTShd[] ctshd;
            if (ctrpr.getShdArray() != null && ctrpr.getShdArray().length != 0) {
                ctshd = ctrpr.getShdArray();
            } else {
                ctrpr.addNewShd();
                ctshd = ctrpr.getShdArray();
            }
            for (int i = 0; i < ctshd.length; i++) {
                ctshd[i].setVal(STShd.CLEAR);
                ctshd[i].setColor("auto");
                ctshd[i].setFill(hexColor(style.getBackgroundColor()));
            }
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
    public XWPFParagraph caseRepetition(Repetition repetition) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(repetition)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, repetition, INVALID_REPETITION_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(repetition.getQuery(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, repetition,
                        queryResult.getDiagnostic());
            } else {
                final List<Object> iteration = new ArrayList<>();
                if (queryResult.getResult() instanceof Collection) {
                    iteration.addAll((Collection<?>) queryResult.getResult());
                } else if (queryResult.getResult() != null) {
                    iteration.add(queryResult.getResult());
                } else {
                    insertMessage(currentParagraph, ValidationMessageLevel.WARNING,
                            repetition.getIterationVar() + " value is null.");
                }
                final Map<String, Object> newVariables = new HashMap<>(variablesStack.peek());
                variablesStack.push(newVariables);
                try {
                    for (Object val : iteration) {
                        newVariables.put(repetition.getIterationVar(), val);
                        currentParagraph = doSwitch(repetition.getBody());
                        closingRepretition(repetition);
                    }
                } finally {
                    variablesStack.pop();
                }
            }
        }

        return currentParagraph;

    }

    @Override
    public XWPFParagraph doSwitch(EObject eObject) {
        if (!monitor.isCanceled()) {
            return super.doSwitch(eObject);
        }
        return null;

    }

    @Override
    public XWPFParagraph caseLet(Let let) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(let)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, let, INVALID_LET_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(let.getValue(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, let, queryResult.getDiagnostic());
            } else {
                final Map<String, Object> newVariables = new HashMap<>(variablesStack.peek());
                variablesStack.push(newVariables);
                try {
                    newVariables.put(let.getName(), queryResult.getResult());
                    currentParagraph = doSwitch(let.getBody());
                } finally {
                    variablesStack.pop();
                }
            }
        }

        return currentParagraph;
    }

    @Override
    public XWPFParagraph caseUserDoc(UserDoc userDoc) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(userDoc)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, userDoc, INVALID_USERDOC_STATEMENT);
        } else {
            final EvaluationResult queryResult = evaluator.eval(userDoc.getId(), variablesStack.peek());
            if (queryResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, userDoc,
                        queryResult.getDiagnostic());
            } else {
                if (queryResult.getResult() == null) {
                    insertMessage(currentParagraph, ValidationMessageLevel.ERROR, "User doc id can't be null.");
                } else {
                    final String id = queryResult.getResult().toString();
                    currentParagraph = insertUserContent(currentParagraph, userDoc, id);
                }
            }
        }

        return currentParagraph;
    }

    /**
     * Inserts the {@link UserContent}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param userDoc
     *            the source {@link UserDoc}
     * @param id
     *            the ID
     * @return the current {@link XWPFParagraph}
     */
    private XWPFParagraph insertUserContent(XWPFParagraph paragraph, UserDoc userDoc, final String id) {
        XWPFParagraph currentParagraph = paragraph;
        // Tag UserContent with evaluated id
        currentParagraph = addStartUserDocField(currentParagraph, userDoc, id);
        // manage user Doc Id Uniqueness
        manageUserDocIdUniqueness(currentParagraph, id, userDoc);
        // Copy userdoc content
        UserContent userContent = userContentManager.consumeUserContent(id);
        boolean needNewParagraphBeforeEndTag = true;
        if (userContent == null) {
            currentParagraph = doSwitch(userDoc.getBody());
        } else {
            try {
                currentParagraph = copier.copyUserContent(userContent, currentParagraph);
                needNewParagraphBeforeEndTag = copier.needNewParagraph();
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                insertMessage(currentParagraph, ValidationMessageLevel.ERROR,
                        UserContentManager.USERDOC_COPY_ERROR + e.getMessage());
            }
        }

        if (currentParagraph == null) {
            currentParagraph = createNewParagraph(generatedDocument,
                    (XWPFParagraph) userDoc.getRuns().get(0).getParent());
            needNewParagraphBeforeEndTag = false;
        }

        if (!currentParagraph.getCTP().xmlText()
                .contains("<w:instrText>" + TokenType.ENDUSERCONTENT.getValue() + "</w:instrText>")) {
            // Tag m:enduserContent
            currentParagraph = addEndUserContentField(currentParagraph, userDoc, needNewParagraphBeforeEndTag);
        }

        return currentParagraph;
    }

    /**
     * userDoc Id Uniqueness test.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param id
     *            the {@link UserDoc} evaluated ID
     * @param userdoc
     *            the {@link UserDoc}
     */
    private void manageUserDocIdUniqueness(XWPFParagraph paragraph, String id, UserDoc userdoc) {
        if (!encountereduserDocIds.add(id)) {
            // insert the error message.
            String msgError = "The id '" + id
                + "' is already used in generated document. Ids must be unique otherwise document part contained userContent could be lost at next generation.";
            insertMessage(paragraph, ValidationMessageLevel.ERROR, msgError);

            TemplateValidationMessage templateValidationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.ERROR, msgError, userdoc.getRuns().get(userdoc.getRuns().size() - 1));
            userdoc.getValidationMessages().add(templateValidationMessage);
        }
    }

    /**
     * Add Start UserContent word Document Field.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param userDoc
     *            the {@link UserDoc} where add field
     * @param id
     *            the {@link UserDoc} evaluated ID
     * @return the current {@link XWPFParagraph}
     */
    private XWPFParagraph addStartUserDocField(XWPFParagraph paragraph, UserDoc userDoc, String id) {
        final XWPFParagraph res;

        if (paragraph == null
            || userDoc.getRuns().size() != 0 && userDoc.getRuns().get(0).getParent() != currentTemplateParagraph) {
            final XWPFParagraph newParagraph = createNewParagraph(generatedDocument,
                    (XWPFParagraph) userDoc.getRuns().get(0).getParent());
            insertTag(newParagraph, TokenType.USERCONTENT.getValue() + " " + id);
            res = newParagraph;
        } else {
            insertTag(paragraph, TokenType.USERCONTENT.getValue() + " " + id);
            res = paragraph;
        }

        return res;
    }

    /**
     * Add End UserContent word Document Field.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param userDoc
     *            the {@link UserDoc} where add field
     * @param needNewParagraph
     *            need New Paragraph boolean
     * @return the current {@link XWPFParagraph}
     */
    private XWPFParagraph addEndUserContentField(XWPFParagraph paragraph, UserDoc userDoc, boolean needNewParagraph) {
        final XWPFParagraph res;

        if (needNewParagraph) {
            final XWPFParagraph newParagraph = createNewParagraph(generatedDocument, paragraph);
            insertTag(newParagraph, TokenType.ENDUSERCONTENT.getValue());
            res = newParagraph;
        } else {
            insertTag(paragraph, TokenType.ENDUSERCONTENT.getValue());
            res = paragraph;
        }

        return res;
    }

    /**
     * Insets a new tag with the given text.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param instrText
     *            the instruction text
     */
    private void insertTag(XWPFParagraph paragraph, String instrText) {
        paragraph.getCTP().addNewR().addNewFldChar().setFldCharType(STFldCharType.BEGIN);
        paragraph.getCTP().addNewR().addNewInstrText().setStringValue(instrText);
        paragraph.getCTP().addNewR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);
        paragraph.getCTP().addNewR().addNewFldChar().setFldCharType(STFldCharType.END);
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
        final int bodySize = repetition.getBody().getStatements().size();
        if (bodySize > 0 && repetition.getBody().getStatements().get(bodySize - 1).getRuns().size() > 0) {
            final IConstruct lastBodyPart = repetition.getBody().getStatements().get(bodySize - 1);
            final int runNumber = lastBodyPart.getRuns().size();
            final XWPFRun lastRun = lastBodyPart.getRuns().get(runNumber - 1);
            final int closingRunNumber = repetition.getClosingRuns().size();
            if (closingRunNumber > 0 && repetition.getClosingRuns().get(0).getParent() != lastRun.getParent()) {
                forceNewParagraph = true;
            }
        }
    }

    @Override
    public XWPFParagraph caseBlock(Block block) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(block)) {
            if (currentParagraph == null && block.getRuns().isEmpty()) {
                currentParagraph = createParagraph(generatedDocument);
            }
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, block, INVALID_BLOCK_STATEMENT);
        } else {
            for (IConstruct construct : block.getStatements()) {
                currentParagraph = doSwitch(construct);
            }
        }

        return currentParagraph;
    }

    @Override
    public XWPFParagraph caseConditional(Conditional conditional) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(conditional)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, conditional, INVALID_CONDITIONAL_STATEMENT);
        } else {
            final EvaluationResult evaluationResult = evaluator.eval(conditional.getCondition(), variablesStack.peek());
            if (evaluationResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, conditional,
                        evaluationResult.getDiagnostic());
                for (XWPFRun tagRun : conditional.getClosingRuns()) {
                    currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
                }
            } else if (evaluationResult.getResult() instanceof Boolean) {
                if ((Boolean) evaluationResult.getResult()) {
                    currentParagraph = doSwitch(conditional.getThen());
                } else if (conditional.getElse() != null) {
                    currentParagraph = doSwitch(conditional.getElse());
                }
            } else {
                for (XWPFRun tagRun : conditional.getRuns()) {
                    currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
                }
                insertMessage(currentParagraph, ValidationMessageLevel.ERROR,
                        "Condition evaluation result must be a boolean.");
                for (XWPFRun tagRun : conditional.getClosingRuns()) {
                    currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
                }
            }
        }

        return currentParagraph;
    }

    @Override
    public XWPFParagraph caseTable(Table table) {
        // Create the table structure in the destination document.

        CTTbl copy = (CTTbl) table.getTable().getCTTbl().copy();
        copy.getTrList().clear();
        final XWPFTable saveTable = currentGeneratedTable;
        try {
            final XWPFTable newTable = POIServices.getInstance().createTable(generatedDocument);
            newTable.getCTTbl().set(copy);
            currentGeneratedTable = newTable;
            // iterate on the row
            for (Row row : table.getRows()) {
                doSwitch(row);
            }
        } finally {
            currentGeneratedTable = saveTable;
        }

        currentTemplateParagraph = null;

        return null;
    }

    /**
     * Creates a {@link XWPFTable} in the given {@link IBody}.
     * 
     * @param body
     *            the {@link IBody} to insert to
     * @return the created {@link XWPFTable}
     * @see POIServices#createTable(IBody)
     */
    @Deprecated
    public static XWPFTable createTable(IBody body) {
        return POIServices.getInstance().createTable(body);
    }

    @Override
    public XWPFParagraph caseRow(Row row) {
        final XWPFTableRow savedRow = currentGeneratedRow;
        try {
            currentGeneratedRow = new XWPFTableRow(currentGeneratedTable.getCTTbl().addNewTr(), currentGeneratedTable);
            final CTRow ctRow = (CTRow) row.getTableRow().getCtRow().copy();
            ctRow.getTcList().clear();
            while (!currentGeneratedRow.getTableCells().isEmpty()) {
                currentGeneratedRow.removeCell(0);
            }

            currentGeneratedRow.getCtRow().set(ctRow);
            // iterate on cells.
            for (Cell cell : row.getCells()) {
                doSwitch(cell);
            }
        } finally {
            currentGeneratedRow = savedRow;
        }

        return currentGeneratedParagraph;
    }

    @Override
    public XWPFParagraph caseCell(Cell cell) {
        final XWPFTableCell newCell = new XWPFTableCell(currentGeneratedRow.getCtRow().addNewTc(), currentGeneratedRow,
                generatedDocument);
        final CTTc ctCell = (CTTc) cell.getTableCell().getCTTc().copy();
        ctCell.getPList().clear();
        ctCell.getTblList().clear();
        ctCell.getSdtList().clear();
        ctCell.getPList().clear();
        newCell.getCTTc().set(ctCell);

        final IBody savedGeneratedDocument = generatedDocument;
        final XWPFParagraph savedGeneratedParagraph = currentGeneratedParagraph;
        final XWPFParagraph savedTemplateParagraph = currentTemplateParagraph;
        generatedDocument = newCell;
        try {
            doSwitch(cell.getBody());
        } finally {
            generatedDocument = savedGeneratedDocument;
            currentGeneratedParagraph = savedGeneratedParagraph;
            currentTemplateParagraph = savedTemplateParagraph;
        }

        // prevent cell with no paragraph
        if (newCell.getParagraphs().size() == 0) {
            final XWPFParagraph newParagraph = newCell.addParagraph();
            newParagraph.createRun();
        } else if (newCell.getParagraphs().size() == 1) {
            final XWPFParagraph firstParagraph = newCell.getParagraphs().get(0);
            if (firstParagraph.getRuns().isEmpty()) {
                newCell.getCTTc().addNewP();
            }
        }

        return currentGeneratedParagraph;
    }

    @Override
    public XWPFParagraph caseBookmark(Bookmark bookmark) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(bookmark)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, bookmark, INVALID_BOOKMARK_STATEMENT);
        } else {
            final EvaluationResult evaluationResult = evaluator.eval(bookmark.getName(), variablesStack.peek());
            if (evaluationResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, bookmark,
                        evaluationResult.getDiagnostic());
            } else {
                if (currentParagraph == null) {
                    currentParagraph = createNewParagraph(generatedDocument,
                            (XWPFParagraph) bookmark.getStyleRun().getParent());
                }
                bookmarkManager.startBookmark(result, currentParagraph, evaluationResult.getResult().toString());
                currentParagraph = doSwitch(bookmark.getBody());
                bookmarkManager.endBookmark(result, currentParagraph, evaluationResult.getResult().toString());
            }
        }

        return currentParagraph;
    }

    @Override
    public XWPFParagraph caseContentControl(ContentControl contentControl) {
        final CTSdtBlock sdtBlock;

        if (generatedDocument instanceof XWPFDocument) {
            sdtBlock = ((XWPFDocument) generatedDocument).getDocument().getBody().addNewSdt();
        } else if (generatedDocument instanceof XWPFHeaderFooter) {
            sdtBlock = ((XWPFHeaderFooter) generatedDocument)._getHdrFtr().addNewSdt();
        } else if (generatedDocument instanceof XWPFFootnote) {
            sdtBlock = ((XWPFFootnote) generatedDocument).getCTFtnEdn().addNewSdt();
        } else if (generatedDocument instanceof XWPFTableCell) {
            sdtBlock = ((XWPFTableCell) generatedDocument).getCTTc().addNewSdt();
        } else {
            throw new IllegalStateException(
                    "can't insert control in " + generatedDocument.getClass().getCanonicalName());
        }

        sdtBlock.set(contentControl.getBlock().copy());
        new XWPFSDT(sdtBlock, generatedDocument); // this do the insertion

        return currentGeneratedParagraph;
    }

    /**
     * Inserts evaluation messages for the given {@link IConstruct}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param construct
     *            the {@link IConstruct}
     * @param diagnostic
     *            the {@link Diagnostic}
     * @return the {@link XWPFParagraph}
     */
    protected XWPFParagraph insertQueryEvaluationMessages(XWPFParagraph paragraph, IConstruct construct,
            Diagnostic diagnostic) {
        XWPFParagraph currentParagraph = paragraph;
        for (XWPFRun tagRun : construct.getRuns()) {
            currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
        }
        for (TemplateValidationMessage message : M2DocUtils.appendDiagnosticMessage(currentParagraph, diagnostic)) {
            result.addMessage(message);
        }
        for (XWPFRun tagRun : construct.getClosingRuns()) {
            currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
        }

        return currentParagraph;
    }

    /**
     * Inserts AQL query syntax messages for the given {@link IConstruct}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param construct
     *            the {@link IConstruct}
     * @param errorPrefix
     *            error message prefix
     * @return the {@link XWPFParagraph}
     */
    protected XWPFParagraph insertQuerySyntaxMessages(XWPFParagraph paragraph, IConstruct construct,
            String errorPrefix) {
        XWPFParagraph currentParagraph = paragraph;
        for (XWPFRun tagRun : construct.getRuns()) {
            currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
        }
        for (TemplateValidationMessage message : construct.getValidationMessages()) {
            insertMessage(currentParagraph, message.getLevel(), errorPrefix + message.getMessage());
        }
        for (XWPFRun tagRun : construct.getClosingRuns()) {
            currentParagraph = (XWPFParagraph) insertRun(currentParagraph, tagRun).getParent();
        }

        return currentParagraph;
    }

    /**
     * Inserts the given message with the given {@link ValidationMessageLevel}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph} to modify
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message
     */
    protected void insertMessage(XWPFParagraph paragraph, ValidationMessageLevel level, String message) {
        result.addMessage(M2DocUtils.appendMessageRun(paragraph, level, message));
    }

    @Override
    public XWPFParagraph caseLink(Link link) {
        XWPFParagraph currentParagraph = currentGeneratedParagraph;
        if (hasError(link)) {
            currentParagraph = insertQuerySyntaxMessages(currentParagraph, link, INVALID_LINK_STATEMENT);
        } else {
            final EvaluationResult nameResult = evaluator.eval(link.getName(), variablesStack.peek());
            if (nameResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                currentParagraph = insertQueryEvaluationMessages(currentParagraph, link, nameResult.getDiagnostic());
            } else {
                final EvaluationResult textResult = evaluator.eval(link.getText(), variablesStack.peek());
                if (nameResult.getDiagnostic().getSeverity() != Diagnostic.OK) {
                    currentParagraph = insertQueryEvaluationMessages(currentParagraph, link,
                            textResult.getDiagnostic());
                } else {
                    bookmarkManager.insertReference(currentParagraph, nameResult.getResult().toString(),
                            textResult.getResult().toString(), false);
                }
            }
        }

        return currentParagraph;
    }

}
