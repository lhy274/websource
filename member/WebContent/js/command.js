/**
 * 
 */
$(function() {
	//logout 클릭시 logoutPro.jsp로 이동
	$("#logout").click(function() {
		location.href='logoutPro.jsp';
		//$(this).attr("src","logoutPro.jsp");
	})
})

$(function() {
	$("#modify").click(function() {
		location.href='modifyForm.jsp';
	})
	$("#leave").click(function() {
		location.href='leaveForm.jsp';
	})
})
