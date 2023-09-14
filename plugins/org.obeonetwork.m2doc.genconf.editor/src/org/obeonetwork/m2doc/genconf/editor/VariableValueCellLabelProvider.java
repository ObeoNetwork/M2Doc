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
package org.obeonetwork.m2doc.genconf.editor;

import java.util.StringJoiner;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
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

/**
 * Variable value {@link CellLabelProvider}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class VariableValueCellLabelProvider extends CellLabelProvider {

    /**
     * Illegal state message.
     */
    static final String DON_T_KNOW_WHAT_TO_DO_WITH = "don't know what to do with ";

    /**
     * String label delimiter.
     */
    private static final String DELIMITER = ", ";

    /**
     * The {@link AdapterFactory}.
     */
    private AdapterFactory adapterFactory;

    /**
     * Consturctor.
     * 
     * @param adapterFactory
     *            the {@link AdapterFactory}
     */
    public VariableValueCellLabelProvider(AdapterFactory adapterFactory) {
        this.adapterFactory = adapterFactory;
    }

    @Override
    public void update(ViewerCell cell) {
        final Definition definition = (Definition) cell.getElement();
        if (definition instanceof StringDefinition) {
            cell.setText(((StringDefinition) definition).getValue());
        } else if (definition instanceof StringSequenceDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (String value : ((StringSequenceDefinition) definition).getValue()) {
                joiner.add(value);
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof StringOrderedSetDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (String value : ((StringOrderedSetDefinition) definition).getValue()) {
                joiner.add(value);
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof IntegerDefinition) {
            cell.setText(String.valueOf(((IntegerDefinition) definition).getValue()));
        } else if (definition instanceof IntegerSequenceDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Integer value : ((IntegerSequenceDefinition) definition).getValue()) {
                joiner.add(String.valueOf(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof IntegerOrderedSetDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Integer value : ((IntegerOrderedSetDefinition) definition).getValue()) {
                joiner.add(String.valueOf(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof RealDefinition) {
            cell.setText(String.valueOf(((RealDefinition) definition).getValue()));
        } else if (definition instanceof RealSequenceDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Double value : ((RealSequenceDefinition) definition).getValue()) {
                joiner.add(String.valueOf(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof RealOrderedSetDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Double value : ((RealOrderedSetDefinition) definition).getValue()) {
                joiner.add(String.valueOf(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof BooleanDefinition) {
            cell.setText(String.valueOf(((BooleanDefinition) definition).isValue()));
        } else if (definition instanceof BooleanSequenceDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Boolean value : ((BooleanSequenceDefinition) definition).getValue()) {
                joiner.add(String.valueOf(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof BooleanOrderedSetDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (Boolean value : ((BooleanOrderedSetDefinition) definition).getValue()) {
                joiner.add(String.valueOf(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof ModelDefinition) {
            final EObject eObj = ((ModelDefinition) definition).getValue();
            if (eObj != null) {
                final String text = getLabel(eObj);
                cell.setText(text);
            } else {
                cell.setText("");
            }
        } else if (definition instanceof ModelSequenceDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (EObject value : ((ModelSequenceDefinition) definition).getValue()) {
                joiner.add(getLabel(value));
            }
            cell.setText(joiner.toString());
        } else if (definition instanceof ModelOrderedSetDefinition) {
            final StringJoiner joiner = new StringJoiner(DELIMITER);
            for (EObject value : ((ModelOrderedSetDefinition) definition).getValue()) {
                joiner.add(getLabel(value));
            }
            cell.setText(joiner.toString());
        } else {
            cell.setText(DON_T_KNOW_WHAT_TO_DO_WITH + definition.getClass().getCanonicalName());
        }
    }

    /**
     * Gets the label of the given {@link EObject}.
     * 
     * @param eObj
     *            the {@link EObject}
     * @return the label of the given {@link EObject}
     */
    private String getLabel(final EObject eObj) {
        final String text;
        final IItemLabelProvider itemProvider = (IItemLabelProvider) adapterFactory.adapt(eObj,
                IItemLabelProvider.class);

        if (itemProvider == null) {
            text = eObj.toString();
        } else {
            text = itemProvider.getText(eObj);
        }
        return text;
    }
}
