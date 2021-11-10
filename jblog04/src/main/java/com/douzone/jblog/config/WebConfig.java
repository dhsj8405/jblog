package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.douzone.config.web.FileUploadConfig;

@Configuration
@ComponentScan({ "com.douzone.jblog.controller", "com.douzone.jblog.exception" })
@Import({   FileUploadConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter {


}
