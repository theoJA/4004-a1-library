package server.logic.handler.model.tests;

import static org.junit.Assert.*;
import server.logic.handler.model.Output;

import org.junit.Test;

public class OutputTest {

	private Output testOutput = new Output("newOutput",1);
	
	@Test
	public void test_getOutput() {
		assertEquals("newOutput", testOutput.getOutput());
	}

	@Test
	public void test_getState() {
		assertEquals(1, testOutput.getState());
	}
	
	@Test
	public void test_setOutput() {
		testOutput.setOutput("freshOutput");
		assertEquals("freshOutput", testOutput.getOutput());
	}
	
	@Test
	public void test_setState() {
		testOutput.setState(2);
		assertEquals(2, testOutput.getState());
	}
}
