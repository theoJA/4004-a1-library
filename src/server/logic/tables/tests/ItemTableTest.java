package server.logic.tables.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Item;
import server.logic.tables.ItemTable;

import java.util.List;

public class ItemTableTest {

	// Assigning the address in memory of instance of TitleTable to a variable
	private ItemTable testItemTable = ItemTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testItemTable, ItemTable.getInstance());
	}
		

}
