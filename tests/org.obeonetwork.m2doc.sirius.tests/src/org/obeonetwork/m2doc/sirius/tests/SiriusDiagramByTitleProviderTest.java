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

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.AssertionFailedError;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * {@link SiriusDiagramByTitleProvider} test class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class SiriusDiagramByTitleProviderTest extends AbstractM2DocSiriusTest {

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
        options.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, getSemanticResource().getContents().get(0));
        options.put(ProviderConstants.PROJECT_ROOT_PATH_KEY, "org.obeonetwork.m2doc.sirius.tests");
        // CHECKSTYLE:OFF
        options.put(ProviderConstants.IMAGE_HEIGHT_KEY, 500);
        options.put(ProviderConstants.IMAGE_WIDTH_KEY, 500);
        // CHECKSTYLE:ON
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
        options.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, getSemanticResource().getContents().get(0));
        options.put(ProviderConstants.PROJECT_ROOT_PATH_KEY, "org.obeonetwork.m2doc.sirius.tests");
        // CHECKSTYLE:OFF
        options.put(ProviderConstants.IMAGE_HEIGHT_KEY, 500);
        options.put(ProviderConstants.IMAGE_WIDTH_KEY, 500);
        // CHECKSTYLE:ON
        options.put("title", "wrongReference");
        try {
            siriusDiagramByTitleProvider.getRepresentationImagePath(options);
            throw new AssertionFailedError("An exception should have been thrown");
        } catch (ProviderException e) {
            assertEquals("Representation with title 'wrongReference' not found", e.getMessage());
        }
    }

    /**
     * Tests {@link SiriusDiagramByTitleProvider#getRepresentationImagePath(Map)}.
     * When the title option refers to an AQL expression that does not provide a String but another type like int.
     * 
     * @throws ProviderException
     */
    @Test
    public void testInvalidAQLExpression() throws ProviderException {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, getSemanticResource().getContents().get(0));
        options.put(ProviderConstants.PROJECT_ROOT_PATH_KEY, "org.obeonetwork.m2doc.sirius.tests");
        // CHECKSTYLE:OFF
        options.put(ProviderConstants.IMAGE_HEIGHT_KEY, 500);
        options.put(ProviderConstants.IMAGE_WIDTH_KEY, 500);
        // CHECKSTYLE:ON
        options.put("title", 0);
        try {
            siriusDiagramByTitleProvider.getRepresentationImagePath(options);
            throw new AssertionFailedError("An exception should have been thrown");
        } catch (ProviderException e) {
            assertEquals(
                    "Image cannot be computed because no representation title has been provided to the provider \"org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider\"",
                    e.getMessage());
        }
    }

    /**
     * Tests {@link SiriusDiagramByTitleProvider#getRepresentationImagePath(Map)}.
     * When the title option refers to an unknown Sirius diagram representation, then an exception must be thrown.
     * 
     * @throws ProviderException
     */
    @Test
    public void testTitleNotPresent() throws ProviderException {
        Map<String, Object> options = new HashMap<String, Object>();
        options.put(ProviderConstants.CONF_ROOT_OBJECT_KEY, getSemanticResource().getContents().get(0));
        options.put(ProviderConstants.PROJECT_ROOT_PATH_KEY, "org.obeonetwork.m2doc.sirius.tests");
        // CHECKSTYLE:OFF
        options.put(ProviderConstants.IMAGE_HEIGHT_KEY, 500);
        options.put(ProviderConstants.IMAGE_WIDTH_KEY, 500);
        // CHECKSTYLE:ON
        try {
            siriusDiagramByTitleProvider.getRepresentationImagePath(options);
            throw new AssertionFailedError("An exception should have been thrown");
        } catch (ProviderException e) {
            assertEquals(
                    "Image cannot be computed because no representation title has been provided to the provider \"org.obeonetwork.m2doc.sirius.SiriusDiagramByTitleProvider\"",
                    e.getMessage());
        }
    }

    @Override
    protected String getAirdPluginPath() {
        return "/org.obeonetwork.m2doc.sirius.tests/resources/representations.aird";
    }

}
