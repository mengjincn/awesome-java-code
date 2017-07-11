package com.java.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.java.code.configuration.SampleConfigurationProperties;
import com.java.code.flyway.PersonRepository;

@SpringBootApplication
@EnableScheduling
public class JavaCodeApplication implements CommandLineRunner{

	@Autowired
	private PersonRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JavaCodeApplication.class, args);
	}
	@Autowired
	private SampleConfigurationProperties sampleConfigurationProperties;
	
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println("JavaCodeApplication is Running!");
		
		System.out.println("The datesource url: "+sampleConfigurationProperties.getUrl());
		System.err.println(this.repository.findAll());
	}
}
