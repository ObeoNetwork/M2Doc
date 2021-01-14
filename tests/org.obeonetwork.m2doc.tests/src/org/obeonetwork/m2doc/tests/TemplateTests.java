/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.tests;

import java.io.IOException;
import java.util.Collection;

import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.parser.DocumentParserException;

/**
 * Tests {@link org.obeonetwork.m2doc.template.Comment Comment}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class TemplateTests extends AbstractTemplatesTestSuite {

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
    public TemplateTests(String testFolder) throws IOException, DocumentParserException {
        super(testFolder);
    }

    /**
     * Gets test folders from resources/feature.
     * 
     * @return test folders from resources/feature
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/template");
    }

}
