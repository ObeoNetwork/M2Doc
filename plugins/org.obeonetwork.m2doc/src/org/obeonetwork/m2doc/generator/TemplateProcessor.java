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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.apache.poi.xwpf.usermodel.XWPFHyperlinkRun;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.AbstractTableProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditionnal;
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
import org.obeonetwork.m2doc.template.util.TemplateSwitch;
import org.obeonetwork.m2doc.util.M2DocUtils;
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
public class TemplateProcessor extends TemplateSwitch<AbstractConstruct> {
    /**
     * Colon.
     */
    private static final String COLON = ":";
    /**
     * Error message when AQL query could not be evaluated.
     */
    private static final String QUERY_EVALERROR_MESSAGE = "Couldn't evaluate query.";
    /**
     * Error message when an AQL query contains syntax errors.
     */
    private static final String QUERY_SYNTAX_ERROR_MESSAGE = "Syntax error in AQL expression.";
    /**
     * The path to the root project where the template is located.
     */
    private String rootProjectPath;

    /**
     * The {@link BookmarkManager}.
     */
    private final BookmarkManager bookmarkManager;

    /**
     * The {@link IQueryEnvironment} instance used for evaluating all the AQL
     * queries found in the template.
     */
    private IQueryEnvironment queryEnvironment;

    /**
     * variable definition used during generation.
     */
    private GenerationEnvironment definitions;
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
     * Used to force a new paragraph in gf:for body when there's a carriage
     * return before the {gd:endfor} tag.
     */
    private boolean forceNewParagraph;
    /**
     * An EObject from the conf model from which the generation has been called.
     */
    private EObject targetConfObject;

    /**
     * Create a new {@link TemplateProcessor} instance given some definitions
     * and a query environment.
     * 
     * @param initialDefs
     *            the definitions used in queries and variable tags
     * @param projectPath
     *            the path to the project where the template is located.
     * @param bookmarkManager
     *            the {@link BookmarkManager}
     * @param queryEnvironment
     *            the query environment used to evaluate queries in the
     * @param destinationDocument
     *            the path to the destination document.
     * @param theTargetConfObject
     *            the root EObject of the gen conf model.
     */
    public TemplateProcessor(Map<String, Object> initialDefs, String projectPath, BookmarkManager bookmarkManager,
            IQueryEnvironment queryEnvironment, IBody destinationDocument, EObject theTargetConfObject) {
        this.rootProjectPath = projectPath;
        this.definitions = new GenerationEnvironment(initialDefs);
        this.bookmarkManager = bookmarkManager;
        this.queryEnvironment = queryEnvironment;
        this.generatedDocument = destinationDocument;
        this.targetConfObject = theTargetConfObject;
    }

