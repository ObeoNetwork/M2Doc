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
package org.obeonetwork.m2doc.template.impl;

import java.util.Map;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.template.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplateFactoryImpl extends EFactoryImpl implements TemplateFactory {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static TemplateFactory init() {
        try {
            TemplateFactory theTemplateFactory = (TemplateFactory)EPackage.Registry.INSTANCE.getEFactory(TemplatePackage.eNS_URI);
            if (theTemplateFactory != null) {
                return theTemplateFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new TemplateFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TemplateFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case TemplatePackage.CONDITIONNAL: return createConditionnal();
            case TemplatePackage.REPETITION: return createRepetition();
            case TemplatePackage.USER_DOC: return createUserDoc();
            case TemplatePackage.USER_CONTENT: return createUserContent();
            case TemplatePackage.QUERY: return createQuery();
            case TemplatePackage.TABLE_MERGE: return createTableMerge();
            case TemplatePackage.IMAGE: return createImage();
            case TemplatePackage.DEFAULT: return createDefault();
            case TemplatePackage.TEMPLATE: return createTemplate();
            case TemplatePackage.REPRESENTATION: return createRepresentation();
            case TemplatePackage.STATIC_FRAGMENT: return createStaticFragment();
            case TemplatePackage.TABLE: return createTable();
            case TemplatePackage.ROW: return createRow();
            case TemplatePackage.CELL: return createCell();
            case TemplatePackage.DOCUMENT_TEMPLATE: return createDocumentTemplate();
            case TemplatePackage.OPTION_VALUE_MAP: return (EObject)createOptionValueMap();
            case TemplatePackage.BOOKMARK: return createBookmark();
            case TemplatePackage.LINK: return createLink();
            case TemplatePackage.TABLE_CLIENT: return createTableClient();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case TemplatePackage.QUERY_BEHAVIOR:
                return createQueryBehaviorFromString(eDataType, initialValue);
            case TemplatePackage.POSITION:
                return createPOSITIONFromString(eDataType, initialValue);
            case TemplatePackage.DOCUMENT:
                return createDocumentFromString(eDataType, initialValue);
            case TemplatePackage.WTABLE:
                return createWTableFromString(eDataType, initialValue);
            case TemplatePackage.RUN:
                return createRunFromString(eDataType, initialValue);
            case TemplatePackage.AST_RESULT:
                return createAstResultFromString(eDataType, initialValue);
            case TemplatePackage.TEMPLATE_VALIDATION_MESSAGE:
                return createTemplateValidationMessageFromString(eDataType, initialValue);
            case TemplatePackage.WTABLE_ROW:
                return createWTableRowFromString(eDataType, initialValue);
            case TemplatePackage.WTABLE_CELL:
                return createWTableCellFromString(eDataType, initialValue);
            case TemplatePackage.PROVIDER:
                return createProviderFromString(eDataType, initialValue);
            case TemplatePackage.BODY:
                return createBodyFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case TemplatePackage.QUERY_BEHAVIOR:
                return convertQueryBehaviorToString(eDataType, instanceValue);
            case TemplatePackage.POSITION:
                return convertPOSITIONToString(eDataType, instanceValue);
            case TemplatePackage.DOCUMENT:
                return convertDocumentToString(eDataType, instanceValue);
            case TemplatePackage.WTABLE:
                return convertWTableToString(eDataType, instanceValue);
            case TemplatePackage.RUN:
                return convertRunToString(eDataType, instanceValue);
            case TemplatePackage.AST_RESULT:
                return convertAstResultToString(eDataType, instanceValue);
            case TemplatePackage.TEMPLATE_VALIDATION_MESSAGE:
                return convertTemplateValidationMessageToString(eDataType, instanceValue);
            case TemplatePackage.WTABLE_ROW:
                return convertWTableRowToString(eDataType, instanceValue);
            case TemplatePackage.WTABLE_CELL:
                return convertWTableCellToString(eDataType, instanceValue);
            case TemplatePackage.PROVIDER:
                return convertProviderToString(eDataType, instanceValue);
            case TemplatePackage.BODY:
                return convertBodyToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Conditionnal createConditionnal() {
        ConditionnalImpl conditionnal = new ConditionnalImpl();
        return conditionnal;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Repetition createRepetition() {
        RepetitionImpl repetition = new RepetitionImpl();
        return repetition;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UserDoc createUserDoc() {
        UserDocImpl userDoc = new UserDocImpl();
        return userDoc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public UserContent createUserContent() {
        UserContentImpl userContent = new UserContentImpl();
        return userContent;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Query createQuery() {
        QueryImpl query = new QueryImpl();
        return query;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TableMerge createTableMerge() {
        TableMergeImpl tableMerge = new TableMergeImpl();
        return tableMerge;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Table createTable() {
        TableImpl table = new TableImpl();
        return table;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Row createRow() {
        RowImpl row = new RowImpl();
        return row;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Cell createCell() {
        CellImpl cell = new CellImpl();
        return cell;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public DocumentTemplate createDocumentTemplate() {
        DocumentTemplateImpl documentTemplate = new DocumentTemplateImpl();
        return documentTemplate;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Map.Entry<String, Object> createOptionValueMap() {
        OptionValueMapImpl optionValueMap = new OptionValueMapImpl();
        return optionValueMap;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Bookmark createBookmark() {
        BookmarkImpl bookmark = new BookmarkImpl();
        return bookmark;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Link createLink() {
        LinkImpl link = new LinkImpl();
        return link;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TableClient createTableClient() {
        TableClientImpl tableClient = new TableClientImpl();
        return tableClient;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Image createImage() {
        ImageImpl image = new ImageImpl();
        return image;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Default createDefault() {
        DefaultImpl default_ = new DefaultImpl();
        return default_;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Template createTemplate() {
        TemplateImpl template = new TemplateImpl();
        return template;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Representation createRepresentation() {
        RepresentationImpl representation = new RepresentationImpl();
        return representation;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public StaticFragment createStaticFragment() {
        StaticFragmentImpl staticFragment = new StaticFragmentImpl();
        return staticFragment;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public QueryBehavior createQueryBehaviorFromString(EDataType eDataType, String initialValue) {
        QueryBehavior result = QueryBehavior.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertQueryBehaviorToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public POSITION createPOSITIONFromString(EDataType eDataType, String initialValue) {
        POSITION result = POSITION.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertPOSITIONToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XWPFDocument createDocumentFromString(EDataType eDataType, String initialValue) {
        return (XWPFDocument)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertDocumentToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public XWPFTable createWTableFromString(EDataType eDataType, String initialValue) {
        return (XWPFTable)super.createFromString(eDataType, initialValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertWTableToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public XWPFRun createRunFromString(EDataType eDataType, String initialValue) {
        return (XWPFRun)super.createFromString(eDataType, initialValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertRunToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public AstResult createAstResultFromString(EDataType eDataType, String initialValue) {
        return (AstResult)super.createFromString(eDataType, initialValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertAstResultToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TemplateValidationMessage createTemplateValidationMessageFromString(EDataType eDataType, String initialValue) {
        return (TemplateValidationMessage)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTemplateValidationMessageToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public XWPFTableRow createWTableRowFromString(EDataType eDataType, String initialValue) {
        return (XWPFTableRow)super.createFromString(eDataType, initialValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertWTableRowToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public XWPFTableCell createWTableCellFromString(EDataType eDataType, String initialValue) {
        return (XWPFTableCell)super.createFromString(eDataType, initialValue);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String convertWTableCellToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IProvider createProviderFromString(EDataType eDataType, String initialValue) {
        return (IProvider)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertProviderToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IBody createBodyFromString(EDataType eDataType, String initialValue) {
        return (IBody)super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertBodyToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public TemplatePackage getTemplatePackage() {
        return (TemplatePackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static TemplatePackage getPackage() {
        return TemplatePackage.eINSTANCE;
    }

} //TemplateFactoryImpl
