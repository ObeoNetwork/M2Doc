/*******************************************************************************
 *  Copyright (c) 2018, 2021 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.m2doc.html.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.obeonetwork.m2doc.html.tests.services.CSSTests;
import org.obeonetwork.m2doc.html.tests.services.HTMLTests;
import org.obeonetwork.m2doc.html.tests.services.M2DocHTMLServicesTests;

/**
 * Aggregates tests for the org.obeonetwork.m2doc.html plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(value = {M2DocHTMLServicesTests.class, HTMLTests.class, CSSTests.class, })
public class AllTests {

}
