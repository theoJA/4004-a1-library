package server.logic.model;

public class Item {
	int itemId;
	String ISBN;
	int copies;
	
	public Item(int itemId, String ISBN, int copies) {
		this.setItemId(itemId);
		this.setItemISBN(ISBN);
		this.setItemCopies(copies);
	}
	
	// Private setters for Item class
	private void setItemId(int itemId) {
		this.itemId = itemId;
	}	
	private void setItemISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	private void setItemCopies(int copies) {
		this.copies = copies;
	}
	//
	
	public int getItemId() {
		return itemId;
	}
	
	public String getItemISBN() {
		return ISBN;
	}
	
	public int getItemCopies() {
		return copies;
	}
}
