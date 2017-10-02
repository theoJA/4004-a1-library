package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Loan;

public class LoanTest {

	private Loan testLoan = new Loan(123);
	
	@Test
	public void test_getLoanUserId() {
		assertEquals(123, testLoan.getUserId() );
	}
}
