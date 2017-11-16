/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo	 - initial API and implementation and/or initial documentation
 *    ...
 *******************************************************************************/
package org.obeonetwork.m2doc.cdo;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.cdo.common.branch.CDOBranch;
import org.eclipse.emf.cdo.common.branch.CDOBranchHandler;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.ConnectorException;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.eclipse.net4j.util.security.IPasswordCredentialsProvider;
import org.eclipse.net4j.util.security.PasswordCredentialsProvider;
import org.eclipse.net4j.util.ui.security.InteractiveCredentialsProvider;
import org.eclipse.swt.SWT;

/**
 * A CDO utility class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class M2DocCDOUtils {

    /**
     * The Plug-in ID.
     */
    public static final String PLUGIN_ID = "org.obeonetwork.m2doc.cdo";

    /**
     * The CDO server option.
     */
    public static final String CDO_SERVER_OPTION = "CDOServer";

    /**
     * The CDO repository option.
     */
    public static final String CDO_REPOSITORY_OPTION = "CDORepository";

    /**
     * The CDO branch option.
     */
    public static final String CDO_BRANCH_OPTION = "CDOBranch";

    /**
     * The CDO login option.
     */
    public static final String CDO_LOGIN_OPTION = "CDOLogin";

    /**
     * The CDO password option.
     */
    public static final String CDO_PASSWORD_OPTION = "CDOPassword";

    /**
     * Constructor.
     */
    private M2DocCDOUtils() {
        // can't create instance
    }

    /**
     * Gets a {@link IConnector} from the given description. Caller is responsible of
     * {@link IConnector#close() closing} the resulting connector.
     * 
     * @param description
     *            the description of the protocol host and port to use for the connection
     * @return a new {@link IConnector}
     */
    public static IConnector getConnector(String description) {
        final IManagedContainer container = getContainer(description);
        IConnector res = null;
        try {
            res = Net4jUtil.getConnector(container, description);
        } catch (ConnectorException e) {
            LifecycleUtil.deactivate(container);
            throw e;
        }
        return res;
    }

    /**
     * Open a new {@link CDOSession}. Caller is responsible of {@link CDOSession#close() closing} the
     * resulting session.
     * 
     * @param connector
     *            the {@link IConnector} to use to open the {@link CDOSession}
     * @param repository
     *            the repository name
     * @return a new {@link CDOSession}
     */
    public static CDOSession openSession(IConnector connector, String repository) {
        return openSession(connector, repository, null, null);
    }

    /**
     * Open a new {@link CDOSession}. Caller is responsible of {@link CDOSession#close() closing} the
     * resulting session.
     * 
     * @param connector
     *            the {@link IConnector} to use to open the {@link CDOSession}
     * @param repository
     *            the repository name
     * @param login
     *            the user login if any, if <code>null</code> otherwise no authentication will by used
     * @param password
     *            the user password if an, if <code>null</code> empty {@link String} will be used
     * @return a new {@link CDOSession}
     */
    public static CDOSession openSession(IConnector connector, String repository, String login, String password) {
        final CDONet4jSessionConfiguration sessionConfiguration = CDONet4jUtil.createNet4jSessionConfiguration();
        sessionConfiguration.setConnector(connector);
        sessionConfiguration.setRepositoryName(repository);
        if (login != null) {
            final String pass;
            if (password != null) {
                pass = password;
            } else {
                pass = "";
            }
            sessionConfiguration.setCredentialsProvider(new PasswordCredentialsProvider(login, pass.toCharArray()));
        } else {
            final IPasswordCredentialsProvider credentialsProvider;
            if (SWT.isLoadable()) {
                credentialsProvider = new InteractiveCredentialsProvider();
            } else {
                credentialsProvider = new ShellCredentialsProvider();
            }
            sessionConfiguration.setCredentialsProvider(credentialsProvider);
        }

        CDOSession res = sessionConfiguration.openNet4jSession();

        return res;
    }

    /**
     * Open a {@link CDOTransaction} on the main {@link CDOBranch}. Caller is responsible of
     * {@link CDOTransaction#close() closing} the resulting transaction.
     * 
     * @param session
     *            the {@link CDOSession} to use to open the {@link CDOTransaction}
     * @return a new {@link CDOTransaction}
     */
    public static CDOTransaction openTransaction(CDOSession session) {
        return openTransaction(session, null);
    }

    /**
     * Open a {@link CDOTransaction} on the given {@link CDOBranch}. Caller is responsible of
     * {@link CDOTransaction#close() closing} the resulting transaction.
     * 
     * @param session
     *            the {@link CDOSession} to use to open the {@link CDOTransaction}
     * @param branchName
     *            the branch name or <code>null</code> for the main branch
     * @return a new {@link CDOTransaction}
     */
    public static CDOTransaction openTransaction(CDOSession session, String branchName) {
        final CDOTransaction res;
        if (branchName != null) {
            CDOBranch branch = session.getBranchManager().getBranch(branchName);
            if (branch != null) {
                res = session.openTransaction(branch);
            } else {
                res = session.openTransaction();
            }
        } else {
            res = session.openTransaction();
        }
        return res;
    }

    /**
     * Lists {@link CDOResource} of the given {@link CDOView}.
     * 
     * @param view
     *            the {@link CDOView}
     * @return the {@link List} of {@link CDOResource} from the given {@link CDOView}
     */
    public static List<CDOResource> getResources(CDOView view) {
        final List<CDOResource> res = new ArrayList<CDOResource>();

        for (EObject eObj : view.getRootResource().eContents()) {
            res.add((CDOResource) eObj);
        }

        return res;
    }

    /**
     * Lists all {@link CDOBranch} for the given {@link CDOSession}.
     * 
     * @param session
     *            the {@link CDOSession} to use to list {@link CDOBranch}
     * @return the {@link List} of {@link CDOBranch} for the given {@link CDOSession}
     */
    public static List<CDOBranch> getBranches(CDOSession session) {
        final List<CDOBranch> res = new ArrayList<CDOBranch>();
        session.getBranchManager().getBranches(Integer.MIN_VALUE, Integer.MAX_VALUE, new CDOBranchHandler() {

            /**
             * {@inheritDoc}
             * 
             * @see org.eclipse.emf.cdo.common.branch.CDOBranchHandler#handleBranch(org.eclipse.emf.cdo.common.branch.CDOBranch)
             */
            public void handleBranch(CDOBranch branch) {
                res.add(branch);
            }
        });
        return res;
    }

    /**
     * Gets a {@link IManagedContainer}.
     * 
     * @param description
     *            the description of the protocol host and port to use for the connection
     * @return a {@link IManagedContainer}
     */
    private static IManagedContainer getContainer(String description) {
        IManagedContainer res = ContainerUtil.createContainer();
        Net4jUtil.prepareContainer(res);
        CDONet4jUtil.prepareContainer(res);
        TCPUtil.prepareContainer(res);
        res.activate();
        return res;
    }

}
