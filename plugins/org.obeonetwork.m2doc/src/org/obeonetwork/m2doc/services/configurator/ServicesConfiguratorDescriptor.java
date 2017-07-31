/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.services.configurator;

/**
 * Basic implementation of {@link IServicesConfiguratorDescriptor}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServicesConfiguratorDescriptor implements IServicesConfiguratorDescriptor {

    /**
     * The {@link IServicesConfigurator}.
     */
    private final IServicesConfigurator configurator;

    /**
     * Constructor.
     * 
     * @param configurator
     *            the {@link IServicesConfigurator}
     */
    public ServicesConfiguratorDescriptor(IServicesConfigurator configurator) {
        this.configurator = configurator;
    }

    @Override
    public IServicesConfigurator getServicesConfigurator() {
        return configurator;
    }

    @Override
    public int hashCode() {
        return configurator.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ServicesConfiguratorDescriptor
            && ((ServicesConfiguratorDescriptor) obj).configurator == configurator;
    }

}
