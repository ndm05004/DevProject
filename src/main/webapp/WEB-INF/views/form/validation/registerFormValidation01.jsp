<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Spring Form</h2>
	<form:form modelAttribute="member" method="post" action="/formtag/validation/result">
		<table>
			<tr>
				<td>Id</td>
				<td>
					<form:input path="userId"/>
					<font color="red"> <!-- form:form 태그는 에러, 데이터바인딩 하기가 용이함 -->
						<form:errors path="userId"></form:errors>
					</font>
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>
					<form:input path="userName"/>
					<font color="red">
						<form:errors path="userName"></form:errors>
					</font>
				</td>
			</tr>
			<tr>
				<td>e-mail</td>
				<td>
					<form:input path="email"/>
					<font color="red">
						<form:errors path="email"></form:errors>
					</font>
				</td>
			</tr>
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
</body>
</html>