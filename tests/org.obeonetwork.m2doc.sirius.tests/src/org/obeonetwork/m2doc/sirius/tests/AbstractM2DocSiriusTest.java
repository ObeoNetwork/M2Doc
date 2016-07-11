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
package org.obeonetwork.m2doc.sirius.tests;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.junit.Before;

/**
 * Utility test class for Sirius testing.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public abstract class AbstractM2DocSiriusTest {
    /**
     * The Sirius session.
     */
    protected Session session;

    /**
     * Initializes the Sirius session from AIRD plugin path.
     * 
     * @throws Exception
     *             if a problem occurs during initialization.
     */
    @Before
    public void setUp() throws Exception {
        URI uri = URI.createPlatformPluginURI(getAirdPluginPath(), true);
        session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
    }

    /**
     * Returns the plugin path of the aird to use for testing.
     * 
     * @return the plugin path of the aird to use for testing.
     */
    protected abstract String getAirdPluginPath();

    /**
     * Provides the semantic resource of the session of the parent class initialized by the setup.
     * 
     * @return Resource The semantic resource.
     */
    protected Resource getSemanticResource() {

        Collection<Resource> semanticResources = session.getSemanticResources();
        Resource resource = null;
        for (Resource r : semanticResources) {
            resource = r;
        }
        return resource;
    }
}
