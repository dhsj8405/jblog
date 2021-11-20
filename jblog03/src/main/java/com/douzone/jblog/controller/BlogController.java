package com.douzone.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;


@Controller
@RequestMapping("/{blogId:(?!assets|images).*}")
public class BlogController {
	private static final Log LOGGER = LogFactory.getLog(BlogController.class);
	@Autowired
	private BlogService blogService;
	@Autowired
	ServletContext servletContext;
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping({"","/{categoryNo}","/{categoryNo}/{postNo}"})
	public String index(	
			@PathVariable("blogId") String blogId, 
			@PathVariable("categoryNo") Optional<Long> categoryNo,
			@PathVariable("postNo") Optional<Long> postNo,
			Model model) {

		Map<String, Object> inputMap = new HashMap<>();
		Map<String, Object> outputMap = new HashMap<>();

		inputMap.put("blogId", blogId);
		inputMap.put("categoryNo", categoryNo);	
		inputMap.put("postNo",postNo);
		
		outputMap = blogService.getContents(inputMap);
		
		model.addAttribute("map",outputMap);
		return "blog/blog-main";
	}
	@Auth
	@RequestMapping(value="/adminBasic", method=RequestMethod.GET)
	public String basic(
			@AuthUser UserVo authUser, 
			Model model,
			@PathVariable("blogId") String blogId) {

		return "blog/blog-admin-basic";
	}
	@Auth
	@RequestMapping(value="/adminBasic", method=RequestMethod.POST)
	public String upload(
			BlogVo blogVo,
			@PathVariable("blogId") String blogId,
			@RequestParam(value="logo-file") MultipartFile multipartFile) {
		
		try {
			String logo = fileUploadService.uploadImage(multipartFile);
			blogVo.setLogo(logo);
			
		} catch(FileUploadException e) {
			LOGGER.info("Blog Info Update:" + e);
		}
		
		blogVo.setId(blogId);
		blogService.modifyContents(blogVo);

		servletContext.setAttribute("blogVo", blogVo);
		return "redirect:/"+blogId+"/adminBasic";
	}
	
	@Auth
	@RequestMapping(value="/adminCategory", method=RequestMethod.GET)
	public String category(
			@PathVariable("blogId") String blogId,
			Model model) {
		List<CategoryVo> categoryList = blogService.getCategories(blogId);
		model.addAttribute("categoryList",categoryList);
		return "blog/blog-admin-category";
	}
	
	@Auth
	@RequestMapping(value="/adminCategory", method=RequestMethod.POST)
	public String category(
			@ModelAttribute CategoryVo categoryVo
			) {
		
			blogService.addCategory(categoryVo);
		return "redirect:/"+categoryVo.getBlogId()+"/adminCategory/"; 
	}
	
//	@Auth
//	@RequestMapping(value="/categoryDelete/{categoryNo}", method=RequestMethod.GET)
//	public String categoryDelete(
//			@PathVariable("blogId") String blogId,
//			@PathVariable("categoryNo") String categoryNo
//			) {
//			blogService.removeCategory(categoryNo);
//		return "redirect:/"+ blogId+"/adminCategory" ; 
//	}
	
	@Auth 
	@RequestMapping(value="/adminWrite", method=RequestMethod.GET)
	public String write(
			Model model,
			@PathVariable("blogId") String blogId) {
		
		List<CategoryVo> categoryList = blogService.getCategories(blogId);
		model.addAttribute("categoryList",categoryList);

		
		return "blog/blog-admin-write";
	}
	
	@Auth 
	@RequestMapping(value="/adminWrite",method=RequestMethod.POST)
	public String write(
			@ModelAttribute PostVo postVo,
			Model model,
			@PathVariable("blogId") String blogId) {
			blogService.addPost(postVo);
		return "redirect:/"+blogId+"/adminWrite/" ; 
	}
	
	@Auth
	@RequestMapping(value="/postDelete/{categoryNo}/{postNo}", method=RequestMethod.GET)
	public String postDelete(
			@PathVariable("blogId") String blogId,
			@PathVariable("categoryNo") String categoryNo,
			@PathVariable("postNo") String postNo
			) {
			blogService.removePost(postNo);
		return "redirect:/" + blogId +"/"+ categoryNo; 
	}

	
	
}
