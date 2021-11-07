package com.douzone.jblog.security;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)			//어느 시점까지 어노테이션의 메모리를 가져갈지
@Target(PARAMETER)			//필드,메소드,클래스,파라미터등 선언할 수 있는 타입을 설정
public @interface AuthUser {

}
