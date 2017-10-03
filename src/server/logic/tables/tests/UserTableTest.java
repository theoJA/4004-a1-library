package server.logic.tables.tests;

import static org.junit.Assert.*;
import server.logic.tables.UserTable;

import org.junit.Test;

public class UserTableTest {

	// Assigning the address in memory of instance of UserTable to a variable
	private UserTable testUserTable = UserTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testUserTable,UserTable.getInstance());
	}
	
	

}
