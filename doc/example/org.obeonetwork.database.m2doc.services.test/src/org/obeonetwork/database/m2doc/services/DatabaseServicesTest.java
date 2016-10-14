package org.obeonetwork.database.m2doc.services;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.obeonetwork.database.m2doc.services.common.AbstractTest;
import org.obeonetwork.dsl.database.DataBase;
import org.obeonetwork.dsl.database.Sequence;
import org.obeonetwork.dsl.database.Table;
import org.obeonetwork.dsl.typeslibrary.UserDefinedTypesLibrary;

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

	@Test
	public void testDefines() {
		List<UserDefinedTypesLibrary> libs = new DataBaseServices().defines(dataBase);
		assertEquals(0, libs.size());
	}

	@Test
	public void allSequenceTest() {
		Set<Sequence> sequences = new DataBaseServices().allSequences(dataBase);
		assertEquals(8, sequences.size());
	}

	@Ignore
	@Test
	public void usedLibraryNameTest() {
		assertEquals("Oracle-11g", new DataBaseServices().typeLibraryName(dataBase));
	}
}
