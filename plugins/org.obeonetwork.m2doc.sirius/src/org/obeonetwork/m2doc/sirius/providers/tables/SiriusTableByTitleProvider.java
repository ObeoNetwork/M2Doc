/*******************************************************************************
 * Copyright (c) 2016 Obeo. 
 *    All rights reserved. This program and the accompanying materials
 *    are made available under the terms of the Eclipse Public License v1.0
 *    which accompanies this distribution, and is available at
 *    http://www.eclipse.org/legal/epl-v10.html
 *     
 *     Contributors:
 *         Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.providers.tables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.element.MTable;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.util.OptionUtil;

/**
 * {@link SiriusTableByTitleProvider} are used to get Sirius table representations by their title. All the tables that match the title
 * passed in the <b>title</b> argument will be output in the document.
 * 
 * @author ldelaigue
 */
public class SiriusTableByTitleProvider extends AbstractSiriusTableProvider {
    /**
     * The option key used to retrieve the 'title' option.
     */
    private static final String TITLE_OBJECT_KEY = "title";

    @Override
    public List<MTable> getTables(ResourceSet resourceSetForModels, Map<String, Object> parameters)
            throws ProviderException {
        EObject rootObject = (EObject) parameters.get(ProviderConstants.CONF_ROOT_OBJECT_KEY);
        boolean refreshTables = OptionUtil.mustRefreshRepresentation(parameters);
        Option<SessionTransientAttachment> attachement = SessionTransientAttachment
                .getSessionTransientAttachement(resourceSetForModels);
        if (!attachement.some()) {
            throw new ProviderException("Cannot find session associated to the models.");
        }
        Session session = attachement.get().getSession();

        Object tableTitle = parameters.get(TITLE_OBJECT_KEY);
        if (!(tableTitle instanceof String)) {
            throw new ProviderException(
                    "Table cannot be computed because no title has been declared in a 'title' parameter ("
                        + this.getClass().getName() + ")");
        }
        List<DTable> tables = getTablesByTitle((String) tableTitle, session, refreshTables);
        return extractTables(tables);
    }

    /**
     * Retrieve all tables whose title equals a given value.
     * 
     * @param title
     *            the title of the tables we want to find.
     * @param session
     *            the Sirius session in which to look for the tables.
     * @param refreshTables
     *            boolean indicate if table must be refreshed.
     * @return a list of maching tables, never <code>null</code> but possibly empty.
     */
    private List<DTable> getTablesByTitle(String title, Session session, Boolean refreshTables) {
        List<DTable> result = new ArrayList<DTable>();
        if (title != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                if (!(representation instanceof DTable)) {
                    continue;
                }
                if (title.equals(representation.getName()) && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
                    if (selectedViewpoints.contains(vp)) {
                        refreshTable(representation, session, refreshTables);
                        result.add((DTable) representation);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> optionsMap = new HashMap<String, OptionType>();
        optionsMap.put(TITLE_OBJECT_KEY, OptionType.AQL_EXPRESSION);
        return optionsMap;
    }

}
