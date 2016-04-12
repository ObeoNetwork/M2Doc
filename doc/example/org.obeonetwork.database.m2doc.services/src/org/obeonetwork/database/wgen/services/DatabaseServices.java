package org.obeonetwork.database.wgen.services;

import org.obeonetwork.dsl.database.Column;
import org.obeonetwork.dsl.database.DataBase;
import org.obeonetwork.dsl.database.Table;
import org.obeonetwork.dsl.typeslibrary.NativeType;
import org.obeonetwork.dsl.typeslibrary.Type;
import org.obeonetwork.dsl.typeslibrary.TypeInstance;
import org.obeonetwork.dsl.typeslibrary.UserDefinedTypeRef;

public class DatabaseServices {

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

}
