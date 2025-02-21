/*******************************************************************************
 *  Copyright (c) 2016, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.parser;

import java.util.StringJoiner;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 * Token used to direct parsing of gendoc templates.
 * 
 * @author Romain Guider
 */
public class ParsingToken {

    /**
     * The start field token {@link ParsingToken}.
     */
    public static final ParsingToken START_FIELD_TOKEN = new ParsingToken(ParsingTokenKind.FIELD_START);

    /**
     * The end field token {@link ParsingToken}.
     */
    public static final ParsingToken END_FIELD_TOKEN = new ParsingToken(ParsingTokenKind.FIELD_END);

    /**
     * The missing end field token {@link ParsingToken}.
     */
    public static final ParsingToken MISSING_END_FIELD_TOKEN = new ParsingToken(ParsingTokenKind.MISSING_FIELD_END);

    /**
     * The underlying body element.
     */
    private final IBodyElement bodyElement;
    /**
     * The run the token is contained in.
     */
    private final XWPFRun run;

    /**
     * The {@link ParsingTokenKind}.
     */
    private final ParsingTokenKind kind;

    /**
     * Creates a new parsing token that corresponds to a {@link XWPFRun}.
     * 
     * @param run
     *            the {@link XWPFRun}
     */
    public ParsingToken(XWPFRun run) {
        this(run, null, ParsingTokenKind.RUN);
    }

    /**
     * Creates a new parsing token that corresponds to a {@link IBodyElement}.
     * 
     * @param bodyElement
     *            the {@link IBodyElement}
     */
    public ParsingToken(IBodyElement bodyElement) {
        this(null, bodyElement, ParsingTokenKind.getParsingTokenKind(bodyElement.getElementType()));
    }

    /**
     * Constructor.
     * 
     * @param kind
     *            the {@link ParsingTokenKind}
     */
    private ParsingToken(ParsingTokenKind kind) {
        this(null, null, kind);
    }

    /**
     * Constructor.
     * 
     * @param run
     *            the {@link XWPFRun}
     * @param bodyElement
     *            the {@link IBodyElement}
     * @param kind
     *            the {@link ParsingTokenKind}
     */
    private ParsingToken(XWPFRun run, IBodyElement bodyElement, ParsingTokenKind kind) {
        this.bodyElement = bodyElement;
        this.run = run;
        this.kind = kind;
    }

    /**
     * Gets the {@link XWPFRun}.
     * 
     * @return the {@link XWPFRun} if the {@link #getKind() kind} is {@link ParsingTokenKind#RUN}, <code>null</code> otherwise
     */
    public XWPFRun getRun() {
        return run;
    }

    /**
     * Gets the {@link IBodyElement}.
     * 
     * @return the {@link IBodyElement} if the {@link #getKind() kind} is not {@link ParsingTokenKind#RUN}, <code>null</code> otherwise
     */
    public IBodyElement getBodyElement() {
        return bodyElement;
    }

    /**
     * Gets the {@link ParsingTokenKind}.
     * 
     * @return the {@link ParsingTokenKind}
     */
    public ParsingTokenKind getKind() {
        return kind;
    }

    @Override
    public String toString() {
        final String result;

        if (getKind() == ParsingTokenKind.RUN) {
            final StringJoiner joiner = new StringJoiner(", ", getKind().toString() + ": [", "]");

            for (CTText text : getRun().getCTR().getTList()) {
                joiner.add(text.getStringValue());
            }

            result = joiner.toString();
        } else {
            result = getKind().toString();
        }

        return result;
    }
}
