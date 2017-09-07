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
import org.obeonetwork.m2doc.services.ServiceRegistry;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;

/**
 * {@link ServiceRegistry} test class.
 * 
 * @author Romain Guider
 */
public class ServiceRegistryTests {
    /**
     * {@link ServiceRegistry} instance used during testing.
     */
    private ServiceRegistry registry = new ServiceRegistry();

    /**
     * Tests the register/getServicePackage couple with a single token.
     */
    @Test
    public void registerServicesTestSingleToken() {
        final List<String> services = new ArrayList<String>();
        services.add(ServicePackage1.class.getCanonicalName());
        services.add(ServicePackage2.class.getCanonicalName());
        registry.register("token1", "bundle", services);

        final Map<String, List<String>> map = registry.getServicePackages("token1");
        assertEquals(1, map.size());
        final List<String> list = map.get("bundle");
        assertEquals(2, list.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list.get(1));
    }

    /**
     * Tests the register/getServicePackage couple with a two tokens.
     */
    @Test
    public void registerServicesTestTwoTokens() {
        final List<String> services1 = new ArrayList<String>();
        services1.add(ServicePackage1.class.getCanonicalName());
        services1.add(ServicePackage2.class.getCanonicalName());
        final List<String> services2 = new ArrayList<String>();
        services2.add(ServicePackage1.class.getCanonicalName());
        services2.add(ServicePackage2.class.getCanonicalName());
        services2.add(ServicePackage3.class.getCanonicalName());

        registry.register("token1", "bundle", services1);
        registry.register("token2", "bundle", services2);

        final Map<String, List<String>> map1 = registry.getServicePackages("token1");
        assertEquals(1, map1.size());
        final List<String> list1 = map1.get("bundle");
        assertEquals(2, list1.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list1.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list1.get(1));
        final Map<String, List<String>> map2 = registry.getServicePackages("token2");
        assertEquals(1, map2.size());
        final List<String> list2 = map2.get("bundle");
        assertEquals(3, list2.size());
        assertEquals(ServicePackage1.class.getCanonicalName(), list2.get(0));
        assertEquals(ServicePackage2.class.getCanonicalName(), list2.get(1));
        assertEquals(ServicePackage3.class.getCanonicalName(), list2.get(2));
    }

    /**
     * Tests the remove method with two tokens.
     */
    @Test
    public void removeService() {
        final List<String> services = new ArrayList<String>();
        services.add(ServicePackage1.class.getCanonicalName());
        services.add(ServicePackage2.class.getCanonicalName());
        services.add(ServicePackage3.class.getCanonicalName());

        registry.register("token", "bundle", services);

        final List<String> servicesToRemove = new ArrayList<String>();
        servicesToRemove.add(ServicePackage1.class.getCanonicalName());
        servicesToRemove.add(ServicePackage2.class.getCanonicalName());

        registry.remove("token", "bundle", servicesToRemove);

        final Map<String, List<String>> map = registry.getServicePackages("token");
        assertEquals(1, map.size());
        final List<String> list = map.get("bundle");
        assertEquals(1, list.size());
        assertEquals(ServicePackage3.class.getCanonicalName(), list.get(0));
    }

}
