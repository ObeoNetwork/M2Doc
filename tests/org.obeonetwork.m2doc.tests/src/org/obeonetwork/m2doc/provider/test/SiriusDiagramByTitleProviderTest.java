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

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.AssertionFailedError;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider;
import org.obeonetwork.m2doc.test.AbstractM2DocTest;

/**
 * {@link SiriusDiagramByTitleProvider} test class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class SiriusDiagramByTitleProviderTest extends AbstractM2DocTest {

    /**
     * Component to test.
     */
    private SiriusDiagramByTitleProvider siriusDiagramByTitleProvider;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        siriusDiagramByTitleProvider = new SiriusDiagramByTitleProvider();
    }

    /**
     * Provides the semantic resource of the session of the parent class initialized by the setup.
     * 
     * @return Resource The semantic resource.
     */
    @Override
    protected Resource getSemanticResource() {

        Collection<Resource> semanticResources = session.getSemanticResources();
        Resource resource = null;
        for (Resource r : semanticResources) {
            resource = r;
        }
        return resource;
    }

    /**
     * Tests {@link SiriusDiagramByTitleProvider#getRepresentationImagePath(Map)}.
     * When all options given are correct, an image from the diagram corresponding to the title must be created.
     * The tested tag is {m:diagram diagramProvider:'org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider' width:'200'
     * height:'200' title:'RF Schema'}
     * 
     * @throws ProviderException
     */
    @Test
    public void testAllOptionPresentAndCorrect() throws ProviderException {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put(ProviderConstants.KEY_CONF_ROOT_OBJECT, getSemanticResource().getContents().get(0));
        IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
        options.put(ProviderConstants.KEY_PROJECT_ROOT_PATH, iProject.getLocation().toString());
        options.put(ProviderConstants.KEY_IMAGE_HEIGHT, 500);
        options.put(ProviderConstants.KEY_IMAGE_WIDTH, 500);
        options.put("title", "RF Schema");
        List<String> representationImagePaths = siriusDiagramByTitleProvider.getRepresentationImagePath(options);
        assertEquals(1, representationImagePaths.size());
        File imageFile = new File(representationImagePaths.get(0));
        assertTrue(imageFile.exists());

    }

    /**
     * Tests {@link SiriusDiagramByTitleProvider#getRepresentationImagePath(Map)}.
     * When the title option refers to an unknown Sirius diagram representation, then an exception must be thrown.
     * 
     * @throws ProviderException
     */
    @Test
    public void testTitleDoNotExist() throws ProviderException {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put(ProviderConstants.KEY_CONF_ROOT_OBJECT, getSemanticResource().getContents().get(0));
        IProject iProject = ResourcesPlugin.getWorkspace().getRoot().getProjects()[0];
        options.put(ProviderConstants.KEY_PROJECT_ROOT_PATH, iProject.getLocation().toString());
        options.put(ProviderConstants.KEY_IMAGE_HEIGHT, 500);
        options.put(ProviderConstants.KEY_IMAGE_WIDTH, 500);
        options.put("title", "wrongReference");
        try {
            siriusDiagramByTitleProvider.getRepresentationImagePath(options);
            throw new AssertionFailedError("An exception should have been thrown");
        } catch (ProviderException e) {
            assertEquals("Representation with title 'wrongReference' not found", e.getMessage());
        }
    }

}
