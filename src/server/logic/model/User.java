package server.logic.model;

public class User {
	int userId;
	String password;
	
	
	public User(int userId, String password) {
		this.setUserId(userId);
		this.setUserPassword(password);
	}
	
	// making the setter methods private so only the user class can set the id
	private void setUserId(int userId) {
		this.userId = userId;
	}
	private void setUserPassword(String password) {
		this.password = password;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getUserPassword() {
		return password;
	}
}
