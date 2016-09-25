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
import org.obeonetwork.dsl.database.View;

//@formatter:off
@ServiceProvider(
value = "Services available for Views"
)
//@formatter:on
public class ViewServices {

    // @formatter:off
    @Documentation(
        value = "Gets the query of the View.",
        params = {
            @Param(name = "view", value = "The View"),
        },
        result = "the query of the View",
        examples = {
            @Example(expression = "view.query()", result = "\"query1\"")
        }
    )
    // @formatter:on
    public String query(View view) {
        return view.getQuery();
    }

}
