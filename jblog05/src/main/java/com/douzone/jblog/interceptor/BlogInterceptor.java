package com.douzone.jblog.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

import ch.qos.logback.core.net.SyslogOutputStream;


public class BlogInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ServletContext servletContext;
	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path = request.getServletPath();
		String pathId ="";

		if(path.equals("/")) {
			return true;
		}
		if(path.indexOf("/", 1) < 0) {
			pathId = path.substring(path.indexOf("/", 0)+1);
		} else {
			pathId = path.substring(path.indexOf("/", 0)+1, path.indexOf("/", 1));
		}
		
		BlogVo blogVo = (BlogVo)servletContext.getAttribute("blogVo");
		// 블로그 변경시 저장된 blogVo 수정
		if(blogVo == null || !(pathId.equals( blogVo.getId() ) )  ) {
			blogVo = blogService.getContents(pathId);	
		}		
		
		// 로고 널값 입력 됐을 때 이전 로고 가져오기  
		if(!(blogVo.getId().isEmpty()) && blogVo.getLogo() == null) {
			blogVo = blogService.getContents(pathId);
		}
		
		servletContext.setAttribute("blogVo",blogVo);

		return true;

	}

}



