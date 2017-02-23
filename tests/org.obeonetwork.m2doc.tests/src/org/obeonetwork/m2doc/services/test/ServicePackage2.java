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
package org.obeonetwork.m2doc.services.test;

import org.eclipse.emf.ecore.EPackage;

/**
 * Test class used to test the service registry.
 * 
 * @author Romain Guider
 */
public class ServicePackage2 {

    /**
     * A custom service.
     * 
     * @param ePkg
     *            the {@link EPackage}
     * @return <code>"That's a custom service call."</code>
     */
    public String someOtherCustomService(EPackage ePkg) {
        return "That's an other custom service call.";
    }

}
