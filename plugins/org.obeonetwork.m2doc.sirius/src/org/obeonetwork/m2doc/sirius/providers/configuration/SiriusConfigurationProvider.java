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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.ui.PlatformUI;
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
     * Mapping of {@link Generation} to {@link Session}.
     */
    private final Map<Generation, Session> sessions = new HashMap<>();

    /**
     * {@link Set} of {@link Session} that need to be closed.
     */
    private final Set<Session> sessionToClose = new HashSet<>();

    /**
     * Mapping of {@link Session} to {@link SessionTransientAttachment}.
     */
    private final Map<Session, SessionTransientAttachment> trasiantAttachments = new HashMap<>();

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
        List<String> uris = new ArrayList<>();
        for (Resource resource : session.getSemanticResources()) {
            if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof EObject
                && resource.getContents().get(0).eClass() != null
                && resource.getContents().get(0).eClass().getEPackage() != null
                && resource.getContents().get(0).eClass().getEPackage().getNsURI() != null
                && !resource.getContents().get(0).eClass().getEPackage().getNsURI().isEmpty()) {
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
    public boolean postValidateTemplate(URI templateURI, DocumentTemplate documentTemplate, Generation generation) {
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
    public void preValidateTemplate(URI templateURI, DocumentTemplate documentTemplate, Generation generation) {
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
    public List<URI> postGenerate(Generation generation, URI templateURI, URI generatedURI,
            DocumentTemplate documentTemplate) {
        final Session session = sessions.remove(generation);
        if (session != null) {
            session.getTransactionalEditingDomain().getResourceSet().eAdapters()
                    .remove(trasiantAttachments.remove(session));
            if (sessionToClose.remove(session)) {
                session.close(new NullProgressMonitor());
            }
        }

        return Collections.emptyList();
    }

    @Override
    public ResourceSet createResourceSetForModels(Generation generation) {
        ResourceSet created = null;
        final Map<String, String> options = GenconfUtils.getOptions(generation);
        final String representationsFileName = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (representationsFileName != null) {
            final URI sessionURI = GenconfUtils.getResolvedURI(generation,
                    URI.createURI(representationsFileName, false));
            if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final Session session = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
                sessions.put(generation, session);
                try {
                    if (!session.isOpen()) {
                        session.open(new NullProgressMonitor());
                        sessionToClose.add(session);
                    }
                    // CHECKSTYLE:OFF
                } catch (Exception e) {
                    // CHECKSTYLE:ON
                    // TODO remove this workaround see https://support.jira.obeo.fr/browse/VP-5389
                    if (PlatformUI.isWorkbenchRunning()) {
                        MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                                "Unable to open Sirius Session",
                                "Check the " + M2DocSiriusUtils.SIRIUS_SESSION_OPTION
                                    + " option or try to open the session manually by double click the .aird file:\n"
                                    + e.getMessage());
                    }
                }
                created = session.getTransactionalEditingDomain().getResourceSet();
                SessionTransientAttachment transiantAttachment = new SessionTransientAttachment(session);
                created.eAdapters().add(transiantAttachment);
                trasiantAttachments.put(session, transiantAttachment);
            } else {
                throw new IllegalArgumentException("The Sirius session doesn't exists: " + sessionURI);
            }
        }
        return created;
    }

}
