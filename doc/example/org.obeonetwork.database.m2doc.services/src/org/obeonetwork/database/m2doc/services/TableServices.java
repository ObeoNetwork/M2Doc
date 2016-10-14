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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.Constraint;
import org.obeonetwork.dsl.database.ForeignKey;
import org.obeonetwork.dsl.database.Index;
import org.obeonetwork.dsl.database.PrimaryKey;
import org.obeonetwork.dsl.database.Table;

//@formatter:off
@ServiceProvider(
value = "Services available for Tables"
)
//@formatter:on
public class TableServices {

    // @formatter:off
    @Documentation(
        value = "Gets the PrimaryKey of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the PrimaryKey of the Table",
        examples = {
            @Example(expression = "table.primaryKey()", result = "pk1")
        }
    )
    // @formatter:on
    public PrimaryKey primaryKey(Table table) {
        return table.getPrimaryKey();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the PrimaryKey name of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the PrimaryKey name of the Table. The empty string if there's no primary key.",
        examples = {
            @Example(expression = "table.primaryKey()", result = "\"pk1\"")
        }
    )
    // @formatter:on
    public String primaryKeyName(Table table) {
        PrimaryKey key = table.getPrimaryKey();
        String result = null;
        if (key != null) {
            result = key.getName();
        }
        return result == null ? "" : result;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of ForeignKey of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the List of ForeignKey of the Table",
        examples = {
            @Example(expression = "table.primaryKey()", result = "{fk1, fk2}")
        }
    )
    // @formatter:on
    public List<ForeignKey> foreignKeys(Table table) {
        return table.getForeignKeys();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of ForeignKey name of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the List of ForeignKey name of the Table",
        examples = {
            @Example(expression = "table.primaryKey()", result = "{\"fk1\", \"fk2\"}")
        }
    )
    // @formatter:on
    public List<String> foreignKeyNames(Table table) {
        final List<String> res = new ArrayList<String>();

        for (ForeignKey key : table.getForeignKeys()) {
            res.add(key.getName());
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Constraint of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the List of Constraint of the Table",
        examples = {
            @Example(expression = "table.constraints()", result = "{constraint1, constraint2}")
        }
    )
    // @formatter:on
    public List<Constraint> constraints(Table table) {
        return table.getConstraints();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Index of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the List of Index of the Table",
        examples = {
            @Example(expression = "table.indexes()", result = "{index1, index2}")
        }
    )
    // @formatter:on
    public List<Index> indexes(Table table) {
        return table.getIndexes();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Column that are not primary key or foreign key of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the List of Column that are not primary key or foreign key of the Table",
        examples = {
            @Example(expression = "table.columns()", result = "{column1, column2}")
        }
    )
    // @formatter:on
    public List<Column> columns(Table table) {
        final List<Column> res = new ArrayList<Column>();

        for (Column column : table.getColumns()) {
            if (!column.isInForeignKey() && !column.isInPrimaryKey()) {
                res.add(column);
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
            value = "Gets the List of all columns of the specified table.",
            params = {
                @Param(name = "table", value = "The Table"),
            },
            result = "the List of columns of the specified table",
            examples = {
                @Example(expression = "table.columns()", result = "{\"column1\", \"column2\"}")
            }
        )
    // @formatter:on
    public List<Column> allColumns(Table table) {
        return table.getColumns();
    }

    // @formatter:off
    @Documentation(
            value = "Gets the List of names of all columns of the specified table.",
            params = {
                @Param(name = "table", value = "The Table"),
            },
            result = "the List of names of all columns of the specified table",
            examples = {
                @Example(expression = "table.columns()", result = "{\"column1\", \"column2\"}")
            }
        )
    // @formatter:on
    public List<String> allColumnNames(Table table) {
        List<String> result = new ArrayList<String>();
        for (Column column : table.getColumns()) {
            result.add(column.getName());
        }
        return result;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Column name that are not primary key or foreign key of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the List of Column name that are not primary key or foreign key of the Table",
        examples = {
            @Example(expression = "table.columns()", result = "{\"column1\", \"column2\"}")
        }
    )
    // @formatter:on
    public List<String> columnNames(Table table) {
        final List<String> res = new ArrayList<String>();
        for (Column column : table.getColumns()) {
            if (!column.isInForeignKey() && !column.isInPrimaryKey()) {
                res.add(column.getName());
            }
        }

        return res;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the record number of the Table.",
        params = {
            @Param(name = "table", value = "The Table"),
        },
        result = "the record number of the Table",
        examples = {
            @Example(expression = "table.recordNumber()", result = "10000")
        }
    )
    // @formatter:on
    public int recordNumber(Table table) {
        return 10000;
    }

}
