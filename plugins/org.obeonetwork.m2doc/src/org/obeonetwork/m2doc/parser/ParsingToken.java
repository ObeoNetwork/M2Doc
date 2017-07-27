/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.parser;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * Token used to direct parsing of gendoc templates.
 * 
 * @author Romain Guider
 */
public class ParsingToken {
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
        bodyElement = null;
        this.run = run;
        kind = ParsingTokenKind.RUN;
    }

    /**
     * Creates a new parsing token that corresponds to a {@link IBodyElement}.
     * 
     * @param bodyElement
     *            the {@link IBodyElement}
     */
    public ParsingToken(IBodyElement bodyElement) {
        this.bodyElement = bodyElement;
        run = null;
        kind = ParsingTokenKind.getParsingTokenKind(bodyElement.getElementType());
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
    ParsingTokenKind getKind() {
        return kind;
    }
}
