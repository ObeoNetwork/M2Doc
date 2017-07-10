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

import com.google.common.collect.Lists;

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
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.obeonetwork.m2doc.genconf.util.ConfigurationServices;
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
    public static final List<String> OPTIONS = Lists.newArrayList(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);

    /**
     * Mapping from {@link IReadOnlyQueryEnvironment} to {@link M2DocSiriusServices}.
     */
    private final Map<IReadOnlyQueryEnvironment, M2DocSiriusServices> services = new HashMap<IReadOnlyQueryEnvironment, M2DocSiriusServices>();

    @Override
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
        final Set<IService> res = new LinkedHashSet<IService>();

        final String sessionURIStr = options.get(M2DocSiriusUtils.SIRIUS_SESSION_OPTION);
        if (sessionURIStr != null) {
            URI sessionURI = URI.createURI(sessionURIStr);
            final String genconfURIStr = options.get(ConfigurationServices.GENCONF_URI_OPTION);
            if (genconfURIStr != null) {
                sessionURI = sessionURI.resolve(URI.createURI(genconfURIStr));
            }
            if (URIConverter.INSTANCE.exists(sessionURI, Collections.emptyMap())) {
                final Session session = SessionManager.INSTANCE.getSession(sessionURI, new NullProgressMonitor());
                if (!session.isOpen()) {
                    session.open(new NullProgressMonitor());
                }
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
            final String genconfURIStr = options.get(ConfigurationServices.GENCONF_URI_OPTION);
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
