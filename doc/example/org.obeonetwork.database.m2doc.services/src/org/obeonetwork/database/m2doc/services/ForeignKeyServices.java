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
package org.obeonetwork.database.m2doc.services;

import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.ForeignKey;
import org.obeonetwork.dsl.database.ForeignKeyElement;
import org.obeonetwork.dsl.database.Table;

//@formatter:off
@ServiceProvider(
value = "Services available for ForeignKeys"
)
//@formatter:on
public class ForeignKeyServices {

    // @formatter:off
    @Documentation(
        value = "Gets the source Table of the ForeignKey.",
        params = {
            @Param(name = "foreignKey", value = "The ForeignKey"),
        },
        result = "the source Table of the ForeignKey",
        examples = {
            @Example(expression = "foreignKey.sourceTable()", result = "table1")
        }
    )
    // @formatter:on
    public Table sourceTable(ForeignKey foreignKey) {
        return foreignKey.getSourceTable();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the target Table of the ForeignKey.",
        params = {
            @Param(name = "foreignKey", value = "The ForeignKey"),
        },
        result = "the target Table of the ForeignKey",
        examples = {
            @Example(expression = "foreignKey.targetTable()", result = "table1")
        }
    )
    // @formatter:on
    public Table targetTable(ForeignKey foreignKey) {
        return foreignKey.getTarget();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of ForeignKeyElement of the ForeignKey.",
        params = {
            @Param(name = "foreignKey", value = "The ForeignKey"),
        },
        result = "the List of ForeignKeyElement of the ForeignKey",
        examples = {
            @Example(expression = "foreignKey.foreignKeys()", result = "{fkElement1, fkElement2}")
        }
    )
    // @formatter:on
    public List<ForeignKeyElement> foreignKeys(ForeignKey foreignKey) {
        return foreignKey.getElements();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the containing Table of the ForeignKey.",
        params = {
            @Param(name = "foreignKey", value = "The ForeignKey"),
        },
        result = "the containing Table of the ForeignKey",
        examples = {
            @Example(expression = "foreignKey.owner()", result = "table1")
        }
    )
    // @formatter:on
    public Table owner(ForeignKey foreignKey) {
        return foreignKey.getOwner();
    }

}
