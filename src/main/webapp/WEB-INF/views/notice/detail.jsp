<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
  <body>
  	<!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 메인 -->
	<main class="main main_notice" id="main_detail">
      <div class="banner_wrap">
        <h2>공지사항 상세</h2>
      </div>
      <div class="container">
      	<h2 class="tit_txt">공지사항 상세</h2>
		<ul class="form_wrap">
          <li class="form_item">
            <label class="form_label" for="noticeSubject">제목</label>
            <div class="form_field">
               <input type="text" id="noticeSubject" name="noticeSubject" value="${notice.noticeSubject }">
            </div>
          </li>
          <li class="form_item">
            <label class="form_label" for="noticeWriter">작성자</label>
            <div class="form_field">
               <input type="text" id="noticeWriter" name="noticeWriter" value="${notice.noticeWriter }" readonly>
            </div>
          </li>
          <li class="form_item">
            <label class="form_label" for="noticeContent">내용</label>
            <div class="form_field">
             <textarea class="noticeContent" id="noticeContent" name="noticeContent">${notice.noticeContent}</textarea>
            </div>
          </li>
          <li class="form_item form_file">
	          <label class="form_label" for="noticeFileAdd">첨부파일</label> 
	          <a href="../resources/noticeFileUpload/${notice.noticeFileRename }" download>${notice.noticeFilename}</a>
<%-- 	          <img alt="첨부파일" src="../resources/noticeFileUpload/${notice.noticeFilename}"> --%>
	       </li>
        </ul>
       	<div class="button_wrap">
	      	<a class="btn btn_1" href="/notice/modify.do?noticeNo=${notice.noticeNo}">수정페이지 이동</a><br>
        	<a class="btn btn_2" href="/notice/list.do">목록으로 이동</a>
   		</div>
      </div>
   </main>
    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>