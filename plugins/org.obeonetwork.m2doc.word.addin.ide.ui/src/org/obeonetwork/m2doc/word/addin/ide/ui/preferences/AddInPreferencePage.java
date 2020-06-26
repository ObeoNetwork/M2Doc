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
package org.obeonetwork.m2doc.word.addin.ide.ui.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;
import org.obeonetwork.m2doc.word.addin.ide.ui.M2DocAddInPlugin;
import org.osgi.service.prefs.Preferences;

/**
 * The add-in preference page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AddInPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    /**
     * The link to add-in manifest.
     */
    private Link manifestLink;

    /**
     * Constructor.
     */
    public AddInPreferencePage() {
        super(GRID);
    }

    @Override
    public void init(IWorkbench workbench) {
        setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, AddInPreferenceInitializer.SCOPE));
        setDescription("Set network preferences for the M2Doc MS Word add-in.");
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        addField(new HostFieldEditor(AddInPreferenceInitializer.HOST_PREFERENCE, "Host:", -1, getFieldEditorParent()));
        addField(new PortFieldEditor(AddInPreferenceInitializer.PORT_PREFERENCE, "Port:", getFieldEditorParent()));
        final Composite parent = getFieldEditorParent();
        final Button button = new Button(parent, SWT.TOGGLE | parent.getStyle());
        if (!M2DocAddInPlugin.getPlugin().isServerStarted()) {
            button.setSelection(false);
            button.setText("Start");
        } else {
            button.setSelection(true);
            button.setText("Stop");
        }
        button.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(org.eclipse.swt.widgets.Event event) {
                if (M2DocAddInPlugin.getPlugin().isServerStarted()) {
                    M2DocAddInPlugin.getPlugin().stopServer();
                    getPreferenceStore().putValue(AddInPreferenceInitializer.STARTED_PREFERENCE, String.valueOf(false));
                    button.setText("Start");
                    manifestLink.setVisible(false);
                } else {
                    startServer();
                    getPreferenceStore().putValue(AddInPreferenceInitializer.STARTED_PREFERENCE, String.valueOf(true));
                    button.setText("Stop");
                    manifestLink.setVisible(true);
                }
            }
        });
        manifestLink = new Link(parent, parent.getStyle());
        manifestLink.addListener(SWT.Selection, new Listener() {
            @Override
            public void handleEvent(org.eclipse.swt.widgets.Event event) {
                final Clipboard clipboard = new Clipboard(getShell().getDisplay());
                TextTransfer textTransfer = TextTransfer.getInstance();
                Transfer[] transfers = new Transfer[] {textTransfer };
                Object[] data = new Object[] {getManifestURL() };
                clipboard.setContents(data, transfers);
                clipboard.dispose();
            }
        });
        manifestLink.setVisible(M2DocAddInPlugin.getPlugin().isServerStarted());
        manifestLink.setText("<a href=\"" + getManifestURL() + "\">Copy manifest link</a>");
    }

    /**
     * Gets the manifest URL.
     * 
     * @return the manifest URL
     */
    private String getManifestURL() {
        final String res;

        final Preferences preferences = InstanceScope.INSTANCE.getNode(AddInPreferenceInitializer.SCOPE);
        final String host = preferences.get(AddInPreferenceInitializer.HOST_PREFERENCE,
                AddInPreferenceInitializer.DEFAULT_HOST);
        final String port = preferences.get(AddInPreferenceInitializer.PORT_PREFERENCE,
                String.valueOf(AddInPreferenceInitializer.DEFAULT_PORT));
        if (AddInPreferenceInitializer.DEFAULT_HOST.equals(host)) {
            res = "http://localhost:" + port + "/assets/manifest.xml";
        } else {
            res = "http://" + host + ":" + port + "/assets/manifest.xml";
        }

        return res;
    }

    @Override
    protected void performApply() {
        super.performApply();
        if (M2DocAddInPlugin.getPlugin().isServerStarted()) {
            M2DocAddInPlugin.getPlugin().stopServer();
            startServer();
        }
        manifestLink.setText("<a href=" + getManifestURL() + ">Copy manifest link</a>");
    }

    /**
     * Starts the server.
     */
    private void startServer() {
        final Preferences preferences = InstanceScope.INSTANCE.getNode(AddInPreferenceInitializer.SCOPE);
        final String host = preferences.get(AddInPreferenceInitializer.HOST_PREFERENCE,
                AddInPreferenceInitializer.DEFAULT_HOST);
        final String port = preferences.get(AddInPreferenceInitializer.PORT_PREFERENCE,
                String.valueOf(AddInPreferenceInitializer.DEFAULT_PORT));
        M2DocAddInPlugin.getPlugin().startServer(host, Integer.valueOf(port));
    }

}
