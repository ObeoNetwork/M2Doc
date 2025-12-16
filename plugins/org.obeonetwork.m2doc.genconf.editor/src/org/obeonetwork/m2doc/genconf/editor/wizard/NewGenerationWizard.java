/*******************************************************************************
 *  Copyright (c) 2018, 2025 Obeo. 
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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.editor.GenerationListener;
import org.obeonetwork.m2doc.genconf.presentation.M2docconfEditorPlugin;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * New generation wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class NewGenerationWizard extends Wizard implements INewWizard {

    /**
     * Default genconf name.
     */
    public static final String DEFAULT_GENCONF_FILE_NAME = "MyGeneration.genconf";

    /**
     * The initial {@link IStructuredSelection}.
     */
    private IStructuredSelection selection = StructuredSelection.EMPTY;

    /**
     * The {@link Generation}.
     */
    private final Generation generation;

    /**
     * The {@link GenerationFileNamesPage}.
     */
    private GenerationFileNamesPage fileNamesPage;
    /**
     * The {@link VariableAndOptionPage}.
     */
    private VariableAndOptionPage optionPage;

    /**
     * The {@link GenerationListener}.
     */
    private GenerationListener generationListener;

    /**
     * Tells if we can change the template file.
     */
    private boolean canChangeTemplateFile = true;

    /**
     * Constructor.
     */
    public NewGenerationWizard() {
        generation = GenconfPackage.eINSTANCE.getGenconfFactory().createGeneration();
        final XMIResourceImpl resource = new XMIResourceImpl();
        resource.setEncoding(StandardCharsets.UTF_8.name());
        final ResourceSet rs = new ResourceSetImpl();
        rs.getResources().add(resource);
        resource.getContents().add(generation);
        TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain(rs);
    }

    @Override
    public void init(IWorkbench workbench, IStructuredSelection selected) {
        selection = selected;
    }

    /**
     * Sets can change template file.
     * 
     * @param canChange
     *            <code>true</code> if change is possible, <code>false</code> otherwise
     */
    public void setCanChangeTemplateFile(boolean canChange) {
        canChangeTemplateFile = canChange;
    }

    @Override
    public void addPages() {
        super.addPages();

        final Generation loadedGeneration = getGeneration(selection);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(generation);
        if (loadedGeneration == null) {
            final URI genconfURI = getGenconfURI(selection);
            if (genconfURI != null) {
                generation.eResource().setURI(genconfURI);
                editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

                    @Override
                    protected void doExecute() {
                        generation.setTemplateFileName(URI.decode(getTemplateFileName(genconfURI).toString()));
                        generation.setValidationFileName(getValidationFileName(genconfURI));
                        generation.setResultFileName(getResultFileName(genconfURI));
                        GenconfUtils.initializeOptions(generation);

                        initializeVariableDefinition(generation);
                    }

                });
            }
        } else {
            generation.eResource().setURI(loadedGeneration.eResource().getURI());
            editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

                @Override
                protected void doExecute() {
                    generation.setName(loadedGeneration.getName());
                    generation.setTemplateFileName(loadedGeneration.getTemplateFileName());
                    generation.setValidationFileName(loadedGeneration.getValidationFileName());
                    generation.setResultFileName(loadedGeneration.getResultFileName());
                    generation.getDefinitions().addAll(loadedGeneration.getDefinitions());
                    generation.getOptions().addAll(loadedGeneration.getOptions());
                    GenconfUtils.initializeOptions(generation);

                    initializeVariableDefinition(generation);
                }

            });
        }

        generationListener = new GenerationListener();
        generationListener.installGenerationListener(generation);

        fileNamesPage = new GenerationFileNamesPage(generation, generationListener, canChangeTemplateFile);
        addPage(fileNamesPage);
        optionPage = new VariableAndOptionPage(generation, generationListener, fileNamesPage);
        addPage(optionPage);
    }

    /**
     * Gets the {@link Generation} from the given {@link IStructuredSelection}.
     * 
     * @param selected
     *            the {@link IStructuredSelection}
     * @return the {@link Generation} from the given {@link IStructuredSelection} if any, <code>null</code> otherwise
     */
    private Generation getGeneration(IStructuredSelection selected) {
        final Generation res;

        if (selected != null && !selected.isEmpty()) {
            final ResourceSet rs = new ResourceSetImpl();
            final IResource container = Platform.getAdapterManager().getAdapter(selection.getFirstElement(),
                    IResource.class);
            final URI genconfURI = URI.createPlatformResourceURI(container.getFullPath().toString(), true);
            if (GenconfUtils.GENCONF_EXTENSION_FILE.equals(genconfURI.fileExtension())) {
                final Resource resource = rs.getResource(genconfURI, true);
                if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof Generation) {
                    res = (Generation) resource.getContents().get(0);
                } else {
                    res = null;
                }
            } else {
                res = null;
            }
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Initializes the {@link Generation#getDefinitions() variable definition} for the given {@link Generation}.
     * 
     * @param gen
     *            the {@link Generation}
     */
    private void initializeVariableDefinition(Generation gen) {
        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        try {
            final TemplateCustomProperties properties = POIServices.getInstance().getTemplateCustomProperties(
                    URIConverter.INSTANCE, URI.createURI(gen.getTemplateFileName()).resolve(gen.eResource().getURI()));
            ((IQueryEnvironment) queryEnvironment).registerEPackage(EcorePackage.eINSTANCE);
            ((IQueryEnvironment) queryEnvironment).registerCustomClassMapping(
                    EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class);
            properties.configureQueryEnvironmentWithResult((IQueryEnvironment) queryEnvironment,
                    EPackage.Registry.INSTANCE);
            final ResourceSetImpl defaultResourceSet = new ResourceSetImpl();
            defaultResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*",
                    new XMIResourceFactoryImpl());
            defaultResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                    .putAll(Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap());

            final ResourceSet resourceSetForModel = AQLUtils.createResourceSetForModels(new ArrayList<Exception>(),
                    queryEnvironment, defaultResourceSet, GenconfUtils.getOptions(gen));
            final List<Definition> newDefinitions = GenconfUtils.getNewDefinitions(gen, EPackage.Registry.INSTANCE,
                    properties);
            gen.getDefinitions().addAll(newDefinitions);
            GenconfUtils.initializeVariableDefinition(gen, queryEnvironment, properties, resourceSetForModel);
            AQLUtils.cleanResourceSetForModels(queryEnvironment, resourceSetForModel);
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            // no initialization if it fails no big deal
        }
    }

    /**
     * Gets the template file name from the given genconf {@link URI}.
     * 
     * @param genconfURI
     *            the template {@link URI}
     * @return the validation file name from the given genconf {@link URI}
     */
    private String getTemplateFileName(URI genconfURI) {
        final String lastSegment = genconfURI.lastSegment();
        return URI.decode(lastSegment.substring(0, lastSegment.length() - GenconfUtils.GENCONF_EXTENSION_FILE.length())
            + M2DocUtils.DOCX_EXTENSION_FILE);
    }

    /**
     * Gets the validation file name from the given genconf {@link URI}.
     * 
     * @param genconfURI
     *            the genconf {@link URI}
     * @return the validation file name from the given genconf {@link URI}
     */
    private String getValidationFileName(URI genconfURI) {
        final String lastSegment = genconfURI.lastSegment();
        return URI.decode(lastSegment.substring(0, lastSegment.length() - GenconfUtils.GENCONF_EXTENSION_FILE.length())
            + "validation." + M2DocUtils.DOCX_EXTENSION_FILE);
    }

    /**
     * Gets the result file name from the given genconf {@link URI}.
     * 
     * @param genconfURI
     *            the genconf {@link URI}
     * @return the result file name from the given genconf {@link URI}
     */
    private String getResultFileName(URI genconfURI) {
        final String lastSegment = genconfURI.lastSegment();
        return URI.decode(lastSegment.substring(0, lastSegment.length() - GenconfUtils.GENCONF_EXTENSION_FILE.length())
            + "generated." + M2DocUtils.DOCX_EXTENSION_FILE);
    }

    @Override
    public void dispose() {
        super.dispose();
        generationListener.removeGenerationListener(generation);
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(generation);
        if (editingDomain != null) {
            editingDomain.dispose();
        }
    }

    /**
     * Gets the genconf {@link URI} from the given selection.
     * 
     * @param selected
     *            the {@link IStructuredSelection}.
     * @return the genconf {@link URI} from the given selection if any, <code>null</code> otherwise
     */
    private URI getGenconfURI(IStructuredSelection selected) {
        final URI res;

        if (selected != null && !selected.isEmpty()) {
            final IResource resource = Platform.getAdapterManager().getAdapter(selection.getFirstElement(),
                    IResource.class);
            if (resource instanceof IFile) {
                final IFile file = (IFile) resource;
                if (M2DocUtils.DOCX_EXTENSION_FILE.equals(file.getFileExtension())) {
                    final String fullPathString = file.getFullPath().toString();
                    String genconfFileString = fullPathString.substring(0,
                            fullPathString.length() - M2DocUtils.DOCX_EXTENSION_FILE.length())
                        + GenconfUtils.GENCONF_EXTENSION_FILE;
                    res = URI.createPlatformResourceURI(genconfFileString, true);
                } else {
                    final String fullPathString = file.getParent().getFullPath().toString();
                    res = URI.createPlatformResourceURI(fullPathString + "/" + DEFAULT_GENCONF_FILE_NAME, true);
                }
            } else if (resource instanceof IContainer) {
                final IContainer container = (IContainer) resource;
                final String fullPathString = container.getFullPath().toString();
                res = URI.createPlatformResourceURI(fullPathString + "/" + DEFAULT_GENCONF_FILE_NAME, true);
            } else {
                res = URI.createPlatformResourceURI("/myproject/" + DEFAULT_GENCONF_FILE_NAME, true);
            }
        } else {
            res = URI.createPlatformResourceURI("/myproject/" + DEFAULT_GENCONF_FILE_NAME, true);
        }

        return res;
    }

    @Override
    public boolean performFinish() {
        final Job job = new WorkspaceJob(
                "Saving generation configuration: " + URI.decode(generation.eResource().getURI().toString())) {

            @Override
            public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
                Status status = new Status(IStatus.OK, M2docconfEditorPlugin.getPlugin().getSymbolicName(),
                        "Generation configuration succesfully saved");

                try {
                    generation.eResource().save(Collections.emptyMap());
                } catch (IOException e) {
                    status = new Status(Status.ERROR, M2docconfEditorPlugin.getPlugin().getSymbolicName(), Status.ERROR,
                            "M2Doc : technical error" + (e.getMessage() == null ? "." : " : " + e.getMessage()), e);
                }
                return status;
            }
        };
        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
        job.schedule();

        return true;
    }

}
