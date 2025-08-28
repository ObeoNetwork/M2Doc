/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.obeonetwork.m2doc.ide.util.ClassProviderRegistryListener;
import org.obeonetwork.m2doc.ide.util.EclipseClassProvider;
import org.obeonetwork.m2doc.ide.util.IClassProviderDescriptor;
import org.obeonetwork.m2doc.util.ClassProvider;
import org.obeonetwork.m2doc.util.IClassProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

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
     * The {@link IClassProvider}.
     */
    private static IClassProvider classProvider = new ClassProvider(M2DocPlugin.class.getClassLoader());

    /**
     * The {@link BundleContext}.
     */
    private static BundleContext bundlerContext;

    /**
     * The {@link List} of {@link #registerClassProvider(IClassProviderDescriptor) registered}
     * {@link IClassProviderDescriptor}.
     */
    private static final List<IClassProviderDescriptor> PROVIDERS = new ArrayList<>();

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

        /** The registry listener that will be used to listen to extension changes. */
        private ClassProviderRegistryListener providerListener = new ClassProviderRegistryListener();

        /** The listener for M2Doc services and package tokens. */
        private DeclaredTokensListener servicesListener = new DeclaredTokensListener();

        /** The listener for M2Doc templates. */
        private DeclaredTemplatesListener templatesListener = new DeclaredTemplatesListener();

        /**
         * Create the Eclipse Implementation.
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            final IExtensionRegistry registry = Platform.getExtensionRegistry();
            registry.addListener(providerListener, ClassProviderRegistryListener.CLASS_PROVIDER_EXTENSION_POINT);
            providerListener.parseInitialContributions();
            registry.addListener(servicesListener, DeclaredTokensListener.SERVICE_REGISTERY_EXTENSION_POINT);
            servicesListener.parseInitialContributions();
            registry.addListener(templatesListener, DeclaredTemplatesListener.TEMPLATE_REGISTERY_EXTENSION_POINT);
            templatesListener.parseInitialContributions();
            bundlerContext = context;
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
            if (classProvider instanceof EclipseClassProvider) {
                ((EclipseClassProvider) classProvider).dispose();
            }
            classProvider = new ClassProvider(M2DocPlugin.class.getClassLoader());
            final IExtensionRegistry registry = Platform.getExtensionRegistry();
            registry.removeListener(servicesListener);
            // TODO clear registry and registryListener ?
        }

    }

    /**
     * Gets the {@link IClassProvider} with {@link Bundle} support.
     * 
     * @return the {@link IClassProvider} with {@link Bundle} support
     */
    public static IClassProvider getClassProvider() {
        if (classProvider.getClass() == ClassProvider.class) {
            if (!PROVIDERS.isEmpty()) {
                classProvider = PROVIDERS.get(0).getClassProvider();
            } else {
                classProvider = new EclipseClassProvider(bundlerContext,
                        M2DocPlugin.getPlugin().getClass().getClassLoader());
            }
        }
        return classProvider;
    }

    /**
     * Sets the {@link EclipseClassProvider} with {@link Bundle} support.
     * 
     * @param classProvider
     *            the {@link EclipseClassProvider}
     */
    public static void setClassProvider(EclipseClassProvider classProvider) {
        M2DocPlugin.classProvider = classProvider;
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
     * Registers the given {@link IClassProviderDescriptor}.
     * 
     * @param configurator
     *            the {@link IClassProviderDescriptor} to register
     */
    public static void registerClassProvider(IClassProviderDescriptor configurator) {
        if (configurator != null) {
            synchronized (PROVIDERS) {
                PROVIDERS.add(configurator);
            }
        }
    }

    /**
     * Unregister the given {@link IClassProviderDescriptor}.
     * 
     * @param providerDescriptor
     *            the {@link IClassProviderDescriptor} to unregister
     */
    public static void unregisterClassProvider(IClassProviderDescriptor providerDescriptor) {
        if (providerDescriptor != null) {
            synchronized (PROVIDERS) {
                PROVIDERS.remove(providerDescriptor);
            }
        }
    }

    /**
     * Gets the {@link List} of registered {@link IClassProviderDescriptor}.
     * 
     * @return the {@link List} of {@link #registerServicesConfigurator(IClassProviderDescriptor) registered}
     *         {@link IClassProviderDescriptor}
     */
    public static List<IClassProvider> getProviders() {
        final List<IClassProvider> res = new ArrayList<>();

        synchronized (PROVIDERS) {
            for (IClassProviderDescriptor descriptor : PROVIDERS) {
                final IClassProvider configurator = descriptor.getClassProvider();
                if (configurator != null) {
                    res.add(configurator);
                }
            }
        }

        return res;
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
