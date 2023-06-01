<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body> <!-- 최우식 + 김민석 -->
	<h3>Result</h3>
	<table border="1">
		<tr>
			<td>id</td>
			<td>이름</td>
		</tr>
		<tr>
			<td>${member.userId }</td>
			<td>${member.userName }</td>
		</tr>

		
		
	</table>
	
</body>
</html>