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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IService;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Configure for {@link IReadOnlyQueryEnvironment}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IServicesConfigurator {

    /**
     * Gets the {@link List} of options managed by this configurator.
     * 
     * @return the {@link List} of options managed by this configurator
     */
    List<String> getOptions();

    /**
     * Gets the {@link Map} of initialized options.
     * 
     * @param options
     *            the {@link Map} of existing options.
     * @return the {@link Map} of initialized options
     */
    Map<String, String> getInitializedOptions(Map<String, String> options);

    /**
     * Validates the given options.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param options
     *            the {@link Map} of options
     * @return the {@link Map} of option name to its {@link Diagnostic}
     */
    Map<String, List<Diagnostic>> validate(IReadOnlyQueryEnvironment queryEnvironment, Map<String, String> options);

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

    /**
     * Create a new resourceSet which would need specific initialization for loading the models according to the given options.
     * 
     * @param context
     *            the {@link Object} context used in {@link #cleanResourceSetForModels(Object)}
     * @param options
     *            the {@link Map} of options
     * @return the created {@link ResourceSet} if any, <code>null</code> otherwise
     * @see #cleanResourceSetForModels(Object)
     */
    ResourceSet createResourceSetForModels(Object context, Map<String, String> options);

    /**
     * Cleans the {@link #createResourceSetForModels(Object, Map) created} {@link ResourceSet} for the given
     * {@link IReadOnlyQueryEnvironment}.
     * 
     * @param context
     *            the {@link Object} context
     */
    void cleanResourceSetForModels(Object context);

}
