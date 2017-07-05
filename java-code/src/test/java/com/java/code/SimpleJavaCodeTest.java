package com.java.code;

import org.junit.Test;
import org.junit.Rule;

import org.springframework.boot.test.rule.OutputCapture;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class SimpleJavaCodeTest {
	@Rule
	public OutputCapture capture = new OutputCapture();
	@Test
	public void testHelloWorld(){
		System.out.println("Hello World!");
		assertThat(capture.toString(), containsString("World"));
	}
}
