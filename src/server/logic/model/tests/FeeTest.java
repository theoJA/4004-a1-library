package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Fee;

public class FeeTest {

	private Fee testFee = new Fee(12, 23);
	
	@Test
	public void test_getUserId() {
		assertEquals(12, testFee.getUserId());
	}

	@Test
	public void test_getFee() {
		assertEquals(23, testFee.getFee(), 0);
	}
	
}
