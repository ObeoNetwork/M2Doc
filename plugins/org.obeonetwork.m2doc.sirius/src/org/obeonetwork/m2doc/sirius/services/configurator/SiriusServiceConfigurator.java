/*******************************************************************************
 * Copyright (c) 2017 Obeo. 
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.modelingproject.ModelingProject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ext.base.Option;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.sirius.M2DocSiriusUtils;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;

/**
 * Configure Sirius {@link IService}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class SiriusServiceConfigurator implements IServicesConfigurator {

    /**
     * The {@link List} of options managed by this configurator.
     */
    public static final List<String> OPTIONS = initOptions();

    /**
     * Mapping from {@link IReadOnlyQueryEnvironment} to {@link M2DocSiriusServices}.
     */
    private final Map<IReadOnlyQueryEnvironment, M2DocSiriusServices> services = new HashMap<IReadOnlyQueryEnvironment, M2DocSiriusServices>();

    /**
     * Initializes options.
     * 
     * @return the {@link List} of options.
     */
    private static List<String> initOptions() {
        final List<String> res = new ArrayList<String>();

        res.add(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);

        return res;
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        final Map<String, String> res = new HashMap<String, String>();

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
            final URI genConfURI = URI.createURI(genConfURIStr);
            if (URIConverter.INSTANCE.exists(genConfURI, Collections.emptyMap()) && genConfURI.isPlatformResource()) {
                final String filePath = genConfURI.toPlatformString(true);
                final IFile genconfFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePath));
                Option<ModelingProject> optionalModelingProject = ModelingProject
                        .asModelingProject(genconfFile.getProject());
                if (optionalModelingProject.some()) {
                    final ModelingProject project = optionalModelingProject.get();
                    final Session session = project.getSession();
                    final URI sessionURI = session.getSessionResource().getURI();
                    res = sessionURI.deresolve(genConfURI).toString();
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

    @Override
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
        final Set<IService> res = new LinkedHashSet<IService>();

        final String sessionURIStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (sessionURIStr != null) {
            URI sessionURI = URI.createURI(sessionURIStr);
            final String genconfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
            if (genconfURIStr != null) {
                sessionURI = sessionURI.resolve(URI.createURI(genconfURIStr));
            }
            if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final Session session = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
                final M2DocSiriusServices serviceInstance = new M2DocSiriusServices(session);
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
        // TODO close the Session ?
    }

    @Override
    public List<String> getOptions() {
        return OPTIONS;
    }

    @Override
    public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, String> options) {
        final Map<String, List<Diagnostic>> res = new HashMap<String, List<Diagnostic>>();

        final String sessionURIStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (sessionURIStr != null) {
            URI sessionURI = URI.createURI(sessionURIStr);
            final String genconfURIStr = options.get(GenconfUtils.GENCONF_URI_OPTION);
            if (genconfURIStr != null) {
                sessionURI = sessionURI.resolve(URI.createURI(genconfURIStr));
            }
            if (!URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
                res.put(M2DocSiriusUtils.SIRIUS_SESSION_OPTION, diagnostics);
                diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, M2DocSiriusUtils.PLUGIN_ID, 0,
                        "The Sirius session doesn't exist: " + sessionURI.toString(), new Object[] {sessionURI}));
            }
        }

        return res;
    }

}
