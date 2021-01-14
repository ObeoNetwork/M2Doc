/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests;

import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.util.MemoryURIHandler;

/**
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TestMemoryURIHandler extends MemoryURIHandler {

    /**
     * The protocol name.
     */
    public static final String PROTOCOL = "m2doctest";

    @Override
    public boolean canHandle(URI uri) {
        return PROTOCOL.equals(uri.scheme());
    }

}
