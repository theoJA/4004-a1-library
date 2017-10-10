package server.logic.handler.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import server.logic.handler.OutputHandler;
import server.logic.tables.UserTable;
import server.logic.tables.TitleTable;
import server.logic.tables.ItemTable;
import server.logic.tables.FeeTable;
import server.logic.tables.LoanTable;

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
	
	@Test
	public void test_createTitle() {
		assertEquals("Your input should in this format:'ISBN,title',ISBN should be a 13-digit number", testOutputHandler.createTitle("wrongformat,wrongformat").getOutput());
		assertEquals("The Title Already Exists!", testOutputHandler.createTitle("9781442668584,By the grace of God").getOutput());
		assertEquals("Success!", testOutputHandler.createTitle("9781442668585,By the grace of Not God").getOutput());
		
		TitleTable testTitleTable = TitleTable.getInstance();
		testTitleTable.delete("9781442668585");
	}
	

}
