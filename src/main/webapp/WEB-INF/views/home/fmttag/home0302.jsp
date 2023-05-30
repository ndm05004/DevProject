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

	<h4>6) type속성을 date로 지정하여 날짜 포맷팅을한다.</h4>
	<p>now : ${now }</p>
	<p>time default : <fmt:formatDate value="${now }" type="time" timeStyle="default"  /></p>
	<p>time short : <fmt:formatDate value="${now }" type="time" timeStyle="short"  /></p>
	<p>time medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"  /></p>
	<p>time long : <fmt:formatDate value="${now }" type="time" timeStyle="long"  /></p>
	<p>time full : <fmt:formatDate value="${now }" type="time" timeStyle="full"  /></p>


</body>
</html>