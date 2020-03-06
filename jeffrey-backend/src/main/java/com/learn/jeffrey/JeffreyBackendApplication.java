package com.learn.jeffrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.RestController;

@EnableAuthorizationServer
@SpringBootApplication
@RestController
public class JeffreyBackendApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(JeffreyBackendApplication.class, args);
		System.out.println(ac.getBean("dataSource"));
	}
}
