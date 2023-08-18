/* 로그인 */
function chk_form() {
	document.getElementById("check_form").submit();
}

/* 팝업창닫기 */
document.querySelector('.modal_btn_close').addEventListener('click', function(){
  document.querySelector('.modal_mask_cover').style.display = 'none';
  document.querySelector('.modal_area_wrap').style.display = 'none';
});

/* 회원정보 수정 전 체크 로직 */
const updatePw = document.querySelector("#member-pw");
const updateEmail = document.querySelector("#member-member-email");
const updatePhone = document.querySelector("#member-phone");
const updateAddress = document.querySelector("#member-address");
const updateHobby = document.querySelector("#member-hobby");



/* joinMemberShip.jsp 우편번호 API */
function sample4_execDaumPostcode(){
	new daum.Postcode({
		oncomplete : function(data) {
			document.querySelector("#member-address").value="["+data.zonecode +"] "+data.roadAddress
		}
	}).open();
}










