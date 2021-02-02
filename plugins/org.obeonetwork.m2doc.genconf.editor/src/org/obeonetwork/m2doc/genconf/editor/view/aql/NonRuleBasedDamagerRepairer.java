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
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.Region;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.presentation.IPresentationDamager;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.swt.custom.StyleRange;

/**
 * Non rule based {@link IPresentationDamager} and {@link IPresentationRepairer}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class NonRuleBasedDamagerRepairer implements IPresentationDamager, IPresentationRepairer {

    /**
     * The document this object works on.
     */
    protected IDocument document;

    /**
     * The default text attribute if non is returned as data by the current token.
     */
    protected TextAttribute defaultTextAttribute;

    /**
     * Constructor.
     * 
     * @param defaultTextAttribute
     *            default {@link TextAttribute}.
     */
    public NonRuleBasedDamagerRepairer(TextAttribute defaultTextAttribute) {
        assert defaultTextAttribute != null;

        this.defaultTextAttribute = defaultTextAttribute;
    }

    @Override
    public void setDocument(IDocument doc) {
        this.document = doc;
    }

    /**
     * Returns the end offset of the line that contains the specified offset or if the offset is inside a line
     * delimiter, the end offset of the next line.
     *
     * @param offset
     *            the offset whose line end offset must be computed
     * @return the line end offset for the given offset
     * @exception BadLocationException
     *                if offset is invalid in the current document
     */
    protected int endOfLineOf(int offset) throws BadLocationException {
        int res;

        IRegion info = document.getLineInformationOfOffset(offset);
        if (offset <= info.getOffset() + info.getLength()) {
            res = info.getOffset() + info.getLength();
        } else {
            int line = document.getLineOfOffset(offset);
            try {
                info = document.getLineInformation(line + 1);
                res = info.getOffset() + info.getLength();
            } catch (BadLocationException x) {
                res = document.getLength();
            }
        }

        return res;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.presentation.IPresentationDamager#getDamageRegion(org.eclipse.jface.text.ITypedRegion,
     *      org.eclipse.jface.text.DocumentEvent, boolean)
     */
    @Override
    public IRegion getDamageRegion(ITypedRegion partition, DocumentEvent event, boolean documentPartitioningChanged) {
        if (!documentPartitioningChanged) {
            try {

                final IRegion info = document.getLineInformationOfOffset(event.getOffset());
                final int start = Math.max(partition.getOffset(), info.getOffset());

                int end;
                if (event.getText() == null) {
                    end = event.getOffset() + event.getLength();
                } else {
                    end = event.getOffset() + event.getText().length();
                }

                if (info.getOffset() <= end && end <= info.getOffset() + info.getLength()) {
                    // optimize the case of the same line
                    end = info.getOffset() + info.getLength();
                } else {
                    end = endOfLineOf(end);
                }
                end = Math.min(partition.getOffset() + partition.getLength(), end);
                return new Region(start, end - start);

            } catch (BadLocationException x) {
                // nothing to do here
            }
        }

        return partition;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.presentation.IPresentationRepairer#createPresentation(org.eclipse.jface.text.TextPresentation,
     *      org.eclipse.jface.text.ITypedRegion)
     */
    @Override
    public void createPresentation(TextPresentation presentation, ITypedRegion region) {
        addRange(presentation, region.getOffset(), region.getLength(), defaultTextAttribute);
    }

    /**
     * Adds style information to the given text presentation.
     *
     * @param presentation
     *            the text presentation to be extended
     * @param offset
     *            the offset of the range to be styled
     * @param length
     *            the length of the range to be styled
     * @param attr
     *            the attribute describing the style of the range to be styled
     */
    protected void addRange(TextPresentation presentation, int offset, int length, TextAttribute attr) {
        if (attr != null) {
            presentation.addStyleRange(
                    new StyleRange(offset, length, attr.getForeground(), attr.getBackground(), attr.getStyle()));
        }
    }
}
