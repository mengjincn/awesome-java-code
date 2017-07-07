package com.java.code.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.java.code.service.HelloWorldService;
@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	@Value("${name:World}")
	private String name;

	public String getHelloMessage() {
		return "Hello " + this.name;
	}
}
