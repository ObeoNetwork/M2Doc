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
package org.obeonetwork.m2doc.services.test;

import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.services.ServiceRegistry;

//CHECKSTYLE:OFF
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceRegistry} test class.
 * 
 * @author Romain Guider
 */
public class ServiceRegistryTest {
    /**
     * {@link ServiceRegistry} instance used during testing.
     */
    private ServiceRegistry registry = ServiceRegistry.INSTANCE;

    @Before
    public void setUp() {
        registry.clear();
    }

    /**
     * Tests the register/getServicePackage couple with a single token.
     */
    @Test
    public void registerServicesTestSingleToken() {
        registry.registerServicePackage(ServicePackage1.class, "token1");
        registry.registerServicePackage(ServicePackage2.class, "token1");
        assertEquals(2, registry.getServicePackages("token1").size());
        assertEquals(ServicePackage1.class, registry.getServicePackages("token1").get(0));
        assertEquals(ServicePackage2.class, registry.getServicePackages("token1").get(1));
    }

    /**
     * Tests the register/getServicePackage couple with a two tokens.
     */
    @Test
    public void registerServicesTestTwoTokens() {
        registry.registerServicePackage(ServicePackage1.class, "token1");
        registry.registerServicePackage(ServicePackage2.class, "token1");
        registry.registerServicePackage(ServicePackage1.class, "token2");
        registry.registerServicePackage(ServicePackage2.class, "token2");
        registry.registerServicePackage(ServicePackage3.class, "token2");
        assertEquals(2, registry.getServicePackages("token1").size());
        assertEquals(ServicePackage1.class, registry.getServicePackages("token1").get(0));
        assertEquals(ServicePackage2.class, registry.getServicePackages("token1").get(1));
        assertEquals(3, registry.getServicePackages("token2").size());
        assertEquals(ServicePackage1.class, registry.getServicePackages("token2").get(0));
        assertEquals(ServicePackage2.class, registry.getServicePackages("token2").get(1));
        assertEquals(ServicePackage3.class, registry.getServicePackages("token2").get(2));
    }

    /**
     * Tests the remove method with two tokens.
     */
    @Test
    public void removeService() {
        registry.registerServicePackage(ServicePackage1.class, "token1");
        registry.registerServicePackage(ServicePackage2.class, "token1");
        registry.registerServicePackage(ServicePackage1.class, "token2");
        registry.registerServicePackage(ServicePackage2.class, "token2");
        registry.registerServicePackage(ServicePackage3.class, "token2");
        assertTrue(registry.remove(ServicePackage1.class, "token2"));
        assertFalse(registry.remove(ServicePackage1.class, "token2"));
        assertFalse(registry.remove(ServicePackage3.class, "token1"));
        assertEquals(2, registry.getServicePackages("token1").size());
        assertEquals(2, registry.getServicePackages("token2").size());
    }

    /**
     * Tests the unmodifiable character of returned service's lists with non
     * empty lists returned.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testServicePackageListsAreUnmodifiable() {
        registry.registerServicePackage(ServicePackage1.class, "token1");
        registry.getServicePackages("token1").add(ServicePackage3.class);
    }

    /**
     * Tests the unmodifiable character of returned service's lists with empty
     * lists returned.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testServicePackageEmptyListsAreUnmodifiable() {
        registry.getServicePackages("token1").add(ServicePackage3.class);
    }
}
