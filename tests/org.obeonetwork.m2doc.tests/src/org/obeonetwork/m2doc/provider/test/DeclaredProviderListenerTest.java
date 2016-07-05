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
package org.obeonetwork.m2doc.provider.test;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.DeclaredProviderListener;
import org.obeonetwork.m2doc.test.ErrorLogListener;
import org.obeonetwork.m2doc.test.M2DocTestPlugin;

import static org.junit.Assert.assertTrue;

/**
 * {@link DeclaredProviderListener} test class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class DeclaredProviderListenerTest {
    /**
     * Listener that catch exception that would be put in the error log.
     */
    private ErrorLogListener errorLogListener;

    @Before
    public void setUp() {
        errorLogListener = M2DocTestPlugin.getDefault().getErrorLogListener();
        Platform.addLogListener(errorLogListener);
    }

    /**
     * Tests that the {@link DeclaredProviderListener} initialization has logged an error in the error log.
     */
    @Test
    public void listenerInitializedErrorTest() {
        assertTrue(findErrorWithMessage(errorLogListener.getAllStatus(),
                "Problem while registering M2Doc Providers : Plug-in org.obeonetwork.m2doc.tests was unable to load class org.obeonetwork.m2doc.test.Wrong."));
        assertTrue(findErrorWithMessage(errorLogListener.getAllStatus(),
                "Problem while registering M2Doc Providers : the provider \"org.obeonetwork.m2doc.provider.test.StubDiagramProvider\" is already registered. The current implementation will not be used."));
    }

    /**
     * Returns true if the given message is found in a status. False otherwise.
     * 
     * @param allStatus
     *            all the status logged.
     * @param errorMessage
     *            the message we want to find in a status.
     * @return true if the given message is found in a status. False otherwise.
     */
    private boolean findErrorWithMessage(List<IStatus> allStatus, String errorMessage) {
        boolean found = false;
        for (IStatus iStatus : allStatus) {
            if (errorMessage.equals(iStatus.getMessage())) {
                found = true;
            }
        }
        return found;
    }

}
