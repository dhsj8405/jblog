package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
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
	@RequestMapping(value="/adminBasic", method=RequestMethod.GET)
	public String basic(
			@AuthUser UserVo authUser, 
			Model model,
			@RequestParam(value = "blogId", required = true, defaultValue = "") String id) {
		BlogVo blogVo = blogService.getContents(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-basic";
	}
	@Auth
	@RequestMapping(value="/adminBasic", method=RequestMethod.POST)
	public String upload(
			BlogVo blogVo,
			@RequestParam(value="blogId", required = true, defaultValue = "") String id,
			@RequestParam(value="logo-file") MultipartFile multipartFile) {
		blogVo.setId(id);
		blogService.modifyContents(blogVo,multipartFile);
		
		return "redirect:/blog/adminBasic?blogId="+id;
	}
	
	@Auth
	@RequestMapping(value="/adminCategory", method=RequestMethod.GET)
	public String category(
			Model model,
			@RequestParam(value = "blogId", required = true, defaultValue = "") String id) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> categoryList = blogService.getCategories(id);

		model.addAttribute("categoryList",categoryList);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value="/adminCategory", method=RequestMethod.POST)
	public String category(
			@ModelAttribute CategoryVo categoryVo
			/*,@RequestParam(value = "blogId", required = true, defaultValue = "") String id*/) {
			
			blogService.addCategory(categoryVo);
			
		return "redirect:/blog/adminCategory?blogId="+categoryVo.getBlogId(); 
	}
	
	@Auth
	@RequestMapping(value="/categoryDelete", method=RequestMethod.GET)
	public String categoryDelete(
						
		return null; 
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
