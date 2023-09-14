/**
 */
package org.obeonetwork.m2doc.genconf.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.obeonetwork.m2doc.genconf.BooleanDefinition;
import org.obeonetwork.m2doc.genconf.BooleanOrderedSetDefinition;
import org.obeonetwork.m2doc.genconf.BooleanSequenceDefinition;
import org.obeonetwork.m2doc.genconf.GenconfFactory;
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
            case GenconfPackage.MODEL_SEQUENCE_DEFINITION:
                return createModelSequenceDefinition();
            case GenconfPackage.MODEL_ORDERED_SET_DEFINITION:
                return createModelOrderedSetDefinition();
            case GenconfPackage.STRING_DEFINITION:
                return createStringDefinition();
            case GenconfPackage.STRING_SEQUENCE_DEFINITION:
                return createStringSequenceDefinition();
            case GenconfPackage.STRING_ORDERED_SET_DEFINITION:
                return createStringOrderedSetDefinition();
            case GenconfPackage.INTEGER_DEFINITION:
                return createIntegerDefinition();
            case GenconfPackage.INTEGER_SEQUENCE_DEFINITION:
                return createIntegerSequenceDefinition();
            case GenconfPackage.INTEGER_ORDERED_SET_DEFINITION:
                return createIntegerOrderedSetDefinition();
            case GenconfPackage.REAL_DEFINITION:
                return createRealDefinition();
            case GenconfPackage.REAL_SEQUENCE_DEFINITION:
                return createRealSequenceDefinition();
            case GenconfPackage.REAL_ORDERED_SET_DEFINITION:
                return createRealOrderedSetDefinition();
            case GenconfPackage.BOOLEAN_DEFINITION:
                return createBooleanDefinition();
            case GenconfPackage.BOOLEAN_SEQUENCE_DEFINITION:
                return createBooleanSequenceDefinition();
            case GenconfPackage.BOOLEAN_ORDERED_SET_DEFINITION:
                return createBooleanOrderedSetDefinition();
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
    @Override
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
    @Override
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
    @Override
    public ModelSequenceDefinition createModelSequenceDefinition() {
        ModelSequenceDefinitionImpl modelSequenceDefinition = new ModelSequenceDefinitionImpl();
        return modelSequenceDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ModelOrderedSetDefinition createModelOrderedSetDefinition() {
        ModelOrderedSetDefinitionImpl modelOrderedSetDefinition = new ModelOrderedSetDefinitionImpl();
        return modelOrderedSetDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
    @Override
    public StringSequenceDefinition createStringSequenceDefinition() {
        StringSequenceDefinitionImpl stringSequenceDefinition = new StringSequenceDefinitionImpl();
        return stringSequenceDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public StringOrderedSetDefinition createStringOrderedSetDefinition() {
        StringOrderedSetDefinitionImpl stringOrderedSetDefinition = new StringOrderedSetDefinitionImpl();
        return stringOrderedSetDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
    @Override
    public IntegerSequenceDefinition createIntegerSequenceDefinition() {
        IntegerSequenceDefinitionImpl integerSequenceDefinition = new IntegerSequenceDefinitionImpl();
        return integerSequenceDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public IntegerOrderedSetDefinition createIntegerOrderedSetDefinition() {
        IntegerOrderedSetDefinitionImpl integerOrderedSetDefinition = new IntegerOrderedSetDefinitionImpl();
        return integerOrderedSetDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
    @Override
    public RealSequenceDefinition createRealSequenceDefinition() {
        RealSequenceDefinitionImpl realSequenceDefinition = new RealSequenceDefinitionImpl();
        return realSequenceDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public RealOrderedSetDefinition createRealOrderedSetDefinition() {
        RealOrderedSetDefinitionImpl realOrderedSetDefinition = new RealOrderedSetDefinitionImpl();
        return realOrderedSetDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
    @Override
    public BooleanSequenceDefinition createBooleanSequenceDefinition() {
        BooleanSequenceDefinitionImpl booleanSequenceDefinition = new BooleanSequenceDefinitionImpl();
        return booleanSequenceDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public BooleanOrderedSetDefinition createBooleanOrderedSetDefinition() {
        BooleanOrderedSetDefinitionImpl booleanOrderedSetDefinition = new BooleanOrderedSetDefinitionImpl();
        return booleanOrderedSetDefinition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
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
    @Override
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
