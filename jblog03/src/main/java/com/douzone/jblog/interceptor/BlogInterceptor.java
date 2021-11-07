package com.douzone.jblog.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;


public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ServletContext servletContext;
	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		

		String urlBlogId = (String)pathVariables.get("blogId");
		BlogVo blog = (BlogVo)servletContext.getAttribute("blogVo");

		
		if(blog == null) {
			BlogVo blogVo = blogService.getContents(urlBlogId);	
			servletContext.setAttribute("blogVo",blogVo);
		}
		// 블로그 변경시 저장된 blogVo 수정
		if(blog.getId()!=urlBlogId) {		
			BlogVo blogVo = blogService.getContents(urlBlogId);	
			servletContext.setAttribute("blogVo",blogVo);
			
		}

		return true;

	}

}
