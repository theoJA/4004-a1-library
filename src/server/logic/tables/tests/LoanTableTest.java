package server.logic.tables.tests;

import static org.junit.Assert.*;

//import java.util.Date;
import java.util.List;

import org.junit.Test;

import server.logic.model.Loan;
import server.logic.tables.LoanTable;

public class LoanTableTest {

	private int testUserId = 1;
	private String testISBN = "9781442667181";
	private String testCopyNumber = "1";
	//private Date testDate = new Date();
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
		assertEquals(testUserId, testLoanTable.getLoanList().get(testUserId).getUserId());
		assertEquals(testISBN, testLoanTable.getLoanList().get(testUserId).getISBN());
		assertEquals(testCopyNumber, testLoanTable.getLoanList().get(testUserId).getCopyNumber());
		//assertEquals(testDate, testLoanTable.getLoanList().get(testUserId).getDate());
		assertEquals(testRenewState, testLoanTable.getLoanList().get(testUserId).getRenewState());
		
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
}
