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
     * Must returns a map of option key to the {@link OptionType} corresponding for each new option handled by the provider.
     * A missing handled option in the map will cause parsing errors.
     * 
     * @return a map of option key to the {@link OptionType} corresponding for each new option handled by the provider.
     */
    Map<String, OptionType> getOptionTypes();
}
