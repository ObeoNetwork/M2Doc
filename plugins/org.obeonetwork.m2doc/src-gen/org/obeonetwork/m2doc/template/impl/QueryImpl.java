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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.QueryBehavior;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.QueryImpl#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.QueryImpl#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @generated
 */
public class QueryImpl extends AbstractConstructImpl implements Query {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * The default value of the '{@link #getBehavior() <em>Behavior</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBehavior()
     * @generated
     * @ordered
     */
	protected static final QueryBehavior BEHAVIOR_EDEFAULT = QueryBehavior.TEXT;

	/**
     * The cached value of the '{@link #getBehavior() <em>Behavior</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getBehavior()
     * @generated
     * @ordered
     */
	protected QueryBehavior behavior = BEHAVIOR_EDEFAULT;

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
	protected QueryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return TemplatePackage.Literals.QUERY;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.QUERY__QUERY, oldQuery, query));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryBehavior getBehavior() {
        return behavior;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setBehavior(QueryBehavior newBehavior) {
        QueryBehavior oldBehavior = behavior;
        behavior = newBehavior == null ? BEHAVIOR_EDEFAULT : newBehavior;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.QUERY__BEHAVIOR, oldBehavior, behavior));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.QUERY__BEHAVIOR:
                return getBehavior();
            case TemplatePackage.QUERY__QUERY:
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
            case TemplatePackage.QUERY__BEHAVIOR:
                setBehavior((QueryBehavior)newValue);
                return;
            case TemplatePackage.QUERY__QUERY:
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
            case TemplatePackage.QUERY__BEHAVIOR:
                setBehavior(BEHAVIOR_EDEFAULT);
                return;
            case TemplatePackage.QUERY__QUERY:
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
            case TemplatePackage.QUERY__BEHAVIOR:
                return behavior != BEHAVIOR_EDEFAULT;
            case TemplatePackage.QUERY__QUERY:
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
        result.append(" (behavior: ");
        result.append(behavior);
        result.append(", query: ");
        result.append(query);
        result.append(')');
        return result.toString();
    }

} //QueryImpl
