/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;
import org.obeonetwork.m2doc.services.configurator.ServicesConfiguratorDescriptor;
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
    public static final class TestServiceConfigurator implements IServicesConfigurator {

        /**
         * The option name.
         */
        public static final String OPTION = "option";

        /**
         * The option value.
         */
        public static final String VALUE = "value";

        @Override
        public List<String> getOptions() {
            final List<String> res = new ArrayList<String>();

            res.add(OPTION);

            return res;
        }

        @Override
        public Map<String, String> getInitializedOptions(Map<String, String> options) {
            final Map<String, String> res = new LinkedHashMap<String, String>();

            res.put(OPTION, VALUE);

            return res;
        }

        @Override
        public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
                Map<String, String> options) {
            final Map<String, List<Diagnostic>> res = new LinkedHashMap<String, List<Diagnostic>>();

            if (options.containsKey(OPTION)) {
                if (!VALUE.equals(options.get(OPTION))) {
                    final List<Diagnostic> diagnostic = new ArrayList<Diagnostic>();
                    diagnostic.add(new BasicDiagnostic());
                }
            }

            return res;
        }

        @Override
        public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
            // nothing to do here
            return Collections.emptySet();
        }

        @Override
        public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment) {
            // nothing to do here
        }
    }

    /**
     * The {@link TestServiceConfigurator}.
     */
    private static final ServicesConfiguratorDescriptor CONFIGURATOR = new ServicesConfiguratorDescriptor(
            new TestServiceConfigurator());

    @BeforeClass
    public static void beforeClass() {
        M2DocUtils.registerServicesConfigurator(CONFIGURATOR);
    }

    @AfterClass
    public static void afterClass() {
        M2DocUtils.unregisterServicesConfigurator(CONFIGURATOR);
    }

    @Test
    public void getInitializedOptions() {
        final Map<String, String> options = new LinkedHashMap<String, String>();

        final Map<String, String> initializedOptions = M2DocUtils.getInitializedOptions(options);

        assertEquals(1, initializedOptions.size());
        assertEquals(TestServiceConfigurator.VALUE, initializedOptions.get(TestServiceConfigurator.OPTION));
    }

}
