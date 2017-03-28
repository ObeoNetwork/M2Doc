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
package org.obeonetwork.m2doc.tests.generator;

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
