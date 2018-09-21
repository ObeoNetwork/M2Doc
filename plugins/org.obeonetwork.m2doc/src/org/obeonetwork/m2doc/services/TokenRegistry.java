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
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * {@link TokenRegistry} is used to register AQL services and {@link EPackage#getNsURI() nsURI}. When launching a
 * generation, a set of services are drawn from the registry. Registring
 * services may have a side effect on other m2doc integration. The registry is
 * thus a map from tokens to service's lists so as to avoid service conflicts
 * between two applications of the document generation.
 * 
 * @author Romain Guider
 */
public final class TokenRegistry {

    /**
     * Single instance of {@link TokenRegistry}.
     */
    public static final TokenRegistry INSTANCE = new TokenRegistry();

    /**
     * Default token used for registering services.
     */
    public static final String DEFAULT_TOKEN = "default";

    /**
     * Mapping from token name to bundle name to list of service class names.
     */
    private Map<String, Map<String, List<String>>> servicesRegistry = new LinkedHashMap<>();

    /**
     * Mapping from token name to list of {@link EPackage#getNsURI() nsURI}.
     */
    private Map<String, List<String>> packagesRegistry = new LinkedHashMap<>();

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
    public void registerServices(String tokenName, String bundleName, List<String> services) {
        Map<String, List<String>> map = servicesRegistry.get(tokenName);
        if (map == null) {
            map = new LinkedHashMap<>();
            servicesRegistry.put(tokenName, map);
        }
        List<String> list = map.get(bundleName);
        if (list == null) {
            list = new ArrayList<>();
            map.put(bundleName, list);
        }

        list.addAll(services);
    }

    /**
     * Registers the given {@link List} of package names to the given token name.
     * 
     * @param tokenName
     *            the token name
     * @param packages
     *            the {@link List} of package names
     */
    public void registerPackages(String tokenName, List<String> packages) {
        List<String> list = packagesRegistry.get(tokenName);
        if (list == null) {
            list = new ArrayList<>();
            packagesRegistry.put(tokenName, list);
        }

        list.addAll(packages);
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
    public void unregisterServices(String tokenName, String bundleName, List<String> services) {
        final Map<String, List<String>> map = servicesRegistry.get(tokenName);
        if (map != null) {
            final List<String> list = map.get(bundleName);
            if (list.removeAll(services) && list.isEmpty()) {
                map.remove(bundleName);
                if (map.isEmpty()) {
                    servicesRegistry.remove(tokenName);
                }
            }
        }
    }

    /**
     * Removes the given {@link List} of package names from the given token name.
     * 
     * @param tokenName
     *            the token name
     * @param packages
     *            the {@link List} of package names
     */
    public void unregisterPackages(String tokenName, List<String> packages) {
        final List<String> list = packagesRegistry.get(tokenName);
        if (list != null && list.removeAll(packages) && list.isEmpty()) {
            packagesRegistry.remove(tokenName);
        }
    }

    /**
     * Gets the mapping of bundle name to list of class names.
     * 
     * @param tokenName
     *            the token name
     * @return the mapping of bundle name to list of class names
     */
    public Map<String, List<String>> getServices(String tokenName) {
        Map<String, List<String>> result = servicesRegistry.get(tokenName);

        if (result == null) {
            result = Collections.emptyMap();
        } else {
            result = Collections.unmodifiableMap(result);
        }

        return result;
    }

    /**
     * Gets the {@link List} of package names for the given token name.
     * 
     * @param tokenName
     *            the token name
     * @return the {@link List} of package names for the given token name
     */
    public List<String> getPackages(String tokenName) {
        List<String> result = packagesRegistry.get(tokenName);

        if (result == null) {
            result = Collections.emptyList();
        } else {
            result = Collections.unmodifiableList(result);
        }

        return result;
    }

    /**
     * Clears the registry by removing all the registered services and packages.
     */
    public void clear() {
        servicesRegistry.clear();
        packagesRegistry.clear();
    }

    /**
     * Gets the {@link Set} of {@link TokenRegistry#registerServices(String, String, List) registered services} and
     * {@link TokenRegistry#registerPackages(String, List) registered packages} tokens.
     * 
     * @return the {@link Set} of {@link TokenRegistry#registerServices(String, String, List) registered services} and
     *         {@link TokenRegistry#registerPackages(String, List) registered packages} tokens
     */
    public Set<String> getRegisteredTokens() {
        final Set<String> res = new LinkedHashSet<>();

        res.addAll(servicesRegistry.keySet());
        res.addAll(packagesRegistry.keySet());

        return res;
    }

    /**
     * Gets the {@link List} of selected tokens for the given {@link TemplateCustomProperties}.
     * 
     * @param properties
     *            the {@link TemplateCustomProperties}
     * @return the {@link List} of selected tokens for the given {@link TemplateCustomProperties}
     */
    public List<String> getSelectedToken(TemplateCustomProperties properties) {
        final List<String> res = new ArrayList<>();

        for (String tokenName : getRegisteredTokens()) {
            boolean isSelected = true;
            for (Entry<String, List<String>> entry : getServices(tokenName).entrySet()) {
                final String bundleName = entry.getKey();
                for (String className : entry.getValue()) {
                    if (!bundleName.equals(properties.getServiceClasses().get(className))) {
                        isSelected = false;
                        break;
                    }
                }
            }
            final Set<String> packages = new HashSet<>(properties.getPackagesURIs());
            for (String pkg : getPackages(tokenName)) {
                if (!packages.contains(pkg)) {
                    isSelected = false;
                    break;
                }
            }
            if (isSelected) {
                res.add(tokenName);
            }
        }

        return res;
    }

    /**
     * Adds the given token name to the given {@link TemplateCustomProperties}.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties}
     * @param tokenName
     *            the token name
     */
    public void selectToken(TemplateCustomProperties customProperties, String tokenName) {
        for (Entry<String, List<String>> entry : getServices(tokenName).entrySet()) {
            final String bundleName = entry.getKey();
            for (String cls : entry.getValue()) {
                customProperties.getServiceClasses().put(cls, bundleName);
            }
        }
        for (String nsURI : getPackages(tokenName)) {
            if (!customProperties.getPackagesURIs().contains(nsURI)) {
                customProperties.getPackagesURIs().add(nsURI);
            }
        }
    }

    /**
     * Removes the given token name from the given {@link TemplateCustomProperties}.
     * 
     * @param properties
     *            the {@link TemplateCustomProperties}
     * @param tokenName
     *            the token name
     */
    public void deselectToken(TemplateCustomProperties properties, String tokenName) {
        final List<String> selectedTokens = getSelectedToken(properties);
        selectedTokens.remove(tokenName);

        for (List<String> classes : getServices(tokenName).values()) {
            for (String cls : classes) {
                properties.getServiceClasses().remove(cls);
            }
        }
        properties.getPackagesURIs().removeAll(getPackages(tokenName));
        for (String token : selectedTokens) {
            selectToken(properties, token);
        }
    }

}
