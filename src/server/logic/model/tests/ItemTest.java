package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Item;

public class ItemTest {

	private Item testItem = new Item(1);
	
	@Test
	public void test_getItemId() {
		assertEquals(1, testItem.getItemId());
	}

}
