<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>10) dataStyle 속성을 지정하지 않으면 기본값은 default이다.</p>
	<p>dateValue : ${dateValue }</p>
	<fmt:parseDate value="${dateValue }" pattern="yyyy-mm-dd HH:mm:ss" var="date"/>
	<p>date : ${date }</p>
	
</body>
</html>