package org.ecliplse.gendoc2.parser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class RunIterator implements Iterator<XWPFRun> {

	private XWPFDocument document;

	private Iterator<XWPFParagraph> paragraphIterator;
	private Iterator<XWPFRun> runIterator;

	public RunIterator(XWPFDocument inputDocument) {
		this.document = inputDocument;
		if (inputDocument == null) {
			throw new IllegalArgumentException("Input documnet shouldn't be null");
		}
		paragraphIterator = inputDocument.getParagraphs().iterator();
	}

	@Override
	public XWPFRun next() {
		if (runIterator == null || !runIterator.hasNext()) {
			while (paragraphIterator.hasNext() && (runIterator == null || !runIterator.hasNext())) {
				runIterator = paragraphIterator.next().getRuns().iterator();
			}
		}
		if (runIterator != null && runIterator.hasNext()) {
			return runIterator.next();
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean hasNext() {
		if (runIterator == null || !runIterator.hasNext()) {
			while (paragraphIterator.hasNext() && (runIterator == null || !runIterator.hasNext())) {
				runIterator = paragraphIterator.next().getRuns().iterator();
			}
		}
		return runIterator != null && runIterator.hasNext();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
