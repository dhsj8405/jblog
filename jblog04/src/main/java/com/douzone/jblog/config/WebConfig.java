package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.config.web.MvcConfig;
import com.douzone.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({ "com.douzone.jblog.controller", "com.douzone.jblog.exception" })
@Import({ MvcConfig.class, SecurityConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter {
	// SiteInterceptors
//	@Bean
//	public HandlerInterceptor siteInterceptor() {
//		return new SiteInterceptor();
//	}

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(siteInterceptor()).addPathPatterns("/**");
//
//	}

}
