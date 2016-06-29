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
package org.obeonetwork.m2doc.provider.test;

import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.services.ServiceRegistry;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceRegistry} test class.
 * 
 * @author Romain Guider
 */
public class ProviderRegistryTest {
    /**
     * {@link ServiceRegistry} instance used during testing.
     */
    private ProviderRegistry registry = ProviderRegistry.INSTANCE;

    @Before
    public void setUp() {
        registry.clear();
    }

    /**
     * Tests the provider registration.
     */
    @Test
    public void registerProviderTest() {
        registry.registerDiagramProvider(new SiriusDiagramByTitleProvider());
        registry.registerDiagramProvider(new SiriusDiagramByRepresentationAndEObjectProvider());
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider") instanceof SiriusDiagramByTitleProvider);
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider") instanceof SiriusDiagramByRepresentationAndEObjectProvider);
    }

    /**
     * Tests the provider retrieving when the given provider qualified name is registered.
     */
    @Test
    public void registerProviderRetrievingTest() {
        registry.registerDiagramProvider(new SiriusDiagramByTitleProvider());
        registry.registerDiagramProvider(new SiriusDiagramByRepresentationAndEObjectProvider());
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider") instanceof SiriusDiagramByRepresentationAndEObjectProvider);
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider") instanceof SiriusDiagramByTitleProvider);
    }

    /**
     * Tests the provider retrieving when given provider qualified name is not registered.
     */
    @Test
    public void registerInvalidProviderRetrievingTest() {
        registry.registerDiagramProvider(new SiriusDiagramByTitleProvider());
        registry.registerDiagramProvider(new SiriusDiagramByRepresentationAndEObjectProvider());
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider") instanceof SiriusDiagramByRepresentationAndEObjectProvider);
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider") instanceof SiriusDiagramByTitleProvider);
    }

    /**
     * Tests the provider unregistration.
     */
    @Test
    public void unregisterProviderTest() {
        registry.registerDiagramProvider(new SiriusDiagramByTitleProvider());
        registry.registerDiagramProvider(new SiriusDiagramByRepresentationAndEObjectProvider());
        assertTrue(registry.removeProvider(new SiriusDiagramByTitleProvider()));
        assertNull(registry.getProvider("org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider"));
        assertFalse(registry.removeProvider(new SiriusDiagramByTitleProvider()));
        assertTrue(registry.removeProvider(new SiriusDiagramByRepresentationAndEObjectProvider()));
        assertNull(
                registry.getProvider("org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider"));
        assertFalse(registry.removeProvider(new SiriusDiagramByRepresentationAndEObjectProvider()));
    }

}
