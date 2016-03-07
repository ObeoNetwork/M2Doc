/**
 */
package org.eclipse.gendoc2.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repetition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.Repetition#getIterationVar <em>Iteration Var</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Repetition#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getRepetition()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore documentation='pour chaque valeur v dans le r\351sultat de l\'\351valuation de query, \r\n- un nouveau contexte est cr\351e en ajoutant la d\351finition \'var =v\' au contexte courant\r\n- le r\351sultat du traitement du corps runs est ins\351r\351 dans le document \r\nles balises {gd:for} et {gd:endfor} sont supprim\351es.\r\nSi la balise {gd:for} est imm\351diatement suivie d\'un retour chariot alors l\'ensemble du paragraphe la contenant est supprim\351e du r\351sultat sinon, le paragraphe duquel on a supprim\351 la balise est ins\351r\351 dans le r\351sultat. Le m\352me traitement est appliqu\351 \340 la balise {gd:endfor}' syntax='{gd:for var | query} body {gd:endfor}'"
 * @generated
 */
public interface Repetition extends Compound {
	/**
	 * Returns the value of the '<em><b>Iteration Var</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Iteration Var</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Iteration Var</em>' attribute.
	 * @see #setIterationVar(String)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getRepetition_IterationVar()
	 * @model
	 * @generated
	 */
	String getIterationVar();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Repetition#getIterationVar <em>Iteration Var</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iteration Var</em>' attribute.
	 * @see #getIterationVar()
	 * @generated
	 */
	void setIterationVar(String value);

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
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getRepetition_Query()
	 * @model dataType="org.eclipse.gendoc2.template.AstResult"
	 * @generated
	 */
	AstResult getQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Repetition#getQuery <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' attribute.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(AstResult value);

} // Repetition
