<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX HOME</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
</head>
<body>
	<h3>AJAX HOME</h3>
	
	<form action="" method="get">
		boardNo : <input type="text" name="boardNo" id="boardNo"/><br> 
		title : <input type="text" name="title" id="title"/><br/>  
		content : <textarea rows="3" cols="5" name="content" id="content"/></textarea>
		<br>
		writer : <input type="text" name="writer" id="writer"><br>
		<input type="button" id="btn" value="전송">
	</form>
	
	<div>
		<button id="putBtn">MODIFY(PUT)</button>
		<button id="putHeaderBtn">MODIFY(PUT WITH HEADER)</button>
		
		<h3>Content Type</h3>
		<button id="postBtn">MODIFY(POST)</button>
		<button id="putJsonBtn">MODIFY(PUT JSON)</button>
		<button id="putXMLBtn">MODIFY(POST XML)</button>
		
		<h3>Accept 매핑</h3>
		<button id="getBtn">READ</button>
		<button id ="getJsonBtn">READ(JSON)</button>
		<button id ="getXmlBtn">READ(xml)</button>
	</div>
	
</body>

<script>

$(function(){
	var putBtn = $("#putBtn");
	var putHeaderBtn = $("#putHeaderBtn");
	
	var postBtn = $("#postBtn");
	var putJsonBtn = $("#putJsonBtn");
	var putXMLBtn = $("#putXMLBtn");
	
	var getBtn 		=$("#getBtn");
	var getJsonBtn	=$("#getJsonBtn");
	var getXmlBtn	=$("#getXmlBtn");
	
	
	putBtn.on("click", function(){
		
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
			boardNo : boardNo,
			title : title,
			content : content,
			writer : writer
		};
		
		$.ajax({
			type : "put",
			url : "/board/" + boardNo,
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
			
		})
	});
	
	putHeaderBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
		}
		
		$.ajax({
			type : "put",
			url : "/board/" + boardNo,
			headers : {
				"X-HTTP-Method-Override" : "PUT"
			},
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log("result: " + result);
				if(result === "SUCCESS"){
					alert(result);
				}
			}
		})
	
	})
	
	
	postBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
		}
		
		$.ajax({
			type : "put",
			url : "/board/" + boardNo,
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log("result : " + result);
				// '==': Equal Operator, '===': Strict Equal Operator로 '==='는 값을 더
				// 엄격하게 비교할 때 사용한다.
				if(result === "SUCCESS"){
					alert(result+"성공");
				}
			}
		});
	});
	
	
	
	putJsonBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var boardObject = {
				boardNo : boardNo,
				title : title,
				content : content,
				writer : writer
		};
	
		$.ajax({
			type : "put",
			url : "/board/" + boardNo,
			data : JSON.stringify(boardObject),
			contentType : "application/json; charset=utf-8",
			success : function(result){
				console.log(result);
				if(result === "SUCCESS"){
					alert(result+"성공");
				}
			}
		});
	});
	
	putXMLBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		var title = $("#title").val();
		var content = $("#content").val();
		var writer = $("#writer").val();
		
		var xmlData = "";
		xmlData += "<Board>";
		xmlData += "<boardNo>" + boardNo + "</boardNo>";
		xmlData += "<title>" + title + "</title>";
		xmlData += "<content>" + content + "</content>";
		xmlData += "<writer>" + writer + "</writer>";
		xmlData += "</Board>";
		
		$.ajax({
			type : "put",
			url : "/board/" + boardNo,
			data : xmlData,
			contentType : "application/xml; charset=utf-8",
			success : function(result){
				if(result === "SUCCESS"){
					alert(result+"성공");
				}
			}
		})
	
	});
	
	
	
	getBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
		
		// GET방식 비동기 HTTP 요청 수행
		$.get("/board/"+boardNo, function(data){
			console.log(data);
			alert(JSON.stringify(data));
		})		
		
	})
	
	
	getJsonBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
	
		$.ajax({
			type : "get",
			url : "/board/" + boardNo,
			headers : {
				"Accept" : "application/json"
			},
			success: function(result){
				console.log(result);
				alert(JSON.stringify(result))
			}
		})
		
	})
	
	getXmlBtn.on("click", function(){
		var boardNo = $("#boardNo").val();
	
		$.ajax({
			type : "get",
			url : "/board/" + boardNo,
			headers : {
				"Accept" : "application/xml"
			},
			success: function(result){
				console.log(result);
				alert(xmlToString(result));
			}
		})
		
	})
	
});

function xmlToString(xmlData){
	
	var xmlString;
	
	if(window.ActiveXObject){
		xmlString = xmlData.xml;
		
	}else{
		xmlString = (new XMLSerializer()).serializeToString(xmlData);
	}
	return xmlString;
	
	
	
	
}


</script>

</html>