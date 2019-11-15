/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IRootEObjectProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * An {@link IRootEObjectProvider} for {@link ResourceSet}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ResourceSetRootEObjectProvider implements IRootEObjectProvider {

    /**
     * The {@link ResourceSet}.
     */
    private final ResourceSet resourceSet;

    /**
     * Constructor.
     * 
     * @param resourceSet
     *            the {@link ResourceSet}
     */
    public ResourceSetRootEObjectProvider(ResourceSet resourceSet) {
        this.resourceSet = resourceSet;
    }

    @Override
    public Set<EObject> getRoots() {
        Set<EObject> res = new HashSet<>();

        for (Resource resource : new ArrayList<>(resourceSet.getResources())) {
            res.addAll(resource.getContents());
        }

        return res;
    }

}
