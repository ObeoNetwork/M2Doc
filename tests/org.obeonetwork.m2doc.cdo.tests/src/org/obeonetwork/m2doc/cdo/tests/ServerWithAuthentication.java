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
package org.obeonetwork.m2doc.cdo.tests;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.cdo.AqlCDOUtils;
import org.eclipse.acceleo.query.cdo.services.configurator.CDOResourceSetConfigurator;
import org.eclipse.acceleo.query.services.configurator.IResourceSetConfiguratorDescriptor;
import org.eclipse.acceleo.query.services.configurator.ResourceSetConfiguratorDescriptor;
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
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;

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
     * The instance of {@link CDOResourceSetConfigurator} descriptor for standalone use.
     */
    private static final IResourceSetConfiguratorDescriptor CONFIGURATOR_DESCRIPTOR = new ResourceSetConfiguratorDescriptor(
            new CDOResourceSetConfigurator());

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws Exception
     *             if something went wrong
     */
    public ServerWithAuthentication(String testFolder) throws Exception {
        super(testFolder);
    }

    /**
     * Starts the {@link CDOServer}.
     */
    @BeforeClass
    public static void startCDOServer() {
        server = new CDOServer(true);
        server.start();
        IConnector connector = AqlCDOUtils
                .getConnector(CDOServer.PROTOCOL + "://" + CDOServer.IP + ":" + CDOServer.PORT);
        CDOSession session = AqlCDOUtils.openSession(connector, CDOServer.REPOSITORY_NAME, CDOServer.USER_NAME,
                CDOServer.PASSWORD);
        final CDOTransaction transaction = AqlCDOUtils.openTransaction(session);
        final CDOResource resource = transaction.createResource("anydsl.ecore");
        final ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
        final Resource anyDSLResource = resourceSet.getResource(URI.createFileURI("resources/anydsl.ecore"), true);
        resource.getContents().addAll(anyDSLResource.getContents());
        try {
            resource.save(null);
            transaction.commit();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (CommitException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        transaction.close();
        session.close();
        connector.close();

        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            AQLUtils.registerResourceSetConfigurator(CONFIGURATOR_DESCRIPTOR);
        }
    }

    /**
     * Stops the {@link CDOServer}.
     */
    @AfterClass
    public static void stopCDOServer() {
        server.stop();

        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            AQLUtils.unregisterResourceSetConfigurator(CONFIGURATOR_DESCRIPTOR);
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
