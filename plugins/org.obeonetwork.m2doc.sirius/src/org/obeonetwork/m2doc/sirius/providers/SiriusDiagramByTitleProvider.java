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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.sirius.services.SiriusDiagramUtils;
import org.obeonetwork.m2doc.sirius.util.OptionUtil;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException {
        EObject rootObject = (EObject) parameters.get(ProviderConstants.CONF_ROOT_OBJECT_KEY);
        String rootPath = createTempDirectoryPath();
        List<String> diagramActivatedLayers = (List<String>) parameters
                .get(ProviderConstants.DIAGRAM_ACTIVATED_LAYERS_KEY);
        Session session = SessionManager.INSTANCE.getSession(rootObject);
        if (session == null) {
            throw new ProviderException("Cannot find session associated to the conf model root element.");
        }
        Object representationTitle = parameters.get(REPRESENTATION_TITLE_KEY);
        refreshRepresentations = OptionUtil.mustRefreshRepresentation(parameters);
        if (!(representationTitle instanceof String)) {
            throw new ProviderException(
                    "Image cannot be computed because no representation title has been provided to the provider \""
                        + this.getClass().getName() + "\"");
        } else {
            DRepresentationDescriptor representation = SiriusDiagramUtils
                    .getAssociatedRepresentationByName((String) representationTitle, session);
            if (representation != null && representation.getDescription() instanceof DiagramDescription) {
                DDiagram resolvedDiagram = (DDiagram) representation.getRepresentation();
                List<DRepresentationDescriptor> representations = new ArrayList<>(1);
                representations.add(representation);
                final InfinitLoopSafeService imageUtility = new InfinitLoopSafeService();
                List<String> resultList = SiriusDiagramUtils.generateAndReturnDiagramImages(rootPath, session,
                        imageUtility, refreshRepresentations, representations,
                        getLayers(resolvedDiagram, diagramActivatedLayers));

                // This is totally boggus since more than one image can be generated.
                // But I'll keep it iso bug.
                setHeight(imageUtility.getHeight());
                setWidth(imageUtility.getWidth());

                return resultList;
            } else {
                throw new ProviderException("Representation with title '" + representationTitle + "' not found");
            }
        }
    }

    @Override
    public Map<String, OptionType> getOptionTypes() {
        Map<String, OptionType> options = new HashMap<>();
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
