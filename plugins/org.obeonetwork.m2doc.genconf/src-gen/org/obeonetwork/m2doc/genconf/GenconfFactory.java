/**
 */
package org.obeonetwork.m2doc.genconf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage
 * @generated
 */
public interface GenconfFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    GenconfFactory eINSTANCE = org.obeonetwork.m2doc.genconf.impl.GenconfFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Generation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Generation</em>'.
     * @generated
     */
    Generation createGeneration();

    /**
     * Returns a new object of class '<em>Model Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Model Definition</em>'.
     * @generated
     */
    ModelDefinition createModelDefinition();

    /**
     * Returns a new object of class '<em>Model Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Model Sequence Definition</em>'.
     * @generated
     */
    ModelSequenceDefinition createModelSequenceDefinition();

    /**
     * Returns a new object of class '<em>Model Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Model Ordered Set Definition</em>'.
     * @generated
     */
    ModelOrderedSetDefinition createModelOrderedSetDefinition();

    /**
     * Returns a new object of class '<em>String Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>String Definition</em>'.
     * @generated
     */
    StringDefinition createStringDefinition();

    /**
     * Returns a new object of class '<em>String Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>String Sequence Definition</em>'.
     * @generated
     */
    StringSequenceDefinition createStringSequenceDefinition();

    /**
     * Returns a new object of class '<em>String Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>String Ordered Set Definition</em>'.
     * @generated
     */
    StringOrderedSetDefinition createStringOrderedSetDefinition();

    /**
     * Returns a new object of class '<em>Integer Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Integer Definition</em>'.
     * @generated
     */
    IntegerDefinition createIntegerDefinition();

    /**
     * Returns a new object of class '<em>Integer Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Integer Sequence Definition</em>'.
     * @generated
     */
    IntegerSequenceDefinition createIntegerSequenceDefinition();

    /**
     * Returns a new object of class '<em>Integer Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Integer Ordered Set Definition</em>'.
     * @generated
     */
    IntegerOrderedSetDefinition createIntegerOrderedSetDefinition();

    /**
     * Returns a new object of class '<em>Real Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Real Definition</em>'.
     * @generated
     */
    RealDefinition createRealDefinition();

    /**
     * Returns a new object of class '<em>Real Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Real Sequence Definition</em>'.
     * @generated
     */
    RealSequenceDefinition createRealSequenceDefinition();

    /**
     * Returns a new object of class '<em>Real Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Real Ordered Set Definition</em>'.
     * @generated
     */
    RealOrderedSetDefinition createRealOrderedSetDefinition();

    /**
     * Returns a new object of class '<em>Boolean Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Boolean Definition</em>'.
     * @generated
     */
    BooleanDefinition createBooleanDefinition();

    /**
     * Returns a new object of class '<em>Boolean Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Boolean Sequence Definition</em>'.
     * @generated
     */
    BooleanSequenceDefinition createBooleanSequenceDefinition();

    /**
     * Returns a new object of class '<em>Boolean Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Boolean Ordered Set Definition</em>'.
     * @generated
     */
    BooleanOrderedSetDefinition createBooleanOrderedSetDefinition();

    /**
     * Returns a new object of class '<em>Option</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Option</em>'.
     * @generated
     */
    Option createOption();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    GenconfPackage getGenconfPackage();

} // GenconfFactory
