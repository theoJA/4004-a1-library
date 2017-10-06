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
		System.out.println(testItemTable.getItemList().get(testItemTable.getItemList().size()-1).getISBN());
		// Should return true when a title is successfully created and added to the title list
		//assertTrue(testItemTable.createItem(testISBN));
		// Checking to see if created title is indeed in the list
		//assertEquals(testISBN, testItemTable.getItemList().get(testItemTable.getItemList().size()).getISBN());
		
		//assertEquals(String.valueOf(testCopyNumber+1), testItemTable.getItemList().get(testItemTable.getItemList().size()).getCopyNumber());
	}
	
}
