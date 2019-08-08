/*******************************************************************************
 * Copyright (c) 2017, 2019 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.services.configurator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.sirius.M2DocSiriusUtils;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;

/**
 * Configure Sirius {@link IService}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class SiriusServiceConfigurator implements IServicesConfigurator {

    /**
     * The {@link List} of options managed by this configurator.
     */
    public static final List<String> OPTIONS = initOptions();

    /**
     * Mapping from {@link IReadOnlyQueryEnvironment} to {@link M2DocSiriusServices}.
     */
    private final Map<IReadOnlyQueryEnvironment, M2DocSiriusServices> services = new HashMap<>();

    /**
     * Mapping of {@link Object} to {@link Session}.
     */
    private final Map<Object, Session> sessions = new HashMap<>();

    /**
     * {@link Set} of {@link Session} that need to be closed.
     */
    private final Set<Session> sessionToClose = new HashSet<>();

    /**
     * Mapping of {@link Session} to {@link SessionTransientAttachment}.
     */
    private final Map<Session, SessionTransientAttachment> transientAttachments = new HashMap<>();

    /**
     * Initializes options.
     * 
     * @return the {@link List} of options.
     */
    private static List<String> initOptions() {
        final List<String> res = new ArrayList<>();

        res.add(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        res.add(M2DocSiriusUtils.SIRIUS_FORCE_REFRESH);

        return res;
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        final Map<String, String> res = new HashMap<>();

        if (!options.containsKey(M2DocSiriusUtils.SIRIUS_SESSION_OPTION)) {
            final String genConfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
            final String sessionURIStr = getSessionString(genConfURIStr);
            if (sessionURIStr != null) {
                res.put(M2DocSiriusUtils.SIRIUS_SESSION_OPTION, sessionURIStr);
            }
        }
        return res;
    }

    /**
     * Gets the {@link Session} option from the generation URI string.
     * 
     * @param genConfURIStr
     *            the generation URI string
     * @return the {@link Session} option from the generation URI string
     */
    protected String getSessionString(final String genConfURIStr) {
        final String res;

        if (genConfURIStr != null) {
            final URI genConfURI = URI.createURI(genConfURIStr, false);
            if (genConfURI.isPlatformResource()) {
                res = getSessionFromPlatformResource(genConfURI);
            } else {
                res = null;
            }
        } else {
            res = null;
        }

        return res;
    }

    /**
     * Gets the {@link Session} path form the given platform resource {@link URI}.
     * 
     * @param platformResourceURI
     *            the platform resource {@link URI}
     * @return the {@link Session} path form the given platform resource {@link URI} if any, <code>null</code> otherwise
     */
    private String getSessionFromPlatformResource(final URI platformResourceURI) {
        final String res;
        final String filePath = platformResourceURI.toPlatformString(true);
        final IFile genconfFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
        final IProject project = genconfFile.getProject();
        final ModelingProject modelingProject = getModelingProject(project);

        if (modelingProject != null) {
            final Session session = modelingProject.getSession();
            if (session != null) {
                final URI sessionURI = session.getSessionResource().getURI();
                res = sessionURI.deresolve(platformResourceURI, false, true, true).toString();
            } else {
                res = null;
            }
        } else {
            res = null;
        }
        return res;
    }

    private ModelingProject getModelingProject(IProject project) {
        ModelingProject modelingProject = null;
        try {
            for (String natureId : project.getDescription().getNatureIds()) {
                IProjectNature nature = project.getNature(natureId);
                if (nature instanceof ModelingProject) {
                    modelingProject = (ModelingProject) nature;
                    break;
                }
            }
        } catch (CoreException e) {
            /* does nothing */
        }
        return modelingProject;
    }

    @Override
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
        final Set<IService> res = new LinkedHashSet<>();

        final String sessionURIStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (sessionURIStr != null) {
            URI sessionURI = URI.createURI(sessionURIStr, false);
            final String genconfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
            if (genconfURIStr != null) {
                sessionURI = sessionURI.resolve(URI.createURI(genconfURIStr));
            }
            if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final Session session = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
                final boolean forceRefresh = Boolean.valueOf(options.get(M2DocSiriusUtils.SIRIUS_FORCE_REFRESH));
                final M2DocSiriusServices serviceInstance = new M2DocSiriusServices(session, forceRefresh);
                res.addAll(ServiceUtils.getServices(queryEnvironment, serviceInstance));
                services.put(queryEnvironment, serviceInstance);
            }
        }

        return res;
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment) {
        final M2DocSiriusServices serviceInstance = services.remove(queryEnvironment);
        if (serviceInstance != null) {
            serviceInstance.clean();
        }
    }

    @Override
    public List<String> getOptions() {
        return OPTIONS;
    }

    @Override
    public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, String> options) {
        final Map<String, List<Diagnostic>> res = new HashMap<>();

        final String sessionURIStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (sessionURIStr != null) {
            URI sessionURI = URI.createURI(sessionURIStr, false);
            final String genconfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
            if (genconfURIStr != null) {
                sessionURI = sessionURI.resolve(URI.createURI(genconfURIStr, false));
            }
            if (!URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final List<Diagnostic> diagnostics = new ArrayList<>();
                res.put(M2DocSiriusUtils.SIRIUS_SESSION_OPTION, diagnostics);
                diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, M2DocSiriusUtils.PLUGIN_ID, 0,
                        "The Sirius session doesn't exist: " + sessionURI.toString(), new Object[] {sessionURI}));
            }
        }
        final String forceRefreshStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (forceRefreshStr != null) {
            final List<Diagnostic> diagnostics = new ArrayList<>();
            res.put(M2DocSiriusUtils.SIRIUS_FORCE_REFRESH, diagnostics);
            if (!Boolean.TRUE.toString().equalsIgnoreCase(forceRefreshStr)
                && !Boolean.FALSE.toString().equalsIgnoreCase(forceRefreshStr)) {
                diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, M2DocSiriusUtils.PLUGIN_ID, 0,
                        "The Sirius force refresh must be a boolean true or flase: " + forceRefreshStr,
                        new Object[] {forceRefreshStr}));
            }
        }

        return res;
    }

    @Override
    public ResourceSet createResourceSetForModels(Object context, Map<String, String> options) {
        ResourceSet created = null;
        final String sessionURIStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (sessionURIStr != null && !sessionURIStr.isEmpty()) {
            URI sessionURI = URI.createURI(sessionURIStr, false);
            final String genconfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
            if (genconfURIStr != null) {
                sessionURI = sessionURI.resolve(URI.createURI(genconfURIStr));
            }
            if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                try {
                    final Session session = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
                    sessions.put(context, session);
                    if (!session.isOpen()) {
                        session.open(new NullProgressMonitor());
                        sessionToClose.add(session);
                    }
                    created = session.getTransactionalEditingDomain().getResourceSet();
                    SessionTransientAttachment transiantAttachment = new SessionTransientAttachment(session);
                    created.eAdapters().add(transiantAttachment);
                    transientAttachments.put(session, transiantAttachment);
                    // CHECKSTYLE:OFF
                } catch (Exception e) {
                    // CHECKSTYLE:ON
                    // TODO remove this workaround see https://support.jira.obeo.fr/browse/VP-5389
                    if (PlatformUI.isWorkbenchRunning()) {
                        MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                                "Unable to open Sirius Session",
                                "Check the " + M2DocSiriusUtils.SIRIUS_SESSION_OPTION
                                    + " option or try to open the session manually by double clicking the .aird file:\n"
                                    + e.getMessage());
                    }
                }
            } else {
                throw new IllegalArgumentException("The Sirius session doesn't exist: " + sessionURI);
            }
        }
        return created;
    }

    @Override
    public void cleanResourceSetForModels(Object context) {
        final Session session = sessions.remove(context);
        if (session != null) {
            if (session.isOpen()) {
                session.getTransactionalEditingDomain().getResourceSet().eAdapters()
                        .remove(transientAttachments.remove(session));
            }
            if (sessionToClose.remove(session)) {
                session.close(new NullProgressMonitor());
            }
        }
    }

}
