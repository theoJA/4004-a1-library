package server.logic.model;

import java.util.Date;

public class Loan {
	int userId;
	String ISBN;
	String copyNumber;
	Date date;
	String renewState;
	
	public Loan(int userId, String ISBN, String copyNumber, Date date, String renewState) {
		this.setUserId(userId);
		this.setISBN(ISBN);
		this.setCopyNumber(copyNumber);
		this.setDate(date);
		this.setRenewState(renewState);
	}
	
	// Loan class setters
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setRenewState(String renewState) {
		this.renewState = renewState;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	public String getISBN() {
		return ISBN;
	}
	public String getCopyNumber() {
		return copyNumber;
	}
	public Date getDate() {
		return date;
	}
	public String getRenewState() {
		return renewState;
	}
}
