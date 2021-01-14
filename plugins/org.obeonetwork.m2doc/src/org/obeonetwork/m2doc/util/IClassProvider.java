/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.util;

/**
 * Provides {@link Class} for service loading.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IClassProvider {

    /**
     * Gets the {@link Class} for the given class name and bundle name.
     * 
     * @param className
     *            the class name
     * @param bundleName
     *            the bundle name, can be <code>null</code>
     * @return the {@link Class} for the given class name and bundle name
     * @throws ClassNotFoundException
     *             if the class can't be found
     */
    Class<?> getClass(String className, String bundleName) throws ClassNotFoundException;

}
