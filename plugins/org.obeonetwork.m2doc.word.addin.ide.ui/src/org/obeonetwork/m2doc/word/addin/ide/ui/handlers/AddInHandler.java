/*******************************************************************************
 *  Copyright (c) 2020 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.word.addin.ide.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.obeonetwork.m2doc.word.addin.CompletionServer;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class AddInHandler extends AbstractHandler {

    /**
     * The {@link CompletionServer}.
     */
    private CompletionServer server;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        try {
            if (server == null) {
                server = new CompletionServer();
                server.start(3000);
            } else {
                server.stop();
                server = null;
            }
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            // nothing to do here
        }

        return null;
    }
}
