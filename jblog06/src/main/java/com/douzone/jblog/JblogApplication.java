package com.douzone.jblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootConfiguration + @ComponentScan + @EnableAutoConfiguration 
public class JblogApplication {
	public static void main(String[] args) {
		SpringApplication.run(JblogApplication.class, args);
		
		
		/*
		 * 스프링부트는 main 메소드가 선언된 클래스를 기준으로 실행된다
		 * @SpringBootApplication 스프링 부트의 기본적 설정 선언(다양한 어노테이션 선언해줌)
		 *  => 대표적으로  
		 * 		@ComponentScan			 : 어노테이션이 부여된 class들을 자동으로 스캔하여 bean으로 등록해주는 어노테이션 (@Configuration, @Repository, @Service, @Controller, @RestController 포함)
		 * 		@EnableAutoConfiguration : componentScan으로 빈을 등록한 후에 추가적인 Bean들을 등록하는 어노테이션
		 *  
		 */
	}
}
