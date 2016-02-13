/**
 */
package org.eclipse.gendoc2.template;

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
 * @see org.eclipse.gendoc2.template.TemplateFactory
 * @model kind="package"
 * @generated
 */
public interface TemplatePackage extends EPackage {
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
	String eNS_URI = "http://www.eclipse.org/mdt/gendoc/template/1.0";

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
	TemplatePackage eINSTANCE = org.eclipse.gendoc2.template.impl.TemplatePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.AbstractConstructImpl <em>Abstract Construct</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.AbstractConstructImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getAbstractConstruct()
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
	 * The number of structural features of the '<em>Abstract Construct</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRUCT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Abstract Construct</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ABSTRACT_CONSTRUCT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.CompoundImpl <em>Compound</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.CompoundImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getCompound()
	 * @generated
	 */
	int COMPOUND = 7;

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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.ConditionnalImpl <em>Conditionnal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.ConditionnalImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getConditionnal()
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
	 * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONNAL__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
	 * The feature id for the '<em><b>Alternatives</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONNAL__ALTERNATIVES = COMPOUND_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Else</b></em>' containment reference list.
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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.RepetitionImpl <em>Repetition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.RepetitionImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getRepetition()
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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.QueryImpl <em>Query</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.QueryImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getQuery()
	 * @generated
	 */
	int QUERY = 3;

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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.TableImpl <em>Table</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.TableImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getTable()
	 * @generated
	 */
	int TABLE = 4;

	/**
	 * The feature id for the '<em><b>Style Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__STYLE_RUN = COMPOUND__STYLE_RUN;

	/**
	 * The feature id for the '<em><b>Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__RUNS = COMPOUND__RUNS;

	/**
	 * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__CLOSING_RUNS = COMPOUND__CLOSING_RUNS;

	/**
	 * The feature id for the '<em><b>Sub Constructs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__SUB_CONSTRUCTS = COMPOUND__SUB_CONSTRUCTS;

	/**
	 * The feature id for the '<em><b>Legend</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE__LEGEND = COMPOUND_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_FEATURE_COUNT = COMPOUND_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Table</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TABLE_OPERATION_COUNT = COMPOUND_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.ImageImpl <em>Image</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.ImageImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getImage()
	 * @generated
	 */
	int IMAGE = 5;

	/**
	 * The feature id for the '<em><b>Style Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
	 * The feature id for the '<em><b>Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
	 * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
	 * The feature id for the '<em><b>Legend</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE__LEGEND = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Image</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMAGE_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.DefaultImpl <em>Default</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.DefaultImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getDefault()
	 * @generated
	 */
	int DEFAULT = 6;

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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.TemplateImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 8;

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
	 * The feature id for the '<em><b>Document</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__DOCUMENT = COMPOUND_FEATURE_COUNT + 1;

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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.RepresentationImpl <em>Representation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.RepresentationImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getRepresentation()
	 * @generated
	 */
	int REPRESENTATION = 9;

	/**
	 * The feature id for the '<em><b>Style Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
	 * The feature id for the '<em><b>Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
	 * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
	 * The feature id for the '<em><b>Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION__QUERY = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Representation Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION__REPRESENTATION_ID = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Representation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.StaticFragmentImpl <em>Static Fragment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.StaticFragmentImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getStaticFragment()
	 * @generated
	 */
	int STATIC_FRAGMENT = 10;

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
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.impl.VarRefImpl <em>Var Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.impl.VarRefImpl
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getVarRef()
	 * @generated
	 */
	int VAR_REF = 11;

