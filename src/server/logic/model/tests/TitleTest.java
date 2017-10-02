package server.logic.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.model.Title;

public class TitleTest {

	private Title testTitle = new Title("1234567890123", "Hello World!");
	
	@Test
	public void test_getTitleISBN() {
		assertEquals("1234567890123", testTitle.getISBN());
	}
	
	@Test
	public void test_getBookTitle() {
		assertEquals("Hello World!", testTitle.getBookTitle());
	}
	
}
