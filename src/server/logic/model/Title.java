package server.logic.model;

public class Title {
	String ISBN;
	
	public Title(String ISBN) {
		this.setTitleISBN(ISBN);
	}
	
	// Private setters for the Title class
	private void setTitleISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	//
	
	public String getTitleISBN() {
		return ISBN;
	}
}