    /**
     * Create a new {@link TemplateProcessor} instance given some definitions
     * and a query environment.
     * 
     * @param defs
     *            the definitions used in queries and variable tags
     * @param bookmarkManager
     *            the {@link BookmarkManager}
     * @param queryEnvironment
     *            the query environment used to evaluate queries in the
     *            template.
     * @param destinationDocument
     *            the path to the destination document.
     * @param theTargetConfObject
     *            the root EObject of the gen conf model.
     */
    public TemplateProcessor(GenerationEnvironment defs, BookmarkManager bookmarkManager,
            IQueryEnvironment queryEnvironment, IBody destinationDocument, EObject theTargetConfObject) {
        this.definitions = defs;
        this.bookmarkManager = bookmarkManager;
        this.queryEnvironment = queryEnvironment;
        this.generatedDocument = destinationDocument;
        this.targetConfObject = theTargetConfObject;
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

    @Override
    public AbstractConstruct caseTemplate(Template object) {
        List<AbstractConstruct> subConstructs = object.getSubConstructs();
        for (int i = 0; i < subConstructs.size(); i++) {
            doSwitch(subConstructs.get(i));
        }
        return object;
    }

    @Override
    public AbstractConstruct caseStaticFragment(StaticFragment object) {
        for (XWPFRun run : object.getRuns()) {
            insertRun(run);
        }
        return object;
    }

    /**
     * Inserts a run in the generated document. The new run is a copy from the specified run.
     * 
     * @param srcRun
     *            the run to copy
     * @return the inserted run.
     */
    @SuppressWarnings("deprecation")
    private XWPFRun insertRun(XWPFRun srcRun) {
        if (srcRun.getParagraph() != currentTemplateParagraph || forceNewParagraph) {
            createNewParagraph(srcRun.getParagraph());
            forceNewParagraph = false;
        }
        XWPFRun newRun = null;
        if (srcRun instanceof XWPFHyperlinkRun) {
            // Hyperlinks meta information is saved in the paragraph and not in the run. So we have to update the paragrapah with a copy of
            // the hyperlink to insert.
            CTHyperlink newHyperlink = currentGeneratedParagraph.getCTP().addNewHyperlink();
            newHyperlink.set(((XWPFHyperlinkRun) srcRun).getCTHyperlink());

            newRun = new XWPFHyperlinkRun(newHyperlink, srcRun.getCTR(), srcRun.getParagraph());
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
    @SuppressWarnings("deprecation")
    private XWPFRun insertFieldRunReplacement(XWPFRun srcRun, String replacement) {
        if (srcRun.getParagraph() != currentTemplateParagraph || forceNewParagraph) {
            createNewParagraph(srcRun.getParagraph());
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
    public AbstractConstruct caseQuery(Query object) {
        // first evaluate the query.
        String strResult;
        EvaluationResult result = null;
        if (object.getQuery() == null) {
            strResult = QUERY_SYNTAX_ERROR_MESSAGE + COLON + object.getValidationMessages().get(0).getMessage();
        } else {
            IQueryEvaluationEngine evaluator = new QueryEvaluationEngine(queryEnvironment);
            result = evaluator.eval(object.getQuery(), definitions.getCurrentDefinitions());
            if (result == null) {
                strResult = QUERY_EVALERROR_MESSAGE;
            } else if (result.getResult() == null) {
                StringBuilder builder = new StringBuilder();
                getDiagnostic(result.getDiagnostic(), builder);
                strResult = builder.toString();
            } else {
                strResult = result.getResult().toString();
            }
        }
        XWPFRun run = insertFieldRunReplacement(object.getStyleRun(), strResult);
        if (object.getQuery() == null || result == null || result.getResult() == null) {
            run.setBold(true);
            run.setColor(M2DocUtils.ERROR_COLOR);
        }
        return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AbstractConstruct caseRepetition(Repetition object) {
        // first evaluate the query
        boolean validQuery = object.getQuery() != null;
        EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval(object.getQuery(),
                definitions.getCurrentDefinitions());
        if (!validQuery || result == null || result.getDiagnostic().getCode() == Diagnostic.ERROR) {
            // insert the tag runs as is.
            for (XWPFRun tagRun : object.getRuns()) {
                insertRun(tagRun);
            }
            // insert the error message.
            XWPFRun run = currentGeneratedParagraph.createRun();
            if (!validQuery) {
                run.setText(QUERY_SYNTAX_ERROR_MESSAGE);
            } else if (result != null) {
                run.setText(result.getDiagnostic().getMessage());
            } else {
                run.setText(QUERY_EVALERROR_MESSAGE);
            }
            if (!validQuery || result == null || result.getDiagnostic().getCode() == Diagnostic.ERROR) {
                run.setBold(true);
                run.setColor(M2DocUtils.ERROR_COLOR);
            }

            for (XWPFRun tagRun : object.getClosingRuns()) {
                insertRun(tagRun);
            }
        } else {
            List<Object> iteration = new ArrayList<Object>();
            if (result.getResult() instanceof Collection) {
                iteration.addAll((Collection<? extends Object>) result.getResult());
            } else {
                iteration.add(result.getResult());
            }
            for (Object val : iteration) {
                this.definitions.setValue(object.getIterationVar(), val);
                for (AbstractConstruct construct : object.getSubConstructs()) {
                    doSwitch(construct);
                }
                // if the {gd:endfor} lies on a distinct paragraph, insert a new
                // paragraph there to take this into account.
                int bodySize = object.getSubConstructs().size();
                if (bodySize > 0 && object.getSubConstructs().get(bodySize - 1).getRuns().size() > 0) {
                    AbstractConstruct lastBodyPart = object.getSubConstructs().get(bodySize - 1);
                    int runNumber = lastBodyPart.getRuns().size();
                    XWPFRun lastRun = lastBodyPart.getRuns().get(runNumber - 1);
                    int closingRunNumber = object.getClosingRuns().size();
                    if (closingRunNumber > 0 && object.getClosingRuns().get(0).getParent() != lastRun.getParent()) {
                        forceNewParagraph = true;
                    }
                }
            }
        }
        return object;

    }

    @Override
    public AbstractConstruct caseConditionnal(Conditionnal object) {
        EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval(object.getQuery(),
                definitions.getCurrentDefinitions());
        boolean validQuery = object.getQuery() != null;
        if (validQuery && result != null && result.getResult() instanceof Boolean) {
            if ((Boolean) result.getResult()) {
                for (AbstractConstruct construct : object.getSubConstructs()) {
                    doSwitch(construct);
                }
            } else if (object.getAlternative() != null) {
                doSwitch(object.getAlternative());
            } else if (object.getElse() != null) {
                for (AbstractConstruct construct : object.getElse().getSubConstructs()) {
                    doSwitch(construct);
                }
            }
        } else {
            for (XWPFRun tagRun : object.getRuns()) {
                insertRun(tagRun);
            }
            XWPFRun run = currentGeneratedParagraph.createRun();
            String message;
            if (!validQuery) {
                message = QUERY_SYNTAX_ERROR_MESSAGE;
            } else if (result != null) {
                message = result.getDiagnostic().getMessage();
            } else {
                message = QUERY_EVALERROR_MESSAGE;
            }
            run.setText(message);
            if (!validQuery || result == null || result.getDiagnostic().getCode() == Diagnostic.ERROR) {
                run.setBold(true);
                run.setColor(M2DocUtils.ERROR_COLOR);
            }
            for (XWPFRun tagRun : object.getClosingRuns()) {
                insertRun(tagRun);
            }
        }
        return object;
    }

    @Override
    public AbstractConstruct caseTable(Table object) {
        // Create the table structure in the destination document.
        XWPFTable generatedTable;
        CTTbl copy = (CTTbl) object.getTable().getCTTbl().copy();
        copy.getTrList().clear();
        if (generatedDocument instanceof XWPFDocument) {
            generatedTable = ((XWPFDocument) generatedDocument).createTable();
            if (generatedTable.getRows().size() > 0) {
                generatedTable.removeRow(0);
            }
            generatedTable.getCTTbl().set(copy);
        } else if (generatedDocument instanceof XWPFTableCell) {
            XWPFTableCell tCell = (XWPFTableCell) generatedDocument;
            int tableRank = tCell.getTables().size();
            XWPFTable newTable = new XWPFTable(copy, tCell, 0, 0);
            if (newTable.getRows().size() > 0) {
                newTable.removeRow(0);
            }
            tCell.insertTable(tableRank, newTable);
            generatedTable = tCell.getTables().get(tableRank);
        } else {
            throw new UnsupportedOperationException("unknown type of IBody : " + generatedDocument.getClass());
        }
        // iterate on the row
        for (Row row : object.getRows()) {
            XWPFTableRow newRow = generatedTable.createRow();
            CTRow ctRow = (CTRow) row.getTableRow().getCtRow().copy();
            ctRow.getTcList().clear();
            newRow.getCtRow().set(ctRow);
            // iterate on cells.
            for (Cell cell : row.getCells()) {
                XWPFTableCell newCell = newRow.createCell();
                CTTc ctCell = (CTTc) cell.getTableCell().getCTTc().copy();
                ctCell.getPList().clear();
                ctCell.getTblList().clear();
                newCell.getCTTc().set(ctCell);
                // process the cell :
                TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, queryEnvironment,
                        newCell, targetConfObject);
                processor.doSwitch(cell.getTemplate());
            }
        }
        return super.caseTable(object);
    }

    /**
     * Returns the picture type based on the filename's extension.
     * 
     * @param fileName
     *            the picture file
     * @return the picture's file extension
     */
    private int getPictureType(String fileName) {
        String[] segments = fileName.split("\\.");
        int result;
        if (segments.length > 1) {
            String extension = segments[segments.length - 1].trim();
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
    public AbstractConstruct caseImage(Image object) {
        XWPFRun imageRun = insertRun(object.getStyleRun());
        imageRun.setText("");
        imageRun.getCTR().getInstrTextList().clear();
        String filePath;
        if ("".equals(this.rootProjectPath) || this.rootProjectPath == null) {
            filePath = object.getFileName();
        } else {
            filePath = this.rootProjectPath + "/" + object.getFileName();
        }
        try {
            int heigth = Units.toEMU(object.getHeight());
            int width = Units.toEMU(object.getWidth());
            FileInputStream imageStream = new FileInputStream(filePath);
            try {
                imageRun.addPicture(imageStream, getPictureType(object.getFileName()), object.getFileName(), width,
                        heigth);
            } finally {
                if (imageStream != null) {
                    imageStream.close();
                }
            }
        } catch (InvalidFormatException e) {
            setErrorMessageToRun("Picture in " + object.getFileName() + " has an invalid format.", imageRun);
        } catch (FileNotFoundException e) {
            setErrorMessageToRun("File " + filePath + " cannot be found.", imageRun);
        } catch (IOException e) {
            setErrorMessageToRun("An I/O Problem occured while reading file.", imageRun);
        }
        return super.caseImage(object);
    }

    @Override
    public AbstractConstruct caseRepresentation(Representation object) {
        XWPFRun imageRun = insertRun(object.getStyleRun());
        IProvider provider = object.getProvider();
        if (provider == null) {
            setErrorMessageToRun(object.getValidationMessages().get(0).getMessage(), imageRun);
        } else {
            Map<String, Object> parameters;
            try {
                parameters = setupParametersMap(object, provider);
                List<String> imagePaths = ((AbstractDiagramProvider) provider).getRepresentationImagePath(parameters);
                for (String imagePath : imagePaths) {
                    try {
                        imageRun.setText("");
                        imageRun.getCTR().getInstrTextList().clear();

                        // get default image size if needed
                        if (object.getHeight() == 0) {
                            object.setHeight(((AbstractDiagramProvider) provider).getHeight());
                        }
                        if (object.getWidth() == 0) {
                            object.setWidth(((AbstractDiagramProvider) provider).getWidth());
                        }
                        int height = Units.toEMU(object.getHeight());
                        int width = Units.toEMU(object.getWidth());
                        FileInputStream fileInputStream = new FileInputStream(imagePath);
                        try {
                            imageRun.addPicture(fileInputStream, getPictureType(imagePath), imagePath, width, height);
                        } finally {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        }
                    } catch (InvalidFormatException e) {
                        setErrorMessageToRun("Picture in " + imagePath + " has an invalid format.", imageRun);
                    } catch (FileNotFoundException e) {
                        setErrorMessageToRun("File " + imagePath + " cannot be found.", imageRun);
                    } catch (IOException e) {
                        setErrorMessageToRun("An I/O Problem occured while reading file.", imageRun);
                    }
                }
            } catch (IllegalArgumentException e) {
                setErrorMessageToRun(e.getMessage(), imageRun);
            } catch (ProviderException e) {
                setErrorMessageToRun(
                        "A problem occured while creating image from an diagram provider : " + e.getMessage(),
                        imageRun);
            }

        }

        return super.caseRepresentation(object);
    }

    @Override
    public AbstractConstruct caseTableClient(TableClient object) {
        XWPFRun tableRun = insertRun(object.getStyleRun());
        tableRun.getCTR().getInstrTextList().clear();
        AbstractTableProvider provider = (AbstractTableProvider) object.getProvider();
        if (provider == null) {
            setErrorMessageToRun(object.getValidationMessages().get(0).getMessage(), tableRun);
        } else {
            Map<String, Object> parameters;
            try {
                parameters = setupParametersMap(object, provider);
                TableClientProcessor tableProcessor = new TableClientProcessor(generatedDocument, provider, parameters);
                tableProcessor.generate(tableRun);
            } catch (IllegalArgumentException e) {
                setErrorMessageToRun(e.getMessage(), tableRun);
            } catch (ProviderException e) {
                setErrorMessageToRun("A problem occured while creating table from a table provider : " + e.getMessage(),
                        tableRun);
            }
        }
        return super.caseTableClient(object);
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
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, targetConfObject);
        parameters.put(ProviderConstants.PROJECT_ROOT_PATH_KEY, rootProjectPath);
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
    public AbstractConstruct caseBookmark(Bookmark bookmark) {
        if (bookmark.getName() == null) {
            XWPFRun run = insertRun(bookmark.getStyleRun());
            setErrorMessageToRun(
                    QUERY_SYNTAX_ERROR_MESSAGE + COLON + bookmark.getValidationMessages().get(0).getMessage(), run);
        } else {
            IQueryEvaluationEngine evaluator = new QueryEvaluationEngine(queryEnvironment);
            final EvaluationResult result = evaluator.eval(bookmark.getName(), definitions.getCurrentDefinitions());
            if (result == null) {
                XWPFRun run = insertRun(bookmark.getStyleRun());
                setErrorMessageToRun(QUERY_EVALERROR_MESSAGE, run);
            } else if (result.getResult() == null) {
                StringBuilder builder = new StringBuilder();
                getDiagnostic(result.getDiagnostic(), builder);
                XWPFRun run = insertRun(bookmark.getStyleRun());
                setErrorMessageToRun(builder.toString(), run);
            } else {
                bookmarkManager.startBookmark(currentGeneratedParagraph, result.getResult().toString());
                for (AbstractConstruct construct : bookmark.getSubConstructs()) {
                    doSwitch(construct);
                }
                bookmarkManager.endBookmark(currentGeneratedParagraph, result.getResult().toString());
            }
        }

        return super.caseBookmark(bookmark);
    }

    @Override
    public AbstractConstruct caseLink(Link link) {
        if (link.getName() == null) {
            XWPFRun run = insertRun(link.getStyleRun());
            setErrorMessageToRun(QUERY_SYNTAX_ERROR_MESSAGE + COLON + link.getValidationMessages().get(0).getMessage(),
                    run);
        } else {
            IQueryEvaluationEngine evaluator = new QueryEvaluationEngine(queryEnvironment);
            final EvaluationResult nameResult = evaluator.eval(link.getName(), definitions.getCurrentDefinitions());
            if (nameResult == null) {
                XWPFRun run = insertRun(link.getStyleRun());
                setErrorMessageToRun(QUERY_EVALERROR_MESSAGE, run);
            } else if (nameResult.getResult() == null) {
                StringBuilder builder = new StringBuilder();
                getDiagnostic(nameResult.getDiagnostic(), builder);
                XWPFRun run = insertRun(link.getStyleRun());
                setErrorMessageToRun(builder.toString(), run);
            } else {
                final EvaluationResult textResult = evaluator.eval(link.getText(), definitions.getCurrentDefinitions());
                if (textResult == null) {
                    XWPFRun run = insertRun(link.getStyleRun());
                    setErrorMessageToRun(QUERY_EVALERROR_MESSAGE, run);
                } else if (textResult.getResult() == null) {
                    StringBuilder builder = new StringBuilder();
                    getDiagnostic(textResult.getDiagnostic(), builder);
                    XWPFRun run = insertRun(link.getStyleRun());
                    setErrorMessageToRun(builder.toString(), run);
                } else {
                    bookmarkManager.insertReference(currentGeneratedParagraph, nameResult.getResult().toString(),
                            textResult.getResult().toString());
                }
            }
        }

        return super.caseLink(link);
    }

    /**
     * Sets an error message into the given run.
     * 
     * @param errorMessage
     *            the error message to set.
     * @param run
     *            the run were to insert the error message.
     */
    public static void setErrorMessageToRun(String errorMessage, XWPFRun run) {
        run.setText(errorMessage);
        run.setBold(true);
        run.setColor(M2DocUtils.ERROR_COLOR);
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
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, targetConfObject);
        parameters.put(ProviderConstants.PROJECT_ROOT_PATH_KEY, rootProjectPath);
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
                    QUERY_SYNTAX_ERROR_MESSAGE + COLON + templateProvider.getValidationMessages().get(0).getMessage());
        } else {
            EvaluationResult result = new QueryEvaluationEngine(queryEnvironment).eval((AstResult) aqlEntry.getValue(),
                    definitions.getCurrentDefinitions());
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

}
