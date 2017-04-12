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
import org.eclipse.core.resources.IResource;
import org.obeonetwork.m2doc.ide.M2DocPlugin;

/**
 * Test if handler is launched on docx file.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class TemplatePropertyTester extends PropertyTester {

    /**
     * Constructor
     */
    public TemplatePropertyTester() {
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        boolean isApplicable = false;
        if (receiver instanceof IResource
            && M2DocPlugin.DOCX_EXTENSION_FILE.equals(((IResource) receiver).getFileExtension())) {
            isApplicable = true;
        } else if (receiver instanceof List) {
            for (Object object : (List) receiver) {
                if (!test(object, property, args, expectedValue)) {
                    isApplicable = false;
                    break;
                }
            }

        }
        return isApplicable;
    }

}
