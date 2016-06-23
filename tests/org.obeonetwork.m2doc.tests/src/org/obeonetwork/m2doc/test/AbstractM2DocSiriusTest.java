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
package org.obeonetwork.m2doc.test;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.junit.Before;

/**
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public abstract class AbstractM2DocSiriusTest extends SiriusDiagramTestCase {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        EclipseTestsSupportHelper.INSTANCE.copyFile("org.obeonetwork.m2doc.tests",
                "/resources/" + getSemanticModelName(), "/" + TEMPORARY_PROJECT_NAME + "/" + getSemanticModelName());
        EclipseTestsSupportHelper.INSTANCE.copyFile("org.obeonetwork.m2doc.tests", "/resources/representations.aird",
                "/" + TEMPORARY_PROJECT_NAME + "/" + "representations.aird");
        URI uri = URI.createPlatformResourceURI(TEMPORARY_PROJECT_NAME + "/representations.aird", true);
        session = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
    }

    public abstract String getSemanticModelName();

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
