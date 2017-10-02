package server.logic.model;

public class Loan {
	int userId;
	String ISBN;
	int copies;
	
	public Loan(int userId, String ISBN, int copies) {
		this.setUserId(userId);
		this.setISBN(ISBN);
		this.setCopies(copies);
	}
	
	// Loan class setters
	private void setUserId(int userId) {
		this.userId = userId;
	}
	private void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	private void setCopies(int copies) {
		this.copies = copies;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	public String getISBN() {
		return ISBN;
	}
	public int getCopies() {
		return copies;
	}
}
