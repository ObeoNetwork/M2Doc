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
package org.obeonetwork.m2doc.parser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.Ignore;
import org.junit.Test;
import org.obeonetwork.m2doc.parser.TokenIterator;

public class RunIteratorTests {

	@Test
	public void testNonEmptyDoc() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
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
		assertTrue(!iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testAccessEmptyIterator() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		TokenIterator iterator = new TokenIterator(document);
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
	}

	@Test
	@Ignore // POI Cannot open a document just created.
	public void testEmptyDoc() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/EmptyDoc.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		TokenIterator iterator = new TokenIterator(document);
		assertTrue(!iterator.hasNext());
	}
}
