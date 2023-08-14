/* 로그인 */
function chk_form() {
	document.getElementById("check_form").submit();
}

/* 팝업창닫기 */
document.querySelector('.modal_btn_close').addEventListener('click', function(){
  document.querySelector('.modal_mask_cover').style.display = 'none';
  document.querySelector('.modal_area_wrap').style.display = 'none';
});

/* 수정기능 */
let modifyClick = document.querySelector("#main_modify .btn_update");
modifyClick.addEventListener("click",function(){
	alert("정말로 수정하시겠습니까?");
})

/* 탈퇴기능 */
let delClick = document.querySelector("#main_modify .btn_delete");
delClick.addEventListener("click",function(){
	alert("정말로 탈퇴하시겠습니까");
})