/*******************************************************************************
 *  Copyright (c) 2024 Obeo. 
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

import java.awt.Container;
import java.io.File;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Generates from template library wizard.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class GenerationWithTemplateLibraryWizard extends Wizard {

    /**
     * The browse label.
     */
    private static final String BROWSE_LABEL = "...";

    /**
     * The {@link List} of template {@link File}.
     */
    private final List<File> templateList;

    /**
     * The template {@link File}.
     */
    private File selectedTemplate;

    /**
     * The output {@link File}.
     */
    private File outputFile;

    /**
     * Constructor.
     * 
     * @param templateList
     *            the {@link List} of template {@link File}
     */
    public GenerationWithTemplateLibraryWizard(List<File> templateList) {
        this.templateList = templateList;
    }

    @Override
    public boolean performFinish() {
        if (outputFile == null) {
            MessageDialog.openError(getShell(), "Error", "Please define an Output File");
            return false;
        }
        return true;
    }

    @Override
    public void createPageControls(Composite pageContainer) {

        final ScrolledComposite sc = new ScrolledComposite(pageContainer, SWT.V_SCROLL);
        sc.setLayoutData(new GridData(GridData.FILL_VERTICAL));
        sc.setExpandVertical(true);
        sc.setExpandHorizontal(true);

        Composite container = new Composite(sc, SWT.NONE);
        container.setLayout(new GridLayout(3, false));

        sc.setContent(container);
        sc.addListener(SWT.Resize, event -> {
            // Refresh client area and recompute vertical bar
            int width = sc.getClientArea().width;
            sc.setMinSize(container.computeSize(width, SWT.DEFAULT));
        });

        createTemplateSelector(container);
        createOutputFileSelector(container);

    }

    /**
     * Creates the template selector.
     * 
     * @param container
     *            the conatiner {@link Composite}
     */
    private void createTemplateSelector(Composite container) {
        createField(container, "List of templates:", //
                () -> {
                    ComboViewer field = new ComboViewer(container, SWT.NONE);
                    field.setContentProvider(new ArrayContentProvider());
                    field.setLabelProvider(new LabelProvider() {
                        @Override
                        public String getText(Object element) {
                            return ((File) element).getName();
                        }
                    });
                    field.addSelectionChangedListener(event -> {
                        selectedTemplate = getSelecteElement(field.getSelection());
                    });
                    field.setInput(templateList);

                    // Set previous template
                    selectedTemplate = getDefaultTemplate();
                    field.setSelection(new StructuredSelection(selectedTemplate), true);

                    return field.getCombo();
                }, null);

        createField(container, "", // no title, only hint.
                () -> {
                    Label field = new Label(container, SWT.NONE);
                    // TODO: update message with the name of the folder?
                    field.setText("Templates loaded from Application");
                    return field;
                }, null);
    }

    /**
     * Creates the output file selector.
     * 
     * @param container
     *            the conatiner {@link Composite}
     */
    private void createOutputFileSelector(Composite container) {
        createField(container, "Output File:", //
                () -> {
                    Text field = new Text(container, SWT.BORDER);
                    field.setEditable(false);

                    // Set previous output or pref
                    updateOutput(getDefaultOutputFile(), field);

                    return field;
                }, //
                field -> {
                    Button browse = new Button(container, SWT.NONE);
                    browse.setText(BROWSE_LABEL);
                    browse.addSelectionListener(SelectionListener.widgetSelectedAdapter(e -> {
                        FileDialog dialog = new FileDialog(getShell());
                        dialog.setText("Select output file");
                        dialog.setFilterExtensions(new String[] {"*.docx"});

                        File initial = outputFile;
                        while (initial != null && !initial.exists()) {
                            initial = initial.getParentFile();
                        }
                        if (initial != null) {
                            // Eclipse choice of name for initial selection
                            dialog.setFilterPath(outputFile.getPath());
                        }
                        String selectedDirectory = dialog.open();
                        if (selectedDirectory != null) {
                            updateOutput(selectedDirectory, field);
                        }

                    }));
                    return browse;
                });
    }

    /**
     * Gets the default output file name.
     * 
     * @return the default output file name
     */
    private String getDefaultOutputFile() {
        // TODO: define a default output file
        return null;
    }

    /**
     * Updates the output file.
     * 
     * @param path
     *            the new output path
     * @param field
     *            the {@link Text} to update
     */
    private void updateOutput(String path, Text field) {
        String localPath = path;
        if (localPath != null) {
            localPath = localPath.trim();
            if (localPath.isEmpty()) {
                localPath = null;
            }
        }

        if (localPath != null) {
            if (!localPath.endsWith(".docx")) {
                localPath = localPath + ".docx";
            }
            outputFile = new File(localPath).getAbsoluteFile();
            field.setText(outputFile.toString());
        } else {
            outputFile = null;
            field.setText("");
        }
    }

    /**
     * Gets the default template {@link File}.
     * 
     * @return the default template {@link File}
     */
    private File getDefaultTemplate() {
        // TODO: improve the default template selection?
        return templateList.get(0);
    }

    /**
     * Gets the selected element from the given {@link ISelection}.
     * 
     * @param <T>
     *            the expected kind of element
     * @param selection
     *            the {@link ISelection}
     * @return the selected element from the given {@link ISelection} if any, <code>null</code> otherwise
     */
    @SuppressWarnings("unchecked")
    private static <T> T getSelecteElement(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection strucSelect = (IStructuredSelection) selection;
            return (T) strucSelect.getFirstElement();
        }
        return null;
    }

    /**
     * Creates a field in the given {@link Container}.
     * 
     * @param <C>
     *            the kind of {@link Control}
     * @param container
     *            the {@link Container}
     * @param labelText
     *            the label
     * @param controller
     *            the {@link Control} supplier
     * @param picker
     *            the {@link Control} picker
     * @return the created field in the given {@link Container}
     */
    private static <C extends Control> C createField(Composite container, String labelText, Supplier<C> controller,
            Function<? super C, Control> picker) {
        Label label = new Label(container, SWT.NONE);
        label.setText(labelText);
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

        C field = controller.get();
        field.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, picker != null ? 1 : 2, 1));

        if (picker != null) {
            Control right = picker.apply(field);
            right.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        }
        return field;
    }

    /**
     * Gets the output {@link File}.
     * 
     * @return the output {@link File}
     */
    public File getOutputFile() {
        return outputFile;
    }

    /**
     * Gets the selected template {@link File}.
     * 
     * @return the selected template {@link File}
     */
    public File getSelectedTemplate() {
        return selectedTemplate;
    }

}
