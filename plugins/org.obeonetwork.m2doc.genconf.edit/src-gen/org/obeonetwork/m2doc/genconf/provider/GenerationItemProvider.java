/**
 */
package org.obeonetwork.m2doc.genconf.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;

/**
 * This is the item provider adapter for a {@link org.obeonetwork.m2doc.genconf.Generation} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class GenerationItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
        IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
    /**
     * This constructs an instance from a factory and a notifier.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public GenerationItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            addNamePropertyDescriptor(object);
            addTemplateFileNamePropertyDescriptor(object);
            addResultFileNamePropertyDescriptor(object);
            addValidationFileNamePropertyDescriptor(object);
            addTimeStampedPropertyDescriptor(object);
            addRefreshRepresentationsPropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_Generation_name_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Generation_name_feature",
                                "_UI_Generation_type"),
                        GenconfPackage.Literals.GENERATION__NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Template File Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTemplateFileNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_Generation_templateFileName_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Generation_templateFileName_feature",
                                "_UI_Generation_type"),
                        GenconfPackage.Literals.GENERATION__TEMPLATE_FILE_NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Result File Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addResultFileNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_Generation_resultFileName_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Generation_resultFileName_feature",
                                "_UI_Generation_type"),
                        GenconfPackage.Literals.GENERATION__RESULT_FILE_NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Validation File Name feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addValidationFileNamePropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_Generation_validationFileName_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Generation_validationFileName_feature",
                                "_UI_Generation_type"),
                        GenconfPackage.Literals.GENERATION__VALIDATION_FILE_NAME, true, false, false,
                        ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Time Stamped feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addTimeStampedPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_Generation_timeStamped_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Generation_timeStamped_feature",
                                "_UI_Generation_type"),
                        GenconfPackage.Literals.GENERATION__TIME_STAMPED, true, false, false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Refresh Representations feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void addRefreshRepresentationsPropertyDescriptor(Object object) {
        itemPropertyDescriptors
                .add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
                        getResourceLocator(), getString("_UI_Generation_refreshRepresentations_feature"),
                        getString("_UI_PropertyDescriptor_description", "_UI_Generation_refreshRepresentations_feature",
                                "_UI_Generation_type"),
                        GenconfPackage.Literals.GENERATION__REFRESH_REPRESENTATIONS, true, false, false,
                        ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, null, null));
    }

    /**
     * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
     * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
     * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
        if (childrenFeatures == null) {
            super.getChildrenFeatures(object);
            childrenFeatures.add(GenconfPackage.Literals.GENERATION__DEFINITIONS);
            childrenFeatures.add(GenconfPackage.Literals.GENERATION__OPTIONS);
        }
        return childrenFeatures;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EStructuralFeature getChildFeature(Object object, Object child) {
        // Check the type of the specified child object and return the proper feature to use for
        // adding (see {@link AddCommand}) it as a child.

        return super.getChildFeature(object, child);
    }

    /**
     * This returns Generation.gif.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/Generation"));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((Generation) object).getName();
        return label == null || label.length() == 0 ? getString("_UI_Generation_type")
                : getString("_UI_Generation_type") + " " + label;
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached
     * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);

        switch (notification.getFeatureID(Generation.class)) {
            case GenconfPackage.GENERATION__NAME:
            case GenconfPackage.GENERATION__TEMPLATE_FILE_NAME:
            case GenconfPackage.GENERATION__RESULT_FILE_NAME:
            case GenconfPackage.GENERATION__VALIDATION_FILE_NAME:
            case GenconfPackage.GENERATION__TIME_STAMPED:
            case GenconfPackage.GENERATION__REFRESH_REPRESENTATIONS:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
                return;
            case GenconfPackage.GENERATION__DEFINITIONS:
            case GenconfPackage.GENERATION__OPTIONS:
                fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
                return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
     * that can be created under this object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);

        newChildDescriptors.add(createChildParameter(GenconfPackage.Literals.GENERATION__DEFINITIONS,
                GenconfFactory.eINSTANCE.createModelDefinition()));

        newChildDescriptors.add(createChildParameter(GenconfPackage.Literals.GENERATION__DEFINITIONS,
                GenconfFactory.eINSTANCE.createStringDefinition()));

        newChildDescriptors.add(createChildParameter(GenconfPackage.Literals.GENERATION__DEFINITIONS,
                GenconfFactory.eINSTANCE.createIntegerDefinition()));

        newChildDescriptors.add(createChildParameter(GenconfPackage.Literals.GENERATION__DEFINITIONS,
                GenconfFactory.eINSTANCE.createRealDefinition()));

        newChildDescriptors.add(createChildParameter(GenconfPackage.Literals.GENERATION__DEFINITIONS,
                GenconfFactory.eINSTANCE.createBooleanDefinition()));

        newChildDescriptors.add(createChildParameter(GenconfPackage.Literals.GENERATION__OPTIONS,
                GenconfFactory.eINSTANCE.createOption()));
    }

    /**
     * Return the resource locator for this item provider's resources.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceLocator getResourceLocator() {
        return M2docconfEditPlugin.INSTANCE;
    }

}
