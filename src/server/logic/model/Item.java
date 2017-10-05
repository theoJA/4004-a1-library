package server.logic.model;

public class Item {
	int itemId;
	String ISBN;
	String copies;
	
	public Item(int itemId, String ISBN, String copies) {
		this.setItemId(itemId);
		this.setISBN(ISBN);
		this.setCopies(copies);
	}
	
	// Private setters for Item class
	private void setItemId(int itemId) {
		this.itemId = itemId;
	}	
	private void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	private void setCopies(String copies) {
		this.copies = copies;
	}
	//
	
	public int getItemId() {
		return itemId;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public String getCopies() {
		return copies;
	}
}
