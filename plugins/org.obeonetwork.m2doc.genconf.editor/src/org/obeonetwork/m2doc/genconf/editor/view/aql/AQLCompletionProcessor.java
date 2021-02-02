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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.acceleo.query.runtime.ICompletionResult;
import org.eclipse.acceleo.query.runtime.IQueryCompletionEngine;
import org.eclipse.acceleo.query.runtime.IReadOnlyQueryEnvironment;
import org.eclipse.acceleo.query.runtime.impl.BasicFilter;
import org.eclipse.acceleo.query.runtime.impl.QueryCompletionEngine;
import org.eclipse.acceleo.query.validation.type.IType;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContextInformationValidator;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.viewers.ILabelProvider;

/**
 * AQL {@link IContentAssistProcessor}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class AQLCompletionProcessor implements IContentAssistProcessor {

    /**
     * The {@link ILabelProvider}.
     */
    private ILabelProvider labelProvider;

    /**
     * The {@link IQueryCompletionEngine}.
     */
    private final IQueryCompletionEngine engine;

    /**
     * The set of defined variables types.
     */
    private final Map<String, Set<IType>> variableTypes;

    /**
     * Constructor.
     * 
     * @param labelProvider
     *            the {@link ILabelProvider}
     * @param queryEnvironment
     *            the {@link IReadOnlyQueryEnvironment}
     * @param variableTypes
     *            the set of defined variables types
     */
    public AQLCompletionProcessor(ILabelProvider labelProvider, IReadOnlyQueryEnvironment queryEnvironment,
            Map<String, Set<IType>> variableTypes) {
        this.labelProvider = labelProvider;
        engine = new QueryCompletionEngine(queryEnvironment);
        this.variableTypes = variableTypes;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeCompletionProposals(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    @Override
    public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
        final ICompletionResult completionResult = engine.getCompletion(viewer.getDocument().get(), offset,
                variableTypes);
        List<org.eclipse.acceleo.query.runtime.ICompletionProposal> proposals = completionResult
                .getProposals(new BasicFilter(completionResult));

        final ICompletionProposal[] result = new ICompletionProposal[proposals.size()];
        int i = 0;
        for (org.eclipse.acceleo.query.runtime.ICompletionProposal proposal : proposals) {
            result[i++] = new AQLCompletionProposal(labelProvider, completionResult, proposal);
        }

        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#computeContextInformation(org.eclipse.jface.text.ITextViewer,
     *      int)
     */
    @Override
    public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
        return new IContextInformation[] {};
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getCompletionProposalAutoActivationCharacters()
     */
    @Override
    public char[] getCompletionProposalAutoActivationCharacters() {
        return new char[] {'.', '>', ' ', };
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationAutoActivationCharacters()
     */
    @Override
    public char[] getContextInformationAutoActivationCharacters() {
        return new char[] {'.', '>', ' ', };
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getErrorMessage()
     */
    @Override
    public String getErrorMessage() {
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.text.contentassist.IContentAssistProcessor#getContextInformationValidator()
     */
    @Override
    public IContextInformationValidator getContextInformationValidator() {
        return new ContextInformationValidator(this);
    }

}
