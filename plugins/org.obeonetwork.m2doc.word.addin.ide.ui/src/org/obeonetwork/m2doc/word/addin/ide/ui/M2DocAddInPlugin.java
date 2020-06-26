package org.obeonetwork.m2doc.word.addin.ide.ui;
/*******************************************************************************

 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.obeonetwork.m2doc.word.addin.CompletionServer;
import org.obeonetwork.m2doc.word.addin.ide.ui.preferences.AddInPreferenceInitializer;
import org.osgi.framework.BundleContext;
import org.osgi.service.prefs.Preferences;

/**
 * Plugin and Activator classes for the bundle.
 * 
 * @author <a href="mailto:cedric.brun@obeo.fr">Cedric Brun</a>
 */
public final class M2DocAddInPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     */
    public static final M2DocAddInPlugin INSTANCE = new M2DocAddInPlugin();

    /**
     * The plugin ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.word.addin.ide.ui";

    /**
     * Keep track of the singleton.
     */
    private static Implementation plugin;

    /**
     * Create the instance.
     */
    public M2DocAddInPlugin() {
        super(new ResourceLocator[] {});
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * 
     * @return the singleton instance.
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     */
    public static class Implementation extends EclipsePlugin {

        /**
         * The {@link CompletionServer}.
         */
        private final CompletionServer server = new CompletionServer();

        /**
         * Tells if the server is {@link #start(String, int) started}.
         */
        private boolean isStarted;

        /**
         * Creates an instance.
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }

        /**
         * Tells if the server is {@link #start(String, int) started}.
         * 
         * @return <code>true</code> if the server is {@link #startServer(String, int) started}, <code>false</code> otherwise
         */
        public boolean isServerStarted() {
            return isStarted;
        }

        /**
         * Starts the server on the given host and port.
         * 
         * @param host
         *            the host
         * @param port
         *            the port
         */
        public void startServer(String host, int port) {
            try {
                server.start(host, port);
                isStarted = true;
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                log(new Status(IStatus.ERROR, PLUGIN_ID, "can't start M2Doc add-in server", e));
            }
        }

        /**
         * Stops the server.
         */
        public void stopServer() {
            try {
                server.stop();
                isStarted = false;
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                log(new Status(IStatus.ERROR, PLUGIN_ID, "can't stop M2Doc add-in server", e));
            }
        }

        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            final Preferences preferences = InstanceScope.INSTANCE.getNode(AddInPreferenceInitializer.SCOPE);
            final String started = preferences.get(AddInPreferenceInitializer.STARTED_PREFERENCE,
                    String.valueOf(false));
            if (Boolean.valueOf(started)) {
                final String host = preferences.get(AddInPreferenceInitializer.HOST_PREFERENCE,
                        AddInPreferenceInitializer.DEFAULT_HOST);
                final String port = preferences.get(AddInPreferenceInitializer.PORT_PREFERENCE,
                        String.valueOf(AddInPreferenceInitializer.DEFAULT_PORT));
                startServer(host, Integer.valueOf(port));
            }
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
            stopServer();
        }

    }

}
