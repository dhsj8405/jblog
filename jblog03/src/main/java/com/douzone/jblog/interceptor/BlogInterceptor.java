package com.douzone.jblog.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

import ch.qos.logback.core.net.SyslogOutputStream;


public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ServletContext servletContext;
	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		String blogId = null;
		Long categoryNo =null;
		PostVo post = null;
		BlogVo blogVo = null;
		List<CategoryVo> categoryList = null;
		Map<String, Object> map = new HashMap<>();
		List<PostVo> postList = null;

//		if(pathVariables.get("blogId") != null) {
//			UserVo authUser = null;
//			HttpSession session = request.getSession(true);
//			session.setAttribute("authUser", authUser);
			
//			return true;
//			}
		
		blogId= (String)pathVariables.get("blogId");
//		BlogVo blog = (BlogVo)servletContext.getAttribute("blogVo");
//		if(blog == null) {
		blogVo = blogService.getContents(blogId);
		servletContext.setAttribute("blogVo", blogVo);
//		}
		
		categoryList = blogService.getCategories(blogId);
		
		
		if(pathVariables.get("categoryNo") != null) {
			categoryNo = Long.parseLong((String)pathVariables.get("categoryNo") );
		
		}else {
			
			categoryNo = categoryList.get(0).getNo();			//기본 메인 포스트는 미분류 카테고리의 포스트
			
		}
		
		postList= blogService.getContents(categoryNo);
		

		
		if(pathVariables.get("postNo") != null) {
			int postNo = Integer.parseInt((String)pathVariables.get("postNo"));
			int index = 0;
			
			for(PostVo postVo: postList) {
				if(postVo.getNo() == postNo) {
					post = postList.get(index);
				}
				index++;
			}
						
		}else {
			post = postList.get(0);
		}
		
		map.put("selectedCategoryNo", categoryNo);
		map.put("categoryList", categoryList);
		map.put("postList", postList);
		map.put("post", post);
		servletContext.setAttribute("map",map);

		System.out.println("**********************");
		return true;

	}
	
}
