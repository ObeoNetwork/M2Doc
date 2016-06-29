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
package org.obeonetwork.m2doc.provider;

import com.google.common.collect.Maps;

import java.util.Map;

import org.eclipse.core.runtime.Status;
import org.obeonetwork.m2doc.M2DocPlugin;

/**
 * {@link ProviderRegistry} is used to register {@link IProvider} like {@link DiagramProvider}. All providers' instances are stored in a map
 * with their class qualified name as key.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public final class ProviderRegistry {
    /**
     * Single instance of {@link ProviderRegistry}.
     */
    public static final ProviderRegistry INSTANCE = new ProviderRegistry();
    /**
     * Default token used for registering services.
     */
    public static final String DEFAULT_TOKEN = "default";
    /**
     * The central map holding the providers. The key is the full qualified name of the provider and the value is the corresponding
     * instance.
     */
    private Map<String, IProvider> registry = Maps.newHashMap();

    /**
     * Private constructor to prevent creation of other instances.
     */
    private ProviderRegistry() {
    }

    /**
     * Clears the registry by removing all the registered services.
     */
    public void clear() {
        this.registry.clear();
    }

    /**
     * Registers the given provider in the cache map with its full qualified name.
     * 
     * @param provider
     *            the provider to register.
     */
    public void registerDiagramProvider(IProvider provider) {
        String classQualifiedName = provider.getClass().getName();
        if (registry.get(classQualifiedName) != null) {
            M2DocPlugin.log(new Status(Status.ERROR, M2DocPlugin.PLUGIN_ID,
                    "Problem while registering M2Doc Providers : the provider \"" + classQualifiedName
                        + "\" is already registered. The current implementation will not be used."));
        } else {
            registry.put(classQualifiedName, provider);
        }

    }

    /**
     * Remove the registered provider corresponding to the class of the given one.
     * 
     * @param provider
     *            the provider to remove from the registered provider.
     * @return true if such element exists and has been removed. False otherwise.
     */
    public boolean removeProvider(IProvider provider) {
        String classQualifiedName = provider.getClass().getName();
        IProvider providerToRemove = registry.get(classQualifiedName);
        if (providerToRemove != null) {
            registry.remove(classQualifiedName);
            return true;
        }
        return false;

    }

    /**
     * Returns the {@link IProvider} corresponding to the given qualified name.
     * 
     * @param providerFullQualifiedName
     *            the qualified name of the provider to retrieve.
     * @return the {@link IProvider} corresponding to the given qualified name. Null if no such element exists.
     */
    public IProvider getProvider(String providerFullQualifiedName) {
        if (providerFullQualifiedName != null) {
            return registry.get(providerFullQualifiedName.trim());
        }
        return null;
    }
}
