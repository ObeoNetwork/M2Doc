/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.IClassProvider;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Utility class for {@link Generation}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public final class GenconfUtils {

    static {
        // make sure org.obeonetwork.m2doc.ide is started
        M2DocPlugin.INSTANCE.getBaseURL();
    }

    /**
     * The genconf extension file.
     */
    public static final String GENCONF_EXTENSION_FILE = "genconf";

    /**
     * The genconf {@link URI} option.
     */
    public static final String GENCONF_URI_OPTION = "GenconfURI";

    /**
     * The {@link Generation#getTemplateFileName() template URI} option.
     */
    public static final String TEMPLATE_URI_OPTION = "TemplateURI";

    /**
     * The {@link Generation#getResultFileName() result URI} option.
     */
    public static final String RESULT_URI_OPTION = "ResultURI";

    /**
     * The {@link Generation#getValidationFileName() result URI} option.
     */
    public static final String VALIDATION_URI_OPTION = "ValidationURI";

    /**
     * Constructor.
     */
    private GenconfUtils() {
    }

    /**
     * Gets the {@link Map} of options from the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the {@link Map} of options from the given {@link Generation}
     */
    public static Map<String, String> getOptions(Generation generation) {
        final Map<String, String> res = new LinkedHashMap<String, String>();

        final Resource eResource = generation.eResource();
        if (eResource != null && eResource.getURI() != null) {
            res.put(GENCONF_URI_OPTION, eResource.getURI().toString());
        }
        if (generation.getTemplateFileName() != null) {
            res.put(TEMPLATE_URI_OPTION,
                    getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false)).toString());
        }
        if (generation.getResultFileName() != null) {
            res.put(RESULT_URI_OPTION,
                    getResolvedURI(generation, URI.createURI(generation.getResultFileName(), false)).toString());
        }
        if (generation.getValidationFileName() != null) {
            res.put(VALIDATION_URI_OPTION,
                    getResolvedURI(generation, URI.createURI(generation.getValidationFileName(), false)).toString());
        }
        for (Option option : generation.getOptions()) {
            res.put(option.getName(), option.getValue());
        }

        return res;
    }

    /**
     * Initializes options for the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     */
    public static void initializeOptions(Generation generation) {
        final Map<String, String> options = getOptions(generation);

        final Map<String, String> initializedOptions = M2DocUtils.getInitializedOptions(options);
        for (Option option : generation.getOptions()) {
            if (initializedOptions.containsKey(option.getName())) {
                option.setValue(initializedOptions.remove(option.getName()));
            }
        }
        for (Entry<String, String> entry : initializedOptions.entrySet()) {
            final Option option = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
            option.setName(entry.getKey());
            option.setValue(entry.getValue());
            generation.getOptions().add(option);
        }
    }

    /**
     * Gets the initialized {@link IQueryEnvironment} for the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the initialized {@link IQueryEnvironment} for the given {@link Generation}
     */
    public static IQueryEnvironment getQueryEnvironment(Generation generation) {
        final URI templateURI;
        templateURI = getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false));

        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);

        final Map<String, String> options = getOptions(generation);
        M2DocUtils.prepareEnvironmentServices(queryEnvironment, templateURI, options);

        return queryEnvironment;
    }

    /**
     * Gets the variables {@link Map} from the given {@link Generation} and {@link ResourceSet}.
     * 
     * @param generation
     *            the {@link Generation} holding the {@link Generation#getDefinitions() definitions}
     * @param resourceSet
     *            the {@link ResourceSet} used to load the model instances
     * @return the created variables {@link Map} from the given {@link Generation} and {@link ResourceSet}
     */
    public static Map<String, Object> getVariables(Generation generation, ResourceSet resourceSet) {
        Map<String, Object> result = new HashMap<String, Object>();
        for (Definition def : generation.getDefinitions()) {
            if (def instanceof ModelDefinition) {
                EObject val = null;
                final EObject originalValue = ((ModelDefinition) def).getValue();
                if (originalValue != null) {
                    URI uri = EcoreUtil.getURI(originalValue);
                    try {
                        val = resourceSet.getEObject(uri, true);
                    } catch (WrappedException e) {
                        /*
                         * The resource could not be loaded, in that case the value is reset to a proxy with the same uri.
                         */
                        if (originalValue != null) {
                            InternalEObject eobj = (InternalEObject) EcoreUtil.create(originalValue.eClass());
                            eobj.eSetProxyURI(uri);
                            val = eobj;
                        }
                    }
                }
                result.put(((ModelDefinition) def).getKey(), val);
            } else if (def instanceof StringDefinition) {
                result.put(((StringDefinition) def).getKey(), ((StringDefinition) def).getValue());
            } else if (def instanceof IntegerDefinition) {
                result.put(((IntegerDefinition) def).getKey(), ((IntegerDefinition) def).getValue());
            } else if (def instanceof RealDefinition) {
                result.put(((RealDefinition) def).getKey(), ((RealDefinition) def).getValue());
            } else if (def instanceof BooleanDefinition) {
                result.put(((BooleanDefinition) def).getKey(), ((BooleanDefinition) def).isValue());
            } else {
                throw new UnsupportedOperationException();
            }
        }
        return result;
    }

    /**
     * Gets the {@link List} of old {@link Definition} delta between the given {@link Generation} and the given
     * {@link TemplateCustomProperties}. {@link Definition} that can be removed from the {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @param templateCustomProperties
     *            the {@link TemplateCustomProperties}
     * @return the {@link List} of new {@link Definition}
     */
    public static List<Definition> getOldDefinitions(Generation generation,
            TemplateCustomProperties templateCustomProperties) {
        final List<Definition> oldDefinitions = new ArrayList<Definition>();

        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        queryEnvironment.registerCustomClassMapping(EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
                EStringToStringMapEntryImpl.class);
        templateCustomProperties.configureQueryEnvironmentWithResult(queryEnvironment);
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Map<String, Set<IType>> variablesTypes = templateCustomProperties.getVariableTypes(validator,
                queryEnvironment);
        for (Definition definition : generation.getDefinitions()) {
            if (!variablesTypes.containsKey(definition.getKey())
                || !isValidDefinitionForType(queryEnvironment, definition, variablesTypes.get(definition.getKey()))) {
                oldDefinitions.add(definition);
            }
        }

        return oldDefinitions;
    }

    /**
     * Gets the {@link List} of new {@link Definition} delta between the given {@link Generation} and the given
     * {@link TemplateCustomProperties}. {@link Definition} that can be added to the {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @param templateCustomProperties
     *            the {@link TemplateCustomProperties}
     * @return the {@link List} of new {@link Definition}
     */
    public static List<Definition> getNewDefinitions(Generation generation,
            TemplateCustomProperties templateCustomProperties) {
        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        queryEnvironment.registerCustomClassMapping(EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
                EStringToStringMapEntryImpl.class);
        templateCustomProperties.configureQueryEnvironmentWithResult(queryEnvironment);
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Map<String, Set<IType>> variablesTypes = templateCustomProperties.getVariableTypes(validator,
                queryEnvironment);
        final Set<String> existingVariables = new HashSet<String>();
        for (Definition definition : generation.getDefinitions()) {
            if (variablesTypes.containsKey(definition.getKey())
                && isValidDefinitionForType(queryEnvironment, definition, variablesTypes.get(definition.getKey()))) {
                existingVariables.add(definition.getKey());
            }
        }

        final List<Definition> newDefinitions = new ArrayList<Definition>();
        for (Entry<String, Set<IType>> entry : variablesTypes.entrySet()) {
            if (!existingVariables.contains(entry.getKey())) {
                final Definition newDefinition = createDefinition(queryEnvironment, entry.getKey(), entry.getValue());
                if (newDefinition != null) {
                    newDefinitions.add(newDefinition);
                }
            }
        }

        return newDefinitions;
    }

    /**
     * Creates the proper definition for the given {@link Set} of {@link IType}.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param name
     *            the {@link Definition#getKey() definition name}
     * @param types
     *            the {@link Set} of {@link IType}
     * @return the proper definition for the given {@link Set} of {@link IType} if any created, <code>null</code> otherwise
     */
    private static Definition createDefinition(IReadOnlyQueryEnvironment queryEnvironment, String name,
            Set<IType> types) {
        Definition res = null;

        final ClassType eObjectType = new ClassType(queryEnvironment, EObject.class);
        final ClassType stringType = new ClassType(queryEnvironment, String.class);
        for (IType type : types) {
            if (eObjectType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createModelDefinition();
                res.setKey(name);
                break;
            } else if (stringType.isAssignableFrom(type)) {
                res = GenconfPackage.eINSTANCE.getGenconfFactory().createStringDefinition();
                res.setKey(name);
                break;
            }
        }

        return res;

    }

    /**
     * Tells if the given {@link Definition} is valid for the given {@link Set} of {@link IType}.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param definition
     *            the {@link Definition} to check
     * @param types
     *            the {@link Set} of {@link IType}
     * @return <code>true</code> if the given {@link Definition} is valid for the given {@link Set} of {@link IType}, <code>false</code>
     *         otherwise
     */
    private static boolean isValidDefinitionForType(IReadOnlyQueryEnvironment queryEnvironment, Definition definition,
            Set<IType> types) {
        boolean res = false;

        final ClassType eObjectType = new ClassType(queryEnvironment, EObject.class);
        final ClassType stringType = new ClassType(queryEnvironment, String.class);
        final ClassType integerType = new ClassType(queryEnvironment, Integer.class);
        final ClassType realType = new ClassType(queryEnvironment, Double.class);
        final ClassType booleanType = new ClassType(queryEnvironment, Boolean.class);
        for (IType type : types) {
            if (eObjectType.isAssignableFrom(type) && definition instanceof ModelDefinition) {
                res = true;
                break;
            } else if (stringType.isAssignableFrom(type) && definition instanceof StringDefinition) {
                res = true;
                break;
            } else if (integerType.isAssignableFrom(type) && definition instanceof IntegerDefinition) {
                res = true;
                break;
            } else if (realType.isAssignableFrom(type) && definition instanceof RealDefinition) {
                res = true;
                break;
            } else if (booleanType.isAssignableFrom(type) && definition instanceof BooleanDefinition) {
                res = true;
                break;
            }
        }

        return res;
    }

    /**
     * Generate a document from the specified generation configuration.
     * 
     * @param generation
     *            the generation configuration
     * @param classProvider
     *            the {@link IClassProvider}
     * @param monitor
     *            used to track the progress will generating.
     * @return generated file
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws IOException
     *             IOException
     */
    public static List<URI> generate(Generation generation, IClassProvider classProvider, Monitor monitor)
            throws DocumentGenerationException, IOException, DocumentParserException {
        if (generation == null) {
            throw new IllegalArgumentException("Null configuration object passed.");
        }
        String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        String resultFilePath = generation.getResultFileName();
        if (resultFilePath == null) {
            throw new DocumentGenerationException("The result file path isn't set in the provided configuration");
        }

        final URI templateURI = getResolvedURI(generation, URI.createURI(generation.getTemplateFileName(), false));
        final URI generatedURI = getResolvedURI(generation, URI.createURI(generation.getResultFileName(), false));
        final URI validationURI;
        if (generation.getValidationFileName() != null) {
            validationURI = getResolvedURI(generation, URI.createURI(generation.getValidationFileName(), false));
        } else {
            validationURI = null;
        }

        // generate result file and validation file.
        return generate(generation, classProvider, templateURI, generatedURI, validationURI, monitor);
    }

    /**
     * Gets the {@link URI#resolve(URI) resolved URI} from the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @param uri
     *            the {@link URI} to resolve
     * @return the {@link URI#resolve(URI) resolved URI} from the given {@link Generation}
     */
    public static URI getResolvedURI(Generation generation, URI uri) {
        final URI res;

        if (generation.eResource() != null && generation.eResource().getURI() != null) {
            res = uri.resolve(generation.eResource().getURI());
        } else {
            res = uri;
        }

        return res;
    }

    /**
     * Launch the documentation generation.
     * 
     * @param generation
     *            the generation configuration object
     * @param classProvider
     *            the {@link IClassProvider}
     * @param templateURI
     *            the template {@link URI}
     * @param generatedURI
     *            the generated docx {@link URI}
     * @param validationURI
     *            the validation docx {@link URI}
     * @param monitor
     *            used to track the progress will generating.
     * @return generated file and validation file if exists
     * @throws IOException
     *             if an I/O problem occurs
     * @throws DocumentParserException
     *             if the document coulnd'nt be parsed.
     * @throws DocumentGenerationException
     *             if the document couldn't be generated
     */
    private static List<URI> generate(Generation generation, IClassProvider classProvider, URI templateURI,
            URI generatedURI, URI validationURI, Monitor monitor)
            throws IOException, DocumentParserException, DocumentGenerationException {
        final IQueryEnvironment queryEnvironment = GenconfUtils.getQueryEnvironment(generation);

        monitor.beginTask("Loading models.", 2);
        final List<Exception> exceptions = new ArrayList<Exception>();
        final ResourceSet resourceSetForModels = M2DocUtils.createResourceSetForModels(exceptions, queryEnvironment,
                new ResourceSetImpl(), getOptions(generation));
        monitor.worked(1);

        if (!resourceSetForModels.getURIConverter().exists(templateURI, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template doest not exist " + templateURI);
        }

        // create generated file
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(resourceSetForModels.getURIConverter(), templateURI,
                queryEnvironment, classProvider)) {

            // create definitions
            Map<String, Object> definitions = GenconfUtils.getVariables(generation, resourceSetForModels);
            monitor.done();

            // validate template
            monitor.beginTask("Validating template.", 1);
            final URI resultValidationURI = validate(resourceSetForModels.getURIConverter(), generatedURI,
                    validationURI, documentTemplate, queryEnvironment);
            monitor.done();

            // launch generation
            M2DocUtils.generate(documentTemplate, queryEnvironment, definitions, generatedURI, monitor);

            List<URI> generatedURIs = new ArrayList<URI>();
            generatedURIs.add(generatedURI);
            if (resultValidationURI != null) {
                generatedURIs.add(resultValidationURI);
            }

            M2DocUtils.cleanResourceSetForModels(queryEnvironment);

            return generatedURIs;
        }
    }

    /**
     * Validate templateInfo information.
     * 
     * @param generation
     *            Generation
     * @param classProvider
     *            the {@link IClassProvider}
     * @return if template contains errors.
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    public static boolean validate(Generation generation, IClassProvider classProvider)
            throws IOException, DocumentParserException, DocumentGenerationException {
        final boolean res;

        // get AQL environment
        IQueryEnvironment queryEnvironment = GenconfUtils.getQueryEnvironment(generation);

        final List<Exception> exceptions = new ArrayList<Exception>();
        final ResourceSet resourceSetForModel = M2DocUtils.createResourceSetForModels(exceptions, queryEnvironment,
                new ResourceSetImpl(), getOptions(generation));

        // get the template path
        final String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null || templateFilePath.isEmpty()) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        final URI templateURI = getResolvedURI(generation, URI.createURI(templateFilePath, false));
        if (!resourceSetForModel.getURIConverter().exists(templateURI, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template file does not exist " + templateFilePath);
        }

        // get the validation path
        final String validationFilePath = generation.getValidationFileName();
        final URI validationURI;
        if (validationFilePath == null || validationFilePath.isEmpty()) {
            validationURI = null;
        } else {
            validationURI = getResolvedURI(generation, URI.createURI(validationFilePath, false));
        }

        // parse template
        try (DocumentTemplate documentTemplate = M2DocUtils.parse(resourceSetForModel.getURIConverter(), templateURI,
                queryEnvironment, classProvider)) {
            final XWPFRun run = documentTemplate.getDocument().getParagraphs().get(0).getRuns().get(0);
            for (Exception e : exceptions) {
                documentTemplate.getBody().getValidationMessages()
                        .add(new TemplateValidationMessage(ValidationMessageLevel.ERROR, e.getMessage(), run));
            }

            // validate template
            res = validate(resourceSetForModel.getURIConverter(), templateURI, validationURI, documentTemplate,
                    queryEnvironment) != null;
        }

        // validate output path
        final String outputPath = generation.getResultFileName();
        if (outputPath == null || outputPath.isEmpty()) {
            throw new DocumentGenerationException("The output path isn't set in the provided configuration");
        } else {
            final URI outputURI = getResolvedURI(generation, URI.createURI(outputPath, false));
            if (resourceSetForModel.getURIConverter().exists(outputURI, Collections.EMPTY_MAP)) {
                final Map<Object, Object> options = new HashMap<Object, Object>();
                final Set<String> attributs = new LinkedHashSet<String>();
                attributs.add(URIConverter.ATTRIBUTE_DIRECTORY);
                attributs.add(URIConverter.ATTRIBUTE_READ_ONLY);
                options.put(URIConverter.OPTION_REQUESTED_ATTRIBUTES, attributs);
                Map<String, ?> attributeValues = resourceSetForModel.getURIConverter().getAttributes(outputURI,
                        options);
                if ((Boolean) attributeValues.get(URIConverter.ATTRIBUTE_DIRECTORY)) {
                    throw new DocumentGenerationException("The output path is a folder");
                } else if ((Boolean) attributeValues.get(URIConverter.ATTRIBUTE_READ_ONLY)) {
                    throw new DocumentGenerationException("The output path is read only");
                }
            }
        }

        M2DocUtils.cleanResourceSetForModels(queryEnvironment);

        return res;
    }

    /**
     * Validates template with templateInfo information.
     * 
     * @param uriConverter
     *            the {@link URIConverter}
     * @param generatedURI
     *            the generated {@link URI}
     * @param validationURI
     *            the validation {@link URI}
     * @param documentTemplate
     *            DocumentTemplate
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @return the validation {@link URI} if the validation isn't OK, <code>null</code> otherwise
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws IOException
     *             IOException
     */
    private static URI validate(URIConverter uriConverter, URI generatedURI, URI validationURI,
            DocumentTemplate documentTemplate, IReadOnlyQueryEnvironment queryEnvironment)
            throws DocumentGenerationException, IOException {
        final URI res;

        final ValidationMessageLevel validationLevel = M2DocUtils.validate(documentTemplate, queryEnvironment);
        if (validationLevel != ValidationMessageLevel.OK) {
            if (validationURI != null) {
                res = validationURI;
            } else {
                res = getValidationLogFile(generatedURI, validationLevel);
            }
            M2DocUtils.serializeValidatedDocumentTemplate(uriConverter, documentTemplate, res);
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the log {@link URI} for the given template {@link URI} and {@link ValidationMessageLevel}.
     * 
     * @param generatedURI
     *            the generated {@link URI}
     * @param level
     *            the {@link ValidationMessageLevel}
     * @return the log {@link URI} for the given template {@link URI} and {@link ValidationMessageLevel}
     */
    private static URI getValidationLogFile(URI generatedURI, ValidationMessageLevel level) {
        final URI res;

        String lastSegmentNoExtension = generatedURI.lastSegment().replaceFirst("[.][^.]+$", "");
        final URI uri = generatedURI.trimSegments(1)
                .appendSegment(lastSegmentNoExtension + "-" + level.name().toLowerCase());
        if (URI.validSegment(generatedURI.fileExtension())) {
            res = uri.appendFileExtension(generatedURI.fileExtension());
        } else {
            res = uri;
        }

        return res;
    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateURI
     *            the template {@link URI}
     * @return the EMF {@link Resource} containing the {@link Generation} model. The {@link URI} of that resource is built based on
     *         {@code templateURI}.
     * @throws IOException
     *             IOException
     */
    public static Resource createConfigurationModel(URI templateURI) throws IOException {
        Resource resource = null;
        TemplateCustomProperties templateProperties = POIServices.getInstance()
                .getTemplateCustomProperties(URIConverter.INSTANCE, templateURI);

        // create genconf model
        if (templateProperties != null) {
            resource = createConfigurationModel(templateProperties, templateURI);
        }
        return resource;
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
    private static Resource createResource(URI templateFile, URI genConfURI) {
        // Create a resource set
        ResourceSet resourceSet = new ResourceSetImpl();
        // Create a resource for this file.
        Resource resource = resourceSet.createResource(genConfURI);
        return resource;
    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateProperties
     *            TemplateInfo
     * @param templateURI
     *            File
     * @return configuration model
     */
    private static Resource createConfigurationModel(TemplateCustomProperties templateProperties,
            final URI templateURI) {
        // create genconf resource.
        URI genConfURI = templateURI.trimFileExtension().appendFileExtension(GenconfUtils.GENCONF_EXTENSION_FILE);
        Resource resource = createResource(templateURI, genConfURI);

        // create initial model content.
        final Generation generation = createInitialModel(genConfURI.trimFileExtension().lastSegment(),
                templateURI.deresolve(genConfURI).toString());
        // add docx properties
        final List<Definition> definitions = GenconfUtils.getNewDefinitions(generation, templateProperties);
        generation.getDefinitions().addAll(definitions);
        if (generation != null) {
            resource.getContents().add(generation);
        }
        // Save the contents of the resource to the file system.
        try {
            saveResource(resource);
        } catch (IOException e) {
            GenconfPlugin.INSTANCE
                    .log(new Status(Status.ERROR, GenconfPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
        }

        // Save the contents of the resource to the file system.
        try {
            saveResource(resource);
        } catch (IOException e) {
            GenconfPlugin.INSTANCE
                    .log(new Status(Status.ERROR, GenconfPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
        }
        return resource;
    }

    /**
     * Create generation eObject.
     * 
     * @param name
     *            the name
     * @param templateFileName
     *            the template file name
     * @return the created {@link Generation}
     */
    private static Generation createInitialModel(String name, String templateFileName) {
        Generation generation = GenconfFactory.eINSTANCE.createGeneration();
        generation.setName(URI.decode(name));
        generation.setTemplateFileName(URI.decode(templateFileName));
        return generation;
    }

    /**
     * Save the contents of the resource to the file system.
     * 
     * @param resource
     *            Resource
     * @throws IOException
     *             if the resource can't be serialized
     */
    private static void saveResource(Resource resource) throws IOException {
        Map<Object, Object> options = new HashMap<>();
        options.put(XMLResource.OPTION_ENCODING, "UTF-8");
        resource.save(options);
    }

    /**
     * Gets or creates the {@link Option} with the given name.
     * 
     * @param generation
     *            the {@link Generation}
     * @param name
     *            the {@link Option#getName() option name}
     * @return the {@link Option} with the given name
     */
    public static Option getOrCreateOption(Generation generation, String name) {
        final Option res;

        Option foundOption = null;
        for (Option option : generation.getOptions()) {
            if (name.equals(option.getName())) {
                foundOption = option;
                break;
            }
        }

        if (foundOption != null) {
            res = foundOption;
        } else {
            res = GenconfPackage.eINSTANCE.getGenconfFactory().createOption();
            res.setName(name);
            generation.getOptions().add(res);
        }

        return res;
    }

    /**
     * Gets the {@link List} of availiable {@link Option#getName() option names} for the given {@link Generation}.
     * 
     * @param generation
     *            the {@link Generation}
     * @return the {@link List} of availiable {@link Option#getName() option names}
     */
    public static List<String> getAviliableOptionNames(Generation generation) {
        List<String> availableOptions = new ArrayList<String>();

        for (IServicesConfigurator configurator : M2DocUtils.getConfigurators()) {
            availableOptions.addAll(configurator.getOptions());
        }
        for (Option option : generation.getOptions()) {
            availableOptions.remove(option.getName());
        }
        return availableOptions;
    }

    /**
     * Initializes the {@link Generation#getDefinitions() variable definitions} from the given {@link Generation} with the given
     * {@link ResourceSet}.
     * 
     * @param generation
     *            the {@link Generation} to initialize
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param properties
     *            the {@link TemplateCustomProperties}
     * @param resourceSet
     *            the {@link ResourceSet} to get values from
     */
    public static void initializeVariableDefinition(Generation generation, IReadOnlyQueryEnvironment queryEnvironment,
            TemplateCustomProperties properties, ResourceSet resourceSet) {
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Map<ModelDefinition, Set<IType>> toInitialilize = new HashMap<ModelDefinition, Set<IType>>();
        for (Definition definition : generation.getDefinitions()) {
            if (definition instanceof ModelDefinition && ((ModelDefinition) definition).getValue() == null) {
                final ModelDefinition modelDefinition = (ModelDefinition) definition;
                final Set<IType> possibleTypes = properties.getVariableTypes(validator, queryEnvironment,
                        properties.getVariables().get(modelDefinition.getKey()));
                toInitialilize.put(modelDefinition, possibleTypes);
            }
        }

        final Iterator<Notifier> it = resourceSet.getAllContents();
        while (!toInitialilize.isEmpty() && it.hasNext()) {
            final Notifier notifier = it.next();
            if (notifier instanceof EObject) {
                EObject element = (EObject) notifier;
                final EClassifierType elementType = new EClassifierType(queryEnvironment, element.eClass());
                ModelDefinition initialized = null;
                for (Entry<ModelDefinition, Set<IType>> entry : toInitialilize.entrySet()) {
                    for (IType definitionType : entry.getValue()) {
                        if (definitionType.isAssignableFrom(elementType)) {
                            initialized = entry.getKey();
                            initialized.setValue(element);
                        }
                    }
                }
                if (initialized != null) {
                    toInitialilize.remove(initialized);
                }
            }
        }
    }

}
