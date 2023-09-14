/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf;

import java.util.Set;

import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.validation.type.AbstractCollectionType;
import org.eclipse.acceleo.query.validation.type.ICollectionType;
import org.eclipse.acceleo.query.validation.type.IType;

/**
 * Set validation type.
 * forked see https://github.com/eclipse-acceleo/acceleo/issues/108
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ForkedSetType extends AbstractCollectionType {

    /**
     * Constructor.
     * 
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param type
     *            the {@link IType}
     */
    public ForkedSetType(IReadOnlyQueryEnvironment queryEnvironment, IType type) {
        super(queryEnvironment, type);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.acceleo.query.validation.type.IType#getType()
     */
    @Override
    public Class<?> getType() {
        return Set.class;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Set(" + getCollectionType().toString() + ")";
    }

    @Override
    public boolean isAssignableFrom(IType otherType) {
        final boolean res;

        if (super.isAssignableFrom(otherType)) {
            if (otherType instanceof ICollectionType) {
                res = getCollectionType().isAssignableFrom(((ICollectionType) otherType).getCollectionType());
            } else {
                // not enough information, assume everything is OK.
                res = true;
            }
        } else {
            res = false;
        }

        return res;
    }

}
