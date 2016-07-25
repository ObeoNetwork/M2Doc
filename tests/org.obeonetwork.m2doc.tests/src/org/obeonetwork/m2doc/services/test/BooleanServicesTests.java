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

import org.junit.Test;
import org.obeonetwork.m2doc.services.BooleanServices;

import static org.junit.Assert.assertEquals;

/**
 * Tests {@link BooleanServices}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class BooleanServicesTests {

    /**
     * The service to test.
     */
    private final BooleanServices services = new BooleanServices();

    /**
     * check(true).
     */
    @Test
    public void checkTrue() {
        assertEquals("X", services.check(true));
    }

    /**
     * check(false).
     */
    @Test
    public void checkFalse() {
        assertEquals("", services.check(false));
    }

    /**
     * yesNo(true).
     */
    @Test
    public void yesNoTrue() {
        assertEquals("Yes", services.yesNo(true));
    }

    /**
     * yesNo(false).
     */
    @Test
    public void yesNoFalse() {
        assertEquals("No", services.yesNo(false));
    }

}
