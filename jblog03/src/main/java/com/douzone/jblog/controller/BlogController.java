package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;


@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	
	@RequestMapping("{id}")
	public String index(@PathVariable("id") String id, Model model) {		
		BlogVo blogVo = blogService.getTitle(id);
//		CategoryVo categoryVo = blogService.getContents(id);
		model.addAttribute("blogVo", blogVo);
		

		return "blog/blog-main";
	}
	
	@RequestMapping("/admin/{id}")
	public String manager(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getTitle(id);

		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-basic";
	}
	@RequestMapping("/category/{id}")
	public String category(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getTitle(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-category";
	}
	
	@RequestMapping("/write/{id}")
	public String write(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getTitle(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-write";
	}
//	@RequestMapping("/blog-main/{id}")
//	public String index(@PathVariable("id") String id, Model model) {		
//		BlogVo blogVo = blogService.getTitle(id);
//
//		model.addAttribute("blogVo", blogVo);
//		
//
//		return "blog/blog-main";
//	}

	
	
}
