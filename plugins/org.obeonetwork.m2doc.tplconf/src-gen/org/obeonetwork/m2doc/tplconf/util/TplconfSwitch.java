/**
 */
package org.obeonetwork.m2doc.tplconf.util;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.obeonetwork.m2doc.tplconf.*;

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
 * @see org.obeonetwork.m2doc.tplconf.TplconfPackage
 * @generated
 */
public class TplconfSwitch<T> extends Switch<T> {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static TplconfPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TplconfSwitch() {
        if (modelPackage == null) {
            modelPackage = TplconfPackage.eINSTANCE;
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
            case TplconfPackage.TEMPLATE_CONFIG: {
                TemplateConfig templateConfig = (TemplateConfig) theEObject;
                T result = caseTemplateConfig(templateConfig);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TplconfPackage.EPACKAGE_MAPPING: {
                EPackageMapping ePackageMapping = (EPackageMapping) theEObject;
                T result = caseEPackageMapping(ePackageMapping);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TplconfPackage.TEMPLATE_VARIABLE: {
                TemplateVariable templateVariable = (TemplateVariable) theEObject;
                T result = caseTemplateVariable(templateVariable);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TplconfPackage.TEMPLATE_TYPE: {
                TemplateType templateType = (TemplateType) theEObject;
                T result = caseTemplateType(templateType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TplconfPackage.SCALAR_TYPE: {
                ScalarType scalarType = (ScalarType) theEObject;
                T result = caseScalarType(scalarType);
                if (result == null)
                    result = caseTemplateType(scalarType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TplconfPackage.STRUCTURED_TYPE: {
                StructuredType structuredType = (StructuredType) theEObject;
                T result = caseStructuredType(structuredType);
                if (result == null)
                    result = caseTemplateType(structuredType);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TplconfPackage.STRING_TO_TYPE_MAP_ENTRY: {
                @SuppressWarnings("unchecked")
                Map.Entry<String, TemplateType> stringToTypeMapEntry = (Map.Entry<String, TemplateType>) theEObject;
                T result = caseStringToTypeMapEntry(stringToTypeMapEntry);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EPackage Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EPackage Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEPackageMapping(EPackageMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Template Variable</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemplateVariable(TemplateVariable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Template Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemplateType(TemplateType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Scalar Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Scalar Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseScalarType(ScalarType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Structured Type</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Structured Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStructuredType(StructuredType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Template Config</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template Config</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemplateConfig(TemplateConfig object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>String To Type Map Entry</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String To Type Map Entry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStringToTypeMapEntry(Map.Entry<String, TemplateType> object) {
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

} // TplconfSwitch
