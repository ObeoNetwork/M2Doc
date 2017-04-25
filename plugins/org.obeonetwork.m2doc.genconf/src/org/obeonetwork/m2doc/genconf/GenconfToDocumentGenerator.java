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
package org.obeonetwork.m2doc.genconf;

import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.provider.ConfigurationProviderService;
import org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * This class can be used to generate documents from {@link Generation} configuration elements.
 * 
 * @author Romain Guider
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class GenconfToDocumentGenerator {

    /**
     * The {@link ConfigurationServices}.
     */
    private final ConfigurationServices configurationServices = new ConfigurationServices();

    /**
     * Generate a document from the specified generation configuration.
     * 
     * @param generation
     *            the generation configuration
     * @return generated file
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws IOException
     *             IOException
     * @deprecated the method with a monitor parameter should be prefered.
     */
    @Deprecated
    public List<URI> generate(Generation generation)
            throws DocumentGenerationException, IOException, DocumentParserException {
        return generate(generation, new BasicMonitor());
    }

    /**
     * Generate a document from the specified generation configuration.
     * 
     * @param generation
     *            the generation configuration
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
    public List<URI> generate(Generation generation, Monitor monitor)
            throws DocumentGenerationException, IOException, DocumentParserException {
        if (generation == null) {
            throw new IllegalArgumentException("Null configuration object passed.");
        }
        // get the template path and parses it.
        String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        // get the result path and parses it.
        String resultFilePath = generation.getResultFileName();
        if (resultFilePath == null) {
            throw new DocumentGenerationException("The result file path isn't set in the provided configuration");
        }

        // get template and result file
        URI templateFile = createURIStartingFromCurrentModel(generation, generation.getTemplateFileName());
        URI generatedFile = createURIStartingFromCurrentModel(generation, generation.getResultFileName());

        if (!URIConverter.INSTANCE.exists(templateFile, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template file doest not exist " + templateFilePath);
        }

        // generate result file.
        return generate(generation, templateFile, generatedFile, monitor);
    }

    /**
     * Creates {@link URI} starting from the current model.
     * 
     * @param generation
     *            the {@link Generation}
     * @param relativePath
     *            the relative path
     * @return the created {@link URI} starting from the current model
     */
    public static URI createURIStartingFromCurrentModel(Generation generation, String relativePath) {
        URI generationURI = generation.eResource().getURI();
        URI relativeURI = URI.createURI(relativePath);

        return relativeURI.resolve(generationURI);
    }

    /**
     * Launch the documentation generation.
     * 
     * @param generation
     *            the generation configuration object
     * @param templateFile
     *            template file
     * @param generatedFile
     *            generated file
     * @return generated file and validation file if exists
     * @throws IOException
     *             if an I/O problem occurs
     * @throws DocumentParserException
     *             if the document coulnd'nt be parsed.
     * @throws DocumentGenerationException
     *             if the document couldn't be generated
     * @deprecated the method with a monitor parameter should be prefered.
     */
    @Deprecated
    public List<URI> generate(Generation generation, URI templateFile, URI generatedFile)
            throws IOException, DocumentParserException, DocumentGenerationException {
        return generate(generation, templateFile, generatedFile, new BasicMonitor());
    }

    /**
     * Launch the documentation generation.
     * 
     * @param generation
     *            the generation configuration object
     * @param templateFile
     *            template file
     * @param generatedFile
     *            generated file
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
    public List<URI> generate(Generation generation, URI templateFile, URI generatedFile, Monitor monitor)
            throws IOException, DocumentParserException, DocumentGenerationException {
        // pre generation
        preGenerate(generation, templateFile, generatedFile, monitor);

        IQueryEnvironment queryEnvironment = configurationServices.initAcceleoEnvironment(generation);

        monitor.beginTask("Loading models.", 2);
        ResourceSet resourceSetForModels = createResourceSetForModels(generation);
        monitor.worked(1);

        // create definitions
        Map<String, Object> definitions = configurationServices.createDefinitions(generation, resourceSetForModels);
        monitor.done();

        // create generated file
        try (DocumentTemplate template = M2DocUtils.parse(templateFile, queryEnvironment,
                this.getClass().getClassLoader())) {

            // validate template
            monitor.beginTask("Validating template.", 1);
            boolean inError = validate(generatedFile, template, generation);
            monitor.done();

            // add providers variables
            definitions.putAll(configurationServices.getProviderVariables(generation));

            // launch generation
            M2DocUtils.generate(template, queryEnvironment, resourceSetForModels, definitions, generatedFile, monitor);

            List<URI> generatedFiles = Lists.newArrayList(generatedFile);
            if (inError) {
                URI validationFile = getValidationLogFile(generatedFile);
                generatedFiles.add(validationFile);
            }

            // post generation
            generatedFiles.addAll(postGenerate(generation, templateFile, generatedFile, template, monitor));

            return generatedFiles;
        }
    }

    /**
     * Pre generation.
     * 
     * @param generation
     *            Generation
     * @param templateFile
     *            File
     * @param generatedFile
     *            File
     * @param monitor
     *            used to track the progress will generating.
     */
    public void preGenerate(Generation generation, URI templateFile, URI generatedFile, Monitor monitor) {
        List<IConfigurationProvider> providers = ConfigurationProviderService.getInstance().getProviders();
        monitor.beginTask("Preparign document generation", providers.size());
        for (IConfigurationProvider configurationProvider : providers) {
            if (!monitor.isCanceled()) {
                configurationProvider.preGenerate(generation, templateFile, generatedFile);
                monitor.worked(1);
            }
        }
        monitor.done();
    }

    /**
     * Create a new resourceSet suitable for loading the models specified in the Generation objects.
     * 
     * @param generation
     *            the generation object.
     * @return a resourceset suitable for loading the models specified in the Generation object.
     */
    public ResourceSet createResourceSetForModels(Generation generation) {
        ResourceSet created = null;
        Iterator<IConfigurationProvider> it = ConfigurationProviderService.getInstance().getProviders().iterator();
        while (created == null && it.hasNext()) {
            IConfigurationProvider cur = it.next();
            created = cur.createResourceSetForModels(generation);
        }
        if (created == null) {
            created = new ResourceSetImpl();

            created.getPackageRegistry().put(GenconfPackage.eNS_URI, GenconfPackage.eINSTANCE);
            created.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());

        }
        return created;
    }

    /**
     * Post generation.
     * 
     * @param generation
     *            Generation
     * @param templateFile
     *            File
     * @param generatedFile
     *            File
     * @param template
     *            DocumentTemplate
     * @param monitor
     *            used to track the progress will generating.
     * @return File list to return after the generation. Generation result and validation log are already in there.
     */
    public List<URI> postGenerate(Generation generation, URI templateFile, URI generatedFile, DocumentTemplate template,
            Monitor monitor) {
        List<URI> files = Lists.newArrayList();
        List<IConfigurationProvider> providers = ConfigurationProviderService.getInstance().getProviders();
        monitor.beginTask("Launching post-generation tasks.", providers.size());
        for (IConfigurationProvider configurationProvider : providers) {
            if (!monitor.isCanceled()) {
                List<URI> postGenerateFiles = configurationProvider.postGenerate(generation, templateFile,
                        generatedFile, template);
                monitor.worked(1);
                if (postGenerateFiles != null) {
                    files.addAll(postGenerateFiles);
                }
            }
        }
        monitor.done();
        return files;

    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateFile
     *            File
     * @return configuration model
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     */
    public Resource createConfigurationModel(URI templateFile) throws IOException {
        Resource resource = null;
        TemplateCustomProperties templateProperties = POIServices.getInstance().getTemplateInformations(templateFile);

        // create genconf model
        if (templateProperties != null) {
            resource = createConfigurationModel(templateProperties, templateFile);
        }
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
    public Resource createConfigurationModel(TemplateCustomProperties templateProperties, final URI templateURI) {
        // pre model creation: by default nothing.
        preCreateConfigurationModel(templateProperties, templateURI);

        // create genconf resource.
        URI genConfURI = templateURI.trimFileExtension()
                .appendFileExtension(ConfigurationServices.GENCONF_EXTENSION_FILE);
        Resource resource = M2DocUtils.createResource(templateURI, genConfURI);

        // create initial model content.
        Generation rootObject = configurationServices.createInitialModel(genConfURI.trimFileExtension().lastSegment(),
                templateURI.deresolve(genConfURI).toString());
        // add docx properties
        TemplateConfigurationServices.getInstance().addProperties(rootObject, templateProperties);
        if (rootObject != null) {
            resource.getContents().add(rootObject);
        }
        // Save the contents of the resource to the file system.
        try {
            M2DocUtils.saveResource(resource);
        } catch (IOException e) {
            GenconfPlugin.INSTANCE
                    .log(new Status(Status.ERROR, GenconfPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
        }

        // post model creation: by default nothing.
        postCreateConfigurationModel(templateProperties, templateURI, rootObject);

        // Save the contents of the resource to the file system.
        try {
            M2DocUtils.saveResource(resource);
        } catch (IOException e) {
            GenconfPlugin.INSTANCE
                    .log(new Status(Status.ERROR, GenconfPlugin.PLUGIN_ID, Status.ERROR, e.getMessage(), e));
        }
        return resource;
    }

    /**
     * Post configuration model creation.
     * 
     * @param templateProperties
     *            TemplateInfo
     * @param templateFile
     *            File
     * @param generation
     *            Generation
     */
    public void postCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateFile,
            Generation generation) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.postCreateConfigurationModel(templateProperties, templateFile, generation);
        }
    }

    /**
     * Pre configuration model creation.
     * 
     * @param templateProperties
     *            TemplateInfo
     * @param templateFile
     *            File
     */
    public void preCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateFile) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.preCreateConfigurationModel(templateProperties, templateFile);
        }
    }

    /**
     * Validate templateInfo information.
     * 
     * @param generation
     *            Generation
     * @return if template contains errors.
     * @throws IOException
     *             IOException
     * @throws DocumentParserException
     *             DocumentParserException
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     */
    public boolean validate(Generation generation)
            throws IOException, DocumentParserException, DocumentGenerationException {
        final boolean res;

        // get the template path and parses it.
        String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        URI generationURI = generation.eResource().getURI();

        // get template and result file
        URI templateURI = URI.createFileURI(generation.getTemplateFileName()).resolve(generationURI);
        if (!URIConverter.INSTANCE.exists(templateURI, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template file does not exist " + templateFilePath);
        }
        // get acceleo environment
        IQueryEnvironment queryEnvironment = configurationServices.initAcceleoEnvironment(generation);

        // parse template
        try (DocumentTemplate template = M2DocUtils.parse(templateURI, queryEnvironment,
                this.getClass().getClassLoader())) {

            // validate template
            if (template != null) {
                res = validate(templateURI, template, generation);
            } else {
                res = true;
            }
        }

        return res;
    }

    /**
     * Validate template with templateInfo information.
     * 
     * @param templateFile
     *            File
     * @param documentTemplate
     *            DocumentTemplate
     * @param generation
     *            Generation
     * @return if template contains errors/warnings/info
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws IOException
     *             IOException
     */
    public boolean validate(URI templateFile, DocumentTemplate documentTemplate, Generation generation)
            throws DocumentGenerationException, IOException {
        final IQueryEnvironment queryEnvironment = configurationServices.initAcceleoEnvironment(generation);

        return validate(templateFile, documentTemplate, generation, queryEnvironment);
    }

    /**
     * Post template validation.
     * 
     * @param templateFile
     *            File
     * @param template
     *            DocumentTemplate
     * @param generation
     *            Generation
     * @return validation result.
     */
    public boolean postValidateTemplate(URI templateFile, DocumentTemplate template, Generation generation) {
        boolean validate = true;
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            validate = validate && configurationProvider.postValidateTemplate(templateFile, template, generation);
        }
        return validate;
    }

    /**
     * Pre template validation.
     * 
     * @param templateFile
     *            File
     * @param template
     *            DocumentTemplate
     * @param generation
     *            Generation
     */
    public void preValidateTemplate(URI templateFile, DocumentTemplate template, Generation generation) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.preValidateTemplate(templateFile, template, generation);
        }
    }

    /**
     * Validate template with templateInfo information.
     * 
     * @param templateFile
     *            File
     * @param documentTemplate
     *            DocumentTemplate
     * @param generation
     *            Generation
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @return if template contains errors
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws IOException
     *             IOException
     */
    public boolean validate(URI templateFile, DocumentTemplate documentTemplate, Generation generation,
            IReadOnlyQueryEnvironment queryEnvironment) throws DocumentGenerationException, IOException {
        URI validationFile = getValidationLogFile(templateFile);

        final ValidationMessageLevel validationResult = M2DocUtils.validate(documentTemplate, queryEnvironment);
        M2DocUtils.serializeValidatedDocumentTemplate(documentTemplate, validationFile);

        return validationResult == ValidationMessageLevel.ERROR
            && postValidateTemplate(templateFile, documentTemplate, generation);
    }

    /**
     * return validation log file.
     * 
     * @param file
     *            File
     * @return validation log file.
     */
    public URI getValidationLogFile(URI file) {
        URI validationFile = file.trimSegments(1)
                .appendSegment(Files.getNameWithoutExtension(file.lastSegment()) + "-error")
                .appendFileExtension(file.fileExtension());
        return validationFile;
    }
}
