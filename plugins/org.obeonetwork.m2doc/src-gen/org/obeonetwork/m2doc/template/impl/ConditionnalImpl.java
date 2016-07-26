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

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditionnal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.ConditionnalImpl#getAlternative <em>Alternative</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.ConditionnalImpl#getElse <em>Else</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.ConditionnalImpl#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConditionnalImpl extends CompoundImpl implements Conditionnal {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * The cached value of the '{@link #getAlternative() <em>Alternative</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAlternative()
     * @generated
     * @ordered
     */
	protected Conditionnal alternative;

	/**
     * The cached value of the '{@link #getElse() <em>Else</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getElse()
     * @generated
     * @ordered
     */
	protected Default else_;

	/**
     * The default value of the '{@link #getQuery() <em>Query</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQuery()
     * @generated
     * @ordered
     */
	protected static final AstResult QUERY_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getQuery() <em>Query</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQuery()
     * @generated
     * @ordered
     */
	protected AstResult query = QUERY_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ConditionnalImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return TemplatePackage.Literals.CONDITIONNAL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Conditionnal getAlternative() {
        return alternative;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAlternative(Conditionnal newAlternative, NotificationChain msgs) {
        Conditionnal oldAlternative = alternative;
        alternative = newAlternative;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.CONDITIONNAL__ALTERNATIVE, oldAlternative, newAlternative);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAlternative(Conditionnal newAlternative) {
        if (newAlternative != alternative) {
            NotificationChain msgs = null;
            if (alternative != null)
                msgs = ((InternalEObject)alternative).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.CONDITIONNAL__ALTERNATIVE, null, msgs);
            if (newAlternative != null)
                msgs = ((InternalEObject)newAlternative).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.CONDITIONNAL__ALTERNATIVE, null, msgs);
            msgs = basicSetAlternative(newAlternative, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.CONDITIONNAL__ALTERNATIVE, newAlternative, newAlternative));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public AstResult getQuery() {
        return query;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setQuery(AstResult newQuery) {
        AstResult oldQuery = query;
        query = newQuery;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.CONDITIONNAL__QUERY, oldQuery, query));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Default getElse() {
        return else_;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetElse(Default newElse, NotificationChain msgs) {
        Default oldElse = else_;
        else_ = newElse;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TemplatePackage.CONDITIONNAL__ELSE, oldElse, newElse);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setElse(Default newElse) {
        if (newElse != else_) {
            NotificationChain msgs = null;
            if (else_ != null)
                msgs = ((InternalEObject)else_).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.CONDITIONNAL__ELSE, null, msgs);
            if (newElse != null)
                msgs = ((InternalEObject)newElse).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TemplatePackage.CONDITIONNAL__ELSE, null, msgs);
            msgs = basicSetElse(newElse, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.CONDITIONNAL__ELSE, newElse, newElse));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case TemplatePackage.CONDITIONNAL__ALTERNATIVE:
                return basicSetAlternative(null, msgs);
            case TemplatePackage.CONDITIONNAL__ELSE:
                return basicSetElse(null, msgs);
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
            case TemplatePackage.CONDITIONNAL__ALTERNATIVE:
                return getAlternative();
            case TemplatePackage.CONDITIONNAL__ELSE:
                return getElse();
            case TemplatePackage.CONDITIONNAL__QUERY:
                return getQuery();
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
            case TemplatePackage.CONDITIONNAL__ALTERNATIVE:
                setAlternative((Conditionnal)newValue);
                return;
            case TemplatePackage.CONDITIONNAL__ELSE:
                setElse((Default)newValue);
                return;
            case TemplatePackage.CONDITIONNAL__QUERY:
                setQuery((AstResult)newValue);
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
            case TemplatePackage.CONDITIONNAL__ALTERNATIVE:
                setAlternative((Conditionnal)null);
                return;
            case TemplatePackage.CONDITIONNAL__ELSE:
                setElse((Default)null);
                return;
            case TemplatePackage.CONDITIONNAL__QUERY:
                setQuery(QUERY_EDEFAULT);
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
            case TemplatePackage.CONDITIONNAL__ALTERNATIVE:
                return alternative != null;
            case TemplatePackage.CONDITIONNAL__ELSE:
                return else_ != null;
            case TemplatePackage.CONDITIONNAL__QUERY:
                return QUERY_EDEFAULT == null ? query != null : !QUERY_EDEFAULT.equals(query);
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
        result.append(" (query: ");
        result.append(query);
        result.append(')');
        return result.toString();
    }

} //ConditionnalImpl
