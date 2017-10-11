package acceptanceTesting;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.ServerOutput;
import server.logic.handler.InputHandler;

public class DeleteTitle {

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
	public void deleteTitle_invalid() {
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
		
		// clerk selects create title
		testServerOutput = testInputHandler.processInput("delete title", CLERK);
		assertEquals("Please Input Title Info:'ISBN'", testServerOutput.getOutput());
		assertEquals(DELETETITLE, testServerOutput.getState());
		
		// clerk submits an existing title and title deletion is invalid
		assertEquals("The Title Does Not Exist!", testOutputHandler.deleteTitle("9781442668585").getOutput());
	}

	@Test
	public void deleteTitle_valid() {
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
		
		// clerk selects create title
		testServerOutput = testInputHandler.processInput("delete title", CLERK);
		assertEquals("Please Input Title Info:'ISBN'", testServerOutput.getOutput());
		assertEquals(DELETETITLE, testServerOutput.getState());
		
		// clerk submits a title and title deletion is valid
		assertEquals("Success!", testOutputHandler.deleteTitle("9781317594277").getOutput());
	}

}
