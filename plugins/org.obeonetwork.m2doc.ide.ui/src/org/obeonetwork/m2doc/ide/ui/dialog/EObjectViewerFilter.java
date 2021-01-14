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
package org.obeonetwork.m2doc.ide.ui.dialog;

import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

/**
 * Filters {@link EObject} by their {@link EClass}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class EObjectViewerFilter extends ViewerFilter {

    /**
     * The {@link Set} of containing {@link EStructuralFeature}.
     */
    private final Set<EStructuralFeature> containingFeatures;

    /**
     * The {@link Set} of accepted {@link EClass}.
     */
    private final Set<EClass> acceptedClasses;

    /**
     * Constructor.
     * 
     * @param containingFeatures
     *            the {@link Set} of containing {@link EStructuralFeature}
     * @param acceptedClasses
     *            the {@link Set} of accepted {@link EClass}
     */
    public EObjectViewerFilter(Set<EStructuralFeature> containingFeatures, Set<EClass> acceptedClasses) {
        this.containingFeatures = containingFeatures;
        this.acceptedClasses = acceptedClasses;
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        final boolean res;

        if (element instanceof Resource) {
            final Resource resource = (Resource) element;
            res = canContainsOrHas(resource);
        } else if (element instanceof EObject) {
            final EStructuralFeature containingFeature = ((EObject) element).eContainingFeature();
            res = containingFeature == null || containingFeatures.contains(containingFeature)
                || ((EObject) element).eContainer() instanceof Resource; // the last one is for CDO
        } else {
            res = false;
        }

        return res;
    }

    /**
     * Tells if the given {@link Resource} has {@link EObject} that have any of the given {@link EStructuralFeature} or has an instance of the
     * given {@link EClass}.
     * 
     * @param resource
     *            the {@link Resource} to check
     * @return <code>true</code> if the given {@link Resource} has {@link EObject} that have any of the given {@link EStructuralFeature} or has
     *         an instance of the given {@link EClass}, <code>false</code> otherwise
     */
    private boolean canContainsOrHas(final Resource resource) {
        boolean res = false;

        contains: for (EObject content : resource.getContents()) {
            if (isInstanceOfAny(content, acceptedClasses)) {
                res = true;
                break;
            } else {
                for (EReference reference : content.eClass().getEAllReferences()) {
                    if (containingFeatures.contains(reference)) {
                        res = true;
                        break contains;
                    }
                }
            }
        }

        return res;
    }

    /**
     * Tells if the given {@link EObject} is instance of any {@link EClass} in the given {@link Set}.
     * 
     * @param eObj
     *            the {@link EObject} to test
     * @param eClasses
     *            the {@link Set} of {@link EClass}
     * @return <code>true</code> if the given {@link EObject} is instance of any {@link EClass} in the given {@link Set}, <code>false</code>
     *         otherwise
     */
    private boolean isInstanceOfAny(EObject eObj, Set<EClass> eClasses) {
        boolean res = false;

        for (EClass eCls : eClasses) {
            if (eCls.isInstance(eObj)) {
                res = true;
                break;
            }
        }

        return res;
    }
}
