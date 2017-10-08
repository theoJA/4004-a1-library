package server.logic.tables.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.FeeTable;

public class FeeTableTest {

	// Assigning the address in memory of instance of TitleTable to a variable
	private FeeTable testFeeTable = FeeTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testFeeTable, FeeTable.getInstance());
	}

}
