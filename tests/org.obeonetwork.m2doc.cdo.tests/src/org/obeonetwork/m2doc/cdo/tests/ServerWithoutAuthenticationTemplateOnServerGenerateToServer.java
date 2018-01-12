/*******************************************************************************
 *  Copyright (c) 2017 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.cdo.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.cdo.util.CommitException;
import org.eclipse.emf.common.util.URI;
import org.obeonetwork.m2doc.parser.DocumentParserException;

/**
 * Tests with authenticated CDO server and template on server and generate to the server.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class ServerWithoutAuthenticationTemplateOnServerGenerateToServer
        extends ServerWithoutAuthenticationTemplateOnServer {

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws IOException
     *             if the tested template can't be read
     * @throws DocumentParserException
     *             if the tested template can't be parsed
     * @throws CommitException
     *             if something went wrong with CDO
     */
    public ServerWithoutAuthenticationTemplateOnServerGenerateToServer(String testFolder)
            throws IOException, DocumentParserException, CommitException {
        super(testFolder);
    }

    @Override
    protected URI getGenerationOutputURI(String testFolderPath) {
        final URI templateURI = getTemplateURI(new File(testFolderPath));

        return URI.createURI(templateURI.toString()
                .replaceAll("5ef25598-0af9-4436-b7df-5764732e4c0b/",
                        "5ef25598-0af9-4436-b7df-5764732e4c0b/" + testFolderPath)
                .replaceAll(".docx", "-generation-test.docx"), false);
    }

}
