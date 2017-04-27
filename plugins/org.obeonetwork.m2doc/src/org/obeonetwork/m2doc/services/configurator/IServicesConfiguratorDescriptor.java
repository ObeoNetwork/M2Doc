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
 * Describes how to get a {@link IServicesConfigurator}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IServicesConfiguratorDescriptor {

    /**
     * Gets the {@link IServicesConfigurator}.
     * 
     * @return the {@link IServicesConfigurator}
     */
    IServicesConfigurator getServicesConfigurator();

}