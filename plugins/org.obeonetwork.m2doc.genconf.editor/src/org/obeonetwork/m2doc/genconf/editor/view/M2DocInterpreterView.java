/*******************************************************************************
 *  Copyright (c) 2021 Obeo. 
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private ISelectionListener selectionListener;

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

    @Override
    public void init(IViewSite site, IMemento memo) throws PartInitException {
        super.init(site, memo);
        this.memento = memo;
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
        selectionListener = new ISelectionListener() {

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
        selectionService.addSelectionListener(selectionListener);
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
                    .getXWPFDocument(resourceSetForModels.getURIConverter(), templateURI);) {
                final DocumentTemplate documentTemplate = M2DocUtils.parse(uriConverter, templateURI, queryEnvironment,
                        classProvider, new BasicMonitor());
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

            updateBrowser(sourceViewer.getTextWidget().getText());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            browser.setText(htmlSerializer.serialize("can't load .genconf file: " + e.getMessage()));
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
        if (selectionListener != null) {
            getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(selectionListener);
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
        if (generation != null) {
            if (m2docEnv != null) {
                m2docEnv.getBookmarkManager().reset();
                m2docEnv.getUserContentManager().reset();
            }
            final IValidationResult validationResult = validationEngine.validate(expression, variableTypes);
            final String validationContent = getValidationHTMLContent(validationResult);

            final AstResult astBuilder = queryBuilder.build(expression);
            final EvaluationResult evaluationResult = evaluationEngine.eval(astBuilder, allVariables);
            final String evaluationContent = htmlSerializer.serialize(evaluationResult.getResult());
            browser.setText(validationContent + evaluationContent);
        } else {
            browser.setText("You need to select a generation model (.genconf file).");
        }
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
                    final IValidationResult validationResult = validator.validate(varTypes, let.getValue());
                    final Set<IType> possibleTypes = validationResult.getPossibleTypes(let.getValue().getAst());
                    res.put(let.getName(), possibleTypes);
                } else if (current instanceof Repetition) {
                    final Repetition repetition = (Repetition) current;
                    final IValidationResult validationResult = validator.validate(varTypes, repetition.getQuery());
                    final Set<IType> possibleTypes = validationResult.getPossibleTypes(repetition.getQuery().getAst());
                    res.put(repetition.getIterationVar(), possibleTypes);
                } else if (current instanceof Parameter) {
                    final Parameter parameter = (Parameter) current;
                    final IValidationResult validationResult = validator.validate(varTypes, parameter.getType());
                    final Set<IType> possibleTypes = validationResult.getPossibleTypes(parameter.getType().getAst());
                    res.put(parameter.getName(), possibleTypes);
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
                    final EvaluationResult evaluationResult = engine.eval(let.getValue(), res);
                    final Object value = evaluationResult.getResult();
                    res.put(let.getName(), value);
                } else if (current instanceof Repetition) {
                    final Repetition repetition = (Repetition) current;
                    final EvaluationResult evaluationResult = engine.eval(repetition.getQuery(), res);
                    final Object value = evaluationResult.getResult();
                    if (value != null) {
                        Iterator<?> valIt = ((Collection<?>) value).iterator();
                        if (valIt.hasNext()) {
                            res.put(repetition.getIterationVar(), valIt.next());
                        } else {
                            // TODO ?
                        }
                    } // Else may happen if some variable name are reused with different type
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

}
