package server.logic.model;

public class Item {
	int itemId;
	String ISBN;
	int copies;
	
	public Item(int itemId, String ISBN, int copies) {
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
	private void setCopies(int copies) {
		this.copies = copies;
	}
	//
	
	public int getItemId() {
		return itemId;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public int getCopies() {
		return copies;
	}
}
