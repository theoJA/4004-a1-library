package acceptanceTesting;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.ServerOutput;
import server.logic.handler.InputHandler;

public class Borrow {

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
	public void borrow_invalid() {
		// user has to login as borrower first
		testServerOutput = testInputHandler.processInput("", WAITING);
		assertEquals("Who Are you?Clerk or User?", testServerOutput.getOutput());
		assertEquals(FINISHWAITING, testServerOutput.getState());

		testServerOutput = testInputHandler.processInput("user", FINISHWAITING);
		assertEquals("Please Input Username and Password:'username,password'", testServerOutput.getOutput());
		assertEquals(USERLOGIN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("michelle@carleton.ca,Michelle", USERLOGIN);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		// system responds with the user menu
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testOutputHandler.userLogin("michelle@carleton.ca,Michelle").getOutput());
		
		// user selects borrow
		testServerOutput = testInputHandler.processInput("borrow", USER);
		assertEquals("Please Input User Info:'useremail,ISBN,copynumber'", testServerOutput.getOutput());
		assertEquals(BORROW, testServerOutput.getState());
		
		// user submits an invalid username when creating item
		assertEquals("The User Does Not Exist!", testOutputHandler.borrow("nonexistentUser@carleton.ca,9781442668585,1").getOutput());
	}
	
	@Test
	public void borrow_valid() {
		// user has to login as borrower first
		testServerOutput = testInputHandler.processInput("", WAITING);
		assertEquals("Who Are you?Clerk or User?", testServerOutput.getOutput());
		assertEquals(FINISHWAITING, testServerOutput.getState());

		testServerOutput = testInputHandler.processInput("user", FINISHWAITING);
		assertEquals("Please Input Username and Password:'username,password'", testServerOutput.getOutput());
		assertEquals(USERLOGIN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("michelle@carleton.ca,Michelle", USERLOGIN);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		// system responds with the user menu
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testOutputHandler.userLogin("michelle@carleton.ca,Michelle").getOutput());
		
		// user selects borrow
		testServerOutput = testInputHandler.processInput("borrow", USER);
		assertEquals("Please Input User Info:'useremail,ISBN,copynumber'", testServerOutput.getOutput());
		assertEquals(BORROW, testServerOutput.getState());
		
		// user successfully borrows a book
		assertEquals("Success!", testOutputHandler.borrow("michelle@carleton.ca,9781317594277,1").getOutput());
	}

}
