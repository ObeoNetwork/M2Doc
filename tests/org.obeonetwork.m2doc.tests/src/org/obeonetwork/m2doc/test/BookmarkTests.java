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
package org.obeonetwork.m2doc.test;

import java.io.IOException;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.parser.DocumentParserException;

/**
 * Tests nominal features.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class BookmarkTests extends AbstractTemplatesTestSuite {

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws IOException
     *             if the tested template can't be read
     * @throws DocumentParserException
     *             if the tested template can't be parsed
     */
    public BookmarkTests(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    /**
     * Gets test folders from resources/feature.
     * 
     * @return test folders from resources/feature
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/bookmark");
    }

}
