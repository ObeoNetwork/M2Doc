/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.propertyTester;

import java.util.List;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.genconf.GenconfPlugin;

/**
 * Tests update document menu visibility.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class UpdateDocumentPropertyTester extends PropertyTester {

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        final boolean res;

        if (receiver instanceof IFile) {
            final URI uri = URI.createPlatformResourceURI(((IFile) receiver).getFullPath().toString(), false);
            final List<URI> genconfURIsFromResult = GenconfPlugin.getPlugin().getGenconfURIsFromResult(uri);
            res = genconfURIsFromResult != null && !genconfURIsFromResult.isEmpty();
        } else {
            res = false;
        }

        return res;
    }

}
