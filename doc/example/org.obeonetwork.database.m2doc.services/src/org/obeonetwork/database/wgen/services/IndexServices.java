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

import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.Index;
import org.obeonetwork.dsl.database.IndexElement;
import org.obeonetwork.dsl.database.Table;

//@formatter:off
@ServiceProvider(
value = "Services available for Indexes"
)
//@formatter:on
public class IndexServices {

    // @formatter:off
    @Documentation(
        value = "Gets the qualifier of the Index.",
        params = {
            @Param(name = "index", value = "The Index"),
        },
        result = "the qualifier of the Index",
        examples = {
            @Example(expression = "index.qualifier()", result = "table1")
        }
    )
    // @formatter:on
    public String qualifier(Index index) {
        return index.getQualifier();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Index of the Index.",
        params = {
            @Param(name = "index", value = "The Index"),
        },
        result = "the List of Index of the Index",
        examples = {
            @Example(expression = "index.elements()", result = "{element1, element2}")
        }
    )
    // @formatter:on
    public List<IndexElement> elements(Index index) {
        return index.getElements();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the Index is unique.",
        params = {
            @Param(name = "index", value = "The Index"),
        },
        result = "true if the Index is unique, false otherwise",
        examples = {
            @Example(expression = "index.isUnique()", result = "true")
        }
    )
    // @formatter:on
    public boolean isUnique(Index index) {
        return index.isUnique();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the cardinality of the Index.",
        params = {
            @Param(name = "index", value = "The Index"),
        },
        result = "the cardinality of the Index",
        examples = {
            @Example(expression = "index.cardinality()", result = "1")
        }
    )
    // @formatter:on
    public int cardinality(Index index) {
        return index.getCardinality();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the type of the Index.",
        params = {
            @Param(name = "index", value = "The Index"),
        },
        result = "the type of the Index",
        examples = {
            @Example(expression = "index.indexType()", result = "\"type\"")
        }
    )
    // @formatter:on
    public String indexType(Index index) {
        return index.getIndexType();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the containing Table of the Index.",
        params = {
            @Param(name = "index", value = "The Index"),
        },
        result = "the containing Table of the Index",
        examples = {
            @Example(expression = "index.owner()", result = "table1")
        }
    )
    // @formatter:on
    public Table owner(Index index) {
        return index.getOwner();
    }

}
