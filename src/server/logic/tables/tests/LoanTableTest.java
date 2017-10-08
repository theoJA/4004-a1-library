package server.logic.tables.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.tables.LoanTable;

public class LoanTableTest {

	// Assigning the address in memory of instance of LoanTable to a variable
	private LoanTable testFeeTable = LoanTable.getInstance();
	
	@Test
	public void test_getInstance() {
		assertEquals(testFeeTable, LoanTable.getInstance());
	}

}
