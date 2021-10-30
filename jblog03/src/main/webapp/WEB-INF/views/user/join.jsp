<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	$("#btn-checkid").click(function(){
		var id = $("#id").val();		//파라미터 x : 읽어오기
		if(id == ''){
			return;
		}
		console.log(id);
		$.ajax({ 
			url: "${pageContext.request.contextPath }/user/api/checkid?id="+id,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e){
				console.log(status, e);
			},
			success: function(response){
			console.log(response);
			if(response.result != "success"){
				console.error(response.message);
				return;
			}
			if(response.data){
				alert("존재하는 아이디 입니다. 다른 아이디를 사용하세요");
				$("#id").val("").focus();		// val() 파라미터 o : 쓰기
				return;
			}
			$("#btn-checkid").hide();
			$("#img-checkid").show();

			}
		});
	
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp" />

			<form:form
					modelAttribute="userVo"
					class ="join-form" 
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
				
		<!--<form class="join-form" id="join-form" method="post" action="${pageContext.request.contextPath }/user/join">  -->	
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/>
			<p style ="text-align:left; padding-left:0; color: #f00">
				<form:errors path="name" />
			</p>
			<!--  <input id="name"name="name" type="text" value="">-->
			
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id"/>
			<!-- <input id="blog-id" name="id" type="text"> -->
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="img-checkid" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style ="text-align:left; padding-left:0; color: #f00">
				<form:errors path="id" />
			</p>
			
			
			<label class="block-label" for="password">패스워드</label>
			<form:input path="password" />
			<p style ="text-align:left; padding-left:0; color: #f00">
				<form:errors path="password" />
			</p>
			<!--<input id="password" name="password" type="password" />  -->

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
