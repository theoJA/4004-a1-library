package server.logic.model;

public class Item {
	int itemId;
	String ISBN;
	
	public Item(int itemId, String ISBN) {
		this.setItemId(itemId);
		this.setItemISBN(ISBN);
	}
	
	// Private setters for Item class
	private void setItemId(int itemId) {
		this.itemId = itemId;
	}	
	private void setItemISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	//
	
	public int getItemId() {
		return itemId;
	}
	
	public String getItemISBN() {
		return ISBN;
	}
}
