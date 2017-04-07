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
package org.obeonetwork.m2doc.provider;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * {@link IDiagramProvider} instances are used to provide diagram's image file from any modeling tool or technology that porvides graphical
 * representations of models.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public abstract class AbstractDiagramProvider implements IProvider {

    /**
     * Returns the path to the image file of the diagram.
     * 
     * @param resourceSetForModels
     *            the resourceset used for loading the models.
     * @param parameters
     *            a map of all parameter name to the corresponding object the provider can use. Global parameters always available are
     *            {@link ProviderConstants#CONF_ROOT_OBJECT_KEY} which give the EObject of the Genconf model from which the generation has
     *            been launched
     * @return the image file of the diagram.
     * @throws ProviderException
     *             if a problem occurs during retrieving.
     */
    public abstract List<String> getRepresentationImagePath(ResourceSet resourceSetForModels,
            Map<String, Object> parameters) throws ProviderException;

    /**
     * Return if the provider is a default one.
     * 
     * @return if the provider is a default one.
     */
    public abstract boolean isDefault();

    /**
     * Should be called after the document generation so that the implementer can cleanup temporary files.
     */
    public void clear() {
        // default implementation has nothing to clean.
    }
}
