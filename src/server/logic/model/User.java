package server.logic.model;

public class User {
	int userId;
	String password;
	String username;
	
	public User(int userId, String password, String username) {
		this.setUserId(userId);
		this.setUserPassword(password);
		this.setUserUsername(username);
	}
	
	// Making the setter methods private so only the user class can set the member variables
	private void setUserId(int userId) {
		this.userId = userId;
	}
	private void setUserPassword(String password) {
		this.password = password;
	}
	private void setUserUsername(String username) {
		this.username = username;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	
	public String getUserPassword() {
		return password;
	}
	
	public String getUserUsername() {
		return username;
	}
}
