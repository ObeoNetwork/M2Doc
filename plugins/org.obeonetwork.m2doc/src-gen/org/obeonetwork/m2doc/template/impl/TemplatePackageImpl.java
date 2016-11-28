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
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.provider.IProvider;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.AbstractImage;
import org.obeonetwork.m2doc.template.AbstractProviderClient;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Cell;
import org.obeonetwork.m2doc.template.Compound;
import org.obeonetwork.m2doc.template.Conditionnal;
import org.obeonetwork.m2doc.template.Default;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Image;
import org.obeonetwork.m2doc.template.Link;
import org.obeonetwork.m2doc.template.Query;
import org.obeonetwork.m2doc.template.QueryBehavior;
import org.obeonetwork.m2doc.template.Repetition;
import org.obeonetwork.m2doc.template.Representation;
import org.obeonetwork.m2doc.template.Row;
import org.obeonetwork.m2doc.template.StaticFragment;
import org.obeonetwork.m2doc.template.Table;
import org.obeonetwork.m2doc.template.TableClient;
import org.obeonetwork.m2doc.template.TableMerge;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplateFactory;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.template.UserContent;
import org.obeonetwork.m2doc.template.UserDoc;
import org.obeonetwork.m2doc.template.util.TemplateValidator;

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
    public static final String copyright = " Copyright (c) 2016 Obeo. \r\n All rights reserved. This program and the accompanying materials\r\n are made available under the terms of the Eclipse Public License v1.0\r\n which accompanies this distribution, and is available at\r\n http://www.eclipse.org/legal/epl-v10.html\r\n  \r\n  Contributors:\r\n      Obeo - initial API and implementation";

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractConstructEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass conditionnalEClass = null;

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
    private EClass tableMergeEClass = null;

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
    private EClass optionValueMapEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractImageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass abstractProviderClientEClass = null;

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
    private EClass tableClientEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass imageEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass defaultEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass compoundEClass = null;

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
    private EClass representationEClass = null;

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
    private EEnum queryBehaviorEEnum = null;

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
    private EDataType providerEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType bodyEDataType = null;

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
        TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new TemplatePackageImpl());

        isInited = true;

        // Create package meta-data objects
        theTemplatePackage.createPackageContents();

        // Initialize created meta-data
        theTemplatePackage.initializePackageContents();

        // Register package validator
        EValidator.Registry.INSTANCE.put(theTemplatePackage, new EValidator.Descriptor() {
            @Override
            public EValidator getEValidator() {
                return TemplateValidator.INSTANCE;
            }
        });

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
    public EClass getAbstractConstruct() {
        return abstractConstructEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractConstruct_StyleRun() {
        return (EAttribute) abstractConstructEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractConstruct_Runs() {
        return (EAttribute) abstractConstructEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractConstruct_ClosingRuns() {
        return (EAttribute) abstractConstructEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractConstruct_ValidationMessages() {
        return (EAttribute) abstractConstructEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getConditionnal() {
        return conditionnalEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getConditionnal_Alternative() {
        return (EReference) conditionnalEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getConditionnal_Query() {
        return (EAttribute) conditionnalEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getConditionnal_Else() {
        return (EReference) conditionnalEClass.getEStructuralFeatures().get(1);
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
        return (EAttribute) queryEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getTableMerge() {
        return tableMergeEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTableMerge_Legend() {
        return (EAttribute) tableMergeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getQuery_Behavior() {
        return (EAttribute) queryEClass.getEStructuralFeatures().get(0);
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
    public EReference getCell_Template() {
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
    public EAttribute getDocumentTemplate_Document() {
        return (EAttribute) documentTemplateEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getOptionValueMap() {
        return optionValueMapEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getOptionValueMap_Key() {
        return (EAttribute) optionValueMapEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getOptionValueMap_Value() {
        return (EAttribute) optionValueMapEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAbstractImage() {
        return abstractImageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractImage_Legend() {
        return (EAttribute) abstractImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractImage_Height() {
        return (EAttribute) abstractImageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractImage_Width() {
        return (EAttribute) abstractImageEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractImage_LegendPOS() {
        return (EAttribute) abstractImageEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getAbstractProviderClient() {
        return abstractProviderClientEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getAbstractProviderClient_OptionValueMap() {
        return (EReference) abstractProviderClientEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getAbstractProviderClient_Provider() {
        return (EAttribute) abstractProviderClientEClass.getEStructuralFeatures().get(1);
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
    public EClass getTableClient() {
        return tableClientEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getImage() {
        return imageEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getImage_FileName() {
        return (EAttribute) imageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getDefault() {
        return defaultEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getCompound() {
        return compoundEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getCompound_SubConstructs() {
        return (EReference) compoundEClass.getEStructuralFeatures().get(0);
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
    public EAttribute getTemplate_TemplateName() {
        return (EAttribute) templateEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getTemplate_Body() {
        return (EAttribute) templateEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getRepresentation() {
        return representationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentation_Query() {
        return (EAttribute) representationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentation_RepresentationId() {
        return (EAttribute) representationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentation_RepresentationTitle() {
        return (EAttribute) representationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getRepresentation_ActivatedLayers() {
        return (EAttribute) representationEClass.getEStructuralFeatures().get(3);
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
    public EEnum getQueryBehavior() {
        return queryBehaviorEEnum;
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
    public EDataType getProvider() {
        return providerEDataType;
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
        abstractConstructEClass = createEClass(ABSTRACT_CONSTRUCT);
        createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__STYLE_RUN);
        createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__RUNS);
        createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__CLOSING_RUNS);
        createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__VALIDATION_MESSAGES);

        conditionnalEClass = createEClass(CONDITIONNAL);
        createEReference(conditionnalEClass, CONDITIONNAL__ALTERNATIVE);
        createEReference(conditionnalEClass, CONDITIONNAL__ELSE);
        createEAttribute(conditionnalEClass, CONDITIONNAL__QUERY);

        repetitionEClass = createEClass(REPETITION);
        createEAttribute(repetitionEClass, REPETITION__ITERATION_VAR);
        createEAttribute(repetitionEClass, REPETITION__QUERY);

        userDocEClass = createEClass(USER_DOC);
        createEAttribute(userDocEClass, USER_DOC__ID);

        userContentEClass = createEClass(USER_CONTENT);
        createEAttribute(userContentEClass, USER_CONTENT__ID);

        queryEClass = createEClass(QUERY);
        createEAttribute(queryEClass, QUERY__BEHAVIOR);
        createEAttribute(queryEClass, QUERY__QUERY);

        tableMergeEClass = createEClass(TABLE_MERGE);
        createEAttribute(tableMergeEClass, TABLE_MERGE__LEGEND);

        imageEClass = createEClass(IMAGE);
        createEAttribute(imageEClass, IMAGE__FILE_NAME);

        defaultEClass = createEClass(DEFAULT);

        compoundEClass = createEClass(COMPOUND);
        createEReference(compoundEClass, COMPOUND__SUB_CONSTRUCTS);

        templateEClass = createEClass(TEMPLATE);
        createEAttribute(templateEClass, TEMPLATE__TEMPLATE_NAME);
        createEAttribute(templateEClass, TEMPLATE__BODY);

        representationEClass = createEClass(REPRESENTATION);
        createEAttribute(representationEClass, REPRESENTATION__QUERY);
        createEAttribute(representationEClass, REPRESENTATION__REPRESENTATION_ID);
        createEAttribute(representationEClass, REPRESENTATION__REPRESENTATION_TITLE);
        createEAttribute(representationEClass, REPRESENTATION__ACTIVATED_LAYERS);

        staticFragmentEClass = createEClass(STATIC_FRAGMENT);

        tableEClass = createEClass(TABLE);
        createEReference(tableEClass, TABLE__ROWS);
        createEAttribute(tableEClass, TABLE__TABLE);

        rowEClass = createEClass(ROW);
        createEReference(rowEClass, ROW__CELLS);
        createEAttribute(rowEClass, ROW__TABLE_ROW);

        cellEClass = createEClass(CELL);
        createEReference(cellEClass, CELL__TEMPLATE);
        createEAttribute(cellEClass, CELL__TABLE_CELL);

        documentTemplateEClass = createEClass(DOCUMENT_TEMPLATE);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__HEADERS);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__FOOTERS);
        createEReference(documentTemplateEClass, DOCUMENT_TEMPLATE__BODY);
        createEAttribute(documentTemplateEClass, DOCUMENT_TEMPLATE__DOCUMENT);

        optionValueMapEClass = createEClass(OPTION_VALUE_MAP);
        createEAttribute(optionValueMapEClass, OPTION_VALUE_MAP__KEY);
        createEAttribute(optionValueMapEClass, OPTION_VALUE_MAP__VALUE);

        abstractImageEClass = createEClass(ABSTRACT_IMAGE);
        createEAttribute(abstractImageEClass, ABSTRACT_IMAGE__LEGEND);
        createEAttribute(abstractImageEClass, ABSTRACT_IMAGE__HEIGHT);
        createEAttribute(abstractImageEClass, ABSTRACT_IMAGE__WIDTH);
        createEAttribute(abstractImageEClass, ABSTRACT_IMAGE__LEGEND_POS);

        abstractProviderClientEClass = createEClass(ABSTRACT_PROVIDER_CLIENT);
        createEReference(abstractProviderClientEClass, ABSTRACT_PROVIDER_CLIENT__OPTION_VALUE_MAP);
        createEAttribute(abstractProviderClientEClass, ABSTRACT_PROVIDER_CLIENT__PROVIDER);

        bookmarkEClass = createEClass(BOOKMARK);
        createEAttribute(bookmarkEClass, BOOKMARK__NAME);

        linkEClass = createEClass(LINK);
        createEAttribute(linkEClass, LINK__NAME);
        createEAttribute(linkEClass, LINK__TEXT);

        tableClientEClass = createEClass(TABLE_CLIENT);

        // Create enums
        queryBehaviorEEnum = createEEnum(QUERY_BEHAVIOR);
        positionEEnum = createEEnum(POSITION);

        // Create data types
        documentEDataType = createEDataType(DOCUMENT);
        wTableEDataType = createEDataType(WTABLE);
        runEDataType = createEDataType(RUN);
        astResultEDataType = createEDataType(AST_RESULT);
        templateValidationMessageEDataType = createEDataType(TEMPLATE_VALIDATION_MESSAGE);
        wTableRowEDataType = createEDataType(WTABLE_ROW);
        wTableCellEDataType = createEDataType(WTABLE_CELL);
        providerEDataType = createEDataType(PROVIDER);
        bodyEDataType = createEDataType(BODY);
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
        conditionnalEClass.getESuperTypes().add(this.getCompound());
        repetitionEClass.getESuperTypes().add(this.getCompound());
        userDocEClass.getESuperTypes().add(this.getCompound());
        userContentEClass.getESuperTypes().add(this.getCompound());
        queryEClass.getESuperTypes().add(this.getAbstractConstruct());
        tableMergeEClass.getESuperTypes().add(this.getCompound());
        imageEClass.getESuperTypes().add(this.getAbstractImage());
        defaultEClass.getESuperTypes().add(this.getCompound());
        compoundEClass.getESuperTypes().add(this.getAbstractConstruct());
        templateEClass.getESuperTypes().add(this.getCompound());
        representationEClass.getESuperTypes().add(this.getAbstractImage());
        staticFragmentEClass.getESuperTypes().add(this.getAbstractConstruct());
        tableEClass.getESuperTypes().add(this.getAbstractConstruct());
        abstractImageEClass.getESuperTypes().add(this.getAbstractProviderClient());
        abstractProviderClientEClass.getESuperTypes().add(this.getAbstractConstruct());
        bookmarkEClass.getESuperTypes().add(this.getCompound());
        linkEClass.getESuperTypes().add(this.getAbstractConstruct());
        tableClientEClass.getESuperTypes().add(this.getAbstractProviderClient());

        // Initialize classes, features, and operations; add parameters
        initEClass(abstractConstructEClass, AbstractConstruct.class, "AbstractConstruct", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractConstruct_StyleRun(), this.getRun(), "styleRun", null, 0, 1, AbstractConstruct.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractConstruct_Runs(), this.getRun(), "runs", null, 0, -1, AbstractConstruct.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractConstruct_ClosingRuns(), this.getRun(), "closingRuns", null, 0, -1,
                AbstractConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractConstruct_ValidationMessages(), this.getTemplateValidationMessage(),
                "validationMessages", null, 0, -1, AbstractConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(conditionnalEClass, Conditionnal.class, "Conditionnal", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getConditionnal_Alternative(), this.getConditionnal(), null, "alternative", null, 0, 1,
                Conditionnal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getConditionnal_Else(), this.getDefault(), null, "else", null, 0, 1, Conditionnal.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getConditionnal_Query(), this.getAstResult(), "query", null, 0, 1, Conditionnal.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(repetitionEClass, Repetition.class, "Repetition", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepetition_IterationVar(), ecorePackage.getEString(), "iterationVar", null, 0, 1,
                Repetition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepetition_Query(), this.getAstResult(), "query", null, 0, 1, Repetition.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(userDocEClass, UserDoc.class, "UserDoc", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserDoc_Id(), this.getAstResult(), "id", null, 0, 1, UserDoc.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(userContentEClass, UserContent.class, "UserContent", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUserContent_Id(), ecorePackage.getEString(), "id", null, 0, 1, UserContent.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getQuery_Behavior(), this.getQueryBehavior(), "behavior", "TEXT", 0, 1, Query.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getQuery_Query(), this.getAstResult(), "query", null, 0, 1, Query.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableMergeEClass, TableMerge.class, "TableMerge", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTableMerge_Legend(), ecorePackage.getEString(), "legend", null, 0, 1, TableMerge.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getImage_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, Image.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(defaultEClass, Default.class, "Default", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(compoundEClass, Compound.class, "Compound", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getCompound_SubConstructs(), this.getAbstractConstruct(), null, "subConstructs", null, 0, -1,
                Compound.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(templateEClass, Template.class, "Template", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTemplate_TemplateName(), ecorePackage.getEString(), "templateName", null, 0, 1,
                Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getTemplate_Body(), this.getBody(), "body", null, 0, 1, Template.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(representationEClass, Representation.class, "Representation", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRepresentation_Query(), this.getAstResult(), "query", null, 0, 1, Representation.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentation_RepresentationId(), ecorePackage.getEString(), "representationId", null, 0, 1,
                Representation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentation_RepresentationTitle(), ecorePackage.getEString(), "representationTitle", null,
                0, 1, Representation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRepresentation_ActivatedLayers(), ecorePackage.getEString(), "activatedLayers", null, 0, -1,
                Representation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

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
        initEReference(getCell_Template(), this.getTemplate(), null, "template", null, 0, 1, Cell.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);
        initEAttribute(getCell_TableCell(), this.getWTableCell(), "tableCell", null, 0, 1, Cell.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(documentTemplateEClass, DocumentTemplate.class, "DocumentTemplate", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDocumentTemplate_Headers(), this.getTemplate(), null, "headers", null, 0, -1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentTemplate_Footers(), this.getTemplate(), null, "footers", null, 0, -1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDocumentTemplate_Body(), this.getTemplate(), null, "body", null, 0, 1, DocumentTemplate.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDocumentTemplate_Document(), this.getDocument(), "document", null, 0, 1,
                DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(optionValueMapEClass, Map.Entry.class, "OptionValueMap", !IS_ABSTRACT, !IS_INTERFACE,
                !IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getOptionValueMap_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getOptionValueMap_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, Map.Entry.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(abstractImageEClass, AbstractImage.class, "AbstractImage", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractImage_Legend(), ecorePackage.getEString(), "legend", null, 0, 1, AbstractImage.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractImage_Height(), ecorePackage.getEInt(), "height", null, 0, 1, AbstractImage.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractImage_Width(), ecorePackage.getEInt(), "width", null, 0, 1, AbstractImage.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractImage_LegendPOS(), this.getPOSITION(), "legendPOS", "BELOW", 0, 1,
                AbstractImage.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        initEClass(abstractProviderClientEClass, AbstractProviderClient.class, "AbstractProviderClient", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAbstractProviderClient_OptionValueMap(), this.getOptionValueMap(), null, "optionValueMap",
                null, 0, -1, AbstractProviderClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAbstractProviderClient_Provider(), this.getProvider(), "provider", null, 0, 1,
                AbstractProviderClient.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bookmarkEClass, Bookmark.class, "Bookmark", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBookmark_Name(), this.getAstResult(), "name", null, 0, 1, Bookmark.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLink_Name(), this.getAstResult(), "name", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLink_Text(), this.getAstResult(), "text", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tableClientEClass, TableClient.class, "TableClient", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        // Initialize enums and add enum literals
        initEEnum(queryBehaviorEEnum, QueryBehavior.class, "QueryBehavior");
        addEEnumLiteral(queryBehaviorEEnum, QueryBehavior.ICON);
        addEEnumLiteral(queryBehaviorEEnum, QueryBehavior.LABEL);
        addEEnumLiteral(queryBehaviorEEnum, QueryBehavior.TEXT);

        initEEnum(positionEEnum, org.obeonetwork.m2doc.template.POSITION.class, "POSITION");
        addEEnumLiteral(positionEEnum, org.obeonetwork.m2doc.template.POSITION.ABOVE);
        addEEnumLiteral(positionEEnum, org.obeonetwork.m2doc.template.POSITION.BELOW);

        // Initialize data types
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
        initEDataType(providerEDataType, IProvider.class, "Provider", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(bodyEDataType, IBody.class, "Body", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
        addAnnotation(this, source, new String[] {"documentation",
            "Le m\u00e9ta mod\u00e8le \'template\' repr\u00e9sente les concepts produit par le parsing \r\ndes templates de g\u00e9n\u00e9ration documentaire.\r\n\r\nLes mod\u00e8les instances sont utilis\u00e9s pour la g\u00e9n\u00e9ration." });
        addAnnotation(conditionnalEClass, source,
                new String[] {"constraints", "AlternativeExcludeElse", "documentation",
                    "Si l\'expression expr est \u00e9valu\u00e9e \u00e0 true dans le contexte courant alors l\'ensemble de la balise jusqu\'\u00e0 la balise gd:endif comprisee est remplac\u00e9e par le traitement de runs1 dans le contexte courant. Sinon, l\'ensemble des balises est remplac\u00e9e par le traitement du premier run_n tel que l\'expression expr de la balise gd:elseif associ\u00e9e est \u00e9valu\u00e9e \u00e0 vrai. Si aucune telle balise n\'est pr\u00e9sente ou si aucune d\'entre-elle porte une expression \u00e9valu\u00e9e \u00e0 true, le r\u00e9sultat du traitement de run_else, si la balise gd:else est pr\u00e9sente, dans le contexte courant est ins\u00e9r\u00e9 \u00e0 la place de l\'ensemble des balises",
                    "syntax", "{gd:if expr} runs1 [{gd:elseif <expr>} runs_n]* [{gd:else} run_else]{gd:endif}" });
        addAnnotation(repetitionEClass, source,
                new String[] {"documentation",
                    "pour chaque valeur v dans le r\u00e9sultat de l\'\u00e9valuation de query, \r\n- un nouveau contexte est cr\u00e9e en ajoutant la d\u00e9finition \'var =v\' au contexte courant\r\n- le r\u00e9sultat du traitement du corps runs est ins\u00e9r\u00e9 dans le document \r\nles balises {gd:for} et {gd:endfor} sont supprim\u00e9es.\r\nSi la balise {gd:for} est imm\u00e9diatement suivie d\'un retour chariot alors l\'ensemble du paragraphe la contenant est supprim\u00e9e du r\u00e9sultat sinon, le paragraphe duquel on a supprim\u00e9 la balise est ins\u00e9r\u00e9 dans le r\u00e9sultat. Le m\u00eame traitement est appliqu\u00e9 \u00e0 la balise {gd:endfor}",
                    "syntax", "{gd:for var | query} body {gd:endfor}" });
        addAnnotation(userDocEClass, source, new String[] {"documentation",
            "UserDoc  tag\r\n\r\nTag template to keep user part modification in previous generated \r\nresult file." });
        addAnnotation(userContentEClass, source, new String[] {"documentation",
            "UserContent  tag\r\n\r\nTag in generated document neede to extract user part modification.\r\nUserContent is generated by M2Doc\r\n\r\n." });
        addAnnotation(queryEClass, source,
                new String[] {"documentation",
                    "La balise est remplac\u00e9e dans le document par la repr\u00e9sentation en \r\nchaine de caract\u00e8re du r\u00e9sultat de l\'\u00e9valuation de l\'expression dans le \r\ncontexte courant. La style du premier run contenant query est utilis\u00e9 pour g\u00e9n\u00e9rer le document. Il n\'y a pas de sens \u00e0 utiliser plusieurs styles diff\u00e9rents dans une requ\u00eate si bien que seul le premier est utilis\u00e9 si plusieurs sont pr\u00e9sents.\r\n- Lorsque le modifier icon est pr\u00e9cis\u00e9, l\'icone de l\'\u00e9l\u00e9ment \u00e9valu\u00e9 telle que d\u00e9finie dans le .edit correspondant  est ins\u00e9r\u00e9e \u00e0 la place du run\r\n- Lorsque le modifier label est pr\u00e9cis\u00e9, le label de l\'\u00e9l\u00e9ment \u00e9valu\u00e9 tel que d\u00e9finin dans le .edit correspondant est ins\u00e9r\u00e9 \u00e0 la place du run.\r\n- Lorsque le modifier text ou qu\'aucun modifier n\'apparait, la repr\u00e9sentation en chaine de caract\u00e8re de l\'\u00e9valuation du r\u00e9sultat est ins\u00e9r\u00e9.",
                    "syntax", "{aql:query [icon, label,text]}" });
    }

} // TemplatePackageImpl
