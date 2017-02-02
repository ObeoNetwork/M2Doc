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
package org.obeonetwork.m2doc.api;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.ClassType;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.acceleo.query.validation.type.SequenceType;
import org.eclipse.acceleo.query.validation.type.SetType;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.obeonetwork.m2doc.M2DocPlugin;
import org.obeonetwork.m2doc.services.ServiceRegistry;

/**
 * AQL services.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public final class QueryServices {

    /**
     * Instance.
     */
    private static QueryServices eINSTANCE = new QueryServices();

    /**
     * Constructor.
     */
    private QueryServices() {
        super();
    }

    /**
     * return the instance.
     * 
     * @return the instance
     */
    public static QueryServices getInstance() {
        return eINSTANCE;
    }

    /**
     * get Acceleo environment.
     * 
     * @return IQueryEnvironment
     */
    public IQueryEnvironment getAcceleoEnvironment() {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        return queryEnvironment;
    }

    /**
     * Registers services for use by the AQL evaluation engine.
     * 
     * @param env
     *            registers the services
     */
    public void registerServices(IQueryEnvironment env) {
        List<Class<?>> services = ServiceRegistry.INSTANCE.getServicePackages(ServiceRegistry.DEFAULT_TOKEN);
        for (Class<?> cls : services) {
            AQL4Compat.register(env, cls);
        }
    }

    /**
     * Register package uris.
     * 
     * @param packageURIs
     *            EList
     * @param queryEnvironment
     *            IQueryEnvironment
     */
    public void registerPackages(EList<String> packageURIs, IQueryEnvironment queryEnvironment) {
        for (String nsURI : packageURIs) {
            EPackage p = EPackage.Registry.INSTANCE.getEPackage(nsURI);
            if (p == null) {
                M2DocPlugin.INSTANCE.log(
                        new Status(Status.WARNING, M2DocPlugin.PLUGIN_ID, "Couldn't find package with nsURI " + nsURI));
            } else {
                queryEnvironment.registerEPackage(p);
            }
        }
    }

    /**
     * Gets the {@link IType} from object.
     * 
     * @param environment
     *            IReadOnlyQueryEnvironment
     * @param value
     *            Object
     * @return the {@link IType} from variables
     */
    public Set<IType> getTypes(IReadOnlyQueryEnvironment environment, Object value) {
        final Set<IType> types = new LinkedHashSet<>();
        if (value instanceof EObject) {
            types.add(new EClassifierType(environment, ((EObject) value).eClass()));
        } else if (value instanceof List) {
            types.add(new SequenceType(environment, new ClassType(environment, Object.class)));
        } else if (value instanceof Set) {
            types.add(new SetType(environment, new ClassType(environment, Object.class)));
        } else if (value == null) {
            types.add(new ClassType(environment, null));
        } else {
            types.add(new ClassType(environment, value.getClass()));
        }
        return types;
    }

}
