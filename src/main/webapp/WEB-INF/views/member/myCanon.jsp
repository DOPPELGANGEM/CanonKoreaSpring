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
    <main class="main" id="my_canon_main">
      <div class="container">
        <div class="mycanon_wrap">
          <aside class="my_canon_lnb">
            <h1 class="lnb_txt">마이캐논</h1>
            <ul class="lnb_link_area">
              <li class="lnb_link_item"><a href="/member/logout.do">로그아웃</a></li>
              <li class="lnb_link_item"><a href="/member/mypage.do?memberId=${memberId}">마이페이지</a></li>
              <li class="lnb_link_item"><a href="/notice/insert.do">공지사항 입력</a></li>
              <li class="lnb_link_item"><a href="/notice/list.do">공지사항 목록</a></li>
            </ul>
          </aside>
          <div class="my_canon_content">
            <h1 class="title"><a href="#">${memberName}</a>님 안녕하세요!</h1>
            <div class="user_info">
              <div class="user_level_wrap">
                <ul class="user_level_item_wrap">
                  <li class="user_level_item user_level_item_01">
                    <div class="my_level">
                      <p class="my_level_txt_uptxt">마이레벨</p>
                      <p class="my_level_txt_downtxt">-</p>
                    </div>
                    <div class="level_product">
                      <p class="my_level_txt_uptxt">레벨부여제품</p>
                      <p class="my_level_txt_downtxt">-</p>
                    </div>
                  </li>
                  <li class="user_level_item user_level_item_02">
                    <div class="user_event">
                      <p class="my_level_txt_uptxt">쿠폰</p>
                      <p class="my_level_txt_downtxt"><strong>0</strong></p>
                    </div>
                    <div class="user_event">
                      <p class="my_level_txt_uptxt">이벤트</p>
                      <p class="my_level_txt_downtxt"><strong>0</strong></p>
                    </div>
                    <div class="user_event">
                      <p class="my_level_txt_uptxt">1:1 문의</p>
                      <p class="my_level_txt_downtxt"><strong>0</strong></p>
                    </div>
                  </li>
                </ul>
              </div>
            <div class="coupon_area">
              <div class="coupon_area_wrap">
                <h2>쿠폰</h2>
                <a href="">더보기 ></a>
              </div>
              <table class="coupon_table">
                <tr>
                  <td>번호</td>
                  <td>구분</td>
                  <td>쿠폰명</td>
                  <td>할인액(율)</td>
                  <td>유효기간</td>
                  <td>적용상품</td>
                  <td>디바이스</td>
                </tr>
              </table>
              <div class="coupon_desc_wrap">
                <p class="desc_txt">사용 가능한 쿠폰이 없습니다.</p>
                <div class="link_area">
                  <a href="" class="link_txt">이스토어 쿠폰존 바로가기 ></a>
                </div>
              </div>
            </div>
            <ul class="service_item_wrap">
              <li class="service_item_list"><a href=""><span>서비스 진행 현황 바로가기</span></a></li>
              <li class="service_item_list"><a href=""><span>패카트리지 회수 현황 바로가기</span></a></li>
            </ul>
            </div>
          </div>
        </div>
      </div>
    </main>
    <!-- 푸터 -->
    <footer id="footer">
      <div class="container">
        <ul class="footer_menu_list_wrap">
          <li class="footer_menu_list"><a href="">컨슈머 제품</a></li>
          <li class="footer_menu_list"><a href="">비즈니스 제품</a></li>
          <li class="footer_menu_list"><a href="">라운지</a></li>
          <li class="footer_menu_list"><a href="">제품 구입/지원</a></li>
          <li class="footer_menu_list"><a href="">회사소개</a></li>
        </ul>
      </div>
    </footer>
    <script src="/resources/js/script.js"></script>
  </body>
</html>