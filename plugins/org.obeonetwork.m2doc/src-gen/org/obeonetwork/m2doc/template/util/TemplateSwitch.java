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
package org.obeonetwork.m2doc.template.util;

import java.util.Map;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;
import org.obeonetwork.m2doc.template.*;

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
 * @see org.obeonetwork.m2doc.template.TemplatePackage
 * @generated
 */
public class TemplateSwitch<T> extends Switch<T> {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static TemplatePackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
     * @param ePackage the package in question.
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
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case TemplatePackage.ABSTRACT_CONSTRUCT: {
                AbstractConstruct abstractConstruct = (AbstractConstruct)theEObject;
                T result = caseAbstractConstruct(abstractConstruct);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.CONDITIONNAL: {
                Conditionnal conditionnal = (Conditionnal)theEObject;
                T result = caseConditionnal(conditionnal);
                if (result == null) result = caseCompound(conditionnal);
                if (result == null) result = caseAbstractConstruct(conditionnal);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.REPETITION: {
                Repetition repetition = (Repetition)theEObject;
                T result = caseRepetition(repetition);
                if (result == null) result = caseCompound(repetition);
                if (result == null) result = caseAbstractConstruct(repetition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.USER_DOC: {
                UserDoc userDoc = (UserDoc)theEObject;
                T result = caseUserDoc(userDoc);
                if (result == null) result = caseCompound(userDoc);
                if (result == null) result = caseAbstractConstruct(userDoc);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.USER_CONTENT: {
                UserContent userContent = (UserContent)theEObject;
                T result = caseUserContent(userContent);
                if (result == null) result = caseCompound(userContent);
                if (result == null) result = caseAbstractConstruct(userContent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.QUERY: {
                Query query = (Query)theEObject;
                T result = caseQuery(query);
                if (result == null) result = caseAbstractConstruct(query);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.TABLE_MERGE: {
                TableMerge tableMerge = (TableMerge)theEObject;
                T result = caseTableMerge(tableMerge);
                if (result == null) result = caseCompound(tableMerge);
                if (result == null) result = caseAbstractConstruct(tableMerge);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.IMAGE: {
                Image image = (Image)theEObject;
                T result = caseImage(image);
                if (result == null) result = caseAbstractImage(image);
                if (result == null) result = caseAbstractProviderClient(image);
                if (result == null) result = caseAbstractConstruct(image);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.DEFAULT: {
                Default default_ = (Default)theEObject;
                T result = caseDefault(default_);
                if (result == null) result = caseCompound(default_);
                if (result == null) result = caseAbstractConstruct(default_);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.COMPOUND: {
                Compound compound = (Compound)theEObject;
                T result = caseCompound(compound);
                if (result == null) result = caseAbstractConstruct(compound);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.TEMPLATE: {
                Template template = (Template)theEObject;
                T result = caseTemplate(template);
                if (result == null) result = caseCompound(template);
                if (result == null) result = caseAbstractConstruct(template);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.REPRESENTATION: {
                Representation representation = (Representation)theEObject;
                T result = caseRepresentation(representation);
                if (result == null) result = caseAbstractImage(representation);
                if (result == null) result = caseAbstractProviderClient(representation);
                if (result == null) result = caseAbstractConstruct(representation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.STATIC_FRAGMENT: {
                StaticFragment staticFragment = (StaticFragment)theEObject;
                T result = caseStaticFragment(staticFragment);
                if (result == null) result = caseAbstractConstruct(staticFragment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.TABLE: {
                Table table = (Table)theEObject;
                T result = caseTable(table);
                if (result == null) result = caseAbstractConstruct(table);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.ROW: {
                Row row = (Row)theEObject;
                T result = caseRow(row);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.CELL: {
                Cell cell = (Cell)theEObject;
                T result = caseCell(cell);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.DOCUMENT_TEMPLATE: {
                DocumentTemplate documentTemplate = (DocumentTemplate)theEObject;
                T result = caseDocumentTemplate(documentTemplate);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.OPTION_VALUE_MAP: {
                @SuppressWarnings("unchecked") Map.Entry<String, Object> optionValueMap = (Map.Entry<String, Object>)theEObject;
                T result = caseOptionValueMap(optionValueMap);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.ABSTRACT_IMAGE: {
                AbstractImage abstractImage = (AbstractImage)theEObject;
                T result = caseAbstractImage(abstractImage);
                if (result == null) result = caseAbstractProviderClient(abstractImage);
                if (result == null) result = caseAbstractConstruct(abstractImage);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT: {
                AbstractProviderClient abstractProviderClient = (AbstractProviderClient)theEObject;
                T result = caseAbstractProviderClient(abstractProviderClient);
                if (result == null) result = caseAbstractConstruct(abstractProviderClient);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.BOOKMARK: {
                Bookmark bookmark = (Bookmark)theEObject;
                T result = caseBookmark(bookmark);
                if (result == null) result = caseCompound(bookmark);
                if (result == null) result = caseAbstractConstruct(bookmark);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.LINK: {
                Link link = (Link)theEObject;
                T result = caseLink(link);
                if (result == null) result = caseAbstractConstruct(link);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case TemplatePackage.TABLE_CLIENT: {
                TableClient tableClient = (TableClient)theEObject;
                T result = caseTableClient(tableClient);
                if (result == null) result = caseAbstractProviderClient(tableClient);
                if (result == null) result = caseAbstractConstruct(tableClient);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Construct</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Construct</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractConstruct(AbstractConstruct object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Conditionnal</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Conditionnal</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseConditionnal(Conditionnal object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Repetition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
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
     * @param object the target of the switch.
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
     * @param object the target of the switch.
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
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Query</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseQuery(Query object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table Merge</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Merge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTableMerge(TableMerge object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
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
     * @param object the target of the switch.
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
     * @param object the target of the switch.
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
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDocumentTemplate(DocumentTemplate object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Option Value Map</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Option Value Map</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseOptionValueMap(Map.Entry<String, Object> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Image</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractImage(AbstractImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Provider Client</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Provider Client</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractProviderClient(AbstractProviderClient object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bookmark</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
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
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLink(Link object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Table Client</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Table Client</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTableClient(TableClient object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Image</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseImage(Image object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Default</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Default</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDefault(Default object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Compound</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Compound</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCompound(Compound object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Template</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Template</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTemplate(Template object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Representation</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Representation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRepresentation(Representation object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Static Fragment</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Static Fragment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStaticFragment(StaticFragment object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //TemplateSwitch
