/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.util;

/**
 * Describes how to get a {@link EclipseClassProvider}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public interface IClassProviderDescriptor {

    /**
     * Gets the {@link EclipseClassProvider}.
     * 
     * @return the {@link EclipseClassProvider}
     */
    EclipseClassProvider getClassProvider();

}
