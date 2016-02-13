package org.eclipse.gendoc2.parser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.ecliplse.gendoc2.parser.RunProvider;
import org.junit.Test;

public class RunProviderTest {

	@Test
	public void testNonEmptyDoc() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunProvider iterator = new RunProvider(document);
		XWPFRun run = iterator.next();
		assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals("P1Run2", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals(" P1Run3", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals("P2Run1 ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals("P2Run2", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals(" ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals("P2Run3", run.getText(run.getTextPosition()));
		assertTrue(!iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testAccessEmptyIterator() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunProvider iterator = new RunProvider(document);
		XWPFRun run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
	}

	@Test
	public void testLookaheadEmptyIterator() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunProvider iterator = new RunProvider(document);
		XWPFRun run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		run = iterator.next();
		assertNull(iterator.lookAhead(1));
	}

	@Test
	public void testHasElements() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunProvider iterator = new RunProvider(document);
		assertTrue(iterator.hasElements(7));
		XWPFRun run = iterator.next();
		assertTrue(iterator.hasElements(6));
		assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertTrue(iterator.hasElements(5));
		assertEquals("P1Run2", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertTrue(iterator.hasElements(4));
		assertEquals(" P1Run3", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertTrue(iterator.hasElements(3));
		assertEquals("P2Run1 ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertTrue(iterator.hasElements(2));
		assertEquals("P2Run2", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertTrue(iterator.hasElements(1));
		assertEquals(" ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertTrue(iterator.hasElements(0));
		assertEquals("P2Run3", run.getText(run.getTextPosition()));
		assertTrue(!iterator.hasNext());
	}

	@Test
	public void testLookAhead() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunProvider iterator = new RunProvider(document);
		assertTrue(iterator.hasElements(7));
		XWPFRun run;
		run = iterator.lookAhead(1);
		assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(2);
		assertEquals("P1Run2", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(3);
		assertEquals(" P1Run3", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(4);
		assertEquals("P2Run1 ", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(5);
		assertEquals("P2Run2", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(6);
		assertEquals(" ", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(7);
		assertEquals("P2Run3", run.getText(run.getTextPosition()));
		assertTrue(iterator.hasElements(7));
	}

	@Test
	public void testNextWitLookAhead() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunProvider iterator = new RunProvider(document);
		assertTrue(iterator.hasElements(7));
		XWPFRun run;
		run = iterator.lookAhead(1);
		assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals("P1Run1 ", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(1);
		assertEquals("P1Run2", run.getText(run.getTextPosition()));
		run = iterator.lookAhead(2);
		assertEquals(" P1Run3", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals("P1Run2", run.getText(run.getTextPosition()));
		run = iterator.next();
		assertEquals(" P1Run3", run.getText(run.getTextPosition()));
		assertTrue(iterator.hasElements(4));
	}

}
