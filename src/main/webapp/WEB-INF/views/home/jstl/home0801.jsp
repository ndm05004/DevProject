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
	<p>jstl 태그들의 Example ::: c:forTokens</p>	
	
	
	<c:forTokens items="${member.hobby }" delims=", " var="hobby">
		<p>${hobby }</p>
	</c:forTokens>
	
	
</body>
</html>