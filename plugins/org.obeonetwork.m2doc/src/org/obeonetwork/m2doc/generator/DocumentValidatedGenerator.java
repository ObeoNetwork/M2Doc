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
package org.obeonetwork.m2doc.generator;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;

/**
 * Class that orchestrates the generation of the template with parsing errors only and no evaluation..
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class DocumentValidatedGenerator {
    /**
     * The template against which generation is done.
     */
    private DocumentTemplate template;
    /**
     * The file where the generation is stored.
     */
    private String destinationFileName;

    /**
     * Create a new {@link DocumentValidatedGenerator} instance given a template.
     * paths.
     * 
     * @param inputDocumentFilePath
     *            the template used for generation
     * @param destinationFileName
     *            the destination file name of the generation
     * @param theTemplate
     *            the template used to generate
     * @throws DocumentGenerationException
     *             when a generation problem occurs.
     */
    public DocumentValidatedGenerator(String inputDocumentFilePath, String destinationFileName,
            DocumentTemplate theTemplate) throws DocumentGenerationException {
        this.template = theTemplate;
        this.destinationFileName = destinationFileName;
    }

    /**
     * actually trigger the document generation process.
     * 
     * @throws IOException
     *             if an I/O problem occurs during generation.
     */
    public void generate() throws IOException {
        TemplateParsingValidator templateValidator = new TemplateParsingValidator();
        templateValidator.doSwitch(this.template.getBody());
        for (Template footerTemplate : this.template.getFooters()) {
            TemplateParsingValidator footerValidator = new TemplateParsingValidator();
            footerValidator.doSwitch(footerTemplate);
        }
        for (Template headerTemplate : this.template.getHeaders()) {
            TemplateParsingValidator headerValidator = new TemplateParsingValidator();
            headerValidator.doSwitch(headerTemplate);
        }
        saveFile(template.getDocument(), destinationFileName);
    }

    /**
     * Save the document into the file pointing at the given path.
     * 
     * @param document
     *            the validated document to save.
     * @param theDestinationFileName
     *            the file were to save the content of the validated document.
     * @throws IOException
     *             throws if the writing of the document on the file system fails.
     */
    private void saveFile(XWPFDocument document, String theDestinationFileName) throws IOException {
        FileOutputStream os = new FileOutputStream(theDestinationFileName);
        try {
            document.write(os);
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

}
