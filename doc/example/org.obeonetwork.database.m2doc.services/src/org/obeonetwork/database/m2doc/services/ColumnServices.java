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

import java.util.List;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.AbstractTable;
import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.ForeignKey;
import org.obeonetwork.dsl.database.ForeignKeyElement;
import org.obeonetwork.dsl.database.Index;
import org.obeonetwork.dsl.database.IndexElement;
import org.obeonetwork.dsl.database.PrimaryKey;
import org.obeonetwork.dsl.database.Sequence;
import org.obeonetwork.dsl.typeslibrary.NativeType;
import org.obeonetwork.dsl.typeslibrary.Type;
import org.obeonetwork.dsl.typeslibrary.TypeInstance;
import org.obeonetwork.dsl.typeslibrary.UserDefinedTypeRef;

//@formatter:off
@ServiceProvider(
value = "Services available for Columns."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class ColumnServices {

    /**
     * The number of records.
     */
    private static final int NUMBER_OF_RECORD = 5000;

    // @formatter:off
    @Documentation(
        value = "Tells if the Columns can contain null values.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "true if the Columns can contain null values, false otherwise",
        examples = {
            @Example(expression = "column.isNullable()", result = "true")
        }
    )
    // @formatter:on
    public boolean isNullable(Column column) {
        return column.isNullable();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Column default value.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the Column default value",
        examples = {
            @Example(expression = "column.defaultValue()", result = "\"default\"")
        }
    )
    // @formatter:on
    public String defaultValue(Column column) {
        return column.getDefaultValue();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Index for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the List of Index for the given Column",
        examples = {
            @Example(expression = "column.indexes()", result = "{index1, index2}")
        }
    )
    // @formatter:on
    public List<Index> indexes(Column column) {
        return column.getIndexes();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of IndexEmement for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the List of IndexEmement for the given Column",
        examples = {
            @Example(expression = "column.indexElements()", result = "{indexElement1, indexElement2}")
        }
    )
    // @formatter:on
    public List<IndexElement> indexElements(Column column) {
        return column.getIndexElements();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the PrimaryKey for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the PrimaryKey for the given Column",
        examples = {
            @Example(expression = "column.primaryKey()", result = "pk")
        }
    )
    // @formatter:on
    public PrimaryKey primaryKey(Column column) {
        return column.getPrimaryKey();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of ForeinKey for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the List of ForeinKey for the given Column",
        examples = {
            @Example(expression = "column.foreignKeys()", result = "{fk1, fk2}")
        }
    )
    // @formatter:on
    public List<ForeignKey> foreignKeys(Column column) {
        return column.getForeignKeys();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of ForeinKeyElement for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the List of ForeinKeyElement for the given Column",
        examples = {
            @Example(expression = "column.foreignKeyElements()", result = "{fke1, fke2}")
        }
    )
    // @formatter:on
    public List<ForeignKeyElement> foreignKeyElements(Column column) {
        return column.getForeignKeyElements();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Type for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the Type for the given Column",
        examples = {
            @Example(expression = "column.type()", result = "type")
        }
    )
    // @formatter:on
    public Type type(Column column) {
        return column.getType();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Sequence for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the Sequence for the given Column",
        examples = {
            @Example(expression = "column.sequence()", result = "sequence")
        }
    )
    // @formatter:on
    public Sequence sequence(Column column) {
        return column.getSequence();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the containing AbstractTable for the given Column.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the containing AbstractTable for the given Column",
        examples = {
            @Example(expression = "column.owner()", result = "abstractTable")
        }
    )
    // @formatter:on
    public AbstractTable owner(Column column) {
        return column.getOwner();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the Column is automatically incremented.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "true if the Column is automatically incremented, false otherwise",
        examples = {
            @Example(expression = "column.isAutoincrement()", result = "true")
        }
    )
    // @formatter:on
    public boolean isAutoincrement(Column column) {
        return column.isAutoincrement();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the Column is in the primary key.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "true if the Column is in the primary key, false otherwise",
        examples = {
            @Example(expression = "column.isInPrimaryKey()", result = "true")
        }
    )
    // @formatter:on
    public boolean isInPrimaryKey(Column column) {
        return column.isInPrimaryKey();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the Column is in the foreign key.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "true if the Column is in the foreign key, false otherwise",
        examples = {
            @Example(expression = "column.isInForeignKey()", result = "true")
        }
    )
    // @formatter:on
    public boolean isInForeignKey(Column column) {
        return column.isInForeignKey();
    }

    // @formatter:off
    @Documentation(
        value = "Tells if the Column contains unique values.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "true if the Column contains unique values, false otherwise",
        examples = {
            @Example(expression = "column.isUnique()", result = "true")
        }
    )
    // @formatter:on
    public boolean isUnique(Column column) {
        return column.isUnique();
    }

    // @formatter:off
    @Documentation(
        value = "Returns the content of a table cell that is checked when the column is in a foreign key.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the content of a table cell that is checked when the column is in a foreign key",
        examples = {
            @Example(expression = "column.checkForeignKey()", result = "\"X\""),
            @Example(expression = "column.checkForeignKey()", result = "\"\"")
        }
    )
    // @formatter:on
    public String checkForeignKey(Column column) {
        return column.isInForeignKey() ? "X" : "";
    }

    // @formatter:off
    @Documentation(
        value = "Returns the content of a table cell that is checked when the column is in a primary key.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the content of a table cell that is checked when the column is in a primary key",
        examples = {
            @Example(expression = "column.checkPrimaryKey()", result = "\"X\""),
            @Example(expression = "column.checkPrimaryKey()", result = "\"\"")
        }
    )
    // @formatter:on
    public String checkPrimaryKey(Column col) {
        return col.isInPrimaryKey() ? "X" : "";
    }

    // @formatter:off
    @Documentation(
        value = "Returns the content of a table cell that is checked when the column is mandatory.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the content of a table cell that is checked when the column is mandatory",
        examples = {
            @Example(expression = "column.isMandatory()", result = "\"Oui\""),
            @Example(expression = "column.isMandatory()", result = "\"Non\"")
        }
    )
    // @formatter:on
    public String isMandatory(Column col) {
        return col.isNullable() ? "Non" : "Oui";
    }

    // @formatter:off
    @Documentation(
        value = "Returns the content of a table cell that is checked when the column is primary key.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the content of a table cell that is checked when the column is primary key",
        examples = {
            @Example(expression = "column.isPrimaryKey()", result = "\"Oui\""),
            @Example(expression = "column.isPrimaryKey()", result = "\"Non\"")
        }
    )
    // @formatter:on
    public String isPrimaryKey(Column col) {
        return col.isInPrimaryKey() ? "Oui" : "Non";
    }

    // @formatter:off
    @Documentation(
        value = "Returns the content of a table cell that is checked when the column is foreign key.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the content of a table cell that is checked when the column is foreign key",
        examples = {
            @Example(expression = "column.isPrimaryKey()", result = "\"Oui\""),
            @Example(expression = "column.isPrimaryKey()", result = "\"Non\"")
        }
    )
    // @formatter:on
    public String isForeignKey(Column col) {
        return col.isInForeignKey() ? "Oui" : "Non";
    }

    // @formatter:off
    @Documentation(
        value = "Returns the number of record.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the number of record",
        examples = {
            @Example(expression = "column.recordNumber()", result = "5000"),
        }
    )
    // @formatter:on
    public int recordNumber(Column col) {
        return NUMBER_OF_RECORD;
    }

    // @formatter:off
    @Documentation(
        value = "Returns the Column type name.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "the Column type name",
        examples = {
            @Example(expression = "column.typeName()", result = "\"varchar\""),
        }
    )
    // @formatter:on
    public String typeName(Column column) {
        final String res;
        Type type = column.getType();
        if (type instanceof NativeType) {
            res = ((NativeType) type).getName();
        } else if (type instanceof UserDefinedTypeRef) {
            res = ((UserDefinedTypeRef) type).getType().getName();
        } else if (type instanceof TypeInstance) {
            res = ((TypeInstance) type).getNativeType().getName();
        } else {
            res = "no name found";
        }

        return res;
    }

}
