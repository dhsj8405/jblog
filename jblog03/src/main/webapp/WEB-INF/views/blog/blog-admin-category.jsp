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
	$("#categoryName").blur(function(){
		var categoryName = $("#categoryName").val();		//파라미터 x : 읽어오기
		$('#checkCategory').html( ' ' );

		categoryName = $.trim( $("#categoryName").val());
		if(categoryName == ''){
			return;
		}
		$.ajax({ 
			url: "${pageContext.request.contextPath }/blog/api/checkCategory?categoryName="+categoryName,
			type: "get",
			dataType: "json",
			error: function(xhr, status, e){
				console.log(status, e);
			},
			success: function(response){
				if(response.result != "success"){
					console.error(response.message);
					return;
				}
				if(response.data){
					$('#disabled-btn-check-category').show();
					$('#enabled-btn-check-category').hide();
					$('#checkCategory').html( '카테고리 이름은 중복될 수 없습니다.' );
					return;
				}
				$('#checkCategory').html( ' ' );
				$('#disabled-btn-check-category').hide();
				$('#enabled-btn-check-category').show();

			}
		});
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
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
				<c:forEach items="${categoryList }"	var="vo" varStatus="status">	
				
					<tr>
						<td>${status.index}</td>
						<td>${vo.name }</td>
						<td>${vo.postCount }</td>
						<td>${vo.description}</td>
						<td>
							<c:if test="${empty vo.postCount && vo.name != '미분류' }">
								<a href="${pageContext.request.contextPath }/blog/categoryDelete/${blogVo.id }/${vo.no }" class="del" >삭제</a>
							</c:if>
						</td>
					</tr>  	
				</c:forEach>				  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
      			 <form action="${pageContext.request.contextPath}/blog/adminCategory/${blogVo.id }" method="post" > 
			      	<table id="admin-cat-add">
			      		<tr>
			      			<td class="t">카테고리명</td>
			      			<td><input id="categoryName" type="text" name="name">
			      				<div id = "checkCategory"></div>
			      			</td>
			      			
			      		</tr>
			      		<tr>
			      			<td class="t">설명</td>
			      			<td><input type="text" name="description"></td>
			      		</tr>
			      		<tr>
			      			<td class="s">&nbsp;</td>
			      			<!-- <td><input id ="disabled-btn-check-category" type="submit" value="카테고리 추가"></td>-->
			      			<td>
				      			<input id ="disabled-btn-check-category" type="submit" disabled value="카테고리 추가">
			      				<input id ="enabled-btn-check-category" type="submit" value="카테고리 추가" style= 'display: none'>
			      			
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