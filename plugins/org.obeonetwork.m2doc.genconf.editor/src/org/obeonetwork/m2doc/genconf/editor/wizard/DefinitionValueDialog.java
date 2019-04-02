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

import java.util.Iterator;
import java.util.Set;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * File selection dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class DefinitionValueDialog extends MessageDialog {

    /**
     * The table minimum height.
     */
    private static final int TABLE_MINIMUM_HEIGHT = 400;

    /**
     * The table minimum width.
     */
    private static final int TABLE_MINIMUM_WIDTH = 200;

    /**
     * The selected value.
     */
    private Object value;

    /**
     * The {@link AdapterFactory}.
     */
    private AdapterFactory adapterFactory;

    /**
     * The {@link Definition} to edit.
     */
    private final Definition definition;

    /**
     * The {@link ResourceSet} for model.
     */
    private final ResourceSet resourceSet;

    /**
     * The {@link IReadOnlyQueryEnvironment}.
     */
    private IReadOnlyQueryEnvironment queryEnvironment;

    /**
     * The {@link TemplateCustomProperties}.
     */
    private TemplateCustomProperties properties;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent {@link Shell}
     * @param adapterFactory
     *            the {@link AdapterFactory}
     * @param definition
     *            the {@link Definition} to edit
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param properties
     *            the {@link TemplateCustomProperties}
     * @param resourceSet
     *            the {@link ResourceSet} for model
     */
    public DefinitionValueDialog(Shell parentShell, AdapterFactory adapterFactory, Definition definition,
            IReadOnlyQueryEnvironment queryEnvironment, TemplateCustomProperties properties, ResourceSet resourceSet) {
        super(parentShell, "Select value for " + definition.getKey(), null,
                "Select a value of type " + properties.getVariables().get(definition.getKey()), MessageDialog.QUESTION,
                new String[] {IDialogConstants.OK_LABEL, IDialogConstants.CANCEL_LABEL}, 0);
        this.definition = definition;
        this.resourceSet = resourceSet;
        this.adapterFactory = adapterFactory;
        this.queryEnvironment = queryEnvironment;
        this.properties = properties;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        final Composite container = new Composite(parent, SWT.NULL);
        container.setLayout(new GridLayout(1, false));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        if (definition instanceof BooleanDefinition) {
            createBooleanCustomArea((BooleanDefinition) definition, container);
        } else if (definition instanceof IntegerDefinition) {
            createIntegerCustomArea((IntegerDefinition) definition, container);
        } else if (definition instanceof ModelDefinition) {
            createModelCustomArea((ModelDefinition) definition, container);
        } else if (definition instanceof RealDefinition) {
            createRealCustomArea((RealDefinition) definition, container);
        } else if (definition instanceof StringDefinition) {
            createStringCustomArea((StringDefinition) definition, container);
        } else {
            throw new IllegalStateException("don't know what to do with " + definition);
        }

        return container;
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link ModelDefinition}.
     * 
     * @param def
     *            the {@link ModelDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createModelCustomArea(final ModelDefinition def, Composite container) {
        final int style = SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;
        final FilteredTree filteredTree = new FilteredTree(container, style, new PatternFilter(), true);
        final TreeViewer treeViewer = filteredTree.getViewer();
        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                final Object selected = ((IStructuredSelection) event.getSelection()).getFirstElement();
                final boolean enableOKButton;
                if (selected instanceof EObject) {
                    final EObject eObj = (EObject) selected;
                    if (isValidValue(def, eObj)) {
                        value = eObj;
                        enableOKButton = true;
                    } else {
                        enableOKButton = false;
                    }
                } else {
                    enableOKButton = false;
                }
                final Button okButton = getButton(OK);
                if (okButton != null) {
                    okButton.setEnabled(enableOKButton);
                }
            }
        });
        treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
        treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
        final GridData gdTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
        gdTable.minimumWidth = TABLE_MINIMUM_WIDTH;
        gdTable.minimumHeight = TABLE_MINIMUM_HEIGHT;
        treeViewer.getTree().setLayoutData(gdTable);
        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final Iterator<IType> it = properties
                .getVariableTypes(validator, queryEnvironment, properties.getVariables().get(def.getKey())).iterator();
        if (it.hasNext()) {
            final IType type = it.next();
            if (type.getType() instanceof EClass) {
                final EClass eCls = (EClass) type.getType();
                final Set<EStructuralFeature> containingFeatures = queryEnvironment.getEPackageProvider()
                        .getAllContainingEStructuralFeatures(eCls);
                treeViewer.addFilter(new ViewerFilter() {

                    @Override
                    public boolean select(Viewer viewer, Object parentElement, Object element) {
                        final boolean res;

                        if (element instanceof Resource) {
                            final Resource resource = (Resource) element;
                            res = canContainsOrHas(resource, containingFeatures, eCls);
                        } else if (element instanceof EObject) {
                            final EStructuralFeature containingFeature = ((EObject) element).eContainingFeature();
                            res = containingFeature == null || containingFeatures.contains(containingFeature)
                                || ((EObject) element).eContainer() instanceof Resource; // the last one is for CDO
                        } else {
                            res = false;
                        }

                        return res;
                    }
                });
            }
        }
        treeViewer.setInput(resourceSet);
        if (def.getValue() != null) {
            try {
                final EObject eObj = resourceSet.getEObject(EcoreUtil.getURI(def.getValue()), true);
                if (eObj != null) {
                    treeViewer.setSelection(new StructuredSelection(eObj), true);
                }
                // CHECKSTYLE:OFF
            } catch (Exception e) {
                // CHECKSTYLE:ON
                // nothing to do here
            }
        } else {
            final Button okButton = getButton(OK);
            if (okButton != null) {
                okButton.setEnabled(false);
            }
        }
    }

    /**
     * Tells if the given {@link Resource} has {@link EObject} that have any of the given {@link EStructuralFeature} or has an instance of the
     * given {@link EClass}.
     * 
     * @param resource
     *            the {@link Resource} to check
     * @param containingFeatures
     *            the {@link Set} of {@link EStructuralFeature}
     * @param eCls
     *            the {@link EClass}
     * @return <code>true</code> if the given {@link Resource} has {@link EObject} that have any of the given {@link EStructuralFeature} or has
     *         an instance of the given {@link EClass}, <code>false</code> otherwise
     */
    private boolean canContainsOrHas(final Resource resource, final Set<EStructuralFeature> containingFeatures,
            EClass eCls) {
        boolean res = false;

        contains: for (EObject content : resource.getContents()) {
            if (eCls.isInstance(content)) {
                res = true;
                break;
            } else {
                for (EReference reference : content.eClass().getEAllReferences()) {
                    if (containingFeatures.contains(reference)) {
                        res = true;
                        break contains;
                    }
                }
            }
        }

        return res;
    }

    /**
     * Tells if the given {@link ModelDefinition} and the given {@link EObject} are compatible.
     * 
     * @param def
     *            the {@link ModelDefinition}
     * @param eObj
     *            the {@link EObject}
     * @return <code>true</code> if the given {@link ModelDefinition} and the given {@link EObject} are compatible, <code>false</code> otherwise
     */
    private boolean isValidValue(ModelDefinition def, EObject eObj) {
        boolean res = false;

        final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
        final EClassifierType eObjType = new EClassifierType(queryEnvironment, eObj.eClass());
        final Set<IType> definitionTypes = properties.getVariableTypes(validator, queryEnvironment,
                properties.getVariables().get(def.getKey()));
        for (IType definitionType : definitionTypes) {
            if (definitionType.isAssignableFrom(eObjType)) {
                res = true;
            }
        }

        return res;
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link StringDefinition}.
     * 
     * @param def
     *            the {@link StringDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createStringCustomArea(StringDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        if (((StringDefinition) definition).getValue() != null) {
            text.setText(((StringDefinition) definition).getValue());
        }
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                value = text.getText();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link RealDefinition}.
     * 
     * @param def
     *            the {@link RealDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createRealCustomArea(RealDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        text.setText(String.valueOf(def.getValue()));
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    value = Double.valueOf(text.getText());
                    getButton(OK).setEnabled(true);
                } catch (NumberFormatException nfe) {
                    getButton(OK).setEnabled(false);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link IntegerDefinition}.
     * 
     * @param def
     *            the {@link IntegerDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createIntegerCustomArea(IntegerDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        text.setText(String.valueOf(def.getValue()));
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    value = Integer.valueOf(text.getText());
                    getButton(OK).setEnabled(true);
                } catch (NumberFormatException nfe) {
                    getButton(OK).setEnabled(false);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }
        });
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link BooleanDefinition}.
     * 
     * @param def
     *            the {@link BooleanDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createBooleanCustomArea(BooleanDefinition def, final Composite container) {
        final Button button = new Button(container, SWT.TOGGLE);
        button.setText(definition.getKey());
        button.setSelection(def.isValue());
        button.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                value = button.getSelection();
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do here
            }
        });
    }

    /**
     * Adds a {@link Label} with the {@link Definition#getKey() definition name} and a {@link Text}.
     * 
     * @param def
     *            the {@link Definition}
     * @param container
     *            the container {@link Composite}
     * @return the created {@link Text}.
     */
    private Text addLabelAndText(Definition def, Composite container) {
        final Composite composite = new Composite(container, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        final Label label = new Label(composite, SWT.NONE);
        label.setText(def.getKey() + " = ");
        final Text text = new Text(composite, SWT.NONE);
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        return text;
    }

    public Object getValue() {
        return value;
    }

}
