/*******************************************************************************
 *  Copyright (c) 2021 Obeo. 
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

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.editor.view.M2DocInterpreterView;
import org.obeonetwork.m2doc.ide.ui.dialog.M2DocFileSelectionDialog;

/**
 * Loads the {@link Generation} in the {@link M2DocInterpreterView}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class LoadInterpreterGenerationHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        final IWorkbenchPart activePart = HandlerUtil.getActivePart(event);

        if (activePart instanceof M2DocInterpreterView) {
            final M2DocInterpreterView view = (M2DocInterpreterView) activePart;
            final Shell shell = HandlerUtil.getActiveShell(event);
            final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(shell, "Select generation file.",
                    getFileName(view.getGenconfURI()), GenconfUtils.GENCONF_EXTENSION_FILE, false);
            final int dialogResult = dialog.open();
            if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                final URI genconfURI = URI.createPlatformResourceURI(dialog.getFileName(), true);
                view.setGenconfURI(genconfURI);
            }
        }

        return null;
    }

    /**
     * Gets the file name from the given {@link URI}.
     * 
     * @param uri
     *            the {@link URI}
     * @return the file name from the given {@link URI}
     */
    private String getFileName(URI uri) {
        final String res;

        if (uri != null) {
            if (uri.isPlatformResource()) {
                res = uri.toPlatformString(true);
            } else if (uri.isFile()) {
                res = uri.path();
            } else {
                res = "";
            }
        } else {
            res = "";
        }

        return res;
    }

}
