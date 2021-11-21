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
					<c:if test="${empty map.postList}">
						<li>포스트가 없습니다.</li>
					</c:if>
					<c:if test="${ map.postList != null }">
						<c:forEach items="${map.postList}" var= "postVo">
							<li><a href="${pageContext.request.contextPath}/${blogVo.id}/${map.selectedCategoryNo }/${postVo.no}">${postVo.title }</a> <span>${postVo.regDateTime }</span>	</li>
						</c:forEach>	
					</c:if>	
				</ul>
				<div class="blog-content">

					<h5>${map.post.categoryName }</h5>
					<c:if test="${empty map.post}">
						<h4>포스트를 입력해주세요</h4>
						<p>
							포스트 내용을 입력해주세요
						<p>
					</c:if>
					<c:if test="${map.post != null}">
						
						<h4>${map.post.title }
							<c:if test="${authUser.id == blogVo.id }">
								<a href="${pageContext.request.contextPath }/${blogVo.id }/postDelete/${map.post.categoryNo }/${map.post.no }" class="post-del" >삭제</a>
							</c:if>
						</h4>
						
						<p>
							${map.post.contents }
						<p>
					</c:if>
					
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
			
					<li><a href="${pageContext.request.contextPath}/${blogVo.id}/${categoryVo.no}">${categoryVo.name}</a></li>
				</c:forEach>
			</ul>
			<c:import url="/WEB-INF/views/includes/blog-footer.jsp" />
		</div>
		
		
		
	</div>
</body>
</html>