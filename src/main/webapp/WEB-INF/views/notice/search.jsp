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
	<main class="main main_notice" id="main_searchlist">
		   	<div class="banner_wrap">
	        	<h2>공지사항 찾기</h2>
	      	</div>
	       	<div class="container">
	  			<h2 class="tit_txt">공지사항 찾기</h2>
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
	          		<c:forEach var="notice" items="${sList }" varStatus="i">
				        	<tr>
								<td>${i.count }</td>
								<td>${notice.noticeSubject }</td>
								<td>${notice.noticeWriter }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.nCreateDate }"/></td>
								<td>
									<c:if test="${!empty notice.noticeFilename }">O</c:if>
									<c:if test="${empty notice.noticeFilename }">X</c:if>
								</td>
								<td><fmt:formatNumber pattern="##,###,###" value="1230000"></fmt:formatNumber></td>
							</tr>  
						</c:forEach>
	          		</tbody>
	          		<tfoot>
						<tr> 
							<td colspan="5">
								<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
									<c:url var="pageUrl" value="/notice/list.do">
										<c:param name="page" value="${p }"></c:param>
									</c:url>
									<a href="${pageUrl }">${p }</a>&nbsp;
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<form action="/notice/search.do"  method=get>
									<select name="searchCondition">
										<option value="all">전체</option>
										<option value="writer">작성자</option>
										<option value="title">제목</option>
										<option value="content">내용</option>
									</select>
									<input type="text" name="searchKeyword" placeholder="검색어를 입력하세요">
									<input type="submit" value="검색">
								</form>
							</td>
						</tr>
					</tfoot>
				</table>
	      	</div>
     </main>
    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
	</body>
</html>