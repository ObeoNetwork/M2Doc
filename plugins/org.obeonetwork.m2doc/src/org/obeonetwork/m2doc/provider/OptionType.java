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
package org.obeonetwork.m2doc.provider;

/**
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public enum OptionType {
    /**
     * Any option used with this type will have it's value evaluating by M2Doc with its AQL parsing and evaluating capabilities.
     */
    AQL_EXPRESSION, /**
                     * Any option used with this type will be pass as a String to the provider.
                     */
    STRING
}
