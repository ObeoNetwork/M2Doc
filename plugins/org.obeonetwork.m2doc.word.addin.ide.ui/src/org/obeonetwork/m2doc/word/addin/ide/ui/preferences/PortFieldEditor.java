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

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.Preferences;

/**
 * A field editor for port.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class PortFieldEditor extends IntegerFieldEditor {

    /**
     * The max port.
     */
    private static final int MAX_PORT = 65535;

    /**
     * The min port.
     */
    private static final int MIN_PORT = 1024;

    /**
     * Constructor.
     * 
     * @param name
     *            the field name
     * @param labelText
     *            the label
     * @param parent
     *            hte parent {@link Composite}
     */
    public PortFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText, parent, 6);
        setValidRange(MIN_PORT, MAX_PORT);
    }

    @Override
    protected boolean checkState() {
        boolean res;

        if (super.checkState()) {
            final Preferences preferences = InstanceScope.INSTANCE.getNode(AddInPreferenceInitializer.SCOPE);
            final String started = preferences.get(AddInPreferenceInitializer.STARTED_PREFERENCE,
                    String.valueOf(false));
            final Text text = getTextControl();
            if (Boolean.valueOf(started)) {
                res = true;
                clearErrorMessage();
            } else if (text == null) {
                res = false;
            } else {
                try (ServerSocket socket = new ServerSocket(getIntValue(), 10, InetAddress.getByName("0.0.0.0"))) {
                    res = true;
                    clearErrorMessage();
                } catch (IOException e) {
                    res = false;
                    showErrorMessage(e.getMessage());
                }
            }
        } else {
            res = false; // messages handled by super.checkState()
        }

        return res;
    }

}
