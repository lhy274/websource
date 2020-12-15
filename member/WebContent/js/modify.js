/**
 * modifyForm.jsp 유효성 검증

비밀번호는 8~15자리 - 영어대소문자,숫자,특수문자 허용
비밀번호 확인 - 이전 비밀번호와 동일

 */
$(function() {
	$("#modifyform").validate({
		//규칙정의 - 이름
		rules:{
			current_password : {
				required:true,
				validPwd:true

			},
			new_password : {
				required:true,
				validPwd:true
			},
			confirm_password : {
				required:true,
				validPwd:true,
				equalTo: "#new_password"
			}
		},//rules
		//규칙에 대한 메세지 정의
		messages:{
			current_password : {
				required:"현재 비밀번호를 입력해 주세요."
				
			},
			new_password : {
				required:"새로운 비밀번호를 입력해 주세요."
			},
			confirm_password : {
				required:"새로운 비밀번호를 입력해 주세요.",
				equalTo: "이전 비밀번호와 다릅니다"
			}
		},//messages
		
		errorPlacement:function(error,element) {
			$(element).closest("form")
					  .find("small[id='"+element.attr('id')+"']")
					  .append(error);
		},//messages
		
		success : function(label) {
			var name = label.attr('for');
			label.text(name+ ' is ok!');
		}


	});//"#modifyform").validate
});//function()

//사용자 검증 메소드 추가
$.validator.addMethod("validPwd", function(value) {
	var regPwd = /^(?=.*[A-Za-z])(?=.*[\d])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,15}$/;
	return regPwd.test(value);
}, "비밀번호를 영대소문자,숫자 조합으로8~15자리로 만들어 주세요");
