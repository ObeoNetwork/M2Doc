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
package org.obeonetwork.m2doc.services;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.obeonetwork.m2doc.M2DocPlugin;

/**
 * Listener that registers services that are declared through an extension.
 * 
 * @author Romain Guider
 */
public class DeclaredServicesListener implements IRegistryEventListener {
    /**
     * Unique ID of the extension point.
     */
    private static final String SERVICEREGISTERY_ID = "org.obeonetwork.m2doc.services.register";
    /**
     * Name of the service element.
     */
    private static final String SERVICE_ELEMENT_NAME = "service";
    /**
     * Name of the attribute used to declare the service's class name.
     */
    private static final String SERVICE_CLASS_ATTR_NAME = "serviceClass";
    /**
     * Name of the attribute used to declare the service's class name.
     */
    private static final String SERVICE_TOKEN_ATTR_NAME = "serviceToken";

    /**
     * Creates and initializes the service registry listener.
     */
    public DeclaredServicesListener() {
        // initializes the extensions
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint(SERVICEREGISTERY_ID);
        for (IExtension extension : extensionPoint.getExtensions()) {
            add(extension);
        }
    }

    /**
     * adds an extension to this.
     * 
     * @param extension
     *            the extension
     */
    private void add(IExtension extension) {
        for (IConfigurationElement confElt : extension.getConfigurationElements()) {
            if (SERVICE_ELEMENT_NAME.equals(confElt.getName())) {
                try {
                    IServiceHolder holder = (IServiceHolder) confElt.createExecutableExtension(SERVICE_CLASS_ATTR_NAME);
                    String token = confElt.getAttribute(SERVICE_TOKEN_ATTR_NAME);
                    ServiceRegistry.INSTANCE.registerServicePackage(holder.getServiceClass(), token);
                } catch (CoreException e) {
                    M2DocPlugin.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID, Status.ERROR,
                            "Problem while registering M2Doc Services : " + e.getMessage(), e));
                }
            }
        }
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (SERVICEREGISTERY_ID.equals(extension.getExtensionPointUniqueIdentifier())) {
                add(extension);
            }
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (SERVICEREGISTERY_ID.equals(extension.getExtensionPointUniqueIdentifier())) {
                for (IConfigurationElement confElt : extension.getConfigurationElements()) {
                    if (SERVICE_ELEMENT_NAME.equals(confElt.getName())) {
                        try {
                            IServiceHolder holder = (IServiceHolder) confElt
                                    .createExecutableExtension(SERVICE_CLASS_ATTR_NAME);
                            String token = confElt.getAttribute(SERVICE_TOKEN_ATTR_NAME);
                            ServiceRegistry.INSTANCE.remove(holder.getServiceClass(), token);
                        } catch (CoreException e) {
                            M2DocPlugin.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID, Status.ERROR,
                                    "Problem while registering M2Doc Services : " + e.getMessage(), e));
                        }

                    }
                }
            }
        }
    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // Do nothing.
    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {
        // Do nothing.
    }

}
