package server.logic.handler.model;

public class ServerOutput {
	String output;
	int state;
	
	public ServerOutput(String output, int state){
		this.output=output;
		this.state=state;
	}
	
	public String getOutput() {
		return output;
	}
	
	public int getState() {
		return state;
	}
	
}