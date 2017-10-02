package server.logic.model;

public class Title {
	String ISBN;
	String bookTitle;
	
	public Title(String ISBN, String bookTitle) {
		this.setISBN(ISBN);
		this.setBookTitle(bookTitle);
	}
	
	// Private setters for the Title class
	private void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	private void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	//
	
	public String getISBN() {
		return ISBN;
	}
	public String getBookTitle() {
		return bookTitle;
	}
}
