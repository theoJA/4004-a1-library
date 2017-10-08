package server.logic.model;

public class Fee {
	int userId;
	int fee;
	
	public Fee(int userId, int fee) {
		this.setUserId(userId);
		this.setFee(fee);
	}
	
	// setters
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	public int getFee() {
		return fee;
	}
}
