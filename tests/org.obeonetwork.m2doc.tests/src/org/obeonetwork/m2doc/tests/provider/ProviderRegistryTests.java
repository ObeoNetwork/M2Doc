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
package org.obeonetwork.m2doc.tests.provider;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.AbstractDiagramProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * {@link ProviderRegistry} test class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class ProviderRegistryTests {
    /**
     * {@link ProviderRegistry} instance used during testing.
     */
    private ProviderRegistry registry = ProviderRegistry.INSTANCE;

    @Before
    public void setUp() {
        registry.clear();
    }

    /**
     * Provider for testing registration
     * 
     * @author pguilet<pierre.guilet@obeo.fr>
     */
    private class ProviderTest extends AbstractDiagramProvider {

        /*
         * (non-Javadoc)
         * 
         * @see org.obeonetwork.m2doc.provider.IProvider#getOptionTypes()
         */
        @Override
        public Map<String, OptionType> getOptionTypes() {
            return null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.obeonetwork.m2doc.provider.DiagramProvider#getRepresentationImagePath(java.util.Map)
         */
        @Override
        public List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException {
            return null;
        }

        @Override
        public boolean isDefault() {
            return false;
        }

        @Override
        public List<ProviderValidationMessage> validate(Map<String, Object> options) {
            return Collections.emptyList();
        }

    }

    /**
     * Tests the provider registration and get method.
     */
    @Test
    public void registerProviderTest() {
        registry.registerProvider(new ProviderTest());
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.tests.provider.ProviderRegistryTests$ProviderTest") instanceof ProviderTest);
    }

    /**
     * Tests the provider retrieval when given provider qualified name is not registered.
     */
    @Test
    public void registerInvalidProviderRetrievingTest() {
        assertNull(registry.getProvider("wrong"));
    }

    /**
     * Tests the provider unregistration.
     */
    @Test
    public void unregisterProviderTest() {
        registry.registerProvider(new ProviderTest());
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.tests.provider.ProviderRegistryTests$ProviderTest") instanceof ProviderTest);
        assertTrue(registry.removeProvider(new ProviderTest()));
        assertNull(registry.getProvider("org.obeonetwork.m2doc.tests.provider.ProviderRegistryTests$ProviderTest"));
        assertFalse(registry.removeProvider(new ProviderTest()));
    }

}
