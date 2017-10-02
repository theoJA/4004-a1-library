package server.logic.model;

public class Item {
	int itemId;
	
	public Item(int itemId) {
		this.setItemId(itemId);
	}
	
	// Private setters for Item class
	private void setItemId(int itemId) {
		this.itemId = itemId;
	}	
	//
	
	public int getItemId() {
		return itemId;
	}
}
