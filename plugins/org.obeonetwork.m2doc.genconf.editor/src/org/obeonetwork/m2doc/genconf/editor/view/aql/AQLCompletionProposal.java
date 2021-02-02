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

import org.eclipse.acceleo.query.runtime.ICompletionProposal;
import org.eclipse.acceleo.query.runtime.ICompletionResult;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.contentassist.ICompletionProposalExtension5;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

/**
 * AQL {@link org.eclipse.jface.text.contentassist.ICompletionProposal}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AQLCompletionProposal implements org.eclipse.jface.text.contentassist.ICompletionProposal,
        ICompletionProposalExtension5, IContextInformation {

    /**
     * The {@link ILabelProvider}.
     */
    private final ILabelProvider labelProvider;

    /**
     * The {@link ICompletionResult}.
     */
    private final ICompletionResult completionResult;

    /**
     * The {@link ICompletionProposal}.
     */
    private final ICompletionProposal proposal;

    /**
     * Constructor.
     * 
     * @param labelProvider
     *            the {@link ILabelProvider}
     * @param completionResult
     *            the {@link ICompletionResult}
     * @param proposal
     *            the {@link ICompletionProposal}
     */
    public AQLCompletionProposal(ILabelProvider labelProvider, ICompletionResult completionResult,
            ICompletionProposal proposal) {
        this.labelProvider = labelProvider;
        this.completionResult = completionResult;
        this.proposal = proposal;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposal#apply(org.eclipse.jface.text.IDocument)
     */
    @Override
    public void apply(IDocument document) {
        try {
            document.replace(completionResult.getReplacementOffset(), completionResult.getReplacementLength(),
                    proposal.getProposal());
        } catch (BadLocationException e) {
            // nothing to do here
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getSelection(org.eclipse.jface.text.IDocument)
     */
    @Override
    public Point getSelection(IDocument document) {
        return new Point(completionResult.getReplacementOffset() + proposal.getCursorOffset(), 0);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getAdditionalProposalInfo()
     */
    @Override
    public String getAdditionalProposalInfo() {
        return proposal.getDescription();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getDisplayString()
     */
    @Override
    public String getDisplayString() {
        return labelProvider.getText(proposal);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getImage()
     */
    @Override
    public Image getImage() {
        return labelProvider.getImage(proposal);
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposal#getContextInformation()
     */
    @Override
    public IContextInformation getContextInformation() {
        return this;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.ICompletionProposalExtension5#getAdditionalProposalInfo(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public Object getAdditionalProposalInfo(org.eclipse.core.runtime.IProgressMonitor monitor) {
        return proposal.getDescription();
    }

    @Override
    public String getContextDisplayString() {
        return getAdditionalProposalInfo();
    }

    @Override
    public String getInformationDisplayString() {
        return getAdditionalProposalInfo();
    };

}
