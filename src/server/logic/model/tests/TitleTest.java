package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Title;

public class TitleTest {

	private Title testTitle = new Title("1234567890123");
	
	@Test
	public void test_getTitleISBN() {
		assertEquals("1234567890123", testTitle.getTitleISBN());
	}
}
