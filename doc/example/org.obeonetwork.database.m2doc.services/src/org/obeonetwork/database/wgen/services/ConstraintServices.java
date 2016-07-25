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
import org.obeonetwork.dsl.database.Constraint;
import org.obeonetwork.dsl.database.Table;

//@formatter:off
@ServiceProvider(
value = "Services available for Constraints."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class ConstraintServices {

    // @formatter:off
    @Documentation(
        value = "Gets the expression of the Constraint.",
        params = {
            @Param(name = "constraint", value = "The Constraint"),
        },
        result = "the expression of the Constraint",
        examples = {
            @Example(expression = "constraint.expression()", result = "\"expression\"")
        }
    )
    // @formatter:on
    public String expression(Constraint constraint) {
        return constraint.getExpression();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the containing Table.",
        params = {
            @Param(name = "constraint", value = "The Constraint"),
        },
        result = "the the containing Table",
        examples = {
            @Example(expression = "constraint.owner()", result = "table")
        }
    )
    // @formatter:on
    public Table owner(Constraint constraint) {
        return constraint.getOwner();
    }

}
