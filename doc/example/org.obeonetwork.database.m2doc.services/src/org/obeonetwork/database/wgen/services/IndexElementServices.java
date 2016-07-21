/*******************************************************************************
 *  Copyright (c) 2016 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *   
 *   Contributors:
 *       Obeo - initial API and implementation
 *  
 *******************************************************************************/
package org.obeonetwork.database.wgen.services;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.IndexElement;

//@formatter:off
@ServiceProvider(
value = "Services available for IndexElements"
)
//@formatter:on
public class IndexElementServices {

    // @formatter:off
    @Documentation(
        value = "Gets the Column of the IndexElement.",
        params = {
            @Param(name = "element", value = "The IndexElement"),
        },
        result = "the Column of the IndexElement",
        examples = {
            @Example(expression = "element.sourceTable()", result = "table1")
        }
    )
    // @formatter:on
    public Column column(IndexElement element) {
        return element.getColumn();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the IndexElement is sorted in ascendant order.",
        params = {
            @Param(name = "element", value = "The IndexElement"),
        },
        result = "true if the IndexElement is sorted in ascendant order, false otherwise",
        examples = {
            @Example(expression = "element.ascendant()", result = "true")
        }
    )
    // @formatter:on
    public boolean ascendant(IndexElement element) {
        return element.isAsc();
    }

}
