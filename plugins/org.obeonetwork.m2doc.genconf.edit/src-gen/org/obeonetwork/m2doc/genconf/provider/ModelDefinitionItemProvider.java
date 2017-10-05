/**
 */
package org.obeonetwork.m2doc.genconf.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.query.parser.AstValidator;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.Query;
import org.eclipse.acceleo.query.runtime.impl.ValidationServices;
import org.eclipse.acceleo.query.validation.type.EClassifierType;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.obeonetwork.m2doc.POIServices;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
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
                                    URI templateURI = URI.createURI(generation.getTemplateFileName())
                                            .resolve(generation.eResource().getURI());
                                    properties = POIServices.getInstance()
                                            .getTemplateCustomProperties(URIConverter.INSTANCE, templateURI);
                                    queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
                                    queryEnvironment.registerCustomClassMapping(
                                            EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
                                            EStringToStringMapEntryImpl.class);
                                    properties.configureQueryEnvironmentWithResult(queryEnvironment);
                                    final AstValidator validator = new AstValidator(
                                            new ValidationServices(queryEnvironment));
                                    final Set<IType> possibleTypes = properties.getVariableTypes(validator,
                                            queryEnvironment, properties.getVariables().get(modelDefinition.getKey()));
                                    res = filterTypes(queryEnvironment, possibleTypes, super.getChoiceOfValues(object));
                                } catch (IOException e) {
                                    res = super.getChoiceOfValues(object);
                                }
                            } else {
                                res = super.getChoiceOfValues(object);
                            }
                        }

                        return res;
                    }

                    private List<?> filterTypes(IReadOnlyQueryEnvironment queryEnvironment, Set<IType> possibleTypes,
                            Collection<?> values) {
                        final List<Object> res = new ArrayList<Object>();

                        for (Object value : values) {
                            if (value == null || (value instanceof EObject
                                && isCompatibleType(queryEnvironment, possibleTypes, ((EObject) value).eClass()))) {
                                res.add(value);
                            }
                        }

                        return res;
                    }

                    private boolean isCompatibleType(IReadOnlyQueryEnvironment queryEnvironment,
                            Set<IType> possibleTypes, EClass eClass) {
                        boolean res = false;

                        final IType variableType = new EClassifierType(queryEnvironment, eClass);
                        for (IType possibleType : possibleTypes) {
                            if (possibleType.isAssignableFrom(variableType)) {
                                res = true;
                                break;
                            }
                        }

                        return res;
                    }

                });

    }

    /**
     * This returns ModelDefinition.gif.
     * <!-- begin-user-doc --> <!--
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
