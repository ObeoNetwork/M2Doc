/**
 */
package org.obeonetwork.m2doc.genconf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
import org.obeonetwork.m2doc.genconf.GenconfPackage;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.IntegerDefinition;
import org.obeonetwork.m2doc.genconf.ModelDefinition;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.genconf.RealDefinition;
import org.obeonetwork.m2doc.genconf.StringDefinition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class GenconfFactoryImpl extends EFactoryImpl implements GenconfFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static GenconfFactory init() {
        try {
            GenconfFactory theGenconfFactory = (GenconfFactory) EPackage.Registry.INSTANCE
                    .getEFactory(GenconfPackage.eNS_URI);
            if (theGenconfFactory != null) {
                return theGenconfFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new GenconfFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public GenconfFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case GenconfPackage.GENERATION:
                return createGeneration();
            case GenconfPackage.MODEL_DEFINITION:
                return createModelDefinition();
            case GenconfPackage.STRING_DEFINITION:
                return createStringDefinition();
            case GenconfPackage.INTEGER_DEFINITION:
                return createIntegerDefinition();
            case GenconfPackage.REAL_DEFINITION:
                return createRealDefinition();
            case GenconfPackage.BOOLEAN_DEFINITION:
                return createBooleanDefinition();
            case GenconfPackage.OPTION:
                return createOption();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Generation createGeneration() {
        GenerationImpl generation = new GenerationImpl();
        return generation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public ModelDefinition createModelDefinition() {
        ModelDefinitionImpl modelDefinition = new ModelDefinitionImpl();
        return modelDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public StringDefinition createStringDefinition() {
        StringDefinitionImpl stringDefinition = new StringDefinitionImpl();
        return stringDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public IntegerDefinition createIntegerDefinition() {
        IntegerDefinitionImpl integerDefinition = new IntegerDefinitionImpl();
        return integerDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public RealDefinition createRealDefinition() {
        RealDefinitionImpl realDefinition = new RealDefinitionImpl();
        return realDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public BooleanDefinition createBooleanDefinition() {
        BooleanDefinitionImpl booleanDefinition = new BooleanDefinitionImpl();
        return booleanDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public Option createOption() {
        OptionImpl option = new OptionImpl();
        return option;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public GenconfPackage getGenconfPackage() {
        return (GenconfPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static GenconfPackage getPackage() {
        return GenconfPackage.eINSTANCE;
    }

} // GenconfFactoryImpl
