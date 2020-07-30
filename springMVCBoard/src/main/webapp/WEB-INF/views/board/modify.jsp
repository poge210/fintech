<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">게시판 수정</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
		
			<div class="panel-heading">게시판 수정</div>
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
				
				<!-- 2020-07-30 작업 페이지로 가기위한 설정 -->
				<input type="hidden" name="pageNum" value='<c:out value="${cri.pageNum}"/>'>
				<input type="hidden" name="amount" value='<c:out value="${cri.amount}"/>'>
				
					<div class="form-group">
						<label>번호</label><input class="form-control" name="bno" 
							value='<c:out value="${board.bno}"/>' readonly="readonly" >
					</div>
					
					<div class="form-group">
						<label>제목</label><input class="form-control" name="title"
							value='<c:out value="${board.title}"/>'>
					</div>
					
					<div class="form-group">
						<label>내용</label><textarea class="form-control" rows="3" name="content">
						<c:out value="${board.content}" /></textarea>
					</div>
					
					<div class="form-group">
						<label>작성자</label><input class="form-control" name="writer"
							value='<c:out value="${board.writer}"/>' readonly="readonly" >
					</div>
					
					<div class="form-group">
						<label>작성날짜</label><input class="form-control" name="regDate"
							value = '<fmt:formatDate pattern = "yyyy/MM/dd" 
							value = "${board.regdate}"/>' readonly="readonly" >
					</div>
					
					<div class="form-group">
						<label>수정날짜</label><input class="form-control" name="updateDate"
							value = '<fmt:formatDate pattern = "yyyy/MM/dd" 
							value = "${board.updateDate}"/>' readonly="readonly" >
					</div>
					
					<button type="submit" data-oper='modify' class="btn btn-primary" 
						onclick="location.href='/board/modify?bno=<c:out value="${board.bno}"/>'">
						수정</button>
					<button type="submit" data-oper="remove" class="btn btn-danger">삭제</button>
					<button type="submit" data-oper='list' class="btn btn-info" 
						onclick="location.href='/board/list'">목록</button>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="../includes/footer.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
		
		var formObj = $("form");
		
		//버튼 이벤트 처리
		$('button').on("click", function(e) {
			//현재 이벤트 처리하는 기본동작을 중단시키고 원하는 명령문 실행
			e.preventDefault();
			
			var operation = $(this).data("oper");
			
			console.log(operation);
			
			if(operation === "remove") {
				formObj.attr("action", "/board/remove");
			}else if (operation === "list") {
				formObj.attr("action", "/board/list").attr("method","get");
				
				//2020-07-30 추가 
				//특정게시판 상세보기 화면에서 수정모드로 들어가지 않고 바로 목록버튼 클릭시 pageNumTag와 amountTag만 필요하므로
				//이 두항목을 복제해두었다가 empty() 함수를 이용해 태그값 모두를 삭제한후 다시 되돌린다
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amonut']").clone();
				
				formObj.empty();
				formObj.append(pageNumTag);
				formObj.append(amountTag);
			}
			
			//e.preventDefault()선언했기 때문에 별도로 submit 처리
			
			formObj.submit();
		});
	});
</script>