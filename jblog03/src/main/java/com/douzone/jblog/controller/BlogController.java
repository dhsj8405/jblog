package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

import ch.qos.logback.core.net.SyslogOutputStream;


@Controller
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping({"/{blogId}","/{blogId}/{categoryNo}","/{blogId}/{categoryNo}/{postNo}"})
	public String index(	
			@PathVariable("blogId") String blogId, 
			@PathVariable("categoryNo") Optional categoryNo,
			@PathVariable("postNo") Optional postNo,
			Model model) {
		Map<String, Object> map = new HashMap<>();
		List<PostVo> postList = null;
		PostVo postVo = null;
		List<CategoryVo> categoryList = null;
		Long selectedCategoryNo=null;
		
		categoryList = blogService.getCategories(blogId);
		if(categoryNo.isPresent()) {
			selectedCategoryNo = Long.parseLong((String)categoryNo.get());
			System.out.println(selectedCategoryNo);
			postList = blogService.getContents(selectedCategoryNo);
		}else {
			selectedCategoryNo = categoryList.get(0).getNo();
			postList= blogService.getContents(categoryList.get(0).getNo());
		}
		
		if(postNo.isPresent()) {
			postVo = blogService.getPostFromList(postList,Integer.parseInt((String)postNo.get()));
		}else {
			if(!postList.isEmpty()) {
				postVo =  postList.get(0);
			}
		}
		
		map.put("selectedCategoryNo",selectedCategoryNo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);
		map.put("post", postVo);
		model.addAttribute("map",map);
		return "blog/blog-main";
	}
	@Auth
	@RequestMapping(value="/adminBasic/{blogId}", method=RequestMethod.GET)
	public String basic(
			@AuthUser UserVo authUser, 
			Model model,
			@PathVariable("blogId") String blogId) {

		return "blog/blog-admin-basic";
	}
	@Auth
	@RequestMapping(value="/adminBasic/{blogId}", method=RequestMethod.POST)
	public String upload(
			BlogVo blogVo,
			@PathVariable("blogId") String blogId,
			@RequestParam(value="logo-file") MultipartFile multipartFile) {
		blogVo.setId(blogId);
		blogService.modifyContents(blogVo,multipartFile);
		servletContext.setAttribute("blogVo", blogVo);

		return "redirect:/blog/adminBasic/"+blogId;
	}
	
	@Auth
	@RequestMapping(value="/adminCategory/{blogId}", method=RequestMethod.GET)
	public String category(
			@PathVariable("blogId") String blogId,
			Model model) {
		List<CategoryVo> categoryList = blogService.getCategories(blogId);
		model.addAttribute("categoryList",categoryList);
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value="/adminCategory/{blogId}", method=RequestMethod.POST)
	public String category(
			@ModelAttribute CategoryVo categoryVo
			) {
		
			blogService.addCategory(categoryVo);
		return "redirect:/blog/adminCategory/"+categoryVo.getBlogId(); 
	}
	

	
	@Auth
	@RequestMapping(value="/categoryDelete/{blogId}/{categoryNo}", method=RequestMethod.GET)
	public String categoryDelete(
			@PathVariable("blogId") String blogId,
			@PathVariable("categoryNo") String categoryNo
			) {
			blogService.removeCategory(categoryNo);
		return "redirect:/blog/adminCategory/" + blogId; 
	}
	
	@Auth 
	@RequestMapping(value="/adminWrite/{blogId}", method=RequestMethod.GET)
	public String write(
			Model model,
			@PathVariable("blogId") String blogId) {
		
		List<CategoryVo> categoryList = blogService.getCategories(blogId);
		model.addAttribute("categoryList",categoryList);

		
		return "blog/blog-admin-write";
	}
	
	@Auth 
	@RequestMapping(value="/adminWrite/{blogId}",method=RequestMethod.POST)
	public String write(
			@ModelAttribute PostVo postVo,
			Model model,
			@PathVariable("blogId") String blogId) {
			blogService.addPost(postVo);
		return "redirect:/blog/adminWrite/" + blogId; 
	}
	
	@Auth
	@RequestMapping(value="/postDelete/{blogId}/{categoryNo}/{postNo}", method=RequestMethod.GET)
	public String postDelete(
			@PathVariable("blogId") String blogId,
			@PathVariable("categoryNo") String categoryNo,
			@PathVariable("postNo") String postNo
			) {
			blogService.removePost(postNo);
		return "redirect:/blog/" + blogId +"/"+ categoryNo; 
	}

	
	
}
