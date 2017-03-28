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
//CHECKSTYLE:OFF

import java.io.FileInputStream;
import java.math.BigInteger;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.m2doc.provider.ProviderRegistry;
import org.obeonetwork.m2doc.tests.provider.TestDiagramProvider;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentGeneratorTests {

    /**
     * Initialize registry.
     */
    @Before
    public void setUp() {
        ProviderRegistry.INSTANCE.clear();
        ProviderRegistry.INSTANCE.registerProvider(new TestDiagramProvider());
    }

    /**
     * Cleaning.
     */
    @After
    public void after() {
        ProviderRegistry.INSTANCE.clear();
    }

    @Test
    public void testBookmarkNominal() throws Exception {
        try (FileInputStream resIs = new FileInputStream("resources/bookmark/nominal/nominal-expected-generation.docx");
                OPCPackage resOPackage = OPCPackage.open(resIs);
                XWPFDocument resDocument = new XWPFDocument(resOPackage);) {

            assertEquals(5, resDocument.getBodyElements().size());
            assertTrue(resDocument.getBodyElements().get(1) instanceof XWPFParagraph);
            XWPFParagraph paragraph = (XWPFParagraph) resDocument.getBodyElements().get(1);
            assertEquals(6, paragraph.getRuns().size());
            assertEquals("Test link before bookmark : ", paragraph.getRuns().get(0).text());

            final BigInteger id = new BigInteger(paragraph.getRuns().get(1).getCTR().getRsidR());

            assertTrue(id != BigInteger.ZERO);
            assertEquals(1, paragraph.getRuns().get(1).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.BEGIN,
                    paragraph.getRuns().get(1).getCTR().getFldCharList().get(0).getFldCharType());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(2).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(2).getCTR().getInstrTextList().size());
            assertEquals(Space.PRESERVE, paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getSpace());
            assertEquals(" REF bookmark1 \\h ",
                    paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getStringValue());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(3).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(3).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.SEPARATE,
                    paragraph.getRuns().get(3).getCTR().getFldCharList().get(0).getFldCharType());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(4).getCTR().getRsidR()));
            assertEquals("a reference to bookmark1", paragraph.getRuns().get(4).text());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(5).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(5).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.END,
                    paragraph.getRuns().get(5).getCTR().getFldCharList().get(0).getFldCharType());

            assertTrue(resDocument.getBodyElements().get(1) instanceof XWPFParagraph);
            paragraph = (XWPFParagraph) resDocument.getBodyElements().get(2);

            assertEquals(1, paragraph.getCTP().getBookmarkStartList().size());
            assertEquals("bookmark1", paragraph.getCTP().getBookmarkStartList().get(0).getName());
            assertEquals(1, paragraph.getCTP().getBookmarkEndList().size());

            assertTrue(resDocument.getBodyElements().get(2) instanceof XWPFParagraph);
            paragraph = (XWPFParagraph) resDocument.getBodyElements().get(3);
            assertEquals(7, paragraph.getRuns().size());
            assertEquals("Test link after bookmark : ", paragraph.getRuns().get(0).text());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(1).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(1).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.BEGIN,
                    paragraph.getRuns().get(1).getCTR().getFldCharList().get(0).getFldCharType());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(2).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(2).getCTR().getInstrTextList().size());
            assertEquals(Space.PRESERVE, paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getSpace());
            assertEquals(" REF bookmark1 \\h ",
                    paragraph.getRuns().get(2).getCTR().getInstrTextList().get(0).getStringValue());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(3).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(3).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.SEPARATE,
                    paragraph.getRuns().get(3).getCTR().getFldCharList().get(0).getFldCharType());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(4).getCTR().getRsidR()));
            assertEquals("a reference to bookmark1", paragraph.getRuns().get(4).text());

            assertEquals(id, new BigInteger(paragraph.getRuns().get(5).getCTR().getRsidR()));
            assertEquals(1, paragraph.getRuns().get(5).getCTR().getFldCharList().size());
            assertEquals(STFldCharType.END,
                    paragraph.getRuns().get(5).getCTR().getFldCharList().get(0).getFldCharType());
        }
    }

}
