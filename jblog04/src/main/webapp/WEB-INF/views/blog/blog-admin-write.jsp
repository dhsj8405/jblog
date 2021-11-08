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
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>

<script>
$(function(){
	$("#postTitle").blur(function(){
		var postTitle = $("#postTitle").val();
		postTitle = $.trim( $("#postTitle").val());
		if(postTitle == ''){
			$('#disabled-btn-check-null').show();
			$('#enabled-btn-check-null').hide();
			return;
		}else{
			console.log(postTitle);
			$('#disabled-btn-check-null').hide();
			$('#enabled-btn-check-null').show();
		}

	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blog-header.jsp" />

		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blog-navigation.jsp" />

				<form action="${pageContext.request.contextPath}/blog/adminWrite/${blogVo.id }/" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
			      				<input id = "postTitle" type="text" size="60" name="title">
				      			<select name="categoryNo">
					      			<c:forEach items="${categoryList}"  var="vo">
					      				<option value="${vo.no}">${vo.name }</option>
					      			</c:forEach>
				      			</select>
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td><textarea name="contents"></textarea></td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s">
			      				<input id = "disabled-btn-check-null" type="submit" disabled value="포스트하기">
			      				<input id = "enabled-btn-check-null" type="submit" value="포스트하기" style= 'display: none'>
			      			</td>
			      		</tr>
			      	</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>