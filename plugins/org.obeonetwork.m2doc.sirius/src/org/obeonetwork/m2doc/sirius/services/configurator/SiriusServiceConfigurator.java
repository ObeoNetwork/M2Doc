/*******************************************************************************
 * Copyright (c) 2017, 2024 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v2.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v20.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.services.configurator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ScalingPolicy;
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
        res.add(M2DocSiriusUtils.SIRIUS_SCALING_POLICY);
        res.add(M2DocSiriusUtils.SIRIUS_SCALE_LEVEL);

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

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options, EObject eObj) {
        final Map<String, String> res = new HashMap<>();

        if (!options.containsKey(M2DocSiriusUtils.SIRIUS_SESSION_OPTION)) {
            final Session session = new EObjectQuery(eObj).getSession();
            if (session != null) {
                final String genConfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
                final URI genConfURI = URI.createURI(genConfURIStr, true);
                final URI sessionURI = session.getSessionResource().getURI();
                final String sessionURIStr = URI.decode(sessionURI.deresolve(genConfURI, false, true, true).toString());

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
            final URI genConfURI = URI.createURI(genConfURIStr, true);
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
     * @param genconfPlatformResourceURI
     *            the platform resource {@link URI}
     * @return the {@link Session} path form the given platform resource {@link URI} if any, <code>null</code> otherwise
     */
    private String getSessionFromPlatformResource(final URI genconfPlatformResourceURI) {
        final String res;
        final String filePath = genconfPlatformResourceURI.toPlatformString(true);
        final IFile genconfFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
        final IProject project = genconfFile.getProject();
        final ModelingProject modelingProject = getModelingProject(project);

        if (modelingProject != null) {
            final Session session = modelingProject.getSession();
            if (session != null) {
                final URI sessionURI = session.getSessionResource().getURI();
                res = URI.decode(sessionURI.deresolve(genconfPlatformResourceURI, false, true, true).toString());
            } else {
                res = getSessionFile(genconfPlatformResourceURI, project);
            }
        } else {
            res = null;
        }
        return res;
    }

    /**
     * Gets the Sirius Session file in the given {@link IContainer}.
     * 
     * @param genconfPlatformResourceURI
     *            the platform resource {@link URI}
     * @param container
     *            the {@link IProject}
     * @return the Sirius Session file in the given {@link IContainer} if any, <code>null</code> otherwise
     */
    private String getSessionFile(URI genconfPlatformResourceURI, IContainer container) {
        String res = null;

        try {
            for (IResource member : container.members()) {
                if (member instanceof IContainer) {
                    res = getSessionFile(genconfPlatformResourceURI, (IContainer) member);
                    if (res != null) {
                        break;
                    }
                } else if (member instanceof IFile
                    && SiriusUtil.SESSION_RESOURCE_EXTENSION.equals(member.getFileExtension())) {
                        final Session session = SessionManager.INSTANCE.getSession(
                                URI.createPlatformResourceURI(member.getFullPath().toString(), true),
                                new NullProgressMonitor());
                        if (session != null) {
                            final URI sessionURI = session.getSessionResource().getURI();
                            res = URI.decode(
                                    sessionURI.deresolve(genconfPlatformResourceURI, false, true, true).toString());
                            break;
                        }
                    }
            }
        } catch (CoreException e) {
            // we ignore this since the option can be set manually afterward
        }

        return res;
    }

    /**
     * Gets the {@link ModelingProject} for the given {@link IProject}.
     * 
     * @param project
     *            the {@link IProject}
     * @return the {@link ModelingProject} for the given {@link IProject} if any, <code>null</code> otherwise
     */
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
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels,
            Map<String, String> options) {
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
                final ScalingPolicy scalingPolicy;
                if (options.containsKey(M2DocSiriusUtils.SIRIUS_SCALING_POLICY)) {
                    scalingPolicy = ScalingPolicy.valueOf(options.get(M2DocSiriusUtils.SIRIUS_SCALING_POLICY));
                } else {
                    scalingPolicy = ScalingPolicy.WORKSPACE_DEFAULT;
                }
                final Integer scaleLevel;
                if (options.containsKey(M2DocSiriusUtils.SIRIUS_SCALE_LEVEL)) {
                    scaleLevel = Integer.valueOf(options.get(M2DocSiriusUtils.SIRIUS_SCALE_LEVEL));
                } else {
                    scaleLevel = null;
                }
                final M2DocSiriusServices serviceInstance = new M2DocSiriusServices(session, forceRefresh,
                        scalingPolicy, scaleLevel);
                res.addAll(ServiceUtils.getServices(queryEnvironment, serviceInstance));
                services.put(queryEnvironment, serviceInstance);
            }
        }

        return res;
    }

    @Override
    public void startGeneration(IReadOnlyQueryEnvironment queryEnvironment, XWPFDocument destinationDocument) {
        // nothing to do here
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels) {
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
        final String forceRefreshStr = options.get(M2DocSiriusUtils.SIRIUS_FORCE_REFRESH);
        if (forceRefreshStr != null) {
            final List<Diagnostic> diagnostics = new ArrayList<>();
            res.put(M2DocSiriusUtils.SIRIUS_FORCE_REFRESH, diagnostics);
            if (!Boolean.TRUE.toString().equalsIgnoreCase(forceRefreshStr)
                && !Boolean.FALSE.toString().equalsIgnoreCase(forceRefreshStr)) {
                diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, M2DocSiriusUtils.PLUGIN_ID, 0,
                        "The Sirius force refresh must be a boolean true or false: " + forceRefreshStr,
                        new Object[] {forceRefreshStr}));
            }
        }
        final String scalePolicyStr = options.get(M2DocSiriusUtils.SIRIUS_SCALING_POLICY);
        if (!isValidScalePolicy(scalePolicyStr)) {
            final List<Diagnostic> diagnostics = new ArrayList<>();
            res.put(M2DocSiriusUtils.SIRIUS_SCALING_POLICY, diagnostics);
            diagnostics.add(new BasicDiagnostic(
                    Diagnostic.ERROR, M2DocSiriusUtils.PLUGIN_ID, 0, "The Sirius scale policy must be one of "
                        + Arrays.toString(ScalingPolicy.values()) + ": " + scalePolicyStr,
                    new Object[] {scalePolicyStr}));
        }
        final String scaleLevelStr = options.get(M2DocSiriusUtils.SIRIUS_SCALE_LEVEL);
        try {
            Integer.valueOf(scaleLevelStr);
        } catch (NumberFormatException e) {
            final List<Diagnostic> diagnostics = new ArrayList<>();
            res.put(M2DocSiriusUtils.SIRIUS_SCALE_LEVEL, diagnostics);
            diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, M2DocSiriusUtils.PLUGIN_ID, 0,
                    "The Sirius scale level must be an integer: " + scalePolicyStr, new Object[] {scaleLevelStr}));
        }

        return res;
    }

    /**
     * Tells if the given scale policy {@link String} is a valid {@link ScalingPolicy}.
     * 
     * @param scalePolicyStr
     *            the scale policy {@link String}
     * @return <code>true</code> if the given scale policy {@link String} is a valid {@link ScalingPolicy}, <code>false</code> otherwise
     */
    private boolean isValidScalePolicy(String scalePolicyStr) {
        boolean res = false;

        for (ScalingPolicy policy : ScalingPolicy.values()) {
            if (policy.name().equals(scalePolicyStr)) {
                res = true;
                break;
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
