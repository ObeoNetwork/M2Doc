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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.obeonetwork.m2doc.M2DocPlugin;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.generator.DocumentValidatedGenerator;
import org.obeonetwork.m2doc.parser.DocumentParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.services.M2DocServices;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * This class can be used to generate documents from {@link Generation} configuration elements.
 * 
 * @author Romain Guider
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
        String validationPath = generatedFile.getProjectRelativePath().removeFileExtension().toString() + "-error."
            + generatedFile.getFileExtension();
        IFile validationFile = project.getFile(new Path(validationPath));
        if (!templateFile.exists()) {
            throw new DocumentGenerationException("The template file doest not exist " + templateFilePath);
        }

        // generate result file.
        return generate(generation, project, templateFile, generatedFile, validationFile);
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
     * @param validationFile
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
    public List<IFile> generate(Generation generation, IProject project, IFile templateFile, IFile generatedFile,
            IFile validationFile) throws IOException, DocumentParserException, DocumentGenerationException {
        // get acceleo environment
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        M2DocUtils.registerServices(queryEnvironment);

        // register packages
        for (String nsURI : generation.getPackagesNSURI()) {
            EPackage p = EPackage.Registry.INSTANCE.getEPackage(nsURI);
            if (p == null) {
                M2DocPlugin.getDefault().getLog().log(
                        new Status(Status.WARNING, M2DocPlugin.PLUGIN_ID, "Couldn't find package with nsURI " + nsURI));
            } else {
                queryEnvironment.registerEPackage(p);
            }
        }

        // create definitions
        ConfigurationServices configurationServices = new ConfigurationServices();
        Map<String, Object> definitions = configurationServices.createDefinitions(generation);

        // create generated file
        FileInputStream is = new FileInputStream(templateFile.getLocation().toFile());
        OPCPackage oPackage;
        try {
            oPackage = OPCPackage.open(is);
        } catch (InvalidFormatException e) {
            throw new IllegalArgumentException("Couldn't open template", e);
        }
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();

        // validate template
        DocumentValidatedGenerator validator = new DocumentValidatedGenerator(
                validationFile.getLocation().toFile().getAbsolutePath(), template);
        boolean inError = validator.generate();

        // lauch generation
        DocumentGenerator generator = new DocumentGenerator(project.getFullPath().toString(),
                templateFile.getLocation().toFile().getAbsolutePath(),
                generatedFile.getLocation().toFile().getAbsolutePath(), template, definitions, queryEnvironment,
                generation);
        generator.generate();

        List<IFile> generatedFiles = Lists.newArrayList(generatedFile);
        if (inError) {
            generatedFiles.add(validationFile);
        }
        return generatedFiles;
    }

    /**
     * Create genconf model from templateInfo information.
     * 
     * @param templateFile
     *            IFile
     * @return configuration model
     * @throws FileNotFoundException
     *             FileNotFoundException
     * @throws InvalidFormatException
     *             InvalidFormatException
     * @throws IOException
     *             IOException
     */
    public Resource createConfigurationModel(IFile templateFile)
            throws FileNotFoundException, InvalidFormatException, IOException {
        Resource resource = null;
        FileInputStream is = new FileInputStream(templateFile.getLocation().toString());
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        TemplateInfo templateInfo = new TemplateInfo(document);

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
        M2DocServices m2DocServices = new M2DocServices();
        m2DocServices.addProperties(rootObject, templateInfo);
        if (rootObject != null) {
            resource.getContents().add(rootObject);
        }

        // Save the contents of the resource to the file system.
        M2DocUtils.saveResource(resource);
        return resource;
    }

}
