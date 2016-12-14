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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFFooter;
import org.apache.poi.xwpf.usermodel.XWPFHeader;
import org.apache.poi.xwpf.usermodel.XWPFHeaderFooter;
import org.eclipse.acceleo.query.runtime.IQueryEnvironment;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.api.POIServices;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHdrFtr;

/**
 * Class that orchestrates the document generation from a template and a set of definitions.
 * 
 * @author Romain Guider
 */
public class DocumentGenerator {
    /**
     * The {@link IQueryEnvironment} instance used for evaluating all the AQL
     * queries found in the template.
     */
    private IQueryEnvironment queryEnvironment;

    /**
     * variable definition used during generation.
     */
    private Map<String, Object> definitions;
    /**
     * An EObject from the conf model from which the generation has been called.
     */
    private EObject targetConfObject;
    /**
     * The template against which generation is done.
     */
    private DocumentTemplate template;
    /**
     * The file where the generation is stored.
     */
    private URI destinationFileName;
    /**
     * The generated document.
     */
    private XWPFDocument destinationDocument;

    /**
     * Create a new {@link DocumentGenerator} instance given a template and a
     * variable definition map.
     * 
     * @param inputDocumentFileName
     *            the template used for generation
     * @param destinationFileName
     *            the destination file name of the generation
     * @param theTemplate
     *            the template used to generate
     * @param variables
     *            a mapping of variables used during generation.
     * @param environment
     *            the {@link IQueryEnvironment} used to generate
     * @param theTargetConfObject
     *            the root EObject of the gen conf model.
     * @throws DocumentGenerationException
     *             when a generation problem occurs.
     */
    public DocumentGenerator(URI inputDocumentFileName, URI destinationFileName, DocumentTemplate theTemplate,
            Map<String, Object> variables, IQueryEnvironment environment, EObject theTargetConfObject)
            throws DocumentGenerationException {
        this.definitions = new HashMap<>(variables);
        this.template = theTemplate;
        this.destinationFileName = destinationFileName;
        this.queryEnvironment = environment;
        this.targetConfObject = theTargetConfObject;
        try {
            this.destinationDocument = createDestinationDocument(inputDocumentFileName);
        } catch (InvalidFormatException e) {
            throw new DocumentGenerationException("Input document seems to have an invalid format.", e);
        } catch (IOException e) {
            throw new DocumentGenerationException("An I/O problem occured while creating the output document.", e);
        }
    }

    /**
     * actually trigger the document generation process.
     * 
     * @throws IOException
     *             if an I/O problem occurs during generation.
     * @throws DocumentParserException
     *             Last generated Document Parser Exception
     */
    public void generate() throws IOException, DocumentParserException {
        // The output document is created from the input so as to get the styles
        // and definitions in the original template.
        final BookmarkManager bookmarkManager = new BookmarkManager();
        final UserContentManager userContentManager = new UserContentManager(this.destinationFileName);
        TemplateProcessor processor = new TemplateProcessor(definitions, bookmarkManager, userContentManager,
                queryEnvironment, destinationDocument, targetConfObject);
        processor.doSwitch(this.template.getBody());
        Iterator<XWPFFooter> footers = destinationDocument.getFooterList().iterator();
        for (Template footerTemplate : this.template.getFooters()) {
            XWPFFooter footer = footers.next();
            cleanHeaderFooter(footer);
            TemplateProcessor footerProc = new TemplateProcessor(definitions, bookmarkManager, userContentManager,
                    queryEnvironment, footer, targetConfObject);
            footerProc.doSwitch(footerTemplate);
            footerProc.clear();
        }
        Iterator<XWPFHeader> headers = destinationDocument.getHeaderList().iterator();
        for (Template headerTemplate : this.template.getHeaders()) {
            XWPFHeader header = headers.next();
            cleanHeaderFooter(header);
            TemplateProcessor headerProc = new TemplateProcessor(definitions, bookmarkManager, userContentManager,
                    queryEnvironment, header, targetConfObject);
            headerProc.doSwitch(headerTemplate);
            headerProc.clear();
        }

        bookmarkManager.markDanglingReferences();
        bookmarkManager.markOpenBookmarks();
        // At this point, the documnet has been generated and just needs being
        // writen on disk.
        POIServices.getInstance().saveFile(destinationDocument, destinationFileName);

        // Remove temporary last destination document
        userContentManager.dispose();
        processor.clear();
    }

    /**
     * Clear up the header or footer from the existing paragraphs and tables.
     * 
     * @param headerFooter
     *            the header or footer to clean up.
     */
    void cleanHeaderFooter(XWPFHeaderFooter headerFooter) {
        CTHdrFtr ctHdrFtr = (CTHdrFtr) headerFooter._getHdrFtr().copy();
        ctHdrFtr.getPList().clear();
        ctHdrFtr.getTblList().clear();
        // XXX : there are many other lists in the header and footer that should
        // probably be cleaned.
        headerFooter.setHeaderFooter(ctHdrFtr);
    }

    /**
     * creates the destination document.
     * 
     * @param templateFileName
     *            the name of the destination document's file.
     * @return the created document
     * @throws IOException
     *             if an I/O problem occurs.
     * @throws InvalidFormatException
     *             if the generated file has a format problem.
     */
    private XWPFDocument createDestinationDocument(URI inputDocumentFileName)
            throws IOException, InvalidFormatException {
        XWPFDocument document = POIServices.getInstance().getXWPFDocument(inputDocumentFileName);
        int size = document.getBodyElements().size();
        for (int i = 0; i < size; i++) {
            document.removeBodyElement(0);
        }
        return document;
    }

}
