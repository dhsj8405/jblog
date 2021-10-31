package com.douzone.jblog.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;

	
	@RequestMapping("")
	public String index(	
			Model model,
			@RequestParam(value = "blogId", required = true, defaultValue = "") String id) {
		
		BlogVo blogVo = blogService.getContents(id);

//		CategoryVo categoryVo = blogService.getContents(id);
		model.addAttribute("blogVo", blogVo);
		
		return "blog/blog-main";
	}
	@Auth
	@RequestMapping("/admin")
	public String manager(
			@AuthUser UserVo authUser, 
			Model model,
			@RequestParam(value = "blogId", required = true, defaultValue = "") String id) {
		System.out.println("123----------------------");
		BlogVo blogVo = blogService.getContents(id);
		
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-basic";
	}
	@Auth
	@RequestMapping("/adminCategory")
	public String category(
			Model model,
			@RequestParam(value = "blogId", required = true, defaultValue = "") String id) {
		BlogVo blogVo = blogService.getContents(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-category";
	}
	@Auth
	@RequestMapping("/adminWrite")
	public String write(
			Model model,
			@RequestParam(value = "blogId", required = true, defaultValue = "") String id) {
		BlogVo blogVo = blogService.getContents(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-write";
	}
//	@RequestMapping("/blog-main/{id}")
//	public String index(@PathVariable("id") String id, Model model) {		
//		BlogVo blogVo = blogService.getContents(id);
//
//		model.addAttribute("blogVo", blogVo);
//		
//
//		return "blog/blog-main";
//	}

	
	
}
