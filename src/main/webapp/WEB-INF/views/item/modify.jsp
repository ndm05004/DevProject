<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Register</h2>
	<form method="post" action="/item/modify" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${item.itemId }"/>
		<input type="hidden" name="pictureUrl" value="${item.pictureUrl }"/>
		<table>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" value="${item.itemName }" name="itemName" id="itemName">
				</td>
			</tr>
			
			<tr>
				<td>가격</td>
				<td>
					<input type="text"  value="${item.price }" name="price" id="price">
				</td>
			</tr>
			
			<tr>
				<td>파일</td>
				<td>
					<img src="/item/display?itemId=${item.itemId }" width="210" height="240"/>
				</td>
			</tr>
			
			<tr>
				<td>파일</td>
				<td>
					<input type="file" name="picture" id="picture"/>
				</td>
			</tr>
			
			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="20" name="description"> ${item.description }</textarea>
				</td>
			</tr>
		</table>
		
		<div>
			<button type="submit" id="btnModify">Modify</button>
			<button type="button" onclick="javascript:location.href='/item/list'" id="btnList">List</button>
		</div>
		
	</form>
		
</body>
</html>