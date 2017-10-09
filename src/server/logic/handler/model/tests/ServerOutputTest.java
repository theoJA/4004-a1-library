package server.logic.handler.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import server.logic.handler.model.ServerOutput;

public class ServerOutputTest {

	private ServerOutput testServerOutput = new ServerOutput("someOutput", 1); 
	
	@Test
	public void test_getOutput() {
		assertEquals("someOutput", testServerOutput.getOutput());
	}
	
	@Test
	public void test_getState() {
		assertEquals(1, testServerOutput.getState());
	}
	
	@Test
	public void test_setOutput() {
		testServerOutput.setOutput("somenewoutput");
		assertEquals("somenewoutput", testServerOutput.getOutput());
	}

}
