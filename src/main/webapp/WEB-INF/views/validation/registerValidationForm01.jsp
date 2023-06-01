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
	<h2>유저 등록 화면</h2>
	<hr/>
	<form:form modelAttribute="member" method="post" action="/validation/registerValidationFormResult">
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
				<td>패스워드</td>
				<td>
					<form:input path="password"/>
					<font color="red">
						<form:errors path="password"></form:errors>
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
						
			<tr>
				<td>성별</td>
				<td>
					<form:radiobutton path="gender" value="male" label="Male"/>
					<form:radiobutton path="gender" value="female" label="Female"/>
					<form:radiobutton path="gender" value="other" label="Other"/>
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