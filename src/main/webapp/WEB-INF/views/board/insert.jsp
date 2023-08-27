<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
  <body>
  	<!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 메인 -->
    <main class="main main_board" id="main_insert">
      <div class="banner_wrap">
      	<h2>게시판 공지사항</h2>
      </div>
      <div class="container">
      	<h2 class="tit_txt">게시판 입력</h2>
        <form action="/board/insert.do" method="post" enctype="multipart/form-data">
	        <ul class="form_wrap">
	          <li class="form_item">
	            <label class="form_label" for="boardTitle">제목</label>
	            <div class="form_field">
	               <input type="text" id="boardTitle" name="boardTitle">
	            </div>
	          </li>
	          <li class="form_item">
	            <label class="form_label" for="boardWriter">작성자</label>
	            <div class="form_field">
	               <input type="text" id="boardWriter" name="boardWriter">
	            </div>
	          </li>
	          <li class="form_item">
	            <label class="form_label" for="boardContent">내용</label>
	            <div class="form_field">
	             <textarea id="boardContent" name="boardContent" class="boardContent"></textarea>
	            </div>
	          </li>
	          <li class="form_item form_file">
	            <label class="form_label" for="boardFileAdd">첨부파일</label> 
	            <input type="file" id="boardFileAdd" class="boardFileAdd" name="boardFileAdd">
	          </li>
	        </ul>
	       <div class="button_wrap">
	         <input type="submit" class="btn_submit" value="등록">
	       </div>
        </form>
      </div>
    </main>
    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>