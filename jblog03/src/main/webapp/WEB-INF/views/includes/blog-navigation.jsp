<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

    
	<ul class="admin-menu">
		<li><a href="${pageContext.request.contextPath}/blog/admin?blogId=${authUser.id}">기본설정</a></li>
		<li><a href="${pageContext.request.contextPath}/blog/adminCategory?blogId=${authUser.id}">카테고리</a></li>
		<li><a href="${pageContext.request.contextPath}/blog/adminWrite?blogId=${authUser.id}">글작성</a></li>
	</ul>    