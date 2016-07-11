package org.obeonetwork.m2doc.sirius.tests;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
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
public class M2DocSiriusTestPlugin extends Plugin {

    // The plug-in ID
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.sirius.tests"; //$NON-NLS-1$

    // The shared instance
    private static M2DocSiriusTestPlugin plugin;

    private static Set<Viewpoint> viewpoints;

    /**
     * The constructor
     */
    public M2DocSiriusTestPlugin() {
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
        viewpoints = new HashSet<Viewpoint>();
        viewpoints
                .addAll(ViewpointRegistry.getInstance().registerFromPlugin(PLUGIN_ID + "/description/genconf.odesign"));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        if (viewpoints != null) {
            for (final Viewpoint viewpoint : viewpoints) {
                ViewpointRegistry.getInstance().disposeFromPlugin(viewpoint);
            }
            viewpoints.clear();
            viewpoints = null;
        }
        super.stop(context);
    }

    /**
     * Returns the shared instance;
     *
     * @return the shared instance;
     */
    public static M2DocSiriusTestPlugin getDefault() {
        return plugin;
    }

}
