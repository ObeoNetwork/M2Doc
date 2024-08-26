/*******************************************************************************
 *  Copyright (c) 2016, 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.obeonetwork.m2doc.ide.services.configurator.ServicesConfiguratorRegistryListener;
import org.obeonetwork.m2doc.ide.ui.util.ClassPropertyUpdaterRegistryListener;
import org.obeonetwork.m2doc.ide.ui.util.IClassPropertyUpdater;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle.
 */
public class Activator extends AbstractUIPlugin {

    /**
     * The delete image key.
     */
    public static final String DELETE_IMG_KEY = "delete";

    /**
     * The add image key.
     */
    public static final String ADD_IMG_KEY = "add";

    /**
     * The plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.ide.ui"; //$NON-NLS-1$

    /**
     * The shared instance.
     */
    private static Activator plugin;

    /**
     * The {@link ClassPropertyUpdaterRegistryListener}.
     */
    private static final ClassPropertyUpdaterRegistryListener classPropertyUpdaterRegistryListener = new ClassPropertyUpdaterRegistryListener();

    /**
     * The registered {@link IClassPropertyUpdater}.
     */
    private static IClassPropertyUpdater classPropertyUpdater;

    /**
     * The constructor.
     */
    public Activator() {
    }

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        plugin = this;
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addListener(classPropertyUpdaterRegistryListener,
                ServicesConfiguratorRegistryListener.SERVICES_CONFIGURATOR_EXTENSION_POINT);
        classPropertyUpdaterRegistryListener.parseInitialContributions();
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        plugin = null;
        super.stop(context);
        final IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeListener(classPropertyUpdaterRegistryListener);
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
            reg.put(ADD_IMG_KEY, ImageDescriptor
                    .createFromURL(new URL("platform:/plugin/org.obeonetwork.m2doc.ide.ui/icons/add.gif")));
            reg.put(DELETE_IMG_KEY, ImageDescriptor
                    .createFromURL(new URL("platform:/plugin/org.obeonetwork.m2doc.ide.ui/icons/delete.gif")));
        } catch (MalformedURLException e) {
            getLog().log(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e));
        }
    }

    /**
     * Gets the registered {@link IClassPropertyUpdater}.
     * 
     * @return the registered {@link IClassPropertyUpdater}
     */
    public static IClassPropertyUpdater getClassPropertyUpdater() {
        return classPropertyUpdater;
    }

    /**
     * Registers the given {@link IClassPropertyUpdater}.
     * 
     * @param updater
     *            the {@link IClassPropertyUpdater} to update
     */
    public static void registerClassPropertyUpdater(IClassPropertyUpdater updater) {
        classPropertyUpdater = updater;
    }
}
