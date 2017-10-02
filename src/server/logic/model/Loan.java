package server.logic.model;

public class Loan {
	int userId;
	
	public Loan(int userId) {
		this.setUserId(userId);
	}
	
	// Loan class setters
	private void setUserId(int userId) {
		this.userId = userId;
	}
	//
	
	public int getUserId() {
		return userId;
	}
}
