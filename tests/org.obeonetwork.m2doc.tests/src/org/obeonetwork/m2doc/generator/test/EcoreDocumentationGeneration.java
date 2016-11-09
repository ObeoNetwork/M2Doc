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
package org.obeonetwork.m2doc.generator.test;

//CHECKSTYLE:OFF
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.api.AQL4Compat;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentTemplateParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.TemplatePackage;

public class EcoreDocumentationGeneration {
    @Test
    public void testStaticFragmentWithFieldProcessing()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        AQL4Compat.register(queryEnvironment, EcoreDocumentationServices.class);
        FileInputStream is = new FileInputStream("templates/ecoreDocumentationTemplate.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentTemplateParser parser = new DocumentTemplateParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        Map<String, Object> definitions = new HashMap<String, Object>();
        definitions.put("self", TemplatePackage.eINSTANCE);
        DocumentGenerator generator = new DocumentGenerator("templates/ecoreDocumentationTemplate.docx",
                "results/generated/ecoreDocumentationTemplateResults.docx", template, definitions, queryEnvironment, null);
        generator.generate();
    }

}
