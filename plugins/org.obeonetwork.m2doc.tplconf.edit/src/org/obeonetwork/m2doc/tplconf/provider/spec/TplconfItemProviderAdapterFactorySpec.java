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

import org.eclipse.emf.common.notify.Adapter;
import org.obeonetwork.m2doc.tplconf.provider.TplconfItemProviderAdapterFactory;

public class TplconfItemProviderAdapterFactorySpec extends TplconfItemProviderAdapterFactory {

    @Override
    public Adapter createEPackageMappingAdapter() {
        return new EPackageMappingItemProviderSpec(this);
    }

    @Override
    public Adapter createStructuredTypeAdapter() {
        return new StructuredTypeItemProviderSpec(this);
    }

    @Override
    public Adapter createTemplateConfigAdapter() {
        return new TemplateConfigItemProviderSpec(this);
    }
}
