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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.SessionTransientAttachment;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.provider.OptionType;
import org.obeonetwork.m2doc.provider.ProviderConstants;
import org.obeonetwork.m2doc.provider.ProviderException;
import org.obeonetwork.m2doc.provider.ProviderValidationMessage;
import org.obeonetwork.m2doc.sirius.services.SiriusRepresentationUtils;
import org.obeonetwork.m2doc.sirius.util.OptionUtil;

/**
 * {@link SiriusDiagramByTitleProvider} are used to get sirius diagrams from an
 * eObject and a name.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
@SuppressWarnings("restriction")
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
    public List<String> getRepresentationImagePath(ResourceSet resourceSetForModel, Map<String, Object> parameters)
            throws ProviderException {
        String rootPath = createTempDirectoryPath();
        List<String> diagramActivatedLayers = (List<String>) parameters
                .get(ProviderConstants.DIAGRAM_ACTIVATED_LAYERS_KEY);
        Option<SessionTransientAttachment> attachement = SessionTransientAttachment
                .getSessionTransientAttachement(resourceSetForModel);
        if (!attachement.some()) {
            throw new ProviderException("Cannot find session associated to the models.");
        }
        Session session = attachement.get().getSession();
        Object representationTitle = parameters.get(REPRESENTATION_TITLE_KEY);
        refreshRepresentations = OptionUtil.mustRefreshRepresentation(parameters);
        if (!(representationTitle instanceof String)) {
            throw new ProviderException(
                    "Image cannot be computed because no representation title has been provided to the provider \""
                        + this.getClass().getName() + "\"");
        } else {
            DRepresentationDescriptor representation = SiriusRepresentationUtils
                    .getAssociatedRepresentationByName((String) representationTitle, session);
            if (representation != null && representation.getDescription() instanceof DiagramDescription) {
                DDiagram resolvedDiagram = (DDiagram) representation.getRepresentation();
                List<DRepresentationDescriptor> representations = new ArrayList<>(1);
                representations.add(representation);
                List<String> resultList = SiriusRepresentationUtils.generateAndReturnDiagramImages(rootPath, session,
                        refreshRepresentations, representations, getLayers(resolvedDiagram, diagramActivatedLayers));

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
