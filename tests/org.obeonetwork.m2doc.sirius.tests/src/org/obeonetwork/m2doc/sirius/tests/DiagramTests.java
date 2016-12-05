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
package org.obeonetwork.m2doc.sirius.tests;

import java.io.IOException;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.parser.DocumentParserException;
import org.obeonetwork.m2doc.template.Bookmark;
import org.obeonetwork.m2doc.template.Link;

/**
 * Tests {@link Bookmark} and {@link Link}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class DiagramTests extends AbstractTemplatesTestSuite {

    public DiagramTests(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/diagram");
    }

}
