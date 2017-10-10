package server.logic.tables.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import server.logic.model.Loan;
import server.logic.tables.LoanTable;
import server.logic.tables.FeeTable;
import server.logic.tables.ItemTable;

public class LoanTableTest {

	private int testUserId = 1;
	private String testISBN = "9781442667181";
	private String testCopyNumber = "1";
	private Date testDate = new Date();
	private String testRenewState = "0";
	
	// Assigning the address in memory of instance of LoanTable to a variable
	private LoanTable testLoanTable = LoanTable.getInstance();
	
	@Test
	public void test_getInstance() {
		assertEquals(testLoanTable, LoanTable.getInstance());
	}

	@Test
	public void test_getLoanList() {
		
		List<Loan> testLoanList = testLoanTable.getLoanList();
		assertEquals(testLoanList, testLoanTable.getLoanList());
		System.out.println(testLoanList);
		assertEquals(testUserId, testLoanTable.getLoanList().get(1).getUserId());
		//assertEquals(testISBN, testLoanTable.getLoanList().get(1).getISBN());
		//assertEquals(testCopyNumber, testLoanTable.getLoanList().get(1).getCopyNumber());
		//assertEquals(testDate, testLoanTable.getLoanList().get(testUserId).getDate());
		//assertEquals("1", testLoanTable.getLoanList().get(1).getRenewState());
		
		//System.out.println(testLoanTable.getLoanList().get(testUserId).getDate());
	}
	
	@Test
	public void test_lookLimit() {
		assertFalse(testLoanTable.lookLimit(0));
		assertTrue(testLoanTable.lookLimit(4));
	}
	
	@Test
	public void test_lookupISBNcopyNumber() {
		assertFalse(testLoanTable.lookup(1, testISBN, testCopyNumber));
		assertTrue(testLoanTable.lookup(1, testISBN + "typo", testCopyNumber));
	}
	
	@Test
	public void test_checkUser() {
		assertFalse(testLoanTable.checkUser(testUserId));
		assertTrue(testLoanTable.checkUser(2));
	}
	
	@Test
	public void test_checkLoanISBNcopyNumber() {
		assertFalse(testLoanTable.checkLoan(testISBN,testCopyNumber));
		assertTrue(testLoanTable.checkLoan(testISBN+"type",testCopyNumber));
		assertTrue(testLoanTable.checkLoan(testISBN,testCopyNumber+"typo"));
	}
	
	@Test
	public void test_checkLoanISBN() {
		assertFalse(testLoanTable.checkLoan(testISBN));
		assertTrue(testLoanTable.checkLoan(testISBN+"typo"));
	}
	
	@Test
	public void test_checkLimit() {
		assertTrue(testLoanTable.checkLimit(testUserId));
	}
	
	@Test
	public void test_createLoan() {
		
		assertEquals("User Invalid",testLoanTable.createLoan(10, testISBN, testCopyNumber, testDate));
		assertEquals("ISBN Invalid",testLoanTable.createLoan(testUserId, testISBN+"typo", testCopyNumber, testDate));
		assertEquals("Copynumber Invalid",testLoanTable.createLoan(testUserId, testISBN, testCopyNumber+"typo", testDate));
		assertEquals("The Item is Not Available",testLoanTable.createLoan(2, "9781442668584", testCopyNumber, testDate));
		
		ItemTable testItemTable = ItemTable.getInstance();
		testItemTable.createItem("9781611687910");
		
		assertEquals("Outstanding Fee Exists", testLoanTable.createLoan(0, "9781611687910", testCopyNumber, testDate));
		assertEquals("success",testLoanTable.createLoan(1, testISBN, testCopyNumber, testDate));
		assertEquals("success",testLoanTable.createLoan(1, "9781317594277", testCopyNumber, testDate));
		assertEquals("success", testLoanTable.createLoan(1, "9781442616899", testCopyNumber, testDate));
		assertEquals("The Maximum Number of Items is Reached", testLoanTable.createLoan(1, "9781317594277", "2", testDate));
		
	}
	
	@Test
	public void test_returnItem() {
		assertEquals("success", testLoanTable.returnItem(1, testISBN, testCopyNumber, testDate));
		assertEquals("The Loan Does Not Exist", testLoanTable.returnItem(1, testISBN, testCopyNumber, testDate));
	}
	
	@Test
	public void test_renewal() {
		List<Loan> testLoanList = testLoanTable.getLoanList();
		
		System.out.println(testLoanList);
		assertEquals("Outstanding Fee Exists", testLoanTable.renewal(0, testISBN, testCopyNumber, testDate));
		FeeTable testFeeTable = FeeTable.getInstance();
		System.out.println(testFeeTable.getFeeList().get(1).getFee());
		testLoanTable.returnItem(1, "9781442667181", testCopyNumber, testDate);
		testFeeTable.payFine(1);
		System.out.println(testFeeTable.getFeeList().get(1).getFee());
		
		assertEquals("The loan does not exist", testLoanTable.renewal(1, testISBN+"typo", testCopyNumber, testDate));
		
		testLoanTable.createLoan(1, testISBN, testCopyNumber, testDate);
		testLoanTable.createLoan(1, "9781317594277", testCopyNumber, testDate);
		testLoanTable.createLoan(1, "9781442616899", testCopyNumber, testDate);
		testLoanTable.createLoan(1, "9781317594277", "2", testDate);
		assertEquals("The Maximum Number of Items is Reached", testLoanTable.renewal(1, testISBN, testCopyNumber, testDate));
		testLoanTable.returnItem(1, "9781317594277", testCopyNumber, testDate);
		testLoanTable.returnItem(1, "9781442616899", testCopyNumber, testDate);
		testLoanTable.returnItem(1, "9781317594277", "2", testDate);
		
		assertEquals("success", testLoanTable.renewal(1, testISBN, testCopyNumber, testDate));
		assertEquals("Renewed Item More Than Once for the Same Loan", testLoanTable.renewal(1, testISBN, testCopyNumber, testDate));
		
	}
}
