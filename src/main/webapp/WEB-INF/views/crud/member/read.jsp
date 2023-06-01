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
	<a href="/crud/member/modify?userNo=${member.userNo }">modify</a>
	<button type="button" id="btnRemove">삭제</button>
	<button type="button" id="btnList">목록</button>
	
	
</body>
</html>