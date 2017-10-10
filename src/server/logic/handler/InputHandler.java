package server.logic.handler;

import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

public class InputHandler {
	
	public static final int WAITING = 0;
	public static final int FINISHWAITING=1;

	OutputHandler outputHandler=new OutputHandler();
	
	public ServerOutput processInput(String input, int state) {
		 String output = "";
		 Output o = new Output("",0);
		 ServerOutput oo = new ServerOutput(output,o.getState());
		 if (state == WAITING) {
	        	output = "Who Are you?Clerk or User?";
	            state = FINISHWAITING;
	            oo.setOutput(output);
	            oo.setState(state);
	     }
	     return oo;
	}
}
