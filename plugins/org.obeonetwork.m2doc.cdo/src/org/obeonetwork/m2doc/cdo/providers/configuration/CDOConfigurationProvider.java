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
package org.obeonetwork.m2doc.cdo.providers.configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.spi.cdo.InternalCDOView;
import org.eclipse.net4j.connector.IConnector;
import org.obeonetwork.m2doc.cdo.M2DocCDOUtils;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.provider.IConfigurationProvider;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Configuration provider for CDO.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class CDOConfigurationProvider implements IConfigurationProvider {

    /**
     * Mapping of {@link Generation} to {@link IConnector}.
     */
    private Map<Generation, IConnector> connectors = new HashMap<Generation, IConnector>();

    /**
     * Mapping of {@link Generation} to {@link CDOTransaction}.
     */
    private Map<Generation, CDOTransaction> transactions = new HashMap<Generation, CDOTransaction>();

    @Override
    public void postCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI,
            Generation generation) {
        // nothing to do here
    }

    @Override
    public void preCreateConfigurationModel(TemplateCustomProperties templateProperties, URI templateURI) {
        // nothing to do here
    }

    @Override
    public boolean postValidateTemplate(URI templateURI, DocumentTemplate documentTemplate, Generation generation) {
        // nothing to do here
        return false;
    }

    @Override
    public void preValidateTemplate(URI templateURI, DocumentTemplate documentTemplate, Generation generation) {
        // nothing to do here
    }

    @Override
    public void preGenerate(Generation generation, URI templateURI, URI generatedURI) {
        // nothing to do here
    }

    @Override
    public List<URI> postGenerate(Generation generation, URI templateURI, URI generatedURI,
            DocumentTemplate documentTemplate) {
        final CDOTransaction transaction = transactions.get(generation);
        if (transaction != null) {
            final CDOSession session = transaction.getSession();
            transaction.close();
            session.close();
            connectors.remove(generation).close();
        }
        return Collections.emptyList();
    }

    @Override
    public ResourceSet createResourceSetForModels(Generation generation) {
        final ResourceSet res;

        final Map<String, String> options = GenconfUtils.getOptions(generation);
        final String cdoServer = options.get(M2DocCDOUtils.CDO_SERVER_OPTION);
        if (cdoServer != null) {
            final String repository = options.get(M2DocCDOUtils.CDO_REPOSITORY_OPTION);
            final String branch = options.get(M2DocCDOUtils.CDO_BRANCH_OPTION);
            final String login = options.get(M2DocCDOUtils.CDO_LOGIN_OPTION);
            final String password = options.get(M2DocCDOUtils.CDO_PASSWORD_OPTION);
            final IConnector connector = M2DocCDOUtils.getConnector(cdoServer);
            connectors.put(generation, connector);
            final CDOSession session = M2DocCDOUtils.openSession(connector, repository, login, password);
            final CDOTransaction transaction = M2DocCDOUtils.openTransaction(session, branch);
            transactions.put(generation, transaction);
            res = transaction.getResourceSet();
            res.getURIConverter().getURIHandlers().add(0, new M2DocCDOURIHandler((InternalCDOView) transaction));
        } else {
            res = null;
        }

        return res;
    }

}
