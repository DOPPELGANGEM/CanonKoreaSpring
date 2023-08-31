<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
<html>
	<body>
		<!-- 헤더 -->
	  	<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	 	 <!-- 메인 -->
		<main class="main main_notice" id="main_detail">
	      <div class="banner_wrap">
	        <h2>게시글 상세</h2>
	      </div>
	      <div class="container">
	      	<h2 class="tit_txt">게시글 상세</h2>
			<ul class="form_wrap">
	          <li class="form_item">
	            <label class="form_label" for="boardTitle">제목</label>
	            <div class="form_field">
	               <input type="text" id="boardTitle" name="boardTitle" value="${board.boardTitle }">
	            </div>
	          </li>
	          <li class="form_item">
	            <label class="form_label" for="boardWriter">작성자</label>
	            <div class="form_field">
	               <input type="text" id="boardWriter" name="boardWriter" value="${board.boardWriter }" readonly>
	            </div>
	          </li>
	          <li class="form_item">
	            <label class="form_label" for="boardContent">내용</label>
	            <div class="form_field">
	             <textarea class="boardContent" id="boardContent" name="boardContent">${board.boardContent}</textarea>
	            </div>
	          </li>
	          <li class="form_item form_file">
		          <label class="form_label" for="boardFileAdd">첨부파일</label> 
	            <input type="file" id="boardFileAdd" class="boardFileAdd" name="boardFileAdd">
		       </li>
	        </ul>
	        
	        <!-- 게시글 수정삭제 -->
<%-- 			${board} --%>
			<c:url var="boardDelUrl" value="/board/delete.do">
				<c:param name="boardNo" value="${board.boardNo}"></c:param>
				<c:param name="boardWriter" value="${board.boardWriter}"></c:param>
			</c:url>
			<c:url var="modifyUrl" value="/board/modify.do">
				<c:param name="boardNo" value="${board.boardNo}"></c:param>
			</c:url>
			<div>
				<c:if test="${board.boardWriter eq memberId }">
					<button type="button" onclick="showModifyPage('${modifyUrl }');">수정하기</button>
					<button type="button" onclick="deleteBoard('${boardDelUrl }');">삭제하기</button>
				</c:if>
				<a href="/board/list.do">목록으로</a>
			</div>
			<hr>
			
			<!-- 댓글등록 -->
			<form action="/reply/add.do" method="post">
				<input type="hidden" name="refBoardNo" value="${board.boardNo}"> <!-- 원래히든 -->
				<table width="500" border="1">
					<tr>
						<td>
							<textarea rows="3" cols="55" name="replyContent"></textarea>
						</td>
						<td>
							<input type="submit" value="등록하기">
						</td>
					</tr>
				</table>
			</form>
			
			<!-- 댓글목록 (수정삭제버튼포함) -->
			<table width="600" border="1">
				<c:forEach var="reply" items="${rList}">
					<tr>
						<td>${reply.replyWriter}</td>
						<td>${reply.replyContent}</td>
						<td>${reply.rCreateDate}</td>
						<td>
							<!-- 기본이벤트없애기 javascript:void(0) this로 foreach문에 있는것을 탐색하기위해-->
							<a href="javascript:void(0)" onclick="showReplyModifyForm(this,'${reply.replyContent}');">수정하기</a> 
							<c:url var="delUrl" value="/reply/delete.do">
								<c:param name="replyNo" value="${reply.replyNo}"></c:param>
								<!-- 내것만지우게 하도록 추가함 -->
								<c:param name="replyWriter" value="${reply.replyWriter}"></c:param>
								<!-- 성공하면 디테일로 가기 위해 필요한 boardNo 셋팅 -->
								<c:param name="refBoardNo" value="${reply.refBoardNo}"></c:param>
							</c:url>
							<a href="javascript:void(0)" onclick="deleteReply('${delUrl }');">삭제하기</a>
							<c:url var="likeUrl" value="/reply/like.do">
								<c:param name="replyNo" value="${reply.replyNo}"></c:param>
								<!-- 내것만지우게 하도록 추가함 -->
								<c:param name="replyWriter" value="${reply.replyWriter}"></c:param>
								<!-- 성공하면 디테일로 가기 위해 필요한 boardNo 셋팅 -->
								<c:param name="refBoardNo" value="${reply.refBoardNo}"></c:param>
							</c:url>
							<a href="javascript:void(0)" onclick="likeReply('${likeUrl }');">좋아요<sup>${reply.refLike}</sup></a>
							<c:url var="unLikeUrl" value="/reply/unlike.do">
								<c:param name="replyNo" value="${reply.replyNo}"></c:param>
								<!-- 내것만지우게 하도록 추가함 -->
								<c:param name="replyWriter" value="${reply.replyWriter}"></c:param>
								<!-- 성공하면 디테일로 가기 위해 필요한 boardNo 셋팅 -->
								<c:param name="refBoardNo" value="${reply.refBoardNo}"></c:param>
							</c:url>
							<a href="javascript:void(0)" onclick="unLikeReply('${unLikeUrl}')">싫어요<sup>${reply.refUnLike}</sup></a>
<!-- 							<button type="button" id="unlike_btn" >싫어요</button> -->
						</td>
					</tr>
					<tr id="replyModifyForm">
						<form action="/reply/update.do" method="post">
							<input type="hidden" name="replyNo" value="${reply.replyNo}">
							<input type="hidden" name="refBoardNo" value="${reply.refBoardNo }">
							<td colspan="3"><input type="text" name="replyContent" value="${reply.replyContent }"></td>
							<td><input type="submit" value="완료"></td>
						</form>

<%-- 						<td colspan="3"><input id="replyContent" type="text" name="replyContent" value="${reply.replyContent }"></td> --%>
<%-- 						<td><input type="button" onclick="replyModify(this,'${reply.replyNo}','${reply.refBoardNo }');" value="완료"></td> --%>
					</tr>
				</c:forEach>
			</table>
	
	
	      </div>
	   </main>
	   <!-- 푸터 -->
    	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    	
	</body>
</html>