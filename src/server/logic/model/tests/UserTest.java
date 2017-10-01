package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.User;

public class UserTest {

	private User testUser = new User(7, "password"); 
	
	// All setters are implemented privately in the User class
	// Only getters will be tested
	
	@Test
	public void test_getUserId() {
		assertEquals(7, testUser.getUserId());
	}
	
	@Test
	public void test_getUserPassword() {
		assertEquals("password", testUser.getUserPassword());
	}
	
}
