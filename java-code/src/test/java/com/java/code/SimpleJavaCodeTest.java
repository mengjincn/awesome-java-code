package com.java.code;

import org.junit.Test;
import org.junit.Rule;

import org.springframework.boot.test.rule.OutputCapture;

import com.google.common.base.Charsets;
import com.google.common.io.BaseEncoding;
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
	@Test
	public void testBaseEncoding(){
		BaseEncoding baseEncoding = BaseEncoding.base64();
		String hello = "Hello World";
		String encodeHello = baseEncoding.encode(hello.getBytes(Charsets.UTF_8));
		assertThat(encodeHello,equalTo("SGVsbG8gV29ybGQ="));
		String decodeHello = new String(baseEncoding.decode(encodeHello),Charsets.UTF_8);
		assertThat(decodeHello,equalTo(hello));
	}
}
