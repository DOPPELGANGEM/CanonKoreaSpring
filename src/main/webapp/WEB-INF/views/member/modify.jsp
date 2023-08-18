<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
  <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
  <body>
    <!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 메인 -->
    <main class="main" id="main_modify">
     <div class="container">
       <form action="/member/update.do" method="post">
	       <h2>회원정보수정</h2>
	       <ul>
	         <li class="info_line">
	           <label for="member-id">아이디</label>
	           <input type="text" id="member-id" name="memberId" value="${member.memberId}" readonly>
	         </li>
	         <li class="info_line">
	           <label for="member-pw">비밀번호</label>
	           <input type="password" id="member-pw" name="memberPw">
	         </li>
	         <li class="info_line">
	           <label for="member-name">이름</label>
	           <input type="text" id="member-name" name="memberName" value="${member.memberName}" readonly>
	         </li>
	         <li class="info_line">
	           <label for="member-age">나이</label>
	           <input type="text" id="member-age" name="memberAge" value="${member.memberAge}" readonly>
	         </li>
	         <li class="info_line info_gender">
	 	          <label for="member-gender">성별 :</label>
	 	          <div class="check_radio">
	 	           남<input type="radio" id="member-gender" name="memberGender" value="M" <c:if test="${member.memberGender eq 'M' }">checked</c:if>>
	 	           여<input type="radio" id="" name="memberGender" value="F" <c:if test="${member.memberGender eq 'F' }">checked</c:if>>
	 	          </div>				
	          </li>
	         <li class="info_line">
	           <label for="member-email">이메일</label>
	           <input type="text" id="member-email" name="memberEmail" value="${ member.memberEmail }">
	         </li>
	         <li class="info_line">
	           <label for="member-phone">전화번호</label>
	           <input type="text" id="member-phone" name="memberPhone" value="${ member.memberPhone }">
	         </li> 
	         <li class="info_line">
	           <label for="member-address">주소</label>
	           <input type="text" id="member-address" name="memberAddress" value="${ member.memberAddress }">
	           <input type="button" onclick="sample4_execDaumPostcode();" value="우편번호찾기">
	         </li>
	         <li class="info_line">
	           <label for="member-hobby">취미</label>
	           <input type="text" id="member-hobby" name="memberHobby" value="${ member.memberHobby }">
	         </li>
	         <li class="info_line">
	           <label for="member-hobby">가입날짜</label>
	           <input type="text" id="member-hobby" name="memberDate" value="${ member.memberDate }" readonly>
	         </li>
	       </ul>
	       <div class="btn_wrap">
    		<button class="btn_update">수정하기</button>
    		<a href="/member/delete.do?memberId=${member.memberId}" class="btn_delete">탈퇴하기</a>
      	   </div>
         </form>
	  </div>
	</main>
    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
  </body>
</html>