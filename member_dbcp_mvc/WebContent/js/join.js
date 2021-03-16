/**
 * joinFrom.jsp 유효성 검증

아이디는 5~12자리 - 영어대소문자,숫자,특수문자 허용
비밀번호는 8~15자리 - 영어대소문자,숫자,특수문자 허용
비밀번호 확인 - 이전 비밀번호와 동일
이름 : 2~4자리 허용
성별 : 무조건 하나 선택
이메일 : 이메일 검증
 */
$(function() {
	$("#joinform").validate({
		rules:{
			userid : { //영어대소문자,숫자,특수문자 허용
				required:true,
				validId:true,
				remote:{
					url:"../checkId.do",
					type:"post",
					data:{
						userid:function(){
							return $("#userid").val();
						}
					}
				}
			},
			password : {
				required:true,
				validPwd:true
			},
			confirm_password : {
				required:true,
				validPwd:true,
				equalTo: "#password"
			},
			name : {
				required:true,
				rangelength:[2,4]
			},
			gender : {
				required:true
			},
			email : {
				required:true,
				eamil:true
			}
		

		},//rules
		
		messages:{
			userid : {
				required: "아이디는 필수 속성입니다.",
				remote:"아이디 사용 불가"
				
			},
			password : {
				required: "비밀번호는 필수 속성입니다."
				
			},
			confirm_password : {
				required:"비밀번호는 필수 속성입니다.",
				equalTo: "이전 비밀번호와 다릅니다"
			},
			name : {
				required:"이름은 필수 속성입니다",
				rangelength: "이름은 2~4자리 사이입니다."
			},
			gender : {
				required:"성별을 선택해 주세요"
			},
			email : {
				required:"이메일은 필수 속성입니다"
			}
			

		},//messages
		errorPlacement:function(error,element) {
			$(element).closest("form")
					  .find("small[id='"+element.attr('id')+"']")
					  .append(error);
		},
		success : function(label) {
			var name = label.attr('for');
			label.text(name+ ' is ok!');
		}
		
		
	});//#joinform").validate
});//function()


$.validator.addMethod("validId", function(value) {
	var regId = /^(?=.*[A-Za-z])(?=.*[\d])(?=.*[!@#$%^*])[A-Za-z\d!@#$%^&*]{5,12}$/;
	return regId.test(value);
}, "아이디를 영대소문자,숫자 조합으로5~12자리로 만들어 주세요"); 
$.validator.addMethod("validPwd", function(value) {
	var regPwd = /^(?=.*[A-Za-z])(?=.*[\d])(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,15}$/;
	return regPwd.test(value);
}, "비밀번호를 영대소문자,숫자 조합으로8~15자리로 만들어 주세요");
$.validator.addMethod("validName", function(value) {
	var regName = /^(?=.*[가-힣])[가-힣]{2,4}$/;
	return regName.test(value);
}, "이름을 2~4자리로 만들어 주세요");
$.validator.addMethod("eamil", function(value) {
    var regEmail = /(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))/;
	return regEamil.test(value);
}, "email을 확인해 주세요.");


$(function() {
	$("#modifyform").validate({
		rules:{
			current_password : {
				required:true,
				validPwd:true,
				equalTo: "#password"
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
		},
		
		messages:{
			current_password : {
				required:"비밀번호는 필수 속성입니다.",
				equalTo: "이전 비밀번호와 다릅니다"
			},
			new_password : {
				required:"비밀번호는 필수 속성입니다."
			},
			confirm_password : {
				required:"비밀번호는 필수 속성입니다.",
				equalTo: "이전 비밀번호와 다릅니다"
			}
		},
		
		errorPlacement:function(error,element) {
			$(element).closest("form")
					  .find("small[id='"+element.attr('id')+"']")
					  .append(error);
		}


	});//"#modifyform").validate
});//function()
