package com.java.code.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.code.service.HelloWorldService;

@RestController
public class HelloController {
	@Autowired
	private HelloWorldService helloWorldService;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	@RequestMapping("/welcome")
	public String welcome(){
		return helloWorldService.getHelloMessage();
	} 
	
}
