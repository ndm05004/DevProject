<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax RegisterForm</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>

	<h1>9. Ajax 방식 요청 처리</h1>

	<h3>Ajax 방식 요청 처리</h3>
	<hr/>
	
	<form>
		<p>userId : <input type="text" name="userId" value="hongkd" id="userId"></p>
		<p>password : <input type="text" name="password" value="1234" id="password"></p>
	</form>
	
	<p>1) URL 경로 상의 경로 변수값을 @PathVariable 어노테이션을 지정하여 문자열 매개변수로 처리한다.</p>
	<button id="registerBtn01">registerBtn01</button>
	
	<p>2) URL 경로 상의 여러개의 경로 변숙밧을 @PathVariable 어노테이션을 지정하여 여러개의 문자열 매개변수로 처리한다.</p>
	<button id="registerBtn02">registerBtn02</button>
	
</body>

<script type="text/javascript">
	
$(function(){
	// 1) URL 경로 상의 경로 변수값을 @PathVariable 어노테이션을 지정하여 문자열 매개변수로 처리한다.
	
	$("#registerBtn01").on("click", function(){
		$.get("/ajax/register/hongkd", function(result){
			console.log("result : " + result);
			if(result === "SUCCESS"){
				alert(result);
			}
		})
	})
	
	//2) URL 경로 상의 여러개의 경로 변수값을 @PathVariable 어노테이션을 지정하여 여러개의 문자열 매개변수로 처리한다.
	$("#registerBtn02").on("click", function(){
		var userId = $("#userId").val();
		var password = $("#password").val();
		var userObject = {
			userId : userId,
			password : password
		}
		
		$.ajax({
			type : "post",
			url : "/ajax/register/" + userId + "/" +password,
			data : JSON.stringify(userObject),
			contentType : "application/json; charset=utf-8",
			success: function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
			
		})
		
	})
	
})
	
</script>

</html>


