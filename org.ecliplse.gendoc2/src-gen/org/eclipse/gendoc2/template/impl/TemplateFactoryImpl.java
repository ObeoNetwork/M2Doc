/**
 */
package org.eclipse.gendoc2.template.impl;

import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.acceleo.query.runtime.IQueryBuilderEngine.AstResult;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.gendoc2.parser.DocumentParsingError;
import org.eclipse.gendoc2.template.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TemplateFactoryImpl extends EFactoryImpl implements TemplateFactory {
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
			case TemplatePackage.QUERY: return createQuery();
			case TemplatePackage.TABLE: return createTable();
			case TemplatePackage.IMAGE: return createImage();
			case TemplatePackage.DEFAULT: return createDefault();
			case TemplatePackage.TEMPLATE: return createTemplate();
			case TemplatePackage.REPRESENTATION: return createRepresentation();
			case TemplatePackage.STATIC_FRAGMENT: return createStaticFragment();
			case TemplatePackage.VAR_REF: return createVarRef();
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
			case TemplatePackage.BODY:
				return createBodyFromString(eDataType, initialValue);
			case TemplatePackage.PARAGRAPH:
				return createParagraphFromString(eDataType, initialValue);
			case TemplatePackage.RUN:
				return createRunFromString(eDataType, initialValue);
			case TemplatePackage.AST_RESULT:
				return createAstResultFromString(eDataType, initialValue);
			case TemplatePackage.DOCUMENT_PARSING_ERROR:
				return createDocumentParsingErrorFromString(eDataType, initialValue);
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
			case TemplatePackage.BODY:
				return convertBodyToString(eDataType, instanceValue);
			case TemplatePackage.PARAGRAPH:
				return convertParagraphToString(eDataType, instanceValue);
			case TemplatePackage.RUN:
				return convertRunToString(eDataType, instanceValue);
			case TemplatePackage.AST_RESULT:
				return convertAstResultToString(eDataType, instanceValue);
			case TemplatePackage.DOCUMENT_PARSING_ERROR:
				return convertDocumentParsingErrorToString(eDataType, instanceValue);
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
	public Query createQuery() {
		QueryImpl query = new QueryImpl();
		return query;
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
	public VarRef createVarRef() {
		VarRefImpl varRef = new VarRefImpl();
		return varRef;
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
	public XWPFParagraph createParagraphFromString(EDataType eDataType, String initialValue) {
		return (XWPFParagraph)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertParagraphToString(EDataType eDataType, Object instanceValue) {
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
	public DocumentParsingError createDocumentParsingErrorFromString(EDataType eDataType, String initialValue) {
		return (DocumentParsingError)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDocumentParsingErrorToString(EDataType eDataType, Object instanceValue) {
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
