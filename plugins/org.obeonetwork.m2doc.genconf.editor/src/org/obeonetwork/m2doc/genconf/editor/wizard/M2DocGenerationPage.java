/*******************************************************************************
 *  Copyright (c) 2019, 2023 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.wizard;

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.ide.ui.dialog.M2DocFileSelectionDialog;
import org.obeonetwork.m2doc.ide.ui.wizard.M2DocMainVariablePage;
import org.obeonetwork.m2doc.ide.ui.wizard.M2DocNewTemplatePage;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Template file name page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocGenerationPage extends WizardPage {

    /**
     * The /resource prefix lenght.
     */
    private static final int PREFIX_LENGTH = "/resource".length();

    /**
     * A dot.
     */
    private static final String DOT = ".";

    /**
     * The "Browse" constant.
     */
    private static final String BROWSE = "Browse";

    /**
     * The generation file name.
     */
    private String generationName;

    /**
     * The destination file name.
     */
    private String destinationName;

    /**
     * The validation file name.
     */
    private String validationName;

    /**
     * Tells if the generation should be launched.
     */
    private boolean launchGeneration;

    /**
     * The {@link M2DocNewTemplatePage}.
     */
    private M2DocNewTemplatePage newTemplatePage;

    /**
     * The {@link M2DocMainVariablePage}.
     */
    private final M2DocMainVariablePage mainVariablePage;

    /**
     * The generation name {@link Text}.
     */
    private Text generationNameText;

    /**
     * The destination name {@link Text}.
     */
    private Text destinationNameText;

    /**
     * The validation name {@link Text}.
     */
    private Text validationNameText;

    /**
     * Constructor.
     * 
     * @param newTemplatePage
     *            the {@link M2DocNewTemplatePage}
     * @param mainVariablePage
     *            the {@link M2DocMainVariablePage}
     */
    protected M2DocGenerationPage(M2DocNewTemplatePage newTemplatePage, M2DocMainVariablePage mainVariablePage) {
        super("Configure generation.");
        this.newTemplatePage = newTemplatePage;
        this.mainVariablePage = mainVariablePage;
    }

    @Override
    public void createControl(Composite parent) {
        final Composite pageComposite = new Composite(parent, parent.getStyle());
        pageComposite.setLayout(new GridLayout(1, false));

        generationNameText = createGenerationNameComposite(pageComposite);
        destinationNameText = createDestinationNameComposite(pageComposite);
        validationNameText = createValidationNameComposite(pageComposite);
        createLaunchGenerationComposite(pageComposite);

        validatePage();

        setControl(pageComposite);
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible) {
            final URI modelURI = URI
                    .createURI(URI.decode(mainVariablePage.getVariableValue().eResource().getURI().toString()));
            final String templateName = new Path(newTemplatePage.getTemplateName()).removeFileExtension().lastSegment();
            if (generationName == null) {
                generationName = URI.createURI(templateName + DOT + GenconfUtils.GENCONF_EXTENSION_FILE)
                        .resolve(modelURI).path().substring(PREFIX_LENGTH);
                generationNameText.setText(generationName);
            }
            if (destinationName == null) {
                destinationName = URI.createURI(templateName + "-result." + M2DocUtils.DOCX_EXTENSION_FILE)
                        .resolve(modelURI).path().substring(PREFIX_LENGTH);
                destinationNameText.setText(destinationName);
            }
            if (validationName == null) {
                validationName = URI.createURI(templateName + "-validation." + M2DocUtils.DOCX_EXTENSION_FILE)
                        .resolve(modelURI).path().substring(PREFIX_LENGTH);
                validationNameText.setText(validationName);
            }
            validatePage();
        }
    }

    /**
     * Creates the launch generation name composite.
     * 
     * @param composite
     *            the container {@link Composite}
     * @return the created {@link Text}
     */
    private Button createLaunchGenerationComposite(Composite composite) {
        final Composite launchComposite = new Composite(composite, composite.getStyle());
        launchComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        launchComposite.setLayout(new GridLayout(1, false));
        final Button launchButton = new Button(launchComposite, SWT.CHECK);
        launchButton.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        launchButton.setText("launch generation on finish");
        launchButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                launchGeneration = !launchGeneration;
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });

        return launchButton;
    }

    /**
     * Creates the generation name composite.
     * 
     * @param composite
     *            the container {@link Composite}
     * @return the created {@link Text}
     */
    private Text createGenerationNameComposite(Composite composite) {
        final Composite generationNameComposite = new Composite(composite, composite.getStyle());
        generationNameComposite.setLayout(new GridLayout(3, false));
        generationNameComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label generationNameLabel = new Label(generationNameComposite, composite.getStyle());
        generationNameLabel.setText("Generation file:");
        final Text res = new Text(generationNameComposite, composite.getStyle());
        res.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        res.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                generationName = res.getText();
                validatePage();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        Button uriButton = new Button(generationNameComposite, SWT.BORDER);
        uriButton.setText(BROWSE);
        uriButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(),
                        "Select generation file.", generationName, GenconfUtils.GENCONF_EXTENSION_FILE, false);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    generationName = dialog.getFileName();
                    validatePage();
                }
            }
        });

        return res;
    }

    /**
     * Creates the destination name composite.
     * 
     * @param composite
     *            the container {@link Composite}
     * @return the created {@link Text}
     */
    private Text createDestinationNameComposite(Composite composite) {
        final Composite destinationNameComposite = new Composite(composite, composite.getStyle());
        destinationNameComposite.setLayout(new GridLayout(3, false));
        destinationNameComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label destinationNameLabel = new Label(destinationNameComposite, composite.getStyle());
        destinationNameLabel.setText("Result file:");
        final Text res = new Text(destinationNameComposite, composite.getStyle());
        res.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        res.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                destinationName = res.getText();
                validatePage();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        Button uriButton = new Button(destinationNameComposite, SWT.BORDER);
        uriButton.setText(BROWSE);
        uriButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(), "Select result file.",
                        destinationName, M2DocUtils.DOCX_EXTENSION_FILE, false);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    destinationName = dialog.getFileName();
                    validatePage();
                }
            }
        });

        return res;
    }

    /**
     * Creates the validation name composite.
     * 
     * @param composite
     *            the container {@link Composite}
     * @return the created {@link Text}
     */
    private Text createValidationNameComposite(Composite composite) {
        final Composite validationNameComposite = new Composite(composite, composite.getStyle());
        validationNameComposite.setLayout(new GridLayout(3, false));
        validationNameComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        final Label validationNameLabel = new Label(validationNameComposite, composite.getStyle());
        validationNameLabel.setText("Validation file:");
        final Text res = new Text(validationNameComposite, composite.getStyle());
        res.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        res.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                validationName = res.getText();
                validatePage();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
        Button uriButton = new Button(validationNameComposite, SWT.BORDER);
        uriButton.setText(BROWSE);
        uriButton.addListener(SWT.Selection, new Listener() {

            @Override
            public void handleEvent(Event event) {
                final M2DocFileSelectionDialog dialog = new M2DocFileSelectionDialog(getShell(),
                        "Select validation file.", validationName, M2DocUtils.DOCX_EXTENSION_FILE, false);
                final int dialogResult = dialog.open();
                if ((dialogResult == IDialogConstants.OK_ID) && !dialog.getFileName().isEmpty()) {
                    validationName = dialog.getFileName();
                    validatePage();
                }
            }
        });

        return res;
    }

    /**
     * Validates the page with the given template name.
     */
    private void validatePage() {
        if (generationName == null) {
            setErrorMessage("The generation configuration file must be defined.");
            setPageComplete(false);
        } else if (!generationName.endsWith(DOT + GenconfUtils.GENCONF_EXTENSION_FILE)) {
            setErrorMessage(String.format("The generation configuration file must ends with \".%s\".",
                    GenconfUtils.GENCONF_EXTENSION_FILE));
            setPageComplete(false);
        } else {
            if (destinationName == null) {
                setErrorMessage("The result file must be defined.");
                setPageComplete(false);
            } else if (!destinationName.endsWith(DOT + M2DocUtils.DOCX_EXTENSION_FILE)) {
                setErrorMessage(
                        String.format("The result file must ends with \".%s\".", M2DocUtils.DOCX_EXTENSION_FILE));
                setPageComplete(false);
            } else {
                if (validationName == null) {
                    setErrorMessage("The result file must be defined.");
                    setPageComplete(false);
                } else if (!validationName.endsWith(DOT + M2DocUtils.DOCX_EXTENSION_FILE)) {
                    setErrorMessage(String.format("The validation file must ends with \".%s\".",
                            M2DocUtils.DOCX_EXTENSION_FILE));
                    setPageComplete(false);
                } else {
                    setErrorMessage(null);
                    setPageComplete(true);
                }
            }
        }
    }

    /**
     * Gets the generation file name.
     * 
     * @return the generation file name
     */
    public String getGenerationName() {
        return generationName;
    }

    /**
     * Gets the destination file name.
     * 
     * @return the destination file name
     */
    public String getDestinationName() {
        return destinationName;
    }

    /**
     * Gets the validation file name.
     * 
     * @return the validation file name
     */
    public String getValidationName() {
        return validationName;
    }

    /**
     * Tells if the generation should be launched.
     * 
     * @return <code>true</code> if the generation should be launched, <code>false</code> otherwise
     */
    public boolean getLaunchGeneration() {
        return launchGeneration;
    }

}
