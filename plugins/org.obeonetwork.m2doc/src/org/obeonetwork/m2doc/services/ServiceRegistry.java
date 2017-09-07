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

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * {@link ServiceRegistry} is used to register AQL services. When launching a
 * generation, a set of services are drawn from the registry. Registring
 * services may have a side effect on other m2doc integration. The registry is
 * thus a map from tokens to service's lists so as to avoid service conflicts
 * between two applications of the document generation.
 * 
 * @author Romain Guider
 */
public final class ServiceRegistry {
    /**
     * Single instance of {@link ServiceRegistry}.
     */
    public static final ServiceRegistry INSTANCE = new ServiceRegistry();
    /**
     * Default token used for registering services.
     */
    public static final String DEFAULT_TOKEN = "default";
    /**
     * Mapping from token name to bundle name to list of service class names.
     */
    private Map<String, Map<String, List<String>>> registry = new LinkedHashMap<String, Map<String, List<String>>>();

    /**
     * Registers the given {@link List} of service class names to the given token name.
     * 
     * @param tokenName
     *            the token name
     * @param bundleName
     *            the bundle name
     * @param services
     *            the {@link List} of service class names
     */
    public void register(String tokenName, String bundleName, List<String> services) {
        Map<String, List<String>> map = registry.get(tokenName);
        if (map == null) {
            map = new LinkedHashMap<String, List<String>>();
            registry.put(tokenName, map);
        }
        List<String> list = map.get(bundleName);
        if (list == null) {
            list = new ArrayList<String>();
            map.put(bundleName, list);
        }

        list.addAll(services);
    }

    /**
     * Removes the given {@link List} of service class names from the given token name.
     * 
     * @param tokenName
     *            the token name
     * @param bundleName
     *            the bundle name
     * @param services
     *            the {@link List} of service class names
     */
    public void remove(String tokenName, String bundleName, List<String> services) {
        final Map<String, List<String>> map = registry.get(tokenName);
        if (map != null) {
            final List<String> list = map.get(bundleName);
            if (list.removeAll(services) && list.isEmpty()) {
                map.remove(bundleName);
                if (map.isEmpty()) {
                    registry.remove(tokenName);
                }
            }
        }
    }

    /**
     * Gets the mapping of bundle name to list of class names.
     * 
     * @param tokenName
     *            the token name
     * @return the mapping of bundle name to list of class names
     */
    public Map<String, List<String>> getServicePackages(String tokenName) {
        Map<String, List<String>> result = registry.get(tokenName);

        if (result == null) {
            result = Collections.emptyMap();
        } else {
            result = Collections.unmodifiableMap(result);
        }

        return result;
    }

    /**
     * Clears the registry by removing all the registered services.
     */
    public void clear() {
        registry.clear();
    }

    /**
     * Gets the {@link Set} of {@link ServiceRegistry#registerServicePackage(Class, String) registered} service tokens.
     * 
     * @return the {@link Set} of {@link ServiceRegistry#registerServicePackage(Class, String) registered} service tokens
     */
    public Set<String> getRegisteredTokens() {
        return registry.keySet();
    }

}
