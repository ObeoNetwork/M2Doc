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
package org.obeonetwork.m2doc.ide.ui.wizard;

import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.acceleo.query.validation.type.NothingType;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.util.M2DocUtils;

/**
 * Variable page.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateVariablesPage extends WizardPage {

    /**
     * Default width.
     */
    private static final int WIDTH = 300;

    /**
     * The {@link TemplateCustomProperties} to edit.
     */
    private TemplateCustomProperties properties;

    /**
     * An other {@link WizardPage} to change while validating.
     */
    private WizardPage wizardPage;

    /**
     * Constructor.
     * 
     * @param properties
     *            the
     *            {@link TemplateCustomProperties} to edit.
     */
    protected TemplateVariablesPage(TemplateCustomProperties properties) {
        super("Template variables");
        this.properties = properties;
    }

    /**
     * Sets the other {@link WizardPage} to change while validating.
     * 
     * @param wizardPage
     *            the other {@link WizardPage} to change while validating
     */
    public void setWizardPage(WizardPage wizardPage) {
        this.wizardPage = wizardPage;
    }

    @Override
    public void createControl(Composite parent) {
        setMessage("Select variable types");

        final Composite container = new Composite(parent, SWT.NULL);
        setControl(container);
        container.setLayout(new GridLayout(2, false));

        final TableViewer variablesTable = new TableViewer(container, SWT.MULTI);
        Table table = variablesTable.getTable();
        table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        variablesTable.getTable().setHeaderVisible(true);
        TableViewerColumn nameColumn = new TableViewerColumn(variablesTable, SWT.NONE);
        nameColumn.getColumn().setText("Variable name");
        nameColumn.getColumn().setWidth(WIDTH);
        nameColumn.setLabelProvider(new CellLabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Entry<String, String>) cell.getElement()).getKey());
            }
        });
        TableViewerColumn typeColumn = new TableViewerColumn(variablesTable, SWT.NONE);
        typeColumn.getColumn().setText("Variable type");
        typeColumn.getColumn().setWidth(WIDTH);
        typeColumn.setLabelProvider(new CellLabelProvider() {

            @SuppressWarnings("unchecked")
            @Override
            public void update(ViewerCell cell) {
                cell.setText(((Entry<String, String>) cell.getElement()).getValue());
            }
        });
        variablesTable.setContentProvider(new IStructuredContentProvider() {

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // nothing to do here
            }

            @Override
            public void dispose() {
                // nothing to do here
            }

            @Override
            public Object[] getElements(Object inputElement) {
                return ((TemplateCustomProperties) inputElement).getVariables().entrySet().toArray();
            }
        });
        variablesTable.setInput(properties);

        final Composite nsURIButtonComposite = new Composite(container, SWT.NONE);
        nsURIButtonComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
        nsURIButtonComposite.setLayout(new GridLayout(1, false));

        final Button editButton = new Button(nsURIButtonComposite, SWT.NONE);
        editButton.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
        editButton.setText("Edit");
        editButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                final String variableName = ((Entry<String, ?>) ((IStructuredSelection) variablesTable.getSelection())
                        .getFirstElement()).getKey();
                final M2DocTypeSelectionDialog dialog = new M2DocTypeSelectionDialog(getShell(), variableName,
                        properties);
                final int dialogResult = dialog.open();
                if (dialogResult == IDialogConstants.OK_ID) {
                    properties.getVariables().put(variableName, dialog.getSelectedType());
                    variablesTable.refresh();
                    validatePage(properties);
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });
        variablesTable.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                editButton.setEnabled(!variablesTable.getSelection().isEmpty());
            }
        });
        editButton.setEnabled(false);

        createM2DocVersion(container, properties);
        validatePage(properties);
    }

    /**
     * Creates the M2Doc version {@link Composite}.
     * 
     * @param container
     *            the container {@link Composite}
     * @param customProperties
     *            the {@link TemplateCustomProperties}
     * @return the created {@link Composite}
     */
    private Composite createM2DocVersion(Composite container, final TemplateCustomProperties customProperties) {
        final Composite res = new Composite(container, SWT.NULL);
        res.setLayout(new GridLayout(3, false));
        res.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

        final Label versionLabel = new Label(res, SWT.NONE);
        versionLabel.setText("M2Doc version: ");

        final Text versionText = new Text(res, SWT.NONE | SWT.SINGLE);
        versionText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
        versionText.addModifyListener(new ModifyListener() {

            @Override
            public void modifyText(ModifyEvent e) {
                customProperties.setM2DocVersion(versionText.getText());
                validatePage(customProperties);
            }
        });

        if (customProperties.getM2DocVersion() != null) {
            versionText.setText(customProperties.getM2DocVersion());
        } else {
            versionText.setText(M2DocUtils.VERSION);
        }
        final Button versionButton = new Button(res, SWT.NONE);
        versionButton.setText("Current version");
        versionButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                versionText.setText(M2DocUtils.VERSION);
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });

        return res;
    }

    /**
     * Validates the page with the given {@link TemplateCustomProperties}.
     * 
     * @param customProperties
     *            the {@link TemplateCustomProperties} to check
     */
    public void validatePage(TemplateCustomProperties customProperties) {
        boolean valide = true;
        final IQueryEnvironment queryEnvironment = Query.newEnvironment();
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        queryEnvironment.registerCustomClassMapping(EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
                EStringToStringMapEntryImpl.class);
        customProperties.configureQueryEnvironmentWithResult(queryEnvironment);
        final AstValidator aqlValidator = new AstValidator(new ValidationServices(queryEnvironment));
        for (Entry<String, Set<IType>> entry : customProperties.getVariableTypes(aqlValidator, queryEnvironment)
                .entrySet()) {
            boolean isValidDeclaration = false;
            String message = "";
            for (IType declaredType : entry.getValue()) {
                if (declaredType instanceof NothingType) {
                    message = ((NothingType) declaredType).getMessage();
                } else {
                    isValidDeclaration = true;
                    break;
                }
            }
            if (!isValidDeclaration) {
                setErrorMessage(message);
                valide = false;
                break;
            }
        }

        if (valide) {
            setErrorMessage(null);
            if (!M2DocUtils.VERSION.equals(customProperties.getM2DocVersion())) {
                String mismatcvhVersionMessage = "M2Doc version mismatch: template version is "
                    + customProperties.getM2DocVersion() + " and current M2Doc version is " + M2DocUtils.VERSION;
                setMessage(mismatcvhVersionMessage, IMessageProvider.WARNING);
                if (wizardPage != null) {
                    wizardPage.setMessage(mismatcvhVersionMessage, IMessageProvider.WARNING);
                }
            } else {
                setMessage("Select variable types");
                if (wizardPage != null) {
                    wizardPage.setMessage("Select services and packages");
                }
            }
        }

        setPageComplete(valide);
    }

}
