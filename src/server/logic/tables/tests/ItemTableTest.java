package server.logic.tables.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Item;
import server.logic.tables.ItemTable;

import java.util.List;

public class ItemTableTest {
	
	private int testItemId = 2;
	private String testISBN = "9781442667181";
	private String testCopyNumber = "1";
	
	// Assigning the address in memory of instance of TitleTable to a variable
	private ItemTable testItemTable = ItemTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testItemTable, ItemTable.getInstance());
	}
	
	@Test
	public void test_getItemList() {
		List<Item> testTitleList = testItemTable.getItemList();
		
		assertEquals(testTitleList, testItemTable.getItemList());
		assertEquals(testItemId, testItemTable.getItemList().get(testItemId).getItemId());
		assertEquals(testISBN, testItemTable.getItemList().get(testItemId).getISBN());
		assertEquals(testCopyNumber, testItemTable.getItemList().get(testItemId).getCopyNumber());
	}
	
	@Test
	public void test_lookup() {
		assertTrue(testItemTable.lookup(testISBN, testCopyNumber));
		assertFalse(testItemTable.lookup("1231231231231", "10"));
	}
	
	@Test
	public void test_createItem() {
		// Should return true when a title is successfully created and added to the title list
		assertTrue(testItemTable.createItem(testISBN));
		
		// Checking to see if created item was recently added into the list
		assertEquals(testISBN, testItemTable.getItemList().get(testItemTable.getItemList().size() - 1).getISBN());
		
		// Checking to see if the created item's copy number was incremented
		int newCopyNumber = Integer.parseInt(testCopyNumber) + 1;
		assertEquals(String.valueOf(newCopyNumber), testItemTable.getItemList().get(testItemTable.getItemList().size() - 1).getCopyNumber());
	}
	
	@Test
	public void test_delete() {
		assertEquals("Active Loan Exists", testItemTable.delete(testISBN, testCopyNumber));
		assertEquals("The Item Does Not Exist", testItemTable.delete(testISBN+"typo", testCopyNumber));
		assertEquals("success", testItemTable.delete("9781611687910", testCopyNumber));
	}
	
	@Test
	public void test_deleteAll() {
		testItemTable.deleteAll("9781442616899");
		assertEquals("N/A", testItemTable.getItemList().get(1).getISBN());
		assertEquals("N/A", testItemTable.getItemList().get(1).getCopyNumber());
	}	
}
