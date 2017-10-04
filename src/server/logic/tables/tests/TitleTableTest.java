package server.logic.tables.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import server.logic.model.Title;
import server.logic.tables.TitleTable;
import java.util.List;

public class TitleTableTest {

	private String testISBN = "9781442616899";
	private String testBookTitle = "Dante's lyric poetry";
	
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
	
	

}
