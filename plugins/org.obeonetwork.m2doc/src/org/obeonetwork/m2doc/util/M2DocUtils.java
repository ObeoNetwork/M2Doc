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

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
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
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.BooleanServices;
import org.obeonetwork.m2doc.services.ImageServices;
import org.obeonetwork.m2doc.services.LinkServices;
import org.obeonetwork.m2doc.services.PaginationServices;
import org.obeonetwork.m2doc.services.ServiceRegistry;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.services.configurator.IServicesConfiguratorDescriptor;
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
    private static final List<IServicesConfiguratorDescriptor> CONFIGURATORS = new ArrayList<IServicesConfiguratorDescriptor>();

    /**
     * Constructor.
     */
    private M2DocUtils() {
        super();
    }

    /**
     * Save the contents of the resource to the file system.
     * 
     * @param resource
     *            Resource
     * @throws IOException
     *             if the resource can't be serialized
     */
    public static void saveResource(Resource resource) throws IOException {
        Map<Object, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        resource.save(options);
    }

    /**
     * Create resource.
     * 
     * @param templateFile
     *            IFile
     * @param genConfURI
     *            URI
     * @return new resource.
     */
    public static Resource createResource(URI templateFile, URI genConfURI) {
        // Create a resource set
        ResourceSet resourceSet = new ResourceSetImpl();
        // Create a resource for this file.
        Resource resource = resourceSet.createResource(genConfURI);
        return resource;
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
     * @return the created {@link XWPFRun}
     */
    public static XWPFRun appendMessageRun(XWPFParagraph paragraph, ValidationMessageLevel level, String message) {
        final XWPFRun res = paragraph.createRun();

        setRunMessage(res, level, message);

        return res;
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
    public static ValidationMessageLevel appendDiagnosticMessage(XWPFParagraph paragraph, Diagnostic diagnostic) {
        ValidationMessageLevel res = ValidationMessageLevel.OK;

        for (Diagnostic child : diagnostic.getChildren()) {
            switch (child.getSeverity()) {
                case Diagnostic.INFO:
                    M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.INFO, child.getMessage());
                    res = ValidationMessageLevel.updateLevel(res, ValidationMessageLevel.INFO);
                    break;
                case Diagnostic.WARNING:
                    M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.WARNING, child.getMessage());
                    res = ValidationMessageLevel.updateLevel(res, ValidationMessageLevel.WARNING);
                    break;
                case Diagnostic.ERROR:
                    M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.ERROR, child.getMessage());
                    res = ValidationMessageLevel.updateLevel(res, ValidationMessageLevel.ERROR);
                    break;

                default:
                    M2DocUtils.appendMessageRun(paragraph, ValidationMessageLevel.INFO, child.getMessage());
                    res = ValidationMessageLevel.updateLevel(res, ValidationMessageLevel.INFO);
                    break;
            }
            paragraph.getRuns().get(paragraph.getRuns().size() - 1).addBreak();
            if (!child.getChildren().isEmpty()) {
                res = ValidationMessageLevel.updateLevel(res, appendDiagnosticMessage(paragraph, child));
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
    public static XWPFRun insertMessageAfter(XWPFRun run, ValidationMessageLevel level, String message) {
        final XWPFRun res;

        final IRunBody parent = run.getParent();
        if (parent instanceof XWPFParagraph) {
            final XWPFParagraph paragraph = (XWPFParagraph) parent;
            res = paragraph.insertNewRun(paragraph.getRuns().indexOf(run));
            setRunMessage(res, level, message);
        } else {
            throw new IllegalStateException("this should not happend");
        }

        return res;
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
     */
    public static void setRunMessage(XWPFRun run, ValidationMessageLevel level, String message) {
        run.setText(message);
        run.setBold(true);
        run.setColor(getColor(level));
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
     *            THe message to log
     */
    public static void validationError(IConstruct construct, String msg) {
        XWPFRun lastRun = construct.getRuns().get(construct.getRuns().size() - 1);
        construct.getValidationMessages()
                .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, msg, lastRun));
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

        Set<IService> services = ServiceUtils.getServices(queryEnvironment, BooleanServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, LinkServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, PaginationServices.class);
        ServiceUtils.registerServices(queryEnvironment, services);
        services = ServiceUtils.getServices(queryEnvironment, new ImageServices(templateURI));
        ServiceUtils.registerServices(queryEnvironment, services);
        List<Class<?>> defaultClasses = ServiceRegistry.INSTANCE.getServicePackages(ServiceRegistry.DEFAULT_TOKEN);
        for (Class<?> cls : defaultClasses) {
            services = ServiceUtils.getServices(queryEnvironment, cls);
            ServiceUtils.registerServices(queryEnvironment, services);
        }
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
     * @param classLoader
     *            the {@link ClassLoader} to use for service Loading
     * @return the {@link DocumentTemplate} resulting from parsing the specified
     *         document
     * @throws DocumentParserException
     *             if a problem occurs while parsing the document.
     */
    @SuppressWarnings("resource")
    public static DocumentTemplate parse(URI templateURI, IQueryEnvironment queryEnvironment, ClassLoader classLoader)
            throws DocumentParserException {
        final DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        final ResourceImpl r = new ResourceImpl(templateURI);

        try {
            // resources are closed in DocumentTemplate.close()
            final InputStream is = URIConverter.INSTANCE.createInputStream(templateURI);
            final OPCPackage oPackage = OPCPackage.open(is);
            final XWPFDocument document = new XWPFDocument(oPackage);
            final List<TemplateValidationMessage> messages = parseTemplateCustomProperties(queryEnvironment,
                    classLoader, document);
            r.getContents().add(result);
            final M2DocParser parser = new M2DocParser(document, queryEnvironment);
            final Template documentBody = parser.parseTemplate();
            for (TemplateValidationMessage validationMessage : messages) {
                documentBody.getValidationMessages().add(validationMessage);
            }
            result.setBody(documentBody);
            result.setInputStream(is);
            result.setOpcPackage(oPackage);
            result.setDocument(document);
            for (XWPFFooter footer : document.getFooterList()) {
                final M2DocParser footerParser = new M2DocParser(footer, queryEnvironment);
                result.getFooters().add(footerParser.parseTemplate());
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final M2DocParser headerParser = new M2DocParser(header, queryEnvironment);
                result.getHeaders().add(headerParser.parseTemplate());
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
     * @param classLoader
     *            the {@link ClassLoader} to use for service Loading
     * @param document
     *            the {@link XWPFDocument}
     * @return the {@link List} of {@link TemplateValidationMessage} produced while reading the {@link TemplateCustomProperties}
     */
    private static List<TemplateValidationMessage> parseTemplateCustomProperties(IQueryEnvironment queryEnvironment,
            ClassLoader classLoader, final XWPFDocument document) {
        final TemplateCustomProperties properties = new TemplateCustomProperties(document);
        final List<TemplateValidationMessage> messages = new ArrayList<>();
        for (String nsURI : properties.getPackagesURIs()) {
            final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
            if (ePackage != null) {
                queryEnvironment.registerEPackage(ePackage);
            } else {
                final XWPFRun run = document.getParagraphs().get(0).getRuns().get(0);
                final TemplateValidationMessage validationMessage = new TemplateValidationMessage(
                        ValidationMessageLevel.ERROR, "can't find EPackage: " + nsURI, run);
                messages.add(validationMessage);
            }
        }
        for (String token : properties.getServiceTokens()) {
            List<Class<?>> services = ServiceRegistry.INSTANCE.getServicePackages(token);
            for (Class<?> cls : services) {
                final Set<IService> s = ServiceUtils.getServices(queryEnvironment, cls);
                ServiceUtils.registerServices(queryEnvironment, s);
            }
        }
        for (String serviceClass : properties.getServiceClasses()) {
            try {
                final Class<?> cls = classLoader.loadClass(serviceClass);
                final Set<IService> s = ServiceUtils.getServices(queryEnvironment, cls);
                ServiceUtils.registerServices(queryEnvironment, s);
            } catch (ClassNotFoundException e) {
                final XWPFRun run = document.getParagraphs().get(0).getRuns().get(0);
                final TemplateValidationMessage validationMessage = new TemplateValidationMessage(
                        ValidationMessageLevel.ERROR, "can't load service class: " + serviceClass, run);
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
    @SuppressWarnings("resource")
    public static DocumentTemplate parseUserContent(URI documentURI, IQueryEnvironment queryEnvironment)
            throws DocumentParserException {
        final DocumentTemplate result = (DocumentTemplate) EcoreUtil.create(TemplatePackage.Literals.DOCUMENT_TEMPLATE);
        final ResourceImpl r = new ResourceImpl(documentURI);

        try {
            // resources are closed in DocumentTemplate.close()
            final InputStream is = URIConverter.INSTANCE.createInputStream(documentURI);
            final OPCPackage oPackage = OPCPackage.open(is);
            final XWPFDocument document = new XWPFDocument(oPackage);
            r.getContents().add(result);
            final BodyGeneratedParser parser = new BodyGeneratedParser(document, queryEnvironment);
            result.setBody(parser.parseTemplate());
            result.setInputStream(is);
            result.setOpcPackage(oPackage);
            result.setDocument(document);
            for (XWPFFooter footer : document.getFooterList()) {
                final BodyGeneratedParser footerParser = new BodyGeneratedParser(footer, queryEnvironment);
                result.getFooters().add(footerParser.parseTemplate());
            }
            for (XWPFHeader header : document.getHeaderList()) {
                final BodyGeneratedParser headerParser = new BodyGeneratedParser(header, queryEnvironment);
                result.getHeaders().add(headerParser.parseTemplate());
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
        TemplateValidationGenerator generator = new TemplateValidationGenerator();

        generator.doSwitch(documentTemplate);
        POIServices.getInstance().saveFile(documentTemplate.getDocument(), destination);
    }

    /**
     * Generates the given template into the given destination.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param resourceSetForModels
     *            the resourceset used to load and process the user models.
     * @param variables
     *            variables
     * @param destination
     *            the destination
     * @return the {@link GenerationResult}
     * @throws DocumentGenerationException
     *             if the generation fails
     * @deprecated the method with a monitor parameter should be prefered.
     */
    @Deprecated
    public static GenerationResult generate(DocumentTemplate documentTemplate,
            IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels, Map<String, Object> variables,
            URI destination) throws DocumentGenerationException {
        return generate(documentTemplate, queryEnvironment, resourceSetForModels, variables, destination,
                new BasicMonitor());
    }

    /**
     * Generates the given template into the given destination.
     * 
     * @param documentTemplate
     *            the {@link DocumentTemplate}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param resourceSetForModels
     *            the resourceset used to load and process the user models.
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
            IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels, Map<String, Object> variables,
            URI destination, Monitor monitor) throws DocumentGenerationException {

        monitor.beginTask("Generating " + destination.lastSegment(), 1);

        try (InputStream is = URIConverter.INSTANCE.createInputStream(documentTemplate.eResource().getURI());
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument destinationDocument = new XWPFDocument(oPackage);) {
            // clear the document
            int size = destinationDocument.getBodyElements().size();
            for (int i = 0; i < size; i++) {
                destinationDocument.removeBodyElement(0);
            }

            final BookmarkManager bookmarkManager = new BookmarkManager();
            final UserContentManager userContentManager = new UserContentManager(documentTemplate, destination);
            final M2DocEvaluator processor = new M2DocEvaluator(bookmarkManager, userContentManager, queryEnvironment,
                    resourceSetForModels, monitor);

            final GenerationResult result = processor.generate(documentTemplate, variables, destinationDocument);

            if (bookmarkManager.markDanglingReferences()) {
                result.updateLevel(ValidationMessageLevel.ERROR);
            }
            if (bookmarkManager.markOpenBookmarks()) {
                result.updateLevel(ValidationMessageLevel.ERROR);
            }

            userContentManager.generateLostFiles(result);
            userContentManager.dispose();
            for (IServicesConfigurator configurator : getConfigurators()) {
                configurator.cleanServices(queryEnvironment);
            }

            // At this point, the document has been generated and just needs being
            // written on disk.
            POIServices.getInstance().saveFile(destinationDocument, destination);

            processor.clear();

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
        final List<IServicesConfigurator> res = new ArrayList<IServicesConfigurator>();

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

}
