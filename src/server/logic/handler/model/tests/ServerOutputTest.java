package server.logic.handler.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.handler.model.ServerOutput;

public class ServerOutputTest {

	private ServerOutput testServerOutput = new ServerOutput("someOutput"); 
	
	@Test
	public void test_getOutput() {
		assertEquals("someOutput", testServerOutput.getOutput());
	}

}
