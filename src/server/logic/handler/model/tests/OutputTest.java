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
		assertEquals("newOutput", testOutput.getOutput());
	}
}
