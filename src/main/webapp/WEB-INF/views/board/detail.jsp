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

			<div>
				<button type="button" onclick="showModifyPage();">수정하기</button>
				<button>삭제하기</button>
				<button type="button" onclick="showNoticeList();">목록으로</button>
			</div>
			<hr>
			
			<!-- 댓글등록 -->
			<form action="/reply/add.do" method="post">
				<input type="text" name="refBoardNo" value="${board.boardNo}"> <!-- 원래히든 -->
				<table>
					<tr>
						<td>
							<textarea rows="3" cols="55" name="replyContent"></textarea>
						</td>
						<td>
							<input type="submit" value="완료">
						</td>
					</tr>
				</table>
			</form>
			
			
			
			
			
	      </div>
	   </main>
	   <!-- 푸터 -->
    	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</body>
</html>