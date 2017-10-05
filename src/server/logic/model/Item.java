package server.logic.model;

public class Item {
	int itemId;
	String ISBN;
	String copyNumber;
	
	public Item(int itemId, String ISBN, String copyNumber) {
		this.setItemId(itemId);
		this.setISBN(ISBN);
		this.setCopyNumber(copyNumber);
	}
	
	// Private setters for Item class
	private void setItemId(int itemId) {
		this.itemId = itemId;
	}	
	private void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	private void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}
	//
	
	public int getItemId() {
		return itemId;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public String getCopyNumber() {
		return copyNumber;
	}
}
