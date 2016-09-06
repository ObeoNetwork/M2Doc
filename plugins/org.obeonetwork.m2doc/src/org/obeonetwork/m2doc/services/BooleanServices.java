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

//@formatter:off
//@ServiceProvider(
//  value = "Services available for Booleans"
//)
//@formatter:on
// TODO activate documentation when changing AQL dependency
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class BooleanServices {

    // @formatter:off
//    @Documentation(
//        value = "Returns \"X\" for boolean true.",
//        params = {
//            @Param(name = "value", value = "The boolean value"),
//        },
//        result = "\"X\" when the boolean value is true, \"\" otherwise.",
//        examples = {
//            @Example(expression = "true.check()", result = "X"),
//            @Example(expression = "false.check()", result = ""),
//        }
//    )
    // @formatter:on
    // TODO activate documentation when changing AQL dependency
    /**
     * return X if true else empty.
     * 
     * @param value
     *            boolean
     * @return X if true else empty.
     */
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
//    @Documentation(
//        value = "Returns \"Yes\" for boolean true, \"No\" otherwise.",
//        params = {
//            @Param(name = "value", value = "The boolean value"),
//        },
//        result = "\"Yes\" when the boolean value is true, \"No\" otherwise.",
//        examples = {
//            @Example(expression = "true.yesNo()", result = "Yes"),
//            @Example(expression = "false.yesNo()", result = "No"),
//        }
//    )
    // @formatter:on
    // TODO activate documentation when changing AQL dependency
    /**
     * return yes if true else no.
     * 
     * @param value
     *            boolean
     * @return yes if true else no.
     */
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
