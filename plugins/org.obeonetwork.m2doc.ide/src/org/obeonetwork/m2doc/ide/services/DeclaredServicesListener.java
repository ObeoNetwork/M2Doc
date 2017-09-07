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
package org.obeonetwork.m2doc.ide.services;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.obeonetwork.m2doc.services.ServiceRegistry;

/**
 * Listener that registers services that are declared through an extension.
 * 
 * @author Romain Guider
 */
public class DeclaredServicesListener implements IRegistryEventListener {
    /**
     * Unique ID of the extension point.
     */
    public static final String SERVICE_REGISTERY_EXTENSION_POINT = "org.obeonetwork.m2doc.ide.services.register";
    /**
     * Name of the service element.
     */
    private static final String TOKEN_ELEMENT_NAME = "token";
    /**
     * Name of the service element.
     */
    private static final String SERVICE_ELEMENT_NAME = "service";
    /**
     * Name of the attribute used to declare the service's class name.
     */
    private static final String SERVICE_CLASS_ATTR_NAME = "class";
    /**
     * Name of the attribute used to declare the service's class name.
     */
    private static final String SERVICE_TOKEN_ATTR_NAME = "name";

    /**
     * Parses initial contributions.
     */
    public void parseInitialContributions() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IExtensionPoint extensionPoint = registry.getExtensionPoint(SERVICE_REGISTERY_EXTENSION_POINT);
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
        for (IConfigurationElement element : extension.getConfigurationElements()) {
            if (TOKEN_ELEMENT_NAME.equals(element.getName())) {
                final String tokenName = element.getAttribute(SERVICE_TOKEN_ATTR_NAME);
                final String bundleName = extension.getContributor().getName();
                final List<String> services = new ArrayList<String>();
                for (IConfigurationElement child : element.getChildren()) {
                    final String className = child.getAttribute(SERVICE_CLASS_ATTR_NAME);
                    services.add(className);
                }
                ServiceRegistry.INSTANCE.register(tokenName, bundleName, services);
            }
        }
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (SERVICE_REGISTERY_EXTENSION_POINT.equals(extension.getExtensionPointUniqueIdentifier())) {
                add(extension);
            }
        }
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            if (SERVICE_REGISTERY_EXTENSION_POINT.equals(extension.getExtensionPointUniqueIdentifier())) {
                for (IConfigurationElement element : extension.getConfigurationElements()) {
                    if (TOKEN_ELEMENT_NAME.equals(element.getName())) {
                        final String tokenName = element.getAttribute(SERVICE_TOKEN_ATTR_NAME);
                        final String bundleName = extension.getContributor().getName();
                        final List<String> services = new ArrayList<String>();
                        for (IConfigurationElement child : element.getChildren()) {
                            final String className = child.getAttribute(SERVICE_CLASS_ATTR_NAME);
                            services.add(className);
                        }
                        ServiceRegistry.INSTANCE.remove(tokenName, bundleName, services);
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
