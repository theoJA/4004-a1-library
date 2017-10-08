package server.logic.model;

public class Fee {
	int userId;
	double fee;
	
	public Fee(int userId, double fee) {
		this.setUserId(userId);
		this.setFee(fee);
	}
	
	// setters
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	//
	
	public int getUserId() {
		return userId;
	}
	public double getFee() {
		return fee;
	}
}
