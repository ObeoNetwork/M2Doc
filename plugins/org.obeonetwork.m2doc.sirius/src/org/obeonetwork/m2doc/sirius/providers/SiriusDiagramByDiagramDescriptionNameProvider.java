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
package org.obeonetwork.m2doc.sirius.providers;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.sirius.util.OptionUtil;

/**
 * {@link SiriusDiagramByDiagramDescriptionNameProvider} are used to get Sirius diagrams images from all representations using a given root
 * eObject and a diagram description name.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class SiriusDiagramByDiagramDescriptionNameProvider extends AbstractSiriusDiagramImagesProvider {
    /**
     * The option key used to retrieve the {@link EObject} root of some diagram representations.
     */
    private static final String TARGET_ROOT_OBJECT_KEY = "object";
    /**
     * The key used in the map passed to {@link IProvider} to define the Sirius
     * representation title from which image will be computed.
     */
    private static final String DIAGRAM_DESCRIPTION_ID_KEY = "descriptionId";

    /**
     * Default constructor.
     */
    public SiriusDiagramByDiagramDescriptionNameProvider() {
    }

    /**
     * Retrieve all representations whose target is the specified EObject and diagram description the given one.
     * 
     * @param targetRootObject
     *            Object which is the target of the representations.
     * @param diagramDescriptionName
     *            the diagram description from which we want to retrieve representations.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return all representations whose target is the specified EObject
     */
    private List<DRepresentation> getAssociatedRepresentationByDiagramDescriptionAndName(EObject targetRootObject,
            String diagramDescriptionName, Session session) {
        List<DRepresentation> result = new ArrayList<DRepresentation>();
        if (diagramDescriptionName != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(targetRootObject,
                    session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                if (representation instanceof DDiagram
                    && diagramDescriptionName.equals(((DDiagram) representation).getDescription().getName())
                    && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
                    if (selectedViewpoints.contains(vp)) {
                        result.add(representation);
                    }
                }
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException {
        String rootPath = (String) parameters.get(ProviderConstants.PROJECT_ROOT_PATH_KEY);
        Object diagramDescriptionName = parameters.get(DIAGRAM_DESCRIPTION_ID_KEY);
        Object targetRootObject = parameters.get(TARGET_ROOT_OBJECT_KEY);
        List<String> diagramActivatedLayers = (List<String>) parameters
                .get(ProviderConstants.DIAGRAM_ACTIVATED_LAYERS_KEY);
        refreshRepresentations = OptionUtil.mustRefreshRepresentation(parameters);
        if (!(diagramDescriptionName instanceof String)) {
            throw new ProviderException(
                    "Image cannot be computed because no diagram description name has been provided to the provider \""
                        + this.getClass().getName() + "\"");
        } else if (!(targetRootObject instanceof EObject)) {
            throw new ProviderException(
                    "Image cannot be computed because no root EObject has been provided to the provider \""
                        + this.getClass().getName() + "\"");
        } else {
            EObject targetRootEObject = (EObject) targetRootObject;
            Session session = SessionManager.INSTANCE.getSession((EObject) targetRootObject);
            if (session == null) {
                throw new ProviderException("Cannot find session associated to the conf model root element.");
            }
            checkDiagramDescriptionExist(session, (String) diagramDescriptionName);
            List<DRepresentation> representations = getAssociatedRepresentationByDiagramDescriptionAndName(
                    targetRootEObject, (String) diagramDescriptionName, session);
            if (!representations.isEmpty() && representations.get(0) instanceof DDiagram) {
                return generateAndReturnDiagramImages(rootPath, session, representations,
                        getLayers((DDiagram) representations.get(0), diagramActivatedLayers));
            }
            return generateAndReturnDiagramImages(rootPath, session, representations, Lists.<Layer> newArrayList());
        }
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
        boolean diagramDescriptionExist = false;
        Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
        for (Viewpoint viewpoint : selectedViewpoints) {
            EList<RepresentationDescription> ownedRepresentations = viewpoint.getOwnedRepresentations();
            for (RepresentationDescription representationDescription : ownedRepresentations) {
                if (diagramDescriptionName.equals(representationDescription.getName())) {
                    diagramDescriptionExist = true;
                }
            }
        }
        if (!diagramDescriptionExist) {
            throw new ProviderException("The provided diagram description '" + diagramDescriptionName
                + "' does not exist in the loaded aird");
        }

    }

    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> optionsMap = new HashMap<String, OptionType>();
        optionsMap.put(TARGET_ROOT_OBJECT_KEY, OptionType.AQL_EXPRESSION);
        optionsMap.put(DIAGRAM_DESCRIPTION_ID_KEY, OptionType.STRING);
        return optionsMap;
    }

    @Override
    public boolean isDefault() {
        return false;
    }

    @Override
    public List<ProviderValidationMessage> validate(Map<String, Object> options) {
        // TODO validate
        return Collections.emptyList();
    }

}
