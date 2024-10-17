/*******************************************************************************
 *  Copyright (c) 2016, 2024 Obeo. 
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
import org.obeonetwork.m2doc.genconf.Generation;

/**
 * Test if handler is launched on genconf file.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class GenerationPropertyTester extends PropertyTester {

    /**
     * Returns <code>true</code> when the generation has a local URI (platform or file).
     * 
     * @param generation
     *            the tested generation.
     * @return <code>true</code> for local objects.
     */
    private boolean isLocal(Generation generation) {
        URI uri = generation.eResource().getURI();
        return uri.isFile() || uri.isPlatform();
    }

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
            switch (property) {
                case "isGenconf":
                    res = receiver instanceof Generation && isLocal((Generation) receiver);
                    break;

                case "canGenerateTemplate":
                    if (receiver instanceof IFile) {
                        final URI uri = URI.createPlatformResourceURI(((IFile) receiver).getFullPath().toString(),
                                false);
                        final List<URI> genconfURIsFromTempate = GenconfPlugin.getPlugin()
                                .getGenconfURIsFromTempate(uri);
                        res = genconfURIsFromTempate != null && !genconfURIsFromTempate.isEmpty();
                    } else {
                        res = false;
                    }
                    break;

                case "canUpdateDocument":
                    if (receiver instanceof IFile) {
                        final URI uri = URI.createPlatformResourceURI(((IFile) receiver).getFullPath().toString(),
                                false);
                        final List<URI> genconfURIsFromResult = GenconfPlugin.getPlugin().getGenconfURIsFromResult(uri);
                        res = genconfURIsFromResult != null && !genconfURIsFromResult.isEmpty();
                    } else {
                        res = false;
                    }
                    break;

                default:
                    res = false;
                    break;
            }
        }

        return res;
    }

}
