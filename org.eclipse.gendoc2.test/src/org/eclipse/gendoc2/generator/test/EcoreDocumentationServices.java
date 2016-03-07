package org.eclipse.gendoc2.generator.test;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

public class EcoreDocumentationServices {

	private static final String DOC_KEY = "documentation";
	private static final String CONSTRAINT_KEY = "constraints";

	private static final String ECORE_SOURCE = "http://www.eclipse.org/emf/2002/Ecore";

	public EcoreDocumentationServices() {

	}

	/**
	 * Returns the documentation of an element.
	 * 
	 * @param element
	 * @return
	 */
	String documentation(EPackage element) {
		for (EAnnotation annotation : element.getEAnnotations()) {
			if (annotation.getSource().equals(ECORE_SOURCE)) {
				return annotation.getDetails().get(DOC_KEY);
			}
		}
		return "";
	}

	/**
	 * Returns the constraints of an EClass.
	 * 
	 * @param element
	 * @return
	 */
	String documentation(EClass eClass) {
		for (EAnnotation annotation : eClass.getEAnnotations()) {
			if (annotation.getSource().equals(ECORE_SOURCE)) {
				return annotation.getDetails().get(CONSTRAINT_KEY);
			}
		}
		return "";
	}

}
