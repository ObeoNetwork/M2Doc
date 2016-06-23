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

/**
 * {@link IDiagramProvider} instances are used to provide diagram's image file from any modeling tool or technology that porvides graphical
 * representations of models.
 * 
 * @author Romain Guider
 */
public abstract class DiagramProvider implements IProvider {
    /**
     * Returns the path to the image file of the diagram.
     * 
     * @param parameters
     *            a map of all parameter name to the corresponding object the provider can use. Global parameters always available are
     *            {@link ProviderConstants#KEY_CONF_ROOT_OBJECT},{@link ProviderConstants#KEY_PROJECT_ROOT_PATH} and
     *            {@link ProviderConstants#KEY_REPRESENTATION_TITLE}
     * @return the image file of the diagram.
     * @throws ProviderException
     *             if a problem occurs during retrieving.
     */
    public abstract List<String> getRepresentationImagePath(Map<String, Object> parameters) throws ProviderException;

}
