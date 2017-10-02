package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Loan;

public class LoanTest {

	private Loan testLoan = new Loan(123, "1234567890123", 2);
	
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
}
