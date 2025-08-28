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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.acceleo.query.AQLUtils;
import org.eclipse.acceleo.query.cdo.AqlCDOUtils;
import org.eclipse.acceleo.query.cdo.services.configurator.CDOResourceSetConfigurator;
import org.eclipse.acceleo.query.services.configurator.IResourceSetConfiguratorDescriptor;
import org.eclipse.acceleo.query.services.configurator.ResourceSetConfiguratorDescriptor;
import org.eclipse.emf.cdo.common.lob.CDOBlob;
import org.eclipse.emf.cdo.eresource.CDOBinaryResource;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.net4j.connector.IConnector;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite;

/**
 * Tests with authenticated CDO server and template on server.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServerWithoutAuthenticationTemplateOnServer extends AbstractTemplatesTestSuite {

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
     * The template {@link URI} to CDO {@link URI}.
     */
    private static final Map<String, URI> TO_CDO_MAPPING = new HashMap<String, URI>();

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws IOException
     *             if the tested template can't be read
     * @throws DocumentParserException
     *             if the tested template can't be parsed
     * @throws CommitException
     *             if something went wrong with CDO
     */
    public ServerWithoutAuthenticationTemplateOnServer(String testFolder)
            throws IOException, DocumentParserException, CommitException {
        super(testFolder);
    }

    @Override
    protected URI getTemplateURI(File testFolder) {
        return TO_CDO_MAPPING.get(testFolder.getPath());
    }

    /**
     * Starts the {@link CDOServer}.
     */
    @BeforeClass
    public static void startCDOServer() {
        final Collection<Object[]> testFolders = retrieveTestFolders(
                "resources/serverWithoutAuthenticationTemplateOnServer");

        server = new CDOServer(false);
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

            for (Object[] parameters : testFolders) {
                final String testFolder = ((String) parameters[0]).replaceAll("\\\\", "/");
                final URI uri = URI.createURI(getTemplateFileInternal(new File(testFolder)).toURI().toString(), false);
                final CDOBinaryResource templateResource = transaction.createBinaryResource(uri.lastSegment());
                final URIConverter uriConverter = transaction.getResourceSet().getURIConverter();
                final InputStream templateInputStream = uriConverter.createInputStream(uri);
                CDOBlob contents = new CDOBlob(templateInputStream);
                templateResource.setContents(contents);

                TO_CDO_MAPPING.put(testFolder, templateResource.getURI());

                templateResource.cdoResource().save(null);
                transaction.commit();
            }
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
     * @throws IOException
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/serverWithoutAuthenticationTemplateOnServer");
    }

}
