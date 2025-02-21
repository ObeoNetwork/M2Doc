/*******************************************************************************
 *  Copyright (c) 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.TokenIterator;
import org.obeonetwork.m2doc.parser.TokenIteratorFieldRewriter;
import org.obeonetwork.m2doc.parser.TokenProvider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TokenProviderTokenIteratorFieldRewriterTests {

    @Test
    public void testNonEmptyDoc() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator tokenIterator = new TokenIteratorFieldRewriter(document);
            TokenProvider iterator = new TokenProvider(tokenIterator);
            XWPFRun run = iterator.next().getRun();
            assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("P1Run2", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals(" P1Run3", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("P2Run1 ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("P2Run2", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals(" ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("P2Run3", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("", run.getText(run.getTextPosition()));
            assertTrue(!iterator.hasNext());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testAccessEmptyIterator() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator tokenIterator = new TokenIteratorFieldRewriter(document);
            TokenProvider iterator = new TokenProvider(tokenIterator);
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
        }
    }

    @Test
    public void testLookaheadEmptyIterator() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator tokenIterator = new TokenIteratorFieldRewriter(document);
            TokenProvider iterator = new TokenProvider(tokenIterator);
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            iterator.next().getRun();
            assertNull(iterator.lookAhead(1));
        }
    }

    @Test
    public void testHasElements() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator tokenIterator = new TokenIteratorFieldRewriter(document);
            TokenProvider iterator = new TokenProvider(tokenIterator);
            // CHECKSTYLE:OFF
            assertTrue(iterator.hasElements(7));
            // CHECKSTYLE:ON
            XWPFRun run = iterator.next().getRun();
            assertTrue(iterator.hasElements(6));
            assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertTrue(iterator.hasElements(5));
            assertEquals("P1Run2", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertTrue(iterator.hasElements(4));
            assertEquals(" P1Run3", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertTrue(iterator.hasElements(3));
            assertEquals("P2Run1 ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertTrue(iterator.hasElements(2));
            assertEquals("P2Run2", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertTrue(iterator.hasElements(1));
            assertEquals(" ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertTrue(iterator.hasElements(0));
            assertEquals("P2Run3", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("", run.getText(run.getTextPosition()));
            assertTrue(!iterator.hasNext());
        }
    }

    @Test
    public void testLookAhead() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator tokenIterator = new TokenIteratorFieldRewriter(document);
            TokenProvider iterator = new TokenProvider(tokenIterator);
            // CHECKSTYLE:OFF
            assertTrue(iterator.hasElements(7));
            XWPFRun run;
            run = iterator.lookAhead(1).getRun();
            assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(2).getRun();
            assertEquals("P1Run2", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(3).getRun();
            assertEquals(" P1Run3", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(4).getRun();
            assertEquals("P2Run1 ", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(5).getRun();
            assertEquals("P2Run2", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(6).getRun();
            assertEquals(" ", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(7).getRun();
            assertEquals("P2Run3", run.getText(run.getTextPosition()));
            assertTrue(iterator.hasElements(7));
            // CHECKSTYLE:ON
        }
    }

    @Test
    public void testNextWitLookAhead() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator tokenIterator = new TokenIteratorFieldRewriter(document);
            TokenProvider iterator = new TokenProvider(tokenIterator);
            // CHECKSTYLE:OFF
            assertTrue(iterator.hasElements(7));
            // CHECKSTYLE:ON
            XWPFRun run;
            run = iterator.lookAhead(1).getRun();
            assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(1).getRun();
            assertEquals("P1Run2", run.getText(run.getTextPosition()));
            run = iterator.lookAhead(2).getRun();
            assertEquals(" P1Run3", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals("P1Run2", run.getText(run.getTextPosition()));
            run = iterator.next().getRun();
            assertEquals(" P1Run3", run.getText(run.getTextPosition()));
            assertTrue(iterator.hasElements(4));
        }
    }

}
