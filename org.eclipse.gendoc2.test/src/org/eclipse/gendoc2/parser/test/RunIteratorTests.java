package org.eclipse.gendoc2.parser.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.ecliplse.gendoc2.parser.RunIterator;
import org.junit.Ignore;
import org.junit.Test;

public class RunIteratorTests {

	@Test
	public void testNonEmptyDoc() throws InvalidFormatException, IOException {
		FileInputStream is = new FileInputStream("templates/RunIteratorTest.docx");
		OPCPackage oPackage = OPCPackage.open(is);
		XWPFDocument document = new XWPFDocument(oPackage);
		RunIterator iterator = new RunIterator(document);
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
		RunIterator iterator = new RunIterator(document);
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
		RunIterator iterator = new RunIterator(document);
		assertTrue(!iterator.hasNext());
	}
}
