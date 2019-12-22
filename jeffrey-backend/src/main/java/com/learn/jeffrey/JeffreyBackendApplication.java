package com.learn.jeffrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JeffreyBackendApplication {

	public static void main(String[] args) {
		ApplicationContext c=SpringApplication.run(JeffreyBackendApplication.class, args);
	}
}
