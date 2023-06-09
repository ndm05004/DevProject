<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- <script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
 --><section class="content">
	<div class="container-fluid">
		<div class="row" style="justify-content:center;">
			<div class="col-md-6">
				<div class="card card-primary">
					<div class="card-header">
						<h3 class="card-title">공지사항 등록</h3>
					</div>
					<!-- 
					파일 업로드
					1) method는 꼭 post
					2) enctype="multipart/form-data"
					3) <input type="file" name="VO속성명">
					
					 -->
					 <!-- 
					 	요청URI : /notice/generalFormPost
					 	요청방식 : post
					 	요청 파라미터 : {boTitle = 제목입니다, boContent=내용입니다. boWriter=작성자, boFile=파일객체}
					  -->
					<form id="frm" name="frm" action="/notice/generalFormPost" method="post" enctype="multipart/form-data" >
						<div class="card-body">
							<div class="form-group">
								<label for="boTitle">제목</label> <input
									type="text" class="form-control" id="boTitle" name="boTitle"
									placeholder="제목을 입력해주세요" required >
							</div>
							<div class="form-group">
								<label for="boContent">내용</label> 
								<textarea rows="3" id="boContent" name="boContent" cols="5"></textarea>
									
							</div>
							<div class="form-group">
								<label for="boWriter">작성자</label> <input
									type="text" class="form-control" id="boWriter" name="boWriter"
									placeholder="작성자를 입력해주세요">
							</div>
							<div class="form-group">
								<label for="boFile">File input</label>
								<div class="input-group">
									<div class="custom-file"> <!-- 파일 여러개 multiple -->
										<input type="file" class="custom-file-input"
											id="boFile" name="boFile" multiple> <label
											class="custom-file-label" for="exampleInputFile">Choose
											file</label>
									</div>
								</div>
							</div>
							
						</div>

						<div class="card-footer">
							<button type="submit" class="btn btn-primary">등록</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
</section>

<script>
	// textarea의 name값을 넣는다.
	CKEDITOR.replace("boContent");
	
	

</script>

