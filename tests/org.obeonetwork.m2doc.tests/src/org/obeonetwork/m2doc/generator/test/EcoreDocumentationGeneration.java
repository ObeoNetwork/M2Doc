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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.junit.Test;
import org.obeonetwork.m2doc.api.AQL4Compat;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentGenerator;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.TemplatePackage;
import org.obeonetwork.m2doc.util.M2DocUtils;

public class EcoreDocumentationGeneration {
    @Test
    public void testStaticFragmentWithFieldProcessing()
            throws IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        queryEnvironment.registerEPackage(EcorePackage.eINSTANCE);
        AQL4Compat.register(queryEnvironment, EcoreDocumentationServices.class);
        String templatePath = "templates/ecoreDocumentationTemplate.docx";
        try (DocumentTemplate template = M2DocUtils.parse(URI.createFileURI(templatePath), queryEnvironment)) {
            Map<String, Object> definitions = new HashMap<>();
            definitions.put("self", TemplatePackage.eINSTANCE);
            DocumentGenerator generator = new DocumentGenerator(URI.createFileURI(templatePath),
                    URI.createFileURI("results/ecoreDocumentationTemplateResults.docx"), template, definitions,
                    queryEnvironment, null);
            generator.generate();
            template.close();
        }
    }

}
