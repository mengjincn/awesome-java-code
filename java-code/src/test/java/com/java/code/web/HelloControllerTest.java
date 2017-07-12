package com.java.code.web;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.code.service.HelloWorldService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {
	@Value("${name:World}")
	private String name;
	

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private HelloWorldService helloWorldService;

	@Test
	public void testHello() throws Exception {
		ResponseEntity<String> entity = this.restTemplate.getForEntity("/hello", String.class);
		assertThat(entity.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(entity.getBody(), equalTo("Hello World!"));
	}

	@Test
	public void testHelloWorldService() {
		assertThat(helloWorldService.getHelloMessage(), equalTo("Hello " + this.name));
	}
	
}
