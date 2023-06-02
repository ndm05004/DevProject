<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>List</h2>
	<a href="/crud/member/register">등록</a>
	
	
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>${member.userId }</td>
		</tr>

		<tr>
			<td>사용자명</td>
			<td>${member.userName }</td>

		</tr>
		<tr>
			<td>auth - 1</td>
			<td>${member.authList[0].auth }</td>
		</tr>
		<tr>
			<td>auth - 2</td>
			<td>${member.authList[1].auth }</td>
		</tr>
		<tr>
			<td>auth - 3</td>
			<td>${member.authList[2].auth }</td>
		</tr>
	</table>
	<button type="button" id="btnModify">수정</button>
	<button type="button" id="btnRemove">삭제</button>
	<button type="button" id="btnList">목록</button>
	
	<form action="/crud/member/remove" method="post" id="delForm">
		<input type="hidden" name="userNo" value="${member.userNo }">
	</form>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
	
</body>

<script type="text/javascript">

$(function(){
	var delForm =$("#delForm")
	var btnModify =$("#btnModify")
	var btnRemove = $("#btnRemove")
	var btnList = $("#btnList")

	//수정
	btnModify.on("click", function(){
		delForm.attr("action", "/crud/member/modify");
		delForm.attr("method", "get");
		delForm.submit();
	});
	
	// 삭제
	btnRemove.on("click", function(){
		if(confirm("정말로 삭제하시겠습니까?")){
			delForm.submit();
		}
	});
	
	// 목록
	btnList.on("click", function(){
		location.href="/crud/member/list";
	});
	
});






</script>


</html>