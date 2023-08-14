<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <header id="header">
	  <div class="container">
	    <h1 class="logo">
	      <a href="/index.jsp">로고</a>
	    </h1> 
	    <div class="header_util_wrap">
	      <ul class="header_util_item_wrap">
	        <li class="header_util_item"><a href="/member/logout.do">로그아웃</a></li>
	        <li class="header_util_item"><a href="/member/mypage.do?memberId=${memberId}">마이페이지</a></li>
	        <li class="header_util_item"><a href="/notice/insert.do">공지사항</a></li>
	      </ul>
	    </div>
	    <nav class="nav_center">
	      <ul class="nav_item nav_left_area">
	        <li class="nav_menu_txt"><a href="#">컨슈머</a></li>
	        <li class="nav_menu_txt"><a href="#">비즈니스</a></li>
	        <li class="nav_menu_txt"><a href="#">회사소개</a></li>
	        <li class="nav_menu_txt"><a href="#">이벤트</a></li>
	      </ul>
	      <ul class="nav_item nav_right_area">
	        <li class="nav_menu_txt"><a href="#">C Logue</a></li>
	        <li class="nav_menu_txt"><a href="#">고객지원</a></li>
	        <li class="nav_menu_txt"><a href="#">e스토어</a></li>
	        <li class="nav_menu_txt"><a href="#">아카데미</a></li>
	      </ul>
	      <ul class="nav_item nav_button_area">
	        <li class="nav_btn_item"><button>다운로드센터</button></li>
	        <li class="nav_btn_item"><button>정품등록</button></li>
	      </ul>
	    </nav>
	  </div>
 </header>