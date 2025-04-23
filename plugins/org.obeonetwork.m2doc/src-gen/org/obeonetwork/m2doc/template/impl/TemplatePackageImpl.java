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
package org.obeonetwork.m2doc.template.impl;

import java.io.InputStream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
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
import org.obeonetwork.m2doc.template.TemplateFactory;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.Visibility;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSdtBlock;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class TemplatePackageImpl extends EPackageImpl implements TemplatePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = " Copyright (c) 2016, 2025 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v2.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v20.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass iConstructEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass commentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionalEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass repetitionEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass userDocEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass userContentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass queryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass blockEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass statementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass templateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass parameterEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass staticFragmentEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass rowEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass cellEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass documentTemplateEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass bookmarkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass linkEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass letEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass contentControlEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass iGenerateableEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum positionEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EEnum visibilityEEnum = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType inputStreamEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType opcPackageEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType documentEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType wTableEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType runEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType astResultEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType templateValidationMessageEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType wTableRowEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType wTableCellEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType bodyEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType ctSdtBlockEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>
     * Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.obeonetwork.m2doc.template.TemplatePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private TemplatePackageImpl() {
        super(eNS_URI, TemplateFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * <p>
     * This method is used to initialize {@link TemplatePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static TemplatePackage init() {
        if (isInited)
            return (TemplatePackage) EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI);

        // Obtain or create and register package
        Object registeredTemplatePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        TemplatePackageImpl theTemplatePackage = registeredTemplatePackage instanceof TemplatePackageImpl
                ? (TemplatePackageImpl) registeredTemplatePackage
                : new TemplatePackageImpl();

        isInited = true;

        // Create package meta-data objects
        theTemplatePackage.createPackageContents();

        // Initialize created meta-data
        theTemplatePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theTemplatePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(TemplatePackage.eNS_URI, theTemplatePackage);
        return theTemplatePackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIConstruct() {
        return iConstructEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIConstruct_StyleRun() {
        return (EAttribute) iConstructEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIConstruct_Runs() {
        return (EAttribute) iConstructEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIConstruct_ClosingRuns() {
        return (EAttribute) iConstructEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getIConstruct_ValidationMessages() {
        return (EAttribute) iConstructEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getComment() {
        return commentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getComment_Text() {
        return (EAttribute) commentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getConditional() {
        return conditionalEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getConditional_Condition() {
        return (EAttribute) conditionalEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getConditional_Then() {
        return (EReference) conditionalEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getConditional_Else() {
        return (EReference) conditionalEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRepetition() {
        return repetitionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepetition_IterationVar() {
        return (EAttribute) repetitionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepetition_Query() {
        return (EAttribute) repetitionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRepetition_Body() {
        return (EReference) repetitionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getUserDoc() {
        return userDocEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUserDoc_Id() {
        return (EAttribute) userDocEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUserDoc_Body() {
        return (EReference) userDocEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getUserContent() {
        return userContentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getUserContent_Id() {
        return (EAttribute) userContentEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getUserContent_Body() {
        return (EReference) userContentEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getQuery() {
        return queryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getQuery_Query() {
        return (EAttribute) queryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBlock() {
        return blockEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getBlock_Statements() {
        return (EReference) blockEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStatement() {
        return statementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTemplate() {
        return templateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTemplate_Visibility() {
        return (EAttribute) templateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTemplate_Name() {
        return (EAttribute) templateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTemplate_Parameters() {
        return (EReference) templateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTemplate_Body() {
        return (EReference) templateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTemplate_DocumentTemplate() {
        return (EReference) templateEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getParameter() {
        return parameterEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getParameter_Name() {
        return (EAttribute) parameterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getParameter_Type() {
        return (EAttribute) parameterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getStaticFragment() {
        return staticFragmentEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTable() {
        return tableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getTable_Rows() {
        return (EReference) tableEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTable_Table() {
        return (EAttribute) tableEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRow() {
        return rowEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getRow_Cells() {
        return (EReference) rowEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRow_TableRow() {
        return (EAttribute) rowEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCell() {
        return cellEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCell_Body() {
        return (EReference) cellEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getCell_TableCell() {
        return (EAttribute) cellEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDocumentTemplate() {
        return documentTemplateEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDocumentTemplate_Headers() {
        return (EReference) documentTemplateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDocumentTemplate_Footers() {
        return (EReference) documentTemplateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDocumentTemplate_Body() {
        return (EReference) documentTemplateEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDocumentTemplate_InputStream() {
        return (EAttribute) documentTemplateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDocumentTemplate_OpcPackage() {
        return (EAttribute) documentTemplateEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getDocumentTemplate_Document() {
        return (EAttribute) documentTemplateEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getDocumentTemplate_Templates() {
        return (EReference) documentTemplateEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getBookmark() {
        return bookmarkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getBookmark_Name() {
        return (EAttribute) bookmarkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getBookmark_Body() {
        return (EReference) bookmarkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLink() {
        return linkEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLink_Name() {
        return (EAttribute) linkEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLink_Text() {
        return (EAttribute) linkEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLet() {
        return letEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLet_Name() {
        return (EAttribute) letEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLet_Value() {
        return (EAttribute) letEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getLet_Body() {
        return (EReference) letEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getContentControl() {
        return contentControlEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getContentControl_Block() {
        return (EAttribute) contentControlEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getIGenerateable() {
        return iGenerateableEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getPOSITION() {
        return positionEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EEnum getVisibility() {
        return visibilityEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getInputStream() {
        return inputStreamEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getOPCPackage() {
        return opcPackageEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getDocument() {
        return documentEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getWTable() {
        return wTableEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getRun() {
        return runEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getAstResult() {
        return astResultEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getTemplateValidationMessage() {
        return templateValidationMessageEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getWTableRow() {
        return wTableRowEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getWTableCell() {
        return wTableCellEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getBody() {
        return bodyEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getCTSdtBlock() {
        return ctSdtBlockEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public TemplateFactory getTemplateFactory() {
        return (TemplateFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        iConstructEClass = createEClass(ICONSTRUCT);
        createEAttribute(iConstructEClass, ICONSTRUCT__STYLE_RUN);
        createEAttribute(iConstructEClass, ICONSTRUCT__RUNS);
        createEAttribute(iConstructEClass, ICONSTRUCT__CLOSING_RUNS);
        createEAttribute(iConstructEClass, ICONSTRUCT__VALIDATION_MESSAGES);

        commentEClass = createEClass(COMMENT);
        createEAttribute(commentEClass, COMMENT__TEXT);

        conditionalEClass = createEClass(CONDITIONAL);
        createEAttribute(conditionalEClass, CONDITIONAL__CONDITION);
        createEReference(conditionalEClass, CONDITIONAL__THEN);
        createEReference(conditionalEClass, CONDITIONAL__ELSE);

        repetitionEClass = createEClass(REPETITION);
        createEAttribute(repetitionEClass, REPETITION__ITERATION_VAR);
        createEAttribute(repetitionEClass, REPETITION__QUERY);
        createEReference(repetitionEClass, REPETITION__BODY);

        userDocEClass = createEClass(USER_DOC);
        createEAttribute(userDocEClass, USER_DOC__ID);
        createEReference(userDocEClass, USER_DOC__BODY);

        userContentEClass = createEClass(USER_CONTENT);
        createEAttribute(userContentEClass, USER_CONTENT__ID);
        createEReference(userContentEClass, USER_CONTENT__BODY);

        queryEClass = createEClass(QUERY);
        createEAttribute(queryEClass, QUERY__QUERY);

        blockEClass = createEClass(BLOCK);
        createEReference(blockEClass, BLOCK__STATEMENTS);

        statementEClass = createEClass(STATEMENT);

        templateEClass = createEClass(TEMPLATE);
        createEAttribute(templateEClass, TEMPLATE__VISIBILITY);
        createEAttribute(templateEClass, TEMPLATE__NAME);
        createEReference(templateEClass, TEMPLATE__PARAMETERS);
        createEReference(templateEClass, TEMPLATE__BODY);
        createEReference(templateEClass, TEMPLATE__DOCUMENT_TEMPLATE);

        parameterEClass = createEClass(PARAMETER);
        createEAttribute(parameterEClass, PARAMETER__NAME);
        createEAttribute(parameterEClass, PARAMETER__TYPE);

        staticFragmentEClass = createEClass(STATIC_FRAGMENT);

        tableEClass = createEClass(TABLE);
        createEReference(tableEClass, TABLE__ROWS);
        createEAttribute(tableEClass, TABLE__TABLE);

        rowEClass = createEClass(ROW);
        createEReference(rowEClass, ROW__CELLS);
        createEAttribute(rowEClass, ROW__TABLE_ROW);

        cellEClass = createEClass(CELL);
        createEReference(cellEClass, CELL__BODY);
        createEAttribute(cellEClass, CELL__TABLE_CELL);

        documentTemplateEClass = createEClass(DOCUMENT_TEMPLATE);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__HEADERS);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__FOOTERS);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__BODY);
        createEAttribute(documentTemplateEClass, DOCUMENT_TEMPLATE__INPUT_STREAM);
        createEAttribute(documentTemplateEClass, DOCUMENT_TEMPLATE__OPC_PACKAGE);
        createEAttribute(documentTemplateEClass, DOCUMENT_TEMPLATE__DOCUMENT);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__TEMPLATES);

        bookmarkEClass = createEClass(BOOKMARK);
        createEAttribute(bookmarkEClass, BOOKMARK__NAME);
        createEReference(bookmarkEClass, BOOKMARK__BODY);

        linkEClass = createEClass(LINK);
        createEAttribute(linkEClass, LINK__NAME);
        createEAttribute(linkEClass, LINK__TEXT);

        letEClass = createEClass(LET);
        createEAttribute(letEClass, LET__NAME);
        createEAttribute(letEClass, LET__VALUE);
        createEReference(letEClass, LET__BODY);

        contentControlEClass = createEClass(CONTENT_CONTROL);
        createEAttribute(contentControlEClass, CONTENT_CONTROL__BLOCK);

        iGenerateableEClass = createEClass(IGENERATEABLE);

        // Create enums
        positionEEnum = createEEnum(POSITION);
        visibilityEEnum = createEEnum(VISIBILITY);

        // Create data types
        inputStreamEDataType = createEDataType(INPUT_STREAM);
        opcPackageEDataType = createEDataType(OPC_PACKAGE);
        documentEDataType = createEDataType(DOCUMENT);
        wTableEDataType = createEDataType(WTABLE);
        runEDataType = createEDataType(RUN);
        astResultEDataType = createEDataType(AST_RESULT);
        templateValidationMessageEDataType = createEDataType(TEMPLATE_VALIDATION_MESSAGE);
        wTableRowEDataType = createEDataType(WTABLE_ROW);
        wTableCellEDataType = createEDataType(WTABLE_CELL);
        bodyEDataType = createEDataType(BODY);
        ctSdtBlockEDataType = createEDataType(CT_SDT_BLOCK);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        iConstructEClass.getESuperTypes().add(this.getIGenerateable());
        commentEClass.getESuperTypes().add(this.getStatement());
        conditionalEClass.getESuperTypes().add(this.getStatement());
        repetitionEClass.getESuperTypes().add(this.getStatement());
        userDocEClass.getESuperTypes().add(this.getStatement());
        userContentEClass.getESuperTypes().add(this.getStatement());
        queryEClass.getESuperTypes().add(this.getStatement());
        blockEClass.getESuperTypes().add(this.getIConstruct());
        statementEClass.getESuperTypes().add(this.getIConstruct());
        templateEClass.getESuperTypes().add(this.getIConstruct());
        staticFragmentEClass.getESuperTypes().add(this.getStatement());
        tableEClass.getESuperTypes().add(this.getStatement());
        documentTemplateEClass.getESuperTypes().add(this.getIGenerateable());
        bookmarkEClass.getESuperTypes().add(this.getStatement());
        linkEClass.getESuperTypes().add(this.getStatement());
        letEClass.getESuperTypes().add(this.getStatement());
        contentControlEClass.getESuperTypes().add(this.getStatement());

        // Initialize classes, features, and operations; add parameters
        initEClass(iConstructEClass, IConstruct.class, "IConstruct", IS_ABSTRACT, IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIConstruct_StyleRun(), this.getRun(), "styleRun", null, 0, 1, IConstruct.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIConstruct_Runs(), this.getRun(), "runs", null, 0, -1, IConstruct.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIConstruct_ClosingRuns(), this.getRun(), "closingRuns", null, 0, -1, IConstruct.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIConstruct_ValidationMessages(), this.getTemplateValidationMessage(), "validationMessages",
                null, 0, -1, IConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getComment_Text(), ecorePackage.getEString(), "text", null, 0, 1, Comment.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionalEClass, Conditional.class, "Conditional", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getConditional_Condition(), this.getAstResult(), "condition", null, 1, 1, Conditional.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConditional_Then(), this.getBlock(), null, "then", null, 1, 1, Conditional.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConditional_Else(), this.getBlock(), null, "else", null, 0, 1, Conditional.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(repetitionEClass, Repetition.class, "Repetition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepetition_IterationVar(), ecorePackage.getEString(), "iterationVar", null, 1, 1,
                Repetition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepetition_Query(), this.getAstResult(), "query", null, 1, 1, Repetition.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRepetition_Body(), this.getBlock(), null, "body", null, 1, 1, Repetition.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(userDocEClass, UserDoc.class, "UserDoc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserDoc_Id(), this.getAstResult(), "id", null, 1, 1, UserDoc.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUserDoc_Body(), this.getBlock(), null, "body", null, 1, 1, UserDoc.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(userContentEClass, UserContent.class, "UserContent", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserContent_Id(), ecorePackage.getEString(), "id", null, 0, 1, UserContent.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getUserContent_Body(), this.getBlock(), null, "body", null, 1, 1, UserContent.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuery_Query(), this.getAstResult(), "query", null, 1, 1, Query.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBlock_Statements(), this.getStatement(), null, "statements", null, 0, -1, Block.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(statementEClass, Statement.class, "Statement", IS_ABSTRACT, IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        initEClass(templateEClass, Template.class, "Template", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTemplate_Visibility(), this.getVisibility(), "visibility", null, 1, 1, Template.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTemplate_Name(), ecorePackage.getEString(), "name", null, 1, 1, Template.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTemplate_Parameters(), this.getParameter(), null, "parameters", null, 1, -1, Template.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getTemplate_Body(), this.getBlock(), null, "body", null, 1, 1, Template.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEReference(getTemplate_DocumentTemplate(), this.getDocumentTemplate(), this.getDocumentTemplate_Templates(),
                "documentTemplate", null, 1, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getParameter_Name(), ecorePackage.getEString(), "name", null, 1, 1, Parameter.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getParameter_Type(), this.getAstResult(), "type", null, 1, 1, Parameter.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(staticFragmentEClass, StaticFragment.class, "StaticFragment", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        initEClass(tableEClass, Table.class, "Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getTable_Rows(), this.getRow(), null, "rows", null, 0, -1, Table.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getTable_Table(), this.getWTable(), "table", null, 0, 1, Table.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(rowEClass, Row.class, "Row", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRow_Cells(), this.getCell(), null, "cells", null, 0, -1, Row.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getRow_TableRow(), this.getWTableRow(), "tableRow", null, 0, 1, Row.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(cellEClass, Cell.class, "Cell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCell_Body(), this.getBlock(), null, "body", null, 0, 1, Cell.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getCell_TableCell(), this.getWTableCell(), "tableCell", null, 0, 1, Cell.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentTemplateEClass, DocumentTemplate.class, "DocumentTemplate", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDocumentTemplate_Headers(), this.getBlock(), null, "headers", null, 0, -1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentTemplate_Footers(), this.getBlock(), null, "footers", null, 0, -1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentTemplate_Body(), this.getBlock(), null, "body", null, 1, 1, DocumentTemplate.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentTemplate_InputStream(), this.getInputStream(), "inputStream", null, 1, 1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentTemplate_OpcPackage(), this.getOPCPackage(), "opcPackage", null, 1, 1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentTemplate_Document(), this.getDocument(), "document", null, 1, 1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentTemplate_Templates(), this.getTemplate(), this.getTemplate_DocumentTemplate(),
                "templates", null, 0, -1, DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bookmarkEClass, Bookmark.class, "Bookmark", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBookmark_Name(), this.getAstResult(), "name", null, 0, 1, Bookmark.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBookmark_Body(), this.getBlock(), null, "body", null, 1, 1, Bookmark.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLink_Name(), this.getAstResult(), "name", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLink_Text(), this.getAstResult(), "text", null, 1, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(letEClass, Let.class, "Let", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLet_Name(), ecorePackage.getEString(), "name", null, 1, 1, Let.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLet_Value(), this.getAstResult(), "value", null, 1, 1, Let.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLet_Body(), this.getBlock(), null, "body", null, 1, 1, Let.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contentControlEClass, ContentControl.class, "ContentControl", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContentControl_Block(), this.getCTSdtBlock(), "block", null, 1, 1, ContentControl.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(iGenerateableEClass, IGenerateable.class, "IGenerateable", IS_ABSTRACT, IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        // Initialize enums and add enum literals
        initEEnum(positionEEnum, org.obeonetwork.m2doc.template.POSITION.class, "POSITION");
        addEEnumLiteral(positionEEnum, org.obeonetwork.m2doc.template.POSITION.ABOVE);
        addEEnumLiteral(positionEEnum, org.obeonetwork.m2doc.template.POSITION.BELOW);

        initEEnum(visibilityEEnum, Visibility.class, "Visibility");
        addEEnumLiteral(visibilityEEnum, Visibility.PRIVATE);
        addEEnumLiteral(visibilityEEnum, Visibility.PROTECTED);
        addEEnumLiteral(visibilityEEnum, Visibility.PUBLIC);

        // Initialize data types
        initEDataType(inputStreamEDataType, InputStream.class, "InputStream", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(opcPackageEDataType, OPCPackage.class, "OPCPackage", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(documentEDataType, XWPFDocument.class, "Document", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(wTableEDataType, XWPFTable.class, "WTable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(runEDataType, XWPFRun.class, "Run", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(astResultEDataType, AstResult.class, "AstResult", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(templateValidationMessageEDataType, TemplateValidationMessage.class, "TemplateValidationMessage",
                IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(wTableRowEDataType, XWPFTableRow.class, "WTableRow", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(wTableCellEDataType, XWPFTableCell.class, "WTableCell", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(bodyEDataType, IBody.class, "Body", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(ctSdtBlockEDataType, CTSdtBlock.class, "CTSdtBlock", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/emf/2002/Ecore
        createEcoreAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createEcoreAnnotations() {
        String source = "http://www.eclipse.org/emf/2002/Ecore";
        addAnnotation(this, source,
                new String[] {"documentation", "This metamodel describes the M2Doc abstract syntax tree." });
        addAnnotation(commentEClass, source, new String[] {"documentation",
            "A comment produce nothing in the generated document.", "syntax", "{m:comment <comment text>}" });
        addAnnotation(conditionalEClass, source, new String[] {"documentation",
            "If {@link Conditional#getCondition() condition} is evaluated to <code>true</code> the {@link Conditional#getThen() then} {@link Compound} is executed, otherwise the {@link Conditional#getElse() else} {@link Compound}",
            "syntax", "{m:if expr} runs1 [{m:elseif <expr>} runs_n]* [{m:else} run_else]{m:endif}" });
        addAnnotation(getConditional_Condition(), source, new String[] {"documentation",
            "If evaluated to <code>true</code> the {@link Conditional#getThen() then} {@link Compound} is executed, otherwise the {@link Conditional#getElse() else} {@link Compound}" });
        addAnnotation(getConditional_Then(), source, new String[] {"documentation",
            "The {@link Compound} is executed if {@link Conditional#getCondition() condition} is evaluated to <code>true</code>." });
        addAnnotation(getConditional_Else(), source, new String[] {"documentation",
            "The {@link Compound} is executed if {@link Conditional#getCondition() condition} is evaluated to <code>false</code>." });
        addAnnotation(repetitionEClass, source, new String[] {"documentation",
            "This produce the {@link Repetition#getBody() body} for each value of the {@link Repetition#getQuery() query} evaluation result. More inforamation about <a href=\"https://www.eclipse.org/acceleo/documentation/aql.html\">Acceleo Query Language</a>.",
            "syntax", "{m:for var | query} body {m:endfor}" });
        addAnnotation(getRepetition_Body(), source,
                new String[] {"documentation", "The {@link Block} of {@link Statement}." });
        addAnnotation(userDocEClass, source, new String[] {"documentation",
            "UserDoc  tag\r\n\r\nTag template to keep user part modification in previous generated \r\nresult file." });
        addAnnotation(getUserDoc_Body(), source,
                new String[] {"documentation", "The {@link Block} of {@link Statement}." });
        addAnnotation(userContentEClass, source, new String[] {"documentation",
            "UserDocDest  tag\r\n\r\nTag in generated document neede to extract user part modification.\r\nUserDocDest is generated by M2Doc\r\n\r\n." });
        addAnnotation(getUserContent_Body(), source,
                new String[] {"documentation", "The {@link Block} of {@link Statement}." });
        addAnnotation(queryEClass, source, new String[] {"documentation",
            "The query produce the {@link Query#getQuery() query} evaluation result in the produced document. More inforamation about <a href=\"https://www.eclipse.org/acceleo/documentation/aql.html\">Acceleo Query Language</a>.",
            "syntax", "{m:<aql query>}" });
        addAnnotation(getTemplate_Visibility(), source, new String[] {"documentation", "The template visibility." });
        addAnnotation(getTemplate_Body(), source,
                new String[] {"documentation", "The {@link Block} of {@link Statement}." });
        addAnnotation(getBookmark_Body(), source,
                new String[] {"documentation", "The {@link Block} of {@link Statement}." });
        addAnnotation(getLet_Body(), source,
                new String[] {"documentation", "The {@link Block} of {@link Statement}." });
        addAnnotation(visibilityEEnum, source, new String[] {"documentation",
            "The visibility defines how an element is visible from other documents." });
    }

} // TemplatePackageImpl
