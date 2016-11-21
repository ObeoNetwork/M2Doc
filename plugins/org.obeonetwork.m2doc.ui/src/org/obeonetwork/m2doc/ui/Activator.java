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
package org.obeonetwork.m2doc.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.obeonetwork.m2doc.services.DeclaredServicesListener;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

    public static final String DELETE_IMG_KEY = "delete";

    public static final String ADD_IMG_KEY = "add";

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.ui"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    private static Activator plugin;

    /**
     * The constructor.
     */
    public Activator() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.
     * BundleContext)
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        Platform.getExtensionRegistry().addListener(new DeclaredServicesListener());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.
     * BundleContext)
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);

    }

    /**
     * Returns the shared instance.
     *
     * @return the shared instance
     */
    public static Activator getDefault() {
        return plugin;
    }

    /**
     * Returns an image descriptor for the image file at the given plug-in
     * relative path.
     *
     * @param path
     *            the path
     * @return the image descriptor
     */
    public static ImageDescriptor getImageDescriptor(String path) {
        return imageDescriptorFromPlugin(PLUGIN_ID, path);
    }

    @Override
    protected void initializeImageRegistry(ImageRegistry reg) {
        try {
            reg.put(ADD_IMG_KEY,
                    ImageDescriptor.createFromURL(new URL("platform:/plugin/org.obeonetwork.m2doc.ui/icons/add.gif")));
            reg.put(DELETE_IMG_KEY, ImageDescriptor
                    .createFromURL(new URL("platform:/plugin/org.obeonetwork.m2doc.ui/icons/delete.gif")));
        } catch (MalformedURLException e) {
            getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e));
        }
    }
}
