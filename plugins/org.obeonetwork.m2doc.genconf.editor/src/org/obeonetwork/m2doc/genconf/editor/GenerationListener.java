/*******************************************************************************
 *  Copyright (c) 2018 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;

/**
 * Update GUI elements according to {@link Generation} changes.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerationListener extends AdapterImpl {

    /**
     * The {@link Generation#getTemplateFileName() template URI} {@link Text}.
     */
    private Text templateURIText;

    /**
     * The {@link Generation#getResultFileName() destination URI} {@link Text}.
     */
    private Text destinationURIText;

    /**
     * The {@link Generation#getValidationFileName() validation URI} {@link Text}.
     */
    private Text validationURIText;

    /**
     * The {@link Generation#getDefinitions() variables} {@link Viewer}.
     */
    private Viewer variablesViewer;

    /**
     * The {@link Generation#getOptions() options} {@link Viewer}.
     */
    private Viewer optionsViewer;

    @SuppressWarnings("unchecked")
    @Override
    public void notifyChanged(Notification msg) {
        super.notifyChanged(msg);
        if (msg.getNotifier() instanceof Generation) {
            switch (msg.getFeatureID(Generation.class)) {
                case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
                    final String newTemplateURI = (String) msg.getNewValue();
                    if (templateURIText != null && !newTemplateURI.equals(templateURIText.getText())) {
                        templateURIText.setText(newTemplateURI);
                    }
                    break;
                case GenconfPackage.GENERATION__RESULT_FILE_NAME:
                    final String newDestionationURI = (String) msg.getNewValue();
                    if (destinationURIText != null && !newDestionationURI.equals(destinationURIText.getText())) {
                        destinationURIText.setText(newDestionationURI);
                    }
                    break;
                case GenconfPackage.GENERATION__VALIDATION_FILE_NAME:
                    final String newValidationURI = (String) msg.getNewValue();
                    if (validationURIText != null && !newValidationURI.equals(validationURIText.getText())) {
                        validationURIText.setText(newValidationURI);
                    }
                    break;
                case GenconfPackage.GENERATION__DEFINITIONS:
                    switch (msg.getEventType()) {
                        case Notification.ADD:
                            final Definition newDefinition = (Definition) msg.getNewValue();
                            newDefinition.eAdapters().add(this);
                            break;
                        case Notification.ADD_MANY:
                            for (Definition definition : (List<Definition>) msg.getNewValue()) {
                                definition.eAdapters().add(this);
                            }
                            break;
                        case Notification.REMOVE:
                            final Definition oldDefinition = (Definition) msg.getOldValue();
                            oldDefinition.eAdapters().remove(this);
                            break;
                        case Notification.REMOVE_MANY:
                            for (Definition definition : (List<Definition>) msg.getOldValue()) {
                                definition.eAdapters().remove(this);
                            }
                            break;
                        default:
                            break;
                    }
                    if (variablesViewer != null && !variablesViewer.getControl().isDisposed()) {
                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (!variablesViewer.getControl().isDisposed()) {
                                    variablesViewer.refresh();
                                }
                            }
                        });
                    }
                    break;
                case GenconfPackage.GENERATION__OPTIONS:
                    switch (msg.getEventType()) {
                        case Notification.ADD:
                            final Option newOption = (Option) msg.getNewValue();
                            newOption.eAdapters().add(this);
                            break;
                        case Notification.ADD_MANY:
                            for (Option option : (List<Option>) msg.getNewValue()) {
                                option.eAdapters().add(this);
                            }
                            break;
                        case Notification.REMOVE:
                            final Option oldoption = (Option) msg.getOldValue();
                            oldoption.eAdapters().remove(this);
                            break;
                        case Notification.REMOVE_MANY:
                            for (Option option : (List<Option>) msg.getOldValue()) {
                                option.eAdapters().remove(this);
                            }
                            break;
                        default:
                            break;
                    }
                    if (optionsViewer != null && !optionsViewer.getControl().isDisposed()) {
                        Display.getDefault().asyncExec(new Runnable() {

                            @Override
                            public void run() {
                                if (!optionsViewer.getControl().isDisposed()) {
                                    optionsViewer.refresh();
                                }
                            }
                        });
                    }
                    break;
                default:
                    break;
            }
        } else if (msg.getNotifier() instanceof Definition) {
            if (variablesViewer != null && !variablesViewer.getControl().isDisposed()) {
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!variablesViewer.getControl().isDisposed()) {
                            variablesViewer.refresh();
                        }
                    }
                });
            }
        } else if (msg.getNotifier() instanceof Option) {
            if (optionsViewer != null && !optionsViewer.getControl().isDisposed()) {
                Display.getDefault().asyncExec(new Runnable() {

                    @Override
                    public void run() {
                        if (!optionsViewer.getControl().isDisposed()) {
                            optionsViewer.refresh();
                        }
                    }
                });
            }
        }
    }

    /**
     * Installs this listener.
     * 
     * @param generation
     *            the {@link Generation}
     */
    public void installGenerationListener(Generation generation) {
        generation.eAdapters().add(this);
        for (Definition definition : generation.getDefinitions()) {
            definition.eAdapters().add(this);
        }
        for (Option option : generation.getOptions()) {
            option.eAdapters().add(this);
        }
    }

    /**
     * Removes this listener.
     * 
     * @param generation
     *            the {@link Generation}
     */
    public void removeGenerationListener(Generation generation) {
        generation.eAdapters().remove(this);
        for (Definition definition : generation.getDefinitions()) {
            definition.eAdapters().remove(this);
        }
        for (Option option : generation.getOptions()) {
            option.eAdapters().remove(this);
        }
    }

    /**
     * Sets the {@link Generation#getTemplateFileName() template URI} {@link Text}.
     * 
     * @param templateURIText
     *            the new {@link Generation#getTemplateFileName() template URI} {@link Text}
     */
    public void setTemplateURIText(Text templateURIText) {
        this.templateURIText = templateURIText;
    }

    /**
     * Sets the {@link Generation#getValidationFileName() validation URI} {@link Text}.
     * 
     * @param validationURIText
     *            the new {@link Generation#getValidationFileName() validation URI} {@link Text}
     */
    public void setValidationURIText(Text validationURIText) {
        this.validationURIText = validationURIText;
    }

    /**
     * Sets the {@link Generation#getResultFileName() result URI} {@link Text}.
     * 
     * @param destinationURIText
     *            the new {@link Generation#getResultFileName() result URI} {@link Text}
     */
    public void setDestinationURIText(Text destinationURIText) {
        this.destinationURIText = destinationURIText;
    }

    /**
     * Sets the {@link Generation#getDefinitions() variables} {@link Viewer}.
     * 
     * @param variablesViewer
     *            the new {@link Generation#getDefinitions() variables} {@link Viewer}
     */
    public void setVariablesViewer(Viewer variablesViewer) {
        this.variablesViewer = variablesViewer;
    }

    /**
     * Sets the {@link Generation#getOptions() options} {@link Viewer}.
     * 
     * @param optionsViewer
     *            the new {@link Generation#getOptions() options} {@link Viewer}
     */
    public void setOptionsViewer(Viewer optionsViewer) {
        this.optionsViewer = optionsViewer;
    }

}
