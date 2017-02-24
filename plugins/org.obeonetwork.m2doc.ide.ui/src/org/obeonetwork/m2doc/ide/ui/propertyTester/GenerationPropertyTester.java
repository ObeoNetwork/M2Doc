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
package org.obeonetwork.m2doc.ide.ui.propertyTester;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.genconf.Generation;

/**
 * Test if handler is launched on genconf file.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class GenerationPropertyTester extends PropertyTester {

    /**
     * Constructor
     */
    public GenerationPropertyTester() {
    }

    /**
     * Returns <code>true</code> when the generation has a local URI (platform or file)
     * 
     * @param generation
     *            the tested generation.
     * @return <code>true</code> for local objects.
     */
    private boolean isLocal(Generation generation) {
        URI uri = generation.eResource().getURI();
        return uri.isFile() || uri.isPlatform();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (receiver instanceof Generation && isLocal((Generation) receiver)) {
            return true;
        }
        if (receiver instanceof List) {
            for (Object object : (List) receiver) {
                if (!test(object, property, args, expectedValue)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
