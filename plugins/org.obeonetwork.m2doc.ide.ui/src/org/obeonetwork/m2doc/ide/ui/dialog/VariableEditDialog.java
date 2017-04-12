/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
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

import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.presentation.EcoreActionBarContributor.ExtendedLoadResourceAction.RegisteredPackageDialog;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;
import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.ScalarType;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.tplconf.TemplateConfigUtil;
import org.obeonetwork.m2doc.tplconf.TemplateType;
import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfFactory;
import org.obeonetwork.m2doc.tplconf.impl.TplconfFactoryImpl;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Dialog to configure one template variable.
 * 
 * @author ldelaigue
 */
public class VariableEditDialog extends TitleAreaDialog {
    /**
     * @author cedric
     */
    private final class ClassifierTreeSelectionListener implements ISelectionChangedListener {
        @Override
        public void selectionChanged(SelectionChangedEvent event) {
            Object firstElement = ((IStructuredSelection) event.getSelection()).getFirstElement();
            final TemplateType type;
            if (firstElement instanceof EClassifier) {
                EClassifier eClassifier = (EClassifier) firstElement;
                final String typeName = TemplateConfigUtil.typeName(eClassifier);
                if (config.getTypesByName().containsKey(typeName)) {
                    type = config.getTypesByName().get(typeName);
                } else {
                    type = TplconfFactoryImpl.eINSTANCE.createStructuredType();
                    final StructuredType newType = (StructuredType) type;
                    newType.setName(eClassifier.getName());
                    newType.setEClassifier(eClassifier);
                    newType.setMappingName(eClassifier.getEPackage().getName());
                    newType.setMapping(mappingsByUri.get(eClassifier.getEPackage().getName()));
                }
            } else if (firstElement instanceof ScalarType) {
                type = (ScalarType) firstElement;
            } else {
                type = null;
            }
            variable.setType(type);
            if (type != null) {
                setTypeName(type);
            }
            validate();
        }
    }

    /**
     * @author cedric
     */
    private final class TypeNameModifyListener implements ModifyListener {
        @Override
        public void modifyText(ModifyEvent e) {
            final String typeName = txtTypeName.getText();
            variable.setTypeName(typeName);
            if (typeName == null || typeName.length() == 0) {
                mmComboViewer.setSelection(new StructuredSelection());
                classifierTreeViewer.setSelection(new StructuredSelection());
            } else if (typeName.indexOf(TemplateConfigUtil.METAMODEL_TYPE_SEPARATOR) > 0) {
                int index = typeName.indexOf(TemplateConfigUtil.METAMODEL_TYPE_SEPARATOR);
                String mmName = typeName.substring(0, index);
                EPackageMapping mapping = findMappingByName(mmName);
                if (mapping != null) {
                    mmComboViewer.setSelection(new StructuredSelection(mapping), true);
                }
                if (config.getTypesByName().containsKey(typeName)) {
                    selectGivenType(typeName);
                } else {
                    String classifierName = typeName.substring(index + 2);
                    filteredTree.getFilterControl().setText(classifierName);
                    if (mmName != null && mapping != null) {
                        selectClassifierIfFound(mmName, mapping, classifierName);
                    } else {
                        classifierTreeViewer.setSelection(new StructuredSelection(), true);
                    }
                }
            } else {
                // scalar type?
                mmComboViewer.setSelection(new StructuredSelection(config));
                TemplateType type = config.getTypesByName().get(typeName);
                if (type instanceof ScalarType) {
                    classifierTreeViewer.setSelection(new StructuredSelection(type), true);
                } else {
                    classifierTreeViewer.setSelection(new StructuredSelection(), true);
                }
            }
            validate();
        }

        /**
         * @param typeName
         */
        private void selectGivenType(final String typeName) {
            TemplateType type = config.getTypesByName().get(typeName);
            if (type instanceof ScalarType) {
                classifierTreeViewer.setSelection(new StructuredSelection(type), true);
            } else if (type instanceof StructuredType) {
                if (((StructuredType) type).getEClassifier() != null) {
                    classifierTreeViewer.setSelection(
                            new StructuredSelection(((StructuredType) type).getEClassifier()), true);
                } else {
                    classifierTreeViewer.setSelection(new StructuredSelection(), true);
                }
            }
        }

        private void selectClassifierIfFound(String mmName, EPackageMapping mapping, String classifierName) {
            if (mapping.getEPackage() != null) {
                EClassifier eClassifier = ((EPackage) mapping.getEPackage()).getEClassifier(classifierName);
                if (eClassifier != null) {
                    final StructuredType mmType = TplconfFactory.eINSTANCE.createStructuredType();
                    mmType.setEClassifier(eClassifier);
                    mmType.setMapping(mapping);
                    mmType.setMappingName(mmName);
                    mmType.setName(classifierName);
                    classifierTreeViewer.setSelection(new StructuredSelection(eClassifier), true);
                } else {
                    classifierTreeViewer.setSelection(new StructuredSelection(), true);
                }
            } else {
                classifierTreeViewer.setSelection(new StructuredSelection(), true);
            }
        }
    }

