package server.logic.handler.tests;

import static org.junit.Assert.*;
import server.logic.handler.InputHandler;
import server.logic.handler.OutputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

import org.junit.Test;

public class InputHandlerTest {

	private InputHandler testInputHandler = new InputHandler();
	private OutputHandler testOutputHandler = new OutputHandler();
	
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
    
	private ServerOutput testServerOutput = new ServerOutput("",0);
	
	@Test
	public void test_processInput() {
		
		testServerOutput = testInputHandler.processInput("", WAITING);
		assertEquals("Who Are you?Clerk or User?", testServerOutput.getOutput());
		assertEquals(FINISHWAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("clerk", FINISHWAITING);
		assertEquals("Please Input The Password:", testServerOutput.getOutput());
		assertEquals(CLERKLOGIN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("user", FINISHWAITING);
		assertEquals("Please Input Username and Password:'username,password'", testServerOutput.getOutput());
		assertEquals(USERLOGIN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("somethingelse", FINISHWAITING);
		assertEquals("Who Are you?Clerk or User?", testServerOutput.getOutput());
		assertEquals(FINISHWAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("admin", CLERKLOGIN);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("michelle@carleton.ca,Michelle", USERLOGIN);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("create user", CLERK);
		assertEquals("Please Input User Info:'username,password'", testServerOutput.getOutput());
		assertEquals(CREATEUSER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("create title", CLERK);
		assertEquals("Please Input Title Info:'ISBN,title'", testServerOutput.getOutput());
		assertEquals(CREATETITLE, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("create item", CLERK);
		assertEquals("Please Input Item Info:'ISBN'", testServerOutput.getOutput());
		assertEquals(CREATEITEM, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("delete user", CLERK);
		assertEquals("Please Input User Info:'useremail'", testServerOutput.getOutput());
		assertEquals(DELETEUSER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("delete title", CLERK);
		assertEquals("Please Input Title Info:'ISBN'", testServerOutput.getOutput());
		assertEquals(DELETETITLE, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("delete item", CLERK);
		assertEquals("Please Input Item Info:'ISBN,copynumber'", testServerOutput.getOutput());
		assertEquals(DELETEITEM, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", CLERK);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", CLERK);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("borrow", USER);
		assertEquals("Please Input User Info:'useremail,ISBN,copynumber'", testServerOutput.getOutput());
		assertEquals(BORROW, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("renew", USER);
		assertEquals("Please Input Title Info:'useremail,ISBN,copynumber'", testServerOutput.getOutput());
		assertEquals(RENEW, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("return", USER);
		assertEquals("Please Input Item Info:'useremail,ISBN,copynumber'", testServerOutput.getOutput());
		assertEquals(RETURN, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("pay fine", USER);
		assertEquals("Please Input User Info:'useremail'", testServerOutput.getOutput());
		assertEquals(PAYFINE, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", USER);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", USER);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("somethingelse", USER);
		assertEquals("Please select from the menu.Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", CREATEUSER);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", CREATEUSER);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", CREATETITLE);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", CREATETITLE);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", CREATEITEM);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", CREATEITEM);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", DELETEUSER);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", DELETEUSER);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", DELETETITLE);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", DELETETITLE);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", DELETEITEM);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", DELETEITEM);
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testServerOutput.getOutput());
		assertEquals(CLERK, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", BORROW);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", BORROW);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", RENEW);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", RENEW);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", RETURN);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", RETURN);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("log out", PAYFINE);
		assertEquals("Successfully Log Out!", testServerOutput.getOutput());
		assertEquals(WAITING, testServerOutput.getState());
		
		testServerOutput = testInputHandler.processInput("main menu", PAYFINE);
		assertEquals("What can I do for you?Menu:Borrow,Renew,Return,Pay Fine.", testServerOutput.getOutput());
		assertEquals(USER, testServerOutput.getState());
	}

}
