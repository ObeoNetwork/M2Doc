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
package org.obeonetwork.m2doc.word.addin.ide.ui.preferences;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

/**
 * A field editor for host.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class HostFieldEditor extends StringFieldEditor {

    /**
     * Constructor.
     * 
     * @param name
     *            the field name
     * @param labelText
     *            the label
     * @param width
     *            the width in char
     * @param parent
     *            hte parent {@link Composite}
     */
    public HostFieldEditor(String name, String labelText, int width, Composite parent) {
        super(name, labelText, width, parent);
    }

    @Override
    protected boolean doCheckState() {
        boolean res;

        final Text text = getTextControl();
        if (text == null) {
            res = false;
        } else {
            try (ServerSocket socket = new ServerSocket(0, 10, InetAddress.getByName(text.getText()))) {
                res = true;
                clearErrorMessage();
            } catch (IOException e) {
                showErrorMessage(e.getMessage());
                res = false;
            }
        }

        return res;
    }

}
