package server.logic.handler.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;
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
	
	@Test
	public void test_createItem() {
		assertEquals("The Title Does Not Exists!", testOutputHandler.createItem("9781442668585").getOutput());
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", testOutputHandler.createItem("wrongFormat").getOutput());
		assertEquals("Success!", testOutputHandler.createItem("9781442616899").getOutput());
		
		ItemTable testItemTable = ItemTable.getInstance();
		testItemTable.delete("9781442616899",testItemTable.getItemList().get(testItemTable.getItemList().size()-1).getCopyNumber());
	}
	
	@Test
	public void test_deleteUser() {
		assertEquals("The User Does Not Exist!", testOutputHandler.deleteUser("someuser@carleton.ca").getOutput());
		assertEquals("Your input should in this format:'useremail'", testOutputHandler.deleteUser("wrongFormat").getOutput());
		
		testOutputHandler.createUser("testuser@carleton.ca,testuser");
		assertEquals("Success!", testOutputHandler.deleteUser("testuser@carleton.ca").getOutput());
		
	}
	
	@Test
	public void test_deleteTitle() {
		assertEquals("The Title Does Not Exist!", testOutputHandler.deleteTitle("9781442668585").getOutput());
		assertEquals("Your input should in this format:'ISBN',ISBN should be a 13-digit number", testOutputHandler.deleteTitle("wrongFormat").getOutput());
		
		testOutputHandler.createTitle("9781442668585,By the grace of Not God");
		assertEquals("Success!", testOutputHandler.deleteTitle("9781442668585").getOutput());
	}
	
	@Test
	public void test_deleteItem() {
		assertEquals("Your input should in this format:'ISBN,copynumber',ISBN should be a 13-digit number", testOutputHandler.deleteItem("someISBN").getOutput());
		
		testOutputHandler.createItem("9781442616899");
		ItemTable testItemTable = ItemTable.getInstance();
		assertEquals("Success!", testOutputHandler.deleteItem("9781442616899,"+testItemTable.getItemList().get(testItemTable.getItemList().size()-1).getCopyNumber()).getOutput());
	}
	
	@Test
	public void test_borrow() {
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", testOutputHandler.borrow("wrongformat").getOutput());
		assertEquals("The User Does Not Exist!", testOutputHandler.borrow("nonexistentUser@carleton.ca,9781442668585,1").getOutput());
		assertEquals("Success!", testOutputHandler.borrow("michelle@carleton.ca,9781611687910,1").getOutput());
		
		LoanTable testLoanTable = LoanTable.getInstance();
		Date testDate = new Date();
		assertEquals("success", testLoanTable.returnItem(2, "9781611687910", "1", testDate));
	}
	
	@Test
	public void test_renew() {
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", testOutputHandler.renew("wrongformat").getOutput());
		assertEquals("The User Does Not Exist!", testOutputHandler.renew("nonexistentUser@carleton.ca,9781442668585,1").getOutput());
		
		assertEquals("Success!", testOutputHandler.borrow("michelle@carleton.ca,9781611687910,1").getOutput());
		assertEquals("Success!", testOutputHandler.renew("michelle@carleton.ca,9781611687910,1").getOutput());
	}
	
	@Test
	public void test_returnBook() {
		assertEquals("Your input should in this format:'useremail,ISBN,copynumber'", testOutputHandler.returnBook("wrongformat").getOutput());
		assertEquals("The User Does Not Exist!", testOutputHandler.returnBook("nonexistentUser@carleton.ca,9781442668585,1").getOutput());
		
		assertEquals("Success!", testOutputHandler.borrow("michelle@carleton.ca,9781611687910,1").getOutput());
		assertEquals("Success!", testOutputHandler.returnBook("michelle@carleton.ca,9781611687910,1").getOutput());
	}
	
	@Test
	public void test_payFine() {
		assertEquals("The User Does Not Exist!", testOutputHandler.payFine("someuser@carleton.ca").getOutput());
		assertEquals("Your input should in this format:'useremail'", testOutputHandler.payFine("wrongFormat").getOutput());
		
		assertEquals("Success!", testOutputHandler.borrow("michelle@carleton.ca,9781611687910,1").getOutput());
		
		FeeTable testFeeTable = FeeTable.getInstance();
		testFeeTable.applyFee(2, 600000); // borrow book for 10 days, should get fined $5
		assertEquals("Success!", testOutputHandler.returnBook("michelle@carleton.ca,9781611687910,1").getOutput());
		assertEquals("Success!", testOutputHandler.payFine("michelle@carleton.ca").getOutput());
	}
	
	@Test
	public void test_clerkLogin() {
		assertEquals("Wrong Password!Please Input The Password:", testOutputHandler.clerkLogin("wrongpassword").getOutput());
		assertEquals("What can I do for you?Menu:Create User/Title/Item,Delete User/Title/Item.", testOutputHandler.clerkLogin("admin").getOutput());
	}
	
	
}