    /**
     * @author cedric
     */
    private final class ClassifierTreeContentProvider implements ITreeContentProvider {
        @Override
        public boolean hasChildren(Object element) {
            return false;
        }

        @Override
        public Object getParent(Object element) {
            return null;
        }

        @Override
        public Object[] getElements(Object inputElement) {
            Object[] result = new Object[0];
            if (inputElement instanceof EPackageMapping) {
                EPackageMapping mm = (EPackageMapping) inputElement;
                if (mm.getEPackage() instanceof EPackage) {
                    EList<EClassifier> eClassifiers = ((EPackage) mm.getEPackage()).getEClassifiers();
                    result = eClassifiers.toArray(new Object[eClassifiers.size()]);
                }
            } else if (inputElement instanceof TemplateConfig) {
                Collection<TemplateType> allTypes = ((TemplateConfig) inputElement).getTypesByName().values();
                result = Collections2.filter(allTypes, Predicates.instanceOf(ScalarType.class)).toArray();
            }
            return result;
        }

        @Override
        public Object[] getChildren(Object parentElement) {
            return null;
        }

        @Override
        public void dispose() {
            // Nothing
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            // Nothing
        }
    }

    /**
     * @author cedric
     */
    private final class BtnAddMetamodelSelectionListener extends SelectionAdapter {
        @Override
        public void widgetSelected(SelectionEvent e) {
            RegisteredPackageDialog dialog = new RegisteredPackageDialog(Display.getCurrent().getActiveShell());
            dialog.setMultipleSelection(false);
            dialog.open();
            if (dialog.getReturnCode() == Dialog.OK) {
                Object firstResult = dialog.getFirstResult();
                if (firstResult instanceof String) {
                    String uri = (String) firstResult;
                    if (mappingsByUri.containsKey(uri)) {
                        // Reuse the one that's already registered
                        mmComboViewer.setSelection(new StructuredSelection(mappingsByUri.get(uri)), true);
                    } else if (EPackage.Registry.INSTANCE.getEPackage(uri) != null) {
                        final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(uri);
                        EPackageMapping newMapping = TplconfFactory.eINSTANCE.createEPackageMapping();
                        newMapping.setName(ePackage.getName());
                        newMapping.setUri(ePackage.getNsURI());
                        newMapping.setEPackage(ePackage);
                        mappingsByUri.put(uri, newMapping);
                        mmComboViewer.refresh();
                        mmComboViewer.setSelection(new StructuredSelection(newMapping), true);
                    }
                }
            }
        }
    }

    private final TemplateConfig config;
    private final TemplateVariable variable;
    private final AdapterFactory adapterFactory;
    private Text txtName;
    private ComboViewer mmComboViewer;
    private Text txtTypeName;
    private TreeViewer classifierTreeViewer;
    private FilteredTree filteredTree;
    private Map<String, EPackageMapping> mappingsByUri = new LinkedHashMap<>();

    /**
     * Create the dialog.
     * 
     * @param parentShell
     *            The parent shell
     * @param var
     *            The variable to edit, must NOT be attached to the {@link TemplateConfig}
     * @param config
     *            The target {@link TemplateConfig}
     * @param adapterFactory
     *            The {@link AdapterFactory}
     */
    public VariableEditDialog(Shell parentShell, TemplateVariable var, TemplateConfig config,
            AdapterFactory adapterFactory) {
        super(parentShell);
        setShellStyle(SWT.CLOSE | SWT.RESIZE | SWT.TITLE | SWT.APPLICATION_MODAL);
        this.variable = checkNotNull(var);
        this.config = checkNotNull(config);
        this.adapterFactory = checkNotNull(adapterFactory);
    }

