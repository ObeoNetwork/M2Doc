/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
/**
 */
package org.obeonetwork.m2doc.template;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.obeonetwork.m2doc.template.TemplateFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='Le m\351ta mod\350le \'template\' repr\351sente les concepts produit par le parsing \r\ndes templates de g\351n\351ration documentaire.\r\n\r\nLes mod\350les instances sont utilis\351s pour la g\351n\351ration.'"
 * @generated
 */
public interface TemplatePackage extends EPackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "template";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.obeonetwork.org/m2doc/template/1.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "template";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	TemplatePackage eINSTANCE = org.obeonetwork.m2doc.template.impl.TemplatePackageImpl.init();

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.AbstractConstructImpl <em>Abstract Construct</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.AbstractConstructImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAbstractConstruct()
     * @generated
     */
	int ABSTRACT_CONSTRUCT = 0;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CONSTRUCT__STYLE_RUN = 0;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CONSTRUCT__RUNS = 1;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CONSTRUCT__CLOSING_RUNS = 2;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES = 3;

    /**
     * The number of structural features of the '<em>Abstract Construct</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CONSTRUCT_FEATURE_COUNT = 4;

	/**
     * The number of operations of the '<em>Abstract Construct</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_CONSTRUCT_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.CompoundImpl <em>Compound</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.CompoundImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCompound()
     * @generated
     */
	int COMPOUND = 9;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPOUND__VALIDATION_MESSAGES = ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND__SUB_CONSTRUCTS = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Compound</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>Compound</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int COMPOUND_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.ConditionnalImpl <em>Conditionnal</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.ConditionnalImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getConditionnal()
     * @generated
     */
	int CONDITIONNAL = 1;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__STYLE_RUN = COMPOUND__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__RUNS = COMPOUND__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITIONNAL__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
     * The feature id for the '<em><b>Alternative</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__ALTERNATIVE = COMPOUND_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__ELSE = COMPOUND_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL__QUERY = COMPOUND_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Conditionnal</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 3;

	/**
     * The number of operations of the '<em>Conditionnal</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONDITIONNAL_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.RepetitionImpl <em>Repetition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.RepetitionImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRepetition()
     * @generated
     */
	int REPETITION = 2;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION__STYLE_RUN = COMPOUND__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION__RUNS = COMPOUND__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPETITION__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
     * The feature id for the '<em><b>Iteration Var</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION__ITERATION_VAR = COMPOUND_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION__QUERY = COMPOUND_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Repetition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Repetition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPETITION_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.UserDocImpl <em>User Doc</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.UserDocImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserDoc()
     * @generated
     */
    int USER_DOC = 3;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC__STYLE_RUN = COMPOUND__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC__RUNS = COMPOUND__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC__ID = COMPOUND_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>User Doc</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>User Doc</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_DOC_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.UserContentImpl <em>User Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.UserContentImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserContent()
     * @generated
     */
    int USER_CONTENT = 4;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT__STYLE_RUN = COMPOUND__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT__RUNS = COMPOUND__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT__ID = COMPOUND_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>User Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>User Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int USER_CONTENT_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.QueryImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getQuery()
     * @generated
     */
	int QUERY = 5;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int QUERY__VALIDATION_MESSAGES = ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Behavior</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY__BEHAVIOR = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY__QUERY = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int QUERY_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.TableMergeImpl <em>Table Merge</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.TableMergeImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTableMerge()
     * @generated
     */
	int TABLE_MERGE = 6;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE__STYLE_RUN = COMPOUND__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE__RUNS = COMPOUND__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_MERGE__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
     * The feature id for the '<em><b>Legend</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE__LEGEND = COMPOUND_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Table Merge</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>Table Merge</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_MERGE_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl <em>Abstract Provider Client</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAbstractProviderClient()
     * @generated
     */
    int ABSTRACT_PROVIDER_CLIENT = 19;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT__RUNS = ABSTRACT_CONSTRUCT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES = ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Option Value Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Provider</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT__PROVIDER = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Abstract Provider Client</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Abstract Provider Client</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_PROVIDER_CLIENT_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.AbstractImageImpl <em>Abstract Image</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.AbstractImageImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAbstractImage()
     * @generated
     */
    int ABSTRACT_IMAGE = 18;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__STYLE_RUN = ABSTRACT_PROVIDER_CLIENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__RUNS = ABSTRACT_PROVIDER_CLIENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__CLOSING_RUNS = ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__VALIDATION_MESSAGES = ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Option Value Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__OPTION_VALUE_MAP = ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP;

    /**
     * The feature id for the '<em><b>Provider</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__PROVIDER = ABSTRACT_PROVIDER_CLIENT__PROVIDER;

    /**
     * The feature id for the '<em><b>Legend</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__LEGEND = ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__HEIGHT = ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__WIDTH = ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Legend POS</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE__LEGEND_POS = ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Abstract Image</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE_FEATURE_COUNT = ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Abstract Image</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABSTRACT_IMAGE_OPERATION_COUNT = ABSTRACT_PROVIDER_CLIENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.TableImpl <em>Table</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.TableImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTable()
     * @generated
     */
	int TABLE = 13;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.RowImpl <em>Row</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.RowImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRow()
     * @generated
     */
	int ROW = 14;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.CellImpl <em>Cell</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.CellImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCell()
     * @generated
     */
	int CELL = 15;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.ImageImpl <em>Image</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.ImageImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getImage()
     * @generated
     */
	int IMAGE = 7;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__STYLE_RUN = ABSTRACT_IMAGE__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__RUNS = ABSTRACT_IMAGE__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__CLOSING_RUNS = ABSTRACT_IMAGE__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMAGE__VALIDATION_MESSAGES = ABSTRACT_IMAGE__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Option Value Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMAGE__OPTION_VALUE_MAP = ABSTRACT_IMAGE__OPTION_VALUE_MAP;

    /**
     * The feature id for the '<em><b>Provider</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMAGE__PROVIDER = ABSTRACT_IMAGE__PROVIDER;

    /**
     * The feature id for the '<em><b>Legend</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__LEGEND = ABSTRACT_IMAGE__LEGEND;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__HEIGHT = ABSTRACT_IMAGE__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__WIDTH = ABSTRACT_IMAGE__WIDTH;

    /**
     * The feature id for the '<em><b>Legend POS</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__LEGEND_POS = ABSTRACT_IMAGE__LEGEND_POS;

    /**
     * The feature id for the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE__FILE_NAME = ABSTRACT_IMAGE_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Image</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_FEATURE_COUNT = ABSTRACT_IMAGE_FEATURE_COUNT + 1;

	/**
     * The number of operations of the '<em>Image</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int IMAGE_OPERATION_COUNT = ABSTRACT_IMAGE_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.DefaultImpl <em>Default</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.DefaultImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDefault()
     * @generated
     */
	int DEFAULT = 8;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFAULT__STYLE_RUN = COMPOUND__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFAULT__RUNS = COMPOUND__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFAULT__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEFAULT__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFAULT__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
     * The number of structural features of the '<em>Default</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFAULT_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 0;

	/**
     * The number of operations of the '<em>Default</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFAULT_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.TemplateImpl <em>Template</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.TemplateImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplate()
     * @generated
     */
	int TEMPLATE = 10;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE__STYLE_RUN = COMPOUND__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE__RUNS = COMPOUND__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEMPLATE__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
     * The feature id for the '<em><b>Template Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE__TEMPLATE_NAME = COMPOUND_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Body</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TEMPLATE__BODY = COMPOUND_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Template</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Template</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TEMPLATE_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.RepresentationImpl <em>Representation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.RepresentationImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRepresentation()
     * @generated
     */
	int REPRESENTATION = 11;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION__STYLE_RUN = ABSTRACT_IMAGE__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION__RUNS = ABSTRACT_IMAGE__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION__CLOSING_RUNS = ABSTRACT_IMAGE__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__VALIDATION_MESSAGES = ABSTRACT_IMAGE__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Option Value Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__OPTION_VALUE_MAP = ABSTRACT_IMAGE__OPTION_VALUE_MAP;

    /**
     * The feature id for the '<em><b>Provider</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__PROVIDER = ABSTRACT_IMAGE__PROVIDER;

    /**
     * The feature id for the '<em><b>Legend</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__LEGEND = ABSTRACT_IMAGE__LEGEND;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__HEIGHT = ABSTRACT_IMAGE__HEIGHT;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__WIDTH = ABSTRACT_IMAGE__WIDTH;

    /**
     * The feature id for the '<em><b>Legend POS</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__LEGEND_POS = ABSTRACT_IMAGE__LEGEND_POS;

    /**
     * The feature id for the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION__QUERY = ABSTRACT_IMAGE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Representation Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION__REPRESENTATION_ID = ABSTRACT_IMAGE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Representation Title</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__REPRESENTATION_TITLE = ABSTRACT_IMAGE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Activated Layers</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int REPRESENTATION__ACTIVATED_LAYERS = ABSTRACT_IMAGE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Representation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION_FEATURE_COUNT = ABSTRACT_IMAGE_FEATURE_COUNT + 4;

	/**
     * The number of operations of the '<em>Representation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int REPRESENTATION_OPERATION_COUNT = ABSTRACT_IMAGE_OPERATION_COUNT + 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.StaticFragmentImpl <em>Static Fragment</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.StaticFragmentImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getStaticFragment()
     * @generated
     */
	int STATIC_FRAGMENT = 12;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATIC_FRAGMENT__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATIC_FRAGMENT__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATIC_FRAGMENT__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT__VALIDATION_MESSAGES = ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The number of structural features of the '<em>Static Fragment</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATIC_FRAGMENT_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
     * The number of operations of the '<em>Static Fragment</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int STATIC_FRAGMENT_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE__VALIDATION_MESSAGES = ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Rows</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__ROWS = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Table</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE__TABLE = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Table</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 2;

	/**
     * The number of operations of the '<em>Table</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TABLE_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
     * The feature id for the '<em><b>Cells</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROW__CELLS = 0;

	/**
     * The feature id for the '<em><b>Table Row</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROW__TABLE_ROW = 1;

	/**
     * The number of structural features of the '<em>Row</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROW_FEATURE_COUNT = 2;

	/**
     * The number of operations of the '<em>Row</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROW_OPERATION_COUNT = 0;

	/**
     * The feature id for the '<em><b>Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CELL__TEMPLATE = 0;

	/**
     * The feature id for the '<em><b>Table Cell</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CELL__TABLE_CELL = 1;

	/**
     * The number of structural features of the '<em>Cell</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CELL_FEATURE_COUNT = 2;

	/**
     * The number of operations of the '<em>Cell</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CELL_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl <em>Document Template</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocumentTemplate()
     * @generated
     */
	int DOCUMENT_TEMPLATE = 16;

	/**
     * The feature id for the '<em><b>Headers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_TEMPLATE__HEADERS = 0;

	/**
     * The feature id for the '<em><b>Footers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_TEMPLATE__FOOTERS = 1;

	/**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_TEMPLATE__BODY = 2;

	/**
     * The feature id for the '<em><b>Document</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__DOCUMENT = 3;

    /**
     * The number of structural features of the '<em>Document Template</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_TEMPLATE_FEATURE_COUNT = 4;

	/**
     * The number of operations of the '<em>Document Template</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DOCUMENT_TEMPLATE_OPERATION_COUNT = 0;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.OptionValueMapImpl <em>Option Value Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.OptionValueMapImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getOptionValueMap()
     * @generated
     */
    int OPTION_VALUE_MAP = 17;

    /**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPTION_VALUE_MAP__KEY = 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPTION_VALUE_MAP__VALUE = 1;

    /**
     * The number of structural features of the '<em>Option Value Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPTION_VALUE_MAP_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Option Value Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OPTION_VALUE_MAP_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.BookmarkImpl <em>Bookmark</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.BookmarkImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBookmark()
     * @generated
     */
    int BOOKMARK = 20;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK__STYLE_RUN = COMPOUND__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK__RUNS = COMPOUND__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK__VALIDATION_MESSAGES = COMPOUND__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK__NAME = COMPOUND_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Bookmark</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Bookmark</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BOOKMARK_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.LinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.LinkImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getLink()
     * @generated
     */
    int LINK = 21;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__RUNS = ABSTRACT_CONSTRUCT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__VALIDATION_MESSAGES = ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__NAME = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK__TEXT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.TableClientImpl <em>Table Client</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.impl.TableClientImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTableClient()
     * @generated
     */
    int TABLE_CLIENT = 22;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT__STYLE_RUN = ABSTRACT_PROVIDER_CLIENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT__RUNS = ABSTRACT_PROVIDER_CLIENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT__CLOSING_RUNS = ABSTRACT_PROVIDER_CLIENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT__VALIDATION_MESSAGES = ABSTRACT_PROVIDER_CLIENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Option Value Map</b></em>' map.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT__OPTION_VALUE_MAP = ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP;

    /**
     * The feature id for the '<em><b>Provider</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT__PROVIDER = ABSTRACT_PROVIDER_CLIENT__PROVIDER;

    /**
     * The number of structural features of the '<em>Table Client</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT_FEATURE_COUNT = ABSTRACT_PROVIDER_CLIENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Table Client</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TABLE_CLIENT_OPERATION_COUNT = ABSTRACT_PROVIDER_CLIENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.QueryBehavior <em>Query Behavior</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.QueryBehavior
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getQueryBehavior()
     * @generated
     */
	int QUERY_BEHAVIOR = 23;

	/**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.POSITION <em>POSITION</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.template.POSITION
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getPOSITION()
     * @generated
     */
	int POSITION = 24;

	/**
     * The meta object id for the '<em>Document</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.poi.xwpf.usermodel.XWPFDocument
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocument()
     * @generated
     */
    int DOCUMENT = 25;

    /**
     * The meta object id for the '<em>WTable</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.apache.poi.xwpf.usermodel.XWPFTable
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTable()
     * @generated
     */
	int WTABLE = 26;

	/**
     * The meta object id for the '<em>Run</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.apache.poi.xwpf.usermodel.XWPFRun
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRun()
     * @generated
     */
	int RUN = 27;


	/**
     * The meta object id for the '<em>Ast Result</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAstResult()
     * @generated
     */
	int AST_RESULT = 28;

	/**
     * The meta object id for the '<em>Validation Message</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.parser.TemplateValidationMessage
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplateValidationMessage()
     * @generated
     */
    int TEMPLATE_VALIDATION_MESSAGE = 29;

    /**
     * The meta object id for the '<em>WTable Row</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.apache.poi.xwpf.usermodel.XWPFTableRow
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableRow()
     * @generated
     */
	int WTABLE_ROW = 30;

	/**
     * The meta object id for the '<em>WTable Cell</em>' data type.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.apache.poi.xwpf.usermodel.XWPFTableCell
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableCell()
     * @generated
     */
	int WTABLE_CELL = 31;

	/**
     * The meta object id for the '<em>Provider</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.obeonetwork.m2doc.provider.IProvider
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getProvider()
     * @generated
     */
    int PROVIDER = 32;

    /**
     * The meta object id for the '<em>Body</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.apache.poi.xwpf.usermodel.IBody
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBody()
     * @generated
     */
    int BODY = 33;

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.AbstractConstruct <em>Abstract Construct</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Construct</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractConstruct
     * @generated
     */
	EClass getAbstractConstruct();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.AbstractConstruct#getStyleRun <em>Style Run</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Style Run</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractConstruct#getStyleRun()
     * @see #getAbstractConstruct()
     * @generated
     */
	EAttribute getAbstractConstruct_StyleRun();

	/**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.AbstractConstruct#getRuns <em>Runs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Runs</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractConstruct#getRuns()
     * @see #getAbstractConstruct()
     * @generated
     */
	EAttribute getAbstractConstruct_Runs();

	/**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.AbstractConstruct#getClosingRuns <em>Closing Runs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Closing Runs</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractConstruct#getClosingRuns()
     * @see #getAbstractConstruct()
     * @generated
     */
	EAttribute getAbstractConstruct_ClosingRuns();

	/**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.AbstractConstruct#getValidationMessages <em>Validation Messages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Validation Messages</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractConstruct#getValidationMessages()
     * @see #getAbstractConstruct()
     * @generated
     */
    EAttribute getAbstractConstruct_ValidationMessages();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Conditionnal <em>Conditionnal</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Conditionnal</em>'.
     * @see org.obeonetwork.m2doc.template.Conditionnal
     * @generated
     */
	EClass getConditionnal();

	/**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Conditionnal#getAlternative <em>Alternative</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Alternative</em>'.
     * @see org.obeonetwork.m2doc.template.Conditionnal#getAlternative()
     * @see #getConditionnal()
     * @generated
     */
	EReference getConditionnal_Alternative();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Conditionnal#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Conditionnal#getQuery()
     * @see #getConditionnal()
     * @generated
     */
	EAttribute getConditionnal_Query();

	/**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Conditionnal#getElse <em>Else</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Else</em>'.
     * @see org.obeonetwork.m2doc.template.Conditionnal#getElse()
     * @see #getConditionnal()
     * @generated
     */
	EReference getConditionnal_Else();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Repetition <em>Repetition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Repetition</em>'.
     * @see org.obeonetwork.m2doc.template.Repetition
     * @generated
     */
	EClass getRepetition();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Repetition#getIterationVar <em>Iteration Var</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Iteration Var</em>'.
     * @see org.obeonetwork.m2doc.template.Repetition#getIterationVar()
     * @see #getRepetition()
     * @generated
     */
	EAttribute getRepetition_IterationVar();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Repetition#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Repetition#getQuery()
     * @see #getRepetition()
     * @generated
     */
	EAttribute getRepetition_Query();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.UserDoc <em>User Doc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>User Doc</em>'.
     * @see org.obeonetwork.m2doc.template.UserDoc
     * @generated
     */
    EClass getUserDoc();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.UserDoc#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.obeonetwork.m2doc.template.UserDoc#getId()
     * @see #getUserDoc()
     * @generated
     */
    EAttribute getUserDoc_Id();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.UserContent <em>User Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>User Content</em>'.
     * @see org.obeonetwork.m2doc.template.UserContent
     * @generated
     */
    EClass getUserContent();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.UserContent#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.obeonetwork.m2doc.template.UserContent#getId()
     * @see #getUserContent()
     * @generated
     */
    EAttribute getUserContent_Id();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Query
     * @generated
     */
	EClass getQuery();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Query#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Query#getQuery()
     * @see #getQuery()
     * @generated
     */
	EAttribute getQuery_Query();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.TableMerge <em>Table Merge</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Merge</em>'.
     * @see org.obeonetwork.m2doc.template.TableMerge
     * @generated
     */
	EClass getTableMerge();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.TableMerge#getLegend <em>Legend</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Legend</em>'.
     * @see org.obeonetwork.m2doc.template.TableMerge#getLegend()
     * @see #getTableMerge()
     * @generated
     */
	EAttribute getTableMerge_Legend();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Query#getBehavior <em>Behavior</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Behavior</em>'.
     * @see org.obeonetwork.m2doc.template.Query#getBehavior()
     * @see #getQuery()
     * @generated
     */
	EAttribute getQuery_Behavior();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Table <em>Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table</em>'.
     * @see org.obeonetwork.m2doc.template.Table
     * @generated
     */
	EClass getTable();

	/**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Table#getRows <em>Rows</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Rows</em>'.
     * @see org.obeonetwork.m2doc.template.Table#getRows()
     * @see #getTable()
     * @generated
     */
	EReference getTable_Rows();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Table#getTable <em>Table</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table</em>'.
     * @see org.obeonetwork.m2doc.template.Table#getTable()
     * @see #getTable()
     * @generated
     */
	EAttribute getTable_Table();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Row <em>Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Row</em>'.
     * @see org.obeonetwork.m2doc.template.Row
     * @generated
     */
	EClass getRow();

	/**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Row#getCells <em>Cells</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Cells</em>'.
     * @see org.obeonetwork.m2doc.template.Row#getCells()
     * @see #getRow()
     * @generated
     */
	EReference getRow_Cells();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Row#getTableRow <em>Table Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Row</em>'.
     * @see org.obeonetwork.m2doc.template.Row#getTableRow()
     * @see #getRow()
     * @generated
     */
	EAttribute getRow_TableRow();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Cell <em>Cell</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Cell</em>'.
     * @see org.obeonetwork.m2doc.template.Cell
     * @generated
     */
	EClass getCell();

	/**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Cell#getTemplate <em>Template</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Template</em>'.
     * @see org.obeonetwork.m2doc.template.Cell#getTemplate()
     * @see #getCell()
     * @generated
     */
	EReference getCell_Template();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Cell#getTableCell <em>Table Cell</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Cell</em>'.
     * @see org.obeonetwork.m2doc.template.Cell#getTableCell()
     * @see #getCell()
     * @generated
     */
	EAttribute getCell_TableCell();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.DocumentTemplate <em>Document Template</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Document Template</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate
     * @generated
     */
	EClass getDocumentTemplate();

	/**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getHeaders <em>Headers</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Headers</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getHeaders()
     * @see #getDocumentTemplate()
     * @generated
     */
	EReference getDocumentTemplate_Headers();

	/**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getFooters <em>Footers</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Footers</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getFooters()
     * @see #getDocumentTemplate()
     * @generated
     */
	EReference getDocumentTemplate_Footers();

	/**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getBody()
     * @see #getDocumentTemplate()
     * @generated
     */
	EReference getDocumentTemplate_Body();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getDocument <em>Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Document</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getDocument()
     * @see #getDocumentTemplate()
     * @generated
     */
    EAttribute getDocumentTemplate_Document();

    /**
     * Returns the meta object for class '{@link java.util.Map.Entry <em>Option Value Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Option Value Map</em>'.
     * @see java.util.Map.Entry
     * @model keyDataType="org.eclipse.emf.ecore.EString"
     *        valueDataType="org.eclipse.emf.ecore.EJavaObject"
     * @generated
     */
    EClass getOptionValueMap();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see java.util.Map.Entry
     * @see #getOptionValueMap()
     * @generated
     */
    EAttribute getOptionValueMap_Key();

    /**
     * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see java.util.Map.Entry
     * @see #getOptionValueMap()
     * @generated
     */
    EAttribute getOptionValueMap_Value();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.AbstractImage <em>Abstract Image</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Image</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractImage
     * @generated
     */
    EClass getAbstractImage();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.AbstractImage#getLegend <em>Legend</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Legend</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractImage#getLegend()
     * @see #getAbstractImage()
     * @generated
     */
    EAttribute getAbstractImage_Legend();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.AbstractImage#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractImage#getHeight()
     * @see #getAbstractImage()
     * @generated
     */
    EAttribute getAbstractImage_Height();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.AbstractImage#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractImage#getWidth()
     * @see #getAbstractImage()
     * @generated
     */
    EAttribute getAbstractImage_Width();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.AbstractImage#getLegendPOS <em>Legend POS</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Legend POS</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractImage#getLegendPOS()
     * @see #getAbstractImage()
     * @generated
     */
    EAttribute getAbstractImage_LegendPOS();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.AbstractProviderClient <em>Abstract Provider Client</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract Provider Client</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractProviderClient
     * @generated
     */
    EClass getAbstractProviderClient();

    /**
     * Returns the meta object for the map '{@link org.obeonetwork.m2doc.template.AbstractProviderClient#getOptionValueMap <em>Option Value Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the map '<em>Option Value Map</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractProviderClient#getOptionValueMap()
     * @see #getAbstractProviderClient()
     * @generated
     */
    EReference getAbstractProviderClient_OptionValueMap();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.AbstractProviderClient#getProvider <em>Provider</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Provider</em>'.
     * @see org.obeonetwork.m2doc.template.AbstractProviderClient#getProvider()
     * @see #getAbstractProviderClient()
     * @generated
     */
    EAttribute getAbstractProviderClient_Provider();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Bookmark <em>Bookmark</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Bookmark</em>'.
     * @see org.obeonetwork.m2doc.template.Bookmark
     * @generated
     */
    EClass getBookmark();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Bookmark#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.template.Bookmark#getName()
     * @see #getBookmark()
     * @generated
     */
    EAttribute getBookmark_Name();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Link <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Link</em>'.
     * @see org.obeonetwork.m2doc.template.Link
     * @generated
     */
    EClass getLink();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Link#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.template.Link#getName()
     * @see #getLink()
     * @generated
     */
    EAttribute getLink_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Link#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see org.obeonetwork.m2doc.template.Link#getText()
     * @see #getLink()
     * @generated
     */
    EAttribute getLink_Text();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.TableClient <em>Table Client</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Table Client</em>'.
     * @see org.obeonetwork.m2doc.template.TableClient
     * @generated
     */
    EClass getTableClient();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Image <em>Image</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Image</em>'.
     * @see org.obeonetwork.m2doc.template.Image
     * @generated
     */
	EClass getImage();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Image#getFileName <em>File Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Name</em>'.
     * @see org.obeonetwork.m2doc.template.Image#getFileName()
     * @see #getImage()
     * @generated
     */
	EAttribute getImage_FileName();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Default <em>Default</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Default</em>'.
     * @see org.obeonetwork.m2doc.template.Default
     * @generated
     */
	EClass getDefault();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Compound <em>Compound</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Compound</em>'.
     * @see org.obeonetwork.m2doc.template.Compound
     * @generated
     */
	EClass getCompound();

	/**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Compound#getSubConstructs <em>Sub Constructs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Sub Constructs</em>'.
     * @see org.obeonetwork.m2doc.template.Compound#getSubConstructs()
     * @see #getCompound()
     * @generated
     */
	EReference getCompound_SubConstructs();

	/**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Template <em>Template</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Template</em>'.
     * @see org.obeonetwork.m2doc.template.Template
     * @generated
     */
	EClass getTemplate();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Template#getTemplateName <em>Template Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Template Name</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getTemplateName()
     * @see #getTemplate()
     * @generated
     */
	EAttribute getTemplate_TemplateName();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Template#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getBody()
     * @see #getTemplate()
     * @generated
     */
    EAttribute getTemplate_Body();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Representation <em>Representation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Representation</em>'.
     * @see org.obeonetwork.m2doc.template.Representation
     * @generated
     */
	EClass getRepresentation();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Representation#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Representation#getQuery()
     * @see #getRepresentation()
     * @generated
     */
	EAttribute getRepresentation_Query();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Representation#getRepresentationId <em>Representation Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Representation Id</em>'.
     * @see org.obeonetwork.m2doc.template.Representation#getRepresentationId()
     * @see #getRepresentation()
     * @generated
     */
	EAttribute getRepresentation_RepresentationId();

	/**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Representation#getRepresentationTitle <em>Representation Title</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Representation Title</em>'.
     * @see org.obeonetwork.m2doc.template.Representation#getRepresentationTitle()
     * @see #getRepresentation()
     * @generated
     */
    EAttribute getRepresentation_RepresentationTitle();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.Representation#getActivatedLayers <em>Activated Layers</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Activated Layers</em>'.
     * @see org.obeonetwork.m2doc.template.Representation#getActivatedLayers()
     * @see #getRepresentation()
     * @generated
     */
    EAttribute getRepresentation_ActivatedLayers();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.StaticFragment <em>Static Fragment</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Static Fragment</em>'.
     * @see org.obeonetwork.m2doc.template.StaticFragment
     * @generated
     */
	EClass getStaticFragment();

	/**
     * Returns the meta object for enum '{@link org.obeonetwork.m2doc.template.QueryBehavior <em>Query Behavior</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Query Behavior</em>'.
     * @see org.obeonetwork.m2doc.template.QueryBehavior
     * @generated
     */
	EEnum getQueryBehavior();

	/**
     * Returns the meta object for enum '{@link org.obeonetwork.m2doc.template.POSITION <em>POSITION</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>POSITION</em>'.
     * @see org.obeonetwork.m2doc.template.POSITION
     * @generated
     */
	EEnum getPOSITION();

	/**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFDocument <em>Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Document</em>'.
     * @see org.apache.poi.xwpf.usermodel.XWPFDocument
     * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFDocument"
     * @generated
     */
    EDataType getDocument();

    /**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFTable <em>WTable</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>WTable</em>'.
     * @see org.apache.poi.xwpf.usermodel.XWPFTable
     * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFTable"
     * @generated
     */
	EDataType getWTable();

	/**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFRun <em>Run</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Run</em>'.
     * @see org.apache.poi.xwpf.usermodel.XWPFRun
     * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFRun"
     * @generated
     */
	EDataType getRun();

	/**
     * Returns the meta object for data type '{@link org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult <em>Ast Result</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Ast Result</em>'.
     * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
     * @model instanceClass="org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult"
     * @generated
     */
	EDataType getAstResult();

	/**
     * Returns the meta object for data type '{@link org.obeonetwork.m2doc.parser.TemplateValidationMessage <em>Validation Message</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Validation Message</em>'.
     * @see org.obeonetwork.m2doc.parser.TemplateValidationMessage
     * @model instanceClass="org.obeonetwork.m2doc.parser.TemplateValidationMessage"
     * @generated
     */
    EDataType getTemplateValidationMessage();

    /**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFTableRow <em>WTable Row</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>WTable Row</em>'.
     * @see org.apache.poi.xwpf.usermodel.XWPFTableRow
     * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFTableRow"
     * @generated
     */
	EDataType getWTableRow();

	/**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFTableCell <em>WTable Cell</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for data type '<em>WTable Cell</em>'.
     * @see org.apache.poi.xwpf.usermodel.XWPFTableCell
     * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFTableCell"
     * @generated
     */
	EDataType getWTableCell();

	/**
     * Returns the meta object for data type '{@link org.obeonetwork.m2doc.provider.IProvider <em>Provider</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Provider</em>'.
     * @see org.obeonetwork.m2doc.provider.IProvider
     * @model instanceClass="org.obeonetwork.m2doc.provider.IProvider"
     * @generated
     */
    EDataType getProvider();

    /**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.IBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Body</em>'.
     * @see org.apache.poi.xwpf.usermodel.IBody
     * @model instanceClass="org.apache.poi.xwpf.usermodel.IBody"
     * @generated
     */
    EDataType getBody();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	TemplateFactory getTemplateFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.AbstractConstructImpl <em>Abstract Construct</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.AbstractConstructImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAbstractConstruct()
         * @generated
         */
		EClass ABSTRACT_CONSTRUCT = eINSTANCE.getAbstractConstruct();

		/**
         * The meta object literal for the '<em><b>Style Run</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_CONSTRUCT__STYLE_RUN = eINSTANCE.getAbstractConstruct_StyleRun();

		/**
         * The meta object literal for the '<em><b>Runs</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_CONSTRUCT__RUNS = eINSTANCE.getAbstractConstruct_Runs();

		/**
         * The meta object literal for the '<em><b>Closing Runs</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_CONSTRUCT__CLOSING_RUNS = eINSTANCE.getAbstractConstruct_ClosingRuns();

		/**
         * The meta object literal for the '<em><b>Validation Messages</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES = eINSTANCE.getAbstractConstruct_ValidationMessages();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.ConditionnalImpl <em>Conditionnal</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.ConditionnalImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getConditionnal()
         * @generated
         */
		EClass CONDITIONNAL = eINSTANCE.getConditionnal();

		/**
         * The meta object literal for the '<em><b>Alternative</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONDITIONNAL__ALTERNATIVE = eINSTANCE.getConditionnal_Alternative();

		/**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONDITIONNAL__QUERY = eINSTANCE.getConditionnal_Query();

		/**
         * The meta object literal for the '<em><b>Else</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONDITIONNAL__ELSE = eINSTANCE.getConditionnal_Else();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.RepetitionImpl <em>Repetition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.RepetitionImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRepetition()
         * @generated
         */
		EClass REPETITION = eINSTANCE.getRepetition();

		/**
         * The meta object literal for the '<em><b>Iteration Var</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute REPETITION__ITERATION_VAR = eINSTANCE.getRepetition_IterationVar();

		/**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute REPETITION__QUERY = eINSTANCE.getRepetition_Query();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.UserDocImpl <em>User Doc</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.UserDocImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserDoc()
         * @generated
         */
        EClass USER_DOC = eINSTANCE.getUserDoc();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER_DOC__ID = eINSTANCE.getUserDoc_Id();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.UserContentImpl <em>User Content</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.UserContentImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserContent()
         * @generated
         */
        EClass USER_CONTENT = eINSTANCE.getUserContent();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute USER_CONTENT__ID = eINSTANCE.getUserContent_Id();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.QueryImpl <em>Query</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.QueryImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getQuery()
         * @generated
         */
		EClass QUERY = eINSTANCE.getQuery();

		/**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute QUERY__QUERY = eINSTANCE.getQuery_Query();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.TableMergeImpl <em>Table Merge</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.TableMergeImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTableMerge()
         * @generated
         */
		EClass TABLE_MERGE = eINSTANCE.getTableMerge();

		/**
         * The meta object literal for the '<em><b>Legend</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TABLE_MERGE__LEGEND = eINSTANCE.getTableMerge_Legend();

		/**
         * The meta object literal for the '<em><b>Behavior</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute QUERY__BEHAVIOR = eINSTANCE.getQuery_Behavior();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.TableImpl <em>Table</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.TableImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTable()
         * @generated
         */
		EClass TABLE = eINSTANCE.getTable();

		/**
         * The meta object literal for the '<em><b>Rows</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference TABLE__ROWS = eINSTANCE.getTable_Rows();

		/**
         * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TABLE__TABLE = eINSTANCE.getTable_Table();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.RowImpl <em>Row</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.RowImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRow()
         * @generated
         */
		EClass ROW = eINSTANCE.getRow();

		/**
         * The meta object literal for the '<em><b>Cells</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ROW__CELLS = eINSTANCE.getRow_Cells();

		/**
         * The meta object literal for the '<em><b>Table Row</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ROW__TABLE_ROW = eINSTANCE.getRow_TableRow();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.CellImpl <em>Cell</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.CellImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCell()
         * @generated
         */
		EClass CELL = eINSTANCE.getCell();

		/**
         * The meta object literal for the '<em><b>Template</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CELL__TEMPLATE = eINSTANCE.getCell_Template();

		/**
         * The meta object literal for the '<em><b>Table Cell</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CELL__TABLE_CELL = eINSTANCE.getCell_TableCell();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl <em>Document Template</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocumentTemplate()
         * @generated
         */
		EClass DOCUMENT_TEMPLATE = eINSTANCE.getDocumentTemplate();

		/**
         * The meta object literal for the '<em><b>Headers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_TEMPLATE__HEADERS = eINSTANCE.getDocumentTemplate_Headers();

		/**
         * The meta object literal for the '<em><b>Footers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_TEMPLATE__FOOTERS = eINSTANCE.getDocumentTemplate_Footers();

		/**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DOCUMENT_TEMPLATE__BODY = eINSTANCE.getDocumentTemplate_Body();

		/**
         * The meta object literal for the '<em><b>Document</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute DOCUMENT_TEMPLATE__DOCUMENT = eINSTANCE.getDocumentTemplate_Document();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.OptionValueMapImpl <em>Option Value Map</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.OptionValueMapImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getOptionValueMap()
         * @generated
         */
        EClass OPTION_VALUE_MAP = eINSTANCE.getOptionValueMap();

        /**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPTION_VALUE_MAP__KEY = eINSTANCE.getOptionValueMap_Key();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute OPTION_VALUE_MAP__VALUE = eINSTANCE.getOptionValueMap_Value();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.AbstractImageImpl <em>Abstract Image</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.AbstractImageImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAbstractImage()
         * @generated
         */
        EClass ABSTRACT_IMAGE = eINSTANCE.getAbstractImage();

        /**
         * The meta object literal for the '<em><b>Legend</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_IMAGE__LEGEND = eINSTANCE.getAbstractImage_Legend();

        /**
         * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_IMAGE__HEIGHT = eINSTANCE.getAbstractImage_Height();

        /**
         * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_IMAGE__WIDTH = eINSTANCE.getAbstractImage_Width();

        /**
         * The meta object literal for the '<em><b>Legend POS</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_IMAGE__LEGEND_POS = eINSTANCE.getAbstractImage_LegendPOS();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl <em>Abstract Provider Client</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.AbstractProviderClientImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAbstractProviderClient()
         * @generated
         */
        EClass ABSTRACT_PROVIDER_CLIENT = eINSTANCE.getAbstractProviderClient();

        /**
         * The meta object literal for the '<em><b>Option Value Map</b></em>' map feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP = eINSTANCE.getAbstractProviderClient_OptionValueMap();

        /**
         * The meta object literal for the '<em><b>Provider</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute ABSTRACT_PROVIDER_CLIENT__PROVIDER = eINSTANCE.getAbstractProviderClient_Provider();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.BookmarkImpl <em>Bookmark</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.BookmarkImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBookmark()
         * @generated
         */
        EClass BOOKMARK = eINSTANCE.getBookmark();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute BOOKMARK__NAME = eINSTANCE.getBookmark_Name();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.LinkImpl <em>Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.LinkImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getLink()
         * @generated
         */
        EClass LINK = eINSTANCE.getLink();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINK__NAME = eINSTANCE.getLink_Name();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINK__TEXT = eINSTANCE.getLink_Text();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.TableClientImpl <em>Table Client</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.TableClientImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTableClient()
         * @generated
         */
        EClass TABLE_CLIENT = eINSTANCE.getTableClient();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.ImageImpl <em>Image</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.ImageImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getImage()
         * @generated
         */
		EClass IMAGE = eINSTANCE.getImage();

		/**
         * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute IMAGE__FILE_NAME = eINSTANCE.getImage_FileName();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.DefaultImpl <em>Default</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.DefaultImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDefault()
         * @generated
         */
		EClass DEFAULT = eINSTANCE.getDefault();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.CompoundImpl <em>Compound</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.CompoundImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCompound()
         * @generated
         */
		EClass COMPOUND = eINSTANCE.getCompound();

		/**
         * The meta object literal for the '<em><b>Sub Constructs</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference COMPOUND__SUB_CONSTRUCTS = eINSTANCE.getCompound_SubConstructs();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.TemplateImpl <em>Template</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.TemplateImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplate()
         * @generated
         */
		EClass TEMPLATE = eINSTANCE.getTemplate();

		/**
         * The meta object literal for the '<em><b>Template Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute TEMPLATE__TEMPLATE_NAME = eINSTANCE.getTemplate_TemplateName();

		/**
         * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute TEMPLATE__BODY = eINSTANCE.getTemplate_Body();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.RepresentationImpl <em>Representation</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.RepresentationImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRepresentation()
         * @generated
         */
		EClass REPRESENTATION = eINSTANCE.getRepresentation();

		/**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute REPRESENTATION__QUERY = eINSTANCE.getRepresentation_Query();

		/**
         * The meta object literal for the '<em><b>Representation Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute REPRESENTATION__REPRESENTATION_ID = eINSTANCE.getRepresentation_RepresentationId();

		/**
         * The meta object literal for the '<em><b>Representation Title</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute REPRESENTATION__REPRESENTATION_TITLE = eINSTANCE.getRepresentation_RepresentationTitle();

        /**
         * The meta object literal for the '<em><b>Activated Layers</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute REPRESENTATION__ACTIVATED_LAYERS = eINSTANCE.getRepresentation_ActivatedLayers();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.StaticFragmentImpl <em>Static Fragment</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.impl.StaticFragmentImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getStaticFragment()
         * @generated
         */
		EClass STATIC_FRAGMENT = eINSTANCE.getStaticFragment();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.QueryBehavior <em>Query Behavior</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.QueryBehavior
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getQueryBehavior()
         * @generated
         */
		EEnum QUERY_BEHAVIOR = eINSTANCE.getQueryBehavior();

		/**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.POSITION <em>POSITION</em>}' enum.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.template.POSITION
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getPOSITION()
         * @generated
         */
		EEnum POSITION = eINSTANCE.getPOSITION();

		/**
         * The meta object literal for the '<em>Document</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.apache.poi.xwpf.usermodel.XWPFDocument
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocument()
         * @generated
         */
        EDataType DOCUMENT = eINSTANCE.getDocument();

        /**
         * The meta object literal for the '<em>WTable</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.apache.poi.xwpf.usermodel.XWPFTable
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTable()
         * @generated
         */
		EDataType WTABLE = eINSTANCE.getWTable();

		/**
         * The meta object literal for the '<em>Run</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.apache.poi.xwpf.usermodel.XWPFRun
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRun()
         * @generated
         */
		EDataType RUN = eINSTANCE.getRun();

		/**
         * The meta object literal for the '<em>Ast Result</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAstResult()
         * @generated
         */
		EDataType AST_RESULT = eINSTANCE.getAstResult();

		/**
         * The meta object literal for the '<em>Validation Message</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.parser.TemplateValidationMessage
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplateValidationMessage()
         * @generated
         */
        EDataType TEMPLATE_VALIDATION_MESSAGE = eINSTANCE.getTemplateValidationMessage();

        /**
         * The meta object literal for the '<em>WTable Row</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.apache.poi.xwpf.usermodel.XWPFTableRow
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableRow()
         * @generated
         */
		EDataType WTABLE_ROW = eINSTANCE.getWTableRow();

		/**
         * The meta object literal for the '<em>WTable Cell</em>' data type.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.apache.poi.xwpf.usermodel.XWPFTableCell
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableCell()
         * @generated
         */
		EDataType WTABLE_CELL = eINSTANCE.getWTableCell();

        /**
         * The meta object literal for the '<em>Provider</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.obeonetwork.m2doc.provider.IProvider
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getProvider()
         * @generated
         */
        EDataType PROVIDER = eINSTANCE.getProvider();

        /**
         * The meta object literal for the '<em>Body</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.apache.poi.xwpf.usermodel.IBody
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBody()
         * @generated
         */
        EDataType BODY = eINSTANCE.getBody();

	}

} //TemplatePackage
