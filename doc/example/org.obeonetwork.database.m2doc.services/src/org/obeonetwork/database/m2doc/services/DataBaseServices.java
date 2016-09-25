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
import org.obeonetwork.dsl.database.Table;
import org.obeonetwork.dsl.database.View;
import org.obeonetwork.dsl.database.util.DatabaseSwitch;
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

    // @formatter:off
    @Documentation(
        value = "Gets the used library of the DataBase.",
        params = {
            @Param(name = "database", value = "The DataBase"),
        },
        result = "the used library of the DataBase",
        examples = {
            @Example(expression = "dataBase.DBLibrary()", result = "\"userLibrary1\""),
            @Example(expression = "dataBase.DBLibrary()", result = "\"Inconnu\"")
        }
    )
    // @formatter:on
    public String DBLibrary(DataBase database) {
        if (database.getUsedLibraries().size() > 0) {
            return database.getUsedLibraries().get(0).getKind().getName();
        } else {
            return "Inconnu";
        }
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
