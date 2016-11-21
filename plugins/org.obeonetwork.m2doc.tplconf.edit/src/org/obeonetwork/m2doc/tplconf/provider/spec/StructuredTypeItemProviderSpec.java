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
package org.obeonetwork.m2doc.tplconf.provider.spec;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.provider.StructuredTypeItemProvider;

public class StructuredTypeItemProviderSpec extends StructuredTypeItemProvider {

    public StructuredTypeItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public Object getImage(Object object) {
        if (object instanceof StructuredType) {
            EObject eClassifier = ((StructuredType) object).getEClassifier();
            if (eClassifier != null) {
                return super.getImage(eClassifier);
            }
        }
        return super.getImage(object);
    }

    @Override
    public String getText(Object object) {
        if (object instanceof StructuredType) {
            EObject eClassifier = ((StructuredType) object).getEClassifier();
            if (eClassifier != null) {
                return super.getText(eClassifier);
            }
        }
        return super.getText(object);
    }
}
