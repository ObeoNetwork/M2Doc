package org.obeonetwork.m2doc;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.obeonetwork.m2doc.provider.DeclaredProviderListener;
import org.obeonetwork.m2doc.provider.IProvider;
import org.osgi.framework.BundleContext;

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

/**
 * Plugin's activator class.
 * 
 * @author PGUILET_OBEO
 */
public class M2DocPlugin extends Plugin {

    /**
     * Plugin's id.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    private static M2DocPlugin plugin;
    /**
     * Register/unregister {@link IProvider} provided by plugins.
     */
    private DeclaredProviderListener providerListener;

    /**
     * The constructor.
     */
    public M2DocPlugin() {
        // not used
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        providerListener = new DeclaredProviderListener();
        Platform.getExtensionRegistry().addListener(providerListener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        Platform.getExtensionRegistry().removeListener(providerListener);
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance.
     */
    public static M2DocPlugin getDefault() {
        return plugin;
    }

    /**
     * Logs the given exception as error or warning.
     * 
     * @param exception
     *            The exception to log.
     * @param blocker
     *            <code>True</code> if the message must be logged as error, <code>False</code> to log it as a
     *            warning.
     */
    public static void log(Exception exception, boolean blocker) {
        int severity = IStatus.WARNING;
        if (blocker) {
            severity = IStatus.ERROR;
        }
        ILog log = getDefault().getLog();
        if (getDefault() != null) {
            log.log(new Status(severity, PLUGIN_ID, exception.getMessage(), exception));
        } else {
            // We are out of eclipse. Prints the message on standard error.
            System.err.println(exception.getMessage());
            exception.printStackTrace();
        }
    }

    /**
     * Puts the given status in the error log view.
     * 
     * @param status
     *            Error Status.
     */
    public static void log(IStatus status) {
        if (getDefault() != null) {
            getDefault().getLog().log(status);
        } else {
            // We are out of eclipse. Prints the message on standard error.
            System.err.println(status.getMessage());
            status.getException().printStackTrace();
        }
    }

    /**
     * Puts the given message in the error log view, as error or warning.
     * 
     * @param message
     *            The message to put in the error log view.
     * @param blocker
     *            <code>True</code> if the message must be logged as error, <code>False</code> to log it as a
     *            warning.
     */
    public static void log(String message, boolean blocker) {
        if (getDefault() == null) {
            // We are out of eclipse. Prints the message on standard error.
            System.err.println(message);
        } else {
            int severity = IStatus.WARNING;
            if (blocker) {
                severity = IStatus.ERROR;
            }
            String errorMessage = message;
            if (errorMessage == null || "".equals(errorMessage)) { //$NON-NLS-1$
                errorMessage = "Logging null message should never happens."; //$NON-NLS-1$
            }
            log(new Status(severity, PLUGIN_ID, errorMessage));
        }
    }
}
