/**
 */
package org.obeonetwork.m2doc.tplconf.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.StructuredType;
import org.obeonetwork.m2doc.tplconf.TplconfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structured Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl#getMappingName <em>Mapping Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl#getMapping <em>Mapping</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.StructuredTypeImpl#getEClassifier <em>EClassifier</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StructuredTypeImpl extends TemplateTypeImpl implements StructuredType {
    /**
     * The default value of the '{@link #getMappingName() <em>Mapping Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMappingName()
     * @generated
     * @ordered
     */
    protected static final String MAPPING_NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getMappingName() <em>Mapping Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMappingName()
     * @generated
     * @ordered
     */
    protected String mappingName = MAPPING_NAME_EDEFAULT;

    /**
     * The cached value of the '{@link #getMapping() <em>Mapping</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getMapping()
     * @generated
     * @ordered
     */
    protected EPackageMapping mapping;

    /**
     * The cached value of the '{@link #getEClassifier() <em>EClassifier</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEClassifier()
     * @generated
     * @ordered
     */
    protected EObject eClassifier;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected StructuredTypeImpl() {
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
        return TplconfPackage.Literals.STRUCTURED_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getMappingName() {
        return mappingName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMappingName(String newMappingName) {
        String oldMappingName = mappingName;
        mappingName = newMappingName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TplconfPackage.STRUCTURED_TYPE__MAPPING_NAME,
                    oldMappingName, mappingName));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EPackageMapping getMapping() {
        if (mapping != null && mapping.eIsProxy()) {
            InternalEObject oldMapping = (InternalEObject) mapping;
            mapping = (EPackageMapping) eResolveProxy(oldMapping);
            if (mapping != oldMapping) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TplconfPackage.STRUCTURED_TYPE__MAPPING,
                            oldMapping, mapping));
            }
        }
        return mapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EPackageMapping basicGetMapping() {
        return mapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMapping(EPackageMapping newMapping) {
        EPackageMapping oldMapping = mapping;
        mapping = newMapping;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TplconfPackage.STRUCTURED_TYPE__MAPPING, oldMapping,
                    mapping));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject getEClassifier() {
        if (eClassifier != null && eClassifier.eIsProxy()) {
            InternalEObject oldEClassifier = (InternalEObject) eClassifier;
            eClassifier = eResolveProxy(oldEClassifier);
            if (eClassifier != oldEClassifier) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            TplconfPackage.STRUCTURED_TYPE__ECLASSIFIER, oldEClassifier, eClassifier));
            }
        }
        return eClassifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetEClassifier() {
        return eClassifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEClassifier(EObject newEClassifier) {
        EObject oldEClassifier = eClassifier;
        eClassifier = newEClassifier;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TplconfPackage.STRUCTURED_TYPE__ECLASSIFIER,
                    oldEClassifier, eClassifier));
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
            case TplconfPackage.STRUCTURED_TYPE__MAPPING_NAME:
                return getMappingName();
            case TplconfPackage.STRUCTURED_TYPE__MAPPING:
                if (resolve)
                    return getMapping();
                return basicGetMapping();
            case TplconfPackage.STRUCTURED_TYPE__ECLASSIFIER:
                if (resolve)
                    return getEClassifier();
                return basicGetEClassifier();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TplconfPackage.STRUCTURED_TYPE__MAPPING_NAME:
                setMappingName((String) newValue);
                return;
            case TplconfPackage.STRUCTURED_TYPE__MAPPING:
                setMapping((EPackageMapping) newValue);
                return;
            case TplconfPackage.STRUCTURED_TYPE__ECLASSIFIER:
                setEClassifier((EObject) newValue);
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
            case TplconfPackage.STRUCTURED_TYPE__MAPPING_NAME:
                setMappingName(MAPPING_NAME_EDEFAULT);
                return;
            case TplconfPackage.STRUCTURED_TYPE__MAPPING:
                setMapping((EPackageMapping) null);
                return;
            case TplconfPackage.STRUCTURED_TYPE__ECLASSIFIER:
                setEClassifier((EObject) null);
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
            case TplconfPackage.STRUCTURED_TYPE__MAPPING_NAME:
                return MAPPING_NAME_EDEFAULT == null ? mappingName != null : !MAPPING_NAME_EDEFAULT.equals(mappingName);
            case TplconfPackage.STRUCTURED_TYPE__MAPPING:
                return mapping != null;
            case TplconfPackage.STRUCTURED_TYPE__ECLASSIFIER:
                return eClassifier != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (mappingName: ");
        result.append(mappingName);
        result.append(')');
        return result.toString();
    }

} // StructuredTypeImpl
