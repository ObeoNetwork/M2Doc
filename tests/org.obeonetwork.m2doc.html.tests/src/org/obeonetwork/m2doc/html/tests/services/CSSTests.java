/*******************************************************************************
 *  Copyright (c) 2021 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.html.tests.services;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.common.EMFPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.html.services.HTMLServicesConfigurator;
import org.obeonetwork.m2doc.html.services.M2DocHTMLServices;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.configurator.IServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.services.configurator.ServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Tests {@link M2DocHTMLServices}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CSSTests extends AbstractTemplatesTestSuite {

    /**
     * The {@link HTMLServicesConfigurator}.
     */
    private static final IServicesConfiguratorDescriptor SERVICES_CONFIGURATOR_DESCRIPTOR = new ServicesConfiguratorDescriptor(
            new HTMLServicesConfigurator());

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
    public CSSTests(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    /**
     * Register the {@link HTMLServicesConfigurator} if needed.
     */
    @BeforeClass
    public static void beforeClass() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            M2DocUtils.registerServicesConfigurator(SERVICES_CONFIGURATOR_DESCRIPTOR);
        }
    }

    /**
     * Unregister the {@link HTMLServicesConfigurator} if needed.
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
        return retrieveTestFolders("resources/css");
    }

}
