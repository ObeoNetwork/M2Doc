/**
 */
package org.eclipse.gendoc2.template;

import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditionnal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.gendoc2.template.Conditionnal#getAlternative <em>Alternative</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Conditionnal#getElse <em>Else</em>}</li>
 *   <li>{@link org.eclipse.gendoc2.template.Conditionnal#getQuery <em>Query</em>}</li>
 * </ul>
 *
 * @see org.eclipse.gendoc2.template.TemplatePackage#getConditionnal()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='AlternativeExcludeElse' documentation='Si l\'expression expr est \351valu\351e \340 true dans le contexte courant alors l\'ensemble de la balise jusqu\'\340 la balise gd:endif comprisee est remplac\351e par le traitement de runs1 dans le contexte courant. Sinon, l\'ensemble des balises est remplac\351e par le traitement du premier run_n tel que l\'expression expr de la balise gd:elseif associ\351e est \351valu\351e \340 vrai. Si aucune telle balise n\'est pr\351sente ou si aucune d\'entre-elle porte une expression \351valu\351e \340 true, le r\351sultat du traitement de run_else, si la balise gd:else est pr\351sente, dans le contexte courant est ins\351r\351 \340 la place de l\'ensemble des balises' syntax='{gd:if expr} runs1 [{gd:elseif <expr>} runs_n]* [{gd:else} run_else]{gd:endif}'"
 * @generated
 */
public interface Conditionnal extends Compound {
	/**
	 * Returns the value of the '<em><b>Alternative</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alternative</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alternative</em>' containment reference.
	 * @see #setAlternative(Conditionnal)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getConditionnal_Alternative()
	 * @model containment="true"
	 * @generated
	 */
	Conditionnal getAlternative();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Conditionnal#getAlternative <em>Alternative</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alternative</em>' containment reference.
	 * @see #getAlternative()
	 * @generated
	 */
	void setAlternative(Conditionnal value);

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
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getConditionnal_Query()
	 * @model dataType="org.eclipse.gendoc2.template.AstResult"
	 * @generated
	 */
	AstResult getQuery();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Conditionnal#getQuery <em>Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query</em>' attribute.
	 * @see #getQuery()
	 * @generated
	 */
	void setQuery(AstResult value);

	/**
	 * Returns the value of the '<em><b>Else</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Else</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Else</em>' containment reference.
	 * @see #setElse(Default)
	 * @see org.eclipse.gendoc2.template.TemplatePackage#getConditionnal_Else()
	 * @model containment="true"
	 * @generated
	 */
	Default getElse();

	/**
	 * Sets the value of the '{@link org.eclipse.gendoc2.template.Conditionnal#getElse <em>Else</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Else</em>' containment reference.
	 * @see #getElse()
	 * @generated
	 */
	void setElse(Default value);

} // Conditionnal
