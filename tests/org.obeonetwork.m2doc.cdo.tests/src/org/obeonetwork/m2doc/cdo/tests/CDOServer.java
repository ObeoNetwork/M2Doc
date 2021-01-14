/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *    Obeo	 - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.obeonetwork.m2doc.cdo.tests;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.cdo.internal.server.Repository;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.IRepository.Props;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.mem.MEMStoreUtil;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.spi.server.InternalSessionManager;
import org.eclipse.emf.cdo.spi.server.InternalStore;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.eclipse.net4j.util.om.OMPlatform;
import org.eclipse.net4j.util.om.log.PrintLogHandler;
import org.eclipse.net4j.util.om.trace.PrintTraceHandler;
import org.eclipse.net4j.util.security.INegotiator;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;
import org.eclipse.net4j.util.security.ResponseNegotiator;
import org.eclipse.net4j.util.security.UserManager;

/**
 * A CDO server for testing purpose. It creates a memory store.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public final class CDOServer {

    /**
     * The TCP port the server is listening to.
     */
    public static final int PORT = 12345;

    /**
     * The repository name.
     */
    public static final String REPOSITORY_NAME = "testRepo";

    /**
     * The protocol to use for communication.
     */
    public static final String PROTOCOL = "tcp";

    /**
     * The IP to bind to.
     */
    public static final String IP = "127.0.0.1";

    /**
     * The user name.
     */
    public static final String USER_NAME = "user";

    /**
     * The password.
     */
    public static final String PASSWORD = "password";

    /**
     * The {@link IRepository} of the CDO server.
     */
    private IRepository repository;

    /**
     * Should we use authentication or not.
     */
    private final boolean withAuth;

    /**
     * The {@link IManagedContainer}.
     */
    private IManagedContainer container;

    /**
     * The {@link IAcceptor}.
     */
    private IAcceptor acceptor;

    /**
     * Constructor.
     * 
     * @param withAuth
     *            tells if authentication should be activated
     */
    public CDOServer(boolean withAuth) {
        this.withAuth = withAuth;
    }

    /**
     * Creates the repository.
     * 
     * @return the repository
     */
    protected IRepository createRepository() {
        Map<String, String> props = new HashMap<String, String>();
        props.put(Props.SUPPORTING_AUDITS, Boolean.TRUE.toString());
        props.put(Props.SUPPORTING_BRANCHES, Boolean.TRUE.toString());

        // final Repository.Default res = CDOServerUtil.createRepository(REPOSITORY_NAME, createStore(), props);
        final Repository.Default res = new Repository.Default() {

            @Override
            public String getUUID() {
                return "5ef25598-0af9-4436-b7df-5764732e4c0b";
            }

        };
        res.setName(REPOSITORY_NAME);
        res.setStore((InternalStore) createStore());
        res.setProperties(props);

        return res;
    }

    /**
     * Creates the {@link IStore}.
     * 
     * @return the created {@link IStore}
     */
    protected IStore createStore() {
        return MEMStoreUtil.createMEMStore();
    }

    /**
     * Create the authentication {@link INegotiator}.
     * 
     * @return the created authentication {@link INegotiator
     */
    protected INegotiator createNegotiator() {
        ResponseNegotiator negotiator = new ResponseNegotiator();
        negotiator.setCredentialsProvider(new PasswordCredentialsProvider(USER_NAME, PASSWORD));
        return negotiator;
    }

    /**
     * Starts the CDO server.
     */
    public void start() {
        if (repository == null) {
            container = ContainerUtil.createContainer();
            Net4jUtil.prepareContainer(container);
            CDONet4jUtil.prepareContainer(container);
            TCPUtil.prepareContainer(container);
            container.activate();

            OMPlatform.INSTANCE.setDebugging(false);
            OMPlatform.INSTANCE.addTraceHandler(PrintTraceHandler.CONSOLE);
            OMPlatform.INSTANCE.addLogHandler(PrintLogHandler.CONSOLE);

            Net4jUtil.prepareContainer(container); // Prepare the Net4j kernel
            TCPUtil.prepareContainer(container); // Prepare the TCP support
            CDONet4jServerUtil.prepareContainer(container); // Prepare the CDO server
            // OCLQueryHandler.prepareContainer(container); // Prepare OCL queries

            repository = createRepository();

            if (withAuth) {
                InternalSessionManager sessionManager = (InternalSessionManager) CDOServerUtil.createSessionManager();
                final UserManager userManager = new UserManager();
                userManager.addUser(USER_NAME, PASSWORD.toCharArray());
                sessionManager.setAuthenticator(userManager);
                ((Repository) repository).setSessionManager(sessionManager);
            }
            CDOServerUtil.addRepository(container, repository);

            acceptor = Net4jUtil.getAcceptor(container, PROTOCOL, IP + ":" + PORT);
        }
    }

    /**
     * Stops the CDO server.
     */
    public void stop() {
        acceptor.close();
        LifecycleUtil.deactivate(repository);
        LifecycleUtil.deactivate(container);
        repository = null;
        container = null;
    }

}
