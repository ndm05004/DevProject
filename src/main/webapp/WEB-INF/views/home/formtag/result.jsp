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
			<td>유져ID</td>
			<td>${member.userId }</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${member.userName }</td>
		</tr>
		<tr>
			<td>email</td>
			<td>${member.email }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>${member.password }</td>
		</tr>
		
		<tr>
			<td>소개</td>
			<td>${member.introduction }</td>
		</tr>
		
		
		<tr>
			<td>취미</td>
			<td>
				<c:forEach items="${member.hobbyList }" var="hobby">
					<c:out value="${hobby }"/><br>
				</c:forEach>
			</td>
			
		</tr>
		
		
	</table>
	
</body>
</html>