package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example")
public class SoapProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapProviderApplication.class, args);
	}

}