	/**
	 * The feature id for the '<em><b>Style Run</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VAR_REF__STYLE_RUN = ABSTRACT_CONSTRUCT__STYLE_RUN;

	/**
	 * The feature id for the '<em><b>Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VAR_REF__RUNS = ABSTRACT_CONSTRUCT__RUNS;

	/**
	 * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VAR_REF__CLOSING_RUNS = ABSTRACT_CONSTRUCT__CLOSING_RUNS;

	/**
	 * The feature id for the '<em><b>Var Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VAR_REF__VAR_NAME = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Var Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VAR_REF_FEATURE_COUNT = ABSTRACT_CONSTRUCT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Var Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VAR_REF_OPERATION_COUNT = ABSTRACT_CONSTRUCT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.gendoc2.template.QueryBehavior <em>Query Behavior</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.gendoc2.template.QueryBehavior
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getQueryBehavior()
	 * @generated
	 */
	int QUERY_BEHAVIOR = 12;

	/**
	 * The meta object id for the '<em>Document</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.poi.xwpf.usermodel.XWPFDocument
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 13;

	/**
	 * The meta object id for the '<em>Paragraph</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.poi.xwpf.usermodel.XWPFParagraph
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getParagraph()
	 * @generated
	 */
	int PARAGRAPH = 14;

	/**
	 * The meta object id for the '<em>Run</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.apache.poi.xwpf.usermodel.XWPFRun
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getRun()
	 * @generated
	 */
	int RUN = 15;


