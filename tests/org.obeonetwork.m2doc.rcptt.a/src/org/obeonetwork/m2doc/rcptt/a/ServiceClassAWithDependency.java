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
package org.obeonetwork.m2doc.rcptt.a;

import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.rcptt.b.ServiceClassB;

/**
 * Test service class.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServiceClassAWithDependency {

    /**
     * A service.
     * 
     * @param string
     *            the {@link String}
     * @param eObj
     *            the {@link EObject}
     * @return a {@link String}
     */
    public String serviceAWithDependency(String string, EObject eObj) {
        final StringBuilder builder = new StringBuilder();

        builder.append("ServiceClassAWithDependency.serviceAWithDependency");
        builder.append('\n');
        builder.append("String parameter is a String: " + (string instanceof String));
        builder.append('\n');
        builder.append(string);
        builder.append('\n');
        builder.append("EObject parameter is a EObject: " + (eObj instanceof EObject));
        builder.append('\n');
        builder.append(eObj.toString());
        builder.append('\n');

        builder.append(new ServiceClassB().serviceB(string, eObj).replaceAll("\n", "\t\n"));

        return builder.toString();
    }

}
