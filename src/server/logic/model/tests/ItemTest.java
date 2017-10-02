package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Item;

public class ItemTest {

	private Item testItem = new Item(1, "1234567890123");
	
	// Item setters automatically gets tested when instance of Item is created
	// Getters tests both the getters and setters
	
	@Test
	public void test_getItemId() {
		assertEquals(1, testItem.getItemId());
	}
	
	@Test
	public void test_getItemISBN() {
		assertEquals("1234567890123", testItem.getItemISBN());
	}

}
