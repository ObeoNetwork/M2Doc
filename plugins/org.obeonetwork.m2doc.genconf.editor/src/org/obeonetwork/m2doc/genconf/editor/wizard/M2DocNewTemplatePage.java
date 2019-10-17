/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Template file name page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocNewTemplatePage extends WizardPage {

    /**
     * The {@link WizardNewProjectCreationPage}.
     */
    private final WizardNewProjectCreationPage newProjectPage;

    /**
     * The template name {@link Text}.
     */
    private Text templateNameText;
    /**
     * The templateName.
     */
    private String templateName;

    /**
     * Constructor.
     * 
     * @param newProjectPage
     *            the {@link WizardNewProjectCreationPage}
     */
    protected M2DocNewTemplatePage(WizardNewProjectCreationPage newProjectPage) {
        super("Select M2Doc template name.");
        this.newProjectPage = newProjectPage;
    }

    /**
     * Sets the template name.
     * 
     * @param templateName
     *            the template name
     */
    private void setTemplateName(String templateName) {
        this.templateName = templateName;
        validatePage(templateName);
    }

    @Override
    public void createControl(Composite parent) {
        final Composite templateNameComposite = new Composite(parent, SWT.NONE);
        templateNameComposite.setLayout(new GridLayout(2, false));
        templateNameComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
        final Label templateNameLabel = new Label(templateNameComposite, SWT.None);
        templateNameLabel.setText("Template name:");
        templateNameText = new Text(templateNameComposite, SWT.NONE);
        templateNameText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                setTemplateName(templateNameText.getText());
            }
        });

        templateNameText.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                setTemplateName(templateNameText.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        templateNameText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        setTemplateName(templateNameText.getText());

        setControl(templateNameComposite);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible && (templateNameText.getText() == null || templateNameText.getText().isEmpty())) {
            templateNameText.setText(newProjectPage.getProjectName() + "." + M2DocUtils.DOCX_EXTENSION_FILE);
            validatePage(templateNameText.getText());
        }
    }

    /**
     * Validates the page with the given template name.
     * 
     * @param name
     *            the template Name.
     */
    private void validatePage(String name) {
        if (name.endsWith("." + M2DocUtils.DOCX_EXTENSION_FILE)) {
            setErrorMessage(null);
            setPageComplete(true);
        } else {
            setErrorMessage("Template name must ends with \"." + M2DocUtils.DOCX_EXTENSION_FILE + "\".");
            setPageComplete(false);
        }
    }

    /**
     * Gets the template name.
     * 
     * @return the template name
     */
    public String getTemplateName() {
        return templateName;
    }

}
