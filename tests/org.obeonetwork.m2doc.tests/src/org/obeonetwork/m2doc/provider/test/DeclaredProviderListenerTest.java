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

import org.eclipse.core.runtime.Platform;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.DeclaredProviderListener;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.services.ServiceRegistry;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider;
import org.obeonetwork.m2doc.test.ErrorLogListener;
import org.obeonetwork.m2doc.test.M2DocTestPlugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * {@link ServiceRegistry} test class.
 * 
 * @author Romain Guider
 */
public class DeclaredProviderListenerTest {
    /**
     * {@link ServiceRegistry} instance used during testing.
     */
    private ProviderRegistry registry = ProviderRegistry.INSTANCE;
    /**
     * Listener that catch exception that would be put in the error log.
     */
    private ErrorLogListener errorLogListener;

    @Before
    public void setUp() {
        errorLogListener = M2DocTestPlugin.getDefault().getErrorLogListener();
        Platform.addLogListener(errorLogListener);
    }

    /**
     * Tests that the {@link DeclaredProviderListener} has been initialized with all available providers.
     */
    @Test
    public void listenerInitializedTest() {
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider") instanceof SiriusDiagramByTitleProvider);
        assertTrue(registry.getProvider(
                "org.obeonetwork.m2doc.sirius.SiriusDiagramByRepresentationAndEObjectProvider") instanceof SiriusDiagramByRepresentationAndEObjectProvider);
    }

    /**
     * Tests that the {@link DeclaredProviderListener} initialization has logged an error in the error log.
     */
    @Test
    public void listenerInitializedErrorTest() {
        assertEquals(2, errorLogListener.getAllStatus().size());
        assertEquals(
                "Problem while registering M2Doc Providers : Plug-in org.obeonetwork.m2doc.tests was unable to load class org.obeonetwork.m2doc.test.Wrong.",
                errorLogListener.getAllStatus().get(0).getMessage());
        assertEquals(
                "Problem while registering M2Doc Providers : the provider \"org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider\" is already registered. The current implementation will not be used.",
                errorLogListener.getAllStatus().get(1).getMessage());
    }

}
