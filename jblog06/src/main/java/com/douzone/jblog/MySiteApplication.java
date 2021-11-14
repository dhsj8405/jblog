package com.douzone.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration 
public class MySiteApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args);
	}
}
