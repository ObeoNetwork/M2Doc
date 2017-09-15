/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#getName <em>Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#getTemplateFileName <em>Template File Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#getResultFileName <em>Result File Name</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#isTimeStamped <em>Time Stamped</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#isRefreshRepresentations <em>Refresh Representations</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#getDefinitions <em>Definitions</em>}</li>
 * <li>{@link org.obeonetwork.m2doc.genconf.Generation#getOptions <em>Options</em>}</li>
 * </ul>
 *
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration()
 * @model
 * @generated
 */
public interface Generation extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Generation#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Template File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Template File Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Template File Name</em>' attribute.
     * @see #setTemplateFileName(String)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_TemplateFileName()
     * @model
     * @generated
     */
    String getTemplateFileName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Generation#getTemplateFileName <em>Template File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Template File Name</em>' attribute.
     * @see #getTemplateFileName()
     * @generated
     */
    void setTemplateFileName(String value);

    /**
     * Returns the value of the '<em><b>Result File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Result File Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Result File Name</em>' attribute.
     * @see #setResultFileName(String)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_ResultFileName()
     * @model
     * @generated
     */
    String getResultFileName();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Generation#getResultFileName <em>Result File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Result File Name</em>' attribute.
     * @see #getResultFileName()
     * @generated
     */
    void setResultFileName(String value);

    /**
     * Returns the value of the '<em><b>Time Stamped</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Time Stamped</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Time Stamped</em>' attribute.
     * @see #setTimeStamped(boolean)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_TimeStamped()
     * @model default="true"
     * @generated
     */
    boolean isTimeStamped();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Generation#isTimeStamped <em>Time Stamped</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Time Stamped</em>' attribute.
     * @see #isTimeStamped()
     * @generated
     */
    void setTimeStamped(boolean value);

    /**
     * Returns the value of the '<em><b>Refresh Representations</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refresh Representations</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Refresh Representations</em>' attribute.
     * @see #setRefreshRepresentations(boolean)
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_RefreshRepresentations()
     * @model default="false"
     * @generated
     */
    boolean isRefreshRepresentations();

    /**
     * Sets the value of the '{@link org.obeonetwork.m2doc.genconf.Generation#isRefreshRepresentations <em>Refresh Representations</em>}'
     * attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Refresh Representations</em>' attribute.
     * @see #isRefreshRepresentations()
     * @generated
     */
    void setRefreshRepresentations(boolean value);

    /**
     * Returns the value of the '<em><b>Definitions</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.genconf.Definition}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Definitions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Definitions</em>' containment reference list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_Definitions()
     * @model containment="true"
     * @generated
     */
    EList<Definition> getDefinitions();

    /**
     * Returns the value of the '<em><b>Options</b></em>' containment reference list.
     * The list contents are of type {@link org.obeonetwork.m2doc.genconf.Option}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Options</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Options</em>' containment reference list.
     * @see org.obeonetwork.m2doc.genconf.GenconfPackage#getGeneration_Options()
     * @model containment="true"
     * @generated
     */
    EList<Option> getOptions();

} // Generation
