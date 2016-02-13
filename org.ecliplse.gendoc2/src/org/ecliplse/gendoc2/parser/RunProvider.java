package org.ecliplse.gendoc2.parser;

import java.util.Iterator;
import java.util.LinkedList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class RunProvider implements Iterator<XWPFRun> {

	private RunIterator runIterator;

	private LinkedList<XWPFRun> lookAhead;

	public RunProvider(XWPFDocument document) {
		this.runIterator = new RunIterator(document);
		lookAhead = new LinkedList<XWPFRun>();
	}

	/**
	 * Returns the ith element or <code>null</code> if there's no such element.
	 * 
	 * @param i
	 * @return the ith element or <code>null</code> if there's no such element.
	 */
	public XWPFRun lookAhead(int i) {
		loadNext(i);
		if (lookAhead.size() == 0 && i > 0) {
			return null;
		} else {
			return lookAhead.get(i - 1);
		}
	}

	/**
	 * returns <code>true</code> if there's <code>i</code> elements left to
	 * read.
	 * 
	 * @param i
	 *            the number of element inquired
	 * @return <code>true</code> if there's <code>i</code> elements left to read
	 */
	public boolean hasElements(int i) {
		loadNext(i);
		return lookAhead.size() >= i;
	}

	private void loadNext(int i) {
		int laSize = lookAhead.size();
		int leftToRead = i - laSize;
		int j = 0;
		while (runIterator.hasNext() && j < leftToRead) {
			lookAhead.addLast(runIterator.next());
			j++;
		}
	}

	@Override
	public boolean hasNext() {
		return !lookAhead.isEmpty() || runIterator.hasNext();
	}

	@Override
	public XWPFRun next() {
		if (!lookAhead.isEmpty()) {
			return lookAhead.removeFirst();
		} else {
			return runIterator.next();
		}
	}

}
