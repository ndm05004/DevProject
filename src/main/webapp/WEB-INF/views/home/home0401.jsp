<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>4. 표현언어(EL)을 이용하여 출력</h3>
	<p>5) 논리 연산자를 이용한 방법</p>
	<table border="1">
	
		<tr>
			<td>\${coin == 1000 && userId == "hongkd"}</td>
			<td>${coin == 1000 && userId == "hongkd"}</td>
		</tr>
	
		<tr>
			<td>\${coin == 1000 and userId eq "hongkd"}</td>
			<td>${coin == 1000 and userId eq "hongkd"}</td>
		</tr>
	
		<tr>
			<td>\${not empty member && userId eq "hongkd"}</td>
			<td>${not empty member && userId eq "hongkd"}</td>
		</tr>
	
		<tr>
			<td>\${! empty member && userId eq "hongkd"}</td>
			<td>${! empty member and userId eq"hongkd"}</td>
		</tr>
	

	
		
	</table>
	
</body>
</html>