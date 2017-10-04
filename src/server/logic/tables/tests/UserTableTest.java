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
		assertEquals("User exists.", testUserTable.checkUserExists(testUsername, testPassword));
		assertEquals("User does not exist.", testUserTable.checkUserExists(testUsername + "typo", testPassword));
		assertEquals("Password does not match.", testUserTable.checkUserExists(testUsername, testPassword + "typo"));
	}

	@Test
	public void  test_lookupByUsername() {
		assertEquals(testUserId, testUserTable.lookup(testUsername));
	}
}
