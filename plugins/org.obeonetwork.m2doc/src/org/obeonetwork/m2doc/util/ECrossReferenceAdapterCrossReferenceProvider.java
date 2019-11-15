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

import java.util.Collection;

import org.eclipse.acceleo.query.runtime.CrossReferenceProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;

/**
 * A {@link CrossReferenceProvider} that use a given {@link ECrossReferenceAdapter} or the one of the given {@link EObject}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ECrossReferenceAdapterCrossReferenceProvider implements CrossReferenceProvider {

    /**
     * The {@link ECrossReferenceAdapter}.
     */
    final ECrossReferenceAdapter adapter;

    /**
     * Constructor.
     * 
     * @param adapter
     *            the {@link ECrossReferenceAdapter}
     */
    public ECrossReferenceAdapterCrossReferenceProvider(ECrossReferenceAdapter adapter) {
        this.adapter = adapter;
    }

    /**
     * Gets the {@link ECrossReferenceAdapter} either the {@link #adapter} or the one of the given {@link EObject}.
     * 
     * @param self
     *            the {@link EObject}
     * @return the {@link ECrossReferenceAdapter} either the {@link #adapter} or the one of the given {@link EObject} if any, <code>null</code>
     *         otherwise
     */
    private ECrossReferenceAdapter getAdapter(EObject self) {
        final ECrossReferenceAdapter res;

        if (adapter != null) {
            res = adapter;
        } else {
            res = ECrossReferenceAdapter.getCrossReferenceAdapter(self);
        }

        return res;
    }

    @Override
    public Collection<Setting> getInverseReferences(EObject self) {
        final Collection<Setting> res;

        final ECrossReferenceAdapter crossReferenceAdapter = getAdapter(self);
        if (crossReferenceAdapter != null) {
            res = crossReferenceAdapter.getInverseReferences(self);
        } else {
            throw new IllegalStateException("No ECrossReferenceAdapter found for :" + self);
        }

        return res;
    }

}
