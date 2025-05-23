/**
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
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
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.template.TemplateFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore documentation='This metamodel describes the M2Doc abstract syntax tree.'"
 * @generated
 */
public interface TemplatePackage extends EPackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "template";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://www.obeonetwork.org/m2doc/template/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_PREFIX = "template";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    TemplatePackage eINSTANCE = org.obeonetwork.m2doc.template.impl.TemplatePackageImpl.init();

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.IGenerateable <em>IGenerateable</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.IGenerateable
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getIGenerateable()
     * @generated
     */
    int IGENERATEABLE = 20;

    /**
     * The number of structural features of the '<em>IGenerateable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IGENERATEABLE_FEATURE_COUNT = 0;

    /**
     * The number of operations of the '<em>IGenerateable</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int IGENERATEABLE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.IConstruct <em>IConstruct</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.IConstruct
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getIConstruct()
     * @generated
     */
    int ICONSTRUCT = 0;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ICONSTRUCT__STYLE_RUN = IGENERATEABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ICONSTRUCT__RUNS = IGENERATEABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ICONSTRUCT__CLOSING_RUNS = IGENERATEABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ICONSTRUCT__VALIDATION_MESSAGES = IGENERATEABLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>IConstruct</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ICONSTRUCT_FEATURE_COUNT = IGENERATEABLE_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>IConstruct</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ICONSTRUCT_OPERATION_COUNT = IGENERATEABLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.Statement <em>Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.Statement
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getStatement()
     * @generated
     */
    int STATEMENT = 8;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATEMENT__STYLE_RUN = ICONSTRUCT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATEMENT__RUNS = ICONSTRUCT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATEMENT__CLOSING_RUNS = ICONSTRUCT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATEMENT__VALIDATION_MESSAGES = ICONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The number of structural features of the '<em>Statement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATEMENT_FEATURE_COUNT = ICONSTRUCT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Statement</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATEMENT_OPERATION_COUNT = ICONSTRUCT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.CommentImpl <em>Comment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.CommentImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getComment()
     * @generated
     */
    int COMMENT = 1;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT__TEXT = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Comment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Comment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int COMMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.ConditionalImpl <em>Conditional</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.ConditionalImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getConditional()
     * @generated
     */
    int CONDITIONAL = 2;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__CONDITION = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Then</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__THEN = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Else</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL__ELSE = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Conditional</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Conditional</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONDITIONAL_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.RepetitionImpl <em>Repetition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.RepetitionImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRepetition()
     * @generated
     */
    int REPETITION = 3;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Iteration Var</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__ITERATION_VAR = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__QUERY = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION__BODY = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Repetition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Repetition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int REPETITION_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.UserDocImpl <em>User Doc</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.UserDocImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserDoc()
     * @generated
     */
    int USER_DOC = 4;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC__ID = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC__BODY = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>User Doc</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>User Doc</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_DOC_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.UserContentImpl <em>User Content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.UserContentImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserContent()
     * @generated
     */
    int USER_CONTENT = 5;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT__ID = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT__BODY = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>User Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>User Content</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int USER_CONTENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.QueryImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getQuery()
     * @generated
     */
    int QUERY = 6;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Query</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY__QUERY = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Query</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int QUERY_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.BlockImpl <em>Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.BlockImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBlock()
     * @generated
     */
    int BLOCK = 7;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK__STYLE_RUN = ICONSTRUCT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK__RUNS = ICONSTRUCT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK__CLOSING_RUNS = ICONSTRUCT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK__VALIDATION_MESSAGES = ICONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Statements</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK__STATEMENTS = ICONSTRUCT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Block</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK_FEATURE_COUNT = ICONSTRUCT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Block</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BLOCK_OPERATION_COUNT = ICONSTRUCT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.TemplateImpl <em>Template</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.TemplateImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplate()
     * @generated
     */
    int TEMPLATE = 9;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__STYLE_RUN = ICONSTRUCT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__RUNS = ICONSTRUCT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__CLOSING_RUNS = ICONSTRUCT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__VALIDATION_MESSAGES = ICONSTRUCT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Visibility</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__VISIBILITY = ICONSTRUCT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__NAME = ICONSTRUCT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__PARAMETERS = ICONSTRUCT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__BODY = ICONSTRUCT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Document Template</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE__DOCUMENT_TEMPLATE = ICONSTRUCT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Template</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_FEATURE_COUNT = ICONSTRUCT_FEATURE_COUNT + 5;

    /**
     * The number of operations of the '<em>Template</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TEMPLATE_OPERATION_COUNT = ICONSTRUCT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.ParameterImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getParameter()
     * @generated
     */
    int PARAMETER = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARAMETER__NAME = 0;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARAMETER__TYPE = 1;

    /**
     * The number of structural features of the '<em>Parameter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARAMETER_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Parameter</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int PARAMETER_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.StaticFragmentImpl <em>Static Fragment</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.StaticFragmentImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getStaticFragment()
     * @generated
     */
    int STATIC_FRAGMENT = 11;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The number of structural features of the '<em>Static Fragment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Static Fragment</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATIC_FRAGMENT_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.TableImpl <em>Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.TableImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTable()
     * @generated
     */
    int TABLE = 12;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Rows</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE__ROWS = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Table</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE__TABLE = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Table</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Table</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int TABLE_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.RowImpl <em>Row</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.RowImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRow()
     * @generated
     */
    int ROW = 13;

    /**
     * The feature id for the '<em><b>Cells</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROW__CELLS = 0;

    /**
     * The feature id for the '<em><b>Table Row</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROW__TABLE_ROW = 1;

    /**
     * The number of structural features of the '<em>Row</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROW_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Row</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROW_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.CellImpl <em>Cell</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.CellImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCell()
     * @generated
     */
    int CELL = 14;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CELL__BODY = 0;

    /**
     * The feature id for the '<em><b>Table Cell</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CELL__TABLE_CELL = 1;

    /**
     * The number of structural features of the '<em>Cell</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CELL_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Cell</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CELL_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl <em>Document Template</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocumentTemplate()
     * @generated
     */
    int DOCUMENT_TEMPLATE = 15;

    /**
     * The feature id for the '<em><b>Headers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__HEADERS = IGENERATEABLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Footers</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__FOOTERS = IGENERATEABLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__BODY = IGENERATEABLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Input Stream</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__INPUT_STREAM = IGENERATEABLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Opc Package</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__OPC_PACKAGE = IGENERATEABLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Document</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__DOCUMENT = IGENERATEABLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Templates</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE__TEMPLATES = IGENERATEABLE_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Document Template</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE_FEATURE_COUNT = IGENERATEABLE_FEATURE_COUNT + 7;

    /**
     * The number of operations of the '<em>Document Template</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int DOCUMENT_TEMPLATE_OPERATION_COUNT = IGENERATEABLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.BookmarkImpl <em>Bookmark</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.BookmarkImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBookmark()
     * @generated
     */
    int BOOKMARK = 16;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK__NAME = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK__BODY = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Bookmark</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Bookmark</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int BOOKMARK_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.LinkImpl <em>Link</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.LinkImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getLink()
     * @generated
     */
    int LINK = 17;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK__NAME = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Text</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK__TEXT = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LINK_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.LetImpl <em>Let</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.LetImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getLet()
     * @generated
     */
    int LET = 18;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__NAME = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__VALUE = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET__BODY = STATEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Let</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Let</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int LET_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.impl.ContentControlImpl <em>Content Control</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.impl.ContentControlImpl
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getContentControl()
     * @generated
     */
    int CONTENT_CONTROL = 19;

    /**
     * The feature id for the '<em><b>Style Run</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL__STYLE_RUN = STATEMENT__STYLE_RUN;

    /**
     * The feature id for the '<em><b>Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL__RUNS = STATEMENT__RUNS;

    /**
     * The feature id for the '<em><b>Closing Runs</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL__CLOSING_RUNS = STATEMENT__CLOSING_RUNS;

    /**
     * The feature id for the '<em><b>Validation Messages</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL__VALIDATION_MESSAGES = STATEMENT__VALIDATION_MESSAGES;

    /**
     * The feature id for the '<em><b>Block</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL__BLOCK = STATEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Content Control</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL_FEATURE_COUNT = STATEMENT_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Content Control</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTENT_CONTROL_OPERATION_COUNT = STATEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.POSITION <em>POSITION</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.POSITION
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getPOSITION()
     * @generated
     */
    int POSITION = 21;

    /**
     * The meta object id for the '{@link org.obeonetwork.m2doc.template.Visibility <em>Visibility</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.template.Visibility
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getVisibility()
     * @generated
     */
    int VISIBILITY = 22;

    /**
     * The meta object id for the '<em>Input Stream</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see java.io.InputStream
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getInputStream()
     * @generated
     */
    int INPUT_STREAM = 23;

    /**
     * The meta object id for the '<em>OPC Package</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.openxml4j.opc.OPCPackage
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getOPCPackage()
     * @generated
     */
    int OPC_PACKAGE = 24;

    /**
     * The meta object id for the '<em>Document</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.xwpf.usermodel.XWPFDocument
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocument()
     * @generated
     */
    int DOCUMENT = 25;

    /**
     * The meta object id for the '<em>WTable</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.xwpf.usermodel.XWPFTable
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTable()
     * @generated
     */
    int WTABLE = 26;

    /**
     * The meta object id for the '<em>Run</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.xwpf.usermodel.XWPFRun
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRun()
     * @generated
     */
    int RUN = 27;

    /**
     * The meta object id for the '<em>Ast Result</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAstResult()
     * @generated
     */
    int AST_RESULT = 28;

    /**
     * The meta object id for the '<em>Validation Message</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.obeonetwork.m2doc.parser.TemplateValidationMessage
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplateValidationMessage()
     * @generated
     */
    int TEMPLATE_VALIDATION_MESSAGE = 29;

    /**
     * The meta object id for the '<em>WTable Row</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.xwpf.usermodel.XWPFTableRow
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableRow()
     * @generated
     */
    int WTABLE_ROW = 30;

    /**
     * The meta object id for the '<em>WTable Cell</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.xwpf.usermodel.XWPFTableCell
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableCell()
     * @generated
     */
    int WTABLE_CELL = 31;

    /**
     * The meta object id for the '<em>Body</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.apache.poi.xwpf.usermodel.IBody
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBody()
     * @generated
     */
    int BODY = 32;

    /**
     * The meta object id for the '<em>CT Sdt Block</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock
     * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCTSdtBlock()
     * @generated
     */
    int CT_SDT_BLOCK = 33;

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.IConstruct <em>IConstruct</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>IConstruct</em>'.
     * @see org.obeonetwork.m2doc.template.IConstruct
     * @generated
     */
    EClass getIConstruct();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.IConstruct#getStyleRun <em>Style Run</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Style Run</em>'.
     * @see org.obeonetwork.m2doc.template.IConstruct#getStyleRun()
     * @see #getIConstruct()
     * @generated
     */
    EAttribute getIConstruct_StyleRun();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.IConstruct#getRuns <em>Runs</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Runs</em>'.
     * @see org.obeonetwork.m2doc.template.IConstruct#getRuns()
     * @see #getIConstruct()
     * @generated
     */
    EAttribute getIConstruct_Runs();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.IConstruct#getClosingRuns <em>Closing
     * Runs</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Closing Runs</em>'.
     * @see org.obeonetwork.m2doc.template.IConstruct#getClosingRuns()
     * @see #getIConstruct()
     * @generated
     */
    EAttribute getIConstruct_ClosingRuns();

    /**
     * Returns the meta object for the attribute list '{@link org.obeonetwork.m2doc.template.IConstruct#getValidationMessages <em>Validation
     * Messages</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute list '<em>Validation Messages</em>'.
     * @see org.obeonetwork.m2doc.template.IConstruct#getValidationMessages()
     * @see #getIConstruct()
     * @generated
     */
    EAttribute getIConstruct_ValidationMessages();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Comment <em>Comment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Comment</em>'.
     * @see org.obeonetwork.m2doc.template.Comment
     * @generated
     */
    EClass getComment();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Comment#getText <em>Text</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see org.obeonetwork.m2doc.template.Comment#getText()
     * @see #getComment()
     * @generated
     */
    EAttribute getComment_Text();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Conditional <em>Conditional</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Conditional</em>'.
     * @see org.obeonetwork.m2doc.template.Conditional
     * @generated
     */
    EClass getConditional();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Conditional#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Condition</em>'.
     * @see org.obeonetwork.m2doc.template.Conditional#getCondition()
     * @see #getConditional()
     * @generated
     */
    EAttribute getConditional_Condition();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Conditional#getThen <em>Then</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Then</em>'.
     * @see org.obeonetwork.m2doc.template.Conditional#getThen()
     * @see #getConditional()
     * @generated
     */
    EReference getConditional_Then();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Conditional#getElse <em>Else</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Else</em>'.
     * @see org.obeonetwork.m2doc.template.Conditional#getElse()
     * @see #getConditional()
     * @generated
     */
    EReference getConditional_Else();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Repetition <em>Repetition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Repetition</em>'.
     * @see org.obeonetwork.m2doc.template.Repetition
     * @generated
     */
    EClass getRepetition();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Repetition#getIterationVar <em>Iteration Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * 
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Repetition#getQuery()
     * @see #getRepetition()
     * @generated
     */
    EAttribute getRepetition_Query();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Repetition#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.Repetition#getBody()
     * @see #getRepetition()
     * @generated
     */
    EReference getRepetition_Body();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.UserDoc <em>User Doc</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User Doc</em>'.
     * @see org.obeonetwork.m2doc.template.UserDoc
     * @generated
     */
    EClass getUserDoc();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.UserDoc#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.obeonetwork.m2doc.template.UserDoc#getId()
     * @see #getUserDoc()
     * @generated
     */
    EAttribute getUserDoc_Id();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.UserDoc#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.UserDoc#getBody()
     * @see #getUserDoc()
     * @generated
     */
    EReference getUserDoc_Body();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.UserContent <em>User Content</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>User Content</em>'.
     * @see org.obeonetwork.m2doc.template.UserContent
     * @generated
     */
    EClass getUserContent();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.UserContent#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.obeonetwork.m2doc.template.UserContent#getId()
     * @see #getUserContent()
     * @generated
     */
    EAttribute getUserContent_Id();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.UserContent#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.UserContent#getBody()
     * @see #getUserContent()
     * @generated
     */
    EReference getUserContent_Body();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Query
     * @generated
     */
    EClass getQuery();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Query#getQuery <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Query</em>'.
     * @see org.obeonetwork.m2doc.template.Query#getQuery()
     * @see #getQuery()
     * @generated
     */
    EAttribute getQuery_Query();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Block <em>Block</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Block</em>'.
     * @see org.obeonetwork.m2doc.template.Block
     * @generated
     */
    EClass getBlock();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Block#getStatements
     * <em>Statements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Statements</em>'.
     * @see org.obeonetwork.m2doc.template.Block#getStatements()
     * @see #getBlock()
     * @generated
     */
    EReference getBlock_Statements();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Statement <em>Statement</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Statement</em>'.
     * @see org.obeonetwork.m2doc.template.Statement
     * @generated
     */
    EClass getStatement();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Template <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Template</em>'.
     * @see org.obeonetwork.m2doc.template.Template
     * @generated
     */
    EClass getTemplate();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Template#getVisibility <em>Visibility</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Visibility</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getVisibility()
     * @see #getTemplate()
     * @generated
     */
    EAttribute getTemplate_Visibility();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Template#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getName()
     * @see #getTemplate()
     * @generated
     */
    EAttribute getTemplate_Name();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Template#getParameters
     * <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getParameters()
     * @see #getTemplate()
     * @generated
     */
    EReference getTemplate_Parameters();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Template#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getBody()
     * @see #getTemplate()
     * @generated
     */
    EReference getTemplate_Body();

    /**
     * Returns the meta object for the container reference '{@link org.obeonetwork.m2doc.template.Template#getDocumentTemplate <em>Document
     * Template</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Document Template</em>'.
     * @see org.obeonetwork.m2doc.template.Template#getDocumentTemplate()
     * @see #getTemplate()
     * @generated
     */
    EReference getTemplate_DocumentTemplate();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Parameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Parameter</em>'.
     * @see org.obeonetwork.m2doc.template.Parameter
     * @generated
     */
    EClass getParameter();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Parameter#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.template.Parameter#getName()
     * @see #getParameter()
     * @generated
     */
    EAttribute getParameter_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Parameter#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.obeonetwork.m2doc.template.Parameter#getType()
     * @see #getParameter()
     * @generated
     */
    EAttribute getParameter_Type();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.StaticFragment <em>Static Fragment</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Static Fragment</em>'.
     * @see org.obeonetwork.m2doc.template.StaticFragment
     * @generated
     */
    EClass getStaticFragment();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Table <em>Table</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Table</em>'.
     * @see org.obeonetwork.m2doc.template.Table
     * @generated
     */
    EClass getTable();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Table#getRows <em>Rows</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * 
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
     * 
     * @return the meta object for class '<em>Row</em>'.
     * @see org.obeonetwork.m2doc.template.Row
     * @generated
     */
    EClass getRow();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.Row#getCells <em>Cells</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * 
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
     * 
     * @return the meta object for class '<em>Cell</em>'.
     * @see org.obeonetwork.m2doc.template.Cell
     * @generated
     */
    EClass getCell();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Cell#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.Cell#getBody()
     * @see #getCell()
     * @generated
     */
    EReference getCell_Body();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Cell#getTableCell <em>Table Cell</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * 
     * @return the meta object for class '<em>Document Template</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate
     * @generated
     */
    EClass getDocumentTemplate();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getHeaders
     * <em>Headers</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Headers</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getHeaders()
     * @see #getDocumentTemplate()
     * @generated
     */
    EReference getDocumentTemplate_Headers();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getFooters
     * <em>Footers</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Footers</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getFooters()
     * @see #getDocumentTemplate()
     * @generated
     */
    EReference getDocumentTemplate_Footers();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getBody
     * <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getBody()
     * @see #getDocumentTemplate()
     * @generated
     */
    EReference getDocumentTemplate_Body();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getInputStream <em>Input
     * Stream</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Input Stream</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getInputStream()
     * @see #getDocumentTemplate()
     * @generated
     */
    EAttribute getDocumentTemplate_InputStream();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getOpcPackage <em>Opc
     * Package</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Opc Package</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getOpcPackage()
     * @see #getDocumentTemplate()
     * @generated
     */
    EAttribute getDocumentTemplate_OpcPackage();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getDocument <em>Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Document</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getDocument()
     * @see #getDocumentTemplate()
     * @generated
     */
    EAttribute getDocumentTemplate_Document();

    /**
     * Returns the meta object for the containment reference list '{@link org.obeonetwork.m2doc.template.DocumentTemplate#getTemplates
     * <em>Templates</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Templates</em>'.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate#getTemplates()
     * @see #getDocumentTemplate()
     * @generated
     */
    EReference getDocumentTemplate_Templates();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Bookmark <em>Bookmark</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Bookmark</em>'.
     * @see org.obeonetwork.m2doc.template.Bookmark
     * @generated
     */
    EClass getBookmark();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Bookmark#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.template.Bookmark#getName()
     * @see #getBookmark()
     * @generated
     */
    EAttribute getBookmark_Name();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Bookmark#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.Bookmark#getBody()
     * @see #getBookmark()
     * @generated
     */
    EReference getBookmark_Body();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Link <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Link</em>'.
     * @see org.obeonetwork.m2doc.template.Link
     * @generated
     */
    EClass getLink();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Link#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * 
     * @return the meta object for the attribute '<em>Text</em>'.
     * @see org.obeonetwork.m2doc.template.Link#getText()
     * @see #getLink()
     * @generated
     */
    EAttribute getLink_Text();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.Let <em>Let</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Let</em>'.
     * @see org.obeonetwork.m2doc.template.Let
     * @generated
     */
    EClass getLet();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Let#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.obeonetwork.m2doc.template.Let#getName()
     * @see #getLet()
     * @generated
     */
    EAttribute getLet_Name();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.Let#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.obeonetwork.m2doc.template.Let#getValue()
     * @see #getLet()
     * @generated
     */
    EAttribute getLet_Value();

    /**
     * Returns the meta object for the containment reference '{@link org.obeonetwork.m2doc.template.Let#getBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Body</em>'.
     * @see org.obeonetwork.m2doc.template.Let#getBody()
     * @see #getLet()
     * @generated
     */
    EReference getLet_Body();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.ContentControl <em>Content Control</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Content Control</em>'.
     * @see org.obeonetwork.m2doc.template.ContentControl
     * @generated
     */
    EClass getContentControl();

    /**
     * Returns the meta object for the attribute '{@link org.obeonetwork.m2doc.template.ContentControl#getBlock <em>Block</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Block</em>'.
     * @see org.obeonetwork.m2doc.template.ContentControl#getBlock()
     * @see #getContentControl()
     * @generated
     */
    EAttribute getContentControl_Block();

    /**
     * Returns the meta object for class '{@link org.obeonetwork.m2doc.template.IGenerateable <em>IGenerateable</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>IGenerateable</em>'.
     * @see org.obeonetwork.m2doc.template.IGenerateable
     * @generated
     */
    EClass getIGenerateable();

    /**
     * Returns the meta object for enum '{@link org.obeonetwork.m2doc.template.POSITION <em>POSITION</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>POSITION</em>'.
     * @see org.obeonetwork.m2doc.template.POSITION
     * @generated
     */
    EEnum getPOSITION();

    /**
     * Returns the meta object for enum '{@link org.obeonetwork.m2doc.template.Visibility <em>Visibility</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for enum '<em>Visibility</em>'.
     * @see org.obeonetwork.m2doc.template.Visibility
     * @generated
     */
    EEnum getVisibility();

    /**
     * Returns the meta object for data type '{@link java.io.InputStream <em>Input Stream</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Input Stream</em>'.
     * @see java.io.InputStream
     * @model instanceClass="java.io.InputStream"
     * @generated
     */
    EDataType getInputStream();

    /**
     * Returns the meta object for data type '{@link org.apache.poi.openxml4j.opc.OPCPackage <em>OPC Package</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>OPC Package</em>'.
     * @see org.apache.poi.openxml4j.opc.OPCPackage
     * @model instanceClass="org.apache.poi.openxml4j.opc.OPCPackage"
     * @generated
     */
    EDataType getOPCPackage();

    /**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.XWPFDocument <em>Document</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
     * @return the meta object for data type '<em>WTable Cell</em>'.
     * @see org.apache.poi.xwpf.usermodel.XWPFTableCell
     * @model instanceClass="org.apache.poi.xwpf.usermodel.XWPFTableCell"
     * @generated
     */
    EDataType getWTableCell();

    /**
     * Returns the meta object for data type '{@link org.apache.poi.xwpf.usermodel.IBody <em>Body</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>Body</em>'.
     * @see org.apache.poi.xwpf.usermodel.IBody
     * @model instanceClass="org.apache.poi.xwpf.usermodel.IBody"
     * @generated
     */
    EDataType getBody();

    /**
     * Returns the meta object for data type '{@link org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock <em>CT Sdt
     * Block</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the meta object for data type '<em>CT Sdt Block</em>'.
     * @see org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock
     * @model instanceClass="org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock"
     * @generated
     */
    EDataType getCTSdtBlock();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    TemplateFactory getTemplateFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.IConstruct <em>IConstruct</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.IConstruct
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getIConstruct()
         * @generated
         */
        EClass ICONSTRUCT = eINSTANCE.getIConstruct();

        /**
         * The meta object literal for the '<em><b>Style Run</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ICONSTRUCT__STYLE_RUN = eINSTANCE.getIConstruct_StyleRun();

        /**
         * The meta object literal for the '<em><b>Runs</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ICONSTRUCT__RUNS = eINSTANCE.getIConstruct_Runs();

        /**
         * The meta object literal for the '<em><b>Closing Runs</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ICONSTRUCT__CLOSING_RUNS = eINSTANCE.getIConstruct_ClosingRuns();

        /**
         * The meta object literal for the '<em><b>Validation Messages</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ICONSTRUCT__VALIDATION_MESSAGES = eINSTANCE.getIConstruct_ValidationMessages();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.CommentImpl <em>Comment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.CommentImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getComment()
         * @generated
         */
        EClass COMMENT = eINSTANCE.getComment();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute COMMENT__TEXT = eINSTANCE.getComment_Text();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.ConditionalImpl <em>Conditional</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.ConditionalImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getConditional()
         * @generated
         */
        EClass CONDITIONAL = eINSTANCE.getConditional();

        /**
         * The meta object literal for the '<em><b>Condition</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONDITIONAL__CONDITION = eINSTANCE.getConditional_Condition();

        /**
         * The meta object literal for the '<em><b>Then</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONDITIONAL__THEN = eINSTANCE.getConditional_Then();

        /**
         * The meta object literal for the '<em><b>Else</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONDITIONAL__ELSE = eINSTANCE.getConditional_Else();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.RepetitionImpl <em>Repetition</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.RepetitionImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRepetition()
         * @generated
         */
        EClass REPETITION = eINSTANCE.getRepetition();

        /**
         * The meta object literal for the '<em><b>Iteration Var</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPETITION__ITERATION_VAR = eINSTANCE.getRepetition_IterationVar();

        /**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute REPETITION__QUERY = eINSTANCE.getRepetition_Query();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference REPETITION__BODY = eINSTANCE.getRepetition_Body();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.UserDocImpl <em>User Doc</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.UserDocImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserDoc()
         * @generated
         */
        EClass USER_DOC = eINSTANCE.getUserDoc();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER_DOC__ID = eINSTANCE.getUserDoc_Id();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER_DOC__BODY = eINSTANCE.getUserDoc_Body();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.UserContentImpl <em>User Content</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.UserContentImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getUserContent()
         * @generated
         */
        EClass USER_CONTENT = eINSTANCE.getUserContent();

        /**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute USER_CONTENT__ID = eINSTANCE.getUserContent_Id();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference USER_CONTENT__BODY = eINSTANCE.getUserContent_Body();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.QueryImpl <em>Query</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.QueryImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getQuery()
         * @generated
         */
        EClass QUERY = eINSTANCE.getQuery();

        /**
         * The meta object literal for the '<em><b>Query</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute QUERY__QUERY = eINSTANCE.getQuery_Query();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.BlockImpl <em>Block</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.BlockImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBlock()
         * @generated
         */
        EClass BLOCK = eINSTANCE.getBlock();

        /**
         * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BLOCK__STATEMENTS = eINSTANCE.getBlock_Statements();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.Statement <em>Statement</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.Statement
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getStatement()
         * @generated
         */
        EClass STATEMENT = eINSTANCE.getStatement();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.TemplateImpl <em>Template</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.TemplateImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplate()
         * @generated
         */
        EClass TEMPLATE = eINSTANCE.getTemplate();

        /**
         * The meta object literal for the '<em><b>Visibility</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEMPLATE__VISIBILITY = eINSTANCE.getTemplate_Visibility();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TEMPLATE__NAME = eINSTANCE.getTemplate_Name();

        /**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE__PARAMETERS = eINSTANCE.getTemplate_Parameters();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE__BODY = eINSTANCE.getTemplate_Body();

        /**
         * The meta object literal for the '<em><b>Document Template</b></em>' container reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TEMPLATE__DOCUMENT_TEMPLATE = eINSTANCE.getTemplate_DocumentTemplate();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.ParameterImpl <em>Parameter</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.ParameterImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getParameter()
         * @generated
         */
        EClass PARAMETER = eINSTANCE.getParameter();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PARAMETER__NAME = eINSTANCE.getParameter_Name();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute PARAMETER__TYPE = eINSTANCE.getParameter_Type();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.StaticFragmentImpl <em>Static Fragment</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.StaticFragmentImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getStaticFragment()
         * @generated
         */
        EClass STATIC_FRAGMENT = eINSTANCE.getStaticFragment();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.TableImpl <em>Table</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.TableImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTable()
         * @generated
         */
        EClass TABLE = eINSTANCE.getTable();

        /**
         * The meta object literal for the '<em><b>Rows</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference TABLE__ROWS = eINSTANCE.getTable_Rows();

        /**
         * The meta object literal for the '<em><b>Table</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute TABLE__TABLE = eINSTANCE.getTable_Table();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.RowImpl <em>Row</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.RowImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRow()
         * @generated
         */
        EClass ROW = eINSTANCE.getRow();

        /**
         * The meta object literal for the '<em><b>Cells</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ROW__CELLS = eINSTANCE.getRow_Cells();

        /**
         * The meta object literal for the '<em><b>Table Row</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ROW__TABLE_ROW = eINSTANCE.getRow_TableRow();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.CellImpl <em>Cell</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.CellImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCell()
         * @generated
         */
        EClass CELL = eINSTANCE.getCell();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CELL__BODY = eINSTANCE.getCell_Body();

        /**
         * The meta object literal for the '<em><b>Table Cell</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CELL__TABLE_CELL = eINSTANCE.getCell_TableCell();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl <em>Document Template</em>}'
         * class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.DocumentTemplateImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocumentTemplate()
         * @generated
         */
        EClass DOCUMENT_TEMPLATE = eINSTANCE.getDocumentTemplate();

        /**
         * The meta object literal for the '<em><b>Headers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOCUMENT_TEMPLATE__HEADERS = eINSTANCE.getDocumentTemplate_Headers();

        /**
         * The meta object literal for the '<em><b>Footers</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOCUMENT_TEMPLATE__FOOTERS = eINSTANCE.getDocumentTemplate_Footers();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOCUMENT_TEMPLATE__BODY = eINSTANCE.getDocumentTemplate_Body();

        /**
         * The meta object literal for the '<em><b>Input Stream</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOCUMENT_TEMPLATE__INPUT_STREAM = eINSTANCE.getDocumentTemplate_InputStream();

        /**
         * The meta object literal for the '<em><b>Opc Package</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOCUMENT_TEMPLATE__OPC_PACKAGE = eINSTANCE.getDocumentTemplate_OpcPackage();

        /**
         * The meta object literal for the '<em><b>Document</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute DOCUMENT_TEMPLATE__DOCUMENT = eINSTANCE.getDocumentTemplate_Document();

        /**
         * The meta object literal for the '<em><b>Templates</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference DOCUMENT_TEMPLATE__TEMPLATES = eINSTANCE.getDocumentTemplate_Templates();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.BookmarkImpl <em>Bookmark</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.BookmarkImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBookmark()
         * @generated
         */
        EClass BOOKMARK = eINSTANCE.getBookmark();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute BOOKMARK__NAME = eINSTANCE.getBookmark_Name();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference BOOKMARK__BODY = eINSTANCE.getBookmark_Body();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.LinkImpl <em>Link</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.LinkImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getLink()
         * @generated
         */
        EClass LINK = eINSTANCE.getLink();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LINK__NAME = eINSTANCE.getLink_Name();

        /**
         * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LINK__TEXT = eINSTANCE.getLink_Text();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.LetImpl <em>Let</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.LetImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getLet()
         * @generated
         */
        EClass LET = eINSTANCE.getLet();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LET__NAME = eINSTANCE.getLet_Name();

        /**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute LET__VALUE = eINSTANCE.getLet_Value();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference LET__BODY = eINSTANCE.getLet_Body();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.impl.ContentControlImpl <em>Content Control</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.impl.ContentControlImpl
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getContentControl()
         * @generated
         */
        EClass CONTENT_CONTROL = eINSTANCE.getContentControl();

        /**
         * The meta object literal for the '<em><b>Block</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute CONTENT_CONTROL__BLOCK = eINSTANCE.getContentControl_Block();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.IGenerateable <em>IGenerateable</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.IGenerateable
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getIGenerateable()
         * @generated
         */
        EClass IGENERATEABLE = eINSTANCE.getIGenerateable();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.POSITION <em>POSITION</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.POSITION
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getPOSITION()
         * @generated
         */
        EEnum POSITION = eINSTANCE.getPOSITION();

        /**
         * The meta object literal for the '{@link org.obeonetwork.m2doc.template.Visibility <em>Visibility</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.template.Visibility
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getVisibility()
         * @generated
         */
        EEnum VISIBILITY = eINSTANCE.getVisibility();

        /**
         * The meta object literal for the '<em>Input Stream</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see java.io.InputStream
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getInputStream()
         * @generated
         */
        EDataType INPUT_STREAM = eINSTANCE.getInputStream();

        /**
         * The meta object literal for the '<em>OPC Package</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.openxml4j.opc.OPCPackage
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getOPCPackage()
         * @generated
         */
        EDataType OPC_PACKAGE = eINSTANCE.getOPCPackage();

        /**
         * The meta object literal for the '<em>Document</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.xwpf.usermodel.XWPFDocument
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getDocument()
         * @generated
         */
        EDataType DOCUMENT = eINSTANCE.getDocument();

        /**
         * The meta object literal for the '<em>WTable</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.xwpf.usermodel.XWPFTable
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTable()
         * @generated
         */
        EDataType WTABLE = eINSTANCE.getWTable();

        /**
         * The meta object literal for the '<em>Run</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.xwpf.usermodel.XWPFRun
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getRun()
         * @generated
         */
        EDataType RUN = eINSTANCE.getRun();

        /**
         * The meta object literal for the '<em>Ast Result</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getAstResult()
         * @generated
         */
        EDataType AST_RESULT = eINSTANCE.getAstResult();

        /**
         * The meta object literal for the '<em>Validation Message</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.obeonetwork.m2doc.parser.TemplateValidationMessage
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getTemplateValidationMessage()
         * @generated
         */
        EDataType TEMPLATE_VALIDATION_MESSAGE = eINSTANCE.getTemplateValidationMessage();

        /**
         * The meta object literal for the '<em>WTable Row</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.xwpf.usermodel.XWPFTableRow
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableRow()
         * @generated
         */
        EDataType WTABLE_ROW = eINSTANCE.getWTableRow();

        /**
         * The meta object literal for the '<em>WTable Cell</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.xwpf.usermodel.XWPFTableCell
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getWTableCell()
         * @generated
         */
        EDataType WTABLE_CELL = eINSTANCE.getWTableCell();

        /**
         * The meta object literal for the '<em>Body</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.apache.poi.xwpf.usermodel.IBody
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getBody()
         * @generated
         */
        EDataType BODY = eINSTANCE.getBody();

        /**
         * The meta object literal for the '<em>CT Sdt Block</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * 
         * @see org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock
         * @see org.obeonetwork.m2doc.template.impl.TemplatePackageImpl#getCTSdtBlock()
         * @generated
         */
        EDataType CT_SDT_BLOCK = eINSTANCE.getCTSdtBlock();

    }

} // TemplatePackage
