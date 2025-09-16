/*******************************************************************************
 *  Copyright (c) 2020, 2025 Obeo. 
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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.runners.Parameterized.Parameters;
import org.xtext.example.mydsl.MyDslStandaloneSetup;
import org.xtext.example.mydsl.myDsl.MyDslPackage;

/**
 * Tests {@link org.obeonetwork.m2doc.template.Query Query}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class XTextTests extends AbstractTemplatesTestSuite {

    {
        MyDslPackage.eINSTANCE.getName();// make sure MyDsl is loaded (XText)
    }

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder
     * @throws Exception
     *             if something went wrong
     */
    public XTextTests(String testFolder) throws Exception {
        super(testFolder);
    }

    @Override
    protected ResourceSet getResourceSetForModel(List<Exception> exceptions) {
        new MyDslStandaloneSetup().createInjectorAndDoEMFRegistration();
        return super.getResourceSetForModel(exceptions);
    }

    /**
     * Gets test folders from resources/feature.
     * 
     * @return test folders from resources/feature
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/xtext");
    }

}
