/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
/**
 */
package org.obeonetwork.m2doc.template.impl;

import org.apache.poi.xwpf.usermodel.IBody;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getTemplateName <em>Template Name</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.TemplateImpl#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TemplateImpl extends CompoundImpl implements Template {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * The default value of the '{@link #getTemplateName() <em>Template Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTemplateName()
     * @generated
     * @ordered
     */
	protected static final String TEMPLATE_NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getTemplateName() <em>Template Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTemplateName()
     * @generated
     * @ordered
     */
	protected String templateName = TEMPLATE_NAME_EDEFAULT;

	/**
     * The default value of the '{@link #getBody() <em>Body</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBody()
     * @generated
     * @ordered
     */
    protected static final IBody BODY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBody() <em>Body</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBody()
     * @generated
     * @ordered
     */
    protected IBody body = BODY_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected TemplateImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return TemplatePackage.Literals.TEMPLATE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getTemplateName() {
        return templateName;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setTemplateName(String newTemplateName) {
        String oldTemplateName = templateName;
        templateName = newTemplateName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__TEMPLATE_NAME, oldTemplateName, templateName));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IBody getBody() {
        return body;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setBody(IBody newBody) {
        IBody oldBody = body;
        body = newBody;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.TEMPLATE__BODY, oldBody, body));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
                return getTemplateName();
            case TemplatePackage.TEMPLATE__BODY:
                return getBody();
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
            case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
                setTemplateName((String)newValue);
                return;
            case TemplatePackage.TEMPLATE__BODY:
                setBody((IBody)newValue);
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
            case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
                setTemplateName(TEMPLATE_NAME_EDEFAULT);
                return;
            case TemplatePackage.TEMPLATE__BODY:
                setBody(BODY_EDEFAULT);
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
            case TemplatePackage.TEMPLATE__TEMPLATE_NAME:
                return TEMPLATE_NAME_EDEFAULT == null ? templateName != null : !TEMPLATE_NAME_EDEFAULT.equals(templateName);
            case TemplatePackage.TEMPLATE__BODY:
                return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
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
        result.append(" (templateName: ");
        result.append(templateName);
        result.append(", body: ");
        result.append(body);
        result.append(')');
        return result.toString();
    }

} //TemplateImpl
