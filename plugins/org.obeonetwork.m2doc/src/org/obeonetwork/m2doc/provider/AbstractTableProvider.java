/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.provider;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.obeonetwork.m2doc.element.MTable;

/**
 * Abstract super-implementation of all table providers.
 * 
 * @author ldelaigue
 */
public abstract class AbstractTableProvider implements IProvider {

    /**
     * Provide the tables to insert in the word document.
     * 
     * @param parameters
     *            Map of arguments
     * @param resourceSetForModels
     *            the resourceset used for loading the models.
     * @return The list of table to insert, may be empty but should not be <code>null</code>.
     * @throws ProviderException
     *             If something goes wrong during the computation of the tables.
     */
    public abstract List<MTable> getTables(ResourceSet resourceSetForModels, Map<String, Object> parameters)
            throws ProviderException;
}
