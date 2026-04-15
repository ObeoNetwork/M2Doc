/*******************************************************************************
 *  Copyright (c) 2016, 2026 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/

package org.obeonetwork.m2doc.ide;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.acceleo.query.ide.QueryPlugin;
import org.eclipse.acceleo.query.ide.runtime.impl.namespace.OSGiQualifiedNameResolver;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.namespace.JavaLoader;
import org.eclipse.acceleo.query.runtime.namespace.ILoader;
import org.eclipse.acceleo.query.runtime.namespace.IQualifiedNameResolver;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.ResourceLocator;
import org.obeonetwork.m2doc.generator.M2DocEvaluationEnvironment;
import org.obeonetwork.m2doc.ide.services.namespace.EclipseM2DocDocumentTemplateLoader;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.BundleListener;

/**
 * Plugin's activator class.
 * 
 * @author PGUILET_OBEO
 */
public class M2DocPlugin extends EMFPlugin {

    /**
     * Plugin's id.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.ide"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    public static final M2DocPlugin INSTANCE = new M2DocPlugin();

    /**
     * The implementation plugin for Eclipse.
     */
    private static Implementation plugin;

    /**
     * The {@link BundleContext}.
     */
    private static BundleContext bundlerContext;

    /**
     * The constructor.
     */
    public M2DocPlugin() {
        super(new ResourceLocator[] {});
    }

    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * Class implementing the EclipsePlugin instance, instanciated when the code is run in an OSGi context.
     * 
     * @author cedric
     */
    public static class Implementation extends EclipsePlugin {

        /** The listener for M2Doc services and package tokens. */
        private DeclaredTokensListener servicesListener = new DeclaredTokensListener();

        /** The listener for M2Doc templates. */
        private DeclaredTemplatesListener templatesListener = new DeclaredTemplatesListener();

        /**
         * The {@link Map} of {@link Bundle#getSymbolicName() bundle name} to {@link Bundle}.
         */
        // TODO add version ?
        private final Map<String, Bundle> bundles = new HashMap<>();

        /**
         * The {@link BundleListener} keeping track of {@link BundleEvent#INSTALLED installed} and {@link BundleEvent#UNINSTALLED
         * uninstalled}
         * {@link Bundle}.
         */
        private final BundleListener bundleListener;

        /**
         * Create the Eclipse Implementation.
         */
        public Implementation() {
            super();

            // make sure org.eclipse.acceleo.query.ide is started
            QueryPlugin.getPlugin();

            // Remember the static instance.
            //
            plugin = this;

            bundleListener = new BundleListener() {

                @Override
                public void bundleChanged(BundleEvent event) {
                    final Bundle bundle = event.getBundle();
                    switch (event.getType()) {
                        case BundleEvent.INSTALLED:
                            bundles.put(bundle.getSymbolicName(), bundle);
                            break;

                        case BundleEvent.UNINSTALLED:
                            bundles.remove(bundle.getSymbolicName());
                            break;

                        default:
                            // nothing to do here
                            break;
                    }
                }

            };

        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            final IExtensionRegistry registry = Platform.getExtensionRegistry();
            registry.addListener(servicesListener, DeclaredTokensListener.SERVICE_REGISTERY_EXTENSION_POINT);
            servicesListener.parseInitialContributions();
            registry.addListener(templatesListener, DeclaredTemplatesListener.TEMPLATE_REGISTERY_EXTENSION_POINT);
            templatesListener.parseInitialContributions();
            bundlerContext = context;
            for (Bundle bundle : context.getBundles()) {
                bundles.put(bundle.getSymbolicName(), bundle);
            }

            context.addBundleListener(bundleListener);
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
            final IExtensionRegistry registry = Platform.getExtensionRegistry();
            registry.removeListener(servicesListener);
            registry.removeListener(templatesListener);
            // TODO clear registries ?
            context.removeBundleListener(bundleListener);
            bundles.clear();
        }

