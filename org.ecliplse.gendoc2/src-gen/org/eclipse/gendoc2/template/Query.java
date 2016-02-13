/**
 */
package org.eclipse.gendoc2.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.Query#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Query#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getQuery()
 * @model
 * @generated
 */
public interface Query extends AbstractConstruct {
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
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getQuery_Query()
	 * @model dataType="org.eclipse.gendoc2.template.AstResult"
	 * @generated
	 */
	AstResult getQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Query#getQuery <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' attribute.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(AstResult value);

	/**
	 * Returns the value of the '<em><b>Behavior</b></em>' attribute.
	 * The default value is <code>"TEXT"</code>.
	 * The literals are from the enumeration {@link org.eclipse.gendoc2.template.QueryBehavior}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior</em>' attribute.
	 * @see org.eclipse.gendoc2.template.QueryBehavior
	 * @see #setBehavior(QueryBehavior)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getQuery_Behavior()
	 * @model default="TEXT"
	 * @generated
	 */
	QueryBehavior getBehavior();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Query#getBehavior <em>Behavior</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavior</em>' attribute.
	 * @see org.eclipse.gendoc2.template.QueryBehavior
	 * @see #getBehavior()
	 * @generated
	 */
	void setBehavior(QueryBehavior value);

} // Query
