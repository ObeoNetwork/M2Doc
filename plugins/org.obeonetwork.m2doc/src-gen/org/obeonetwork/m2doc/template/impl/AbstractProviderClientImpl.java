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

import java.util.Collection;

import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.obeonetwork.m2doc.parser.TemplateValidationMessage;

import org.obeonetwork.m2doc.provider.IProvider;

import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Provider Client</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl#getStyleRun <em>Style Run</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl#getRuns <em>Runs</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl#getClosingRuns <em>Closing Runs</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl#getValidationMessages <em>Validation Messages</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl#getOptionValueMap <em>Option Value Map</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl#getProvider <em>Provider</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractProviderClientImpl extends MinimalEObjectImpl.Container implements AbstractProviderClient {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The default value of the '{@link #getStyleRun() <em>Style Run</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyleRun()
     * @generated
     * @ordered
     */
    protected static final XWPFRun STYLE_RUN_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getStyleRun() <em>Style Run</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getStyleRun()
     * @generated
     * @ordered
     */
    protected XWPFRun styleRun = STYLE_RUN_EDEFAULT;

    /**
     * The cached value of the '{@link #getRuns() <em>Runs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRuns()
     * @generated
     * @ordered
     */
    protected EList<XWPFRun> runs;

    /**
     * The cached value of the '{@link #getClosingRuns() <em>Closing Runs</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getClosingRuns()
     * @generated
     * @ordered
     */
    protected EList<XWPFRun> closingRuns;

    /**
     * The cached value of the '{@link #getValidationMessages() <em>Validation Messages</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getValidationMessages()
     * @generated
     * @ordered
     */
    protected EList<TemplateValidationMessage> validationMessages;

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
    protected AbstractProviderClientImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return TemplatePackage.Literals.ABSTRACT_PROVIDER_CLIENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XWPFRun getStyleRun() {
        return styleRun;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStyleRun(XWPFRun newStyleRun) {
        XWPFRun oldStyleRun = styleRun;
        styleRun = newStyleRun;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_PROVIDER_CLIENT__STYLE_RUN, oldStyleRun, styleRun));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<XWPFRun> getRuns() {
        if (runs == null) {
            runs = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.ABSTRACT_PROVIDER_CLIENT__RUNS);
        }
        return runs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<XWPFRun> getClosingRuns() {
        if (closingRuns == null) {
            closingRuns = new EDataTypeUniqueEList<XWPFRun>(XWPFRun.class, this, TemplatePackage.ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS);
        }
        return closingRuns;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<TemplateValidationMessage> getValidationMessages() {
        if (validationMessages == null) {
            validationMessages = new EDataTypeUniqueEList<TemplateValidationMessage>(TemplateValidationMessage.class, this, TemplatePackage.ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES);
        }
        return validationMessages;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EMap<String, Object> getOptionValueMap() {
        if (optionValueMap == null) {
            optionValueMap = new EcoreEMap<String,Object>(TemplatePackage.Literals.OPTION_VALUE_MAP, OptionValueMapImpl.class, this, TemplatePackage.ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP);
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.ABSTRACT_PROVIDER_CLIENT__PROVIDER, oldProvider, provider));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP:
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
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__STYLE_RUN:
                return getStyleRun();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__RUNS:
                return getRuns();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS:
                return getClosingRuns();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES:
                return getValidationMessages();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP:
                if (coreType) return getOptionValueMap();
                else return getOptionValueMap().map();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__PROVIDER:
                return getProvider();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__STYLE_RUN:
                setStyleRun((XWPFRun)newValue);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__RUNS:
                getRuns().clear();
                getRuns().addAll((Collection<? extends XWPFRun>)newValue);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS:
                getClosingRuns().clear();
                getClosingRuns().addAll((Collection<? extends XWPFRun>)newValue);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                getValidationMessages().addAll((Collection<? extends TemplateValidationMessage>)newValue);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP:
                ((EStructuralFeature.Setting)getOptionValueMap()).set(newValue);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__PROVIDER:
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
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__STYLE_RUN:
                setStyleRun(STYLE_RUN_EDEFAULT);
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__RUNS:
                getRuns().clear();
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS:
                getClosingRuns().clear();
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES:
                getValidationMessages().clear();
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP:
                getOptionValueMap().clear();
                return;
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__PROVIDER:
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
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__STYLE_RUN:
                return STYLE_RUN_EDEFAULT == null ? styleRun != null : !STYLE_RUN_EDEFAULT.equals(styleRun);
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__RUNS:
                return runs != null && !runs.isEmpty();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS:
                return closingRuns != null && !closingRuns.isEmpty();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES:
                return validationMessages != null && !validationMessages.isEmpty();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP:
                return optionValueMap != null && !optionValueMap.isEmpty();
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT__PROVIDER:
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
        result.append(" (styleRun: ");
        result.append(styleRun);
        result.append(", runs: ");
        result.append(runs);
        result.append(", closingRuns: ");
        result.append(closingRuns);
        result.append(", validationMessages: ");
        result.append(validationMessages);
        result.append(", provider: ");
        result.append(provider);
        result.append(')');
        return result.toString();
    }

} //AbstractProviderClientImpl
