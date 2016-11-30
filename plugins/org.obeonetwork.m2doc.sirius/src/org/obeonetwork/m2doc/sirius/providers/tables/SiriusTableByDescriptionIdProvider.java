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

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.sirius.session.CleaningAIRDJob;
import org.obeonetwork.m2doc.sirius.session.CleaningJobRegistry;
import org.obeonetwork.m2doc.sirius.util.OptionUtil;

/**
 * {@link SiriusTableByDescriptionIdProvider} are used to get Sirius table representations using a given root
 * eObject and a representation ID. All the tables attached to the given EObject (passed in argument <b>object</b>) and whose representation
 * ID equals the <b>tableId</b> argument will be output in the document.
 * 
 * @author ldelaigue
 */
public class SiriusTableByDescriptionIdProvider extends AbstractSiriusTableProvider {
    /**
     * The option key used to retrieve the {@link EObject} root of some diagram representations.
     */
    private static final String TARGET_ROOT_OBJECT_KEY = "object";
    /**
     * The key used in the map passed to {@link IProvider} to define the Sirius
     * table name (ID of the description in the odesign).
     */
    private static final String DESCRIPTION_ID_KEY = "descriptionId";
    /**
     * the key used in the map passed to {@link IProvider} to define the value of the create option.
     */
    private static final String CREATE_ID_KEY = "create";
    /**
     * The value to be tested for representation to be created.
     */
    private static final String CREATE_VALUE = "true";

    /**
     * Retrieve all representations whose target is the specified EObject and diagram description the given one.
     * 
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param diagramDescriptionName
     *            the diagram description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @param refreshTables
     *            boolean indicate if tables must be refreshed.
     * @return all representations whose target is the specified EObject
     */
    private List<DTable> getAssociatedTablesByDiagramDescriptionAndName(EObject targetRootObject,
            String diagramDescriptionName, Session session, Boolean refreshTables) {
        List<DTable> result = new ArrayList<DTable>();
        if (diagramDescriptionName != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(targetRootObject,
                    session);
            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
            for (DRepresentation representation : representations) {
                if (representation instanceof DTable
                    && diagramDescriptionName.equals(((DTable) representation).getDescription().getName())
                    && representation.eContainer() instanceof DView) {
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
    public List<MTable> getTables(Map<String, Object> parameters) throws ProviderException {
        Object tableId = parameters.get(DESCRIPTION_ID_KEY);
        Object target = parameters.get(TARGET_ROOT_OBJECT_KEY);
        boolean refreshTables = OptionUtil.mustRefreshRepresentation(parameters);
        Generation generation = (Generation) parameters.get(ProviderConstants.CONF_ROOT_OBJECT_KEY);
        List<MTable> result;
        boolean createRepresentation = CREATE_VALUE.equals(parameters.get(CREATE_ID_KEY));
        if (!(tableId instanceof String)) {
            throw new ProviderException(
                    "Table cannot be computed because no table name has been declared in a 'tableId' parameter ("
                        + this.getClass().getName() + ")");
        }
        if (!(target instanceof EObject)) {
            throw new ProviderException(
                    "Table cannot be computed because no root EObject has been declared in a 'object' parameter ("
                        + this.getClass().getName() + ")");
        }
        EObject eTarget = (EObject) target;
        Session session = SessionManager.INSTANCE.getSession(eTarget);
        if (session == null) {
            throw new ProviderException("Cannot find the session associated to the model root element.");
        }
        checkDiagramDescriptionExist(session, (String) tableId);
        List<DTable> tables = getAssociatedTablesByDiagramDescriptionAndName(eTarget, (String) tableId, session,
                refreshTables);
        if (!tables.isEmpty()) {
            result = extractTables(tables);
        } else if (createRepresentation) {
            RepresentationDescription description = findDiagramDescription(session, (String) tableId);
            session.getTransactionalEditingDomain().getCommandStack().execute(new CreateRepresentationCommand(session,
                    description, eTarget, (String) tableId, new NullProgressMonitor()));
            result = Collections.emptyList();
            for (DRepresentation representation : DialectManager.INSTANCE.getRepresentations(eTarget, session)) {
                if (representation instanceof DTable && ((DTable) representation).getDescription() == description) {
                    CleaningJobRegistry.INSTANCE.registerJob(generation,
                            new CleaningAIRDJob(eTarget, session, representation));
                    result = extractTables(Lists.newArrayList((DTable) representation));
                }
            }
        } else {
            result = Collections.emptyList();
        }
        return result;
    }

    /**
     * Returns the specified diagram representation.
     * 
     * @param session
     *            the session
     * @param diagramDescriptionName
     *            the diagram description name
     * @return the specified representation description.
     * @throws ProviderException
     *             if the specified representation doesn't exist.
     */
    private RepresentationDescription findDiagramDescription(Session session, String diagramDescriptionName)
            throws ProviderException {
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
        for (Viewpoint viewpoint : selectedViewpoints) {
            EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
            for (RepresentationDescription representationDescription : ownedRepresentations) {
                if (diagramDescriptionName.equals(representationDescription.getName())) {
                    return representationDescription;
                }
            }
        }
        throw new ProviderException(
                "The provided diagram description '" + diagramDescriptionName + "' does not exist in the loaded aird");
    }

    /**
     * Checks that the given description exists in the session. If not we throw an exception informing of the problem.
     * 
     * @param session
     *            the session were to find diagram description.
     * @param diagramDescriptionName
     *            the diagram description to find in the session.
     * @throws ProviderException
     *             if the diagram description does not exist in the session.
     */
    private void checkDiagramDescriptionExist(Session session, String diagramDescriptionName) throws ProviderException {
        if (findDiagramDescription(session, diagramDescriptionName) == null) {
            throw new ProviderException("The provided diagram description '" + diagramDescriptionName
                + "' does not exist in the loaded aird");
        }

    }

    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> optionsMap = new HashMap<String, OptionType>();
        optionsMap.put(TARGET_ROOT_OBJECT_KEY, OptionType.AQL_EXPRESSION);
        optionsMap.put(DESCRIPTION_ID_KEY, OptionType.STRING);
        return optionsMap;
    }

}
