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

import java.util.Arrays;
import java.util.Collections;

import org.eclipse.emf.ecore.presentation.EcoreActionBarContributor.ExtendedLoadResourceAction.RegisteredPackageDialog;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

/**
 * Open registered package dialog.
 * 
 * @author Nathalie Lepine <nathalie.lepine@obeo.fr>
 */
public class URIDialogCellEditor extends DialogCellEditor {

	/**
	 * Constructor.
	 * 
	 * @param parent
	 *            Composite
	 */
	public URIDialogCellEditor(Composite parent) {
		super(parent, SWT.NONE);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.viewers.DialogCellEditor#openDialogBox(org.eclipse.swt.widgets.Control)
	 */
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		RegisteredPackageDialog dialog = new RegisteredPackageDialog(Display.getCurrent().getActiveShell());
		if (dialog.open() == Dialog.OK && dialog.getResult() != null) {
			return Arrays.asList(dialog.getResult());
		}
		return Collections.emptyList();
	}

}
