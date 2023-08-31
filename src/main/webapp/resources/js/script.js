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
		alert("로그인 확인중..");
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

/* 게시판 수정 */
function showModifyPage(modifyUrl) {
	alert(modifyUrl);
	//location.href = modifyUrl;
}

/* 게시판 삭제 */
function deleteBoard(boardDelUrl){
	//alert(boardDelUrl);
	location.href = boardDelUrl;
}

/* 댓글 수정 */
function showReplyModifyForm(obj, replyContent) {
	//alert("clicked");
	//console.log(trTagDisplay.parentElement.parentElement.nextElementSibling);
	const modifyFormTag = obj.parentElement.parentElement.nextElementSibling;
	console.log(modifyFormTag);
	modifyFormTag.style.display = "block";
}

function replyModify(obj, replyNo, refBoardNo){
	//alert("clicked");
	//const modifyInputValue = obj.parentElement.previousElementSibling.childNodes[0].value;
	//modifyInputValue.action = "/reply/update.do";
	//modifyInputValue.method = "post";
	//modifyInputValue.submit();
}

/* 댓글 삭제 */
function deleteReply(url){
	//alert(url);
	location.href= url;
}

/* 댓글 좋아요 */
function likeReply(url){
	console.log(url);
	location.href= url;
}

/* 댓글 싫어요 */
function unLikeReply(url){
	//alert(url);
	location.href= url;
}





