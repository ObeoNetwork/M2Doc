/*******************************************************************************
 *  Copyright (c) 2017, 2025 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.sirius.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.acceleo.query.ide.QueryPlugin;
import org.eclipse.acceleo.query.sirius.AqlSiriusUtils;
import org.eclipse.emf.common.util.URI;
import org.junit.AfterClass;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.ide.M2DocPlugin;
import org.obeonetwork.m2doc.template.DocumentTemplate;

/**
 * Run a folder with templates as a test suite JUnit with Sirius support.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public abstract class AbstractTemplatesTestSuite extends org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite {

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder path
     * @throws Exception
     *             if something went wrong
     */
    public AbstractTemplatesTestSuite(String testFolder) throws Exception {
        super(testFolder);
        // make sure m2doc.ide is started
        M2DocPlugin.getPlugin();
        // make sure org.eclipse.acceleo.query.ide is started
        QueryPlugin.getPlugin();
    }

    /**
     * Unregisters.
     * 
     * @throws IOException
     *             if the {@link DocumentTemplate} can't be closed
     */
    @AfterClass
    public static void afterClass() throws IOException {
        org.obeonetwork.m2doc.tests.AbstractTemplatesTestSuite.afterClass();
    }

    @Override
    protected void setTemplateFileName(Generation gen, String templateFileName) {
        super.setTemplateFileName(gen, templateFileName);
        final Option option = GenconfUtils.getOrCreateOption(gen, AqlSiriusUtils.SIRIUS_SESSION_OPTION);
        option.setValue(getSessionURI(new File(getTestFolderPath())).deresolve(gen.eResource().getURI()).toString());
    }

    /**
     * Gets the session {@link URI} from the test folder path.
     * 
     * @param testFolder
     *            the test folder path
     * @return the session {@link URI} from the test folder path
     */
    protected URI getSessionURI(File testFolder) {
        return URI.createURI(testFolder.toURI().toString() + testFolder.getName() + ".aird", false);
    }

    @Override
    public void generation() throws Exception {
        super.generation();
    }

}
