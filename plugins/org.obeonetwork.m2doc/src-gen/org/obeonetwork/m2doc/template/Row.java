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

import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.template.Row#getCells <em>Cells</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.template.Row#getTableRow <em>Table Row</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getRow()
 * @model
 * @generated
 */
public interface Row extends EObject {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * Returns the value of the '<em><b>Cells</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.template.Cell}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cells</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Cells</em>' containment reference list.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRow_Cells()
     * @model containment="true"
     * @generated
     */
    EList<Cell> getCells();

    /**
     * Returns the value of the '<em><b>Table Row</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Row</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Table Row</em>' attribute.
     * @see #setTableRow(XWPFTableRow)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getRow_TableRow()
     * @model dataType="org.obeonetwork.m2doc.template.WTableRow"
     * @generated
     */
    XWPFTableRow getTableRow();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Row#getTableRow <em>Table Row</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Table Row</em>' attribute.
     * @see #getTableRow()
     * @generated
     */
    void setTableRow(XWPFTableRow value);

} // Row
