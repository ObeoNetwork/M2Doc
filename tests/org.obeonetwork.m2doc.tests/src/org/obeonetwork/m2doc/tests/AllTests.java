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
package org.obeonetwork.m2doc.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.obeonetwork.m2doc.tests.generator.GeneratorTests;
import org.obeonetwork.m2doc.tests.parser.ParserTests;
import org.obeonetwork.m2doc.tests.properties.PropertiesTests;
import org.obeonetwork.m2doc.tests.services.ServicesTests;
import org.obeonetwork.m2doc.tests.userdoc.UserdocTests;

/**
 * Aggregates tests for the org.obeonetwork.m2doc plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(
    value = {GeneratorTests.class, ParserTests.class, PropertiesTests.class, ServicesTests.class, UserdocTests.class,
        BookmarkTests.class, CommentTests.class, ConditionalTests.class, LetTests.class, QueryTests.class,
        RepetitionTests.class, StaticTests.class, UserDocTests.class, M2DocUtilsTests.class, MTableTests.class, })
public class AllTests {

}
