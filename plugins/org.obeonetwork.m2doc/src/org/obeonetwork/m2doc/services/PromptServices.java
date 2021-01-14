/*******************************************************************************
 *  Copyright (c) 2019 Obeo. 
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v2.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v20.html
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

/**
 * Services prompting for value.
 * 
 * @author <a href="mailto:yvan.lussaud@obeo.fr">Yvan Lussaud</a>
 */
//@formatter:off
@ServiceProvider(
value = "Services available for prompting user for values using the console."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype" })
public class PromptServices {

    // @formatter:off
    @Documentation(
        value = "Prompts for a String value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as a String.",
        examples = {
            @Example(expression = "'Enter your name: '.promptString()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public String promptString(String message) {
        return System.console().readLine(message);
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Integer value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as an Integer.",
        examples = {
            @Example(expression = "'Enter your age: '.promptInteger()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Integer promptInteger(String message) {
        return Integer.valueOf(promptString(message));
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Long value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as an Long.",
        examples = {
            @Example(expression = "'Enter your age: '.promptLong()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Long promptLong(String message) {
        return Long.valueOf(promptString(message));
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Float value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as a Float.",
        examples = {
            @Example(expression = "'Enter your weight: '.promptFloat()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Float promptFloat(String message) {
        return Float.valueOf(promptString(message));
    }

    // @formatter:off
    @Documentation(
        value = "Prompts for a Double value with the given message.",
        params = {
            @Param(name = "message", value = "The message displayed to the user"),
        },
        result = "Prompts the user and return the input of the user as a Double.",
        examples = {
            @Example(expression = "'Enter your weight: '.promptDouble()", result = "prompts the user"),
        }
    )
    // @formatter:on
    public Double promptDouble(String message) {
        return Double.valueOf(promptString(message));
    }

}
