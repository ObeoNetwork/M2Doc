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
import org.obeonetwork.m2doc.provider.DiagramProvider;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractProvider;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.util.TemplateSwitch;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;

/**
 * The {@link TemplateProcessor} class implements a switch over template that generates the doc.
 * 
 * @author Romain Guider
 */
public class TemplateProcessor extends TemplateSwitch<AbstractConstruct> {
    private static final String QUERY_EVALERROR_MESSAGE = "Couldn't evaluate query.";
    private static final String QUERY_SYNTAX_ERROR_MESSAGE = "Syntax error in AQL expression.";
    /**
     * constant defining the color of error messages.
     */
    private static final String ERROR_COLOR = "FF0000";
    /**
     * The path to the root project where the template is located.
     */
    private String rootProjectPath;
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
     * @param queryEnvironment
     *            the query environment used to evaluate queries in the
     * @param destinationDocument
     *            the path to the destination document.
     * @param targetConfObject
     *            the root EObject of the gen conf model.
     */
    public TemplateProcessor(Map<String, Object> initialDefs, String projectPath, IQueryEnvironment queryEnvironment,
            IBody destinationDocument, EObject theTargetConfObject) {
        this.rootProjectPath = projectPath;
        this.definitions = new GenerationEnvironment(initialDefs);
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
     * @param queryEnvironment
     *            the query environment used to evaluate queries in the
     *            template.
     * @param destinationDocument
     *            the path to the destination document.
     * @param targetConfObject
     *            the root EObject of the gen conf model.
     */
    public TemplateProcessor(GenerationEnvironment defs, IQueryEnvironment queryEnvironment, IBody destinationDocument,
            EObject theTargetConfObject) {
        this.definitions = defs;
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
        XWPFRun generatedRun = currentGeneratedParagraph.createRun();
        generatedRun.getCTR().set(srcRun.getCTR());
        return generatedRun;
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
        if (srcRun.getParagraph() != currentTemplateParagraph || forceNewParagraph) {
            createNewParagraph(srcRun.getParagraph());
            forceNewParagraph = false;
        }
        // creates as many paragraphs as there are '\n's in the string.
        String[] fragments = replacement.split("\n");
        XWPFRun result = null;
        int size = fragments.length;
        for (int i = 0; i < size - 1; i++) {
            XWPFRun generatedRun = insertFragment(fragments[i], srcRun);
            if (result == null) {
                result = generatedRun;
            }
            createNewParagraph(srcRun.getParagraph());
        }
        if (size > 0) {
            XWPFRun generatedRun = insertFragment(fragments[size - 1], srcRun);
            if (result == null) {
                result = generatedRun;
            }
        }
        return result;
    }

    /**
     * Insert a new run for a fragment of text following a '\n' character.
     * 
     * @param fragment
     *            the text fragment to insert
     * @param srcRun
     *            the run to copy the style from.
     * @return the generated run.
     */
    private XWPFRun insertFragment(String fragment, XWPFRun srcRun) {
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
            strResult = QUERY_SYNTAX_ERROR_MESSAGE + ":" + object.getParsingErrors().get(0).getMessage();
        } else {
            @SuppressWarnings("restriction")
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
            run.setColor(ERROR_COLOR);
        }
        return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AbstractConstruct caseRepetition(Repetition object) {
        // first evaluate the query
        boolean validQuery = object.getQuery() != null;
        @SuppressWarnings("restriction")
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
                run.setColor(ERROR_COLOR);
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
                // paragraph there to take this into acount.
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
        @SuppressWarnings("restriction")
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
                run.setColor(ERROR_COLOR);
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
                TemplateProcessor processor = new TemplateProcessor(definitions, queryEnvironment, newCell,
                        targetConfObject);
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
            imageRun.setText("Picture in " + object.getFileName() + " has an invalid format.");
            imageRun.setBold(true);
            imageRun.setColor(ERROR_COLOR);
        } catch (FileNotFoundException e) {
            imageRun.setText("File " + filePath + " cannot be found.");
            imageRun.setBold(true);
            imageRun.setColor(ERROR_COLOR);
        } catch (IOException e) {
            imageRun.setText("An I/O Problem occured while reading file ");
            imageRun.setBold(true);
            imageRun.setColor(ERROR_COLOR);
        }
        return super.caseImage(object);
    }

