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

import java.util.Map;

/**
 * Interface used by any provider that extends the capabilities of information retrieving of a tag like diagram tag.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public interface IProvider {
    /**
     * Returns a map of option key to the {@link OptionType} corresponding.
     * 
     * @return a map of option key to the {@link OptionType} corresponding. Null or empty list if not used.
     */
    Map<String, OptionType> getOptionTypes();
}
