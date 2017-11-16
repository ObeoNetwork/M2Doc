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
package org.obeonetwork.m2doc.cdo.tests;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.net4j.connector.IConnector;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.cdo.M2DocCDOUtils;
import org.obeonetwork.m2doc.cdo.providers.configuration.CDOConfigurationProvider;
import org.obeonetwork.m2doc.cdo.services.configurator.CDOServicesConfigurator;
import org.obeonetwork.m2doc.genconf.provider.ConfigurationProviderService;
import org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.services.configurator.ServicesConfiguratorDescriptor;
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Tests with authenticated CDO server.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServerWithAuthentication extends AbstractTemplatesTestSuite {

    /**
     * The {@link CDOServer}.
     */
    private static CDOServer server;

    /**
     * The instance of {@link CDOConfigurationProvider} for standalone use.
     */
    private static final IConfigurationProvider CONFIGURATION_PROVIDER = new CDOConfigurationProvider();

    /**
     * The instance of {@link CDOServicesConfigurator} for standalone use.
     */
    private static final ServicesConfiguratorDescriptor SERVICES_CONFIGURATOR_DESCRIPTOR = new ServicesConfiguratorDescriptor(
            new CDOServicesConfigurator());

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
    public ServerWithAuthentication(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    @BeforeClass
    public static void startCDOServer() throws IOException, CommitException {
        server = new CDOServer(true);
        server.start();
        IConnector connector = M2DocCDOUtils
                .getConnector(CDOServer.PROTOCOL + "://" + CDOServer.IP + ":" + CDOServer.PORT);
        CDOSession session = M2DocCDOUtils.openSession(connector, CDOServer.REPOSITORY_NAME, CDOServer.USER_NAME,
                CDOServer.PASSWORD);
        final CDOTransaction transaction = M2DocCDOUtils.openTransaction(session);
        final CDOResource resource = transaction.createResource("anydsl.ecore");
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        final Resource anyDSLResource = resourceSet.getResource(URI.createFileURI("resources/anydsl.ecore"), true);
        resource.getContents().addAll(anyDSLResource.getContents());
        resource.save(null);
        transaction.commit();
        transaction.close();
        session.close();
        connector.close();

        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            ConfigurationProviderService.getInstance().register(CONFIGURATION_PROVIDER);
            M2DocUtils.registerServicesConfigurator(SERVICES_CONFIGURATOR_DESCRIPTOR);
        }
    }

    @AfterClass
    public static void stopCDOServer() {
        server.stop();

        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            ConfigurationProviderService.getInstance().getProviders().remove(CONFIGURATION_PROVIDER);
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
        return retrieveTestFolders("resources/serverWithAuthentication");
    }

}
