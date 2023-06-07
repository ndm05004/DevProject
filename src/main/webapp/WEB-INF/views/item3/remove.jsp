<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Remove</h2>
	<form method="post" action="/item3/remove" enctype="multipart/form-data">
		<input type="hidden" name="itemId" value="${item.itemId }"/>		<table>
			<tr>
				<td>이름</td>
				<td>
					<input type="text" disabled="disabled" value="${item.itemName }" name="itemName" id="itemName">
				</td>
			</tr>
			
			<tr>
				<td>가격</td>
				<td>
					<input type="text" disabled="disabled" value="${item.price }" name="price" id="price">
				</td>
			</tr>
			
			<tr>
				<td>파일</td>
				<td>
					<div class="uploadedList"></div>
				</td>
			</tr>
			

			<tr>
				<td>개요</td>
				<td>
					<textarea rows="10" cols="20" disabled="disabled" name="description"> ${item.description }</textarea>
				</td>
			</tr>
		</table>
		
		<div>
			<button type="submit" id="btnRemove">Remove</button>
			<button type="button" onclick="javascript:location.href='/item3/list'" id="btnList">List</button>
		</div>
		
	</form>
		
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<script type="text/javascript">

$(function(){
	
	var itemId = ${item.itemId};
	console.log("itemId: " + itemId);
	
	$.getJSON("/item3/getAttach/" + itemId, function(list){
		$(list).each(function(){
			console.log("data : " + this);
			
			var data = this;
			var str ="";
			if(checkImageType(data)){ // 이미지면 이미지태그를 이용하여 출력
				str += "<div>";
				str += "<a href='/item3/displayFile?fileName=" + data + "'>";
				str += "<img src='/item3/displayFile?fileName="+getThumbnailName(data)+ "'/>";
				str += "</a>";
				str += "<span>X</span>";
				str += "</div>";
			}else{
				// 이미지가 아닌 일반 파일
				str += "<div>"
				str += "<a href='/item3/displayFile?fileName=" + data + "'>" + getOriginalName(data) + "</a>"; 
				str += "</a>";
				str += "<span>X</span>";
				str += "</div>";
				
			}
			$(".uploadedList").append(str);
		});
	});
	
	
	
	function getThumbnailName(fileName){
		
		var front = fileName.substr(0, 12); // /2023/06/07 폴더
		var end = fileName.substr(12);
		
		console.log("front : " + front);
		console.log("end : " + end);
		
		return front + "s_" + end;
		
	}
	
	function getOriginalName(fileName){
		
		if(checkImageType(fileName)){
			return;
		}
		
		var idx = fileName.indexOf("_") + 1;
		return fileName.substr(idx);
		
	}
	
	
	// 이미지 파일인지 확인하는 함수
	function checkImageType(fileName){
		var pattern = /jpg|gif|png|jepg/;
		return fileName.match(pattern); // 패턴과 일치하면 true(이미지면)
	}

})



</script>
</html>