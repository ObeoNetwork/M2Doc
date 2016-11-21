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
import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.provider.EPackageMappingItemProvider;

public class EPackageMappingItemProviderSpec extends EPackageMappingItemProvider {

    public EPackageMappingItemProviderSpec(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    @Override
    public String getText(Object object) {
        if (object instanceof EPackageMapping) {
            return ((EPackageMapping) object).getName() + " - " + ((EPackageMapping) object).getUri();
        }
        return super.getText(object);
    }
}
