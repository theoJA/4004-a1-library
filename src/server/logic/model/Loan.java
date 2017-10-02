package server.logic.model;

import java.util.Date;

public class Loan {
	int userId;
	String ISBN;
	int copies;
	Date date;
	String renewState;
	
	public Loan(int userId, String ISBN, int copies, Date date, String renewState) {
		this.setUserId(userId);
		this.setISBN(ISBN);
		this.setCopies(copies);
		this.setDate(date);
		this.setRenewState(renewState);
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
	private void setRenewState(String renewState) {
		this.renewState = renewState;
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
	public String getRenewState() {
		return renewState;
	}
}
