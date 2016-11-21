/**
 */
package org.obeonetwork.m2doc.tplconf.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.obeonetwork.m2doc.tplconf.EPackageMapping;
import org.obeonetwork.m2doc.tplconf.TplconfPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EPackage Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl#getUri <em>Uri</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.tplconf.impl.EPackageMappingImpl#getEPackage <em>EPackage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EPackageMappingImpl extends MinimalEObjectImpl.Container implements EPackageMapping {
    /**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected static final String NAME_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getName()
     * @generated
     * @ordered
     */
    protected String name = NAME_EDEFAULT;

    /**
     * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getUri()
     * @generated
     * @ordered
     */
    protected static final String URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getUri()
     * @generated
     * @ordered
     */
    protected String uri = URI_EDEFAULT;

    /**
     * The cached value of the '{@link #getEPackage() <em>EPackage</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getEPackage()
     * @generated
     * @ordered
     */
    protected EObject ePackage;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected EPackageMappingImpl() {
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
        return TplconfPackage.Literals.EPACKAGE_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TplconfPackage.EPACKAGE_MAPPING__NAME, oldName,
                    name));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getUri() {
        return uri;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setUri(String newUri) {
        String oldUri = uri;
        uri = newUri;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TplconfPackage.EPACKAGE_MAPPING__URI, oldUri, uri));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject getEPackage() {
        if (ePackage != null && ePackage.eIsProxy()) {
            InternalEObject oldEPackage = (InternalEObject) ePackage;
            ePackage = eResolveProxy(oldEPackage);
            if (ePackage != oldEPackage) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, TplconfPackage.EPACKAGE_MAPPING__EPACKAGE,
                            oldEPackage, ePackage));
            }
        }
        return ePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetEPackage() {
        return ePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setEPackage(EObject newEPackage) {
        EObject oldEPackage = ePackage;
        ePackage = newEPackage;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TplconfPackage.EPACKAGE_MAPPING__EPACKAGE,
                    oldEPackage, ePackage));
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
            case TplconfPackage.EPACKAGE_MAPPING__NAME:
                return getName();
            case TplconfPackage.EPACKAGE_MAPPING__URI:
                return getUri();
            case TplconfPackage.EPACKAGE_MAPPING__EPACKAGE:
                if (resolve)
                    return getEPackage();
                return basicGetEPackage();
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
            case TplconfPackage.EPACKAGE_MAPPING__NAME:
                setName((String) newValue);
                return;
            case TplconfPackage.EPACKAGE_MAPPING__URI:
                setUri((String) newValue);
                return;
            case TplconfPackage.EPACKAGE_MAPPING__EPACKAGE:
                setEPackage((EObject) newValue);
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
            case TplconfPackage.EPACKAGE_MAPPING__NAME:
                setName(NAME_EDEFAULT);
                return;
            case TplconfPackage.EPACKAGE_MAPPING__URI:
                setUri(URI_EDEFAULT);
                return;
            case TplconfPackage.EPACKAGE_MAPPING__EPACKAGE:
                setEPackage((EObject) null);
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
            case TplconfPackage.EPACKAGE_MAPPING__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case TplconfPackage.EPACKAGE_MAPPING__URI:
                return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
            case TplconfPackage.EPACKAGE_MAPPING__EPACKAGE:
                return ePackage != null;
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
        result.append(" (name: ");
        result.append(name);
        result.append(", uri: ");
        result.append(uri);
        result.append(')');
        return result.toString();
    }

} // EPackageMappingImpl
