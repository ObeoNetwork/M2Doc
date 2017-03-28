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
import org.obeonetwork.m2doc.tests.provider.ProviderTests;
import org.obeonetwork.m2doc.tests.services.ServicesTests;
import org.obeonetwork.m2doc.tests.userdoc.UserdocTests;
import org.obeonetwork.m2doc.tests.util.UtilsTests;

/**
 * Aggregates tests for the org.obeonetwork.m2doc plug-in.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
@RunWith(Suite.class)
@SuiteClasses(value = {GeneratorTests.class, ParserTests.class, PropertiesTests.class, ProviderTests.class,
    ServicesTests.class, UserdocTests.class, UtilsTests.class, BookmarkTests.class, CommentTests.class,
    ConditionalTests.class, DiagramTests.class, ImageTests.class, LetTests.class, QueryTests.class,
    RepetitionTests.class, StaticTests.class, UserDocTests.class, })
public class AllTests {

}
