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
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.template.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.obeonetwork.m2doc.template.TemplatePackage
 * @generated
 */
public class TemplateValidator extends EObjectValidator {
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
	public static final TemplateValidator INSTANCE = new TemplateValidator();

	/**
     * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.common.util.Diagnostic#getSource()
     * @see org.eclipse.emf.common.util.Diagnostic#getCode()
     * @generated
     */
	public static final String DIAGNOSTIC_SOURCE = "org.obeonetwork.m2doc.template";

	/**
     * A constant with a fixed name that can be used as the base value for additional hand written constants.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
     * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TemplateValidator() {
        super();
    }

	/**
     * Returns the package of this validator switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EPackage getEPackage() {
      return TemplatePackage.eINSTANCE;
    }

	/**
     * Calls <code>validateXXX</code> for the corresponding classifier of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
        switch (classifierID) {
            case TemplatePackage.ABSTRACT_CONSTRUCT:
                return validateAbstractConstruct((AbstractConstruct)value, diagnostics, context);
            case TemplatePackage.CONDITIONNAL:
                return validateConditionnal((Conditionnal)value, diagnostics, context);
            case TemplatePackage.REPETITION:
                return validateRepetition((Repetition)value, diagnostics, context);
            case TemplatePackage.USER_DOC:
                return validateUserDoc((UserDoc)value, diagnostics, context);
            case TemplatePackage.USER_CONTENT:
                return validateUserContent((UserContent)value, diagnostics, context);
            case TemplatePackage.QUERY:
                return validateQuery((Query)value, diagnostics, context);
            case TemplatePackage.TABLE_MERGE:
                return validateTableMerge((TableMerge)value, diagnostics, context);
            case TemplatePackage.IMAGE:
                return validateImage((Image)value, diagnostics, context);
            case TemplatePackage.DEFAULT:
                return validateDefault((Default)value, diagnostics, context);
            case TemplatePackage.COMPOUND:
                return validateCompound((Compound)value, diagnostics, context);
            case TemplatePackage.TEMPLATE:
                return validateTemplate((Template)value, diagnostics, context);
            case TemplatePackage.REPRESENTATION:
                return validateRepresentation((Representation)value, diagnostics, context);
            case TemplatePackage.STATIC_FRAGMENT:
                return validateStaticFragment((StaticFragment)value, diagnostics, context);
            case TemplatePackage.TABLE:
                return validateTable((Table)value, diagnostics, context);
            case TemplatePackage.ROW:
                return validateRow((Row)value, diagnostics, context);
            case TemplatePackage.CELL:
                return validateCell((Cell)value, diagnostics, context);
            case TemplatePackage.DOCUMENT_TEMPLATE:
                return validateDocumentTemplate((DocumentTemplate)value, diagnostics, context);
            case TemplatePackage.OPTION_VALUE_MAP:
                return validateOptionValueMap((Map.Entry<?, ?>)value, diagnostics, context);
            case TemplatePackage.ABSTRACT_IMAGE:
                return validateAbstractImage((AbstractImage)value, diagnostics, context);
            case TemplatePackage.ABSTRACT_PROVIDER_CLIENT:
                return validateAbstractProviderClient((AbstractProviderClient)value, diagnostics, context);
            case TemplatePackage.BOOKMARK:
                return validateBookmark((Bookmark)value, diagnostics, context);
            case TemplatePackage.LINK:
                return validateLink((Link)value, diagnostics, context);
            case TemplatePackage.TABLE_CLIENT:
                return validateTableClient((TableClient)value, diagnostics, context);
            case TemplatePackage.QUERY_BEHAVIOR:
                return validateQueryBehavior((QueryBehavior)value, diagnostics, context);
            case TemplatePackage.POSITION:
                return validatePOSITION((POSITION)value, diagnostics, context);
            case TemplatePackage.DOCUMENT:
                return validateDocument((XWPFDocument)value, diagnostics, context);
            case TemplatePackage.WTABLE:
                return validateWTable((XWPFTable)value, diagnostics, context);
            case TemplatePackage.RUN:
                return validateRun((XWPFRun)value, diagnostics, context);
            case TemplatePackage.AST_RESULT:
                return validateAstResult((AstResult)value, diagnostics, context);
            case TemplatePackage.TEMPLATE_VALIDATION_MESSAGE:
                return validateTemplateValidationMessage((TemplateValidationMessage)value, diagnostics, context);
            case TemplatePackage.WTABLE_ROW:
                return validateWTableRow((XWPFTableRow)value, diagnostics, context);
            case TemplatePackage.WTABLE_CELL:
                return validateWTableCell((XWPFTableCell)value, diagnostics, context);
            case TemplatePackage.PROVIDER:
                return validateProvider((IProvider)value, diagnostics, context);
            case TemplatePackage.BODY:
                return validateBody((IBody)value, diagnostics, context);
            default:
                return true;
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateAbstractConstruct(AbstractConstruct abstractConstruct, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(abstractConstruct, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateConditionnal(Conditionnal conditionnal, DiagnosticChain diagnostics, Map<Object, Object> context) {
        if (!validate_NoCircularContainment(conditionnal, diagnostics, context)) return false;
        boolean result = validate_EveryMultiplicityConforms(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryDataValueConforms(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryProxyResolves(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_UniqueID(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryKeyUnique(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(conditionnal, diagnostics, context);
        if (result || diagnostics != null) result &= validateConditionnal_AlternativeExcludeElse(conditionnal, diagnostics, context);
        return result;
    }

	/**
     * Validates the AlternativeExcludeElse constraint of '<em>Conditionnal</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateConditionnal_AlternativeExcludeElse(Conditionnal conditionnal, DiagnosticChain diagnostics, Map<Object, Object> context) {
        // TODO implement the constraint
        // -> specify the condition that violates the constraint
        // -> verify the diagnostic details, including severity, code, and message
        // Ensure that you remove @generated or mark it @generated NOT
        if (false) {
            if (diagnostics != null) {
                diagnostics.add
                    (createDiagnostic
                        (Diagnostic.ERROR,
                         DIAGNOSTIC_SOURCE,
                         0,
                         "_UI_GenericConstraint_diagnostic",
                         new Object[] { "AlternativeExcludeElse", getObjectLabel(conditionnal, context) },
                         new Object[] { conditionnal },
                         context));
            }
            return false;
        }
        return true;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateRepetition(Repetition repetition, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(repetition, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUserDoc(UserDoc userDoc, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(userDoc, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateUserContent(UserContent userContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(userContent, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateQuery(Query query, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(query, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateTableMerge(TableMerge tableMerge, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(tableMerge, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateTable(Table table, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(table, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateRow(Row row, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(row, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateCell(Cell cell, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(cell, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateDocumentTemplate(DocumentTemplate documentTemplate, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(documentTemplate, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateOptionValueMap(Map.Entry<?, ?> optionValueMap, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint((EObject)optionValueMap, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAbstractImage(AbstractImage abstractImage, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(abstractImage, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateAbstractProviderClient(AbstractProviderClient abstractProviderClient, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(abstractProviderClient, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateBookmark(Bookmark bookmark, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(bookmark, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateLink(Link link, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(link, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTableClient(TableClient tableClient, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(tableClient, diagnostics, context);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateImage(Image image, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(image, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateDefault(Default default_, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(default_, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateCompound(Compound compound, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(compound, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateTemplate(Template template, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(template, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateRepresentation(Representation representation, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(representation, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateStaticFragment(StaticFragment staticFragment, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return validate_EveryDefaultConstraint(staticFragment, diagnostics, context);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateQueryBehavior(QueryBehavior queryBehavior, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validatePOSITION(POSITION position, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateDocument(XWPFDocument document, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateWTable(XWPFTable wTable, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateRun(XWPFRun run, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateAstResult(AstResult astResult, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateTemplateValidationMessage(TemplateValidationMessage templateValidationMessage, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateWTableRow(XWPFTableRow wTableRow, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean validateWTableCell(XWPFTableCell wTableCell, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateProvider(IProvider provider, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean validateBody(IBody body, DiagnosticChain diagnostics, Map<Object, Object> context) {
        return true;
    }

    /**
     * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ResourceLocator getResourceLocator() {
        // TODO
        // Specialize this to return a resource locator for messages specific to this validator.
        // Ensure that you remove @generated or mark it @generated NOT
        return super.getResourceLocator();
    }

} //TemplateValidator
