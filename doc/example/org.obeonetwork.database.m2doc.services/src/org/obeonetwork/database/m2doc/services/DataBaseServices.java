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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.acceleo.annotations.api.documentation.Documentation;
import org.eclipse.acceleo.annotations.api.documentation.Example;
import org.eclipse.acceleo.annotations.api.documentation.Param;
import org.eclipse.acceleo.annotations.api.documentation.ServiceProvider;
import org.obeonetwork.dsl.database.AbstractTable;
import org.obeonetwork.dsl.database.DataBase;
import org.obeonetwork.dsl.database.ForeignKey;
import org.obeonetwork.dsl.database.Schema;
import org.obeonetwork.dsl.database.Sequence;
import org.obeonetwork.dsl.database.Table;
import org.obeonetwork.dsl.database.TableContainer;
import org.obeonetwork.dsl.database.View;
import org.obeonetwork.dsl.database.util.DatabaseSwitch;
import org.obeonetwork.dsl.typeslibrary.NativeTypesLibrary;
import org.obeonetwork.dsl.typeslibrary.TypesLibrary;
import org.obeonetwork.dsl.typeslibrary.UserDefinedTypesLibrary;

//@formatter:off
@ServiceProvider(
value = "Services available for DataBases."
)
//@formatter:on
@SuppressWarnings({"checkstyle:javadocmethod", "checkstyle:javadoctype"})
public class DataBaseServices {

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
    public String url(DataBase dataBase) {
        return dataBase.getUrl();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of Schema of the DataBase.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the List of Schema of the DataBase",
        examples = {
            @Example(expression = "dataBase.schemas()", result = "{schema1, schema2}")
        }
    )
    // @formatter:on
    public List<Schema> schemas(DataBase dataBase) {
        return dataBase.getSchemas();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the List of UserDefinedTypesLibrary of the DataBase.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the List of UserDefinedTypesLibrary of the DataBase",
        examples = {
            @Example(expression = "dataBase.defines()", result = "{userLib1, userLib2}")
        }
    )
    // @formatter:on
    public List<UserDefinedTypesLibrary> defines(DataBase dataBase) {
        return dataBase.getDefines();
    }

    /**
     * Returns the name of the used library or the empty string if it's not known.
     * 
     * @param db
     * @return
     */
    public String typeLibraryName(DataBase db) {
        List<TypesLibrary> libs = db.getUsedLibraries();
        if (libs.size() > 0 && libs.get(0) instanceof NativeTypesLibrary) {
            return ((NativeTypesLibrary) libs.get(0)).getName();
        } else {
            return "";
        }
    }

    // @formatter:off
    @Documentation(
        value = "Returns the type libraries the database uses.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the type libraries the database uses.",
        examples = {
            @Example(expression = "dataBase.usedLibraries()", result = "\"userLibrary1\""),
            @Example(expression = "dataBase.usedLibraries()", result = "\"Inconnu\"")
        }
    )
    // @formatter:on
    public List<TypesLibrary> usedLibrary(DataBase database) {
        return database.getUsedLibraries();
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Set of all Table contained in the DataBase.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the Set of all Table contained in the DataBase",
        examples = {
            @Example(expression = "dataBase.allTables()", result = "{table1, table2, table3}")
        }
    )
    // @formatter:on
    public Set<Table> allTables(DataBase database) {
        TableCollector collector = new TableCollector();
        collector.doSwitch(database);
        return collector.tables;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Set of all View contained in the DataBase.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the Set of all View contained in the DataBase",
        examples = {
            @Example(expression = "dataBase.allTables()", result = "{view1, view2, view3}")
        }
    )
    // @formatter:on
    public Set<View> allViews(DataBase database) {
        ViewCollector collector = new ViewCollector();
        collector.doSwitch(database);
        return collector.tables;
    }

    // @formatter:off
    @Documentation(
        value = "Gets the Set of all sequences defined in the specified database.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the Set of all sequences defined in the specified database",
        examples = {
            @Example(expression = "dataBase.allSequence()", result = "{seq1, seq2, seq3}")
        }
    )
    // @formatter:on
    public Set<Sequence> allSequences(DataBase database) {
        Set<Sequence> result = new HashSet<Sequence>();
        Set<Table> tables = allTables(database);
        for (Table table : tables) {
            if (table.eContainer() instanceof TableContainer) {
                result.addAll(((TableContainer) table.eContainer()).getSequences());
            }
        }
        return result;
    }

    /**
     * Returns the list of local tables including tables in schemas.
     * 
     * @param db
     *            the database
     * @return the list of local tables.
     */
    public List<Table> tables(DataBase db) {
        List<Table> result = new ArrayList<Table>();
        for (AbstractTable table : db.getTables()) {
            if (table instanceof Table) {
                result.add((Table) table);
            }
        }
        for (Schema schema : db.getSchemas()) {
            for (AbstractTable table : schema.getTables()) {
                if (table instanceof Table) {
                    result.add((Table) table);
                }
            }
        }
        return result;
    }

    /**
     * Returns the list of local sequences including sequences in schemas.
     * 
     * @param db
     *            the database
     * @return the list of local sequences.
     */
    public List<Sequence> sequences(DataBase db) {
        List<Sequence> result = new ArrayList<Sequence>();
        for (Sequence seq : db.getSequences()) {
            result.add(seq);
        }
        for (Schema schema : db.getSchemas()) {
            for (Sequence seq : schema.getSequences()) {
                result.add(seq);
            }
        }
        return result;
    }

    /**
     * Switch class used to collect the tables of a data base.
     * 
     * @author Romain Guider
     */
    private static class TableCollector extends DatabaseSwitch<String> {
        /**
         * The set used to collect tables.
         */
        private Set<Table> tables = new HashSet<Table>();

        @Override
        public String caseDataBase(DataBase object) {
            for (AbstractTable table : object.getTables()) {
                doSwitch(table);
            }
            for (Schema schema : object.getSchemas()) {
                doSwitch(schema);
            }
            return "";
        }

        @Override
        public String caseTable(Table object) {
            tables.add(object);
            for (ForeignKey key : object.getForeignKeys()) {
                doSwitch(key.getTarget());
            }
            return "";
        }

        @Override
        public String caseSchema(Schema object) {
            for (AbstractTable table : object.getTables()) {
                doSwitch(table);
            }
            return "";
        }

    }

    /**
     * Switch class used to collect the tables of a data base.
     * 
     * @author Romain Guider
     */
    private static class ViewCollector extends DatabaseSwitch<String> {
        /**
         * The set used to collect tables.
         */
        private Set<View> tables = new HashSet<View>();

        @Override
        public String caseDataBase(DataBase object) {
            for (AbstractTable table : object.getTables()) {
                doSwitch(table);
            }
            for (Schema schema : object.getSchemas()) {
                doSwitch(schema);
            }
            return "";
        }

        @Override
        public String caseView(View object) {
            tables.add(object);
            return "";
        }

        @Override
        public String caseSchema(Schema object) {
            for (AbstractTable table : object.getTables()) {
                doSwitch(table);
            }
            return "";
        }

    }

}
