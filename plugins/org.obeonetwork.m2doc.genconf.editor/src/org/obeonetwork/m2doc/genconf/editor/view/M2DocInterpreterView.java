/*******************************************************************************
 *  Copyright (c) 2021, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.EvaluationResult;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IQueryValidationEngine;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IValidationMessage;
import org.eclipse.acceleo.query.runtime.IValidationResult;
import org.eclipse.acceleo.query.runtime.ValidationMessageLevel;
import org.eclipse.acceleo.query.runtime.impl.QueryBuilderEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryEvaluationEngine;
import org.eclipse.acceleo.query.runtime.impl.QueryValidationEngine;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.commands.ActionHandler;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextListener;
import org.eclipse.jface.text.TextEvent;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.VerticalRuler;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.IHandlerActivation;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.texteditor.ITextEditorActionDefinitionIds;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.editor.view.aql.AQLConfiguration;
import org.obeonetwork.m2doc.genconf.editor.view.aql.ColorManager;
import org.obeonetwork.m2doc.genconf.editor.view.aql.ProposalLabelProvider;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.M2DocTemplateService;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Parameter;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.util.HtmlSerializer;
import org.obeonetwork.m2doc.util.IClassProvider;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * M2Doc {@link ViewPart}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocInterpreterView extends ViewPart {

    /**
     * Updates the browser with a given expression.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class UpdateBrowserRunnable implements Runnable {

        /**
         * The new expression.
         */
        private final String expression;

        /**
         * Constructor.
         * 
         * @param expression
         *            the new expression
         */
        private UpdateBrowserRunnable(String expression) {
            this.expression = expression;
        }

        @Override
        public void run() {
            if (!Thread.interrupted()) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        browser.setText(IN_PROGRESS_HTML);
                    }
                });
            }

            final String html;
            if (generation != null) {

                if (m2docEnv != null) {
                    m2docEnv.getBookmarkManager().reset();
                    m2docEnv.getUserContentManager().reset();
                }
                final IValidationResult validationResult = validationEngine.validate(expression, variableTypes);
                final String validationContent = getValidationHTMLContent(validationResult);

                final String evaluationContent;
                if (!hasError(validationResult)) {
                    final AstResult astBuilder = queryBuilder.build(expression);
                    final EvaluationResult evaluationResult = evaluationEngine.eval(astBuilder, allVariables);
                    evaluationContent = htmlSerializer.serialize(evaluationResult.getResult());
                } else {
                    evaluationContent = "";
                }
                html = validationContent + evaluationContent;
            } else {
                html = "You need to select a generation model (.genconf file).";
            }
            if (!Thread.interrupted()) {
                Display.getDefault().syncExec(new Runnable() {

                    @Override
                    public void run() {
                        browser.setText(html);
                    }
                });
            }

        }

        /**
         * Tells if the given {@link IValidationResult} has at least one {@link ValidationMessageLevel#ERROR}.
         * 
         * @param validationResult
         *            the {@link IValidationResult}
         * @return <code>true</code> if the given {@link IValidationResult} has at least one {@link ValidationMessageLevel#ERROR},
         *         <code>false</code> otherwise
         */
        private boolean hasError(IValidationResult validationResult) {
            for (IValidationMessage message : validationResult.getMessages()) {
                if (message.getLevel() == ValidationMessageLevel.ERROR) {
                    return true;
                }
            }

            return false;
        }
    }

    /**
     * Highlights info, warning, and error in the {@link SourceViewer}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static final class HighlightFunction extends BrowserFunction {

        /**
         * The function name.
         */
        private static final String NAME = "highlight";

        /**
         * The {@link SourceViewer} to highlight.
         */
        private SourceViewer sourceViewer;

        /**
         * Constructor.
         * 
         * @param browser
         *            the browser whose javascript can invoke this function
         * @param sourceViewer
         *            the {@link SourceViewer} to highlight
         */
        private HighlightFunction(Browser browser, SourceViewer sourceViewer) {
            super(browser, NAME);
            this.sourceViewer = sourceViewer;
        }

        @Override
        public Object function(Object[] arguments) {
            // arguments[0]: severity
            // arguments[1]: start
            // arguments[2]: end

            final Display display = Display.getDefault();
            final int start = ((Double) arguments[1]).intValue();
            final int length = ((Double) arguments[2]).intValue() - start;
            switch ((String) arguments[0]) {
                case "INFO":
                    sourceViewer.getTextWidget()
                            .setStyleRange(new StyleRange(start, length, null, display.getSystemColor(SWT.COLOR_CYAN)));
                    break;

                case "WARNING":
                    sourceViewer.getTextWidget().setStyleRange(
                            new StyleRange(start, length, null, display.getSystemColor(SWT.COLOR_YELLOW)));
                    break;

                case "ERROR":
                    sourceViewer.getTextWidget()
                            .setStyleRange(new StyleRange(start, length, null, display.getSystemColor(SWT.COLOR_RED)));
                    break;

                default:
                    break;
            }

            return null;
        }

    }

    /**
     * Clears Highlights in the {@link SourceViewer}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private static final class ClearHighlightFunction extends BrowserFunction {

        /**
         * The function name.
         */
        private static final String NAME = "clearHighlight";

        /**
         * The {@link SourceViewer} to highlight.
         */
        private SourceViewer sourceViewer;

        /**
         * Constructor.
         * 
         * @param browser
         *            the browser whose javascript can invoke this function
         * @param sourceViewer
         *            the {@link SourceViewer} to highlight
         */
        private ClearHighlightFunction(Browser browser, SourceViewer sourceViewer) {
            super(browser, NAME);
            this.sourceViewer = sourceViewer;
        }

        @Override
        public Object function(Object[] arguments) {
            // arguments[0]: severity
            // arguments[1]: start
            // arguments[2]: end

            final int start = ((Double) arguments[1]).intValue();
            final int length = ((Double) arguments[2]).intValue() - start;
            sourceViewer.getTextWidget().setStyleRange(new StyleRange(start, length, null, null));

            return null;
        }

    }

    /**
     * The in progress HTML.
     */
    private static final String IN_PROGRESS_HTML = initInProgressHTML();

    /**
     * The HTML validation message {@link String#format(String, Object...) format}.
     */
    private static final String VALIDATION_HTML_FORMAT = "<p onmouseover=\"" + HighlightFunction.NAME
        + "(%s,%s,%s);\" onmouseout=\"" + ClearHighlightFunction.NAME
        + "(%s,%s,%s);\" style=\"background-color: %s;\">%s: %s (%s, %s)</p>";

    /**
     * The genconf URI memento.
     */
    private static final String GENCONF_URI = "genconfURI";

    /**
     * The expression memento.
     */
    private static final String EXPRESSION = "expression";

    /**
     * The selection variable.
     */
    private static final String SELECTION_VARIABLE = "selection";

    /**
     * The {@link IMemento}.
     */
    private IMemento memento;

    /**
     * The {@link ColorManager}.
     */
    private final ColorManager colorManager = new ColorManager();

    /**
     * The {@link ILabelProvider}.
     */
    private final ILabelProvider labelProvider = new ProposalLabelProvider();

    /**
     * The {@link Generation} path label.
     */
    private Label genconfLabel;

    /**
     * The {@link SourceViewer}.
     */
    private SourceViewer sourceViewer;

    /**
     * The {@link Browser}.
     */
    private Browser browser;

    /**
     * The {@link ISelectionListener} updating the selection variable.
     */
    private ISelectionListener updateSelectionListener;

    /**
     * The {@link HtmlSerializer}.
     */
    private final HtmlSerializer htmlSerializer = new HtmlSerializer();

    /**
     * Content assist {@link IHandlerActivation}.
     */
    private IHandlerActivation contentAssistHandlerActivation;

    /**
     * The variables possible types.
     */
    private final Map<String, Set<IType>> variableTypes = new LinkedHashMap<>();

    /**
     * The variables value.
     */
    private Map<String, Object> allVariables;

    /**
     * The selection value.
     */
    private Object selectionValue;

    /**
     * The {@link IQueryEnvironment}.
     */
    private IQueryEnvironment queryEnvironment;

    /**
     * The {@link ResourceSet} for models.
     */
    private ResourceSet resourceSetForModels;

    /**
     * The {@link IQueryValidationEngine}.
     */
    private IQueryValidationEngine validationEngine;

    /**
     * The {@link QueryEvaluationEngine}.
     */
    private QueryEvaluationEngine evaluationEngine;

    /**
     * The {@link IQueryBuilderEngine}.
     */
    private IQueryBuilderEngine queryBuilder;

    /**
     * The {@link Generation}.
     */
    private Generation generation;

    /**
     * The {@link M2DocEvaluationEnvironment}.
     */
    private M2DocEvaluationEnvironment m2docEnv;

    /**
     * The single thread executor.
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    /**
     * The updating {@link Future}.
     */
    private Future<?> updatingFuture;

    @Override
    public void init(IViewSite site, IMemento memo) throws PartInitException {
        super.init(site, memo);
        this.memento = memo;
    }

    /**
     * Initializes the in progress HTML.
     * 
     * @return the inprogress HTML
     */
    private static String initInProgressHTML() {
        final StringBuilder res = new StringBuilder();

        try (InputStream stream = M2DocInterpreterView.class.getResourceAsStream("/icons/loader.gif")) {
            res.append("<img style=\"display: block;margin-left: auto;margin-right: auto;width: 10%;\" src=\"");
            res.append("data:image/gif;base64, ");
            final byte[] imageData = IOUtils.toByteArray(stream);
            res.append(new String(Base64.getEncoder().encode(imageData)));
            res.append("\"/>");
        } catch (IOException e) {
            return "";
        }

        return res.toString();
    }

    @Override
    public void saveState(IMemento memo) {
        super.saveState(memo);
        if (getGenconfURI() != null) {
            memo.putString(GENCONF_URI, getGenconfURI().toString());
        }
        memo.putString(EXPRESSION, sourceViewer.getTextWidget().getText());
    }

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(1, false));

        Composite sashComposite = new Composite(parent, SWT.NONE);
        sashComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        final GridLayout sashLayout = new GridLayout(1, false);
        sashComposite.setLayout(sashLayout);

        genconfLabel = new Label(sashComposite, SWT.BORDER);
        genconfLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));

        SashForm sashForm = new SashForm(sashComposite, SWT.VERTICAL);
        sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        sourceViewer = new SourceViewer(sashForm, new VerticalRuler(0), SWT.BORDER);
        sourceViewer.addTextListener(new ITextListener() {

            @Override
            public void textChanged(TextEvent event) {
                updateBrowser(sourceViewer.getTextWidget().getText());
            }
        });

        browser = new Browser(sashForm, SWT.BORDER);
        new HighlightFunction(browser, sourceViewer);
        new ClearHighlightFunction(browser, sourceViewer);

        sashForm.setWeights(new int[] {1, 5});

        if (memento != null) {
            final String savedGenconfURI = memento.getString(GENCONF_URI);
            if (savedGenconfURI != null) {
                setGenconfURI(URI.createURI(savedGenconfURI));
            }
            final String expression = memento.getString(EXPRESSION);
            if (expression != null) {
                sourceViewer.setInput(new Document(expression));
            } else {
                sourceViewer.setInput(new Document(""));
            }
        } else {
            sourceViewer.setInput(new Document(""));
        }

        final ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
        updateSelectionListener = new ISelectionListener() {

            @Override
            public void selectionChanged(IWorkbenchPart part, ISelection selection) {
                if (part != M2DocInterpreterView.this && generation != null) {
                    final LinkedHashSet<IType> selectionPossibleTypes = new LinkedHashSet<IType>();
                    if (selection instanceof IStructuredSelection) {
                        selectionValue = tryToAdaptToEObject(((IStructuredSelection) selection).getFirstElement());
                        selectionPossibleTypes.addAll(getPossibleTypesForValue(queryEnvironment, selectionValue));
                    }
                    variableTypes.put(SELECTION_VARIABLE, selectionPossibleTypes);
                    if (allVariables != null) {
                        allVariables.put(SELECTION_VARIABLE, selectionValue);
                    }
                    updateBrowser(sourceViewer.getTextWidget().getText());
                }
            }
        };
        selectionService.addSelectionListener(updateSelectionListener);
        updateBrowser(sourceViewer.getTextWidget().getText());
    }

    /**
     * Tries to adapt the given element to an {@link EObject}.
     * 
     * @param element
     *            the element to adapt
     * @return the adapted {@link EObject} if any, the element otherwise
     */
    private Object tryToAdaptToEObject(Object element) {
        final Object res;

        if (element instanceof EObject) {
            res = element;
        } else if (element instanceof IAdaptable) {
            final Object adaptedElement = ((IAdaptable) element).getAdapter(EObject.class);
            if (adaptedElement != null) {
                res = adaptedElement;
            } else {
                res = Platform.getAdapterManager().getAdapter(element, EObject.class);
            }
        } else if (element != null) {
            res = Platform.getAdapterManager().getAdapter(element, EObject.class);
        } else {
            res = null;
        }

        if (res != null) {
            return res;
        } else {
            return element;
        }
    }

    /**
     * Gets the possible {@link IType} for the given {@link Object}.
     * 
     * @param environment
     *            the {@link IQueryEnvironment}
     * @param object
     *            the {@link Object} value
     * @return the possible {@link IType} for the given {@link Object}
     */
    private Set<IType> getPossibleTypesForValue(IQueryEnvironment environment, Object object) {
        final LinkedHashSet<IType> res = new LinkedHashSet<IType>();

        if (object instanceof EObject) {
            res.add(new EClassifierType(environment, ((EObject) object).eClass()));
        } else if (object != null) {
            res.add(new ClassType(environment, object.getClass()));
        }

        return res;
    }

    /**
     * Sets the new {@link Generation} {@link URI}.
     * 
     * @param genconfURI
     *            the new {@link Generation} {@link URI}
     */
    public void setGenconfURI(URI genconfURI) {
        browser.setText(IN_PROGRESS_HTML);
        sourceViewer.unconfigure();

        if (generation != null) {
            for (IServicesConfigurator configurator : M2DocUtils.getConfigurators()) {
                configurator.cleanServices(queryEnvironment, resourceSetForModels);
            }
            try {
                if (m2docEnv != null) {
                    m2docEnv.getUserContentManager().dispose();
                }
            } catch (IOException e) {
                // not a big deal
            }
        }

        try {
            generation = GenconfUtils.getGeneration(genconfURI);
            final List<Exception> exceptions = new ArrayList<>();
            resourceSetForModels = M2DocUtils.createResourceSetForModels(exceptions, generation, new ResourceSetImpl(),
                    GenconfUtils.getOptions(generation));
            queryEnvironment = GenconfUtils.getQueryEnvironment(resourceSetForModels, generation);
            final URI templateURI = GenconfUtils.getResolvedURI(generation,
                    URI.createURI(generation.getTemplateFileName(), false));
            final IClassProvider classProvider = M2DocPlugin.getClassProvider();
            final URIConverter uriConverter = resourceSetForModels.getURIConverter();
            try (XWPFDocument document = POIServices.getInstance()
                    .getXWPFDocument(resourceSetForModels.getURIConverter(), templateURI);
                    DocumentTemplate documentTemplate = M2DocUtils.parse(uriConverter, templateURI, queryEnvironment,
                            classProvider, new BasicMonitor());) {
                final TemplateCustomProperties properties = new TemplateCustomProperties(document);
                properties.configureQueryEnvironmentWithResult(queryEnvironment);
                properties.configureQueryEnvironmentWithResult(queryEnvironment, classProvider);
                if (!documentTemplate.getTemplates().isEmpty()) {
                    final byte[] serializedDocument = serializeDocument(documentTemplate);
                    m2docEnv = new M2DocEvaluationEnvironment(queryEnvironment, uriConverter,
                            documentTemplate.eResource().getURI(), null);
                    for (Template template : documentTemplate.getTemplates()) {
                        queryEnvironment.registerService(
                                new M2DocTemplateService(template, serializedDocument, m2docEnv, new BasicMonitor()));
                    }
                }
                final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
                variableTypes.clear();
                variableTypes.put(SELECTION_VARIABLE, getPossibleTypesForValue(queryEnvironment, selectionValue));
                variableTypes.putAll(properties.getVariableTypes(validator, queryEnvironment));
                variableTypes.putAll(parseVariableTypes(queryEnvironment, validator, variableTypes,
                        resourceSetForModels.getURIConverter(), templateURI));

                validationEngine = new QueryValidationEngine(queryEnvironment);

                evaluationEngine = new QueryEvaluationEngine(queryEnvironment);
                final Map<String, Object> variables = GenconfUtils.getVariables(generation, resourceSetForModels);
                allVariables = new HashMap<>(variables);
                allVariables.put(SELECTION_VARIABLE, selectionValue);
                allVariables.putAll(parseVariableValues(queryEnvironment, evaluationEngine, allVariables, uriConverter,
                        templateURI));
                queryBuilder = new QueryBuilderEngine(queryEnvironment);
            }

            final AQLConfiguration configuration = new AQLConfiguration(colorManager, labelProvider, queryEnvironment,
                    variableTypes);
            sourceViewer.configure(configuration);
            setUpContentAssist(configuration.getContentAssistant(sourceViewer));

            for (IServicesConfigurator configurator : M2DocUtils.getConfigurators()) {
                configurator.startGeneration(queryEnvironment, new XWPFDocument());
            }

            if (generation != null) {
                genconfLabel.setText(URI.decode(genconfURI.toString()));
            } else {
                genconfLabel.setText("");
            }
            updateBrowser(sourceViewer.getTextWidget().getText());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            browser.setText(htmlSerializer.serialize("can't load .genconf file: " + e.getMessage()));
            genconfLabel.setText("can't load .genconf file");
            generation = null;
            m2docEnv = null;
        }
    }

    /**
     * Serializes the given {@link DocumentTemplate}.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate} to serialize
     * @return the byte array of the serialized {@link DocumentTemplate}
     * @throws IOException
     *             if the serialization fail
     */
    private static byte[] serializeDocument(DocumentTemplate documentTemplate) throws IOException {
        final byte[] serializedDocument;
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            documentTemplate.getDocument().write(output);
            serializedDocument = output.toByteArray();
        }
        return serializedDocument;
    }

    /**
     * Gets the {@link Generation} {@link URI}.
     * 
     * @return the {@link Generation} {@link URI} if nay, <code>null</code> otherwise
     */
    public URI getGenconfURI() {
        final URI res;

        if (generation != null) {
            res = generation.eResource().getURI();
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Sets up the content assist action for the given source viewer.
     * 
     * @param assistant
     *            The viewer {@link IContentAssistant}
     */
    protected final void setUpContentAssist(final IContentAssistant assistant) {
        IHandlerService service = getSite().getService(IHandlerService.class);
        if (contentAssistHandlerActivation != null) {
            service.deactivateHandler(contentAssistHandlerActivation);
        }

        IAction contentAssistAction = new Action() {
            @Override
            public void run() {
                assistant.showPossibleCompletions();
            }
        };
        IHandler contentAssistHandler = new ActionHandler(contentAssistAction);

        contentAssistHandlerActivation = service
                .activateHandler(ITextEditorActionDefinitionIds.CONTENT_ASSIST_PROPOSALS, contentAssistHandler);
    }

    @Override
    public void dispose() {
        super.dispose();
        colorManager.dispose();
        labelProvider.dispose();
        IHandlerService service = getSite().getService(IHandlerService.class);
        service.deactivateHandler(contentAssistHandlerActivation);
        browser.dispose();
        if (genconfLabel != null) {
            genconfLabel.dispose();
        }
        if (sourceViewer != null && sourceViewer.getTextWidget() != null) {
            sourceViewer.getTextWidget().dispose();
        }
        if (updateSelectionListener != null) {
            getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(updateSelectionListener);
        }
    }

    @Override
    public void setFocus() {
        if (sourceViewer != null) {
            sourceViewer.getControl().setFocus();
        }
    }

    /**
     * Updates the browser content according to the given {@link IReadOnlyQueryEnvironment} and expression.
     * 
     * @param expression
     *            the AQL expression
     */
    private void updateBrowser(final String expression) {
        executorService.shutdownNow();
        if (updatingFuture != null && !updatingFuture.isDone()) {
            updatingFuture.cancel(true);
        }
        executorService = Executors.newFixedThreadPool(1);
        updatingFuture = executorService.submit(new UpdateBrowserRunnable(expression));
    }

    /**
     * Gets the HTML representation of the given {@link IValidationResult}.
     * 
     * @param validationResult
     *            the {@link IValidationResult}
     * @return the HTML representation of the given {@link IValidationResult}
     */
    private String getValidationHTMLContent(IValidationResult validationResult) {
        final StringBuilder res = new StringBuilder();

        if (!validationResult.getMessages().isEmpty()) {
            for (IValidationMessage message : validationResult.getMessages()) {
                switch (message.getLevel()) {
                    case INFO:
                        res.append(String.format(VALIDATION_HTML_FORMAT, "'INFO'", message.getStartPosition(),
                                message.getEndPosition(), "'INFO'", message.getStartPosition(),
                                message.getEndPosition(), "cyan", "INFO", message.getMessage(),
                                message.getStartPosition(), message.getEndPosition()));
                        break;

                    case WARNING:
                        res.append(String.format(VALIDATION_HTML_FORMAT, "'WARNING'", message.getStartPosition(),
                                message.getEndPosition(), "'WARNING'", message.getStartPosition(),
                                message.getEndPosition(), "yellow", "WARNING", message.getMessage(),
                                message.getStartPosition(), message.getEndPosition()));
                        break;

                    case ERROR:
                        res.append(String.format(VALIDATION_HTML_FORMAT, "'ERROR'", message.getStartPosition(),
                                message.getEndPosition(), "'ERROR'", message.getStartPosition(),
                                message.getEndPosition(), "red", "ERROR", message.getMessage(),
                                message.getStartPosition(), message.getEndPosition()));
                        break;

                    default:
                        break;
                }
            }
            res.append("<hr>");
        }

        return res.toString();
    }

    /**
     * Parses the template to retrieve internal variable (let, for, template, ...) types.
     * 
     * @param ven
     *            the {@link IReadOnlyQueryEnvironment}
     * @param validator
     *            the {@link AstValidator}
     * @param varTypes
     *            the declared variable types
     * @param uriConv
     *            the {@link URIConverter}
     * @param tpltURI
     *            the template URI
     * @return the mapping of internal variable (let, for, template, ...) types
     */
    private Map<String, Set<IType>> parseVariableTypes(IReadOnlyQueryEnvironment ven, AstValidator validator,
            Map<String, Set<IType>> varTypes, URIConverter uriConv, URI tpltURI) {
        final Map<String, Set<IType>> res = new HashMap<>();

        try (DocumentTemplate template = M2DocUtils.parse(uriConv, tpltURI, (IQueryEnvironment) ven,
                M2DocPlugin.getClassProvider(), new BasicMonitor())) {
            final Iterator<EObject> it = template.eAllContents();
            while (it.hasNext()) {
                final EObject current = it.next();
                if (current instanceof Let) {
                    final Let let = (Let) current;
                    if (let.getName() != null) {
                        final IValidationResult validationResult = validator.validate(varTypes, let.getValue());
                        final Set<IType> possibleTypes = validationResult.getPossibleTypes(let.getValue().getAst());
                        res.put(let.getName(), possibleTypes);
                    }
                } else if (current instanceof Repetition) {
                    final Repetition repetition = (Repetition) current;
                    if (repetition.getIterationVar() != null) {
                        final IValidationResult validationResult = validator.validate(varTypes, repetition.getQuery());
                        final Set<IType> possibleTypes = validationResult
                                .getPossibleTypes(repetition.getQuery().getAst());
                        res.put(repetition.getIterationVar(), possibleTypes);
                    }
                } else if (current instanceof Parameter) {
                    final Parameter parameter = (Parameter) current;
                    if (parameter.getName() != null) {
                        final IValidationResult validationResult = validator.validate(varTypes, parameter.getType());
                        final Set<IType> possibleTypes = validationResult
                                .getPossibleTypes(parameter.getType().getAst());
                        res.put(parameter.getName(), possibleTypes);
                    }
                }
            }

        } catch (IOException e) {
            // nothing to do here: if we can't parse it doesn't matter
        } catch (DocumentParserException e) {
            // nothing to do here: if we can't parse it doesn't matter
        }

        return res;
    }

    /**
     * Parses the template to retrieve internal variable (let, for, template, ...) value.
     * 
     * @param env
     *            the {@link IReadOnlyQueryEnvironment}
     * @param engine
     *            the {@link QueryEvaluationEngine}
     * @param variables
     *            the declared variable values
     * @param uriConverter
     *            the {@link URIConverter}
     * @param tpltURI
     *            the template URI
     * @return the mapping of internal variable (let, for, template, ...) types
     */
    private Map<String, Object> parseVariableValues(IReadOnlyQueryEnvironment env, QueryEvaluationEngine engine,
            Map<String, Object> variables, URIConverter uriConverter, URI tpltURI) {
        final Map<String, Object> res = new HashMap<>(variables);

        try (DocumentTemplate template = M2DocUtils.parse(uriConverter, tpltURI, (IQueryEnvironment) env,
                M2DocPlugin.getClassProvider(), new BasicMonitor())) {
            final Iterator<EObject> it = template.eAllContents();
            while (it.hasNext()) {
                final EObject current = it.next();
                if (current instanceof Let) {
                    final Let let = (Let) current;
                    if (let.getName() != null) {
                        final EvaluationResult evaluationResult = engine.eval(let.getValue(), res);
                        final Object value = evaluationResult.getResult();
                        res.put(let.getName(), value);
                    }
                } else if (current instanceof Repetition) {
                    final Repetition repetition = (Repetition) current;
                    res.putAll(getRepetitionVariableValues(engine, repetition));
                } else if (current instanceof Parameter) {
                    // final Parameter parameter = (Parameter) current;
                    // TODO ?
                }
            }

        } catch (IOException e) {
            // nothing to do here: if we can't parse it doesn't matter
        } catch (DocumentParserException e) {
            // nothing to do here: if we can't parse it doesn't matter
        }

        return res;
    }

    /**
     * Gets the {@link Repetition} variable value.
     * 
     * @param engine
     *            the {@link QueryEvaluationEngine}
     * @param repetition
     *            the {@link Repetition}
     * @return the {@link Repetition} variable value
     */
    private Map<String, Object> getRepetitionVariableValues(QueryEvaluationEngine engine, final Repetition repetition) {
        final Map<String, Object> res = new HashMap<>();

        final EvaluationResult evaluationResult = engine.eval(repetition.getQuery(), res);
        final Object value = evaluationResult.getResult();
        if (value != null) {
            Iterator<?> valIt = ((Collection<?>) value).iterator();
            if (valIt.hasNext()) {
                if (repetition.getIterationVar() != null) {
                    res.put(repetition.getIterationVar(), valIt.next());
                }
            } else {
                // TODO ?
            }
        } // Else may happen if some variable name are reused with different type

        return res;
    }

}
