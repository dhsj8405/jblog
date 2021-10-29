package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/blog")
public class BlogController {
	@RequestMapping("/blog-main")
	public String index() {
		return "blog/blog-main";
	}
}
