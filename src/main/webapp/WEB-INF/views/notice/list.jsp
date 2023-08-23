<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
  <body>
	  <!-- 헤더 -->
	  <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	  <!-- 메인 -->
	   <main class="main main_notice" id="main_noticelist">
		   	<div class="banner_wrap">
	        	<h2>공지사항 목록</h2>
	      	</div>
	       	<div class="container">
	  			<h2 class="tit_txt">공지사항 목록</h2>
	        	<table class="table_wrap">
		      		<thead class="thead_area">
			            <tr>
			              	<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성날짜</th>
							<th>첨부파일</th>
							<th>조회수</th>
			            </tr>
		      		</thead>
	          		<tbody class="tbody_area">
	          		<c:forEach var="notice" items="${nList }" varStatus="i">
				        	<tr>
							   <!-- list 데이터는 items에 넣었고 var에서 설정한 변수로 list 데이터에서 -->
							   <!-- 꺼낸 값을 사용하고 i의 값은 varStatus로 사용 -->
								<td>${i.count }</td>
								<c:url var="detailUrl" value="/notice/detail.do">
									<c:param name="noticeNo" value="${notice.noticeNo}"></c:param>
								</c:url>
								<td><a href="${detailUrl }">${notice.noticeSubject }</a></td>
								<td>${notice.noticeWriter }</td>
								<td>
									<!-- 시 분초 다나옴 ${notice.nCreateDate } --> 
									<fmt:formatDate pattern="yyyy-MM-dd" value="${notice.nCreateDate }"/>
								</td>
								<td>
									<c:if test="${!empty notice.noticeFilename }">O</c:if>
									<c:if test="${empty notice.noticeFilename }">X</c:if>
								</td>
								<td><fmt:formatNumber pattern="##,###,###" value="1230000"></fmt:formatNumber></td>
							</tr>  
						</c:forEach>
	          		</tbody>
	          		<tfoot>
						<tr class="nav_area"> 
							<td colspan="6">
								<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
									<c:url var="pageUrl" value="/notice/list.do">
										<c:param name="page" value="${p }"></c:param>
									</c:url>
									<a href="${pageUrl }">${p }</a>&nbsp;
								</c:forEach>
							</td>
						</tr>
						<tr class="search_area">
							<td colspan="6">
								<form action="/notice/search.do"  method=get>
									<select name="searchCondition" class="search_condition">
										<option value="all">전체</option>
										<option value="writer">작성자</option>
										<option value="title">제목</option>
										<option value="content">내용</option>
									</select>
									<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요" class="search_keyword">
									<input type="submit" value="검색" class="search_btn">
								</form>
							</td>
							<!-- <td><button>글쓰기</button></td> -->
						</tr>
					</tfoot>
				</table>
	      	</div>
     </main>
	<!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>