/**
 */
package org.eclipse.gendoc2.template.impl;

import org.apache.poi.xwpf.usermodel.IBody;
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
import org.eclipse.gendoc2.parser.DocumentParsingError;
import org.eclipse.gendoc2.template.AbstractConstruct;
import org.eclipse.gendoc2.template.Cell;
import org.eclipse.gendoc2.template.Compound;
import org.eclipse.gendoc2.template.Conditionnal;
import org.eclipse.gendoc2.template.Default;
import org.eclipse.gendoc2.template.DocumentTemplate;
import org.eclipse.gendoc2.template.Image;
import org.eclipse.gendoc2.template.Query;
import org.eclipse.gendoc2.template.QueryBehavior;
import org.eclipse.gendoc2.template.Repetition;
import org.eclipse.gendoc2.template.Representation;
import org.eclipse.gendoc2.template.Row;
import org.eclipse.gendoc2.template.StaticFragment;
import org.eclipse.gendoc2.template.Table;
import org.eclipse.gendoc2.template.TableMerge;
import org.eclipse.gendoc2.template.Template;
import org.eclipse.gendoc2.template.TemplateFactory;
import org.eclipse.gendoc2.template.TemplatePackage;
import org.eclipse.gendoc2.template.VarRef;
import org.eclipse.gendoc2.template.util.TemplateValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplatePackageImpl extends EPackageImpl implements TemplatePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractConstructEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionnalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass repetitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableMergeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentTemplateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass imageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass defaultEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compoundEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass representationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass staticFragmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass varRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum queryBehaviorEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum positionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bodyEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType wTableEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType runEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType astResultEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType documentParsingErrorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType wTableRowEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType wTableCellEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.gendoc2.template.TemplatePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TemplatePackageImpl() {
		super(eNS_URI, TemplateFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TemplatePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TemplatePackage init() {
		if (isInited) return (TemplatePackage)EPackage.Registry.INSTANCE.getEPackage(TemplatePackage.eNS_URI);

		// Obtain or create and register package
		TemplatePackageImpl theTemplatePackage = (TemplatePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TemplatePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TemplatePackageImpl());

		isInited = true;

		// Create package meta-data objects
		theTemplatePackage.createPackageContents();

		// Initialize created meta-data
		theTemplatePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theTemplatePackage, 
			 new EValidator.Descriptor() {
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
	 * @generated
	 */
	public EClass getAbstractConstruct() {
		return abstractConstructEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractConstruct_StyleRun() {
		return (EAttribute)abstractConstructEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractConstruct_Runs() {
		return (EAttribute)abstractConstructEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractConstruct_ClosingRuns() {
		return (EAttribute)abstractConstructEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAbstractConstruct_ParsingErrors() {
		return (EAttribute)abstractConstructEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionnal() {
		return conditionnalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionnal_Alternative() {
		return (EReference)conditionnalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConditionnal_Query() {
		return (EAttribute)conditionnalEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionnal_Else() {
		return (EReference)conditionnalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRepetition() {
		return repetitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepetition_IterationVar() {
		return (EAttribute)repetitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepetition_Query() {
		return (EAttribute)repetitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuery() {
		return queryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuery_Query() {
		return (EAttribute)queryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTableMerge() {
		return tableMergeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTableMerge_Legend() {
		return (EAttribute)tableMergeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuery_Behavior() {
		return (EAttribute)queryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTable() {
		return tableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTable_Rows() {
		return (EReference)tableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTable_Table() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRow() {
		return rowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRow_Cells() {
		return (EReference)rowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRow_TableRow() {
		return (EAttribute)rowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCell() {
		return cellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCell_Template() {
		return (EReference)cellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCell_TableCell() {
		return (EAttribute)cellEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentTemplate() {
		return documentTemplateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentTemplate_Headers() {
		return (EReference)documentTemplateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentTemplate_Footers() {
		return (EReference)documentTemplateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentTemplate_Body() {
		return (EReference)documentTemplateEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImage() {
		return imageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Legend() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_FileName() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Height() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_Width() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getImage_LegendPOS() {
		return (EAttribute)imageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefault() {
		return defaultEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompound() {
		return compoundEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCompound_SubConstructs() {
		return (EReference)compoundEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTemplate() {
		return templateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplate_TemplateName() {
		return (EAttribute)templateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTemplate_Document() {
		return (EAttribute)templateEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRepresentation() {
		return representationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepresentation_Query() {
		return (EAttribute)representationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRepresentation_RepresentationId() {
		return (EAttribute)representationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStaticFragment() {
		return staticFragmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVarRef() {
		return varRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVarRef_VarName() {
		return (EAttribute)varRefEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getQueryBehavior() {
		return queryBehaviorEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getPOSITION() {
		return positionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getBody() {
		return bodyEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWTable() {
		return wTableEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getRun() {
		return runEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAstResult() {
		return astResultEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getDocumentParsingError() {
		return documentParsingErrorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWTableRow() {
		return wTableRowEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getWTableCell() {
		return wTableCellEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TemplateFactory getTemplateFactory() {
		return (TemplateFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		abstractConstructEClass = createEClass(ABSTRACT_CONSTRUCT);
		createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__STYLE_RUN);
		createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__RUNS);
		createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__CLOSING_RUNS);
		createEAttribute(abstractConstructEClass, ABSTRACT_CONSTRUCT__PARSING_ERRORS);

		conditionnalEClass = createEClass(CONDITIONNAL);
		createEReference(conditionnalEClass, CONDITIONNAL__ALTERNATIVE);
		createEReference(conditionnalEClass, CONDITIONNAL__ELSE);
		createEAttribute(conditionnalEClass, CONDITIONNAL__QUERY);

		repetitionEClass = createEClass(REPETITION);
		createEAttribute(repetitionEClass, REPETITION__ITERATION_VAR);
		createEAttribute(repetitionEClass, REPETITION__QUERY);

		queryEClass = createEClass(QUERY);
		createEAttribute(queryEClass, QUERY__BEHAVIOR);
		createEAttribute(queryEClass, QUERY__QUERY);

		tableMergeEClass = createEClass(TABLE_MERGE);
		createEAttribute(tableMergeEClass, TABLE_MERGE__LEGEND);

		imageEClass = createEClass(IMAGE);
		createEAttribute(imageEClass, IMAGE__LEGEND);
		createEAttribute(imageEClass, IMAGE__FILE_NAME);
		createEAttribute(imageEClass, IMAGE__HEIGHT);
		createEAttribute(imageEClass, IMAGE__WIDTH);
		createEAttribute(imageEClass, IMAGE__LEGEND_POS);

		defaultEClass = createEClass(DEFAULT);

		compoundEClass = createEClass(COMPOUND);
		createEReference(compoundEClass, COMPOUND__SUB_CONSTRUCTS);

		templateEClass = createEClass(TEMPLATE);
		createEAttribute(templateEClass, TEMPLATE__TEMPLATE_NAME);
		createEAttribute(templateEClass, TEMPLATE__DOCUMENT);

		representationEClass = createEClass(REPRESENTATION);
		createEAttribute(representationEClass, REPRESENTATION__QUERY);
		createEAttribute(representationEClass, REPRESENTATION__REPRESENTATION_ID);

		staticFragmentEClass = createEClass(STATIC_FRAGMENT);

		varRefEClass = createEClass(VAR_REF);
		createEAttribute(varRefEClass, VAR_REF__VAR_NAME);

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

		// Create enums
		queryBehaviorEEnum = createEEnum(QUERY_BEHAVIOR);
		positionEEnum = createEEnum(POSITION);

		// Create data types
		bodyEDataType = createEDataType(BODY);
		wTableEDataType = createEDataType(WTABLE);
		runEDataType = createEDataType(RUN);
		astResultEDataType = createEDataType(AST_RESULT);
		documentParsingErrorEDataType = createEDataType(DOCUMENT_PARSING_ERROR);
		wTableRowEDataType = createEDataType(WTABLE_ROW);
		wTableCellEDataType = createEDataType(WTABLE_CELL);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
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
		queryEClass.getESuperTypes().add(this.getAbstractConstruct());
		tableMergeEClass.getESuperTypes().add(this.getCompound());
		imageEClass.getESuperTypes().add(this.getAbstractConstruct());
		defaultEClass.getESuperTypes().add(this.getCompound());
		compoundEClass.getESuperTypes().add(this.getAbstractConstruct());
		templateEClass.getESuperTypes().add(this.getCompound());
		representationEClass.getESuperTypes().add(this.getAbstractConstruct());
		staticFragmentEClass.getESuperTypes().add(this.getAbstractConstruct());
		varRefEClass.getESuperTypes().add(this.getAbstractConstruct());
		tableEClass.getESuperTypes().add(this.getAbstractConstruct());

		// Initialize classes, features, and operations; add parameters
		initEClass(abstractConstructEClass, AbstractConstruct.class, "AbstractConstruct", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAbstractConstruct_StyleRun(), this.getRun(), "styleRun", null, 0, 1, AbstractConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractConstruct_Runs(), this.getRun(), "runs", null, 0, -1, AbstractConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractConstruct_ClosingRuns(), this.getRun(), "closingRuns", null, 0, -1, AbstractConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAbstractConstruct_ParsingErrors(), this.getDocumentParsingError(), "parsingErrors", null, 0, -1, AbstractConstruct.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionnalEClass, Conditionnal.class, "Conditionnal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionnal_Alternative(), this.getConditionnal(), null, "alternative", null, 0, 1, Conditionnal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionnal_Else(), this.getDefault(), null, "else", null, 0, 1, Conditionnal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConditionnal_Query(), this.getAstResult(), "query", null, 0, 1, Conditionnal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repetitionEClass, Repetition.class, "Repetition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepetition_IterationVar(), ecorePackage.getEString(), "iterationVar", null, 0, 1, Repetition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepetition_Query(), this.getAstResult(), "query", null, 0, 1, Repetition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(queryEClass, Query.class, "Query", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQuery_Behavior(), this.getQueryBehavior(), "behavior", "TEXT", 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuery_Query(), this.getAstResult(), "query", null, 0, 1, Query.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableMergeEClass, TableMerge.class, "TableMerge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTableMerge_Legend(), ecorePackage.getEString(), "legend", null, 0, 1, TableMerge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(imageEClass, Image.class, "Image", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImage_Legend(), ecorePackage.getEString(), "legend", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImage_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImage_Height(), ecorePackage.getEInt(), "height", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImage_Width(), ecorePackage.getEInt(), "width", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImage_LegendPOS(), this.getPOSITION(), "legendPOS", null, 0, 1, Image.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(defaultEClass, Default.class, "Default", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compoundEClass, Compound.class, "Compound", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCompound_SubConstructs(), this.getAbstractConstruct(), null, "subConstructs", null, 0, -1, Compound.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(templateEClass, Template.class, "Template", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTemplate_TemplateName(), ecorePackage.getEString(), "templateName", null, 0, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemplate_Document(), this.getBody(), "document", null, 0, 1, Template.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(representationEClass, Representation.class, "Representation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepresentation_Query(), this.getAstResult(), "query", null, 0, 1, Representation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepresentation_RepresentationId(), ecorePackage.getEString(), "representationId", null, 0, 1, Representation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(staticFragmentEClass, StaticFragment.class, "StaticFragment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(varRefEClass, VarRef.class, "VarRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVarRef_VarName(), ecorePackage.getEString(), "varName", null, 0, 1, VarRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableEClass, Table.class, "Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTable_Rows(), this.getRow(), null, "rows", null, 0, -1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTable_Table(), this.getWTable(), "table", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rowEClass, Row.class, "Row", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRow_Cells(), this.getCell(), null, "cells", null, 0, -1, Row.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRow_TableRow(), this.getWTableRow(), "tableRow", null, 0, 1, Row.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cellEClass, Cell.class, "Cell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCell_Template(), this.getTemplate(), null, "template", null, 0, 1, Cell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCell_TableCell(), this.getWTableCell(), "tableCell", null, 0, 1, Cell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentTemplateEClass, DocumentTemplate.class, "DocumentTemplate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDocumentTemplate_Headers(), this.getTemplate(), null, "headers", null, 0, -1, DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentTemplate_Footers(), this.getTemplate(), null, "footers", null, 0, -1, DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentTemplate_Body(), this.getTemplate(), null, "body", null, 0, 1, DocumentTemplate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(queryBehaviorEEnum, QueryBehavior.class, "QueryBehavior");
		addEEnumLiteral(queryBehaviorEEnum, QueryBehavior.ICON);
		addEEnumLiteral(queryBehaviorEEnum, QueryBehavior.LABEL);
		addEEnumLiteral(queryBehaviorEEnum, QueryBehavior.TEXT);

		initEEnum(positionEEnum, org.eclipse.gendoc2.template.POSITION.class, "POSITION");
		addEEnumLiteral(positionEEnum, org.eclipse.gendoc2.template.POSITION.ABOVE);
		addEEnumLiteral(positionEEnum, org.eclipse.gendoc2.template.POSITION.BELOW);

		// Initialize data types
		initEDataType(bodyEDataType, IBody.class, "Body", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(wTableEDataType, XWPFTable.class, "WTable", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(runEDataType, XWPFRun.class, "Run", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(astResultEDataType, AstResult.class, "AstResult", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(documentParsingErrorEDataType, DocumentParsingError.class, "DocumentParsingError", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(wTableRowEDataType, XWPFTableRow.class, "WTableRow", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(wTableCellEDataType, XWPFTableCell.class, "WTableCell", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";	
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "documentation", "Le m\u00e9ta mod\u00e8le \'template\' repr\u00e9sente les concepts produit par le parsing \r\ndes templates de g\u00e9n\u00e9ration documentaire.\r\n\r\nLes mod\u00e8les instances sont utilis\u00e9s pour la g\u00e9n\u00e9ration."
		   });	
		addAnnotation
		  (conditionnalEClass, 
		   source, 
		   new String[] {
			 "constraints", "AlternativeExcludeElse",
			 "documentation", "Si l\'expression expr est \u00e9valu\u00e9e \u00e0 true dans le contexte courant alors l\'ensemble de la balise jusqu\'\u00e0 la balise gd:endif comprisee est remplac\u00e9e par le traitement de runs1 dans le contexte courant. Sinon, l\'ensemble des balises est remplac\u00e9e par le traitement du premier run_n tel que l\'expression expr de la balise gd:elseif associ\u00e9e est \u00e9valu\u00e9e \u00e0 vrai. Si aucune telle balise n\'est pr\u00e9sente ou si aucune d\'entre-elle porte une expression \u00e9valu\u00e9e \u00e0 true, le r\u00e9sultat du traitement de run_else, si la balise gd:else est pr\u00e9sente, dans le contexte courant est ins\u00e9r\u00e9 \u00e0 la place de l\'ensemble des balises",
			 "syntax", "{gd:if expr} runs1 [{gd:elseif <expr>} runs_n]* [{gd:else} run_else]{gd:endif}"
		   });	
		addAnnotation
		  (repetitionEClass, 
		   source, 
		   new String[] {
			 "documentation", "pour chaque valeur v dans le r\u00e9sultat de l\'\u00e9valuation de query, \r\n- un nouveau contexte est cr\u00e9e en ajoutant la d\u00e9finition \'var =v\' au contexte courant\r\n- le r\u00e9sultat du traitement du corps runs est ins\u00e9r\u00e9 dans le document \r\nles balises {gd:for} et {gd:endfor} sont supprim\u00e9es.\r\nSi la balise {gd:for} est imm\u00e9diatement suivie d\'un retour chariot alors l\'ensemble du paragraphe la contenant est supprim\u00e9e du r\u00e9sultat sinon, le paragraphe duquel on a supprim\u00e9 la balise est ins\u00e9r\u00e9 dans le r\u00e9sultat. Le m\u00eame traitement est appliqu\u00e9 \u00e0 la balise {gd:endfor}",
			 "syntax", "{gd:for var | query} body {gd:endfor}"
		   });	
		addAnnotation
		  (queryEClass, 
		   source, 
		   new String[] {
			 "documentation", "La balise est remplac\u00e9e dans le document par la repr\u00e9sentation en \r\nchaine de caract\u00e8re du r\u00e9sultat de l\'\u00e9valuation de l\'expression dans le \r\ncontexte courant. La style du premier run contenant query est utilis\u00e9 pour g\u00e9n\u00e9rer le document. Il n\'y a pas de sens \u00e0 utiliser plusieurs styles diff\u00e9rents dans une requ\u00eate si bien que seul le premier est utilis\u00e9 si plusieurs sont pr\u00e9sents.\r\n- Lorsque le modifier icon est pr\u00e9cis\u00e9, l\'icone de l\'\u00e9l\u00e9ment \u00e9valu\u00e9 telle que d\u00e9finie dans le .edit correspondant  est ins\u00e9r\u00e9e \u00e0 la place du run\r\n- Lorsque le modifier label est pr\u00e9cis\u00e9, le label de l\'\u00e9l\u00e9ment \u00e9valu\u00e9 tel que d\u00e9finin dans le .edit correspondant est ins\u00e9r\u00e9 \u00e0 la place du run.\r\n- Lorsque le modifier text ou qu\'aucun modifier n\'apparait, la repr\u00e9sentation en chaine de caract\u00e8re de l\'\u00e9valuation du r\u00e9sultat est ins\u00e9r\u00e9.",
			 "syntax", "{aql:query [icon, label,text]}"
		   });	
		addAnnotation
		  (varRefEClass, 
		   source, 
		   new String[] {
			 "documentation", "remplace le champ par la valeur de la variable dans l\'environnement ou par un commentaire disant que la variable n\'est pas d\u00e9finie.",
			 "syntax", "{var:varName}"
		   });
	}

} //TemplatePackageImpl
