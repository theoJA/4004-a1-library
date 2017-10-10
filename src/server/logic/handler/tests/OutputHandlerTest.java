package server.logic.handler.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.tables.UserTable;

public class OutputHandlerTest {

	private OutputHandler testOutputHandler = new OutputHandler();
	
	@Test
	public void test_createUser() {
		
		// creating user with the wrong user name format
		assertEquals("Your input should in this format:'username,password'", testOutputHandler.createUser("newUser,password").getOutput());
		assertEquals("The User Already Exists!", testOutputHandler.createUser("zhibo@carleton.ca,zhibo").getOutput());
		assertEquals("Success!", testOutputHandler.createUser("newUser@carleton.ca,password").getOutput());
		
		UserTable testUserTable = UserTable.getInstance();
		testUserTable.delete(testUserTable.getUserList().size() - 1);
		
	}
	
	//@Test
	//public void test_createTitle
	

}
