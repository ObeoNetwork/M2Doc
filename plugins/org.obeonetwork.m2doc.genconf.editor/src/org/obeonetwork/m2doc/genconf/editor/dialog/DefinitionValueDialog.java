/*******************************************************************************
 *  Copyright (c) 2018, 2023 Obeo. 
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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.ICollectionType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
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
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.ModelSequenceDefinition;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.RealSequenceDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.StringSequenceDefinition;
import org.obeonetwork.m2doc.ide.ui.dialog.EObjectSelectionDialog;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * Value selection dialog.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@SuppressWarnings("restriction")
public class DefinitionValueDialog extends EObjectSelectionDialog {

    /**
     * String value delimiter.
     */
    private static final String DELIMITER = ",";

    /**
     * The {@link Definition} to edit.
     */
    private final Definition definition;

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
        super(parentShell, adapterFactory, "Select value for " + definition.getKey(),
                "Select a value of type " + properties.getVariables().get(definition.getKey()), queryEnvironment,
                resourceSet, null);
        this.definition = definition;
        this.queryEnvironment = queryEnvironment;
        this.properties = properties;
    }

    @Override
    protected Control createCustomArea(Composite parent) {
        final Composite container = new Composite(parent, parent.getStyle());
        container.setLayout(new GridLayout(1, false));
        container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

        if (definition instanceof BooleanDefinition) {
            createBooleanCustomArea((BooleanDefinition) definition, container);
        } else if (definition instanceof BooleanSequenceDefinition) {
            createBooleanSequenceCustomArea((BooleanSequenceDefinition) definition, container);
        } else if (definition instanceof BooleanOrderedSetDefinition) {
            createBooleanOrderedSetCustomArea((BooleanOrderedSetDefinition) definition, container);
        } else if (definition instanceof IntegerDefinition) {
            createIntegerCustomArea((IntegerDefinition) definition, container);
        } else if (definition instanceof IntegerSequenceDefinition) {
            createIntegerSequenceCustomArea((IntegerSequenceDefinition) definition, container);
        } else if (definition instanceof IntegerOrderedSetDefinition) {
            createIntegerOrderedSetCustomArea((IntegerOrderedSetDefinition) definition, container);
        } else if (definition instanceof ModelDefinition) {
            final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
            final Set<IType> acceptedTypes = properties.getVariableTypes(validator, queryEnvironment,
                    properties.getVariables().get(definition.getKey()));
            createModelCustomArea(acceptedTypes, container);
        } else if (definition instanceof ModelSequenceDefinition || definition instanceof ModelOrderedSetDefinition) {
            setMultiSelection(true);
            value = new ArrayList<>();
            final AstValidator validator = new AstValidator(new ValidationServices(queryEnvironment));
            final Set<IType> acceptedTypes = properties.getVariableTypes(validator, queryEnvironment,
                    properties.getVariables().get(definition.getKey()));
            final Set<IType> acceptedRawTypes = new LinkedHashSet<>();
            for (IType acceptedType : acceptedTypes) {
                if (acceptedType instanceof ICollectionType) {
                    acceptedRawTypes.add(((ICollectionType) acceptedType).getCollectionType());
                }
            }
            createModelCustomArea(acceptedRawTypes, container);
        } else if (definition instanceof RealDefinition) {
            createRealCustomArea((RealDefinition) definition, container);
        } else if (definition instanceof RealSequenceDefinition) {
            createRealSequenceCustomArea((RealSequenceDefinition) definition, container);
        } else if (definition instanceof RealOrderedSetDefinition) {
            createRealOrderedSetCustomArea((RealOrderedSetDefinition) definition, container);
        } else if (definition instanceof StringDefinition) {
            createStringCustomArea((StringDefinition) definition, container);
        } else if (definition instanceof StringSequenceDefinition) {
            createStringSequenceCustomArea((StringSequenceDefinition) definition, container);
        } else if (definition instanceof StringOrderedSetDefinition) {
            createStringOrderedSetCustomArea((StringOrderedSetDefinition) definition, container);
        } else {
            throw new IllegalStateException("don't know what to do with " + definition);
        }

        return container;
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link StringDefinition}.
     * 
     * @param def
     *            the {@link StringDefinition}
     * @param container
     *            the container {@link Composite}
     */
    protected void createStringCustomArea(StringDefinition def, final Composite container) {
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link StringSequenceDefinition}.
     * 
     * @param def
     *            the {@link StringSequenceDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createStringSequenceCustomArea(StringSequenceDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (String value : def.getValue()) {
            joiner.add(value);
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    final String[] values = text.getText().split(DELIMITER);
                    final List<String> res = new ArrayList<>();
                    for (String val : values) {
                        res.add(val);
                    }
                    value = res;
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link StringOrderedSetDefinition}.
     * 
     * @param def
     *            the {@link StringOrderedSetDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createStringOrderedSetCustomArea(StringOrderedSetDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (String value : def.getValue()) {
            joiner.add(value);
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    final String[] values = text.getText().split(DELIMITER);
                    final Set<String> res = new LinkedHashSet<>();
                    for (String val : values) {
                        res.add(val);
                    }
                    value = res;
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link RealDefinition}.
     * 
     * @param def
     *            the {@link RealDefinition}
     * @param container
     *            the container {@link Composite}
     */
    protected void createRealCustomArea(RealDefinition def, final Composite container) {
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link RealSequenceDefinition}.
     * 
     * @param def
     *            the {@link RealSequenceDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createRealSequenceCustomArea(RealSequenceDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (Double value : def.getValue()) {
            joiner.add(String.valueOf(value));
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    final String[] values = text.getText().split(DELIMITER);
                    final List<Double> res = new ArrayList<>();
                    for (String val : values) {
                        res.add(Double.valueOf(val.trim()));
                    }
                    value = res;
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link RealOrderedSetDefinition}.
     * 
     * @param def
     *            the {@link RealOrderedSetDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createRealOrderedSetCustomArea(RealOrderedSetDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (Double value : def.getValue()) {
            joiner.add(String.valueOf(value));
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    final String[] values = text.getText().split(DELIMITER);
                    final Set<Double> res = new LinkedHashSet<>();
                    for (String val : values) {
                        res.add(Double.valueOf(val.trim()));
                    }
                    value = res;
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link IntegerSequenceDefinition}.
     * 
     * @param def
     *            the {@link IntegerSequenceDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createIntegerSequenceCustomArea(IntegerSequenceDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (Integer value : def.getValue()) {
            joiner.add(String.valueOf(value));
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    final String[] values = text.getText().split(DELIMITER);
                    final List<Integer> res = new ArrayList<>();
                    for (String val : values) {
                        res.add(Integer.valueOf(val.trim()));
                    }
                    value = res;
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link IntegerOrderedSetDefinition}.
     * 
     * @param def
     *            the {@link IntegerOrderedSetDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createIntegerOrderedSetCustomArea(IntegerOrderedSetDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (Integer value : def.getValue()) {
            joiner.add(String.valueOf(value));
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    final String[] values = text.getText().split(DELIMITER);
                    final Set<Integer> res = new LinkedHashSet<>();
                    for (String val : values) {
                        res.add(Integer.valueOf(val.trim()));
                    }
                    value = res;
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
    protected void createBooleanCustomArea(BooleanDefinition def, final Composite container) {
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
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link BooleanSequenceDefinition}.
     * 
     * @param def
     *            the {@link BooleanSequenceDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createBooleanSequenceCustomArea(BooleanSequenceDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (Boolean value : def.getValue()) {
            joiner.add(String.valueOf(value));
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                final String[] values = text.getText().split(DELIMITER);
                final List<Boolean> res = new ArrayList<>();
                for (String val : values) {
                    res.add(Boolean.valueOf(val.trim()));
                }
                value = res;
                getButton(OK).setEnabled(true);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // nothing to do here
            }

        });
    }

    /**
     * Creates the {@link #createCustomArea(Composite) custom area} for the given {@link BooleanOrderedSetDefinition}.
     * 
     * @param def
     *            the {@link BooleanOrderedSetDefinition}
     * @param container
     *            the container {@link Composite}
     */
    private void createBooleanOrderedSetCustomArea(BooleanOrderedSetDefinition def, final Composite container) {
        final Text text = addLabelAndText(def, container);
        final StringJoiner joiner = new StringJoiner(DELIMITER);
        for (Boolean value : def.getValue()) {
            joiner.add(String.valueOf(value));
        }
        text.setText(joiner.toString());
        text.addKeyListener(new KeyListener() {

            @Override
            public void keyReleased(KeyEvent e) {
                final String[] values = text.getText().split(DELIMITER);
                final Set<Boolean> res = new LinkedHashSet<>();
                for (String val : values) {
                    res.add(Boolean.valueOf(val.trim()));
                }
                value = res;
                getButton(OK).setEnabled(true);
            }

            @Override
            public void keyPressed(KeyEvent e) {
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
        final Composite composite = new Composite(container, container.getStyle());
        composite.setLayout(new GridLayout(2, false));
        composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        final Label label = new Label(composite, container.getStyle());
        label.setText(def.getKey() + " = ");
        final Text text = new Text(composite, container.getStyle());
        text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

        return text;
    }

    public Object getValue() {
        return value;
    }

    @Override
    protected void createLoadResourceButton(Composite parent) {
        // nothing to do here
    }

}
