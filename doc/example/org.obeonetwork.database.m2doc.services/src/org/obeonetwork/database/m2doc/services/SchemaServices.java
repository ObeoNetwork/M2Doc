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

import java.util.Set;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.obeonetwork.dsl.database.Table;

//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class SchemaServices {

    // @formatter:off
    @Documentation(
        value = "Gets the url of the DataBase.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the id of the DataBase",
        examples = {
            @Example(expression = "dataBase.url()", result = "\"url\"")
        }
    )
    // @formatter:on
    public Set<Table> tables() {
        throw new UnsupportedOperationException("unimplemented");
    }

}
