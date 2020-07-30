<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board List Page
				<button id="regBtn" type="button" class="btn btn-success btn-xs pull-right">
					신규 게시판 등록</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover"
						id="dataList">
						<thead>
							<tr>
								<th>#번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>수정일</th>
							</tr>
						</thead>

						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}" /></td>
								<td><a class="move" href='<c:out value="${board.bno}"/>'>
								<c:out value="${board.title}" /></a></td>
								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.updateDate}" /></td>
							</tr>
						</c:forEach>
					</table>
					
					<!-- 2020-07-30 하단에 페이징 보여주기 -->
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous">
							<a href="${pageMaker.startPage -1}">이전</a></li>
						</c:if>
							
						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
							<li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active':''} ">
							<a href="${num}">${num}</a>
							</li>
						</c:forEach>
						
						<c:if test="${pageMaker.next}">
							<li class="paginate_button next">
							<a href="${pageMaker.endPage +1}">다음</a></li> 
						</c:if>
					</ul>
				</div>
				
				<form id="actionForm" action="/board/list" method="get">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum}">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount}">
				</form>
				
					<!-- 2020-07-29 Modal 추가 -->
					<!-- modal fade : 모달창이 부드럽게 표시
						 aria-labelledby : 모달 제목 지정
					 -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						 aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<!-- 모달창 헤더선언 
									data-dismiss=모달창을 자동으로 close 해줌
								-->
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Modal title</h4>
								</div>
								<div class="modal-body">처리가 완료되었습니다</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" 
									  		data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save changes</button>
								</div>
							</div>
							<!-- /. modal-content -->
						</div>
						<!-- /. modal-dialog -->
					</div>
					<!-- /. modal -->
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
</div>
<%@ include file="../includes/footer.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
		var result = '<c:out value="${result}"/>';
	
		//Sb Admin 에서 제공하는  버튼 등을 작동 안하게 설정 2020-07-29
		$("#dataList").DataTable({
			"paging" : false, //페이징 처리
			"ordering" : false, //정렬순서
			"info" : false, //페이지당 자료 갯수
			"searching" : false //검색창
		});
		
		checkModal(result);
		
		//replaceState :현재 주소창의 내용 변경
		//매개변수 
		//(1)stateObj:replaceState에 전달된 history 항목과 연관된 자바스크립트 객체
		//(2)title
		//(3)url 
		//history항목의 url 지정
		
		//history 속성
		//이전 페이지로 이동 : window.history.back()
		//다음 페이지로 이동 : window.history.forward()
		//해당 페이지로 이동 : window.history.go(index번호)
		
		//뒤로가기시 문제점 처리 보완
		history.replaceState({},null,null);
		
		function checkModal(result) {
			
			if (result === '' || history.state) {
				return;
			}
			//등록한 게시물 모달창에 보여주기
			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다");
			}
		
		//모달창 보여주기
		$("#myModal").modal("show");
		
		}
		//신규 게시판 등록 버튼 클릭 처리 2020-07-29
		// on : 이벤트 처리 
		// function() : 무기명 함수 
		// self.location : url 지정
		$("#regBtn").on("click", function() {
			self.location = "/board/register";
		});
		
		var actionForm = $("#actionForm");
		
		$(".paginate_button a").on("click", function(e) {
			
			//html 본래의 이벤트처리 실행을 막고 별도처리
			e.preventDefault();
			
			console.log("click");
			
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});
		
		//특정게시물 상세보기 처리 20-07-30
		$(".move").on("click", function(e) {
			
			e.preventDefault();
			actionForm.append("<input type='hidden' name='bno' value='"+$(this).attr("href")+"'>");
			actionForm.attr("action", "/board/get");
			actionForm.submit();
			
		});
	
	});
</script>