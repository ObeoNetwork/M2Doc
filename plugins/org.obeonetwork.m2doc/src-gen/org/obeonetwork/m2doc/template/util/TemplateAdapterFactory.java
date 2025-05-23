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
package org.obeonetwork.m2doc.template.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.IGenerateable;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Parameter;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.Statement;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.template.TemplatePackage
 * @generated
 */
public class TemplateAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static TemplatePackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemplateAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = TemplatePackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TemplateSwitch<Adapter> modelSwitch = new TemplateSwitch<Adapter>() {
        @Override
        public Adapter caseIConstruct(IConstruct object) {
            return createIConstructAdapter();
        }

        @Override
        public Adapter caseComment(Comment object) {
            return createCommentAdapter();
        }

        @Override
        public Adapter caseConditional(Conditional object) {
            return createConditionalAdapter();
        }

        @Override
        public Adapter caseRepetition(Repetition object) {
            return createRepetitionAdapter();
        }

        @Override
        public Adapter caseUserDoc(UserDoc object) {
            return createUserDocAdapter();
        }

        @Override
        public Adapter caseUserContent(UserContent object) {
            return createUserContentAdapter();
        }

        @Override
        public Adapter caseQuery(Query object) {
            return createQueryAdapter();
        }

        @Override
        public Adapter caseBlock(Block object) {
            return createBlockAdapter();
        }

        @Override
        public Adapter caseStatement(Statement object) {
            return createStatementAdapter();
        }

        @Override
        public Adapter caseTemplate(Template object) {
            return createTemplateAdapter();
        }

        @Override
        public Adapter caseParameter(Parameter object) {
            return createParameterAdapter();
        }

        @Override
        public Adapter caseStaticFragment(StaticFragment object) {
            return createStaticFragmentAdapter();
        }

        @Override
        public Adapter caseTable(Table object) {
            return createTableAdapter();
        }

        @Override
        public Adapter caseRow(Row object) {
            return createRowAdapter();
        }

        @Override
        public Adapter caseCell(Cell object) {
            return createCellAdapter();
        }

        @Override
        public Adapter caseDocumentTemplate(DocumentTemplate object) {
            return createDocumentTemplateAdapter();
        }

        @Override
        public Adapter caseBookmark(Bookmark object) {
            return createBookmarkAdapter();
        }

        @Override
        public Adapter caseLink(Link object) {
            return createLinkAdapter();
        }

        @Override
        public Adapter caseLet(Let object) {
            return createLetAdapter();
        }

        @Override
        public Adapter caseContentControl(ContentControl object) {
            return createContentControlAdapter();
        }

        @Override
        public Adapter caseIGenerateable(IGenerateable object) {
            return createIGenerateableAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.IConstruct <em>IConstruct</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.IConstruct
     * @generated
     */
    public Adapter createIConstructAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Comment <em>Comment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Comment
     * @generated
     */
    public Adapter createCommentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Conditional <em>Conditional</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Conditional
     * @generated
     */
    public Adapter createConditionalAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Repetition <em>Repetition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Repetition
     * @generated
     */
    public Adapter createRepetitionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.UserDoc <em>User Doc</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.UserDoc
     * @generated
     */
    public Adapter createUserDocAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.UserContent <em>User Content</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.UserContent
     * @generated
     */
    public Adapter createUserContentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Query <em>Query</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Query
     * @generated
     */
    public Adapter createQueryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Block <em>Block</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Block
     * @generated
     */
    public Adapter createBlockAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Statement <em>Statement</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Statement
     * @generated
     */
    public Adapter createStatementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Template <em>Template</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Template
     * @generated
     */
    public Adapter createTemplateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Parameter <em>Parameter</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Parameter
     * @generated
     */
    public Adapter createParameterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.StaticFragment <em>Static Fragment</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.StaticFragment
     * @generated
     */
    public Adapter createStaticFragmentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Table <em>Table</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Table
     * @generated
     */
    public Adapter createTableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Row <em>Row</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Row
     * @generated
     */
    public Adapter createRowAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Cell <em>Cell</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Cell
     * @generated
     */
    public Adapter createCellAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.DocumentTemplate <em>Document Template</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.DocumentTemplate
     * @generated
     */
    public Adapter createDocumentTemplateAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Bookmark <em>Bookmark</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Bookmark
     * @generated
     */
    public Adapter createBookmarkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Link <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Link
     * @generated
     */
    public Adapter createLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.Let <em>Let</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.Let
     * @generated
     */
    public Adapter createLetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.ContentControl <em>Content Control</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.ContentControl
     * @generated
     */
    public Adapter createContentControlAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.obeonetwork.m2doc.template.IGenerateable <em>IGenerateable</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.obeonetwork.m2doc.template.IGenerateable
     * @generated
     */
    public Adapter createIGenerateableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // TemplateAdapterFactory
