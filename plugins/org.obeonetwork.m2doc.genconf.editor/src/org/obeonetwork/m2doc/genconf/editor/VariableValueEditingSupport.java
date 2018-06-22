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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;

/**
 * {@link Definition#getKey() variable name} editing support.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class VariableValueEditingSupport extends EditingSupport {

    /**
     * The {@link EditingDomain}.
     */
    private EditingDomain editingDomain;

    /**
     * The {@link AdapterFactory}.
     */
    private AdapterFactory adapterFactory;

    /**
     * The {@link ITemplateCustomPropertiesProvider}.
     */
    private ITemplateCustomPropertiesProvider provider;

    /**
     * Constructor.
     * 
     * @param viewer
     *            the {@link ColumnViewer}
     * @param editingDomain
     *            the {@link EditingDomain}
     * @param adapterFactory
     *            the {@link AdapterFactory}
     * @param provider
     *            the {@link ITemplateCustomPropertiesProvider}
     */
    public VariableValueEditingSupport(ColumnViewer viewer, EditingDomain editingDomain, AdapterFactory adapterFactory,
            ITemplateCustomPropertiesProvider provider) {
        super(viewer);
        this.editingDomain = editingDomain;
        this.adapterFactory = adapterFactory;
        this.provider = provider;
    }

    @Override
    protected CellEditor getCellEditor(Object element) {
        final Definition definition = (Definition) element;
        final EStructuralFeature valueFeature = getValueFeature(definition);
        final AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory);
        IPropertySource propertySource = contentProvider.getPropertySource(element);
        IPropertyDescriptor[] propertyDescriptors = propertySource.getPropertyDescriptors();
        for (IPropertyDescriptor propertyDescriptor : propertyDescriptors) {
            if (valueFeature.getName().equals(propertyDescriptor.getId())) {
                return propertyDescriptor.createPropertyEditor((Composite) getViewer().getControl());
            }
        }
        return null;
    }

    @Override
    protected boolean canEdit(Object element) {
        return this.provider.getTemplateCustomProperties() != null;
    }

    @Override
    protected Object getValue(Object element) {
        final Definition definition = (Definition) element;
        final EStructuralFeature valueFeature = getValueFeature(definition);

        return definition.eGet(valueFeature);
    }

    @Override
    protected void setValue(Object element, Object value) {
        final Definition definition = (Definition) element;
        final EStructuralFeature valueFeature = getValueFeature(definition);
        final Object currentValue = definition.eGet(valueFeature);
        if ((currentValue == null && value != null) || (currentValue != null && !currentValue.equals(value))) {
            editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, definition, valueFeature, value));
        }
    }

    /**
     * Gets the {@link EStructuralFeature} containing the value of the {@link Definition}.
     * 
     * @param definition
     *            the {@link Definition}
     * @return the {@link EStructuralFeature} containing the value of the {@link Definition}.
     */
    EStructuralFeature getValueFeature(Definition definition) {
        final EStructuralFeature res;

        if (definition instanceof ModelDefinition) {
            res = GenconfPackage.Literals.MODEL_DEFINITION__VALUE;
        } else if (definition instanceof StringDefinition) {
            res = GenconfPackage.Literals.STRING_DEFINITION__VALUE;
        } else {
            throw new IllegalStateException(
                    CustomGenconfEditor.DON_T_KNOW_WHAT_TO_DO_WITH + definition.getClass().getCanonicalName());
        }

        return res;
    }

}
