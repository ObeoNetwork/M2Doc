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
package org.obeonetwork.m2doc.genconf.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;

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
        } else if (definition instanceof IntegerDefinition) {
            cell.setText(String.valueOf(((IntegerDefinition) definition).getValue()));
        } else if (definition instanceof RealDefinition) {
            cell.setText(String.valueOf(((RealDefinition) definition).getValue()));
        } else if (definition instanceof BooleanDefinition) {
            cell.setText(String.valueOf(((BooleanDefinition) definition).isValue()));
        } else if (definition instanceof ModelDefinition) {
            final String text;
            final EObject eObj = ((ModelDefinition) definition).getValue();
            if (eObj != null) {
                final IItemLabelProvider itemProvider = (IItemLabelProvider) adapterFactory.adapt(eObj,
                        IItemLabelProvider.class);

                if (itemProvider == null) {
                    text = eObj.toString();
                } else {
                    text = itemProvider.getText(eObj);
                }
                cell.setText(text);
            } else {
                cell.setText("");
            }
        } else {
            cell.setText(DON_T_KNOW_WHAT_TO_DO_WITH + definition.getClass().getCanonicalName());
        }
    }
}
