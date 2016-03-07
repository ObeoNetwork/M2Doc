/**
 */
package org.obeonetwork.wgen.template;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Merge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.obeonetwork.wgen.template.TableMerge#getLegend <em>Legend</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.wgen.template.TemplatePackage#getTableMerge()
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
	 * @see org.obeonetwork.wgen.template.TemplatePackage#getTableMerge_Legend()
	 * @model
	 * @generated
	 */
	String getLegend();

	/**
	 * Sets the value of the '{@link org.obeonetwork.wgen.template.TableMerge#getLegend <em>Legend</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Legend</em>' attribute.
	 * @see #getLegend()
	 * @generated
	 */
	void setLegend(String value);

} // TableMerge
