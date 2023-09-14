/**
 */
package org.obeonetwork.m2doc.genconf.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition;
import org.obeonetwork.m2doc.genconf.Definition;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.IntegerOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.IntegerSequenceDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.ModelOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.ModelSequenceDefinition;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.RealOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.RealSequenceDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;
import org.obeonetwork.m2doc.genconf.StringOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.StringSequenceDefinition;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.genconf.GenconfPackage
 * @generated
 */
public class GenconfSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static GenconfPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public GenconfSwitch() {
        if (modelPackage == null) {
            modelPackage = GenconfPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param ePackage
     *            the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case GenconfPackage.GENERATION: {
                Generation generation = (Generation) theEObject;
                T result = caseGeneration(generation);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.DEFINITION: {
                Definition definition = (Definition) theEObject;
                T result = caseDefinition(definition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.MODEL_DEFINITION: {
                ModelDefinition modelDefinition = (ModelDefinition) theEObject;
                T result = caseModelDefinition(modelDefinition);
                if (result == null)
                    result = caseDefinition(modelDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.MODEL_SEQUENCE_DEFINITION: {
                ModelSequenceDefinition modelSequenceDefinition = (ModelSequenceDefinition) theEObject;
                T result = caseModelSequenceDefinition(modelSequenceDefinition);
                if (result == null)
                    result = caseDefinition(modelSequenceDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.MODEL_ORDERED_SET_DEFINITION: {
                ModelOrderedSetDefinition modelOrderedSetDefinition = (ModelOrderedSetDefinition) theEObject;
                T result = caseModelOrderedSetDefinition(modelOrderedSetDefinition);
                if (result == null)
                    result = caseDefinition(modelOrderedSetDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.STRING_DEFINITION: {
                StringDefinition stringDefinition = (StringDefinition) theEObject;
                T result = caseStringDefinition(stringDefinition);
                if (result == null)
                    result = caseDefinition(stringDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.STRING_SEQUENCE_DEFINITION: {
                StringSequenceDefinition stringSequenceDefinition = (StringSequenceDefinition) theEObject;
                T result = caseStringSequenceDefinition(stringSequenceDefinition);
                if (result == null)
                    result = caseDefinition(stringSequenceDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.STRING_ORDERED_SET_DEFINITION: {
                StringOrderedSetDefinition stringOrderedSetDefinition = (StringOrderedSetDefinition) theEObject;
                T result = caseStringOrderedSetDefinition(stringOrderedSetDefinition);
                if (result == null)
                    result = caseDefinition(stringOrderedSetDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.INTEGER_DEFINITION: {
                IntegerDefinition integerDefinition = (IntegerDefinition) theEObject;
                T result = caseIntegerDefinition(integerDefinition);
                if (result == null)
                    result = caseDefinition(integerDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.INTEGER_SEQUENCE_DEFINITION: {
                IntegerSequenceDefinition integerSequenceDefinition = (IntegerSequenceDefinition) theEObject;
                T result = caseIntegerSequenceDefinition(integerSequenceDefinition);
                if (result == null)
                    result = caseDefinition(integerSequenceDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.INTEGER_ORDERED_SET_DEFINITION: {
                IntegerOrderedSetDefinition integerOrderedSetDefinition = (IntegerOrderedSetDefinition) theEObject;
                T result = caseIntegerOrderedSetDefinition(integerOrderedSetDefinition);
                if (result == null)
                    result = caseDefinition(integerOrderedSetDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.REAL_DEFINITION: {
                RealDefinition realDefinition = (RealDefinition) theEObject;
                T result = caseRealDefinition(realDefinition);
                if (result == null)
                    result = caseDefinition(realDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.REAL_SEQUENCE_DEFINITION: {
                RealSequenceDefinition realSequenceDefinition = (RealSequenceDefinition) theEObject;
                T result = caseRealSequenceDefinition(realSequenceDefinition);
                if (result == null)
                    result = caseDefinition(realSequenceDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.REAL_ORDERED_SET_DEFINITION: {
                RealOrderedSetDefinition realOrderedSetDefinition = (RealOrderedSetDefinition) theEObject;
                T result = caseRealOrderedSetDefinition(realOrderedSetDefinition);
                if (result == null)
                    result = caseDefinition(realOrderedSetDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.BOOLEAN_DEFINITION: {
                BooleanDefinition booleanDefinition = (BooleanDefinition) theEObject;
                T result = caseBooleanDefinition(booleanDefinition);
                if (result == null)
                    result = caseDefinition(booleanDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.BOOLEAN_SEQUENCE_DEFINITION: {
                BooleanSequenceDefinition booleanSequenceDefinition = (BooleanSequenceDefinition) theEObject;
                T result = caseBooleanSequenceDefinition(booleanSequenceDefinition);
                if (result == null)
                    result = caseDefinition(booleanSequenceDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.BOOLEAN_ORDERED_SET_DEFINITION: {
                BooleanOrderedSetDefinition booleanOrderedSetDefinition = (BooleanOrderedSetDefinition) theEObject;
                T result = caseBooleanOrderedSetDefinition(booleanOrderedSetDefinition);
                if (result == null)
                    result = caseDefinition(booleanOrderedSetDefinition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case GenconfPackage.OPTION: {
                Option option = (Option) theEObject;
                T result = caseOption(option);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Generation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Generation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGeneration(Generation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDefinition(Definition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelDefinition(ModelDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Sequence Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelSequenceDefinition(ModelSequenceDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Model Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Model Ordered Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelOrderedSetDefinition(ModelOrderedSetDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringDefinition(StringDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Sequence Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringSequenceDefinition(StringSequenceDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Ordered Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringOrderedSetDefinition(StringOrderedSetDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntegerDefinition(IntegerDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Sequence Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntegerSequenceDefinition(IntegerSequenceDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Integer Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Ordered Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntegerOrderedSetDefinition(IntegerOrderedSetDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Real Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Real Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRealDefinition(RealDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Real Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Real Sequence Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRealSequenceDefinition(RealSequenceDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Real Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Real Ordered Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRealOrderedSetDefinition(RealOrderedSetDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Boolean Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBooleanDefinition(BooleanDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Boolean Sequence Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean Sequence Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBooleanSequenceDefinition(BooleanSequenceDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Boolean Ordered Set Definition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean Ordered Set Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBooleanOrderedSetDefinition(BooleanOrderedSetDefinition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Option</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Option</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOption(Option object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }

} // GenconfSwitch