    @Override
    public AbstractConstruct caseRepresentation(Representation object) {
        XWPFRun imageRun = insertRun(object.getStyleRun());
        IProvider provider = ProviderRegistry.INSTANCE.getProvider(object.getRepresentationProvider());
        if (provider == null) {
            imageRun.setText("The image tag is referencing an unknown diagram provider.");
            imageRun.setBold(true);
            imageRun.setColor(ERROR_COLOR);
        } else if (!(provider instanceof DiagramProvider)) {
            imageRun.setText("The image tag is referencing a provider that is not an instance of DiagramProvider.");
            imageRun.setBold(true);
            imageRun.setColor(ERROR_COLOR);
        } else {
            Map<String, Object> parameters;
            try {
                parameters = setupParametersMap(object, provider);
                List<String> imagePaths = ((DiagramProvider) provider).getRepresentationImagePath(parameters);
                for (String imagePath : imagePaths) {
                    try {
                        imageRun.setText("");
                        imageRun.getCTR().getInstrTextList().clear();

                        int heigth = Units.toEMU(object.getHeight());
                        int width = Units.toEMU(object.getWidth());
                        FileInputStream fileInputStream = new FileInputStream(imagePath);
                        try {
                            imageRun.addPicture(fileInputStream, getPictureType(imagePath), imagePath, width, heigth);
                        } finally {
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                        }
                    } catch (InvalidFormatException e) {
                        imageRun.setText("Picture in " + imagePath + " has an invalid format.");
                        imageRun.setBold(true);
                        imageRun.setColor(ERROR_COLOR);
                    } catch (FileNotFoundException e) {
                        imageRun.setText("File " + imagePath + " cannot be found.");
                        imageRun.setBold(true);
                        imageRun.setColor(ERROR_COLOR);
                    } catch (IOException e) {
                        imageRun.setText("An I/O Problem occured while reading file ");
                        imageRun.setBold(true);
                        imageRun.setColor(ERROR_COLOR);
                    }
                }
            } catch (IllegalArgumentException e) {
                imageRun.setText(e.getMessage());
                imageRun.setBold(true);
                imageRun.setColor(ERROR_COLOR);
            } catch (ProviderException e) {
                imageRun.setText("A problem occured while creating image from an IDiagramProvider : " + e.getMessage());
                imageRun.setBold(true);
                imageRun.setColor(ERROR_COLOR);
            }

        }

        return super.caseRepresentation(object);
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
        parameters.put(ProviderConstants.KEY_CONF_ROOT_OBJECT, targetConfObject);
        parameters.put(ProviderConstants.KEY_PROJECT_ROOT_PATH, rootProjectPath);
        parameters.put(ProviderConstants.KEY_IMAGE_HEIGHT, object.getHeight());
        parameters.put(ProviderConstants.KEY_IMAGE_WIDTH, object.getWidth());
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
    private void setGenericParameters(AbstractProvider templateProvider, Map<String, OptionType> optionTypes,
            Map<String, Object> parameters) throws IllegalArgumentException {
        EMap<String, Object> optionsMap = templateProvider.getOptionValueMap();
        Set<Entry<String, Object>> optionsMapEntries = optionsMap.entrySet();
        for (Entry<String, Object> optionsMapEntry : optionsMapEntries) {
            if ((optionTypes != null && optionTypes.get(optionsMapEntry.getKey()) == null) || optionTypes == null) {
                parameters.put(optionsMapEntry.getKey(), optionsMapEntry.getValue());
            } else if (optionTypes != null) {
                OptionType optionType = optionTypes.get(optionsMapEntry.getKey());
                if (OptionType.AQL_EXPRESSION == optionType) {
                    if (optionsMapEntry.getValue() == null) {
                        throw new IllegalArgumentException(QUERY_SYNTAX_ERROR_MESSAGE + ":"
                            + templateProvider.getParsingErrors().get(0).getMessage());
                    } else {
                        @SuppressWarnings("restriction")
                        EvaluationResult result = new QueryEvaluationEngine(queryEnvironment)
                                .eval((AstResult) optionsMapEntry.getValue(), definitions.getCurrentDefinitions());
                        if (result == null) {
                            throw new IllegalArgumentException(QUERY_EVALERROR_MESSAGE);
                        } else if (result.getResult() == null) {
                            StringBuilder builder = new StringBuilder();
                            getDiagnostic(result.getDiagnostic(), builder);
                            throw new IllegalArgumentException(builder.toString());
                        } else {
                            parameters.put(optionsMapEntry.getKey(), result.getResult());
                        }
                    }

                } else {
                    throw new UnsupportedOperationException("All option types should be supported.");
                }
            }

        }
    }

}
