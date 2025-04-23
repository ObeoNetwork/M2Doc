/**
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 */
package org.obeonetwork.m2doc.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.Link#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Link#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getLink()
 * @model
 * @generated
 */
public interface Link extends Statement {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(AstResult)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getLink_Name()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult" required="true"
     * @generated
     */
    AstResult getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Link#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(AstResult value);

    /**
     * Returns the value of the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Text</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Text</em>' attribute.
     * @see #setText(AstResult)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getLink_Text()
     * @model dataType="org.obeonetwork.m2doc.template.AstResult" required="true"
     * @generated
     */
    AstResult getText();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Link#getText <em>Text</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Text</em>' attribute.
     * @see #getText()
     * @generated
     */
    void setText(AstResult value);

} // Link
