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
package org.obeonetwork.m2doc.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.CrossReferenceProvider;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IRootEObjectProvider;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameLookupEngine;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameQueryEnvironment;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.acceleo.query.services.configurator.IServicesConfigurator;
import org.eclipse.acceleo.query.services.configurator.ServicesConfiguratorDescriptor;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.generator.M2DocEvaluator;
import org.obeonetwork.m2doc.generator.M2DocValidator;
import org.obeonetwork.m2doc.generator.TemplateValidationGenerator;
import org.obeonetwork.m2doc.migrator.IM2DocMigrator;
import org.obeonetwork.m2doc.migrator.M2Doc4Migrator;
import org.obeonetwork.m2doc.migrator.Version;
import org.obeonetwork.m2doc.parser.BodyGeneratedParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.M2DocParser;
import org.obeonetwork.m2doc.parser.ParsingErrorMessage;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.BooleanServices;
import org.obeonetwork.m2doc.services.DocumentServiceConfigurator;
import org.obeonetwork.m2doc.services.ExcelServices;
import org.obeonetwork.m2doc.services.GenerationResultServices;
import org.obeonetwork.m2doc.services.ImageServices;
import org.obeonetwork.m2doc.services.LinkServices;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.obeonetwork.m2doc.services.configurator.IM2DocServicesConfigurator;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;

