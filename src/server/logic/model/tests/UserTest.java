package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.User;

public class UserTest {

	private User testUser = new User(7); 
	
	@Test
	public void test_userId() {
		assertEquals(7, testUser.getUserId());
	}

}
