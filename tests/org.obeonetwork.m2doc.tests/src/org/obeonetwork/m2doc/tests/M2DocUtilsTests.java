/*******************************************************************************
 *  Copyright (c) 2017, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.services.configurator.IResourceSetConfigurator;
import org.eclipse.acceleo.query.services.configurator.IResourceSetConfiguratorDescriptor;
import org.eclipse.acceleo.query.services.configurator.IServicesConfigurator;
import org.eclipse.acceleo.query.services.configurator.ResourceSetConfiguratorDescriptor;
import org.eclipse.acceleo.query.services.configurator.ServicesConfiguratorDescriptor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.obeonetwork.m2doc.services.configurator.IM2DocServicesConfigurator;
import org.obeonetwork.m2doc.util.M2DocUtils;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link M2DocUtils}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocUtilsTests {

    /**
     * Test {@link IServicesConfigurator}.
     * 
     * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
     */
    public static final class TestServiceConfigurator implements IM2DocServicesConfigurator, IResourceSetConfigurator {

        /**
         * The option name.
         */
        public static final String OPTION = "option";

        /**
         * The option value.
         */
        public static final String VALUE = "value";

        /**
         * The {@link ResourceSet}.
         */
        public static final ResourceSet RESOURCE_SET = new ResourceSetImpl();

        @Override
        public List<String> getOptions() {
            final List<String> res = new ArrayList<>();

            res.add(OPTION);

            return res;
        }

        @Override
        public Map<String, String> getInitializedOptions(Map<String, String> options) {
            final Map<String, String> res = new LinkedHashMap<>();

            res.put(OPTION, VALUE);

            return res;
        }

        @Override
        public Map<String, String> getInitializedOptions(Map<String, String> options, EObject eObj) {
            return Collections.emptyMap();
        }

        @Override
        public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
                Map<String, String> options) {
            final Map<String, List<Diagnostic>> res = new LinkedHashMap<>();

            if (options.containsKey(OPTION)) {
                if (!VALUE.equals(options.get(OPTION))) {
                    final List<Diagnostic> diagnostic = new ArrayList<>();
                    diagnostic.add(new BasicDiagnostic());
                }
            }

            return res;
        }

        @Override
        public Set<IService<?>> getServices(IReadOnlyQueryEnvironment queryEnvironment,
                ResourceSet resourceSetForModels, Map<String, String> options, boolean forForkspace) {
            // nothing to do here
            return Collections.emptySet();
        }

        @Override
        public void startGeneration(IReadOnlyQueryEnvironment queryEnvironment, XWPFDocument destinationDocument) {
            // nothing to do here
        }

        @Override
        public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment, ResourceSet resourceSetForModels) {
            // nothing to do here
        }

        @Override
        public ResourceSet createResourceSetForModels(Object context, Map<String, String> options) {
            return RESOURCE_SET;
        }

        @Override
        public void cleanResourceSetForModels(Object context) {
            // nothing to do here
        }
    }

    /**
     * The {@link TestServiceConfigurator}.
     */
    private static final ServicesConfiguratorDescriptor SERVICE_CONFIGURATOR = new ServicesConfiguratorDescriptor(
            M2DocUtils.M2DOC_LANGUAGE, new TestServiceConfigurator());

    /**
     * The {@link TestServiceConfigurator}.
     */
    private static final IResourceSetConfiguratorDescriptor RESOURCE_SET_CONFIGURATOR = new ResourceSetConfiguratorDescriptor(
            new TestServiceConfigurator());

    @BeforeClass
    public static void beforeClass() {
        AQLUtils.registerServicesConfigurator(SERVICE_CONFIGURATOR);
        AQLUtils.registerResourceSetConfigurator(RESOURCE_SET_CONFIGURATOR);
    }

    @AfterClass
    public static void afterClass() {
        AQLUtils.unregisterServicesConfigurator(SERVICE_CONFIGURATOR);
        AQLUtils.unregisterResourceSetConfigurator(RESOURCE_SET_CONFIGURATOR);
    }

    @Test
    public void getInitializedOptions() {
        final Map<String, String> options = new LinkedHashMap<>();

        final Map<String, String> initializedOptions = M2DocUtils.getInitializedOptions(options);

        assertEquals(3, initializedOptions.size());
        assertEquals("false", initializedOptions.get(M2DocUtils.UPDATE_FIELDS_OPTION));
        assertEquals("false", initializedOptions.get(M2DocUtils.IGNORE_VERSION_CHECK_OPTION));
        assertEquals(TestServiceConfigurator.VALUE, initializedOptions.get(TestServiceConfigurator.OPTION));
    }

    @Test
    public void createResourceSetForModels() {
        List<Exception> exceptions = new ArrayList<>();
        final ResourceSet rs = AQLUtils.createResourceSetForModels(exceptions, Query.newEnvironment(), null,
                new HashMap<>());

        assertEquals(TestServiceConfigurator.RESOURCE_SET, rs);
    }

    @Test
    public void versionTest() throws IOException {
        final String manifest = new String(Files.readAllBytes(Paths.get("META-INF/MANIFEST.MF")));

        final String version = manifest.substring(manifest.indexOf("Bundle-Version: ") + 16,
                manifest.indexOf(".qualifier"));

        assertEquals(version, M2DocUtils.VERSION);
    }

}
