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
	   <form action="/board/modify.do" method="post" enctype="multipart/form-data">
    	<!-- 수정확인내용 hidden -->
    	<input type="hidden" name="boardNo" value="${board.boardNo}">
    	<input type="hidden" name="boardFileName" value="${board.boardFileName}">
		<input type="hidden" name="boardFileRename" value="${board.boardFileRename}">
		<input type="hidden" name="boardFilePath"  value="${board.boardFilePath}">
		<input type="hidden" name="boardFileLength" value="${board.boardFileLength}">
		
	    <main class="main main_notice" id="main_noticeModify">
	      <div class="banner_wrap">
	      	<h2>공지사항 수정</h2>
	      </div>
	      <div class="container">
	      	<h2 class="tit_txt">공지사항 수정</h2>
	      	<input type="hidden" name="noticeNo" value="${board.boardNo}"> 
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
<!-- 	          <li class="form_item form_file"> -->
<!-- 		          <label class="form_label" for="boardFileAdd">첨부파일</label>  -->
<!-- 	            <input type="file" id="boardFileAdd" class="boardFileAdd" name="boardFileAdd"> -->
<!-- 		       </li> -->
		       <li class="form_item form_file">
					<label class="form_label" for="boardFileAdd">첨부파일</label>
					<a href="../resources/buploadFiles/${board.boardFileName }" download>${board.boardFileName }</a>
					<input type="file" name="uploadFile">
				</li>
	        </ul>
	       <div class="button_wrap">
	         <input type="submit" value="수정하기" class="btn btn_1">
	         <a href="/notice/list.do" class="btn btn_2">목록으로 이동</a>
	       </div>
	      </div>
	    </main>
	</form>
	   
	   
	   <!-- 푸터 -->
    	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</body>
</html>