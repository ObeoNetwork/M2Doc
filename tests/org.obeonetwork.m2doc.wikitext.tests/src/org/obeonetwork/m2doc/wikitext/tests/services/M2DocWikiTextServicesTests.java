/*******************************************************************************
 *  Copyright (c) 2018, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.wikitext.tests.services;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.services.configurator.IServicesConfiguratorDescriptor;
import org.eclipse.acceleo.query.services.configurator.ServicesConfiguratorDescriptor;
import org.eclipse.emf.common.EMFPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;
import org.obeonetwork.m2doc.util.M2DocUtils;
import org.obeonetwork.m2doc.wikitext.services.M2DocWikiTextServices;
import org.obeonetwork.m2doc.wikitext.services.WikiTextServicesConfigurator;

/**
 * Tests {@link M2DocWikiTextServices}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocWikiTextServicesTests extends AbstractTemplatesTestSuite {

    /**
     * The {@link WikiTextServicesConfigurator}.
     */
    private static final IServicesConfiguratorDescriptor CONFIGURATOR_DESCRIPTOR = new ServicesConfiguratorDescriptor(
            M2DocUtils.M2DOC_LANGUAGE, new WikiTextServicesConfigurator());

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws Exception
     *             if something went wrong
     */
    public M2DocWikiTextServicesTests(String testFolder) throws Exception {
        super(testFolder);
    }

    /**
     * Register the {@link WikiTextServicesConfigurator} if needed.
     */
    @BeforeClass
    public static void beforeClass() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            AQLUtils.registerServicesConfigurator(CONFIGURATOR_DESCRIPTOR);
        }
    }

    /**
     * Unregister the {@link WikiTextServicesConfigurator} if needed.
     * 
     * @throws IOException
     *             if the template can't be closed
     */
    @AfterClass
    public static void afterClass() throws IOException {
        AbstractTemplatesTestSuite.afterClass();
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            AQLUtils.unregisterServicesConfigurator(CONFIGURATOR_DESCRIPTOR);
        }
    }

    /**
     * Gets test folders from resources/feature.
     * 
     * @return test folders from resources/feature
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/m2DocWikiTextServices");
    }

}
