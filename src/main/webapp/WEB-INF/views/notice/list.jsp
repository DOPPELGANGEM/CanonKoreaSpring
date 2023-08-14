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
	   <main class="main main_notice" id="main_noticelist">
	   	<div class="banner_wrap">
        	<h2>공지사항</h2>
      	</div>
       	<div class="container">
  			<h2 class="tit_txt">공지사항 목록</h2>
        	<table class="table_wrap">
	      		<thead class="thead_area">
		            <tr>
		              <th>번호</th>
		              <th>제목</th>
		              <th>등록일</th>
		            </tr>
	      		</thead>
          		<tbody class="tbody_area">
		            <c:forEach items="${requestScope.nList}" var="notice"> <!-- ListController에서 가져온 request scope에서꺼내옴 -->
			          <tr>
			            <td>${notice.noticeNo}</td>
			            <td><a href="/notice/detail.do?noticeNo=${notice.noticeNo}">${notice.noticeTitle}</a></td>
			            <td>${notice.regDate}</td>
			          </tr>    
				     </c:forEach>    
          		</tbody>
			</table>
			<ul class="paging_wrap">
			 ${pageNavi}
	      	</ul>
      	</div>
     </main>
	<!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>