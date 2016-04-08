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

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.m2doc.template.Table#getRows <em>Rows</em>}</li>
 *   <li>{@link org.obeonetwork.m2doc.template.Table#getTable <em>Table</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.template.TemplatePackage#getTable()
 * @model
 * @generated
 */
public interface Table extends AbstractConstruct {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * Returns the value of the '<em><b>Rows</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.template.Row}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Rows</em>' containment reference list.
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTable_Rows()
     * @model containment="true"
     * @generated
     */
	EList<Row> getRows();

	/**
     * Returns the value of the '<em><b>Table</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Table</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Table</em>' attribute.
     * @see #setTable(XWPFTable)
     * @see org.obeonetwork.m2doc.template.TemplatePackage#getTable_Table()
     * @model dataType="org.obeonetwork.m2doc.template.WTable"
     * @generated
     */
	XWPFTable getTable();

	/**
     * Sets the value of the '{@link org.obeonetwork.m2doc.template.Table#getTable <em>Table</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table</em>' attribute.
     * @see #getTable()
     * @generated
     */
	void setTable(XWPFTable value);

} // Table
