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
package org.obeonetwork.m2doc.tests.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.obeonetwork.m2doc.services.TokenRegistry;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;

/**
 * {@link TokenRegistry} test class.
 * 
 * @author Romain Guider
 */
public class ServiceRegistryTests {

    /**
     * {@link TokenRegistry} instance used during testing.
     */
    private TokenRegistry registry = new TokenRegistry();

    @Test
    public void registerServicesTestSingleToken() {
        final List<String> services = new ArrayList<>();
        services.add(ServicePackage1.class.getCanonicalName());
        services.add(ServicePackage2.class.getCanonicalName());
        registry.registerServices("token1", "bundle", services);

        final Map<String, List<String>> map = registry.getServices("token1");
        assertEquals(1, map.size());
        final List<String> list = map.get("bundle");
        assertEquals(2, list.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list.get(1));
    }

    @Test
    public void registerServicesTestTwoTokens() {
        final List<String> services1 = new ArrayList<>();
        services1.add(ServicePackage1.class.getCanonicalName());
        services1.add(ServicePackage2.class.getCanonicalName());
        final List<String> services2 = new ArrayList<>();
        services2.add(ServicePackage1.class.getCanonicalName());
        services2.add(ServicePackage2.class.getCanonicalName());
        services2.add(ServicePackage3.class.getCanonicalName());

        registry.registerServices("token1", "bundle", services1);
        registry.registerServices("token2", "bundle", services2);

        final Map<String, List<String>> map1 = registry.getServices("token1");
        assertEquals(1, map1.size());
        final List<String> list1 = map1.get("bundle");
        assertEquals(2, list1.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list1.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list1.get(1));
        final Map<String, List<String>> map2 = registry.getServices("token2");
        assertEquals(1, map2.size());
        final List<String> list2 = map2.get("bundle");
        assertEquals(3, list2.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list2.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list2.get(1));
        assertEquals(ServicePackage3.class.getCanonicalName(), list2.get(2));
    }

    @Test
    public void unregisterServices() {
        final List<String> services = new ArrayList<>();
        services.add(ServicePackage1.class.getCanonicalName());
        services.add(ServicePackage2.class.getCanonicalName());
        services.add(ServicePackage3.class.getCanonicalName());

        registry.registerServices("token", "bundle", services);

        final List<String> servicesToRemove = new ArrayList<>();
        servicesToRemove.add(ServicePackage1.class.getCanonicalName());
        servicesToRemove.add(ServicePackage2.class.getCanonicalName());

        registry.unregisterServices("token", "bundle", servicesToRemove);

        final Map<String, List<String>> map = registry.getServices("token");
        assertEquals(1, map.size());
        final List<String> list = map.get("bundle");
        assertEquals(1, list.size());
        assertEquals(ServicePackage3.class.getCanonicalName(), list.get(0));
    }

    @Test
    public void registerPackagesTestSingleToken() {
        final List<String> packages = new ArrayList<>();
        packages.add("someNsURI1");
        packages.add("someNsURI2");
        registry.registerPackages("token1", packages);

        final List<String> list = registry.getPackages("token1");
        assertEquals(2, list.size());
        assertEquals("someNsURI1", list.get(0));
        assertEquals("someNsURI2", list.get(1));
    }

    @Test
    public void registerPackagesTestTwoTokens() {
        final List<String> packages1 = new ArrayList<>();
        packages1.add("someNsURI1");
        packages1.add("someNsURI2");
        final List<String> packages2 = new ArrayList<>();
        packages2.add("someNsURI1");
        packages2.add("someNsURI2");
        packages2.add("someNsURI3");

        registry.registerPackages("token1", packages1);
        registry.registerPackages("token2", packages2);

        final List<String> list1 = registry.getPackages("token1");
        assertEquals(2, list1.size());
        assertEquals("someNsURI1", list1.get(0));
        assertEquals("someNsURI2", list1.get(1));
        final List<String> list2 = registry.getPackages("token2");
        assertEquals(3, list2.size());
        assertEquals("someNsURI1", list2.get(0));
        assertEquals("someNsURI2", list2.get(1));
        assertEquals("someNsURI3", list2.get(2));
    }

    @Test
    public void unregisterPackages() {
        final List<String> packages = new ArrayList<>();
        packages.add("someNsURI1");
        packages.add("someNsURI2");
        packages.add("someNsURI3");

        registry.registerPackages("token", packages);

        final List<String> packagesToRemove = new ArrayList<>();
        packagesToRemove.add("someNsURI1");
        packagesToRemove.add("someNsURI2");

        registry.unregisterPackages("token", packagesToRemove);

        final List<String> list = registry.getPackages("token");
        assertEquals(1, list.size());
        assertEquals("someNsURI3", list.get(0));
    }

}
