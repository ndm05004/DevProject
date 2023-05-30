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
	<p>jstl 태그들의 Example ::: c:catch</p>
	<!-- 
		EL에서 발생한 에러가 아니라, 자바식에서 발생한 에러 정보이기 때문에 var속성에 담긴
		에러 정보를 확인할 수 있다.
	 -->
	<%
		String[] hobbyArray = {"Music", "Movie"};
	%>
	
	<c:catch var ="ex">
		<%=hobbyArray[3] %>
	</c:catch>
	<p>
		<c:if test="${ex != null }">
			${ex }
		</c:if>
	</p>
	
</body>
</html>