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
package org.obeonetwork.m2doc.ide.tests;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.osgi.framework.BundleContext;

/**
 * Plugin's activator class.
 * 
 * @author PGUILET_OBEO
 */
public class M2DocTestPlugin extends EMFPlugin {

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.ide.tests"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    public static final M2DocTestPlugin INSTANCE = new M2DocTestPlugin();

    /**
     * The implementation plugin for Eclipse.
     */
    private static Implementation plugin;

    /**
     * Listener that catch exception that would be put in the error log.
     */
    private ErrorLogListener errorLogListener = new ErrorLogListener();

    /**
     * The constructor.
     */
    public M2DocTestPlugin() {
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
     * Returns the listener that catch exception that would be put in the error log.
     * 
     * @return the listener that catch exception that would be put in the error log.
     */
    public ErrorLogListener getErrorLogListener() {
        return errorLogListener;
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

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
         */
        @Override
        public void start(BundleContext context) throws Exception {
            super.start(context);
            Platform.addLogListener(INSTANCE.errorLogListener);
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
         */
        @Override
        public void stop(BundleContext context) throws Exception {
            super.stop(context);
            Platform.removeLogListener(INSTANCE.errorLogListener);
        }
    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance;
     */
    public static M2DocTestPlugin getDefault() {
        return INSTANCE;
    }

}
