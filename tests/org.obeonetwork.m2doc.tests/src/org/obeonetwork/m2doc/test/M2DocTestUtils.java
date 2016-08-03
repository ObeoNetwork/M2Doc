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
package org.obeonetwork.m2doc.test;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.m2doc.parser.TemplateValidationMessage;
import org.obeonetwork.m2doc.parser.ValidationMessageLevel;
import org.obeonetwork.m2doc.template.AbstractConstruct;
import org.obeonetwork.m2doc.template.DocumentTemplate;
import org.obeonetwork.m2doc.template.Template;
import org.obeonetwork.m2doc.template.TemplatePackage;

import static org.junit.Assert.assertEquals;

/**
 * Utilities for M2Doc tests.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public final class M2DocTestUtils {

    /**
     * Constructor.
     */
    private M2DocTestUtils() {
        // nothing to do here
    }

    /**
     * Asserts the given {@link TemplateValidationMessage}.
     * 
     * @param actualMessage
     *            the actual {@link TemplateValidationMessage}
     * @param expectedLevel
     *            the expected {@link TemplateValidationMessage#getLevel() level}
     * @param exprectedMessage
     *            the expected {@link TemplateValidationMessage#getMessage() message}
     * @param expectetLocation
     *            the expected {@link TemplateValidationMessage#getLocation() location}
     */
    public static void assertTemplateValidationMessage(TemplateValidationMessage actualMessage,
            ValidationMessageLevel expectedLevel, String exprectedMessage, XWPFRun expectetLocation) {
        assertEquals(expectedLevel, actualMessage.getLevel());
        assertEquals(exprectedMessage, actualMessage.getMessage());
        assertEquals(expectetLocation, actualMessage.getLocation());
    }

    /**
     * Gets the {@link XWPFRun} containing the given text in the given {@link XWPFDocument}.
     * 
     * @param document
     *            the {@link XWPFDocument}
     * @param text
     *            the {@link XWPFRun}
     * @return the {@link XWPFRun} containing the given text in the given {@link XWPFDocument} if any, <code>null</code> otherwise
     */
    public static XWPFRun getRunContaining(XWPFDocument document, String text) {
        XWPFRun res = null;

        for (XWPFParagraph paragraph : document.getParagraphs()) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.text().contains(text)) {
                    res = run;
                    break;
                }
            }
        }

        return res;
    }

    /**
     * Creates a new {@link DocumentTemplate} with the given {@link DocumentTemplate#getBody() body}. The body is linked to {@link XWPFRun}.
     * 
     * @param body
     *            the {@link Template}
     * @return a new {@link DocumentTemplate}
     */
    public static DocumentTemplate createDocumentTemplate(Template body) {
        final DocumentTemplate res = TemplatePackage.eINSTANCE.getTemplateFactory().createDocumentTemplate();

        final XWPFDocument document = new XWPFDocument();
        res.setDocument(document);
        res.setBody(body);

        final XWPFParagraph paragraph = document.createParagraph();

        linkRuns(paragraph, body);

        return res;
    }

    /**
     * Links the given {@link AbstractConstruct} with new {@link XWPFRun} created in the given {@link XWPFParagraph}.
     * 
     * @param paragraph
     *            the {@link XWPFParagraph}
     * @param construct
     *            the {@link AbstractConstruct}
     */
    private static void linkRuns(XWPFParagraph paragraph, AbstractConstruct construct) {
        construct.setStyleRun(paragraph.createRun());

        construct.getRuns().add(paragraph.createRun());
        construct.getRuns().add(paragraph.createRun());
        construct.getRuns().add(paragraph.createRun());

        for (EObject child : construct.eContents()) {
            if (child instanceof AbstractConstruct) {
                linkRuns(paragraph, (AbstractConstruct) child);
            }
        }

        construct.getClosingRuns().add(paragraph.createRun());
        construct.getClosingRuns().add(paragraph.createRun());
        construct.getClosingRuns().add(paragraph.createRun());
    }

}
