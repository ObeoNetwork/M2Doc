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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.sirius.M2DocSiriusUtils;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Configuration provider for Sirius.
 * Automatically add session registered EPackage URIs in configuration model.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
@SuppressWarnings("restriction")
public class SiriusConfigurationProvider implements IConfigurationProvider {

    /**
     * {@inheritDoc}
     * 
     * @see
     *      org.obeonetwork.m2doc.provider.configuration.IProviderConfiguration#postCreateConfigurationModel(org.obeonetwork.m2doc.properties.
     *      TemplateCustomProperties, org.eclipse.core.resources.File, org.obeonetwork.m2doc.genconf.Generation)
     */
    @Override
    public void postCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI,
            Generation generation) {
        // add generation resource in session
        ModelingProject modelingProject = getModelingProject(templateURI);
        if (modelingProject != null) {
            Session session = modelingProject.getSession();
            if (session != null) {
                // add semantic resource to session
                addConfigurationModelToSession(generation, session);
            }
        }
    }

    /**
     * Add generation resource to session.
     * 
     * @param generation
     *            Generation
     * @param session
     *            Session
     */
    protected void addConfigurationModelToSession(Generation generation, Session session) {
        URI uri = generation.eResource().getURI();
        AddSemanticResourceCommand cmd = new AddSemanticResourceCommand(session, uri, new NullProgressMonitor());
        session.getTransactionalEditingDomain().getCommandStack().execute(cmd);
    }

    /**
     * Return template file project if it is a modeling project.
     * 
     * @param templateURI
     *            the template {@link URI}
     * @return modeling project
     */
    protected ModelingProject getModelingProject(URI templateURI) {
        if (templateURI.isPlatformResource()) {
            IResource r = ResourcesPlugin.getWorkspace().getRoot().findMember(templateURI.toPlatformString(true));
            if (r != null) {
                IProject project = r.getProject();
                Option<ModelingProject> optionalModelingProject = ModelingProject.asModelingProject(project);
                if (optionalModelingProject.some()) {
                    return optionalModelingProject.get();
                }
            }
        }
        return null;
    }

    /**
     * Get ePackage nsuri from all session resources.
     * 
     * @param session
     *            Session
     * @return uris list
     */
    protected List<String> addURIs(Session session) {
        List<String> uris = Lists.newArrayList();
        for (Resource resource : session.getSemanticResources()) {
            if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof EObject
                && resource.getContents().get(0).eClass() != null
                && resource.getContents().get(0).eClass().getEPackage() != null
                && !Strings.isNullOrEmpty(resource.getContents().get(0).eClass().getEPackage().getNsURI())) {
                String nsURI = resource.getContents().get(0).eClass().getEPackage().getNsURI();
                if (!uris.contains(nsURI)) {
                    uris.add(nsURI);
                }
            }
        }
        return uris;
    }

    /**
     * {@inheritDoc}
     * 
     * @see
     *      org.obeonetwork.m2doc.provider.configuration.IProviderConfiguration#preCreateConfigurationModel(org.obeonetwork.m2doc.properties.
     *      TemplateCustomProperties, org.eclipse.core.resources.File)
     */
    @Override
    public void preCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI) {
        // do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider#postValidateTemplate(org.eclipse.core.resources.File,
     *      org.obeonetwork.m2doc.template.DocumentTemplate, org.obeonetwork.m2doc.genconf.Generation)
     */
    @Override
    public boolean postValidateTemplate(URI templateURI, DocumentTemplate template, Generation generation) {
        // do nothing.
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider#preValidateTemplate(org.eclipse.core.resources.File,
     *      org.obeonetwork.m2doc.template.DocumentTemplate, org.obeonetwork.m2doc.genconf.Generation)
     */
    @Override
    public void preValidateTemplate(URI templateURI, DocumentTemplate template, Generation generation) {
        // do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider#preGenerate(org.obeonetwork.m2doc.genconf.Generation,
     *      org.eclipse.core.resources.IProject, org.eclipse.core.resources.File, org.eclipse.core.resources.File)
     */
    @Override
    public void preGenerate(Generation generation, URI templateURI, URI generatedURI) {
        // do nothing.
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider#postGenerate(org.obeonetwork.m2doc.genconf.Generation,
     *      org.eclipse.core.resources.IProject, org.eclipse.core.resources.File, org.eclipse.core.resources.File,
     *      org.obeonetwork.m2doc.template.DocumentTemplate)
     */
    @Override
    public List<URI> postGenerate(Generation generation, URI templateURI, URI generatedURI, DocumentTemplate template) {
        // do nothing.
        return Lists.newArrayList();
    }

    @Override
    public ResourceSet createResourceSetForModels(Generation generation) {
        ResourceSet created = null;
        final Map<String, String> options = GenconfUtils.getOptions(generation);
        final String representationsFileName = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (representationsFileName != null && representationsFileName.endsWith("aird")) {
            final URI sessionURI = GenconfUtils.createURIStartingFromCurrentModel(generation, representationsFileName);
            if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final Session s = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
                s.open(new NullProgressMonitor());
                created = s.getTransactionalEditingDomain().getResourceSet();
                created.eAdapters().add(new SessionTransientAttachment(s));
            }
        }
        return created;
    }

}
