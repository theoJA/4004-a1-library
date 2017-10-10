package server.logic.tables.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import server.logic.model.Title;
import server.logic.tables.TitleTable;
import java.util.List;

public class TitleTableTest {

	private String testISBN = "9781442616899";
	private String testBookTitle = "Dante's lyric poetry";
	private String newTestISBN = "9999999999999";
	private String newTestBookTitle = "Programming for Dummies"; 
	
	// Assigning the address in memory of instance of TitleTable to a variable
	private TitleTable testTitleTable = TitleTable.getInstance();
	
	@Test
	public void test_getInstance() {
		// Check if UserTable.getInstance() indeed returns the instance of UserTable
		assertEquals(testTitleTable, TitleTable.getInstance());
	}
	
	@Test
	public void test_getTitleList() {
		List<Title> testTitleList = testTitleTable.getTitleList();
		
		assertEquals(testTitleList, testTitleTable.getTitleList());
		assertEquals(testISBN, testTitleTable.getTitleList().get(1).getISBN());
		assertEquals(testBookTitle, testTitleTable.getTitleList().get(1).getBookTitle());
	}
	
	@Test
	public void test_lookupByISBN() {
		assertTrue(testTitleTable.lookup(testISBN));
		assertFalse(testTitleTable.lookup("1234567890123"));
	}
	
	@Test
	public void test_createTitle() {
		// Should return true when a title is successfully created and added to the title list
		assertTrue(testTitleTable.createTitle(newTestISBN, newTestBookTitle));
		// Checking to see if created title is indeed in the list
		assertEquals(newTestISBN, testTitleTable.getTitleList().get(testTitleTable.getTitleList().size() - 1).getISBN());
		assertEquals(newTestBookTitle, testTitleTable.getTitleList().get(testTitleTable.getTitleList().size() - 1).getBookTitle());
				
		// Should return false when given title info exists in the title list
		assertFalse(testTitleTable.createTitle(testISBN, testBookTitle));
	}
	
	@Test
	public void test_delete() {
		assertEquals("Active Loan Exists", testTitleTable.delete("9781442668584"));
		assertEquals("The Title Does Not Exist", testTitleTable.delete("9781442668584909"));
		assertEquals("success", testTitleTable.delete("9781611687910"));
		testTitleTable.createTitle("9781611687910", "Writing for justice");
	}
	
}
