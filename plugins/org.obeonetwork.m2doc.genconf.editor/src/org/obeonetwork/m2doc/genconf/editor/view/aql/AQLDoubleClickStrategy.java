/*******************************************************************************
 * Copyright (c) 2017, 2021 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.obeonetwork.m2doc.genconf.editor.view.aql;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextDoubleClickStrategy;
import org.eclipse.jface.text.ITextViewer;

/**
 * AQL {@link ITextDoubleClickStrategy}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AQLDoubleClickStrategy implements ITextDoubleClickStrategy {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.ITextDoubleClickStrategy#doubleClicked(org.eclipse.jface.text.ITextViewer)
     */
    @Override
    public void doubleClicked(ITextViewer part) {
        final int pos = part.getSelectedRange().x;
        if (pos >= 0) {
            selectWord(part, pos);
        }
    }

    /**
     * Selects a word in the given {@link ITextViewer} at the given caret position.
     * 
     * @param text
     *            the {@link ITextViewer}
     * @param caretPos
     *            the caret position
     * @return <code>true</code> if the selection has been made, <code>false</code> otherwise
     */
    protected boolean selectWord(ITextViewer text, int caretPos) {
        boolean res;

        IDocument doc = text.getDocument();
        int startPos;
        int endPos;

        try {
            int pos = caretPos;
            char c;

            while (pos >= 0) {
                c = doc.getChar(pos);
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
                pos--;
            }

            startPos = pos;

            pos = caretPos;
            int length = doc.getLength();

            while (pos < length) {
                c = doc.getChar(pos);
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
                pos++;
            }

            endPos = pos;
            selectRange(text, startPos, endPos);
            res = true;

        } catch (BadLocationException x) {
            res = false;
        }

        return res;
    }

    /**
     * Selects the given range.
     * 
     * @param text
     *            the {@link ITextViewer}
     * @param start
     *            the start position
     * @param stop
     *            the end position
     */
    private void selectRange(ITextViewer text, int start, int stop) {
        int offset = start + 1;
        int length = stop - offset;
        text.setSelectedRange(offset, length);
    }
}
