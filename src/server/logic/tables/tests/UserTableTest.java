package server.logic.tables.tests;

import static org.junit.Assert.*;
import server.logic.tables.UserTable;
import server.logic.model.User;
import java.util.List;

import org.junit.Test;

public class UserTableTest {

	private String testUsername = "zhibo@carleton.ca";
	private String testPassword = "zhibo";
	
	// Assigning the address in memory of instance of UserTable to a variable
	private UserTable testUserTable = UserTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testUserTable,UserTable.getInstance());
	}
	
	@Test
	public void test_getUserList() {
		List<User> testUserList = testUserTable.getUserList();
		System.out.println(testUserList);
		assertEquals(testUserList, testUserTable.getUserList());
		assertEquals(testUsername, testUserTable.getUserList().get(0).getUsername());
		assertEquals(testPassword, testUserTable.getUserList().get(0).getPassword());
	}
	

}
