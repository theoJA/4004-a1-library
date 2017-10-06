package server.logic.tables.tests;

import static org.junit.Assert.*;
import server.logic.tables.UserTable;
import server.logic.model.User;
import java.util.List;

import org.junit.Test;

public class UserTableTest {

	private String testUsername = "kevin@carleton.ca";
	private String testPassword = "kevin";
	private int testUserId = 3;
	private String newTestUsername = "testUser@carleton.ca";
	private String newTestPassword = "testUser";
	
	
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
		
		assertEquals(testUserList, testUserTable.getUserList());
		assertEquals(testUsername, testUserTable.getUserList().get(3).getUsername());
		assertEquals(testPassword, testUserTable.getUserList().get(3).getPassword());
	}
	
	@Test
	public void test_checkUserExists() {
		assertEquals(0, testUserTable.checkUserExists(testUsername, testPassword));
		assertEquals(2, testUserTable.checkUserExists(testUsername + "typo", testPassword));
		assertEquals(1, testUserTable.checkUserExists(testUsername, testPassword + "typo"));
	}

	@Test
	public void test_lookupByUsername() {
		// True when user user name exists in the user list
		assertEquals(testUserId, testUserTable.lookup(testUsername));
		// False when user name does not exist in the user list
		assertEquals(-1, testUserTable.lookup("noName"));
	}
	
	@Test
	public void test_lookupByUserId() {
		// True when user id exists in the user list
		assertTrue(testUserTable.lookup(testUserId));
		
		// False when user id does not exist in the user list
		assertFalse(testUserTable.lookup(20));
	}
	
	@Test
	public void test_createUser() {
		// Should return true when a user is successfully created and added to the user list
		assertTrue(testUserTable.createUser(newTestUsername, newTestPassword));
		// Checking to see if created user is indeed in the list
		assertEquals(newTestUsername, testUserTable.getUserList().get(testUserTable.lookup(newTestUsername)).getUsername());
		assertEquals(newTestPassword, testUserTable.getUserList().get(testUserTable.lookup(newTestUsername)).getPassword());
		
		// Should return false when given user info exists in the user list
		assertFalse(testUserTable.createUser(testUsername, testPassword));
	}
	
}
