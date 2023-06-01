<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>Spring Form</h2>
	<p>1) 모델에 기본 생성자로 생성한 폼 객체를 추가한 후에 화면에 전달한다.</p>
	<!-- 맵의 키는 value로 들어가고 맵의 value는 label로 들어감 -->
	<!-- 
		Map으로 넘긴 데이터를 items 속성에 넣어, Map의 key는 value가 되고 Map의 value는 label의 몸체 값으로 셋팅한다.
	 -->
	<form:form modelAttribute="member" method="post" action="/formtag/checkboxe/result">
		<table>
			<tr>
				<td>개발자여부</td>
				<td>
					<form:checkbox path="developer" value="Y"/>
				</td>
			</tr>
			<tr>
				<td>외국인여부</td>
				<td>
					<form:checkbox path="foreigner" value="true"/>
				</td>
			</tr>
			<tr>
				<td>취미(hobby)</td>
				<td>
					<form:checkbox path="hobby" value="Sports" label="Sports"/>
					<form:checkbox path="hobby" value="Music" label="Music"/>
					<form:checkbox path="hobby" value="Movie" label="Movie"/>
				</td>		
			</tr>
			<tr>
				<td>취미(hobbyArray)</td>
				<td>
					<form:checkbox path="hobbyArray" value="Sports" label="Sports"/>
					<form:checkbox path="hobbyArray" value="Music" label="Music"/>
					<form:checkbox path="hobbyArray" value="Movie" label="Movie"/>
				</td>
			</tr>
			<tr>
				<td>취미(hobbyList)</td>
				<td>
					<form:checkbox path="hobbyList" value="Sports" label="Sports"/>
					<form:checkbox path="hobbyList" value="Music" label="Music"/>
					<form:checkbox path="hobbyList" value="Movie" label="Movie"/>
				</td>
			</tr>
			
		</table>
		<form:button name="register">등록</form:button>
	</form:form>
	
</body>
</html>