	/**
	 * The meta object id for the '<em>Ast Result</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
	 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getAstResult()
	 * @generated
	 */
	int AST_RESULT = 16;

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.AbstractConstruct <em>Abstract Construct</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Abstract Construct</em>'.
	 * @see org.eclipse.gendoc2.template.AbstractConstruct
	 * @generated
	 */
	EClass getAbstractConstruct();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.AbstractConstruct#getStyleRun <em>Style Run</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style Run</em>'.
	 * @see org.eclipse.gendoc2.template.AbstractConstruct#getStyleRun()
	 * @see #getAbstractConstruct()
	 * @generated
	 */
	EAttribute getAbstractConstruct_StyleRun();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gendoc2.template.AbstractConstruct#getRuns <em>Runs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Runs</em>'.
	 * @see org.eclipse.gendoc2.template.AbstractConstruct#getRuns()
	 * @see #getAbstractConstruct()
	 * @generated
	 */
	EAttribute getAbstractConstruct_Runs();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.gendoc2.template.AbstractConstruct#getClosingRuns <em>Closing Runs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Closing Runs</em>'.
	 * @see org.eclipse.gendoc2.template.AbstractConstruct#getClosingRuns()
	 * @see #getAbstractConstruct()
	 * @generated
	 */
	EAttribute getAbstractConstruct_ClosingRuns();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Conditionnal <em>Conditionnal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditionnal</em>'.
	 * @see org.eclipse.gendoc2.template.Conditionnal
	 * @generated
	 */
	EClass getConditionnal();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Conditionnal#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see org.eclipse.gendoc2.template.Conditionnal#getQuery()
	 * @see #getConditionnal()
	 * @generated
	 */
	EAttribute getConditionnal_Query();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gendoc2.template.Conditionnal#getAlternatives <em>Alternatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Alternatives</em>'.
	 * @see org.eclipse.gendoc2.template.Conditionnal#getAlternatives()
	 * @see #getConditionnal()
	 * @generated
	 */
	EReference getConditionnal_Alternatives();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gendoc2.template.Conditionnal#getElse <em>Else</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Else</em>'.
	 * @see org.eclipse.gendoc2.template.Conditionnal#getElse()
	 * @see #getConditionnal()
	 * @generated
	 */
	EReference getConditionnal_Else();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Repetition <em>Repetition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repetition</em>'.
	 * @see org.eclipse.gendoc2.template.Repetition
	 * @generated
	 */
	EClass getRepetition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Repetition#getIterationVar <em>Iteration Var</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Iteration Var</em>'.
	 * @see org.eclipse.gendoc2.template.Repetition#getIterationVar()
	 * @see #getRepetition()
	 * @generated
	 */
	EAttribute getRepetition_IterationVar();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Repetition#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see org.eclipse.gendoc2.template.Repetition#getQuery()
	 * @see #getRepetition()
	 * @generated
	 */
	EAttribute getRepetition_Query();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Query <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Query</em>'.
	 * @see org.eclipse.gendoc2.template.Query
	 * @generated
	 */
	EClass getQuery();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Query#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see org.eclipse.gendoc2.template.Query#getQuery()
	 * @see #getQuery()
	 * @generated
	 */
	EAttribute getQuery_Query();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Query#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Behavior</em>'.
	 * @see org.eclipse.gendoc2.template.Query#getBehavior()
	 * @see #getQuery()
	 * @generated
	 */
	EAttribute getQuery_Behavior();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Table <em>Table</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Table</em>'.
	 * @see org.eclipse.gendoc2.template.Table
	 * @generated
	 */
	EClass getTable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Table#getLegend <em>Legend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Legend</em>'.
	 * @see org.eclipse.gendoc2.template.Table#getLegend()
	 * @see #getTable()
	 * @generated
	 */
	EAttribute getTable_Legend();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Image <em>Image</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Image</em>'.
	 * @see org.eclipse.gendoc2.template.Image
	 * @generated
	 */
	EClass getImage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Image#getLegend <em>Legend</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Legend</em>'.
	 * @see org.eclipse.gendoc2.template.Image#getLegend()
	 * @see #getImage()
	 * @generated
	 */
	EAttribute getImage_Legend();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Default <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default</em>'.
	 * @see org.eclipse.gendoc2.template.Default
	 * @generated
	 */
	EClass getDefault();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Compound <em>Compound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compound</em>'.
	 * @see org.eclipse.gendoc2.template.Compound
	 * @generated
	 */
	EClass getCompound();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.gendoc2.template.Compound#getSubConstructs <em>Sub Constructs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Constructs</em>'.
	 * @see org.eclipse.gendoc2.template.Compound#getSubConstructs()
	 * @see #getCompound()
	 * @generated
	 */
	EReference getCompound_SubConstructs();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see org.eclipse.gendoc2.template.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Template#getTemplateName <em>Template Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Template Name</em>'.
	 * @see org.eclipse.gendoc2.template.Template#getTemplateName()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_TemplateName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Template#getDocument <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Document</em>'.
	 * @see org.eclipse.gendoc2.template.Template#getDocument()
	 * @see #getTemplate()
	 * @generated
	 */
	EAttribute getTemplate_Document();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.Representation <em>Representation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Representation</em>'.
	 * @see org.eclipse.gendoc2.template.Representation
	 * @generated
	 */
	EClass getRepresentation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Representation#getQuery <em>Query</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Query</em>'.
	 * @see org.eclipse.gendoc2.template.Representation#getQuery()
	 * @see #getRepresentation()
	 * @generated
	 */
	EAttribute getRepresentation_Query();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.Representation#getRepresentationId <em>Representation Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Representation Id</em>'.
	 * @see org.eclipse.gendoc2.template.Representation#getRepresentationId()
	 * @see #getRepresentation()
	 * @generated
	 */
	EAttribute getRepresentation_RepresentationId();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.StaticFragment <em>Static Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Static Fragment</em>'.
	 * @see org.eclipse.gendoc2.template.StaticFragment
	 * @generated
	 */
	EClass getStaticFragment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.gendoc2.template.VarRef <em>Var Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Var Ref</em>'.
	 * @see org.eclipse.gendoc2.template.VarRef
	 * @generated
	 */
	EClass getVarRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.gendoc2.template.VarRef#getVarName <em>Var Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Var Name</em>'.
	 * @see org.eclipse.gendoc2.template.VarRef#getVarName()
	 * @see #getVarRef()
	 * @generated
	 */
	EAttribute getVarRef_VarName();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.gendoc2.template.QueryBehavior <em>Query Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Query Behavior</em>'.
	 * @see org.eclipse.gendoc2.template.QueryBehavior
	 * @generated
	 */
	EEnum getQueryBehavior();

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
	 * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFParagraph <em>Paragraph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Paragraph</em>'.
	 * @see org.apache.poi.xwpf.usermodel.XWPFParagraph
	 * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFParagraph"
	 * @generated
	 */
	EDataType getParagraph();

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
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.AbstractConstructImpl <em>Abstract Construct</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.AbstractConstructImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getAbstractConstruct()
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
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.ConditionnalImpl <em>Conditionnal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.ConditionnalImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getConditionnal()
		 * @generated
		 */
		EClass CONDITIONNAL = eINSTANCE.getConditionnal();

