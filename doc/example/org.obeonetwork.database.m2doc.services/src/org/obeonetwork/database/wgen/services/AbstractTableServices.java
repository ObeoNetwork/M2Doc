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
import org.obeonetwork.dsl.database.AbstractTable;
import org.obeonetwork.dsl.database.TableContainer;

//@formatter:off
@ServiceProvider(
  value = "Services available for AbstractTables."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class AbstractTableServices {

    // @formatter:off
    @Documentation(
        value = "Gets the containing TableContainer.",
        params = {
            @Param(name = "abstractTable", value = "The AbstractTable"),
        },
        result = "the containing TableContainer",
        examples = {
            @Example(expression = "abstractTable.owner()", result = "tableContainer")
        }
    )
    // @formatter:on
    public TableContainer owner(AbstractTable abstractTable) {
        return abstractTable.getOwner();
    }

}
