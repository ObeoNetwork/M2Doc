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

import java.util.HashSet;
import java.util.Set;

import org.obeonetwork.dsl.database.AbstractTable;
import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.DataBase;
import org.obeonetwork.dsl.database.ForeignKey;
import org.obeonetwork.dsl.database.Schema;
import org.obeonetwork.dsl.database.Table;
import org.obeonetwork.dsl.database.View;
import org.obeonetwork.dsl.database.util.DatabaseSwitch;
import org.obeonetwork.dsl.typeslibrary.NativeType;
import org.obeonetwork.dsl.typeslibrary.Type;
import org.obeonetwork.dsl.typeslibrary.TypeInstance;
import org.obeonetwork.dsl.typeslibrary.UserDefinedTypeRef;

/**
 * Set of services for the Database DSL.
 * 
 * @author Romain Guider
 *
 */
public class DatabaseServices {
	/**
	 * No arg constructor required by the AQL runtime.
	 */
	public DatabaseServices() {
	}

	/**
	 * Returns the content of a table cell that is checked when the column is in
	 * a foreign key.
	 * 
	 * @param col
	 * @return
	 */
	public String checkForeignKey(Column col) {
		return col.isInForeignKey() ? "X" : "";
	}

	/**
	 * Returns the content of a table cell that is checked when the column is in
	 * a primary key.
	 * 
	 * @param col
	 * @return
	 */
	public String checkPrimaryKey(Column col) {
		return col.isInPrimaryKey() ? "X" : "";
	}

	public String isMandatory(Column col) {
		return col.isNullable() ? "Non" : "Oui";
	}

	public String isPrimaryKey(Column col) {
		return col.isInPrimaryKey() ? "Non" : "Oui";
	}

	public String isForeignKey(Column col) {
		return col.isInForeignKey() ? "Non" : "Oui";
	}

	public int recordNumber(Column col) {
		return 5000;
	}

	public int recordNumber(Table table) {
		return 10000;
	}

	public String typeName(Column column) {
		Type type = column.getType();
		if (type instanceof NativeType) {
			return ((NativeType) type).getName();
		} else if (type instanceof UserDefinedTypeRef) {
			return ((UserDefinedTypeRef) type).getType().getName();
		} else if (type instanceof TypeInstance) {
			return ((TypeInstance) type).getNativeType().getName();
		} else {
			return "no name found";
		}
	}

	public String DBLibrary(DataBase db) {
		if (db.getUsedLibraries().size() > 0) {
			return db.getUsedLibraries().get(0).getKind().getName();
		} else {
			return "Inconnu";
		}
	}

	/**
	 * Creates and returns a complete list of the database tables.
	 * 
	 * @param db
	 * @return
	 */
	public Set<Table> allTables(DataBase db) {
		TableCollector collector = new TableCollector();
		collector.doSwitch(db);
		return collector.tables;
	}

	/**
	 * Creates and returns a complete list of the database tables.
	 * 
	 * @param db
	 * @return
	 */
	public Set<View> allViews(DataBase db) {
		ViewCollector collector = new ViewCollector();
		collector.doSwitch(db);
		return collector.tables;
	}

	/**
	 * Switch class used to collect the tables of a data base.
	 * 
	 * @author Romain Guider
	 *
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
	 *
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
