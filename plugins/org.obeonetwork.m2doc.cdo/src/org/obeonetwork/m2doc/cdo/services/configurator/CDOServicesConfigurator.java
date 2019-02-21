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
package org.obeonetwork.m2doc.cdo.services.configurator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.spi.cdo.InternalCDOView;
import org.eclipse.net4j.connector.IConnector;
import org.obeonetwork.m2doc.cdo.M2DocCDOUtils;
import org.obeonetwork.m2doc.cdo.providers.configuration.M2DocCDOURIHandler;
import org.obeonetwork.m2doc.services.configurator.IServicesConfigurator;

/**
 * Sirius {@link IServicesConfigurator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CDOServicesConfigurator implements IServicesConfigurator {

    /**
     * The {@link List} of options managed by this configurator.
     */
    public static final List<String> OPTIONS = initOptions();

    /**
     * Mapping of {@link Object} to {@link IConnector}.
     */
    private Map<Object, IConnector> connectors = new HashMap<Object, IConnector>();

    /**
     * Mapping of {@link Object} to {@link CDOTransaction}.
     */
    private Map<Object, CDOTransaction> transactions = new HashMap<Object, CDOTransaction>();

    /**
     * Initializes options.
     * 
     * @return the {@link List} of options.
     */
    private static List<String> initOptions() {
        final List<String> res = new ArrayList<String>();

        res.add(M2DocCDOUtils.CDO_SERVER_OPTION);
        res.add(M2DocCDOUtils.CDO_REPOSITORY_OPTION);
        res.add(M2DocCDOUtils.CDO_BRANCH_OPTION);
        res.add(M2DocCDOUtils.CDO_LOGIN_OPTION);
        res.add(M2DocCDOUtils.CDO_PASSWORD_OPTION);

        return res;
    }

    @Override
    public List<String> getOptions() {
        return OPTIONS;
    }

    @Override
    public Map<String, String> getInitializedOptions(Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, String> options) {
        return Collections.emptyMap();
    }

    @Override
    public Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options) {
        return Collections.emptySet();
    }

    @Override
    public void cleanServices(IReadOnlyQueryEnvironment queryEnvironment) {
        // nothing to do here
    }

    @Override
    public ResourceSet createResourceSetForModels(Object context, Map<String, String> options) {
        final ResourceSet res;

        final String cdoServer = options.get(M2DocCDOUtils.CDO_SERVER_OPTION);
        if (cdoServer != null) {
            final String repository = options.get(M2DocCDOUtils.CDO_REPOSITORY_OPTION);
            final String branch = options.get(M2DocCDOUtils.CDO_BRANCH_OPTION);
            final String login = options.get(M2DocCDOUtils.CDO_LOGIN_OPTION);
            final String password = options.get(M2DocCDOUtils.CDO_PASSWORD_OPTION);
            final IConnector connector = M2DocCDOUtils.getConnector(cdoServer);
            connectors.put(context, connector);
            final CDOSession session = M2DocCDOUtils.openSession(connector, repository, login, password);
            final CDOTransaction transaction = M2DocCDOUtils.openTransaction(session, branch);
            transactions.put(context, transaction);
            res = transaction.getResourceSet();
            res.getURIConverter().getURIHandlers().add(0, new M2DocCDOURIHandler((InternalCDOView) transaction));
        } else {
            res = null;
        }

        return res;
    }

    @Override
    public void cleanResourceSetForModels(Object context) {
        final CDOTransaction transaction = transactions.get(context);
        if (transaction != null) {
            final CDOSession session = transaction.getSession();
            transaction.close();
            session.close();
            connectors.remove(context).close();
        }
    }

}
