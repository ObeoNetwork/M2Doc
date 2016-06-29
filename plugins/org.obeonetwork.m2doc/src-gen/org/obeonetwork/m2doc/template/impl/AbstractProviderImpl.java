/**
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 */
package org.obeonetwork.m2doc.template.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.obeonetwork.m2doc.provider.IProvider;

import org.obeonetwork.m2doc.template.AbstractProvider;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderImpl#getOptionValueMap <em>Option Value Map</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderImpl#getProvider <em>Provider</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractProviderImpl extends AbstractConstructImpl implements AbstractProvider {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The cached value of the '{@link #getOptionValueMap() <em>Option Value Map</em>}' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getOptionValueMap()
     * @generated
     * @ordered
     */
    protected EMap<String, Object> optionValueMap;

    /**
     * The default value of the '{@link #getProvider() <em>Provider</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProvider()
     * @generated
     * @ordered
     */
    protected static final IProvider PROVIDER_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getProvider() <em>Provider</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getProvider()
     * @generated
     * @ordered
     */
    protected IProvider provider = PROVIDER_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AbstractProviderImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.ABSTRACT_PROVIDER;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, Object> getOptionValueMap() {
        if (optionValueMap == null) {
            optionValueMap = new EcoreEMap<String,Object>(TemplatePackage.Literals.OPTION_VALUE_MAP, OptionValueMapImpl.class, this, TemplatePackage.ABSTRACT_PROVIDER__OPTION_VALUE_MAP);
        }
        return optionValueMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IProvider getProvider() {
        return provider;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setProvider(IProvider newProvider) {
        IProvider oldProvider = provider;
        provider = newProvider;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_PROVIDER__PROVIDER, oldProvider, provider));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER__OPTION_VALUE_MAP:
                return ((InternalEList<?>)getOptionValueMap()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER__OPTION_VALUE_MAP:
                if (coreType) return getOptionValueMap();
                else return getOptionValueMap().map();
            case TemplatePackage.ABSTRACT_PROVIDER__PROVIDER:
                return getProvider();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER__OPTION_VALUE_MAP:
                ((EStructuralFeature.Setting)getOptionValueMap()).set(newValue);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER__PROVIDER:
                setProvider((IProvider)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER__OPTION_VALUE_MAP:
                getOptionValueMap().clear();
                return;
            case TemplatePackage.ABSTRACT_PROVIDER__PROVIDER:
                setProvider(PROVIDER_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER__OPTION_VALUE_MAP:
                return optionValueMap != null && !optionValueMap.isEmpty();
            case TemplatePackage.ABSTRACT_PROVIDER__PROVIDER:
                return PROVIDER_EDEFAULT == null ? provider != null : !PROVIDER_EDEFAULT.equals(provider);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (provider: ");
        result.append(provider);
        result.append(')');
        return result.toString();
    }

} //AbstractProviderImpl
