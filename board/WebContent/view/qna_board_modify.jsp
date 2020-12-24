<%@page import="java.net.URLEncoder"%>
<%@page import="domain.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../include/header.jsp"%>
<!-- Main content -->
<section class="content">
	<div class="box box-primary">
		<div class="box-header">
			<h3 class="box-title">Board Modify</h3>
		</div>
		<div style="height:20px"></div>
		<form action="qUpdate.do" method="post" role="form" enctype="multipart/form-data">
			<div class="box-body">
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">글쓴이</label>
					<div class="col-sm-10" >
					<input type="text" name="name" size="10" class="form-control"	maxlength='10' value="${vo.name }" readonly>
					</div>
				</div>
				<div class="form-group row">
					<label for="title" class="col-sm-2 col-form-label" >제목</label>
					<div class="col-sm-10">
						<input type="text" name="title" size="50" class="form-control"	maxlength='100' value="${vo.title }">
					</div>
				</div>
				<div class="form-group row">
					<label for="content" class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<textarea name='content' cols='60' class="form-control" rows='15'>${vo.content }</textarea>
					</div>
				</div>
				<div class="form-group row">
					<label for="name" class="col-sm-2 col-form-label">비밀번호</label>
					<div class="col-sm-10">
						<input type="password" name="password" class="form-control" size="10" maxlength='10'>
					</div>
				</div>
				<div class="form-group row">
					<label for="filename" class="col-sm-2 col-form-label">파일첨부</label>
					<div class="col-sm-10">
					<% //EL 바로 못 가져와서 기존 방법대로 리퀘슽,GET 해서 변수로 가져옴
						BoardVO board = (BoardVO)request.getAttribute("vo");	
						String attachFullName = board.getAttach();
						
						if(attachFullName!=null) {
							//out.print("<a href='view/download.jsp?fileName="+URLEncoder.encode(attachFullName, "utf-8")+"'>");
							// 그냥 할라면 EL에 a링크에 다운로드 페이지 연결하고 파일이름만 주면 됐는데++인코딩 작업 한번 거친것
							out.print("<a href='view/download.jsp?fileName="+URLEncoder.encode(attachFullName, "utf-8")+"'>");
							out.print(attachFullName); //파일명 준거
							out.print("</a> ");
						}else {
							out.print("<input type='file' name='attach'>");
							out.print("<small class='text-muted'>(파일크기 : 2MB)</small>");
						}
					 %>					
					</div>
				</div>
				<div style="height:20px"></div>
				<div class="box-footer text-center">
					<button type="submit" class="btn btn-primary">수정</button>
					<button type="button" class="btn btn-danger" onclick="history.back()">취소</button>
				</div>
				<div style="height:20px"></div>
			</div>
			<input type="hidden" name="bno" value="${vo.bno }" />
		</form>
	</div>
</section>
<script>
$(function() {
	let inputFile = $("input[name='attach']");
	
	$(inputFile).change(function() {
		let files = inputFile[0].files;
		let fileName = files[0].name;
		let fileSize = files[0].size;
		
		//확장자 제한(txt|jpg|png|gif)
		var reg = /(.*?)\.(txt|jpg|png|gif)$/;
		
		//사이즈 제한
		var maxSize = 2097152;
		
		if(!reg.test(fileName)) {
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		if(fileSize > maxSize) { //파일사이즈가 max사이즈를 넘어가면
			alert("파일 사이즈를 확인해 주세요.");
			return false;			
		}
		return true;
	})
})
</script>
<%@include file="../include/footer.jsp"%>
