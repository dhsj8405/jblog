<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


<script>


$(document).on("click", ".delete-img", function(){
	event.preventDefault();
	
	  let categoryNo = $(this).attr("value")
	  let imgObj = $(this);

	  $.ajax({
	    url : "${pageContext.request.contextPath }/category/api/deleteCategory?categoryNo="+ categoryNo,
 	    type: "POST",
	    data: {},
	    dataType: "json",
	    success: function( result ){
	      if( result ){
	        // 삭제 버튼을 누른 row 제거
	        $(imgObj).parent().parent().remove();
	      }
	    },
	    error: function( err ){
	      console.log(err)
	    }
	  })
	});


$(document).on("click", ".form-btn", function(){
	var vo = {};
	console.log("1");
	vo.blogId = $("#blogId").val();
	console.log("2");
	vo.name = $("#name-cat-form").val();
	vo.description = $("#description-cat-form").val();
  $.ajax({
    url: "${pageContext.request.contextPath}/category/api/addCategory",
    type: "post",
    dataType: "json",
    contentType: 'application/json',
   	data: JSON.stringify(vo),

    success: function( response ){
    	removeTable();
    	createNewTable(response.data);
    },
    error: function( err ){
      console.log(err)
    }
  })
});


function removeTable(){
  // 원래 테이블 제거
  $(".admin-cat-body").remove();
  // ajax로 추가했던 테이블 제거
  $(".new-cat-body").remove();
  // 입력 form 초기화
  $("#name-cat-form").val("");
  $("#description-cat-form").val("");
};


function createNewTable(categoryList){
  $newTbody = $("<tbody class='new-cat-body'></tbody>")
  $(".admin-cat").append($newTbody);
  for(i=0 ; i < categoryList.length; i ++){
	  var vo = categoryList[i];
	  if(vo.postCount == null){
		  vo.postCount = '';
	  }
  	  let $cellsOfRow = $(
    	"<tr>" +
	        "<td>" + i + "</td>" +
	        "<td>" + vo.name + "</td>" +
	        "<td>" + vo.postCount + "</td>" +
	        "<td>" + vo.description + "</td>" +
	        "<td>" +
			        "<img src='${pageContext.request.contextPath}/assets/images/delete.jpg'" +
			        "class='delete-img' value='"+ vo.no + "'>" +
	        "</td>" +
        "</tr>");
    $newTbody.append($cellsOfRow);
  }
};

	
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

					<tbody class="admin-cat-body">
						<c:forEach items="${categoryList }" var="vo" varStatus="status">
							<tr>
								<td>${status.index}</td>
								<td>${vo.name }</td>
								<td>${vo.postCount }</td>
								<td>${vo.description}</td>
								<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg" value="${vo.no }" class="delete-img"></td>
							</tr>
						</c:forEach>
					</tbody>

				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<input type="hidden" id="blogId" name="blogId" value="${ blogVo.id}">
				<input type="hidden" id="categoryList" name="categoryList"	value="${ categoryList}">
				<table>
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="name-cat-form" type="text" name="name"
							required></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input id="description-cat-form" type="text"
							name="description" required></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input class="form-btn" type="submit" value="카테고리 추가"></td>
					</tr>
				</table>

				<div id="cat-delete-form" class="delete-form" title="카테고리 삭제" style="display: none">
					<p class="validateTips normal">정말 삭제하시겠습니까?</p>
					<p class="validateTips error" style="display: none;">카테고리가 비어있지 않습니다.</p>
					<form>
					<input type="hidden" id = "hidden-no" value = "">
					</form>
				</div>

				<%-- <h4 class="n-c">새로운 카테고리 추가</h4>
      			 <form action="${pageContext.request.contextPath}/${blogVo.id }/adminCategory" method="post" > 
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
			       </form> --%>
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