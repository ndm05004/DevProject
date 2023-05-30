<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>7장 JSP</h3>
	<p>jstl 태그들의 Example ::: c:remove</p>
	<c:set value="${member.userId }" var="memberId"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId}</td>
		</tr>
	</table>
	<hr>
	
	<c:set target="${member }" property="userId" value="hongkildong"/>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${member.userId}</td>
			<td>${memberId}</td>
		</tr>
	</table>
	<hr>
	<c:remove var="memberId"/>	
	
	<p>memberId 변수를 삭제처리함</p>
	<table border="1">
		<tr>
			<td>member.userId</td>
			<td>${memberId}</td>
		</tr>
	</table>
	
	
</body>
</html>