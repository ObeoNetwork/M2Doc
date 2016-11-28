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
    /**
     * Returns the string "Oui" when the parameter is </code>true<code> and "Non" otherwise.
     * @param b 
     * @return a yes/no string representation of the parameter.
     */
    private String yesNo(boolean b) {
        return b? "Oui" : "Non";
    }
    // @formatter:on
    public String isAutoincrement(Column column) {
        if (column != null) {
            return yesNo(column.isAutoincrement());
        } else {
            return "No";
        }
    }

    /**
     * Returns "X" when the column is autoincrement and "" if the column is not unique or is <code>null</code/
     * 
     * @param column
     * @return
     */
    public String checkAutoincrement(Column column) {
        if (column != null) {
            return column.isAutoincrement() ? "X" : "";
        } else {
            return "";
        }
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
    public String isInPrimaryKey(Column column) {
        if (column != null) {
            return yesNo(column.isInPrimaryKey());
        } else {
            return "No";
        }
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
    public String isInForeignKey(Column column) {
        if (column != null) {
            return yesNo(column.isInForeignKey());
        } else {
            return "No";
        }
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
    public String isUnic(Column column) {
        if (column != null) {
            return yesNo(column.isUnique());
        } else {
            return "No";
        }
    }

    // @formatter:off
    @Documentation(
        value = "returns a X when the colonne is nullable, an empty string otherwise.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "returns a X when the colonne is nullable, an empty string otherwise.",
        examples = {
            @Example(expression = "column.checkNullable()", result = "true")
        }
    )
    // @formatter:on
    public String checkNullable(Column column) {
        if (column != null) {
            return column.isNullable() ? "X" : "";
        } else {
            return "";
        }
    }

    /**
     * Returns "Yes" when the column is nullable and "No" otherwise or if it is <code>null</code>.
     * 
     * @param column
     * @return
     */
    public String isNullable(Column column) {
        if (column != null) {
            return yesNo(column.isNullable());
        } else {
            return "No";
        }
    }

    // @formatter:off
    @Documentation(
        value = "returns a X when the colonne is nullable, an empty string otherwise.",
        params = {
            @Param(name = "column", value = "The Column"),
        },
        result = "returns a X when the colonne is nullable, an empty string otherwise.",
        examples = {
            @Example(expression = "column.checkNullable()", result = "true")
        }
    )
    // @formatter:on
    public String checkMandatory(Column column) {
        if (column != null) {
            return column.isNullable() ? "" : "X";
        } else {
            return "";
        }
    }

    /**
     * Returns "Yes" when the column is nullable and "No" otherwise or if it is <code>null</code>.
     * 
     * @param column
     * @return
     */
    public String isMandatory(Column column) {
        if (column != null) {
            return yesNo(!column.isNullable());
        } else {
            return "No";
        }
    }

    /**
     * Returns "X" when the column is in a foreign key and "" otherwise or if the column is <code>null</code/
     * 
     * @param column
     * @return
     */
    public String checkInForeignKey(Column column) {
        if (column != null) {
            return column.isInForeignKey() ? "X" : "";
        } else {
            return "";
        }
    }

    /**
     * Returns "X" when the column is unique and "" if the column is not unique or is <code>null</code/
     * 
     * @param column
     * @return
     */
    public String checkUnique(Column column) {
        if (column != null) {
            return column.isUnique() ? "X" : "";
        } else {
            return "";
        }
    }

    /**
     * Returns "X" when the column is in a primary key and "" otherwise or if the column is <code>null</code/
     * 
     * @param column
     * @return
     */
    public String checkInPrimaryKey(Column col) {
        if (col != null) {
            return col.isInPrimaryKey() ? "X" : "";
        } else {
            return "";
        }
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
        if (type instanceof UserDefinedTypeRef) {
            res = ((UserDefinedTypeRef) type).getType().getName();
        } else if (type instanceof TypeInstance) {
            res = ((TypeInstance) type).getNativeType().getName();
        } else {
            res = "no name found";
        }

        return res;
    }

    /**
     * Returns the length and the precision of the type associated to the column.
     * Result is like follows :
     * <ul>
     * <li>&lt;length&gt; if the type has the NativeKind.LENGTH attribute</li>
     * <li>&lt;length,precision&gt; if the type has the NativeKind.LENGTH_AND_PRECISION attribute</li>
     * 
     * @param col
     *            the column
     * @return a description of the length of the column's type.
     */
    public String typeLength(Column col) {
        final String res;
        Type type = col.getType();
        if (type instanceof TypeInstance) {
            TypeInstance instance = (TypeInstance) type;
            switch (instance.getNativeType().getSpec()) {
                case LENGTH:
                    res = instance.getLength().toString();
                    break;
                case LENGTH_AND_PRECISION:
                    res = instance.getLength() + "," + instance.getPrecision();
                    break;
                default:
                    res = "";
            }
        } else {
            res = "";
        }
        return res;
    }
}
