<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<ul class="blog-list">
				<c:forEach items="${map.postList}" var= "postVo">
					<li><a href="${pageContext.request.contextPath}/blog/${blogVo.id}/${map.selectedCategoryNo }/${postVo.no}">${postVo.title }</a> <span>${postVo.regDateTime }</span>	</li>
				</c:forEach>
				
				</ul>
				<div class="blog-content">
					<h4>${map.post.title }</h4>
					<p>
						${map.post.contents }
					<p>
				</div>
				
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
	
				<c:forEach items="${map.categoryList }"	var="categoryVo" varStatus="status">			
			
					<li><a href="${pageContext.request.contextPath}/blog/${blogVo.id}/${categoryVo.no}">${categoryVo.name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>