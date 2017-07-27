/**
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 */
package org.obeonetwork.m2doc.template.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.obeonetwork.m2doc.template.Block;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Comment;
import org.obeonetwork.m2doc.template.Conditional;
import org.obeonetwork.m2doc.template.ContentControl;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.IConstruct;
import org.obeonetwork.m2doc.template.Let;
import org.obeonetwork.m2doc.template.Link;
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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * 
 * @see org.obeonetwork.m2doc.template.TemplatePackage
 * @generated
 */
public class TemplateSwitch<T> extends Switch<T> {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static TemplatePackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemplateSwitch() {
        if (modelPackage == null) {
            modelPackage = TemplatePackage.eINSTANCE;
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
            case TemplatePackage.ICONSTRUCT: {
                IConstruct iConstruct = (IConstruct) theEObject;
                T result = caseIConstruct(iConstruct);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.COMMENT: {
                Comment comment = (Comment) theEObject;
                T result = caseComment(comment);
                if (result == null)
                    result = caseStatement(comment);
                if (result == null)
                    result = caseIConstruct(comment);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.CONDITIONAL: {
                Conditional conditional = (Conditional) theEObject;
                T result = caseConditional(conditional);
                if (result == null)
                    result = caseStatement(conditional);
                if (result == null)
                    result = caseIConstruct(conditional);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.REPETITION: {
                Repetition repetition = (Repetition) theEObject;
                T result = caseRepetition(repetition);
                if (result == null)
                    result = caseStatement(repetition);
                if (result == null)
                    result = caseIConstruct(repetition);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.USER_DOC: {
                UserDoc userDoc = (UserDoc) theEObject;
                T result = caseUserDoc(userDoc);
                if (result == null)
                    result = caseStatement(userDoc);
                if (result == null)
                    result = caseIConstruct(userDoc);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.USER_CONTENT: {
                UserContent userContent = (UserContent) theEObject;
                T result = caseUserContent(userContent);
                if (result == null)
                    result = caseStatement(userContent);
                if (result == null)
                    result = caseIConstruct(userContent);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.QUERY: {
                Query query = (Query) theEObject;
                T result = caseQuery(query);
                if (result == null)
                    result = caseStatement(query);
                if (result == null)
                    result = caseIConstruct(query);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.BLOCK: {
                Block block = (Block) theEObject;
                T result = caseBlock(block);
                if (result == null)
                    result = caseIConstruct(block);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.STATEMENT: {
                Statement statement = (Statement) theEObject;
                T result = caseStatement(statement);
                if (result == null)
                    result = caseIConstruct(statement);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.TEMPLATE: {
                Template template = (Template) theEObject;
                T result = caseTemplate(template);
                if (result == null)
                    result = caseIConstruct(template);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.STATIC_FRAGMENT: {
                StaticFragment staticFragment = (StaticFragment) theEObject;
                T result = caseStaticFragment(staticFragment);
                if (result == null)
                    result = caseStatement(staticFragment);
                if (result == null)
                    result = caseIConstruct(staticFragment);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.TABLE: {
                Table table = (Table) theEObject;
                T result = caseTable(table);
                if (result == null)
                    result = caseStatement(table);
                if (result == null)
                    result = caseIConstruct(table);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.ROW: {
                Row row = (Row) theEObject;
                T result = caseRow(row);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.CELL: {
                Cell cell = (Cell) theEObject;
                T result = caseCell(cell);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.DOCUMENT_TEMPLATE: {
                DocumentTemplate documentTemplate = (DocumentTemplate) theEObject;
                T result = caseDocumentTemplate(documentTemplate);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.BOOKMARK: {
                Bookmark bookmark = (Bookmark) theEObject;
                T result = caseBookmark(bookmark);
                if (result == null)
                    result = caseStatement(bookmark);
                if (result == null)
                    result = caseIConstruct(bookmark);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.LINK: {
                Link link = (Link) theEObject;
                T result = caseLink(link);
                if (result == null)
                    result = caseStatement(link);
                if (result == null)
                    result = caseIConstruct(link);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.LET: {
                Let let = (Let) theEObject;
                T result = caseLet(let);
                if (result == null)
                    result = caseStatement(let);
                if (result == null)
                    result = caseIConstruct(let);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.CONTENT_CONTROL: {
                ContentControl contentControl = (ContentControl) theEObject;
                T result = caseContentControl(contentControl);
                if (result == null)
                    result = caseStatement(contentControl);
                if (result == null)
                    result = caseIConstruct(contentControl);
                if (result == null)
                    result = defaultCase(theEObject);
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>IConstruct</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>IConstruct</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIConstruct(IConstruct object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComment(Comment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Conditional</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Conditional</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseConditional(Conditional object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Repetition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Repetition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRepetition(Repetition object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Doc</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Doc</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserDoc(UserDoc object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>User Content</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>User Content</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseUserContent(UserContent object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Query</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseQuery(Query object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Block</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Block</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBlock(Block object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Statement</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Statement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStatement(Statement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Template</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemplate(Template object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Static Fragment</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Static Fragment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStaticFragment(StaticFragment object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTable(Table object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Row</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Row</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRow(Row object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Cell</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Cell</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCell(Cell object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Document Template</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentTemplate(DocumentTemplate object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bookmark</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bookmark</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBookmark(Bookmark object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLink(Link object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Let</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Let</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLet(Let object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Content Control</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Content Control</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContentControl(ContentControl object) {
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

} // TemplateSwitch
