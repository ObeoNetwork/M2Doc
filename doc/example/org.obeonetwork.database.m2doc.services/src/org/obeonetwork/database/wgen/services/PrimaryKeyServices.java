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

import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.PrimaryKey;
import org.obeonetwork.dsl.database.Table;

//@formatter:off
@ServiceProvider(
value = "Services available for PrimaryKeys"
)
//@formatter:on
public class PrimaryKeyServices {

    // @formatter:off
    @Documentation(
        value = "Gets the List of Column of the PrimaryKey.",
        params = {
            @Param(name = "key", value = "The PrimaryKey"),
        },
        result = "the List of Column of the PrimaryKey",
        examples = {
            @Example(expression = "key.columns()", result = "{column1, column2}")
        }
    )
    // @formatter:on
    public List<Column> columns(PrimaryKey key) {
        return key.getColumns();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Table of the PrimaryKey.",
        params = {
            @Param(name = "key", value = "The PrimaryKey"),
        },
        result = "the Table of the PrimaryKey",
        examples = {
            @Example(expression = "key.table()", result = "table1")
        }
    )
    // @formatter:on
    public Table table(PrimaryKey key) {
        return key.getOwner();
    }

}
