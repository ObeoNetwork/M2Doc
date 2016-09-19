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
package org.obeonetwork.m2doc.sirius.providers.configuration;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.generator.TemplateGenerator;
import org.obeonetwork.m2doc.properties.TemplateInfo;
import org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Configuration provider for Sirius.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class SiriusConfigurationProvider implements IConfigurationProvider {

    /**
     * {@inheritDoc}
     * 
     * @see
     *      org.obeonetwork.m2doc.provider.configuration.IProviderConfiguration#postCreateConfigurationModel(org.obeonetwork.m2doc.properties.
     *      TemplateInfo, org.eclipse.core.resources.IFile, org.obeonetwork.m2doc.genconf.Generation)
     */
    @Override
    public void postCreateConfigurationModel(TemplateInfo templateInfo, IFile templateFile, Generation generation) {
        // add generation resource in session
        URI uri = generation.eResource().getURI();
        IProject project = templateFile.getProject();
        ModelingProject.hasModelingProjectNature(project);
        Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(project);
        if (optionalModelingProject.some()) {
            ModelingProject modelingProject = optionalModelingProject.get();
            Session session = modelingProject.getSession();
            if (session != null) {
                // add semantic resources
                AddSemanticResourceCommand cmd = new AddSemanticResourceCommand(session, uri,
                        new NullProgressMonitor());
                session.getTransactionalEditingDomain().getCommandStack().execute(cmd);

                // add semantic resource URI to generation uris packages.
                List<String> uris = Lists.newArrayList();
                for (Resource resource : session.getSemanticResources()) {
                    if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof EObject
                        && resource.getContents().get(0).eClass() != null
                        && resource.getContents().get(0).eClass().getEPackage() != null
                        && !Strings.isNullOrEmpty(resource.getContents().get(0).eClass().getEPackage().getNsURI())) {
                        uris.add(resource.getContents().get(0).eClass().getEPackage().getNsURI());
                    }
                }
                if (!uris.isEmpty()) {
                    generation.getPackagesNSURI().addAll(uris);
                }
            }
        }

    }

    /**
     * {@inheritDoc}
     * 
     * @see
     *      org.obeonetwork.m2doc.provider.configuration.IProviderConfiguration#preCreateConfigurationModel(org.obeonetwork.m2doc.properties.
     *      TemplateInfo, org.eclipse.core.resources.IFile)
     */
    @Override
    public void preCreateConfigurationModel(TemplateInfo templateInfo, IFile templateFile) {
        // do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider#postValidateTemplate(org.eclipse.core.resources.IFile,
     *      org.obeonetwork.m2doc.template.DocumentTemplate, org.obeonetwork.m2doc.genconf.Generation,
     *      org.obeonetwork.m2doc.generator.TemplateGenerator)
     */
    @Override
    public boolean postValidateTemplate(IFile templateFile, DocumentTemplate template, Generation generation,
            TemplateGenerator generator) {
        // do nothing.
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider#preValidateTemplate(org.eclipse.core.resources.IFile,
     *      org.obeonetwork.m2doc.template.DocumentTemplate, org.obeonetwork.m2doc.genconf.Generation)
     */
    @Override
    public void preValidateTemplate(IFile templateFile, DocumentTemplate template, Generation generation) {
        // do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider#preGenerate(org.obeonetwork.m2doc.genconf.Generation,
     *      org.eclipse.core.resources.IProject, org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile)
     */
    @Override
    public void preGenerate(Generation generation, IProject project, IFile templateFile, IFile generatedFile) {
        // do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.provider.configuration.IConfigurationProvider#postGenerate(org.obeonetwork.m2doc.genconf.Generation,
     *      org.eclipse.core.resources.IProject, org.eclipse.core.resources.IFile, org.eclipse.core.resources.IFile,
     *      org.obeonetwork.m2doc.template.DocumentTemplate, org.obeonetwork.m2doc.generator.DocumentGenerator)
     */
    @Override
    public List<IFile> postGenerate(Generation generation, IProject project, IFile templateFile, IFile generatedFile,
            DocumentTemplate template, DocumentGenerator generator) {
        // do nothing.
        return Lists.newArrayList();
    }

}
