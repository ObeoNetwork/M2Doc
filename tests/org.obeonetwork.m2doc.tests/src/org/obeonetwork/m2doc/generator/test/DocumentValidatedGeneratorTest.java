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

import java.io.File;
//CHECKSTYLE:OFF
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.junit.Test;
import org.obeonetwork.m2doc.generator.DocumentGenerationException;
import org.obeonetwork.m2doc.generator.DocumentValidatedGenerator;
import org.obeonetwork.m2doc.parser.DocumentParser;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.DocumentTemplate;

import static org.junit.Assert.assertTrue;

/**
 * Tests the {@link DocumentValidatedGenerator} class.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class DocumentValidatedGeneratorTest {
    /**
     * Ensure that the validation generation produces a document.
     * 
     * @throws InvalidFormatException
     * @throws IOException
     * @throws DocumentParserException
     * @throws DocumentGenerationException
     */
    @Test
    public void testGeneration()
            throws InvalidFormatException, IOException, DocumentParserException, DocumentGenerationException {
        IQueryEnvironment queryEnvironment = org.eclipse.acceleo.query.runtime.Query
                .newEnvironmentWithDefaultServices(null);
        FileInputStream is = new FileInputStream("templates/testParsingErrorSimpleTag.docx");
        OPCPackage oPackage = OPCPackage.open(is);
        XWPFDocument document = new XWPFDocument(oPackage);
        DocumentParser parser = new DocumentParser(document, queryEnvironment);
        DocumentTemplate template = parser.parseDocument();
        DocumentValidatedGenerator generator = new DocumentValidatedGenerator("results/testParsingErrorSimpleTag.docx",
                template);
        generator.generate();
        assertTrue(new File("results/testParsingErrorSimpleTag.docx").exists());
    }

}
