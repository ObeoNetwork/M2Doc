/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.command;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;

/**
 * Abstract {@link Generation} handler.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractGenerationHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        final Shell shell = HandlerUtil.getActiveShell(event);
        if (selection instanceof IStructuredSelection) {
            Iterator<?> it = ((IStructuredSelection) selection).iterator();
            while (it.hasNext()) {
                final Object selected = it.next();
                final Generation generation;
                if (selected instanceof IFile
                    && GenconfUtils.GENCONF_EXTENSION_FILE.equals(((IFile) selected).getFileExtension())) {
                    URI genconfURI = URI.createPlatformResourceURI(((IFile) selected).getFullPath().toString(), true);
                    generation = getGeneration(genconfURI);
                } else if (selected instanceof Generation) {
                    generation = (Generation) selected;
                } else {
                    generation = null;
                }

                if (generation != null) {
                    execute(event, generation);
                } else {
                    MessageDialog.openError(shell, "Bad selection",
                            "Document generation action can only be triggered on Generation object or a .genconf file.");
                }
            }
        } else {
            MessageDialog.openError(shell, "Bad selection",
                    "Document generation action can only be triggered on Generation object or a .genconf file.");
        }

        return null;
    }

    /**
     * Executes the operation on the given {@link Generation}.
     * 
     * @param event
     *            the original {@link ExecutionEvent}
     * @param generation
     *            the {@link Generation}
     */
    protected abstract void execute(ExecutionEvent event, Generation generation);

    /**
     * Gets the Generation from the given {@link URI}.
     * 
     * @param uri
     *            the {@link URI}
     * @return the Generation from the given {@link URI}
     */
    protected Generation getGeneration(URI uri) {
        ResourceSet rs = new ResourceSetImpl();
        Resource modelResource = rs.getResource(uri, true);
        if (modelResource != null && !modelResource.getContents().isEmpty()) {
            EObject root = modelResource.getContents().get(0);
            if (root instanceof Generation) {
                return (Generation) root;
            }
        }
        return null;
    }

}
