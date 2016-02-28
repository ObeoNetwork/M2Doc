package org.eclipse.gendoc2.parser;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBody;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class TokenIterator implements Iterator<ParsingToken> {

	private IBody document;

	private Iterator<IBodyElement> elementIterator;
	private Iterator<ParsingToken> tokenIterator;

	public TokenIterator(IBody inputBody) {
		this.document = inputBody;
		if (inputBody == null) {
			throw new IllegalArgumentException("Input documnet shouldn't be null");
		}
		elementIterator = inputBody.getBodyElements().iterator();
	}

	/**
	 * Put the iterator in a state where it is finished or there's a token to
	 * consumme in the currentIterator.
	 */
	private void moveToNextToken() {
		if (tokenIterator == null || !tokenIterator.hasNext()) {
			while (elementIterator.hasNext() && (tokenIterator == null || !tokenIterator.hasNext())) {
				final IBodyElement element = elementIterator.next();
				if (element.getElementType().equals(BodyElementType.PARAGRAPH)) {
					tokenIterator = new RunIterator(((XWPFParagraph) element).getRuns());
				} else if (element.getElementType().equals(BodyElementType.TABLE)) {
					tokenIterator = new TableIterator((XWPFTable) element);
				} else {
					throw new UnsupportedOperationException(
							"Unsupported type of body element : " + element.getElementType());
				}
			}
		}
	}

	@Override
	public ParsingToken next() {
		moveToNextToken();
		if (tokenIterator != null && tokenIterator.hasNext()) {
			return tokenIterator.next();
		} else {
			throw new NoSuchElementException();
		}
	}

	@Override
	public boolean hasNext() {
		moveToNextToken();
		return tokenIterator != null && tokenIterator.hasNext();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
