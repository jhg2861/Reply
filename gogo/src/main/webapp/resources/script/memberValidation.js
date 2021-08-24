/**
 * 
 */
 
 function memberCheck() {
	let userid   = document.getElementById("userid");
	let userpwd  = document.getElementById("userpwd");
	let username = document.getElementById("username");
	let email    = document.getElementById("email");
	let birth    = document.getElementById("birth");
	let phone1   = document.getElementById("phone1");
	let phone2   = document.getElementById("phone2");

	if(userid.value.trim().length < 3 || userid.value.trim().length > 5) {
		alert("아이디는 3~5글자 크기로 입력해 주세요");
		userid.select();
		return false;
	}
	
	if(userpwd.value.trim().length < 3 || userpwd.value.trim().length > 5) {
		alert("비밀번호는 3~5글자 크기로 입력해 주세요");
		userpwd.select();
		return false;
	}
	
	if(username.value.trim().length == 0) {
		alert("이름을 입력해 주세요");
		username.select();
		return false;
	}
	
	if(email.value.trim().length == 0) {
		alert("이메일을 입력해 주세요");
		email.select();
		return false;
	}
	
	if(birth.value.length == 0) {
		alert("생년월일을 선택해 주세요");
		birth.focus();
		return false;
	}

	if(isNaN(phone2.value.trim()) || phone2.value.length != 8) {
		alert("전화번호는 -없이 숫자 8자리만 입력해주세요");
		phone2.select();
		return false;
	}
	
	let phone = phone1.value.trim() + phone2.value.trim();
	document.getElementById("phone").value = phone;
	
	return true;
}
 
 
function loginCheck() {
	alert("asd");
	let userid   = document.getElementById("userid");
	let userpwd  = document.getElementById("userpwd");

	if(userid.value.trim().length < 3 || userid.value.trim().length > 5) {
		alert("아이디는 3~5글자 크기로 입력해 주세요");
		userid.select();
		return false;
	}
	
	if(userpwd.value.trim().length < 3 || userpwd.value.trim().length > 5) {
		alert("비밀번호는 3~5글자 크기로 입력해 주세요");
		userpwd.select();
		return false;
	}
	
	return true;
}
