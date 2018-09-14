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
package org.obeonetwork.m2doc.services;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;

//@formatter:off
@ServiceProvider(
  value = "Services available for Booleans. See [document examples](https://github.com/ObeoNetwork/M2Doc/tree/master/tests/org.obeonetwork.m2doc.tests/resources/booleanServices)."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class BooleanServices {

    // @formatter:off
    @Documentation(
        value = "Returns \"X\" for boolean true.",
        params = {
            @Param(name = "value", value = "The boolean value"),
        },
        result = "\"X\" when the boolean value is true, \"\" otherwise.",
        examples = {
            @Example(expression = "true.check()", result = "X"),
            @Example(expression = "false.check()", result = ""),
        }
    )
    // @formatter:on
    public String check(boolean value) {
        final String res;

        if (value) {
            res = "X";
        } else {
            res = "";
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Returns \"Yes\" for boolean true, \"No\" otherwise.",
        params = {
            @Param(name = "value", value = "The boolean value"),
        },
        result = "\"Yes\" when the boolean value is true, \"No\" otherwise.",
        examples = {
            @Example(expression = "true.yesNo()", result = "Yes"),
            @Example(expression = "false.yesNo()", result = "No"),
        }
    )
    // @formatter:on
    public String yesNo(boolean value) {
        final String res;

        if (value) {
            res = "Yes";
        } else {
            res = "No";
        }

        return res;
    }

}
