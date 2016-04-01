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

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * {@link ServiceRegistry} is used to register AQL services. When launching a
 * generation, a set of services are drawn from the registry. Registring
 * services may have a side effect on other m2doc integration. The registry is
 * thus a map from tokens to service's lists so as to avoid service conflicts
 * between two applications of the document generation.
 * 
 * @author Romain Guider
 *
 */
public class ServiceRegistry {
	/**
	 * Single instance of {@link ServiceRegistry}.
	 */
	public static final ServiceRegistry INSTANCE = new ServiceRegistry();
	/**
	 * Default token used for registering services.
	 */
	public static final String DEFAULT_TOKEN = "default";

	/**
	 * Private constructor to prevent creation of other instances.
	 */
	private ServiceRegistry() {

	}

	/**
	 * The central map holding the services.
	 */
	private Map<String, List<Class<?>>> registry = Maps.newHashMap();

	/**
	 * Register a service package under the specified token.
	 * 
	 * @param servicePackage
	 *            the class that contains the registered services.
	 * @param token
	 *            the token under which services must be stored.
	 */
	public void registerServicePackage(Class<?> servicePackage, String token) {
		List<Class<?>> services = registry.get(token);
		if (services == null) {
			services = Lists.newArrayList();
			registry.put(token, services);
		}
		services.add(servicePackage);
	}

	/**
	 * Removes a service package from a service token.
	 * 
	 * @param servicePackage
	 *            the service package to remove
	 * @param token
	 *            the token underwich the services must be removed.
	 * @return <code>true</code> if the service was actually present under the
	 *         specified token in the registry, <code>false</code> otherwise.
	 */
	public boolean remove(Class<?> servicePackage, String token) {
		List<Class<?>> services = registry.get(token);
		if (services == null) {
			return false;
		} else {
			return services.remove(servicePackage);
		}
	}

	/**
	 * Returns the set of services registred under the specified token.
	 * 
	 * @param token
	 *            the token for which services are required.
	 * @return an unmodifiable version of the list of services registered under
	 *         the specified token.
	 */
	public List<Class<?>> getServicePackages(String token) {
		List<Class<?>> result = registry.get(token);
		if (result == null) {
			result = Collections.emptyList();
		} else {
			result = Collections.unmodifiableList(result);
		}
		return result;
	}

	/**
	 * Clears the registry by removing all the registered services.
	 */
	public void clear() {
		this.registry.clear();
	}
}