    /**
     * Create contents of the dialog.
     * 
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        setTitle("Configure Variable");
        setMessage("Configure the variable name and type.");
        Composite area = (Composite) super.createDialogArea(parent);
        Composite container = new Composite(area, SWT.NONE);
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
        container.setLayout(new GridLayout(2, false));

        Label lblVariableName = new Label(container, SWT.NONE);
        lblVariableName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblVariableName.setText("TemplateVariable Name:");

        txtName = new Text(container, SWT.BORDER);
        txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        Label lblMetamodel = new Label(container, SWT.NONE);
        lblMetamodel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblMetamodel.setText("Meta-Model:");

        Composite composite = new Composite(container, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
        GridLayout glComposite = new GridLayout(2, false);
        glComposite.verticalSpacing = 0;
        glComposite.marginHeight = 0;
        glComposite.marginWidth = 0;
        glComposite.horizontalSpacing = 0;
        composite.setLayout(glComposite);

        Combo combo = new Combo(composite, SWT.NONE);
        combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
        mmComboViewer = new ComboViewer(combo);
        mmComboViewer.setContentProvider(new IStructuredContentProvider() {
            @Override
            public Object[] getElements(Object inputElement) {
                @SuppressWarnings("unchecked")
                Map<String, EPackageMapping> map = (Map<String, EPackageMapping>) inputElement;
                List<Object> result = new ArrayList<>();
                result.add(config);
                result.addAll(map.values());
                return result.toArray(new Object[result.size()]);
            }

            @Override
            public void dispose() {
                // Nothing
            }

            @Override
            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
                // Nothing
            }
        });
        mmComboViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        for (EPackageMapping mapping : config.getMappings()) {
            if (mapping.getUri() != null && mapping.getName() != null) {
                mappingsByUri.put(mapping.getUri(), mapping);
            }
        }
        mmComboViewer.setInput(mappingsByUri);

        Button btnAddMetamodel = new Button(composite, SWT.NONE);
        btnAddMetamodel.setText("...");
        btnAddMetamodel.addSelectionListener(new BtnAddMetamodelSelectionListener());

        Label lblType = new Label(container, SWT.NONE);
        lblType.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblType.setText("TemplateType:");

        PatternFilter filter = new PatternFilter();
        filteredTree = new FilteredTree(container, SWT.BORDER | SWT.SINGLE, filter, true);
        classifierTreeViewer = filteredTree.getViewer();

        Label lblTypeName = new Label(container, SWT.NONE);
        lblTypeName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
        lblTypeName.setText("TemplateType Name:");

        txtTypeName = new Text(container, SWT.BORDER);
        txtTypeName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        classifierTreeViewer.setContentProvider(new ClassifierTreeContentProvider());
        classifierTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));

        // Set initial values
        txtName.setText(variable.getName());
        if (variable.getTypeName() != null) {
            txtTypeName.setText(variable.getTypeName());
        }
        TemplateType type = variable.getType();
        if (type instanceof ScalarType) {
            mmComboViewer.setSelection(new StructuredSelection(config), true);
            classifierTreeViewer.setSelection(new StructuredSelection(variable.getType()), true);
        } else if (type instanceof StructuredType) {
            if (((StructuredType) variable.getType()).getMapping() != null) {
                mmComboViewer.setSelection(new StructuredSelection(((StructuredType) variable.getType()).getMapping()),
                        true);
            }
            if (((StructuredType) variable.getType()).getEClassifier() != null) {
                classifierTreeViewer.setSelection(
                        new StructuredSelection(((StructuredType) variable.getType()).getEClassifier()), true);
            }
        }

        hookListeners();

        return container;
    }

    protected void hookListeners() {
        txtName.addModifyListener(new ModifyListener() {
            @Override
            public void modifyText(ModifyEvent e) {
                variable.setName(txtName.getText());
                validate();
            }
        });

        txtTypeName.addModifyListener(new TypeNameModifyListener());

        classifierTreeViewer.addSelectionChangedListener(new ClassifierTreeSelectionListener());

        mmComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                if (event.getSelection().isEmpty()) {
                    classifierTreeViewer.setInput(config);
                } else {
                    Object firstElement = ((IStructuredSelection) event.getSelection()).getFirstElement();
                    if (firstElement != null) {
                        classifierTreeViewer.setInput(firstElement);
                    }
                }
                classifierTreeViewer.refresh();
                validate();
            }
        });
    }

    private EPackageMapping findMappingByName(String name) {
        if (name != null) {
            for (EPackageMapping mapping : mappingsByUri.values()) {
                if (name.equals(mapping.getName())) {
                    return mapping;
                }
            }
        }
        return null;
    }

    protected void setTypeName(final TemplateType type) {
        if (type == null) {
            if (txtTypeName.getText() != null && txtTypeName.getText().length() > 0) {
                txtTypeName.setText("");
            }
        } else if (type instanceof ScalarType) {
            if (txtTypeName.getText() == null || !txtTypeName.getText().equals(type.getName())) {
                txtTypeName.setText(type.getName());
            }
        } else if (type instanceof StructuredType) {
            String typeName = TemplateConfigUtil.typeName(((StructuredType) type).getMappingName(), type.getName());
            if (txtTypeName.getText() == null || !txtTypeName.getText().equals(typeName)) {
                txtTypeName.setText(typeName);
            }
        }
    }

    protected void validate() {
        String blockingMessage = null;
        if (variable.getTypeName() == null || variable.getTypeName().length() == 0) {
            blockingMessage = "The variable type name must be set";
        }
        if (variable.getType() == null
            && variable.getTypeName().indexOf(TemplateConfigUtil.METAMODEL_TYPE_SEPARATOR) <= 0) {
            blockingMessage = "The scalar type of the variable is not supported";
        }
        if (!TemplateConfigUtil.isValidTypeName(variable.getTypeName())) {
            blockingMessage = "The variable type is not valid";
        }
        if (!TemplateCustomProperties.isValidVariableName(variable.getName())) {
            blockingMessage = "The variable name must be valid (start with a letter or underscore, contain only letters, digits, or underscores)";
        }
        if (blockingMessage != null) {
            blockWithMessage(blockingMessage);
        } else {
            getButton(IDialogConstants.OK_ID).setEnabled(true);
            setErrorMessage(null);
        }
    }

    private void blockWithMessage(String message) {
        setErrorMessage(message);
        getButton(IDialogConstants.OK_ID).setEnabled(false);
    }

    /**
     * Create contents of the button bar.
     * 
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

}
