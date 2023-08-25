/* 팝업창닫기 */
const popClose = document.querySelector(".modal_btn_close");
popClose.addEventListener('click', function(){
  document.querySelector('.modal_mask_cover').style.display = 'none';
  document.querySelector('.modal_area_wrap').style.display = 'none';
});


/* 로그인 유효성 검사 */
const loginIdInput = document.querySelector(".canon_input_id");
const loginPwInput = document.querySelector(".canon_input_pw");
const loginBtn = document.querySelector(".login_btn");
loginBtn.addEventListener("click", function(){
	//alert("clicked");
	if(loginIdInput.value === "" && loginPwInput.value === "" ) {
		alert("id와 pw 값 모두 입력해주세요!!");
	} else {
		alert("로그인 성공!");
	}
});


/* joinMemberShip.jsp 우편번호 API */
function sample4_execDaumPostcode(){
	new daum.Postcode({
		oncomplete : function(data) {
			document.querySelector("#member-address").value="["+data.zonecode +"] "+data.roadAddress
		}
	}).open();
}












