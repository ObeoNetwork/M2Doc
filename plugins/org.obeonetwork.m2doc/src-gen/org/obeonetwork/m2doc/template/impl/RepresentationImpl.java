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
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.TemplatePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Representation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.RepresentationImpl#getQuery <em>Query</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.RepresentationImpl#getRepresentationId <em>Representation Id</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.impl.RepresentationImpl#getRepresentationTitle <em>Representation Title</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RepresentationImpl extends AbstractImageImpl implements Representation {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

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
     * The default value of the '{@link #getRepresentationId() <em>Representation Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRepresentationId()
     * @generated
     * @ordered
     */
	protected static final String REPRESENTATION_ID_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getRepresentationId() <em>Representation Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRepresentationId()
     * @generated
     * @ordered
     */
	protected String representationId = REPRESENTATION_ID_EDEFAULT;

	/**
     * The default value of the '{@link #getRepresentationTitle() <em>Representation Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepresentationTitle()
     * @generated
     * @ordered
     */
    protected static final String REPRESENTATION_TITLE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRepresentationTitle() <em>Representation Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRepresentationTitle()
     * @generated
     * @ordered
     */
    protected String representationTitle = REPRESENTATION_TITLE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected RepresentationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return TemplatePackage.Literals.REPRESENTATION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REPRESENTATION__QUERY, oldQuery, query));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getRepresentationId() {
        return representationId;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRepresentationId(String newRepresentationId) {
        String oldRepresentationId = representationId;
        representationId = newRepresentationId;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REPRESENTATION__REPRESENTATION_ID, oldRepresentationId, representationId));
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRepresentationTitle() {
        return representationTitle;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRepresentationTitle(String newRepresentationTitle) {
        String oldRepresentationTitle = representationTitle;
        representationTitle = newRepresentationTitle;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, TemplatePackage.REPRESENTATION__REPRESENTATION_TITLE, oldRepresentationTitle, representationTitle));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case TemplatePackage.REPRESENTATION__QUERY:
                return getQuery();
            case TemplatePackage.REPRESENTATION__REPRESENTATION_ID:
                return getRepresentationId();
            case TemplatePackage.REPRESENTATION__REPRESENTATION_TITLE:
                return getRepresentationTitle();
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
            case TemplatePackage.REPRESENTATION__QUERY:
                setQuery((AstResult)newValue);
                return;
            case TemplatePackage.REPRESENTATION__REPRESENTATION_ID:
                setRepresentationId((String)newValue);
                return;
            case TemplatePackage.REPRESENTATION__REPRESENTATION_TITLE:
                setRepresentationTitle((String)newValue);
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
            case TemplatePackage.REPRESENTATION__QUERY:
                setQuery(QUERY_EDEFAULT);
                return;
            case TemplatePackage.REPRESENTATION__REPRESENTATION_ID:
                setRepresentationId(REPRESENTATION_ID_EDEFAULT);
                return;
            case TemplatePackage.REPRESENTATION__REPRESENTATION_TITLE:
                setRepresentationTitle(REPRESENTATION_TITLE_EDEFAULT);
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
            case TemplatePackage.REPRESENTATION__QUERY:
                return QUERY_EDEFAULT == null ? query != null : !QUERY_EDEFAULT.equals(query);
            case TemplatePackage.REPRESENTATION__REPRESENTATION_ID:
                return REPRESENTATION_ID_EDEFAULT == null ? representationId != null : !REPRESENTATION_ID_EDEFAULT.equals(representationId);
            case TemplatePackage.REPRESENTATION__REPRESENTATION_TITLE:
                return REPRESENTATION_TITLE_EDEFAULT == null ? representationTitle != null : !REPRESENTATION_TITLE_EDEFAULT.equals(representationTitle);
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
        result.append(", representationId: ");
        result.append(representationId);
        result.append(", representationTitle: ");
        result.append(representationTitle);
        result.append(')');
        return result.toString();
    }

} //RepresentationImpl
