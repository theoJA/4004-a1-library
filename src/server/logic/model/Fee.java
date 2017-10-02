package server.logic.model;

public class Fee {
	int userId;
	int fee;
	
	public Fee(int userId) {
		this.setUserId(userId);
	}
	
	// setters
	private void setUserId(int userId) {
		this.userId = userId;
	}
	//
	
	public int getUserId() {
		return userId;
	}
}
