/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/

package org.obeonetwork.m2doc.ide.jdt;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.BundleContext;

/**
 * Plugin's activator class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocJDTPlugin extends EMFPlugin {

    /**
     * Plugin's id.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.ide.jdt"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    public static final M2DocJDTPlugin INSTANCE = new M2DocJDTPlugin();

    /**
     * The implementation plugin for Eclipse.
     */
    private static Implementation plugin;

    /**
     * The constructor.
     */
    public M2DocJDTPlugin() {
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
        }

        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
        }

    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance.
     */
    public static M2DocJDTPlugin getDefault() {
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
        M2DocJDTPlugin.INSTANCE.log(new Status(severity, PLUGIN_ID, exception.getMessage(), exception));
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
        M2DocJDTPlugin.INSTANCE.log(new Status(severity, PLUGIN_ID, errorMessage));
    }
}
