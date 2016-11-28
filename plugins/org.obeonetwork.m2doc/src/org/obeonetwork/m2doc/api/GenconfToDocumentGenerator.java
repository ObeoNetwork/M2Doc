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

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.generator.TemplateGenerator;
import org.obeonetwork.m2doc.generator.TemplateValidator;
import org.obeonetwork.m2doc.parser.DocumentParserException;
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
    public List<IFile> generate(Generation generation)
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

        // get project container
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IResource configurationFile = workspace.getRoot()
                .findMember(generation.eResource().getURI().toPlatformString(true));
        IProject project = configurationFile.getProject();

        // get template and result file
        IFile templateFile = project.getFile(new Path(generation.getTemplateFileName()));
        IFile generatedFile = project.getFile(new Path(generation.getResultFileName()));
        if (!templateFile.exists()) {
            throw new DocumentGenerationException("The template file doest not exist " + templateFilePath);
        }

        // generate result file.
        return generate(generation, project, templateFile, generatedFile);
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
     * @param project
     *            IProject
     * @return generated file and validation file if exists
     * @throws IOException
     *             if an I/O problem occurs
     * @throws DocumentParserException
     *             if the document coulnd'nt be parsed.
     * @throws DocumentGenerationException
     *             if the document couldn't be generated
     */
    public List<IFile> generate(Generation generation, IProject project, IFile templateFile, IFile generatedFile)
            throws IOException, DocumentParserException, DocumentGenerationException {
        // pre generation
        preGenerate(generation, project, templateFile, generatedFile);

        IQueryEnvironment queryEnvironment = QueryServices.getInstance().initAcceleoEnvironment(generation);

        // create definitions
        ConfigurationServices configurationServices = new ConfigurationServices();
        Map<String, Object> definitions = configurationServices.createDefinitions(generation);

        // create generated file
        DocumentTemplate template = POIServices.getInstance().parseTemplate(templateFile, queryEnvironment);

        // validate template
        boolean inError = validate(generatedFile, template, generation);

        // launch generation
        DocumentGenerator generator = new DocumentGenerator(project.getLocation().toOSString(),
                templateFile.getLocation().toFile().getAbsolutePath(),
                generatedFile.getLocation().toFile().getAbsolutePath(), template, definitions, queryEnvironment,
                generation);
        generator.generate();

        List<IFile> generatedFiles = Lists.newArrayList(generatedFile);
        if (inError) {
            IFile validationFile = getValidationLogFile(generatedFile);
            generatedFiles.add(validationFile);
        }

        // post generation
        generatedFiles.addAll(postGenerate(generation, project, templateFile, generatedFile, template, generator));

        return generatedFiles;
    }

    /**
     * Pre generation.
     * 
     * @param generation
     *            Generation
     * @param project
     *            IProject
     * @param templateFile
     *            IFile
     * @param generatedFile
     *            IFile
     */
    public void preGenerate(Generation generation, IProject project, IFile templateFile, IFile generatedFile) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.preGenerate(generation, project, templateFile, generatedFile);
        }
    }

    /**
     * Post generation.
     * 
     * @param generation
     *            Generation
     * @param project
     *            IProject
     * @param templateFile
     *            IFile
     * @param generatedFile
     *            IFile
     * @param template
     *            DocumentTemplate
     * @param generator
     *            DocumentGenerator
     * @return IFile list to return after the generation. Generation result and validation log are already in there.
     */
    public List<IFile> postGenerate(Generation generation, IProject project, IFile templateFile, IFile generatedFile,
            DocumentTemplate template, DocumentGenerator generator) {
        List<IFile> files = Lists.newArrayList();
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            List<IFile> postGenerateFiles = configurationProvider.postGenerate(generation, project, templateFile,
                    generatedFile, template, generator);
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
     *            IFile
     * @return configuration model
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     */
    public Resource createConfigurationModel(IFile templateFile) throws IOException {
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
     * @param templateFile
     *            IFile
     * @return configuration model
     */
    public Resource createConfigurationModel(TemplateInfo templateInfo, final IFile templateFile) {
        // pre model creation: by default nothing.
        preCreateConfigurationModel(templateInfo, templateFile);

        // create genconf resource.
        URI templateURI = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);
        URI genConfURI = templateURI.trimFileExtension()
                .appendFileExtension(ConfigurationServices.GENCONF_EXTENSION_FILE);
        Resource resource = M2DocUtils.createResource(templateFile, genConfURI);

        // create initial model content.
        ConfigurationServices configurationServices = new ConfigurationServices();
        Generation rootObject = configurationServices.createInitialModel(genConfURI.trimFileExtension().lastSegment(),
                templateFile.getProjectRelativePath().toString());
        // add docx properties
        TemplateConfigurationServices.getInstance().addProperties(rootObject, templateInfo);
        if (rootObject != null) {
            resource.getContents().add(rootObject);
        }
        // Save the contents of the resource to the file system.
        M2DocUtils.saveResource(resource);

        // post model creation: by default nothing.
        postCreateConfigurationModel(templateInfo, templateFile, rootObject);

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
     *            IFile
     * @param generation
     *            Generation
     */
    public void postCreateConfigurationModel(TemplateInfo templateInfo, IFile templateFile, Generation generation) {
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
     *            IFile
     */
    public void preCreateConfigurationModel(TemplateInfo templateInfo, IFile templateFile) {
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
        // get the template path and parses it.
        String templateFilePath = generation.getTemplateFileName();
        if (templateFilePath == null) {
            throw new DocumentGenerationException("The template file path isn't set in the provided configuration");
        }

        // get project container
        IResource configurationFile = ResourcesPlugin.getWorkspace().getRoot()
                .findMember(generation.eResource().getURI().toPlatformString(true));
        IProject project = configurationFile.getProject();

        // get template and result file
        IFile templateFile = project.getFile(new Path(generation.getTemplateFileName()));
        if (!templateFile.exists()) {
            throw new DocumentGenerationException("The template file doest not exist " + templateFilePath);
        }
        // get acceleo environment
        IQueryEnvironment queryEnvironment = QueryServices.getInstance().initAcceleoEnvironment(generation);

        // parse template
        DocumentTemplate template = POIServices.getInstance().parseTemplate(templateFile, queryEnvironment);

        // validate template
        if (template != null) {
            return validate(templateFile, template, generation);
        }
        return true;
    }

    /**
     * Validate template with templateInfo information.
     * 
     * @param templateFile
     *            IFile
     * @param template
     *            DocumentTemplate
     * @param generation
     *            Generation
     * @return if template contains errors
     * @throws DocumentGenerationException
     *             DocumentGenerationException
     * @throws IOException
     *             IOException
     */
    public boolean validate(IFile templateFile, DocumentTemplate template, Generation generation)
            throws DocumentGenerationException, IOException {
        // pre template validation
        preValidateTemplate(templateFile, template, generation);
        IFile validationFile = getValidationLogFile(templateFile);

        final TemplateValidator validator = new TemplateValidator();

        validator.validate(template, generation);
        TemplateGenerator generator = new TemplateGenerator(validationFile.getLocation().toFile().getAbsolutePath(),
                template);
        boolean validationResult = generator.generate();
        return validationResult && postValidateTemplate(templateFile, template, generation, generator);
    }

    /**
     * Post template validation.
     * 
     * @param templateFile
     *            IFile
     * @param template
     *            DocumentTemplate
     * @param generation
     *            Generation
     * @param generator
     *            TemplateGenerator
     * @return validation result.
     */
    public boolean postValidateTemplate(IFile templateFile, DocumentTemplate template, Generation generation,
            TemplateGenerator generator) {
        boolean validate = true;
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            validate = validate
                && configurationProvider.postValidateTemplate(templateFile, template, generation, generator);
        }
        return validate;
    }

    /**
     * Pre template validation.
     * 
     * @param templateFile
     *            IFile
     * @param template
     *            DocumentTemplate
     * @param generation
     *            Generation
     */
    public void preValidateTemplate(IFile templateFile, DocumentTemplate template, Generation generation) {
        for (IConfigurationProvider configurationProvider : ConfigurationProviderService.getInstance().getProviders()) {
            configurationProvider.preValidateTemplate(templateFile, template, generation);
        }
    }

    /**
     * Validate template with templateInfo information.
     * 
     * @param templateFile
     *            IFile
     * @param template
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
    public boolean validate(IFile templateFile, DocumentTemplate template, Generation generation,
            IReadOnlyQueryEnvironment queryEnvironment) throws DocumentGenerationException, IOException {
        IFile validationFile = getValidationLogFile(templateFile);

        final TemplateValidator validator = new TemplateValidator();
        Map<String, Set<IType>> types = QueryServices.getInstance().getTypes(queryEnvironment, generation);
        validator.validate(template, generation, queryEnvironment, types);
        TemplateGenerator generator = new TemplateGenerator(validationFile.getLocation().toFile().getAbsolutePath(),
                template);
        return generator.generate();
    }

    /**
     * return validation log file.
     * 
     * @param file
     *            IFile
     * @return validation log file.
     */
    public IFile getValidationLogFile(IFile file) {
        String validationPath = file.getProjectRelativePath().removeFileExtension().toString() + "-error."
            + file.getFileExtension();
        IFile validationFile = file.getProject().getFile(new Path(validationPath));
        return validationFile;
    }
}
