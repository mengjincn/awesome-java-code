package com.java.code;

import org.junit.Test;
import org.junit.Rule;

import org.springframework.boot.test.rule.OutputCapture;

import com.java.code.util.MathUtils;

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
	@Test
	public void testIsPrime(){
		assertTrue(MathUtils.isPrime(5));
	}
	
	@Test
	public void testSqrt(){
		assertThat(MathUtils.sqrt(2), is(1.414213562373095));
	}
}
