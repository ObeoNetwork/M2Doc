/*******************************************************************************
 * Copyright (c) 2017, 2025 Obeo. 
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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.ServiceUtils;
import org.eclipse.acceleo.query.services.configurator.IServicesConfigurator;
import org.eclipse.acceleo.query.sirius.AqlSiriusUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ScalingPolicy;
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
    private final Map<IReadOnlyQueryEnvironment, M2DocSiriusServices> services = new HashMap<>();

    /**
     * Initializes options.
     * 
     * @return the {@link List} of options.
     */
    private static List<String> initOptions() {
        final List<String> res = new ArrayList<>();

        res.add(M2DocSiriusUtils.SIRIUS_FORCE_REFRESH);
        res.add(M2DocSiriusUtils.SIRIUS_SCALING_POLICY);
        res.add(M2DocSiriusUtils.SIRIUS_SCALE_LEVEL);

        return res;
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options, EObject eObj) {
        return Collections.emptyMap();
    }

    @Override
    public Set<IService<?>> getServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels,
            Map<String, String> options, boolean forWorkspace) {
        final Set<IService<?>> res = new LinkedHashSet<>();

        final Session session = AqlSiriusUtils.getSession(options, new NullProgressMonitor());
        if (session != null) {
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
            final M2DocSiriusServices serviceInstance = new M2DocSiriusServices(session, forceRefresh, scalingPolicy,
                    scaleLevel);
            res.addAll(ServiceUtils.getServices(queryEnvironment, serviceInstance));
            services.put(queryEnvironment, serviceInstance);
        }

        return res;
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

}
