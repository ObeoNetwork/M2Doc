/*******************************************************************************
 *  Copyright (c) 2017, 2024 Obeo. 
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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.obeonetwork.m2doc.sirius.tests.tables.DMStyleTest;
import org.obeonetwork.m2doc.sirius.tests.tables.DMTableTest;

/**
 * Aggregates tests for the org.obeonetwork.m2doc.sirius plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(value = {M2DocSiriusServicesTests.class, M2DocSiriusServicesWithForceRefreshTests.class,
    M2DocSiriusServicesWithScaleLevel1Tests.class, M2DocSiriusServicesWithScaleLevel50Tests.class, DMStyleTest.class,
    DMTableTest.class, })

public class AllTests {

}
