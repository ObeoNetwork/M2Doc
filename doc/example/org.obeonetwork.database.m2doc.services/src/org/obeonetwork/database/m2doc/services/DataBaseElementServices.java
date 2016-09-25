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
import org.obeonetwork.dsl.database.DatabaseElement;

//@formatter:off
@ServiceProvider(
value = "Services available for DataBaseElements."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class DataBaseElementServices {

    // @formatter:off
    @Documentation(
        value = "Gets the id of the DataBaseElement.",
        params = {
            @Param(name = "databaseElement", value = "The DataBaseElement"),
        },
        result = "the id of the DataBaseElement",
        examples = {
            @Example(expression = "databaseElement.id()", result = "\"ID1\"")
        }
    )
    // @formatter:on
    public String id(DatabaseElement databaseElement) {
        return databaseElement.getID();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the comment of the DataBaseElement.",
        params = {
            @Param(name = "databaseElement", value = "The DataBaseElement"),
        },
        result = "the comment of the DataBaseElement",
        examples = {
            @Example(expression = "databaseElement.comments()", result = "\"this element is used for ...\"")
        }
    )
    // @formatter:on
    public String comments(DatabaseElement databaseElement) {
        return databaseElement.getComments();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the technical id of the DataBaseElement.",
        params = {
            @Param(name = "databaseElement", value = "The DataBaseElement"),
        },
        result = "the technical id of the DataBaseElement",
        examples = {
            @Example(expression = "databaseElement.technicalID()", result = "\"TID1\"")
        }
    )
    // @formatter:on
    public String technicalID(DatabaseElement databaseElement) {
        return databaseElement.getTechID();
    }

}
