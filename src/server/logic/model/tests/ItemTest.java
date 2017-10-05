package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Item;

public class ItemTest {

	private Item testItem = new Item(1, "1234567890123", "3");
	
	// Item setters automatically gets tested when instance of Item is created
	// Getters tests both the getters and setters
	
	@Test
	public void test_getItemId() {
		assertEquals(1, testItem.getItemId());
	}
	
	@Test
	public void test_getISBN() {
		assertEquals("1234567890123", testItem.getISBN());
	}
	
	@Test
	public void test_getCopies() {
		assertEquals("3", testItem.getCopies());
	}

}
