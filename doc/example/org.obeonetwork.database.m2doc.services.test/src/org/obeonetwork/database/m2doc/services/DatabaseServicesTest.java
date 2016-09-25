package org.obeonetwork.database.m2doc.services;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.obeonetwork.database.m2doc.services.common.AbstractTest;
import org.obeonetwork.dsl.database.DataBase;
import org.obeonetwork.dsl.database.Table;

public class DatabaseServicesTest extends AbstractTest {
	/**
	 * the test database model.
	 */
	private DataBase dataBase;

	@Before
	public void setUp() {
		dataBase = loadModel("model/serie-oracle.database");
	}

	@Test
	public void testAllTables() {
		Set<Table> tables = new DataBaseServices().allTables(dataBase);
		assertEquals(10, tables.size());
	}

}
