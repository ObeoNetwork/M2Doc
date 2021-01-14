/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.EmptyFileException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.TokenIterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RunIteratorTests {

    @Test
    public void testNonEmptyDoc() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator iterator = new TokenIterator(document);
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

    @Test
    public void testAccessEmptyIterator() throws InvalidFormatException, IOException {
        try (FileInputStream is = new FileInputStream("resources/document/notEmpty/notEmpty-template.docx");
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator iterator = new TokenIterator(document);
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertNotNull(iterator.next());
            assertFalse(iterator.hasNext());
            boolean hasException = false;
            try {
                iterator.next();
            } catch (NoSuchElementException e) {
                assertTrue(e instanceof NoSuchElementException);
                hasException = true;
            }
            assertTrue(hasException);
        }
    }

    @Test(expected = EmptyFileException.class)
    public void testEmptyDoc() throws InvalidFormatException, IOException {
        final File file = new File("resources/document/empty/empty-template.docx");
        assertTrue(file.exists());
        try (FileInputStream is = new FileInputStream(file);
                OPCPackage oPackage = OPCPackage.open(is);
                XWPFDocument document = new XWPFDocument(oPackage);) {
            TokenIterator iterator = new TokenIterator(document);

            assertTrue(!iterator.hasNext());
        }
    }
}
