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

import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 * Enum used to determine the kind of parsing token.
 * 
 * @author Romain Guider
 */
public enum ParsingTokenKind {
    /**
     * {@link XWPFRun} kind.
     */
    RUN,

    /**
     * {@link XWPFTable} kind.
     */
    TABLE
}
