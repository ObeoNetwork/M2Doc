/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Activator used to init extensions provided.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class M2DocSiriusPlugin extends AbstractUIPlugin {
    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.sirius"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    private static M2DocSiriusPlugin plugin;

    /**
     * Bundle context.
     */
    private static BundleContext context;

    static BundleContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
     * BundleContext)
     */
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        M2DocSiriusPlugin.context = bundleContext;
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        M2DocSiriusPlugin.context = null;
    }

    /**
     * Return this plugin's instance.
     * 
     * @return this plugin's instance.
     */
    public static M2DocSiriusPlugin getDefault() {
        return plugin;
    }
}
