package server.logic.tables.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import server.logic.model.Fee;
import server.logic.tables.FeeTable;

public class FeeTableTest {

	// This user has been initialized during FeeTable()
	private int testUserId_0 = 0;
	private int testFee_0 = 5;
	private int newTestFee_0 = 9;
	
	// This user is not initialized during FeeTable()
	private int testUserId_1 = 1;
	private int testFee_1 = 0;
	private int newTestFee_1 = 4;

	
	// Assigning the address in memory of instance of FeeTable to a variable
	private FeeTable testFeeTable = FeeTable.getInstance();
	private List<Fee> testFeeList = testFeeTable.getFeeList();
	
	@Test
	public void test_getInstance() {
		// Check if FeeTable.getInstance() indeed returns the instance of FeeTable
		// Also the method getInstance() calls the Initialization() method. Therefore by passing this test, 
		// 	we prove that Initialization() works
		assertEquals(testFeeTable, FeeTable.getInstance());
	}
	
	@Test
	public void test_getFeeList() {	
		assertEquals(testFeeList, testFeeTable.getFeeList());
		assertEquals(testUserId_0, testFeeTable.getFeeList().get(testUserId_0).getUserId());
		assertEquals(testFee_0, testFeeTable.getFeeList().get(testUserId_0).getFee());
	}
	
	@Test
	public void test_applyFee() {
		// userId 0 has an existing fee of $5 in the list
		// userId 0 has only borrowed for 3 days so they don't receive any fee. Therefore the fee should still be $5
		testFeeTable.applyFee(0, 180000);
		assertEquals(testFee_0, testFeeTable.getFeeList().get(testUserId_0).getFee());
		System.out.println(testFeeTable.getFeeList().get(testUserId_0).getFee());
		// If we apply a new fee of 9 days overdue to userId 0, the user should be charged with $9 fee ($5 + $4) since after 5 days,
		//	 its $1 for every additional day
		testFeeTable.applyFee(0, 540000);
		assertEquals(newTestFee_0, testFeeTable.getFeeList().get(testUserId_0).getFee());
		System.out.println(testFeeTable.getFeeList().get(testUserId_0).getFee());
		
		// now userId 1 is not initialized in the list in FeeTable(). Therefore, this user will begin with an initial fee of $0
		// userId 1 has only borrowed for 4 days so they don't receive any fee. Therefore the fee should still be $0
		testFeeTable.applyFee(0, 240000);
		assertEquals(testFee_1, testFeeTable.getFeeList().get(testUserId_1).getFee());
		System.out.println(testFeeTable.getFeeList().get(testUserId_1).getFee());
		// After 5 days, userId 1 will be charged $1 for every additional day. Therefore, after 9 days, userId 1 should be charged with $4
		testFeeTable.applyFee(1, 540000);
		assertEquals(newTestFee_1, testFeeTable.getFeeList().get(testUserId_1).getFee());
		System.out.println(testFeeTable.getFeeList().get(testUserId_1).getFee());
	}
	
	@Test
	public void test_lookupForNoFees() {
		// user doesn't exist so no fees, should be true
		assertTrue(testFeeTable.lookup(6));
		
		// userId 1 should not exists in the list since this test is independent from the applyFee test
		assertTrue(testFeeTable.lookup(testUserId_1));
		
		// user exists, and has fee, should be false
		assertFalse(testFeeTable.lookup(testUserId_0));
	}

	
}
