/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.wikitext.tests.services;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.EMFPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.configurator.IServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.services.configurator.ServicesConfiguratorDescriptor;
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
    private static final IServicesConfiguratorDescriptor SERVICES_CONFIGURATOR_DESCRIPTOR = new ServicesConfiguratorDescriptor(
            new WikiTextServicesConfigurator());

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws IOException
     *             if the tested template can't be read
     * @throws DocumentParserException
     *             if the tested template can't be parsed
     */
    public M2DocWikiTextServicesTests(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    /**
     * Register the {@link WikiTextServicesConfigurator} if needed.
     */
    @BeforeClass
    public static void beforeClass() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            M2DocUtils.registerServicesConfigurator(SERVICES_CONFIGURATOR_DESCRIPTOR);
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
            M2DocUtils.unregisterServicesConfigurator(SERVICES_CONFIGURATOR_DESCRIPTOR);
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
