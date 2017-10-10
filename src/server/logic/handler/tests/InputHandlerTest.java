package server.logic.handler.tests;

import static org.junit.Assert.*;
import server.logic.handler.InputHandler;
import server.logic.handler.model.Output;
import server.logic.handler.model.ServerOutput;

import org.junit.Test;

public class InputHandlerTest {

	private InputHandler testInputHandler = new InputHandler();
	
	@Test
	public void test_processInput() {
		//Output testOutput = new Output("", 0);
		//ServerOutput testServerOutput = new ServerOutput("", 0);
		
		assertEquals("Who Are you?Clerk or User?", testInputHandler.processInput("", 0).getOutput());
		assertEquals(0, testInputHandler.processInput("", 0).getState());
		
		
	}

}
