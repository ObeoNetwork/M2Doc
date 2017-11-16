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
@SuiteClasses(value = {M2DocSiriusServicesTests.class, DMStyleTest.class, DMTableTest.class, })
public class AllTests {

}
