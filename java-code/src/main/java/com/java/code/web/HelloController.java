package com.java.code.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
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
	public String welcome(HttpServletRequest request) {
		printCookies(request);
		return helloWorldService.getHelloMessage();
	}

	@GetMapping("/cookie")
	public String testCookie(HttpServletRequest request, HttpServletResponse response) {
		printCookies(request);
		response.addCookie(new Cookie("id", "1"));
		return "cookie";
	}

	private void printCookies(HttpServletRequest request) {
		Cookie[] cs = request.getCookies();
		if(cs==null) return;
		for (Cookie cookie : cs) {			
			System.out.println(cookie.getName() + ": " + cookie.getValue());
		}
	}

}
