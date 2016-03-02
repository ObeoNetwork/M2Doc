/**
 */
package org.eclipse.gendoc2.template;

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
 *   <li>{@link org.eclipse.gendoc2.template.Table#getRows <em>Rows</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Table#getTable <em>Table</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getTable()
 * @model
 * @generated
 */
public interface Table extends AbstractConstruct {
	/**
	 * Returns the value of the '<em><b>Rows</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.gendoc2.template.Row}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rows</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rows</em>' containment reference list.
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getTable_Rows()
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
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getTable_Table()
	 * @model dataType="org.eclipse.gendoc2.template.WTable"
	 * @generated
	 */
	XWPFTable getTable();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Table#getTable <em>Table</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Table</em>' attribute.
	 * @see #getTable()
	 * @generated
	 */
	void setTable(XWPFTable value);

} // Table
