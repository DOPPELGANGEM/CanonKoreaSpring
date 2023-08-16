<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
  <body>
    <!-- 헤더 -->
    <jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
    <!-- 메인 -->
    <main class="main" id="main_login" >
      <div class="container">
        <h2 class="main_title_txt">로그인</h2>
        <div class="box_wrap">
          <p class="bold_txt"><strong>통합회원으로 전환하시고 <br>더욱 새로워진 캐논코리아를 만나보세요!</strong>
          </p>
          <p class="regular_txt">통합회원전환 이후에도 모든 서비스를 동일하게 이용하실 수 있습니다.</p>
          <div class="btn_wrap">
            <a href="" class="integrated_btn">통합회원전환 ></a>
          </div>
        </div>
        <form id="check_form" action="/member/login.do" method="post">
          <div class="input_wrap">
            <div class="input_field_area">
              <input type="text" placeholder="아이디" name="memberId" id="canon_id" class="canon_input_id">
              <input type="password" placeholder="비밀번호" name="memberPw" id="canon_pw" class="canon_input_pw">
            </div>
            <div class="remember_acc_wrap">
              <ul class="check_field_wrap">
                <li class="check_item">
                  <input type="checkbox" name="rember_check" id="acc_remind_01" class="check_alone">
                  <label for="acc_remind_01">아이디 기억</label>
                </li>
                <li class="check_item">
                  <input type="checkbox" name="rember_check" id="acc_remind_02" class="check_alone">
                  <label for="acc_remind_02">로그인 유지</label>
                </li>
              </ul>
              <ul class="menu_wrap">
                <li class="menu_item"><a href="">아이디 찾기</a></li>
                <li class="menu_item"><a href="">비밀번호 찾기</a></li>
                <li class="menu_item"><a href="/member/register.do">회원가입</a></li>
              </ul>
            </div>
          </div>
          <div class="big_btn_wrap">
            <a href="javascript:void(0)" class="login_btn" onclick="chk_form()">로그인</a>
          </div>
        </form>
        <p class="add_txt">개인정보수정 페이지에서 SNS 로그인 연동 설정을 하시면 재방문 시 간편하게 로그인 가능합니다.</p>
        <!-- 모달창 -->
		<div class="modal_area_wrap">
	     	<div class="modal_area">
	          <div class="modal_txt_area">
	            <h4 class="modal_main_txt">통합 회원 전환 안내</h4>
	            <p class="modal_sub_txt">     
	              <span class="space_txt">안녕하세요, 캐논코리아 주식회사입니다.</span><br>
	              휴대전화번호 변경 또는 개명으로 인하여<br>
	              캐논코리아 회원정보와 본인인증 정보가 다를 경우,<br>
	              통합 회원 전환이 정상적으로 진행되지 않습니다.<br>
	              이런 경우, 아래 통합 회원 전환 신청 페이지에서 신청 부탁드립니다.
	            </p>
	            <div class="modal_link">
	              <a href="/member/register.do" class="modal_link_txt">통합 회원 가입 신청</a>
	            </div>
	          </div>
	          <button type="button" class="modal_btn_close">닫기</button>
	        </div>
	      </div>
	     <div class="modal_mask_cover"></div>	      
      </div>
    </main>
    <!-- 푸터 -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
    <script> 
    	//특정헤더요소제거임시..
	  	const selectTag = document.querySelector(".header_util_wrap");
    	console.log(selectTag);
    	selectTag.remove();
    	
    </script> 
  </body>
</html>