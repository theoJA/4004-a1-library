package server.logic.tables.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import server.logic.tables.TitleTable;

public class TitleTableTest {

	// Assigning the address in memory of instance of TitleTable to a variable
	private TitleTable testTitleTable = TitleTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testTitleTable, TitleTable.getInstance());
	}

}
