package server.logic.tables.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import server.logic.model.Fee;
import server.logic.tables.FeeTable;

public class FeeTableTest {

	private int testUserId = 0;
	private int testFee = 5;
	
	// Assigning the address in memory of instance of FeeTable to a variable
	private FeeTable testFeeTable = FeeTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if FeeTable.getInstance() indeed returns the instance of FeeTable
		assertEquals(testFeeTable, FeeTable.getInstance());
	}
	
	@Test
	public void test_getFeeList() {
		List<Fee> testFeeList = testFeeTable.getFeeList();
		
		assertEquals(testFeeList, testFeeTable.getFeeList());
		assertEquals(testUserId, testFeeTable.getFeeList().get(testUserId).getUserId());
		assertEquals(testFee, testFeeTable.getFeeList().get(testUserId).getFee());
	}
	
	

}
