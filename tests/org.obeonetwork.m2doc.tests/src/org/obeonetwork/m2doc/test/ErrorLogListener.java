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
package org.obeonetwork.m2doc.test;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;

/**
 * Component logging all event that would be placed in the error log view in their triggering order.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class ErrorLogListener implements ILogListener {

    /**
     * All status that have been logged in their triggering option.
     */
    private List<IStatus> allStatus;

    public ErrorLogListener() {
        allStatus = new ArrayList<IStatus>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.runtime.ILogListener#logging(org.eclipse.core.runtime.IStatus, java.lang.String)
     */
    @Override
    public void logging(IStatus status, String plugin) {
        allStatus.add(status);
    }

    /**
     * Returns all status logged in their triggering option.
     * 
     * @return all status logged in their triggering option.
     */
    public List<IStatus> getAllStatus() {
        return allStatus;
    }
}
