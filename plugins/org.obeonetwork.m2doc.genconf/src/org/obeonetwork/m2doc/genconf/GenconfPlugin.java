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

package org.obeonetwork.m2doc.genconf;

import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.BundleContext;

/**
 * Plugin's activator class.
 * 
 * @author PGUILET_OBEO
 */
public class GenconfPlugin extends EMFPlugin {

    /**
     * Plugin's id.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.genconf"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    public static final GenconfPlugin INSTANCE = new GenconfPlugin();

    /**
     * The implementation plugin for Eclipse.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public GenconfPlugin() {
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

        /**
         * The {@link GenconfResourceListener}.
         */
        private GenconfResourceListener listener;

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
            listener = new GenconfResourceListener();
            ResourcesPlugin.getWorkspace().addResourceChangeListener(listener);
            listener.walkWorkspace(ResourcesPlugin.getWorkspace());
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            ResourcesPlugin.getWorkspace().removeResourceChangeListener(listener);
            listener = null;
            super.stop(context);
        }

        /**
         * Gets the {@link Generation} {@link URI} from the given result {@link URI}.
         * 
         * @param resultURI
         *            the result {@link URI}
         * @return the {@link Generation} {@link URI} from the given result {@link URI} if any, <code>null</code> otherwise
         */
        public List<URI> getGenconfURIsFromResult(URI resultURI) {
            return listener.getGenconfURIsFromResult(resultURI);
        }

        /**
         * Gets the {@link Generation} {@link URI} from the given template {@link URI}.
         * 
         * @param templateURI
         *            the template {@link URI}
         * @return the {@link Generation} {@link URI} from the given template {@link URI} if any, <code>null</code> otherwise
         */
        public List<URI> getGenconfURIsFromTempate(URI templateURI) {
            return listener.getGenconfURIsFromTempate(templateURI);
        }

    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance.
     */
    public static GenconfPlugin getDefault() {
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
        GenconfPlugin.INSTANCE.log(new Status(severity, PLUGIN_ID, exception.getMessage(), exception));
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
        GenconfPlugin.INSTANCE.log(new Status(severity, PLUGIN_ID, errorMessage));
    }
}
