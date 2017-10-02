package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Loan;
import java.util.Date;


public class LoanTest {

	Date testDate = new Date();
	private Loan testLoan = new Loan(123, "1234567890123", 2, testDate, "RETURNED");
	
	@Test
	public void test_getUserId() {
		assertEquals(123, testLoan.getUserId() );
	}
	
	@Test
	public void test_getISBN() {
		assertEquals("1234567890123", testLoan.getISBN());
	}
	
	@Test
	public void test_getCopies() {
		assertEquals(2, testLoan.getCopies());
	}
	
	@Test
	public void test_getDate() {
		assertEquals(testDate, testLoan.getDate());
	}
	
	@Test
	public void test_getRenewState() {
		assertEquals("RETURNED", testLoan.getRenewState());
	}
	

	
}
