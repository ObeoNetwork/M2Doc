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

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.NamedElement;

//@formatter:off
@ServiceProvider(
value = "Services available for NamedElements"
)
//@formatter:on
public class NamedElementServices {

    // @formatter:off
    @Documentation(
        value = "Gets the name of the NamedElement.",
        params = {
            @Param(name = "element", value = "The NamedElement"),
        },
        result = "the name of the NamedElement",
        examples = {
            @Example(expression = "element.name()", result = "\"element1\"")
        }
    )
    // @formatter:on
    public String name(NamedElement element) {
        return element.getName();
    }

}
