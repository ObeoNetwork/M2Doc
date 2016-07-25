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
import org.obeonetwork.dsl.database.ForeignKeyElement;

//@formatter:off
@ServiceProvider(
  value = "Services available for ForeignKeyElements"
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class ForeignKeyElementServices {

    // @formatter:off
    @Documentation(
        value = "Gets the foreign key Column of the ForeignKeyElement.",
        params = {
            @Param(name = "element", value = "The ForeignKeyElement"),
        },
        result = "the foreign key of the ForeignKeyElement",
        examples = {
            @Example(expression = "element.foreignKeyColumn()", result = "column1")
        }
    )
    // @formatter:on
    public Column foreignKeyColumn(ForeignKeyElement element) {
        return element.getFkColumn();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the primary key Column of the ForeignKeyElement.",
        params = {
            @Param(name = "element", value = "The ForeignKeyElement"),
        },
        result = "the primary key of the ForeignKeyElement",
        examples = {
            @Example(expression = "element.primaryKeyColumn()", result = "column1")
        }
    )
    // @formatter:on
    public Column primaryKeyColumn(ForeignKeyElement element) {
        return element.getPkColumn();
    }

}
