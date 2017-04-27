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

import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;

/**
 * Configure for {@link IReadOnlyQueryEnvironment}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IServicesConfigurator {

    /**
     * Gets the {@link Set} of {@link IService} for the given {@link IReadOnlyQueryEnvironment} and options.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param options
     *            the {@link Map} of options
     * @return the {@link Set} of {@link IService} for the given {@link IReadOnlyQueryEnvironment}
     */
    Set<IService> getServices(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options);

    /**
     * Clears the services for the given {@link IReadOnlyQueryEnvironment} and options.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     */
    void cleanServices(IReadOnlyQueryEnvironment queryEnvironment);

}
