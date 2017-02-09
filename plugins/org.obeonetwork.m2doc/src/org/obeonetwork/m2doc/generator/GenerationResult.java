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
package org.obeonetwork.m2doc.generator;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.UserDoc;

/**
 * The
 * {@link org.obeonetwork.m2doc.util.M2DocUtils#generate(org.obeonetwork.m2doc.template.DocumentTemplate, org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment, java.util.Map, org.eclipse.emf.common.util.URI)
 * generation} result.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerationResult {

    /**
     * The {@link ValidationMessageLevel} for the generation.
     */
    private ValidationMessageLevel level = ValidationMessageLevel.OK;

    /**
     * Mapping of lost fragments from {@link UserDoc#getId() user doc ID} to fragment {@link URI}.
     */
    private final Map<String, URI> losts = new HashMap<String, URI>();

    /**
     * Updates the current {@link #getLevel() level} with the given {@link ValidationMessageLevel}.
     * 
     * @param levels
     *            the {@link ValidationMessageLevel}
     * @return the updated {@link #getLevel() level}
     */
    public ValidationMessageLevel updateLevel(ValidationMessageLevel... levels) {
        level = ValidationMessageLevel.updateLevel(level, levels);

        return level;
    }

    /**
     * Gets the mapping of lost fragments from {@link UserDoc#getId() user doc ID} to fragment {@link URI}.
     * 
     * @return the mapping of lost fragments from {@link UserDoc#getId() user doc ID} to fragment {@link URI}
     */
    public Map<String, URI> getLosts() {
        return losts;
    }

    /**
     * Gets the {@link ValidationMessageLevel}.
     * 
     * @return the {@link ValidationMessageLevel}
     */
    public ValidationMessageLevel getLevel() {
        return level;
    }

}
