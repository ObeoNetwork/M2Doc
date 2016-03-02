/**
 */
package org.eclipse.gendoc2.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Representation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.Representation#getQuery <em>Query</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Representation#getRepresentationId <em>Representation Id</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getRepresentation()
 * @model
 * @generated
 */
public interface Representation extends AbstractConstruct {
	/**
	 * Returns the value of the '<em><b>Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query</em>' attribute.
	 * @see #setQuery(AstResult)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getRepresentation_Query()
	 * @model dataType="org.eclipse.gendoc2.template.AstResult"
	 * @generated
	 */
	AstResult getQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Representation#getQuery <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' attribute.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(AstResult value);

	/**
	 * Returns the value of the '<em><b>Representation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Representation Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Representation Id</em>' attribute.
	 * @see #setRepresentationId(String)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getRepresentation_RepresentationId()
	 * @model
	 * @generated
	 */
	String getRepresentationId();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Representation#getRepresentationId <em>Representation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Representation Id</em>' attribute.
	 * @see #getRepresentationId()
	 * @generated
	 */
	void setRepresentationId(String value);

} // Representation
