<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시판 등록</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
		
			<div class="panel-heading">게시판 등록</div>
			<div class="panel-body">
					
					<div class="form-group">
						<label>번호</label><input class="form-control" name="bno" 
							value='<c:out value="${board.bno}"/>' readonly="readonly" >
					</div>
					
					<div class="form-group">
						<label>제목</label><input class="form-control" name="title"
							value='<c:out value="${board.title}"/>' readonly="readonly" >
					</div>
					
					<div class="form-group">
						<label>내용</label><textarea class="form-control" rows="3" name="content"
							readonly="readonly"><c:out value="${board.content}" /></textarea>
					</div>
					
					<div class="form-group">
						<label>작성자</label><input class="form-control" name="writer"
							value='<c:out value="${board.writer}"/>' readonly="readonly" >
					</div>
					
					<button data-oper='modify' class="btn btn-primary">수정</button>
					<button data-oper='list' class="btn btn-danger">목록</button>
					
					<form id ="operForm" action="/board/modify" method="get">
						<input type="hidden" id="bno" name="bno" value='<c:out value="${board.bno}"/>'>
						<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
						<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
					</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="../includes/footer.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		
		var operForm = $("#operForm");
		//Form 에서 버튼중에 data-oper 속성이 modify 로 지정되어있는 버튼 클릭시 
		$("button[data-oper='modify']").on("click",function(e) {
			//attr를 이용해서 action 속성값을 변경 
			operForm.attr("action","/board/modify").submit();
		});
		
		$("button[data-oper='list']").on("click",function(e) {
			//Form에서 속성을 찾는다
			//remove() : Form 에서 해당 태그와 태그값을 한꺼번에 삭제처리
			//empty() : Form에서 해당 태그는 두고 값만 삭제 
			operForm.find("#bno").remove();
			operForm.attr("action","/board/list")
			operForm.submit();
		});
		
	});
</script>