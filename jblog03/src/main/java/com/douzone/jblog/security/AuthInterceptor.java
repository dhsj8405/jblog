package com.douzone.jblog.security;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		//2. casting
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		//4. Handler Method에 @Auth가 없으면 Type에 있는지 확인
		if(auth == null) {
			//auth = handlerMethod.
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
		}
		//5. Type과 Method에 @Auth가 적용이 안되어 있는 경우
		if(auth == null) {
			return true;
		}

		//6. @Auth가 적용이 되어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}


		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		//7. 권한(Authorization) 체크를 위해서 @Auth의 role을 가져오기("USER", "ADMIN")
		String role = auth.role();
		
		//8. 권한 체크
		if("USER".equals(role) == false) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;

		}
		Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);		
		String blogId = (String)pathVariables.get("blogId");
		

		if(authUser.getId().equals(blogId) == false) {
			response.sendRedirect(request.getContextPath() );
			return false;
		}
		return true;
	}

}
