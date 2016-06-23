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
package org.obeonetwork.m2doc.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Representation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.Representation#getQuery <em>Query</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Representation#getRepresentationId <em>Representation Id</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Representation#getRepresentationTitle <em>Representation Title</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepresentation()
 * @model
 * @generated
 */
public interface Representation extends AbstractImage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * Returns the value of the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' attribute.
     * @see #setQuery(AstResult)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepresentation_Query()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult"
     * @generated
     */
	AstResult getQuery();

	/**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Representation#getQuery <em>Query</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' attribute.
     * @see #getQuery()
     * @generated
     */
	void setQuery(AstResult value);

	/**
     * Returns the value of the '<em><b>Representation Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Representation Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Representation Id</em>' attribute.
     * @see #setRepresentationId(String)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepresentation_RepresentationId()
     * @model
     * @generated
     */
	String getRepresentationId();

	/**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Representation#getRepresentationId <em>Representation Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Representation Id</em>' attribute.
     * @see #getRepresentationId()
     * @generated
     */
	void setRepresentationId(String value);

    /**
     * Returns the value of the '<em><b>Representation Title</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Representation Title</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Representation Title</em>' attribute.
     * @see #setRepresentationTitle(String)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRepresentation_RepresentationTitle()
     * @model
     * @generated
     */
    String getRepresentationTitle();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Representation#getRepresentationTitle <em>Representation Title</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Representation Title</em>' attribute.
     * @see #getRepresentationTitle()
     * @generated
     */
    void setRepresentationTitle(String value);

} // Representation
