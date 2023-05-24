<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register ALL Form</title>
</head>
<body>

	<h2>Register All Form</h2>
	<form action="/registerUser" method="post">
		<table border="1">
			<tr>
				<td>유저 ID</td>
				<td>${member.userId}</td>
			</tr>
			<tr>
				<td>패스워드</td>
				<td>${member.password}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${member.userName}</td>
			</tr>
			<tr>
				<td>E-Mail</td>
				<td>${member.email}</td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td>${member.dateOfBirth}</td>
			</tr>
			<tr>
				<td>성별</td>
				<td>
					<c:choose>
						<c:when test="${member.gender eq 'male'}">
							남자
						</c:when>

						<c:when test="${member.gender eq 'female'}">
							여자
						</c:when>
						<c:otherwise>
							나머지
						</c:otherwise>
					</c:choose>
	
				</td>
			</tr>
			<tr>
				<td>개발자 여부</td>	
				<td>			
					<c:choose>
						<c:when test="${member.developer eq 'Y'}">
							개발자
						</c:when>

						<c:otherwise>
							개발자 아님
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>외국인 여부</td>
				<td>
					<c:choose>
						<c:when test="${member.foreigner}">
							외국인
						</c:when>
						<c:otherwise>
							내국인
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<td>국적</td>
				<td>
						<c:choose>
							<c:when test="${member.nationality[0] eq 'korea'}">
								대한민국
							</c:when>
							<c:when test="${member.nationality[0] eq 'germany'}">
								독일
							</c:when>
							<c:when test="${member.nationality[0] eq 'austrailia'}">
								호주
							</c:when>
							<c:when test="${member.nationality[0] eq 'canada'}">
								캐나다	
							</c:when>
							<c:when test="${member.nationality[0] eq 'usa'}">
								미국
							</c:when>
						</c:choose>

				
				</td>
			</tr>
			<tr>
				<td>소유차량</td>

				<td>
					<c:forEach var="cars" items="${member.cars}">
						${cars }
					</c:forEach>
				</td>
			
			</tr>
			<tr>
				<td>취미</td>
				<td>
				<c:forEach var="hobby" items="${member.hobby}">
					
					<c:choose>
						<c:when test="${hobby eq 'sports'}">
							운동
						</c:when>
		
						<c:when test="${hobby eq 'music'}">
							음악
						</c:when>
		
						<c:when test="${hobby eq 'movie'}">
							영화
						</c:when>

					</c:choose>
				
				</c:forEach>
				</td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>${member.address.postCode}</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>${member.address.location}</td>
			</tr>
			<tr>
				<td>카드1 - 번호</td>
				<td>${member.cardList[0].no }</td>
			</tr>
			<tr>
				<td>카드1 - 유효년월</td>
				<td>${member.cardList[0].validMonth }</td>
			</tr>
			<tr>
				<td>카드2 - 번호</td>
				<td>${member.cardList[1].no }</td>
			</tr>
			<tr>
				<td>카드2 - 유효년월</td>
				<td>${member.cardList[1].validMonth }</td>
			</tr>
			<tr>
				<td>소개</td>
				<td>
					<textarea rows="30" cols="10" name="introduction">
					${member.introduction}
					</textarea>
				</td>
			</tr>
			<tr>
				<td>
					<input type="submit" value="뒤로가기"/>

				</td>
			</tr>
		</table>
	</form>
</body>
</html>














