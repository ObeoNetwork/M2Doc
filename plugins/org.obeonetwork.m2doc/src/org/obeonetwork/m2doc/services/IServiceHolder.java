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
package org.obeonetwork.m2doc.services;

/**
 * Interface used to convey service package's classes through extensions.
 * 
 * @author Romain Guider
 */
public interface IServiceHolder {
    /**
     * Returns the actual service's class.
     * 
     * @return the actual service's class.
     */
    Class<?> getServiceClass();
}
