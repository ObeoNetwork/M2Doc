/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/

package org.obeonetwork.m2doc.rcptt.ui.command;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.obeonetwork.m2doc.tests.M2DocTestUtils;

/**
 * Asserts that two .docx files are equals.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AssertEqualsHandler extends AbstractHandler {

    /**
     * "M2Doc assert equals .docx" message.
     */
    private static final String M2_DOC_ASSERT_EQUALS_DOCX = "M2Doc assert equals .docx";

    /**
     * The command has been executed, so extract extract the needed information
     * from the application context.
     * 
     * @param event
     *            the {@link ExecutionEvent}
     * @throws ExecutionException
     *             if something went wrong
     * @return <code>null</code>
     */
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelection selection = HandlerUtil.getCurrentSelection(event);
        Shell shell = HandlerUtil.getActiveShell(event);

        if (selection instanceof IStructuredSelection) {
            final List<?> selected = ((IStructuredSelection) selection).toList();
            if (selected.size() == 2 && selected.get(0) instanceof IFile && selected.get(1) instanceof IFile) {
                final IFile expected = (IFile) selected.get(0);
                final IFile actual = (IFile) selected.get(1);

                URI expectedURI = URI.createPlatformResourceURI(expected.getFullPath().toString(), true);
                URI actualURI = URI.createPlatformResourceURI(actual.getFullPath().toString(), true);
                try {
                    M2DocTestUtils.assertDocx(expectedURI, actualURI);
                    MessageDialog.openInformation(shell, M2_DOC_ASSERT_EQUALS_DOCX, "Same .docx files");
                } catch (FileNotFoundException e) {
                    MessageDialog.openInformation(shell, M2_DOC_ASSERT_EQUALS_DOCX, e.getMessage());
                } catch (IOException e) {
                    MessageDialog.openInformation(shell, M2_DOC_ASSERT_EQUALS_DOCX, e.getMessage());
                }
            }
        }

        return null;
    }

}
