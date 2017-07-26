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

import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cell</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.Cell#getTemplate <em>Template</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Cell#getTableCell <em>Table Cell</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getCell()
 * @model
 * @generated
 */
public interface Cell extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template</em>' containment reference.
     * @see #setTemplate(Template)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getCell_Template()
     * @model containment="true"
     * @generated
     */
    Template getTemplate();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Cell#getTemplate <em>Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template</em>' containment reference.
     * @see #getTemplate()
     * @generated
     */
    void setTemplate(Template value);

    /**
     * Returns the value of the '<em><b>Table Cell</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Cell</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Table Cell</em>' attribute.
     * @see #setTableCell(XWPFTableCell)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getCell_TableCell()
     * @model dataType="org.obeonetwork.m2doc.template.WTableCell"
     * @generated
     */
    XWPFTableCell getTableCell();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Cell#getTableCell <em>Table Cell</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Table Cell</em>' attribute.
     * @see #getTableCell()
     * @generated
     */
    void setTableCell(XWPFTableCell value);

} // Cell
