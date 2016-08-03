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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;

/**
 * {@link SiriusDiagramByTitleProvider} are used to get sirius diagrams from an
 * eObject and a name.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class SiriusDiagramByTitleProvider extends AbstractSiriusDiagramImagesProvider {
    /**
     * The key used in the map passed to {@link IProvider} to define the Sirius
     * representation title from which image will be computed.
     */
    private static final String REPRESENTATION_TITLE_KEY = "title";

    /**
     * Default constructor.
     */
    public SiriusDiagramByTitleProvider() {
    }

    /**
     * Replace forbidden characters with "_" in a filename.
     * 
     * @param name
     *            the string to sanitized.
     * @return sanitized string.
     */
    public static String sanitizeFilename(String name) {
        return name.replaceAll("[:\\\\/*?|<>]", "_");
    }

    /**
     * Retrieve all representations whose target is the specified EObject.
     * 
     * @param representationName
     *            the name of the representation from which we want to create an image.
     * @param session
     *            the Sirius session from which we want to find the representation with the given name.
     * @return the corresponding representation.
     */
    private DRepresentation getAssociatedRepresentationByName(String representationName, Session session) {
        if (representationName != null) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getAllRepresentations(session);

            // Filter representations to keep only those in a selected viewpoint
            Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);

            for (DRepresentation representation : representations) {
                if (representationName.equals(representation.getName())
                    && representation.eContainer() instanceof DView) {
                    DView dView = (DView) representation.eContainer();
                    Viewpoint vp = dView.getViewpoint();
                    if (selectedViewpoints.contains(vp)) {
                        return representation;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException {
        EObject rootObject = (EObject) parameters.get(ProviderConstants.CONF_ROOT_OBJECT_KEY);
        String rootPath = (String) parameters.get(ProviderConstants.PROJECT_ROOT_PATH_KEY);
        Session session = SessionManager.INSTANCE.getSession(rootObject);
        if (session == null) {
            throw new ProviderException("Cannot find session associated to the conf model root element.");
        }
        Object representationTitle = parameters.get(REPRESENTATION_TITLE_KEY);
        if (!(representationTitle instanceof String)) {
            throw new ProviderException(
                    "Image cannot be computed because no representation title has been provided to the provider \""
                        + this.getClass().getName() + "\"");
        } else {
            DRepresentation representation = getAssociatedRepresentationByName((String) representationTitle, session);
            if (representation instanceof DSemanticDiagram) {
                DSemanticDiagram dsd = (DSemanticDiagram) representation;
                List<DRepresentation> representations = new ArrayList<DRepresentation>(1);
                representations.add(dsd);
                List<String> resultList = generateAndReturnDiagramImages(rootPath, session, representations);
                return resultList;
            } else {
                throw new ProviderException("Representation with title '" + representationTitle + "' not found");
            }
        }
    }

    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> options = new HashMap<String, OptionType>();
        options.put(REPRESENTATION_TITLE_KEY, OptionType.AQL_EXPRESSION);
        return options;
    }

    @Override
    public boolean isDefault() {
        return true;
    }

    @Override
    public List<ProviderValidationMessage> validate(Map<String, Object> options) {
        // TODO validate
        return Collections.emptyList();
    }

}
