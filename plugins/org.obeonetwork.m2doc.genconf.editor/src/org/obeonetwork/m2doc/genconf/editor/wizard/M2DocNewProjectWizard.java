/*******************************************************************************
 *  Copyright (c) 2019, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.wizard;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hpsf.IllegalPropertySetDataException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.presentation.M2docconfEditorPlugin;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.ide.ui.wizard.M2DocMainVariablePage;
import org.obeonetwork.m2doc.ide.ui.wizard.M2DocNewTemplatePage;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

/**
 * The M2Doc new project wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocNewProjectWizard extends Wizard implements INewWizard {

    /**
     * The finish {@link WorkspaceJob}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    private final class FinishJob extends WorkspaceJob {

        /**
         * The variable value.
         */
        private final EObject variableValue;

        /**
         * The variable name.
         */
        private final String variableName;

        /**
         * Tells if we should launch the generation.
         */
        private final boolean launchGeneration;

        /**
         * The project name.
         */
        private final String projectName;

        /**
         * The template name.
         */
        private final String templateName;

        /**
         * The genconf {@link URI}.
         */
        private final URI genconfURI;

        /**
         * The result {@link URI}.
         */
        private final URI destinationURI;

        /**
         * The validation {@link URI}.
         */
        private final URI validationURI;

        /**
         * Constructor.
         * 
         * @param name
         *            the job name
         * @param variableValue
         *            the variable value
         * @param variableName
         *            the variable name
         * @param launchGeneration
         *            tells if we should launch the generation
         * @param projectName
         *            the project name
         * @param templateName
         *            the template name
         * @param genconfURI
         *            the genconf {@link URI}
         * @param destinationURI
         *            the result {@link URI}
         * @param validationURI
         *            the validation {@link URI}
         */
        private FinishJob(String name, EObject variableValue, String variableName, boolean launchGeneration,
                String projectName, String templateName, URI genconfURI, URI destinationURI, URI validationURI) {
            super(name);
            this.variableValue = variableValue;
            this.variableName = variableName;
            this.launchGeneration = launchGeneration;
            this.projectName = projectName;
            this.templateName = templateName;
            this.genconfURI = genconfURI;
            this.destinationURI = destinationURI;
            this.validationURI = validationURI;
        }

        @Override
        public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
            Status status = new Status(IStatus.OK, M2docconfEditorPlugin.getPlugin().getSymbolicName(),
                    "M2Doc project " + projectName + " created succesfully.");

            final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
            project.create(monitor);
            project.open(monitor);
            try {
                final URI templateURI = createSampleTemplate(templateName, variableName, variableValue, monitor,
                        project);
                final Generation generation = createGenconf(genconfURI, destinationURI, validationURI, templateURI,
                        variableName, variableValue);
                if (launchGeneration) {
                    GenconfUtils.generate(generation, M2DocPlugin.getClassProvider(), BasicMonitor.toMonitor(monitor));
                }
            } catch (IOException | CoreException | DocumentGenerationException | DocumentParserException
                    | InvalidFormatException e) {
                status = new Status(IStatus.ERROR, M2docconfEditorPlugin.getPlugin().getSymbolicName(),
                        "M2Doc project " + projectName + " creation failed.", e);
            }

            return status;
        }
    }

    /**
     * The {@link WizardNewProjectCreationPage}.
     */
    private WizardNewProjectCreationPage newProjectPage;

    /**
     * The {@link M2DocNewTemplatePage}.
     */
    private M2DocNewTemplatePage newTemplatePage;

    /**
     * The {@link M2DocMainVariablePage}.
     */
    private M2DocMainVariablePage variablePage;

    /**
     * The {@link M2DocGenerationPage}.
     */
    private M2DocGenerationPage generationPage;

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        // TODO Auto-generated method stub
    }

    @Override
    public void addPages() {
        newProjectPage = new WizardNewProjectCreationPage("basicNewProjectPage");
        addPage(newProjectPage);
        newTemplatePage = new M2DocNewTemplatePage(newProjectPage);
        addPage(newTemplatePage);
        variablePage = new M2DocMainVariablePage();
        addPage(variablePage);
        generationPage = new M2DocGenerationPage(newTemplatePage, variablePage);
        addPage(generationPage);
    }

    @Override
    public boolean performFinish() {
        final String projectName = newProjectPage.getProjectName();
        final String templateName = newTemplatePage.getTemplateName();
        final String variableName = variablePage.getVariableName();
        final EObject variableValue = variablePage.getVariableValue();
        final URI genconfURI = URI.createPlatformResourceURI(generationPage.getGenerationName(), true);
        final URI destinationURI = URI.createPlatformResourceURI(generationPage.getDestinationName(), true);
        final URI validationURI = URI.createPlatformResourceURI(generationPage.getValidationName(), true);
        final boolean launchGeneration = generationPage.getLaunchGeneration();

        final Job job = new FinishJob("Creating M2Doc project: " + projectName, variableValue, variableName,
                launchGeneration, projectName, templateName, genconfURI, destinationURI, validationURI);
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();

        return true;
    }

    /**
     * Creates the sample template.
     * 
     * @param templateName
     *            the template name
     * @param variableName
     *            the variable name
     * @param variableValue
     *            the variable value
     * @param monitor
     *            the {@link IProgressMonitor}
     * @param project
     *            the {@link IllegalPropertySetDataException}
     * @return
     * @throws IOException
     *             if the template file can't be saved
     * @throws CoreException
     *             if the template file can't be saved
     * @throws InvalidFormatException
     *             if the sample template can't be read
     * @return the template {@link URI}
     */
    private URI createSampleTemplate(final String templateName, final String variableName, final EObject variableValue,
            IProgressMonitor monitor, final IProject project)
            throws IOException, CoreException, InvalidFormatException {
        final URI res;

        final URIConverter uriConverter = new ExtensibleURIConverterImpl();
        final MemoryURIHandler handler = new MemoryURIHandler();
        uriConverter.getURIHandlers().add(0, handler);
        try (XWPFDocument sampleTemplate = M2DocUtils.createSampleTemplate(variableName, variableValue.eClass());) {
            final URI memoryURI = URI
                    .createURI(MemoryURIHandler.PROTOCOL + "://resources/temp." + M2DocUtils.DOCX_EXTENSION_FILE);
            POIServices.getInstance().saveFile(uriConverter, sampleTemplate, memoryURI);

            try (InputStream source = uriConverter.createInputStream(memoryURI)) {
                final IFile templateFile = project.getFile(templateName);
                templateFile.create(source, true, monitor);
                res = URI.createPlatformResourceURI(templateFile.getFullPath().toString(), true);
            }
        }

        return res;
    }

    /**
     * Creates the {@link Generation}.
     * 
     * @param genconfURI
     *            the {@link Generation} {@link URI}
     * @param destinationURI
     *            the result {@link URI}
     * @param validationURI
     *            the validation {@link URI}
     * @param templateURI
     *            the template {@link URI}
     * @param variableName
     *            the variable name
     * @param variableValue
     *            the variable value
     * @return the created {@link Generation}
     * @throws IOException
     *             if the generation resource can't saved
     */
    private Generation createGenconf(URI genconfURI, URI destinationURI, URI validationURI, URI templateURI,
            String variableName, EObject variableValue) throws IOException {
        final Generation res;

        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource;
        if (resourceSet.getURIConverter().exists(genconfURI, null)) {
            resource = resourceSet.getResource(genconfURI, true);
        } else {
            resource = resourceSet.createResource(URI.createURI(URI.decode(genconfURI.toString())));
        }
        res = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        resource.getContents().clear();
        resource.getContents().add(res);
        res.setTemplateFileName(URI.decode(templateURI.deresolve(genconfURI).toString()));
        res.setResultFileName(URI.decode(destinationURI.deresolve(genconfURI).toString()));
        res.setValidationFileName(URI.decode(validationURI.deresolve(genconfURI).toString()));

        final ModelDefinition variableDefinition = GenconfPackage.eINSTANCE.getGenconfFactory().createModelDefinition();
        res.getDefinitions().add(variableDefinition);
        variableDefinition.setKey(variableName);
        variableDefinition.setValue(resourceSet.getEObject(EcoreUtil.getURI(variableValue), true));

        GenconfUtils.initializeOptions(res);

        resource.save(null);

        return res;
    }

}