/**
 * Utility class for M2Doc.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class M2DocUtils {

    /**
     * The AQL language name for {@link IServicesConfigurator}.
     */
    public static final String M2DOC_LANGUAGE = "org.obeonetwork.m2doc";

    /**
     * A qualifier separator.
     */
    public static final String QUALIFIER_SEPARATOR = ".";

    /**
     * M2Doc version.
     */
    public static final String VERSION = "5.0.0";

    /**
     * The plugin ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc";

    /**
     * The {@link org.obeonetwork.m2doc.template.Query Query} field tag.
     */
    public static final String M = "m:";

    /**
     * The field start.
     */
    public static final String FIELD_START = "{";

    /**
     * The {@link org.obeonetwork.m2doc.template.Query Query} field.
     */
    public static final String M_FIELD_START = FIELD_START + M;

    /**
     * The {@link org.obeonetwork.m2doc.template.Query Query} field end.
     */
    public static final String FIELD_END = "}";

    /**
     * constant defining the color of info messages.
     */
    public static final String INFO_COLOR = "0000FF";

    /**
     * constant defining the color of warning messages.
     */
    public static final String WARNING_COLOR = "FFA500";

    /**
     * constant defining the color of error messages.
     */
    public static final String ERROR_COLOR = "FF0000";

    /**
     * Docx extension file.
     */
    public static final String DOCX_EXTENSION_FILE = "docx";

    /**
     * The template {@link URI} option.
     */
    public static final String TEMPLATE_URI_OPTION = "TemplateURI";

    /**
     * The result {@link URI} option.
     */
    public static final String RESULT_URI_OPTION = "ResultURI";

    /**
     * The validation {@link URI} option.
     */
    public static final String VALIDATION_URI_OPTION = "ValidationURI";

    /**
     * The update fields option.
     */
    public static final String UPDATE_FIELDS_OPTION = "UpdateFields";

    /**
     * The ignore {@link #VERSION} check option.
     */
    public static final String IGNORE_VERSION_CHECK_OPTION = "IgnoreVersionCheck";

    /**
     * Separator between the text of a M2DOC template element and a corresponding parsing error and between two parsing error.
     */
    public static final String BLANK_SEPARATOR = "    ";

    /**
     * The separator between the tag were a parsing error has been detected and the start of the error message.
     */
    public static final String LOCATION_SEPARATOR = "<---";

    /**
     * The load template monitor work.
     */
    private static final int LOAD_TEMPLATE_MONITOR_WORK = 20;

    /**
     * The parse template custom properties monitor work.
     */
    private static final int PARSE_TEMPLATE_CUSTOM_PROPERTIES_MONITOR_WORK = 5;

    /**
     * The parse template monitor work.
     */
    private static final int PARSE_TEMPLATE_MONITOR_WORK = 30;

    /**
     * The parse total monitor work.
     */
    private static final int TOTAL_PARSE_MONITOR_WORK = LOAD_TEMPLATE_MONITOR_WORK
        + PARSE_TEMPLATE_CUSTOM_PROPERTIES_MONITOR_WORK + PARSE_TEMPLATE_MONITOR_WORK;

    /**
     * The initalize destination document monitor work.
     */
    private static final int INIT_DEST_DOC_MONITOR_WORK = LOAD_TEMPLATE_MONITOR_WORK;

    /**
     * The template services monitor work.
     */
    private static final int TEMPLATE_SERVICES_MONITOR_WORK = 10;

    /**
     * The lost files monitor work.
     */
    private static final int LOST_FILES_MONITOR_WORK = 10;

    /**
     * The document save monitor work.
     */
    private static final int DOCUMENT_SAVE_MONITOR_WORK = 20;

    /**
     * The document save monitor work.
     */
    private static final int ENGINE_CLEAN_MONITOR_WORK = 5;

    /**
     * The "Unable to open " message.
     */
    private static final String UNABLE_TO_OPEN = "Unable to open ";

    /**
     * The generate total monitor work.
     */
    private static final int TOTAL_GENERATE_MONITOR_WORK = INIT_DEST_DOC_MONITOR_WORK + TEMPLATE_SERVICES_MONITOR_WORK
        + M2DocEvaluator.MONITOR_WORK + LOST_FILES_MONITOR_WORK + DOCUMENT_SAVE_MONITOR_WORK;

    // register standalone IServiceConfigurator
    static {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            AQLUtils.registerServicesConfigurator(
                    new ServicesConfiguratorDescriptor(M2DOC_LANGUAGE, new DocumentServiceConfigurator()));
        }
    }

    /**
     * Constructor.
     */
    private M2DocUtils() {
        // nothing to do here
    }

    /**
     * Appends the given error message at the end of the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message
     * @return the created {@link TemplateValidationMessage}
     */
    public static TemplateValidationMessage appendMessageRun(XWPFParagraph paragraph, ValidationMessageLevel level,
            String message) {
        final XWPFRun run = paragraph.createRun();

        setRunMessage(run, level, BLANK_SEPARATOR + LOCATION_SEPARATOR + message);

        return new TemplateValidationMessage(level, message, run);
    }

    /**
     * Appends the given {@link Diagnostic} to the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param diagnostic
     *            the {@link Diagnostic}
     * @return the maximum {@link ValidationMessageLevel}
     */
    public static List<TemplateValidationMessage> appendDiagnosticMessage(XWPFParagraph paragraph,
            Diagnostic diagnostic) {
        final List<TemplateValidationMessage> res = new ArrayList<>();

        for (Diagnostic child : diagnostic.getChildren()) {
            switch (child.getSeverity()) {
                case Diagnostic.INFO:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.INFO, child.getMessage()));
                    break;
                case Diagnostic.WARNING:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.WARNING, child.getMessage()));
                    break;
                case Diagnostic.ERROR:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR, child.getMessage()));
                    break;

                default:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.INFO, child.getMessage()));
                    break;
            }
            paragraph.getRuns().get(paragraph.getRuns().size() - 1).addBreak();
            if (!child.getChildren().isEmpty()) {
                res.addAll(appendDiagnosticMessage(paragraph, child));
            }
        }

        return res;
    }

    /**
     * Inserts a new message {@link XWPFRun} after the given {@link XWPFRun}.
     * 
     * @param run
     *            the {@link XWPFRun} used as reference.
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message
     * @return the created {@link XWPFRun}
     */
    public static TemplateValidationMessage insertMessageAfter(XWPFRun run, ValidationMessageLevel level,
            String message) {
        final IRunBody parent = run.getParent();
        if (parent instanceof XWPFParagraph) {
            final XWPFParagraph paragraph = (XWPFParagraph) parent;
            final XWPFRun newRun = paragraph.insertNewRun(paragraph.getRuns().indexOf(run));
            setRunMessage(newRun, level, message);
            return new TemplateValidationMessage(level, message, newRun);
        } else {
            throw new IllegalStateException("this should not happend");
        }
    }

    /**
     * Set the given message to the given {@link XWPFRun}.
     * 
     * @param run
     *            the {@link XWPFRun}
     * @param level
     *            the {@link ValidationMessageLevel}
     * @param message
     *            the message
     * @return the {@link TemplateValidationMessage}
     */
    public static TemplateValidationMessage setRunMessage(XWPFRun run, ValidationMessageLevel level, String message) {
        run.setText(message);
        run.setBold(true);
        run.setColor(getColor(level));

        return new TemplateValidationMessage(level, message, run);
    }

    /**
     * Gets the color to use for the given {@link ValidationMessageLevel}.
     * 
     * @param level
     *            the {@link ValidationMessageLevel}
     * @return the color to use for the given {@link ValidationMessageLevel}
     */
    public static String getColor(ValidationMessageLevel level) {
        final String res;

        switch (level) {
            case INFO:
                res = M2DocUtils.INFO_COLOR;
                break;

            case WARNING:
                res = M2DocUtils.WARNING_COLOR;
                break;

            case ERROR:
                res = M2DocUtils.ERROR_COLOR;
                break;

            default:
                res = M2DocUtils.INFO_COLOR;
                break;
        }

        return res;
    }

    /**
     * Add a validation error message to a given {@link IConstruct}'s last run.
     * 
     * @param construct
     *            The construct in which to 'log' the message
     * @param msg
     *            the message to log
     */
    public static void validationError(IConstruct construct, String msg) {
        XWPFRun lastRun = construct.getRuns().get(construct.getRuns().size() - 1);
        construct.getValidationMessages()
                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, msg, lastRun));
    }

    /**
     * Add a validation warning message to a given {@link IConstruct}'s last run.
     * 
     * @param construct
     *            The construct in which to 'log' the message
     * @param msg
     *            the message to log
     */
    public static void validationWarning(IConstruct construct, String msg) {
        XWPFRun lastRun = construct.getRuns().get(construct.getRuns().size() - 1);
        construct.getValidationMessages()
                .add(new TemplateValidationMessage(ValidationMessageLevel.WARNING, msg, lastRun));
    }

    /**
     * Add a validation info message to a given {@link IConstruct}'s last run.
     * 
     * @param construct
     *            The construct in which to 'log' the message
     * @param msg
     *            the message to log
     */
    public static void validationInfo(IConstruct construct, String msg) {
        XWPFRun lastRun = construct.getRuns().get(construct.getRuns().size() - 1);
        construct.getValidationMessages().add(new TemplateValidationMessage(ValidationMessageLevel.INFO, msg, lastRun));
    }

    /**
     * Creates an error message.
     * 
     * @param message
     *            the error to create a message from
     * @param objects
     *            the list of the message arguments
     * @return the formated error message
     */
    public static String message(ParsingErrorMessage message, Object... objects) {
        return MessageFormat.format(message.getMessage(), objects);
    }

    /**
     * Gets the initialized {@link IQualifiedNameQueryEnvironment} for the given {@link ResourceSet} and options.
     * 
     * @param resolver
     *            the {@link IQualifiedNameResolver}
     * @param resourceSetForModels
     *            the {@link ResourceSet} for model elements
     * @param templateURI
     *            the template {@link URI}
     * @param options
     *            the {@link Map} of options
     * @param forWorkspace
     *            tells if the {@link IService} will be used in a workspace
     * @return the initialized {@link IQualifiedNameQueryEnvironment} for the given {@link ResourceSet} and options
     */
    public static IQualifiedNameQueryEnvironment getQueryEnvironment(IQualifiedNameResolver resolver,
            ResourceSet resourceSetForModels, URI templateURI, Map<String, String> options, boolean forWorkspace) {
        final ECrossReferenceAdapterCrossReferenceProvider crossReferenceProvider = new ECrossReferenceAdapterCrossReferenceProvider(
                ECrossReferenceAdapter.getCrossReferenceAdapter(resourceSetForModels));
        final ResourceSetRootEObjectProvider rootProvider = new ResourceSetRootEObjectProvider(resourceSetForModels);

        return getQueryEnvironment(resolver, resourceSetForModels, crossReferenceProvider, rootProvider, templateURI,
                options, forWorkspace);
    }

    /**
     * Gets the initialized {@link IQualifiedNameQueryEnvironment} for the given {@link ResourceSet} and options.
     * 
     * @param resolver
     *            the {@link IQualifiedNameResolver}
     * @param resourceSetForModels
     *            the {@link ResourceSet} for model elements
     * @param crossReferenceProvider
     *            the {@link CrossReferenceProvider} used for eInverse() service
     * @param rootProvider
     *            the {@link IRootEObjectProvider} used for the allInstances() service
     * @param templateURI
     *            the template {@link URI}
     * @param options
     *            the {@link Map} of options
     * @param forWorkspace
     *            tells if the {@link IService} will be used in a workspace
     * @return the initialized {@link IQualifiedNameQueryEnvironment} for the given {@link ResourceSet} and options
     */
    public static IQualifiedNameQueryEnvironment getQueryEnvironment(IQualifiedNameResolver resolver,
            ResourceSet resourceSetForModels, CrossReferenceProvider crossReferenceProvider,
            IRootEObjectProvider rootProvider, URI templateURI, Map<String, String> options, boolean forWorkspace) {
        final IQualifiedNameQueryEnvironment queryEnvironment = AQLUtils.newQualifiedNameEnvironmentDefaultServices(
                M2DOC_LANGUAGE, options, resolver, resourceSetForModels, forWorkspace);

        Set<IService<?>> services = ServiceUtils.getServices(queryEnvironment, BooleanServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, LinkServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment,
                new PaginationServices(resourceSetForModels.getURIConverter(), templateURI));
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment,
                new ImageServices(resourceSetForModels.getURIConverter(), templateURI));
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment,
                new ExcelServices(resourceSetForModels.getURIConverter(), templateURI));
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, new GenerationResultServices());
        ServiceUtils.registerServices(queryEnvironment, services);

        return queryEnvironment;
    }

    /**
     * Parses a template document and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            URI for the template, used when external links (images, includes) have to be resolved
     * @param qualifiedName
     *            the qualified name of the parsed {@link DocumentTemplate}
     * @param monitor
     *            used to track the progress will generating
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    @SuppressWarnings("resource")
    public static DocumentTemplate parse(URIConverter uriConverter, URI templateURI, String qualifiedName,
            Monitor monitor) throws DocumentParserException {
        final DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        result.setQualifiedName(qualifiedName);
        final ResourceImpl r = new ResourceImpl(templateURI);

        try {
            monitor.beginTask("Parsing " + templateURI, TOTAL_PARSE_MONITOR_WORK);
            monitor.subTask("Loading template");
            // resources are closed in DocumentTemplate.close()
            final InputStream is = uriConverter.createInputStream(templateURI);
            final OPCPackage oPackage = OPCPackage.open(is);
            final XWPFDocument document = new XWPFDocument(oPackage);

            nextSubTask(monitor, LOAD_TEMPLATE_MONITOR_WORK, "Parsing template custom properties");

            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            result.setProperties(properties);
            r.getContents().add(result);

            nextSubTask(monitor, PARSE_TEMPLATE_CUSTOM_PROPERTIES_MONITOR_WORK, "Parsing template body");
            final int unitOfWork = PARSE_TEMPLATE_MONITOR_WORK
                / (1 + document.getFooterList().size() + document.getHeaderList().size());

            final M2DocParser parser = new M2DocParser(document);
            final Block documentBody = parser.parseBlock(result.getTemplates(), TokenType.EOF);
            result.setBody(documentBody);
            result.setInputStream(is);
            result.setOpcPackage(oPackage);
            result.setDocument(document);

            nextSubTask(monitor, unitOfWork, "Parsing template footers");

            for (XWPFFooter footer : document.getFooterList()) {
                final M2DocParser footerParser = new M2DocParser(footer);
                result.getFooters().add(footerParser.parseBlock(null, TokenType.EOF));

                monitor.worked(unitOfWork);
            }

            nextSubTask(monitor, 0, "Parsing template headers");

            for (XWPFHeader header : document.getHeaderList()) {
                final M2DocParser headerParser = new M2DocParser(header);
                result.getHeaders().add(headerParser.parseBlock(null, TokenType.EOF));

                monitor.worked(unitOfWork);
            }

        } catch (IOException e) {
            throw new DocumentParserException(UNABLE_TO_OPEN + templateURI, e);
        } catch (InvalidFormatException e1) {
            throw new DocumentParserException("Invalid .docx format " + templateURI, e1);
        } finally {
            monitor.done();
        }

        return result;
    }

    /**
     * Prepares the given {@link IQualifiedNameQueryEnvironment} for the given {@link DocumentTemplate}.
     * 
     * @param queryEnvironment
     *            the {@link IQualifiedNameQueryEnvironment}
     * @param ePackageRegistry
     *            the {@link EPackage.Registry} to use to resolve {@link EPackage#getNsURI() nsURI}
     * @param documentTemplate
     *            the {@link XWPFDocument}
     * @return the {@link List} of {@link TemplateValidationMessage} produced preparing the {@link IQualifiedNameQueryEnvironment}.
     */
    public static List<TemplateValidationMessage> prepareEnvironment(IQualifiedNameQueryEnvironment queryEnvironment,
            EPackage.Registry ePackageRegistry, DocumentTemplate documentTemplate) {
        final IQualifiedNameResolver resolver = queryEnvironment.getLookupEngine().getResolver();
        final String qualifiedName = documentTemplate.getQualifiedName();
        final Set<String> nsURIs = AQLUtils.getAllNeededEPackages(resolver, qualifiedName);
        final List<TemplateValidationMessage> messages = new ArrayList<>();
        final List<String> missingNsURIs = AQLUtils.registerEPackages(queryEnvironment, ePackageRegistry, nsURIs);
        for (String nsURI : missingNsURIs) {
            @SuppressWarnings("resource")
            final XWPFRun run = getOrCreateFirstRun(documentTemplate.getDocument());
            final TemplateValidationMessage validationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.ERROR, "can't find EPackage: " + nsURI, run);
            messages.add(validationMessage);
        }
        for (TemplateValidationMessage validationMessage : messages) {
            documentTemplate.getBody().getValidationMessages().add(validationMessage);
        }

        return messages;
    }

    /**
     * Gets or create the first {@link XWPFRun} of the given {@link XWPFDocument}.
     * 
     * @param document
     *            the {@link XWPFDocument}
     * @return the first {@link XWPFRun} of the given {@link XWPFDocument} or the created one if none existed
     */
    public static XWPFRun getOrCreateFirstRun(XWPFDocument document) {
        final XWPFRun res;

        final XWPFParagraph paragraph;
        if (!document.getParagraphs().isEmpty()) {
            paragraph = document.getParagraphs().get(0);
        } else {
            paragraph = document.createParagraph();
        }
        if (!paragraph.getRuns().isEmpty()) {
            res = paragraph.getRuns().get(0);
        } else {
            res = paragraph.createRun();
        }

        return res;
    }

    /**
     * Parses a document for {@link UserContent} and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param documentURI
     *            URI for the document
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    @SuppressWarnings("resource")
    public static DocumentTemplate parseUserContent(URIConverter uriConverter, URI documentURI)
            throws DocumentParserException {
        final DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        final ResourceImpl r = new ResourceImpl(documentURI);

        try {
            // resources are closed in DocumentTemplate.close()
            final InputStream is = uriConverter.createInputStream(documentURI);
            final OPCPackage oPackage = OPCPackage.open(is);
            final XWPFDocument document = new XWPFDocument(oPackage);
            r.getContents().add(result);
            final BodyGeneratedParser parser = new BodyGeneratedParser(document);
            result.setBody(parser.parseBlock(null, TokenType.EOF));
            result.setInputStream(is);
            result.setOpcPackage(oPackage);
            result.setDocument(document);
            for (XWPFFooter footer : document.getFooterList()) {
                final BodyGeneratedParser footerParser = new BodyGeneratedParser(footer);
                result.getFooters().add(footerParser.parseBlock(null, TokenType.EOF));
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final BodyGeneratedParser headerParser = new BodyGeneratedParser(header);
                result.getHeaders().add(headerParser.parseBlock(null, TokenType.EOF));
            }

        } catch (IOException e) {
            throw new DocumentParserException(UNABLE_TO_OPEN + documentURI, e);
        } catch (InvalidFormatException e1) {
            throw new DocumentParserException("Invalid .docx format " + documentURI, e1);
        }

        return result;
    }

    /**
     * Validates the given {@link DocumentTemplate} with the given {@link IQualifiedNameQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IQualifiedNameQueryEnvironment}
     * @param monitor
     *            used to track the progress will generating
     * @return the {@link ValidationMessageLevel}
     */
    public static ValidationMessageLevel validate(DocumentTemplate documentTemplate,
            IQualifiedNameQueryEnvironment queryEnvironment, Monitor monitor) {
        return validate(documentTemplate, queryEnvironment, false, monitor);
    }

    /**
     * Validates the given {@link DocumentTemplate} with the given {@link IQualifiedNameQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IQualifiedNameQueryEnvironment}
     * @param ignoreVersionCheck
     *            ignore the {@link #VERSION} check
     * @param monitor
     *            used to track the progress will generating
     * @return the {@link ValidationMessageLevel}
     */
    public static ValidationMessageLevel validate(DocumentTemplate documentTemplate,
            IQualifiedNameQueryEnvironment queryEnvironment, boolean ignoreVersionCheck, Monitor monitor) {
        final M2DocValidator validator = new M2DocValidator();
        queryEnvironment.getLookupEngine().pushImportsContext(documentTemplate.getQualifiedName(),
                documentTemplate.getQualifiedName());
        try {
            return validator.validate(documentTemplate, queryEnvironment, ignoreVersionCheck, monitor);
        } finally {
            queryEnvironment.getLookupEngine().popContext(documentTemplate.getQualifiedName());
        }
    }

    /**
     * Serializes the given {@link M2DocUtils#validate(DocumentTemplate, IReadOnlyQueryEnvironment, Map) validated} {@link DocumentTemplate}
     * to the given destination.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param documentTemplate
     *            the {@link M2DocUtils#validate(DocumentTemplate, IReadOnlyQueryEnvironment, Map) validated} {@link DocumentTemplate}
     * @param destination
     *            the destination {@link URI}
     * @throws IOException
     *             if the {@link DocumentTemplate} can't be serialized to the given destination
     */
    @SuppressWarnings("resource")
    public static void serializeValidatedDocumentTemplate(URIConverter uriConverter, DocumentTemplate documentTemplate,
            URI destination) throws IOException {
        TemplateValidationGenerator generator = new TemplateValidationGenerator();

        generator.doSwitch(documentTemplate);
        POIServices.getInstance().saveFile(uriConverter, documentTemplate.getDocument(), destination);
    }

    /**
     * Generates the given template into the given destination.
     * 
     * @param m2docEnv
     *            the {@link M2DocEvaluationEnvironment}
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param variables
     *            variables
     * @param updateFields
     *            tells if we should update fields at the end of the generations
     * @param monitor
     *            used to track the progress will generating
     * @return the {@link GenerationResult}
     * @throws DocumentGenerationException
     *             if the generation fails
     */
    public static GenerationResult generate(M2DocEvaluationEnvironment m2docEnv, DocumentTemplate documentTemplate,
            Map<String, Object> variables, boolean updateFields, Monitor monitor) throws DocumentGenerationException {

        monitor.beginTask("Generating " + m2docEnv.getDestinationURI().lastSegment(), TOTAL_GENERATE_MONITOR_WORK);
        monitor.subTask("Initializing desination document");

        // prepare the lookup engine context and find the main template
        final IQualifiedNameQueryEnvironment queryEnvironment = m2docEnv.getResolver().getLookupEngine()
                .getQueryEnvironment();
        final IQualifiedNameLookupEngine lookupEngine = queryEnvironment.getLookupEngine();
        lookupEngine.pushImportsContext(documentTemplate.getQualifiedName(), documentTemplate.getQualifiedName());
        String currentExtendQualifiedName = lookupEngine.getExtend(documentTemplate.getQualifiedName());
        DocumentTemplate mainDocument = documentTemplate;
        final List<String> extendQualifiedNamesToPop = new ArrayList<>();
        while (currentExtendQualifiedName != null) {
            Object extended = m2docEnv.getResolver().resolve(currentExtendQualifiedName);
            if (extended instanceof DocumentTemplate) {
                mainDocument = (DocumentTemplate) extended;
                lookupEngine.pushContext(currentExtendQualifiedName);
                extendQualifiedNamesToPop.add(0, currentExtendQualifiedName);

                currentExtendQualifiedName = lookupEngine.getExtend(currentExtendQualifiedName);
            } else {
                currentExtendQualifiedName = null;
            }
        }

        final URIConverter uriConverter = m2docEnv.getResourceSetForModels().getURIConverter();
        try (InputStream is = uriConverter.createInputStream(mainDocument.eResource().getURI());
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument destinationDocument = new XWPFDocument(oPackage);) {

            nextSubTask(monitor, INIT_DEST_DOC_MONITOR_WORK, "Initializing engine");

            for (IServicesConfigurator configurator : AQLUtils.getServicesConfigurators(M2DOC_LANGUAGE)) {
                if (configurator instanceof IM2DocServicesConfigurator) {
                    ((IM2DocServicesConfigurator) configurator).startGeneration(queryEnvironment, destinationDocument);
                }
            }

            final M2DocEvaluator evaluator = new M2DocEvaluator(m2docEnv, monitor);

            nextSubTask(monitor, TEMPLATE_SERVICES_MONITOR_WORK, "Generating");

            final GenerationResult result = evaluator.generate(mainDocument, variables, destinationDocument);

            nextSubTask(monitor, 0, "Saving lost files");
            // monitor.subTask("Saving lost files");

            m2docEnv.getBookmarkManager().markDanglingReferences(result);
            m2docEnv.getBookmarkManager().markOpenBookmarks(result);

            m2docEnv.getUserContentManager().generateLostFiles(result, m2docEnv.getCopier());
            m2docEnv.getUserContentManager().dispose();

            if (updateFields) {
                POIServices.getInstance().markFieldsAsDirty(destinationDocument);
            }

            nextSubTask(monitor, LOST_FILES_MONITOR_WORK, "Saving generated document");

            // At this point, the document has been generated and just needs to be written on disk.
            POIServices.getInstance().saveFile(uriConverter, destinationDocument, m2docEnv.getDestinationURI());

            nextSubTask(monitor, DOCUMENT_SAVE_MONITOR_WORK, "Cleaning template services");

            monitor.worked(ENGINE_CLEAN_MONITOR_WORK);

            return result;
        } catch (IOException e) {
            throw new DocumentGenerationException("An I/O problem occured while creating the output document.", e);
        } catch (InvalidFormatException e) {
            throw new DocumentGenerationException("Input document seems to have an invalid format.", e);
        } finally {
            monitor.done();
            for (String extendQualifiedName : extendQualifiedNamesToPop) {
                lookupEngine.popContext(extendQualifiedName);
            }
            lookupEngine.popContext(documentTemplate.getQualifiedName());
        }
    }

    /**
     * Starts next sub task on the given {@link Monitor}.
     * 
     * @param monitor
     *            the {@link Monitor}
     * @param previousTaskDone
     *            the work done in the previous task
     * @param nextSubTaskName
     *            the next sub task name
     */
    private static void nextSubTask(Monitor monitor, int previousTaskDone, String nextSubTaskName) {
        if (monitor.isCanceled()) {
            throw new CancellationException("Canceled by user");
        }
        monitor.worked(previousTaskDone);
        monitor.subTask(nextSubTaskName);
    }

    /**
     * Gets the {@link Map} of initialized options.
     * 
     * @param options
     *            the {@link Map} of existing options.
     * @return the {@link Map} of initialized options
     */
    public static Map<String, String> getInitializedOptions(Map<String, String> options) {
        final Map<String, String> res = new LinkedHashMap<>();

        res.put(M2DocUtils.UPDATE_FIELDS_OPTION, Boolean.FALSE.toString());
        res.put(M2DocUtils.IGNORE_VERSION_CHECK_OPTION, Boolean.FALSE.toString());
        res.putAll(AQLUtils.getInitializedOptions(M2DOC_LANGUAGE, options));

        return res;
    }

    /**
     * Gets the {@link Map} of initialized options for the given {@link EObject}.
     * 
     * @param options
     *            the {@link Map} of existing options.
     * @param eObj
     *            the {@link EObject}
     * @return the {@link Map} of initialized options for the given {@link EObject}
     */
    public static Map<String, String> getInitializedOptions(Map<String, String> options, EObject eObj) {
        final Map<String, String> res = new LinkedHashMap<>();

        res.put(M2DocUtils.UPDATE_FIELDS_OPTION, Boolean.FALSE.toString());
        res.put(M2DocUtils.IGNORE_VERSION_CHECK_OPTION, Boolean.FALSE.toString());
        res.putAll(AQLUtils.getInitializedOptions(M2DOC_LANGUAGE, options, eObj));

        return res;
    }

    /**
     * Gets the {@link List} of possible option names.
     * 
     * @return the {@link List} of possible option names
     */
    public static List<String> getPossibleOptionNames() {
        final List<String> res = new ArrayList<>();

        res.addAll(AQLUtils.getPossibleOptionNames(M2DOC_LANGUAGE));

        res.add(UPDATE_FIELDS_OPTION);
        res.add(IGNORE_VERSION_CHECK_OPTION);

        return res;
    }

    /**
     * Creates a sample template {@link XWPFDocument}.
     * 
     * @param variableName
     *            the variable name
     * @param eCls
     *            the variable {@link EClass}
     * @return the created sample template {@link XWPFDocument}
     * @throws IOException
     *             if the sample template can't be read
     * @throws InvalidFormatException
     *             if the sample template can't be read
     */
    public static XWPFDocument createSampleTemplate(String variableName, EClass eCls)
            throws InvalidFormatException, IOException {
        final SampleTemplateGenerator generator = new SampleTemplateGenerator();
        return generator.generate(variableName, eCls);
    }

    /**
     * Migrates the template at the given {@link URI} to the output {@link URI}.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            {@link URI} for the template to migrate
     * @param outputURI
     *            the output {@link URI}
     * @param outputErrorURI
     *            the error document serialization {@link URI} if any, <code>null</code> otherwise
     * @param monitor
     *            used to track the progress will generating
     * @return the {@link List} of {@link TemplateValidationMessage}
     * @throws DocumentParserException
     *             if the migration fails
     */
    public static List<TemplateValidationMessage> migrate(URIConverter uriConverter, URI templateURI, URI outputURI,
            URI outputErrorURI, Monitor monitor) throws DocumentParserException {
        final List<TemplateValidationMessage> result = new ArrayList<>();

        monitor.beginTask("Migrating " + templateURI, TOTAL_PARSE_MONITOR_WORK + LOAD_TEMPLATE_MONITOR_WORK);
        monitor.subTask("Loading template");
        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(uriConverter, templateURI)) {
            nextSubTask(monitor, LOAD_TEMPLATE_MONITOR_WORK, "Parsing template custom properties");
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            if (properties.getM2DocVersion() != null) {
                final Version templateVersion = new Version(properties.getM2DocVersion());
                final List<IM2DocMigrator> migrators = getTemplateMigrators(templateVersion);

                final int unitOfWork = PARSE_TEMPLATE_MONITOR_WORK
                    / (1 + document.getFooterList().size() + document.getHeaderList().size());

                nextSubTask(monitor, PARSE_TEMPLATE_CUSTOM_PROPERTIES_MONITOR_WORK, "Parsing template body");

                for (IM2DocMigrator migrator : migrators) {
                    result.addAll(migrator.migrate(document));
                }

                nextSubTask(monitor, unitOfWork, "Migrating template footers");

                for (XWPFFooter footer : document.getFooterList()) {
                    for (IM2DocMigrator migrator : migrators) {
                        result.addAll(migrator.migrate(footer));
                    }

                    monitor.worked(unitOfWork);
                }

                nextSubTask(monitor, 0, "Migrating template headers");

                for (XWPFHeader header : document.getHeaderList()) {
                    for (IM2DocMigrator migrator : migrators) {
                        result.addAll(migrator.migrate(header));
                    }

                    monitor.worked(unitOfWork);
                }

                nextSubTask(monitor, 0, "Save template " + outputURI);
                properties.setM2DocVersion(VERSION);
                properties.save();
                POIServices.getInstance().saveFile(uriConverter, document, outputURI);

                // save the error template if needed
                if (!result.isEmpty() && outputErrorURI != null) {
                    for (TemplateValidationMessage message : result) {
                        insertMessageAfter(message.getLocation(), message.getLevel(), message.getMessage());
                    }
                    POIServices.getInstance().saveFile(uriConverter, document, outputErrorURI);
                }
            }
        } catch (IOException e) {
            throw new DocumentParserException(UNABLE_TO_OPEN + templateURI + " or save " + outputURI, e);
        } finally {
            monitor.done();
        }

        return result;
    }

    /**
     * Gets the {@link List} of {@link IM2DocMigrator} for the given M2Doc {@link Version}.
     * 
     * @param version
     *            the M2Doc {@link Version}
     * @return the {@link List} of {@link IM2DocMigrator} for the given M2Doc {@link Version}
     */
    public static List<IM2DocMigrator> getTemplateMigrators(final Version version) {
        final List<IM2DocMigrator> result = new ArrayList<>();

        if (new Version("4.0.0").compareTo(version) > 0) {
            result.add(new M2Doc4Migrator());
        }

        return result;
    }

    /**
     * Tells if the given template {@link URI} needs to be migrated.
     * 
     * @param uriConverter
     *            the {@link URIConverter}
     * @param templateURI
     *            the template {@link URI}
     * @return <code>true</code> if the given template {@link URI} needs to be migrated, <code>false</code> otherwise
     * @throws IOException
     *             if the template can't be read
     */
    public static boolean needMigration(URIConverter uriConverter, URI templateURI) throws IOException {
        final boolean result;

        try (XWPFDocument document = POIServices.getInstance().getXWPFDocument(uriConverter, templateURI)) {
            final TemplateCustomProperties properties = new TemplateCustomProperties(document);
            if (properties.getM2DocVersion() != null) {
                final Version templateVersion = new Version(properties.getM2DocVersion());
                result = !getTemplateMigrators(templateVersion).isEmpty();
            } else {
                result = false;
            }
        }

        return result;
    }

}
