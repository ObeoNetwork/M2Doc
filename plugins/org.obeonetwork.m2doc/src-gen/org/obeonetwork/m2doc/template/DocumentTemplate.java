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

import java.io.Closeable;
import java.io.InputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Template</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getHeaders <em>Headers</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getFooters <em>Footers</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getBody <em>Body</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getInputStream <em>Input Stream</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getOpcPackage <em>Opc Package</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getDocument <em>Document</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.DocumentTemplate#getTemplates <em>Templates</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate()
 * @model
 * @generated NOT
 */
public interface DocumentTemplate extends IGenerateable, Closeable {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Headers</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.template.Block}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Headers</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Headers</em>' containment reference list.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_Headers()
     * @model containment="true"
     * @generated
     */
    EList<Block> getHeaders();

    /**
     * Returns the value of the '<em><b>Footers</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.template.Block}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Footers</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Footers</em>' containment reference list.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_Footers()
     * @model containment="true"
     * @generated
     */
    EList<Block> getFooters();

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
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_Body()
     * @model containment="true" required="true"
     * @generated
     */
    Block getBody();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getBody <em>Body</em>}' containment reference.
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
     * Returns the value of the '<em><b>Input Stream</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Stream</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Input Stream</em>' attribute.
     * @see #setInputStream(InputStream)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_InputStream()
     * @model dataType="org.obeonetwork.m2doc.template.InputStream" required="true"
     * @generated
     */
    InputStream getInputStream();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getInputStream <em>Input Stream</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Input Stream</em>' attribute.
     * @see #getInputStream()
     * @generated
     */
    void setInputStream(InputStream value);

    /**
     * Returns the value of the '<em><b>Opc Package</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Opc Package</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Opc Package</em>' attribute.
     * @see #setOpcPackage(OPCPackage)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_OpcPackage()
     * @model dataType="org.obeonetwork.m2doc.template.OPCPackage" required="true"
     * @generated
     */
    OPCPackage getOpcPackage();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getOpcPackage <em>Opc Package</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Opc Package</em>' attribute.
     * @see #getOpcPackage()
     * @generated
     */
    void setOpcPackage(OPCPackage value);

    /**
     * Returns the value of the '<em><b>Document</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Document</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Document</em>' attribute.
     * @see #setDocument(XWPFDocument)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_Document()
     * @model dataType="org.obeonetwork.m2doc.template.Document" required="true"
     * @generated
     */
    XWPFDocument getDocument();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getDocument <em>Document</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Document</em>' attribute.
     * @see #getDocument()
     * @generated
     */
    void setDocument(XWPFDocument value);

    /**
     * Returns the value of the '<em><b>Templates</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.template.Template}.
     * It is bidirectional and its opposite is '{@link org.obeonetwork.m2doc.template.Template#getDocumentTemplate <em>Document Template</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Templates</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Templates</em>' containment reference list.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getDocumentTemplate_Templates()
     * @see org.obeonetwork.m2doc.template.Template#getDocumentTemplate
     * @model opposite="documentTemplate" containment="true"
     * @generated
     */
    EList<Template> getTemplates();

} // DocumentTemplate
