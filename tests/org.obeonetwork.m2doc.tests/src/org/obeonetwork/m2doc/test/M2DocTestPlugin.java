package org.obeonetwork.m2doc.test;

import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Plugin;
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
public class M2DocTestPlugin extends Plugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.tests"; //$NON-NLS-1$

    // The shared instance
    private static M2DocTestPlugin plugin;

    /**
     * Listener that catch exception that would be put in the error log.
     */
    private ErrorLogListener errorLogListener = new ErrorLogListener();

    /**
     * The constructor
     */
    public M2DocTestPlugin() {
        // not used
    }

    /**
     * Returns the listener that catch exception that would be put in the error log.
     * 
     * @return the listener that catch exception that would be put in the error log.
     */
    public ErrorLogListener getErrorLogListener() {
        return errorLogListener;
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
        Platform.addLogListener(errorLogListener);
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
    }

    /**
     * Returns the shared instance;
     *
     * @return the shared instance;
     */
    public static M2DocTestPlugin getDefault() {
        return plugin;
    }

}
