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
package org.obeonetwork.m2doc.genconf.editor.command;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.obeonetwork.m2doc.genconf.GenconfPlugin;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.editor.command.GenerateHandler.GenerateJob;

/**
 * Contributes generate template menu.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerateTemplateContributionItem extends ContributionItem {

    @Override
    public void fill(Menu menu, int index) {
        super.fill(menu, index);

        int i = index;
        final URI templateURI = getSelectionURI();
        if (templateURI != null) {
            for (final URI uri : GenconfPlugin.getPlugin().getGenconfURIsFromTempate(templateURI)) {
                final MenuItem menuItem = new MenuItem(menu, SWT.PUSH, i++);
                menuItem.setText(uri.path());
                menuItem.addListener(SWT.Selection, new Listener() {

                    public void handleEvent(Event event) {
                        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

                        final Generation generation = GenconfUtils.getGeneration(uri);
                        final GenerateJob job = new GenerateJob(generation, shell);
                        job.setRule(ResourcesPlugin.getWorkspace().getRoot());
                        job.setUser(true);
                        job.schedule();
                    }
                });
            }
        }
    }

    /**
     * Gets the selected {@link URI}.
     * 
     * @return the selected {@link URI}
     */
    private URI getSelectionURI() {
        final URI res;

        final Object object = ((IStructuredSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getSelectionService().getSelection()).getFirstElement();
        if (object instanceof IFile) {
            res = URI.createPlatformResourceURI(((IFile) object).getFullPath().toString(), true);
        } else {
            res = null;
        }

        return res;
    }

}
