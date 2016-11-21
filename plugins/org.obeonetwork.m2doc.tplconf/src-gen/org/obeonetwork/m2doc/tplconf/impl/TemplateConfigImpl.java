/**
 */
package org.obeonetwork.m2doc.tplconf.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.TemplateConfig;
import org.obeonetwork.m2doc.tplconf.TemplateType;
import org.obeonetwork.m2doc.tplconf.TemplateVariable;
import org.obeonetwork.m2doc.tplconf.TplconfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl#getMappings <em>Mappings</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl#getVariables <em>Variables</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.TemplateConfigImpl#getTypesByName <em>Types By Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateConfigImpl extends MinimalEObjectImpl.Container implements TemplateConfig {
    /**
     * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMappings()
     * @generated
     * @ordered
     */
    protected EList<EPackageMapping> mappings;

    /**
     * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getVariables()
     * @generated
     * @ordered
     */
    protected EList<TemplateVariable> variables;

    /**
     * The cached value of the '{@link #getTypesByName() <em>Types By Name</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getTypesByName()
     * @generated
     * @ordered
     */
    protected EMap<String, TemplateType> typesByName;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TemplateConfigImpl() {
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
        return TplconfPackage.Literals.TEMPLATE_CONFIG;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<EPackageMapping> getMappings() {
        if (mappings == null) {
            mappings = new EObjectContainmentEList<EPackageMapping>(EPackageMapping.class, this,
                    TplconfPackage.TEMPLATE_CONFIG__MAPPINGS);
        }
        return mappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<TemplateVariable> getVariables() {
        if (variables == null) {
            variables = new EObjectContainmentEList<TemplateVariable>(TemplateVariable.class, this,
                    TplconfPackage.TEMPLATE_CONFIG__VARIABLES);
        }
        return variables;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EMap<String, TemplateType> getTypesByName() {
        if (typesByName == null) {
            typesByName = new EcoreEMap<String, TemplateType>(TplconfPackage.Literals.STRING_TO_TYPE_MAP_ENTRY,
                    StringToTypeMapEntryImpl.class, this, TplconfPackage.TEMPLATE_CONFIG__TYPES_BY_NAME);
        }
        return typesByName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TplconfPackage.TEMPLATE_CONFIG__MAPPINGS:
                return ((InternalEList<?>) getMappings()).basicRemove(otherEnd, msgs);
            case TplconfPackage.TEMPLATE_CONFIG__VARIABLES:
                return ((InternalEList<?>) getVariables()).basicRemove(otherEnd, msgs);
            case TplconfPackage.TEMPLATE_CONFIG__TYPES_BY_NAME:
                return ((InternalEList<?>) getTypesByName()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
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
            case TplconfPackage.TEMPLATE_CONFIG__MAPPINGS:
                return getMappings();
            case TplconfPackage.TEMPLATE_CONFIG__VARIABLES:
                return getVariables();
            case TplconfPackage.TEMPLATE_CONFIG__TYPES_BY_NAME:
                if (coreType)
                    return getTypesByName();
                else return getTypesByName().map();
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
            case TplconfPackage.TEMPLATE_CONFIG__MAPPINGS:
                getMappings().clear();
                getMappings().addAll((Collection<? extends EPackageMapping>) newValue);
                return;
            case TplconfPackage.TEMPLATE_CONFIG__VARIABLES:
                getVariables().clear();
                getVariables().addAll((Collection<? extends TemplateVariable>) newValue);
                return;
            case TplconfPackage.TEMPLATE_CONFIG__TYPES_BY_NAME:
                ((EStructuralFeature.Setting) getTypesByName()).set(newValue);
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
            case TplconfPackage.TEMPLATE_CONFIG__MAPPINGS:
                getMappings().clear();
                return;
            case TplconfPackage.TEMPLATE_CONFIG__VARIABLES:
                getVariables().clear();
                return;
            case TplconfPackage.TEMPLATE_CONFIG__TYPES_BY_NAME:
                getTypesByName().clear();
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
            case TplconfPackage.TEMPLATE_CONFIG__MAPPINGS:
                return mappings != null && !mappings.isEmpty();
            case TplconfPackage.TEMPLATE_CONFIG__VARIABLES:
                return variables != null && !variables.isEmpty();
            case TplconfPackage.TEMPLATE_CONFIG__TYPES_BY_NAME:
                return typesByName != null && !typesByName.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // TemplateConfigImpl
