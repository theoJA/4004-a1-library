package server.logic.handler.model;

public class Output {
	String output;
	int state;
	
	public Output(String output, int state){
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
