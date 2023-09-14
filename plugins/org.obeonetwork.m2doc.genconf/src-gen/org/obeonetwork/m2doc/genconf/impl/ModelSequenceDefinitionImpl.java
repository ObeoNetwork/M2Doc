/**
 */
package org.obeonetwork.m2doc.genconf.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.ModelSequenceDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Sequence Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.impl.ModelSequenceDefinitionImpl#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModelSequenceDefinitionImpl extends DefinitionImpl implements ModelSequenceDefinition {
    /**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getValue()
     * @generated
     * @ordered
     */
    protected EList<EObject> value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ModelSequenceDefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return GenconfPackage.Literals.MODEL_SEQUENCE_DEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<EObject> getValue() {
        if (value == null) {
            value = new EObjectResolvingEList<EObject>(EObject.class, this,
                    GenconfPackage.MODEL_SEQUENCE_DEFINITION__VALUE);
        }
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case GenconfPackage.MODEL_SEQUENCE_DEFINITION__VALUE:
                return getValue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case GenconfPackage.MODEL_SEQUENCE_DEFINITION__VALUE:
                getValue().clear();
                getValue().addAll((Collection<? extends EObject>) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case GenconfPackage.MODEL_SEQUENCE_DEFINITION__VALUE:
                getValue().clear();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case GenconfPackage.MODEL_SEQUENCE_DEFINITION__VALUE:
                return value != null && !value.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ModelSequenceDefinitionImpl
