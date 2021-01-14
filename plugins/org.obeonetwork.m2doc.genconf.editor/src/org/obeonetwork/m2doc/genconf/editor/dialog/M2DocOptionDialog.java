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
package org.obeonetwork.m2doc.genconf.editor.dialog;

import java.util.Collections;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.ide.ui.wizard.SelectRegisteredTemplatePage.CollectionContentProvider;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * File selection dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocOptionDialog extends MessageDialog {

    /**
     * The {@link Generation}.
     */
    private Generation generation;

    /**
     * The {@link Option} to edit.
     */
    private Option option;

    /**
     * The option Name.
     */
    private String optionName;

    /**
     * The option value.
     */
    private String optionValue;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     * @param generation
     *            the {@link Generation}
     * @param option
     *            the {@link Option} to editr
     */
    public M2DocOptionDialog(Shell parentShell, Generation generation, Option option) {
        super(parentShell, "Set option name and value.", null, "Set option name and value.", MessageDialog.QUESTION,
                new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
        this.generation = generation;
        this.option = option;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        final Composite container = new Composite(parent, parent.getStyle());
        container.setLayout(new GridLayout(2, false));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        final ComboViewer combo = new ComboViewer(container, parent.getStyle());
        combo.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        combo.setLabelProvider(new LabelProvider());
        combo.setContentProvider(new CollectionContentProvider());
        combo.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                optionName = (String) ((IStructuredSelection) event.getSelection()).getFirstElement();
            }
        });
        final List<String> possibleOptions = getPossibleOptions(generation, option);

        combo.setInput(possibleOptions);
        combo.setSelection(new StructuredSelection(option.getName()));

        final Text text = new Text(container, SWT.BORDER);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        text.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                optionValue = text.getText();
            }
        });
        if (option.getValue() != null) {
            text.setText(option.getValue());
        }

        return container;
    }

    /**
     * Gets the sorted {@link List} of possible {@link Option} {@link Option#getName() name}.
     * 
     * @param gen
     *            the {@link Generation}
     * @param opt
     *            the edited {@link Option}
     * @return the sorted {@link List} of possible {@link Option} {@link Option#getName() name}
     */
    private List<String> getPossibleOptions(Generation gen, Option opt) {
        final List<String> possibleOptions = M2DocUtils.getPossibleOptionNames();

        for (Option o : gen.getOptions()) {
            if (o != opt) {
                possibleOptions.remove(o.getName());
            }
        }
        Collections.sort(possibleOptions);
        return possibleOptions;
    }

    /**
     * Gets the option name.
     * 
     * @return the option name
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * Gets the option value.
     * 
     * @return the option value
     */
    public String getOptionValue() {
        return optionValue;
    }

}
