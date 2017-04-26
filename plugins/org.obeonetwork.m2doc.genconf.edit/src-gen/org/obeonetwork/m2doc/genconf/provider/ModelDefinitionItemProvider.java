/**
 */
package org.obeonetwork.m2doc.genconf.provider;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.generator.M2DocValidator;
import org.obeonetwork.m2doc.properties.TemplateCustomProperties;

/**
 * This is the item provider adapter for a
 * {@link org.obeonetwork.m2doc.genconf.ModelDefinition} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
@SuppressWarnings("restriction")
public class ModelDefinitionItemProvider extends DefinitionItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ModelDefinitionItemProvider(AdapterFactory adapterFactory) {
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

            addValuePropertyDescriptor(object);
        }
        return itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Value feature. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated NOT
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
                    @Override
                    public Collection<?> getChoiceOfValues(final Object object) {
                        Collection<?> res;

                        if (object == null) {
                            res = super.getChoiceOfValues(object);
                        } else {
                            final ModelDefinition modelDefinition = (ModelDefinition) object;
                            if (modelDefinition.eContainer() instanceof Generation) {
                                final Generation generation = (Generation) modelDefinition.eContainer();
                                TemplateCustomProperties properties;
                                try {
                                    final IQueryEnvironment queryEnvironment = Query.newEnvironment();
                                    properties = POIServices.getInstance().getTemplateCustomProperties(
                                            URI.createURI(generation.getTemplateFileName()));
                                    for (String nsURI : properties.getPackagesURIs()) {
                                        final EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
                                        queryEnvironment.registerEPackage(ePackage);
                                    }
                                    final AstValidator validator = new AstValidator(
                                            new ValidationServices(queryEnvironment));
                                    final Set<IType> possibleTypes = M2DocValidator.getVariableTypes(validator,
                                            queryEnvironment, properties.getVariables().get(modelDefinition.getKey()));
                                    final Iterable<?> filter = Iterables.filter(super.getChoiceOfValues(object),
                                            new Predicate<Object>() {

                                                /**
                                                 * (non-Javadoc)
                                                 * 
                                                 * @see com.google.common.base.Predicate#apply(java.lang.Object)
                                                 */
                                                @Override
                                                public boolean apply(Object value) {
                                                    return value == null || (value instanceof EObject
                                                        && isCompatiblType(possibleTypes, ((EObject) value).eClass()));
                                                }

                                                private boolean isCompatiblType(Set<IType> possibleTypes,
                                                        EClass eClass) {
                                                    boolean res = false;

                                                    final IType variableType = new EClassifierType(queryEnvironment,
                                                            eClass);
                                                    for (IType possibleType : possibleTypes) {
                                                        if (possibleType.isAssignableFrom(variableType)) {
                                                            res = true;
                                                            break;
                                                        }
                                                    }

                                                    return res;
                                                }
                                            });
                                    res = Lists.newArrayList(filter);
                                } catch (IOException e) {
                                    res = super.getChoiceOfValues(object);
                                }
                            } else {
                                res = super.getChoiceOfValues(object);
                            }
                        }

                        return res;
                    }
                });

    }

    /**
     * This returns ModelDefinition.gif. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object getImage(Object object) {
        return overlayImage(object, getResourceLocator().getImage("full/obj16/ModelDefinition"));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected boolean shouldComposeCreationImage() {
        return true;
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
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
     * This handles model notifications by calling {@link #updateChildren} to
     * update any cached children and by creating a viewer notification, which
     * it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        updateChildren(notification);
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

}
