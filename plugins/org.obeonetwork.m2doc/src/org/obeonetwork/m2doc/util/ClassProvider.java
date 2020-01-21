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
package org.obeonetwork.m2doc.util;

/**
 * Provides {@link Class} for service loading.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ClassProvider implements IClassProvider {

    /**
     * The {@link ClassLoader}.
     */
    private final ClassLoader classLoader;

    /**
     * Constructor.
     * 
     * @param classLoader
     *            the {@link ClassLoader}
     */
    public ClassProvider(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public Class<?> getClass(String className, String bundleName) throws ClassNotFoundException {
        return classLoader.loadClass(className);
    }

    /**
     * Gets the {@link ClassLoader}.
     * 
     * @return the classLoader the {@link ClassLoader}
     */
    protected ClassLoader getClassLoader() {
        return classLoader;
    }

}
