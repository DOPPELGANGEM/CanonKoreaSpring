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
	        	<h2>게시판 목록</h2>
	      	</div>
	       	<div class="container">
	  			<h2 class="tit_txt">게시판 목록</h2>
	        	<table class="table_wrap">
		      		<thead class="thead_area">
			            <tr>
			              	<th>번호</th>
			              	<th style="color:#dc000c;">게시글번호(boardNo)</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성날짜</th>
							<th>첨부파일</th>
							<th>조회수</th>
			            </tr>
		      		</thead>
	          		<tbody class="tbody_area">
	          		<c:forEach var="board" items="${bList }" varStatus="i">
				        	<tr>
								<td>${i.count }</td>
								<c:url var="detailUrl" value="/board/detail.do">
									<c:param name="boardNo" value="${board.boardNo}"></c:param>
								</c:url>
								<td><strong style="color:#dc000c;">${board.boardNo}</strong></td>
								<td><a href="${detailUrl }">${board.boardTitle }</a></td>
								<td>${board.boardWriter }</td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.bCreateDate }"/></td>
								<td>
									<c:if test="${!empty board.boardFileName }">O</c:if>
									<c:if test="${empty board.boardFileName }">X</c:if>
								</td>
								<td><fmt:formatNumber pattern="##,###,###" value="1230000"></fmt:formatNumber></td>
							</tr>  
						</c:forEach>
	          		</tbody>
	          		<tfoot>
						<tr class="nav_area"> 
							<td colspan="7">
								<c:if test="${pInfo.startNavi != 1}">
									<c:url var="prevUrl" value="/board/list.do">
										<c:param name="page" value="${pInfo.startNavi-1}"></c:param>
									</c:url>
									<a href="${prevUrl }" class="nav_link prev_link"><p><span>[이전]</span></p></a></a>
								</c:if>
								<c:forEach begin="${pInfo.startNavi }" end="${pInfo.endNavi }" var="p">
									<c:url var="pageUrl" value="/board/list.do">
										<c:param name="page" value="${p }"></c:param>
									</c:url>
									<a href="${pageUrl }">${p }</a>&nbsp;
								</c:forEach>
								<c:if test="${pInfo.endNavi != naviTotalCount}">
									<c:url var="nextUrl" value="/board/list.do">
										<c:param name="page" value="${pInfo.endNavi+1}"></c:param>
									</c:url>
									<a href="${nextUrl }" class="nav_link next_link"><p><span>[다음]</span></p></a></a>
								</c:if>
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
<!-- 							<td><button>글쓰기</button></td> -->
						</tr>
					</tfoot>
				</table>
	      	</div>
     </main>
	<!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>