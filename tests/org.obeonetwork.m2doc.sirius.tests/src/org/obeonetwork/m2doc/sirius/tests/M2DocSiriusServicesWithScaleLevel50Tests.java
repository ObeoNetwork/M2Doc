/*******************************************************************************
 *  Copyright (c) 2024, 2025 Obeo. 
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

import java.util.Collection;

import org.eclipse.sirius.ui.business.api.dialect.ExportFormat.ScalingPolicy;
import org.junit.runners.Parameterized.Parameters;
import org.obeonetwork.m2doc.genconf.GenconfUtils;
import org.obeonetwork.m2doc.genconf.Generation;
import org.obeonetwork.m2doc.genconf.Option;
import org.obeonetwork.m2doc.sirius.M2DocSiriusUtils;
import org.obeonetwork.m2doc.sirius.services.M2DocSiriusServices;

/**
 * Tests {@link M2DocSiriusServices}.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
public class M2DocSiriusServicesWithScaleLevel50Tests extends AbstractTemplatesTestSuite {

    /**
     * Constructor.
     * 
     * @param testFolder
     *            the test folder path
     * @throws Exception
     *             if something went wrong
     */
    public M2DocSiriusServicesWithScaleLevel50Tests(String testFolder) throws Exception {
        super(testFolder);
    }

    @Override
    protected void setTemplateFileName(Generation gen, String templateFileName) {
        super.setTemplateFileName(gen, templateFileName);
        final Option scalingPolicyOption = GenconfUtils.getOrCreateOption(gen, M2DocSiriusUtils.SIRIUS_SCALING_POLICY);
        scalingPolicyOption.setValue(ScalingPolicy.AUTO_SCALING.name());
        final Option scaleLevelOption = GenconfUtils.getOrCreateOption(gen, M2DocSiriusUtils.SIRIUS_SCALE_LEVEL);
        scaleLevelOption.setValue("50");
    }

    /**
     * Gets the {@link Collection} of test folders.
     * 
     * @return the {@link Collection} of test folders
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> retrieveTestFolders() {
        return retrieveTestFolders("resources/asImageWithSiriusScaleLevel50");
    }

}
