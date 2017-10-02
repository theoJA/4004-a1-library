package server.logic.model;

import java.util.Date;

public class Loan {
	int userId;
	String ISBN;
	int copies;
	Date date;
	
	public Loan(int userId, String ISBN, int copies, Date date) {
		this.setUserId(userId);
		this.setISBN(ISBN);
		this.setCopies(copies);
		this.setDate(date);
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
	private void setDate(Date date) {
		this.date = date;
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
	public Date getDate() {
		return date;
	}
}
