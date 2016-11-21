/**
 */
package org.obeonetwork.m2doc.tplconf.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfPackage;

/**
 * This is the item provider adapter for a
 * {@link org.obeonetwork.m2doc.tplconf.TemplateVariable} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class TemplateVariableItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemplateVariableItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addNamePropertyDescriptor(object);
            addTypeNamePropertyDescriptor(object);
            addTypePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_TemplateVariable_name_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_TemplateVariable_name_feature",
                                "_UI_TemplateVariable_type"),
                        TplconfPackage.Literals.TEMPLATE_VARIABLE__NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Type Name feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTypeNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_TemplateVariable_typeName_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_TemplateVariable_typeName_feature",
                                "_UI_TemplateVariable_type"),
                        TplconfPackage.Literals.TEMPLATE_VARIABLE__TYPE_NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Type feature.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTypePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_TemplateVariable_type_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_TemplateVariable_type_feature",
                                "_UI_TemplateVariable_type"),
                        TplconfPackage.Literals.TEMPLATE_VARIABLE__TYPE, true, false, true, null, null, null));
    }

    /**
     * This returns TemplateVariable.gif.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/TemplateVariable"));
    }

    /**
     * This returns the label text for the adapted class.
     * <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((TemplateVariable) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_TemplateVariable_type")
                : getString("_UI_TemplateVariable_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(TemplateVariable.class)) {
            case TplconfPackage.TEMPLATE_VARIABLE__NAME:
            case TplconfPackage.TEMPLATE_VARIABLE__TYPE_NAME:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s
     * describing the children that can be created under this object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

    /**
     * Return the resource locator for this item provider's resources. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return TplconfEditPlugin.INSTANCE;
    }

}
