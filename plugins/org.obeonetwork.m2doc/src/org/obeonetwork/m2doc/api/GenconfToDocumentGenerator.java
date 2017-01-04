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
package org.obeonetwork.m2doc.api;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.provider.configuration.ConfigurationProviderService;
import org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider;
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
     */
    public List<URI> generate(Generation generation)
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
        return generate(generation, templateFile, generatedFile);
    }

    private URI createURIStartingFromCurrentModel(Generation generation, String relativePath) {
        URI generationURI = generation.eResource().getURI().trimSegments(1);
        for (String s : Splitter.on(CharMatcher.anyOf("/\\")).split(relativePath)) {
            generationURI = generationURI.appendSegment(s);
        }
        return generationURI;
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
     */
    public List<URI> generate(Generation generation, URI templateFile, URI generatedFile)
            throws IOException, DocumentParserException, DocumentGenerationException {
        // pre generation
        preGenerate(generation, templateFile, generatedFile);

        IQueryEnvironment queryEnvironment = QueryServices.getInstance().initAcceleoEnvironment(generation);

        // create definitions
        ConfigurationServices configurationServices = new ConfigurationServices();
        Map<String, Object> definitions = configurationServices.createDefinitions(generation);

        // create generated file
        try (DocumentTemplate template = M2DocUtils.parse(templateFile, queryEnvironment)) {

            // validate template
            boolean inError = validate(generatedFile, template, generation);

            // launch generation
            DocumentGenerator generator = new DocumentGenerator(templateFile, generatedFile, template, definitions,
                    queryEnvironment, generation);
            generator.generate();

            List<URI> generatedFiles = Lists.newArrayList(generatedFile);
            if (inError) {
                URI validationFile = getValidationLogFile(generatedFile);
                generatedFiles.add(validationFile);
            }

            // post generation
            generatedFiles.addAll(postGenerate(generation, templateFile, generatedFile, template, generator));
            template.close();

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
     */
    public void preGenerate(Generation generation, URI templateFile, URI generatedFile) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.preGenerate(generation, templateFile, generatedFile);
        }
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
     * @param generator
     *            DocumentGenerator
     * @return File list to return after the generation. Generation result and validation log are already in there.
     */
    public List<URI> postGenerate(Generation generation, URI templateFile, URI generatedFile, DocumentTemplate template,
            DocumentGenerator generator) {
        List<URI> files = Lists.newArrayList();
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            List<URI> postGenerateFiles = configurationProvider.postGenerate(generation, templateFile, generatedFile,
                    template, generator);
            if (postGenerateFiles != null) {
                files.addAll(postGenerateFiles);
            }
        }
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
        TemplateInfo templateInfo = POIServices.getInstance().getTemplateInformations(templateFile);

        // create genconf model
        if (templateInfo != null) {
            resource = createConfigurationModel(templateInfo, templateFile);
        }
        return resource;
    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateInfo
     *            TemplateInfo
     * @param templateURI
     *            File
     * @return configuration model
     */
    public Resource createConfigurationModel(TemplateInfo templateInfo, final URI templateURI) {
        // pre model creation: by default nothing.
        preCreateConfigurationModel(templateInfo, templateURI);

        // create genconf resource.
        URI genConfURI = templateURI.trimFileExtension()
                .appendFileExtension(ConfigurationServices.GENCONF_EXTENSION_FILE);
        Resource resource = M2DocUtils.createResource(templateURI, genConfURI);

        // create initial model content.
        ConfigurationServices configurationServices = new ConfigurationServices();
        Generation rootObject = configurationServices.createInitialModel(genConfURI.trimFileExtension().lastSegment(),
                templateURI.deresolve(genConfURI).toString());
        // add docx properties
        TemplateConfigurationServices.getInstance().addProperties(rootObject, templateInfo);
        if (rootObject != null) {
            resource.getContents().add(rootObject);
        }
        // Save the contents of the resource to the file system.
        M2DocUtils.saveResource(resource);

        // post model creation: by default nothing.
        postCreateConfigurationModel(templateInfo, templateURI, rootObject);

        // Save the contents of the resource to the file system.
        M2DocUtils.saveResource(resource);
        return resource;
    }

    /**
     * Post configuration model creation.
     * 
     * @param templateInfo
     *            TemplateInfo
     * @param templateFile
     *            File
     * @param generation
     *            Generation
     */
    public void postCreateConfigurationModel(TemplateInfo templateInfo, URI templateFile, Generation generation) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.postCreateConfigurationModel(templateInfo, templateFile, generation);
        }
    }

    /**
     * Pre configuration model creation.
     * 
     * @param templateInfo
     *            TemplateInfo
     * @param templateFile
     *            File
     */
    public void preCreateConfigurationModel(TemplateInfo templateInfo, URI templateFile) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.preCreateConfigurationModel(templateInfo, templateFile);
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
        URI templateFile = generationURI.trimSegments(1).appendSegment(generation.getTemplateFileName());
        if (!URIConverter.INSTANCE.exists(templateFile, Collections.EMPTY_MAP)) {
            throw new DocumentGenerationException("The template file doest not exist " + templateFilePath);
        }
        // get acceleo environment
        IQueryEnvironment queryEnvironment = QueryServices.getInstance().initAcceleoEnvironment(generation);

        // parse template
        try (DocumentTemplate template = M2DocUtils.parse(templateFile, queryEnvironment)) {

            // validate template
            if (template != null) {
                res = validate(templateFile, template, generation);
            } else {
                res = true;
            }
            template.close();
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
        final IQueryEnvironment queryEnvironment = QueryServices.getInstance().initAcceleoEnvironment(generation);

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

        Map<String, Set<IType>> types = QueryServices.getInstance().getTypes(queryEnvironment, generation);
        final ValidationMessageLevel validationResult = M2DocUtils.validate(documentTemplate, queryEnvironment, types);
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
