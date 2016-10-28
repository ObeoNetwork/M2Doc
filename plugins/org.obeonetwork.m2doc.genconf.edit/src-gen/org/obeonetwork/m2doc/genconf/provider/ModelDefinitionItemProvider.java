/**
 */
package org.obeonetwork.m2doc.genconf.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.ModelDefinition;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This is the item provider adapter for a {@link org.obeonetwork.m2doc.genconf.ModelDefinition} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelDefinitionItemProvider extends DefinitionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ModelDefinitionItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addValuePropertyDescriptor(object);
            addTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Value feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated-not
     */
    protected void addValuePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(new ItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_ModelDefinition_value_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_ModelDefinition_value_feature",
                                "_UI_ModelDefinition_type"),
                        GenconfPackage.Literals.MODEL_DEFINITION__VALUE, true, false, true, null, null, null) {

                    /**
                     * (non-Javadoc)
                     * 
                     * @see org.eclipse.emf.edit.provider.ItemPropertyDescriptor#getChoiceOfValues(java.lang.Object)
                     */
                    @SuppressWarnings({"unchecked", "rawtypes"})
                    @Override
                    public Collection<?> getChoiceOfValues(final Object object) {
                        Collection<?> choiceOfValues = super.getChoiceOfValues(object);
                        Iterable filter = Iterables.filter(choiceOfValues, new Predicate() {

                            /**
                             * (non-Javadoc)
                             * 
                             * @see com.google.common.base.Predicate#apply(java.lang.Object)
                             */
                            @Override
                            public boolean apply(Object arg0) {
                                return arg0 == null || ((ModelDefinition) object).getType() == null
                                    || (arg0 != null && ((ModelDefinition) object) != null && arg0 instanceof EObject
                                        && ((ModelDefinition) object).getType().getEPackage().getNsURI()
                                                .equals(((EObject) arg0).eClass().getEPackage().getNsURI())
                                        && ((ModelDefinition) object).getType().getName()
                                                .equals(((EObject) arg0).eClass().getName()));
                            }
                        });
                        return Lists.newArrayList(filter);
                    }

                });
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors.add
            (createItemPropertyDescriptor
                (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                 getResourceLocator(),
                 getString("_UI_ModelDefinition_type_feature"),
                 getString("_UI_PropertyDescriptor_description", "_UI_ModelDefinition_type_feature", "_UI_ModelDefinition_type"),
                 GenconfPackage.Literals.MODEL_DEFINITION__TYPE,
                 true,
                 false,
                 false,
                 null,
                 null,
                 null));
    }

    /**
     * This returns ModelDefinition.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelDefinition"));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean shouldComposeCreationImage() {
        return true;
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated-not
     */
    @Override
    public String getText(Object object) {
        String label = ((ModelDefinition) object).getKey();
        EObject value = ((ModelDefinition) object).getValue();
        if (value != null) {
            label += ": " + value.toString();
        }
        return label == null ? "" : label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(ModelDefinition.class)) {
            case GenconfPackage.MODEL_DEFINITION__TYPE:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
