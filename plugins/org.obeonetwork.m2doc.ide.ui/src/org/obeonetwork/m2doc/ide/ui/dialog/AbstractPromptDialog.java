/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.ide.ui.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The M2Doc prompt dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractPromptDialog extends Dialog {

    /**
     * The initial x size of the dialog.
     */
    private static final int X = 200;

    /**
     * The initial y size of the dialog.
     */
    private static final int Y = 450;

    /**
     * The dialog message.
     */
    private String message;

    /**
     * The value.
     */
    private String value;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     * @param message
     *            the dialog message
     */
    protected AbstractPromptDialog(Shell parentShell, String message) {
        super(parentShell);
        this.message = message;
    }

    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("M2Doc");
    }

    @Override
    protected Point getInitialSize() {
        return new Point(Y, X);
    }

    @Override
    protected Control createDialogArea(Composite parent) {
        final Composite container = (Composite) super.createDialogArea(parent);

        final Label label = new Label(container, SWT.NONE);
        label.setText(message);
        final Text text = new Text(container, SWT.NONE);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                if (validate(text.getText())) {
                    value = text.getText();
                    getButton(OK).setEnabled(true);
                } else {
                    value = null;
                    getButton(OK).setEnabled(false);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });

        return container;
    }

    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Tells if the given input is valid.
     * 
     * @param input
     *            the input {@link String}
     * @return <code>true</code> if the given input is valid, <code>false</code> otherwise
     */
    public abstract boolean validate(String input);

}
