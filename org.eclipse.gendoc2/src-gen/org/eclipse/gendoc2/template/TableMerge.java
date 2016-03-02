/**
 */
package org.eclipse.gendoc2.template;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Merge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.TableMerge#getLegend <em>Legend</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getTableMerge()
 * @model
 * @generated
 */
public interface TableMerge extends Compound {
	/**
	 * Returns the value of the '<em><b>Legend</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Legend</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Legend</em>' attribute.
	 * @see #setLegend(String)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getTableMerge_Legend()
	 * @model
	 * @generated
	 */
	String getLegend();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.TableMerge#getLegend <em>Legend</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Legend</em>' attribute.
	 * @see #getLegend()
	 * @generated
	 */
	void setLegend(String value);

} // TableMerge
