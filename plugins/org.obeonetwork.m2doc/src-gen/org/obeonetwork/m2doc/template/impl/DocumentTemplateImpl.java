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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getHeaders <em>Headers</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getFooters <em>Footers</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DocumentTemplateImpl extends MinimalEObjectImpl.Container implements DocumentTemplate {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * The cached value of the '{@link #getHeaders() <em>Headers</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getHeaders()
     * @generated
     * @ordered
     */
	protected EList<Template> headers;

	/**
     * The cached value of the '{@link #getFooters() <em>Footers</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getFooters()
     * @generated
     * @ordered
     */
	protected EList<Template> footers;

	/**
     * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBody()
     * @generated
     * @ordered
     */
	protected Template body;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected DocumentTemplateImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return TemplatePackage.Literals.DOCUMENT_TEMPLATE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Template> getHeaders() {
        if (headers == null) {
            headers = new EObjectContainmentEList<Template>(Template.class, this, TemplatePackage.DOCUMENT_TEMPLATE__HEADERS);
        }
        return headers;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<Template> getFooters() {
        if (footers == null) {
            footers = new EObjectContainmentEList<Template>(Template.class, this, TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS);
        }
        return footers;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Template getBody() {
        return body;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetBody(Template newBody, NotificationChain msgs) {
        Template oldBody = body;
        body = newBody;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__BODY, oldBody, newBody);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setBody(Template newBody) {
        if (newBody != body) {
            NotificationChain msgs = null;
            if (body != null)
                msgs = ((InternalEObject)body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.DOCUMENT_TEMPLATE__BODY, null, msgs);
            if (newBody != null)
                msgs = ((InternalEObject)newBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.DOCUMENT_TEMPLATE__BODY, null, msgs);
            msgs = basicSetBody(newBody, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.DOCUMENT_TEMPLATE__BODY, newBody, newBody));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                return ((InternalEList<?>)getHeaders()).basicRemove(otherEnd, msgs);
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                return ((InternalEList<?>)getFooters()).basicRemove(otherEnd, msgs);
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                return basicSetBody(null, msgs);
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
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                return getHeaders();
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                return getFooters();
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                return getBody();
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
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                getHeaders().clear();
                getHeaders().addAll((Collection<? extends Template>)newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                getFooters().clear();
                getFooters().addAll((Collection<? extends Template>)newValue);
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                setBody((Template)newValue);
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
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                getHeaders().clear();
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                getFooters().clear();
                return;
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                setBody((Template)null);
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
            case TemplatePackage.DOCUMENT_TEMPLATE__HEADERS:
                return headers != null && !headers.isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__FOOTERS:
                return footers != null && !footers.isEmpty();
            case TemplatePackage.DOCUMENT_TEMPLATE__BODY:
                return body != null;
        }
        return super.eIsSet(featureID);
    }

} //DocumentTemplateImpl
