package server.logic.model;

public class Loan {
	int userId;
	String ISBN;
	
	public Loan(int userId, String ISBN) {
		this.setUserId(userId);
		this.setISBN(ISBN);
	}
	
	// Loan class setters
	private void setUserId(int userId) {
		this.userId = userId;
	}
	private void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	public String getISBN() {
		return ISBN;
	}
}
