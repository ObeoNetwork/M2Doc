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
package org.obeonetwork.m2doc.genconf.design.services;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.obeonetwork.m2doc.genconf.design.Activator;

/**
 * EMF Services.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class EMFServices {

    /**
     * return label from .edit itemProvider.
     * 
     * @param eObject
     *            EObject
     * @return label from .edit itemProvider.
     */
    public String getLabel(EObject eObject) {
        AdapterFactory adapterFactory = Activator.getDefault().getAdapterFactory();
        if (adapterFactory != null) {
            IItemLabelProvider itemLabelProvider = (IItemLabelProvider) adapterFactory.adapt(eObject,
                    IItemLabelProvider.class);
            if (itemLabelProvider != null) {
                return itemLabelProvider.getText(eObject);
            }
        }
        return "";
    }
}