        /**
         * Loads imported services from the workspace.
         * 
         * @param m2docEnv
         *            the {@link M2DocEvaluationEnvironment}
         * @param documentTemplate
         *            the {@link DocumentTemplate}
         */
        public void loadServicesFromWorkspace(M2DocEvaluationEnvironment m2docEnv,
                final DocumentTemplate documentTemplate) {
            final TemplateCustomProperties properties = documentTemplate.getProperties();
            // bundle name to resolver
            final Map<String, IQualifiedNameResolver> eclipseResolvers = new HashMap<>();
            if (properties != null) {
                IQualifiedNameResolver resolver = m2docEnv.getResolver();
                for (Entry<String, String> entry : properties.getImports().entrySet()) {
                    final String importedQualifiedName = entry.getKey();
                    final Object resolved = resolver.resolve(importedQualifiedName);
                    final String bundleName = entry.getValue();
                    if (resolved == null && bundleName != null) {
                        IQualifiedNameResolver eclipseResolver = eclipseResolvers.computeIfAbsent(bundleName,
                                bn -> createResolver(m2docEnv, bn));
                        if (eclipseResolver != null) {
                            final Object eclipseResolved = eclipseResolver.resolve(importedQualifiedName);
                            resolver.register(importedQualifiedName, eclipseResolved);
                        }
                    }
                }
            }
        }

        /**
         * Creates the {@link IQualifiedNameResolver} for the given bundle name.
         * 
         * @param m2docEnv
         *            the {@link M2DocEvaluationEnvironment}
         * @param bundleName
         *            the bundle name
         * @return the created {@link IQualifiedNameResolver}
         */
        private IQualifiedNameResolver createResolver(M2DocEvaluationEnvironment m2docEnv, String bundleName) {
            final IQualifiedNameResolver result;

            final IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(bundleName);
            if (project.isAccessible()) {
                result = QueryPlugin.getPlugin().createQualifiedNameResolver(getClass().getClassLoader(), null, project,
                        M2DocUtils.QUALIFIER_SEPARATOR, false);
                result.addLoader(new EclipseM2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(),
                        M2DocUtils.QUALIFIER_SEPARATOR));
                final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
                result.addLoader(javaLoader);
            } else {
                final Bundle bundle = bundles.get(bundleName);
                if (bundle != null) {
                    result = new OSGiQualifiedNameResolver(bundle, null, M2DocUtils.QUALIFIER_SEPARATOR);
                    result.addLoader(new EclipseM2DocDocumentTemplateLoader(m2docEnv, new BasicMonitor(),
                            M2DocUtils.QUALIFIER_SEPARATOR));
                    final ILoader javaLoader = new JavaLoader(M2DocUtils.QUALIFIER_SEPARATOR, false);
                    result.addLoader(javaLoader);
                    Query.newQualifiedNameEnvironment(result);
                } else {
                    result = null;
                }
            }

            return result;
        }

    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance.
     */
    public static M2DocPlugin getDefault() {
        return INSTANCE;
    }

    /**
     * Logs the given exception as error or warning.
     * 
     * @param exception
     *            The exception to log.
     * @param blocker
     *            <code>true</code> if the message must be logged as error, <code>false</code> to log it as a
     *            warning.
     */
    public static void log(Exception exception, boolean blocker) {
        int severity = IStatus.WARNING;
        if (blocker) {
            severity = IStatus.ERROR;
        }
        M2DocPlugin.INSTANCE.log(new Status(severity, PLUGIN_ID, exception.getMessage(), exception));
    }

    /**
     * Puts the given message in the error log view, as error or warning.
     * 
     * @param message
     *            The message to put in the error log view.
     * @param blocker
     *            <code>true</code> if the message must be logged as error, <code>false</code> to log it as a
     *            warning.
     */
    public static void log(String message, boolean blocker) {
        int severity = IStatus.WARNING;
        if (blocker) {
            severity = IStatus.ERROR;
        }
        String errorMessage = message;
        if (errorMessage == null || "".equals(errorMessage)) { //$NON-NLS-1$
            errorMessage = "Logging null message should never happens."; //$NON-NLS-1$
        }
        M2DocPlugin.INSTANCE.log(new Status(severity, PLUGIN_ID, errorMessage));
    }

    /**
     * Gets the {@link BundleContext}.
     * 
     * @return the {@link BundleContext}
     */
    public static BundleContext getBundlerContext() {
        return bundlerContext;
    }

}
