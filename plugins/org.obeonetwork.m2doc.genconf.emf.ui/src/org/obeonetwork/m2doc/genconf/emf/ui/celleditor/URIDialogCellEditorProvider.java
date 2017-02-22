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
package org.obeonetwork.m2doc.genconf.emf.ui.celleditor;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.sirius.ext.emf.ui.ICellEditorProvider;
import org.eclipse.swt.widgets.Composite;
import org.obeonetwork.m2doc.genconf.Generation;

/**
 * Cell Editor Provider.
 * 
 * @author Nathalie Lepine <nathalie.lepine@obeo.fr>
 */
public class URIDialogCellEditorProvider implements ICellEditorProvider {

    /**
     * Constructor.
     */
    public URIDialogCellEditorProvider() {
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ext.emf.ui.ICellEditorProvider#provides(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.edit.provider.IItemPropertyDescriptor)
     */
    @Override
    public boolean provides(EObject eObject, IItemPropertyDescriptor itemPropertyDescriptor) {
        return eObject instanceof Generation;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ext.emf.ui.ICellEditorProvider#getCellEditor(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.edit.provider.IItemPropertyDescriptor, org.eclipse.swt.widgets.Composite)
     */
    @Override
    public CellEditor getCellEditor(EObject eObject, IItemPropertyDescriptor propertyDescriptor, Composite parent) {
        return new URIDialogCellEditor(parent);
    }

}
