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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.AbstractTable;
import org.obeonetwork.dsl.database.Sequence;
import org.obeonetwork.dsl.database.Table;
import org.obeonetwork.dsl.database.TableContainer;
import org.obeonetwork.dsl.database.View;

//@formatter:off
@ServiceProvider(
value = "Services available for TableContainers"
)
//@formatter:on
public class TableContainerServices {

    // @formatter:off
    @Documentation(
        value = "Gets the List of Table of the NamedElement.",
        params = {
            @Param(name = "container", value = "The NamedElement"),
        },
        result = "the List of Table of the NamedElement",
        examples = {
            @Example(expression = "container.tables()", result = "{table1, table2}")
        }
    )
    // @formatter:on
    public List<Table> tables(TableContainer container) {
        final List<Table> res = new ArrayList<Table>();

        for (AbstractTable abstractTable : container.getTables()) {
            if (abstractTable instanceof Table) {
                res.add((Table) abstractTable);
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of View of the NamedElement.",
        params = {
            @Param(name = "container", value = "The NamedElement"),
        },
        result = "the List of View of the NamedElement",
        examples = {
            @Example(expression = "container.views()", result = "{view1, view2}")
        }
    )
    // @formatter:on
    public List<View> views(TableContainer container) {
        final List<View> res = new ArrayList<View>();

        for (AbstractTable abstractTable : container.getTables()) {
            if (abstractTable instanceof View) {
                res.add((View) abstractTable);
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Sequence of the NamedElement.",
        params = {
            @Param(name = "container", value = "The NamedElement"),
        },
        result = "the List of Sequence of the NamedElement",
        examples = {
            @Example(expression = "container.sequences()", result = "{sequence1, sequence2}")
        }
    )
    // @formatter:on
    public List<Sequence> sequences(TableContainer container) {
        return container.getSequences();
    }

}
