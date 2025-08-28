/*******************************************************************************
 *  Copyright (c) 2019, 2025 Obeo. 
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

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.services.configurator.IServicesConfiguratorDescriptor;
import org.eclipse.acceleo.query.services.configurator.ServicesConfiguratorDescriptor;
import org.eclipse.emf.common.EMFPlugin;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.html.services.HTMLServicesConfigurator;
import org.obeonetwork.m2doc.html.services.M2DocHTMLServices;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Tests {@link M2DocHTMLServices}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class HTMLTests extends AbstractTemplatesTestSuite {

    /**
     * The {@link HTMLServicesConfigurator}.
     */
    private static final IServicesConfiguratorDescriptor CONFIGURATOR_DESCRIPTOR = new ServicesConfiguratorDescriptor(
            M2DocUtils.M2DOC_LANGUAGE, new HTMLServicesConfigurator());

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
    public HTMLTests(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    /**
     * Register the {@link HTMLServicesConfigurator} if needed.
     */
    @BeforeClass
    public static void beforeClass() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            AQLUtils.registerServicesConfigurator(CONFIGURATOR_DESCRIPTOR);
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
        return retrieveTestFolders("resources/html");
    }

}
