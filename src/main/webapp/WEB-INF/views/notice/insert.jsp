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
        <ul>
			<li>
				<label>제목</label>
				<input type="text" name="noticeSubject">
			</li>
			<li>
				<label>작성자</label>
				<input type="text" name="noticeWriter">
			</li>
			<li>
				<label>내용</label>
				<textarea rows="4" cols="50" name="noticeContent"></textarea>
			</li>
			<li>
				<label>첨부파일</label>
				<!-- String으로 받을 수 없고 변환작업이필요하다. -->
				<input type="file" name="noticeFileAdd">
			</li>
		</ul>
<!-- 	         <ul class="form_wrap"> -->
<!-- 	           <li class="form_item"> -->
<!-- 	             <label class="form_label" for="noticeSubject">제목</label> -->
<!-- 	             <div class="form_field"> -->
<!-- 	                <input type="text" id="noticeSubject" name="noticeSubject"> -->
<!-- 	             </div> -->
<!-- 	           </li> -->
<!-- 	           <li class="form_item"> -->
<!-- 	             <label class="form_label" for="noticeContent">내용</label> -->
<!-- 	             <div class="form_field"> -->
<!-- 	              <textarea id="noticeContent" name="noticeContent" class="noticeContent"></textarea> -->
<!-- 	             </div> -->
<!-- 	           </li> -->
<!-- 	           <li class="form_item"> -->
<!-- 	             <label class="form_label" for="noticeFileAdd">첨부파일</label> -->
<!-- 	             <div class="form_field"> -->
<!-- 	                <input type="file" id=noticeFileAdd name="noticeFileAdd"> -->
<!-- 	             </div> -->
<!-- 	           </li> -->
<!-- 	         </ul> -->
	         
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