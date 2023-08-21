<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
  <body>
  	<!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 메인 -->
    <main class="main main_notice" id="main_insert">
      <div class="banner_wrap">
      	<h2>공지사항</h2>
      </div>
      <div class="container">
      	<h2 class="tit_txt">공지사항 입력</h2>
        <form action="/notice/insert.do" method="post" enctype="multipart/form-data">
	        <ul class="form_wrap">
	          <li class="form_item">
	            <label class="form_label" for="noticeSubject">제목</label>
	            <div class="form_field">
	               <input type="text" id="noticeSubject" name="noticeSubject">
	            </div>
	          </li>
	          <li class="form_item">
	            <label class="form_label" for="noticeWriter">작성자</label>
	            <div class="form_field">
	               <input type="text" id="noticeWriter" name="noticeWriter">
	            </div>
	          </li>
	          <li class="form_item">
	            <label class="form_label" for="noticeContent">내용</label>
	            <div class="form_field">
	             <textarea id="noticeContent" name="noticeContent" class="noticeContent"></textarea>
	            </div>
	          </li>
	          <li class="form_item form_file">
	          	 <!-- String으로 받을 수 없고 변환작업이필요하다. -->
	            <label class="form_label" for="noticeFileAdd">첨부파일</label> 
	            	<input type="file" id="noticeFileAdd" class="noticeFileAdd" name="noticeFileAdd">
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