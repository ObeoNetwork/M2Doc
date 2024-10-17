/*******************************************************************************
 *  Copyright (c) 2024 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.preference;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PathEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.obeonetwork.m2doc.ide.ui.M2DocUIPlugin;

/**
 * Template M2Doc libraries preferences page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocLibrariesPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * Constructor.
     */
    public M2DocLibrariesPreferences() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench workbench) {
        final IPreferenceStore prefStore = M2DocUIPlugin.getDefault().getPreferenceStore();
        setPreferenceStore(prefStore);
        setDescription("M2Doc libraries preferences");
    }

    @Override
    protected void createFieldEditors() {
        addField(new PathEditor(M2DocUIPlugin.LIBRARIES_PREFERENCES, "Template libraries", "Select M2Doc library folder",
                getFieldEditorParent()));
    }

}