		/**
		 * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITIONNAL__QUERY = eINSTANCE.getConditionnal_Query();

		/**
		 * The meta object literal for the '<em><b>Alternatives</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONNAL__ALTERNATIVES = eINSTANCE.getConditionnal_Alternatives();

		/**
		 * The meta object literal for the '<em><b>Else</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITIONNAL__ELSE = eINSTANCE.getConditionnal_Else();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.RepetitionImpl <em>Repetition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.RepetitionImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getRepetition()
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
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.QueryImpl <em>Query</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.QueryImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getQuery()
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
		 * The meta object literal for the '<em><b>Behavior</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUERY__BEHAVIOR = eINSTANCE.getQuery_Behavior();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.TableImpl <em>Table</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.TableImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getTable()
		 * @generated
		 */
		EClass TABLE = eINSTANCE.getTable();

		/**
		 * The meta object literal for the '<em><b>Legend</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TABLE__LEGEND = eINSTANCE.getTable_Legend();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.ImageImpl <em>Image</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.ImageImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getImage()
		 * @generated
		 */
		EClass IMAGE = eINSTANCE.getImage();

		/**
		 * The meta object literal for the '<em><b>Legend</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMAGE__LEGEND = eINSTANCE.getImage_Legend();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.DefaultImpl <em>Default</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.DefaultImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getDefault()
		 * @generated
		 */
		EClass DEFAULT = eINSTANCE.getDefault();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.CompoundImpl <em>Compound</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.CompoundImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getCompound()
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
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.TemplateImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getTemplate()
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
		 * The meta object literal for the '<em><b>Document</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPLATE__DOCUMENT = eINSTANCE.getTemplate_Document();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.RepresentationImpl <em>Representation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.RepresentationImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getRepresentation()
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
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.StaticFragmentImpl <em>Static Fragment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.StaticFragmentImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getStaticFragment()
		 * @generated
		 */
		EClass STATIC_FRAGMENT = eINSTANCE.getStaticFragment();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.impl.VarRefImpl <em>Var Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.impl.VarRefImpl
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getVarRef()
		 * @generated
		 */
		EClass VAR_REF = eINSTANCE.getVarRef();

		/**
		 * The meta object literal for the '<em><b>Var Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VAR_REF__VAR_NAME = eINSTANCE.getVarRef_VarName();

		/**
		 * The meta object literal for the '{@link org.eclipse.gendoc2.template.QueryBehavior <em>Query Behavior</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.gendoc2.template.QueryBehavior
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getQueryBehavior()
		 * @generated
		 */
		EEnum QUERY_BEHAVIOR = eINSTANCE.getQueryBehavior();

		/**
		 * The meta object literal for the '<em>Document</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.apache.poi.xwpf.usermodel.XWPFDocument
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getDocument()
		 * @generated
		 */
		EDataType DOCUMENT = eINSTANCE.getDocument();

		/**
		 * The meta object literal for the '<em>Paragraph</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.apache.poi.xwpf.usermodel.XWPFParagraph
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getParagraph()
		 * @generated
		 */
		EDataType PARAGRAPH = eINSTANCE.getParagraph();

		/**
		 * The meta object literal for the '<em>Run</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.apache.poi.xwpf.usermodel.XWPFRun
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getRun()
		 * @generated
		 */
		EDataType RUN = eINSTANCE.getRun();

		/**
		 * The meta object literal for the '<em>Ast Result</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
		 * @see org.eclipse.gendoc2.template.impl.TemplatePackageImpl#getAstResult()
		 * @generated
		 */
		EDataType AST_RESULT = eINSTANCE.getAstResult();

	}

} //TemplatePackage
