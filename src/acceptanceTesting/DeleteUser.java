package acceptanceTesting;

import static org.junit.Assert.*;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.ServerOutput;
import server.logic.handler.InputHandler;

import org.junit.Test;

public class DeleteUser {

	public int WAITING = 0;
	public int FINISHWAITING=1;
	public int CLERK = 2;
    public int USER = 3;
    public int CREATEUSER=4;
    public int CREATETITLE=5;
    public int CREATEITEM=6;
    public int DELETEUSER=7;
    public int DELETETITLE=8;
    public int DELETEITEM=9;
    public int BORROW=10;
    public int RENEW=11;
    public int RETURN=12;
    public int PAYFINE=13;
    public int CLERKLOGIN=14;
    public int USERLOGIN=15;
	
	private OutputHandler testOutputHandler = new OutputHandler();
	private InputHandler testInputHandler = new InputHandler();
	private ServerOutput testServerOutput = new ServerOutput("",0);
	
	@Test
	public void deleteUser_invalid() {
		// user has to login as clerk first
		testServerOutput = testInputHandler.processInput("", WAITING);
		assertEquals("Who Are you?Clerk or User?", testServerOutput.getOutput());
		assertEquals(FINISHWAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("clerk", FINISHWAITING);
		assertEquals("Please Input The Password:", testServerOutput.getOutput());
		assertEquals(CLERKLOGIN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("admin", CLERKLOGIN);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		// system responds with the clerk menu
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testOutputHandler.clerkLogin("admin").getOutput());

		// clerk enters 'delete user'
		testServerOutput = testInputHandler.processInput("delete user", CLERK);
		assertEquals("Please Input User Info:'useremail'", testServerOutput.getOutput());
		assertEquals(DELETEUSER, testServerOutput.getState());
		
		// clerk enters an already existing username and password
		assertEquals("The User Does Not Exist!", testOutputHandler.deleteUser("someuser@carleton.ca").getOutput());
		
	}
	
	@Test
	public void deleteUser_valid() {
		// user has to login as clerk first
		testServerOutput = testInputHandler.processInput("", WAITING);
		assertEquals("Who Are you?Clerk or User?", testServerOutput.getOutput());
		assertEquals(FINISHWAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("clerk", FINISHWAITING);
		assertEquals("Please Input The Password:", testServerOutput.getOutput());
		assertEquals(CLERKLOGIN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("admin", CLERKLOGIN);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		// system responds with the clerk menu
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testOutputHandler.clerkLogin("admin").getOutput());

		// clerk enters 'delete User'
		testServerOutput = testInputHandler.processInput("delete user", CLERK);
		assertEquals("Please Input User Info:'useremail'", testServerOutput.getOutput());
		assertEquals(DELETEUSER, testServerOutput.getState());
		
		// clerk enters a valid username and password
		assertEquals("Success!", testOutputHandler.deleteUser("sun@carleton.ca").getOutput());
	}
	

}
