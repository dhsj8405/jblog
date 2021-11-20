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
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>



<script>


var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});
var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-item-template.ejs"
});


 function removeTable(){
  // 원래 테이블 제거
  $(".admin-cat-body").remove();
  
  $("#name-cat-form").val("");
  $("#description-cat-form").val("");
};


/* function createNewTable(categoryList){
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
 */

$(function(){
	// 카테고리 리스트 
	var fetchList = function(){
	
		$.ajax({
			url: '${pageContext.request.contextPath }/category/api/list/' ,
			async: true,
			type: 'get',
			dataType: 'json',
			data: '',
			success: function(response){
				console.log(response);
				console.log("success");
		 	
				if(response.result != "success"){
					console.error(response.message);
					return;
				}

				var html = listTemplate.render(response);
				$(".admin-cat-body").append(html);

			},
			error: function(xhr, status, e){
				console.error(status + ":" + e);
			}
		});
	}

	// 카테고리 추가
 	$(document).on("click", ".form-btn", function(){
 		
		var vo = {};
		vo.blogId = $("#blogId").val();
		vo.name = $("#name-cat-form").val();
		vo.description = $("#description-cat-form").val();
	  	$.ajax({
		    url: "${pageContext.request.contextPath}/category/api/addCategory",
		    type: "post",
		    dataType: "json",
		    contentType: 'application/json',
		   	data: JSON.stringify(vo),
	
		    success: function( response ){
		    	if(response.data == -1){
					$('#checkCategory').html( '카테고리 이름은 중복할 수 없습니다.' );
					$('#name-cat-form').focus();
					return;
		    	}else if(response.data == -2){
		    		$('#checkCategory').html( '카테고리 이름은 필수 항목입니다.' );
		    		$('#name-cat-form').focus();
		    		return;
		    	}
		    	else{
		    		var html = listItemTemplate.render(response);
			    	$(".admin-cat-body").append(html);
			    	$("#name-cat-form").val("");
			    	$("#description-cat-form").val("");
			    	dialogInsert.dialog("open");	
			    	return;
		    	}
		    	
		    	
		    },
		    error: function( err ){
		      console.log(err)
		    }
	  	})
	}); 
	
	// 카테고리 중복 도는 공백입력후 수정시 알림 삭제
 	$('#name-cat-form').change(function(){
		$('#checkCategory').html( ' ' );
	})
	
	//카테고리 중복 및 공백 제목 방지 다이알로그 객체  : 카테고리추가
 	var dialogInsert = $("#dialog-insert-form").dialog({
				autoOpen: false,
				width: 300,
				height: 200,
				modal: true,
				buttons: {
					"확인": function(){
						$(this).dialog('close');
					}
				},
				close: function(){
				}
			});
	// 삭제 다이알로 객체 만들기

	 var dialogDelete = $("#dialog-delete-form").dialog({
		autoOpen: false,
		width: 300,
		height: 200,
		modal: true,
		buttons: {
			"삭제": function(){
				var categoryNo = $("#hidden-categoryNo").val();
				var postCount = $("#hidden-postCount").val();
				if(postCount != ''){
					$(".info-cat-empty").show();
				}
				else{
					$.ajax({
					    url : "${pageContext.request.contextPath }/category/api/deleteCategory?categoryNo="+ categoryNo,
				 	    type: "POST",
					    data: {},
					    dataType: "json",
					    success: function( response ){
					      if( response ){
					    	 console.log(response.data);
							$(".admin-cat-body tr[value=" + response.data + "]").remove();
	
							dialogDelete.dialog('close');
	
					     	}
					    },
					    error: function( err ){
					      console.log(err)
					    }
					})
				}
			},
			"취소": function(){
				$(this).dialog('close');
				$(".info-cat-empty").hide();

			}
		},
		close: function(){
			$("#hidden-no").val("");
			$(".info-cat-empty").hide();

		}
		
	});
 	
	//카테고리 삭제 모달창 불러오기
	$(document).on("click", ".delete-img", function(event){
		event.preventDefault();

		let categoryNo = $(this).attr("value1");
		let postCount = $(this).attr("value2");


		  $("#hidden-categoryNo").val(categoryNo);
		  $("#hidden-postCount").val(postCount);
	      dialogDelete.dialog("open"); 

		});
	//카테고리 추가 모달창 불러오기
	/* $(document).on("click", ".form-btn", function(){
		event.preventDefault();

		let categoryName = $("#name-cat-form").val();
		let categoryDescription = $("#description-cat-form").val();
		let blogId = $("#blogId").val();
		
		
		  $("#hidden-categoryName").val(categoryNo);
		  $("#hidden-categoryDescription").val(postCount);
		  $("#hidden-blogId").val(categoryNo);
		  
	      dialogCheck.dialog("open"); 

		}); */
		
	fetchList();
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
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<tbody class="admin-cat-body"></tbody> 
				<%-- 	<tbody class="admin-cat-body">
						<c:forEach items="${categoryList }" var="vo" varStatus="status">
							<tr>
								<td>${status.index}</td>
								<td>${vo.name }</td>
								<td>${vo.postCount }</td>
								<td>${vo.description}</td>
								<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg" value="${vo.no }" class="delete-img"></td>
							</tr>
						</c:forEach>
					</tbody> --%>

				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<input type="hidden" id="blogId" name="blogId" value="${ blogVo.id}">
				<table>
					<tr>
						<td class="t">카테고리명</td>
						<td><input id="name-cat-form" type="text" name="name" required>
							<div id = "checkCategory"></div>
						</td>
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

				<div id="dialog-delete-form" class="delete-form" title="카테고리 삭제" style="display: none">
					<p class="info-normal">정말 삭제하시겠습니까?</p>
					<p class="info-cat-empty" style="display: none; color:red;">카테고리가 비어있지 않습니다.</p>
					
					<form>
					<input type="hidden" id = "hidden-categoryNo" value = "">
					<input type="hidden" id = "hidden-postCount" value = "">
					
					</form>
				</div>
				
				<div id="dialog-insert-form" class="check-form" title="카테고리 추가" style="display: none">
					<p class="info-normal">카테고리가 추가 되었습니다.</p>
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