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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.Template#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Template#getParameters <em>Parameters</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Template#getBody <em>Body</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Template#getDocumentTemplate <em>Document Template</em>}</li>
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
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTemplate_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Template#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.template.Parameter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTemplate_Parameters()
     * @model containment="true" required="true"
     * @generated
     */
    EList<Parameter> getParameters();

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

    /**
     * Returns the value of the '<em><b>Document Template</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getTemplates <em>Templates</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Document Template</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Document Template</em>' container reference.
     * @see #setDocumentTemplate(DocumentTemplate)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTemplate_DocumentTemplate()
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getTemplates
     * @model opposite="templates" required="true" transient="false"
     * @generated
     */
    DocumentTemplate getDocumentTemplate();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Template#getDocumentTemplate <em>Document Template</em>}' container
     * reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Document Template</em>' container reference.
     * @see #getDocumentTemplate()
     * @generated
     */
    void setDocumentTemplate(DocumentTemplate value);

} // Template
