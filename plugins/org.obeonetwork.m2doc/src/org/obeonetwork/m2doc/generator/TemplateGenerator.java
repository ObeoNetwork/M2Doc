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

import java.io.IOException;

import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;

/**
 * Class that orchestrates the generation of the template with validation messages only and no evaluation.
 * 
 * @author pguilet<pierre.guilet@obeo.fr>
 */
public class TemplateGenerator {
    /**
     * The template against which generation is done.
     */
    private DocumentTemplate template;
    /**
     * The file where the validation is stored.
     */
    private String destinationFileName;

    /**
     * Create a new {@link TemplateGenerator} instance given a template.
     * paths.
     * 
     * @param destinationFileName
     *            the destination file of the validation
     * @param theTemplate
     *            the template used to generate
     * @throws DocumentGenerationException
     *             when a generation problem occurs.
     */
    public TemplateGenerator(String destinationFileName, DocumentTemplate theTemplate)
            throws DocumentGenerationException {
        this.template = theTemplate;
        this.destinationFileName = destinationFileName;
    }

    /**
     * actually trigger the document generation process.
     * 
     * @return if there are errors
     * @throws IOException
     *             if an I/O problem occurs during generation.
     */
    public boolean generate() throws IOException {
        boolean inError = false;
        TemplateValidationGenerator templateValidator = new TemplateValidationGenerator();
        templateValidator.doSwitch(this.template.getBody());
        inError = templateValidator.isInError();
        for (Template footerTemplate : this.template.getFooters()) {
            TemplateValidationGenerator footerValidator = new TemplateValidationGenerator();
            footerValidator.doSwitch(footerTemplate);
            inError = inError || footerValidator.isInError();

        }
        for (Template headerTemplate : this.template.getHeaders()) {
            TemplateValidationGenerator headerValidator = new TemplateValidationGenerator();
            headerValidator.doSwitch(headerTemplate);
            inError = inError || headerValidator.isInError();
        }
        if (inError) {
            POIServices.getInstance().saveFile(template.getDocument(), destinationFileName);
        }
        return inError;
    }

}
