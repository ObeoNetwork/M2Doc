/*******************************************************************************
 *  Copyright (c) 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.ui.propertyTester;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Test if handler is launched on genconf file.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplatePropertyTester extends org.obeonetwork.m2doc.ide.ui.propertyTester.TemplatePropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        final boolean res;

        if (receiver instanceof List) {
            boolean allValid = true;
            for (Object object : (List<?>) receiver) {
                if (!test(object, property, args, expectedValue)) {
                    allValid = false;
                    break;
                }
            }
            res = allValid;
        } else {
            if (receiver instanceof GraphicalEditPart) {
                EObject eObj = (EObject) ((GraphicalEditPart) receiver).getAdapter(EObject.class);
                if (eObj instanceof DSemanticDecorator) {
                    res = super.test(((DSemanticDecorator) eObj).getTarget(), property, args, expectedValue);
                } else {
                    res = false;
                }
            } else {
                res = false;
            }
        }

        return res;
    }

}
