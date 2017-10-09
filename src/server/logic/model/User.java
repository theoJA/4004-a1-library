package server.logic.model;

public class User {
	int userId;
	String password;
	String username;
	
	public User(int userId, String username, String password) {
		this.setUserId(userId);
		this.setUsername(username);
		this.setPassword(password);
	}
	
	// Making the setter methods private so only the user class can set the member variables
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
}
