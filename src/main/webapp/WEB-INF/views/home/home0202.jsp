<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>4. 표현언어(EL)</h3>
	<p>2) 산술 연산자를 이용한 방법</p>
	<table border="1">
		
		<tr>
			<td>\${coin == 100}</td>
			<td>${coin == 100}</td>
		</tr>
		
		<tr>
			<td>\${coin eq 100}</td>
			<td>${coin eq 100}</td>
		</tr>
		
		<tr>
			<td>\${coin != 100}</td>
			<td>${coin != 100}</td>
		</tr>
		
		<tr>
			<td>\${coin ne 100}</td>
			<td>${coin ne 100}</td>
		</tr>
		
		<tr>
			<td>\${coin &lt; 100}</td>
			<td>${coin < 100}</td>
		</tr>
		
		<tr>
			<td>\${coin &lt; 100}</td>
			<td>${coin lt 100}</td>
		</tr>
		
		<tr>
			<td>\${coin &gt; 100}</td>
			<td>${coin > 100}</td>
		</tr>
		
		<tr>
			<td>\${coin &gt;= 1000}</td>
			<td>${coin >= 1000}</td>
		</tr>
		<tr>
			<td>\${coin &gt;= 1000}</td>
			<td>${coin ge 1000}</td>
		</tr>
		
		<tr>
			<td>\${coin &lt;= 1000}</td>
			<td>${coin <= 1000}</td>
		</tr>
		<tr>
			<td>\${coin &lt;= 1000}</td>
			<td>${coin le 1000}</td>
		</tr>
		
	</table>
	
	
</body>
</html>