package server.logic.model;

public class Title {
	String ISBN;
	String bookTitle;
	
	public Title(String ISBN, String bookTitle) {
		this.setTitleISBN(ISBN);
		this.setBookTitle(bookTitle);
	}
	
	// Private setters for the Title class
	private void setTitleISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	private void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	//
	
	public String getTitleISBN() {
		return ISBN;
	}
	public String getBookTitle() {
		return bookTitle;
	}
}
