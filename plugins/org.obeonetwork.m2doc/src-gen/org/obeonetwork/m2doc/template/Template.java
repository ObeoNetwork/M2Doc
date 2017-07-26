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
package org.obeonetwork.m2doc.template;

import org.apache.poi.xwpf.usermodel.IBody;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.Template#getXWPFBody <em>XWPF Body</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Template#getBody <em>Body</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getTemplate()
 * @model
 * @generated
 */
public interface Template extends IConstruct {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>XWPF Body</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>XWPF Body</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>XWPF Body</em>' attribute.
     * @see #setXWPFBody(IBody)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTemplate_XWPFBody()
     * @model dataType="org.obeonetwork.m2doc.template.Body"
     * @generated
     */
    IBody getXWPFBody();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Template#getXWPFBody <em>XWPF Body</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>XWPF Body</em>' attribute.
     * @see #getXWPFBody()
     * @generated
     */
    void setXWPFBody(IBody value);

    /**
     * Returns the value of the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Body</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Body</em>' containment reference.
     * @see #setBody(Block)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTemplate_Body()
     * @model containment="true" required="true"
     *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='The {@link Block} of {@link Statement}.'"
     * @generated
     */
    Block getBody();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Template#getBody <em>Body</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Body</em>' containment reference.
     * @see #getBody()
     * @generated
     */
    void setBody(Block value);

} // Template
