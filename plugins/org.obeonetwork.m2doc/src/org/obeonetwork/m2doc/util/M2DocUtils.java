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
package org.obeonetwork.m2doc.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IRunBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.generator.BookmarkManager;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.GenerationResult;
import org.obeonetwork.m2doc.generator.M2DocEvaluator;
import org.obeonetwork.m2doc.generator.M2DocValidator;
import org.obeonetwork.m2doc.generator.TemplateValidationGenerator;
import org.obeonetwork.m2doc.generator.UserContentManager;
import org.obeonetwork.m2doc.parser.BodyGeneratedParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.M2DocParser;
import org.obeonetwork.m2doc.parser.ParsingErrorMessage;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.TokenType;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.BooleanServices;
import org.obeonetwork.m2doc.services.ExcelServices;
import org.obeonetwork.m2doc.services.ImageServices;
import org.obeonetwork.m2doc.services.LinkServices;
import org.obeonetwork.m2doc.services.M2DocTemplateService;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.services.configurator.IServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;

/**
 * Utility class for M2Doc.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class M2DocUtils {

    /**
     * The {@link org.obeonetwork.m2doc.template.Query Query} tag.
     */
    public static final String M = "m:";

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
     * The {@link List} of {@link #registerServicesConfigurator(IServicesConfiguratorDescriptor) registered}
     * {@link IServicesConfiguratorDescriptor}.
     */
    private static final List<IServicesConfiguratorDescriptor> CONFIGURATORS = new ArrayList<>();

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

        setRunMessage(run, level, message);

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
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.INFO,
                            getMessageWithException(child)));
                    break;
                case Diagnostic.WARNING:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.WARNING,
                            getMessageWithException(child)));
                    break;
                case Diagnostic.ERROR:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR,
                            getMessageWithException(child)));
                    break;

                default:
                    res.add(M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.INFO,
                            getMessageWithException(child)));
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
     * Gets the given {@link Diagnostic} {@link Diagnostic#getMessage() message} and {@link Diagnostic#getException() exception}.
     * 
     * @param diagnostic
     *            the {@link Diagnostic}
     * @return the given {@link Diagnostic} {@link Diagnostic#getMessage() message} and {@link Diagnostic#getException() exception}
     */
    private static String getMessageWithException(Diagnostic diagnostic) {
        String res;

        final Throwable exception;
        if (diagnostic.getException() != null) {
            exception = diagnostic.getException().getCause();
        } else {
            exception = null;
        }
        if (exception != null) {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                    PrintWriter printWriter = new PrintWriter(out);) {
                exception.printStackTrace(printWriter);
                printWriter.flush();
                out.flush();
                res = diagnostic.getMessage() + "\n" + new String(out.toByteArray());
            } catch (IOException e) {
                // nothing to do here
                res = diagnostic.getMessage();
            }
        } else {
            res = diagnostic.getMessage();
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
     * Prepares the given {@link IQueryEnvironment} for M2Doc services.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param templateURI
     *            the template {@link URI}
     * @param options
     *            the {@link Map} of options
     */
    public static void prepareEnvironmentServices(IQueryEnvironment queryEnvironment, URI templateURI,
            Map<String, String> options) {
        prepareEnvironmentServices(queryEnvironment, URIConverter.INSTANCE, templateURI, options);
    }

    /**
     * Prepares the given {@link IQueryEnvironment} for M2Doc services.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            the template {@link URI}
     * @param options
     *            the {@link Map} of options
     */
    public static void prepareEnvironmentServices(IQueryEnvironment queryEnvironment, URIConverter uriConverter,
            URI templateURI, Map<String, String> options) {

        Set<IService> services = ServiceUtils.getServices(queryEnvironment, BooleanServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, LinkServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, PaginationServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, new ImageServices(uriConverter, templateURI));
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, new ExcelServices(uriConverter, templateURI));
        ServiceUtils.registerServices(queryEnvironment, services);
        for (IServicesConfigurator configurator : getConfigurators()) {
            ServiceUtils.registerServices(queryEnvironment, configurator.getServices(queryEnvironment, options));
        }
    }

    /**
     * Parses a template document and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @param templateURI
     *            URI for the template, used when external links (images, includes) have to be resolved
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param classProvider
     *            the {@link IClassProvider} to use for service Loading
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    public static DocumentTemplate parse(URI templateURI, IQueryEnvironment queryEnvironment,
            IClassProvider classProvider) throws DocumentParserException {
        return parse(URIConverter.INSTANCE, templateURI, queryEnvironment, classProvider);
    }

    /**
     * Parses a template document and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param templateURI
     *            URI for the template, used when external links (images, includes) have to be resolved
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param classProvider
     *            the {@link IClassProvider} to use for service Loading
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    @SuppressWarnings("resource")
    public static DocumentTemplate parse(URIConverter uriConverter, URI templateURI, IQueryEnvironment queryEnvironment,
            IClassProvider classProvider) throws DocumentParserException {
        final DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        final ResourceImpl r = new ResourceImpl(templateURI);

        try {
            // resources are closed in DocumentTemplate.close()
            final InputStream is = uriConverter.createInputStream(templateURI);
            final OPCPackage oPackage = OPCPackage.open(is);
            final XWPFDocument document = new XWPFDocument(oPackage);
            final List<TemplateValidationMessage> messages = parseTemplateCustomProperties(queryEnvironment,
                    classProvider, document);
            r.getContents().add(result);
            final M2DocParser parser = new M2DocParser(document, queryEnvironment);
            final Block documentBody = parser.parseBlock(result.getTemplates(), TokenType.EOF);
            for (TemplateValidationMessage validationMessage : messages) {
                documentBody.getValidationMessages().add(validationMessage);
            }
            result.setBody(documentBody);
            result.setInputStream(is);
            result.setOpcPackage(oPackage);
            result.setDocument(document);
            for (XWPFFooter footer : document.getFooterList()) {
                final M2DocParser footerParser = new M2DocParser(footer, queryEnvironment);
                result.getFooters().add(footerParser.parseBlock(null, TokenType.EOF));
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final M2DocParser headerParser = new M2DocParser(header, queryEnvironment);
                result.getHeaders().add(headerParser.parseBlock(null, TokenType.EOF));
            }

        } catch (IOException e) {
            throw new DocumentParserException("Unable to open " + templateURI, e);
        } catch (InvalidFormatException e1) {
            throw new DocumentParserException("Invalid .docx format " + templateURI, e1);
        }

        return result;
    }

    /**
     * Parses {@link TemplateCustomProperties} for the given {@link XWPFDocument} and initializes the given {@link IQueryEnvironment}.
     * 
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @param classProvider
     *            the {@link IClassProvider} to use for service Loading
     * @param document
     *            the {@link XWPFDocument}
     * @return the {@link List} of {@link TemplateValidationMessage} produced while reading the {@link TemplateCustomProperties}
     */
    private static List<TemplateValidationMessage> parseTemplateCustomProperties(IQueryEnvironment queryEnvironment,
            IClassProvider classProvider, final XWPFDocument document) {
        final TemplateCustomProperties properties = new TemplateCustomProperties(document);
        final List<TemplateValidationMessage> messages = new ArrayList<>();
        final List<String> missingNsURIs = properties.configureQueryEnvironmentWithResult(queryEnvironment);
        for (String nsURI : missingNsURIs) {
            final XWPFRun run = document.getParagraphs().get(0).getRuns().get(0);
            final TemplateValidationMessage validationMessage = new TemplateValidationMessage(
                    ValidationMessageLevel.ERROR, "can't find EPackage: " + nsURI, run);
            messages.add(validationMessage);
        }
        for (Entry<String, String> entry : properties.getServiceClasses().entrySet()) {
            try {
                final Class<?> cls = classProvider.getClass(entry.getKey(), entry.getValue());
                final Set<IService> s = ServiceUtils.getServices(queryEnvironment, cls);
                ServiceUtils.registerServices(queryEnvironment, s);
            } catch (ClassNotFoundException e) {
                final XWPFRun run = document.getParagraphs().get(0).getRuns().get(0);
                final TemplateValidationMessage validationMessage = new TemplateValidationMessage(
                        ValidationMessageLevel.ERROR, "can't load service class: " + entry.getKey(), run);
                messages.add(validationMessage);
            }
        }

        return messages;
    }

    /**
     * Parses a document for {@link UserContent} and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @param documentURI
     *            URI for the document
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    public static DocumentTemplate parseUserContent(URI documentURI, IQueryEnvironment queryEnvironment)
            throws DocumentParserException {
        return parseUserContent(URIConverter.INSTANCE, documentURI, queryEnvironment);
    }

    /**
     * Parses a document for {@link UserContent} and returns the {@link DocumentTemplate} resulting from
     * this parsing.
     * 
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param documentURI
     *            URI for the document
     * @param queryEnvironment
     *            the {@link IQueryEnvironment}
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    @SuppressWarnings("resource")
    public static DocumentTemplate parseUserContent(URIConverter uriConverter, URI documentURI,
            IQueryEnvironment queryEnvironment) throws DocumentParserException {
        final DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        final ResourceImpl r = new ResourceImpl(documentURI);

        try {
            // resources are closed in DocumentTemplate.close()
            final InputStream is = uriConverter.createInputStream(documentURI);
            final OPCPackage oPackage = OPCPackage.open(is);
            final XWPFDocument document = new XWPFDocument(oPackage);
            r.getContents().add(result);
            final BodyGeneratedParser parser = new BodyGeneratedParser(document, queryEnvironment);
            result.setBody(parser.parseBlock(null));
            result.setInputStream(is);
            result.setOpcPackage(oPackage);
            result.setDocument(document);
            for (XWPFFooter footer : document.getFooterList()) {
                final BodyGeneratedParser footerParser = new BodyGeneratedParser(footer, queryEnvironment);
                result.getFooters().add(footerParser.parseBlock(null));
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final BodyGeneratedParser headerParser = new BodyGeneratedParser(header, queryEnvironment);
                result.getHeaders().add(headerParser.parseBlock(null));
            }

        } catch (IOException e) {
            throw new DocumentParserException("Unable to open " + documentURI, e);
        } catch (InvalidFormatException e1) {
            throw new DocumentParserException("Invalid .docx format " + documentURI, e1);
        }

        return result;
    }

    /**
     * Validates the given {@link DocumentTemplate} with the given {@link IReadOnlyQueryEnvironment} and variables types.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @return the {@link ValidationMessageLevel}
     */
    public static ValidationMessageLevel validate(DocumentTemplate documentTemplate,
            IReadOnlyQueryEnvironment queryEnvironment) {
        final M2DocValidator validator = new M2DocValidator();
        return validator.validate(documentTemplate, queryEnvironment);
    }

    /**
     * Serializes the given {@link M2DocUtils#validate(DocumentTemplate, IReadOnlyQueryEnvironment, Map) validated} {@link DocumentTemplate}
     * to the given destination.
     * 
     * @param documentTemplate
     *            the {@link M2DocUtils#validate(DocumentTemplate, IReadOnlyQueryEnvironment, Map) validated} {@link DocumentTemplate}
     * @param destination
     *            the destination {@link URI}
     * @throws IOException
     *             if the {@link DocumentTemplate} can't be serialized to the given destination
     */
    public static void serializeValidatedDocumentTemplate(DocumentTemplate documentTemplate, URI destination)
            throws IOException {
        serializeValidatedDocumentTemplate(URIConverter.INSTANCE, documentTemplate, destination);
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
    public static void serializeValidatedDocumentTemplate(URIConverter uriConverter, DocumentTemplate documentTemplate,
            URI destination) throws IOException {
        TemplateValidationGenerator generator = new TemplateValidationGenerator();

        generator.doSwitch(documentTemplate);
        POIServices.getInstance().saveFile(uriConverter, documentTemplate.getDocument(), destination);
    }

    /**
     * Generates the given template into the given destination.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param variables
     *            variables
     * @param destination
     *            the destination
     * @param monitor
     *            used to track the progress will generating.
     * @return the {@link GenerationResult}
     * @throws DocumentGenerationException
     *             if the generation fails
     */
    public static GenerationResult generate(DocumentTemplate documentTemplate,
            IReadOnlyQueryEnvironment queryEnvironment, Map<String, Object> variables, URI destination, Monitor monitor)
            throws DocumentGenerationException {
        return generate(documentTemplate, queryEnvironment, variables, URIConverter.INSTANCE, destination, monitor);
    }

    /**
     * Generates the given template into the given destination.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param variables
     *            variables
     * @param uriConverter
     *            the {@link URIConverter uri converter} to use.
     * @param destination
     *            the destination
     * @param monitor
     *            used to track the progress will generating.
     * @return the {@link GenerationResult}
     * @throws DocumentGenerationException
     *             if the generation fails
     */
    public static GenerationResult generate(DocumentTemplate documentTemplate,
            IReadOnlyQueryEnvironment queryEnvironment, Map<String, Object> variables, URIConverter uriConverter,
            URI destination, Monitor monitor) throws DocumentGenerationException {

        monitor.beginTask("Generating " + destination.lastSegment(), 1);

        try (InputStream is = uriConverter.createInputStream(documentTemplate.eResource().getURI());
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument destinationDocument = new XWPFDocument(oPackage);) {

            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(uriConverter, documentTemplate,
                    destination);
            final M2DocEvaluator evaluator = new M2DocEvaluator(bookmarkManager, userContentManager, queryEnvironment,
                    monitor);
            for (Template template : documentTemplate.getTemplates()) {
                ((IQueryEnvironment) queryEnvironment).registerService(new M2DocTemplateService(template, uriConverter,
                        bookmarkManager, userContentManager, queryEnvironment, monitor));
            }

            final GenerationResult result = evaluator.generate(documentTemplate, variables, destinationDocument);

            bookmarkManager.markDanglingReferences(result);
            bookmarkManager.markOpenBookmarks(result);

            userContentManager.generateLostFiles(result);
            userContentManager.dispose();
            for (IServicesConfigurator configurator : getConfigurators()) {
                configurator.cleanServices(queryEnvironment);
            }

            // At this point, the document has been generated and just needs to be written on disk.
            POIServices.getInstance().saveFile(uriConverter, destinationDocument, destination);

            return result;
        } catch (IOException e) {
            throw new DocumentGenerationException("An I/O problem occured while creating the output document.", e);
        } catch (InvalidFormatException e) {
            throw new DocumentGenerationException("Input document seems to have an invalid format.", e);
        } finally {
            monitor.done();
        }
    }

    /**
     * Registers the given {@link IServicesConfiguratorDescriptor}.
     * 
     * @param configurator
     *            the {@link IServicesConfiguratorDescriptor} to register
     */
    public static void registerServicesConfigurator(IServicesConfiguratorDescriptor configurator) {
        if (configurator != null) {
            synchronized (CONFIGURATORS) {
                CONFIGURATORS.add(configurator);
            }
        }
    }

    /**
     * Unregister the given {@link IServicesConfiguratorDescriptor}.
     * 
     * @param configuratorDescriptor
     *            the {@link IServicesConfiguratorDescriptor} to unregister
     */
    public static void unregisterServicesConfigurator(IServicesConfiguratorDescriptor configuratorDescriptor) {
        if (configuratorDescriptor != null) {
            synchronized (CONFIGURATORS) {
                CONFIGURATORS.remove(configuratorDescriptor);
            }
        }
    }

    /**
     * Gets the {@link List} of registered {@link IServicesConfigurator}.
     * 
     * @return the {@link List} of {@link #registerServicesConfigurator(IServicesConfiguratorDescriptor) registered}
     *         {@link IServicesConfigurator}
     */
    public static List<IServicesConfigurator> getConfigurators() {
        final List<IServicesConfigurator> res = new ArrayList<>();

        synchronized (CONFIGURATORS) {
            for (IServicesConfiguratorDescriptor descriptor : CONFIGURATORS) {
                final IServicesConfigurator configurator = descriptor.getServicesConfigurator();
                if (configurator != null) {
                    res.add(configurator);
                }
            }
        }

        return res;
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

        for (IServicesConfigurator configurator : getConfigurators()) {
            res.putAll(configurator.getInitializedOptions(options));
        }

        return res;
    }

}
